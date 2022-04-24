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

import hibernate.factories.SessionFactoryBuilderPaymentHistory;

@Entity
@Table(name="payment_history")
public class PaymentHistoryHibernate implements Serializable {
	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(PaymentHistoryHibernate.class.getName()); 
	
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
	
	@Column(name="amount_paid")
	private int amountPaid;
	
	@Column(name="date_due")
	private String dateDue;
	
	@Column(name="date_paid")
	private String datePaid;
	
	public PaymentHistoryHibernate() {
		this.id = 0;
		this.customerId = 0;
		this.amountPaid = 0;
		this.dateDue = "";
		this.datePaid = "";
	}
	
	public PaymentHistoryHibernate(int id, int customerId, int amountPaid, String dueDate, String datePaid) {
		this.id = id;
		this.customerId = customerId;
		this.amountPaid = amountPaid;
		this.dateDue = dueDate;
		this.datePaid = datePaid;
	}
	
	public PaymentHistoryHibernate(PaymentHistoryHibernate obj) {
		this.id = obj.id;
		this.customerId = obj.customerId;
		this.amountPaid = obj.amountPaid;
		this.dateDue = obj.dateDue;
		this.datePaid = obj.datePaid;
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

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getDueDate() {
		return dateDue;
	}

	public void setDueDate(String dueDate) {
		this.dateDue = dueDate;
	}

	public String getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(String datePaid) {
		this.datePaid = datePaid;
	}
	
	@Override
	public String toString() {
		return "id: " + id + "\ncustomerid: " + customerId + "\namount paid: " + amountPaid + "\ndate due: "
				+ dateDue + "\ndatepaid: " + datePaid + "\n";
	}

	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderPaymentHistory.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("payment created");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PaymentHistoryHibernate> readAll(String id) {
		setupLogger();
		ArrayList<PaymentHistoryHibernate> paymentHistory = new ArrayList<>();
		Session session = SessionFactoryBuilderPaymentHistory.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		paymentHistory = (ArrayList<PaymentHistoryHibernate>) session.createQuery("FROM PaymentHistoryHibernate WHERE customer_id = " + id).getResultList();
		transaction.commit();
		session.close();
		logger.info("read from payment list");
		return paymentHistory;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PaymentHistoryHibernate> readAllForEveryOne() {
		ArrayList<PaymentHistoryHibernate> paymentHistory = new ArrayList<>();
		Session session = SessionFactoryBuilderPaymentHistory.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		paymentHistory = (ArrayList<PaymentHistoryHibernate>) session.createQuery("FROM PaymentHistoryHibernate").getResultList();
		transaction.commit();
		session.close();
		return paymentHistory;
	}
	
	public void update() {
		Session session = SessionFactoryBuilderPaymentHistory.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		PaymentHistoryHibernate obj = (PaymentHistoryHibernate) session.get(PaymentHistoryHibernate.class, this.id);
		obj.setCustomerId(this.customerId);
		obj.setAmountPaid(this.amountPaid);
		obj.setDueDate(this.dateDue);
		obj.setDatePaid(this.datePaid);
		session.update(obj);
		transaction.commit();
		session.close();
	}
	
	public void delete() {
		Session session = SessionFactoryBuilderPaymentHistory.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		PaymentHistoryHibernate obj = (PaymentHistoryHibernate) session.get(PaymentHistoryHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
	}
	
}
