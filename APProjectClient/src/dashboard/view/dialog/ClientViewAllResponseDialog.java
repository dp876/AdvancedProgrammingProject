package dashboard.view.dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import object.classes.ReportedIssueObj;
import object.classes.ReportedIssueResponseObj;

import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientViewAllResponseDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnClose;
	private JScrollPane scrollPane;
	private JTextArea textAreaAllResponses;
	private ReportedIssueObj issueObj = new ReportedIssueObj();
	private ArrayList<ReportedIssueResponseObj> reportedIssueResponseList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientViewAllResponseDialog dialog = new ClientViewAllResponseDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClientViewAllResponseDialog() {
		initializeComponents();
		addComponentsToPanel();
		registerListeners();
	}
	
	public ClientViewAllResponseDialog(ReportedIssueObj issueObj, ArrayList<ReportedIssueResponseObj> reportedIssueResponseList) {
		this.issueObj = issueObj;
		this.reportedIssueResponseList = reportedIssueResponseList;
		initializeComponents();
		addComponentsToPanel();
		registerListeners();
		displayAllResponse();
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
		
		textAreaAllResponses = new JTextArea();
		textAreaAllResponses.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		textAreaAllResponses.setEditable(false);
		scrollPane.setViewportView(textAreaAllResponses);
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
	
	private void displayAllResponse() {
		String str = "";
		StringBuilder strBuilder;
		textAreaAllResponses.setText("Product Service: " + issueObj.getProductService());
		str = textAreaAllResponses.getText() + "\n";
		
		textAreaAllResponses.setText(str + "Complaint Category: " + issueObj.getCategoryName());
		str = textAreaAllResponses.getText() + "\n";
		
		String complaint = issueObj.getComplaint();
		
		// break line after a certain amount of characters
		strBuilder = new StringBuilder(complaint + "\n\n");
		int i = 0;
		while ((i = strBuilder.indexOf(" ", i + 40)) != -1) {
			strBuilder.replace(i, i + 1, "\n      ");
		}
		
		textAreaAllResponses.setText(str + "Complaint: \n      " + strBuilder);
		str = textAreaAllResponses.getText() + "\n";
		
		// loop through responses and display all that correspond with the selected complaint ID
		for (ReportedIssueResponseObj value : reportedIssueResponseList) {
			if (issueObj.getId() == value.getComplaintId()) {
				textAreaAllResponses.setText(str + "Response From: " + value.getEmployeeName());
				str = textAreaAllResponses.getText() + "\n";
				
				textAreaAllResponses.setText(str + "Response Date: " + value.getResponseDate());
				str = textAreaAllResponses.getText() + "\n";
				
				String response = value.getResponse();
				
				// break line after a certain amount of characters
				strBuilder = new StringBuilder(response + "\n\n");
				int x = 0;
				while ((x = strBuilder.indexOf(" ", x + 40)) != -1) {
					strBuilder.replace(x, x + 1, "\n      ");
				}
				
				textAreaAllResponses.setText(str + "Response: \n      " + strBuilder);
				str = textAreaAllResponses.getText() + "\n";				
			}		
		}
	}
}
