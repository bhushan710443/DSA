package LowLevelDesign.BookMyShow.Strategy;

import LowLevelDesign.BookMyShow.Entity.BookingDto;
import LowLevelDesign.BookMyShow.Entity.Seat;
import LowLevelDesign.BookMyShow.Enum.SeatType;

import java.util.List;

public class BookingStrategyClass  implements BookingStrategy {

    @Override
    public double findActualPrice(BookingDto bookingDto) {

        List<Seat> seats= bookingDto.getSeats();
        double amount=0.0;

        for(Seat seat : seats){
            if(seat.getType()==SeatType.PRIMIUM){
                 amount= amount + 500;
            }else if (seat.getType()==SeatType.GOLD){
                amount= amount + 400;
            }else {
                amount=amount+300;
            }
        }
        return amount;
    }
}