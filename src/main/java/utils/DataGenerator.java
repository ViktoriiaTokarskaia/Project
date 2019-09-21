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

        BookDao bookDao = new BookDao();
        bookDao.saveBulkBooks(bookList);

    }
}
