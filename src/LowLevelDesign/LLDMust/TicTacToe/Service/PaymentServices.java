package LowLevelDesign.LLDMust.TicTacToe.Service;

import LowLevelDesign.LLDMust.TicTacToe.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
