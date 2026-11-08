package LowLevelDesign.LLDAdvance.DesignAURLShortener.Payment;

import LowLevelDesign.LLDAdvance.DesignAURLShortener.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAURLShortener.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
