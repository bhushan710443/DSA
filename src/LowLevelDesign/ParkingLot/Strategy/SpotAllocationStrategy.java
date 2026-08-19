package LowLevelDesign.ParkingLot.Strategy;

import LowLevelDesign.ParkingLot.Entity.ParkingSpot;
import LowLevelDesign.ParkingLot.Enum.VehicleType;

import java.util.List;

/**
 * Strategy Pattern: Different spot allocation strategies.
 * e.g., Nearest to entrance, Random, Floor-based, etc.
 */
public interface SpotAllocationStrategy {
    ParkingSpot findSpot(List<ParkingSpot> availableSpots, VehicleType vehicleType);
}

