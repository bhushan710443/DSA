package LowLevelDesign.LLDAdvance.DesignAWorkflowEngine.Payment;

import LowLevelDesign.LLDAdvance.DesignAWorkflowEngine.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAWorkflowEngine.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
