package entity;

import java.util.Objects;
import java.util.Vector;

public class NhanVien {
	protected int maNV;
	protected String tenNV;
	protected boolean gioiTinh;
	protected String ngaySinh;
	protected String SDT;
	protected String email;
	protected String CCCD;
	protected String chucVu;
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	/**
	 * @param maNV
	 * @param tenNV
	 * @param gioiTinh
	 * @param ngaySinh
	 * @param sDT
	 * @param email
	 * @param cCCD
	 * @param chucVu
	 */
	public NhanVien(int maNV, String tenNV, boolean gioiTinh, String ngaySinh, String sDT, String email, String cCCD,
			String chucVu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		SDT = sDT;
		this.email = email;
		CCCD = cCCD;
		this.chucVu = chucVu;
	}
	/**
	 * 
	 */
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	}