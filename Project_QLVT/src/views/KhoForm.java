package views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import main.Program;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;


public class KhoForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable tableKho;
	private JTextField textFieldMaKho;
	private JTextField textFieldTenKho;
	private JTextField textFieldDiaChi;
	private JTextField textFieldTim;
	private JTextField textFieldMaCN;

	/**
	 * Create the panel.
	 */
	public KhoForm() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblChiNhanh = new JLabel("Chi Nhánh");
		lblChiNhanh.setFont(new Font("Tahoma", Font.BOLD, 16));

		JComboBox<String> comboBox = new JComboBox<String>();
		for (String key : Program.servers.keySet()) {
			comboBox.addItem(key);
		}
		comboBox.setSelectedIndex(Program.mChinhanh);
		comboBox.setEditable(false);
		comboBox.setEnabled(false);
		
		if (Program.mGroup.equals("CONGTY")) {
			comboBox.setEnabled(true);
		}
		
		JPanel panel_4 = new JPanel();
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(201)
							.addComponent(lblChiNhanh, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 687, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(211, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblChiNhanh)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/add.png")));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_4.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/delete.png")));
		panel_4.add(btnXoa);
		
		JButton btnGhi = new JButton("Ghi");
		btnGhi.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/write.png")));
		btnGhi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_4.add(btnGhi);
		
		JButton btnHoanTac = new JButton("Hoàn Tác");
		btnHoanTac.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/undo.png")));
		panel_4.add(btnHoanTac);
		
		JButton btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/refresh.png")));
		panel_4.add(btnLamMoi);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/log-out_12572185.png")));
		panel_4.add(btnThoat);
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(148, 148, 148));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		tableKho = new JTable();
		tableKho.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"M\u00E3 Kho", "T\u00EAn Kho", "\u0110\u1ECBa Ch\u1EC9", "M\u00E3 CN"
			}
		));
		tableKho.getColumnModel().getColumn(1).setPreferredWidth(137);
		tableKho.getColumnModel().getColumn(2).setPreferredWidth(131);
		tableKho.getColumnModel().getColumn(3).setPreferredWidth(76);
		scrollPane.setViewportView(tableKho);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);
		
		textFieldTim = new JTextField();

		textFieldTim.setColumns(15);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/find.png")));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(240, 240, 240));
		panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel_3.add(textFieldTim);
		panel_3.add(btnTim);
		panel_3.add(separator);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(207, 207, 207));
		add(panel_2, BorderLayout.SOUTH);

		JLabel lblMaKho = new JLabel("Mã Kho");
		lblMaKho.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldMaKho = new JTextField();
		textFieldMaKho.setEditable(false);
		textFieldMaKho.setColumns(10);

		JLabel lblTenKho = new JLabel("Tên Kho");
		lblTenKho.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldTenKho = new JTextField();
		textFieldTenKho.setColumns(10);

		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldDiaChi = new JTextField();
		textFieldDiaChi.setColumns(10);

		JLabel lblMaCN = new JLabel("Mã Chi Nhánh");
		lblMaCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldMaCN = new JTextField();
		textFieldMaCN.setEditable(false);
		textFieldMaCN.setColumns(10);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(86)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldDiaChi, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addComponent(lblMaKho, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textFieldMaKho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(144)))
					.addGap(38)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblMaCN, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldMaCN, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblTenKho, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textFieldTenKho, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
					.addGap(204))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTenKho, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldTenKho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldMaKho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaKho, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaCN, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldMaCN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldDiaChi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);

	}
}
