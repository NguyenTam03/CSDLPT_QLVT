package controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.CTPLDao;
import dao.DatHangDao;
import dao.IAbstractDao;
import dao.PhieuLapDao;
import main.Program;
import model.CTPLModel;
import model.DatHangModel;
import model.PhieuLapModel;
import views.DatHangForm;
import views.DonHangOptionForm;
import views.PhieuLapForm;

public class PhieuLapController {
	private PhieuLapForm PLForm;
	private ListSelectionListener selectionListener_CTPN, selectionListener;
	private CTPLDao ctplDao;
	private ArrayList<CTPLModel> ctplList;
	private DefaultTableModel dhModel;
	private DonHangOptionForm DHOptionView;

	public PhieuLapController(PhieuLapForm PLForm) {
		this.PLForm = PLForm;
	}

	public void initController() {
		PLForm.getMenuItemPN().addActionListener(l -> initPhieuLap());
		PLForm.getMenuItemCTPN().addActionListener(l -> initCTPhieuLap());
		PLForm.getBtnThoat().addActionListener(l -> exitPhieuLap());
	}

	private void exitPhieuLap() {
		Program.frmMain.getTabbedPane_Main().removeTabAt(Program.frmMain.getTabbedPane_Main().getSelectedIndex());
		Program.frmMain.getPanel_dathang().remove(PLForm);
	}

