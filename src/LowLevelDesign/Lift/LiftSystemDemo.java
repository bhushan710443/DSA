package LowLevelDesign.Lift;

import LowLevelDesign.Lift.Controller.LiftController;
import LowLevelDesign.Lift.Entity.Lift;
import LowLevelDesign.Lift.Repository.LiftRepository;
import LowLevelDesign.Lift.Service.LiftService;
import LowLevelDesign.Lift.Strategy.NearestLiftStrategy;

/**
 * =====================================================
 * LOW LEVEL DESIGN - LIFT / ELEVATOR SYSTEM
 * =====================================================
 *
 * DESIGN PATTERNS USED:
 * 1. Strategy Pattern     - LiftSelectionStrategy (for selecting which lift serves a request)
 * 2. Repository Pattern   - LiftRepository (data access abstraction)
 * 3. Service Layer        - LiftService (business logic)
 * 4. Controller           - LiftController (entry point / API layer)
 *
 * COMPONENTS:
 * ┌─────────────────────────────────────────────────────────────────┐
 * │  Entity Layer                                                    │
 * │  ├── Lift           (id, name, capacity, position, stops)       │
 * │  ├── LiftPosition   (currentFloor, direction)                   │
 * │  └── LiftRequest    (fromFloor, toFloor, direction)             │
 * ├─────────────────────────────────────────────────────────────────┤
 * │  Enum Layer                                                      │
 * │  ├── Going          (UP, DOWN, IDLE)                            │
 * │  └── DoorState      (OPEN, CLOSED)                              │
 * ├─────────────────────────────────────────────────────────────────┤
 * │  Strategy Layer                                                  │
 * │  ├── LiftSelectionStrategy (interface)                          │
 * │  └── NearestLiftStrategy   (picks nearest available lift)       │
 * ├─────────────────────────────────────────────────────────────────┤
 * │  Repository Layer                                                │
 * │  └── LiftRepository (in-memory store of lifts)                  │
 * ├─────────────────────────────────────────────────────────────────┤
 * │  Service Layer                                                   │
 * │  └── LiftService    (scheduling, movement, door control)        │
 * ├─────────────────────────────────────────────────────────────────┤
 * │  Controller Layer                                                │
 * │  └── LiftController (API for callLift, selectFloor, status)     │
 * ├─────────────────────────────────────────────────────────────────┤
 * │  Exception Layer                                                 │
 * │  ├── LiftNotFoundException                                      │
 * │  ├── InvalidFloorException                                      │
 * │  └── LiftFullException                                          │
 * └─────────────────────────────────────────────────────────────────┘
 *
 * ALGORITHM: SCAN (Elevator Algorithm)
 * - Lift moves in one direction, serving all requests on the way
 * - When no more requests in current direction, reverses or goes idle
 * - Uses TreeSet for ordered stop management
 */
public class LiftSystemDemo {

    public static void main(String[] args) {
        // Setup: Building with floors 0 (Ground) to 10
        LiftRepository repository = new LiftRepository();
        NearestLiftStrategy strategy = new NearestLiftStrategy();
        LiftService service = new LiftService(repository, strategy, 0, 10);
        LiftController controller = new LiftController(service);

        // Add 3 lifts to the building
        Lift lift1 = new Lift("L1", "Lift-A", 8, 0);  // starts at ground floor
        Lift lift2 = new Lift("L2", "Lift-B", 8, 5);  // starts at floor 5
        Lift lift3 = new Lift("L3", "Lift-C", 6, 10); // starts at floor 10

        service.addLift(lift1);
        service.addLift(lift2);
        service.addLift(lift3);

        System.out.println("=== Initial Status ===");
        controller.getAllLiftsStatus().forEach(System.out::println);

        // Scenario 1: Person at floor 3 wants to go to floor 7
        System.out.println("\n=== Request: Floor 3 -> Floor 7 ===");
        Lift assigned1 = controller.callLift(3, 7);
        System.out.println("Assigned: " + assigned1.getName());

        // Scenario 2: Person at floor 8 wants to go to floor 2
        System.out.println("\n=== Request: Floor 8 -> Floor 2 ===");
        Lift assigned2 = controller.callLift(8, 2);
        System.out.println("Assigned: " + assigned2.getName());

        // Simulate movement
        System.out.println("\n=== Simulating Movement ===");
        for (int i = 0; i < 5; i++) {
            System.out.println("--- Step " + (i + 1) + " ---");
            controller.step();
            controller.getAllLiftsStatus().forEach(System.out::println);
        }

        // Scenario 3: Person inside Lift-A presses floor 9
        System.out.println("\n=== Internal Request: Lift-A -> Floor 9 ===");
        controller.selectFloor("L1", 9);

        // Continue simulation
        System.out.println("\n=== Continue Simulation ===");
        for (int i = 0; i < 5; i++) {
            System.out.println("--- Step " + (i + 6) + " ---");
            controller.step();
            controller.getAllLiftsStatus().forEach(System.out::println);
        }
    }
}

