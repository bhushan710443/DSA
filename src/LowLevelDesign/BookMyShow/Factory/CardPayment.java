package LowLevelDesign.BookMyShow.Factory;

import LowLevelDesign.BookMyShow.Entity.Ticket;
import LowLevelDesign.BookMyShow.Enum.PayMode;
import LowLevelDesign.BookMyShow.Repository.PaymentRepository;
import LowLevelDesign.BookMyShow.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
