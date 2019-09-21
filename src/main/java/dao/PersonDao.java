package dao;

import models.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Hibernate4Util;
import java.util.List;

public class PersonDao {

    public Person savePerson (Person person) {
        Transaction transaction = null;
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            long id = (Long) session.save(person);
            transaction.commit();
            person = getPersonById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    public List<Person> getPersons() {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            return session.createQuery("from Person", Person.class).list();
        }
    }

    public Person getPersonById(Long id) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<Person> query = session.createQuery("From Person where id= :id", Person.class);
            query.setParameter("id", id);
            List authors = query.list();
            return authors.size() > 0 ? query.list().get(0) : null;
        }
    }

    public Person getPersonByEmailAndPassword (String email, String password) {
        try(Session session = Hibernate4Util.getSessionFactory().openSession()){
            Query<Person> query = session.createQuery("From Person where email = :email AND password= :password", Person.class);
            query.setParameter("email",email);
            query.setParameter("password",password);
            List persons = query.list();
            return persons.size() > 0? query.list().get(0) :null;
        }
    }
}






