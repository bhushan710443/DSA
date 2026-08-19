package LowLevelDesign.Liabrary.Exceptions;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException(String message) {
        super(message);
    }
}

