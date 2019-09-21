package dao;


import models.Author;
import models.Loan;
import models.LoanStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Hibernate4Util;

import java.util.ArrayList;
import java.util.List;

public class LoanStatusDao {
    public LoanStatus saveLoanStatus(LoanStatus loanStatus) {
        Transaction transaction = null;
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {

            // start a transaction
            transaction = session.beginTransaction();
            long id = (Long) session.save(loanStatus);

            // commit transaction
            transaction.commit();
            loanStatus = getLoanStatusByid(id);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return loanStatus;
    }
    public List<LoanStatus> getLoanStatus() {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            return session.createQuery("from LoanStatus", LoanStatus.class).list();
        }
    }

    public LoanStatus getLoanStatusByName(String name) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<LoanStatus> query = session.createQuery("From LoanStatus where name= :name", LoanStatus.class);
            query.setParameter("name", name);
            List authors = query.list();
            return authors.size() > 0 ? query.list().get(0) : null;
        }
    }

    public LoanStatus getLoanStatusByid(Long id) {
        try (Session session = Hibernate4Util.getSessionFactory().openSession()) {
            Query<LoanStatus> query = session.createQuery("From LoanStatus where id= :id", LoanStatus.class);
            query.setParameter("id", id);
            List loanStatuses = query.list();
            return loanStatuses.size() > 0 ? query.list().get(0) : null;
        }
    }


}
