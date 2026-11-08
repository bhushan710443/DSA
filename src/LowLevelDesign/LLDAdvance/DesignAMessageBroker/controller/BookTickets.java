package LowLevelDesign.LLDAdvance.DesignAMessageBroker.controller;


import LowLevelDesign.LLDAdvance.DesignAMessageBroker.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAMessageBroker.Service.BookingServiceLogic;
import LowLevelDesign.LLDAdvance.DesignAMessageBroker.exceptions.TicketNotFoundException;

public class BookTickets{

    public BookingServiceLogic bookingService;

    public BookTickets(BookingServiceLogic bookingService) {
        this.bookingService = bookingService;
    }


    public Ticket getTicket(String id){
       Ticket ticket = bookingService.getTicket(id);
       if(ticket==null){
           throw new TicketNotFoundException("Ticket Not Present");
       }
        return ticket;
    }

    public Ticket bookTicket(BookingDto bookingDto){
        return bookingService.bookTicket(bookingDto);
    }

    public Ticket findShowTicket(){
        return null;
    }



}
