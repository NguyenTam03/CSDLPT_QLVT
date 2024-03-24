package controller;

import main.Program;
import views.PhieuLapForm;

public class PhieuLapController {
	private PhieuLapForm dh;
	
	public PhieuLapController(PhieuLapForm dh) {
		this.dh = dh;
	}
	
	public void initController() {
		dh.getMenuItemPN().addActionListener(l -> initPhieuLap());
		dh.getBtnThoat().addActionListener(l -> exitPhieuLap());
	}
	
	private void exitPhieuLap() {
		Program.frmMain.getTabbedPane_Main().removeTabAt(Program.frmMain.getTabbedPane_Main().getSelectedIndex());
		Program.frmMain.getPanel_dathang().remove(dh);
	}
	
	private void initPhieuLap() {
		dh.getMnOption().setText("Phiếu Nhập");
		dh.getTable().setEnabled(true);
		dh.getTableCTPN().setEnabled(true);
		dh.getBtnThem().setEnabled(true);
		dh.getBtnXoa().setEnabled(true);
		dh.getBtnGhi().setEnabled(true);
		dh.getBtnHoanTac().setEnabled(false);
		dh.getBtnLamMoi().setEnabled(true);
		dh.getBtnDHOption().setEnabled(true);
		dh.getTFMaKho().setEditable(true);
		
		if (Program.mGroup.equals("CONGTY")) {
			dh.getComboBox().setEnabled(true);
		}
	}
}
