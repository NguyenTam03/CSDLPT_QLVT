package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextField;

public class KhoForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textFieldMaKho;
	private JTextField textFieldTenKho;
	private JTextField textFieldDiaChi;
	private JTextField textFieldMaChiNhanh;

	public KhoForm() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblChiNhanh = new JLabel("Chi Nhánh");
		lblChiNhanh.setFont(new Font("Tahoma", Font.BOLD, 16));

		JComboBox<String> comboBox = new JComboBox<String>();
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		// lblSearch.setIcon(new
		// ImageIcon(VatTuForm.class.getResource("/imgs/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblChiNhanh, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE).addGap(48)
						.addComponent(lblSearch, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGap(23)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSearch, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 31,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblChiNhanh)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE))
								.addGap(22)))));
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

		JLabel lblMaChiNhanh = new JLabel("Mã Chi Nhánh");
		lblMaChiNhanh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldMaChiNhanh = new JTextField();
		textFieldMaChiNhanh.setEditable(false);
		textFieldMaChiNhanh.setColumns(10);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblMaKho, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldMaKho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldDiaChi, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
					.addGap(31)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMaChiNhanh, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTenKho, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldMaChiNhanh)
						.addComponent(textFieldTenKho, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldMaKho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaKho, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTenKho, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldTenKho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldDiaChi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaChiNhanh, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldMaChiNhanh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);

	}
}
