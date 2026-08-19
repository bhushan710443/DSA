package LowLevelDesign.RestaurantOrder.Exceptions;

public class ItemNotAvailableException extends RuntimeException {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}

