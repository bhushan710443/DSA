package LowLevelDesign.LLDMust.FoodOrderingSystem.Payment;

import LowLevelDesign.LLDMust.FoodOrderingSystem.Entity.Ticket;
import LowLevelDesign.LLDMust.FoodOrderingSystem.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
