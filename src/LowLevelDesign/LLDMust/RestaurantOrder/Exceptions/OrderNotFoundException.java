package LowLevelDesign.LLDMust.RestaurantOrder.Exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}

