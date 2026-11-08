package LowLevelDesign.LLDAdvance.DesignARideMatchingEngine.Payment;

import LowLevelDesign.LLDAdvance.DesignARideMatchingEngine.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignARideMatchingEngine.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
