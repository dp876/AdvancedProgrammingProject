package object.classes;

import java.io.Serializable;

public class OnlineEmployeesObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	
	public OnlineEmployeesObj() {
		this.userId = 0;
		this.userName = "";
	}
	
	public OnlineEmployeesObj(int userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	public OnlineEmployeesObj(OnlineEmployeesObj obj) {
		this.userId = obj.userId;
		this.userName = obj.userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "userId: " + userId + "\nuserName: " + userName + "\n";
	}

}
