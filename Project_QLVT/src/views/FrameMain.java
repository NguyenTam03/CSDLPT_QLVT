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
import java.awt.Color;

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
	
	public JPanel getPanelLogout() {
		return panelLogout;
	}
	
	public JPanel getPanelExit() {
		return panelExit;
	}

	public JPanel getPanelAddLogin() {
		return panelAddLogin;
	}


	public FrameMain() {
		setTitle("Quản Lý Vật Tư");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1169, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1155, 147);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabbedPane.setBounds(0, 0, 1155, 147);
		panel.add(tabbedPane);
		
//		-------- Nhap xuat--------
		frmNhapXuat = new JPanel();
		frmNhapXuat.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tabbedPane.addTab("Nhập Xuất", null, frmNhapXuat, null);
		frmNhapXuat.setLayout(null);
		
		
		panelNhanVien = new JPanel();
		panelNhanVien.setBounds(25, 10, 81, 85);
		frmNhapXuat.add(panelNhanVien);
		panelNhanVien.setLayout(null);
		
		JLabel lblNV = new JLabel("Nhân Viên");
		lblNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblNV.setBounds(0, 52, 81, 13);
		panelNhanVien.add(lblNV);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/staff.png")));
		lblNewLabel.setBounds(0, 0, 81, 65);
		panelNhanVien.add(lblNewLabel);
		
		panelVatTu = new JPanel();
		panelVatTu.setLayout(null);
		panelVatTu.setBounds(132, 10, 81, 85);
		frmNhapXuat.add(panelVatTu);
		
		JLabel lblVT = new JLabel("Vật Tư");
		lblVT.setHorizontalAlignment(SwingConstants.CENTER);
		lblVT.setBounds(0, 52, 81, 13);
		panelVatTu.add(lblVT);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/material.png")));
		lblNewLabel_2.setBounds(0, 0, 81, 63);
		panelVatTu.add(lblNewLabel_2);
		
		panelKhoHang = new JPanel();
		panelKhoHang.setLayout(null);
		panelKhoHang.setBounds(243, 10, 81, 85);
		frmNhapXuat.add(panelKhoHang);
		
		JLabel lblKH = new JLabel("Kho Hàng");
		lblKH.setHorizontalAlignment(SwingConstants.CENTER);
		lblKH.setBounds(0, 52, 80, 13);
		panelKhoHang.add(lblKH);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/warehouse.png")));
		lblNewLabel_3.setBounds(0, 0, 80, 65);
		panelKhoHang.add(lblNewLabel_3);
		
		panelLapPhieu = new JPanel();
		panelLapPhieu.setLayout(null);
		panelLapPhieu.setBounds(354, 10, 81, 85);
		frmNhapXuat.add(panelLapPhieu);
		
		JLabel lblNewLabel_1_3 = new JLabel("Lập Phiếu");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(0, 52, 80, 13);
		panelLapPhieu.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/report.png")));
		lblNewLabel_4.setBounds(0, 0, 80, 65);
		panelLapPhieu.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Quản Lý Nhập Xuất");
		lblNewLabel_5.setBounds(168, 95, 131, 13);
		frmNhapXuat.add(lblNewLabel_5);
		
//		--------Bao Cao--------
		JPanel frmBaoCao = new JPanel();
		tabbedPane.addTab("Báo Cáo", null, frmBaoCao, null);
		frmBaoCao.setLayout(null);
		
//		-------- He Thong--------
		JPanel frmHeThong = new JPanel();
		tabbedPane.addTab("Hệ Thống", null, frmHeThong, null);
		frmHeThong.setLayout(null);
		
		panelLogout = new JPanel();
		panelLogout.setBounds(25, 10, 81, 71);
		frmHeThong.add(panelLogout);
		panelLogout.setLayout(null);
		
		JLabel lblLogout = new JLabel("Đăng Xuất");
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setBounds(0, 52, 81, 13);
		panelLogout.add(lblLogout);
		
		JLabel lblIconLogout = new JLabel("");
		lblIconLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogout.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/log-out.png")));
		lblIconLogout.setBounds(0, 0, 81, 65);
		panelLogout.add(lblIconLogout);
		
		panelExit = new JPanel();
		panelExit.setLayout(null);
		panelExit.setBounds(159, 10, 63, 71);
		frmHeThong.add(panelExit);
		
		JLabel lblExit = new JLabel("Thoát");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(0, 52, 63, 13);
		panelExit.add(lblExit);
		
		JLabel lblIconExit = new JLabel("");
		lblIconExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconExit.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/exit.png")));
		lblIconExit.setBounds(10, 11, 40, 41);
		panelExit.add(lblIconExit);
		
		panelAddLogin = new JPanel();
		panelAddLogin.setLayout(null);
		panelAddLogin.setBounds(285, 10, 81, 71);
		frmHeThong.add(panelAddLogin);
		
		if (Program.mGroup.equals("USER")) {
			panelAddLogin.setBackground(Color.GRAY);
		}
		
		JLabel lblAddLogin = new JLabel("Tạo tài khoản");
		lblAddLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddLogin.setBounds(0, 52, 81, 13);
		panelAddLogin.add(lblAddLogin);
		
		JLabel lblIconAdd = new JLabel("");
		lblIconAdd.setBackground(Color.WHITE);
		lblIconAdd.setIcon(new ImageIcon(FrameMain.class.getResource("/imgs/plus.png")));
		lblIconAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconAdd.setBounds(0, 0, 81, 65);
		panelAddLogin.add(lblIconAdd);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 633, 1135, 20);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		lblInfoNV = new JLabel(
				"MANV: " + Program.username + 
				" HOTEN: " + Program.mHoten + 
				" VAI TRO:" + Program.mGroup);
		lblInfoNV.setBounds(0, 3, 1032, 13);
		panel_2.add(lblInfoNV);
		
		setLocationRelativeTo(null);
		
		NavController ac = new NavController(this);
		ac.initController();
	}
}
