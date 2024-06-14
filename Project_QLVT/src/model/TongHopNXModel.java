package model;

public class TongHopNXModel {
	private String ngay;
	private int nhap;
	private String tyLeNhap;
	private int xuat;
	private String tyLeXuat;

	public TongHopNXModel(String ngay, int nhap, String tyLeNhap, int xuat, String tyLeXuat) {
		this.ngay = ngay;
		this.nhap = nhap;
		this.tyLeNhap = tyLeNhap;
		this.xuat = xuat;
		this.tyLeXuat = tyLeXuat;
	}

	public String getNgay() {
		return ngay;
	}

	public int getNhap() {
		return nhap;
	}

	public String getTyLeNhap() {
		return tyLeNhap;
	}

	public int getXuat() {
		return xuat;
	}

	public String getTyLeXuat() {
		return tyLeXuat;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public void setNhap(int nhap) {
		this.nhap = nhap;
	}

	public void setTyLeNhap(String tyLeNhap) {
		this.tyLeNhap = tyLeNhap;
	}

	public void setXuat(int xuat) {
		this.xuat = xuat;
	}

	public void setTyLeXuat(String tyLeXuat) {
		this.tyLeXuat = tyLeXuat;
	}

}
