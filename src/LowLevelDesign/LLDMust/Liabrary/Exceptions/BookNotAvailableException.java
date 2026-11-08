package LowLevelDesign.LLDMust.Liabrary.Exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String message) {
        super(message);
    }
}

