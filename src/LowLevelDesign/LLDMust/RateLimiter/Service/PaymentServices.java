package LowLevelDesign.LLDMust.RateLimiter.Service;

import LowLevelDesign.LLDMust.RateLimiter.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
