package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import dao.DatHangDao;
import main.Program;
import model.DatHangModel;
import model.KhoModel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.sql.SQLException;

import javax.swing.SwingConstants;

public class DatHangForm extends CommonView<DatHangModel, DatHangDao> {
	private static final long serialVersionUID = 1L;
	private JButton btnCheDoOption, btnVatTuOption, btnKhoOption;
	private JTextField textFieldMaDH;
	private JTextField textFieldNCC;
	private JTextField textFieldMaNV;
	private JTextField textFieldMaKho;
	private JTextField textFieldMaVT;
	private JTable tableCTDH;
	private JSpinner spinnerSoLuong, spinnerDonGia;
	
	public DatHangForm() {
		super();
		btnCheDoOption = new JButton("Chọn chế độ");
		btnCheDoOption.setIcon(new ImageIcon(CommonView.class.getResource("/imgs/down-arrow.png")));
		panel_4.add(btnCheDoOption);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(207, 207, 207));
		add(panel_2, BorderLayout.SOUTH);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Thông tin");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(panelInfo, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
							.addGap(104))
						.addComponent(panelInfo, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
		);
		
		tableCTDH = new JTable();
		scrollPane.setViewportView(tableCTDH);
		
		JPanel panelDatHang = new JPanel();
		panelDatHang.setBorder(new TitledBorder(null, "\u0110\u01A1n H\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatHang.setLayout(null);
		
		JLabel lblMaDH = new JLabel("Mã Đặt Hàng");
		lblMaDH.setBounds(20, 15, 71, 14);
		panelDatHang.add(lblMaDH);
		
		textFieldMaDH = new JTextField();
		textFieldMaDH.setBounds(101, 12, 71, 20);
		panelDatHang.add(textFieldMaDH);
		textFieldMaDH.setColumns(10);
		
		JLabel lblNgay = new JLabel("Ngày");
		lblNgay.setBounds(20, 36, 49, 14);
		panelDatHang.add(lblNgay);
		
		JComboBox comboBoxNgay = new JComboBox();
		comboBoxNgay.setBounds(101, 33, 152, 20);
		panelDatHang.add(comboBoxNgay);
		
		JLabel lblNCC = new JLabel("Nhà Cung Cấp");
		lblNCC.setBounds(20, 58, 77, 14);
		panelDatHang.add(lblNCC);
		
		textFieldNCC = new JTextField();
		textFieldNCC.setBounds(101, 55, 71, 20);
		panelDatHang.add(textFieldNCC);
		textFieldNCC.setColumns(10);
		
		JLabel lblMaNV = new JLabel("Mã Nhân Viên");
		lblMaNV.setBounds(20, 78, 71, 14);
		panelDatHang.add(lblMaNV);
		
		textFieldMaNV = new JTextField();
		textFieldMaNV.setColumns(10);
		textFieldMaNV.setBounds(101, 76, 71, 20);
		panelDatHang.add(textFieldMaNV);
		
		JLabel lblMaKho = new JLabel("Mã Kho");
		lblMaKho.setBounds(20, 100, 46, 14);
		panelDatHang.add(lblMaKho);
		
		textFieldMaKho = new JTextField();
		textFieldMaKho.setBounds(101, 99, 86, 20);
		panelDatHang.add(textFieldMaKho);
		textFieldMaKho.setColumns(10);
		
		JPanel panelCTDH = new JPanel();
		panelCTDH.setBorder(new TitledBorder(null, "Chi Ti\u1EBFt \u0110\u01A1n H\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panelInfo = new GroupLayout(panelInfo);
		gl_panelInfo.setHorizontalGroup(
			gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addComponent(panelDatHang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panelCTDH, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_panelInfo.setVerticalGroup(
			gl_panelInfo.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelInfo.createSequentialGroup()
					.addComponent(panelDatHang, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelCTDH, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
		);
		panelCTDH.setLayout(null);
		
		JLabel lblMaVT = new JLabel("Mã Vật Tư");
		lblMaVT.setBounds(20, 25, 59, 14);
		panelCTDH.add(lblMaVT);
		
		textFieldMaVT = new JTextField();
		textFieldMaVT.setBounds(101, 22, 86, 20);
		panelCTDH.add(textFieldMaVT);
		textFieldMaVT.setColumns(10);
		
		btnVatTuOption = new JButton("Chọn Vật Tư");
		btnVatTuOption.setBounds(206, 21, 103, 23);
		panelCTDH.add(btnVatTuOption);
		
		JLabel lblSoLuong = new JLabel("Số Lượng");
		lblSoLuong.setBounds(20, 50, 59, 14);
		panelCTDH.add(lblSoLuong);
		
		spinnerSoLuong = new JSpinner();
		spinnerSoLuong.setBounds(101, 47, 86, 20);
		panelCTDH.add(spinnerSoLuong);
		
		JLabel lblDonGia = new JLabel("Đơn Giá");
		lblDonGia.setBounds(20, 73, 46, 14);
		panelCTDH.add(lblDonGia);
		
		spinnerDonGia = new JSpinner();
		spinnerDonGia.setBounds(101, 70, 86, 20);
		panelCTDH.add(spinnerDonGia);
		
		btnKhoOption = new JButton("Chọn Kho Hàng");
		btnKhoOption.setBounds(202, 95, 107, 23);
		panelDatHang.add(btnKhoOption);
		panelInfo.setLayout(gl_panelInfo);
		panel_2.setLayout(gl_panel_2);
		
		panel_4.remove(getBtnChuyenChiNhanh());

//		load chi nhánh lên combobox
		loadChiNhanh();
		
		dao = DatHangDao.getInstance();
		loadDataIntoTable();
		
//		CONGTY có thể chọn chi nhánh để xem dữ liệu
		comboBox.addItemListener(l -> loadDataOtherServer(l));
		
		table.getSelectionModel().addListSelectionListener(selectionListener);
	}
	
	public void loadDataIntoTable() {
		loadData();
		for (DatHangModel dh : list) {
			Object[] rowData = {dh.getMaSoDDH(), dh.getNgay(), dh.getNhaCC(), dh.getManv(), dh.getMakho()};
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
			table.getSelectionModel().removeListSelectionListener(selectionListener);
			model.setRowCount(0);
			loadDataIntoTable();
			table.getSelectionModel().addListSelectionListener(selectionListener);
		}
	}
}
