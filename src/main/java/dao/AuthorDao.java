package dao;

import models.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.Hibernate4Util;

import java.util.List;

public class AuthorDao {
    public void saveAuthor(Author author) {
        Transaction transaction = null;
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the author object
            session.save(author);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
/*            if (transaction != null) {
                transaction.rollback();
            }*/
            e.printStackTrace();
        }
    }
    public List<Author> getAuthors() {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            return session.createQuery("from Author", Author.class).list();
        }
    }
}
