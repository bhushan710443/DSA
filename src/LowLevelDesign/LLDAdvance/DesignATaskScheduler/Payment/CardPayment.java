package LowLevelDesign.LLDAdvance.DesignATaskScheduler.Payment;

import LowLevelDesign.LLDAdvance.DesignATaskScheduler.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignATaskScheduler.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
