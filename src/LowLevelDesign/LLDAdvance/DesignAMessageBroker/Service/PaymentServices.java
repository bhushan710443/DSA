package LowLevelDesign.LLDAdvance.DesignAMessageBroker.Service;

import LowLevelDesign.LLDAdvance.DesignAMessageBroker.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
