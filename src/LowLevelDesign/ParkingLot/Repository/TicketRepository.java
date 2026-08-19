package LowLevelDesign.ParkingLot.Repository;

import LowLevelDesign.ParkingLot.Entity.ParkingTicket;
import LowLevelDesign.ParkingLot.Enum.TicketStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-memory repository for managing parking tickets.
 * Thread-safe using ConcurrentHashMap.
 */
public class TicketRepository {

    private final Map<String, ParkingTicket> tickets = new ConcurrentHashMap<>();

    public void save(ParkingTicket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
    }

    public ParkingTicket getByTicketId(String ticketId) {
        return tickets.get(ticketId);
    }

    public boolean hasActiveTicket(String licensePlate) {
        return tickets.values().stream()
                .anyMatch(t -> t.getStatus() == TicketStatus.ACTIVE
                        && t.getVehicle().getLicensePlate().equals(licensePlate));
    }

    public void markCompleted(String ticketId) {
        // Ticket is already marked completed via closeTicket() on the entity.
        // This method can be used for additional cleanup or indexing if needed.
        ParkingTicket ticket = tickets.get(ticketId);
        if (ticket != null) {
            // Already closed by ParkingService before calling this
        }
    }

    public List<ParkingTicket> getActiveTickets() {
        return tickets.values().stream()
                .filter(t -> t.getStatus() == TicketStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    public List<ParkingTicket> getAllTickets() {
        return new ArrayList<>(tickets.values());
    }
}

