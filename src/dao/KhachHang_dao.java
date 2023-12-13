package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectSQL;
import entity.KhachHang;

public class KhachHang_dao extends ConnectSQL {
	ArrayList<KhachHang> dskh;

	public KhachHang_dao() {
		dskh = new ArrayList<KhachHang>();
	}

	public ArrayList<KhachHang> doctuBang() {
		ConnectSQL.getInstance();
		Connection con = ConnectSQL.getConnection();
		try {

			String sql = "Select * from KhachHang";
			Statement statement = connect().createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int maKH = rs.getInt(1);
				String hoTen = rs.getString(2);
				String ngaySinh = rs.getString(3);
				String sdt = rs.getString(4);
				String email = rs.getString(5);
				KhachHang kh = new KhachHang(maKH,hoTen,ngaySinh,sdt,email);
				dskh.add(kh);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dskh;
	}

	public static boolean addKhachHang(KhachHang kh) {
		Connection con = ConnectSQL.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into KhachHang(tenKH,sdt,email,diaChi) values(?,?,?,?)");
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getSdt());
			stmt.setString(3, kh.getEmail());
			stmt.setString(4, kh.getDiaChi());
			n = stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}

	public static boolean updateKhachHang(KhachHang kh) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql =  "update KhachHang" + " set tenKH = ?" + ", sdt=?" + ", email=?" + ", diaChi=?" + " where maKH = ?";
			stmt = con.prepareStatement(sql);
			//System.out.println(sql);
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getSdt());
			stmt.setString(3, kh.getEmail());
			stmt.setString(4, kh.getDiaChi());
			stmt.setInt(5, kh.getMaKH());
			n = stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}

	private void close(PreparedStatement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	public static KhachHang timTenKHTheoSDT(String sdtKH) {
        // Thực hiện truy vấn SQL để kiểm tra số điện thoại trong cơ sở dữ liệu
        // Trả về tên khách hàng nếu có, ngược lại trả về null
		KhachHang khachHang = null;
        try (Connection connection = connect()) {
            String query = "SELECT * FROM KhachHang WHERE sdt = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, sdtKH);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                	int maKH = resultSet.getInt("maKH");
                    String tenKH = resultSet.getString("tenKH");
                    String sdt = resultSet.getString("sdt");
                    String email = resultSet.getString("email");
                    String diaChi = resultSet.getString("diaChi");
                    khachHang = new KhachHang(maKH, tenKH, sdt, email, diaChi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

}