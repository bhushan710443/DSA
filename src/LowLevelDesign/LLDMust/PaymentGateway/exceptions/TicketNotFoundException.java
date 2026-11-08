package LowLevelDesign.LLDMust.PaymentGateway.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
