package entity;

import java.util.Objects;

public class DichVu {
	private int maDV;
	private String tenDV;
	private String loaiDichVu;
	private String donViTinh;
	private int giaNhap;
	private int giaBan;
	
	public int getMaDV() {
		return maDV;
	}
	public void setMaDV(int maDV) {
		this.maDV = maDV;
	}
	public String getTenDV() {
		return tenDV;
	}
	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}
	public String getLoaiDichVu() {
		return loaiDichVu;
	}
	public void setLoaiDichVu(String loaiDichVu) {
		this.loaiDichVu = loaiDichVu;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public int getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(int giaNhap) {
		this.giaNhap = giaNhap;
	}
	public int getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}
	/**
	 * @param maDV
	 * @param tenDV
	 * @param loaiDichVu
	 * @param donViTinh
	 * @param giaNhap
	 * @param giaBan
	 */
	public DichVu(int maDV, String tenDV, String loaiDichVu, String donViTinh, int giaNhap, int giaBan) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.loaiDichVu = loaiDichVu;
		this.donViTinh = donViTinh;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
	}
	/**
	 * 
	 */
	public DichVu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}