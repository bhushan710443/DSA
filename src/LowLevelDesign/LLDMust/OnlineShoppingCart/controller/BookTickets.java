package LowLevelDesign.LLDMust.OnlineShoppingCart.controller;


import LowLevelDesign.LLDMust.OnlineShoppingCart.Entity.Ticket;
import LowLevelDesign.LLDMust.OnlineShoppingCart.Service.BookingServiceLogic;
import LowLevelDesign.LLDMust.OnlineShoppingCart.exceptions.TicketNotFoundException;

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
