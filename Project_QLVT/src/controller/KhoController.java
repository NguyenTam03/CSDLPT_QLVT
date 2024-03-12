package controller;

import javax.swing.JOptionPane;

import main.Program;
import model.KhoModel;
import views.KhoForm;

public class KhoController {
	private KhoForm khoView;
	private KhoModel khoModel;
	
	public KhoController(KhoForm khoView) {
		this.khoView = khoView;
		khoModel = new KhoModel();
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
			String maKho = khoView.getTextFieldMaKho().getText().trim();
			String tenKho = khoView.getTextFieldTenKho().getText().trim();
			String diaChi = khoView.getTextFieldDiaChi().getText().trim();
			String macn = khoView.getTextFieldMaCN().getText();
			khoModel.setMaKho(maKho);
			khoModel.setTenKho(tenKho);
			khoModel.setDiaChi(diaChi);
			khoModel.setMacn(macn);
			if (!khoView.getBtnThem().isEnabled()) {
				addDataToDB(khoModel);
			}else {
				upDateDataToDB(khoModel);
			}
		}
		khoView.getTable().setEnabled(true);
		khoView.getBtnThem().setEnabled(true);
		khoView.getBtnXoa().setEnabled(true);
		khoView.getBtnLamMoi().setEnabled(true);
		khoView.getBtnThoat().setEnabled(true);
	}

	private void addDataToDB(KhoModel khoModel) {
		if (khoModel.getMaKho().equals("") || khoModel.getTenKho().equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin", "Ghi", JOptionPane.WARNING_MESSAGE);
			return;
		}
//		Execute query
		String sql = "{? = call dbo.sp_TraCuu_MaKho(?)}";
		int res = Program.ExecSqlNoQuery(sql, khoModel.getMaKho());
//		execute fail
		if (res == -1)
			return;
//		execute return 1, dữ liệu đã tồn tại
		if (res == 1) {
			JOptionPane.showMessageDialog(null, "Đã tồn tại mã kho " + khoModel.getMaKho() + " nay, vui lòng nhập lại", "Warning",
					JOptionPane.WARNING_MESSAGE);
		} else {
			if (khoView.getDao().insert(khoModel) == -1) return;

			Object[] newRow = { khoModel.getMaKho(), khoModel.getTenKho(), khoModel.getDiaChi(), khoModel.getMacn() };
			khoView.getModel().addRow(newRow);
			JOptionPane.showMessageDialog(null, "Ghi thành công!");
		}
		
	}
	
	private void upDateDataToDB(KhoModel khoModel) {
		if (khoModel.getTenKho().equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin", "Ghi", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (khoView.getDao().update(khoModel) == -1) return;
		khoView.getTable().setValueAt(khoModel.getTenKho(), khoView.getTable().getSelectedRow(), 1);
		khoView.getTable().setValueAt(khoModel.getDiaChi(), khoView.getTable().getSelectedRow(), 2);
		JOptionPane.showMessageDialog(null, "Ghi thành công!");
	}
	
	private void reFreshData() {
		khoView.getTable().getSelectionModel().removeListSelectionListener(khoView.getSelectionListener());
		khoView.getModel().setRowCount(0);
		khoView.loadDataIntoTable();
		khoView.getTable().getSelectionModel().addListSelectionListener(khoView.getSelectionListener());
		khoView.getTextFieldMaKho().setText("");
		khoView.getTextFieldTenKho().setText("");
		khoView.getTextFieldDiaChi().setText("");
	}
	
	private void deleteData() {
		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dữ liệu này không?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (reply == JOptionPane.NO_OPTION) return;
		khoModel.setMaKho(khoView.getTextFieldMaKho().getText());
		// not execute 
		if (khoView.getDao().delete(khoModel) == -1)
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
