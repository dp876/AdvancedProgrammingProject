package hibernate.model;

import java.io.Serializable;

public class EmployeeHibernate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String employeeType;
	private int employeeUserId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String homeAddress;
	private String teleNum;
	
	public EmployeeHibernate() {
		this.id = 0;
		this.employeeType = "";
		this.employeeUserId = 0;
		this.firstName = "";
		this.lastName = "";
		this.emailAddress = "";
		this.homeAddress = "";
		this.teleNum = "";
	}
	
	public EmployeeHibernate(int id, String employeeType, int employeeUserId, String firstName, String lastName, String emailAddress, String homeAddress, String teleNum) {
		this.id = id;
		this.employeeType = employeeType;
		this.employeeUserId = employeeUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
		this.teleNum = teleNum;
	}
	
	public EmployeeHibernate(EmployeeHibernate obj) {
		this.id = obj.id;
		this.employeeType = obj.employeeType;
		this.employeeUserId = obj.employeeUserId;
		this.firstName = obj.firstName;
		this.lastName = obj.lastName;
		this.emailAddress = obj.emailAddress;
		this.homeAddress = obj.homeAddress;
		this.teleNum = obj.teleNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public int getEmployeeUserId() {
		return employeeUserId;
	}

	public void setEmployeeUserId(int employeeUserId) {
		this.employeeUserId = employeeUserId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getTeleNum() {
		return teleNum;
	}

	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nemployeeType: " + employeeType + "\nemployeeUserId: " + employeeUserId + "\nfirstName: "
				+ firstName + "\nlastName: " + lastName + "\nemailAddress: " + emailAddress + "\nhomeAddress: "
				+ homeAddress + "\nteleNum: " + teleNum + "\n";
	}
	
}
