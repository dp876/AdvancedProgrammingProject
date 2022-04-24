package dashboard.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dashboard.controller.ClientDashboardController;
import dashboard.view.dialog.ClientViewAllResponseDialog;
import hibernate.model.ReportIssueHibernate;
import object.classes.AccountStatus;
import object.classes.ComplaintCategory;
import object.classes.ComplaintReportHistory;
import object.classes.ListOfOnlineEmployees;
import object.classes.OnlineEmployeesObj;
import object.classes.PaymentHistoryObj;
import object.classes.PaymentListObj;
import object.classes.ProductServices;
import object.classes.ReportIssueCategories;
import object.classes.ReportedIssueObj;
import object.classes.ReportedIssueResponseObj;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class ClientDashboardView extends JFrame implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientDashboardController clientDashboardController;
	private JPanel panelMenu;
	private JButton btnAccountStatus;
	private JButton btnChat;
	private JButton btnReportIssue;
	private JButton btnComplaintHistory;
	private JTabbedPane tabbedPane;
	private JPanel panelAccountStatusTab;
	private JPanel panelChatTab;
	private JPanel panelReportIssueTab;
	private JPanel panelComplaintHistoryTab;
	private JButton btnLogout;
	private JTextArea txtAreaReportIssue;
	private JLabel lblProductService;
	private JLabel lblCategory;
	private JComboBox<Object> comboBoxProductService;
	private JComboBox<Object> comboBoxCategory;
	private JLabel lblDescription;
	private JButton btnSubmitReport;
	private JTextField txtProductServiceComplaintHistory;
	private JTextField txtCategoryComplaintHistory;
	private JLabel lblProductService_1;
	private JLabel lblCategory_1;
	private JLabel lblDescription_1;
	private JLabel lblResponse;
	private JTextArea txtAreaDescriptionComplaintHistory;
	private JTextArea txtAreaResponseComplaintHistory;
	private JLabel lblComplaints;
	private Border border;
	private JLabel lblPaymentStatus;
	private JLabel lblLastResponse;
	private JLabel lblFrom;
	private JTextField txtLastResponseFromComplaintHistory;
	private JLabel lblDate;
	private JTextField txtLastResponseDateComplaintHistory;
	private JButton btnViewAllResponses;
	private JLabel lblAmountDue;
	private JLabel lblDueDate;
	private JTextField txtPaymentStatus;
	private JTextField txtAmountDue;
	private JTextField txtDueDate;
	private JLabel lblPaymentHistory;
	private JLabel lblAvailableForLive;
	private JButton btnSend;
	private JTextArea txtChat;
	private JTextPane textPaneLiveChat;
	private JScrollPane scrollPaneChatArea;
	private JScrollPane scrollPaneLiveChat;
	
	private ArrayList<Integer> comboBoxProductServiceIndexId = new ArrayList<>();
	private ArrayList<Integer> comboBoxCategoryIndexId = new ArrayList<>();
	private JTable tableComplaintHistory;
	private JScrollPane scrollPaneComplaintTable;
	private JTable tablePaymentHistory;
	private JScrollPane scrollPanePaymentHistory;
	
	private ArrayList<ReportedIssueObj> reportedIssueList;
	private ArrayList<ReportedIssueResponseObj> reportedIssueResponseList;
	private JLabel lblLoggedInAs;
	private JLabel lblUserName;
