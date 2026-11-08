package LowLevelDesign.LLDMust.BookMyShow.Factory.Payment;

import LowLevelDesign.LLDMust.BookMyShow.Entity.Ticket;
import LowLevelDesign.LLDMust.BookMyShow.Enum.PayMode;
import LowLevelDesign.LLDMust.BookMyShow.Repository.PaymentRepository;
import LowLevelDesign.LLDMust.BookMyShow.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
