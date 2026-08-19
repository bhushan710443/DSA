package LowLevelDesign.Liabrary.Service;

import LowLevelDesign.Liabrary.Entity.*;
import LowLevelDesign.Liabrary.Enum.BookAvailability;
import LowLevelDesign.Liabrary.Enum.BookType;
import LowLevelDesign.Liabrary.Exceptions.*;
import LowLevelDesign.Liabrary.Observer.LibraryObserver;
import LowLevelDesign.Liabrary.Repository.BookRepository;
import LowLevelDesign.Liabrary.Repository.BorrowRepository;
import LowLevelDesign.Liabrary.Repository.ReservationRepository;
import LowLevelDesign.Liabrary.Repository.UserRepository;
import LowLevelDesign.Liabrary.Strategy.BookSearchStrategy;
import LowLevelDesign.Liabrary.Strategy.FineCalculationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Core service for Library Management System.
 *
 * Design Principles Applied:
 * ──────────────────────────
 * 1. SRP          → Only business logic here, no I/O
 * 2. OCP          → New fine/search strategies without modifying this class
 * 3. DIP          → Depends on abstractions (Strategy interfaces, Observer interface)
 * 4. Observer     → Decoupled notifications on borrow/return/fine/renewal events
 * 5. Strategy     → Pluggable fine calculation and search algorithms
 * 6. Repository   → Data access abstracted away from business logic
 * 7. Thread-safe  → Synchronized critical sections for concurrent access
 */
