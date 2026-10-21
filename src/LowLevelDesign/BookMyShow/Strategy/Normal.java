package LowLevelDesign.BookMyShow.Strategy;

import LowLevelDesign.BookMyShow.Entity.BookingDto;
import LowLevelDesign.BookMyShow.Entity.Seat;
import LowLevelDesign.BookMyShow.Enum.SeatType;

import java.util.List;

public class Normal implements BookingStrategy {

    @Override
    public double findActualPrice(BookingDto bookingDto) {

        List<Seat> seats= bookingDto.getSeats();
        double amount=0.0;

        for(Seat seat : seats){
                amount=amount+300;
        }
        return amount;
    }
}