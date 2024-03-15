package controller;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Stack;

import javax.swing.JOptionPane;

import main.Program;
import views.NhanVienForm;

public class NhanVienController {
	private Stack undoList = new Stack();
	private NhanVienForm NhanVienCtrl;
	private boolean isSelectThem = false;

	public NhanVienController(NhanVienForm NhanVienCtrl) {
		this.NhanVienCtrl = NhanVienCtrl;
	}

	public void initController() {
		NhanVienCtrl.getBtnThem().addActionListener(e -> addNhanVien());
		NhanVienCtrl.getBtnGhi().addActionListener(e -> pushDataToDB());
		NhanVienCtrl.getBtnLamMoi().addActionListener(e -> refreshData());
		NhanVienCtrl.getBtnHoanTac().addActionListener(e -> undoData());
	}

	public void addNhanVien() {
		isSelectThem = true;
		NhanVienCtrl.getBtnThem().setEnabled(false);
		NhanVienCtrl.getBtnXoa().setEnabled(false);
		NhanVienCtrl.getBtnLamMoi().setEnabled(false);
		NhanVienCtrl.getBtnChuyenChiNhanh().setEnabled(false);
		NhanVienCtrl.getBtnThoat().setEnabled(false);

		// Count number of Staff
		String sql = "Select Max(MaNV) From Link2.QLVT_DATHANG.dbo.NhanVien ";
		Program.myReader = Program.ExecSqlDataReader(sql);
		int numberOf = 1;
		try {
			Program.myReader.next();
			numberOf = Program.myReader.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		isSelectThem = true;
		NhanVienCtrl.getTFMaNV().setText(String.valueOf(numberOf + 1));
		NhanVienCtrl.getTFHo().setText("");
		NhanVienCtrl.getTFTen().setText("");
		NhanVienCtrl.getTFCMND().setText("");
		NhanVienCtrl.getTFDiaChi().setText("");
		NhanVienCtrl.getNgaySinh().setDate(null);
		NhanVienCtrl.getTFMaCN().setText(Program.macn.get(Program.mChinhanh));
		NhanVienCtrl.getTable().setEnabled(false);
	}

	// push or update info into Data
	public void pushDataToDB() {
		String sql = "";
		String MaNV, Ho, Ten, CMND, DiaChi, MaCN;
		Float Luong;
		java.util.Date NgaySinh = null;
		try {
			MaNV = NhanVienCtrl.getTFMaNV().getText().trim();
			Ho = NhanVienCtrl.getTFHo().getText().trim();
			Ten = NhanVienCtrl.getTFTen().getText().trim();
			CMND = NhanVienCtrl.getTFCMND().getText().trim();
			DiaChi = NhanVienCtrl.getTFDiaChi().getText().trim();
			NgaySinh = NhanVienCtrl.getNgaySinh().getDate();
			Luong = ((Integer) NhanVienCtrl.getLuong().getValue()).floatValue();
			MaCN = NhanVienCtrl.getTFMaCN().getText().trim();

			if (Ho.isEmpty() || Ten.isEmpty() || CMND.isEmpty() || DiaChi.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Hãy nhập đủ thông tin", "Thông Báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (CMND.length() != 9 && CMND.length() != 12) {
				JOptionPane.showMessageDialog(null, "Số CMND phải có 9 hoặc 12 chữ số", "Thông Báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// .*\\d.* : chứa số
			if (Ho.matches(".*\\d.*") || Ten.matches(".*\\d.*")) {
				JOptionPane.showMessageDialog(null, "Họ tên không được chứa số", "Thông Báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// .*\D.* : chứa chữ
			if (CMND.matches(".*\\D.*")) {
				JOptionPane.showMessageDialog(null, "Số CMND không được chứa chữ", "Thông Báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (NgaySinh == null) {
				JOptionPane.showMessageDialog(null, "Hãy chọn ngày sinh", "Thông Báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (Luong >= 40000000) {
				JOptionPane.showMessageDialog(null, "Lương phải trên 4.000.000đ", "Thông Báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn ghi dữ liệu vào bảng không?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (reply == JOptionPane.YES_NO_OPTION) {
				if (isSelectThem) {
					addDataToDB(sql, Integer.parseInt(MaNV), Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaCN, false);
					isSelectThem = false;
				} else {
					updateDataToDB(sql, Integer.parseInt(MaNV), Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaCN, false);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "THông Báo", JOptionPane.WARNING_MESSAGE);
			return;
		}

	}

	// push info into Data
	public void addDataToDB(String sql, Integer MaNV, String Ho, String Ten, String CMND, String DiaChi,
			java.util.Date NgaySinh, Float Luong, String MaCN, boolean TrangThaiXoa) {
		sql = "Insert into NhanVien(MaNV, Ho, Ten, SoCMND, DiaChi, NgaySinh, Luong, MaCN, TrangThaiXoa) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if (Program.ExecSqlDML(sql, MaNV, Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaCN, TrangThaiXoa) == -1)
			return;
		Object[] newRow = { MaNV, Ho, Ten, CMND, DiaChi, NgaySinh, formatObjecttoMoney(Luong), MaCN, TrangThaiXoa };
		NhanVienCtrl.getModel().addRow(newRow);
		String sqlUndo = "Delete from NhanVien where MaNV = '"+ MaNV.toString()+"'";
		undoList.push(sqlUndo);
		JOptionPane.showConfirmDialog(null, "Ghi Thành Công", "Thông Báo", JOptionPane.CLOSED_OPTION);
	}

	// update info Data
	public void updateDataToDB(String sql, Integer MaNV, String Ho, String Ten, String CMND, String DiaChi,
			java.util.Date NgaySinh, Float Luong, String MaCN, boolean TrangThaiXoa) {
		sql = "Update NhanVien set Ho = ?, Ten = ?, SoCMND = ?, DiaChi = ?, NgaySinh = ?, Luong = ? where MaNV = ?";
		if (Program.ExecSqlDML(sql, Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaNV) == -1)
			return;
		refreshData();

		// format lại Ngày Sinh thành dạng yyyy-MM-dd
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedNgaySinh = dateFormat.format(NgaySinh);
		String sqlUndo = "Update NhanVien set Ho = '"+ Ho +"', Ten = '"+ Ten +"', SoCMND = '"+ CMND +"', DiaChi = '"
				+ DiaChi +"', NgaySinh = '"+ formattedNgaySinh +"', Luong = '"+ Luong +"' where MaNV = '"+ MaNV.toString()+"'";
		undoList.push(sqlUndo);
		JOptionPane.showConfirmDialog(null, "Ghi Thành Công", "Thông Báo", JOptionPane.CLOSED_OPTION);
	}

	private void refreshData() {
		NhanVienCtrl.getTable().getSelectionModel().removeListSelectionListener(NhanVienCtrl.getSelectionListener());
		NhanVienCtrl.getModel().setRowCount(0);
		NhanVienCtrl.loadDataIntoTable();
		NhanVienCtrl.getTable().getSelectionModel().addListSelectionListener(NhanVienCtrl.getSelectionListener());
	}

	private void undoData() {
		if (undoList.isEmpty()) {
			JOptionPane.showConfirmDialog(null, "Hết thao tác để khôi phục", "Thông Báo", JOptionPane.CLOSED_OPTION);
			NhanVienCtrl.getBtnHoanTac().setEnabled(false);
			return;
		}
		if (isSelectThem && NhanVienCtrl.getBtnThem().isEnabled() == false) {
			NhanVienCtrl.getBtnThem().setEnabled(true);
			NhanVienCtrl.getBtnXoa().setEnabled(true);
			NhanVienCtrl.getBtnLamMoi().setEnabled(true);
			NhanVienCtrl.getBtnChuyenChiNhanh().setEnabled(true);
			NhanVienCtrl.getBtnThoat().setEnabled(true);
			NhanVienCtrl.getTable().setEnabled(true);
			isSelectThem = false;
			return;
		}
		String queryUndo = undoList.pop().toString();
		if(queryUndo.contains("sp_ChuyenChiNhanh")) {
//            Program.ExecSqlDML(queryUndo);
		}
		else {
//			if (Program.ExecSqlNonQuery(queryUndo) != 0)
//				JOptionPane.showConfirmDialog(null, "Khôi phục thất bại", "Thông Báo", JOptionPane.CLOSED_OPTION);
//				return;
		}
		refreshData();
		
		
	}
	
	// format x.000đ => x000 ( Integer)
	public static Integer formatMoneyToInteger(Object money) {
		return Integer.parseInt(((String) money).replaceAll("[^0-9]", ""));
	}

	// format x000 => x.000 đ
	public static String formatObjecttoMoney(Object string) {
		if (string == null)
			return null;
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		return currencyFormat.format(string);
	}

}
