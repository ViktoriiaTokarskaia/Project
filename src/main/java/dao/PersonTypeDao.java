package dao;

import models.PersonType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Hibernate4Util;

import java.util.ArrayList;
import java.util.List;

public class PersonTypeDao {
        public PersonType savePersonType(PersonType personType) {
            Transaction transaction = null;
            try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
                // start a transaction
                transaction = session.beginTransaction();
                long id = (Long) session.save(personType);
                // commit transaction
                transaction.commit();
                personType = getPersonTypeById(id);
            } catch (Exception e) {
/*            if (transaction != null) {
                transaction.rollback();
            }*/
                e.printStackTrace();
            }

            return personType;
        }
        public List<PersonType> getPersonType() {
            try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
                return session.createQuery("from PersonType", PersonType.class).list();
            }
        }

        public PersonType getPersonTypeByType(String type) {
            try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
                Query<PersonType> query = session.createQuery("From PersonType where name= :name", PersonType.class);
                query.setParameter("type", type);
                List personTypes = query.list();
                return personTypes.size() > 0 ? query.list().get(0) : null;
            }
        }

        public PersonType getPersonTypeById(Long id) {
            try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
                Query<PersonType> query = session.createQuery("From PersonType where id= :id", PersonType.class);
                query.setParameter("id", id);
                List personTypes = query.list();
                return personTypes.size() > 0 ? query.list().get(0) : null;
            }
        }

        public List<PersonType> saveBulkPersonTypes(List<PersonType> personTypes) {
            List<PersonType> result = new ArrayList<>();
            for(PersonType a: personTypes){
                PersonType tempPersonType = getPersonTypeByType(a.getType());
                if(tempPersonType == null) {
                    result.add(savePersonType(a));
                }
                else{
                    result.add(a);
                }

            }
            return result;
        }
    }

