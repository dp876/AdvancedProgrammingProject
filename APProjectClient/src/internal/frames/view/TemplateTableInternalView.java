package internal.frames.view;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TemplateTableInternalView extends JInternalFrame {

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

	/**
	 * Create the frame.
	 */
	public TemplateTableInternalView() {
		super("Table Frame", true, true, true, true);
		
		initalizeComponents();
		addComponentsToWindow();
		setWindowsProperties();	
		registerActionListeners();
	}
	
	private void initalizeComponents() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 590, 356);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id"
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
		scrollPane.setViewportView(table);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(10, 460, 140, 30);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(160, 460, 140, 30);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(310, 460, 140, 30);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(460, 460, 140, 30);
		
	}
	
	private void addComponentsToWindow() {
		this.getContentPane().add(scrollPane);
		this.getContentPane().add(btnRefresh);
		this.getContentPane().add(btnInsert);
		this.getContentPane().add(btnUpdate);
		this.getContentPane().add(btnDelete);
	}
	
	public void setWindowsProperties() {
		this.getContentPane().setLayout(null);
		this.setSize(850, 550);
		this.setVisible(true);
	}
	
	private void registerActionListeners() {
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
