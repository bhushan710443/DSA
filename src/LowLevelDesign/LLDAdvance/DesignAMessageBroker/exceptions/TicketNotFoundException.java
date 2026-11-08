package LowLevelDesign.LLDAdvance.DesignAMessageBroker.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
