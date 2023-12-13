package entity;

public class ChiTietHoaDon {
	int maCTHD;
	int maHD;
	int tongThoiGian;
	int tongTienHat;
	int tongTien;
	/**
	 * @param maCTHD
	 * @param maHD
	 * @param tongThoiGian
	 * @param tongTienHat
	 * @param tongTien
	 */
	public ChiTietHoaDon(int maCTHD, int maHD, int tongThoiGian, int tongTienHat, int tongTien) {
		super();
		this.maCTHD = maCTHD;
		this.maHD = maHD;
		this.tongThoiGian = tongThoiGian;
		this.tongTienHat = tongTienHat;
		this.tongTien = tongTien;
	}
	/**
	 * 
	 */
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaCTHD() {
		return maCTHD;
	}
	public void setMaCTHD(int maCTHD) {
		this.maCTHD = maCTHD;
	}
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public int getTongThoiGian() {
		return tongThoiGian;
	}
	public void setTongThoiGian(int tongThoiGian) {
		this.tongThoiGian = tongThoiGian;
	}
	public int getTongTienHat() {
		return tongTienHat;
	}
	public void setTongTienHat(int tongTienHat) {
		this.tongTienHat = tongTienHat;
	}
	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	
}
