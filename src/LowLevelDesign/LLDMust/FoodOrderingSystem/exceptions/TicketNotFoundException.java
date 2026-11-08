package LowLevelDesign.LLDMust.FoodOrderingSystem.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
