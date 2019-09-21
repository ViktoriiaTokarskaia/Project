import dao.BookDao;
import models.Author;
import models.Book;
import models.Genre;

import java.util.ArrayList;
import java.util.List;


public class Application {
    public static void main(String[] args) {

        //Since a Book is linked to Author and Genre. Then to create a book needs author and genre first
        BookDao bookDao = new BookDao();

        // example: 2 authors for 1 book
        List<Author> authorList = new ArrayList<>();

        Author author1 = new Author();
        author1.setName("George Kurtz");
        authorList.add(author1);

        Author author2 = new Author();
        author2.setName("Robert Knake");
        authorList.add(author2);

        Author author3 = new Author();
        author3.setName("Devon Kearns");
        authorList.add(author3);

        Author author4 = new Author();
        author4.setName("Kim Zetter");
        authorList.add(author4);

        Author author5 = new Author();
        author5.setName("Kevin D. Mitnick");
        authorList.add(author5);


        // one genre, but can add more genre
        Genre genre = new Genre();
        genre.setName("Cyber Security");
        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre);

        Genre genre1 = new Genre();
        genre1.setName("Drama");
        genreList.add(genre1);

             Genre genre2 = new Genre();
        genre2.setName("Fiction");
        genreList.add(genre2);

        Genre genre3 = new Genre();
        genre3.setName("Crime");
        genreList.add(genre3);

        Genre genre4 = new Genre();
        genre4.setName("Thriller");
        genreList.add(genre4);





        // adding books (will take 5 as an example)

        Book book = new Book();
        book.setTitle("Linux (Hacking Exposed");
        book.setAuthors(authorList);
        book.setGenres(genreList);
        bookDao.saveBook(book);

        Book book1 = new Book();
        book1.setTitle("The Fifth Domain: Defending Our Country, Our Companies, and Ourselves in the Age of Cyber Threats");
        book1.setAuthors(authorList);
        book1.setGenres(genreList);
        bookDao.saveBook(book1);

        Book book3 = new Book();
        book3.setTitle("Metasploit: The Penetration Tester's Guide");
        book3.setAuthors(authorList);
        book3.setGenres(genreList);
        bookDao.saveBook(book3);

        Book book4 = new Book();
        book4.setTitle("Countdown to Zero Day: Stuxnet and the Launch of the World's First Digital Weapon");
        book4.setAuthors(authorList);
        book4.setGenres(genreList);
        bookDao.saveBook(book4);

        Book book5 = new Book();
        book5.setTitle("The Art of Deception: Controlling the Human Element of Security");
        book5.setAuthors(authorList);
        book5.setGenres(genreList);
        bookDao.saveBook(book5);


    }
}
