package model;

import java.sql.Date;

public class NhanVienModel {
	private Integer MANV;
	private String HO;
	private String TEN;
	private String SOCMND;
	private String DIACHI;
	private Date NGAYSINH;
	private Double LUONG;
	private String MACN;
	private Boolean TrangThaiXoa;
	public Integer getMANV() {
		return MANV;
	}
	public void setMANV(Integer mANV) {
		MANV = mANV;
	}
	public String getHO() {
		return HO;
	}
	public void setHO(String hO) {
		HO = hO;
	}
	public String getTEN() {
		return TEN;
	}
	public void setTEN(String tEN) {
		TEN = tEN;
	}
	public String getSOCMND() {
		return SOCMND;
	}
	public void setSOCMND(String sOCMND) {
		SOCMND = sOCMND;
	}
	public String getDIACHI() {
		return DIACHI;
	}
	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}
	public Date getNGAYSINH() {
		return NGAYSINH;
	}
	public void setNGAYSINH(Date nGAYSINH) {
		NGAYSINH = nGAYSINH;
	}
	public Double getLUONG() {
		return LUONG;
	}
	public void setLUONG(Double lUONG) {
		LUONG = lUONG;
	}
	public String getMACN() {
		return MACN;
	}
	public void setMACN(String mACN) {
		MACN = mACN;
	}
	public Boolean getTrangThaiXoa() {
		return TrangThaiXoa;
	}
	public void setTrangThaiXoa(Boolean trangThaiXoa) {
		TrangThaiXoa = trangThaiXoa;
	}
	
	
}
