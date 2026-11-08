package LowLevelDesign.LLDAdvance.DesignAnInMemoryKeyValueStore.Payment;

import LowLevelDesign.LLDAdvance.DesignAnInMemoryKeyValueStore.Entity.Ticket;
import LowLevelDesign.LLDAdvance.DesignAnInMemoryKeyValueStore.Service.PaymentServices;

public class CardPayment implements PaymentServices {

    PaymentRepository paymentRepository;

    @Override
    public void pay(Ticket ticket, Double amount, PayMode mode) {
        paymentRepository.payForTicket(ticket, amount);
    }
}
