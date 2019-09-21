package dao;

import models.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Hibernate4Util;

import java.util.List;

public class BookDao {
    public void saveBook(Book book) {
        if(getBookByTitle(book.getTitle()) == null){
            AuthorDao authorDao = new AuthorDao();
            book.setAuthors(authorDao.saveBulkAuthors(book.getAuthors()));

            GenreDao genreDao = new GenreDao();
            book.setGenres(genreDao.saveBulkGenres(book.getGenres()));

            Transaction transaction = null;
            try (Session session = Hibernate4Util.getSessionFactory().getCurrentSession()) {
                transaction = session.beginTransaction();
                session.save(book);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public List<Book> getBooks() {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }

    public Book getBookByTitle(String title) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery("From Book where title= :title", Book.class);
            query.setParameter("title", title);
            List books = query.list();
            return books.size() > 0 ? query.list().get(0) : null;
        }
    }

    public void saveBulkBooks(List<Book> books) {
        for(Book a: books){
            saveBook(a);
        }
    }
}
