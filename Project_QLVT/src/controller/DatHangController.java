package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.Stack;

import javax.swing.JOptionPane;

import common.method.Formatter;
import common.method.ISearcher;
import common.method.IValidation;
import main.Program;
import model.CTDDHModel;
import model.DatHangModel;
import views.DatHangForm;
import views.KhoOptionForm;

public class DatHangController implements ISearcher {
	private DatHangForm dh;
	private DatHangModel dhModel;
	private CTDDHModel ctdhModel;
	private Stack<String> undoList;
	private int row;

	private enum Mode {
		DONDATHANG, CTDDH
	}

	private Mode mode;

	public DatHangController(DatHangForm dh) {
		this.dh = dh;
		dhModel = new DatHangModel();
		ctdhModel = new CTDDHModel();
		undoList = new Stack<String>();
		row = 0;
	}

	public void initController() {
		dh.getMntmDatHang().addActionListener(l -> initDatHang());
		dh.getMntmCTDH().addActionListener(l -> initCTDatHang());
		dh.getBtnThem().addActionListener(l -> addDatHang());
		dh.getBtnHoanTac().addActionListener(l -> btnUndo());
		dh.getBtnKhoOption().addActionListener(l -> openKhoForm());
		dh.getBtnVatTuOption().addActionListener(l -> openVatTuForm());
		dh.getBtnGhi().addActionListener(l -> btnGhiListener());
		dh.getBtnLamMoi().addActionListener(l -> reFreshData());
		dh.getBtnXoa().addActionListener(l -> deleteData());
		dh.getTextFieldTim().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
	}

	private void initDatHang() {
		mode = Mode.DONDATHANG;
		dh.getTable().setEnabled(true);
		dh.getTableCTDH().setEnabled(true);
		dh.getBtnVatTuOption().setEnabled(false);
		dh.getSpinnerDonGia().setEnabled(false);
		dh.getSpinnerSoLuong().setEnabled(false);
		if (!Program.mGroup.equals("CONGTY")) {
			dh.getBtnThem().setEnabled(true);
			if (dh.getTable().getRowCount() > 0) {
				dh.getBtnXoa().setEnabled(true);
			}

			dh.getBtnGhi().setEnabled(true);
			dh.getBtnHoanTac().setEnabled(false);
			dh.getBtnLamMoi().setEnabled(true);
			dh.getBtnKhoOption().setEnabled(true);
			dh.getMnOption().setText("Đặt hàng");
			dh.getTextFieldNCC().setEditable(true);
			dh.getTextFieldTim().setEnabled(true);
		} else {
			dh.getComboBox().setEnabled(true);
		}
	}

	private void initCTDatHang() {
		mode = Mode.CTDDH;
		dh.getTable().setEnabled(true);
		dh.getTableCTDH().setEnabled(true);
		dh.getBtnKhoOption().setEnabled(false);
		dh.getTextFieldNCC().setEditable(false);
		if (!Program.mGroup.equals("CONGTY")) {
			dh.getBtnThem().setEnabled(true);
			dh.getBtnVatTuOption().setEnabled(true);

			// có data thì mới được xóa
			if (dh.getTable().getRowCount() > 0) {
				dh.getBtnXoa().setEnabled(true);
			}
			dh.getBtnGhi().setEnabled(true);
			dh.getBtnHoanTac().setEnabled(false);
			dh.getBtnLamMoi().setEnabled(true);
			dh.getMnOption().setText("CT đặt hàng");
			dh.getSpinnerSoLuong().setEnabled(true);
			dh.getSpinnerDonGia().setEnabled(true);
		} else {
			dh.getComboBox().setEnabled(true);
		}
	}

