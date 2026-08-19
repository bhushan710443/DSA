package LowLevelDesign.Liabrary.Entity;

import java.time.LocalDateTime;

/**
 * Represents a reservation when a book is not available.
 * Users are served in FIFO order when the book is returned.
 */
public class Reservation {
    private final String reservationId;
    private final User user;
    private final String bookId;
    private final LocalDateTime reservedAt;
    private boolean fulfilled;

    public Reservation(String reservationId, User user, String bookId) {
        this.reservationId = reservationId;
        this.user = user;
        this.bookId = bookId;
        this.reservedAt = LocalDateTime.now();
        this.fulfilled = false;
    }

    public String getReservationId() {
        return reservationId;
    }

    public User getUser() {
        return user;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void markFulfilled() {
        this.fulfilled = true;
    }

    @Override
    public String toString() {
        return "Reservation{id='" + reservationId + "', user='" + user.getName() +
                "', bookId='" + bookId + "', status=" + (fulfilled ? "FULFILLED" : "WAITING") + "}";
    }
}

