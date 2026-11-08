package LowLevelDesign.LLDMust.PaymentGateway.Service;

import LowLevelDesign.LLDMust.PaymentGateway.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
