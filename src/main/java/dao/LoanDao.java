package dao;

import models.Author;
import models.Loan;
import models.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Hibernate4Util;

import java.util.ArrayList;
import java.util.List;

public class LoanDao {
    public Loan saveLoan(Loan loan) {
        Transaction transaction = null;
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            long id = (Long) session.save(loan);
            // commit transaction
            transaction.commit();

        } catch (Exception e) {
/*            if (transaction != null) {
                transaction.rollback();
            }*/
            e.printStackTrace();
        }

        return loan;
    }

    public List<Loan> getLoans() {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            return session.createQuery("from Loan", Loan.class).list();
        }
    }

    public List<Loan> getLoansByPerson(Person person) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<Loan> query = session.createQuery("From Loan where person= :person", Loan.class);
            query.setParameter("person", person);
            return query.list();
        }
    }

    public Loan getLoanByid(Long id) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<Loan> query = session.createQuery("From Loan where id= :id", Loan.class);
            query.setParameter("id", id);
            List loans = query.list();
            return loans.size() > 0 ? query.list().get(0) : null;
        }
    }


    public List<Loan> saveBulkLoans(List<Loan> loans) {
        List<Loan> result = new ArrayList<>();
        for(Loan a: loans){
            Loan tempLoan = getLoanByid(a.getId());
            if(tempLoan == null) {
                result.add(saveLoan(a));
            }
            else{
                result.add(a);
            }

        }
        return result;
    }

}
