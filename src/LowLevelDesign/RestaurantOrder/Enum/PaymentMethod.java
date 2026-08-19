package LowLevelDesign.RestaurantOrder.Enum;

/**
 * Payment methods with processing fee (if any).
 */
public enum PaymentMethod {
    CASH(0.0),
    CARD(2.0),          // 2% convenience fee
    UPI(0.0),
    WALLET(0.0);

    private final double processingFeePercent;

    PaymentMethod(double processingFeePercent) {
        this.processingFeePercent = processingFeePercent;
    }

    public double getProcessingFeePercent() {
        return processingFeePercent;
    }
}

