package LowLevelDesign.Liabrary.Entity;

import LowLevelDesign.Liabrary.Enum.BookAvailability;
import LowLevelDesign.Liabrary.Enum.BookType;

public class Book {
    private final String bookId;
    private final String name;
    private final String author;
    private final BookType type;
    private BookAvailability availability;
    private BookAddress address;

    public Book(String bookId, String name, String author, BookType type, BookAddress address) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.type = type;
        this.address = address;
        this.availability = BookAvailability.AVAILABLE;
    }

    public String getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public BookType getType() {
        return type;
    }

    public BookAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(BookAvailability availability) {
        this.availability = availability;
    }

    public BookAddress getAddress() {
        return address;
    }

    public void setAddress(BookAddress address) {
        this.address = address;
    }

    public boolean isAvailable() {
        return availability == BookAvailability.AVAILABLE;
    }

    @Override
    public String toString() {
        return "Book{id='" + bookId + "', name='" + name + "', author='" + author +
                "', type=" + type + ", availability=" + availability + "}";
    }
}
