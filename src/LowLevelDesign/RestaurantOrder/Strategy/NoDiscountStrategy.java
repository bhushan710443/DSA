package LowLevelDesign.RestaurantOrder.Strategy;

import LowLevelDesign.RestaurantOrder.Entity.Order;

/**
 * No discount strategy (default).
 * Follows Null Object Pattern — avoids null checks in service.
 */
public class NoDiscountStrategy implements PricingStrategy {

    @Override
    public double calculateDiscount(Order order) {
        return 0;
    }

    @Override
    public String getStrategyName() {
        return "No Discount";
    }
}

