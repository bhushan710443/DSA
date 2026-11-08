package LowLevelDesign.LLDAdvance.DesignAnInventoryManagementSystem.Service;

import LowLevelDesign.LLDAdvance.DesignAnInventoryManagementSystem.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
