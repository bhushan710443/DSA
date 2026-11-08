package LowLevelDesign.LLDMust.PaymentGateway.Payment;

import LowLevelDesign.LLDMust.PaymentGateway.Entity.Ticket;
import LowLevelDesign.LLDMust.PaymentGateway.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
