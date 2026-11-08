package LowLevelDesign.LLDAdvance.DesignAMultiplayerGame.Observer;

import LowLevelDesign.LLDAdvance.DesignAMultiplayerGame.Entity.Ticket;

public class Notify  implements ObserverLiabrary {

    @Override
    public void ticketBooked(Ticket ticket) {
        System.out.println("Ticket booked into the theator"+ ticket.getTheatorName());
    }
}
