package LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.controller;


import LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.Service.BookingServiceLogic;
import LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.exceptions.TicketNotFoundException;

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
