package LowLevelDesign.BookMyShow.Service;

import LowLevelDesign.BookMyShow.Entity.Ticket;
import LowLevelDesign.BookMyShow.Enum.PayMode;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
