package LowLevelDesign.LLDAdvance.DesignATaskScheduler.Service;

import LowLevelDesign.LLDAdvance.DesignATaskScheduler.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
