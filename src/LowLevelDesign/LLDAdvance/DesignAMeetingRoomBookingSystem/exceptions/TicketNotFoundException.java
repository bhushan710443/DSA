package LowLevelDesign.LLDAdvance.DesignAMeetingRoomBookingSystem.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
