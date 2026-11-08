package LowLevelDesign.LLDMust.VendingMachine.Observer;

import LowLevelDesign.LLDMust.VendingMachine.Entity.Ticket;

public class Notify  implements ObserverLiabrary {

    @Override
    public void ticketBooked(Ticket ticket) {
        System.out.println("Ticket booked into the theator"+ ticket.getTheatorName());
    }
}
