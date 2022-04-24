package hibernate.model;

import java.io.Serializable;

public class AssignedTechnicianHibernate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int technicianId;
	private int reportedIssueId;
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
	
}
