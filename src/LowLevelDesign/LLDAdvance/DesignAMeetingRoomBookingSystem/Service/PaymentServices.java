package LowLevelDesign.LLDAdvance.DesignAMeetingRoomBookingSystem.Service;

import LowLevelDesign.LLDAdvance.DesignAMeetingRoomBookingSystem.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
