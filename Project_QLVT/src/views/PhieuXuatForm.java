package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import common.method.Formatter;
import controller.PhieuXuatController;
import dao.CTPXDao;
import dao.PhieuXuatDao;
import main.Program;
import model.CTPXModel;
import model.PhieuXuatModel;
import javax.swing.border.EtchedBorder;

public class PhieuXuatForm extends CommonView<PhieuXuatModel, PhieuXuatDao> {
	private static final long serialVersionUID = 1L;
	private JButton btnVatTuOption, btnKhoOption;
	private JTextField textFieldMaPX;
	private JTextField textFieldTenKH;
	private JTextField textFieldMaNV;
	private static JTextField textFieldMaKho;
	private static JTextField textFieldMaVT;
	private JTable tableCTPX;
	private static JSpinner spinnerSoLuong, spinnerDonGia;
//	private JComboBox<String> comboBoxNgay;
	private JDateChooser ngay;
	public DefaultTableModel ctpxModel;
	private CTPXDao ctpxDao;
	private ArrayList<CTPXModel> ctpxList;
	private JMenuItem mntmPhieuXuat, mntmCTPX;
	private JLabel lblHoTenNV;
	private static JLabel lblTenKho;
	private static JLabel lblTenVT;
	private JMenu mnOption;
	private ListSelectionListener selectionListenerCTPX;
	private KhoOptionFormForPX khoOptionFormPx;
	private VatTuOptionFormForPX vatTuOptionFormPx;

	public PhieuXuatForm() {
		table.setEnabled(false);
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

		JLabel lblNewLabel_1 = new JLabel("Chi Tiết Phiếu Xuất");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
								.addComponent(panelInfo, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)))
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(panelInfo, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))));

		tableCTPX = new JTable();
		tableCTPX.setEnabled(false);
		scrollPane.setViewportView(tableCTPX);

		JPanel panelDatHang = new JPanel();
		panelDatHang.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Phi\u1EBFu Xu\u1EA5t", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatHang.setLayout(null);

		JLabel lblMaPX = new JLabel("Mã Phiếu Xuất");
		lblMaPX.setBounds(20, 15, 103, 14);
		panelDatHang.add(lblMaPX);

		textFieldMaPX = new JTextField();
		textFieldMaPX.setBounds(133, 12, 71, 20);
		panelDatHang.add(textFieldMaPX);
		textFieldMaPX.setColumns(10);
		textFieldMaPX.setEditable(false);

		JLabel lblNgay = new JLabel("Ngày");
		lblNgay.setBounds(20, 36, 49, 14);
		panelDatHang.add(lblNgay);

