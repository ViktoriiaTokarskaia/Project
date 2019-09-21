package dao;

import models.Genre;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Hibernate4Util;

import java.util.ArrayList;
import java.util.List;

public class GenreDao {
    public Genre saveGenre(Genre genre) {
        Transaction transaction = null;
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            long id = (Long) session.save(genre);
            // commit transaction
            transaction.commit();
            genre = getGenreById(id);
        } catch (Exception e) {
/*            if (transaction != null) {
                transaction.rollback();
            }*/
            e.printStackTrace();
        }
        return genre;
    }


    public List<Genre> getGenres() {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            return session.createQuery("from Genre", Genre.class).list();
        }
    }

    public Genre getGenreByName(String name) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<Genre> query = session.createQuery("From Genre where name= :name", Genre.class);
            query.setParameter("name", name);
            List genres = query.list();
            return genres.size() > 0 ? query.list().get(0) : null;
        }
    }

    public Genre getGenreById(Long id) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<Genre> query = session.createQuery("From Genre where id= :id", Genre.class);
            query.setParameter("id", id);
            List genres = query.list();
            return genres.size() > 0 ? query.list().get(0) : null;
        }
    }

    public List<Genre> saveBulkGenres(List<Genre> genres) {
        List<Genre> result = new ArrayList<>();
        for(Genre a: genres){
            Genre tempGenre = getGenreByName(a.getName());
            if(tempGenre == null) {
                result.add(saveGenre(a));
            }
            else{
                result.add(a);
            }

        }
        return result;
    }
}
