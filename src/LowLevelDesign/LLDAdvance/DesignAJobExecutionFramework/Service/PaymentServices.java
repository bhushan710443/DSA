package LowLevelDesign.LLDAdvance.DesignAJobExecutionFramework.Service;

import LowLevelDesign.LLDAdvance.DesignAJobExecutionFramework.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
