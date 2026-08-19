package LowLevelDesign.RestaurantOrder.Service;

import LowLevelDesign.RestaurantOrder.Entity.*;
import LowLevelDesign.RestaurantOrder.Enum.*;
import LowLevelDesign.RestaurantOrder.Exceptions.*;
import LowLevelDesign.RestaurantOrder.Observer.OrderObserver;
import LowLevelDesign.RestaurantOrder.Repository.*;
import LowLevelDesign.RestaurantOrder.Strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Core service for Restaurant Order Management System.
 *
 * Design Principles Applied:
 * ──────────────────────────
 * 1. SRP          → Only business logic, no I/O or UI
 * 2. OCP          → New pricing strategies without modifying this class
 * 3. DIP          → Depends on abstractions (PricingStrategy, OrderObserver)
 * 4. Observer     → Decoupled notifications (customer SMS, kitchen display)
 * 5. Strategy     → Pluggable discount/pricing algorithms
 * 6. Repository   → Data access abstracted from business logic
 * 7. Thread-safe  → Synchronized order state transitions
 *
 * Interview point: "This is the heart of the system. All business rules
 * (validation, pricing, state transitions) live here. The controller
 * just delegates, the repository just stores."
 */
public class OrderService {

    private static final double GST_RATE = 0.05;             // 5% GST on food
    private static final double DELIVERY_CHARGE = 40.0;      // Flat delivery fee
    private static final double FREE_DELIVERY_THRESHOLD = 500.0; // Free delivery above ₹500

    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    private final PricingStrategy pricingStrategy;
    private final List<OrderObserver> observers;

