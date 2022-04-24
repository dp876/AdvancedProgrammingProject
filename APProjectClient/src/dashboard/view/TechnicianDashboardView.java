package dashboard.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dashboard.controller.TechnicianDashboardController;
import dashboard.view.dialog.TechnicianViewCustomerDetailsDialog;
import hibernate.model.AssignedTechnicianHibernate;
import hibernate.model.ReportResponseHibernate;
import object.classes.AssignedComplaintResponsesObj;
import object.classes.AssignedComplaintsObj;
import object.classes.ListOfAssignedComplaintsObj;
import object.classes.utility.DateLabelFormatter;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TechnicianDashboardView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TechnicianDashboardController technicianDashboardController;
	private JPanel panelMenu;
	private JButton btnComplaints;
	private JButton btnChat;
	private JButton btnLogout;
	private JTabbedPane tabbedPane;
	private JPanel panelComplaintsTab;
	private JPanel panelChatTab;
	private JLabel lblLoggedInAs;
	private JLabel lblUserName;
	private JLabel lblAssignedComplaints;
	private JTable tableAssignedComplaints;
	private JLabel lblCustomer;
	private JTextField txtCustomer;
	private JTextField txtProductService;
	private JTextField txtComplaintCategory;
	private JTextArea textAreaDescription;
	private JLabel lblResponse;
	private JScrollPane scrollPaneTextAreaResponse;
	private JButton btnViewDetails;
	private JLabel lblDateOfVisit;
	private UtilDateModel utilDateModel;
	private Properties dateProperties;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JScrollPane scrollPaneTextAreaDescription;
	private JScrollPane scrollPaneAssignedComplaintsTable;
	private JLabel lblProductService;
	private JLabel lblComplaintCategory;
	private JLabel lblDescription;
	private JButton btnSubmitResponse;
	private JTextArea textAreaResponse;
	private JCheckBox chckbxLiveChat;
	private JTextPane textPaneLiveChat;
	private JScrollPane scrollPaneLiveChat;
	private JScrollPane scrollPaneChat;
	private JTextArea textAreaChat;
	private JButton btnSend;
