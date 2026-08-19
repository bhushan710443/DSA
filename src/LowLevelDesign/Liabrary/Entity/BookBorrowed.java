package LowLevelDesign.Liabrary.Entity;

import java.time.LocalDateTime;

/**
 * Represents a borrow transaction (like a ticket).
 * Tracks: who borrowed, when, return status, fine, and renewal count.
 */
public class BookBorrowed {

    private final String borrowId;
    private final Book book;
    private final User borrowedBy;
    private final LocalDateTime borrowedTime;
    private LocalDateTime dueDate;
    private LocalDateTime returnedTime;
    private double fineCharged;
    private boolean returned;
    private int renewalCount;

    public BookBorrowed(String borrowId, Book book, User borrowedBy) {
        this.borrowId = borrowId;
        this.book = book;
        this.borrowedBy = borrowedBy;
        this.borrowedTime = LocalDateTime.now();
        this.dueDate = borrowedTime.plusDays(borrowedBy.getFreeDays());
        this.returned = false;
        this.fineCharged = 0.0;
        this.renewalCount = 0;
    }

    public void returnBook(double fine) {
        this.returnedTime = LocalDateTime.now();
        this.fineCharged = fine;
        this.returned = true;
    }

    /**
     * Renew: extends the due date by the user's free days.
     */
    public void renew(int additionalDays) {
        this.dueDate = this.dueDate.plusDays(additionalDays);
        this.renewalCount++;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public Book getBook() {
        return book;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public LocalDateTime getBorrowedTime() {
        return borrowedTime;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getReturnedTime() {
        return returnedTime;
    }

    public double getFineCharged() {
        return fineCharged;
    }

    public boolean isReturned() {
        return returned;
    }

    public int getRenewalCount() {
        return renewalCount;
    }

    public boolean canRenew() {
        return !returned && renewalCount < borrowedBy.getMembership().getMaxRenewals();
    }

    @Override
    public String toString() {
        return "BorrowRecord{id='" + borrowId + "', book='" + book.getName() +
                "', user='" + borrowedBy.getName() + "', borrowed=" + borrowedTime.toLocalDate() +
                ", due=" + dueDate.toLocalDate() +
                ", renewals=" + renewalCount +
                ", returned=" + (returned ? returnedTime.toLocalDate() : "NOT YET") + "}";
    }
}
