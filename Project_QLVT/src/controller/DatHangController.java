package controller;

import main.Program;
import views.DatHangForm;

public class DatHangController {
	private DatHangForm dh;
	
	public DatHangController(DatHangForm dh) {
		this.dh = dh;
	}
	
	public void initController() {
		dh.getMntmDatHang().addActionListener(l -> initDatHang());
		dh.getBtnThoat().addActionListener(l -> exitDatHang());
	}
	
	private void exitDatHang() {
		Program.frmMain.getTabbedPane_Main().removeTabAt(Program.frmMain.getTabbedPane_Main().getSelectedIndex());
		Program.frmMain.getPanel_dathang().remove(dh);
	}
	
	private void initDatHang() {
		dh.getTable().setEnabled(true);
		dh.getTableCTDH().setEnabled(true);
		dh.getBtnThem().setEnabled(true);
		dh.getBtnXoa().setEnabled(true);
		dh.getBtnGhi().setEnabled(true);
		dh.getBtnHoanTac().setEnabled(false);
		dh.getBtnLamMoi().setEnabled(true);
		dh.getBtnKhoOption().setEnabled(true);
		dh.getTextFieldMaKho().setEditable(true);
		
		if (Program.mGroup.equals("CONGTY")) {
			dh.getComboBox().setEnabled(true);
		}
	}
}
