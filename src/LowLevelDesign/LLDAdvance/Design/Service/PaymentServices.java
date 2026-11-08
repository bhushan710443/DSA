package LowLevelDesign.LLDAdvance.Design.Service;

import LowLevelDesign.LLDAdvance.Design.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
