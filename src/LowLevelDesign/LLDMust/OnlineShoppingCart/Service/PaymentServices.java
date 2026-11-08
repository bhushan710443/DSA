package LowLevelDesign.LLDMust.OnlineShoppingCart.Service;

import LowLevelDesign.LLDMust.OnlineShoppingCart.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
