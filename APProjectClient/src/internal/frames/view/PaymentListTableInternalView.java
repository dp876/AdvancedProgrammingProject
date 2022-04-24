package internal.frames.view;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dashboard.controller.RepresentativeDashboardController;
import hibernate.model.PaymentListHibernate;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class PaymentListTableInternalView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnRefresh;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnInsert;
	private JScrollPane scrollPane;
	private JButton btnNewRow;
	private RepresentativeDashboardController representativeDashboardController;

	/**
	 * Create the frame.
	 */
	public PaymentListTableInternalView(RepresentativeDashboardController representativeDashboardController) {
		super("Payment List", true, true, true, true);
		this.representativeDashboardController = representativeDashboardController;
		initalizeComponents();
		addComponentsToWindow();
		setWindowsProperties();	
		registerActionListeners();
		
		TimerTask task = new TimerTask() {
	        public void run() {
	        	representativeDashboardController.sendQueryToModel("READ_ALL_PAYMENT_LIST");
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 1000L;
	    timer.schedule(task, delay);
	}
	
	private void initalizeComponents() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 814, 438);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Customer Id", "Amount Due", "Date Due", "Payment Status"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
						false, true, true, true, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPane.setViewportView(table);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(PaymentListTableInternalView.class.getResource("/Images/icons8-refresh-document-25.png")));
		btnRefresh.setFocusable(false);
		btnRefresh.setBounds(10, 460, 140, 30);
		
		btnInsert = new JButton("Insert");
		btnInsert.setIcon(new ImageIcon(PaymentListTableInternalView.class.getResource("/Images/icons8-insert-button-25.png")));
		btnInsert.setFocusable(false);
		btnInsert.setBounds(160, 460, 140, 30);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(PaymentListTableInternalView.class.getResource("/Images/icons8-update-25.png")));
		btnUpdate.setFocusable(false);
		btnUpdate.setBounds(310, 460, 140, 30);
		
		btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(PaymentListTableInternalView.class.getResource("/Images/icons8-delete-bin-30.png")));
		btnDelete.setFocusable(false);
		btnDelete.setBounds(460, 460, 140, 30);
		
		btnNewRow = new JButton("New Row");
		btnNewRow.setIcon(new ImageIcon(PaymentListTableInternalView.class.getResource("/Images/icons8-plus-25.png")));
		btnNewRow.setFocusable(false);
		btnNewRow.setBounds(654, 460, 170, 30);
		
	}
	
	private void addComponentsToWindow() {
		this.getContentPane().add(scrollPane);
		this.getContentPane().add(btnRefresh);
		this.getContentPane().add(btnInsert);
		this.getContentPane().add(btnUpdate);
		this.getContentPane().add(btnDelete);
		this.getContentPane().add(btnNewRow);
	}
	
	public void setWindowsProperties() {
		this.getContentPane().setLayout(null);
		this.setSize(850, 550);
		this.setVisible(true);
	}
	
	private void registerActionListeners() {
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				representativeDashboardController.sendQueryToModel("READ_ALL_PAYMENT_LIST");
			}
		});
		
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		btnNewRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.addRow(new Object[] { null, null, null, null, null });
			}
		});
	}

	public void populateTable(ArrayList<PaymentListHibernate> paymentListList) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0); // clear table

		for (PaymentListHibernate value : paymentListList) {
			tableModel.addRow(new Object[] { value.getId(), value.getCustomerId(),
					value.getAmountDue(), value.getDueDate(), value.getPaymentStatus() });
		}
	}
	
	private boolean validateForEmptyCells() {
		int index = table.getSelectedRow();
		
		if (table.getValueAt(index, 1).toString().isEmpty() || 
				table.getValueAt(index, 2).toString().isEmpty() ||
				table.getValueAt(index, 3).toString().isEmpty() ||
				table.getValueAt(index, 4).toString().isEmpty()) {
			return false;
		}
		return true;
	}
	
	private boolean validateDatatypeInCells() {
		int index = table.getSelectedRow();
		
		try {
			Integer.parseInt(table.getValueAt(index, 1).toString());
			Integer.parseInt(table.getValueAt(index, 2).toString());
			return true;
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "Invalid data type entered", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Invalid data type entered", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	private void insert() {
		int index = table.getSelectedRow();
		if(index < 0)
			return;
		
		if (table.getValueAt(index, 0) == null) {
			if (validateDatatypeInCells() && validateForEmptyCells()) {
				PaymentListHibernate paymentListHibernateObj = new PaymentListHibernate();
				paymentListHibernateObj.setCustomerId(Integer.parseInt(table.getValueAt(index, 1).toString()));
				paymentListHibernateObj.setAmountDue(Integer.parseInt(table.getValueAt(index, 2).toString()));
				paymentListHibernateObj.setDueDate(table.getValueAt(index, 3).toString());
				paymentListHibernateObj.setPaymentStatus(table.getValueAt(index, 4).toString());
				
				representativeDashboardController.sendObjectToModel("INSERT_PAYMENT_LIST");
				representativeDashboardController.sendObjectToModel(paymentListHibernateObj);
				
				JOptionPane.showMessageDialog(null, "Operation was sent", "Operation", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "This recored cannot be inserted", "Insert", JOptionPane.WARNING_MESSAGE);
	}
	
	private void update() {
		int index = table.getSelectedRow();
		if(index < 0)
			return;
		
		if (table.getValueAt(index, 0) != null) {
			if (!table.getValueAt(index, 0).toString().isEmpty()) {
				if (validateDatatypeInCells() && validateForEmptyCells()) {
					PaymentListHibernate paymentListHibernateObj = new PaymentListHibernate();
					paymentListHibernateObj.setId(Integer.parseInt(table.getValueAt(index, 0).toString()));
					paymentListHibernateObj.setCustomerId(Integer.parseInt(table.getValueAt(index, 1).toString()));
					paymentListHibernateObj.setAmountDue(Integer.parseInt(table.getValueAt(index, 2).toString()));
					paymentListHibernateObj.setDueDate(table.getValueAt(index, 3).toString());
					paymentListHibernateObj.setPaymentStatus(table.getValueAt(index, 4).toString());
					
					representativeDashboardController.sendObjectToModel("UPDATE_PAYMENT_LIST");
					representativeDashboardController.sendObjectToModel(paymentListHibernateObj);

					JOptionPane.showMessageDialog(null, "Operation was sent", "Operation",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "This recored cannot be updated", "Update", JOptionPane.WARNING_MESSAGE);
	}
	
	private void delete() {
		int index = table.getSelectedRow();
		if(index < 0)
			return;
		
		if (table.getValueAt(index, 0) != null) {
			if (!table.getValueAt(index, 0).toString().isEmpty()) {
				
				int opt = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Record", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (opt != JOptionPane.YES_OPTION)
					return;
				
				int id = (int) table.getValueAt(index, 0);
				representativeDashboardController.sendObjectToModel("DELETE_PAYMENT_LIST");
				representativeDashboardController.sendObjectToModel(id);

				JOptionPane.showMessageDialog(null, "Operation was sent", "Operation", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "This recored cannot be deleted", "Delete", JOptionPane.WARNING_MESSAGE);
	}
}
