package LowLevelDesign.LLDAdvance.DesignARideMatchingEngine.Strategy;

import LowLevelDesign.LLDAdvance.DesignARideMatchingEngine.Enum.SeatType;

import java.util.List;

public class Gold implements BookingStrategy {

    @Override
    public double findActualPrice(BookingDto bookingDto) {

        List<Seat> seats = bookingDto.getSeats();
        double amount = 0.0;

        for (Seat seat : seats) {
            if (seat.getType() == SeatType.GOLD) {
                amount = amount + 400;
            }
        }
        return amount;
    }
}