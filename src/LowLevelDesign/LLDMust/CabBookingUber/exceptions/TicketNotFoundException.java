package LowLevelDesign.LLDMust.CabBookingUber.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
