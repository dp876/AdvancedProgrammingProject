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

import hibernate.factories.SessionFactoryBuilderReportResponse;

@Entity
@Table(name="response")
public class ReportResponseHibernate implements Serializable {
	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(ReportResponseHibernate.class.getName()); 
	
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
	
	@Column(name="complaint_id")
	private int complaintId;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="responder_id")
	private int responderId;
	
	@Column(name="response")
	private String response;
	
	@Column(name="response_date")
	private String responseDate;
	
	public ReportResponseHibernate() {
		this.id = 0;
		this.complaintId  = 0;
		this.customerId = 0;
		this.responderId = 0;
		this.response = "";
		this.responseDate = "";
	}
	
	public ReportResponseHibernate(int id, int compId, int custId, int respId, String response, String respDate) {
		this.id = id;
		this.complaintId = compId;
		this.customerId = custId;
		this.responderId = respId;
		this.response = response;
		this.responseDate = respDate;
	}
	
	public ReportResponseHibernate(ReportResponseHibernate obj) {
		this.id = obj.id;
		this.complaintId = obj.complaintId;
		this.customerId = obj.customerId;
		this.responderId = obj.responderId;
		this.response = obj.response;
		this.responseDate = obj.responseDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getResponderId() {
		return responderId;
	}

	public void setResponderId(int responderId) {
		this.responderId = responderId;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncomplaint id: " + complaintId + "\ncustomer id: " + customerId + "\nresponder id: "
				+ responderId + "\nresponse: " + response + "\nresponse date: " + responseDate + "\n";
	}
	
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderReportResponse.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("report response created");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ReportResponseHibernate> readAll(){
		setupLogger();
		ArrayList<ReportResponseHibernate> responseList = new ArrayList<>();
		Session session = SessionFactoryBuilderReportResponse.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		responseList = (ArrayList<ReportResponseHibernate>) session.createQuery("FROM ReportResponseHibernate").getResultList();
		transaction.commit();
		session.close();
		logger.info("all report responses read");
		return responseList;
	}
	
	public void update() {
		setupLogger();
		Session session = SessionFactoryBuilderReportResponse.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ReportResponseHibernate obj = (ReportResponseHibernate) session.get(ReportResponseHibernate.class, this.id);
		obj.setComplaintId(this.complaintId);
		obj.setCustomerId(this.customerId);
		obj.setResponderId(this.responderId);
		obj.setResponse(this.response);
		obj.setResponseDate(this.responseDate);
		session.update(obj);
		transaction.commit();
		session.close();
		logger.info("report response updated");
	}
	
	public void delete() {
		setupLogger();
		Session session = SessionFactoryBuilderReportResponse.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ReportResponseHibernate obj = (ReportResponseHibernate) session.get(ReportResponseHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
		logger.info("report response deleted");
	}
	
}
