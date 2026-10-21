package LowLevelDesign.BookMyShow.Strategy;

import LowLevelDesign.BookMyShow.Entity.BookingDto;

public interface BookingStrategy {

    public double findActualPrice(BookingDto bookingDto);
}
