package model;

public class CTPXModel extends CTAbstractModel {
	private String MAPX;

	public CTPXModel(String MAVT, Integer SOLUONG, Double DONGIA, String mAPX) {
		super(MAVT, SOLUONG, DONGIA);
		MAPX = mAPX;
	}

	public String getMAPX() {
		return MAPX;
	}

	public void setMAPX(String mAPX) {
		MAPX = mAPX;
	}
	
	
}
