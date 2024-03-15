package controller;

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
		String sql = "Select Count(*) From Link2.QLVT_DATHANG.dbo.NhanVien";
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
		NhanVienCtrl.getTFMaCN().setText(Program.macn.get(Program.mChinhanh));
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
				
			}
		}
	}
	// push info into Data
	public void addDataToDB(String sql, Integer MaNV,String Ho, String Ten, String CMND,
			String DiaChi, LocalDate NgaySinh, Float Luong,String MaCN,boolean TrangThaiXoa) {
		if( Ho.equals("") || Ten.equals("") ) {
			JOptionPane.showConfirmDialog(null, "Hãy điền đủ thông tin!!","Ghi",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		sql = "Insert into NhanVien(MaNV, Ho, Ten, SoCMND, DiaChi, NgaySinh, Luong, MaCN, TrangThaiXoa) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if(Program.ExecSqlDML(sql, MaNV, Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaCN, TrangThaiXoa) == -1 )
			return;
		Object [] newRow = {MaNV, Ho, Ten, CMND, DiaChi, NgaySinh, Luong, MaCN, TrangThaiXoa};
		NhanVienCtrl.getModel().addRow(newRow);
		JOptionPane.showConfirmDialog(null,"Ghi Thành Công","Thông Báo",JOptionPane.OK_OPTION);
	}
	public void updateDataToDB() {
		
	}
}
