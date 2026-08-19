package LowLevelDesign.RestaurantOrder.Entity;

/**
 * Represents a restaurant customer.
 */
public class Customer {

    private final String customerId;
    private final String name;
    private final String phone;
    private String address; // For delivery orders
    private int loyaltyPoints;

    public Customer(String customerId, String name, String phone, String address) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.loyaltyPoints = 0;
    }

    public Customer(String customerId, String name, String phone) {
        this(customerId, name, phone, null);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    public void redeemPoints(int points) {
        if (points > loyaltyPoints) {
            throw new IllegalArgumentException("Insufficient loyalty points");
        }
        this.loyaltyPoints -= points;
    }

    @Override
    public String toString() {
        return "Customer{'" + name + "', phone='" + phone + "', points=" + loyaltyPoints + "}";
    }
}