    public OrderService(MenuRepository menuRepository,
                        OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        PaymentRepository paymentRepository,
                        PricingStrategy pricingStrategy) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
        this.pricingStrategy = pricingStrategy;
        this.observers = new ArrayList<>();
    }

    // ─── OBSERVER MANAGEMENT ─────────────────────────────────────────

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    // ─── PLACE ORDER (Thread-safe) ───────────────────────────────────

    public synchronized Order placeOrder(String customerId, OrderType orderType,
                                         List<OrderItemRequest> itemRequests) {
        Customer customer = getValidCustomer(customerId);

        // Validate delivery orders have address
        if (orderType == OrderType.DELIVERY && (customer.getAddress() == null || customer.getAddress().isEmpty())) {
            throw new InvalidOrderStateException("Delivery order requires customer address.");
        }

        // Validate at least one item
        if (itemRequests == null || itemRequests.isEmpty()) {
            throw new InvalidOrderStateException("Order must contain at least one item.");
        }

        // Create order
        String orderId = generateOrderId();
        Order order = new Order(orderId, customer, orderType);

        // Add items (validate availability)
        int maxPrepTime = 0;
        for (OrderItemRequest req : itemRequests) {
            MenuItem menuItem = getValidMenuItem(req.itemId);

            if (!menuItem.isAvailable()) {
                throw new ItemNotAvailableException(
                        "Item '" + menuItem.getName() + "' is currently unavailable.");
            }

            OrderItem orderItem = new OrderItem(menuItem, req.quantity, req.specialInstructions);
            order.addItem(orderItem);
            maxPrepTime = Math.max(maxPrepTime, menuItem.getPreparationTimeMinutes());
        }

        // Set estimated time (max prep time + buffer)
        order.setEstimatedTimeMinutes(maxPrepTime + 5);

        // Calculate pricing
        double discount = pricingStrategy.calculateDiscount(order);
        double tax = order.getSubtotal() * GST_RATE;
        double deliveryCharge = calculateDeliveryCharge(order);
        order.applyPricing(discount, tax, deliveryCharge);

        // Save
        orderRepository.save(order);

        // Notify observers
        notifyOrderPlaced(orderId, customer.getName(), order.getItems().size());

        return order;
    }

    // ─── UPDATE ORDER STATUS (State Machine) ─────────────────────────

    public synchronized Order confirmOrder(String orderId) {
        Order order = getValidOrder(orderId);
        validateTransition(order, OrderStatus.CONFIRMED);

        OrderStatus oldStatus = order.getStatus();
        order.confirm();

        notifyStatusChanged(orderId, oldStatus, OrderStatus.CONFIRMED);
        return order;
    }

    public synchronized Order startPreparing(String orderId) {
        Order order = getValidOrder(orderId);
        validateTransition(order, OrderStatus.PREPARING);

        OrderStatus oldStatus = order.getStatus();
        order.startPreparing();

        notifyStatusChanged(orderId, oldStatus, OrderStatus.PREPARING);
        return order;
    }

    public synchronized Order markReady(String orderId) {
        Order order = getValidOrder(orderId);
        validateTransition(order, OrderStatus.READY);

        OrderStatus oldStatus = order.getStatus();
        order.markReady();

        notifyStatusChanged(orderId, oldStatus, OrderStatus.READY);
        return order;
    }

    public synchronized Order markOutForDelivery(String orderId) {
        Order order = getValidOrder(orderId);

        if (order.getOrderType() != OrderType.DELIVERY) {
            throw new InvalidOrderStateException("Only delivery orders can be marked 'Out for Delivery'.");
        }
        validateTransition(order, OrderStatus.OUT_FOR_DELIVERY);

        OrderStatus oldStatus = order.getStatus();
        order.markOutForDelivery();

        notifyStatusChanged(orderId, oldStatus, OrderStatus.OUT_FOR_DELIVERY);
        return order;
    }

    public synchronized Order markDelivered(String orderId) {
        Order order = getValidOrder(orderId);
        validateTransition(order, OrderStatus.DELIVERED);

        OrderStatus oldStatus = order.getStatus();
        order.markDelivered();

        // Award loyalty points (1 point per ₹50 spent)
        int points = (int) (order.getTotalAmount() / 50);
        order.getCustomer().addLoyaltyPoints(points);

        notifyStatusChanged(orderId, oldStatus, OrderStatus.DELIVERED);
        return order;
    }

    public synchronized Order markPickedUp(String orderId) {
        Order order = getValidOrder(orderId);

        if (order.getOrderType() == OrderType.DELIVERY) {
            throw new InvalidOrderStateException("Delivery orders should use markDelivered().");
        }
        validateTransition(order, OrderStatus.PICKED_UP);

        OrderStatus oldStatus = order.getStatus();
        order.markPickedUp();

        int points = (int) (order.getTotalAmount() / 50);
        order.getCustomer().addLoyaltyPoints(points);

        notifyStatusChanged(orderId, oldStatus, OrderStatus.PICKED_UP);
        return order;
    }

    // ─── CANCEL ORDER ────────────────────────────────────────────────

    public synchronized Order cancelOrder(String orderId, String reason) {
        Order order = getValidOrder(orderId);

        if (!order.isCancellable()) {
            throw new InvalidOrderStateException(
                    "Cannot cancel order in '" + order.getStatus() + "' state. " +
                    "Orders can only be cancelled before preparation starts.");
        }

        order.cancel(reason);

        // Refund payment if already paid
        Payment payment = paymentRepository.getByOrderId(orderId);
        if (payment != null && payment.getStatus() == PaymentStatus.COMPLETED) {
            payment.markRefunded();
        }

        notifyOrderCancelled(orderId, order.getCustomer().getName(), reason);
        return order;
    }

    // ─── PROCESS PAYMENT ─────────────────────────────────────────────

    public synchronized Payment processPayment(String orderId, PaymentMethod method) {
        Order order = getValidOrder(orderId);

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new InvalidOrderStateException("Cannot pay for a cancelled order.");
        }

        // Check if already paid
        Payment existing = paymentRepository.getByOrderId(orderId);
        if (existing != null && existing.getStatus() == PaymentStatus.COMPLETED) {
            throw new PaymentFailedException("Order already paid. Payment ID: " + existing.getPaymentId());
        }

        // Calculate final amount with processing fee
        double processingFee = order.getTotalAmount() * (method.getProcessingFeePercent() / 100.0);
        double finalAmount = order.getTotalAmount() + processingFee;

        // Create payment
        String paymentId = "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Payment payment = new Payment(paymentId, orderId, finalAmount, method);

        // Simulate payment processing (in real system: call payment gateway)
        String txnRef = "TXN" + System.currentTimeMillis();
        payment.markCompleted(txnRef);
        paymentRepository.save(payment);

        // Notify
        notifyPaymentCompleted(orderId, finalAmount, method.name());

        return payment;
    }

    // ─── MENU MANAGEMENT ─────────────────────────────────────────────

    public void addMenuItem(MenuItem item) {
        menuRepository.addItem(item);
    }

    public void updateItemAvailability(String itemId, boolean available) {
        MenuItem item = getValidMenuItem(itemId);
        item.setAvailable(available);
    }

    public List<MenuItem> getMenu() {
        return menuRepository.getAvailableItems();
    }

    public List<MenuItem> getMenuByCategory(MenuCategory category) {
        return menuRepository.getByCategory(category);
    }

    // ─── CUSTOMER MANAGEMENT ─────────────────────────────────────────

    public void registerCustomer(Customer customer) {
        customerRepository.addCustomer(customer);
    }

    // ─── QUERY / STATS ───────────────────────────────────────────────

    public List<Order> getActiveOrders() {
        return orderRepository.getActiveOrders();
    }

    public List<Order> getCustomerOrders(String customerId) {
        return orderRepository.getByCustomerId(customerId);
    }

    public Order getOrderDetails(String orderId) {
        return getValidOrder(orderId);
    }

    public String getOrderBill(String orderId) {
        Order order = getValidOrder(orderId);
        StringBuilder bill = new StringBuilder();
        bill.append("┌─────────────────────────────────────────┐\n");
        bill.append("│        RESTAURANT ORDER BILL            │\n");
        bill.append("├─────────────────────────────────────────┤\n");
        bill.append("│ Order: ").append(order.getOrderId()).append("\n");
        bill.append("│ Customer: ").append(order.getCustomer().getName()).append("\n");
        bill.append("│ Type: ").append(order.getOrderType()).append("\n");
        bill.append("├─────────────────────────────────────────┤\n");
        for (OrderItem item : order.getItems()) {
            bill.append("│ ").append(item).append("\n");
        }
        bill.append("├─────────────────────────────────────────┤\n");
        bill.append(String.format("│ Subtotal:        ₹%.2f%n", order.getSubtotal()));
        if (order.getDiscount() > 0) {
            bill.append(String.format("│ Discount:       -₹%.2f (%s)%n", order.getDiscount(), pricingStrategy.getStrategyName()));
        }
        bill.append(String.format("│ GST (5%%):        ₹%.2f%n", order.getTax()));
        if (order.getDeliveryCharge() > 0) {
            bill.append(String.format("│ Delivery:        ₹%.2f%n", order.getDeliveryCharge()));
        }
        bill.append("├─────────────────────────────────────────┤\n");
        bill.append(String.format("│ TOTAL:           ₹%.2f%n", order.getTotalAmount()));
        bill.append("└─────────────────────────────────────────┘");
        return bill.toString();
    }

    // ─── PRIVATE HELPERS ─────────────────────────────────────────────

    private Customer getValidCustomer(String customerId) {
        Customer customer = customerRepository.getById(customerId);
        if (customer == null) throw new CustomerNotFoundException("Customer not found: " + customerId);
        return customer;
    }

    private MenuItem getValidMenuItem(String itemId) {
        MenuItem item = menuRepository.getById(itemId);
        if (item == null) throw new MenuItemNotFoundException("Menu item not found: " + itemId);
        return item;
    }

    private Order getValidOrder(String orderId) {
        Order order = orderRepository.getById(orderId);
        if (order == null) throw new OrderNotFoundException("Order not found: " + orderId);
        return order;
    }

    private String generateOrderId() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private double calculateDeliveryCharge(Order order) {
        if (order.getOrderType() != OrderType.DELIVERY) return 0;
        return order.getSubtotal() >= FREE_DELIVERY_THRESHOLD ? 0 : DELIVERY_CHARGE;
    }

    /**
     * Validates that the order can transition to the target status.
     * State Machine logic — prevents invalid transitions.
     */
    private void validateTransition(Order order, OrderStatus targetStatus) {
        OrderStatus current = order.getStatus();
        boolean valid;

        if (targetStatus == OrderStatus.CONFIRMED) {
            valid = current == OrderStatus.PLACED;
        } else if (targetStatus == OrderStatus.PREPARING) {
            valid = current == OrderStatus.CONFIRMED;
        } else if (targetStatus == OrderStatus.READY) {
            valid = current == OrderStatus.PREPARING;
        } else if (targetStatus == OrderStatus.OUT_FOR_DELIVERY) {
            valid = current == OrderStatus.READY;
        } else if (targetStatus == OrderStatus.DELIVERED) {
            valid = current == OrderStatus.OUT_FOR_DELIVERY || current == OrderStatus.READY;
        } else if (targetStatus == OrderStatus.PICKED_UP) {
            valid = current == OrderStatus.READY;
        } else if (targetStatus == OrderStatus.CANCELLED) {
            valid = current == OrderStatus.PLACED || current == OrderStatus.CONFIRMED;
        } else {
            valid = false;
        }

        if (!valid) {
            throw new InvalidOrderStateException(
                    "Cannot transition from '" + current + "' to '" + targetStatus + "'.");
        }
    }

    // ─── OBSERVER NOTIFICATIONS ──────────────────────────────────────

    private void notifyOrderPlaced(String orderId, String customerName, int itemCount) {
        for (OrderObserver observer : observers) {
            observer.onOrderPlaced(orderId, customerName, itemCount);
        }
    }

    private void notifyStatusChanged(String orderId, OrderStatus oldStatus, OrderStatus newStatus) {
        for (OrderObserver observer : observers) {
            observer.onOrderStatusChanged(orderId, oldStatus, newStatus);
        }
    }

    private void notifyOrderCancelled(String orderId, String customerName, String reason) {
        for (OrderObserver observer : observers) {
            observer.onOrderCancelled(orderId, customerName, reason);
        }
    }

    private void notifyPaymentCompleted(String orderId, double amount, String method) {
        for (OrderObserver observer : observers) {
            observer.onPaymentCompleted(orderId, amount, method);
        }
    }

    // ─── REQUEST DTO (used for placing orders) ───────────────────────

    public static class OrderItemRequest {
        public final String itemId;
        public final int quantity;
        public final String specialInstructions;

        public OrderItemRequest(String itemId, int quantity, String specialInstructions) {
            this.itemId = itemId;
            this.quantity = quantity;
            this.specialInstructions = specialInstructions;
        }

        public OrderItemRequest(String itemId, int quantity) {
            this(itemId, quantity, null);
        }
    }
}

