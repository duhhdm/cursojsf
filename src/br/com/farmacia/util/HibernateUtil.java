package br.com.farmacia.util;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManager sessionFactory = buildSessionFactory();

    private static EntityManager buildSessionFactory() {
        try {
           
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmacias");
    		EntityManager manager = factory.createEntityManager();
        	return manager;
        }
        catch (Throwable ex) {
            System.err.println("Erro na conexão." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getSessionFactory() {
    	return sessionFactory;
    }

}