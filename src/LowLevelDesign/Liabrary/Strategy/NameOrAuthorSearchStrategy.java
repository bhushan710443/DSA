package LowLevelDesign.Liabrary.Strategy;

import LowLevelDesign.Liabrary.Entity.Book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Searches books by name or author (case-insensitive contains match).
 */
public class NameOrAuthorSearchStrategy implements BookSearchStrategy {

    @Override
    public List<Book> search(List<Book> books, String query) {
        String lowerQuery = query.toLowerCase();
        return books.stream()
                .filter(book ->
                        book.getName().toLowerCase().contains(lowerQuery) ||
                        book.getAuthor().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }
}

