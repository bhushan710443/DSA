package LowLevelDesign.ParkingLot.Strategy;

import LowLevelDesign.ParkingLot.Entity.ParkingTicket;

/**
 * Strategy Pattern: Different pricing strategies can be implemented.
 * e.g., Hourly, Flat rate, Weekend pricing, etc.
 */
public interface PricingStrategy {
    double calculatePrice(ParkingTicket ticket);
}

