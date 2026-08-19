package LowLevelDesign.RestaurantOrder.Strategy;

import LowLevelDesign.RestaurantOrder.Entity.Order;

/**
 * Strategy Pattern: Different pricing/discount strategies.
 *
 * Interview point: "I used Strategy so the restaurant can swap
 * discount algorithms without changing the order service.
 * E.g., Happy Hour, Flat Discount, Percentage, Loyalty-based."
 */
public interface PricingStrategy {

    /**
     * Calculate discount amount for the given order.
     * @param order The order to apply discount on
     * @return Discount amount (0 if no discount applies)
     */
    double calculateDiscount(Order order);

    /**
     * Name of this pricing strategy (for display/logging).
     */
    String getStrategyName();
}

