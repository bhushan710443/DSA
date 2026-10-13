package LowLevelDesign.BookMyShow.Entity;

import jdk.jshell.Snippet;

import java.util.List;

public class Ticket {

    private String ticketId;
    private String theatorName;

    private String screen;

    private String show;
    private List<Seat> seats;

    private double price;

    private Snippet.Status status;

    public Ticket(String ticketId, String theatorName, String screen, String show, List<Seat> seats, double price, Snippet.Status status) {
        this.ticketId = ticketId;
        this.theatorName = theatorName;
        this.screen = screen;
        this.show = show;
        this.seats = seats;
        this.price = price;
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTheatorName() {
        return theatorName;
    }

    public void setTheatorName(String theatorName) {
        this.theatorName = theatorName;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Snippet.Status getStatus() {
        return status;
    }

    public void setStatus(Snippet.Status status) {
        this.status = status;
    }
}
