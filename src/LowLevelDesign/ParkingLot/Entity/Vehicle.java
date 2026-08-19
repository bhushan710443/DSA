package LowLevelDesign.ParkingLot.Entity;

import LowLevelDesign.ParkingLot.Enum.VehicleType;

public class Vehicle {
    private final String licensePlate;
    private final VehicleType type;
    private final String ownerName;

    public Vehicle(String licensePlate, VehicleType type, String ownerName) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.ownerName = ownerName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "licensePlate='" + licensePlate + "', type=" + type + ", owner='" + ownerName + "'}";
    }
}

