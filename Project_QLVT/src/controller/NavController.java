package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import main.Program;
import views.CreateLoginForm;
import views.DatHangForm;
import views.FrameMain;
import views.KhoForm;
import views.LoginForm;
import views.NhanVienForm;
import views.VatTuForm;

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

		 clickedComponentShowTab("Nhân viên", "Tab nhân viên",
		 frmMain.getMnNhanVien(), frmMain.getPanel_NV(), NhanVienForm.class);
		// --------
		// mouse listener vat tu
		 clickedComponentShowTab("Vật tư", "Tab vật tư", frmMain.getMnVatTu(),
		 frmMain.getPanel_VT(), VatTuForm.class);

		// ---------------
		// mouse listener kho hang
		clickedComponentShowTab("Kho", "Tab kho", frmMain.getMnKho(), frmMain.getPanel_Kho(), KhoForm.class);

		// ---------------------
		clickedMenuItem("Đặt hàng", frmMain.getMntmDatHang(), frmMain.getPanel_dathang(), DatHangForm.class);
	}

	private void logout() {
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

	private void clickedComponentShowTab(String label, String tip, JMenu t, JPanel t1, Class<?> formClass) {
		t.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Component form = (Component) formClass.getDeclaredConstructor().newInstance();
					t1.add(form, BorderLayout.CENTER);
					frmMain.getTabbedPane_Main().addTab(label, null, t1, tip);
					frmMain.getTabbedPane_Main().setSelectedComponent(t1);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

	private void clickedMenuItem(String label, JMenuItem item, JPanel t1, Class<?> formClass) {
		item.addActionListener(l -> {
			Component form;
			try {
				form = (Component) formClass.getDeclaredConstructor().newInstance();
				t1.add(form, BorderLayout.CENTER);
				frmMain.getTabbedPane_Main().addTab(label, t1);
				frmMain.getTabbedPane_Main().setSelectedComponent(t1);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}
}
