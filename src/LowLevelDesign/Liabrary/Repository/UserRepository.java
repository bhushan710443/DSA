package LowLevelDesign.Liabrary.Repository;

import LowLevelDesign.Liabrary.Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getById(String userId) {
        return users.get(userId);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User findByName(String name) {
        return users.values().stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}

