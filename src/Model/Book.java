package Model;

import services.Observers.Observer;
import services.Subject.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Book implements Subject{
    private String name;
    private String author;
    private LocalDate publishedDate;

    private String subject;
    private List<BookItem> bookItems;

    private List<Observer> memberList;

    public Book(String name, String author, LocalDate publishedDate,String subject) {
        this.name = name;
        this.author = author;
        this.publishedDate = publishedDate;
        bookItems = new ArrayList<>();
        this.subject = subject;
        memberList = new ArrayList<>();
    }

    public List<Observer> getMemberList() {
        return memberList;
    }

    public String getSubject() {
        return subject;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void addBookItem(int rackId) {
        bookItems.add(new BookItem(rackId));
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    @Override
    public void addObserver(Observer observer) {
        memberList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        memberList.remove(observer);
    }

    @Override
    public void notifyObservers(Object object) {
        String message = "";
        if(object instanceof String) {
            message = "your book: "+ object +" is available now";
        }else if(object instanceof BookItem){
            message = "your book-item with this id:"+ ((BookItem) object).getBookItemId()+" has passed it's due date of " + ((BookItem) object).getIssuedDate();
        }
        for(var member:memberList) {
            member.update(message);
        }
    }
}
