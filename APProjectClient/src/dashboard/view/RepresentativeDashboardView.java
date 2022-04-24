package dashboard.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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

import dashboard.controller.RepresentativeDashboardController;
import dashboard.view.dialog.RepresentativeAssignTechnicianDialog;
import dashboard.view.dialog.RepresentativeViewCustomerDetailsDialog;
import hibernate.model.AssignedTechnicianHibernate;
import hibernate.model.ComplaintCategoriesHibernate;
import hibernate.model.CustomerHibernate;
import hibernate.model.CustomerLoginHibernate;
import hibernate.model.EmployeeHibernate;
import hibernate.model.EmployeeLoginHibernate;
import hibernate.model.PaymentHistoryHibernate;
import hibernate.model.PaymentListHibernate;
import hibernate.model.ProductServicesHibernate;
import hibernate.model.ReportIssueHibernate;
import hibernate.model.ReportResponseHibernate;
import internal.frames.view.*;
import object.classes.AssignedComplaintsIdObj;
import object.classes.ComplaintsForRepresentativeObj;
import object.classes.ListOfComplaintsForRepresentative;
import object.classes.RepresentativeHomeTabInfoObj;
import object.classes.RepresentativeResponsesObj;
import object.classes.TechniciansObj;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class RepresentativeDashboardView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RepresentativeDashboardController representativeDashboardController;
	private JPanel panelMenu;
	private JButton btnHome;
	private JButton btnComplaints;
	private JButton btnChat;
	private JButton btnTables;
	private JButton btnLogout;
	private JTabbedPane tabbedPane;
	private JPanel panelHomeTab;
	private JPanel panelComplaintsTab;
	private JPanel panelTablesTab;
	private JPanel panelChatTab;
	private JLabel lblLoggedInAs;
	private JLabel lblUserName;
	private JDesktopPane desktopPane;
	private JTextField txtCustomer;
	private JTextField txtComplaintCategory;
	private JTextField txtProductService;
	private JLabel lblComplaints;
	private JTable tableComplaints;
	private JMenuBar menuBar;
	private JMenu menuView;
	private JMenuItem menuItemAssignedTechnicianTable;
	private JMenuItem menuItemComplaintCategoryTable;
	private JMenuItem menuItemCustomerTable;
	private JMenuItem menuItemCustomerLoginTable;
	private JMenuItem menuItemEmployeeTable;
	private JMenuItem menuItemEmployeeLoginTable;
	private JMenuItem menuItemPaymentListTable;
	private JMenuItem menuItemPaymentHistoryTable;
	private JMenuItem menuItemProductServicesTable;
	private JMenuItem menuItemReportedIssueTable;
	private JMenuItem menuItemResponseTable;
	private JLabel lblFilterIcon;
	private JScrollPane scrollPaneTextAreaDescription;
	private JScrollPane scrollPaneTextAreaResponse;
	private JScrollPane scrollPaneComplaintsTable;
	private JComboBox<Object> comboBoxFilterProductService;
	private JLabel lblCustomer;
	private JLabel lblProductService;
	private JLabel lblComplaintCategory;
	private JLabel lblDescription;
	private JLabel lblResponse;
	private JTextArea textAreaDescription;
	private JTextArea textAreaResponse;
	private JButton btnViewDetails;
	private JButton btnAssignTechnician;
	private JButton btnMarkAsCompleted;
	private JButton btnSubmitResponse;
