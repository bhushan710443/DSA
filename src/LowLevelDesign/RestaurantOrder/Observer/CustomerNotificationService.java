package LowLevelDesign.RestaurantOrder.Observer;

import LowLevelDesign.RestaurantOrder.Enum.OrderStatus;

/**
 * Sends notifications to customers (SMS/Push/Email).
 * In production: integrates with Twilio, Firebase, SendGrid, etc.
 */
public class CustomerNotificationService implements OrderObserver {

    @Override
    public void onOrderPlaced(String orderId, String customerName, int itemCount) {
        System.out.println("[SMS -> " + customerName + "]: Your order #" + orderId +
                " (" + itemCount + " items) has been placed successfully!");
    }

    @Override
    public void onOrderStatusChanged(String orderId, OrderStatus oldStatus, OrderStatus newStatus) {
        String message;
        if (newStatus == OrderStatus.CONFIRMED) {
            message = "Your order is confirmed! Kitchen is on it.";
        } else if (newStatus == OrderStatus.PREPARING) {
            message = "Your food is being prepared";
        } else if (newStatus == OrderStatus.READY) {
            message = "Your order is ready!";
        } else if (newStatus == OrderStatus.OUT_FOR_DELIVERY) {
            message = "Your order is on its way!";
        } else if (newStatus == OrderStatus.DELIVERED) {
            message = "Order delivered! Enjoy your meal!";
        } else if (newStatus == OrderStatus.PICKED_UP) {
            message = "Order picked up! Enjoy!";
        } else {
            message = "Order status updated to: " + newStatus;
        }
        System.out.println("SMS -> Order #" + orderId + ": " + message);
    }

    @Override
    public void onOrderCancelled(String orderId, String customerName, String reason) {
        System.out.println("[SMS -> " + customerName + "]: Your order #" + orderId +
                " has been cancelled. Reason: " + reason + ". Refund initiated.");
    }

    @Override
    public void onPaymentCompleted(String orderId, double amount, String method) {
        System.out.println("[SMS]: Payment of Rs." + String.format("%.2f", amount) +
                " received via " + method + " for order #" + orderId);
    }
}

