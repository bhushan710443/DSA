package LowLevelDesign.LLDMust.NotificationSystem.Payment;

import LowLevelDesign.LLDMust.NotificationSystem.Entity.Ticket;
import LowLevelDesign.LLDMust.NotificationSystem.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
