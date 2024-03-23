package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;

import dao.CTDDHDao;
import dao.DatHangDao;
import dao.PhieuLapDao;
import main.Program;
import model.CTDDHModel;
import model.DatHangModel;
import model.PhieuLapModel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import controller.DatHangController;
import controller.PhieuLapController;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class PhieuLapForm extends CommonView<PhieuLapModel, PhieuLapDao> {
	private static final long serialVersionUID = 1L;
	private JButton btnVatTuOption, btnKhoOption;
	private JTextField textFieldMaDH;
	private JTextField textFieldNCC;
	private JTextField textFieldMaNV;
	private JTextField textFieldMaKho;
	private JTextField textFieldMaVT;
	private JTable tableCTDH;
	private JSpinner spinnerSoLuong, spinnerDonGia;
	private JComboBox<String> comboBoxNgay;
	private DefaultTableModel ctdhModel;
	private CTDDHDao ctdhDao;
	private ArrayList<CTDDHModel> ctdhList;
	private JMenuItem mntmDatHang, mntmCTDH;

	public PhieuLapForm() {
		super();
		comboBox.setEnabled(false);
		scrollPane.setEnabled(false);
		getBtnThem().setEnabled(false);
		getBtnXoa().setEnabled(false);
		getBtnGhi().setEnabled(false);
		getBtnLamMoi().setEnabled(false);
		this.setEnabled(false);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(207, 207, 207));
		add(panel_2, BorderLayout.SOUTH);

		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);

		JLabel lblNewLabel = new JLabel("Thông tin");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setForeground(Color.DARK_GRAY);

		JLabel lblNewLabel_1 = new JLabel("Chi Tiết Đơn Đặt Hàng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(panelInfo, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)))
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, 0, 0, Short.MAX_VALUE))
						.addComponent(panelInfo, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))));

		tableCTDH = new JTable();
		tableCTDH.setEnabled(false);
		scrollPane.setViewportView(tableCTDH);

		JPanel panelDatHang = new JPanel();
		panelDatHang.setBorder(
				new TitledBorder(null, "\u0110\u01A1n H\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatHang.setLayout(null);

		JLabel lblMaDH = new JLabel("Mã Đặt Hàng");
		lblMaDH.setBounds(20, 15, 103, 14);
		panelDatHang.add(lblMaDH);

		textFieldMaDH = new JTextField();
		textFieldMaDH.setBounds(133, 12, 71, 20);
		panelDatHang.add(textFieldMaDH);
		textFieldMaDH.setColumns(10);
		textFieldMaDH.setEditable(false);

		JLabel lblNgay = new JLabel("Ngày");
		lblNgay.setBounds(20, 36, 49, 14);
		panelDatHang.add(lblNgay);

		comboBoxNgay = new JComboBox<>();
		comboBoxNgay.setBounds(133, 33, 152, 20);
		panelDatHang.add(comboBoxNgay);
		comboBoxNgay.setEnabled(false);

		JLabel lblNCC = new JLabel("Nhà Cung Cấp");
		lblNCC.setBounds(20, 58, 103, 14);
		panelDatHang.add(lblNCC);

		textFieldNCC = new JTextField();
		textFieldNCC.setBounds(133, 55, 71, 20);
		panelDatHang.add(textFieldNCC);
		textFieldNCC.setColumns(10);
		textFieldNCC.setEditable(false);

		JLabel lblMaNV = new JLabel("Mã Nhân Viên");
		lblMaNV.setBounds(20, 78, 103, 14);
		panelDatHang.add(lblMaNV);

		textFieldMaNV = new JTextField();
		textFieldMaNV.setColumns(10);
		textFieldMaNV.setBounds(133, 77, 71, 20);
		panelDatHang.add(textFieldMaNV);
		textFieldMaNV.setEditable(false);

		JLabel lblMaKho = new JLabel("Mã Kho");
		lblMaKho.setBounds(20, 100, 46, 14);
		panelDatHang.add(lblMaKho);

		textFieldMaKho = new JTextField();
		textFieldMaKho.setEditable(false);
		textFieldMaKho.setBounds(101, 99, 86, 20);
		panelDatHang.add(textFieldMaKho);
		textFieldMaKho.setColumns(10);

		JPanel panelCTDH = new JPanel();
		panelCTDH.setBorder(new TitledBorder(null, "Chi Ti\u1EBFt \u0110\u01A1n H\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GroupLayout gl_panelInfo = new GroupLayout(panelInfo);
		gl_panelInfo.setHorizontalGroup(gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addComponent(panelDatHang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panelCTDH, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		gl_panelInfo.setVerticalGroup(gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInfo.createSequentialGroup()
						.addComponent(panelDatHang, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelCTDH, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)));
		panelCTDH.setLayout(null);

		JLabel lblMaVT = new JLabel("Mã Vật Tư");
		lblMaVT.setBounds(20, 25, 59, 14);		
		panelCTDH.add(lblMaVT);

		textFieldMaVT = new JTextField();
		textFieldMaVT.setEditable(false);
		textFieldMaVT.setBounds(101, 22, 86, 20);
		panelCTDH.add(textFieldMaVT);
		textFieldMaVT.setColumns(10);

		btnVatTuOption = new JButton("Chọn Vật Tư");
		btnVatTuOption.setEnabled(false);
		btnVatTuOption.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnVatTuOption.setBounds(216, 21, 103, 23);
		panelCTDH.add(btnVatTuOption);

		JLabel lblSoLuong = new JLabel("Số Lượng");
		lblSoLuong.setBounds(20, 50, 59, 14);
		panelCTDH.add(lblSoLuong);

		spinnerSoLuong = new JSpinner();
		spinnerSoLuong.setEnabled(false);
		spinnerSoLuong.setBounds(101, 47, 86, 20);
		panelCTDH.add(spinnerSoLuong);

		JLabel lblDonGia = new JLabel("Đơn Giá");
		lblDonGia.setBounds(20, 73, 46, 14);
		panelCTDH.add(lblDonGia);

		spinnerDonGia = new JSpinner();
		spinnerDonGia.setEnabled(false);
		spinnerDonGia.setBounds(101, 70, 86, 20);
		panelCTDH.add(spinnerDonGia);

		btnKhoOption = new JButton("Chọn Kho Hàng");
		btnKhoOption.setEnabled(false);
		btnKhoOption.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnKhoOption.setBounds(212, 96, 107, 23);
		panelDatHang.add(btnKhoOption);
		panelInfo.setLayout(gl_panelInfo);
		panel_2.setLayout(gl_panel_2);

		panel_4.remove(getBtnChuyenChiNhanh());

		JMenuBar menuBar = new JMenuBar();
		panel_4.add(menuBar);

		JMenu mnOption = new JMenu("Chọn chế độ");
		mnOption.setBackground(Color.LIGHT_GRAY);
		mnOption.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnOption);

		mntmDatHang = new JMenuItem("Đặt hàng");
		mnOption.add(mntmDatHang);

		mntmCTDH = new JMenuItem("Chi tiết đặt hàng");
		mnOption.add(mntmCTDH);

//      load chi nhánh lên combobox
		loadChiNhanh();

//		Đơn đặt hàng table
		dao = PhieuLapDao.getInstance();
		loadDataIntoTable();

//		Chi tiết đơn đặt hàng table
		ctdhDao = CTDDHDao.getInstance();
		ctdhModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCTDH.setModel(ctdhModel);
		ctdhModel = (DefaultTableModel) tableCTDH.getModel();
		ctdhModel.setColumnIdentifiers(ctdhDao.getColName());
		ctdhList = ctdhDao.selectAll();
		for (CTDDHModel dh : ctdhList) {
			Object[] rowData = { dh.getMaSoDDH(), dh.getMavt(), dh.getSoLuong(), dh.getDonGia() };
			ctdhModel.addRow(rowData);
		}

//		CONGTY có thể chọn chi nhánh để xem dữ liệu
		comboBox.addItemListener(l -> loadDataOtherServer(l));

//		lắng nghe sự kiện chọn row đồng thời in dữ liệu ra textfield
		selectionListener = e -> {
			textFieldMaDH.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			//comboBoxNgay.setSelectedItem(table.getValueAt(table.getSelectedRow(), 1).toString());
			textFieldNCC.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			textFieldMaNV.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			textFieldMaKho.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
		};
		table.setEnabled(false);
		table.getSelectionModel().addListSelectionListener(selectionListener);
		
//		DatHangController ac = new PhieuLapController(this);
//		ac.initController();
	}

	public JButton getBtnVatTuOption() {
		return btnVatTuOption;
	}

	public JButton getBtnKhoOption() {
		return btnKhoOption;
	}

	public JTextField getTextFieldMaDH() {
		return textFieldMaDH;
	}

	public JTextField getTextFieldNCC() {
		return textFieldNCC;
	}

	public JTextField getTextFieldMaNV() {
		return textFieldMaNV;
	}

	public JTextField getTextFieldMaKho() {
		return textFieldMaKho;
	}

	public JTextField getTextFieldMaVT() {
		return textFieldMaVT;
	}

	public JTable getTableCTDH() {
		return tableCTDH;
	}

	public JMenuItem getMntmDatHang() {
		return mntmDatHang;
	}

	public JMenuItem getMntmCTDH() {
		return mntmCTDH;
	}

	public void loadDataIntoTable() {
		loadData();
		for (PhieuLapModel pl : list) {
			Object[] rowData = { pl.getMaSoDDH(), pl.getNgay(), pl.getMaSoDDH(), pl.getManv(), pl.getMaKho()};
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
			Program.mChinhanh = comboBox.getSelectedIndex();
			if (Program.Connect() == 0)
				return;
			table.getSelectionModel().removeListSelectionListener(selectionListener);
			model.setRowCount(0);
			loadDataIntoTable();
			table.getSelectionModel().addListSelectionListener(selectionListener);
		}
	}
}