//	private boolean showTabsHeader;
	private JTable tableOnlineEmployees;
	private JScrollPane scrollPaneTablOnlineEmployees;
	
	private StyledDocument styledDoc;
	private Style style;
	private SimpleAttributeSet attributes;
	private JButton btnClearLiveChatPane;


	/**
	 * Create the frame.
	 */
	public ClientDashboardView(ClientDashboardController obj) {
		getContentPane().setFocusable(false);
		this.clientDashboardController = obj;
		initializeComponents();
		addMnemonics();
		addComponentsToPanel();
		addComponentsToWindow();
		setWindowProperties();
		registerActionListeners();
		
		
		TimerTask task = new TimerTask() {
	        public void run() {
	            lblUserName.setText(clientDashboardController.getUsernameFromModel());
	            clientDashboardController.sendQueryToModel("ACCOUNT_STATUS");
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 1000L;
	    timer.schedule(task, delay);
		
	}
	
	private void initializeComponents() {
//		showTabsHeader = false;
		
		this.setTitle("Client Dashboard");
		panelMenu = new JPanel();
		panelMenu.setFocusable(false);
		panelMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMenu.setBounds(-11, -12, 215, 696);
		panelMenu.setLayout(null);
		
		border = BorderFactory.createLineBorder(Color.BLACK);
//		btnHome.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnAccountStatus = new JButton("ACCOUNT STATUS");
		btnAccountStatus.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-business-report-30.png")));
		btnAccountStatus.setFocusable(false);
		btnAccountStatus.setBounds(12, 241, 202, 55);
//		btnAccountStatus.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnChat = new JButton("CHAT");
		btnChat.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-chat-30.png")));
		btnChat.setFocusable(false);
		btnChat.setBounds(12, 296, 202, 55);
//		btnPaymentHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnReportIssue = new JButton("REPORT ISSUE");
		btnReportIssue.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-solve-30.png")));
		btnReportIssue.setFocusable(false);
		btnReportIssue.setBounds(12, 351, 202, 55);
//		btnReportHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnComplaintHistory = new JButton("COMPLAINT HISTORY");
		btnComplaintHistory.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-strike-30.png")));
		btnComplaintHistory.setFocusable(false);
		btnComplaintHistory.setBounds(12, 406, 202, 55);
//		btnComplaintHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnLogout = new JButton("LOGOUT");
		btnLogout.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-logout-rounded-left-25.png")));
		btnLogout.setFocusable(false);
		btnLogout.setBounds(12, 461, 202, 55);
//		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		
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
		
		panelAccountStatusTab = new JPanel();
		panelAccountStatusTab.setFocusable(false);
		panelAccountStatusTab.setLayout(null);
		
		lblPaymentStatus = new JLabel("Payment Status:");
		lblPaymentStatus.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblPaymentStatus.setFocusable(false);
		lblPaymentStatus.setBounds(40, 40, 138, 22);
		
		lblAmountDue = new JLabel("Amount Due:");
		lblAmountDue.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblAmountDue.setFocusable(false);
		lblAmountDue.setBounds(40, 73, 138, 22);
		
		lblDueDate = new JLabel("Due Date:");
		lblDueDate.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblDueDate.setFocusable(false);
		lblDueDate.setBounds(40, 106, 138, 22);
		
		txtPaymentStatus = new JTextField();
		txtPaymentStatus.setEditable(false);
		txtPaymentStatus.setColumns(10);
		txtPaymentStatus.setBounds(188, 40, 260, 25);
		
		txtAmountDue = new JTextField();
		txtAmountDue.setEditable(false);
		txtAmountDue.setColumns(10);
		txtAmountDue.setBounds(188, 73, 260, 25);
		
		txtDueDate = new JTextField();
		txtDueDate.setEditable(false);
		txtDueDate.setColumns(10);
		txtDueDate.setBounds(188, 106, 260, 25);
		
		lblPaymentHistory = new JLabel("Payment History:");
		lblPaymentHistory.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblPaymentHistory.setFocusable(false);
		lblPaymentHistory.setBounds(40, 165, 138, 22);
		
		panelChatTab = new JPanel();
		panelChatTab.setFocusable(false);
		panelChatTab.setLayout(null);
		
		lblAvailableForLive = new JLabel("Available For Live Chat:");
		lblAvailableForLive.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblAvailableForLive.setFocusable(false);
		lblAvailableForLive.setBounds(50, 60, 190, 22);
		
		btnSend = new JButton("Send");
		btnSend.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-send-30.png")));
		btnSend.setBounds(650, 490, 180, 40);
		
		panelReportIssueTab = new JPanel();
		panelReportIssueTab.setFocusable(false);
		panelReportIssueTab.setLayout(null);
		
		lblProductService = new JLabel("Product Service:");
		lblProductService.setFocusable(false);
		lblProductService.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblProductService.setBounds(80, 91, 138, 22);
		
		comboBoxProductService = new JComboBox<Object>();
		comboBoxProductService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxProductService.setBounds(241, 90, 230, 25);
		
		lblCategory = new JLabel("Category:");
		lblCategory.setFocusable(false);
		lblCategory.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblCategory.setBounds(80, 125, 138, 22);
		
		comboBoxCategory = new JComboBox<Object>();
		comboBoxCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxCategory.setBounds(241, 124, 230, 25);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFocusable(false);
		lblDescription.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblDescription.setBounds(80, 179, 138, 22);
		
		txtAreaReportIssue = new JTextArea();
		txtAreaReportIssue.setWrapStyleWord(true);
		txtAreaReportIssue.setLineWrap(true);
		txtAreaReportIssue.setBounds(241, 180, 540, 290);
		txtAreaReportIssue.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		btnSubmitReport = new JButton("Submit Report");
		btnSubmitReport.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-submit-document-30.png")));
		btnSubmitReport.setFocusable(false);
		btnSubmitReport.setBounds(601, 481, 180, 40);
		
		panelComplaintHistoryTab = new JPanel();
		panelComplaintHistoryTab.setFocusable(false);
		panelComplaintHistoryTab.setLayout(null);
		
		lblComplaints = new JLabel("Past Complaints:");
		lblComplaints.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblComplaints.setFocusable(false);
		lblComplaints.setBounds(36, 30, 138, 22);
		
		lblProductService_1 = new JLabel("Product Service:");
		lblProductService_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblProductService_1.setFocusable(false);
		lblProductService_1.setBounds(309, 63, 138, 22);
		
		lblCategory_1 = new JLabel("Category:");
		lblCategory_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblCategory_1.setFocusable(false);
		lblCategory_1.setBounds(309, 95, 138, 22);
		
		lblDescription_1 = new JLabel("Description:");
		lblDescription_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblDescription_1.setFocusable(false);
		lblDescription_1.setBounds(309, 134, 138, 22);
		
		txtProductServiceComplaintHistory = new JTextField();
		txtProductServiceComplaintHistory.setEditable(false);
		txtProductServiceComplaintHistory.setBounds(457, 63, 260, 25);
		panelComplaintHistoryTab.add(txtProductServiceComplaintHistory);
		txtProductServiceComplaintHistory.setColumns(10);
		
		txtCategoryComplaintHistory = new JTextField();
		txtCategoryComplaintHistory.setEditable(false);
		txtCategoryComplaintHistory.setColumns(10);
		txtCategoryComplaintHistory.setBounds(457, 98, 260, 25);
		
		txtAreaDescriptionComplaintHistory = new JTextArea();
		txtAreaDescriptionComplaintHistory.setWrapStyleWord(true);
		txtAreaDescriptionComplaintHistory.setLineWrap(true);
		txtAreaDescriptionComplaintHistory.setEditable(false);
		txtAreaDescriptionComplaintHistory.setBounds(457, 135, 369, 148);
		txtAreaDescriptionComplaintHistory.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		lblResponse = new JLabel("Response:");
		lblResponse.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblResponse.setFocusable(false);
		lblResponse.setBounds(309, 388, 138, 22);
		
		txtAreaResponseComplaintHistory = new JTextArea();
		txtAreaResponseComplaintHistory.setLineWrap(true);
		txtAreaResponseComplaintHistory.setWrapStyleWord(true);
		txtAreaResponseComplaintHistory.setEditable(false);
		txtAreaResponseComplaintHistory.setBounds(457, 389, 369, 148);
		txtAreaResponseComplaintHistory.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		lblLastResponse = new JLabel("Last Response:");
		lblLastResponse.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblLastResponse.setFocusable(false);
		lblLastResponse.setBounds(309, 289, 138, 22);
		
		lblFrom = new JLabel("From:");
		lblFrom.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblFrom.setFocusable(false);
		lblFrom.setBounds(309, 322, 138, 22);
		
		txtLastResponseFromComplaintHistory = new JTextField();
		txtLastResponseFromComplaintHistory.setEditable(false);
		txtLastResponseFromComplaintHistory.setColumns(10);
		txtLastResponseFromComplaintHistory.setBounds(457, 323, 350, 25);
		
		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblDate.setFocusable(false);
		lblDate.setBounds(309, 355, 138, 22);
		
		txtLastResponseDateComplaintHistory = new JTextField();
		txtLastResponseDateComplaintHistory.setEditable(false);
		txtLastResponseDateComplaintHistory.setColumns(10);
		txtLastResponseDateComplaintHistory.setBounds(457, 356, 350, 25);
		
		btnViewAllResponses = new JButton("View All Responses");
		btnViewAllResponses.setFocusable(false);
		btnViewAllResponses.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-view-25.png")));
		btnViewAllResponses.setBounds(623, 548, 203, 35);
		
		scrollPaneComplaintTable = new JScrollPane();
		scrollPaneComplaintTable.setBounds(36, 63, 245, 520);
		tableComplaintHistory = new JTable();
		tableComplaintHistory.setModel(new DefaultTableModel(
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

		scrollPanePaymentHistory = new JScrollPane();
		scrollPanePaymentHistory.setBounds(40, 198, 650, 373);
		tablePaymentHistory = new JTable();
		tablePaymentHistory.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Passed Due Date", "Date Paid", "Amount Due"
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
		scrollPanePaymentHistory.setViewportView(tablePaymentHistory);
		
		
		scrollPaneLiveChat = new JScrollPane();
		scrollPaneLiveChat.setBounds(340, 98, 490, 300);
		
		textPaneLiveChat = new JTextPane();
		textPaneLiveChat.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 22));
		textPaneLiveChat.setEditable(false);
		scrollPaneLiveChat.setViewportView(textPaneLiveChat);
//		txtPaneLiveChat.setBorder(BorderFactory.createCompoundBorder(border,
//	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		scrollPaneChatArea = new JScrollPane();
		scrollPaneChatArea.setBounds(340, 409, 490, 70);
		
		txtChat = new JTextArea();
		txtChat.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		scrollPaneChatArea.setViewportView(txtChat);
		txtChat.setLineWrap(true);
		txtChat.setWrapStyleWord(true);
//		txtChat.setBorder(BorderFactory.createCompoundBorder(border,
//	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		
		scrollPaneTablOnlineEmployees = new JScrollPane();
		scrollPaneTablOnlineEmployees.setBounds(50, 98, 230, 450);
		
		tableOnlineEmployees = new JTable();
		tableOnlineEmployees.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Employee User Id", "Employee"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPaneTablOnlineEmployees.setViewportView(tableOnlineEmployees);
		
		tableOnlineEmployees.getColumnModel().getColumn(0).setMinWidth(0);
		tableOnlineEmployees.getColumnModel().getColumn(0).setMaxWidth(0);
		tableOnlineEmployees.getColumnModel().getColumn(0).setWidth(0);
		
		btnClearLiveChatPane = new JButton("");
		btnClearLiveChatPane.setToolTipText("Clear Live Chat");
		btnClearLiveChatPane.setContentAreaFilled(false);
		btnClearLiveChatPane.setFocusable(false);
		btnClearLiveChatPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClearLiveChatPane.setBorderPainted(false);
		btnClearLiveChatPane.setIcon(new ImageIcon(ClientDashboardView.class.getResource("/Images/icons8-clear-20.png")));
		btnClearLiveChatPane.setBounds(805, 73, 25, 25);
		
		// to allow styling in text pane
		styledDoc = textPaneLiveChat.getStyledDocument();
		style = textPaneLiveChat.addStyle("", null);
		attributes = new SimpleAttributeSet();
		
	}		
	
	private void addMnemonics() {
		btnAccountStatus.setMnemonic(KeyEvent.VK_A);
		btnChat.setMnemonic(KeyEvent.VK_C);
		btnReportIssue.setMnemonic(KeyEvent.VK_R);
		btnComplaintHistory.setMnemonic(KeyEvent.VK_H);
		btnLogout.setMnemonic(KeyEvent.VK_L);
	}
	
	private void addComponentsToPanel() {
		panelMenu.add(btnAccountStatus);
		panelMenu.add(btnChat);
		panelMenu.add(btnReportIssue);
		panelMenu.add(btnComplaintHistory);
		panelMenu.add(btnLogout);
		
		panelMenu.add(lblLoggedInAs);
		panelMenu.add(lblUserName);
		
		tabbedPane.addTab("ACCOUNT STATUS", null, panelAccountStatusTab, null);
		tabbedPane.addTab("CHAT", null, panelChatTab, null);
		tabbedPane.addTab("REPORT ISSUE", null, panelReportIssueTab, null);
		tabbedPane.addTab("COMPLAINT HISTORY", null, panelComplaintHistoryTab, null);
		
		panelReportIssueTab.add(lblProductService);
		panelReportIssueTab.add(comboBoxProductService);
		panelReportIssueTab.add(lblCategory);
		panelReportIssueTab.add(comboBoxCategory);
		panelReportIssueTab.add(lblDescription);
		panelReportIssueTab.add(txtAreaReportIssue);
		panelReportIssueTab.add(btnSubmitReport);
		
		panelComplaintHistoryTab.add(lblComplaints);
		panelComplaintHistoryTab.add(lblProductService_1);
		panelComplaintHistoryTab.add(lblCategory_1);
		panelComplaintHistoryTab.add(lblDescription_1);
		panelComplaintHistoryTab.add(txtCategoryComplaintHistory);
		panelComplaintHistoryTab.add(txtAreaDescriptionComplaintHistory);
		panelComplaintHistoryTab.add(lblResponse);
		panelComplaintHistoryTab.add(txtAreaResponseComplaintHistory);
		panelComplaintHistoryTab.add(lblLastResponse);
		panelComplaintHistoryTab.add(lblFrom);
		panelComplaintHistoryTab.add(txtLastResponseFromComplaintHistory);
		panelComplaintHistoryTab.add(lblDate);
		panelComplaintHistoryTab.add(txtLastResponseDateComplaintHistory);
		panelComplaintHistoryTab.add(btnViewAllResponses);
		panelComplaintHistoryTab.add(scrollPaneComplaintTable);
		
		scrollPaneComplaintTable.setViewportView(tableComplaintHistory);
		
		panelAccountStatusTab.add(lblPaymentStatus);
		panelAccountStatusTab.add(lblAmountDue);
		panelAccountStatusTab.add(lblDueDate);
		panelAccountStatusTab.add(txtPaymentStatus);
		panelAccountStatusTab.add(txtAmountDue);
		panelAccountStatusTab.add(txtDueDate);
		panelAccountStatusTab.add(lblPaymentHistory);		
		panelAccountStatusTab.add(scrollPanePaymentHistory);
		
		panelChatTab.add(lblAvailableForLive);
		panelChatTab.add(btnSend);
		panelChatTab.add(scrollPaneChatArea);
		panelChatTab.add(scrollPaneLiveChat);
		panelChatTab.add(scrollPaneTablOnlineEmployees);
		panelChatTab.add(btnClearLiveChatPane);
		
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
		
		btnAccountStatus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearComponents();
				tabbedPane.setSelectedIndex(0);
				clientDashboardController.sendQueryToModel("ACCOUNT_STATUS");				
			}
		});
		
		btnChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				clientDashboardController.sendQueryToModel("GET_EMPLOYEES_AVAILABLE_FOR_CHAT");
			}
		});
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		
		btnReportIssue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearComponents();
				tabbedPane.setSelectedIndex(2);
				clientDashboardController.sendQueryToModel("REPORT_ISSUE");
			}
		});
		
		btnComplaintHistory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearComponents();
				tabbedPane.setSelectedIndex(3);
				clientDashboardController.sendQueryToModel("COMPLAINT_HISTORY");
			}
		});
		
		btnSubmitReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendComplaintToModel();
			}
		});
		
		tableComplaintHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int i = tableComplaintHistory.getSelectedRow(); // get selected row index
				populateComplaintHistoryAfterTableClick(i);
			}
		});
		
		btnClearLiveChatPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPaneLiveChat.setText("");
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		
		btnViewAllResponses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableComplaintHistory.getSelectedRow(); // get selected row index
				
				if (index < 0)
					return; // return if no row was selected from the table
				
				ClientViewAllResponseDialog dialog = new ClientViewAllResponseDialog(reportedIssueList.get(index), reportedIssueResponseList);
				dialog.setVisible(true);
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
			 clientDashboardController.returnToLoginScreen();
		 }  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void populateAccountStatus(AccountStatus accountStatus) {
		ArrayList<PaymentHistoryObj> paymentHistoryListObj = new ArrayList<>();
		
		PaymentListObj paymentStatus = accountStatus.getPaymentStatus(); // store payment status
		paymentHistoryListObj = accountStatus.getPaymentHistoryList(); // store payment history
		
		txtPaymentStatus.setText(paymentStatus.getPaymentStatus());
		txtAmountDue.setText(String.valueOf(paymentStatus.getAmountDue()));
		txtDueDate.setText(paymentStatus.getDueDate());
		
		DefaultTableModel tableModel = (DefaultTableModel) tablePaymentHistory.getModel();
		tableModel.setRowCount(0); // clear table
		
		// populate JTable with payment history
		for(PaymentHistoryObj value : paymentHistoryListObj ){
			tableModel.addRow(new Object[] { value.getDueDate(), value.getDatePaid(), value.getAmountPaid() });
		} 
	}
	
	public void populateReportIssueCategories(ReportIssueCategories obj) {
		ArrayList<ProductServices> psList = obj.getProductServicesList();
		ArrayList<ComplaintCategory> ccList = obj.getComplaintCategoryList();
		
		for (ProductServices value : psList) {
			comboBoxProductServiceIndexId.add(value.getId());
			comboBoxProductService.addItem(value.getProduct_name()); 
		}
		
		for (ComplaintCategory value : ccList) {
			comboBoxCategoryIndexId.add(value.getId());
			comboBoxCategory.addItem(value.getCategoryName()); 
		}
	}
	 
	private void sendComplaintToModel() {
		if (!validateReportingIssue()) {
			JOptionPane.showMessageDialog(this, "Null report cannot be sent...", "Not Sent", JOptionPane.WARNING_MESSAGE);
			return;
		}
			
		ReportIssueHibernate reportIssueHibernate = new ReportIssueHibernate();
		
		int productServiceId = comboBoxProductService.getSelectedIndex(); // id for product service
		int complaintCategoryId = comboBoxCategory.getSelectedIndex(); // id for complaint category
		
		reportIssueHibernate.setProductServiceId(comboBoxProductServiceIndexId.get(productServiceId));
		reportIssueHibernate.setComplaintCategoryId(comboBoxCategoryIndexId.get(complaintCategoryId));
		reportIssueHibernate.setComplaint(txtAreaReportIssue.getText());
//		reportIssueHibernate.setReplied_to("NO");
		
		clientDashboardController.sendObjectToModel("SUBMIT_COMPLAINT");
		clientDashboardController.sendObjectToModel(reportIssueHibernate);
		
		JOptionPane.showMessageDialog(this, "Report successfully sent...", "Sent", JOptionPane.INFORMATION_MESSAGE);
		txtAreaReportIssue.setText(""); // clear text area after sending
	}

	public void populateComplaintReportHistory(ComplaintReportHistory obj) {
		reportedIssueList = obj.getReportedIssueList();
		reportedIssueResponseList = obj.getReportedIssueResponseList();
		
		// populate JTable with response history
		DefaultTableModel tableModel = (DefaultTableModel) tableComplaintHistory.getModel();
		tableModel.setRowCount(0); // clear table

		for (ReportedIssueObj value : reportedIssueList) {
			tableModel.addRow(new Object[] { value.getComplaint() });
		}  
		
	}
	
	private void populateComplaintHistoryAfterTableClick(int index) {
		ReportedIssueObj issueObj = reportedIssueList.get(index);
		
		// display info from the complaint made
		txtProductServiceComplaintHistory.setText(issueObj.getProductService());
		txtCategoryComplaintHistory.setText(issueObj.getCategoryName());
		txtAreaDescriptionComplaintHistory.setText(issueObj.getComplaint());
		
		
		ReportedIssueResponseObj lastResponseObj = new ReportedIssueResponseObj();
		int size = reportedIssueResponseList.size() - 1;
		
		// iterate through response history from end to beginning
		for (; size > -1; size--) {
			ReportedIssueResponseObj obj = reportedIssueResponseList.get(size);
			if (issueObj.getId() == obj.getComplaintId()) {
				lastResponseObj = reportedIssueResponseList.get(size);
				break;
			}
		}
		
		// display info of the last response to the a complaint
		txtLastResponseFromComplaintHistory.setText(lastResponseObj.getEmployeeName());
		txtLastResponseDateComplaintHistory.setText(lastResponseObj.getResponseDate());
		txtAreaResponseComplaintHistory.setText(lastResponseObj.getResponse());
	}
	
	private void clearComponents() {
		// clear components for Account Status
		txtPaymentStatus.setText("");
		txtAmountDue.setText("");
		txtDueDate.setText("");
		
		// clear components for Report Issue
		comboBoxProductService.removeAllItems();
		comboBoxCategory.removeAllItems();
		txtAreaReportIssue.setText("");
		comboBoxProductServiceIndexId.clear(); // clear indexing for combo boxes array list
		comboBoxCategoryIndexId.clear(); // clear indexing for combo boxes array list
				
		// clear components for complaint history
		txtProductServiceComplaintHistory.setText("");
		txtCategoryComplaintHistory.setText("");
		txtAreaDescriptionComplaintHistory.setText("");
		txtLastResponseFromComplaintHistory.setText("");
		txtLastResponseDateComplaintHistory.setText("");
		txtAreaResponseComplaintHistory.setText("");
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel = (DefaultTableModel) tableComplaintHistory.getModel();
		tableModel.setRowCount(0); // clear table
		
		tableModel = (DefaultTableModel) tablePaymentHistory.getModel();
		tableModel.setRowCount(0); // clear table
	}
	
	private boolean validateReportingIssue() {
		if (txtAreaReportIssue.getText().isEmpty() || 
				comboBoxCategory.getSelectedIndex() < 0 || 
				comboBoxProductService.getSelectedIndex() < 0) 
			return false;
		return true;
	}

	public void populateOnlineEmployees(ListOfOnlineEmployees listOfOnlineEmployees) {
		DefaultTableModel tableModel = (DefaultTableModel) tableOnlineEmployees.getModel();
		tableModel.setRowCount(0); // clear table

		for (OnlineEmployeesObj value : listOfOnlineEmployees.getOnlineEmployeesList()) {
			tableModel.addRow(new Object[] { value.getUserId(), value.getUserName() });
		} 
	}
	
	private void sendMessage() {
		int index = tableOnlineEmployees.getSelectedRow();
		if (index < 0)
			return;
		
		if (txtChat.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Empty message cannot be sent", "Empty Message", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		DefaultTableModel tableModel = (DefaultTableModel) tableOnlineEmployees.getModel();
		String recipientUserId = tableModel.getValueAt(index, 0).toString();
		String message = txtChat.getText();
		
		writeToLiveChatPane("SEND", message);
		txtChat.setText("");
		
		clientDashboardController.sendObjectToModel("SEND_MESSAGE");
		clientDashboardController.sendObjectToModel(recipientUserId);
		clientDashboardController.sendObjectToModel(message);
		
	}
	
	public void displayMessageOnView(String message) {
		if (message == null)
			return;
		
		String[] messageParts = message.split("\\~");
		
		String sender = messageParts[0].trim();
		
		int index = tableOnlineEmployees.getSelectedRow();
		DefaultTableModel tableModel = (DefaultTableModel) tableOnlineEmployees.getModel();
		String recipientUserId = tableModel.getValueAt(index, 0).toString();
		
		
		if (!recipientUserId.equalsIgnoreCase(sender)) {
			int opt = JOptionPane.showConfirmDialog(null, "A user wants to respond to you.\nAccept?",
					"New Chat", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (opt != JOptionPane.YES_OPTION)
				return;
			
			textPaneLiveChat.setText("");
			txtChat.setText(""); 
			tabbedPane.setSelectedIndex(1);
		}

		recipientUserId = messageParts[0].trim();
		String body = messageParts[1].trim();
		
		writeToLiveChatPane("RECEIVE", body);
		
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
		textPaneLiveChat.setFont(textPaneLiveChat.getFont().deriveFont(20.5f));
	}
}
