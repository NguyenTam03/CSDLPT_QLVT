package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import javax.swing.JOptionPane;

import common.method.Formatter;
import common.method.ISearcher;
import main.Program;
import model.PhieuXuatModel;
import model.CTPXModel;
import views.PhieuXuatForm;

public class PhieuXuatController implements ISearcher {
	private PhieuXuatForm px;
	private PhieuXuatModel phieuXuatModel;
	private CTPXModel ctpxModel;
	private Stack<String> undoList;
	private int row;
	private boolean isCTPX, isPhieuXuat;
//	private ListSelectionListener selectionListener_CTPX;

	public PhieuXuatController(PhieuXuatForm px) {
		this.px = px;
		phieuXuatModel = new PhieuXuatModel();
		undoList = new Stack<String>();
		row = 0;
		isCTPX = isPhieuXuat = false;

	}

	public void initController() {
		px.getMntmPhieuXuat().addActionListener(l -> initPhieuXuat());
		px.getBtnThoat().addActionListener(l -> exitPhieuXuat());
//		px.getMntmPhieuXuat().addActionListener(l -> btnCheDoPhieuXuatClicked());
		px.getMntmCTPX().addActionListener(l -> initCTPX());
		
		px.getBtnThem().addActionListener(l -> addPhieuXuatCTPX());
		px.getBtnLamMoi().addActionListener(l -> reFreshData());
		px.getBtnHoanTac().addActionListener(l -> btnUndo());
		px.getBtnGhi().addActionListener(l -> btnGhi());
		px.getBtnXoa().addActionListener(l -> deleteData());
		
		px.getBtnKhoOption().addActionListener(l -> openKhoForm());
		px.getBtnVatTuOption().addActionListener(l -> openVatTuForm());

		px.getTextFieldTim().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
	}

	private void initPhieuXuat() {
		isPhieuXuat = true;
		px.getTable().setEnabled(true);
		px.getTableCTPX().setEnabled(true);
		px.getSpinnerSoLuong().setEnabled(false);
		px.getSpinnerDonGia().setEnabled(false);
//		px.getBtnThem().setEnabled(true);
//		px.getBtnXoa().setEnabled(true);
//		px.getBtnGhi().setEnabled(true);
//		px.getBtnHoanTac().setEnabled(false);
//		px.getBtnLamMoi().setEnabled(true);
//		px.getBtnKhoOption().setEnabled(true);
//		px.getTextFieldMaKho().setEditable(true);

//		px.getTable().getSelectionModel().addListSelectionListener(px.getSelectionListener());
//		px.getTableCTPX().getSelectionModel().removeListSelectionListener(px.getSelectionListener());

		if (!Program.mGroup.equals("CONGTY")) {
			px.getBtnThem().setEnabled(true);
			if (px.getTable().getRowCount() > 0) {
				px.getBtnXoa().setEnabled(true);
			}
			px.getComboBox().setEnabled(false);

			px.getMnOption().setText("Phiếu Xuất");
			px.getBtnHoanTac().setEnabled(false);
			px.getBtnLamMoi().setEnabled(true);
			px.getBtnKhoOption().setEnabled(true);
			px.getTextFieldTenKH().setEditable(true);
			px.getTextFieldTim().setEditable(true);

		} else {
			px.getComboBox().setEnabled(true);

		}
	}

