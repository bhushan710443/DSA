package LowLevelDesign.RestaurantOrder.Repository;

import LowLevelDesign.RestaurantOrder.Entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerRepository {

    private final Map<String, Customer> customers = new ConcurrentHashMap<>();

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Customer getById(String customerId) {
        return customers.get(customerId);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer findByPhone(String phone) {
        return customers.values().stream()
                .filter(c -> c.getPhone().equals(phone))
                .findFirst()
                .orElse(null);
    }
}

