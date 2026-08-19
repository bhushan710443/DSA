package LowLevelDesign.Lift.Strategy;

import LowLevelDesign.Lift.Entity.Lift;
import LowLevelDesign.Lift.Entity.LiftRequest;
import LowLevelDesign.Lift.Enum.Going;

import java.util.List;

/**
 * Selects the nearest available lift that is either:
 * 1. IDLE
 * 2. Moving towards the requested floor in the same direction
 */
public class NearestLiftStrategy implements LiftSelectionStrategy {

    @Override
    public Lift selectLift(List<Lift> lifts, LiftRequest request) {
        Lift bestLift = null;
        int minDistance = Integer.MAX_VALUE;

        for (Lift lift : lifts) {
            if (lift.isFull()) continue;

            int currentFloor = lift.getPosition().getCurrentFloor();
            Going liftDirection = lift.getPosition().getDirection();
            int distance = Math.abs(currentFloor - request.getFromFloor());

            // Priority 1: Idle lift closest to the request floor
            if (liftDirection == Going.IDLE) {
                if (distance < minDistance) {
                    minDistance = distance;
                    bestLift = lift;
                }
            }
            // Priority 2: Lift moving in the same direction and hasn't passed the floor
            else if (liftDirection == request.getDirection()) {
                if (liftDirection == Going.UP && currentFloor <= request.getFromFloor()) {
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestLift = lift;
                    }
                } else if (liftDirection == Going.DOWN && currentFloor >= request.getFromFloor()) {
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestLift = lift;
                    }
                }
            }
        }

        // Fallback: if no ideal lift found, pick the nearest non-full lift
        if (bestLift == null) {
            for (Lift lift : lifts) {
                if (lift.isFull()) continue;
                int distance = Math.abs(lift.getPosition().getCurrentFloor() - request.getFromFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestLift = lift;
                }
            }
        }

        return bestLift;
    }
}

