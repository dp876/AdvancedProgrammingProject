package object.classes;

import java.io.Serializable;


public class PaymentListObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int customerId;
	private int amountDue;
	private String dueDate;
	private String paymentStatus;
	
	public PaymentListObj() {
		this.id = 0;
		this.customerId = 0;
		this.amountDue = 0;
		this.dueDate = "";
		this.paymentStatus = "";
	}
	
	public PaymentListObj(int id, int customer_id, int amount_due, String due_date, String payment_status) {
		this.id = id;
		this.customerId = customer_id;
		this.amountDue = amount_due;
		this.dueDate = due_date;
		this.paymentStatus = payment_status;
	}
	
	public PaymentListObj(PaymentListObj obj) {
		this.id = obj.id;
		this.customerId = obj.customerId;
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

	public void setDue_date(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPayment_status(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncustomer id: " + customerId + "\namount due: " + amountDue + "\ndue date: " + dueDate
				+ "\npayment status: " + paymentStatus + "\n";
	}
	
	
}
