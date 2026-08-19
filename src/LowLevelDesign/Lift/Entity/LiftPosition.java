package LowLevelDesign.Lift.Entity;

import LowLevelDesign.Lift.Enum.Going;

public class LiftPosition {
    private int currentFloor;
    private Going direction;

    public LiftPosition(int currentFloor, Going direction) {
        this.currentFloor = currentFloor;
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Going getDirection() {
        return direction;
    }

    public void setDirection(Going direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "LiftPosition{floor=" + currentFloor + ", direction=" + direction + "}";
    }
}
