package LowLevelDesign.Liabrary.Strategy;

import LowLevelDesign.Liabrary.Entity.BookBorrowed;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Fine strategy that excludes weekends from overdue calculation.
 * Demonstrates Strategy Pattern: swap this in without changing LibraryService.
 *
 * Interview talking point:
 * "If the library decides weekends shouldn't count as overdue days,
 *  I just plug in a new strategy — zero changes to the service."
 */
public class WeekendExemptFineStrategy implements FineCalculationStrategy {

    private final double finePerDay;

    public WeekendExemptFineStrategy(double finePerDay) {
        this.finePerDay = finePerDay;
    }

    @Override
    public double calculateFine(BookBorrowed record, int allowedDays) {
        LocalDate startDate = record.getBorrowedTime().toLocalDate();
        LocalDate today = LocalDate.now();

        long totalDays = ChronoUnit.DAYS.between(startDate, today);
        if (totalDays <= allowedDays) {
            return 0.0;
        }

        // Count only weekdays after allowed period
        LocalDate overdueStart = startDate.plusDays(allowedDays);
        long weekdayOverdue = 0;

        for (LocalDate date = overdueStart; !date.isAfter(today); date = date.plusDays(1)) {
            DayOfWeek day = date.getDayOfWeek();
            if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
                weekdayOverdue++;
            }
        }

        return weekdayOverdue * finePerDay;
    }
}

