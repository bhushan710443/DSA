package LowLevelDesign.LLDMust.Splitwise.Payment;

import LowLevelDesign.LLDMust.Splitwise.Entity.Ticket;
import LowLevelDesign.LLDMust.Splitwise.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
