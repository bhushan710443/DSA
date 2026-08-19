package LowLevelDesign.RestaurantOrder.Strategy;

import LowLevelDesign.RestaurantOrder.Entity.Order;

import java.time.LocalTime;

/**
 * Happy Hour strategy: Discount applies only during specific time window.
 * e.g., 3PM-6PM → 30% off on beverages.
 *
 * Interview point: "This shows Strategy + time-based business rules.
 * Restaurant can activate different strategies for lunch vs dinner."
 */
public class HappyHourStrategy implements PricingStrategy {

    private final double percentage;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public HappyHourStrategy(double percentage, LocalTime startTime, LocalTime endTime) {
        this.percentage = percentage;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public double calculateDiscount(Order order) {
        LocalTime now = LocalTime.now();
        if (now.isAfter(startTime) && now.isBefore(endTime)) {
            return order.getSubtotal() * (percentage / 100.0);
        }
        return 0; // Not in happy hour window
    }

    @Override
    public String getStrategyName() {
        return "Happy Hour " + percentage + "% off (" + startTime + " - " + endTime + ")";
    }
}

