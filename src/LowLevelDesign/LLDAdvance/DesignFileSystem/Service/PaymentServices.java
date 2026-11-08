package LowLevelDesign.LLDAdvance.DesignFileSystem.Service;

import LowLevelDesign.LLDAdvance.DesignFileSystem.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
