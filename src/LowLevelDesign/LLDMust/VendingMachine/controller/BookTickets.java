package LowLevelDesign.LLDMust.VendingMachine.controller;


import LowLevelDesign.LLDMust.VendingMachine.Entity.Ticket;
import LowLevelDesign.LLDMust.VendingMachine.Service.BookingServiceLogic;
import LowLevelDesign.LLDMust.VendingMachine.exceptions.TicketNotFoundException;

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
