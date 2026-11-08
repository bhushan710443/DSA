package LowLevelDesign.LLDMust.CabBookingUber.Service;

import LowLevelDesign.LLDMust.CabBookingUber.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
