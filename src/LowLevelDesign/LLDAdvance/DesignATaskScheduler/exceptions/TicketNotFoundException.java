package LowLevelDesign.LLDAdvance.DesignATaskScheduler.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
