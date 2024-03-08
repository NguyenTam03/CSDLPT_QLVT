package controller;

import javax.swing.JOptionPane;

import main.Program;
import views.KhoForm;

public class KhoController {
	private KhoForm khoView;

	public KhoController(KhoForm khoView) {
		this.khoView = khoView;
	}

	public void initController() {
		khoView.getBtnThem().addActionListener(l -> addKho());
		khoView.getBtnGhi().addActionListener(l -> pushDataToDB());
		khoView.getBtnLamMoi().addActionListener(l -> reFreshData());
		khoView.getBtnXoa().addActionListener(l -> deleteData());
	}

	private void addKho() {
		khoView.getBtnThem().setEnabled(false);
		khoView.getBtnXoa().setEnabled(false);
		khoView.getBtnLamMoi().setEnabled(false);
		khoView.getBtnChuyenChiNhanh().setEnabled(false);
		khoView.getBtnThoat().setEnabled(false);
		
		khoView.getTextFieldMaKho().setEditable(true);
		khoView.getTextFieldMaKho().setText("");

		khoView.getTextFieldTenKho().setText("");

		khoView.getTextFieldDiaChi().setText("");

		khoView.getTable().setEnabled(false);
	}
	
	private void pushDataToDB() {
		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn ghi dữ liệu vào bảng không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (reply == JOptionPane.YES_OPTION) {
			String sql = "";
			String maKho = khoView.getTextFieldMaKho().getText().trim();
			String tenKho = khoView.getTextFieldTenKho().getText().trim();
			String diaChi = khoView.getTextFieldDiaChi().getText().trim();
			if (khoView.getBtnThem().isSelected()) {
				addDataToDB(sql, maKho, tenKho, diaChi);
			}else {
				upDateDataToDB(sql, maKho, tenKho, diaChi);
			}
		}
		
		khoView.getTable().setEnabled(true);
		khoView.getBtnThem().setEnabled(true);
		khoView.getBtnXoa().setEnabled(true);
	}

	private void addDataToDB(String sql, String maKho, String tenKho, String diaChi) {
		if (maKho.equals("") || tenKho.equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin", "Ghi", JOptionPane.WARNING_MESSAGE);
			return;
		}
//		Execute query
		sql = "{? = call dbo.sp_TraCuu_MaKho(?)}";
		int res = Program.ExecSqlNoQuery(sql, maKho);
//		execute fail
		if (res == -1)
			return;
//		execute return 1, dữ liệu đã tồn tại
		if (res == 1) {
			JOptionPane.showMessageDialog(null, "Đã tồn tại mã kho " + maKho + " nay, vui lòng nhập lại", "Warning",
					JOptionPane.WARNING_MESSAGE);
		} else {
			sql = "INSERT INTO Kho (MAKHO, TENKHO, DIACHI, MACN) VALUES (?, ?, ?, ?)";
			if (Program.ExecSqlDML(sql, maKho, tenKho, diaChi, khoView.getTextFieldMaCN().getText()) == -1)
				return;

			Object[] newRow = { maKho, tenKho, diaChi, khoView.getTextFieldMaCN().getText() };
			khoView.getModel().addRow(newRow);
			JOptionPane.showMessageDialog(null, "Ghi thành công!");
		}
		
	}
	
	private void upDateDataToDB(String sql, String maKho, String tenKho, String diaChi) {
		if (maKho.equals("") || tenKho.equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin", "Ghi", JOptionPane.WARNING_MESSAGE);
			return;
		}
		sql = "UPDATE Kho SET TENKHO = ?, DIACHI = ? WHERE MAKHO = ?";
		if (Program.ExecSqlDML(sql, tenKho, diaChi, maKho) == -1)
			return;
		JOptionPane.showMessageDialog(null, "Ghi thành công!");
	}
	
	private void reFreshData() {
		khoView.getTable().getSelectionModel().removeListSelectionListener(khoView.getSelectionListener());
		khoView.getModel().setRowCount(0);
		khoView.loadDataIntoTable();
		khoView.getTable().getSelectionModel().addListSelectionListener(khoView.getSelectionListener());
	}
	
	private void deleteData() {
		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dữ liệu này không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (reply == JOptionPane.NO_OPTION) return;
		// Execute sql delete kho table
		String maKho = khoView.getTextFieldMaKho().getText();
		String sql = "DELETE FROM Kho WHERE MAKHO = ?";
		// not execute 
		if (Program.ExecSqlDML(sql, maKho) == -1)
			return;
		// delete selected row in table
		if (khoView.getTable().getSelectedRow() != -1) {
			khoView.getTable().getSelectionModel().removeListSelectionListener(khoView.getSelectionListener());
			khoView.getModel().removeRow(khoView.getTable().getSelectedRow());
			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			khoView.getTable().getSelectionModel().addListSelectionListener(khoView.getSelectionListener());
			
			khoView.getTextFieldMaKho().setText("");

			khoView.getTextFieldTenKho().setText("");

			khoView.getTextFieldDiaChi().setText("");
		}
		
		
	}
}
