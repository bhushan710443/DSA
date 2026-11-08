package LowLevelDesign.LLDAdvance.DesignAJobExecutionFramework.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
