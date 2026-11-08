package LowLevelDesign.LLDMust.ATMMachine.Payment;

import LowLevelDesign.LLDMust.ATMMachine.Entity.Ticket;
import LowLevelDesign.LLDMust.ATMMachine.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
