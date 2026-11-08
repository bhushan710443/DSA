package LowLevelDesign.LLDMust.LoggingFramework.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
