package LowLevelDesign.LLDAdvance.DesignAWebCrawler.Payment;

import LowLevelDesign.LLDAdvance.DesignAWebCrawler.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAWebCrawler.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
