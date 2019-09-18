package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Hibernate4Util {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

                // Add table classes/entities below
                configuration.addAnnotatedClass(Author.class);
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Genre.class);
                configuration.addAnnotatedClass(Loan.class);
                configuration.addAnnotatedClass(LoanStatus.class);
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(PersonStatus.class);
                configuration.addAnnotatedClass(PersonType.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}


