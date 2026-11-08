package LowLevelDesign.LLDAdvance.DesignARideMatchingEngine.Service;

import LowLevelDesign.LLDAdvance.DesignARideMatchingEngine.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
