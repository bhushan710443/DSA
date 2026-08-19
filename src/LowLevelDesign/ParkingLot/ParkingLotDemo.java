package LowLevelDesign.ParkingLot;

import LowLevelDesign.ParkingLot.Controller.ParkingLotController;
import LowLevelDesign.ParkingLot.Entity.ParkingSpot;
import LowLevelDesign.ParkingLot.Entity.ParkingTicket;
import LowLevelDesign.ParkingLot.Entity.Vehicle;
import LowLevelDesign.ParkingLot.Enum.VehicleType;
import LowLevelDesign.ParkingLot.Repository.ParkingSpotRepository;
import LowLevelDesign.ParkingLot.Repository.TicketRepository;
import LowLevelDesign.ParkingLot.Service.ParkingService;
import LowLevelDesign.ParkingLot.Strategy.HourlyPricingStrategy;
import LowLevelDesign.ParkingLot.Strategy.NearestSpotStrategy;

/**
 * ╔═══════════════════════════════════════════════════════════════╗
 * ║              PARKING LOT - LOW LEVEL DESIGN                   ║
 * ╠═══════════════════════════════════════════════════════════════╣
 * ║                                                               ║
 * ║  Design Patterns Used:                                        ║
 * ║  ─────────────────────                                        ║
 * ║  1. Strategy Pattern  → PricingStrategy, SpotAllocation       ║
 * ║  2. Repository Pattern → Data access abstraction              ║
 * ║  3. Layered Architecture → Controller → Service → Repository  ║
 * ║                                                               ║
 * ║  SOLID Principles:                                            ║
 * ║  ─────────────────                                            ║
 * ║  S - Each class has single responsibility                     ║
 * ║  O - Open for extension (new strategies, vehicle types)       ║
 * ║  L - Subtypes are substitutable (strategy interfaces)         ║
 * ║  I - Focused interfaces (PricingStrategy, SpotAllocation)     ║
 * ║  D - Dependencies injected via constructor                    ║
 * ║                                                               ║
 * ║  Key Features:                                                ║
 * ║  ─────────────                                                ║
 * ║  • Multi-floor parking lot with multiple vehicle types        ║
 * ║  • Ticket-based entry/exit system                             ║
 * ║  • Configurable pricing (hourly, flat, etc.)                  ║
 * ║  • Pluggable spot allocation (nearest, random, etc.)          ║
 * ║  • Vehicle search/locate functionality                        ║
 * ║  • Thread-safe repositories (ConcurrentHashMap)               ║
 * ║                                                               ║
 * ╚═══════════════════════════════════════════════════════════════╝
 */
public class ParkingLotDemo {

