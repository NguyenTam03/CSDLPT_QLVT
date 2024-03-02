package model;

public class CTPNModel extends CTAbstractModel {
	private String MAPN;

	public CTPNModel(String MAVT, Integer SOLUONG, Double DONGIA, String mAPN) {
		super(MAVT, SOLUONG, DONGIA);
		MAPN = mAPN;
	}

	public String getMAPN() {
		return MAPN;
	}

	public void setMAPN(String mAPN) {
		MAPN = mAPN;
	}
	
}
