package controller;

import java.util.Stack;

import javax.swing.JOptionPane;

import main.Program;
import views.VatTuForm;

public class VatTuController {
	private VatTuForm vatTuForm;
	private Stack<String> undoList;

	public VatTuController(VatTuForm vatTuForm) {
		super();
		this.vatTuForm = vatTuForm;
	}

	public void initController() {
		vatTuForm.getBtnThem().addActionListener(l -> addVatTu());
		vatTuForm.getBtnGhi().addActionListener(l -> pushDataToDB());
		vatTuForm.getBtnLamMoi().addActionListener(l -> reFreshData());
		vatTuForm.getBtnXoa().addActionListener(l -> deleteData());
	}

	private void addVatTu() {
		vatTuForm.getBtnThem().setEnabled(false);
		vatTuForm.getBtnXoa().setEnabled(false);
		vatTuForm.getBtnLamMoi().setEnabled(false);
		vatTuForm.getBtnChuyenChiNhanh().setEnabled(false);
		vatTuForm.getBtnThoat().setEnabled(false);

		vatTuForm.getTextFieldMaVT().setEditable(true);
		vatTuForm.getTextFieldMaVT().setText("");

		vatTuForm.getTextFieldTenVT().setText("");

		vatTuForm.getTextFieldDonVi().setText("");

		vatTuForm.getSpinner().setValue(0);

		vatTuForm.getTable().setEnabled(false);
	}

	private void pushDataToDB() {
		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn ghi dữ liệu vào bảng không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (reply == JOptionPane.YES_OPTION) {
			String sql = "";
			String maVT = vatTuForm.getTextFieldMaVT().getText().trim();
			String tenVT = vatTuForm.getTextFieldTenVT().getText().trim();
			String donVi = vatTuForm.getTextFieldTenVT().getText().trim();
			String soLuong = vatTuForm.getSpinner().getValue().toString().trim();

			String cauTruyVanHoanTac = "";
			if (vatTuForm.getBtnThem().isSelected()) {

				addDataToDB(sql, maVT, tenVT, donVi, soLuong);
				cauTruyVanHoanTac = "DELETE Vattu "
						+ "WHERE MAVT = " + maVT;
				
			} else {
				upDateDataToDB(sql, maVT, tenVT, donVi, soLuong);
				cauTruyVanHoanTac = "UPDATE ";
			}
			
			undoList.push(cauTruyVanHoanTac);
		}
		vatTuForm.getTable().setEnabled(true);
		vatTuForm.getBtnThem().setEnabled(true);
		vatTuForm.getBtnXoa().setEnabled(true);
		vatTuForm.getBtnLamMoi().setEnabled(true);
		vatTuForm.getBtnChuyenChiNhanh().setEnabled(true);
		vatTuForm.getBtnThoat().setEnabled(true);
	}

	private void addDataToDB(String sql, String maVT, String tenVT, String donVi, String soLuong) {
		if (maVT.equals("") || tenVT.equals("") || donVi.equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin", "Ghi", JOptionPane.WARNING_MESSAGE);
			return;
		}
//		Execute query
		sql = "{? = call dbo.sp_TraCuu_MaVT(?)}";
//		chưa có sp_TraCuu_MaVT
		int res = Program.ExecSqlNoQuery(sql, maVT);
//		execute fail
		if (res == -1)
			return;
//		execute return 1, dữ liệu đã tồn tại
		if (res == 1) {
			JOptionPane.showMessageDialog(null, "Đã tồn tại mã vật tư " + maVT + " nay, vui lòng nhập lại", "Warning",
					JOptionPane.WARNING_MESSAGE);
		} else {
			sql = "INSERT INTO Kho (MAVT, TENVT, DVT, SOLUONGTON) VALUES (?, ?, ?, ?)";
			if (Program.ExecSqlDML(sql, maVT, tenVT, donVi, soLuong) == -1)
				return;

			Object[] newRow = { maVT, tenVT, donVi, soLuong };
			vatTuForm.getModel().addRow(newRow);
			JOptionPane.showMessageDialog(null, "Ghi thành công!");
		}

	}

	private void upDateDataToDB(String sql, String maVT, String tenVT, String donVi, String soLuong) {
		if (maVT.equals("") || tenVT.equals("") || donVi.equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin", "Ghi", JOptionPane.WARNING_MESSAGE);
			return;
		}
		sql = "UPDATE Vattu SET TENVT = ?, DVT = ?, SOLUONGTON = ? WHERE MAVT = ?";
		if (Program.ExecSqlDML(sql, tenVT, donVi, soLuong, maVT) == -1)
			return;
		JOptionPane.showMessageDialog(null, "Ghi thành công!");
	}

	private void reFreshData() {
		vatTuForm.getTable().getSelectionModel().removeListSelectionListener(vatTuForm.getSelectionListener());
		vatTuForm.getModel().setRowCount(0);
		vatTuForm.loadDataIntoTable();
		vatTuForm.getTable().getSelectionModel().addListSelectionListener(vatTuForm.getSelectionListener());
	}

	private void deleteData() {
		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dữ liệu này không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (reply == JOptionPane.NO_OPTION)
			return;
		// Execute sql delete kho table
		String maVT = vatTuForm.getTextFieldMaVT().getText();
		String sql = "DELETE FROM Vattu WHERE MAVT = ?";
		
		String cauTruyVanHoanTac = "INSERT INTO Vattu( MAVT,TENVT,DVT,SOLUONGTON) " +
				" VALUES( '" + vatTuForm.getTextFieldMaVT().getText() + "','" +
				vatTuForm.getTextFieldTenVT().getText() + "','" +
				vatTuForm.getTextFieldDonVi().getText() + "', " +
				vatTuForm.getSpinner().getValue() + " ) ";
		undoList.push(cauTruyVanHoanTac);
		// not execute
		if (Program.ExecSqlDML(sql, maVT) == -1)
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
		}

	}
	
	private void undo() {
		if (vatTuForm.getBtnThem().isSelected()) {
			vatTuForm.getBtnThem().setEnabled(true);
			vatTuForm.getBtnXoa().setEnabled(true);
			vatTuForm.getBtnLamMoi().setEnabled(true);
			vatTuForm.getBtnHoanTac().setEnabled(false);
			vatTuForm.getBtnThoat().setEnabled(true);

			vatTuForm.getTextFieldMaVT().setEditable(false);
		}
		if (undoList.empty()) {
			JOptionPane.showMessageDialog(null, "Không còn thao tác nào để khôi phục", "Warning", JOptionPane.INFORMATION_MESSAGE);
			vatTuForm.getBtnHoanTac().setEnabled(false);
			return;
		}
		String cauTruyVanHoanTac = undoList.pop().toString();
		Program.ExecSqlNoQuery(cauTruyVanHoanTac);
	}

}
