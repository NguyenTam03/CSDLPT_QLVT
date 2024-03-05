package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import main.Program;
import views.CreateLoginForm;
import views.FrameMain;
import views.LoginForm;

public class NavController extends MouseController {
	private FrameMain frmMain;

	public NavController(FrameMain frmMain) {
		this.frmMain = frmMain;
	}

	public void initController() {
		// mouse listener logout
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
		hoverComponent(frmMain.getPanelLogout());
		// ------
		// mouse listener exit
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
		hoverComponent(frmMain.getPanelExit());
		// -------
		// mouse listener add login
		frmMain.getPanelAddLogin().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Program.mGroup.equals("CONGTY") || Program.mGroup.equals("CHINHANH")) {
					new CreateLoginForm().setVisible(true);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (Program.mGroup.equals("CONGTY") || Program.mGroup.equals("CHINHANH")) {
					frmMain.getPanelAddLogin().setCursor(new Cursor(Cursor.HAND_CURSOR));
					frmMain.getPanelAddLogin().setBackground(Color.WHITE);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (Program.mGroup.equals("CONGTY") || Program.mGroup.equals("CHINHANH")) {
					frmMain.getPanelAddLogin().setBackground(new Color(240, 240, 240));
				}

			}
		});
		// --------------
		// mouse listener nhanvien
		clickedComponentShowTab(frmMain.getTabbedPane_Main(), "Nhân viên", "Tab nhân viên", frmMain.getPanelNhanVien(), frmMain.getPanel_NV());
		hoverComponent(frmMain.getPanelNhanVien());
		// --------
		// mouse listener vat tu
		clickedComponentShowTab(frmMain.getTabbedPane_Main(), "Vật tư", "Tab vật tư", frmMain.getPanelVatTu(), frmMain.getPanel_VT());
		hoverComponent(frmMain.getPanelVatTu());
		// ---------------
		// mouse listener kho hang
		clickedComponentShowTab(frmMain.getTabbedPane_Main(), "Kho", "Tab kho", frmMain.getPanelKhoHang(), frmMain.getPanel_Kho());
		hoverComponent(frmMain.getPanelKhoHang());
		// ---------------------
		// mouse listener lap phieu
		clickedComponentShowTab(frmMain.getTabbedPane_Main(), "Lập phiếu", "Tab lập phiếu", frmMain.getPanelLapPhieu(), frmMain.getPanel_LapPhieu());
		hoverComponent(frmMain.getPanelLapPhieu());
	}

}
