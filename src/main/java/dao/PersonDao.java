package dao;

import models.Author;
import models.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Hibernate4Util;

import java.util.ArrayList;
import java.util.List;

public class PersonDao {

        public Person savePerson (Person person) {
            Transaction transaction = null;
            try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
                // start a transaction
                transaction = session.beginTransaction();
                long id = (Long) session.save(person);
                // commit transaction
                transaction.commit();
                person = getpersonById(id);
            } catch (Exception e) {
/*            if (transaction != null) {
                transaction.rollback();
            }*/
                e.printStackTrace();
            }

            return person;
        }
        public List<Person> getPersons() {
            try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
                return session.createQuery("from Person", Person.class).list();
            }
        }

        public Person getpersonById(Long id) {
            try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
                Query<Person> query = session.createQuery("From Person where name= :id", Person.class);
                query.setParameter("id", id);
                List authors = query.list();
                return authors.size() > 0 ? query.list().get(0) : null;
            }
        }

        public Person getPersonByEmail(String email) {
                try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
                    Query<Person> query = session.createQuery("From Person where email= :email", Person.class);
                    query.setParameter("email", email);
                    List persons = query.list();
                    return persons.size() > 0 ? query.list().get(0) : null;
                }

        }

        public Person getPersonByEmailAndPassword (String email, String password) {
            try(Session session = Hibernate4Util.getSessionFactory().openSession()){
                Query<Person> query = session.createQuery("From Person where email = :email AND password= :password", Person.class);
                query.setParameter("email","email");
                query.setParameter("password","password");
                List persons = query.list();
                return persons.size() > 0? query.list().get(0) :null;
            }
        }




        public List<Person> saveBulkPeron(List<Author> authors, Person[] persons) {
            List<Person> result = new ArrayList<>();
            for(Person a: persons){
                Person tempPerson = getpersonById(a.getId());
                if(tempPerson == null) {
                    result.add(savePerson(a));
                }
                else{
                    result.add(a);
                }

            }
            return result;
        }
    }






