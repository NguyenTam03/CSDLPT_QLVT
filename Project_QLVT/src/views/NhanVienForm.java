package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
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
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

public class NhanVienForm extends JPanel {
	private JTable table;
	private JTextField textFieldTim;
	private JTextField TFDiaChi;
	private JTextField TFMaNV;
	private JTextField TFHo;
	private JTextField TFTen;
	private JTextField TFCMND;
	private JTextField TFNgaySinh;
	private JTextField TFMaCN;

	/**
	 * Create the panel.
	 */
	public NhanVienForm() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblChiNhanh = new JLabel("Chi Nhánh");
		lblChiNhanh.setFont(new Font("Tahoma", Font.BOLD, 16));

		JComboBox comboBox = new JComboBox();
		
		JPanel panel_4 = new JPanel();
		//lblSearch.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/search.png")));
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
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE))
					.addContainerGap())
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
		
		JButton btnChuyenChiNhanh = new JButton("Chuyển Chi Nhánh");
		btnChuyenChiNhanh.setIcon(new ImageIcon(VatTuForm.class.getResource("/imgs/building.png")));
		panel_4.add(btnChuyenChiNhanh);
		
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

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã nhân viên", "Họ", "Tên", "CMND", "Địa chỉ", "Ngày sinh", "Lương", "Mã chi nhánh", "Trạng thái xóa"
			}
		));
		scrollPane.setViewportView(table);
		
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

		JLabel lbMaNV = new JLabel("Mã nhân viên");
		lbMaNV.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbHo = new JLabel("Họ");
		lbHo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbTen = new JLabel("Tên");
		lbTen.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbLuong = new JLabel("Lương");
		lbLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JSpinner Luong = new JSpinner();
		Luong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Luong.setModel(new SpinnerNumberModel(Integer.valueOf(4000000), Integer.valueOf(4000000), null, Integer.valueOf(100000)));
		
		JLabel lbDiaChi = new JLabel("Địa chỉ");
		lbDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TFDiaChi = new JTextField();
		TFDiaChi.setColumns(10);
		
		TFMaNV = new JTextField();
		TFMaNV.setColumns(10);
		
		TFHo = new JTextField();
		TFHo.setColumns(10);
		
		TFTen = new JTextField();
		TFTen.setColumns(10);
		
		JLabel lbCMND = new JLabel("CMND");
		lbCMND.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TFCMND = new JTextField();
		TFCMND.setColumns(10);
		
		JLabel lbNgaySinh = new JLabel("Ngày sinh");
		lbNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TFNgaySinh = new JTextField();
		TFNgaySinh.setColumns(10);
		
		JLabel lbTrangThaiXoa = new JLabel("Trạng thái xóa");
		lbTrangThaiXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JCheckBox CheckBoxTrangThaiXoa = new JCheckBox("");
		CheckBoxTrangThaiXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lbMaCN = new JLabel("Mã chi nhánh");
		lbMaCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TFMaCN = new JTextField();
		TFMaCN.setColumns(10);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbDiaChi, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
						.addComponent(lbMaNV, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(TFMaNV, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(TFDiaChi, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
							.addGap(2)))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lbHo, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
							.addGap(24))
						.addComponent(lbNgaySinh, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(TFHo, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addComponent(TFNgaySinh, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
					.addGap(20)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lbTen, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(TFTen, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lbCMND, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(TFCMND, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lbLuong, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(Luong, GroupLayout.PREFERRED_SIZE, 105, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lbMaCN, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(TFMaCN, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(49))
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGap(382)
					.addComponent(lbTrangThaiXoa, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CheckBoxTrangThaiXoa, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(358))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lbCMND, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
									.addGroup(gl_panel_2.createSequentialGroup()
										.addGap(7)
										.addComponent(TFMaNV, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
									.addComponent(lbHo, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_2.createSequentialGroup()
										.addGap(7)
										.addComponent(TFHo, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(TFCMND, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
									.addGap(7))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lbMaNV, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
									.addGap(1))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_2.createSequentialGroup()
											.addGap(1)
											.addComponent(TFTen, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
											.addGap(6))
										.addComponent(lbTen, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(34)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(TFDiaChi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbDiaChi, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(2)
									.addComponent(lbNgaySinh, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addGroup(gl_panel_2.createSequentialGroup()
											.addGap(2)
											.addComponent(lbMaCN, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
										.addGroup(gl_panel_2.createSequentialGroup()
											.addGap(4)
											.addComponent(TFMaCN, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
									.addGap(3))
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
									.addComponent(lbLuong, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
									.addGroup(gl_panel_2.createSequentialGroup()
										.addGap(11)
										.addComponent(Luong, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
									.addGroup(gl_panel_2.createSequentialGroup()
										.addGap(7)
										.addComponent(TFNgaySinh, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))))))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(18)
							.addComponent(CheckBoxTrangThaiXoa))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbTrangThaiXoa, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);

	}
}