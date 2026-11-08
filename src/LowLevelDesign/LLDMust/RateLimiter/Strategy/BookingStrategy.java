package LowLevelDesign.LLDMust.RateLimiter.Strategy;

public interface BookingStrategy {

    public double findActualPrice(BookingDto bookingDto);
}
