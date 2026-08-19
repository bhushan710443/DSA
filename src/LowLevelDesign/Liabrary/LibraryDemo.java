package LowLevelDesign.Liabrary;

import LowLevelDesign.Liabrary.Controller.LibraryController;
import LowLevelDesign.Liabrary.Entity.*;
import LowLevelDesign.Liabrary.Enum.BookType;
import LowLevelDesign.Liabrary.Enum.MembershipType;
import LowLevelDesign.Liabrary.Observer.NotificationService;
import LowLevelDesign.Liabrary.Repository.BookRepository;
import LowLevelDesign.Liabrary.Repository.BorrowRepository;
import LowLevelDesign.Liabrary.Repository.ReservationRepository;
import LowLevelDesign.Liabrary.Repository.UserRepository;
import LowLevelDesign.Liabrary.Service.LibraryService;
import LowLevelDesign.Liabrary.Strategy.NameOrAuthorSearchStrategy;
import LowLevelDesign.Liabrary.Strategy.PerDayFineStrategy;

import java.util.List;

/**
 * ╔═══════════════════════════════════════════════════════════════════════╗
 * ║        LIBRARY MANAGEMENT SYSTEM - LOW LEVEL DESIGN (v3)              ║
 * ╠═══════════════════════════════════════════════════════════════════════╣
 * ║                                                                       ║
 * ║  Design Patterns Used:                                                ║
 * ║  ─────────────────────                                                ║
 * ║  1. Strategy Pattern  → FineCalculation, BookSearch                   ║
 * ║  2. Observer Pattern  → NotificationService (borrow/return/renew)     ║
 * ║  3. Repository Pattern → Data access abstraction                      ║
 * ║  4. Layered Architecture → Controller → Service → Repository          ║
 * ║                                                                       ║
 * ║  SOLID Principles:                                                    ║
 * ║  ─────────────────                                                    ║
 * ║  S - Each class has single responsibility                             ║
 * ║  O - Open for extension (new strategies, observers, membership tiers) ║
 * ║  L - Subtypes substitutable (strategy & observer interfaces)          ║
 * ║  I - Focused interfaces (FineStrategy, SearchStrategy, Observer)      ║
 * ║  D - Dependencies injected via constructor                            ║
 * ║                                                                       ║
 * ║  Key Features:                                                        ║
 * ║  ─────────────                                                        ║
 * ║  • Borrow & Return with ticket tracking                               ║
 * ║  • Book Renewal with membership-based limits                          ║
 * ║  • Book Reservation (waitlist with FIFO queue)                        ║
 * ║  • Observer-based notifications (borrow, return, fine, renewal)       ║
 * ║  • Membership tiers (BASIC, PREMIUM, STUDENT) with different limits   ║
 * ║  • Per-day fine (based on membership free days)                       ║
 * ║  • Thread-safe borrow/return/renew (synchronized)                     ║
 * ║  • Search by name/author (Strategy pattern)                           ║
 * ║  • Locate book (shelf location or who has it + due date)              ║
 * ║  • Renewal blocked if waitlist exists (fairness)                      ║
 * ║                                                                       ║
 * ╚═══════════════════════════════════════════════════════════════════════╝
 */
public class LibraryDemo {

