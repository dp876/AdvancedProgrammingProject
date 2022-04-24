package hibernate.model;

import java.io.Serializable;

public class ReportResponseHibernate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int complaintId;
	private int customerId;
	private int responderId;
	private String response;
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
	
}