public class LibraryService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowRepository borrowRepository;
    private final ReservationRepository reservationRepository;
    private final FineCalculationStrategy fineStrategy;
    private final BookSearchStrategy searchStrategy;
    private final List<LibraryObserver> observers;

    public LibraryService(BookRepository bookRepository,
                          UserRepository userRepository,
                          BorrowRepository borrowRepository,
                          ReservationRepository reservationRepository,
                          FineCalculationStrategy fineStrategy,
                          BookSearchStrategy searchStrategy) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.borrowRepository = borrowRepository;
        this.reservationRepository = reservationRepository;
        this.fineStrategy = fineStrategy;
        this.searchStrategy = searchStrategy;
        this.observers = new ArrayList<>();
    }

    // ─── OBSERVER MANAGEMENT ─────────────────────────────────────────

    public void addObserver(LibraryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LibraryObserver observer) {
        observers.remove(observer);
    }

    // ─── BORROW BOOK (Thread-safe) ──────────────────────────────────

    public synchronized BookBorrowed borrowBook(String userId, String bookId) {
        User user = getValidUser(userId);
        Book book = getValidBook(bookId);

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(
                    "Book '" + book.getName() + "' is not available. Status: " + book.getAvailability() +
                    ". Use reserveBook() to join the waitlist.");
        }

        if (!user.canBorrowMore()) {
            throw new BorrowLimitExceededException(
                    "User '" + user.getName() + "' has reached max limit of " +
                    user.getMaxBooksAllowed() + " books (" + user.getMembership() + " membership).");
        }

        // Mark book as borrowed
        book.setAvailability(BookAvailability.WITH_USER);

        // Create borrow record
        String borrowId = generateBorrowId();
        BookBorrowed record = new BookBorrowed(borrowId, book, user);
        borrowRepository.save(record);
        user.addBorrowRecord(record);

        // Notify observers
        notifyBookBorrowed(bookId, book.getName(), userId);

        return record;
    }

    // ─── RETURN BOOK (Thread-safe + Reservation handling) ────────────

    public synchronized BookBorrowed returnBook(String borrowId) {
        BookBorrowed record = borrowRepository.getById(borrowId);
        if (record == null) {
            throw new InvalidBorrowIdException("Invalid borrow ID: " + borrowId);
        }

        if (record.isReturned()) {
            throw new InvalidBorrowIdException("Book already returned for borrow ID: " + borrowId);
        }

        // Calculate fine based on user's membership free days
        int allowedDays = record.getBorrowedBy().getFreeDays();
        double fine = fineStrategy.calculateFine(record, allowedDays);

        // Mark as returned
        record.returnBook(fine);
        Book book = record.getBook();

        // Notify fine if applicable
        if (fine > 0) {
            notifyFineCharged(record.getBorrowedBy().getUserId(), book.getName(), fine);
        }

        // Check if someone has reserved this book (FIFO queue)
        Reservation nextReservation = reservationRepository.pollNextReservation(book.getBookId());
        if (nextReservation != null) {
            // Keep book as "reserved" — notify the waiting user to come pick it up
            book.setAvailability(BookAvailability.RESERVED);
            nextReservation.markFulfilled();
            notifyBookAvailable(book.getBookId(), book.getName(), nextReservation.getUser().getUserId());
        } else {
            // No reservation, make it available on shelf
            book.setAvailability(BookAvailability.AVAILABLE);
        }

        return record;
    }

    // ─── RENEW BOOK (Extend due date) ────────────────────────────────

    public synchronized BookBorrowed renewBook(String borrowId) {
        BookBorrowed record = borrowRepository.getById(borrowId);
        if (record == null) {
            throw new InvalidBorrowIdException("Invalid borrow ID: " + borrowId);
        }

        if (record.isReturned()) {
            throw new InvalidBorrowIdException("Cannot renew — book already returned: " + borrowId);
        }

        if (!record.canRenew()) {
            throw new RenewalLimitExceededException(
                    "User '" + record.getBorrowedBy().getName() + "' has exhausted renewals (" +
                    record.getRenewalCount() + "/" +
                    record.getBorrowedBy().getMembership().getMaxRenewals() + ") for this book.");
        }

        // Cannot renew if book has pending reservations (fairness)
        if (reservationRepository.hasReservation(record.getBook().getBookId())) {
            throw new BookNotAvailableException(
                    "Cannot renew — other users are waiting for this book. Please return it.");
        }

        int additionalDays = record.getBorrowedBy().getFreeDays();
        record.renew(additionalDays);

        // Notify observers about renewal
        notifyBookRenewed(record.getBook().getBookId(), record.getBook().getName(),
                record.getBorrowedBy().getUserId(), record.getRenewalCount());

        return record;
    }

    // ─── RESERVE BOOK (Waitlist) ─────────────────────────────────────

    public Reservation reserveBook(String userId, String bookId) {
        User user = getValidUser(userId);
        Book book = getValidBook(bookId);

        if (book.isAvailable()) {
            throw new BookNotAvailableException(
                    "Book '" + book.getName() + "' is already available. Use borrowBook() instead.");
        }

        if (reservationRepository.isUserAlreadyInQueue(bookId, userId)) {
            throw new BookNotAvailableException(
                    "User '" + user.getName() + "' already has a reservation for this book.");
        }

        String reservationId = "RSV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Reservation reservation = new Reservation(reservationId, user, bookId);
        reservationRepository.addReservation(reservation);

        return reservation;
    }

    // ─── SEARCH BOOKS (Delegated to Strategy) ─────────────────────────

    public List<Book> searchBooks(String query) {
        return searchStrategy.search(bookRepository.getAllBooks(), query);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    public List<Book> getBooksByType(BookType type) {
        return bookRepository.getByType(type);
    }

    // ─── LOCATE BOOK ─────────────────────────────────────────────────

    public String locateBook(String bookId) {
        Book book = getValidBook(bookId);

        if (book.isAvailable()) {
            return "Book '" + book.getName() + "' is at: " + book.getAddress();
        }

        BookBorrowed activeRecord = borrowRepository.getActiveRecordByBookId(bookId);
        if (activeRecord != null) {
            int queueSize = reservationRepository.getQueueSize(bookId);
            return "Book '" + book.getName() + "' is with: " +
                    activeRecord.getBorrowedBy().getName() +
                    " (since " + activeRecord.getBorrowedTime().toLocalDate() +
                    ", due " + activeRecord.getDueDate().toLocalDate() + ")" +
                    (queueSize > 0 ? " | Waitlist: " + queueSize + " users" : "");
        }

        return "Book '" + book.getName() + "' is not available. Status: " + book.getAvailability();
    }

    // ─── USER MANAGEMENT ─────────────────────────────────────────────

    public void registerUser(User user) {
        userRepository.addUser(user);
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    // ─── STATS ───────────────────────────────────────────────────────

    public long getAvailableBookCount() {
        return bookRepository.countAvailable();
    }

    public long getBorrowedBookCount() {
        return bookRepository.countByStatus(BookAvailability.WITH_USER);
    }

    public List<BookBorrowed> getUserBorrowHistory(String userId) {
        User user = getValidUser(userId);
        return user.getBorrowHistory();
    }

    public List<Reservation> getUserReservations(String userId) {
        return reservationRepository.getUserReservations(userId);
    }

    // ─── PRIVATE HELPERS ─────────────────────────────────────────────

    private User getValidUser(String userId) {
        User user = userRepository.getById(userId);
        if (user == null) throw new UserNotFoundException("User not found: " + userId);
        return user;
    }

    private Book getValidBook(String bookId) {
        Book book = bookRepository.getById(bookId);
        if (book == null) throw new BookNotFoundException("Book not found: " + bookId);
        return book;
    }

    private String generateBorrowId() {
        return "BRW-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // ─── OBSERVER NOTIFICATIONS ──────────────────────────────────────

    private void notifyBookAvailable(String bookId, String bookName, String userId) {
        for (LibraryObserver observer : observers) {
            observer.onBookAvailable(bookId, bookName, userId);
        }
    }

    private void notifyBookBorrowed(String bookId, String bookName, String userId) {
        for (LibraryObserver observer : observers) {
            observer.onBookBorrowed(bookId, bookName, userId);
        }
    }

    private void notifyFineCharged(String userId, String bookName, double fine) {
        for (LibraryObserver observer : observers) {
            observer.onFineCharged(userId, bookName, fine);
        }
    }

    private void notifyBookRenewed(String bookId, String bookName, String userId, int renewalCount) {
        for (LibraryObserver observer : observers) {
            observer.onBookRenewed(bookId, bookName, userId, renewalCount);
        }
    }
}
