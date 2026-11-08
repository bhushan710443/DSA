package LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
