package main.view;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import login.controller.ClientLoginController;
import login.controller.RepresentativeLoginController;
import login.controller.TechnicianLoginController;
import main.controller.MainController;

public class MainView extends JFrame implements ActionListener{
	ButtonGroup bg;
	JRadioButton client;
	JRadioButton representative;
	JRadioButton technician;
	JButton Continue;
	JPanel panTop;
	JPanel panBottom;
	MainController mc;
	
	public MainView(MainController mainCon){
		this.mc=mainCon;
		this.setLayout(null);
		initializeComponents();
		addComponentsToPanels();
		addPanelsToWindow();
		setWindowProperties();
		registerListeners();
	}
	private void initializeComponents() {
		this.setTitle("User Select");
		client=new JRadioButton("Client");
		representative= new JRadioButton("Representative");
		technician=new JRadioButton("Technician");
		Continue=new JButton("Continue");
		panTop=new JPanel(new GridLayout(3,1));
		panBottom=new JPanel();
		bg=new ButtonGroup();
		
		bg.add(client);
		bg.add(representative);
		bg.add(technician);
		
		panTop.setBounds(65,10,150,100);
		panBottom.setBounds(65,110,100,100);
	}
	
	private void addComponentsToPanels() {
		panTop.add(client);
		panTop.add(representative);
		panTop.add(technician);
		panBottom.add(Continue);
	}
	
	private void addPanelsToWindow() {
		this.add(panTop);
		this.add(panBottom);
	}
	
	public void setWindowProperties() {
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 180);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void registerListeners() {
		Continue.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Continue) {
			if (client.isSelected()) {
				this.setVisible(false);
				ClientLoginController.main(null);
			} else {

				if (representative.isSelected()) {
					this.setVisible(false);
					RepresentativeLoginController.main(null);
				} else {

					if (technician.isSelected()) {
						this.setVisible(false);
						TechnicianLoginController.main(null);
					} else {
						JOptionPane.showMessageDialog(null, "Please select an option.");
					}
					
				}
			}

		}
	}
	
	
}
