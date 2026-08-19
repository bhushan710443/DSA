package LowLevelDesign.Liabrary.Strategy;

import LowLevelDesign.Liabrary.Entity.Book;
import LowLevelDesign.Liabrary.Enum.BookType;

import java.util.List;

/**
 * Strategy Pattern: Different book search/recommendation strategies.
 */
public interface BookSearchStrategy {
    List<Book> search(List<Book> books, String query);
}

