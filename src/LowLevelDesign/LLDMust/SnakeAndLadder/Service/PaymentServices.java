package LowLevelDesign.LLDMust.SnakeAndLadder.Service;

import LowLevelDesign.LLDMust.SnakeAndLadder.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
