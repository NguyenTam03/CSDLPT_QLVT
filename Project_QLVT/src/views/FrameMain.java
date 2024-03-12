package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.NavController;
import main.Program;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblInfoNV;
	private JPanel panel_Main;
	private JTabbedPane tabbedPane_Main;
	private JPanel panel_VT, panel_NV, panel_Kho, panel_dathang, panel_phieulap, panel_phieudat;
	private JMenu mnNhanVien, mnVatTu, mnKho, mnLapPhieu, mnLogout, mnExit, mnCreateTK;
	private JMenuItem mntmDatHang, mntmPhieuLap, mntmPhieuXuat;

	public FrameMain() {
		setTitle("Quản Lý Vật Tư");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1169, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(4, 4, 3, 4));
		contentPane.add(panel, BorderLayout.NORTH);

		UIManager.put("TabbedPane.selected", Color.WHITE);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));

//		------------Nhap Xuat---------------
		JMenuBar menuBarNhapXuat = new JMenuBar();
		tabbedPane.addTab("Nhập Xuất", null, menuBarNhapXuat, null);

		mnNhanVien = new JMenu("Nhân viên");
		mnNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		mnNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNhanVien.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/staff.png")));
		menuBarNhapXuat.add(mnNhanVien);
		
		mnVatTu = new JMenu("Vật tư");
		mnVatTu.setHorizontalAlignment(SwingConstants.CENTER);
		mnVatTu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnVatTu.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/material.png")));
		menuBarNhapXuat.add(mnVatTu);
		
		mnKho = new JMenu("Kho");
		mnKho.setHorizontalAlignment(SwingConstants.CENTER);
		mnKho.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnKho.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/warehouse.png")));
		menuBarNhapXuat.add(mnKho);
	
		mnLapPhieu = new JMenu("Lập phiếu");
		mnLapPhieu.setHorizontalAlignment(SwingConstants.CENTER);
		mnLapPhieu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnLapPhieu.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/report.png")));
		menuBarNhapXuat.add(mnLapPhieu);

		mntmDatHang = new JMenuItem("Đặt hàng");
		mntmDatHang.setInheritsPopupMenu(true);
		mntmDatHang.setIgnoreRepaint(true);
		mntmDatHang.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/shopping-cart.png")));
		mnLapPhieu.add(mntmDatHang);

		mntmPhieuLap = new JMenuItem("Phiếu lập");
		mntmPhieuLap.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/invoice.png")));
		mnLapPhieu.add(mntmPhieuLap);

		mntmPhieuXuat = new JMenuItem("Phiếu xuất");
		mntmPhieuXuat.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/invoice.png")));
		mnLapPhieu.add(mntmPhieuXuat);

		panel.setLayout(new BorderLayout(0, 0));
		panel.add(tabbedPane);

//		------------He Thong---------------
		JMenuBar menuBarHeThong = new JMenuBar();
		tabbedPane.addTab("Hệ Thống", null, menuBarHeThong, null);

		mnLogout = new JMenu("Đăng xuất");
		mnLogout.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnLogout.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/log-out.png")));
		menuBarHeThong.add(mnLogout);

		mnExit = new JMenu("Thoát");
		mnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnExit.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/exit.png")));
		menuBarHeThong.add(mnExit);

		mnCreateTK = new JMenu("Tạo tài khoản");
		mnCreateTK.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnCreateTK.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/plus.png")));
		menuBarHeThong.add(mnCreateTK);

		if (Program.mGroup.equals("USER")) {
			mnCreateTK.setEnabled(false);
		}

//		------------Bao Cao---------------
		JMenuBar menuBarReport = new JMenuBar();
		tabbedPane.addTab("Báo Cáo", null, menuBarReport, null);

		panel_Main = new JPanel();
		contentPane.add(panel_Main, BorderLayout.CENTER);
		panel_Main.setLayout(new BorderLayout(0, 0));

		tabbedPane_Main = new JTabbedPane(JTabbedPane.TOP);
		panel_Main.add(tabbedPane_Main, BorderLayout.CENTER);

		VatTuForm vatTuView = new VatTuForm();
		panel_VT = new JPanel();
		panel_VT.setLayout(new BorderLayout(0, 0));
		panel_VT.add(vatTuView, BorderLayout.CENTER);
		vatTuView.getBtnThoat().addActionListener(l -> {
			tabbedPane_Main.removeTabAt(tabbedPane_Main.getSelectedIndex());
		});

		NhanVienForm nhanVienView = new NhanVienForm();
		panel_NV = new JPanel();
		panel_NV.setLayout(new BorderLayout(0, 0));
		panel_NV.add(nhanVienView, BorderLayout.CENTER);
		nhanVienView.getBtnThoat().addActionListener(l -> {
			tabbedPane_Main.removeTabAt(tabbedPane_Main.getSelectedIndex());
		});

		KhoForm khoView = new KhoForm();
		panel_Kho = new JPanel();
		panel_Kho.setLayout(new BorderLayout(0, 0));
		panel_Kho.add(khoView, BorderLayout.CENTER);
		khoView.getBtnThoat().addActionListener(l -> {
			tabbedPane_Main.removeTabAt(tabbedPane_Main.getSelectedIndex());
		});
		
		DatHangForm datHangView = new DatHangForm();
		panel_dathang = new JPanel();
		panel_dathang.setLayout(new BorderLayout(0, 0));
		panel_dathang.add(datHangView, BorderLayout.CENTER);
		datHangView.getBtnThoat().addActionListener(l -> {
			tabbedPane_Main.removeTabAt(tabbedPane_Main.getSelectedIndex());
		});
		
		lblInfoNV = new JLabel(
				"MANV: " + Program.username + " HOTEN: " + Program.mHoten + " VAI TRO:" + Program.mGroup);
		lblInfoNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInfoNV.setBackground(Color.WHITE);
		contentPane.add(lblInfoNV, BorderLayout.SOUTH);

		setLocationRelativeTo(null);

		NavController ac = new NavController(this);
		ac.initController();
	}

	public JMenu getMnNhanVien() {
		return mnNhanVien;
	}

	public JMenu getMnVatTu() {
		return mnVatTu;
	}

	public JMenu getMnKho() {
		return mnKho;
	}

	public JMenu getMnLapPhieu() {
		return mnLapPhieu;
	}

	public JMenu getMnLogout() {
		return mnLogout;
	}

	public JMenu getMnExit() {
		return mnExit;
	}

	public JMenu getMnCreateTK() {
		return mnCreateTK;
	}

	public JMenuItem getMntmDatHang() {
		return mntmDatHang;
	}

	public JMenuItem getMntmPhieuLap() {
		return mntmPhieuLap;
	}

	public JMenuItem getMntmPhieuXuat() {
		return mntmPhieuXuat;
	}

	public JTabbedPane getTabbedPane_Main() {
		return tabbedPane_Main;
	}

	public JPanel getPanel_VT() {
		return panel_VT;
	}

	public JPanel getPanel_NV() {
		return panel_NV;
	}

	public JPanel getPanel_Kho() {
		return panel_Kho;
	}

	public JPanel getPanel_dathang() {
		return panel_dathang;
	}
	
}
