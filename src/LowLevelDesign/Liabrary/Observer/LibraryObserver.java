package LowLevelDesign.Liabrary.Observer;

/**
 * Observer Pattern: Interface for notification listeners.
 * Any class that wants to be notified about library events implements this.
 *
 * Interview point: "Adding a new notification channel (email, SMS, push)
 * requires just implementing this interface — zero changes to service code."
 */
public interface LibraryObserver {
    void onBookAvailable(String bookId, String bookName, String userId);
    void onBookBorrowed(String bookId, String bookName, String userId);
    void onFineCharged(String userId, String bookName, double fine);
    void onBookRenewed(String bookId, String bookName, String userId, int renewalCount);
}
