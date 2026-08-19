package LowLevelDesign.Lift.Repository;

import LowLevelDesign.Lift.Entity.Lift;
import LowLevelDesign.Lift.Exceptions.LiftNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiftRepository {
    private final Map<String, Lift> lifts = new HashMap<>();

    public void addLift(Lift lift) {
        lifts.put(lift.getId(), lift);
    }

    public Lift getLiftById(String id) {
        Lift lift = lifts.get(id);
        if (lift == null) {
            throw new LiftNotFoundException("Lift not found with id: " + id);
        }
        return lift;
    }

    public List<Lift> getAllLifts() {
        return new ArrayList<>(lifts.values());
    }

    public void removeLift(String id) {
        lifts.remove(id);
    }
}

