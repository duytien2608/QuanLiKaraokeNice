package dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectSQL;
import entity.KhachHang;
import entity.NhanVien;

public class NhanVien_dao extends ConnectSQL {
	ArrayList<NhanVien> dsnv;

	public NhanVien_dao() {
		dsnv = new ArrayList<NhanVien>();
	}

	public ArrayList<NhanVien> doctuBang() {
		ConnectSQL.getInstance();
//		Connection con = ConnectSQL.getConnection();
		ConnectSQL con = new ConnectSQL();
		try {
			con.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String sql = "Select * from NhanVien";
			Statement st = con.connect().createStatement();
			// Thực thi câu lệnh sql
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int maNV = rs.getInt(1);
				String tenNV = rs.getString(2);
				boolean gioiTinh = rs.getBoolean(3);
				String ngaySinh = rs.getString(4);
				String SDT = rs.getString(5);
				String email = rs.getString(6);
				String CCCD = rs.getString(7);
				String chucVu = rs.getString(8);
				NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, SDT, email, CCCD, chucVu);
				dsnv.add(nv);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dsnv;
	}

	public static boolean addNhanVien(NhanVien nv) {
	Connection con = ConnectSQL.getConnection();
	PreparedStatement stmt = null;
	int n = 0;
	try {
		stmt = con.prepareStatement("insert into NhanVien(tenNV, gioiTinh, ngaySinh, sdt, email, cccd, chucVu) values(?,?,?,?,?,?,?)");
		stmt.setString(1, nv.getTenNV());
		stmt.setBoolean(2, nv.isGioiTinh());
		stmt.setString(3, nv.getNgaySinh());
		stmt.setString(4, nv.getSDT());
		stmt.setString(5, nv.getEmail());
		stmt.setString(6, nv.getCCCD());
		stmt.setString(7, nv.getChucVu());
		n = stmt.executeUpdate();
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	} 
	return n > 0;
}
	
	public static boolean updateNhanVien(NhanVien nv) throws SQLException {
		Connection con = ConnectSQL.connect();
		int n = 0;
		try {
			String sql = "update NhanVien set tenNV=?, gioiTinh =?, ngaySinh =?, SDT =?, email =?, CCCD =?, chucVu =? where maNV =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getTenNV());
			ps.setBoolean(2, nv.isGioiTinh());
			ps.setString(3, nv.getNgaySinh());
			ps.setString(4, nv.getSDT());
			ps.setString(5, nv.getEmail());
			ps.setString(6, nv.getCCCD());
			ps.setString(7, nv.getChucVu());
			ps.setInt(8, nv.getMaNV());
			n = ps.executeUpdate();
			ps.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n > 0;
	} 
	public static NhanVien timTenNVTheoMa(int maNV) {
        // Thực hiện truy vấn SQL để kiểm tra số điện thoại trong cơ sở dữ liệu
        // Trả về tên khách hàng nếu có, ngược lại trả về null
		NhanVien NhanVien = null;
        try (Connection connection = connect()) {
            String query = "SELECT * FROM NhanVien WHERE maNV = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, maNV);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
    				String tenNV = rs.getString(1);
    				boolean gioiTinh = rs.getBoolean(2);
    				String ngaySinh = rs.getString(3);
    				String SDT = rs.getString(4);
    				String email = rs.getString(5);
    				String CCCD = rs.getString(6);
    				String chucVu = rs.getString(7);
    				NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, SDT, email, CCCD, chucVu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NhanVien;
    }
	public static int timMaNVTheoTen(String tenNV) {
		int maNV = -1;
        try (Connection connection = connect()) {
            String query = "SELECT maNV FROM NhanVien WHERE tenNV = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, tenNV);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                	maNV = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return maNV;
	}
	private void close(PreparedStatement ps) {
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	
}
