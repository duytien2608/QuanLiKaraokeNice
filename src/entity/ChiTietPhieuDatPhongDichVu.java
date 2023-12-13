package entity;

public class ChiTietPhieuDatPhongDichVu {
	public int maPhieu;
	public int maPDP;
	public int maDV;
	public int soLuong;
	public boolean tinhTien;
	public int getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(int maPhieu) {
		this.maPhieu = maPhieu;
	}
	public int getMaPDP() {
		return maPDP;
	}
	public void setMaPDP(int maPDP) {
		this.maPDP = maPDP;
	}
	public int getMaDV() {
		return maDV;
	}
	public void setMaDV(int maDV) {
		this.maDV = maDV;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public boolean isTinhTien() {
		return tinhTien;
	}
	public void setTinhTien(boolean tinhTien) {
		this.tinhTien = tinhTien;
	}
	/**
	 * @param maPhieu
	 * @param maPDP
	 * @param maDV
	 * @param soLuong
	 * @param tinhTien
	 */
	public ChiTietPhieuDatPhongDichVu(int maPhieu, int maPDP, int maDV, int soLuong, boolean tinhTien) {
		super();
		this.maPhieu = maPhieu;
		this.maPDP = maPDP;
		this.maDV = maDV;
		this.soLuong = soLuong;
		this.tinhTien = tinhTien;
	}
	/**
	 * 
	 */
	public ChiTietPhieuDatPhongDichVu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
