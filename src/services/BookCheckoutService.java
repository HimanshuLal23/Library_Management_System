package services;

import Model.Book;
import Model.BookItem;
import Exception.LMSException;
import Model.Member;
import repository.BookRepository;
import services.Observers.Observer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static Exception.ExceptionName.BookNotAvailableException;
import static Exception.ExceptionName.MaximumIssuedBookException;

public class BookCheckoutService {
    private BookRepository bookRepository;

    public BookCheckoutService() {
        bookRepository = BookRepository.getInstance();
    }

    public BookItem checkoutBook(Book book, Member member) throws LMSException{
        if(member.getIssuedBooks()==5) {
            throw new LMSException(MaximumIssuedBookException,"You alread have checked out 5 books");
        }
        if(!bookRepository.getBooksInLibrary().contains(book)) {
            throw new LMSException(BookNotAvailableException,"The searched book does not exist");
        }
        List<BookItem> bookItemList = book.getBookItems();
        for(var bookItem:bookItemList) {
            if(bookItem.isAvailable()) {
                bookItem.setIsNotAvailable();
                bookItem.setIssuedDate(LocalDate.now(ZoneId.systemDefault()));
                return bookItem;
            }
        }
        book.addObserver(member);
        return null;
    }

    public String returnBookItem(BookItem returningBookItem, Member member) throws LMSException{
        List<Observer> memberList = new ArrayList<>();
        Book parentBook = null;
        boolean bookItemIsFromLibrary = false;
        for(var book:bookRepository.getBooksInLibrary()) {
            for(var bookItem:book.getBookItems()) {
                if(bookItem.equals(returningBookItem)) {
                    parentBook = book;
                }
            }
        }
        String message = "";
        LocalDate issuedDate = returningBookItem.getIssuedDate();
        int dateDif = LocalDate.now(ZoneId.of("Asia/Kolkata")).getDayOfMonth()-issuedDate.getDayOfMonth();
        if(dateDif>=10) {
            message = "You "+member.getName() + " will be fined for " + dateDif + "days";
        }else {
            message = "Thanks for on-time return";
        }
        returningBookItem.isAvailable();
        returningBookItem.setIssuedDate(null);
        parentBook.notifyObservers(parentBook.getName());
        member.removeBookItem(returningBookItem);
        return message;
    }

    public void renewBook(Book book) throws LMSException{
        List<BookItem> bookItemList = book.getBookItems();
        for(var bookItem:bookItemList) {
            if(!bookItem.isAvailable()) {
                bookItem.isAvailable();
                bookItem.setIssuedDate(LocalDate.now(ZoneId.of("IST")));
                return;
            }
        }
    }
}
