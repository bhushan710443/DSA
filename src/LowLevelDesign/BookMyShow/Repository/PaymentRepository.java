package LowLevelDesign.BookMyShow.Repository;

import LowLevelDesign.BookMyShow.Entity.Ticket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentRepository {

    private Map<String, Double> payment = new ConcurrentHashMap<>();

    public Ticket payForTicket(Ticket ticket, Double amount){
        payment.put(ticket.getTicketId(), amount);
        return ticket;
    }



}
