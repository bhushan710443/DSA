package LowLevelDesign.LLDAdvance.DesignAnInMemoryKeyValueStore.Service;

import LowLevelDesign.LLDAdvance.DesignAnInMemoryKeyValueStore.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
