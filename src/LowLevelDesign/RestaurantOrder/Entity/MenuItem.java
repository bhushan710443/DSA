package LowLevelDesign.RestaurantOrder.Entity;

import LowLevelDesign.RestaurantOrder.Enum.MenuCategory;

/**
 * Represents a dish/item on the restaurant menu.
 * Immutable fields (id, name, category) + mutable (price, available).
 */
public class MenuItem {

    private final String itemId;
    private final String name;
    private final String description;
    private final MenuCategory category;
    private double price;
    private boolean available;
    private int preparationTimeMinutes; // Estimated time to prepare

    public MenuItem(String itemId, String name, String description,
                    MenuCategory category, double price, int preparationTimeMinutes) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.preparationTimeMinutes = preparationTimeMinutes;
        this.available = true;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPreparationTimeMinutes() {
        return preparationTimeMinutes;
    }

    public void setPreparationTimeMinutes(int preparationTimeMinutes) {
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    @Override
    public String toString() {
        return name + " (₹" + price + ") [" + category + "]" +
                (available ? "" : " — UNAVAILABLE");
    }
}

