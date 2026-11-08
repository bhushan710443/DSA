package LowLevelDesign.LLDAdvance.DesignAWorkflowEngine.Service;

import LowLevelDesign.LLDAdvance.DesignAWorkflowEngine.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
