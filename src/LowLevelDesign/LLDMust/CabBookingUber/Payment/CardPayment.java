package LowLevelDesign.LLDMust.CabBookingUber.Payment;

import LowLevelDesign.LLDMust.CabBookingUber.Entity.Ticket;
import LowLevelDesign.LLDMust.CabBookingUber.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
