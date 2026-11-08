package LowLevelDesign.LLDAdvance.DesignAnAPIAuthenticationAndAuthorizationModule.Payment;

import LowLevelDesign.LLDAdvance.DesignAnAPIAuthenticationAndAuthorizationModule.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAnAPIAuthenticationAndAuthorizationModule.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
