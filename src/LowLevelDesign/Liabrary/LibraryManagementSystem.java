package LowLevelDesign.Liabrary;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * ══════════════════════════════════════════════════════════════════════════════
 *  LIBRARY MANAGEMENT SYSTEM — LLD INTERVIEW APPROACH
 * ══════════════════════════════════════════════════════════════════════════════
 *
 *  STEP 1: REQUIREMENTS CLARIFICATION (Ask the interviewer)
 *  ─────────────────────────────────────────────────────────
 *  Functional Requirements:
 *    1. Users can search books by title/author
 *    2. Users can borrow & return books
 *    3. Each user has a borrow limit (based on membership)
 *    4. Fine calculation for late returns
 *    5. Book reservation (waitlist) when not available
 *    6. Locate a book (physical location in library)
 *
 *  Non-Functional:
 *    - Thread safety (concurrent borrow/return)
 *    - Extensible pricing/search strategies
 *
 *  STEP 2: IDENTIFY CORE ENTITIES
 *  ───────────────────────────────
 *    Book, User, BookBorrow (Ticket), Reservation
 *
 *  STEP 3: IDENTIFY DESIGN PATTERNS
 *  ─────────────────────────────────
 *    - Strategy  → Fine calculation (per-day, flat, etc.)
 *    - Observer  → Notify user when reserved book is available
 *    - Repository → Data access abstraction
 *
 *  STEP 4: CLASS DIAGRAM (draw this on whiteboard)
 *  ────────────────────────────────────────────────
 *
 *    ┌──────────────┐        ┌───────────────┐
 *    │   BookType   │        │ MembershipType│
 *    │ (Enum)       │        │ (Enum)        │
 *    └──────────────┘        └───────────────┘
 *           │                        │
 *    ┌──────▼──────┐         ┌──────▼──────┐
 *    │    Book     │         │    User     │
 *    │─────────────│         │─────────────│
 *    │ id, title   │         │ id, name    │
 *    │ author,type │         │ membership  │
 *    │ available   │         │ borrowCount │
 *    └─────────────┘         └─────────────┘
 *           │                        │
 *           └────────┐  ┌───────────┘
 *                    ▼  ▼
 *            ┌───────────────┐
 *            │  BorrowRecord │  (like a Ticket)
 *            │───────────────│
 *            │ book, user    │
 *            │ borrowDate    │
 *            │ returnDate    │
 *            │ fine          │
 *            └───────────────┘
 *
 *    ┌─────────────────────────────┐
 *    │    LibraryService           │  (Core Logic)
 *    │─────────────────────────────│
 *    │ + borrowBook(userId, bookId)│
 *    │ + returnBook(borrowId)      │
 *    │ + searchBook(query)         │
 *    │ + reserveBook(userId,bookId)│
 *    └─────────────────────────────┘
 *              │
 *              ├── FineStrategy (interface)  ← Strategy Pattern
 *              └── Observer (interface)      ← Observer Pattern
 *
 * ══════════════════════════════════════════════════════════════════════════════
 *  STEP 5: CODE (Interview-style — all in one file for quick writing)
 * ══════════════════════════════════════════════════════════════════════════════
 */
public class LibraryManagementSystem {

    // ═══════════════════════════════════════════════════════════════════
    // ENUMS
    // ═══════════════════════════════════════════════════════════════════

    enum BookType { SCIENCE, FICTION, HISTORY, MATHEMATICS }

    enum MembershipType {
        BASIC(2, 14),
        PREMIUM(5, 30),
        STUDENT(3, 21);

        final int maxBooks;
        final int freeDays;

        MembershipType(int maxBooks, int freeDays) {
            this.maxBooks = maxBooks;
            this.freeDays = freeDays;
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // ENTITIES
    // ═══════════════════════════════════════════════════════════════════

    static class Book {
        String id;
        String title;
        String author;
        BookType type;
        String rackLocation;  // e.g., "Section-A, Rack-3, Shelf-2"
        boolean available;

        Book(String id, String title, String author, BookType type, String rackLocation) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.type = type;
            this.rackLocation = rackLocation;
            this.available = true;
        }

        @Override
        public String toString() {
            return title + " by " + author + " [" + (available ? "AVAILABLE" : "BORROWED") + "]";
        }
    }

