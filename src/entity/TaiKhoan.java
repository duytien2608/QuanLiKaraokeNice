package entity;

import java.util.Objects;

public class TaiKhoan {
	private int maNV;
	private String tenDangNhap;
	private String matKhau;
	private boolean kichHoat;
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public boolean isKichHoat() {
		return kichHoat;
	}
	public void setKichHoat(boolean kichHoat) {
		this.kichHoat = kichHoat;
	}
	/**
	 * @param maNV
	 * @param tenDangNhap
	 * @param matKhau
	 * @param kichHoat
	 */
	public TaiKhoan(int maNV, String tenDangNhap, String matKhau, boolean kichHoat) {
		super();
		this.maNV = maNV;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.kichHoat = kichHoat;
	}
	/**
	 * 
	 */
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
