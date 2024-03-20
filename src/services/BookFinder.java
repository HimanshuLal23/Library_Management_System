package services;

import Model.Book;
import Model.BookItem;
import repository.BookRepository;
import Exception.LMSException;
import Exception.ExceptionName;

import java.util.Date;
import java.util.List;

public class BookFinder {
    private BookRepository bookRepository;

    public BookFinder() {
        bookRepository = BookRepository.getInstance();
    }
    public Book searchBookByName(String bookName) throws LMSException{
        for(var book:bookRepository.getBooksInLibrary()) {
            if(book.getName().equals(bookName)) {
                List<BookItem> bookItemList = book.getBookItems();
                for(var bookItem:bookItemList) {
                    if(bookItem.isAvailable()) {
                       return book;
                    }
                }
            }
        }
        throw new LMSException(ExceptionName.BookNotAvailableException,"Book not available");
    }

    public Book searchBookByAuthor(String authorName) throws LMSException {
        for(var book:bookRepository.getBooksInLibrary()) {
            if(book.getAuthor().equals(authorName)) {
                List<BookItem> bookItemList = book.getBookItems();
                for(var bookItem:bookItemList) {
                    if(bookItem.isAvailable()) {
                        return book;
                    }
                }
            }
        }
        throw new LMSException(ExceptionName.BookNotAvailableException,"Book not available");
    }

    public Book searchBookBySubject(String subjectName) throws LMSException {
        for(var book:bookRepository.getBooksInLibrary()) {
            if(book.getSubject().equals(subjectName)) {
                List<BookItem> bookItemList = book.getBookItems();
                for(var bookItem:bookItemList) {
                    if(bookItem.isAvailable()) {
                        return book;
                    }
                }
            }
        }
        throw new LMSException(ExceptionName.BookNotAvailableException,"Book not available");
    }

    public Book searchBookByPublicationDate(Date date) throws LMSException {
        for(var book:bookRepository.getBooksInLibrary()) {
            if(book.getPublishedDate().equals(date)) {
                List<BookItem> bookItemList = book.getBookItems();
                for(var bookItem:bookItemList) {
                    if(bookItem.isAvailable()) {
                        return book;
                    }
                }
            }
        }
        throw new LMSException(ExceptionName.BookNotAvailableException,"Book not available");
    }

}
