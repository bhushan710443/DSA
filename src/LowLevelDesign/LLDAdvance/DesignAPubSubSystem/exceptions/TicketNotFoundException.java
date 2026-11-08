package LowLevelDesign.LLDAdvance.DesignAPubSubSystem.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
