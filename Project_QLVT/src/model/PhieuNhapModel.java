package model;

import java.sql.Date;

public class PhieuNhapModel {
	private String MAPN;
	private Date NGAY;
	private String MasoDDH;
	private Integer MANV;
	private String MAKHO;
	public String getMAPN() {
		return MAPN;
	}
	public void setMAPN(String mAPN) {
		MAPN = mAPN;
	}
	public Date getNGAY() {
		return NGAY;
	}
	public void setNGAY(Date nGAY) {
		NGAY = nGAY;
	}
	public String getMasoDDH() {
		return MasoDDH;
	}
	public void setMasoDDH(String masoDDH) {
		MasoDDH = masoDDH;
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
