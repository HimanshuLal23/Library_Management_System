package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookItem {
    private UUID bookItemId;
    private boolean isAvailable;
    private int rackId;
    private LocalDate issuedDate;

    public BookItem(int rackId) {
        this.rackId = rackId;
        this.bookItemId = UUID.randomUUID();
        this.isAvailable = true;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public int getRackId() {
        return rackId;
    }

    public UUID getBookItemId() {
        return bookItemId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsNotAvailable() {
        this.isAvailable = false;
    }
}
