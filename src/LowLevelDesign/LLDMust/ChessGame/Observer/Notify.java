package LowLevelDesign.LLDMust.ChessGame.Observer;

import LowLevelDesign.LLDMust.ChessGame.Entity.Ticket;

public class Notify  implements ObserverLiabrary {

    @Override
    public void ticketBooked(Ticket ticket) {
        System.out.println("Ticket booked into the theator"+ ticket.getTheatorName());
    }
}
