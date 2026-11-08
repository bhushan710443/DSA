package LowLevelDesign.LLDAdvance.DesignAURLShortener.Service;

import LowLevelDesign.LLDAdvance.DesignAURLShortener.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
