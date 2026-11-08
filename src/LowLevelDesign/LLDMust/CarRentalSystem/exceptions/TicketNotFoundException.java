package LowLevelDesign.LLDMust.CarRentalSystem.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
