package LowLevelDesign.LLDAdvance.DesignAMessageBroker.Payment;

import LowLevelDesign.LLDAdvance.DesignAMessageBroker.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAMessageBroker.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
