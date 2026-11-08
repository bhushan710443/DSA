package LowLevelDesign.LLDAdvance.DesignAMultiplayerGame.Payment;

import LowLevelDesign.LLDAdvance.DesignAMultiplayerGame.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAMultiplayerGame.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
