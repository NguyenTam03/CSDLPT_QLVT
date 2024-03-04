package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class VatTuForm extends JPanel {
	private JTable table;
	private JTextField textFieldMaVT;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public VatTuForm() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblChiNhanh = new JLabel("Chi Nhánh");
		lblChiNhanh.setFont(new Font("Tahoma", Font.BOLD, 16));

		JComboBox comboBox = new JComboBox();

		JLabel lblSearch = new JLabel("");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		//lblSearch.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblChiNhanh, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(lblSearch, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSearch, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblChiNhanh)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(22))))
		);
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(148, 148, 148));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, },
				new String[] { "M\u00E3 V\u1EADt T\u01B0", "T\u00EAn V\u1EADt T\u01B0",
						"\u0110\u01A1n V\u1ECB T\u00EDnh", "S\u1ED1 L\u01B0\u1EE3ng T\u1ED3n" }));
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(207, 207, 207));
		add(panel_2, BorderLayout.SOUTH);

		JLabel lblMVtT = new JLabel("Mã Vật Tư");
		lblMVtT.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldMaVT = new JTextField();
		textFieldMaVT.setColumns(10);

		JLabel lblTenVT = new JLabel("Tên Vật Tư");
		lblTenVT.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblDonVi = new JLabel("Đơn Vị Tính");
		lblDonVi.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblSoLuong = new JLabel("Số Lượng Tồn");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JSpinner spinner = new JSpinner();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMVtT, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldMaVT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(lblTenVT, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(lblDonVi, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(lblSoLuong, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(16))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMVtT, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldMaVT, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTenVT, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDonVi, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSoLuong, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);

	}
}