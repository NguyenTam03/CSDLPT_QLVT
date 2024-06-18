package controller;


import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;

import common.method.Formatter;
import main.Program;
import model.JasperReportModel;
import model.TongHopNXModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import views.ReportHoatDongNhanVien;

public class ReportHoatDongNhanVienController implements IJasperReportController {
	private String tuNgay, denNgay;
	private ReportHoatDongNhanVien form;

	public ReportHoatDongNhanVienController(ReportHoatDongNhanVien form) {
		this.form = form;
	}

	public void initController() {
		form.getBtnXuat().addActionListener((e) -> {
			if (form.getTuNgay().getDate() != null && form.getTuNgay().getDate() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				tuNgay = formatter.format(form.getTuNgay().getDate());
				denNgay = formatter.format(form.getDenNgay().getDate());
				print();
			}
		});

		form.getBtnXemTruoc().addActionListener((e) -> {
			if (form.getTuNgay().getDate() != null && form.getTuNgay().getDate() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				tuNgay = formatter.format(form.getTuNgay().getDate());
				denNgay = formatter.format(form.getDenNgay().getDate());
				preview();
			}
		});
	}

	private JasperPrint DanhSachHDNhanVien() {
		JasperPrint jasperPrint = null;
	    try {
	        InputStream inputStream = ReportNhanVien.class.getResourceAsStream("/reports/ReportDSNV.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("nameBranch", form.getComboBox().getSelectedItem());
	        parameters.put("paramMACN", Program.macn.get(form.getComboBox().getSelectedIndex()));

	        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, Program.conn);
	    } catch (JRException e) {
	        e.printStackTrace();
	    }
	    return jasperPrint;
	}

	@Override
	public void preview() {
		try {
		    JasperPrint jasperPrint = DanhSachHDNhanVien();

		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Chọn nơi lưu file PDF");

		    // hợp lệ hay không
		    int userSelection = fileChooser.showSaveDialog(null);
		    if (userSelection == JFileChooser.APPROVE_OPTION) {
		        File fileToSave = fileChooser.getSelectedFile();
		        String filePath = fileToSave.getAbsolutePath();

		        // Kiểm tra xem đường dẫn đã kết thúc bằng phần mở rộng ".pdf" chưa
		        if (!filePath.toLowerCase().endsWith(".pdf")) {
		            filePath += ".pdf"; 
		        }
		        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

	}

	@Override
	public void print() {
		getData();
		try {
			reportModel.saveReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Report successfully");

	}

}
