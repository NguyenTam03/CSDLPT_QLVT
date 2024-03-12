package controller;


import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JMenuItem;

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
		frmMain.getMnLogout().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logout();
			}
		});
			
			
		frmMain.getMnExit().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				exitFrameMain();
			}
		});
		
		frmMain.getMnCreateTK().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createLogin();
			}
		});
		
		clickedComponentShowTab("Nhân viên", "Tab nhân viên", frmMain.getMnNhanVien(), frmMain.getPanel_NV());
		// --------
		// mouse listener vat tu
		clickedComponentShowTab("Vật tư", "Tab vật tư", frmMain.getMnVatTu(), frmMain.getPanel_VT());
		
		// ---------------
		// mouse listener kho hang
		clickedComponentShowTab("Kho", "Tab kho", frmMain.getMnKho(), frmMain.getPanel_Kho());
		
		// ---------------------
		clickedMenuItem("Đặt hàng", frmMain.getMntmDatHang(), frmMain.getPanel_dathang());
	}
	
	private void logout() {
		System.out.println(1);
		frmMain.dispose();
		try {
			Program.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new LoginForm().setVisible(true);
	}

	private void exitFrameMain() {
		try {
			Program.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	private void createLogin() {
		new CreateLoginForm().setVisible(true);
	}
	
	private void clickedComponentShowTab(String label, String tip, Component t, Component t1) {
		t.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmMain.getTabbedPane_Main().addTab(label, null, t1, tip);
				frmMain.getTabbedPane_Main().setSelectedComponent(t1);
			}
		});
	}
	
	private void clickedMenuItem(String label, JMenuItem item, Component t1) {
		item.addActionListener(l -> {
			frmMain.getTabbedPane_Main().addTab(label, t1);
			frmMain.getTabbedPane_Main().setSelectedComponent(t1);
		});
	}
}
