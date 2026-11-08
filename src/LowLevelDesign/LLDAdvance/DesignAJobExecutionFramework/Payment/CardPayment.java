package LowLevelDesign.LLDAdvance.DesignAJobExecutionFramework.Payment;

import LowLevelDesign.LLDAdvance.DesignAJobExecutionFramework.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAJobExecutionFramework.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
