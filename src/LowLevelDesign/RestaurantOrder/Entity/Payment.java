package LowLevelDesign.RestaurantOrder.Entity;

import LowLevelDesign.RestaurantOrder.Enum.PaymentMethod;
import LowLevelDesign.RestaurantOrder.Enum.PaymentStatus;

import java.time.LocalDateTime;

/**
 * Represents a payment transaction for an order.
 */
public class Payment {

    private final String paymentId;
    private final String orderId;
    private final double amount;
    private final PaymentMethod method;
    private PaymentStatus status;
    private final LocalDateTime initiatedAt;
    private LocalDateTime completedAt;
    private String transactionRef; // External payment gateway reference

    public Payment(String paymentId, String orderId, double amount, PaymentMethod method) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.PENDING;
        this.initiatedAt = LocalDateTime.now();
    }

    public void markCompleted(String transactionRef) {
        this.status = PaymentStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
        this.transactionRef = transactionRef;
    }

    public void markFailed() {
        this.status = PaymentStatus.FAILED;
    }

    public void markRefunded() {
        this.status = PaymentStatus.REFUNDED;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getInitiatedAt() {
        return initiatedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    @Override
    public String toString() {
        return "Payment{id='" + paymentId + "', amount=₹" + amount +
                ", method=" + method + ", status=" + status + "}";
    }
}

