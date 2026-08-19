package LowLevelDesign.Liabrary.Entity;

import LowLevelDesign.Liabrary.Enum.MembershipType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private final String userId;
    private final String name;
    private final String email;
    private final MembershipType membership;
    private final List<BookBorrowed> borrowHistory;

    public User(String userId, String name, String email, MembershipType membership) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.membership = membership;
        this.borrowHistory = new ArrayList<>();
    }

    // Convenience constructor (defaults to BASIC membership)
    public User(String userId, String name, String email) {
        this(userId, name, email, MembershipType.BASIC);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public MembershipType getMembership() {
        return membership;
    }

    /**
     * Returns an unmodifiable view of borrow history.
     * Encapsulation: external code cannot modify internal list.
     */
    public List<BookBorrowed> getBorrowHistory() {
        return Collections.unmodifiableList(borrowHistory);
    }

    public void addBorrowRecord(BookBorrowed record) {
        borrowHistory.add(record);
    }

    public int getActiveBorrowCount() {
        return (int) borrowHistory.stream().filter(b -> !b.isReturned()).count();
    }

    public boolean canBorrowMore() {
        return getActiveBorrowCount() < membership.getMaxBooksAllowed();
    }

    public int getMaxBooksAllowed() {
        return membership.getMaxBooksAllowed();
    }

    public int getFreeDays() {
        return membership.getFreeDays();
    }

    @Override
    public String toString() {
        return "User{id='" + userId + "', name='" + name + "', membership=" + membership +
                ", activeBooks=" + getActiveBorrowCount() + "/" + getMaxBooksAllowed() + "}";
    }
}
