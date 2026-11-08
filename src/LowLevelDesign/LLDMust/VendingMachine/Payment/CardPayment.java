package LowLevelDesign.LLDMust.VendingMachine.Payment;

import LowLevelDesign.LLDMust.VendingMachine.Entity.Ticket;
import LowLevelDesign.LLDMust.VendingMachine.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
