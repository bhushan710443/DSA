package LowLevelDesign.RestaurantOrder.Entity;

/**
 * Represents a single line item in an order.
 * Links a MenuItem with quantity and special instructions.
 */
public class OrderItem {

    private final MenuItem menuItem;
    private int quantity;
    private final String specialInstructions; // e.g., "no onions", "extra spicy"
    private double itemTotal; // price * quantity (computed at creation)

    public OrderItem(MenuItem menuItem, int quantity, String specialInstructions) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.specialInstructions = specialInstructions;
        this.itemTotal = menuItem.getPrice() * quantity;
    }

    public OrderItem(MenuItem menuItem, int quantity) {
        this(menuItem, quantity, null);
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.itemTotal = menuItem.getPrice() * quantity;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    @Override
    public String toString() {
        return menuItem.getName() + " x" + quantity + " = ₹" + itemTotal +
                (specialInstructions != null ? " (" + specialInstructions + ")" : "");
    }
}

