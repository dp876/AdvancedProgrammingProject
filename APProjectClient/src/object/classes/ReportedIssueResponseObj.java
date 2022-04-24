package object.classes;

import java.io.Serializable;

public class ReportedIssueResponseObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int complaintId;
	private String employeeType;
	private String employeeName;
	private String response;
	private String responseDate;
	
	public ReportedIssueResponseObj() {
		this.complaintId = 0;
		this.employeeType = "";
		this.employeeName = "";
		this.response = "";
		this.responseDate = "";
	}

	public ReportedIssueResponseObj(int complaintId, String employeeType, String employeeName, String response, String responseDate) {
		this.complaintId = complaintId;
		this.employeeType = employeeType;
		this.employeeName = employeeName;
		this.response = response;
		this.responseDate = responseDate;
	}
	
	public ReportedIssueResponseObj(ReportedIssueResponseObj obj) {
		this.complaintId = obj.complaintId;
		this.employeeType = obj.employeeType;
		this.employeeName = obj.employeeName;
		this.response = obj.response;
		this.responseDate = obj.responseDate;
	}

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
		return "complaintId: " + complaintId + "\nemployeeType: " + employeeType + "\nemployeeName: " + employeeName
				+ "\nresponse: " + response + "\nresponseDate: " + responseDate + "\n";
	}
	
}
