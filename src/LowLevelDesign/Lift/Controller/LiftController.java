package LowLevelDesign.Lift.Controller;

import LowLevelDesign.Lift.Entity.Lift;
import LowLevelDesign.Lift.Entity.LiftRequest;
import LowLevelDesign.Lift.Service.LiftService;

import java.util.List;

/**
 * LiftController - Entry point for lift system operations.
 * Handles external requests (call lift) and internal requests (select floor).
 */
public class LiftController {

    private final LiftService liftService;

    public LiftController(LiftService liftService) {
        this.liftService = liftService;
    }

    /**
     * External button press: User calls a lift from a floor
     * @param fromFloor - floor where user is standing
     * @param toFloor - destination floor
     * @return assigned Lift
     */
    public Lift callLift(int fromFloor, int toFloor) {
        return liftService.callLift(fromFloor, toFloor);
    }

    /**
     * Internal button press: User selects a floor inside the lift
     * @param liftId - id of the lift
     * @param destinationFloor - floor to go to
     */
    public void selectFloor(String liftId, int destinationFloor) {
        liftService.selectFloorInsideLift(liftId, destinationFloor);
    }

    /**
     * Get status of a specific lift
     */
    public Lift getLiftStatus(String liftId) {
        return liftService.getLiftStatus(liftId);
    }

    /**
     * Get status of all lifts
     */
    public List<Lift> getAllLiftsStatus() {
        return liftService.getAllLiftsStatus();
    }

    /**
     * Simulate one time-step movement of all lifts
     */
    public void step() {
        liftService.moveLifts();
    }
}
