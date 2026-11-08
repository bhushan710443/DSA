package LowLevelDesign.LLDAdvance.DesignASubscriptionManagementSystem.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
