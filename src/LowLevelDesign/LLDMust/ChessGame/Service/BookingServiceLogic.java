package LowLevelDesign.LLDMust.ChessGame.Service;

import LowLevelDesign.LLDMust.ChessGame.Entity.Ticket;
import LowLevelDesign.LLDMust.ChessGame.Repository.BookingRepository;
import LowLevelDesign.LLDMust.ChessGame.Strategy.BookingStrategy;
import jdk.jshell.Snippet;

public class BookingServiceLogic {

    BookingRepository bookingRepository;

    BookingStrategy bookingStrategy;

    PaymentServices paymentService;
    public BookingServiceLogic(BookingRepository bookingRepository, BookingStrategy bookingStrategy, PaymentServices paymentService) {
        this.bookingRepository = bookingRepository;
        this.paymentService = paymentService;
        this.bookingStrategy = bookingStrategy;
    }


    public Ticket bookTicket(BookingDto bookingDto){
        Ticket ticket;
        try {

            Double price = bookingStrategy.findActualPrice(bookingDto);
            ticket = new Ticket("A12",
                    bookingDto.getTheatorID(),
                    bookingDto.getScreenId(),
                    bookingDto.getShowId(),
                    bookingDto.getSeats(),
                    price,
                    Snippet.Status.VALID
            );
//            paymentService.payForTicket(ticket, price, bookingDto.getMode() );
            bookingRepository.bookTicket(ticket);
        }catch (Exception e){
            throw new AlreadyBookedExcaption("Ticket Is already booked");
        }
        return ticket;
    }
    public Ticket getTicket(String id){
        return bookingRepository.getTicket(id);
    }

}
