package LowLevelDesign.RestaurantOrder.Entity;

import LowLevelDesign.RestaurantOrder.Enum.OrderStatus;
import LowLevelDesign.RestaurantOrder.Enum.OrderType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Core entity: Represents a restaurant order.
 * Contains order items, customer info, status, pricing breakdown.
 *
 * Interview point: "Order is the aggregate root — all modifications
 * to order items go through the Order entity to maintain consistency."
 */
public class Order {

    private final String orderId;
    private final Customer customer;
    private final OrderType orderType;
    private final List<OrderItem> items;
    private final LocalDateTime placedAt;

    private OrderStatus status;
    private double subtotal;        // Sum of item prices
    private double discount;        // From pricing strategy
    private double tax;             // GST (5% on food)
    private double deliveryCharge;  // For delivery orders
    private double totalAmount;     // Final payable amount

    private LocalDateTime confirmedAt;
    private LocalDateTime preparedAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime cancelledAt;
    private String cancellationReason;

    private int estimatedTimeMinutes; // Total estimated preparation time

    public Order(String orderId, Customer customer, OrderType orderType) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderType = orderType;
        this.items = new ArrayList<>();
        this.placedAt = LocalDateTime.now();
        this.status = OrderStatus.PLACED;
        this.subtotal = 0;
        this.discount = 0;
        this.tax = 0;
        this.deliveryCharge = 0;
        this.totalAmount = 0;
    }

    // ─── Item Management ──────────────────────────────────────────

    public void addItem(OrderItem item) {
        items.add(item);
        recalculateSubtotal();
    }

    public void removeItem(String itemId) {
        items.removeIf(i -> i.getMenuItem().getItemId().equals(itemId));
        recalculateSubtotal();
    }

    private void recalculateSubtotal() {
        this.subtotal = items.stream()
                .mapToDouble(OrderItem::getItemTotal)
                .sum();
    }

    // ─── Status Transitions ───────────────────────────────────────

    public void confirm() {
        this.status = OrderStatus.CONFIRMED;
        this.confirmedAt = LocalDateTime.now();
    }

    public void startPreparing() {
        this.status = OrderStatus.PREPARING;
    }

    public void markReady() {
        this.status = OrderStatus.READY;
        this.preparedAt = LocalDateTime.now();
    }

    public void markOutForDelivery() {
        this.status = OrderStatus.OUT_FOR_DELIVERY;
    }

    public void markDelivered() {
        this.status = OrderStatus.DELIVERED;
        this.deliveredAt = LocalDateTime.now();
    }

    public void markPickedUp() {
        this.status = OrderStatus.PICKED_UP;
        this.deliveredAt = LocalDateTime.now();
    }

    public void cancel(String reason) {
        this.status = OrderStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
        this.cancellationReason = reason;
    }

    // ─── Pricing ──────────────────────────────────────────────────

    public void applyPricing(double discount, double tax, double deliveryCharge) {
        this.discount = discount;
        this.tax = tax;
        this.deliveryCharge = deliveryCharge;
        this.totalAmount = subtotal - discount + tax + deliveryCharge;
    }

    // ─── Getters ──────────────────────────────────────────────────

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTax() {
        return tax;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }

    public void setEstimatedTimeMinutes(int estimatedTimeMinutes) {
        this.estimatedTimeMinutes = estimatedTimeMinutes;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public boolean isCancellable() {
        // Can only cancel before preparation starts
        return status == OrderStatus.PLACED || status == OrderStatus.CONFIRMED;
    }

    @Override
    public String toString() {
        return "Order{id='" + orderId + "', customer='" + customer.getName() +
                "', type=" + orderType + ", status=" + status +
                ", items=" + items.size() + ", total=₹" + String.format("%.2f", totalAmount) + "}";
    }
}

