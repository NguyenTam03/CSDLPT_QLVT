package model;

public class CTPXModel extends CTAbstractModel {
	private String mapx;

	public CTPXModel(String mavt, Integer soLuong, Float donGia, String mapx) {
		super(mavt, soLuong, donGia);
		this.mapx = mapx;
	}

	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	
	
	
}