	private void addDatHang() {
		dh.getTextFieldTim().setEnabled(false);
		dh.getBtnHoanTac().setEnabled(true);
		dh.getBtnThem().setEnabled(false);
		dh.getBtnGhi().setEnabled(true);
		dh.getBtnLamMoi().setEnabled(false);
		dh.getBtnThoat().setEnabled(false);
		dh.getBtnXoa().setEnabled(false);
		dh.getMnOption().setEnabled(false);
		if (mode == Mode.DONDATHANG) {
			row = dh.getTable().getSelectedRow();

			dh.getTextFieldMaDH().setText("");
			dh.getTextFieldMaDH().setEditable(true);
			dh.getNgayDat().setEnabled(true);
			dh.getNgayDat().setDate(new Date());
			dh.getTextFieldNCC().setText("");
			dh.getTextFieldMaNV().setText(Program.username);
			DatHangForm.getTextFieldMaKho().setText("");
			DatHangForm.getTextFieldTenKho().setText("");

			dh.getTable().getSelectionModel().removeListSelectionListener(dh.getSelectionListener());
			dh.getTable().getSelectionModel().clearSelection();
		}
		if (mode == Mode.CTDDH) {
			row = dh.getTableCTDH().getSelectedRow();
			dh.getBtnVatTuOption().setEnabled(true);
			dh.getSpinnerSoLuong().setValue(0);
			dh.getSpinnerDonGia().setValue(0);

			dh.getTableCTDH().getSelectionModel().removeListSelectionListener(dh.getSelectionCTDHListener());
			dh.getTableCTDH().getSelectionModel().clearSelection();
			dh.getTableCTDH().setEnabled(false);
		}
		dh.getTable().setEnabled(false);
	}

