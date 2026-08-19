package LowLevelDesign.Liabrary.Exceptions;

public class RenewalLimitExceededException extends RuntimeException {
    public RenewalLimitExceededException(String message) {
        super(message);
    }
}

