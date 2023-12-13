package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connectDB.ConnectSQL;
import entity.PhieuDatPhong;
import entity.Phong;

public class DatPhong_dao extends ConnectSQL {
	private String sql;
	public List<PhieuDatPhong> docTuBang() {
		ConnectSQL.getInstance();
		ConnectSQL con = new ConnectSQL();
		ArrayList<PhieuDatPhong> dsdp = new ArrayList<PhieuDatPhong>();
		try {
			sql = "select * from PhieuDatPhong";
//			System.out.println(sql);
			Statement statement = con.connect().createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int maPDP = rs.getInt(1);
				int maPhong = rs.getInt(2);
				int maKH = rs.getInt(3);
				String tenKH = rs.getString(4);
				String sdt = rs.getString(5);
				int soLuongNguoi = rs.getInt(6);
				String thoiGianDat = rs.getString(7);
				String tenNhanVien = rs.getString(8);

				dsdp.add(new PhieuDatPhong(maPDP, maPhong, maKH, tenKH, sdt, soLuongNguoi, thoiGianDat,false, tenNhanVien));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsdp;
	}
	
	public static boolean updateTrangThaiPhong(int maPhong) {
		int n = 0;
		ConnectSQL con = new ConnectSQL();
		PreparedStatement stmt = null;
		String sql = "UPDATE Phong set tinhTrang = N'Phòng đang sử dụng' where maPhong = ?";
		try {
			stmt = con.connect().prepareStatement(sql);
			stmt.setInt(1, maPhong);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n > 0;
	}
	public static void addDatPhong(PhieuDatPhong pdp) {
		ConnectSQL con = new ConnectSQL();
		PreparedStatement stmt = null;
//		System.out.println(phong.toString());
		try {
			stmt = con.connect().prepareStatement("insert into PhieuDatPhong(maPhong,maKH,tenKH,sdt,soLuongNguoi,thoiGianDat,trangThai,tenNhanVien) values(?,?,?,?,?,?,?,?)");
			stmt.setInt(1, pdp.getMaPhong());
			stmt.setInt(2, pdp.getMaKH());
			stmt.setString(3, pdp.getTenKhachHang());
			stmt.setString(4, pdp.getSDT());
			stmt.setInt(5, pdp.getSoLuongNguoi());
			stmt.setString(6, pdp.getThoiGianDat());
			stmt.setBoolean(7, false);
			stmt.setString(8, pdp.getTenNhanVien());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	public static PhieuDatPhong getMaPDP(String tenPhong) {
		PhieuDatPhong phong = null;
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
			String sql = "SELECT PhieuDatPhong.maPDP\r\n"
					+ "FROM Phong\r\n"
					+ "INNER JOIN PhieuDatPhong ON PhieuDatPhong.maPhong = phong.maPhong\r\n"
					+ "where Phong.tenPhong = ?";
			PreparedStatement ps = con.connect().prepareStatement(sql);
			ps.setString(1, tenPhong);
			// Thực thi câu lệnh sql
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int maPDP = rs.getInt("maPDP");
				phong = new PhieuDatPhong(maPDP, 0, 0, null, null, 0, null, false, null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return phong;
	}

	private static void close(PreparedStatement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
}
