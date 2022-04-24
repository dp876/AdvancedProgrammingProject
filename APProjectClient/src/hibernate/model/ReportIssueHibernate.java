package hibernate.model;

import java.io.Serializable;




public class ReportIssueHibernate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private int productServiceId;
	private int complaintCategoryId;
	private String complaint;
	private String completed;
	
	
	//default constructor
	public ReportIssueHibernate() {
		this.id = 0;
		this.userId = 0;
		this.productServiceId = 0;
		this.complaintCategoryId = 0;
		this.complaint = "";
		this.completed = "NO";
	}
	
	//primary constructor
	public ReportIssueHibernate(int id, int userId, int psId, int ccId, String complaint, String completed) {
		this.id = id;
		this.userId = userId;
		this.productServiceId = psId;
		this.complaintCategoryId = ccId;
		this.complaint = complaint;
		this.completed = completed;
	}
	
	//copy constructor
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

	public int getUserid() {
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
		return "id: " + id + "\nuser Id: " + userId + "\nproduct service id: " + productServiceId
				+ "\ncomplaint category id: " + complaintCategoryId + "\ncomplaint: " + complaint + "\nreplied to: "
				+ completed + "\n";
	}
	

	
}
