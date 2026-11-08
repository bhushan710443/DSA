package LowLevelDesign.LLDMust.FoodOrderingSystem.Service;

import LowLevelDesign.LLDMust.FoodOrderingSystem.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
