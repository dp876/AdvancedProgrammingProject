package hibernate.model;

import java.io.Serializable;

public class PaymentHistoryHibernate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int customerId;
	private int amountPaid;
	private String dateDue;
	private String datePaid;
	
	public PaymentHistoryHibernate() {
		this.id = 0;
		this.customerId = 0;
		this.amountPaid = 0;
		this.dateDue = "";
		this.datePaid = "";
	}
	
	public PaymentHistoryHibernate(int id, int customer_id, int amount_paid, String due_date, String date_paid) {
		this.id = id;
		this.customerId = customer_id;
		this.amountPaid = amount_paid;
		this.dateDue = due_date;
		this.datePaid = date_paid;
	}
	
	public PaymentHistoryHibernate(PaymentHistoryHibernate obj) {
		this.id = obj.id;
		this.customerId = obj.customerId;
		this.amountPaid = obj.amountPaid;
		this.dateDue = obj.dateDue;
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

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getDueDate() {
		return dateDue;
	}

	public void setDueDate(String dueDate) {
		this.dateDue = dueDate;
	}

	public String getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(String datePaid) {
		this.datePaid = datePaid;
	}
	
	@Override
	public String toString() {
		return "id: " + id + "\ncustomer id: " + customerId + "\namount paid: " + amountPaid + "\ndate due: "
				+ dateDue + "\ndate paid: " + datePaid + "\n";
	}


}
