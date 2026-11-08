package LowLevelDesign.LLDAdvance.DesignASubscriptionManagementSystem.Payment;

import LowLevelDesign.LLDAdvance.DesignASubscriptionManagementSystem.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignASubscriptionManagementSystem.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
