package LowLevelDesign.LLDMust.FoodOrderingSystem.Observer;

import LowLevelDesign.LLDMust.FoodOrderingSystem.Entity.Ticket;

public class Notify  implements ObserverLiabrary {

    @Override
    public void ticketBooked(Ticket ticket) {
        System.out.println("Ticket booked into the theator"+ ticket.getTheatorName());
    }
}
