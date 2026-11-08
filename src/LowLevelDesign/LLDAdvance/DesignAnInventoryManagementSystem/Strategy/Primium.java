package LowLevelDesign.LLDAdvance.DesignAnInventoryManagementSystem.Strategy;

import LowLevelDesign.LLDAdvance.DesignAnInventoryManagementSystem.Enum.SeatType;

import java.util.List;

public class Primium implements BookingStrategy {

    @Override
    public double findActualPrice(BookingDto bookingDto) {

        List<Seat> seats= bookingDto.getSeats();
        double amount=0.0;

        for(Seat seat : seats){
            if(seat.getType()== SeatType.PRIMIUM) {
                amount = amount + 500;
            }
        }
        return amount;
    }
}