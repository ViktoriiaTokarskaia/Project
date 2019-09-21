package dao;

import models.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Hibernate4Util;

import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    public Author saveAuthor(Author author) {
        Transaction transaction = null;
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            long id = (Long) session.save(author);
            transaction.commit();
            author = getAuthorById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return author;
    }
    public List<Author> getAllAuthors() {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            return session.createQuery("from Author", Author.class).list();
        }
    }

    public Author getAuthorByName(String name) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<Author> query = session.createQuery("From Author where name= :name", Author.class);
            query.setParameter("name", name);
            List authors = query.list();
            return authors.size() > 0 ? query.list().get(0) : null;
        }
    }

    public Author getAuthorById(Long id) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<Author> query = session.createQuery("From Author where id= :id", Author.class);
            query.setParameter("id", id);
            List authors = query.list();
            return authors.size() > 0 ? query.list().get(0) : null;
        }
    }

    public List<Author> saveBulkAuthors(List<Author> authors) {
        List<Author> result = new ArrayList<>();
        for(Author a: authors){
            Author tempAuthor = getAuthorByName(a.getName());
            if(tempAuthor == null) {
                result.add(saveAuthor(a));
            }
            else{
                result.add(a);
            }
        }
        return result;
    }
}
