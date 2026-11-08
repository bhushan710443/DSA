package LowLevelDesign.LLDAdvance.DesignAnInMemoryKeyValueStore.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
