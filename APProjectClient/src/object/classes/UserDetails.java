package object.classes;

import java.io.Serializable;

public class UserDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String firstName;
	protected String lastName;
	protected String emailAddress;
	protected String homeAddress;
	protected String teleNum;
	
	public UserDetails() {
		this.firstName = "";
		this.lastName = "";
		this.emailAddress = "";
		this.homeAddress = "";
		this.teleNum = "";
	}
	
	public UserDetails(String firstName, String lastName, String emailAddress, String homeAddress, String teleNum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
		this.teleNum = teleNum;
	}
	
	public UserDetails(UserDetails obj) {
		this.firstName = obj.firstName;
		this.lastName = obj.lastName;
		this.emailAddress = obj.emailAddress;
		this.homeAddress = obj.homeAddress;
		this.teleNum = obj.teleNum;
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
		return "firstName: " + firstName + "\nlastName: " + lastName + "\nemailAddress: " + emailAddress
				+ "\nhomeAddress: " + homeAddress + "\nteleNum: " + teleNum + "\n";
	}
	
}
