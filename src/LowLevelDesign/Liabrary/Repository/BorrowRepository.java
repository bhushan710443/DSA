package LowLevelDesign.Liabrary.Repository;

import LowLevelDesign.Liabrary.Entity.BookBorrowed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BorrowRepository {

    private final Map<String, BookBorrowed> borrowRecords = new ConcurrentHashMap<>();

    public void save(BookBorrowed record) {
        borrowRecords.put(record.getBorrowId(), record);
    }

    public BookBorrowed getById(String borrowId) {
        return borrowRecords.get(borrowId);
    }

    public List<BookBorrowed> getActiveRecordsByUser(String userId) {
        return borrowRecords.values().stream()
                .filter(r -> !r.isReturned() && r.getBorrowedBy().getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public BookBorrowed getActiveRecordByBookId(String bookId) {
        return borrowRecords.values().stream()
                .filter(r -> !r.isReturned() && r.getBook().getBookId().equals(bookId))
                .findFirst()
                .orElse(null);
    }

    public List<BookBorrowed> getAllRecords() {
        return new ArrayList<>(borrowRecords.values());
    }

    public List<BookBorrowed> getReturnedRecords() {
        return borrowRecords.values().stream()
                .filter(BookBorrowed::isReturned)
                .collect(Collectors.toList());
    }
}

