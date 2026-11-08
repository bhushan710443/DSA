package LowLevelDesign.LLDMust.SnakeAndLadder.Payment;

import LowLevelDesign.LLDMust.SnakeAndLadder.Entity.Ticket;
import LowLevelDesign.LLDMust.SnakeAndLadder.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
