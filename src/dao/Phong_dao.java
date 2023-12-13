package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectSQL;
import entity.Phong;

public class Phong_dao extends ConnectSQL {
	private String sql;


	public List<Phong> docTuBang() {
		ConnectSQL.getInstance();
		ConnectSQL con = new ConnectSQL();
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			sql = "select * from Phong";
//			System.out.println(sql);
			Statement statement = con.connect().createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int maPhong = rs.getInt(1);
				String tenPhong = rs.getString(2);
				int giaPhong = rs.getInt(3);
				String loaiPhong = rs.getString(4);
				String tinhTrang = rs.getString(5);

				dsPhong.add(new Phong(maPhong, tenPhong, giaPhong, loaiPhong, tinhTrang));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	public static void addPhong(Phong phong) {
		ConnectSQL con = new ConnectSQL();
		PreparedStatement stmt = null;
//		System.out.println(phong.toString());
		try {
			stmt = con.connect().prepareStatement("insert into Phong(tenPhong, giaPhong, loaiPhong,tinhTrang) values(?,?,?,?)");
			stmt.setString(1, phong.getTenPhong());
			stmt.setInt(2, phong.getGiaPhong());
			stmt.setString(3, phong.getLoaiPhong());
			stmt.setString(4, phong.getTinhTrang());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public static boolean updatePhong(Phong phong) {
		int n = 0;
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "update Phong set  tenPhong=?, giaPhong =?, loaiPhong =?, tinhTrang =? where maPhong=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, phong.getTenPhong());
			stmt.setInt(2, phong.getGiaPhong());
			stmt.setString(3, phong.getLoaiPhong());
			stmt.setString(4, phong.getTinhTrang());
			stmt.setInt(5, phong.getMaPhong());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return n > 0;
	}

	public static void deletePhong(String maPhong) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from Phong where MaPhong=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maPhong);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
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
