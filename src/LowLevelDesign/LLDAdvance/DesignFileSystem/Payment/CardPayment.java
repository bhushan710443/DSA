package LowLevelDesign.LLDAdvance.DesignFileSystem.Payment;

import LowLevelDesign.LLDAdvance.DesignFileSystem.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignFileSystem.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
