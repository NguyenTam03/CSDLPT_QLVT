package views;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

import controller.KhoController;
import controller.NhanVienController;
import dao.NhanVienDao;
import main.Program;
import model.NhanVienModel;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ItemEvent;
import java.sql.SQLException;



public class NhanVienForm extends CommonView<NhanVienModel, NhanVienDao> {

	private static final long serialVersionUID = 1L;
	private JTextField TFDiaChi;
	private JTextField TFMaNV;
	private JTextField TFHo;
	private JTextField TFTen;
	private JTextField TFCMND;
	private JTextField TFNgaySinh;
	private JTextField TFMaCN;
	private JSpinner Luong;
	private JCheckBox CheckBoxTrangThaiXoa;
	/**
	 * Create the panel.
	 */
	public NhanVienForm() {
		super();
		
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

		Luong = new JSpinner();
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
		
		CheckBoxTrangThaiXoa = new JCheckBox("");
		CheckBoxTrangThaiXoa.setEnabled(false);
		CheckBoxTrangThaiXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lbMaCN = new JLabel("Mã chi nhánh");
		lbMaCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		TFMaCN = new JTextField();
		TFMaCN.setEditable(false);
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
//		load chi nhánh lên combobox
		loadChiNhanh();
//		load dữ liệu từ chi nhánh lên table
		dao = NhanVienDao.getInstance();
		loadDataIntoTable();
//		CONGTY có thể chọn chi nhánh để xem dữ liệu
		comboBox.addItemListener(l -> loadDataOtherServer(l));
		
//		lắng nghe sự kiện chọn row đồng thời in dữ liệu ra textfield
		selectionListener = e -> {
			TFMaNV.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			TFHo.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			TFTen.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			TFCMND.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			TFDiaChi.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
			TFNgaySinh.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
			Luong.setValue(table.getValueAt(table.getSelectedRow(), 6));
			TFMaCN.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
			CheckBoxTrangThaiXoa.setSelected((boolean)table.getValueAt(table.getSelectedRow(), 8));
		};

		// Thêm sự kiện chọn
		table.getSelectionModel().addListSelectionListener(selectionListener);
		
//		Listener event
		NhanVienController ac = new NhanVienController(this);
		ac.initController();
	}
	

	public JTextField getTFDiaChi() {
		return TFDiaChi;
	}

	public JTextField getTFMaNV() {
		return TFMaNV;
	}

	public JTextField getTFHo() {
		return TFHo;
	}

	public JTextField getTFTen() {
		return TFTen;
	}

	public JTextField getTFCMND() {
		return TFCMND;
	}

	public JTextField getTFNgaySinh() {
		return TFNgaySinh;
	}

	public JTextField getTFMaCN() {
		return TFMaCN;
	}

	public void loadDataIntoTable() {
		loadData();
		for (NhanVienModel NhanVien : list) {
			Object[] rowData = { NhanVien.getManv(), NhanVien.getHo(),NhanVien.getTen(),NhanVien.getSoCMND(),
					NhanVien.getDiaChi(),NhanVien.getNgaySinh(), NhanVien.getLuong(), NhanVien.getMacn(),
					NhanVien.getTrangThaiXoa()};
			model.addRow(rowData);
		}
	}

	
	private void loadDataOtherServer(ItemEvent l) {
		if (l.getStateChange() == ItemEvent.SELECTED) {
			if (Program.conn != null) {
				try {
					Program.conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			Program.servername = Program.servers.get(comboBox.getSelectedItem());
			Program.mlogin = Program.remotelogin;
			Program.password = Program.remotepassword;
			if (Program.Connect() == 0)
				return;
			TFMaCN.setText(Program.maCN);
			table.getSelectionModel().removeListSelectionListener(selectionListener);
			model.setRowCount(0);
			loadDataIntoTable();
			table.getSelectionModel().addListSelectionListener(selectionListener);
		}
	}

}
