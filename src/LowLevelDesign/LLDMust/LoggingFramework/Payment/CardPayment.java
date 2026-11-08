package LowLevelDesign.LLDMust.LoggingFramework.Payment;

import LowLevelDesign.LLDMust.LoggingFramework.Entity.Ticket;
import LowLevelDesign.LLDMust.LoggingFramework.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
