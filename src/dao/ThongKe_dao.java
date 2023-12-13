package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectSQL;

public class ThongKe_dao extends ConnectSQL {
	public static String[] ThongKeDoanhThu() throws SQLException {
		String[] thongTin = new String[2];
		ConnectSQL con = new ConnectSQL();
		String sql = "select  sum(hoadon.tongTien-(soluong*giaNhap)) as doanhThuDichVu, sum(chitiethoadon.tongtien) as TongTienDoanhThu\r\n"
				+ "	from hoadon\r\n"
				+ "	inner join chitiethoadon on chitiethoadon.mahd = hoadon.mahd\r\n"
				+ "	inner join phieudatphong on hoadon.mapdp = phieudatphong.mapdp\r\n"
				+ "	inner join chitietphieudatphongdichvu on chitietphieudatphongdichvu.mapdp = phieudatphong.mapdp\r\n"
				+ "	inner join dichvu on chitietphieudatphongdichvu.madv = dichvu.madv";
		PreparedStatement ps = con.connect().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			thongTin[0] = rs.getString("doanhThuDichVu");
			thongTin[1] = rs.getString("tongTienDoanhThu");
		}
		return thongTin;
	}
	public static String[] ThongKeTheoNhanVien(String tenNV) throws SQLException {
		String[] thongTin = new String[5];
		ConnectSQL con = new ConnectSQL();
		String sql = "	select sum(hoadon.tongTien) as tienDichVu, sum(chitiethoadon.tongtien) as TongTien\r\n"
				+ "	from hoadon\r\n"
				+ "	inner join nhanvien on hoadon.manv = nhanvien.manv\r\n"
				+ "	inner join chitiethoadon on chitiethoadon.mahd = hoadon.mahd\r\n"
				+ "	where tenNV = ?";
		PreparedStatement ps = con.connect().prepareStatement(sql);
			ps.setString(1, tenNV);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			thongTin[0] = rs.getString("tienDichVu");
			thongTin[1] = rs.getString("TongTien");
		}
		return thongTin;
	}
	public static int ThongKeTheoKH(String tenKH) throws SQLException {
		int tongTien = 0;
		ConnectSQL con = new ConnectSQL();
		String sql="select sum(chitiethoadon.tongtien) as TongTien \r\n"
				+ "	from chitiethoadon\r\n"
				+ "	inner join hoadon on hoadon.mahd = chitiethoadon.mahd\r\n"
				+ "	inner join phieudatphong on phieudatphong.mapdp = hoadon.mapdp\r\n"
				+ "	where tenKH =?";
		PreparedStatement ps = con.connect().prepareStatement(sql);
		ps.setString(1, tenKH);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			tongTien = rs.getInt(1);
		}
		return tongTien;
	}
	public static int ThongKeTheoPhong(String tenPhong) throws SQLException {
		int tongTien = 0;
		ConnectSQL con = new ConnectSQL();
		String sql = "select Sum(chitiethoadon.tongtien) as tongTien\r\n"
				+ "	from chitiethoadon\r\n"
				+ "	inner join hoadon on hoadon.mahd = chitiethoadon.mahd\r\n"
				+ "	inner join phieudatphong on phieudatphong.mapdp = hoadon.mapdp\r\n"
				+ "	inner join Phong on phong.maphong = phieudatphong.maphong\r\n"
				+ "	where tenPhong = ?";
		PreparedStatement ps = con.connect().prepareStatement(sql);
		ps.setString(1, tenPhong);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			tongTien = rs.getInt(1);
		}
		return tongTien;
	}
}
