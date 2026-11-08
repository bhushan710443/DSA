package LowLevelDesign.LLDMust.VendingMachine.Service;

import LowLevelDesign.LLDMust.VendingMachine.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
