package LowLevelDesign.Lift.Exceptions;

public class LiftNotFoundException extends RuntimeException {
    public LiftNotFoundException(String message) {
        super(message);
    }
}

