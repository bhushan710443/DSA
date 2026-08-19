package LowLevelDesign.Liabrary.Entity;

import LowLevelDesign.Liabrary.Enum.BookType;

public class BookAddress {

    private final String section;   // Section in library (e.g., "A", "B", "C")
    private final BookType type;    // Category section
    private final String rack;      // Rack number
    private final int shelfNumber;  // Shelf position

    public BookAddress(String section, BookType type, String rack, int shelfNumber) {
        this.section = section;
        this.type = type;
        this.rack = rack;
        this.shelfNumber = shelfNumber;
    }

    public String getSection() {
        return section;
    }

    public BookType getType() {
        return type;
    }

    public String getRack() {
        return rack;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    @Override
    public String toString() {
        return "Section-" + section + ", Rack-" + rack + ", Shelf-" + shelfNumber;
    }
}
