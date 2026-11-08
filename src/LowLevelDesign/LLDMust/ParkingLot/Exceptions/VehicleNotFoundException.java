package LowLevelDesign.LLDMust.ParkingLot.Exceptions;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String message) {
        super(message);
    }
}

