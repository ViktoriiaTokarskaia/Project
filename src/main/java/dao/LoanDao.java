package dao;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Hibernate4Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanDao {

    public Loan createLoan(Book book, Person person) {
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setPerson(person);
        loan.setLoanStatus(LoanStatus.ACTIVE);
        loan.setCreatedDate(new Date());

        return saveLoan(loan);
    }

    public void returnBook(Loan loan) {
        loan.setLoanStatus(LoanStatus.RETURNED);
        loan.setUpdateDate(new Date());
        updateLoan(loan);

    }

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

    public Loan updateLoan(Loan loan) {
        Transaction transaction = null;
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(loan);
            transaction.commit();

        } catch (Exception e) {
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

    public Loan getLoanById(Long id) {
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
            Loan tempLoan = getLoanById(a.getId());
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
