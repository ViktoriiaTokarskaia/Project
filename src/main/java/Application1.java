import dao.BookDao;
import models.Book;

import java.util.List;

public class Application1 {

    public static void main(String[] args) {

        BookDao bookDao = new BookDao();

        //Add new Book
        Book book = new Book();
        book.setTitle("Linux (Hacking Exposed)");

        Book book1 = new Book();
        book1.setTitle("The Fifth Domain: Defending Our Country, Our Companies, and Ourselves in the Age of Cyber Threats");

        Book book2 = new Book();
        book2.setTitle("Metasploit: The Penetration Tester's Guide");

        Book book3 = new Book();
        book3.setTitle("Countdown to Zero Day: Stuxnet and the Launch of the World's First Digital Weapon");

        Book book4 = new Book();
        book4.setTitle("The Art of Deception: Controlling the Human Element of Security");

        bookDao.saveBook(book);
        bookDao.saveBook(book1);
        bookDao.saveBook(book2);
        bookDao.saveBook(book3);
        bookDao.saveBook(book4);

        //List all authors
        List<Book> books = bookDao.getBooks();
        books.forEach(a -> System.out.println(a.getTitle()));
    }

}
