package LowLevelDesign.LLDAdvance.DesignAnAPIAuthenticationAndAuthorizationModule.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
