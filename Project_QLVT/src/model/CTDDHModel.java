package model;

public class CTDDHModel extends CTAbstractModel {
	private String maSoDDH;

	public CTDDHModel(String mavt, Integer soLuong, Double donGia, String maSoDDH) {
		super(mavt, soLuong, donGia);
		this.maSoDDH = maSoDDH;
	}

	public String getMaSoDDH() {
		return maSoDDH;
	}

	public void setMaSoDDH(String maSoDDH) {
		this.maSoDDH = maSoDDH;
	}
	
	
}
