package LowLevelDesign.Liabrary.Observer;

/**
 * Concrete observer: Logs notifications to console.
 * In production, this could be EmailNotifier, SMSNotifier, PushNotifier, etc.
 *
 * Interview point: "Each notification channel is a separate observer class.
 * We can add/remove them at runtime without touching business logic."
 */
public class NotificationService implements LibraryObserver {

    @Override
    public void onBookAvailable(String bookId, String bookName, String userId) {
        System.out.println("🔔 NOTIFICATION [" + userId + "]: Book '" + bookName +
                "' is now available! Your reservation is confirmed. Please pick it up within 48 hours.");
    }

    @Override
    public void onBookBorrowed(String bookId, String bookName, String userId) {
        System.out.println("📚 NOTIFICATION [" + userId + "]: You have borrowed '" + bookName +
                "'. Return within the due date to avoid fines.");
    }

    @Override
    public void onFineCharged(String userId, String bookName, double fine) {
        System.out.println("⚠️ NOTIFICATION [" + userId + "]: Fine of ₹" + fine +
                " charged for late return of '" + bookName + "'.");
    }

    @Override
    public void onBookRenewed(String bookId, String bookName, String userId, int renewalCount) {
        System.out.println("🔄 NOTIFICATION [" + userId + "]: Book '" + bookName +
                "' renewed successfully (renewal #" + renewalCount + "). Due date extended.");
    }
}
