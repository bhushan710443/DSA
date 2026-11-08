package LowLevelDesign.LLDMust.BookMyShow.Service;

import LowLevelDesign.LLDMust.BookMyShow.Entity.Ticket;
import LowLevelDesign.LLDMust.BookMyShow.Enum.PayMode;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
