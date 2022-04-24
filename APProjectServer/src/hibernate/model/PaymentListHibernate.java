package hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.factories.SessionFactoryBuilderPaymentList;

@Entity
@Table(name="payments_list")
public class PaymentListHibernate implements Serializable {
	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(PaymentListHibernate.class.getName()); 
	
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
	
	@Column(name="amount_due")
	private int amountDue;
	
	@Column(name="due_date")
	private String dueDate;
	
	@Column(name="payment_status")
	private String paymentStatus;
	
	
	public PaymentListHibernate() {
		this.id = 0;
		this.customerId = 0;
		this.amountDue = 0;
		this.dueDate = "";
		this.paymentStatus = "";
	}
	
	public PaymentListHibernate(int id, int customer_id, int amount_due, String due_date, String payment_status /*, ArrayList<PaymentHistory> paymentHistory*/) {
		this.id = id;
		this.customerId = customer_id;
		this.amountDue = amount_due;
		this.dueDate = due_date;
		this.paymentStatus = payment_status;
	}
	
	public PaymentListHibernate(PaymentListHibernate obj) {
		this.id = obj.id;
		this.customerId = obj.id;
		this.amountDue = obj.amountDue;
		this.dueDate = obj.dueDate;
		this.paymentStatus = obj.paymentStatus;
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

	public int getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(int amountDue) {
		this.amountDue = amountDue;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncustomer id: " + customerId + "\namount due: " + amountDue + "\ndue_date: " + dueDate
				+ "\npayment status: " + paymentStatus + "\npaymentHistory: " /*+ paymentHistory */+ "\n";
	}

	//to create a payment list
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderPaymentList.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("payment created");
	}
	
	public PaymentListHibernate readSingle(String id) {
		setupLogger();
		PaymentListHibernate paymentList = null;
		Session session = SessionFactoryBuilderPaymentList.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		paymentList = (PaymentListHibernate) session.createQuery("FROM PaymentListHibernate WHERE customer_id = " + id).getSingleResult();
		transaction.commit();
		session.close();
		logger.info("single payment read");
		return paymentList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PaymentListHibernate> readAll(String id) {
		setupLogger();
		ArrayList<PaymentListHibernate> paymentList = new ArrayList<>();
		Session session = SessionFactoryBuilderPaymentList.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		paymentList = (ArrayList<PaymentListHibernate>) session.createQuery("FROM PaymentListHibernate WHERE customer_id = " + id).getResultList();
		transaction.commit();
		session.close();
		logger.info("payments read");
		return paymentList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PaymentListHibernate> readAllForEveryOne() {
		setupLogger();
		ArrayList<PaymentListHibernate> paymentList = new ArrayList<>();
		Session session = SessionFactoryBuilderPaymentList.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		paymentList = (ArrayList<PaymentListHibernate>) session.createQuery("FROM PaymentListHibernate").getResultList();
		transaction.commit();
		session.close();
		logger.info("payments read for all");
		return paymentList;
	}
	
	public void update() {
		setupLogger();
		Session session = SessionFactoryBuilderPaymentList.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		PaymentListHibernate obj = (PaymentListHibernate) session.get(PaymentListHibernate.class, this.id);
		obj.setCustomerId(this.customerId);
		obj.setAmountDue(this.amountDue);
		obj.setDueDate(this.dueDate);
		obj.setPaymentStatus(this.paymentStatus);
		session.update(obj);
		transaction.commit();
		session.close();
		logger.info("payment updated");
	}
	
	public void delete() {
		setupLogger();
		Session session = SessionFactoryBuilderPaymentList.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		PaymentListHibernate obj = (PaymentListHibernate) session.get(PaymentListHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
		logger.info("payment deleted");
	}
	
}
