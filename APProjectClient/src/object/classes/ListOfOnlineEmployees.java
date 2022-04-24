package object.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfOnlineEmployees implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<OnlineEmployeesObj> onlineEmployeesList = new ArrayList<>();
	
	public ListOfOnlineEmployees() {
		this.onlineEmployeesList = new ArrayList<>();
	}
	
	public ListOfOnlineEmployees(ArrayList<OnlineEmployeesObj> onlineEmployeesList) {
		this.onlineEmployeesList = onlineEmployeesList;
	}
	
	public ListOfOnlineEmployees(ListOfOnlineEmployees obj) {
		this.onlineEmployeesList = obj.onlineEmployeesList;
	}
	
	public void addToListOfOnlineEmployees(OnlineEmployeesObj obj) {
		onlineEmployeesList.add(obj);
	}

	public ArrayList<OnlineEmployeesObj> getOnlineEmployeesList() {
		return onlineEmployeesList;
	}

	public void setOnlineEmployeesList(ArrayList<OnlineEmployeesObj> onlineEmployeesList) {
		this.onlineEmployeesList = onlineEmployeesList;
	}

	@Override
	public String toString() {
		return "onlineEmployeesList: " + onlineEmployeesList + "\n";
	}
	
}
