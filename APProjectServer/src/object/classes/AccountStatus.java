package object.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountStatus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PaymentListObj paymentStatus;
	private ArrayList<PaymentHistoryObj> paymentHistoryList;
	
	public AccountStatus() {
		paymentStatus = new PaymentListObj();
		paymentHistoryList = new ArrayList<>();
	}

	public AccountStatus(PaymentListObj paymentStatus, ArrayList<PaymentHistoryObj> paymentHistoryList) {
		this.paymentStatus = paymentStatus;
		this.paymentHistoryList = paymentHistoryList;
	}
	
	public AccountStatus(AccountStatus obj) {
		this.paymentStatus = obj.paymentStatus;
		this.paymentHistoryList = obj.paymentHistoryList;
	}

	public void addToPaymentHistoryList(PaymentHistoryObj obj) {
		paymentHistoryList.add(obj);
	}
	
	public PaymentListObj getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentListObj paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public ArrayList<PaymentHistoryObj> getPaymentHistoryList() {
		return paymentHistoryList;
	}

	public void setPaymentHistoryList(ArrayList<PaymentHistoryObj> paymentHistoryList) {
		this.paymentHistoryList = paymentHistoryList;
	}

	@Override
	public String toString() {
		return "paymentStatus: " + paymentStatus + "\npaymentHistoryList: " + paymentHistoryList + "\n";
	}
	
}
