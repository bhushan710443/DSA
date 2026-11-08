package LowLevelDesign.LLDMust.Splitwise.controller;


import LowLevelDesign.LLDMust.Splitwise.Entity.Ticket;
import LowLevelDesign.LLDMust.Splitwise.Service.BookingServiceLogic;
import LowLevelDesign.LLDMust.Splitwise.exceptions.TicketNotFoundException;

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
