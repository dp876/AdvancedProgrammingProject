package dashboard.view.dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import dashboard.view.RepresentativeDashboardView;
import object.classes.TechniciansObj;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JPanel;

public class RepresentativeAssignTechnicianDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnClose;
	private ArrayList<TechniciansObj> technicianObjArrayList;
	private RepresentativeDashboardView representativeDashboardView;
	private JTable tableTechnicians;
	private JButton btnAssign;
	private JPanel panel;
	private JScrollPane scrollPane;
	private boolean assigned;
	private int technicianId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RepresentativeAssignTechnicianDialog dialog = new RepresentativeAssignTechnicianDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RepresentativeAssignTechnicianDialog() {
		initializeComponents();
		addComponentsToPanel();
		registerListeners();
	}
	
	public RepresentativeAssignTechnicianDialog(ArrayList<TechniciansObj> technicianObjArrayList) {
		this.technicianObjArrayList = technicianObjArrayList;
		this.assigned = false;
		this.technicianId = 0;
		initializeComponents();
		addComponentsToPanel();
		registerListeners();
		populateTable();
	}
		
	private void initializeComponents() {
		this.setModal(true);
		this.setResizable(false);
		this.setType(Type.POPUP);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 700, 500);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBounds(155, 415, 390, 35);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(200, 0, 190, 35);
		btnClose.setFocusable(false);
		btnClose.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 15));
		
		btnAssign = new JButton("Assign");
		btnAssign.setBounds(0, 0, 190, 35);
		btnAssign.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 15));
		btnAssign.setFocusable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 30, 600, 370);
		
		tableTechnicians = new JTable();
		tableTechnicians.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Technician ID", "Technician"
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
		scrollPane.setViewportView(tableTechnicians);
		
		// hide Technician ID column
		tableTechnicians.getColumnModel().getColumn(0).setMinWidth(0);
		tableTechnicians.getColumnModel().getColumn(0).setMaxWidth(0);
		tableTechnicians.getColumnModel().getColumn(0).setWidth(0);
	}
	
	private void addComponentsToPanel() {
		
		panel.add(btnClose);
		panel.add(btnAssign);
		getContentPane().add(scrollPane);
		
	}
	
	private void registerListeners() {
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableTechnicians.getSelectedRow();
				if(index < 0) {
					JOptionPane.showMessageDialog(null, "No technician was selected", "No Selection", JOptionPane.WARNING_MESSAGE);
					return;
				}
				assigned = true;
				technicianId = Integer.parseInt(tableTechnicians.getModel().getValueAt(index, 0).toString());
				setVisible(false);
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
	private void populateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) tableTechnicians.getModel();
		tableModel.setRowCount(0); // clear table

		for (TechniciansObj value : technicianObjArrayList) {
			tableModel.addRow(new Object[] { value.getIdPrimaryKey(), value.getFullName() });
		} 
		
	}

	public boolean isAssigned() {
		return assigned;
	}

	public int getTechnicianId() {
		return technicianId;
	}
	
}
