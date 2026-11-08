package LowLevelDesign.LLDAdvance.DesignAMeetingRoomBookingSystem.Payment;

import LowLevelDesign.LLDAdvance.DesignAMeetingRoomBookingSystem.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAMeetingRoomBookingSystem.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
