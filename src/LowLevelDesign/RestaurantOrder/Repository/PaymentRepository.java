package LowLevelDesign.RestaurantOrder.Repository;

import LowLevelDesign.RestaurantOrder.Entity.Payment;
import LowLevelDesign.RestaurantOrder.Enum.PaymentStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PaymentRepository {

    private final Map<String, Payment> payments = new ConcurrentHashMap<>();

    public void save(Payment payment) {
        payments.put(payment.getPaymentId(), payment);
    }

    public Payment getById(String paymentId) {
        return payments.get(paymentId);
    }

    public Payment getByOrderId(String orderId) {
        return payments.values().stream()
                .filter(p -> p.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public List<Payment> getByStatus(PaymentStatus status) {
        return payments.values().stream()
                .filter(p -> p.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments.values());
    }
}

