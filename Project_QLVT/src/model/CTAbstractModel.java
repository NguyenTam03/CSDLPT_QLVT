package model;

public class CTAbstractModel {
	private String MAVT;
	private Integer SOLUONG;
	private Double DONGIA;
	
	public CTAbstractModel(String MAVT, Integer SOLUONG, Double DONGIA) {
		this.MAVT = MAVT;
		this.SOLUONG = SOLUONG;
		this.DONGIA = DONGIA;
	}
	
	public String getMAVT() {
		return MAVT;
	}
	public void setMAVT(String mAVT) {
		MAVT = mAVT;
	}
	public Integer getSOLUONG() {
		return SOLUONG;
	}
	public void setSOLUONG(Integer sOLUONG) {
		SOLUONG = sOLUONG;
	}
	public Double getDONGIA() {
		return DONGIA;
	}
	public void setDONGIA(Double dONGIA) {
		DONGIA = dONGIA;
	}
	
	
}
