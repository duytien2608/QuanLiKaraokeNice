package entity;

import java.util.Objects;
import java.util.Vector;

public class KhachHang {
	private int maKH;
	private String tenKH;
	private String sdt;
	private String email;
	private String diaChi;
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	/**
	 * @param maKH
	 * @param tenKH
	 * @param sdt
	 * @param email
	 * @param diaChi
	 */
	public KhachHang(int maKH, String tenKH, String sdt, String email, String diaChi) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.email = email;
		this.diaChi = diaChi;
	}
	/**
	 * 
	 */
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}