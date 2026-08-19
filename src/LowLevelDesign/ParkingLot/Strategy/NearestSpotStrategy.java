package LowLevelDesign.ParkingLot.Strategy;

import LowLevelDesign.ParkingLot.Entity.ParkingSpot;
import LowLevelDesign.ParkingLot.Enum.VehicleType;

import java.util.List;

/**
 * Allocates the nearest available spot (lowest floor, lowest spot number).
 */
public class NearestSpotStrategy implements SpotAllocationStrategy {

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> availableSpots, VehicleType vehicleType) {
        return availableSpots.stream()
                .filter(spot -> spot.getSupportedVehicleType() == vehicleType && spot.isAvailable())
                .min((s1, s2) -> {
                    if (s1.getFloor() != s2.getFloor()) {
                        return Integer.compare(s1.getFloor(), s2.getFloor());
                    }
                    return Integer.compare(s1.getSpotNumber(), s2.getSpotNumber());
                })
                .orElse(null);
    }
}

