package LowLevelDesign.Liabrary.Repository;

import LowLevelDesign.Liabrary.Entity.Book;
import LowLevelDesign.Liabrary.Enum.BookAvailability;
import LowLevelDesign.Liabrary.Enum.BookType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookRepository {

    private final Map<String, Book> books = new ConcurrentHashMap<>();

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    public Book getById(String bookId) {
        return books.get(bookId);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Book> getAvailableBooks() {
        return books.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Book> searchByName(String keyword) {
        return books.values().stream()
                .filter(book -> book.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getByType(BookType type) {
        return books.values().stream()
                .filter(book -> book.getType() == type)
                .collect(Collectors.toList());
    }

    public long countAvailable() {
        return books.values().stream().filter(Book::isAvailable).count();
    }

    public long countByStatus(BookAvailability status) {
        return books.values().stream()
                .filter(book -> book.getAvailability() == status)
                .count();
    }
}

