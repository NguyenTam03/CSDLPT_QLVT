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

			@Override
			public void mouseEntered(MouseEvent e) {
				frmMain.getPanelLogout().setCursor(new Cursor(Cursor.HAND_CURSOR));
				frmMain.getPanelLogout().setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frmMain.getPanelLogout().setBackground(new Color(240, 240, 240));
				;
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

			@Override
			public void mouseEntered(MouseEvent e) {
				frmMain.getPanelExit().setCursor(new Cursor(Cursor.HAND_CURSOR));
				frmMain.getPanelExit().setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frmMain.getPanelExit().setBackground(new Color(240, 240, 240));
				;
			}
		});

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

		frmMain.getPanelNhanVien().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmMain.getTabbedPane_Main().addTab("Nhân viên", null, frmMain.getPanel_NV(), "Tab nhân viên");
				frmMain.getTabbedPane_Main().setSelectedComponent(frmMain.getPanel_NV());
			}
			
			
			@Override
			public void mouseEntered(MouseEvent e) {
				frmMain.getPanelNhanVien().setCursor(new Cursor(Cursor.HAND_CURSOR));
				frmMain.getPanelNhanVien().setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frmMain.getPanelNhanVien().setBackground(new Color(240, 240, 240));
			}
		});

		frmMain.getPanelVatTu().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmMain.getTabbedPane_Main().addTab("Vật tư", null, frmMain.getPanel_VT(), "Tab vật tư");
				frmMain.getTabbedPane_Main().setSelectedComponent(frmMain.getPanel_VT());
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				frmMain.getPanelVatTu().setCursor(new Cursor(Cursor.HAND_CURSOR));
				frmMain.getPanelVatTu().setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frmMain.getPanelVatTu().setBackground(new Color(240, 240, 240));
			}
		});

		frmMain.getPanelKhoHang().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmMain.getTabbedPane_Main().addTab("Kho hàng", null, frmMain.getPanel_Kho(), "Tab kho hàng");
				frmMain.getTabbedPane_Main().setSelectedComponent(frmMain.getPanel_Kho());
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {

				frmMain.getPanelKhoHang().setCursor(new Cursor(Cursor.HAND_CURSOR));
				frmMain.getPanelKhoHang().setBackground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				frmMain.getPanelKhoHang().setBackground(new Color(240, 240, 240));

			}
		});

		frmMain.getPanelLapPhieu().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				frmMain.getTabbedPane_Main().addTab("Lập phiếu", null, frmMain.getPanel_5(), "Tab lap phieu");
//				frmMain.getTabbedPane_Main().setSelectedComponent(frmMain.getPanel_5());
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {

				frmMain.getPanelLapPhieu().setCursor(new Cursor(Cursor.HAND_CURSOR));
				frmMain.getPanelLapPhieu().setBackground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				frmMain.getPanelLapPhieu().setBackground(new Color(240, 240, 240));

			}
		});
	}

}
