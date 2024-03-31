package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;

import dao.CTDDHDao;
import dao.DatHangDao;
import dao.KhoDao;
import main.Program;
import model.CTDDHModel;
import model.DatHangModel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import common.method.Formatter;
import controller.DatHangController;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class DatHangForm extends CommonView<DatHangModel, DatHangDao> {
	private static final long serialVersionUID = 1L;
	private JButton btnVatTuOption, btnKhoOption;
	private JTextField textFieldMaDH;
	private JTextField textFieldNCC;
	private JTextField textFieldMaNV;
	private static JTextField textFieldMaKho;
	private static JTextField textFieldMaVT;
	private static JTextField textFieldTenKho;
	private JTable tableCTDH;
	private JSpinner spinnerSoLuong, spinnerDonGia;
	private JDateChooser ngayDat;
	private DefaultTableModel ctdhModel;
	private CTDDHDao ctdhDao;
	private ArrayList<CTDDHModel> ctdhList;
	private JMenuItem mntmDatHang, mntmCTDH;
	private JMenu mnOption;
	private ListSelectionListener selectionCTDHListener;
	private VatTuOptionForm vtOptionForm;
	private KhoOptionForm khoOptionForm;
	
	public DatHangForm() {
		super();
		table.setEnabled(false);
		comboBox.setEnabled(false);
		scrollPane.setEnabled(false);
		getBtnThem().setEnabled(false);
		getBtnXoa().setEnabled(false);
		getBtnGhi().setEnabled(false);
		getBtnLamMoi().setEnabled(false);
		getTextFieldTim().setEnabled(false);
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
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup()
				.addComponent(panelInfo, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)))
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
						.addComponent(panelInfo, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))));

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
		textFieldMaDH.setBounds(133, 12, 103, 20);
		panelDatHang.add(textFieldMaDH);
		textFieldMaDH.setColumns(10);
		textFieldMaDH.setEditable(false);

		JLabel lblNgay = new JLabel("Ngày");
		lblNgay.setBounds(20, 36, 49, 14);
		panelDatHang.add(lblNgay);

		ngayDat = new JDateChooser();
		ngayDat.setBounds(133, 33, 152, 20);
		panelDatHang.add(ngayDat);
		ngayDat.setEnabled(false);
		ngayDat.setDateFormatString("dd-MM-yyyy");

		JLabel lblNCC = new JLabel("Nhà Cung Cấp");
		lblNCC.setBounds(20, 58, 103, 14);
		panelDatHang.add(lblNCC);

		textFieldNCC = new JTextField();
		textFieldNCC.setBounds(133, 55, 103, 20);
		panelDatHang.add(textFieldNCC);
		textFieldNCC.setColumns(10);
		textFieldNCC.setEditable(false);

		JLabel lblMaNV = new JLabel("Mã Nhân Viên");
		lblMaNV.setBounds(20, 78, 103, 14);
		panelDatHang.add(lblMaNV);

		textFieldMaNV = new JTextField();
		textFieldMaNV.setColumns(10);
		textFieldMaNV.setBounds(133, 77, 103, 20);
		panelDatHang.add(textFieldMaNV);
		textFieldMaNV.setEditable(false);

		JLabel lblMaKho = new JLabel("Mã Kho");
		lblMaKho.setBounds(20, 100, 46, 14);
		panelDatHang.add(lblMaKho);

		textFieldMaKho = new JTextField();
		textFieldMaKho.setEditable(false);
		textFieldMaKho.setBounds(133, 99, 103, 20);
		panelDatHang.add(textFieldMaKho);
		textFieldMaKho.setColumns(10);

		JPanel panelCTDH = new JPanel();
		panelCTDH.setBorder(new TitledBorder(null, "Chi Ti\u1EBFt \u0110\u01A1n H\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GroupLayout gl_panelInfo = new GroupLayout(panelInfo);
		gl_panelInfo.setHorizontalGroup(gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addComponent(panelCTDH, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panelDatHang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		gl_panelInfo.setVerticalGroup(gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInfo.createSequentialGroup()
						.addComponent(panelDatHang, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelCTDH, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
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
		btnVatTuOption.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnVatTuOption.setBounds(216, 21, 103, 31);
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
		btnKhoOption.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnKhoOption.setBounds(272, 96, 108, 30);
		panelDatHang.add(btnKhoOption);

		JLabel lblTnKho = new JLabel("Tên Kho");
		lblTnKho.setBounds(20, 125, 46, 14);
		panelDatHang.add(lblTnKho);

		textFieldTenKho = new JTextField();
		textFieldTenKho.setEditable(false);
		textFieldTenKho.setColumns(10);
		textFieldTenKho.setBounds(133, 122, 103, 20);
		panelDatHang.add(textFieldTenKho);
		panelInfo.setLayout(gl_panelInfo);
		panel_2.setLayout(gl_panel_2);

		panel_4.remove(getBtnChuyenChiNhanh());

		JMenuBar menuBar = new JMenuBar();
		panel_4.add(menuBar);

		mnOption = new JMenu("Chọn chế độ");
		mnOption.setBackground(Color.LIGHT_GRAY);
		mnOption.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnOption);

		mntmDatHang = new JMenuItem("Đặt hàng");
		mnOption.add(mntmDatHang);

		mntmCTDH = new JMenuItem("Chi tiết đặt hàng");
		mnOption.add(mntmCTDH);

		getBtnThoat().addActionListener(l -> exitDatHang());

//      load chi nhánh lên combobox
		loadChiNhanh();

//		Đơn đặt hàng table
		dao = DatHangDao.getInstance();
		ctdhDao = CTDDHDao.getInstance();
		loadDataIntoTable();

		// không cho phép chỉnh sửa nội dung trực tiếp trên row
		ctdhModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCTDH.setModel(ctdhModel);
		ctdhModel = (DefaultTableModel) tableCTDH.getModel();

//		CONGTY có thể chọn chi nhánh để xem dữ liệu
		comboBox.addItemListener(l -> loadDataOtherServer(l));

//		lắng nghe sự kiện chọn row đồng thời in dữ liệu ra textfield
		selectionCTDHListener = e -> {
			textFieldMaVT.setText(tableCTDH.getValueAt(tableCTDH.getSelectedRow(), 1).toString());
			spinnerSoLuong.setValue(tableCTDH.getValueAt(tableCTDH.getSelectedRow(), 2));
			spinnerDonGia.setValue(Formatter.formatMoneyToInteger(tableCTDH.getValueAt(tableCTDH.getSelectedRow(), 3)));
		};

		selectionListener = e -> {
			textFieldMaDH.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			try {
				ngayDat.setDate(new SimpleDateFormat("dd-MM-yyyy")
						.parse(table.getValueAt(table.getSelectedRow(), 1).toString()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			textFieldNCC.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			textFieldMaNV.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			String makho = table.getValueAt(table.getSelectedRow(), 4).toString();
			textFieldMaKho.setText(makho);
			String tenkho = KhoDao.getInstance().selectById(makho).getTenKho();
			textFieldTenKho.setText(tenkho);

			loadDataCTDDH();
		};

		table.getSelectionModel().addListSelectionListener(selectionListener);

		if (table.getRowCount() > 0) {
			table.getSelectionModel().setSelectionInterval(0, 0);
		}

		DatHangController ac = new DatHangController(this);
		ac.initController();
	}

	public static JTextField getTextFieldTenKho() {
		return textFieldTenKho;
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

	public static JTextField getTextFieldMaKho() {
		return textFieldMaKho;
	}

	public static JTextField getTextFieldMaVT() {
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

	public JDateChooser getNgayDat() {
		return ngayDat;
	}

	public JMenu getMnOption() {
		return mnOption;
	}

	public JSpinner getSpinnerSoLuong() {
		return spinnerSoLuong;
	}

	public JSpinner getSpinnerDonGia() {
		return spinnerDonGia;
	}

	public ListSelectionListener getSelectionCTDHListener() {
		return selectionCTDHListener;
	}

	public VatTuOptionForm getVtOptionForm() {
		String masoddh = textFieldMaDH.getText();
		vtOptionForm = new VatTuOptionForm(masoddh);
		return vtOptionForm;
	}

	public KhoOptionForm getKhoOptionForm() {
		khoOptionForm = new KhoOptionForm();
		return khoOptionForm;
	}

	private void loadDataCTDDH() {
		tableCTDH.getSelectionModel().removeListSelectionListener(selectionCTDHListener);
		String msddh = table.getValueAt(table.getSelectedRow(), 0).toString();

		ctdhModel.setColumnIdentifiers(ctdhDao.getColName());
		ctdhModel.setRowCount(0);
		ctdhList = ctdhDao.selectByCondition("SELECT * FROM CTDDH WHERE MasoDDH = ?", msddh);

		for (CTDDHModel dh : ctdhList) {
			Object[] rowData = { dh.getMaSoDDH(), dh.getMavt(), dh.getSoLuong(),
					Formatter.formatObjecttoMoney(dh.getDonGia()) };
			ctdhModel.addRow(rowData);
		}
		if (tableCTDH.getRowCount() > 0) {
			tableCTDH.getSelectionModel().addListSelectionListener(selectionCTDHListener);
			tableCTDH.getSelectionModel().setSelectionInterval(0, 0);
		}
	}

	public void loadDataIntoTable() {
		loadData();
		for (DatHangModel dh : list) {
			Object[] rowData = { dh.getMaSoDDH(), Formatter.formatterDate(dh.getNgay()), dh.getNhaCC(), dh.getManv(),
					dh.getMakho() };
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

	private void exitDatHang() {
		Program.frmMain.getTabbedPane_Main().removeTabAt(Program.frmMain.getTabbedPane_Main().getSelectedIndex());
		Program.frmMain.getPanel_dathang().remove(this);
	}
}
