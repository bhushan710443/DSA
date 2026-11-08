package LowLevelDesign.LLDMust.RateLimiter.Payment;

import LowLevelDesign.LLDMust.RateLimiter.Entity.Ticket;
import LowLevelDesign.LLDMust.RateLimiter.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
