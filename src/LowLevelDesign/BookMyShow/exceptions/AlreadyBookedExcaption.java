package LowLevelDesign.BookMyShow.exceptions;

public class AlreadyBookedExcaption extends RuntimeException{

    public AlreadyBookedExcaption(String message) {
        super(message);
    }
}
