package LowLevelDesign.LLDMust.HotelReservationSystem.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
