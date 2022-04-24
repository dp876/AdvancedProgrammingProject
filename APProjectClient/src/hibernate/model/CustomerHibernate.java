package hibernate.model;

import java.io.Serializable;

public class CustomerHibernate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int customerUserId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String homeAddress;
	private String teleNum;
	
	public CustomerHibernate() {
		this.id = 0;
		this.customerUserId = 0;
		this.firstName = "";
		this.lastName = "";
		this.emailAddress = "";
		this.homeAddress = "";
		this.teleNum = "";
	}
	
	public CustomerHibernate(int id, int customerUserId, String firstName, String lastName, String emailAddress, String homeAddress, String teleNum) {
		this.id = id;
		this.customerUserId = customerUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
		this.teleNum = teleNum;
	}

	public CustomerHibernate(CustomerHibernate obj) {
		this.id = obj.id;
		this.customerUserId = obj.customerUserId;
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

	public int getCustomerUserId() {
		return customerUserId;
	}

	public void setCustomerUserId(int customerUserId) {
		this.customerUserId = customerUserId;
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
		return "id: " + id + "\ncustomerUserId: " + customerUserId + "\nfirstName: " + firstName + "\nlastName: "
				+ lastName + "\nemailAddress: " + emailAddress + "\nhomeAddress: " + homeAddress + "\nteleNum: "
				+ teleNum + "\n";
	}
}
