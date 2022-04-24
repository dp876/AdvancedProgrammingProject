package hibernate.model;

import java.io.Serializable;

public class EmployeeLoginHibernate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int employeeId;
	private String employeePassword;
	
	public EmployeeLoginHibernate() {
		this.id = 0;
		this.employeeId = 0;
		this.employeePassword = "";
	}
	
	public EmployeeLoginHibernate(int id, int employeeId, String employeePassword) {
		this.id = id;
		this.employeeId = employeeId;
		this.employeePassword = employeePassword;
	}
	
	public EmployeeLoginHibernate(EmployeeLoginHibernate obj) {
		this.id = obj.id;
		this.employeeId = obj.employeeId;
		this.employeePassword = obj.employeePassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nemployeeId: " + employeeId + "\nemployeePassword: " + employeePassword + "\n";
	}
	
}
