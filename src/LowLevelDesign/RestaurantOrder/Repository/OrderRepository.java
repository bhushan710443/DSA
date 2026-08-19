package LowLevelDesign.RestaurantOrder.Repository;

import LowLevelDesign.RestaurantOrder.Entity.Order;
import LowLevelDesign.RestaurantOrder.Enum.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class OrderRepository {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order getById(String orderId) {
        return orders.get(orderId);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public List<Order> getByStatus(OrderStatus status) {
        return orders.values().stream()
                .filter(o -> o.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Order> getByCustomerId(String customerId) {
        return orders.values().stream()
                .filter(o -> o.getCustomer().getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    public List<Order> getActiveOrders() {
        return orders.values().stream()
                .filter(o -> o.getStatus() != OrderStatus.DELIVERED &&
                             o.getStatus() != OrderStatus.PICKED_UP &&
                             o.getStatus() != OrderStatus.CANCELLED)
                .collect(Collectors.toList());
    }

    public long countByStatus(OrderStatus status) {
        return orders.values().stream()
                .filter(o -> o.getStatus() == status)
                .count();
    }
}

