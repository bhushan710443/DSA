package LowLevelDesign.Lift.Entity;

import LowLevelDesign.Lift.Enum.Going;

public class LiftRequest {
    private int fromFloor;       // floor where user pressed the button
    private int toFloor;         // destination floor
    private Going direction;     // direction user wants to go

    public LiftRequest(int fromFloor, int toFloor) {
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
        this.direction = toFloor > fromFloor ? Going.UP : Going.DOWN;
    }

    public int getFromFloor() {
        return fromFloor;
    }

    public int getToFloor() {
        return toFloor;
    }

    public Going getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "LiftRequest{from=" + fromFloor + ", to=" + toFloor + ", direction=" + direction + "}";
    }
}