//		comboBoxNgay = new JComboBox<>();
//		comboBoxNgay.setBounds(133, 33, 152, 20);
//		panelDatHang.add(comboBoxNgay);
//		comboBoxNgay.setEnabled(false);
		ngay = new JDateChooser();
		ngay.setBounds(133, 33, 152, 20);
		panelDatHang.add(ngay);
		ngay.setEnabled(false);

		JLabel lblTenKH = new JLabel("Tên Khách Hàng");
		lblTenKH.setBounds(20, 58, 103, 14);
		panelDatHang.add(lblTenKH);

		textFieldTenKH = new JTextField();
		textFieldTenKH.setBounds(133, 55, 131, 20);
		panelDatHang.add(textFieldTenKH);
		textFieldTenKH.setColumns(10);
		textFieldTenKH.setEditable(false);

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
		textFieldMaKho.setBounds(73, 97, 57, 20);
		panelDatHang.add(textFieldMaKho);
		textFieldMaKho.setColumns(10);

		JPanel panelCTPX = new JPanel();
		panelCTPX.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chi Ti\u1EBFt Phi\u1EBFu Xu\u1EA5t", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GroupLayout gl_panelInfo = new GroupLayout(panelInfo);
		gl_panelInfo.setHorizontalGroup(gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addComponent(panelDatHang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panelCTPX, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		gl_panelInfo.setVerticalGroup(gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInfo.createSequentialGroup()
						.addComponent(panelDatHang, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelCTPX, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)));
		panelCTPX.setLayout(null);

		JLabel lblMaVT = new JLabel("Mã Vật Tư");
		lblMaVT.setBounds(20, 25, 59, 14);
		panelCTPX.add(lblMaVT);

		textFieldMaVT = new JTextField();
		textFieldMaVT.setEditable(false);
		textFieldMaVT.setBounds(89, 22, 86, 20);
		panelCTPX.add(textFieldMaVT);
		textFieldMaVT.setColumns(10);

		btnVatTuOption = new JButton("Chọn Vật Tư");
		btnVatTuOption.setEnabled(false);
		btnVatTuOption.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnVatTuOption.setBounds(89, 54, 103, 23);
		panelCTPX.add(btnVatTuOption);

		JLabel lblSoLuong = new JLabel("Số Lượng");
		lblSoLuong.setBounds(20, 91, 59, 14);
		panelCTPX.add(lblSoLuong);

		spinnerSoLuong = new JSpinner();
		spinnerSoLuong
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnerSoLuong.setEnabled(false);
		spinnerSoLuong.setBounds(89, 88, 86, 20);
		panelCTPX.add(spinnerSoLuong);

		JLabel lblDonGia = new JLabel("Đơn Giá");
		lblDonGia.setBounds(197, 91, 52, 14);
		panelCTPX.add(lblDonGia);

		spinnerDonGia = new JSpinner();
		spinnerDonGia.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(1)));
		spinnerDonGia.setEnabled(false);
		spinnerDonGia.setBounds(253, 88, 86, 20);
		panelCTPX.add(spinnerDonGia);

		btnKhoOption = new JButton("Chọn Kho Hàng");
		btnKhoOption.setEnabled(false);
		btnKhoOption.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnKhoOption.setBounds(234, 96, 105, 23);
		panelDatHang.add(btnKhoOption);
		panelInfo.setLayout(gl_panelInfo);
		panel_2.setLayout(gl_panel_2);

		panel_4.remove(getBtnChuyenChiNhanh());

		JMenuBar menuBar = new JMenuBar();
		panel_4.add(menuBar);

		mnOption = new JMenu("Chọn chế độ");
		mnOption.setBackground(Color.LIGHT_GRAY);
		mnOption.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnOption);

		mntmPhieuXuat = new JMenuItem("Phiếu xuất");
		mnOption.add(mntmPhieuXuat);

		mntmCTPX = new JMenuItem("Chi tiết phiếu xuất");
		mnOption.add(mntmCTPX);

		lblHoTenNV = new JLabel("");
		lblHoTenNV.setBounds(214, 75, 125, 20);
		panelDatHang.add(lblHoTenNV);

		lblTenKho = new JLabel("");
		lblTenKho.setBounds(133, 97, 103, 20);
		panelDatHang.add(lblTenKho);

		lblTenVT = new JLabel("");
		lblTenVT.setBounds(194, 22, 166, 20);
		panelCTPX.add(lblTenVT);

//      load chi nhánh lên combobox
		loadChiNhanh();

//		Phiếu xuất table
		dao = PhieuXuatDao.getInstance();
		loadDataIntoTable();

//		Chi tiết phiếu xuất table
		ctpxDao = CTPXDao.getInstace();
		ctpxModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCTPX.setModel(ctpxModel);
		ctpxModel = (DefaultTableModel) tableCTPX.getModel();
		loadDataIntoTableCTPX();

//		CONGTY có thể chọn chi nhánh để xem dữ liệu
		comboBox.addItemListener(l -> loadDataOtherServer(l));