//	private Border border;
	
	private JButton btnSend;
	private JTextArea textAreaChat;
	private JTextPane textPaneLiveChat;
	private JScrollPane scrollPaneLiveChat;
	private JScrollPane scrollPane;
	private JComboBox<Object> comboBoxFilterCategory;
	private JLabel lblServicesFilter;
	private JLabel lblComplaintsFilter;
	private JLabel lblOutstandingIcon;
	private JLabel lblResolvedIcon;
	private JLabel lblAmtResolved;
	private JLabel lblAmtOutstanding;
	private JPanel panelAroundComplaintsCount;
	private JTable tableServices;
	private JScrollPane scrollPaneServicesTable;
	private ArrayList<Integer> comboBoxProductServiceIndexId;
	private ArrayList<Integer> comboBoxComplaintCategoryIndexId;
	private ArrayList<ComplaintsForRepresentativeObj> complaintsForRepresentativeObjArrayList;
	private ArrayList<TechniciansObj> techniciansObjArrayList;
	private ArrayList<ComplaintCategoriesHibernate> complaintCategoriesObjArrayList;
	private ArrayList<ProductServicesHibernate> productServicesObjArrayList;
	private ArrayList<AssignedComplaintsIdObj> assignedComplaintsIdObjArrayList;
	private ArrayList<RepresentativeResponsesObj> representativeResponsesObjArrayList;
	private JButton btnNewResponse;
	private AssignedTechnicianTableInternalView assignedTechnicianInternalView;
	private ComplaintCategoriesTableInternalView complaintCategoriesInternalView;
	private CustomerLoginTableInternalView customerLoginInternalView;
	private CustomerTableInternalView customerInternalView;
	private EmployeeLoginTableInternalView employeeLoginInternalView;
	private EmployeeTableInternalView employeeInternalView;
	private PaymentHistoryTableInternalView paymentHistoryInternalView;
	private PaymentListTableInternalView paymentListInternalView;
	private ProductServicesTableInternalView productServicesInternalView;
	private ReportedIssueTableInternalView reportedIssueInternalView;
	private ResponseTableInternalView responseInternalView;
	
	private String recipientUserId;
	
	private StyledDocument styledDoc;
	private Style style;
	private SimpleAttributeSet attributes;
	
	/**
	 * Create the frame.
	 */
	public RepresentativeDashboardView(RepresentativeDashboardController obj) {
		this.representativeDashboardController = obj;
		initializeComponents();
		addMenuItemsToMenu();
		addMnemonics();
		addToolTips();
		addComponentsToPanel();
		addComponentsToWindow();
		setWindowProperties();
		registerMenuItemActionListeners();
		registerActionListeners();
		
		TimerTask task = new TimerTask() {
	        public void run() {
	            lblUserName.setText(representativeDashboardController.getUsernameFromModel());
	            representativeDashboardController.sendQueryToModel("REPRESENTATIVE_HOME");
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 1000L;
	    timer.schedule(task, delay);
	}
	
	private void initializeComponents() {
		this.setTitle("Representative Dashboard");
		
		recipientUserId = "NULL";
		
		assignedTechnicianInternalView = null;
		complaintCategoriesInternalView = null;
		customerLoginInternalView = null;
		customerInternalView = null;
		employeeLoginInternalView = null;
		employeeInternalView = null;
		paymentHistoryInternalView = null;
		paymentListInternalView = null;
		productServicesInternalView = null;
		reportedIssueInternalView = null;
		responseInternalView = null;
		
		comboBoxProductServiceIndexId = new ArrayList<>();
		comboBoxComplaintCategoryIndexId = new ArrayList<>();
		
		complaintsForRepresentativeObjArrayList = null;
		representativeResponsesObjArrayList = null;
		techniciansObjArrayList = null;
		productServicesObjArrayList = null;
		complaintCategoriesObjArrayList = null;
		assignedComplaintsIdObjArrayList = null;
		
		panelMenu = new JPanel();
		panelMenu.setFocusable(false);
		panelMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMenu.setBounds(-11, -12, 215, 696);
		panelMenu.setLayout(null);
		
//		border = BorderFactory.createLineBorder(Color.BLACK);
		
		btnHome = new JButton("HOME");
		btnHome.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-home-25.png")));
		btnHome.setFocusable(false);
		btnHome.setBounds(12, 230, 202, 55);
//		btnHome.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnSend = new JButton("Send");
		btnSend.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-send-30.png")));
		btnSend.setBounds(355, 477, 180, 40);
		
		btnComplaints = new JButton("COMPLAINTS");
		btnComplaints.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-strike-30.png")));
		btnComplaints.setFocusable(false);
		btnComplaints.setBounds(12, 285, 202, 55);
//		btnAccountStatus.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnChat = new JButton("CHAT");
		btnChat.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-chat-30.png")));
		btnChat.setFocusable(false);
		btnChat.setBounds(12, 395, 202, 55);
//		btnPaymentHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnTables = new JButton("TABLES");
		btnTables.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-table-30.png")));
		btnTables.setFocusable(false);
		btnTables.setBounds(12, 340, 202, 55);
//		btnReportHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnLogout = new JButton("LOGOUT");
		btnLogout.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-logout-rounded-left-30.png")));
		btnLogout.setFocusable(false);
		btnLogout.setBounds(12, 450, 202, 55);
//		btnComplaintHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFocusable(false);
		tabbedPane.setBounds(214, 11, 880, 649);
		
		panelHomeTab = new JPanel();
		panelHomeTab.setFocusable(false);
		panelHomeTab.setLayout(null);
		
		panelComplaintsTab = new JPanel();
		panelComplaintsTab.setFocusable(false);
		panelComplaintsTab.setLayout(null);
		
		lblComplaints = new JLabel("Complaints:");
		lblComplaints.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblComplaints.setFocusable(false);
		lblComplaints.setBounds(19, 33, 115, 22);
		
		txtCustomer = new JTextField();
		txtCustomer.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		txtCustomer.setFocusable(false);
		txtCustomer.setEditable(false);
		txtCustomer.setColumns(10);
		txtCustomer.setBounds(390, 75, 300, 25);
		
		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblCustomer.setFocusable(false);
		lblCustomer.setBounds(224, 76, 160, 22);
		
		lblProductService = new JLabel("Product Service:");
		lblProductService.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblProductService.setFocusable(false);
		lblProductService.setBounds(224, 103, 160, 22);
		
		lblComplaintCategory = new JLabel("Complaint Category:");
		lblComplaintCategory.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblComplaintCategory.setFocusable(false);
		lblComplaintCategory.setBounds(224, 130, 160, 22);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblDescription.setFocusable(false);
		lblDescription.setBounds(224, 157, 160, 22);
		
		scrollPaneTextAreaDescription = new JScrollPane();
		scrollPaneTextAreaDescription.setBounds(391, 159, 298, 168);
		
		textAreaDescription = new JTextArea();
		scrollPaneTextAreaDescription.setViewportView(textAreaDescription);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		textAreaDescription.setFocusable(false);
		textAreaDescription.setEditable(false);
		
		txtComplaintCategory = new JTextField();
		txtComplaintCategory.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		txtComplaintCategory.setFocusable(false);
		txtComplaintCategory.setEditable(false);
		txtComplaintCategory.setColumns(10);
		txtComplaintCategory.setBounds(390, 129, 300, 25);
		
		txtProductService = new JTextField();
		txtProductService.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		txtProductService.setFocusable(false);
		txtProductService.setEditable(false);
		txtProductService.setColumns(10);
		txtProductService.setBounds(390, 102, 300, 25);
		
		btnViewDetails = new JButton("View Details");
		
		btnViewDetails.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-view-20.png")));
		btnViewDetails.setFocusable(false);
		btnViewDetails.setBounds(700, 74, 165, 30);
		
		btnMarkAsCompleted = new JButton("Mark as completed");
		btnMarkAsCompleted.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-check-25.png")));
		btnMarkAsCompleted.setFocusable(false);
		btnMarkAsCompleted.setBounds(700, 297, 165, 30);
		
		lblResponse = new JLabel("Response:");
		lblResponse.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblResponse.setFocusable(false);
		lblResponse.setBounds(224, 362, 160, 22);
		
		scrollPaneTextAreaResponse = new JScrollPane();
		scrollPaneTextAreaResponse.setBounds(391, 365, 298, 168);
		
		textAreaResponse = new JTextArea();
		scrollPaneTextAreaResponse.setViewportView(textAreaResponse);
		textAreaResponse.setWrapStyleWord(true);
		textAreaResponse.setLineWrap(true);
		textAreaResponse.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		
		btnSubmitResponse = new JButton("Submit Response");
		btnSubmitResponse.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-submit-document-25.png")));
		btnSubmitResponse.setFocusable(false);
		btnSubmitResponse.setBounds(510, 564, 180, 35);
		
		scrollPaneComplaintsTable = new JScrollPane();
		scrollPaneComplaintsTable.setBounds(19, 75, 191, 533);
		
		tableComplaints = new JTable();
		tableComplaints.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Complaints", "service_id", "category_id", "index"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPaneComplaintsTable.setViewportView(tableComplaints);
		
		// hide few table columns------------------------------------
		tableComplaints.getColumnModel().getColumn(1).setMinWidth(0);
		tableComplaints.getColumnModel().getColumn(1).setMaxWidth(0);
		tableComplaints.getColumnModel().getColumn(1).setWidth(0);
		
		tableComplaints.getColumnModel().getColumn(2).setMinWidth(0);
		tableComplaints.getColumnModel().getColumn(2).setMaxWidth(0);
		tableComplaints.getColumnModel().getColumn(2).setWidth(0);
		
		tableComplaints.getColumnModel().getColumn(3).setMinWidth(0);
		tableComplaints.getColumnModel().getColumn(3).setMaxWidth(0);
		tableComplaints.getColumnModel().getColumn(3).setWidth(0);
		//------------------------------------------------------------
		
		
		lblFilterIcon = new JLabel("");
		lblFilterIcon.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-filter-2-20.png")));
		lblFilterIcon.setBounds(136, 33, 20, 20);
		
		comboBoxFilterProductService = new JComboBox<Object>();
		comboBoxFilterProductService.setBounds(166, 33, 224, 22);
		
		btnAssignTechnician = new JButton("Assign Technician");
		btnAssignTechnician.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-note-taking-25.png")));
		btnAssignTechnician.setFocusable(false);
		btnAssignTechnician.setBounds(700, 185, 165, 30);
		
		panelTablesTab = new JPanel();
		panelTablesTab.setFocusable(false);
		panelTablesTab.setLayout(null);
		panelChatTab = new JPanel();
		panelChatTab.setFocusable(false);
		panelChatTab.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(5, 5, 865, 610);
		desktopPane.setLayout(null);
		
		lblUserName = new JLabel("[full name]");
		lblUserName.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		lblUserName.setFocusable(false);
		lblUserName.setBounds(15, 75, 199, 22);
		
		lblLoggedInAs = new JLabel("Logged in as:");
		lblLoggedInAs.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblLoggedInAs.setFocusable(false);
		lblLoggedInAs.setBounds(15, 52, 138, 22);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(12, 10, 202, 22);
		
		menuView = new JMenu("View");
		
		menuItemAssignedTechnicianTable = new JMenuItem("Assigned Technician Table");
		menuItemComplaintCategoryTable = new JMenuItem("Complaint Category Table");
		menuItemCustomerTable = new JMenuItem("Customer Table");
		menuItemCustomerLoginTable = new JMenuItem("Customer Login Table");
		menuItemEmployeeTable = new JMenuItem("Employee Table");
		menuItemEmployeeLoginTable = new JMenuItem("Employee Login Table");
		menuItemPaymentListTable = new JMenuItem("Payment List Table");
		menuItemPaymentHistoryTable = new JMenuItem("Payment History Table");
		menuItemProductServicesTable = new JMenuItem("Product Services Table");
		menuItemReportedIssueTable = new JMenuItem("Reported Issue Table");
		menuItemResponseTable = new JMenuItem("Response Table");
		
		comboBoxFilterCategory = new JComboBox<Object>();
		comboBoxFilterCategory.setBounds(407, 33, 224, 22);
		
		lblServicesFilter = new JLabel("Services");
		lblServicesFilter.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		lblServicesFilter.setBounds(166, 8, 90, 14);
		
		lblComplaintsFilter = new JLabel("Complaints");
		lblComplaintsFilter.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		lblComplaintsFilter.setBounds(407, 8, 90, 14);
		
		panelAroundComplaintsCount = new JPanel();
		panelAroundComplaintsCount.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Complaints", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAroundComplaintsCount.setBounds(30, 30, 215, 115);
		panelAroundComplaintsCount.setLayout(null);
		
		lblResolvedIcon = new JLabel("Resolved:");
		lblResolvedIcon.setBounds(17, 30, 140, 25);
		lblResolvedIcon.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblResolvedIcon.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-maintenance-25.png")));
		
		lblOutstandingIcon = new JLabel("Outstanding:");
		lblOutstandingIcon.setBounds(17, 66, 140, 25);
		lblOutstandingIcon.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblOutstandingIcon.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-bug-25.png")));
		
		lblAmtResolved = new JLabel("0");
		lblAmtResolved.setBounds(157, 31, 50, 20);
		lblAmtResolved.setFont(new Font("Ebrima", Font.BOLD, 20));
		
		scrollPaneServicesTable = new JScrollPane();
		scrollPaneServicesTable.setBounds(32, 190, 600, 350);
		
		tableServices = new JTable();
		tableServices.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Services", "Resolved", "Outstanding"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPaneServicesTable.setViewportView(tableServices);
		
		lblAmtOutstanding = new JLabel("0");
		lblAmtOutstanding.setBounds(157, 67, 50, 20);
		lblAmtOutstanding.setFont(new Font("Ebrima", Font.BOLD, 20));
		
		btnNewResponse = new JButton("New");
		btnNewResponse.setFocusable(false);
		btnNewResponse.setIcon(new ImageIcon(RepresentativeDashboardView.class.getResource("/Images/icons8-plus-25.png")));
		btnNewResponse.setBounds(390, 564, 110, 35);
		
		scrollPaneLiveChat = new JScrollPane();
		scrollPaneLiveChat.setBounds(45, 45, 490, 340);
		
		textPaneLiveChat = new JTextPane();
		textPaneLiveChat.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 16));
		scrollPaneLiveChat.setViewportView(textPaneLiveChat);
//		textPaneLiveChat.setBorder(BorderFactory.createCompoundBorder(border,
//	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textPaneLiveChat.setEditable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(45, 396, 490, 70);
		
		textAreaChat = new JTextArea();
		textAreaChat.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		scrollPane.setViewportView(textAreaChat);
		textAreaChat.setLineWrap(true);
		textAreaChat.setWrapStyleWord(true);
//		txtChat.setBorder(BorderFactory.createCompoundBorder(border,
//	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		// to allow styling in text pane
		styledDoc = textPaneLiveChat.getStyledDocument();
		style = textPaneLiveChat.addStyle("", null);
		attributes = new SimpleAttributeSet();
	
	}
	
	
	private void addComponentsToPanel() {
		panelMenu.add(btnHome);
		panelMenu.add(btnComplaints);
		panelMenu.add(btnChat);
		panelMenu.add(btnTables);
		panelMenu.add(btnLogout);
		panelMenu.add(lblUserName);
		panelMenu.add(lblLoggedInAs);
		panelMenu.add(menuBar);
		
		tabbedPane.addTab("HOME", null, panelHomeTab, null);
		tabbedPane.addTab("COMPLAINTS", null, panelComplaintsTab, null);
		tabbedPane.addTab("TABLES", null, panelTablesTab, null);
		tabbedPane.addTab("CHAT", null, panelChatTab, null);
		
		panelAroundComplaintsCount.add(lblAmtOutstanding);
		panelAroundComplaintsCount.add(lblAmtResolved);
		panelAroundComplaintsCount.add(lblOutstandingIcon);
		panelAroundComplaintsCount.add(lblResolvedIcon);
		
		panelHomeTab.add(panelAroundComplaintsCount);
		panelHomeTab.add(scrollPaneServicesTable);
		
		panelTablesTab.add(desktopPane);
		
		panelComplaintsTab.add(lblComplaints);
		panelComplaintsTab.add(txtCustomer);
		panelComplaintsTab.add(lblCustomer);
		panelComplaintsTab.add(lblProductService);
		panelComplaintsTab.add(lblComplaintCategory);
		panelComplaintsTab.add(lblDescription);
		panelComplaintsTab.add(scrollPaneTextAreaDescription);
		panelComplaintsTab.add(txtComplaintCategory);
		panelComplaintsTab.add(txtProductService);
		panelComplaintsTab.add(btnViewDetails);
		panelComplaintsTab.add(btnMarkAsCompleted);
		panelComplaintsTab.add(lblResponse);
		panelComplaintsTab.add(scrollPaneTextAreaResponse);
		panelComplaintsTab.add(btnSubmitResponse);
		panelComplaintsTab.add(scrollPaneComplaintsTable);
		panelComplaintsTab.add(lblFilterIcon);
		panelComplaintsTab.add(comboBoxFilterProductService);
		panelComplaintsTab.add(comboBoxFilterCategory);
		panelComplaintsTab.add(lblServicesFilter);
		panelComplaintsTab.add(lblComplaintsFilter);
		panelComplaintsTab.add(btnAssignTechnician);
		panelComplaintsTab.add(btnNewResponse);
		
		panelChatTab.add(scrollPaneLiveChat);
		panelChatTab.add(scrollPane);
		panelChatTab.add(btnSend);
		
	}
	
	private void addMnemonics() {
		menuView.setMnemonic(KeyEvent.VK_V);
		menuItemAssignedTechnicianTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.SHIFT_DOWN_MASK));
		menuItemComplaintCategoryTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.SHIFT_DOWN_MASK));
		menuItemCustomerTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.SHIFT_DOWN_MASK));
		menuItemCustomerLoginTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.SHIFT_DOWN_MASK));
		menuItemEmployeeTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.SHIFT_DOWN_MASK));
		menuItemEmployeeLoginTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, KeyEvent.SHIFT_DOWN_MASK));
		menuItemPaymentListTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, KeyEvent.SHIFT_DOWN_MASK));
		menuItemPaymentHistoryTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, KeyEvent.SHIFT_DOWN_MASK));
		menuItemProductServicesTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.SHIFT_DOWN_MASK));
		menuItemReportedIssueTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, KeyEvent.SHIFT_DOWN_MASK));
		menuItemResponseTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.SHIFT_DOWN_MASK));
		
		btnHome.setMnemonic(KeyEvent.VK_H);
		btnComplaints.setMnemonic(KeyEvent.VK_C);
		btnTables.setMnemonic(KeyEvent.VK_T);
		btnChat.setMnemonic(KeyEvent.VK_A);
		btnLogout.setMnemonic(KeyEvent.VK_L);
	}
	
	private void addToolTips() {
		menuView.setToolTipText("View tables to open");
		menuItemAssignedTechnicianTable.setToolTipText("Opens assigned technician table");
		menuItemComplaintCategoryTable.setToolTipText("Opens complaint category table");
		menuItemCustomerTable.setToolTipText("Opens customer table");
		menuItemCustomerLoginTable.setToolTipText("Opens customer login table");
		menuItemEmployeeTable.setToolTipText("Opens employee table");
		menuItemEmployeeLoginTable.setToolTipText("Opens employee login table");
		menuItemPaymentListTable.setToolTipText("Opens payment list table");
		menuItemPaymentHistoryTable.setToolTipText("Opens payment history table");
		menuItemProductServicesTable.setToolTipText("Opens product services table");
		menuItemReportedIssueTable.setToolTipText("Opens reported issue table");
		menuItemResponseTable.setToolTipText("Opens response table");
	}
	
	private void addMenuItemsToMenu() {
		menuBar.add(menuView);
		
		menuView.add(menuItemAssignedTechnicianTable);
		menuView.add(menuItemComplaintCategoryTable);
		menuView.add(menuItemCustomerTable);
		menuView.add(menuItemCustomerLoginTable);
		menuView.add(menuItemEmployeeTable);
		menuView.add(menuItemEmployeeLoginTable);
		menuView.add(menuItemPaymentListTable);
		menuView.add(menuItemPaymentHistoryTable);
		menuView.add(menuItemProductServicesTable);
		menuView.add(menuItemReportedIssueTable);
		menuView.add(menuItemResponseTable);
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
	
	private void registerMenuItemActionListeners() {
		menuItemAssignedTechnicianTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(assignedTechnicianInternalView = new AssignedTechnicianTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemComplaintCategoryTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(complaintCategoriesInternalView = new ComplaintCategoriesTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemCustomerTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(customerInternalView = new CustomerTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemCustomerLoginTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(customerLoginInternalView = new CustomerLoginTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemEmployeeTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(employeeInternalView = new EmployeeTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemEmployeeLoginTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(employeeLoginInternalView = new EmployeeLoginTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemPaymentListTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(paymentListInternalView = new PaymentListTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemPaymentHistoryTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(paymentHistoryInternalView = new PaymentHistoryTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemProductServicesTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(productServicesInternalView = new ProductServicesTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemReportedIssueTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(reportedIssueInternalView = new ReportedIssueTableInternalView(representativeDashboardController));
			}
		});
		
		menuItemResponseTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				desktopPane.add(responseInternalView = new ResponseTableInternalView(representativeDashboardController));
			}
		});
	}
	
	private void registerActionListeners() {
		tableComplaints.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tableComplaints.getSelectedRow();
				int index = Integer.parseInt(tableComplaints.getModel().getValueAt(row, 3).toString());
				populateComplaintTabFieldsAfterTableClick(index);
			}
		});
		
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				representativeDashboardController.sendQueryToModel("REPRESENTATIVE_HOME");
			}
		});
		
		btnComplaints.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearComplaintComponents();
				tabbedPane.setSelectedIndex(1);
				representativeDashboardController.sendQueryToModel("REPRESENTATIVE_COMPLAINTS");
			}
		});
		
		btnTables.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		
		btnChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableComplaints.getSelectedRow();
				
				if(row < 0)
					return;
				
				int index = Integer.parseInt(tableComplaints.getModel().getValueAt(row, 3).toString());
				
				RepresentativeViewCustomerDetailsDialog dialog = new RepresentativeViewCustomerDetailsDialog(complaintsForRepresentativeObjArrayList.get(index));
				dialog.setVisible(true);
			}
		});
		
		btnAssignTechnician.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableComplaints.getSelectedRow();
				
				if(row < 0)
					return;
				
				int index = Integer.parseInt(tableComplaints.getModel().getValueAt(row, 3).toString());
				
				RepresentativeAssignTechnicianDialog dialog = new RepresentativeAssignTechnicianDialog(techniciansObjArrayList);
				dialog.setVisible(true);
				boolean assigned = dialog.isAssigned(); // if technician is to be assigned
				int technicianId = dialog.getTechnicianId(); // get selected technician id
				dialog.dispose();
				
				if (assigned) {
					assignTechnician(technicianId, index);
				}
			}
		});
		
		btnMarkAsCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableComplaints.getSelectedRow();
				
				if(row < 0)
					return;
				
				int index = Integer.parseInt(tableComplaints.getModel().getValueAt(row, 3).toString());
				sendCompletedComplaintId(index);
			}
		});
		
		btnNewResponse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newResponse();
			}
		});
		
		btnSubmitResponse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableComplaints.getSelectedRow();
				
				if(row < 0)
					return;
				
				int index = Integer.parseInt(tableComplaints.getModel().getValueAt(row, 3).toString());
				sendResponseToComplaint(index);
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
			 representativeDashboardController.returnToLoginScreen();
		 }  
	}
	
	public void populateHomeTab(RepresentativeHomeTabInfoObj representativeHomeTabInfoObj) {
				
		int amtResolved = representativeHomeTabInfoObj.getResolvedComplaintsArrayList().size();
		int amtOutstanding = representativeHomeTabInfoObj.getOutstandingComplaintsArrayList().size();
		
		lblAmtResolved.setText(String.valueOf(amtResolved));
		lblAmtOutstanding.setText(String.valueOf(amtOutstanding));
		
		DefaultTableModel tableModel = (DefaultTableModel) tableServices.getModel();
		tableModel.setRowCount(0); // clear table

		for (ProductServicesHibernate value : representativeHomeTabInfoObj.getProductServicesArrayList()) {
			int resolvedCount = 0;
			int outstandingCount = 0;
			
			for (ReportIssueHibernate resolved : representativeHomeTabInfoObj.getResolvedComplaintsArrayList()) {
				if (value.getId() == resolved.getProductServiceId()) {
					resolvedCount++;
				}
			}
			
			for (ReportIssueHibernate outstanding : representativeHomeTabInfoObj.getOutstandingComplaintsArrayList()) {
				if (value.getId() == outstanding.getProductServiceId()) {
					outstandingCount++;
				}
			}
			
			tableModel.addRow(new Object[] { value.getProductName(), resolvedCount, outstandingCount });
		}
	}
	
	public void populateComplaintTab(ListOfComplaintsForRepresentative listOfComplaintsForRepresentative) {
		comboBoxFilterProductService.removeAllItems();
		comboBoxFilterCategory.removeAllItems();
		
		comboBoxProductServiceIndexId.clear();
		comboBoxComplaintCategoryIndexId.clear();
		
		productServicesObjArrayList = listOfComplaintsForRepresentative.getProductServicesArrayList();
		complaintCategoriesObjArrayList = listOfComplaintsForRepresentative.getComplaintCategoriesArrayList();
		techniciansObjArrayList = listOfComplaintsForRepresentative.getTechnicianArrayList();
		complaintsForRepresentativeObjArrayList = listOfComplaintsForRepresentative.getCompliantsForRepresentativeArrayList();
		representativeResponsesObjArrayList = listOfComplaintsForRepresentative.getRepresentativeResponsesObjArrayList();
		assignedComplaintsIdObjArrayList = listOfComplaintsForRepresentative.getAssignedComplaintsIdObjArrayList();
		
		comboBoxFilterProductService.addItem("");
		comboBoxProductServiceIndexId.add(-1);
		for (ProductServicesHibernate value : productServicesObjArrayList) {
			comboBoxProductServiceIndexId.add(value.getId());
			comboBoxFilterProductService.addItem(value.getProductName());
		}
		
		comboBoxFilterCategory.addItem("");
		comboBoxComplaintCategoryIndexId.add(-1);
		for (ComplaintCategoriesHibernate value : complaintCategoriesObjArrayList) {
			comboBoxComplaintCategoryIndexId.add(value.getId());
			comboBoxFilterCategory.addItem(value.getCategoryName());
		}
		
		DefaultTableModel tableModel = (DefaultTableModel) tableComplaints.getModel();
		tableModel.setRowCount(0); // clear table

		int indexer = 0;
		for (ComplaintsForRepresentativeObj value : complaintsForRepresentativeObjArrayList) {
			tableModel.addRow(new Object[] { value.getCategoryName(), value.getProductServiceId(), value.getComplaintCategoryId(), indexer });
			indexer++;
		}  
		
	}
	
	private void populateComplaintTabFieldsAfterTableClick(int index) {
		
		clearComplaintComponents();
		
		ComplaintsForRepresentativeObj obj = complaintsForRepresentativeObjArrayList.get(index);
		
		txtCustomer.setText(obj.getFullName());
		txtProductService.setText(obj.getProductName());
		txtComplaintCategory.setText(obj.getCategoryName());
		textAreaDescription.setText(obj.getComplaint());
		
		populateResponse(obj.getComplaintPrimaryKey());
		checkIfAlreadyAssigned(obj.getComplaintPrimaryKey());
		
	}
	
	private void checkIfAlreadyAssigned(int complaintPK) {
		btnAssignTechnician.setEnabled(true);
		for (AssignedComplaintsIdObj value : assignedComplaintsIdObjArrayList) {
			if (value.getAssingedComplaintId() == complaintPK) {
				btnAssignTechnician.setEnabled(false);
				break;
			}
		}
	}
	
	private void populateResponse(int complaintPK) {
		int size = representativeResponsesObjArrayList.size() - 1;
		boolean responded = false;
		String response = "";
		
		// iterate through response history from end to beginning
		for (; size > -1; size--) {
			RepresentativeResponsesObj obj = representativeResponsesObjArrayList.get(size);
			if (complaintPK == obj.getComplaintIdPrimaryKey()) {
				responded = true;
				response = obj.getResponse();
				break;
			}
		}
		
		if (responded) {
			textAreaResponse.setText(response);
			textAreaResponse.setEditable(false);
			btnSubmitResponse.setEnabled(false);
			btnNewResponse.setEnabled(true);
		}
	}
	
	private void clearComplaintComponents() {
		txtCustomer.setText("");
		txtProductService.setText("");
		txtComplaintCategory.setText("");
		textAreaDescription.setText("");
		textAreaResponse.setText("");
		
		textAreaResponse.setEditable(true);
		btnSubmitResponse.setEnabled(true);
		btnNewResponse.setEnabled(false);
	}
	
	private void newResponse() {
		textAreaResponse.setText("");
		textAreaResponse.setEditable(true);
		btnSubmitResponse.setEnabled(true);
		btnNewResponse.setEnabled(false);
	}
	
	private void sendCompletedComplaintId(int index) {
		int opt = JOptionPane.showConfirmDialog(null, "Mark as completed", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(opt != JOptionPane.YES_OPTION)
			return;
		
		int id = complaintsForRepresentativeObjArrayList.get(index).getComplaintPrimaryKey();
		
		representativeDashboardController.sendObjectToModel("MARK_AS_COMPLETED");
		representativeDashboardController.sendObjectToModel(id);
	}
	
	private void sendResponseToComplaint(int index) {
		if (!validateSendingResponse()) {
			JOptionPane.showMessageDialog(null, "Null response cannot be sent...", "Not Sent", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ReportResponseHibernate reportResponseHibernateObj = new ReportResponseHibernate();
		
		int complaintPK = complaintsForRepresentativeObjArrayList.get(index).getComplaintPrimaryKey();
		int customerPK = complaintsForRepresentativeObjArrayList.get(index).getCustomerPrimaryKey();
		
		String response = textAreaResponse.getText();
		
		reportResponseHibernateObj.setComplaintId(complaintPK);
		reportResponseHibernateObj.setCustomerId(customerPK);
		reportResponseHibernateObj.setResponse(response);
		
		representativeDashboardController.sendObjectToModel("REPRESENTATIVE_RESPONSE");
		representativeDashboardController.sendObjectToModel(reportResponseHibernateObj);
		
		JOptionPane.showMessageDialog(this, "Response successfully sent...", "Sent", JOptionPane.INFORMATION_MESSAGE);
		btnSubmitResponse.setEnabled(false);
	}
	
	private boolean validateSendingResponse() {
		int row = tableComplaints.getSelectedRow();
		
		if (row < 0 || textAreaResponse.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	private void assignTechnician(int technicianId, int index) {
		int complaintPK = complaintsForRepresentativeObjArrayList.get(index).getComplaintPrimaryKey();
		
		AssignedTechnicianHibernate assignedTechnicianObj = new AssignedTechnicianHibernate();
		assignedTechnicianObj.setTechnicianId(technicianId);
		assignedTechnicianObj.setReportedIssueId(complaintPK);
		assignedTechnicianObj.setLiveChat("NO");
		
		representativeDashboardController.sendObjectToModel("ASSIGN_TECHNICIAN");
		representativeDashboardController.sendObjectToModel(assignedTechnicianObj);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public void sendListOfAssignedTechnicianToInternalFrame(
			ArrayList<AssignedTechnicianHibernate> assignedTechnicianList) {
		assignedTechnicianInternalView.populateTable(assignedTechnicianList);
	}

	public void sendListOfComplaintCategoriesToInternalFrame(
			ArrayList<ComplaintCategoriesHibernate> complaintCategoriesList) {
		complaintCategoriesInternalView.populateTable(complaintCategoriesList);
	}

	public void sendListOfCustomerLoginToInternalFrame(ArrayList<CustomerLoginHibernate> customerLoginList) {
		customerLoginInternalView.populateTable(customerLoginList);
	}

	public void sendListOfCustomerToInternalFrame(ArrayList<CustomerHibernate> customerList) {
		customerInternalView.populateTable(customerList);
	}

	public void sendListOfEmployeeLoginToInternalFrame(ArrayList<EmployeeLoginHibernate> employeeLoginList) {
		employeeLoginInternalView.populateTable(employeeLoginList);
	}

	public void sendListOfEmployeeToInternalFrame(ArrayList<EmployeeHibernate> employeeList) {
		employeeInternalView.populateTable(employeeList);
	}

	public void sendListOfPaymentHistoryToInternalFrame(ArrayList<PaymentHistoryHibernate> paymentHistoryList) {
		paymentHistoryInternalView.populateTable(paymentHistoryList);
	}

	public void sendListOfPaymentListToInternalFrame(ArrayList<PaymentListHibernate> paymentListList) {
		paymentListInternalView.populateTable(paymentListList);
	}

	public void sendListOfProductServicesToInternalFrame(ArrayList<ProductServicesHibernate> productServicesList) {
		productServicesInternalView.populateTable(productServicesList);
	}

	public void sendListOfReportedIssuesToInternalFrame(ArrayList<ReportIssueHibernate> reportedIssueList) {
		reportedIssueInternalView.populateTable(reportedIssueList);
	}

	public void sendListOfResponsesToInternalFrame(ArrayList<ReportResponseHibernate> responseList) {
		responseInternalView.populateTable(responseList);
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
			tabbedPane.setSelectedIndex(3);
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
		
		representativeDashboardController.sendObjectToModel("SEND_MESSAGE");
		representativeDashboardController.sendObjectToModel(recipientUserId);
		representativeDashboardController.sendObjectToModel(message);
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
