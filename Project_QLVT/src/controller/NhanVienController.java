package controller;

import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import main.Program;
import views.NhanVienForm;

public class NhanVienController {
	private NhanVienForm NhanVienCtrl;
	
	public NhanVienController(NhanVienForm NhanVienCtrl) {
		this.NhanVienCtrl = NhanVienCtrl;
	}
	public void initController() {
		NhanVienCtrl.getBtnThem().addActionListener(e -> addNhanVien());
		NhanVienCtrl.getBtnGhi().addActionListener(e -> pushDataToDB());
	}
	public void addNhanVien() {
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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		NhanVienCtrl.getTFMaNV().setText(String.valueOf(numberOf + 1));
		NhanVienCtrl.getTFHo().setText("");
		NhanVienCtrl.getTFTen().setText("");
		NhanVienCtrl.getTFCMND().setText("");
		NhanVienCtrl.getTFDiaChi().setText("");
		NhanVienCtrl.getTFNgaySinh().setText("");
		NhanVienCtrl.getTFMaCN().setText(Program.maCN);
		NhanVienCtrl.getTable().setEnabled(false);
	}
	// push or update info into Data
	public void pushDataToDB() {
		int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn ghi dữ liệu vào bảng không?", "Confirm", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(reply == JOptionPane.YES_NO_OPTION) {
			String sql ="";
			String MaNV,Ho,Ten,CMND,DiaChi,MaCN;
			Float Luong;
			LocalDate NgaySinh = null;
			try {
				MaNV = NhanVienCtrl.getTFMaNV().getText().trim();
				Ho = NhanVienCtrl.getTFHo().getText().trim();
				Ten = NhanVienCtrl.getTFTen().getText().trim();
				CMND = NhanVienCtrl.getTFCMND().getText().trim();
				DiaChi = NhanVienCtrl.getTFDiaChi().getText().trim();
				String date = NhanVienCtrl.getTFNgaySinh().getText().trim();
				Luong = ((Integer) NhanVienCtrl.getLuong().getValue()).floatValue();
				
				MaCN = NhanVienCtrl.getTFMaCN().getText().trim();
				if(CMND.equals(""))
					CMND = null;
				if(DiaChi.equals(""))
					DiaChi = null;
				if(Luong == 0)
					Luong = null;
				if(!date.equals(""))
					NgaySinh = LocalDate.parse(date);	
				
				if(!NhanVienCtrl.getBtnThem().isEnabled()) {
					addDataToDB(sql, Integer.parseInt(MaNV), Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaCN, false);
				}
				else {
					updateDataToDB(sql, Integer.parseInt(MaNV), Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaCN, false);
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"THông Báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	}
	
	// push info into Data
	public void addDataToDB(String sql, Integer MaNV,String Ho, String Ten, String CMND,
			String DiaChi, LocalDate NgaySinh, Float Luong,String MaCN,boolean TrangThaiXoa) {
		if( Ho.equals("") || Ten.equals("") ) {
			JOptionPane.showConfirmDialog(null, "Hãy điền đủ thông tin!!","Ghi",JOptionPane.CLOSED_OPTION);
			return;
		}
		
		sql = "Insert into NhanVien(MaNV, Ho, Ten, SoCMND, DiaChi, NgaySinh, Luong, MaCN, TrangThaiXoa) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if(Program.ExecSqlDML(sql, MaNV, Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaCN, TrangThaiXoa) == -1 )
			return;
		Object [] newRow = {MaNV, Ho, Ten, CMND, DiaChi, NgaySinh, formatObjecttoMoney(Luong), MaCN, TrangThaiXoa};
		NhanVienCtrl.getModel().addRow(newRow);
		JOptionPane.showConfirmDialog(null,"Ghi Thành Công","Thông Báo",JOptionPane.CLOSED_OPTION);
	}
	
	// update info Data
	public void updateDataToDB(String sql, Integer MaNV,String Ho, String Ten, String CMND,
			String DiaChi, LocalDate NgaySinh, Float Luong,String MaCN,boolean TrangThaiXoa) {
		if( Ho.equals("") || Ten.equals("") ) {
			JOptionPane.showConfirmDialog(null, "Hãy điền đủ thông tin!!","Ghi",JOptionPane.WARNING_MESSAGE);
			return;
		}
		sql = "Update NhanVien set Ho = ?, Ten = ?, SoCMND = ?, DiaChi = ?, NgaySinh = ?, Luong = ? where MaNV = ?";
		if(Program.ExecSqlDML(sql, Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaNV) == -1 )
			return;
		refreshData();
		JOptionPane.showConfirmDialog(null, "Ghi Thành Công","Thông Báo", JOptionPane.CLOSED_OPTION);
	}
	private void refreshData() {
		NhanVienCtrl.getTable().getSelectionModel().removeListSelectionListener(NhanVienCtrl.getSelectionListener());
		NhanVienCtrl.getModel().setRowCount(0);
		NhanVienCtrl.loadDataIntoTable();
		NhanVienCtrl.getTable().getSelectionModel().addListSelectionListener(NhanVienCtrl.getSelectionListener());
	}
	
	// format x.000đ => x000 ( Integer)
	public static Integer formatMoneyToInteger(Object money) {
		return Integer.parseInt(((String) money).replaceAll("[^0-9]", ""));
	} 

	// format x000  => x.000 đ
	public static String formatObjecttoMoney(Object string) {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		return currencyFormat.format(string);
	}
}
