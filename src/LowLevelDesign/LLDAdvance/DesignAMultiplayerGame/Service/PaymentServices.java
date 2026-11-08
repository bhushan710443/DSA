package LowLevelDesign.LLDAdvance.DesignAMultiplayerGame.Service;

import LowLevelDesign.LLDAdvance.DesignAMultiplayerGame.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
