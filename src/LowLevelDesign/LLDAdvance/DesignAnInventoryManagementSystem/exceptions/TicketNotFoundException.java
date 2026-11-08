package LowLevelDesign.LLDAdvance.DesignAnInventoryManagementSystem.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
