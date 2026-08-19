package LowLevelDesign.Lift.Entity;

import LowLevelDesign.Lift.Enum.Going;

import java.util.TreeSet;

public class Lift {
    private String id;
    private String name;
    private int capacity;
    private int currentPassengers;
    private LiftPosition position;
    private TreeSet<Integer> upStops;    // floors to stop going up (sorted ascending)
    private TreeSet<Integer> downStops;  // floors to stop going down (sorted descending)

    public Lift(String id, String name, int capacity, int initialFloor) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.currentPassengers = 0;
        this.position = new LiftPosition(initialFloor, Going.IDLE);
        this.upStops = new TreeSet<>();
        this.downStops = new TreeSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    public void setCurrentPassengers(int currentPassengers) {
        this.currentPassengers = currentPassengers;
    }

    public LiftPosition getPosition() {
        return position;
    }

    public TreeSet<Integer> getUpStops() {
        return upStops;
    }

    public TreeSet<Integer> getDownStops() {
        return downStops;
    }

    public boolean isFull() {
        return currentPassengers >= capacity;
    }

    public boolean isIdle() {
        return position.getDirection() == Going.IDLE;
    }

    public void addStop(int floor) {
        int currentFloor = position.getCurrentFloor();
        Going direction = position.getDirection();

        if (direction == Going.UP || (direction == Going.IDLE && floor > currentFloor)) {
            upStops.add(floor);
        } else if (direction == Going.DOWN || (direction == Going.IDLE && floor < currentFloor)) {
            downStops.add(floor);
        } else {
            // Same floor, no need to add
        }
    }

    @Override
    public String toString() {
        return "Lift{id='" + id + "', name='" + name + "', position=" + position +
                ", passengers=" + currentPassengers + "/" + capacity + "}";
    }
}
