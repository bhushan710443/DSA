package LowLevelDesign.LLDAdvance.DesignAnInventoryManagementSystem.Payment;

import LowLevelDesign.LLDAdvance.DesignAnInventoryManagementSystem.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAnInventoryManagementSystem.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
