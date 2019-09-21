package utils;

import dao.BookDao;
import models.Author;
import models.Book;
import models.Genre;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public void generateBooks() {
        System.out.println("Generating books");

        List<Author> authorList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();

        Book book = new Book();
        Author author1 = new Author();
        author1.setName("George Kurtz");
        Author author2 = new Author();
        author2.setName("Robert Knake");
        authorList.add(author1);
        authorList.add(author2);
        Genre genre = new Genre();
        genre.setName("Technology");
        genreList.add(genre);
        book.setTitle("Linux (Hacking Exposed)");
        book.setAuthors(authorList);
        book.setGenres(genreList);
        bookList.add(book);

        Book book1 = new Book();
        Author author3 = new Author();
        author3.setName("Devon Kearns");
        authorList = new ArrayList<>();
        authorList.add(author3);
        Genre genre3 = new Genre();
        genre3.setName("Crime");
        Genre genre4 = new Genre();
        genre4.setName("Thriller");
        genreList = new ArrayList<>();
        genreList.add(genre3);
        genreList.add(genre4);
        book1.setTitle("The Fifth Domain: Defending Our Country, Our Companies, and Ourselves in the Age of Cyber Threats");
        book1.setAuthors(authorList);
        book1.setGenres(genreList);
        bookList.add(book1);

        Book book2 = new Book();
        Author author4 = new Author();
        author4.setName("K.A Stroud");
        Author author5 = new Author();
        author5.setName("H.K Dass");
        authorList.add(author4);
        authorList.add(author5);
        Genre genre5 = new Genre();
        genre5.setName("Engineeing");
        genreList.add(genre5);
        book.setTitle("Advance Engineering Mathematics");
        book.setAuthors(authorList);
        book.setGenres(genreList);
        bookList.add(book2);

        Book book3 = new Book();
        Author author6 = new Author();
        author6.setName("Chinua Achebe");
        authorList.add(author6);
        Genre genre6 = new Genre();
        genre6.setName("Literature");
        genreList.add(genre6);
        book.setTitle("Thing Fall Apart");
        book.setAuthors(authorList);
        book.setGenres(genreList);
        bookList.add(book3);

        Book book4 = new Book();
        Author author7 = new Author();
        author7.setName("Nora Ephron");
        authorList.add(author7);
        //Genre genre6 = new Genre();
        genre6.setName("Literature");
        genreList.add(genre6);
        book.setTitle("I feel bad about my neck");
        book.setAuthors(authorList);
        book.setGenres(genreList);
        bookList.add(book4);

        Book book5 = new Book();
        Author author8 = new Author();
        author8.setName("J.K Rowling");
        authorList.add(author7);
        Genre genre7 = new Genre();
        genre7.setName("Fantasy");
        genreList.add(genre7);
        book.setTitle("Harry Potter and the goblet of fire");
        book.setAuthors(authorList);
        book.setGenres(genreList);
        bookList.add(book5);

        Book book6 = new Book();
        Author author9 = new Author();
        author9.setName("Olga Tokarczuk");
        authorList.add(author9);
        Genre genre8 = new Genre();
        genre6.setName("Eco-Triller");
        genreList.add(genre8);
        book.setTitle("Drive your plow over the bones of the dead");
        book.setAuthors(authorList);
        book.setGenres(genreList);
        bookList.add(book6);

        Book book7 = new Book();
        Author author10 = new Author();
        author10.setName("Dr Amanda Brown");
        authorList.add(author10);
        Genre genre9 = new Genre();
        genre9.setName("True Crime");
        genreList.add(genre9);
        book.setTitle("Prison Doctor");
        book.setAuthors(authorList);
        book.setGenres(genreList);
        bookList.add(book7);

        Book book8 = new Book();
        Author author11 = new Author();
        author11.setName("Olga Tokarczuk");
        authorList.add(author9);
        Genre genre10 = new Genre();
        genre10.setName("War & Espionage");
        genreList.add(genre9);
        book.setTitle("This is going to hurt");
        book.setAuthors(authorList);
        book.setGenres(genreList);
        bookList.add(book8);






        BookDao bookDao = new BookDao();
        bookDao.saveBulkBooks(bookList);

    }
}
