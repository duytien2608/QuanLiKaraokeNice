package dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import connectDB.ConnectSQL;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;

public class ThanhToan_dao extends ConnectSQL {
	ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();

	public ArrayList<HoaDon> doctuBang() {
		ConnectSQL con = new ConnectSQL();
		try {
			con.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String sql = "Select * from HoaDon where trangthai = 0";
			Statement st = con.connect().createStatement();
			// Thực thi câu lệnh sql
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int maHD = rs.getInt(1);
				int maPDP = rs.getInt(2);
				String thoiGianKetThuc = rs.getString(3);
				double tongTien = rs.getDouble(4);
				boolean trangThai = rs.getBoolean(5);
				int maNV = rs.getInt(6);
				HoaDon hd = new HoaDon(maHD, maPDP, thoiGianKetThuc, tongTien, trangThai, maNV);
				dshd.add(hd);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dshd;
	}

	public static String[] getDataFromTable(int maHD) throws SQLException {
		String[] thongTin = new String[5];
		ConnectSQL con = new ConnectSQL();
		String sql = "select tenKH,sdt,giaPhong,tongTien,\r\n"
				+ "	datediff(minute,thoigiandat,thoigianketthuc) as tongThoiGian\r\n" + "	from HoaDon\r\n"
				+ "	inner join PhieuDatPhong on phieudatphong.mapdp = hoadon.mapdp\r\n"
				+ "	inner join phong on phieudatphong.maPhong = phong.maphong\r\n"
				+ "	where maHD = ? and hoaDon.trangthai=0";
		PreparedStatement ps = con.connect().prepareStatement(sql);
		ps.setInt(1, maHD);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			thongTin[0] = rs.getString("tenKH");
			thongTin[1] = rs.getString("sdt");
			thongTin[2] = rs.getString("giaPhong");
			thongTin[3] = rs.getString("tongTien");
			thongTin[4] = rs.getString("tongThoiGian");

		}
		return thongTin;
	}

	public static boolean addChiTietHoaDon(ChiTietHoaDon cthd) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into ChiTietHoaDon(maHD,tongThoiGian,tongTienHat,tongTien) values(?,?,?,?)");
			stmt.setInt(1, cthd.getMaHD());
			stmt.setInt(2, cthd.getTongThoiGian());
			stmt.setInt(3, cthd.getTongTienHat());
			stmt.setInt(4, cthd.getTongTien());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return n > 0;
	}
	public static boolean updateTrangThaiHoaDon(int maHD) {
		int n = 0;
		ConnectSQL con = new ConnectSQL();
		PreparedStatement stmt = null;
		String sql = "UPDATE hoaDon set trangThai = 1 where maHD = ?";
		try {
			stmt = con.connect().prepareStatement(sql);
			stmt.setInt(1, maHD);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n > 0;
	}
}
