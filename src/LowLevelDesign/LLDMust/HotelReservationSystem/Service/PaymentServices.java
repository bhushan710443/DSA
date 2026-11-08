package LowLevelDesign.LLDMust.HotelReservationSystem.Service;

import LowLevelDesign.LLDMust.HotelReservationSystem.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
