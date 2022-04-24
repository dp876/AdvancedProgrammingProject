package object.classes;

import java.io.Serializable;

public class AssignedComplaintResponsesObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int assignedId;
	private String liveChat;
	private String response;
	
	public AssignedComplaintResponsesObj() {
		this.assignedId = 0;
		this.liveChat = "";
		this.response = "";
	}
	
	public AssignedComplaintResponsesObj(int assignedId, String liveChat, String response) {
		this.assignedId = assignedId;
		this.liveChat = liveChat;
		this.response = response;
	}
	
	public AssignedComplaintResponsesObj(AssignedComplaintResponsesObj obj) {
		this.assignedId = obj.assignedId;
		this.liveChat = obj.liveChat;
		this.response = obj.response;
	}

	public int getAssignedId() {
		return assignedId;
	}

	public void setAssignedId(int assignedId) {
		this.assignedId = assignedId;
	}

	public String getLiveChat() {
		return liveChat;
	}

	public void setLiveChat(String liveChat) {
		this.liveChat = liveChat;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "assignedId: " + assignedId + "\nliveChat: " + liveChat + "\nresponse: " + response + "\n";
	}
	
}
