package hibernate.model;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.factories.SessionFactoryBuilderCustomerLogin;

@Entity
@Table(name="customer_login")
public class CustomerLoginHibernate implements Serializable {

	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(CustomerLoginHibernate.class.getName()); 
	
	private static void setupLogger() {
        LogManager.getLogManager().reset();
        
        ConsoleHandler ch = new ConsoleHandler();
        logger.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("Log.log", true);
            logger.addHandler(fh);
        } catch (java.io.IOException e) {            
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
         
    }
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="cus_password")
	private String customerPassword;
	
	public CustomerLoginHibernate() {
		this.id = 0;
		this.customerId = 0;
		this.customerPassword = "";
	}

	public CustomerLoginHibernate(int id, int customerId, String customerPassword) {
		this.id = id;
		this.customerId = customerId;
		this.customerPassword = customerPassword;
	}
	
	public CustomerLoginHibernate(CustomerLoginHibernate obj) {
		this.id = obj.id;
		this.customerId = obj.customerId;
		this.customerPassword = obj.customerPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncustomerId: " + customerId + "\ncustomerPassword: " + customerPassword + "\n";
	}
	
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderCustomerLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("customer credentials created");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CustomerLoginHibernate> readAll() {
		ArrayList<CustomerLoginHibernate> customerLoginList = new ArrayList<>();
		Session session = SessionFactoryBuilderCustomerLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		customerLoginList = (ArrayList<CustomerLoginHibernate>) session.createQuery("FROM CustomerLoginHibernate").getResultList();
		transaction.commit();
		session.close();
		return customerLoginList;
	}
	
	public void update() {
		Session session = SessionFactoryBuilderCustomerLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CustomerLoginHibernate obj = (CustomerLoginHibernate) session.get(CustomerLoginHibernate.class, this.id);
		obj.setCustomerId(this.customerId);
		obj.setCustomerPassword(this.customerPassword);
		session.update(obj);
		transaction.commit();
		session.close();
	}
	
	public void delete() {
		Session session = SessionFactoryBuilderCustomerLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CustomerLoginHibernate obj = (CustomerLoginHibernate) session.get(CustomerLoginHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
	}
}
