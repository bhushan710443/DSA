package LowLevelDesign.LLDMust.Lift.Strategy;

import LowLevelDesign.LLDMust.Lift.Entity.Lift;
import LowLevelDesign.LLDMust.Lift.Entity.LiftRequest;

import java.util.List;

/**
 * Strategy interface for selecting the optimal lift to serve a request.
 * Different algorithms can be implemented (nearest lift, least loaded, etc.)
 */
public interface LiftSelectionStrategy {
    Lift selectLift(List<Lift> lifts, LiftRequest request);
}

