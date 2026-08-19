package LowLevelDesign.Liabrary.Strategy;

import LowLevelDesign.Liabrary.Entity.BookBorrowed;

/**
 * Strategy Pattern: Different fine calculation strategies.
 * e.g., Per-day fine, Fixed fine, No-fine for premium members, etc.
 */
public interface FineCalculationStrategy {

    /**
     * Calculate the fine for a borrowed book.
     * @param record The borrow record
     * @param allowedDays Number of days allowed without fine
     * @return Fine amount (0 if within allowed period)
     */
    double calculateFine(BookBorrowed record, int allowedDays);
}

