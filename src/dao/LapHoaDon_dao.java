package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import connectDB.ConnectSQL;
import entity.ChiTietPhieuDatPhongDichVu;
import entity.HoaDon;
import entity.NhanVien;

public class LapHoaDon_dao extends ConnectSQL {
	 public static List<Object[]> getDataFromDatabase() throws SQLException {
	        List<Object[]> resultList = new ArrayList<>();

		        String sql = "Select phieudatphong.Mapdp,phong.maphong,tenphong, tenkh, soluongnguoi,thoigiandat from PhieuDatphong\r\n"
		        		+ "inner join phong on phieudatphong.maphong = phong.maphong where phieudatphong.trangthai = 0\r\n";

	        try (PreparedStatement preparedStatement = connect().prepareStatement(sql)) {
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    Object[] rowData = new Object[]{
	                            resultSet.getInt("MaPDP"),
	                            resultSet.getInt("maPhong"),
	                            resultSet.getString("tenPhong"),
	                            resultSet.getString("tenKH"),
	                            resultSet.getInt("soLuongNguoi"),
	                            resultSet.getString("thoigianDat"),
	                    };

	                    resultList.add(rowData);
	                }
	            }
	        }

	        return resultList;
	    }
	 public static List<Object[]> getDichVuDangSuDung(int maPDP) throws SQLException {
	        List<Object[]> resultList = new ArrayList<>();

	        String sql =  "select tenDV, donViTinh, soluong, giaban\r\n"
	        		+ "from chitietphieudatphongdichvu\r\n"
	        		+ "inner join dichvu on chitietphieudatphongdichvu.madv = dichvu.madv where mapdp=?";

	        try (PreparedStatement preparedStatement = connect().prepareStatement(sql)) {
	        		preparedStatement.setInt(1, maPDP);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    Object[] rowData = new Object[]{
	                            resultSet.getString("tendv"),
	                            resultSet.getString("donvitinh"),
	                            resultSet.getInt("soluong"),
	                            resultSet.getInt("giaban"),
	                    };

	                    resultList.add(rowData);
	                }
	            }
	        }

	        return resultList;
	    }
	 public static double TongTienDichVuTheoMa(int mapdp) throws SQLException {
		 double tongTien = 0;
		 String sql = "select sum(dichvu.giaban*chitietphieudatphongdichvu.soluong) as tongtien\r\n"
		 		+ "from chitietphieudatphongdichvu\r\n"
		 		+ "inner join dichvu on chiTietPhieudatphongdichvu.maDV = DichVu.maDV where mapdp =?";
		 PreparedStatement ps = connect().prepareStatement(sql);
		 ps.setInt(1, mapdp);
		 ResultSet rs = ps.executeQuery();
		 if(rs.next()) {
			 tongTien = rs.getDouble("TongTien");
		 }
		 return tongTien;
	 }
	 public static List<Object[]> getMaPhieu(int maPDP) throws SQLException{
		 List<Object[]> resultList = new ArrayList<Object[]>();
		 String sql =  ("Select maphieudatphongdichvu from chitietphieudatphongdichvu where mapdp=?");
		 PreparedStatement ps = connect().prepareStatement(sql);
		 ps.setInt(1, maPDP);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()) {
			 Object[] rowData = new Object[]{
                     rs.getInt("MaPDP")};
			 resultList.add(rowData);
		 }		
		 return resultList;
	 }
	 public static List<String> getMaPhieuDichVuList(int maPDP) throws SQLException {
	        List<String> maPhieuDichVuList = new ArrayList<>();

	            String sql = "SELECT maPhieuDatPhongDichVu FROM ChiTietPhieuDatPhongDichVu where mapdp=?";
	            
	            try (PreparedStatement preparedStatement = connect().prepareStatement(sql)) {
	            		preparedStatement.setInt(1, maPDP);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        String maPhieuDatPhongDichVu = resultSet.getString("maPhieuDatPhongDichVu");
	                        maPhieuDichVuList.add(maPhieuDatPhongDichVu);
	                    }
	                }
	            }
	   

	        return maPhieuDichVuList;
	    }
	 public static boolean addHoaDon(HoaDon hd) {
			ConnectSQL con = new ConnectSQL();
			PreparedStatement stmt = null;
			int n = 0;
			try {
				stmt = con.connect().prepareStatement("insert into HoaDon(maPDP, thoiGianKetThuc, tongTien,trangthai, maNV) values(?,?,?,?,?)");
				stmt.setInt(1, hd.getMaPDP());
				stmt.setString(2, hd.getThoiGianKetThuc());
				stmt.setDouble(3, hd.getTongTien());
				stmt.setBoolean(4, false);
				stmt.setInt(5, hd.getMaNV());
				n = stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			} 
			return n > 0;
		}
		public static boolean updateTrangThaiPDP(int maPDP) {
			int n = 0;
			ConnectSQL con = new ConnectSQL();
			PreparedStatement stmt = null;
			String sql = "UPDATE PhieuDatPhong set trangThai = 1 where maPDP = ?";
			try {
				stmt = con.connect().prepareStatement(sql);
				stmt.setInt(1, maPDP);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return n > 0;
		}
		public static boolean updateTrangThaiPhong(int maPDP) {
			int n = 0;
			ConnectSQL con = new ConnectSQL();
			PreparedStatement stmt = null;
			String sql = "Update phong set phong.tinhTrang = N'Phòng Trống'\r\n"
					+ "from phong inner join PhieuDatPhong on phong.maPhong = phieuDatphong.maPhong \r\n"
					+ "where maPDP =?";
			try {
				stmt = con.connect().prepareStatement(sql);
				stmt.setInt(1, maPDP);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return n > 0;
		}
}
