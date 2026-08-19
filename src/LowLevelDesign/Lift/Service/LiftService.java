package LowLevelDesign.Lift.Service;

import LowLevelDesign.Lift.Entity.Lift;
import LowLevelDesign.Lift.Entity.LiftRequest;
import LowLevelDesign.Lift.Enum.Going;
import LowLevelDesign.Lift.Exceptions.InvalidFloorException;
import LowLevelDesign.Lift.Exceptions.LiftFullException;
import LowLevelDesign.Lift.Exceptions.LiftNotFoundException;
import LowLevelDesign.Lift.Repository.LiftRepository;
import LowLevelDesign.Lift.Strategy.LiftSelectionStrategy;

import java.util.List;

public class LiftService {

    private final LiftRepository liftRepository;
    private final LiftSelectionStrategy selectionStrategy;
    private final int minFloor;
    private final int maxFloor;

    public LiftService(LiftRepository liftRepository, LiftSelectionStrategy selectionStrategy,
                       int minFloor, int maxFloor) {
        this.liftRepository = liftRepository;
        this.selectionStrategy = selectionStrategy;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    /**
     * External request: User presses button on a floor to call a lift
     */
    public Lift callLift(int fromFloor, int toFloor) {
        validateFloor(fromFloor);
        validateFloor(toFloor);

        if (fromFloor == toFloor) {
            throw new InvalidFloorException("Source and destination floor cannot be the same");
        }

        LiftRequest request = new LiftRequest(fromFloor, toFloor);
        List<Lift> allLifts = liftRepository.getAllLifts();

        Lift selectedLift = selectionStrategy.selectLift(allLifts, request);

        if (selectedLift == null) {
            throw new LiftNotFoundException("No available lift to serve the request");
        }

        // Add pickup floor and destination floor as stops
        assignRequestToLift(selectedLift, request);

        System.out.println("Lift '" + selectedLift.getName() + "' assigned for request: " + request);
        return selectedLift;
    }

    /**
     * Internal request: User presses a floor button inside the lift
     */
    public void selectFloorInsideLift(String liftId, int destinationFloor) {
        validateFloor(destinationFloor);
        Lift lift = liftRepository.getLiftById(liftId);

        if (lift.isFull()) {
            throw new LiftFullException("Lift " + lift.getName() + " is full");
        }

        lift.addStop(destinationFloor);
        System.out.println("Floor " + destinationFloor + " added to lift '" + lift.getName() + "'");
    }

    /**
     * Move the lift one step (simulate movement)
     */
    public void moveLifts() {
        List<Lift> allLifts = liftRepository.getAllLifts();
        for (Lift lift : allLifts) {
            moveLift(lift);
        }
    }

    private void moveLift(Lift lift) {
        Going direction = lift.getPosition().getDirection();
        int currentFloor = lift.getPosition().getCurrentFloor();

        if (direction == Going.UP) {
            Integer nextStop = lift.getUpStops().higher(currentFloor - 1);
            if (nextStop == null) {
                // No more up stops, check down stops or go idle
                if (!lift.getDownStops().isEmpty()) {
                    lift.getPosition().setDirection(Going.DOWN);
                } else {
                    lift.getPosition().setDirection(Going.IDLE);
                }
                return;
            }
            // Move one floor up
            lift.getPosition().setCurrentFloor(currentFloor + 1);
            if (lift.getUpStops().contains(currentFloor + 1)) {
                lift.getUpStops().remove(currentFloor + 1);
                System.out.println("Lift '" + lift.getName() + "' stopped at floor " + (currentFloor + 1));
                openDoors(lift);
            }
        } else if (direction == Going.DOWN) {
            Integer nextStop = lift.getDownStops().lower(currentFloor + 1);
            if (nextStop == null) {
                // No more down stops, check up stops or go idle
                if (!lift.getUpStops().isEmpty()) {
                    lift.getPosition().setDirection(Going.UP);
                } else {
                    lift.getPosition().setDirection(Going.IDLE);
                }
                return;
            }
            // Move one floor down
            lift.getPosition().setCurrentFloor(currentFloor - 1);
            if (lift.getDownStops().contains(currentFloor - 1)) {
                lift.getDownStops().remove(currentFloor - 1);
                System.out.println("Lift '" + lift.getName() + "' stopped at floor " + (currentFloor - 1));
                openDoors(lift);
            }
        }
        // IDLE lifts don't move
    }

    private void openDoors(Lift lift) {
        System.out.println("Doors opened at floor " + lift.getPosition().getCurrentFloor());
        // Simulate door open/close delay
        System.out.println("Doors closed at floor " + lift.getPosition().getCurrentFloor());
    }

    private void assignRequestToLift(Lift lift, LiftRequest request) {
        int currentFloor = lift.getPosition().getCurrentFloor();
        Going direction = lift.getPosition().getDirection();

        // If idle, set direction towards the pickup floor
        if (direction == Going.IDLE) {
            if (request.getFromFloor() > currentFloor) {
                lift.getPosition().setDirection(Going.UP);
                lift.getUpStops().add(request.getFromFloor());
                lift.getUpStops().add(request.getToFloor());
            } else if (request.getFromFloor() < currentFloor) {
                lift.getPosition().setDirection(Going.DOWN);
                lift.getDownStops().add(request.getFromFloor());
                lift.getDownStops().add(request.getToFloor());
            } else {
                // Already at pickup floor, just add destination
                if (request.getToFloor() > currentFloor) {
                    lift.getPosition().setDirection(Going.UP);
                    lift.getUpStops().add(request.getToFloor());
                } else {
                    lift.getPosition().setDirection(Going.DOWN);
                    lift.getDownStops().add(request.getToFloor());
                }
            }
        } else {
            // Lift is already moving, add stops accordingly
            lift.addStop(request.getFromFloor());
            lift.addStop(request.getToFloor());
        }
    }

    public Lift getLiftStatus(String liftId) {
        return liftRepository.getLiftById(liftId);
    }

    public List<Lift> getAllLiftsStatus() {
        return liftRepository.getAllLifts();
    }

    public void addLift(Lift lift) {
        liftRepository.addLift(lift);
    }

    private void validateFloor(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            throw new InvalidFloorException("Floor " + floor + " is invalid. Must be between "
                    + minFloor + " and " + maxFloor);
        }
    }
}
