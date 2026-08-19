package LowLevelDesign.ParkingLot.Entity;

import LowLevelDesign.ParkingLot.Enum.ParkingSpotStatus;
import LowLevelDesign.ParkingLot.Enum.VehicleType;

public class ParkingSpot {
    private final String spotId;
    private final int floor;
    private final int spotNumber;
    private final VehicleType supportedVehicleType;
    private ParkingSpotStatus status;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, int floor, int spotNumber, VehicleType supportedVehicleType) {
        this.spotId = spotId;
        this.floor = floor;
        this.spotNumber = spotNumber;
        this.supportedVehicleType = supportedVehicleType;
        this.status = ParkingSpotStatus.AVAILABLE;
        this.parkedVehicle = null;
    }

    public boolean isAvailable() {
        return status == ParkingSpotStatus.AVAILABLE;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.status = ParkingSpotStatus.OCCUPIED;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.status = ParkingSpotStatus.AVAILABLE;
    }

    public String getSpotId() {
        return spotId;
    }

    public int getFloor() {
        return floor;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getSupportedVehicleType() {
        return supportedVehicleType;
    }

    public ParkingSpotStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingSpotStatus status) {
        this.status = status;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" + "id='" + spotId + "', floor=" + floor +
                ", spot=" + spotNumber + ", type=" + supportedVehicleType +
                ", status=" + status + "}";
    }
}