    public static void main(String[] args) {

        // ─── 1. Setup Repositories ───────────────────────────────────
        BookRepository bookRepository = new BookRepository();
        UserRepository userRepository = new UserRepository();
        BorrowRepository borrowRepository = new BorrowRepository();
        ReservationRepository reservationRepository = new ReservationRepository();

        // ─── 2. Setup Strategies ─────────────────────────────────────
        PerDayFineStrategy fineStrategy = new PerDayFineStrategy(5.0); // ₹5/day overdue
        NameOrAuthorSearchStrategy searchStrategy = new NameOrAuthorSearchStrategy();

        // ─── 3. Create Service & Controller ──────────────────────────
        LibraryService libraryService = new LibraryService(
                bookRepository, userRepository, borrowRepository,
                reservationRepository, fineStrategy, searchStrategy);

        // ─── 4. Register Observer (Notification Service) ─────────────
        NotificationService notificationService = new NotificationService();
        libraryService.addObserver(notificationService);

        LibraryController controller = new LibraryController(libraryService);

        // ─── 5. Initialize Data ──────────────────────────────────────
        initializeBooks(controller);
        initializeUsers(controller);

        // ─── 6. Demo Scenarios ───────────────────────────────────────
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("     LIBRARY MANAGEMENT SYSTEM DEMO (v3 - Improved)");
        System.out.println("═══════════════════════════════════════════════════════\n");

        // Stats
        System.out.println("📊 Library Stats:");
        System.out.println("   Total Available: " + controller.getAvailableBookCount());
        System.out.println("   Currently Borrowed: " + controller.getBorrowedBookCount());
        System.out.println();

        // ─── BORROW BOOKS (with notifications) ──────────────────────
        System.out.println("─── BORROWING BOOKS ────────────────────────────────\n");

        BookBorrowed borrow1 = controller.borrowBook("U1", "B1");
        BookBorrowed borrow2 = controller.borrowBook("U1", "B3");
        BookBorrowed borrow3 = controller.borrowBook("U2", "B5");
        BookBorrowed borrow4 = controller.borrowBook("U2", "B2");
        System.out.println();

        // ─── RENEWAL SYSTEM ───────────────────────────────────────────
        System.out.println("─── BOOK RENEWAL ───────────────────────────────────\n");

        // U1 renews B1 (BASIC membership: 1 renewal allowed)
        BookBorrowed renewed = controller.renewBook(borrow1.getBorrowId());
        System.out.println("   Due date extended: " + renewed.getDueDate().toLocalDate());
        System.out.println();

        // U1 tries to renew again → should FAIL (BASIC = 1 renewal max)
        try {
            controller.renewBook(borrow1.getBorrowId());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
        System.out.println();

        // ─── RESERVATION SYSTEM ─────────────────────────────────────
        System.out.println("─── RESERVATION (WAITLIST) ─────────────────────────\n");

        // U3 wants B1, but it's borrowed by U1
        Reservation res1 = controller.reserveBook("U3", "B1");
        System.out.println("📋 " + res1);

        // U4 also wants B1
        Reservation res2 = controller.reserveBook("U4", "B1");
        System.out.println("📋 " + res2);
        System.out.println();

        // Locate B1 — should show who has it + due date + waitlist count
        System.out.println("📍 " + controller.locateBook("B1"));
        System.out.println();

        // ─── RETURN WITH RESERVATION NOTIFICATION ────────────────────
        System.out.println("─── RETURNING BOOK (triggers reservation notify) ───\n");

        // U1 returns B1 → U3 should get notification (first in queue)
        controller.returnBook(borrow1.getBorrowId());
        System.out.println();

        // ─── SEARCH BOOKS ────────────────────────────────────────────
        System.out.println("─── SEARCHING BOOKS ────────────────────────────────\n");

        List<Book> results = controller.searchBooks("Psychology");
        System.out.println("🔍 Search 'Psychology': " + results.size() + " results");
        results.forEach(b -> System.out.println("   → " + b));
        System.out.println();

        results = controller.searchBooks("Verma");
        System.out.println("🔍 Search by author 'Verma': " + results.size() + " results");
        results.forEach(b -> System.out.println("   → " + b));
        System.out.println();

        // ─── LOCATE BOOK ─────────────────────────────────────────────
        System.out.println("─── LOCATING BOOKS ─────────────────────────────────\n");

        System.out.println("📍 " + controller.locateBook("B3")); // Borrowed - shows due date
        System.out.println("📍 " + controller.locateBook("B4")); // Available on shelf
        System.out.println();

        // ─── MEMBERSHIP DEMO ─────────────────────────────────────────
        System.out.println("─── MEMBERSHIP LIMITS ──────────────────────────────\n");

        // Student (U5) can only borrow 2 books
        controller.borrowBook("U5", "B6");
        controller.borrowBook("U5", "B7");
        try {
            controller.borrowBook("U5", "B8"); // 3rd book - should FAIL for STUDENT
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
        System.out.println();

        // Premium (U4) can borrow up to 5 books
        controller.borrowBook("U4", "B4");
        controller.borrowBook("U4", "B8");
        System.out.println("✅ Premium user U4 borrowed 2 books (limit: 5)");
        System.out.println();

        // ─── ERROR HANDLING ──────────────────────────────────────────
        System.out.println("─── ERROR HANDLING ─────────────────────────────────\n");

        try {
            controller.borrowBook("U99", "B1"); // Invalid user
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }

        try {
            controller.borrowBook("U1", "B99"); // Invalid book
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }

        try {
            controller.returnBook("INVALID-ID");
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }

        try {
            controller.reserveBook("U3", "B1"); // Already reserved
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
        System.out.println();

        // ─── FINAL STATS ─────────────────────────────────────────────
        System.out.println("─── FINAL STATS ────────────────────────────────────\n");
        System.out.println("📊 Available: " + controller.getAvailableBookCount());
        System.out.println("📊 Borrowed:  " + controller.getBorrowedBookCount());
        System.out.println();

        // User history
        System.out.println("📖 Bhavesh's borrow history:");
        controller.getUserHistory("U1").forEach(h -> System.out.println("   → " + h));

        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("            DEMO COMPLETED SUCCESSFULLY");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    // ─── DATA INITIALIZATION ─────────────────────────────────────────

    private static void initializeBooks(LibraryController controller) {
        controller.addBook(new Book("B1", "Introduction to Physics", "H.C. Verma",
                BookType.MATHEMATICS, new BookAddress("A", BookType.MATHEMATICS, "R1", 1)));

        controller.addBook(new Book("B2", "Sapiens: A Brief History", "Yuval Noah Harari",
                BookType.HISTORY, new BookAddress("B", BookType.HISTORY, "R2", 3)));

        controller.addBook(new Book("B3", "Thinking Fast and Slow", "Daniel Kahneman",
                BookType.PSYCHOLOGY, new BookAddress("C", BookType.PSYCHOLOGY, "R1", 2)));

        controller.addBook(new Book("B4", "A Short History of Nearly Everything", "Bill Bryson",
                BookType.GEOGRAPHY, new BookAddress("B", BookType.GEOGRAPHY, "R3", 1)));

        controller.addBook(new Book("B5", "The Psychology of Money", "Morgan Housel",
                BookType.PSYCHOLOGY, new BookAddress("C", BookType.PSYCHOLOGY, "R2", 4)));

        controller.addBook(new Book("B6", "Calculus Made Easy", "Silvanus Thompson",
                BookType.MATHEMATICS, new BookAddress("A", BookType.MATHEMATICS, "R2", 2)));

        controller.addBook(new Book("B7", "Guns, Germs, and Steel", "Jared Diamond",
                BookType.HISTORY, new BookAddress("B", BookType.HISTORY, "R1", 5)));

        controller.addBook(new Book("B8", "Man's Search for Meaning", "Viktor Frankl",
                BookType.PHILOSOPHY, new BookAddress("D", BookType.PHILOSOPHY, "R1", 1)));
    }

    private static void initializeUsers(LibraryController controller) {
        controller.registerUser(new User("U1", "Bhavesh", "bhavesh@email.com", MembershipType.BASIC));
        controller.registerUser(new User("U2", "Priya", "priya@email.com", MembershipType.PREMIUM));
        controller.registerUser(new User("U3", "Raj", "raj@email.com", MembershipType.BASIC));
        controller.registerUser(new User("U4", "Sneha", "sneha@email.com", MembershipType.PREMIUM));
        controller.registerUser(new User("U5", "Amit", "amit@email.com", MembershipType.STUDENT));
    }
}
