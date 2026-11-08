package LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.Repository;

import LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.Entity.Ticket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepository {

    private Map<String, Ticket> ticketStore = new ConcurrentHashMap<>();

    public Ticket bookTicket(Ticket ticket){
        ticketStore.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public Ticket getTicket(String id){
        return ticketStore.get(id);
    }



}
