package LowLevelDesign.BookMyShow.Entity;

import LowLevelDesign.BookMyShow.Enum.PayMode;

import java.util.List;

public class BookingDto {

    private String showId;
    private String theatorID;
    private String ScreenId;
    private List<Seat> seats;
    private PayMode mode;

    public BookingDto(String showId, String theatorID, String screenId, List<Seat> seats, PayMode mode) {
        this.showId = showId;
        this.theatorID = theatorID;
        ScreenId = screenId;
        this.seats = seats;
        this.mode = mode;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getTheatorID() {
        return theatorID;
    }

    public void setTheatorID(String theatorID) {
        this.theatorID = theatorID;
    }

    public String getScreenId() {
        return ScreenId;
    }

    public void setScreenId(String screenId) {
        ScreenId = screenId;
    }

    public PayMode getMode() {
        return mode;
    }

    public void setMode(PayMode mode) {
        this.mode = mode;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
