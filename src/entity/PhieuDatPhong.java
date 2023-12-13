package entity;

import java.util.Date;

public class PhieuDatPhong {
	private int maPDP;
	private int maPhong;
	private int maKH;
	private String tenKhachHang;
	private String SDT;
	private int soLuongNguoi;
	private String thoiGianDat;
	private boolean trangThai;
	private String tenNhanVien;
	
	/**
	 * @param maPDP
	 * @param maPhong
	 * @param maKH
	 * @param tenKhachHang
	 * @param sDT
	 * @param soLuongNguoi
	 * @param thoiGianDat
	 * @param trangThai
	 * @param tenNhanVien
	 */
	public PhieuDatPhong(int maPDP, int maPhong, int maKH, String tenKhachHang, String sDT, int soLuongNguoi,
			String thoiGianDat, boolean trangThai, String tenNhanVien) {
		super();
		this.maPDP = maPDP;
		this.maPhong = maPhong;
		this.maKH = maKH;
		this.tenKhachHang = tenKhachHang;
		SDT = sDT;
		this.soLuongNguoi = soLuongNguoi;
		this.thoiGianDat = thoiGianDat;
		this.trangThai = trangThai;
		this.tenNhanVien = tenNhanVien;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public int getMaPDP() {
		return maPDP;
	}
	public void setMaPDP(int maPDP) {
		this.maPDP = maPDP;
	}
	public int getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(int maPhong) {
		this.maPhong = maPhong;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public int getSoLuongNguoi() {
		return soLuongNguoi;
	}
	public void setSoLuongNguoi(int soLuongNguoi) {
		this.soLuongNguoi = soLuongNguoi;
	}
	public String getThoiGianDat() {
		return thoiGianDat;
	}
	public void setThoiGianDat(String thoiGianDat) {
		this.thoiGianDat = thoiGianDat;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public PhieuDatPhong() {
		super();
		// TODO Auto-generated constructor stub
	}
		
}
