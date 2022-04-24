package hibernate.factories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.model.ProductServicesHibernate;


public class SessionFactoryBuilderProductServices {
	private static SessionFactory sessionFactory = null;

	
	public static SessionFactory getSessionFactory() {
		
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ProductServicesHibernate.class).buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
	public static void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
	
}
