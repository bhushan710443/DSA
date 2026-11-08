package LowLevelDesign.LLDMust.HotelReservationSystem.Payment;

import LowLevelDesign.LLDMust.HotelReservationSystem.Entity.Ticket;
import LowLevelDesign.LLDMust.HotelReservationSystem.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
