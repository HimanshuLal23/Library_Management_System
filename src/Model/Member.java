package Model;

import services.Observers.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Member implements Observer {
    private String name;
    private UUID id;
    private String email;
    private int phone;
    private List<BookItem> bookItemList;

    public Member(String name, String email, int phone) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
        bookItemList = new ArrayList<>();
    }

    public int getIssuedBooks() {
        return bookItemList.size();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }
    public void removeBookItem(BookItem bookItem) {
        this.bookItemList.remove(bookItem);
    }
    public void update(String message) {
        System.out.println("Hey "+this.name+" "+message);
    }
}
