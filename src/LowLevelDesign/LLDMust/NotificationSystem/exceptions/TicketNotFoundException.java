package LowLevelDesign.LLDMust.NotificationSystem.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
