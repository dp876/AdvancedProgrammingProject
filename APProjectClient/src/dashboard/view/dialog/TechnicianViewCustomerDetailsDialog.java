package dashboard.view.dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;
import javax.swing.JTextArea;

import object.classes.AssignedComplaintsObj;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TechnicianViewCustomerDetailsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnClose;
	private JScrollPane scrollPane;
	private JTextArea textAreaCustomerDetails;
	private AssignedComplaintsObj assignedComplaint;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TechnicianViewCustomerDetailsDialog dialog = new TechnicianViewCustomerDetailsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TechnicianViewCustomerDetailsDialog() {
		initializeComponents();
		addComponentsToPanel();
		registerListeners();
	}
	
	public TechnicianViewCustomerDetailsDialog(AssignedComplaintsObj assignedComplaint) {
		this.assignedComplaint = assignedComplaint;
		initializeComponents();
		addComponentsToPanel();
		registerListeners();
		displayAssignedCustomerInfo();
	}
		
	private void initializeComponents() {
		this.setModal(true);
		this.setResizable(false);
		this.setType(Type.POPUP);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 700, 500);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		btnClose = new JButton("Close");
		btnClose.setFocusable(false);
		btnClose.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 15));
		btnClose.setBounds(285, 415, 130, 35);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 30, 600, 370);
		
		textAreaCustomerDetails = new JTextArea();
		textAreaCustomerDetails.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		textAreaCustomerDetails.setEditable(false);
		scrollPane.setViewportView(textAreaCustomerDetails);
	}
	
	private void addComponentsToPanel() {
		getContentPane().add(btnClose);
		getContentPane().add(scrollPane);
	}
	
	private void registerListeners() {
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	private void displayAssignedCustomerInfo() {
		String str = "";
		textAreaCustomerDetails.setText("Name: " + assignedComplaint.getFullName());
		str = textAreaCustomerDetails.getText() + "\n";
		
		textAreaCustomerDetails.setText(str + "Email: " + assignedComplaint.getEmail());
		str = textAreaCustomerDetails.getText() + "\n";
		
		textAreaCustomerDetails.setText(str + "Telephone #: " + assignedComplaint.getTele());
		str = textAreaCustomerDetails.getText() + "\n";
		
		textAreaCustomerDetails.setText(str + "Adddress: " + assignedComplaint.getAddress());
		
	}
	
}
