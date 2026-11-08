package LowLevelDesign.LLDAdvance.DesignASubscriptionManagementSystem.Service;

import LowLevelDesign.LLDAdvance.DesignASubscriptionManagementSystem.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
