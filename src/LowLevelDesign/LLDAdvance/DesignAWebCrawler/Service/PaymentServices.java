package LowLevelDesign.LLDAdvance.DesignAWebCrawler.Service;

import LowLevelDesign.LLDAdvance.DesignAWebCrawler.Entity.Ticket;

public interface PaymentServices {

    public void pay(Ticket ticket, Double price, PayMode mode);

}
