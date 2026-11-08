package LowLevelDesign.LLDAdvance.DesignACouponAndDiscountEngine.Service;

import LowLevelDesign.LLDAdvance.DesignACouponAndDiscountEngine.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
