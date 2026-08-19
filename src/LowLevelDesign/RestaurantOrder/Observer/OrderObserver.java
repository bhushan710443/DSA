package LowLevelDesign.RestaurantOrder.Observer;

import LowLevelDesign.RestaurantOrder.Enum.OrderStatus;

/**
 * Observer Pattern: Interface for order event listeners.
 *
 * Interview point: "Observer decouples notification/display logic from order processing.
 * Kitchen display, customer SMS, and analytics can all independently react to events."
 */
public interface OrderObserver {

    void onOrderPlaced(String orderId, String customerName, int itemCount);

    void onOrderStatusChanged(String orderId, OrderStatus oldStatus, OrderStatus newStatus);

    void onOrderCancelled(String orderId, String customerName, String reason);

    void onPaymentCompleted(String orderId, double amount, String method);
}

