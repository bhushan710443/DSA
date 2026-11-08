package LowLevelDesign.LLDAdvance.DesignACouponAndDiscountEngine.Payment;

import LowLevelDesign.LLDAdvance.DesignACouponAndDiscountEngine.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignACouponAndDiscountEngine.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
