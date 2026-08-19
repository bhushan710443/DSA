package LowLevelDesign.Lift.Strategy;

import LowLevelDesign.Lift.Entity.Lift;
import LowLevelDesign.Lift.Entity.LiftRequest;

import java.util.List;

/**
 * Strategy interface for selecting the optimal lift to serve a request.
 * Different algorithms can be implemented (nearest lift, least loaded, etc.)
 */
public interface LiftSelectionStrategy {
    Lift selectLift(List<Lift> lifts, LiftRequest request);
}

