package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import main.Program;
import model.JasperReportModel;
import model.TongHopNXModel;
import net.sf.jasperreports.engine.JRException;
import views.TongHopNhapXuat;

public class TongHopNhapXuatController implements IJasperReportController {
	private JasperReportModel<TongHopNXModel> reportModel;
	private String tuNgay, denNgay;
	private TongHopNhapXuat form;
	private int tongNhap, tongXuat;

	public TongHopNhapXuatController(TongHopNhapXuat form) {
		this.tongNhap = 0;
		this.tongXuat = 0;
		this.form = form;
		reportModel = new JasperReportModel<>();
		reportModel.setFilePath("reports/tong_hop_nhap_xuat.jrxml");
		reportModel.setFileReport("D:/Đồ án csdlpt/report/tong_nhap_xuat.html");
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

	private void getData() {
		String sql = "EXEC DBO.SP_TONG_HOP_NHAP_XUAT ?, ?";
		Program.myReader = Program.ExecSqlDataReader(sql, tuNgay, denNgay);

		try {
			while (Program.myReader.next()) {
				TongHopNXModel model = new TongHopNXModel(Program.myReader.getDate(1).toString(),
						Program.myReader.getInt(2), Program.myReader.getString(3), Program.myReader.getInt(4),
						Program.myReader.getString(5));
				tongNhap += model.getNhap();
				tongXuat += model.getXuat();
				reportModel.getList().add(model);
			}
			String chiNhanh = form.getComboBox().getItemAt(Program.mChinhanh);
			reportModel.getParameters().put("tenChiNhanh", chiNhanh);
			reportModel.getParameters().put("tuNgay", tuNgay);
			reportModel.getParameters().put("denNgay", denNgay);
			reportModel.getParameters().put("tongNhap", tongNhap);
			reportModel.getParameters().put("tongXuat", tongXuat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void preview() {
		getData();
		try {
			reportModel.preViewReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Report successfully");

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