    public static void main(String[] args) {

        // ─── 1. Setup Repositories ───────────────────────────────────
        ParkingSpotRepository spotRepository = new ParkingSpotRepository();
        TicketRepository ticketRepository = new TicketRepository();

        // ─── 2. Initialize Parking Spots (2 floors) ──────────────────
        initializeParkingSpots(spotRepository);

        // ─── 3. Setup Strategies ─────────────────────────────────────
        NearestSpotStrategy spotStrategy = new NearestSpotStrategy();
        HourlyPricingStrategy pricingStrategy = new HourlyPricingStrategy();

        // ─── 4. Create Service & Controller ──────────────────────────
        ParkingService parkingService = new ParkingService(
                spotRepository, ticketRepository, spotStrategy, pricingStrategy);
        ParkingLotController controller = new ParkingLotController(parkingService);

        // ─── 5. Demo Scenarios ───────────────────────────────────────
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("        PARKING LOT SYSTEM DEMO");
        System.out.println("═══════════════════════════════════════════════\n");

        // Check availability
        System.out.println("📊 Available Spots:");
        System.out.println("   BIKE spots: " + controller.getAvailableSpots(VehicleType.BIKE));
        System.out.println("   CAR spots:  " + controller.getAvailableSpots(VehicleType.CAR));
        System.out.println("   TRUCK spots: " + controller.getAvailableSpots(VehicleType.TRUCK));
        System.out.println();

        // Park vehicles
        System.out.println("─── PARKING VEHICLES ───────────────────────\n");
        Vehicle car1 = new Vehicle("MH12AB1234", VehicleType.CAR, "Bhavesh");
        Vehicle bike1 = new Vehicle("MH14XY5678", VehicleType.BIKE, "Raj");
        Vehicle truck1 = new Vehicle("MH01ZZ9999", VehicleType.TRUCK, "Kumar");
        Vehicle car2 = new Vehicle("KA05CD4321", VehicleType.CAR, "Priya");

        ParkingTicket ticket1 = controller.entry(car1);
        ParkingTicket ticket2 = controller.entry(bike1);
        ParkingTicket ticket3 = controller.entry(truck1);
        ParkingTicket ticket4 = controller.entry(car2);

        System.out.println();

        // Check availability after parking
        System.out.println("📊 Available Spots After Parking:");
        System.out.println("   BIKE spots: " + controller.getAvailableSpots(VehicleType.BIKE));
        System.out.println("   CAR spots:  " + controller.getAvailableSpots(VehicleType.CAR));
        System.out.println("   TRUCK spots: " + controller.getAvailableSpots(VehicleType.TRUCK));
        System.out.println();

        // Locate a vehicle
        System.out.println("─── LOCATING VEHICLE ───────────────────────\n");
        ParkingSpot locatedSpot = controller.locateVehicle("MH12AB1234");
        System.out.println("🔍 Vehicle MH12AB1234 found at: " + locatedSpot);
        System.out.println();

        // Exit / Unpark
        System.out.println("─── EXITING VEHICLES ───────────────────────\n");
        ParkingTicket exitTicket1 = controller.exit(ticket1.getTicketId());
        ParkingTicket exitTicket2 = controller.exit(ticket2.getTicketId());
        System.out.println();

        // Final availability
        System.out.println("📊 Available Spots After Exit:");
        System.out.println("   BIKE spots: " + controller.getAvailableSpots(VehicleType.BIKE));
        System.out.println("   CAR spots:  " + controller.getAvailableSpots(VehicleType.CAR));
        System.out.println("   TRUCK spots: " + controller.getAvailableSpots(VehicleType.TRUCK));
        System.out.println();

        // Try parking duplicate vehicle (should throw exception)
        System.out.println("─── ERROR HANDLING ─────────────────────────\n");
        try {
            controller.entry(truck1); // Already parked
        } catch (Exception e) {
            System.out.println("❌ Expected Error: " + e.getMessage());
        }

        try {
            controller.exit("INVALID-TICKET");
        } catch (Exception e) {
            System.out.println("❌ Expected Error: " + e.getMessage());
        }

        try {
            controller.locateVehicle("XX00XX0000"); // Not parked
        } catch (Exception e) {
            System.out.println("❌ Expected Error: " + e.getMessage());
        }

        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("        DEMO COMPLETED SUCCESSFULLY");
        System.out.println("═══════════════════════════════════════════════");
    }

    /**
     * Initialize parking spots for the demo.
     * Floor 1: 5 Bike, 5 Car, 2 Truck
     * Floor 2: 5 Bike, 5 Car, 2 Truck
     */
    private static void initializeParkingSpots(ParkingSpotRepository repository) {
        int spotCounter = 1;

        for (int floor = 1; floor <= 2; floor++) {
            // Bike spots
            for (int i = 1; i <= 5; i++) {
                String spotId = "F" + floor + "-B" + i;
                repository.addSpot(new ParkingSpot(spotId, floor, spotCounter++, VehicleType.BIKE));
            }
            // Car spots
            for (int i = 1; i <= 5; i++) {
                String spotId = "F" + floor + "-C" + i;
                repository.addSpot(new ParkingSpot(spotId, floor, spotCounter++, VehicleType.CAR));
            }
            // Truck spots
            for (int i = 1; i <= 2; i++) {
                String spotId = "F" + floor + "-T" + i;
                repository.addSpot(new ParkingSpot(spotId, floor, spotCounter++, VehicleType.TRUCK));
            }
        }
    }
}

