package LowLevelDesign.LLDMust.ParkingLot.Strategy;

import LowLevelDesign.LLDMust.ParkingLot.Entity.ParkingTicket;

/**
 * Strategy Pattern: Different pricing strategies can be implemented.
 * e.g., Hourly, Flat rate, Weekend pricing, etc.
 */
public interface PricingStrategy {
    double calculatePrice(ParkingTicket ticket);
}

