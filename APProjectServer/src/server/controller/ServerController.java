package server.controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import server.model.ServerModel;
import server.view.ServerView;

public class ServerController {
	private ServerView sView;
	private ServerModel sModel;
	
	public ServerController() {
		sModel = new ServerModel();
		sView = new ServerView(this);
	}
	
	public void serverState(boolean b) {
		sModel.serverState(b);
	}
	
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            new ServerController();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (Exception ex) {
            ex.printStackTrace();
        }
	}

}
