package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import javax.swing.JOptionPane;

import main.Program;
import model.PhieuXuatModel;
import views.PhieuXuatForm;

public class PhieuXuatController {
	private PhieuXuatForm px;
	private PhieuXuatModel phieuXuatModel;
	private Stack<String> undoList;
	private int row;

	public PhieuXuatController(PhieuXuatForm px) {
		this.px = px;
		phieuXuatModel = new PhieuXuatModel();
		undoList = new Stack<String>();
		row = 0;

	}

	public void initController() {
		px.getMntmPhieuXuat().addActionListener(l -> initPhieuXuat());
		px.getBtnThoat().addActionListener(l -> exitPhieuXuat());
		px.getMntmPhieuXuat().addActionListener(l -> btnCheDoPhieuXuatClicked());
		px.getMntmCTPX().addActionListener(l -> btnCheDoChiTietPhieuXuatClicked());
		px.getBtnThem().addActionListener(l -> addPhieuXuat());
		px.getBtnLamMoi().addActionListener(l -> reFreshData());
	}

	private void initPhieuXuat() {
		px.getTable().setEnabled(true);
		px.getTableCTPX().setEnabled(true);
		px.getBtnThem().setEnabled(true);
		px.getBtnXoa().setEnabled(true);
		px.getBtnGhi().setEnabled(true);
		px.getBtnHoanTac().setEnabled(false);
		px.getBtnLamMoi().setEnabled(true);
		px.getBtnKhoOption().setEnabled(true);
		px.getTextFieldMaKho().setEditable(true);

//		if (Program.mGroup.equals("CONGTY")) {
//			px.getComboBox().setEnabled(true);
//		}
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
	
	private void addPhieuXuat() {
		if (!px.getMntmCTPX().isEnabled()) {
			px.getTextFieldMaPX().setEditable(true);
			Date currentDate = Calendar.getInstance().getTime();
			px.getNgay().setDate(currentDate);
			px.getNgay().setEnabled(false);
			
			px.getTextFieldTenKH().setEditable(true);
			px.getTextFieldMaNV().setText(Program.username);
			
			px.getBtnKhoOption().setEnabled(true);
			
			px.getTextFieldMaVT().setEnabled(false);
			px.getBtnVatTuOption().setEnabled(false);
			px.getSpinnerSoLuong().setEnabled(false);
			px.getSpinnerDonGia().setEnabled(false);
			
		}
		
		if (!px.getMntmPhieuXuat().isEnabled()) {
			
			if (!Program.username.equals(px.getTextFieldMaNV().getText())) {
				JOptionPane.showMessageDialog(null, "Không thể thêm chi tiết phiếu xuất trên phiếu không phải do mình tạo!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			px.getTextFieldMaPX().setEditable(true);
			Date currentDate = Calendar.getInstance().getTime();
			px.getNgay().setDate(currentDate);
			px.getNgay().setEnabled(false);
			
			px.getTextFieldTenKH().setEditable(true);
			px.getTextFieldMaNV().setText(Program.username);
			
			px.getBtnKhoOption().setEnabled(true);
			
			px.getTextFieldMaVT().setEnabled(false);
			px.getBtnVatTuOption().setEnabled(true);
			
			px.getSpinnerSoLuong().setEnabled(true);
			px.getSpinnerSoLuong().setValue(1);
			
			px.getSpinnerDonGia().setEnabled(true);
			px.getSpinnerDonGia().setValue(1);
			
		}
		
//		Luu vi tri row dang chon
		row = px.getTable().getSelectedRow();
		px.getBtnThem().setEnabled(false);
		px.getBtnXoa().setEnabled(false);
		px.getBtnGhi().setEnabled(true);
		px.getBtnLamMoi().setEnabled(false);
		px.getBtnHoanTac().setEnabled(true);
		px.getBtnThoat().setEnabled(false);

		px.getTable().setEnabled(false);
		px.getTableCTPX().setEnabled(false);

		px.getTable().getSelectionModel().removeListSelectionListener(px.getSelectionListener());
		px.getTable().getSelectionModel().clearSelection();
		px.getTable().setEnabled(false);
		
	}
	
	
	private void reFreshData() {
		/* Refresh bảng phiếu xuất */
		px.getTable().getSelectionModel().removeListSelectionListener(px.getSelectionListener());
		px.getModel().setRowCount(0);
		px.loadDataIntoTable();
		px.getTable().getSelectionModel().addListSelectionListener(px.getSelectionListener());
		px.getTable().getSelectionModel().setSelectionInterval(0, 0);
		
		/* Refresh bảng ctpx */
		px.getTableCTPX().getSelectionModel().removeListSelectionListener(px.getSelectionListener());
		px.getCtpxModel().setRowCount(0);
		px.loadDataIntoTableCTPX();
		px.getTableCTPX().getSelectionModel().addListSelectionListener(px.getSelectionListener());
		px.getTableCTPX().getSelectionModel().setSelectionInterval(0, 0);
		
	}

}
