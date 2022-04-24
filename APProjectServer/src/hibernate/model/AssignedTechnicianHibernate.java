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

import hibernate.factories.SessionFactoryBuilderAssignedTechnician;

@Entity
@Table(name="assigned_technician")
public class AssignedTechnicianHibernate implements Serializable {
	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(AssignedTechnicianHibernate.class.getName()); 
	
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
	
	@Column(name="technician_id")
	private int technicianId;
	
	@Column(name="reported_issue_id")
	private int reportedIssueId;

	@Column(name="live_chat")
	private String liveChat;
	
	public AssignedTechnicianHibernate() {
		this.id = 0;
		this.technicianId = 0;
		this.reportedIssueId = 0;
		this.liveChat = "";
	}

	public AssignedTechnicianHibernate(int id, int technicianId, int reportedIssueId, String liveChat) {
		this.id = id;
		this.technicianId = technicianId;
		this.reportedIssueId = reportedIssueId;
		this.liveChat = liveChat;
	}
	
	public AssignedTechnicianHibernate(AssignedTechnicianHibernate obj) {
		this.id = obj.id;
		this.technicianId = obj.technicianId;
		this.reportedIssueId = obj.reportedIssueId;
		this.liveChat = obj.liveChat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(int technicianId) {
		this.technicianId = technicianId;
	}

	public int getReportedIssueId() {
		return reportedIssueId;
	}

	public void setReportedIssueId(int reportedIssueId) {
		this.reportedIssueId = reportedIssueId;
	}

	public String getLiveChat() {
		return liveChat;
	}

	public void setLiveChat(String liveChat) {
		this.liveChat = liveChat;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ntechnicianId: " + technicianId + "\nreportedIssueId: " + reportedIssueId
				+ "\nliveChat: " + liveChat + "\n";
	}
	
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderAssignedTechnician.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("technician created");
	}
	
	public void update() {
		Session session = SessionFactoryBuilderAssignedTechnician.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		AssignedTechnicianHibernate obj = (AssignedTechnicianHibernate) session.get(AssignedTechnicianHibernate.class, this.id);
		obj.setTechnicianId(this.technicianId);
		obj.setReportedIssueId(this.reportedIssueId);
		obj.setLiveChat(this.liveChat);
		session.update(obj);
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AssignedTechnicianHibernate> readAll() {
		ArrayList<AssignedTechnicianHibernate> assignedTechnicianList = new ArrayList<>();
		Session session = SessionFactoryBuilderAssignedTechnician.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		assignedTechnicianList = (ArrayList<AssignedTechnicianHibernate>) session.createQuery("FROM AssignedTechnicianHibernate").getResultList();
		transaction.commit();
		session.close();
		return assignedTechnicianList;
	}
	
	public void delete() {
		Session session = SessionFactoryBuilderAssignedTechnician.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		AssignedTechnicianHibernate obj = (AssignedTechnicianHibernate) session.get(AssignedTechnicianHibernate.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
	}
	
	public void updateLiveChatStatus() {
		setupLogger();
		Session session = SessionFactoryBuilderAssignedTechnician.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		AssignedTechnicianHibernate obj = (AssignedTechnicianHibernate) session.get(AssignedTechnicianHibernate.class, this.id);
		obj.setLiveChat(this.liveChat);
		session.update(obj);
		transaction.commit();
		session.close();
		logger.info("live chat status updated");
	}

}
