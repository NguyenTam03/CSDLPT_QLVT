package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Program;

import javax.swing.JTabbedPane;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class frmMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblInfoNV;
	
	
	public JLabel getLblInfoNV() {
		return lblInfoNV;
	}


	public void setLblInfoNV(JLabel lblInfoNV) {
		this.lblInfoNV = lblInfoNV;
	}


	/**
	 * Create the frame.
	 */
	public frmMain() {
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
		
		JPanel frmNhapXuat = new JPanel();
		frmNhapXuat.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tabbedPane.addTab("Nhập Xuất", null, frmNhapXuat, null);
		frmNhapXuat.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(25, 10, 81, 85);
		frmNhapXuat.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNV = new JLabel("Nhân Viên");
		lblNV.setBounds(10, 52, 71, 13);
		panel_1.add(lblNV);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmMain.class.getResource("/imgs/staff.png")));
		lblNewLabel.setBounds(23, 0, 75, 65);
		panel_1.add(lblNewLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(132, 10, 81, 85);
		frmNhapXuat.add(panel_1_1);
		
		JLabel lblVT = new JLabel("Vật Tư");
		lblVT.setBounds(20, 52, 39, 13);
		panel_1_1.add(lblVT);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(frmMain.class.getResource("/imgs/material.png")));
		lblNewLabel_2.setBounds(20, 0, 75, 65);
		panel_1_1.add(lblNewLabel_2);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBounds(243, 10, 81, 85);
		frmNhapXuat.add(panel_1_2);
		
		JLabel lblKH = new JLabel("Kho Hàng");
		lblKH.setBounds(10, 52, 70, 13);
		panel_1_2.add(lblKH);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(frmMain.class.getResource("/imgs/warehouse.png")));
		lblNewLabel_3.setBounds(23, 0, 75, 65);
		panel_1_2.add(lblNewLabel_3);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setLayout(null);
		panel_1_3.setBounds(354, 10, 81, 85);
		frmNhapXuat.add(panel_1_3);
		
		JLabel lblNewLabel_1_3 = new JLabel("Lập Phiếu");
		lblNewLabel_1_3.setBounds(10, 52, 70, 13);
		panel_1_3.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(frmMain.class.getResource("/imgs/report.png")));
		lblNewLabel_4.setBounds(23, 0, 75, 65);
		panel_1_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Quản Lý Nhập Xuất");
		lblNewLabel_5.setBounds(168, 95, 131, 13);
		frmNhapXuat.add(lblNewLabel_5);
		
		JPanel frmBaoCao = new JPanel();
		tabbedPane.addTab("Báo Cáo", null, frmBaoCao, null);
		frmBaoCao.setLayout(null);
		
		JPanel frmHeThong = new JPanel();
		tabbedPane.addTab("Hệ Thống", null, frmHeThong, null);
		frmHeThong.setLayout(null);
		
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
	}
}
