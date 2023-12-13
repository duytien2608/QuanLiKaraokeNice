package entity;

import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class HoaDon {
	private int maHD;
	private int maPDP;
	private String thoiGianKetThuc;
	private double tongTien;
	private boolean trangThai;
	private int maNV;
	
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	
	public String getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}
	public void setThoiGianKetThuc(String thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	/**
	 * @param maHD
	 * @param maPhieuDatPhongDichVu
	 * @param thoiGianKetThuc
	 * @param tongTien
	 * @param maNV
	 */
	public int getMaPDP() {
		return maPDP;
	}
	public void setMaPDP(int maPDP) {
		this.maPDP = maPDP;
		
	}
	/**
	 * @param maHD
	 * @param maPDP
	 * @param thoiGianKetThuc
	 * @param tongTien
	 * @param maNV
	 * @param trangThai
	 */
	/**
	 * @param maHD
	 * @param maPDP
	 * @param thoiGianKetThuc
	 * @param tongTien
	 * @param trangThai
	 * @param maNV
	 */
	public HoaDon(int maHD, int maPDP, String thoiGianKetThuc, double tongTien, boolean trangThai, int maNV) {
		super();
		this.maHD = maHD;
		this.maPDP = maPDP;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
		this.maNV = maNV;
	}
	/**
	 * 
	 */
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
