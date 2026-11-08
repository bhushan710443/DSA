package LowLevelDesign.LLDMust.TicTacToe.Payment;

import LowLevelDesign.LLDMust.TicTacToe.Entity.Ticket;
import LowLevelDesign.LLDMust.TicTacToe.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
