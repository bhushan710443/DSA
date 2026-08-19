package LowLevelDesign.ParkingLot.Repository;

import LowLevelDesign.ParkingLot.Entity.ParkingSpot;
import LowLevelDesign.ParkingLot.Enum.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-memory repository for managing parking spots.
 * Thread-safe using ConcurrentHashMap.
 */
public class ParkingSpotRepository {

    // spotId -> ParkingSpot
    private final Map<String, ParkingSpot> spots = new ConcurrentHashMap<>();

    public void addSpot(ParkingSpot spot) {
        spots.put(spot.getSpotId(), spot);
    }

    public ParkingSpot getSpotById(String spotId) {
        return spots.get(spotId);
    }

    public List<ParkingSpot> getAllSpots() {
        return new ArrayList<>(spots.values());
    }

    public List<ParkingSpot> getAvailableSpots(VehicleType type) {
        return spots.values().stream()
                .filter(spot -> spot.isAvailable() && spot.getSupportedVehicleType() == type)
                .collect(Collectors.toList());
    }

    public long countAvailableSpots(VehicleType type) {
        return spots.values().stream()
                .filter(spot -> spot.isAvailable() && spot.getSupportedVehicleType() == type)
                .count();
    }

    public ParkingSpot findSpotByVehicleLicensePlate(String licensePlate) {
        return spots.values().stream()
                .filter(spot -> !spot.isAvailable()
                        && spot.getParkedVehicle() != null
                        && spot.getParkedVehicle().getLicensePlate().equals(licensePlate))
                .findFirst()
                .orElse(null);
    }
}

