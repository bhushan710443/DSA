package LowLevelDesign.LLDAdvance.DesignAWorkflowEngine.controller;


import LowLevelDesign.LLDAdvance.DesignAWorkflowEngine.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAWorkflowEngine.Service.BookingServiceLogic;
import LowLevelDesign.LLDAdvance.DesignAWorkflowEngine.exceptions.TicketNotFoundException;

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
