package LowLevelDesign.LLDMust.CarRentalSystem.Strategy;

public interface BookingStrategy {

    public double findActualPrice(BookingDto bookingDto);
}
