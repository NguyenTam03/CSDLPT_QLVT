package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import main.Program;
import views.CreateLoginForm;
import views.FrameMain;
import views.LoginForm;

public class NavController {
	private FrameMain frmMain;
	
	public NavController(FrameMain frmMain) {
		this.frmMain = frmMain;
	}
	
	public void initController() {
		frmMain.getPanelLogout().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        frmMain.dispose();
		        try {
					Program.conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		        new LoginForm().setVisible(true);
		    }
		});
		
		frmMain.getPanelExit().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Program.conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		frmMain.getPanelAddLogin().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Program.mGroup.equals("CONGTY") || Program.mGroup.equals("CHINHANH")) {
					new CreateLoginForm().setVisible(true);
				}
			}
		});
	}
}
