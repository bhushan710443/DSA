package LowLevelDesign.Liabrary.Enum;

/**
 * Membership tiers determine:
 * - How many books a user can borrow concurrently
 * - Fine-free period (days)
 * - Maximum renewals allowed per borrow
 */
public enum MembershipType {
    BASIC(3, 14, 1),       // 3 books, 14 days free, 1 renewal
    PREMIUM(5, 30, 3),     // 5 books, 30 days free, 3 renewals
    STUDENT(2, 21, 2);     // 2 books, 21 days free, 2 renewals

    private final int maxBooksAllowed;
    private final int freeDays;
    private final int maxRenewals;

    MembershipType(int maxBooksAllowed, int freeDays, int maxRenewals) {
        this.maxBooksAllowed = maxBooksAllowed;
        this.freeDays = freeDays;
        this.maxRenewals = maxRenewals;
    }

    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }

    public int getFreeDays() {
        return freeDays;
    }

    public int getMaxRenewals() {
        return maxRenewals;
    }
}
