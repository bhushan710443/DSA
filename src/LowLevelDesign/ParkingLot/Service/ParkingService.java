package LowLevelDesign.ParkingLot.Service;

import LowLevelDesign.ParkingLot.Entity.ParkingSpot;
import LowLevelDesign.ParkingLot.Entity.ParkingTicket;
import LowLevelDesign.ParkingLot.Entity.Vehicle;
import LowLevelDesign.ParkingLot.Enum.VehicleType;
import LowLevelDesign.ParkingLot.Exceptions.InvalidTicketException;
import LowLevelDesign.ParkingLot.Exceptions.ParkingFullException;
import LowLevelDesign.ParkingLot.Exceptions.VehicleAlreadyParkedException;
import LowLevelDesign.ParkingLot.Exceptions.VehicleNotFoundException;
import LowLevelDesign.ParkingLot.Repository.ParkingSpotRepository;
import LowLevelDesign.ParkingLot.Repository.TicketRepository;
import LowLevelDesign.ParkingLot.Strategy.PricingStrategy;
import LowLevelDesign.ParkingLot.Strategy.SpotAllocationStrategy;

import java.util.List;
import java.util.UUID;

/**
 * Core service layer handling all parking lot business logic.
 * Uses Strategy pattern for spot allocation and pricing.
 * Uses Repository pattern for data access.
 */
public class ParkingService {

    private final ParkingSpotRepository spotRepository;
    private final TicketRepository ticketRepository;
    private final SpotAllocationStrategy spotAllocationStrategy;
    private final PricingStrategy pricingStrategy;

    public ParkingService(ParkingSpotRepository spotRepository,
                          TicketRepository ticketRepository,
                          SpotAllocationStrategy spotAllocationStrategy,
                          PricingStrategy pricingStrategy) {
        this.spotRepository = spotRepository;
        this.ticketRepository = ticketRepository;
        this.spotAllocationStrategy = spotAllocationStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    /**
     * Park a vehicle: allocate spot, generate ticket.
     */
    public ParkingTicket parkVehicle(Vehicle vehicle) {
        // Check if vehicle is already parked
        if (ticketRepository.hasActiveTicket(vehicle.getLicensePlate())) {
            throw new VehicleAlreadyParkedException(
                    "Vehicle " + vehicle.getLicensePlate() + " is already parked.");
        }

        // Find available spots for the vehicle type
        List<ParkingSpot> availableSpots = spotRepository.getAvailableSpots(vehicle.getType());
        if (availableSpots.isEmpty()) {
            throw new ParkingFullException(
                    "No parking spots available for vehicle type: " + vehicle.getType());
        }

        // Use strategy to pick the best spot
        ParkingSpot assignedSpot = spotAllocationStrategy.findSpot(availableSpots, vehicle.getType());
        if (assignedSpot == null) {
            throw new ParkingFullException(
                    "No suitable parking spot found for vehicle type: " + vehicle.getType());
        }

        // Park the vehicle
        assignedSpot.parkVehicle(vehicle);

        // Generate ticket
        String ticketId = generateTicketId();
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, assignedSpot);
        ticketRepository.save(ticket);

        System.out.println("✅ Vehicle parked: " + vehicle.getLicensePlate() +
                " at Spot: " + assignedSpot.getSpotId() +
                " | Ticket: " + ticketId);

        return ticket;
    }

    /**
     * Unpark a vehicle: calculate fee, free spot, close ticket.
     */
    public ParkingTicket unparkVehicle(String ticketId) {
        ParkingTicket ticket = ticketRepository.getByTicketId(ticketId);
        if (ticket == null) {
            throw new InvalidTicketException("Invalid ticket ID: " + ticketId);
        }

        // Calculate price
        double amount = pricingStrategy.calculatePrice(ticket);

        // Free the spot
        ticket.getSpot().removeVehicle();

        // Close ticket
        ticket.closeTicket(amount);
        ticketRepository.markCompleted(ticketId);

        System.out.println("✅ Vehicle unparked: " + ticket.getVehicle().getLicensePlate() +
                " | Amount charged: ₹" + amount);

        return ticket;
    }

    /**
     * Locate a vehicle by license plate.
     */
    public ParkingSpot locateVehicle(String licensePlate) {
        ParkingSpot spot = spotRepository.findSpotByVehicleLicensePlate(licensePlate);
        if (spot == null) {
            throw new VehicleNotFoundException(
                    "Vehicle not found with license plate: " + licensePlate);
        }
        return spot;
    }

    /**
     * Get available spot count for a vehicle type.
     */
    public long getAvailableSpotCount(VehicleType type) {
        return spotRepository.countAvailableSpots(type);
    }

    /**
     * Get all active tickets.
     */
    public List<ParkingTicket> getActiveTickets() {
        return ticketRepository.getActiveTickets();
    }

    private String generateTicketId() {
        return "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
