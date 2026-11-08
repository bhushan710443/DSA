package LowLevelDesign.LLDMust.ChessGame.Service;

import LowLevelDesign.LLDMust.ChessGame.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
