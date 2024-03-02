package model;

import java.sql.Date;

public class DatHangModel {
	private String MasoDDH;
	private Date NGAY;
	private String NhaCC;
	private Integer MANV;
	private String MAKHO;
	public String getMasoDDH() {
		return MasoDDH;
	}
	public void setMasoDDH(String masoDDH) {
		MasoDDH = masoDDH;
	}
	public Date getNGAY() {
		return NGAY;
	}
	public void setNGAY(Date nGAY) {
		NGAY = nGAY;
	}
	public String getNhaCC() {
		return NhaCC;
	}
	public void setNhaCC(String nhaCC) {
		NhaCC = nhaCC;
	}
	public Integer getMANV() {
		return MANV;
	}
	public void setMANV(Integer mANV) {
		MANV = mANV;
	}
	public String getMAKHO() {
		return MAKHO;
	}
	public void setMAKHO(String mAKHO) {
		MAKHO = mAKHO;
	}
	
	
}
