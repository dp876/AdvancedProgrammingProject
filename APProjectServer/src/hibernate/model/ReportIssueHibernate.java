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

import hibernate.factories.SessionFactoryBuilderReportIssue;

@Entity
@Table(name="reported_issue")
public class ReportIssueHibernate implements Serializable {

	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(ReportIssueHibernate.class.getName()); 
	
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
	
	@Column(name="userid")
	private int userId;
	
	@Column(name="product_service_id")
	private int productServiceId;
	
	@Column(name="complaint_category_id")
	private int complaintCategoryId;
	
	@Column(name="complaint")
	private String complaint;
	
	@Column(name="completed")
	private String completed;
	
	public ReportIssueHibernate() {
		this.id = 0;
		this.userId = 0;
		this.productServiceId = 0;
		this.complaintCategoryId = 0;
		this.complaint = "";
		this.completed = "NO";
	}
	
	public ReportIssueHibernate(int id, int userid, int psId, int ccId, String complaint, String completed) {
		this.id = id;
		this.userId = userid;
		this.productServiceId = psId;
		this.complaintCategoryId = ccId;
		this.complaint = complaint;
		this.completed = completed;
	}
	
	public ReportIssueHibernate(ReportIssueHibernate obj) {
		this.id = obj.id;
		this.userId = obj.userId;
		this.productServiceId = obj.productServiceId;
		this.complaintCategoryId = obj.productServiceId;
		this.complaint = obj.complaint;
		this.completed = obj.completed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserid(int userId) {
		this.userId = userId;
	}

	public int getProductServiceId() {
		return productServiceId;
	}

	public void setProductServiceId(int productServiceId) {
		this.productServiceId = productServiceId;
	}

	public int getComplaintCategoryId() {
		return complaintCategoryId;
	}

	public void setComplaintCategoryId(int complaintCategoryId) {
		this.complaintCategoryId = complaintCategoryId;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nuserid: " + userId + "\nproduct service id: " + productServiceId
				+ "\ncomplaint category id: " + complaintCategoryId + "\ncomplaint: " + complaint + "\nreplied to: "
				+ completed + "\n";
	}
	
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderReportIssue.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("report issue created");
	}
	
	public ReportIssueHibernate readSingle(String id) {
		setupLogger();
		ReportIssueHibernate reportIssue = null;
		Session session = SessionFactoryBuilderReportIssue.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		reportIssue = (ReportIssueHibernate) session.createQuery("FROM ReportIsseHibernate WHERE id = " + userId).getSingleResult();
		transaction.commit();
		session.close();
		logger.info("report issue read");
		return reportIssue;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ReportIssueHibernate> readAll(String id) {
		setupLogger();
		ArrayList<ReportIssueHibernate> reportIssue = new ArrayList<>();
		Session session = SessionFactoryBuilderReportIssue.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		reportIssue = (ArrayList<ReportIssueHibernate>) session.createQuery("FROM ReportIssueHibernate WHERE id = " + userId).getResultList();
		transaction.commit();
		session.close();
		logger.info("all report issues read");
		return reportIssue;
		
	}
	
	public void updateCompletedStatus() {
		setupLogger();
		Session session = SessionFactoryBuilderReportIssue.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ReportIssueHibernate obj = (ReportIssueHibernate) session.get(ReportIssueHibernate.class, this.id);
		obj.setCompleted(this.completed);
		session.update(obj);
		transaction.commit();
		session.close();
		logger.info("issue status updated");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ReportIssueHibernate> readAllForEveryOne() {
		setupLogger();
		ArrayList<ReportIssueHibernate> reportIssueList = new ArrayList<>();
		Session session = SessionFactoryBuilderReportIssue.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		reportIssueList = (ArrayList<ReportIssueHibernate>) session.createQuery("FROM ReportIssueHibernate").getResultList();
		transaction.commit();
		session.close();
		logger.info("all issues read");
		return reportIssueList;
		
	}
	
	public void update() {
		setupLogger();
		Session session = SessionFactoryBuilderReportIssue.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ReportIssueHibernate obj = (ReportIssueHibernate) session.get(ReportIssueHibernate.class, this.id);
		obj.setUserid(this.userId);
		obj.setProductServiceId(this.productServiceId);
		obj.setComplaintCategoryId(this.complaintCategoryId);
		obj.setComplaint(this.complaint);
		obj.setCompleted(this.completed);
		session.update(obj);
		transaction.commit();
		session.close();
		logger.info("issue updated");
	}
	
	public void delete() {
		setupLogger();
		Session session = SessionFactoryBuilderReportIssue.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		ReportIssueHibernate obj = (ReportIssueHibernate) session.get(ReportIssueHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
		logger.info("report issue deleted");
	}
	
}
