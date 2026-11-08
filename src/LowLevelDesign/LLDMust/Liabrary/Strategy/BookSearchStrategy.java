package LowLevelDesign.LLDMust.Liabrary.Strategy;

import LowLevelDesign.LLDMust.Liabrary.Entity.Book;

import java.util.List;

/**
 * Strategy Pattern: Different book search/recommendation strategies.
 */
public interface BookSearchStrategy {
    List<Book> search(List<Book> books, String query);
}

