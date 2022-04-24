package object.classes;

import java.io.Serializable;

public class PaymentHistoryObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int customerId;
	private int amountPaid;
	private String dueDate;
	private String datePaid;
	
	public PaymentHistoryObj() {
		this.id = 0;
		this.customerId = 0;
		this.amountPaid = 0;
		this.dueDate = "";
		this.datePaid = "";
	}
	
	public PaymentHistoryObj(int id, int customer_id, int amount_paid, String due_date, String date_paid) {
		this.id = id;
		this.customerId = customer_id;
		this.amountPaid = amount_paid;
		this.dueDate = due_date;
		this.datePaid = date_paid;
	}
	
	public PaymentHistoryObj(PaymentHistoryObj obj) {
		this.id = obj.id;
		this.customerId = obj.customerId;
		this.amountPaid = obj.amountPaid;
		this.dueDate = obj.dueDate;
		this.datePaid = obj.datePaid;
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

	public void setCustomer_id(int customerId) {
		this.customerId = customerId;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmount_paid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDue_date(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getDatePaid() {
		return datePaid;
	}

	public void setDate_paid(String datePaid) {
		this.datePaid = datePaid;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncustomer id: " + customerId + "\namount paid: " + amountPaid + "\ndue date: "
				+ dueDate + "\ndate paid: " + datePaid + "\n";
	}

}
