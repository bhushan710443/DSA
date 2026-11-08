package LowLevelDesign.LLDMust.ChessGame.Payment;

import LowLevelDesign.LLDMust.ChessGame.Entity.Ticket;
import LowLevelDesign.LLDMust.ChessGame.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
