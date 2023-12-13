package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectSQL;
import entity.ChiTietPhieuDatPhongDichVu;
import entity.DichVu;
import entity.KhachHang;
import entity.NhanVien;
import entity.ChiTietPhieuDatPhongDichVu;
import entity.PhieuDatPhong;

public class DatDichVu_dao extends ConnectSQL {
	ArrayList<DichVu> dsdv;
	ArrayList<ChiTietPhieuDatPhongDichVu> dsDatDichVu;

	public List<ChiTietPhieuDatPhongDichVu> docTuBang() {
		dsDatDichVu = new ArrayList<ChiTietPhieuDatPhongDichVu>();
		ConnectSQL.getInstance();
		ConnectSQL con = new ConnectSQL();
		try {
			con.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String sql = "Select * from ChiTietPhieuDatPhongDichVu ";
			Statement st = connect().createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				int maPhong = rs.getInt(2);
				int maDV = rs.getInt(3);
				int soLuong = rs.getInt(5);
				boolean tinhTien = rs.getBoolean(6);

				ChiTietPhieuDatPhongDichVu dv = new ChiTietPhieuDatPhongDichVu(id, maPhong, maDV, soLuong, false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dsDatDichVu;
	}

	public static List<String> getTenDichVu() {
		List<String> dichVu = new ArrayList<String>();
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
			String sql = "Select tenDV, donViTinh from DichVu";
			Statement st = con.connect().createStatement();
			// Thực thi câu lệnh sql
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String tenDV = rs.getString("tenDV");
				String donViTinh = rs.getString("donViTinh");
				dichVu.add(tenDV + "-" + donViTinh);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dichVu;
	}

	public static List<String> getTenPhong() {
		List<String> phong = new ArrayList<String>();
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
			String sql = "Select tenPhong from Phong where tinhTrang= N'Phòng đang sử dụng'";
			Statement st = con.connect().createStatement();
			// Thực thi câu lệnh sql
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String tenPhong = rs.getString("tenPhong");
				phong.add(tenPhong);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return phong;
	}

	public static PhieuDatPhong getMaPDP(String tenPhong) {
		PhieuDatPhong phong;
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
			PreparedStatement stmt = null;
			String sql = "SELECT PhieuDatPhong.maPDP\r\n" + "FROM Phong\r\n"
					+ "INNER JOIN PhieuDatPhong ON PhieuDatPhong.maPhong = phong.maPhong\r\n"
					+ "where Phong.tenPhong = ?";
			stmt = con.connect().prepareStatement(sql);
			stmt.setString(1, tenPhong);
			// Thực thi câu lệnh sql
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maPDP = rs.getInt("maPDP");
//				phong.add(maPhong);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public static void addDatDichVu(ChiTietPhieuDatPhongDichVu phieu) {
		ConnectSQL con = new ConnectSQL();
		PreparedStatement stmt = null;
		try {
			stmt = con.connect()
					.prepareStatement("insert into ChiTietPhieuDatPhongDichVu(maPDP, maDV, soLuong) values(?,?,?)");
			stmt.setInt(1, phieu.getMaPDP());
			stmt.setInt(2, phieu.getMaDV());
			stmt.setInt(3, phieu.getSoLuong());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public List<ChiTietPhieuDatPhongDichVu> getChiTietByMaPhieuDatPhong(int maPhieuDatPhong) {
		List<ChiTietPhieuDatPhongDichVu> chiTietList = new ArrayList<>();

		String sql = "SELECT * FROM ChiTietPhieuDatPhongDichVu WHERE maPDP = ?";
		try (PreparedStatement statement = connect().prepareStatement(sql)) {
			statement.setInt(1, maPhieuDatPhong);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					ChiTietPhieuDatPhongDichVu chiTiet = new ChiTietPhieuDatPhongDichVu();
					chiTiet.setMaPhieu(resultSet.getInt("maPhieuDatPhongDichVu"));
					chiTiet.setMaPDP(resultSet.getInt("maPDP"));
					chiTiet.setMaDV(resultSet.getInt("maDV"));
					chiTiet.setSoLuong(resultSet.getInt("SoLuong"));

					chiTietList.add(chiTiet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return chiTietList;
	}

	public static boolean updateDichVu(ChiTietPhieuDatPhongDichVu dv) throws SQLException {
		Connection con = ConnectSQL.connect();
		int n = 0;
		try {
			String sql = "update ChitietPhieuDatPhongDichVu set maPDP=?, maDV =?, soLuong =? where maPhieuDatPhongDichVu =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dv.getMaPDP());
			ps.setInt(2, dv.getMaDV());
			ps.setInt(3, dv.getSoLuong());
			ps.setInt(4, dv.getMaPhieu());
			n = ps.executeUpdate();
			ps.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}
	public static boolean deleteDichVu(int maPhieu) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from Chitietphieudatphongdichvu where maPhieudatphongdichvu=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, maPhieu);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
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
