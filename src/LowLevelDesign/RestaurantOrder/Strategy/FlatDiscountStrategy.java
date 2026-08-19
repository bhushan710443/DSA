package LowLevelDesign.RestaurantOrder.Strategy;

import LowLevelDesign.RestaurantOrder.Entity.Order;

/**
 * Flat discount: Fixed amount off (e.g., ₹100 off on orders above ₹500).
 */
public class FlatDiscountStrategy implements PricingStrategy {

    private final double discountAmount;
    private final double minimumOrderAmount;

    public FlatDiscountStrategy(double discountAmount, double minimumOrderAmount) {
        this.discountAmount = discountAmount;
        this.minimumOrderAmount = minimumOrderAmount;
    }

    @Override
    public double calculateDiscount(Order order) {
        if (order.getSubtotal() >= minimumOrderAmount) {
            return discountAmount;
        }
        return 0;
    }

    @Override
    public String getStrategyName() {
        return "Flat ₹" + discountAmount + " off (min order ₹" + minimumOrderAmount + ")";
    }
}