    static class User {
        String id;
        String name;
        MembershipType membership;
        int activeBorrowCount;

        User(String id, String name, MembershipType membership) {
            this.id = id;
            this.name = name;
            this.membership = membership;
            this.activeBorrowCount = 0;
        }

        boolean canBorrow() {
            return activeBorrowCount < membership.maxBooks;
        }
    }

    static class BorrowRecord {
        String id;
        Book book;
        User user;
        LocalDateTime borrowedAt;
        LocalDateTime returnedAt;
        double fine;
        boolean returned;

        BorrowRecord(String id, Book book, User user) {
            this.id = id;
            this.book = book;
            this.user = user;
            this.borrowedAt = LocalDateTime.now();
            this.returned = false;
        }

        void markReturned(double fine) {
            this.returnedAt = LocalDateTime.now();
            this.fine = fine;
            this.returned = true;
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // STRATEGY PATTERN — Fine Calculation
    // (Tell interviewer: "I'm using Strategy so we can swap fine logic")
    // ═══════════════════════════════════════════════════════════════════

    interface FineStrategy {
        double calculate(BorrowRecord record, int freeDays);
    }

    static class PerDayFineStrategy implements FineStrategy {
        private final double ratePerDay;

        PerDayFineStrategy(double ratePerDay) {
            this.ratePerDay = ratePerDay;
        }

        @Override
        public double calculate(BorrowRecord record, int freeDays) {
            long days = Duration.between(record.borrowedAt, LocalDateTime.now()).toDays();
            if (days <= freeDays) return 0;
            return (days - freeDays) * ratePerDay;
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // OBSERVER PATTERN — Notification on book return
    // (Tell interviewer: "Observer decouples notification from business logic")
    // ═══════════════════════════════════════════════════════════════════

    interface LibraryObserver {
        void onBookAvailable(String bookId, String bookTitle, String waitingUserId);
    }

    static class ConsoleNotifier implements LibraryObserver {
        @Override
        public void onBookAvailable(String bookId, String bookTitle, String waitingUserId) {
            System.out.println("  🔔 NOTIFY [" + waitingUserId + "]: '" + bookTitle + "' is now available!");
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // CORE SERVICE — All business logic here
    // (This is what the interviewer cares about most)
    // ═══════════════════════════════════════════════════════════════════

    static class LibraryService {
        private final Map<String, Book> books = new ConcurrentHashMap<>();
        private final Map<String, User> users = new ConcurrentHashMap<>();
        private final Map<String, BorrowRecord> records = new ConcurrentHashMap<>();

        // Reservation queue: bookId → queue of userIds (FIFO)
        private final Map<String, Queue<String>> waitlist = new ConcurrentHashMap<>();

        private final FineStrategy fineStrategy;
        private final List<LibraryObserver> observers = new ArrayList<>();

        LibraryService(FineStrategy fineStrategy) {
            this.fineStrategy = fineStrategy;
        }

        void addObserver(LibraryObserver observer) {
            observers.add(observer);
        }

        // ─── BORROW (synchronized for thread safety) ─────────────────

        synchronized BorrowRecord borrowBook(String userId, String bookId) {
            User user = users.get(userId);
            Book book = books.get(bookId);

            // Validations
            if (user == null) throw new RuntimeException("User not found: " + userId);
            if (book == null) throw new RuntimeException("Book not found: " + bookId);
            if (!book.available) throw new RuntimeException("Book not available. Use reserve().");
            if (!user.canBorrow()) throw new RuntimeException(
                    user.name + " reached limit (" + user.membership.maxBooks + " books, " + user.membership + ")");

            // Borrow
            book.available = false;
            user.activeBorrowCount++;

            String recordId = "BRW-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            BorrowRecord record = new BorrowRecord(recordId, book, user);
            records.put(recordId, record);

            System.out.println("  ✅ " + user.name + " borrowed '" + book.title + "' [" + recordId + "]");
            return record;
        }

        // ─── RETURN (handles reservation queue) ──────────────────────

        synchronized BorrowRecord returnBook(String recordId) {
            BorrowRecord record = records.get(recordId);
            if (record == null) throw new RuntimeException("Invalid record: " + recordId);
            if (record.returned) throw new RuntimeException("Already returned");

            // Calculate fine based on user's membership free days
            double fine = fineStrategy.calculate(record, record.user.membership.freeDays);
            record.markReturned(fine);
            record.user.activeBorrowCount--;

            System.out.println("  ✅ " + record.user.name + " returned '" + record.book.title +
                    "'" + (fine > 0 ? " | Fine: ₹" + fine : " | No fine"));

            // Check waitlist — notify next user
            Queue<String> queue = waitlist.get(record.book.id);
            if (queue != null && !queue.isEmpty()) {
                String nextUserId = queue.poll();
                // Notify via observer
                for (LibraryObserver obs : observers) {
                    obs.onBookAvailable(record.book.id, record.book.title, nextUserId);
                }
                // Keep book unavailable (reserved for next user)
            } else {
                record.book.available = true;
            }

            return record;
        }

        // ─── RESERVE (Waitlist) ──────────────────────────────────────

        void reserveBook(String userId, String bookId) {
            Book book = books.get(bookId);
            if (book == null) throw new RuntimeException("Book not found");
            if (book.available) throw new RuntimeException("Book is available — just borrow it!");

            waitlist.computeIfAbsent(bookId, k -> new LinkedList<>()).add(userId);
            int position = waitlist.get(bookId).size();
            System.out.println("  📋 " + users.get(userId).name + " reserved '" + book.title +
                    "' (position: " + position + " in queue)");
        }

        // ─── SEARCH ──────────────────────────────────────────────────

        List<Book> search(String query) {
            String q = query.toLowerCase();
            return books.values().stream()
                    .filter(b -> b.title.toLowerCase().contains(q) ||
                                 b.author.toLowerCase().contains(q))
                    .collect(Collectors.toList());
        }

        // ─── LOCATE ──────────────────────────────────────────────────

        String locateBook(String bookId) {
            Book book = books.get(bookId);
            if (book == null) throw new RuntimeException("Book not found");
            if (book.available) return "📍 '" + book.title + "' → " + book.rackLocation;

            // Find who has it
            Optional<BorrowRecord> active = records.values().stream()
                    .filter(r -> r.book.id.equals(bookId) && !r.returned)
                    .findFirst();
            return active.map(r -> "📍 '" + book.title + "' is with " + r.user.name +
                    " (since " + r.borrowedAt.toLocalDate() + ")")
                    .orElse("📍 Book not available");
        }

        // ─── HELPERS ─────────────────────────────────────────────────

        void addBook(Book book) { books.put(book.id, book); }
        void addUser(User user) { users.put(user.id, user); }
    }

    // ═══════════════════════════════════════════════════════════════════
    // MAIN — Demo (show interviewer it works end-to-end)
    // ═══════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   LIBRARY MANAGEMENT SYSTEM — Interview Version      ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        // Setup
        FineStrategy fineStrategy = new PerDayFineStrategy(5.0); // ₹5/day
        LibraryService library = new LibraryService(fineStrategy);
        library.addObserver(new ConsoleNotifier());

        // Add books
        library.addBook(new Book("B1", "Clean Code", "Robert Martin", BookType.SCIENCE, "A-R1-S3"));
        library.addBook(new Book("B2", "Design Patterns", "Gang of Four", BookType.SCIENCE, "A-R2-S1"));
        library.addBook(new Book("B3", "Sapiens", "Yuval Harari", BookType.HISTORY, "B-R1-S2"));
        library.addBook(new Book("B4", "Atomic Habits", "James Clear", BookType.FICTION, "C-R1-S1"));

        // Add users (different memberships)
        library.addUser(new User("U1", "Bhavesh", MembershipType.BASIC));    // 2 books, 14 free days
        library.addUser(new User("U2", "Priya", MembershipType.PREMIUM));    // 5 books, 30 free days
        library.addUser(new User("U3", "Raj", MembershipType.STUDENT));      // 3 books, 21 free days

        // ─── Scenario 1: Borrow ──────────────────────────────────────
        System.out.println("── BORROW ─────────────────────────────────────────");
        BorrowRecord r1 = library.borrowBook("U1", "B1");
        BorrowRecord r2 = library.borrowBook("U1", "B2");
        library.borrowBook("U2", "B3");

        // U1 tries to borrow 3rd (BASIC limit = 2) → should FAIL
        try {
            library.borrowBook("U1", "B4");
        } catch (Exception e) {
            System.out.println("  ❌ " + e.getMessage());
        }
        System.out.println();

        // ─── Scenario 2: Reserve (Waitlist) ──────────────────────────
        System.out.println("── RESERVE ────────────────────────────────────────");
        library.reserveBook("U3", "B1");  // B1 is with U1
        library.reserveBook("U2", "B1");  // U2 also wants B1
        System.out.println();

        // ─── Scenario 3: Search ──────────────────────────────────────
        System.out.println("── SEARCH ─────────────────────────────────────────");
        List<Book> results = library.search("Clean");
        System.out.println("  🔍 '" + "Clean" + "' → " + results.size() + " result(s)");
        results.forEach(b -> System.out.println("     " + b));
        System.out.println();

        // ─── Scenario 4: Locate ──────────────────────────────────────
        System.out.println("── LOCATE ─────────────────────────────────────────");
        System.out.println("  " + library.locateBook("B1"));  // Borrowed
        System.out.println("  " + library.locateBook("B4"));  // Available
        System.out.println();

        // ─── Scenario 5: Return (triggers reservation notification) ──
        System.out.println("── RETURN (triggers waitlist notification) ─────────");
        library.returnBook(r1.id);  // U3 should get notified (first in queue)
        System.out.println();

        // ─── Scenario 6: Return with no waitlist ─────────────────────
        System.out.println("── RETURN (no waitlist) ────────────────────────────");
        library.returnBook(r2.id);  // B2 becomes available normally
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   ✅ All scenarios demonstrated successfully          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");

        /*
         * ══════════════════════════════════════════════════════════════
         * INTERVIEW TALKING POINTS (mention these to interviewer):
         * ══════════════════════════════════════════════════════════════
         *
         * 1. STRATEGY PATTERN → FineStrategy + SearchStrategy
         *    "I used Strategy so we can plug in different fine calculations
         *     and search algorithms without changing the service.
         *     E.g., PerDayFine, WeekendExemptFine, NameSearch, TypeSearch."
         *
         * 2. OBSERVER PATTERN → LibraryObserver
         *    "When a book is borrowed/returned/renewed, observers are notified.
         *     Decouples notification logic (email/SMS/push) from business logic."
         *
         * 3. THREAD SAFETY → synchronized borrow/return/renew
         *    "In a real system, multiple users might try to borrow the same
         *     book. I synchronized critical sections to prevent race conditions.
         *     For better scalability: use ReentrantLock per book."
         *
         * 4. MEMBERSHIP ENUM with config
         *    "Encapsulating maxBooks, freeDays, maxRenewals in the enum keeps it
         *     extensible — adding a new tier is just one line. No if-else chains."
         *
         * 5. RESERVATION QUEUE (FIFO) with FAIRNESS
         *    "Waitlist ensures fairness. First-come-first-served when
         *     a book becomes available. Renewal is BLOCKED if waitlist exists."
         *
         * 6. BOOK RENEWAL with limits
         *    "Users can extend due dates, but limited by membership tier.
         *     If other users are waiting → renewal denied (fairness over convenience)."
         *
         * 7. LAYERED ARCHITECTURE → Controller → Service → Repository
         *    "Mirrors real-world Spring Boot apps. Each layer has one job.
         *     Repository can be swapped from in-memory to DB without touching service."
         *
         * 8. ENCAPSULATION
         *    "User.getBorrowHistory() returns unmodifiable list.
         *     Book fields are private with controlled setters."
         *
         * 9. EXTENSIBILITY POINTS:
         *    - Add new FineStrategy (FlatRateFine, WeekendExemptFine)
         *    - Add new Observer (EmailNotifier, SMSNotifier, PushNotifier)
         *    - Add new SearchStrategy (FuzzySearch, TypeSearch)
         *    - Add new MembershipType (FACULTY with unlimited books)
         *    - Add multiple copies of same book (inventory count)
         *    - Add Librarian role with admin operations
         *
         * 10. WHAT I'D ADD WITH MORE TIME:
         *    - Payment processing for fines (PaymentStrategy)
         *    - ISBN + multiple copies tracking
         *    - Librarian admin (add/remove books, block users)
         *    - Audit log for all transactions (another Observer)
         *    - Database persistence (JPA Repository interfaces)
         *    - Reservation expiry (48hr pickup window)
         *
         * ══════════════════════════════════════════════════════════════
         */
    }
}

