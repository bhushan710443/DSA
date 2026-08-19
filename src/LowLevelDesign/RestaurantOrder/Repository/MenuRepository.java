package LowLevelDesign.RestaurantOrder.Repository;

import LowLevelDesign.RestaurantOrder.Entity.MenuItem;
import LowLevelDesign.RestaurantOrder.Enum.MenuCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MenuRepository {

    private final Map<String, MenuItem> menuItems = new ConcurrentHashMap<>();

    public void addItem(MenuItem item) {
        menuItems.put(item.getItemId(), item);
    }

    public void removeItem(String itemId) {
        menuItems.remove(itemId);
    }

    public MenuItem getById(String itemId) {
        return menuItems.get(itemId);
    }

    public List<MenuItem> getAllItems() {
        return new ArrayList<>(menuItems.values());
    }

    public List<MenuItem> getAvailableItems() {
        return menuItems.values().stream()
                .filter(MenuItem::isAvailable)
                .collect(Collectors.toList());
    }

    public List<MenuItem> getByCategory(MenuCategory category) {
        return menuItems.values().stream()
                .filter(item -> item.getCategory() == category)
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchByName(String keyword) {
        String lower = keyword.toLowerCase();
        return menuItems.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }
}

