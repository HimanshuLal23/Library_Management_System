package repository;

import Model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static List<Book> booksInLibrary;
    private BookRepository() {
        booksInLibrary = new ArrayList<>();
    }
    private static class Holder {
        private static BookRepository INSTANCE = new BookRepository();
    }

    public static BookRepository getInstance() {
        return Holder.INSTANCE;
    }

    public void addBook(Book book) {
        booksInLibrary.add(book);
    }

    public List<Book> getBooksInLibrary() {
        return booksInLibrary;
    }
}
