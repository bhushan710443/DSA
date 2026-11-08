package LowLevelDesign.LLDMust.VendingMachine.Strategy;

public interface BookingStrategy {

    public double findActualPrice(BookingDto bookingDto);
}
