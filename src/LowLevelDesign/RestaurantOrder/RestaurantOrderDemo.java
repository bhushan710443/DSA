package LowLevelDesign.RestaurantOrder;

import LowLevelDesign.RestaurantOrder.Controller.OrderController;
import LowLevelDesign.RestaurantOrder.Entity.*;
import LowLevelDesign.RestaurantOrder.Enum.*;
import LowLevelDesign.RestaurantOrder.Observer.CustomerNotificationService;
import LowLevelDesign.RestaurantOrder.Observer.KitchenDisplayService;
import LowLevelDesign.RestaurantOrder.Repository.*;
import LowLevelDesign.RestaurantOrder.Service.OrderService;
import LowLevelDesign.RestaurantOrder.Service.OrderService.OrderItemRequest;
import LowLevelDesign.RestaurantOrder.Strategy.PercentageDiscountStrategy;

import java.util.List;

/**
 * ╔═══════════════════════════════════════════════════════════════════════════╗
 * ║         RESTAURANT ORDER MANAGEMENT SYSTEM - LOW LEVEL DESIGN            ║
 * ╠═══════════════════════════════════════════════════════════════════════════╣
 * ║                                                                           ║
 * ║  Design Patterns Used:                                                    ║
 * ║  ─────────────────────                                                    ║
 * ║  1. Strategy Pattern   → PricingStrategy (Flat, Percentage, HappyHour)    ║
 * ║  2. Observer Pattern   → OrderObserver (CustomerSMS, KitchenDisplay)      ║
 * ║  3. Repository Pattern → Data access abstraction (Menu, Order, Customer)  ║
 * ║  4. State Machine      → Order status transitions with validation         ║
 * ║  5. Layered Arch       → Controller → Service → Repository                ║
 * ║                                                                           ║
 * ║  SOLID Principles:                                                        ║
 * ║  ─────────────────                                                        ║
 * ║  S - OrderService handles orders, MenuRepo handles menu data              ║
 * ║  O - New pricing strategies / observers without modifying service          ║
 * ║  L - All PricingStrategy implementations are interchangeable              ║
 * ║  I - Focused interfaces (PricingStrategy, OrderObserver)                  ║
 * ║  D - All dependencies injected via constructor                            ║
 * ║                                                                           ║
 * ║  Key Features:                                                            ║
 * ║  ─────────────                                                            ║
 * ║  • Place order with multiple items + special instructions                 ║
 * ║  • Order status lifecycle (State Machine with validation)                 ║
 * ║  • Pricing with discount strategies (Flat, %, HappyHour)                  ║
 * ║  • GST calculation (5%) + delivery charges                                ║
 * ║  • Payment processing with multiple methods (CASH/CARD/UPI/WALLET)        ║
 * ║  • Order cancellation (only before preparation)                           ║
 * ║  • Observer notifications (customer SMS + kitchen display)                ║
 * ║  • Loyalty points system                                                  ║
 * ║  • Menu availability management                                           ║
 * ║  • Thread-safe state transitions                                          ║
 * ║  • Bill generation with pricing breakdown                                 ║
 * ║                                                                           ║
 * ╚═══════════════════════════════════════════════════════════════════════════╝
 */
public class RestaurantOrderDemo {

