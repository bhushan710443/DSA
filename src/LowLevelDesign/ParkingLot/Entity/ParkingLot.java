package LowLevelDesign.ParkingLot.Entity;

import LowLevelDesign.ParkingLot.Enum.VehicleType;

import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final String parkingLotId;
    private final String name;
    private final String address;
    private final int totalFloors;
    private final Map<Integer, List<ParkingSpot>> floorToSpotsMap;

    public ParkingLot(String parkingLotId, String name, String address, int totalFloors,
                      Map<Integer, List<ParkingSpot>> floorToSpotsMap) {
        this.parkingLotId = parkingLotId;
        this.name = name;
        this.address = address;
        this.totalFloors = totalFloors;
        this.floorToSpotsMap = floorToSpotsMap;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public Map<Integer, List<ParkingSpot>> getFloorToSpotsMap() {
        return floorToSpotsMap;
    }

    public int getAvailableSpotCount(VehicleType type) {
        return (int) floorToSpotsMap.values().stream()
                .flatMap(List::stream)
                .filter(spot -> spot.getSupportedVehicleType() == type && spot.isAvailable())
                .count();
    }
}

