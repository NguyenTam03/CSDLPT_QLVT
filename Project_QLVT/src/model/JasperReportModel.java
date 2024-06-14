package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportModel<E> {
	private String filePath;
	private String fileReport;
	private List<E> list;
	private Map<String, Object> parameters;
	private JRBeanCollectionDataSource dataSource;
	private JasperReport report;
	private JasperPrint print;

	public JasperReportModel() {
		this.list = new ArrayList<E>();
		this.parameters = new HashMap<String, Object>();
	}

	public JasperReportModel(String filePath, Map<String, Object> parameters, JRBeanCollectionDataSource dataSource,
			JasperReport report, JasperPrint print) {
		this.filePath = filePath;
		this.parameters = parameters;
		this.dataSource = dataSource;
		this.report = report;
		this.print = print;

	}

	public void saveReport() throws JRException {
		dataSource = new JRBeanCollectionDataSource(list);
		// truy xuất đến file report
		InputStream inputStream = JasperReportModel.class.getClassLoader().getResourceAsStream(filePath);
		// --
		JasperReport report = JasperCompileManager.compileReport(inputStream);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
		JasperExportManager.exportReportToPdfFile(print, fileReport);
	}

	public void preViewReport() throws JRException {
		dataSource = new JRBeanCollectionDataSource(list);
		// truy xuất đến file report
		InputStream inputStream = JasperReportModel.class.getClassLoader().getResourceAsStream(filePath);
		// --
		JasperReport report = JasperCompileManager.compileReport(inputStream);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
		JasperViewer.viewReport(print, false);
	}

	public String getFileReport() {
		return fileReport;
	}

	public List<E> getList() {
		return list;
	}

	public void setFileReport(String fileReport) {
		this.fileReport = fileReport;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public String getFilePath() {
		return filePath;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public JRBeanCollectionDataSource getDataSource() {
		return dataSource;
	}

	public JasperReport getReport() {
		return report;
	}

	public JasperPrint getPrint() {
		return print;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public void setDataSource(JRBeanCollectionDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setReport(JasperReport report) {
		this.report = report;
	}

	public void setPrint(JasperPrint print) {
		this.print = print;
	}

}
