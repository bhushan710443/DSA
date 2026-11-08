package LowLevelDesign.LLDAdvance.DesignACouponAndDiscountEngine.Repository;

import LowLevelDesign.LLDAdvance.DesignACouponAndDiscountEngine.Entity.Ticket;

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