	private void initCTPX() {
		isCTPX = true;
		if (px.getTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Chưa chọn phiếu xuất");
			return;
		}

		rowSelectedPX = px.getTable().getSelectedRow();
		isPhieuXuat = false;
		selectionListener_CTPX = e -> {
			if (px.getTableCTPX().getSelectedRow() != -1) {
				px.getTextFieldMaVT().setText(
						px.getTableCTPX().getValueAt(px.getTableCTPX().getSelectedRow(), 1).toString());
				String sql = "SELECT TENVT FROM VATTU WHERE MAVT = ?";
				Program.myReader = Program.ExecSqlDataReader(sql, px.getTextFieldMaVT().getText());
				try {
					Program.myReader.next();
					px.getLblTenVT().setText(Program.myReader.getString(1));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				px.getSpinnerSoLuong()
						.setValue(px.getTableCTPX().getValueAt(px.getTableCTPX().getSelectedRow(), 2));
				px.getSpinnerDonGia().setValue(Formatter.formatMoneyToFloat(
						px.getTableCTPX().getValueAt(px.getTableCTPX().getSelectedRow(), 3)));
			}
		};
		px.getTable().setEnabled(false);
		px.getTableCTPX().setEnabled(true);
		px.getBtnKhoOption().setEnabled(false);
		px.getTextFieldTenKH().setEditable(false);
		if (!Program.mGroup.equals("CONGTY")) {
			px.getBtnThem().setEnabled(true);
			px.getBtnVatTuOption().setEnabled(true);
			if (px.getTable().getRowCount() > 0) {
				px.getBtnXoa().setEnabled(true);

			}
			px.getBtnGhi().setEnabled(true);
			px.getBtnHoanTac().setEnabled(false);
			px.getBtnLamMoi().setEnabled(true);
			px.getMnOption().setText("Chi Tiết Phiếu Xuất");
			px.getSpinnerSoLuong().setEnabled(true);
			px.getSpinnerSoLuong().setEnabled(true);
		} else {
			px.getComboBox().setEnabled(true);
		}
	}

	private void exitPhieuXuat() {
		Program.frmMain.getTabbedPane_Main().removeTabAt(Program.frmMain.getTabbedPane_Main().getSelectedIndex());
		Program.frmMain.getPanel_phieuxuat().remove(px);
	}

	private void btnCheDoPhieuXuatClicked() {
		/* Bật chức năng của phiếu xuất */
		px.getTextFieldMaPX().setEnabled(false);
		px.getNgay().setEnabled(false);

		px.getTextFieldTenKH().setEnabled(false);
		px.getTextFieldMaNV().setEnabled(false);

		px.getBtnKhoOption().setEnabled(true);
		px.getTextFieldMaKho().setEnabled(false);

		/* Tắt chức năng của chi tiết phiếu xuất */
		px.getTextFieldMaVT().setEnabled(false);
		px.getBtnVatTuOption().setEnabled(false);
		px.getSpinnerSoLuong().setEnabled(false);
		px.getSpinnerDonGia().setEnabled(false);

		/* Bật bảng px và ctpx */
		px.getTable().setEnabled(true);
		px.getTableCTPX().setEnabled(true);
	}

	private void btnCheDoChiTietPhieuXuatClicked() {
		/* Tắt chức năng của phiếu xuất */
		px.getTextFieldMaPX().setEnabled(false);
		px.getNgay().setEnabled(false);

		px.getTextFieldTenKH().setEnabled(false);
		px.getTextFieldMaNV().setEnabled(false);

		px.getBtnKhoOption().setEnabled(false);
		px.getTextFieldMaKho().setEnabled(false);

		/* Bật chức năng của chi tiết phiếu xuất */
		px.getTextFieldMaVT().setEnabled(false);
		px.getBtnVatTuOption().setEnabled(true);
		px.getSpinnerSoLuong().setEnabled(false);
		px.getSpinnerDonGia().setEnabled(false);

		/* Bật bảng px và ctpx */
		px.getTable().setEnabled(true);
		px.getTableCTPX().setEnabled(true);
	}

	private void addPhieuXuatCTPX() {
		px.getTextFieldTim().setEnabled(false);
		px.getBtnHoanTac().setEnabled(true);
		px.getBtnThem().setEnabled(false);
		px.getBtnGhi().setEnabled(true);
		px.getBtnLamMoi().setEnabled(false);
		px.getBtnThoat().setEnabled(false);
		px.getBtnXoa().setEnabled(false);
		px.getMnOption().setEnabled(false);
		if (isPhieuXuat) {
			row = px.getTable().getSelectedRow();
			px.getTextFieldMaPX().setEditable(true);
			px.getTextFieldMaPX().setText("");
			Date currentDate = Calendar.getInstance().getTime();
			px.getNgay().setDate(currentDate);
			px.getNgay().setEnabled(false);

			px.getTextFieldTenKH().setEditable(true);
			px.getTextFieldTenKH().setText("");
			px.getTextFieldMaNV().setText(Program.username);
			
			px.getBtnKhoOption().setEnabled(true);
			PhieuXuatForm.getTextFieldMaKho().setText("");
			PhieuXuatForm.getLblTenKho().setText("");
			
			
			px.getTable().getSelectionModel().removeListSelectionListener(px.getSelectionListener());
			px.getTable().getSelectionModel().clearSelection();
//			px.getTextFieldMaVT().setEnabled(false);
//			px.getBtnVatTuOption().setEnabled(false);
//			px.getSpinnerSoLuong().setEnabled(false);
//			px.getSpinnerDonGia().setEnabled(false);

		}

		if (isCTPX) {

			if (!Program.username.equals(px.getTextFieldMaNV().getText())) {
				JOptionPane.showMessageDialog(null,
						"Không thể thêm chi tiết phiếu xuất trên phiếu không phải do mình tạo!", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
//			px.getTextFieldMaPX().setEditable(true);
//			Date currentDate = Calendar.getInstance().getTime();
//			px.getNgay().setDate(currentDate);
//			px.getNgay().setEnabled(false);
//
//			px.getTextFieldTenKH().setEditable(true);
//			px.getTextFieldMaNV().setText(Program.username);
//
//			px.getBtnKhoOption().setEnabled(true);
//
//			px.getTextFieldMaVT().setEnabled(false);
//			px.getBtnVatTuOption().setEnabled(true);
//
//			px.getSpinnerSoLuong().setEnabled(true);
//			px.getSpinnerSoLuong().setValue(1);
//
//			px.getSpinnerDonGia().setEnabled(true);
//			px.getSpinnerDonGia().setValue(1);
			
			row = px.getTableCTPX().getSelectedRow();
			px.getBtnVatTuOption().setEnabled(true);
			PhieuXuatForm.getSpinnerDonGia().setEnabled(true);
			px.getSpinnerSoLuong().setValue(0);
			px.getSpinnerDonGia().setValue(0);
			
			px.getTableCTPX().getSelectionModel().removeListSelectionListener(px.getSelectionListenerCTPX());
			px.getTableCTPX().getSelectionModel().clearSelection();
			px.getTableCTPX().setEnabled(false);

		}

		px.getTable().setEnabled(false);

	}
	
	private void btnUndo() {
		/* hoàn tác sự kiện click btnthem */
		if (!px.getBtnThem().isEnabled()) {
			px.getTextFieldMaPX().setEditable(false);
			px.getNgay().setEnabled(false);
			px.getTextFieldTim().setEnabled(true);
			px.getTable().setEnabled(true);

			px.getBtnThem().setEnabled(true);
			px.getBtnXoa().setEnabled(true);
			px.getBtnLamMoi().setEnabled(true);
			px.getBtnThoat().setEnabled(true);
			px.getMnOption().setEnabled(true);

//			trở về dòng được select hiện tại
			px.getTable().getSelectionModel().addListSelectionListener(px.getSelectionListener());
			px.getTable().getSelectionModel().setSelectionInterval(row, row);

			if (undoList.empty()) {
				px.getBtnHoanTac().setEnabled(false);
			}
			return;
		}
		
		String sqlUndo = "";
		sqlUndo = undoList.pop();
		try {
			Program.ExecSqlDML(sqlUndo);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi, undo không thành công!", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		reFreshData();
		if (row <= px.getTable().getRowCount() - 1) {
			px.getTable().getSelectionModel().setSelectionInterval(row, row);
		}
		if (undoList.empty()) {
			px.getBtnHoanTac().setEnabled(false);
			return;
		}
	}
	
	private boolean checkInputData() {
		if (isPhieuXuat) {
			if (px.getTextFieldMaPX().equals("")) {
				JOptionPane.showMessageDialog(null, "Mã phiếu xuất không được bỏ trống.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (px.getTextFieldMaPX().getText().length() > 8) {
				JOptionPane.showMessageDialog(null, "Mã phiếu xuất không thể quá 8 kí tự.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (px.getTextFieldTenKH().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Tên khách hàng không được bỏ trống.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (px.getTextFieldTenKH().getText().length() > 100) {
				JOptionPane.showMessageDialog(null, "Tên khách hàng không quá 100 kí tự.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (px.getTextFieldMaKho().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Không bỏ trống mã kho.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		
		if (isCTPX) {
			if (!Program.username.equals(px.getTextFieldMaNV().getText())) {
				JOptionPane.showMessageDialog(null, "Không thể thêm chi tiết phiếu xuất với phiếu xuất do người khác tạo.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (px.getTextFieldMaPX().equals("")) {
				JOptionPane.showMessageDialog(null, "Mã phiếu xuất không được bỏ trống.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (px.getTextFieldMaPX().getText().length() > 8) {
				JOptionPane.showMessageDialog(null, "Mã phiếu xuất không thể quá 8 kí tự.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (px.getTextFieldMaVT().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Mã vật tư không được bỏ trống.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (px.getTextFieldMaVT().getText().length() > 4) {
				JOptionPane.showMessageDialog(null, "Mã vật tư không thể quá 4 kí tự.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			String sql = "SELECT SOLUONGTON FROM VATTU WHERE = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, px.getTextFieldMaVT().getText());
			int soLuongTon = 0;
			try {
				Program.myReader.next();
				soLuongTon = Program.myReader.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (Integer.valueOf(px.getSpinnerSoLuong().getValue().toString()) > soLuongTon) {
				JOptionPane.showMessageDialog(null, "Số lượng vật tư không thể lớn hơn số lượng vật tư đang có trong kho hàng.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	private void btnGhi() {
		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn ghi dữ liệu vào bảng không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (reply == JOptionPane.YES_OPTION) {
			if (isPhieuXuat) {
				row = px.getTable().getSelectedRow();
				String mapx = px.getTextFieldMaPX().getText().trim();
				Integer manv = Integer.parseInt(px.getTextFieldMaNV().getText());
				String tenKH = px.getTextFieldTenKH().getText();
				String makho = PhieuXuatForm.getTextFieldMaKho().getText();
				java.sql.Date ngay = new java.sql.Date(px.getNgay().getDate().getTime());
				phieuXuatModel.setMapx(mapx);
				phieuXuatModel.setNgay(ngay);
				phieuXuatModel.setManv(manv);
				phieuXuatModel.setMaKho(makho);
				phieuXuatModel.setHoTenKH(tenKH);
				if (checkInputData()) {
					if (!px.getBtnThem().isEnabled()) {
						addDataToDB();
					} else {
						upDateDataToDB();
					}
				}
			}
			
			if (isCTPX) {
				row = px.getTableCTPX().getSelectedRow();
				String mapx = px.getTextFieldMaPX().getText();
				String mavt = PhieuXuatForm.getTextFieldMaVT().getText();
				Integer soLuong = (Integer) px.getSpinnerSoLuong().getValue();
				Float donGia = (Float) px.getSpinnerDonGia().getValue();
				ctpxModel.setMavt(mavt);
				ctpxModel.setSoLuong(soLuong);
				ctpxModel.setDonGia(donGia);
				ctpxModel.setMapx(mapx);
				if (checkInputData()) {
					if (!px.getBtnThem().isEnabled()) {
						addDataToDB();
					} else {
						upDateDataToDB();
					}
				}
			}
		}
	}
		
	private void addDataToDB() {
		String sql = "";
		int res = 0;
		if (isPhieuXuat) {
			// Execute query
			sql = "{? = call dbo.sp_KiemTraMaPhieuXuat(?)}";
			res = 0;
			try {
				res = Program.ExecSqlNoQuery(sql, phieuXuatModel.getMapx());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi kiểm tra phiếu xuất!\n" + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// execute return 1, don da ton tai
			if (res == 1) {
				JOptionPane.showMessageDialog(null,
						"Đã tồn tại mã phiếu xuất " + phieuXuatModel.getMapx() + " này, vui lòng nhập lại", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					Object[] newRow = { phieuXuatModel.getMapx(), Formatter.formatterDate(phieuXuatModel.getNgay()),
							phieuXuatModel.getHoTenKH(), phieuXuatModel.getManv(), phieuXuatModel.getMaKho() };
					px.getModel().addRow(newRow);
					px.getDao().insert(phieuXuatModel);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Lỗi thêm kho!\n" + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					reFreshData();
					px.getTable().getSelectionModel().setSelectionInterval(row, row);
					return;
				}
			}
		
			JOptionPane.showMessageDialog(null, "Ghi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

			px.getTable().setEnabled(true);
			px.getBtnThem().setEnabled(true);
			px.getBtnXoa().setEnabled(true);
			px.getBtnLamMoi().setEnabled(true);
			px.getBtnThoat().setEnabled(true);
			px.getBtnHoanTac().setEnabled(true);
			px.getTextFieldTim().setEnabled(true);
			px.getTable().getSelectionModel().addListSelectionListener(px.getSelectionListener());
			px.getTable().getSelectionModel().setSelectionInterval(px.getTable().getRowCount() - 1,
					px.getTable().getRowCount() - 1);
			px.getList().add(phieuXuatModel);
			row = px.getTable().getRowCount() - 1;
			// Luu truy van de hoan tac yeu cau them
			String sqlUndo;
			sqlUndo = "DELETE FROM PhieuXuat WHERE MAPX = '" + phieuXuatModel.getMapx() + "'";
			undoList.push(sqlUndo);
		}
		
		if (isCTPX) {
			
		}
	}
	private void upDateDataToDB() {
		String tenKH = px.getTable().getValueAt(px.getTable().getSelectedRow(), 2).toString();
		String maKho = px.getTable().getValueAt(px.getTable().getSelectedRow(), 3).toString();

		try {
			px.getTable().setValueAt(phieuXuatModel.getHoTenKH(), px.getTable().getSelectedRow(), 2);
			px.getTable().setValueAt(phieuXuatModel.getMaKho(), px.getTable().getSelectedRow(), 3);
			px.getDao().update(phieuXuatModel);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi update kho!\n" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			reFreshData();
			px.getTable().getSelectionModel().setSelectionInterval(row, row);
			return;
		}

		JOptionPane.showMessageDialog(null, "Ghi thành công!", "Thông báo", JOptionPane.OK_OPTION);
		px.getBtnHoanTac().setEnabled(true);
		px.getTable().getSelectionModel().setSelectionInterval(row, row);
		px.getList().set(row, phieuXuatModel);
		// Luu truy van de hoan tac yeu cau update
		String sqlUndo;
		sqlUndo = "UPDATE PhieuXuat SET HOTENKH = '" + tenKH + "'," + "MAKHO = '" + maKho + "' " + "WHERE MAPX = '"
				+ phieuXuatModel.getMapx() + "'";
		undoList.push(sqlUndo);
	}
	
	private void deleteData() {
		/* chưa chọn hàng thì không được xóa */
		if (isPhieuXuat) {
			if (px.getTable().getSelectedRow() == -1) {
				return;
			}
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dữ liệu này không?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION) {
				return;
			}
			String sql = "SELECT * FROM CTPX WHERE MAPX = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, px.getTextFieldMaPX().getText());
			try {
				if (Program.myReader.next()) {
					JOptionPane.showMessageDialog(null, "Không thể xóa phiếu nhập khi đã tồn tại vật tư", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			set dữ liệu của row đó
			String mapx = px.getTextFieldMaPX().getText().trim();
			Integer manv = Integer.parseInt(px.getTextFieldMaNV().getText());
			String tenKH = px.getTextFieldTenKH().getText();
			String makho = px.getTextFieldMaKho().getText();
			java.sql.Date ngay = new java.sql.Date(px.getNgay().getDate().getTime());
			phieuXuatModel.setMapx(mapx);
			phieuXuatModel.setNgay(ngay);
			phieuXuatModel.setManv(manv);
			phieuXuatModel.setMaKho(makho);
			phieuXuatModel.setHoTenKH(tenKH);

			row = px.getTable().getSelectedRow();
			try {
				px.getTable().getSelectionModel().removeListSelectionListener(px.getSelectionListener());
				px.getModel().removeRow(px.getTable().getSelectedRow());
				px.getDao().delete(phieuXuatModel);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi xóa phiếu xuất!", "Error", JOptionPane.ERROR_MESSAGE);
				reFreshData();
				px.getTable().getSelectionModel().setSelectionInterval(row, row);
				return;
			}

			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			// listener selection row
			px.getTable().getSelectionModel().addListSelectionListener(px.getSelectionListener());
			if (px.getTable().getRowCount() > 0) {
				if (row == 0) {
					px.getTable().getSelectionModel().setSelectionInterval(row + 1, row + 1);
				} else {
					px.getTable().getSelectionModel().setSelectionInterval(row - 1, row - 1);
				}
			}
			px.getBtnHoanTac().setEnabled(true);
			px.getList().remove(row);

			String sqlUndo = "INSERT INTO PHIEUXUAT (MAPX, NGAY, HOTENKH, MANV, MAKHO) VALUES ('" + phieuXuatModel.getMapx()
					+ "', '" + phieuXuatModel.getNgay() + "', '" + phieuXuatModel.getHoTenKH() + "', '" + phieuXuatModel.getManv() + "', '"
					+ phieuXuatModel.getMaKho() + "')";
			undoList.push(sqlUndo);

			if (px.getTable().getRowCount() == 0) {
				px.getBtnXoa().setEnabled(false);
			}
			
		}
		
		if (isCTPX) {
			if (px.getTable().getSelectedRow() == -1) {
				return;
			}
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dữ liệu này không?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION) {
				return;
			}

//			set dữ liệu của row đó
			String mapx = px.getTextFieldMaPX().getText().trim();
			String mavt = px.getTextFieldMaVT().getText();
			Integer soLuong =  (Integer) px.getSpinnerSoLuong().getValue();
			Float dongia = (Float) px.getSpinnerDonGia().getValue();
			java.sql.Date ngay = new java.sql.Date(px.getNgay().getDate().getTime());
			ctpxModel.setMapx(mapx);
			ctpxModel.setMavt(mavt);
			ctpxModel.setSoLuong(soLuong);
			ctpxModel.setDonGia(dongia);

			row = px.getTable().getSelectedRow();
			try {
				px.getTableCTPX().getSelectionModel().removeListSelectionListener(px.getSelectionListenerCTPX());
				px.getCtpxModel().removeRow(px.getTableCTPX().getSelectedRow());
				px.getCtpxDao().delete(ctpxModel);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết phiếu xuất!", "Error", JOptionPane.ERROR_MESSAGE);
				reFreshData();
				px.getTableCTPX().getSelectionModel().setSelectionInterval(row, row);
				return;
			}

			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			// listener selection row
			px.getTableCTPX().getSelectionModel().addListSelectionListener(px.getSelectionListenerCTPX());
			if (px.getTable().getRowCount() > 0) {
				if (row == 0) {
					px.getTable().getSelectionModel().setSelectionInterval(row + 1, row + 1);
				} else {
					px.getTable().getSelectionModel().setSelectionInterval(row - 1, row - 1);
				}
			}
			px.getBtnHoanTac().setEnabled(true);
			px.getList().remove(row);

			String sqlUndo = "INSERT INTO CTPX (MAPX, MAVT, SOLUONG, DONGIA) VALUES ('" + ctpxModel.getMapx()
					+ "', '" + ctpxModel.getMavt() + "', '" + ctpxModel.getSoLuong() + "', '" + ctpxModel.getDonGia() + "')";
			undoList.push(sqlUndo);

			if (px.getTable().getRowCount() == 0) {
				px.getBtnXoa().setEnabled(false);
			}
		}
		
		
	}

	private void reFreshData() {
		/* Refresh bảng phiếu xuất */
		px.getTable().getSelectionModel().removeListSelectionListener(px.getSelectionListener());
		px.getModel().setRowCount(0);
		px.loadDataIntoTable();
		px.getTable().getSelectionModel().addListSelectionListener(px.getSelectionListener());
		px.getTable().getSelectionModel().setSelectionInterval(0, 0);

//		/* Refresh bảng ctpx */
//		px.getTableCTPX().getSelectionModel().removeListSelectionListener(px.getSelectionListener());
//		px.getCtphieuXuatModel().setRowCount(0);
//		px.loadDataIntoTableCTPX();
//		px.getTableCTPX().getSelectionModel().addListSelectionListener(px.getSelectionListener());
//		px.getTableCTPX().getSelectionModel().setSelectionInterval(0, 0);

	}
	
	
	private void openKhoForm() {
		px.getKhoOptionFormPx().setVisible(true);
	}
	private void openVatTuForm() {
		px.getVatTuOptionFormPx().setVisible(true);
	}
	@Override
	public void search() {
		String input = px.getTextFieldTim().getText().trim().toLowerCase();
		px.getTable().getSelectionModel().removeListSelectionListener(px.getSelectionListener());
		px.getModel().setRowCount(0);

		for (PhieuXuatModel model : px.getList()) {
			if (model.getMapx().toLowerCase().contains(input) || model.getHoTenKH().toLowerCase().contains(input)
					|| model.getMaKho().toLowerCase().contains(input) || model.getManv().toString().contains(input)) {
				Object[] rowData = { model.getMapx(), model.getNgay(), model.getHoTenKH(), model.getManv(),
						model.getMaKho() };
				px.getModel().addRow(rowData);
			}
		}
		px.getTable().getSelectionModel().addListSelectionListener(px.getSelectionListener());
		if (px.getTable().getRowCount() > 0) {
			px.getTable().getSelectionModel().setSelectionInterval(0, 0);
		}

	}

}
