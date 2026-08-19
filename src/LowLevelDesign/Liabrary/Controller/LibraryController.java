package LowLevelDesign.Liabrary.Controller;

import LowLevelDesign.Liabrary.Entity.Book;
import LowLevelDesign.Liabrary.Entity.BookBorrowed;
import LowLevelDesign.Liabrary.Entity.Reservation;
import LowLevelDesign.Liabrary.Entity.User;
import LowLevelDesign.Liabrary.Enum.BookType;
import LowLevelDesign.Liabrary.Service.LibraryService;

import java.util.List;

/**
 * Controller layer — Entry point / API for the Library System.
 * Delegates all business logic to LibraryService.
 *
 * Interview point: "This mirrors REST controller pattern.
 * In Spring Boot, each method would have @GetMapping/@PostMapping annotations."
 */
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    // ─── Borrow, Return, Renew & Reserve ──────────────────────────

    public BookBorrowed borrowBook(String userId, String bookId) {
        return libraryService.borrowBook(userId, bookId);
    }

    public BookBorrowed returnBook(String borrowId) {
        return libraryService.returnBook(borrowId);
    }

    public BookBorrowed renewBook(String borrowId) {
        return libraryService.renewBook(borrowId);
    }

    public Reservation reserveBook(String userId, String bookId) {
        return libraryService.reserveBook(userId, bookId);
    }

    // ─── Search & Locate ─────────────────────────────────────────

    public List<Book> searchBooks(String query) {
        return libraryService.searchBooks(query);
    }

    public List<Book> getAvailableBooks() {
        return libraryService.getAvailableBooks();
    }

    public List<Book> getBooksByType(BookType type) {
        return libraryService.getBooksByType(type);
    }

    public String locateBook(String bookId) {
        return libraryService.locateBook(bookId);
    }

    // ─── User Management ─────────────────────────────────────────

    public void registerUser(User user) {
        libraryService.registerUser(user);
    }

    // ─── Admin: Add Book ─────────────────────────────────────────

    public void addBook(Book book) {
        libraryService.addBook(book);
    }

    // ─── Stats ───────────────────────────────────────────────────

    public long getAvailableBookCount() {
        return libraryService.getAvailableBookCount();
    }

    public long getBorrowedBookCount() {
        return libraryService.getBorrowedBookCount();
    }

    public List<BookBorrowed> getUserHistory(String userId) {
        return libraryService.getUserBorrowHistory(userId);
    }

    public List<Reservation> getUserReservations(String userId) {
        return libraryService.getUserReservations(userId);
    }
}
