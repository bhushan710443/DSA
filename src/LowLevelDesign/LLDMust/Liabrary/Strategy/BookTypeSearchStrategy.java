package LowLevelDesign.LLDMust.Liabrary.Strategy;

import LowLevelDesign.LLDMust.Liabrary.Entity.Book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Searches books by their type/category.
 * Demonstrates Strategy Pattern extensibility — just add a new class.
 */
public class BookTypeSearchStrategy implements BookSearchStrategy {

    @Override
    public List<Book> search(List<Book> books, String query) {
        String upperQuery = query.toUpperCase().trim();
        return books.stream()
                .filter(book -> book.getType().name().contains(upperQuery))
                .collect(Collectors.toList());
    }
}