//		lắng nghe sự kiện chọn row đồng thời in dữ liệu ra textfield cho bảng phiếu xuất
		selectionListenerCTPX = e -> {
			String sql = "SELECT MAVT FROM Vattu WHERE TENVT = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, tableCTPX.getValueAt(tableCTPX.getSelectedRow(), 1).toString());
			try {
				Program.myReader.next();
				textFieldMaVT.setText(Program.myReader.getString(1));
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			getLblTenVT().setText(tableCTPX.getValueAt(tableCTPX.getSelectedRow(), 1).toString());
			spinnerSoLuong.setValue(tableCTPX.getValueAt(tableCTPX.getSelectedRow(), 2));
			spinnerDonGia.setValue(tableCTPX.getValueAt(tableCTPX.getSelectedRow(), 3));
						
		};
		selectionListener = e -> {
			textFieldMaPX.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			try {
				ngay.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(table.getValueAt(table.getSelectedRow(), 1).toString()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			textFieldTenKH.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			
			/* Lấy manv giựa trên họ tên */
			String sql = "SELECT MANV FROM NhanVien WHERE Ho + ' ' + Ten = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, table.getValueAt(table.getSelectedRow(), 3).toString());
			try {
				Program.myReader.next();
				textFieldMaNV.setText(Program.myReader.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			getLblHoTenNV().setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			
			
			/* Lấy makho giựa trên tenkho */
			sql = "SELECT MAKHO FROM KHO WHERE TENKHO = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, table.getValueAt(table.getSelectedRow(), 4).toString());
			try {
				Program.myReader.next();
				textFieldMaKho.setText(Program.myReader.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			sql = "SELECT TENKHO FROM KHO WHERE MAKHO = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, getTextFieldMaKho().getText());
			try {
				Program.myReader.next();
				getLblTenKho().setText(Program.myReader.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			loadDataIntoTableCTPX();
		};
		table.getSelectionModel().addListSelectionListener(selectionListener);

//		lắng nghe sự kiện chọn row đồng thời in dữ liệu ra textfield cho bảng ctpx

//		selectionListener = e -> {
//			textFieldMaVT.setText(tableCTPX.getValueAt(tableCTPX.getSelectedRow(), 1).toString()); 
//			
//			
//			spinnerSoLuong.setValue(tableCTPX.getValueAt(tableCTPX.getSelectedRow(), 2));
//			spinnerDonGia.setValue(tableCTPX.getValueAt(tableCTPX.getSelectedRow(), 3));
//						
//		};
//		tableCTPX.setEnabled(false);
//		tableCTPX.getSelectionModel().addListSelectionListener(selectionListener);
//
		PhieuXuatController ac = new PhieuXuatController(this);
		ac.initController();
	}

	public JButton getBtnVatTuOption() {
		return btnVatTuOption;
	}

	public JButton getBtnKhoOption() {
		return btnKhoOption;
	}

	public JTextField getTextFieldMaPX() {
		return textFieldMaPX;
	}

	public JTextField getTextFieldTenKH() {
		return textFieldTenKH;
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

	public JDateChooser getNgay() {
		return ngay;
	}

	public JTable getTableCTPX() {
		return tableCTPX;
	}

	public JMenuItem getMntmPhieuXuat() {
		return mntmPhieuXuat;
	}

	public JMenuItem getMntmCTPX() {
		return mntmCTPX;
	}

	public static JSpinner getSpinnerSoLuong() {
		return spinnerSoLuong;
	}

	public static JSpinner getSpinnerDonGia() {
		return spinnerDonGia;
	}

	public DefaultTableModel getCtpxModel() {
		return ctpxModel;
	}

	public JLabel getLblHoTenNV() {
		return lblHoTenNV;
	}

	public static JLabel getLblTenKho() {
		return lblTenKho;
	}

	public static JLabel getLblTenVT() {
		return lblTenVT;
	}

	public JMenu getMnOption() {
		return mnOption;
	}

	public CTPXDao getCtpxDao() {
		return ctpxDao;
	}

	public ListSelectionListener getSelectionListenerCTPX() {
		return selectionListenerCTPX;
	}

	public KhoOptionFormForPX getKhoOptionFormPx() {
		khoOptionFormPx = new KhoOptionFormForPX();
		return khoOptionFormPx;
	}
	
	


	

	public VatTuOptionFormForPX getVatTuOptionFormPx() {
		String mapx = textFieldMaPX.getText();
		vatTuOptionFormPx = new VatTuOptionFormForPX(mapx);
		return vatTuOptionFormPx;
	}



	public void loadDataIntoTable() {
		SimpleDateFormat sdf = null;
		String ngayHienThi = "";
		String hoTenNV = "";
		String tenKho = "";
		loadData();
		for (PhieuXuatModel px : list) {
			/* format ngày thành dd-mm-yyyy */
			sdf = new SimpleDateFormat("dd-MM-yyyy");
			ngayHienThi = sdf.format(px.getNgay()) ;
			/* hiển thị tên nhân viên thay cho mã */
			String sql = "SELECT Ho + ' ' + Ten FROM NhanVien WHERE MANV = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, px.getManv());
			try {
				Program.myReader.next();
				hoTenNV = Program.myReader.getString(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/* hiển thị tên nhân viên thay cho mã */
			sql = "SELECT TENKHO FROM KHO WHERE MAKHO = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, px.getMaKho());
			try {
				Program.myReader.next();
				tenKho = Program.myReader.getString(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Object[] rowData = { px.getMapx(), ngayHienThi, px.getHoTenKH(), hoTenNV, tenKho };
			model.addRow(rowData);
		}
	}

	public void loadDataIntoTableCTPX() {
		tableCTPX.getSelectionModel().removeListSelectionListener(selectionListenerCTPX);
		ctpxModel.setColumnIdentifiers(ctpxDao.getColName());
		ctpxModel.setRowCount(0);
		ctpxList = ctpxDao.selectAllByMaPX(textFieldMaPX.getText());
		String tenVT = "";
		for (CTPXModel px : ctpxList) {
			String sql = "SELECT TENVT FROM VATTU WHERE MAVT = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, px.getMavt());
			try {
				Program.myReader.next();
				tenVT = Program.myReader.getString(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Object[] rowData = { px.getMapx(), tenVT, px.getSoLuong(), px.getDonGia() };
			ctpxModel.addRow(rowData);
		}
		
		if (tableCTPX.getRowCount() > 0) {
			tableCTPX.getSelectionModel().addListSelectionListener(selectionListenerCTPX);
			tableCTPX.getSelectionModel().setSelectionInterval(0, 0);
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
