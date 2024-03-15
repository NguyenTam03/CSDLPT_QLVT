package model;

public class CTAbstractModel {
	private String mavt;
	private Integer soLuong;
	private Double donGia;
	
	public CTAbstractModel() {
		
	}
	
	public CTAbstractModel(String mavt, Integer soLuong, Double donGia) {
		
		this.mavt = mavt;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public String getMavt() {
		return mavt;
	}

	public void setMavt(String mavt) {
		this.mavt = mavt;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}
	

	
}
