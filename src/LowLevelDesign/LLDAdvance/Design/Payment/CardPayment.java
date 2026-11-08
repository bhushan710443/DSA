package LowLevelDesign.LLDAdvance.Design.Payment;

import LowLevelDesign.LLDAdvance.Design.Entity.Ticket;
import LowLevelDesign.LLDAdvance.Design.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
