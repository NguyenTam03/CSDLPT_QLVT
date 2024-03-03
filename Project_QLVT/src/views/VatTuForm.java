package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JSpinner;

public class VatTuForm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldMaVT;
	private JTextField textField;
	private JTextField textFieldDonVi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VatTuForm frame = new VatTuForm();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
	public VatTuForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 876, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblChiNhanh = new JLabel("Chi Nhánh");
		lblChiNhanh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChiNhanh.setBounds(206, 25, 89, 34);
		panel.add(lblChiNhanh);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(329, 26, 279, 28);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 108, 876, 181);
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setBounds(803, 11, 49, 14);
		lblSearch.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/search.png")));
		panel_1.add(lblSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 856, 146);
		scrollPane.setBackground(Color.WHITE);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"M\u00E3 V\u1EADt T\u01B0", "T\u00EAn V\u1EADt T\u01B0", "\u0110\u01A1n V\u1ECB T\u00EDnh", "S\u1ED1 L\u01B0\u1EE3ng T\u1ED3n"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblThongTin = new JLabel("Thông Tin");
		lblThongTin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblThongTin.setBounds(10, 297, 89, 20);
		contentPane.add(lblThongTin);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 324, 876, 153);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMaVT = new JLabel("Mã Vật Tư");
		lblMaVT.setBounds(10, 11, 61, 21);
		panel_2.add(lblMaVT);
		
		textFieldMaVT = new JTextField();
		textFieldMaVT.setBounds(82, 11, 96, 20);
		panel_2.add(textFieldMaVT);
		textFieldMaVT.setColumns(10);
		
		JLabel lblTenVT = new JLabel("Tên Vật Tư");
		lblTenVT.setBounds(231, 14, 61, 21);
		panel_2.add(lblTenVT);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(302, 11, 122, 20);
		panel_2.add(textField);
		
		JLabel lblDonVi = new JLabel("Đơn Vị Tính");
		lblDonVi.setBounds(466, 14, 61, 21);
		panel_2.add(lblDonVi);
		
		textFieldDonVi = new JTextField();
		textFieldDonVi.setColumns(10);
		textFieldDonVi.setBounds(544, 11, 96, 20);
		panel_2.add(textFieldDonVi);
		
		JLabel lblSoLuong = new JLabel("Số Lượng Tồn");
		lblSoLuong.setBounds(674, 14, 79, 21);
		panel_2.add(lblSoLuong);
		
		JSpinner spinnerSoLuong = new JSpinner();
		spinnerSoLuong.setBounds(774, 11, 73, 20);
		panel_2.add(spinnerSoLuong);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getTextFieldMaVT() {
		return textFieldMaVT;
	}

	public void setTextFieldMaVT(JTextField textFieldMaVT) {
		this.textFieldMaVT = textFieldMaVT;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextFieldDonVi() {
		return textFieldDonVi;
	}

	public void setTextFieldDonVi(JTextField textFieldDonVi) {
		this.textFieldDonVi = textFieldDonVi;
	}
}
