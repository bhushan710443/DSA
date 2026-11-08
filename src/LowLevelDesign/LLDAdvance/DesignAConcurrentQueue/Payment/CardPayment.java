package LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.Payment;

import LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
