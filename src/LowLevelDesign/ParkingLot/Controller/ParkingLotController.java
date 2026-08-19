package LowLevelDesign.ParkingLot.Controller;

import LowLevelDesign.ParkingLot.Entity.ParkingSpot;
import LowLevelDesign.ParkingLot.Entity.ParkingTicket;
import LowLevelDesign.ParkingLot.Entity.Vehicle;
import LowLevelDesign.ParkingLot.Enum.VehicleType;
import LowLevelDesign.ParkingLot.Service.ParkingService;

/**
 * Controller layer - acts as the entry point / API layer.
 * Delegates all business logic to the service.
 */
public class ParkingLotController {

    private final ParkingService parkingService;

    public ParkingLotController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    /**
     * Entry gate: Park a vehicle.
     */
    public ParkingTicket entry(Vehicle vehicle) {
        return parkingService.parkVehicle(vehicle);
    }

    /**
     * Exit gate: Unpark a vehicle and charge.
     */
    public ParkingTicket exit(String ticketId) {
        return parkingService.unparkVehicle(ticketId);
    }

    /**
     * Locate vehicle by license plate (display board / help desk).
     */
    public ParkingSpot locateVehicle(String licensePlate) {
        return parkingService.locateVehicle(licensePlate);
    }

    /**
     * Get available parking spots for a vehicle type (display board).
     */
    public long getAvailableSpots(VehicleType type) {
        return parkingService.getAvailableSpotCount(type);
    }
}
