package LowLevelDesign.LLDMust.Splitwise.Service;

import LowLevelDesign.LLDMust.Splitwise.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
