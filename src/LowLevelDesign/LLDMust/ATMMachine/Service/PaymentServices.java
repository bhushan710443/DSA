package LowLevelDesign.LLDMust.ATMMachine.Service;

import LowLevelDesign.LLDMust.ATMMachine.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
