package LowLevelDesign.ParkingLot.Strategy;

import LowLevelDesign.ParkingLot.Entity.ParkingTicket;
import LowLevelDesign.ParkingLot.Enum.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Hourly pricing: charges per hour based on vehicle type.
 * Minimum charge = 1 hour.
 */
public class HourlyPricingStrategy implements PricingStrategy {

    private final Map<VehicleType, Double> hourlyRates;

    public HourlyPricingStrategy() {
        // Default rates
        this.hourlyRates = Map.of(
                VehicleType.BIKE, 20.0,
                VehicleType.CAR, 40.0,
                VehicleType.TRUCK, 80.0
        );
    }

    public HourlyPricingStrategy(Map<VehicleType, Double> hourlyRates) {
        this.hourlyRates = hourlyRates;
    }

    @Override
    public double calculatePrice(ParkingTicket ticket) {
        LocalDateTime entry = ticket.getEntryTime();
        LocalDateTime exit = LocalDateTime.now();

        long hours = Duration.between(entry, exit).toHours();
        if (hours == 0) hours = 1; // Minimum 1 hour charge

        VehicleType vehicleType = ticket.getVehicle().getType();
        double rate = hourlyRates.getOrDefault(vehicleType, 40.0);

        return hours * rate;
    }
}

