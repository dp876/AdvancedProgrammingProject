package object.classes;

import java.io.Serializable;
import java.util.ArrayList;


public class ListOfAssignedComplaintsObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<AssignedComplaintsObj> assignedComplaintsListObj;
	private ArrayList<AssignedComplaintResponsesObj> assignedComplaintResponsesListObj;
	
	public ListOfAssignedComplaintsObj() {
		this.assignedComplaintsListObj = new ArrayList<>();
		this.assignedComplaintResponsesListObj = new ArrayList<>();
	}
	
	public ListOfAssignedComplaintsObj(ArrayList<AssignedComplaintsObj> assignedComplaintsListObj, ArrayList<AssignedComplaintResponsesObj> assignedComplaintResponsesListObj) {
		this.assignedComplaintsListObj = assignedComplaintsListObj;
		this.assignedComplaintResponsesListObj = assignedComplaintResponsesListObj;
	}
	
	public ListOfAssignedComplaintsObj(ListOfAssignedComplaintsObj obj) {
		this.assignedComplaintsListObj = obj.assignedComplaintsListObj;
	}
	
	public void addToListOfComplaintResponses(AssignedComplaintResponsesObj obj) {
		assignedComplaintResponsesListObj.add(obj);
	}
	
	public ArrayList<AssignedComplaintResponsesObj> getAssignedComplaintResponsesListObj() {
		return assignedComplaintResponsesListObj;
	}

	public void setAssignedComplaintResponsesListObj(
			ArrayList<AssignedComplaintResponsesObj> assignedComplaintResponsesListObj) {
		this.assignedComplaintResponsesListObj = assignedComplaintResponsesListObj;
	}

	public void addToListOfAssignedComplaints(AssignedComplaintsObj obj) {
		assignedComplaintsListObj.add(obj);
	}

	public ArrayList<AssignedComplaintsObj> getAssignedComplaintsListObj() {
		return assignedComplaintsListObj;
	}

	public void setAssignedComplaintsListObj(ArrayList<AssignedComplaintsObj> assignedComplaintsListObj) {
		this.assignedComplaintsListObj = assignedComplaintsListObj;
	}

	@Override
	public String toString() {
		return "assignedComplaintsListObj: " + assignedComplaintsListObj + "\nassignedComplaintResponsesListObj: "
				+ assignedComplaintResponsesListObj + "\n";
	}

}
