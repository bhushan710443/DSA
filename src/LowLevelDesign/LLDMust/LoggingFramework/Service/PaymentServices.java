package LowLevelDesign.LLDMust.LoggingFramework.Service;

import LowLevelDesign.LLDMust.LoggingFramework.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
