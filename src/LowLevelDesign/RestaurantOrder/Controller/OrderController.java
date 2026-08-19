package LowLevelDesign.RestaurantOrder.Controller;

import LowLevelDesign.RestaurantOrder.Entity.*;
import LowLevelDesign.RestaurantOrder.Enum.*;
import LowLevelDesign.RestaurantOrder.Service.OrderService;
import LowLevelDesign.RestaurantOrder.Service.OrderService.OrderItemRequest;

import java.util.List;

/**
 * Controller layer — Entry point / API for the Restaurant Order System.
 * Delegates all business logic to OrderService.
 *
 * Interview point: "In Spring Boot, each method maps to a REST endpoint.
 * E.g., placeOrder → POST /api/orders, getOrderDetails → GET /api/orders/{id}"
 */
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ─── Order Operations ─────────────────────────────────────────

    public Order placeOrder(String customerId, OrderType type, List<OrderItemRequest> items) {
        return orderService.placeOrder(customerId, type, items);
    }

    public Order confirmOrder(String orderId) {
        return orderService.confirmOrder(orderId);
    }

    public Order startPreparing(String orderId) {
        return orderService.startPreparing(orderId);
    }

    public Order markReady(String orderId) {
        return orderService.markReady(orderId);
    }

    public Order markOutForDelivery(String orderId) {
        return orderService.markOutForDelivery(orderId);
    }

    public Order markDelivered(String orderId) {
        return orderService.markDelivered(orderId);
    }

    public Order markPickedUp(String orderId) {
        return orderService.markPickedUp(orderId);
    }

    public Order cancelOrder(String orderId, String reason) {
        return orderService.cancelOrder(orderId, reason);
    }

    // ─── Payment ──────────────────────────────────────────────────

    public Payment processPayment(String orderId, PaymentMethod method) {
        return orderService.processPayment(orderId, method);
    }

    // ─── Query ────────────────────────────────────────────────────

    public Order getOrderDetails(String orderId) {
        return orderService.getOrderDetails(orderId);
    }

    public String getOrderBill(String orderId) {
        return orderService.getOrderBill(orderId);
    }

    public List<Order> getActiveOrders() {
        return orderService.getActiveOrders();
    }

    public List<Order> getCustomerOrders(String customerId) {
        return orderService.getCustomerOrders(customerId);
    }

    // ─── Menu ─────────────────────────────────────────────────────

    public List<MenuItem> getMenu() {
        return orderService.getMenu();
    }

    public List<MenuItem> getMenuByCategory(MenuCategory category) {
        return orderService.getMenuByCategory(category);
    }

    public void addMenuItem(MenuItem item) {
        orderService.addMenuItem(item);
    }

    public void updateItemAvailability(String itemId, boolean available) {
        orderService.updateItemAvailability(itemId, available);
    }

    // ─── Customer ─────────────────────────────────────────────────

    public void registerCustomer(Customer customer) {
        orderService.registerCustomer(customer);
    }
}

