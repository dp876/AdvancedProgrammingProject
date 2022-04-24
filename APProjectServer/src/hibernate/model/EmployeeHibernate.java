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

import hibernate.factories.SessionFactoryBuilderEmployee;

@Entity
@Table(name="employee")
public class EmployeeHibernate implements Serializable {

	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(EmployeeHibernate.class.getName()); 
	
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
	
	@Column(name="employee_type")
	private String employeeType;
	
	@Column(name="employee_user_id")
	private int employeeUserId;
	
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
	
	public EmployeeHibernate() {
		this.id = 0;
		this.employeeType = "";
		this.employeeUserId = 0;
		this.firstName = "";
		this.lastName = "";
		this.emailAddress = "";
		this.homeAddress = "";
		this.teleNum = "";
	}
	
	public EmployeeHibernate(int id, String employeeType, int employeeUserId, String firstName, String lastName, String emailAddress, String homeAddress, String teleNum) {
		this.id = id;
		this.employeeType = employeeType;
		this.employeeUserId = employeeUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
		this.teleNum = teleNum;
	}
	
	public EmployeeHibernate(EmployeeHibernate obj) {
		this.id = obj.id;
		this.employeeType = obj.employeeType;
		this.employeeUserId = obj.employeeUserId;
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

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public int getEmployeeUserId() {
		return employeeUserId;
	}

	public void setEmployeeUserId(int employeeUserId) {
		this.employeeUserId = employeeUserId;
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
		return "id: " + id + "\nemployeeType: " + employeeType + "\nemployeeUserId: " + employeeUserId + "\nfirstName: "
				+ firstName + "\nlastName: " + lastName + "\nemailAddress: " + emailAddress + "\nhomeAddress: "
				+ homeAddress + "\nteleNum: " + teleNum + "\n";
	}
	
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("employee created");
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<EmployeeHibernate> readAll() {
		ArrayList<EmployeeHibernate> employeeHibernateList = new ArrayList<>();
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		employeeHibernateList = (ArrayList<EmployeeHibernate>) session.createQuery("FROM EmployeeHibernate").getResultList();
		transaction.commit();
		session.close();
		return employeeHibernateList;
	}
	
	public void update() {
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		EmployeeHibernate obj = (EmployeeHibernate) session.get(EmployeeHibernate.class, this.id);
		obj.setEmployeeType(this.employeeType);
		obj.setEmployeeUserId(this.employeeUserId);
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
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		EmployeeHibernate obj = (EmployeeHibernate) session.get(EmployeeHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
	}
	
}
