package LowLevelDesign.LLDAdvance.DesignAnAPIAuthenticationAndAuthorizationModule.Service;

import LowLevelDesign.LLDAdvance.DesignAnAPIAuthenticationAndAuthorizationModule.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
