package LowLevelDesign.LLDMust.RateLimiter.controller;


import LowLevelDesign.LLDMust.RateLimiter.Entity.Ticket;
import LowLevelDesign.LLDMust.RateLimiter.Service.BookingServiceLogic;
import LowLevelDesign.LLDMust.RateLimiter.exceptions.TicketNotFoundException;

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
