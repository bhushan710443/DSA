package LowLevelDesign.LLDAdvance.DesignAPubSubSystem.Service;

import LowLevelDesign.LLDAdvance.DesignAPubSubSystem.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
