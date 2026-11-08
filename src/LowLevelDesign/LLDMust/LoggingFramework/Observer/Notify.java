package LowLevelDesign.LLDMust.LoggingFramework.Observer;

import LowLevelDesign.LLDMust.LoggingFramework.Entity.Ticket;

public class Notify  implements ObserverLiabrary {

    @Override
    public void ticketBooked(Ticket ticket) {
        System.out.println("Ticket booked into the theator"+ ticket.getTheatorName());
    }
}
