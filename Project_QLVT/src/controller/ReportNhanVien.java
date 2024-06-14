package controller;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import main.Program;
import model.NhanVienModel;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;
import views.ReportDanhSachNhanVien;

public class ReportNhanVien {
	private ReportDanhSachNhanVien reportDanhSachNhanVien;

	public ReportNhanVien(ReportDanhSachNhanVien reportDanhSachNhanVien) {
		this.reportDanhSachNhanVien = reportDanhSachNhanVien;
	}
	
	public void initController() {
		reportDanhSachNhanVien.getBtnXemTruoc().addActionListener(e -> xemTruocNhanVien() );
		reportDanhSachNhanVien.getBtnXuatBan().addActionListener(e -> xuatBanNhanVien());
		System.out.println(Program.mChinhanh);
		System.out.println(Program.mGroup);
		System.out.println(Program.servername);
	}
	
	public JasperPrint DanhSachNhanVien() {
	    JasperPrint jasperPrint = null;
	    try {
	    	JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance())
            .setProperty("net.sf.jasperreports.fonts", "D:/Repository_CSDLPT/Project_QLVT/font.xml");
	    	
	        InputStream inputStream = ReportNhanVien.class.getResourceAsStream("/reports/ReportDSNV.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("nameBranch", reportDanhSachNhanVien.getCBChiNhanh().getSelectedItem());
	        parameters.put("paramMACN", Program.macn.get(reportDanhSachNhanVien.getCBChiNhanh().getSelectedIndex()));

	        // Tạo và điền dữ liệu vào báo cáo
	        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, Program.conn);
	    } catch (JRException e) {
	        e.printStackTrace();
	    }
	    return jasperPrint;
	}
	public void xuatBanNhanVien() {
		try {
		    JasperPrint jasperPrint = DanhSachNhanVien(); // Gán giá trị cho biến JasperPrint

		    // Tạo một đối tượng FileChooser
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Chọn nơi lưu file PDF");

		    // Hiển thị hộp thoại lưu file và kiểm tra xem người dùng đã chọn một đường dẫn
		    // hợp lệ hay không
		    int userSelection = fileChooser.showSaveDialog(null);
		    if (userSelection == JFileChooser.APPROVE_OPTION) {
		        // Lấy đường dẫn được chọn
		        File fileToSave = fileChooser.getSelectedFile();
		        String filePath = fileToSave.getAbsolutePath();

		        // Kiểm tra xem đường dẫn đã kết thúc bằng phần mở rộng ".pdf" chưa
		        if (!filePath.toLowerCase().endsWith(".pdf")) {
		            filePath += ".pdf"; // Nếu không, thêm phần mở rộng ".pdf" vào đường dẫn
		        }
 
		     // Xuất báo cáo ra file PDF
		        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

	}
	
	public void xemTruocNhanVien() {
		try {
			JasperPrint jasperPrint = DanhSachNhanVien();
			JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}