//	private boolean showTabsHeader;
	private ArrayList<AssignedComplaintsObj> listOfAssignedComplaints;
	private ArrayList<AssignedComplaintResponsesObj> assignedComplaintResponsesList;
	private JButton btnMarkAsCompleted;
	private SimpleDateFormat dateOnlyFormatter;
	private String recipientUserId;
	
	private StyledDocument styledDoc;
	private Style style;
	private SimpleAttributeSet attributes;
	
	/**
	 * Create the frame.
	 */
	public TechnicianDashboardView(TechnicianDashboardController obj) {
		this.technicianDashboardController = obj;
		initializeComponents();
		addMnemonics();
		addComponentsToPanel();
		addComponentsToWindow();
		setWindowProperties();
		registerActionListeners();
		
		TimerTask task = new TimerTask() {
	        public void run() {
	            lblUserName.setText(technicianDashboardController.getUsernameFromModel());
	            technicianDashboardController.sendQueryToModel("ASSIGNED_COMPLAINTS");
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 1000L;
	    timer.schedule(task, delay);
	}
	
	private void initializeComponents() {
		this.setTitle("Technician Dashboard");
		
		recipientUserId = "NULL";
		
//		showTabsHeader = false;
		listOfAssignedComplaints = null;
		assignedComplaintResponsesList = null;
		dateOnlyFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		panelMenu = new JPanel();
		panelMenu.setFocusable(false);
		panelMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMenu.setBounds(-11, -12, 215, 696);
		panelMenu.setLayout(null);
//		btnAccountStatus.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnComplaints = new JButton("COMPLAINTS");
		btnComplaints.setFocusable(false);
		btnComplaints.setIcon(new ImageIcon(TechnicianDashboardView.class.getResource("/Images/icons8-strike-30.png")));
		btnComplaints.setBounds(12, 267, 202, 55);
//		btnPaymentHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnChat = new JButton("CHAT");
		btnChat.setFocusable(false);
		btnChat.setIcon(new ImageIcon(TechnicianDashboardView.class.getResource("/Images/icons8-chat-30.png")));
		btnChat.setBounds(12, 322, 202, 55);
//		btnReportHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnLogout = new JButton("LOGOUT");
		btnLogout.setFocusable(false);
		btnLogout.setIcon(new ImageIcon(TechnicianDashboardView.class.getResource("/Images/icons8-logout-rounded-left-30.png")));
		btnLogout.setBounds(12, 377, 202, 55);
//		btnComplaintHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblLoggedInAs = new JLabel("Logged in as:");
		lblLoggedInAs.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblLoggedInAs.setFocusable(false);
		lblLoggedInAs.setBounds(15, 52, 138, 22);
		
		lblUserName = new JLabel("[full name]");
		lblUserName.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		lblUserName.setFocusable(false);
		lblUserName.setBounds(15, 75, 199, 22);		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFocusable(false);
		tabbedPane.setBounds(214, 11, 880, 649);
//		tabbedPane.setUI(new BasicTabbedPaneUI() {
//		    @Override
//		    protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
//		        if (showTabsHeader) {
//		            return super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight);
//		        } else {
//		            return 0;
//		        }
//		    }
//		});
		
		panelComplaintsTab = new JPanel();
		panelComplaintsTab.setFocusable(false);
		panelComplaintsTab.setLayout(null);
		
		lblAssignedComplaints = new JLabel("Assigned Complaints:");
		lblAssignedComplaints.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblAssignedComplaints.setFocusable(false);
		lblAssignedComplaints.setBounds(10, 11, 170, 22);
				
		scrollPaneAssignedComplaintsTable = new JScrollPane();
		scrollPaneAssignedComplaintsTable.setBounds(10, 44, 191, 555);
		tableAssignedComplaints = new JTable();
		tableAssignedComplaints.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Complaints"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPaneAssignedComplaintsTable.setViewportView(tableAssignedComplaints);	
		
		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblCustomer.setFocusable(false);
		lblCustomer.setBounds(215, 45, 160, 22);
		
		lblProductService = new JLabel("Product Service:");
		lblProductService.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblProductService.setFocusable(false);
		lblProductService.setBounds(215, 72, 160, 22);
		
		lblComplaintCategory = new JLabel("Complaint Category:");
		lblComplaintCategory.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblComplaintCategory.setFocusable(false);
		lblComplaintCategory.setBounds(215, 99, 160, 22);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblDescription.setFocusable(false);
		lblDescription.setBounds(215, 126, 160, 22);
		
		txtCustomer = new JTextField();
		txtCustomer.setEditable(false);
		txtCustomer.setFocusable(false);
		txtCustomer.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		txtCustomer.setBounds(381, 44, 300, 25);
		txtCustomer.setColumns(10);
		
		txtProductService = new JTextField();
		txtProductService.setEditable(false);
		txtProductService.setFocusable(false);
		txtProductService.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		txtProductService.setColumns(10);
		txtProductService.setBounds(381, 71, 300, 25);
		
		txtComplaintCategory = new JTextField();
		txtComplaintCategory.setEditable(false);
		txtComplaintCategory.setFocusable(false);
		txtComplaintCategory.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		txtComplaintCategory.setColumns(10);
		txtComplaintCategory.setBounds(381, 98, 300, 25);
		
		scrollPaneTextAreaDescription = new JScrollPane();
		scrollPaneTextAreaDescription.setBounds(381, 127, 300, 170);
		
		textAreaDescription = new JTextArea();
		textAreaDescription.setEditable(false);
		textAreaDescription.setFocusable(false);
		textAreaDescription.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setLineWrap(true);
		scrollPaneTextAreaDescription.setViewportView(textAreaDescription);
		
		lblResponse = new JLabel("Response:");
		lblResponse.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblResponse.setFocusable(false);
		lblResponse.setBounds(215, 360, 160, 22);
		
		scrollPaneTextAreaResponse = new JScrollPane();
		scrollPaneTextAreaResponse.setBounds(381, 362, 300, 170);
		
		textAreaResponse = new JTextArea();
		scrollPaneTextAreaResponse.setViewportView(textAreaResponse);
		textAreaResponse.setWrapStyleWord(true);
		textAreaResponse.setLineWrap(true);
		textAreaResponse.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		
		btnViewDetails = new JButton("View Details");
		btnViewDetails.setFocusable(false);
		btnViewDetails.setIcon(new ImageIcon(TechnicianDashboardView.class.getResource("/Images/icons8-view-20.png")));
		btnViewDetails.setBounds(691, 43, 165, 30);
		
		btnSubmitResponse = new JButton("Submit Response");
		btnSubmitResponse.setFocusable(false);
		btnSubmitResponse.setIcon(new ImageIcon(TechnicianDashboardView.class.getResource("/Images/icons8-submit-document-25.png")));
		btnSubmitResponse.setBounds(501, 564, 180, 35);
		
		lblDateOfVisit = new JLabel("Date of Visit:");
		lblDateOfVisit.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblDateOfVisit.setFocusable(false);
		lblDateOfVisit.setBounds(215, 330, 160, 22);
		
		panelChatTab = new JPanel();
		panelChatTab.setFocusable(false);
		panelChatTab.setLayout(null);
		
		scrollPaneLiveChat = new JScrollPane();
		scrollPaneLiveChat.setBounds(45, 45, 500, 315);
		
		textPaneLiveChat = new JTextPane();
		textPaneLiveChat.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		textPaneLiveChat.setEditable(false);
		scrollPaneLiveChat.setViewportView(textPaneLiveChat);
		
		scrollPaneChat = new JScrollPane();
		scrollPaneChat.setBounds(45, 378, 500, 70);
		
		textAreaChat = new JTextArea();
		textAreaChat.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		scrollPaneChat.setViewportView(textAreaChat);
		textAreaChat.setWrapStyleWord(true);
		textAreaChat.setLineWrap(true);
		
		btnSend = new JButton("Send");
		btnSend.setFocusable(false);
		btnSend.setIcon(new ImageIcon(TechnicianDashboardView.class.getResource("/Images/icons8-send-25.png")));
		btnSend.setBounds(365, 459, 180, 40);
		
		utilDateModel = new UtilDateModel();
		
		dateProperties = new Properties();
		dateProperties.put("text.today", "Today");
		dateProperties.put("text.month", "Month");
		dateProperties.put("text.year", "Year");
		
		datePanel = new JDatePanelImpl(utilDateModel, dateProperties);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(381, 332, 250, 25);
		
		chckbxLiveChat = new JCheckBox("Avalilable For Live Chat");
		chckbxLiveChat.setFocusable(false);
		chckbxLiveChat.setBounds(381, 534, 165, 23);
		
		btnMarkAsCompleted = new JButton("Mark as completed");
		btnMarkAsCompleted.setIcon(new ImageIcon(TechnicianDashboardView.class.getResource("/Images/icons8-check-25.png")));
		btnMarkAsCompleted.setFocusable(false);
		btnMarkAsCompleted.setBounds(691, 267, 165, 30);
		
		
		// to allow styling in text pane
		styledDoc = textPaneLiveChat.getStyledDocument();
		style = textPaneLiveChat.addStyle("", null);
		attributes = new SimpleAttributeSet();
	
	}
	
	private void addMnemonics() {
		btnComplaints.setMnemonic(KeyEvent.VK_C);
		btnChat.setMnemonic(KeyEvent.VK_H);
		btnLogout.setMnemonic(KeyEvent.VK_L);
	}
	
	private void addComponentsToPanel() {
		panelMenu.add(btnComplaints);
		panelMenu.add(btnChat);
		panelMenu.add(btnLogout);
		panelMenu.add(lblLoggedInAs);
		panelMenu.add(lblUserName);

		tabbedPane.addTab("COMPLAINTS", null, panelComplaintsTab, null);
		tabbedPane.addTab("CHAT", null, panelChatTab, null);
		
		panelComplaintsTab.add(lblAssignedComplaints);
		panelComplaintsTab.add(scrollPaneAssignedComplaintsTable);
		panelComplaintsTab.add(lblCustomer);
		panelComplaintsTab.add(lblProductService);
		panelComplaintsTab.add(lblComplaintCategory);
		panelComplaintsTab.add(lblDescription);
		panelComplaintsTab.add(txtCustomer);
		panelComplaintsTab.add(txtProductService);
		panelComplaintsTab.add(txtComplaintCategory);
		panelComplaintsTab.add(scrollPaneTextAreaDescription);
		panelComplaintsTab.add(lblResponse);
		panelComplaintsTab.add(scrollPaneTextAreaResponse);
		panelComplaintsTab.add(btnViewDetails);
		panelComplaintsTab.add(datePicker);
		panelComplaintsTab.add(btnSubmitResponse);
		panelComplaintsTab.add(lblDateOfVisit);
		panelComplaintsTab.add(chckbxLiveChat);
		panelComplaintsTab.add(btnMarkAsCompleted);
		
		panelChatTab.add(scrollPaneLiveChat);
		panelChatTab.add(scrollPaneChat);
		panelChatTab.add(btnSend);
	}
	private void addComponentsToWindow() {
		this.getContentPane().add(panelMenu);
		this.getContentPane().add(tabbedPane);
	}
	
	private void setWindowProperties() {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds(100, 100, 1110, 700);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void registerActionListeners() {
		
		//this is to display all of the complaints
		btnComplaints.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetComponents();
				tabbedPane.setSelectedIndex(0);
				technicianDashboardController.sendQueryToModel("ASSIGNED_COMPLAINTS");
			}
		});
		
		btnChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableAssignedComplaints.getSelectedRow(); // get selected row index
				
				if (index < 0)
					return; // return if no row was selected from the table
				
				TechnicianViewCustomerDetailsDialog dialog = new TechnicianViewCustomerDetailsDialog(listOfAssignedComplaints.get(index));
				dialog.setVisible(true);
			}
		});
		
		btnSubmitResponse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableAssignedComplaints.getSelectedRow(); // get selected row index
				
				if (index < 0)
					return; // return if no row was selected from the table
				
				sendResponseToComplaint(index);
			}
		});
		
		btnMarkAsCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableAssignedComplaints.getSelectedRow(); // get selected row index
				
				if (index < 0)
					return; // return if no row was selected from the table
				
				sendCompletedComplaintId(index);
			}
		});
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		
		tableAssignedComplaints.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int i = tableAssignedComplaints.getSelectedRow(); // get selected row index
				populateAssignedComplaintAfterTableClick(i);
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				logout();
			}
		});
	}	
	
	private void logout() {
		int option = JOptionPane.showConfirmDialog(this, "Are you sure?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		 if(option == JOptionPane.YES_OPTION){  
			 technicianDashboardController.returnToLoginScreen();
		 }  
	}
	
	public void populateAssignedComplaints(ListOfAssignedComplaintsObj obj) {
		listOfAssignedComplaints = obj.getAssignedComplaintsListObj();
		assignedComplaintResponsesList = obj.getAssignedComplaintResponsesListObj();
		
		DefaultTableModel tableModel = (DefaultTableModel) tableAssignedComplaints.getModel();
		tableModel.setRowCount(0); // clear table

		for (AssignedComplaintsObj value : listOfAssignedComplaints) {
			tableModel.addRow(new Object[] { value.getComplaintCategory() });
		} 
	}
	
	private void populateAssignedComplaintAfterTableClick(int index) {
		AssignedComplaintsObj assignedComplaint = listOfAssignedComplaints.get(index);

		txtCustomer.setText(assignedComplaint.getFullName());
		txtProductService.setText(assignedComplaint.getProductService());
		txtComplaintCategory.setText(assignedComplaint.getComplaintCategory());
		textAreaDescription.setText(assignedComplaint.getComplaint());
		
		textAreaResponse.setEditable(true);
		btnSubmitResponse.setEnabled(true);
		textAreaResponse.setText("");
		chckbxLiveChat.setEnabled(true);
		chckbxLiveChat.setSelected(false);
		
		for (AssignedComplaintResponsesObj obj : assignedComplaintResponsesList) {
			if (obj.getAssignedId() == assignedComplaint.getId()) {
				textAreaResponse.setEditable(false);
				btnSubmitResponse.setEnabled(false);
				chckbxLiveChat.setEnabled(false);
				
				textAreaResponse.setText(obj.getResponse());
				
				if (obj.getLiveChat().equalsIgnoreCase("YES")) {
					chckbxLiveChat.setSelected(true);
				} 
				
				break;
			}
		}
	}
	
	private void resetComponents() {
		txtCustomer.setText("");
		txtProductService.setText("");
		txtComplaintCategory.setText("");
		textAreaDescription.setText("");
		textAreaResponse.setText("");
		chckbxLiveChat.setSelected(false);
	}
	
	private void sendCompletedComplaintId(int index) {
		int opt = JOptionPane.showConfirmDialog(null, "Mark as completed", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(opt != JOptionPane.YES_OPTION)
			return;
		
		int id = listOfAssignedComplaints.get(index).getComplaintPrimaryKey();
		
		technicianDashboardController.sendObjectToModel("MARK_AS_COMPLETED");
		technicianDashboardController.sendObjectToModel(id);
	}
	
	private void sendResponseToComplaint(int index) {
		
		if (!validateSendingResponse()) {
			JOptionPane.showMessageDialog(null, "Null response cannot be sent...", "Not Sent", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ReportResponseHibernate reportResponseHibernateObj = new ReportResponseHibernate();
		
		int complaintPK = listOfAssignedComplaints.get(index).getComplaintPrimaryKey();
		int customerPK = listOfAssignedComplaints.get(index).getCustomerPrimaryKey();
		
		Date date = (Date) datePicker.getModel().getValue();

		String response = textAreaResponse.getText() + "\n\n";
		response += "Proposed Vist Date: " + dateOnlyFormatter.format(date);
		
		reportResponseHibernateObj.setComplaintId(complaintPK);
		reportResponseHibernateObj.setCustomerId(customerPK);
		reportResponseHibernateObj.setResponse(response);
		
		
		boolean checked = chckbxLiveChat.isSelected();
		String liveChat = "NO";
		
		if (checked) 
			liveChat = "YES";
		
		AssignedTechnicianHibernate assignedTechnicianHibernateObj = new AssignedTechnicianHibernate();
		assignedTechnicianHibernateObj.setId(listOfAssignedComplaints.get(index).getId());
		assignedTechnicianHibernateObj.setLiveChat(liveChat);
		
		technicianDashboardController.sendObjectToModel("TECHNICIAN_RESPONSE");
		technicianDashboardController.sendObjectToModel(reportResponseHibernateObj);
		technicianDashboardController.sendObjectToModel(assignedTechnicianHibernateObj);
		
		JOptionPane.showMessageDialog(this, "Response successfully sent...", "Sent", JOptionPane.INFORMATION_MESSAGE);
		btnSubmitResponse.setEnabled(false);
	}
	
	private boolean validateSendingResponse() {
		int row = tableAssignedComplaints.getSelectedRow();
		
		if (row < 0 || textAreaResponse.getText().isEmpty() || 
				datePicker.getModel().getValue() == null) {
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void displayMessageOnView(String message) {
		if (message == null)
			return;
		
		String[] messageParts = message.split("\\~");
		
		String sender = messageParts[0].trim();
		
		if (!recipientUserId.equalsIgnoreCase(sender)) {
			int opt = JOptionPane.showConfirmDialog(null, "A new user wants to chat with you.\nAccept?",
					"New Chat", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (opt != JOptionPane.YES_OPTION)
				return;
			
			textPaneLiveChat.setText("");
			textAreaChat.setText("");
			tabbedPane.setSelectedIndex(1);
		}

		recipientUserId = messageParts[0].trim();
		String body = messageParts[1].trim();
		
		writeToLiveChatPane("RECEIVE", body);

	}
	
	private void sendMessage() {
		
		if (textAreaChat.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Empty message cannot be sent", "Message", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (recipientUserId.equalsIgnoreCase("NULL")) {
			JOptionPane.showMessageDialog(null, "There is no user to send message to", "Message", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String message = textAreaChat.getText();
		writeToLiveChatPane("SEND", message);
		
		textAreaChat.setText("");
		
		technicianDashboardController.sendObjectToModel("SEND_MESSAGE");
		technicianDashboardController.sendObjectToModel(recipientUserId);
		technicianDashboardController.sendObjectToModel(message);
	}
	
	
	private void writeToLiveChatPane(String operation, String message) {
		// ------------------- break line after a certain amount of characters
		StringBuilder sb = new StringBuilder(message + "\n\n");

		int i = 0;
		while ((i = sb.indexOf(" ", i + 30)) != -1) {
			sb.replace(i, i + 1, "\n");
		}
		// --------------------------------------------

		// color background and align text in text pane
		StyleConstants.setForeground(style, Color.white);

		// place cursor at end of textpane
		int len = textPaneLiveChat.getDocument().getLength();
		textPaneLiveChat.setCaretPosition(len);
		
		if (operation.equalsIgnoreCase("RECEIVE")) {
			StyleConstants.setBackground(style, Color.blue);
			StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
		} else {
			StyleConstants.setBackground(style, Color.red);
			StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_RIGHT);
		}
		
		textPaneLiveChat.setParagraphAttributes(attributes, true);
		
		// move scroll bar to end (bottom)
		JScrollBar vertical = scrollPaneLiveChat.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		
		try {
			styledDoc.insertString(styledDoc.getLength(), sb.toString(), style);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
}