package LowLevelDesign.LLDMust.CarRentalSystem.Service;

import LowLevelDesign.LLDMust.CarRentalSystem.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
