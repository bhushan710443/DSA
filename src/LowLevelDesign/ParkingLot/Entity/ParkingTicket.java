package LowLevelDesign.ParkingLot.Entity;

import LowLevelDesign.ParkingLot.Enum.TicketStatus;

import java.time.LocalDateTime;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private TicketStatus status;
    private double amountCharged;

    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;
    }

    public void closeTicket(double amount) {
        this.exitTime = LocalDateTime.now();
        this.amountCharged = amount;
        this.status = TicketStatus.COMPLETED;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public double getAmountCharged() {
        return amountCharged;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" + "ticketId='" + ticketId + "', vehicle=" + vehicle.getLicensePlate() +
                ", spot=" + spot.getSpotId() + ", entry=" + entryTime +
                ", status=" + status + ", amount=" + amountCharged + "}";
    }
}

