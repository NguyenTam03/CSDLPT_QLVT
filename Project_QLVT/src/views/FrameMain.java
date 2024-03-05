package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.NavController;
import main.Program;

import javax.swing.JTabbedPane;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblInfoNV;
	private JPanel panelLogout;
	private JPanel panelExit;
	private JPanel panelAddLogin;
	private JPanel frmNhapXuat;
	private JPanel panelNhanVien;
	private JPanel panelVatTu;
	private JPanel panelKhoHang;
	private JPanel panelLapPhieu;
	private JPanel panel_Main;
	private JTabbedPane tabbedPane_Main;
	private JPanel panel_VT, panel_NV, panel_Kho;

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

//		-------- Nhap xuat--------
		frmNhapXuat = new JPanel();
		frmNhapXuat.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tabbedPane.addTab("Nhập Xuất", null, frmNhapXuat, null);

		panelNhanVien = new JPanel();
		panelNhanVien.setLayout(new BorderLayout(0, 0));

		JLabel lblNV = new JLabel("Nhân Viên");
		lblNV.setHorizontalAlignment(SwingConstants.CENTER);
		panelNhanVien.add(lblNV, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/staff.png")));
		panelNhanVien.add(lblNewLabel, BorderLayout.CENTER);

		panelVatTu = new JPanel();
		panelVatTu.setLayout(new BorderLayout(0, 0));

		JLabel lblVT = new JLabel("Vật Tư");
		lblVT.setHorizontalAlignment(SwingConstants.CENTER);
		panelVatTu.add(lblVT, BorderLayout.SOUTH);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/material.png")));
		panelVatTu.add(lblNewLabel_2);

		panelKhoHang = new JPanel();
		panelKhoHang.setLayout(new BorderLayout(0, 0));

		JLabel lblKH = new JLabel("Kho Hàng");
		lblKH.setHorizontalAlignment(SwingConstants.CENTER);
		panelKhoHang.add(lblKH, BorderLayout.SOUTH);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/warehouse.png")));
		panelKhoHang.add(lblNewLabel_3);

		panelLapPhieu = new JPanel();
		panelLapPhieu.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1_3 = new JLabel("Lập Phiếu");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		panelLapPhieu.add(lblNewLabel_1_3, BorderLayout.SOUTH);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/report.png")));
		panelLapPhieu.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Quản Lý Nhập Xuất");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_frmNhapXuat = new GroupLayout(frmNhapXuat);
		gl_frmNhapXuat.setHorizontalGroup(gl_frmNhapXuat.createParallelGroup(Alignment.LEADING).addGroup(gl_frmNhapXuat
				.createSequentialGroup()
				.addGroup(gl_frmNhapXuat.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_frmNhapXuat.createSequentialGroup().addGap(38)
								.addComponent(panelNhanVien, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addGap(43)
								.addComponent(panelVatTu, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addGap(43)
								.addComponent(panelKhoHang, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(42).addComponent(panelLapPhieu, GroupLayout.PREFERRED_SIZE, 65,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_frmNhapXuat.createSequentialGroup().addGap(131).addComponent(lblNewLabel_5,
								GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(685, Short.MAX_VALUE)));
		gl_frmNhapXuat.setVerticalGroup(gl_frmNhapXuat.createParallelGroup(Alignment.LEADING).addGroup(gl_frmNhapXuat
				.createSequentialGroup()
				.addGroup(gl_frmNhapXuat.createParallelGroup(Alignment.LEADING)
						.addComponent(panelKhoHang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelVatTu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_frmNhapXuat.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panelLapPhieu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelNhanVien, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 69,
										GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE).addComponent(lblNewLabel_5)));
		frmNhapXuat.setLayout(gl_frmNhapXuat);

//		--------Bao Cao--------
		JPanel frmBaoCao = new JPanel();
		tabbedPane.addTab("Báo Cáo", null, frmBaoCao, null);
		frmBaoCao.setLayout(null);

//		-------- He Thong--------
		JPanel frmHeThong = new JPanel();
		tabbedPane.addTab("Hệ Thống", null, frmHeThong, null);

		panelLogout = new JPanel();
		panelLogout.setLayout(new BorderLayout(0, 0));

		JLabel lblLogout = new JLabel("Đăng Xuất");
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		panelLogout.add(lblLogout, BorderLayout.SOUTH);

		JLabel lblIconLogout = new JLabel("");
		lblIconLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogout.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/log-out.png")));
		panelLogout.add(lblIconLogout);

		panelExit = new JPanel();
		panelExit.setLayout(new BorderLayout(0, 0));

		JLabel lblExit = new JLabel("Thoát");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		panelExit.add(lblExit, BorderLayout.SOUTH);

		JLabel lblIconExit = new JLabel("");
		lblIconExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconExit.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/exit.png")));
		panelExit.add(lblIconExit);

		panelAddLogin = new JPanel();

		if (Program.mGroup.equals("USER")) {
			panelAddLogin.setBackground(new Color(1f, 1f, 1f, .5f));
		}
		panel.setLayout(new BorderLayout(0, 0));
		panelAddLogin.setLayout(new BorderLayout(0, 0));

		JLabel lblAddLogin = new JLabel("Tạo tài khoản");
		lblAddLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panelAddLogin.add(lblAddLogin, BorderLayout.SOUTH);

		JLabel lblIconAdd = new JLabel("");
		lblIconAdd.setBackground(Color.WHITE);
		lblIconAdd.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/plus.png")));
		lblIconAdd.setHorizontalAlignment(SwingConstants.CENTER);
		panelAddLogin.add(lblIconAdd);
		GroupLayout gl_frmHeThong = new GroupLayout(frmHeThong);
		gl_frmHeThong.setHorizontalGroup(gl_frmHeThong.createParallelGroup(Alignment.LEADING).addGroup(gl_frmHeThong
				.createSequentialGroup().addGap(36)
				.addComponent(panelLogout, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE).addGap(52)
				.addComponent(panelExit, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE).addGap(53)
				.addComponent(panelAddLogin, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE).addGap(767)));
		gl_frmHeThong.setVerticalGroup(gl_frmHeThong.createParallelGroup(Alignment.LEADING).addGroup(gl_frmHeThong
				.createSequentialGroup()
				.addGroup(gl_frmHeThong.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelAddLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_frmHeThong.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panelExit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelLogout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 62,
										Short.MAX_VALUE)))
				.addContainerGap(30, Short.MAX_VALUE)));
		frmHeThong.setLayout(gl_frmHeThong);
		panel.add(tabbedPane);

		panel_Main = new JPanel();
		contentPane.add(panel_Main, BorderLayout.CENTER);
		panel_Main.setLayout(new BorderLayout(0, 0));

		tabbedPane_Main = new JTabbedPane(JTabbedPane.TOP);
		panel_Main.add(tabbedPane_Main, BorderLayout.CENTER);


		panel_VT = new JPanel();
		panel_VT.setLayout(new BorderLayout(0, 0));
		panel_VT.add(new VatTuForm(), BorderLayout.CENTER);


		panel_NV = new JPanel();
		panel_NV.setLayout(new BorderLayout(0,0));
		panel_NV.add(new NhanVienForm(), BorderLayout.CENTER);
		
		panel_Kho = new JPanel();
		panel_Kho.setLayout(new BorderLayout(0,0));
		panel_Kho.add(new KhoForm(), BorderLayout.CENTER);
		
		lblInfoNV = new JLabel(
				"MANV: " + Program.username + " HOTEN: " + Program.mHoten + " VAI TRO:" + Program.mGroup);
		lblInfoNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInfoNV.setBackground(Color.WHITE);
		contentPane.add(lblInfoNV, BorderLayout.SOUTH);

		setLocationRelativeTo(null);

		NavController ac = new NavController(this);
		ac.initController();
	}

	public JPanel getPanelLogout() {
		return panelLogout;
	}

	public JPanel getPanelExit() {
		return panelExit;
	}

	public JPanel getPanelAddLogin() {
		return panelAddLogin;
	}

	public JPanel getPanelNhanVien() {
		return panelNhanVien;
	}

	public JPanel getPanelVatTu() {
		return panelVatTu;
	}

	public JPanel getPanelKhoHang() {
		return panelKhoHang;
	}

	public JPanel getPanelLapPhieu() {
		return panelLapPhieu;
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

}
