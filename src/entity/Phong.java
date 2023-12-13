package entity;

import java.time.LocalTime;
import java.util.Objects;

public class Phong {
	private int maPhong;
	private String tenPhong;
	private int giaPhong;
	private String loaiPhong;
	private String tinhTrang;
	public int getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(int maPhong) {
		this.maPhong = maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public int getGiaPhong() {
		return giaPhong;
	}
	public void setGiaPhong(int giaPhong) {
		this.giaPhong = giaPhong;
	}
	public String getLoaiPhong() {
		return loaiPhong;
	}
	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	/**
	 * @param maPhong
	 * @param tenPhong
	 * @param giaPhong
	 * @param loaiPhong
	 * @param tinhTrang
	 */
	public Phong(int maPhong, String tenPhong, int giaPhong, String loaiPhong, String tinhTrang) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.giaPhong = giaPhong;
		this.loaiPhong = loaiPhong;
		this.tinhTrang = tinhTrang;
	}
	/**
	 * 
	 */
	public Phong() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}