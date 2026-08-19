package LowLevelDesign.RestaurantOrder.Exceptions;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(String message) {
        super(message);
    }
}

