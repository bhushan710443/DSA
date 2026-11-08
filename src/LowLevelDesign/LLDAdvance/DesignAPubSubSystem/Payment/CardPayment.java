package LowLevelDesign.LLDAdvance.DesignAPubSubSystem.Payment;

import LowLevelDesign.LLDAdvance.DesignAPubSubSystem.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAPubSubSystem.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
