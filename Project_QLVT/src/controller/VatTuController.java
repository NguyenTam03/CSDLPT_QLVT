package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import main.Program;
import model.VattuModel;
import views.VatTuForm;

public class VatTuController {
	private VatTuForm vatTuForm;
	private VattuModel vattuModel;
	private Stack<String> undoList;
	private int row;

	public VatTuController(VatTuForm vatTuForm) {
		this.vatTuForm = vatTuForm;
		vattuModel = new VattuModel();
		undoList = new Stack<String>();
		row = 0;
	}

	public void initController() {
		vatTuForm.getBtnThem().addActionListener(l -> addVatTu());
		vatTuForm.getBtnGhi().addActionListener(l -> pushDataToDB());
		vatTuForm.getBtnLamMoi().addActionListener(l -> reFreshData());
		vatTuForm.getBtnXoa().addActionListener(l -> deleteData());
		vatTuForm.getBtnThoat().addActionListener(l -> exitVatTu());
		vatTuForm.getBtnHoanTac().addActionListener(l -> btnHoanTacClicked());
		autoSearchVatTu();
	}

	private void autoSearchVatTu() {
		vatTuForm.getTextFieldTim().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchVatTu();
			}
		});
	}

	private void searchVatTu() {
		String input = vatTuForm.getTextFieldTim().getText().toLowerCase().trim();
		vatTuForm.getTable().getSelectionModel().removeListSelectionListener(vatTuForm.getSelectionListener());
		vatTuForm.getModel().setRowCount(0);

		for (VattuModel model : vatTuForm.getList()) {
			if (model.getTenVT().toLowerCase().contains(input)) {
				Object[] dataRow = { model.getMavt(), model.getTenVT(), model.getDvt(), model.getSoLuongTon() };
				vatTuForm.getModel().addRow(dataRow);
			}
		}

		vatTuForm.getTable().getSelectionModel().addListSelectionListener(vatTuForm.getSelectionListener());
		if (vatTuForm.getTable().getRowCount() > 0) {
			vatTuForm.getTable().setRowSelectionInterval(0, 0);
		}
	}

	private void exitVatTu() {
		Program.frmMain.getTabbedPane_Main().removeTabAt(Program.frmMain.getTabbedPane_Main().getSelectedIndex());
		Program.frmMain.getPanel_VT().remove(vatTuForm);
	}

	private void addVatTu() {
//		Luu vi tri row dang chon
		row = vatTuForm.getTable().getSelectedRow();
		vatTuForm.getBtnThem().setEnabled(false);
		vatTuForm.getBtnXoa().setEnabled(false);
		vatTuForm.getBtnLamMoi().setEnabled(false);
		vatTuForm.getBtnHoanTac().setEnabled(true);
		vatTuForm.getBtnThoat().setEnabled(false);

		vatTuForm.getTextFieldMaVT().setEditable(true);
		vatTuForm.getTextFieldMaVT().setText("");
		vatTuForm.getTextFieldTenVT().setText("");
		vatTuForm.getTextFieldDonVi().setText("");
		vatTuForm.getSpinner().setValue(0);
		vatTuForm.getTextFieldTim().setEnabled(false);

		vatTuForm.getTable().getSelectionModel().removeListSelectionListener(vatTuForm.getSelectionListener());
		vatTuForm.getTable().getSelectionModel().clearSelection();
		vatTuForm.getTable().setEnabled(false);
	}

	private void btnHoanTacClicked() {
//		hoan tac su kien click btnThem
		if (!vatTuForm.getBtnThem().isEnabled()) {
			vatTuForm.getBtnThem().setEnabled(true);
			vatTuForm.getBtnXoa().setEnabled(true);
			vatTuForm.getBtnLamMoi().setEnabled(true);
			vatTuForm.getBtnThoat().setEnabled(true);

			vatTuForm.getTextFieldMaVT().setEditable(false);
			vatTuForm.getTextFieldMaVT().setText("");
			vatTuForm.getTextFieldTenVT().setText("");
			vatTuForm.getTextFieldDonVi().setText("");
			vatTuForm.getSpinner().setValue(0);
			vatTuForm.getTextFieldTim().setEnabled(true);
			vatTuForm.getTable().setEnabled(true);
//			trở về dòng được trọn trước đó
			vatTuForm.getTable().getSelectionModel().addListSelectionListener(vatTuForm.getSelectionListener());
			vatTuForm.getTable().getSelectionModel().addSelectionInterval(row, row);
			return;
		}

		if (undoList.empty()) {
			vatTuForm.getBtnHoanTac().setEnabled(false);
			return;
		}

		String sqlUndo = undoList.pop();
		if (Program.ExecSqlDML(sqlUndo) == -1)
			return;
		System.out.println(sqlUndo);
		reFreshData();
		if (row <= vatTuForm.getTable().getRowCount() - 1) {
			vatTuForm.getTable().getSelectionModel().setSelectionInterval(row, row);
		}
	}

	private void pushDataToDB() {
		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn ghi dữ liệu vào bảng không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (reply == JOptionPane.YES_OPTION) {
			String maVT = vatTuForm.getTextFieldMaVT().getText().trim();
			String tenVT = vatTuForm.getTextFieldTenVT().getText().trim();
			String donVi = vatTuForm.getTextFieldDonVi().getText().trim();
			Integer soLuong = (Integer) vatTuForm.getSpinner().getValue();

			vattuModel.setMavt(maVT);
			vattuModel.setTenVT(tenVT);
			vattuModel.setDvt(donVi);
			vattuModel.setSoLuongTon(soLuong);
			row = vatTuForm.getTable().getSelectedRow();
			boolean check = checkInputData(vattuModel);
			if (check) {
				if (!vatTuForm.getBtnThem().isEnabled()) {
					addDataToDB(vattuModel);
				} else {
					upDateDataToDB(vattuModel);
				}
			}

		}
	}

	private boolean regexMatch(String patt, String text) {

		Pattern pattern = Pattern.compile(patt, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		return matcher.find();
	}

	private boolean checkInputData(VattuModel vattuModel) {
		/* Kiểm tra mã vật tư */
		if (vattuModel.getMavt().equals("")) {
//			để null vì không cần có frame cha cụ thể
			JOptionPane.showMessageDialog(null, "Mã vật tư không được bỏ trống", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getTextFieldMaVT().setFocusable(true);
			return false;
		}
		if (!regexMatch("^[a-zA-Z0-9]*$", vattuModel.getMavt())) {
			JOptionPane.showMessageDialog(null, "Mã vật tư chỉ gồm chữ cái và số", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getTextFieldMaVT().setFocusable(true);
			return false;
		}

		if (vattuModel.getMavt().length() > 4) {
			JOptionPane.showMessageDialog(null, "Mã vật tư không quá 4 kí tự", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getTextFieldMaVT().setFocusable(true);
			return false;
		}

		/* Kiểm tra tên vật tư */
		if (vattuModel.getTenVT().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên vật tư không được bỏ trống", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getTextFieldTenVT().setFocusable(true);
			return false;
		}
		if (!regexMatch("^[\\p{L}0-9\\s]*$", vattuModel.getTenVT())) {
			JOptionPane.showMessageDialog(null, "Tên vật tư chỉ gồm chữ cái, số, và khoảng trắng", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getTextFieldTenVT().setFocusable(true);
			return false;
		}

		if (vattuModel.getTenVT().length() > 30) {
			JOptionPane.showMessageDialog(null, "Tên vật tư không quá 30 kí tự", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getTextFieldTenVT().setFocusable(true);
			return false;
		}

		/* Kiểm tra đơn vị tính */
		if (vattuModel.getDvt().equals("")) {
			JOptionPane.showMessageDialog(null, "Đơn vị tính không được bỏ trống", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getTextFieldDonVi().setFocusable(true);
			return false;
		}
		if (!regexMatch("^[\\p{L}0-9]*$", vattuModel.getDvt())) {
			JOptionPane.showMessageDialog(null, "Đơn vị tính chỉ gồm chữ cái", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getTextFieldDonVi().setFocusable(true);
			return false;
		}

		if (vattuModel.getDvt().length() > 15) {
			JOptionPane.showMessageDialog(null, "Đơn vị tính không quá 15 kí tự", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getTextFieldDonVi().setFocusable(true);
			return false;
		}

		/* Kiểm tra số lượng tồn */
		if (vattuModel.getSoLuongTon() < 0) {
			JOptionPane.showMessageDialog(null, "Số lượng tồn phải ít nhất bằng 0", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			vatTuForm.getSpinner().setFocusable(true);
			return false;
		}

		return true;
	}

	private void addDataToDB(VattuModel vattuModel) {
//		Execute query
		String sql = "{? = call dbo.sp_TraCuu_MaVT(?)}";
//		chưa có sp_TraCuu_MaVT
		int res = Program.ExecSqlNoQuery(sql, vattuModel.getMavt());
//		execute fail
		if (res == -1)
			return;
//		execute return 1, dữ liệu đã tồn tại
		if (res == 1) {
			JOptionPane.showMessageDialog(null,
					"Đã tồn tại mã vật tư " + vattuModel.getMavt() + " này, vui lòng nhập lại", "Warning",
					JOptionPane.WARNING_MESSAGE);
		} else {

			if (vatTuForm.getDao().insert(vattuModel) == -1)
				return;

			Object[] newRow = { vattuModel.getMavt(), vattuModel.getTenVT(), vattuModel.getDvt(),
					vattuModel.getSoLuongTon() };
			vatTuForm.getModel().addRow(newRow);
			JOptionPane.showMessageDialog(null, "Ghi thành công!", "Thông báo", JOptionPane.OK_OPTION);

			vatTuForm.getTable().setEnabled(true);
			vatTuForm.getBtnThem().setEnabled(true);
			vatTuForm.getBtnXoa().setEnabled(true);
			vatTuForm.getBtnLamMoi().setEnabled(true);
			vatTuForm.getBtnHoanTac().setEnabled(true);
			vatTuForm.getBtnThoat().setEnabled(true);
			vatTuForm.getTextFieldTim().setEnabled(true);
			vatTuForm.getTable().getSelectionModel().addListSelectionListener(vatTuForm.getSelectionListener());
			vatTuForm.getTable().getSelectionModel().setSelectionInterval(vatTuForm.getTable().getRowCount() - 1,
					vatTuForm.getTable().getRowCount() - 1);
			vatTuForm.getList().add(vattuModel);
//			Luu truy van de hoan tac yeu cau them
			String sqlUndo;
			sqlUndo = "DELETE FROM Vattu WHERE MAVT = '" + vattuModel.getMavt() + "'";
			undoList.push(sqlUndo);
		}

	}

	private void upDateDataToDB(VattuModel vattuModel) {
		String tenVT = vatTuForm.getTable().getValueAt(vatTuForm.getTable().getSelectedRow(), 1).toString();
		String donVi = vatTuForm.getTable().getValueAt(vatTuForm.getTable().getSelectedRow(), 2).toString();
		String soLuong = vatTuForm.getTable().getValueAt(vatTuForm.getTable().getSelectedRow(), 3).toString();

		if (vatTuForm.getDao().update(vattuModel) == -1)
			return;
		vatTuForm.getBtnHoanTac().setEnabled(true);
		vatTuForm.getTable().setValueAt(vattuModel.getTenVT(), vatTuForm.getTable().getSelectedRow(), 1);
		vatTuForm.getTable().setValueAt(vattuModel.getDvt(), vatTuForm.getTable().getSelectedRow(), 2);
		vatTuForm.getTable().setValueAt(vattuModel.getSoLuongTon(), vatTuForm.getTable().getSelectedRow(), 3);
		JOptionPane.showMessageDialog(null, "Ghi thành công!", "Thông báo", JOptionPane.OK_OPTION);
		vatTuForm.getTable().getSelectionModel().setSelectionInterval(row, row);
		System.out.println(vatTuForm.getList().set(row, vattuModel));
//		Lưu truy vấn để hoàn tác yêu cầu update
		String sqlUndo;
		sqlUndo = "UPDATE Vattu SET TENVT = N'" + tenVT + "', DVT = N'" + donVi + "', SOLUONGTON = " + soLuong
				+ " WHERE MAVT = '" + vattuModel.getMavt() + "'";
		undoList.push(sqlUndo);
	}

	private void reFreshData() {
		vatTuForm.getTable().getSelectionModel().removeListSelectionListener(vatTuForm.getSelectionListener());
		vatTuForm.getModel().setRowCount(0);
		vatTuForm.loadDataIntoTable();
		vatTuForm.getTable().getSelectionModel().addListSelectionListener(vatTuForm.getSelectionListener());
	}

	private boolean checkCTDDH(String maVT) {
		String sql = "SELECT DISTINCT MAVT FROM CTDDH WHERE MAVT = ?";
		Program.myReader = Program.ExecSqlDataReader(sql, maVT);
		try {
			Program.myReader.next();
			if (Program.myReader.getString(1) != null) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean checkCTPN(String maVT) {
		String sql = "SELECT DISTINCT MAVT FROM CTPN WHERE MAVT = ?";
		Program.myReader = Program.ExecSqlDataReader(sql, maVT);
		try {
			Program.myReader.next();
			if (Program.myReader.getString(1) != null) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean checkCTPX(String maVT) {
		String sql = "SELECT DISTINCT MAVT FROM CTPX WHERE MAVT = ?";
		Program.myReader = Program.ExecSqlDataReader(sql, maVT);
		try {
			Program.myReader.next();
			if (Program.myReader.getString(1) != null) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void deleteData() {
		if (vatTuForm.getTable().getRowCount() == 0)
			vatTuForm.getBtnXoa().setEnabled(false);
		vattuModel.setMavt(vatTuForm.getTextFieldMaVT().getText());
		vattuModel.setTenVT(vatTuForm.getTextFieldTenVT().getText());
		vattuModel.setDvt(vatTuForm.getTextFieldDonVi().getText());
		vattuModel.setSoLuongTon((Integer) vatTuForm.getSpinner().getValue());

		if (checkCTDDH(vattuModel.getMavt())) {
			JOptionPane.showConfirmDialog(null, "Không thể xóa vật tư này vì đã lập đơn đặt hàng", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (checkCTPN(vattuModel.getMavt())) {
			JOptionPane.showConfirmDialog(null, "Không thể xóa vật tư này vì đã lập phiếu nhập", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (checkCTPX(vattuModel.getMavt())) {
			JOptionPane.showConfirmDialog(null, "Không thể xóa vật tư này vì đã lập phiếu xuất", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dữ liệu này không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (reply == JOptionPane.NO_OPTION)
			return;
		row = vatTuForm.getTable().getSelectedRow();

//		not execute
		if (vatTuForm.getDao().delete(vattuModel) == -1)
//			xóa ở db
			return;
		// delete selected row in table
		if (vatTuForm.getTable().getSelectedRow() != -1) {
			vatTuForm.getTable().getSelectionModel().removeListSelectionListener(vatTuForm.getSelectionListener());
			vatTuForm.getModel().removeRow(vatTuForm.getTable().getSelectedRow());
			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			vatTuForm.getTable().getSelectionModel().addListSelectionListener(vatTuForm.getSelectionListener());

			vatTuForm.getTextFieldMaVT().setText("");

			vatTuForm.getTextFieldTenVT().setText("");

			vatTuForm.getTextFieldDonVi().setText("");

			vatTuForm.getSpinner().setValue(0);
//			xóa ở danh sánh
			System.out.println(vatTuForm.getList().remove(row));
		}

		String sqlUndo = "INSERT INTO Vattu(MAVT,TENVT,DVT,SOLUONGTON) " + " VALUES( '"
				+ vattuModel.getMavt() + "',N'" + vattuModel.getTenVT() + "',N'"
				+ vattuModel.getDvt() + "', " + vattuModel.getSoLuongTon() + " ) ";

		undoList.push(sqlUndo);

	}

}
