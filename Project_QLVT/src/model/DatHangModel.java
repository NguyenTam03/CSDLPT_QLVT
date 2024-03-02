package model;

import java.sql.Date;

public class DatHangModel {
	private String maSoDDH;
	private Date ngay;
	private String nhaCC;
	private Integer manv;
	private String makho;
	
	public DatHangModel(String maSoDDH, Date ngay, String nhaCC, Integer manv, String makho) {
		super();
		this.maSoDDH = maSoDDH;
		this.ngay = ngay;
		this.nhaCC = nhaCC;
		this.manv = manv;
		this.makho = makho;
	}

	public String getMaSoDDH() {
		return maSoDDH;
	}

	public void setMaSoDDH(String maSoDDH) {
		this.maSoDDH = maSoDDH;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public String getNhaCC() {
		return nhaCC;
	}

	public void setNhaCC(String nhaCC) {
		this.nhaCC = nhaCC;
	}

	public Integer getManv() {
		return manv;
	}

	public void setManv(Integer manv) {
		this.manv = manv;
	}

	public String getMakho() {
		return makho;
	}

	public void setMakho(String makho) {
		this.makho = makho;
	}
	
	
}
