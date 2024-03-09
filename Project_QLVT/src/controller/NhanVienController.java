package controller;

import views.NhanVienForm;

public class NhanVienController {
	private NhanVienForm NhanVienCtrl;
	
	public NhanVienController(NhanVienForm NhanVienCtrl) {
		this.NhanVienCtrl = NhanVienCtrl;
	}
	public void initController() {
		NhanVienCtrl.getBtnThem().addActionListener(e -> addNhanVien() );
	}
	public void addNhanVien() {
		NhanVienCtrl.getBtnThem().setEnabled(false);
		NhanVienCtrl.getBtnXoa().setEnabled(false);
		NhanVienCtrl.getBtnLamMoi().setEnabled(false);
		NhanVienCtrl.getBtnChuyenChiNhanh().setEnabled(false);
		NhanVienCtrl.getBtnThoat().setEnabled(false);
		
		NhanVienCtrl.getTFMaNV().setEditable(true);
		NhanVienCtrl.getTFMaNV().setText("");
		NhanVienCtrl.getTFHo().setText("");
		NhanVienCtrl.getTFTen().setText("");
		NhanVienCtrl.getTFCMND().setText("");
		NhanVienCtrl.getTFDiaChi().setText("");
		NhanVienCtrl.getTFNgaySinh().setText("");
		
		NhanVienCtrl.getTable().setEnabled(false);
	}
	
}
