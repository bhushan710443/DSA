package LowLevelDesign.LLDAdvance.DesignACouponAndDiscountEngine.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
