package LowLevelDesign.RestaurantOrder.Strategy;

import LowLevelDesign.RestaurantOrder.Entity.Order;

/**
 * Percentage discount: X% off on total (with optional max cap).
 * e.g., 20% off up to ₹150 maximum discount.
 */
public class PercentageDiscountStrategy implements PricingStrategy {

    private final double percentage;
    private final double maxDiscount; // Cap on discount amount

    public PercentageDiscountStrategy(double percentage, double maxDiscount) {
        this.percentage = percentage;
        this.maxDiscount = maxDiscount;
    }

    public PercentageDiscountStrategy(double percentage) {
        this(percentage, Double.MAX_VALUE); // No cap
    }

    @Override
    public double calculateDiscount(Order order) {
        double discount = order.getSubtotal() * (percentage / 100.0);
        return Math.min(discount, maxDiscount);
    }

    @Override
    public String getStrategyName() {
        return percentage + "% off" + (maxDiscount < Double.MAX_VALUE ? " (max ₹" + maxDiscount + ")" : "");
    }
}