    public static void main(String[] args) {

        // ─── 1. Setup Repositories ───────────────────────────────────
        MenuRepository menuRepository = new MenuRepository();
        OrderRepository orderRepository = new OrderRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        PaymentRepository paymentRepository = new PaymentRepository();

        // ─── 2. Setup Strategy (swap to change discount logic) ───────
        // Option A: Flat ₹50 off on orders above ₹300
        // Option B: 20% off capped at ₹150
        PercentageDiscountStrategy pricingStrategy = new PercentageDiscountStrategy(20, 150);

        // ─── 3. Create Service & Controller ──────────────────────────
        OrderService orderService = new OrderService(
                menuRepository, orderRepository, customerRepository,
                paymentRepository, pricingStrategy);

        // ─── 4. Register Observers ───────────────────────────────────
        orderService.addObserver(new CustomerNotificationService());
        orderService.addObserver(new KitchenDisplayService());

        OrderController controller = new OrderController(orderService);

        // ─── 5. Initialize Data ──────────────────────────────────────
        initializeMenu(controller);
        initializeCustomers(controller);

        // ─── 6. Demo Scenarios ───────────────────────────────────────
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("      RESTAURANT ORDER MANAGEMENT SYSTEM DEMO");
        System.out.println("═══════════════════════════════════════════════════════════\n");

        // ─── SCENARIO 1: Place a Delivery Order ──────────────────────
        System.out.println("─── SCENARIO 1: PLACE DELIVERY ORDER ───────────────────\n");

        List<OrderItemRequest> items1 = List.of(
                new OrderItemRequest("M1", 2, "extra spicy"),    // Paneer Tikka x2
                new OrderItemRequest("M4", 1),                   // Butter Chicken x1
                new OrderItemRequest("M7", 2),                   // Naan x2
                new OrderItemRequest("M9", 1, "less sugar")      // Gulab Jamun x1
        );

        Order order1 = controller.placeOrder("C1", OrderType.DELIVERY, items1);
        System.out.println("\n📝 Order placed: " + order1);
        System.out.println();

        // Print bill
        System.out.println(controller.getOrderBill(order1.getOrderId()));
        System.out.println();

        // ─── SCENARIO 2: Order Lifecycle (State Machine) ─────────────
        System.out.println("\n─── SCENARIO 2: ORDER LIFECYCLE ────────────────────────\n");

        controller.confirmOrder(order1.getOrderId());
        controller.startPreparing(order1.getOrderId());
        controller.markReady(order1.getOrderId());
        controller.markOutForDelivery(order1.getOrderId());
        controller.markDelivered(order1.getOrderId());
        System.out.println("\n✅ Order fully delivered! Loyalty points earned.");
        System.out.println();

        // ─── SCENARIO 3: Payment Processing ──────────────────────────
        System.out.println("─── SCENARIO 3: PAYMENT ────────────────────────────────\n");

        Payment payment = controller.processPayment(order1.getOrderId(), PaymentMethod.UPI);
        System.out.println("💰 " + payment);
        System.out.println();

        // ─── SCENARIO 4: Dine-In Order (no delivery charge) ──────────
        System.out.println("─── SCENARIO 4: DINE-IN ORDER ──────────────────────────\n");

        List<OrderItemRequest> items2 = List.of(
                new OrderItemRequest("M3", 1),   // Veg Spring Roll
                new OrderItemRequest("M5", 1),   // Biryani
                new OrderItemRequest("M10", 2)   // Masala Chai x2
        );

        Order order2 = controller.placeOrder("C2", OrderType.DINE_IN, items2);
        System.out.println("\n📝 " + order2);
        System.out.println(controller.getOrderBill(order2.getOrderId()));
        System.out.println();

        // Quick lifecycle for dine-in
        controller.confirmOrder(order2.getOrderId());
        controller.startPreparing(order2.getOrderId());
        controller.markReady(order2.getOrderId());
        controller.markPickedUp(order2.getOrderId());
        System.out.println();

        // ─── SCENARIO 5: Cancel Order ────────────────────────────────
        System.out.println("─── SCENARIO 5: CANCEL ORDER ───────────────────────────\n");

        List<OrderItemRequest> items3 = List.of(
                new OrderItemRequest("M4", 1),
                new OrderItemRequest("M8", 1)
        );
        Order order3 = controller.placeOrder("C3", OrderType.TAKEAWAY, items3);
        System.out.println();

        // Pay first
        controller.processPayment(order3.getOrderId(), PaymentMethod.CARD);
        System.out.println();

        // Cancel (before preparation) → should refund
        controller.cancelOrder(order3.getOrderId(), "Customer changed mind");
        System.out.println("✅ Order cancelled and refund initiated.\n");

        // ─── SCENARIO 6: Invalid State Transition ────────────────────
        System.out.println("─── SCENARIO 6: ERROR HANDLING ─────────────────────────\n");

        // Try to cancel a delivered order
        try {
            controller.cancelOrder(order1.getOrderId(), "too late");
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }

        // Try to place order with unavailable item
        controller.updateItemAvailability("M4", false); // Butter Chicken sold out
        try {
            List<OrderItemRequest> badItems = List.of(new OrderItemRequest("M4", 1));
            controller.placeOrder("C1", OrderType.TAKEAWAY, badItems);
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }

        // Invalid customer
        try {
            controller.placeOrder("C99", OrderType.DINE_IN, List.of(new OrderItemRequest("M1", 1)));
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }

        // Skip state (PLACED → READY without CONFIRMED)
        List<OrderItemRequest> items4 = List.of(new OrderItemRequest("M1", 1));
        Order order4 = controller.placeOrder("C1", OrderType.TAKEAWAY, items4);
        try {
            controller.markReady(order4.getOrderId()); // Should fail
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
        System.out.println();

        // ─── SCENARIO 7: View Menu ───────────────────────────────────
        System.out.println("─── SCENARIO 7: MENU & STATS ──────────────────────────\n");

        System.out.println("📋 Available Menu Items:");
        controller.getMenu().forEach(item -> System.out.println("   " + item));
        System.out.println();

        System.out.println("📊 Active Orders: " + controller.getActiveOrders().size());
        System.out.println("📊 C1's Order History: " + controller.getCustomerOrders("C1").size() + " orders");
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("              DEMO COMPLETED SUCCESSFULLY");
        System.out.println("═══════════════════════════════════════════════════════════");

        /*
         * ══════════════════════════════════════════════════════════════════
         * INTERVIEW TALKING POINTS:
         * ══════════════════════════════════════════════════════════════════
         *
         * 1. STRATEGY PATTERN → PricingStrategy
         *    "Discount logic is pluggable. Restaurant can switch between
         *     flat discount, percentage, happy hour, or loyalty-based
         *     without changing service code."
         *
         * 2. OBSERVER PATTERN → OrderObserver
         *    "Customer SMS and Kitchen Display are independent observers.
         *     Adding analytics/billing observer = zero change to service."
         *
         * 3. STATE MACHINE → Order Status Transitions
         *    "I validate every transition. PLACED→READY is invalid.
         *     Must go PLACED→CONFIRMED→PREPARING→READY. Prevents bugs."
         *
         * 4. THREAD SAFETY → synchronized state changes
         *    "Multiple kitchen staff can't accidentally mark the same order
         *     as ready twice. In production: use optimistic locking / versioning."
         *
         * 5. LAYERED ARCHITECTURE → Controller → Service → Repository
         *    "Controller = thin API layer. Service = all business rules.
         *     Repository = data access. I can swap DB without touching service."
         *
         * 6. CANCELLATION POLICY
         *    "Orders can only be cancelled before PREPARING.
         *     Auto-refund if payment was already made."
         *
         * 7. PRICING BREAKDOWN
         *    "Subtotal + Strategy Discount + GST + Delivery Charge = Total.
         *     Free delivery above ₹500 (configurable threshold)."
         *
         * 8. LOYALTY POINTS
         *    "1 point per ₹50 spent, awarded on delivery/pickup.
         *     Could add LoyaltyDiscountStrategy to redeem points."
         *
         * 9. EXTENSIBILITY:
         *    - Add CouponCodeStrategy (validate + apply coupon)
         *    - Add SurgeChargeStrategy (peak hours)
         *    - Add DeliveryPartner entity (assign/track delivery)
         *    - Add TableReservation for dine-in
         *    - Add Kitchen queue priority (VIP orders first)
         *    - Add Order rating/feedback system
         *
         * ══════════════════════════════════════════════════════════════════
         */
    }

    // ─── DATA INITIALIZATION ─────────────────────────────────────────

    private static void initializeMenu(OrderController controller) {
        // Starters
        controller.addMenuItem(new MenuItem("M1", "Paneer Tikka", "Grilled cottage cheese with spices",
                MenuCategory.STARTER, 220, 15));
        controller.addMenuItem(new MenuItem("M2", "Chicken Wings", "Crispy fried wings with dip",
                MenuCategory.STARTER, 280, 12));
        controller.addMenuItem(new MenuItem("M3", "Veg Spring Roll", "Crispy rolls with vegetable filling",
                MenuCategory.STARTER, 180, 10));

        // Main Course
        controller.addMenuItem(new MenuItem("M4", "Butter Chicken", "Creamy tomato-based chicken curry",
                MenuCategory.MAIN_COURSE, 350, 20));
        controller.addMenuItem(new MenuItem("M5", "Veg Biryani", "Fragrant rice with mixed vegetables",
                MenuCategory.MAIN_COURSE, 280, 25));
        controller.addMenuItem(new MenuItem("M6", "Dal Makhani", "Creamy black lentils",
                MenuCategory.MAIN_COURSE, 220, 18));

        // Sides
        controller.addMenuItem(new MenuItem("M7", "Butter Naan", "Soft bread with butter",
                MenuCategory.SIDE, 50, 5));
        controller.addMenuItem(new MenuItem("M8", "Jeera Rice", "Cumin-flavored rice",
                MenuCategory.SIDE, 120, 10));

        // Desserts
        controller.addMenuItem(new MenuItem("M9", "Gulab Jamun", "Sweet milk dumplings in syrup",
                MenuCategory.DESSERT, 150, 5));

        // Beverages
        controller.addMenuItem(new MenuItem("M10", "Masala Chai", "Spiced Indian tea",
                MenuCategory.BEVERAGE, 60, 5));
        controller.addMenuItem(new MenuItem("M11", "Mango Lassi", "Sweet yogurt mango drink",
                MenuCategory.BEVERAGE, 120, 5));
    }

    private static void initializeCustomers(OrderController controller) {
        controller.registerCustomer(new Customer("C1", "Bhavesh", "9876543210", "Flat 42, MG Road, Pune"));
        controller.registerCustomer(new Customer("C2", "Priya", "9876543211", "HSR Layout, Bangalore"));
        controller.registerCustomer(new Customer("C3", "Raj", "9876543212", "Andheri West, Mumbai"));
    }
}


