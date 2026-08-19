package LowLevelDesign.RestaurantOrder.Observer;

import LowLevelDesign.RestaurantOrder.Enum.OrderStatus;

/**
 * Kitchen Display System (KDS) — shows orders to the chef.
 * Another concrete observer demonstrating the pattern.
 *
 * Interview point: "Kitchen display is an independent observer.
 * Adding a new display/printer requires zero changes to order service."
 */
public class KitchenDisplayService implements OrderObserver {

    @Override
    public void onOrderPlaced(String orderId, String customerName, int itemCount) {
        System.out.println("🍳 KITCHEN DISPLAY: New order #" + orderId +
                " from " + customerName + " (" + itemCount + " items) — PENDING CONFIRMATION");
    }

    @Override
    public void onOrderStatusChanged(String orderId, OrderStatus oldStatus, OrderStatus newStatus) {
        if (newStatus == OrderStatus.CONFIRMED) {
            System.out.println("🍳 KITCHEN DISPLAY: ★ START PREPARING order #" + orderId + " ★");
        } else if (newStatus == OrderStatus.READY) {
            System.out.println("🍳 KITCHEN DISPLAY: ✓ Order #" + orderId + " DONE — ready for pickup/delivery");
        }
    }

    @Override
    public void onOrderCancelled(String orderId, String customerName, String reason) {
        System.out.println("🍳 KITCHEN DISPLAY: ✗ CANCEL order #" + orderId + " — " + reason);
    }

    @Override
    public void onPaymentCompleted(String orderId, double amount, String method) {
        // Kitchen doesn't need payment info — empty implementation
    }
}

