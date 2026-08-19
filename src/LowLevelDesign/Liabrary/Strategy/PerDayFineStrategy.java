package LowLevelDesign.Liabrary.Strategy;

import LowLevelDesign.Liabrary.Entity.BookBorrowed;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Per-day fine strategy:
 * - First 14 days: FREE
 * - After 14 days: ₹5 per extra day
 */
public class PerDayFineStrategy implements FineCalculationStrategy {

    private final double finePerDay;

    public PerDayFineStrategy() {
        this.finePerDay = 5.0; // Default ₹5/day
    }

    public PerDayFineStrategy(double finePerDay) {
        this.finePerDay = finePerDay;
    }

    @Override
    public double calculateFine(BookBorrowed record, int allowedDays) {
        long daysBorrowed = Duration.between(record.getBorrowedTime(), LocalDateTime.now()).toDays();

        if (daysBorrowed <= allowedDays) {
            return 0.0;
        }

        long overdueDays = daysBorrowed - allowedDays;
        return overdueDays * finePerDay;
    }
}

