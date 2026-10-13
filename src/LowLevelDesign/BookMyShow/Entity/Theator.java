package LowLevelDesign.BookMyShow.Entity;

import java.util.List;

public class Theator {
    private String name;
    private Address address ;
    private List<Screen> screens;

    public Theator(String name, Address address, List<Screen> screens) {
        this.name = name;
        this.address = address;
        this.screens = screens;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }
}
