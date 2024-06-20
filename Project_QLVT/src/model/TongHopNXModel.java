package model;

public class TongHopNXModel {
	private String ngay;
	private float nhap;
	private String tyLeNhap;
	private float xuat;
	private String tyLeXuat;

	public TongHopNXModel() {
	}

	public TongHopNXModel(String ngay, float nhap, String tyLeNhap, float xuat, String tyLeXuat) {
		this.ngay = ngay;
		this.nhap = nhap;
		this.tyLeNhap = tyLeNhap;
		this.xuat = xuat;
		this.tyLeXuat = tyLeXuat;
	}

	public String getNgay() {
		return ngay;
	}

	public float getNhap() {
		return nhap;
	}

	public String getTyLeNhap() {
		return tyLeNhap;
	}

	public float getXuat() {
		return xuat;
	}

	public String getTyLeXuat() {
		return tyLeXuat;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public void setNhap(float nhap) {
		this.nhap = nhap;
	}

	public void setTyLeNhap(String tyLeNhap) {
		this.tyLeNhap = tyLeNhap;
	}

	public void setXuat(float xuat) {
		this.xuat = xuat;
	}

	public void setTyLeXuat(String tyLeXuat) {
		this.tyLeXuat = tyLeXuat;
	}

}
