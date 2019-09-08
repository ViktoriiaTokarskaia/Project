

import utils.Hibernate4Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Application {
    public static void main(String[] args) throws Exception {
        SessionFactory sessFact = Hibernate4Util.getSessionFactory();
        Session session = sessFact.getCurrentSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        tr.commit();
        sessFact.close();
    }
}
