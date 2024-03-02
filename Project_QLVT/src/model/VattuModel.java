package model;

public class VattuModel {
	private String mavt;
	private String tenVT;
	private String dvt;
	private String soLuongTon;
	
	public VattuModel(String mavt, String tenVT, String dvt, String soLuongTon) {
		super();
		this.mavt = mavt;
		this.tenVT = tenVT;
		this.dvt = dvt;
		this.soLuongTon = soLuongTon;
	}

	public String getMavt() {
		return mavt;
	}

	public void setMavt(String mavt) {
		this.mavt = mavt;
	}

	public String getTenVT() {
		return tenVT;
	}

	public void setTenVT(String tenVT) {
		this.tenVT = tenVT;
	}

	public String getDvt() {
		return dvt;
	}

	public void setDvt(String dvt) {
		this.dvt = dvt;
	}

	public String getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(String soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	
	
}	
