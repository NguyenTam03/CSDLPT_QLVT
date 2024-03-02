package Test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.ComponentOrientation;
import java.awt.Rectangle;
import net.miginfocom.swing.MigLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class frmMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		
		JLabel lblNewLabel_1 = new JLabel("Nhân Viên");
		lblNewLabel_1.setBounds(10, 52, 70, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmMain.class.getResource("/imgs/staff.png")));
		lblNewLabel.setBounds(23, 0, 75, 65);
		panel_1.add(lblNewLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(132, 10, 81, 85);
		frmNhapXuat.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Vật Tư");
		lblNewLabel_1_1.setBounds(20, 52, 61, 13);
		panel_1_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(frmMain.class.getResource("/imgs/material.png")));
		lblNewLabel_2.setBounds(23, 0, 75, 65);
		panel_1_1.add(lblNewLabel_2);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBounds(243, 10, 81, 85);
		frmNhapXuat.add(panel_1_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Kho Hàng");
		lblNewLabel_1_2.setBounds(10, 52, 70, 13);
		panel_1_2.add(lblNewLabel_1_2);
		
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
		
		JLabel lblNewLabel_6 = new JLabel("MANV: HOTEN: VAI TRO:");
		lblNewLabel_6.setBounds(0, 3, 1032, 13);
		panel_2.add(lblNewLabel_6);
	}
}
