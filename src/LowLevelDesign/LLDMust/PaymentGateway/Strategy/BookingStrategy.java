package LowLevelDesign.LLDMust.PaymentGateway.Strategy;

public interface BookingStrategy {

    public double findActualPrice(BookingDto bookingDto);
}
