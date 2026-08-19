package LowLevelDesign.Liabrary.Exceptions;

public class InvalidBorrowIdException extends RuntimeException {
    public InvalidBorrowIdException(String message) {
        super(message);
    }
}

