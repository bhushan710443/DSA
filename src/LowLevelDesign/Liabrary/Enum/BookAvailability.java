package LowLevelDesign.Liabrary.Enum;

/**
 * Book availability states in the library system.
 * AVAILABLE    → On shelf, can be borrowed
 * WITH_USER    → Currently borrowed by a user
 * RESERVED     → Returned but reserved for next user in queue
 * NOT_AVAILABLE → Under maintenance or lost
 */
public enum BookAvailability {
    AVAILABLE,
    WITH_USER,
    RESERVED,
    NOT_AVAILABLE
}
