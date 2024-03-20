import Model.Book;
import Model.BookItem;
import Model.Member;
import controller.BookController;
import controller.MemberController;
import Exception.LMSException;
import services.BookCheckoutService;
import services.BookFinder;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Member member1 = new Member("Jetha-lal","jetha@gmail.com",987654321);
            MemberController memberController = new MemberController();
            memberController.registerMember(member1);
            Member member2 = new Member("Champak-lal","champak@gmail.com",987654312);
            memberController.registerMember(member2);
            Member member3 = new Member("Babita-ji","babita@gmail.com",987654213);
            memberController.registerMember(member3);
            Member member4 = new Member("Sundar-lal","sundar@gmail.com",987653214);
            memberController.registerMember(member4);

            BookController bookController = new BookController();
            Book book1 = new Book("Jungle Book","Rudyard Kipling", LocalDate.of(1967,10,18),"relationships");
            book1.addBookItem(1);
            book1.addBookItem(2);
            bookController.addBook(book1);

            Book book2 = new Book("Jungle Book2","Rudyard Kipling2",LocalDate.of(1977,10,18),"relationships2");
            book2.addBookItem(1);
            book2.addBookItem(2);
            bookController.addBook(book2);

            BookFinder bookFinder = new BookFinder();
            Book book = bookFinder.searchBookByName("Jungle Book2");
            BookCheckoutService bookCheckoutService = new BookCheckoutService();
            BookItem bookItem1 = bookCheckoutService.checkoutBook(book,member1);
            BookItem bookItem2 = bookCheckoutService.checkoutBook(book,member2);
            BookItem bookItem3 = bookCheckoutService.checkoutBook(book,member3);

            System.out.println(bookCheckoutService.returnBookItem(bookItem1,member1));

        }catch (LMSException lmsException) {
            System.out.println(lmsException.getMessage());
        }


    }
}