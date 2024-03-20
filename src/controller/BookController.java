package controller;

import Exception.LMSException;
import Exception.ExceptionName;
import Model.Book;
import repository.BookRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class BookController {
    private BookRepository bookRepository;

    public BookController() {
        bookRepository = BookRepository.getInstance();
    }
    public void addBookItem(String bookName, String authorName, String subject, Date publishedDate) throws LMSException {
        for(var currentBook:bookRepository.getBooksInLibrary()) {
            if(currentBook.getName().equals(bookName) && currentBook.getAuthor().equals(authorName) && currentBook.getSubject().equals(subject) && currentBook.getPublishedDate().equals(publishedDate)) {
                currentBook.addBookItem(1);
            }
        }
        throw new LMSException(ExceptionName.BookNotAvailableException,"Book not available");
    }

    public void removeBookItem(String bookName, String authorName, String subject, Date publishedDate, UUID bookItemId) throws LMSException {
        for(var currentBook:bookRepository.getBooksInLibrary()) {
            if(currentBook.getName().equals(bookName) && currentBook.getAuthor().equals(authorName) && currentBook.getSubject().equals(subject) && currentBook.getPublishedDate().equals(publishedDate)) {
                if(currentBook.getBookItems().contains(bookItemId)) {
                    currentBook.getBookItems().remove(bookItemId);
                }
            }
        }
        throw new LMSException(ExceptionName.WrongBookItemException,"Book item not available");
    }

    public void addBook(String bookName, String authorName, String subject, LocalDate publishedDate) throws LMSException {
        for(var currentBook:bookRepository.getBooksInLibrary()) {
            if(currentBook.getName().equals(bookName) && currentBook.getAuthor().equals(authorName) && currentBook.getSubject().equals(subject) && currentBook.getPublishedDate().equals(publishedDate)) {
                throw new LMSException(ExceptionName.BookDuplicationException,"Book already exists, please add a book item");
            }
        }
        Book book = new Book(bookName,authorName,publishedDate,subject);
        bookRepository.addBook(book);
    }

    public void addBook(Book book) throws LMSException {
        for(var currentBook:bookRepository.getBooksInLibrary()) {
            if(currentBook.getName().equals(book.getName()) && currentBook.getAuthor().equals(book.getAuthor()) && currentBook.getSubject().equals(book.getSubject()) && currentBook.getPublishedDate().equals(book.getPublishedDate())) {
                throw new LMSException(ExceptionName.BookDuplicationException,"Book already exists, please add a book item");
            }
        }
        bookRepository.addBook(book);
    }
}
