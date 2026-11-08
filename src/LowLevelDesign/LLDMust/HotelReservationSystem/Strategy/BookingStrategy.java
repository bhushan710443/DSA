package LowLevelDesign.LLDMust.HotelReservationSystem.Strategy;

public interface BookingStrategy {

    public double findActualPrice(BookingDto bookingDto);
}