	private void initPhieuLap() {
		selectionListener = e -> {
			PLForm.getTFMaPN().setText(PLForm.getTable().getValueAt(PLForm.getTable().getSelectedRow(), 0).toString());
			PLForm.getNgay()
					.setDate((java.util.Date) PLForm.getTable().getValueAt(PLForm.getTable().getSelectedRow(), 1));
			PLForm.getTFMaDDH().setText(PLForm.getTable().getValueAt(PLForm.getTable().getSelectedRow(), 2).toString());
			PLForm.getTFMaNV().setText(PLForm.getTable().getValueAt(PLForm.getTable().getSelectedRow(), 3).toString());
			String sql = "SELECT Ho +' '+Ten FROM NHANVIEN WHERE MANV = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, PLForm.getTFMaNV().getText());
			try {
				Program.myReader.next();
				PLForm.getLbHoTenNV().setText(Program.myReader.getString(1));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			PLForm.getTFMaKho().setText(PLForm.getTable().getValueAt(PLForm.getTable().getSelectedRow(), 4).toString());
			sql = "SELECT TENKHO FROM KHO WHERE MAKHO = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, PLForm.getTFMaKho().getText());
			try {
				Program.myReader.next();
				PLForm.getLbTenKho().setText(Program.myReader.getString(1));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			ctplDao = CTPLDao.getInstance();
			// .setColumnIdentifiers to set column name
			try {
				PLForm.getCtplModel().setColumnIdentifiers(ctplDao.getColName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// .setRowCount to remove all rows
			PLForm.getCtplModel().setRowCount(0);
			ctplList = ctplDao.selectAllCTPN(PLForm.getTFMaPN().getText());
			for (CTPLModel pl : ctplList) {
				Object[] rowData = { pl.getMaPN(), pl.getMavt(), pl.getSoLuong(),
						NhanVienController.formatObjecttoMoney(pl.getDonGia()) };
				PLForm.getCtplModel().addRow(rowData);
			}
		};
		PLForm.getTableCTPN().setEnabled(false);
		PLForm.getTable().setEnabled(true);
		PLForm.getTable().getSelectionModel().addListSelectionListener(selectionListener);
		PLForm.getTableCTPN().getSelectionModel().removeListSelectionListener(selectionListener_CTPN);

		PLForm.getMnOption().setText("Phiếu Nhập");
		PLForm.getBtnThem().setEnabled(true);
		PLForm.getBtnXoa().setEnabled(true);
		PLForm.getBtnGhi().setEnabled(true);
		PLForm.getBtnHoanTac().setEnabled(false);
		PLForm.getBtnLamMoi().setEnabled(true);
		PLForm.getBtnDHOption().setEnabled(true);
		PLForm.getBtnCTDHOption().setEnabled(false);
		PLForm.getTFMaKho().setEditable(true);
		// Chọn đơn hàng
		PLForm.getBtnDHOption().addActionListener(e -> ChonDonHang());
		if (Program.mGroup.equals("CONGTY")) {
			PLForm.getComboBox().setEnabled(true);
		}
	}
	private void ChonDonHang() {
		DHOptionView = new DonHangOptionForm();
		ArrayList<DatHangModel> dhList;
		DatHangDao dhDao;
		dhModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dhDao = DatHangDao.getInstance();
		try {
			dhModel.setColumnIdentifiers(dhDao.getColName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		dhDao = DatHangDao.getInstance();
		dhModel = (DefaultTableModel) DHOptionView.getTableDH().getModel();
		dhModel.setColumnIdentifiers(((IAbstractDao<DatHangModel>) dhDao).getColName());
		dhList = ((IAbstractDao<DatHangModel>) dhDao).selectAll();
		
		for (DatHangModel dh : dhList) {
			String sql_isPhieuNhap = "SELECT case when exists(select 1 from PhieuNhap where MaSoDDH = ?) then 1 else 0 end ";
			Program.myReader = Program.ExecSqlDataReader(sql_isPhieuNhap, dh.getMaSoDDH());
			try {
				Program.myReader.next();
				// Nếu đơn hàng đã được nhập thì không hiển thị (không thêm vào table)
				if (Program.myReader.getInt(1) == 1) {
					continue;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Object[] rowData = { dh.getMaSoDDH(), dh.getNgay(), dh.getNhaCC(), dh.getManv(), dh.getMakho() };
			
			dhModel.addRow(rowData);
		}
		DHOptionView.getTableDH().setModel(dhModel);
		dhModel = (DefaultTableModel) DHOptionView.getTableDH().getModel();
		DHOptionView.setVisible(true);
		DHOptionView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DHOptionView.getBtnChon().addActionListener(e -> clickBtnChon());
		DHOptionView.getBtnThoat().addActionListener(e -> DHOptionView.dispose());
	}
	private void clickBtnChon() {
		DatHangModel dh = new DatHangModel();
		dh.setMaSoDDH(dhModel.getValueAt(DHOptionView.getTableDH().getSelectedRow(), 0).toString());
		System.out.println(dh.getMaSoDDH());
	}

	private void initCTPhieuLap() {
		selectionListener_CTPN = e -> {
			PLForm.getTFMaVT()
					.setText(PLForm.getTableCTPN().getValueAt(PLForm.getTableCTPN().getSelectedRow(), 1).toString());
			String sql = "SELECT TENVT FROM VATTU WHERE MAVT = ?";
			Program.myReader = Program.ExecSqlDataReader(sql, PLForm.getTFMaVT().getText());
			try {
				Program.myReader.next();
				PLForm.getLbTenVatTu().setText(Program.myReader.getString(1));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			PLForm.getSoLuong().setValue(PLForm.getTableCTPN().getValueAt(PLForm.getTableCTPN().getSelectedRow(), 2));
			PLForm.getDonGia().setValue(NhanVienController
					.formatMoneyToInteger(PLForm.getTableCTPN().getValueAt(PLForm.getTableCTPN().getSelectedRow(), 3)));
		};
		PLForm.getTable().setEnabled(false);
		PLForm.getTableCTPN().setEnabled(true);
		PLForm.getTableCTPN().getSelectionModel().addListSelectionListener(selectionListener_CTPN);
		PLForm.getTableCTPN().getSelectionModel().removeListSelectionListener(selectionListener);
		PLForm.getMnOption().setText("Chi Tiết Phiếu Nhập");
		PLForm.getBtnThem().setEnabled(true);
		PLForm.getBtnXoa().setEnabled(true);
		PLForm.getBtnGhi().setEnabled(true);
		PLForm.getBtnHoanTac().setEnabled(false);
		PLForm.getBtnLamMoi().setEnabled(true);
		PLForm.getBtnDHOption().setEnabled(false);
		PLForm.getBtnCTDHOption().setEnabled(true);
		PLForm.getTFMaKho().setEditable(true);

		if (Program.mGroup.equals("CONGTY")) {
			PLForm.getComboBox().setEnabled(true);
		}

	}
}
