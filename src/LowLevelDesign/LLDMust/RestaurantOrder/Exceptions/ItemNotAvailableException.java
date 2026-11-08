package LowLevelDesign.LLDMust.RestaurantOrder.Exceptions;

public class ItemNotAvailableException extends RuntimeException {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}

