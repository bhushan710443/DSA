package LowLevelDesign.Liabrary.Repository;

import LowLevelDesign.Liabrary.Entity.Reservation;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Manages book reservations (waitlist).
 * Uses a Queue per book to maintain FIFO order.
 */
public class ReservationRepository {

    // bookId -> Queue of reservations (FIFO)
    private final Map<String, Queue<Reservation>> reservationQueue = new ConcurrentHashMap<>();
    private final Map<String, Reservation> allReservations = new ConcurrentHashMap<>();

    public void addReservation(Reservation reservation) {
        allReservations.put(reservation.getReservationId(), reservation);
        reservationQueue
                .computeIfAbsent(reservation.getBookId(), k -> new ConcurrentLinkedQueue<>())
                .add(reservation);
    }

    /**
     * Get next waiting user for a book (FIFO).
     */
    public Reservation getNextReservation(String bookId) {
        Queue<Reservation> queue = reservationQueue.get(bookId);
        if (queue == null) return null;

        // Skip fulfilled reservations
        while (!queue.isEmpty()) {
            Reservation next = queue.peek();
            if (!next.isFulfilled()) {
                return next;
            }
            queue.poll(); // Remove fulfilled
        }
        return null;
    }

    public Reservation pollNextReservation(String bookId) {
        Queue<Reservation> queue = reservationQueue.get(bookId);
        if (queue == null) return null;

        while (!queue.isEmpty()) {
            Reservation next = queue.poll();
            if (!next.isFulfilled()) {
                return next;
            }
        }
        return null;
    }

    public boolean hasReservation(String bookId) {
        return getNextReservation(bookId) != null;
    }

    public boolean isUserAlreadyInQueue(String bookId, String userId) {
        Queue<Reservation> queue = reservationQueue.get(bookId);
        if (queue == null) return false;
        return queue.stream()
                .anyMatch(r -> !r.isFulfilled() && r.getUser().getUserId().equals(userId));
    }

    public int getQueueSize(String bookId) {
        Queue<Reservation> queue = reservationQueue.get(bookId);
        if (queue == null) return 0;
        return (int) queue.stream().filter(r -> !r.isFulfilled()).count();
    }

    public List<Reservation> getUserReservations(String userId) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : allReservations.values()) {
            if (r.getUser().getUserId().equals(userId) && !r.isFulfilled()) {
                result.add(r);
            }
        }
        return result;
    }
}

