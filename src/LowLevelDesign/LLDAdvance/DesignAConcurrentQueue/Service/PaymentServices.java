package LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.Service;

import LowLevelDesign.LLDAdvance.DesignAConcurrentQueue.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
