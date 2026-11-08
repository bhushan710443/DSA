package LowLevelDesign.LLDMust.BookMyShow.Strategy;

import LowLevelDesign.LLDMust.BookMyShow.Entity.BookingDto;

public interface BookingStrategy {

    public double findActualPrice(BookingDto bookingDto);
}
