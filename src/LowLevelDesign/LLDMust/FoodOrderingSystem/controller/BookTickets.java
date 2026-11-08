package LowLevelDesign.LLDMust.FoodOrderingSystem.controller;


import LowLevelDesign.LLDMust.FoodOrderingSystem.Entity.Ticket;
import LowLevelDesign.LLDMust.FoodOrderingSystem.Service.BookingServiceLogic;
import LowLevelDesign.LLDMust.FoodOrderingSystem.exceptions.TicketNotFoundException;

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
