package hibernate.model;

import java.io.Serializable;

public class PaymentListHibernate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int customerId;
	private int amountDue;
	private String dueDate;
	private String paymentStatus;
	
	public PaymentListHibernate() {
		this.id = 0;
		this.customerId = 0;
		this.amountDue = 0;
		this.dueDate = "";
		this.paymentStatus = "";
	}
	
	public PaymentListHibernate(int id, int customerId, int amountDue, String dueDate, String paymentStatus /*, ArrayList<PaymentHistory> paymentHistory*/) {
		this.id = id;
		this.customerId = customerId;
		this.amountDue = amountDue;
		this.dueDate = dueDate;
		this.paymentStatus = paymentStatus;
	}
	
	public PaymentListHibernate(PaymentListHibernate obj) {
		this.id = obj.id;
		this.customerId = obj.id;
		this.amountDue = obj.amountDue;
		this.dueDate = obj.dueDate;
		this.paymentStatus = obj.paymentStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(int amountDue) {
		this.amountDue = amountDue;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncustomer id: " + customerId + "\namount due: " + amountDue + "\ndue date: " + dueDate
				+ "\npayment status: " + paymentStatus + "\n";
	}

}
