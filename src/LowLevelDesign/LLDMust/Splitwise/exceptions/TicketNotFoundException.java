package LowLevelDesign.LLDMust.Splitwise.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
