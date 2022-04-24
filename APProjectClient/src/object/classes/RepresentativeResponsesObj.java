package object.classes;

import java.io.Serializable;

public class RepresentativeResponsesObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int complaintIdPrimaryKey;
	private String response;
	
	public RepresentativeResponsesObj() {
		this.complaintIdPrimaryKey = 0;
		this.response = "";
	}

	public RepresentativeResponsesObj(int complaintIdPrimaryKey, String response) {
		this.complaintIdPrimaryKey = complaintIdPrimaryKey;
		this.response = response;
	}
	
	public RepresentativeResponsesObj(RepresentativeResponsesObj obj) {
		this.complaintIdPrimaryKey = obj.complaintIdPrimaryKey;
		this.response = obj.response;
	}

	public int getComplaintIdPrimaryKey() {
		return complaintIdPrimaryKey;
	}

	public void setComplaintIdPrimaryKey(int complaintIdPrimaryKey) {
		this.complaintIdPrimaryKey = complaintIdPrimaryKey;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "complaintIdPrimaryKey: " + complaintIdPrimaryKey + "\nresponse: " + response + "\n";
	}
	
}
