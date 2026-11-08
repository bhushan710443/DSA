package LowLevelDesign.LLDMust.NotificationSystem.Service;

import LowLevelDesign.LLDMust.NotificationSystem.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