	private void btnUndo() {
//		hoan tac su kien click btnthem
		if (!dh.getBtnThem().isEnabled()) {
			dh.getTextFieldMaDH().setEditable(false);
			dh.getNgayDat().setEnabled(false);
			dh.getTextFieldTim().setEnabled(true);
			dh.getTable().setEnabled(true);

			dh.getBtnThem().setEnabled(true);
			dh.getBtnXoa().setEnabled(true);
			dh.getBtnLamMoi().setEnabled(true);
			dh.getBtnThoat().setEnabled(true);
			dh.getMnOption().setEnabled(true);

//			trở về dòng được select hiện tại
			dh.getTable().getSelectionModel().addListSelectionListener(dh.getSelectionListener());
			dh.getTable().getSelectionModel().setSelectionInterval(row, row);

			if (undoList.empty()) {
				dh.getBtnHoanTac().setEnabled(false);
			}
			return;
		}

		String sqlUndo = "";
		try {
			sqlUndo = undoList.pop();
			Program.ExecSqlDML(sqlUndo);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi, undo không thành công!", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		reFreshData();
		if (row <= dh.getTable().getRowCount() - 1) {
			dh.getTable().getSelectionModel().setSelectionInterval(row, row);
		}

		if (undoList.empty()) {
			dh.getBtnHoanTac().setEnabled(false);
			return;
		}
	}

	private boolean validateObject(IValidation object) {
		boolean isValid = object.validate();
		if (mode == Mode.DONDATHANG) {
			switch (DatHangModel.validateError) {
			case ERROR_MASODDH:
				dh.getTextFieldMaDH().requestFocusInWindow();
				break;
			case ERROR_NGAY:
				dh.getNgayDat().requestFocusInWindow();
				break;
			case ERROR_NHACC:
				dh.getTextFieldNCC().requestFocusInWindow();
				break;
			default:
				break;
			}
		}
		if (mode == Mode.CTDDH) {
			switch (CTDDHModel.validateError) {
			case ERROR_MAVT:
				DatHangForm.getTextFieldMaVT().requestFocusInWindow();
				break;
			case ERROR_SOLUONG:
				dh.getSpinnerSoLuong().requestFocusInWindow();
				break;
			case ERROR_DONGIA:
				dh.getSpinnerDonGia().requestFocusInWindow();
				break;
			default:
				break;
			}
		}

		return isValid;
	}

	private void btnGhiListener() {
		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn ghi dữ liệu vào bảng không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (reply == JOptionPane.YES_OPTION) {
			if (mode == Mode.DONDATHANG) {
				row = dh.getTable().getSelectedRow();
				String maDDH = dh.getTextFieldMaDH().getText().trim();
				Integer manv = Integer.parseInt(dh.getTextFieldMaNV().getText());
				String nhacc = dh.getTextFieldNCC().getText();
				String makho = DatHangForm.getTextFieldMaKho().getText();
				java.sql.Date ngay = new java.sql.Date(dh.getNgayDat().getDate().getTime());
				dhModel.setMaSoDDH(maDDH);
				dhModel.setNgay(ngay);
				dhModel.setManv(manv);
				dhModel.setMakho(makho);
				dhModel.setNhaCC(nhacc);
				if (validateObject(dhModel)) {
					if (!dh.getBtnThem().isEnabled()) {
						addDataToDB();
					} else {
						upDateDataToDB();
					}
				}
			}
			if (mode == Mode.CTDDH) {
				row = dh.getTableCTDH().getSelectedRow();
				String maDDH = dh.getTextFieldMaDH().getText();
				String mavt = DatHangForm.getTextFieldMaVT().getText();
				Integer soLuong = (Integer) dh.getSpinnerSoLuong().getValue();
				Float donGia = (Float) dh.getSpinnerDonGia().getValue();
				ctdhModel.setMavt(mavt);
				ctdhModel.setSoLuong(soLuong);
				ctdhModel.setDonGia(donGia);
				ctdhModel.setMaSoDDH(maDDH);
				if (validateObject(ctdhModel)) {
					if (!dh.getBtnThem().isEnabled()) {
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
		if (mode == Mode.DONDATHANG) {
			// Execute query
			sql = "{? = call dbo.sp_TraCuu_MADDH(?)}";
			res = 0;
			try {
				res = Program.ExecSqlNoQuery(sql, dhModel.getMaSoDDH());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi kiểm tra đơn đặt hàng!\n" + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// execute return 1, don da ton tai
			if (res == 1) {
				JOptionPane.showMessageDialog(null,
						"Đã tồn tại mã đơn đặt hàng " + dhModel.getMaSoDDH() + " này, vui lòng nhập lại", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					Object[] newRow = { dhModel.getMaSoDDH(), Formatter.formatterDate(dhModel.getNgay()),
							dhModel.getNhaCC(), dhModel.getManv(), dhModel.getMakho() };
					dh.getModel().addRow(newRow);
					dh.getDao().insert(dhModel);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Lỗi thêm kho!\n" + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					reFreshData();
					dh.getTable().getSelectionModel().setSelectionInterval(row, row);
					return;
				}
			}
		
			JOptionPane.showMessageDialog(null, "Ghi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

			dh.getTable().setEnabled(true);
			dh.getBtnThem().setEnabled(true);
			dh.getBtnXoa().setEnabled(true);
			dh.getBtnLamMoi().setEnabled(true);
			dh.getBtnThoat().setEnabled(true);
			dh.getBtnHoanTac().setEnabled(true);
			dh.getTextFieldTim().setEnabled(true);
			dh.getTable().getSelectionModel().addListSelectionListener(dh.getSelectionListener());
			dh.getTable().getSelectionModel().setSelectionInterval(dh.getTable().getRowCount() - 1,
					dh.getTable().getRowCount() - 1);
			dh.getList().add(dhModel);
			row = dh.getTable().getRowCount() - 1;
			// Luu truy van de hoan tac yeu cau them
			String sqlUndo;
			sqlUndo = "DELETE FROM DatHang WHERE MasoDDH = '" + dhModel.getMaSoDDH() + "'";
			undoList.push(sqlUndo);
		}
		if (mode == Mode.CTDDH) {
			
		}
	}

	private void upDateDataToDB() {
		String nhacc = dh.getTable().getValueAt(dh.getTable().getSelectedRow(), 2).toString();
		String makho = dh.getTable().getValueAt(dh.getTable().getSelectedRow(), 3).toString();

		try {
			dh.getTable().setValueAt(dhModel.getNhaCC(), dh.getTable().getSelectedRow(), 2);
			dh.getTable().setValueAt(dhModel.getMakho(), dh.getTable().getSelectedRow(), 3);
			dh.getDao().update(dhModel);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi update kho!\n" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			reFreshData();
			dh.getTable().getSelectionModel().setSelectionInterval(row, row);
			return;
		}

		JOptionPane.showMessageDialog(null, "Ghi thành công!", "Thông báo", JOptionPane.OK_OPTION);
		dh.getBtnHoanTac().setEnabled(true);
		dh.getTable().getSelectionModel().setSelectionInterval(row, row);
		dh.getList().set(row, dhModel);
		// Luu truy van de hoan tac yeu cau update
		String sqlUndo;
		sqlUndo = "UPDATE DatHang SET NhaCC = '" + nhacc + "'," + "MAKHO = '" + makho + "' " + "WHERE MasoDDH = '"
				+ dhModel.getMaSoDDH() + "'";
		undoList.push(sqlUndo);

	}

	private void reFreshData() {
		dh.getTable().getSelectionModel().removeListSelectionListener(dh.getSelectionListener());
		dh.getModel().setRowCount(0);
		dh.loadDataIntoTable();
		dh.getTable().getSelectionModel().addListSelectionListener(dh.getSelectionListener());
		dh.getTable().getSelectionModel().setSelectionInterval(0, 0);
	}

	private boolean checkPhieuNhap(String masoDDH) {
		String sql = "SELECT DISTINCT MasoDDH FROM PhieuNhap WHERE MasoDDH = ?";
		Program.myReader = Program.ExecSqlDataReader(sql, masoDDH);

		try {
			Program.myReader.next();
			if (Program.myReader.getRow() > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private void deleteData() {
//		chưa chọn hàng thì không xóa được
		if (dh.getTable().getSelectedRow() == -1)
			return;
		if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dữ liệu này không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION) {
			return;
		}
//		set dữ liệu của row đó
		String maDDH = dh.getTextFieldMaDH().getText().trim();
		Integer manv = Integer.parseInt(dh.getTextFieldMaNV().getText());
		String nhacc = dh.getTextFieldNCC().getText();
		String makho = DatHangForm.getTextFieldMaKho().getText();
		java.sql.Date ngay = new java.sql.Date(dh.getNgayDat().getDate().getTime());
		dhModel.setMaSoDDH(maDDH);
		dhModel.setNgay(ngay);
		dhModel.setManv(manv);
		dhModel.setMakho(makho);
		dhModel.setNhaCC(nhacc);
//		check exist
		if (checkPhieuNhap(dhModel.getMaSoDDH())) {
			JOptionPane.showMessageDialog(null, "Không thể xóa đơn đặt hàng này vì đã lập phiếu nhập.", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		row = dh.getTable().getSelectedRow();
		try {
			dh.getTable().getSelectionModel().removeListSelectionListener(dh.getSelectionListener());
			dh.getModel().removeRow(dh.getTable().getSelectedRow());
			dh.getDao().delete(dhModel);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi xóa đơn đặt hàng!", "Error", JOptionPane.ERROR_MESSAGE);
			reFreshData();
			dh.getTable().getSelectionModel().setSelectionInterval(row, row);
			return;
		}

		JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		// listener selection row
		dh.getTable().getSelectionModel().addListSelectionListener(dh.getSelectionListener());
		if (dh.getTable().getRowCount() > 0) {
			if (row == 0) {
				dh.getTable().getSelectionModel().setSelectionInterval(row + 1, row + 1);
			} else {
				dh.getTable().getSelectionModel().setSelectionInterval(row - 1, row - 1);
			}
		}
		dh.getBtnHoanTac().setEnabled(true);
		dh.getList().remove(row);

		String sqlUndo = "INSERT INTO DatHang (MasoDDH, NGAY, NhaCC, MANV, MAKHO) VALUES ('" + dhModel.getMaSoDDH()
				+ "', '" + dhModel.getNgay() + "', '" + dhModel.getNhaCC() + "', '" + dhModel.getManv() + "', '"
				+ dhModel.getMakho() + "')";
		undoList.push(sqlUndo);

		if (dh.getTable().getRowCount() == 0) {
			dh.getBtnXoa().setEnabled(false);
		}
	}

	private void openKhoForm() {
		dh.getKhoOptionForm().setVisible(true);
	}
	
	private void openVatTuForm() {
		dh.getVtOptionForm().setVisible(true);
	}

	@Override
	public void search() {
		String input = dh.getTextFieldTim().getText().trim().toLowerCase();
		dh.getTable().getSelectionModel().removeListSelectionListener(dh.getSelectionListener());
		dh.getModel().setRowCount(0);

		for (DatHangModel d : dh.getList()) {
			if (d.getMakho().toLowerCase().contains(input) || d.getManv().toString().contains(input)
					|| d.getMaSoDDH().contains(input) || d.getNhaCC().contains(input)) {
				Object[] rowData = { d.getMaSoDDH(), Formatter.formatterDate(d.getNgay()), d.getNhaCC(), d.getManv(),
						d.getMakho() };
				dh.getModel().addRow(rowData);
			}
		}
		dh.getTable().getSelectionModel().addListSelectionListener(dh.getSelectionListener());
		if (dh.getTable().getRowCount() > 0) {
			dh.getTable().getSelectionModel().setSelectionInterval(0, 0);
		}
	}
}
