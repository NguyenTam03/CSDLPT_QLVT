package model;

public class CTDDHModel extends CTAbstractModel {
	private String MasoDDH;
	
	public CTDDHModel(String MasoDDH, String MAVT, Integer SOLUONG, Double DONGIA) {
		super(MAVT, SOLUONG, DONGIA);
		this.MasoDDH = MasoDDH;
	}
	
	public String getMasoDDH() {
		return MasoDDH;
	}
	public void setMasoDDH(String masoDDH) {
		MasoDDH = masoDDH;
	}
}
