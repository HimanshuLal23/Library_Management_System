package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssuedBooks {
    private Member member;
    private List<BookItem> bookItemsWithMember;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public void setMember(Member member) {
        this.member = member;
    }

    public void addBookItemsWithMember(BookItem bookItem) {
        if(bookItemsWithMember == null) {
            bookItemsWithMember = new ArrayList<>();
        }
        this.bookItemsWithMember.add(bookItem);
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        this.dueDate = issueDate.plusDays(5);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
