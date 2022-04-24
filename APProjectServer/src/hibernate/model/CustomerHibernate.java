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

import hibernate.factories.SessionFactoryBuilderCustomer;

@Entity
@Table(name="customer")
public class CustomerHibernate implements Serializable {

	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(CustomerHibernate.class.getName()); 
	
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
	
	@Column(name="customer_user_id")
	private int customerUserId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email_address")
	private String emailAddress;
	
	@Column(name="home_address")
	private String homeAddress;
	
	@Column(name="tele_num")
	private String teleNum;
	
	public CustomerHibernate() {
		this.id = 0;
		this.customerUserId = 0;
		this.firstName = "";
		this.lastName = "";
		this.emailAddress = "";
		this.homeAddress = "";
		this.teleNum = "";
	}
	
	public CustomerHibernate(int id, int customerUserId, String firstName, String lastName, String emailAddress, String homeAddress, String teleNum) {
		this.id = id;
		this.customerUserId = customerUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
		this.teleNum = teleNum;
	}

	public CustomerHibernate(CustomerHibernate obj) {
		this.id = obj.id;
		this.customerUserId = obj.customerUserId;
		this.firstName = obj.firstName;
		this.lastName = obj.lastName;
		this.emailAddress = obj.emailAddress;
		this.homeAddress = obj.homeAddress;
		this.teleNum = obj.teleNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerUserId() {
		return customerUserId;
	}

	public void setCustomerUserId(int customerUserId) {
		this.customerUserId = customerUserId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getTeleNum() {
		return teleNum;
	}

	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncustomerUserId: " + customerUserId + "\nfirstName: " + firstName + "\nlastName: "
				+ lastName + "\nemailAddress: " + emailAddress + "\nhomeAddress: " + homeAddress + "\nteleNum: "
				+ teleNum + "\n";
	}
	
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("customer created");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CustomerHibernate> readAll() {
		ArrayList<CustomerHibernate> customerHibernateList = new ArrayList<>();
		Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		customerHibernateList = (ArrayList<CustomerHibernate>) session.createQuery("FROM CustomerHibernate").getResultList();
		transaction.commit();
		session.close();
		return customerHibernateList;
	}
	
	public void update() {
		Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CustomerHibernate obj = (CustomerHibernate) session.get(CustomerHibernate.class, this.id);
		obj.setCustomerUserId(this.customerUserId);
		obj.setFirstName(this.firstName);
		obj.setLastName(this.lastName);
		obj.setEmailAddress(this.emailAddress);
		obj.setHomeAddress(this.homeAddress);
		obj.setTeleNum(this.teleNum);
		session.update(obj);
		transaction.commit();
		session.close();
	}
	
	public void delete() {
		Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CustomerHibernate obj = (CustomerHibernate) session.get(CustomerHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
	}
}
