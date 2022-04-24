package object.classes;

import java.io.Serializable;

public class AssignedComplaintsIdObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int assingedComplaintId;
	
	public AssignedComplaintsIdObj() {
		this.assingedComplaintId = 0;
	}
	
	public AssignedComplaintsIdObj(int assingedComplaintId) {
		this.assingedComplaintId = assingedComplaintId;
	}
	
	public AssignedComplaintsIdObj(AssignedComplaintsIdObj obj) {
		this.assingedComplaintId = obj.assingedComplaintId;
	}

	public int getAssingedComplaintId() {
		return assingedComplaintId;
	}

	public void setAssingedComplaintId(int assingedComplaintId) {
		this.assingedComplaintId = assingedComplaintId;
	}

	@Override
	public String toString() {
		return "assingedComplaintId: " + assingedComplaintId + "\n";
	}
	
}
