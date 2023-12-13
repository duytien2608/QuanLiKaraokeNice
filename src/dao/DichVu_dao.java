package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectSQL;
import connectDB.ConnectSQL;
import entity.DichVu;
import entity.KhachHang;
import entity.NhanVien;

public class DichVu_dao extends ConnectSQL {
	List<DichVu> dsdv;

	public List<DichVu> docTuBang() {
		dsdv = new ArrayList<DichVu>();
		ConnectSQL.getInstance();
		ConnectSQL con = new ConnectSQL();
		try {
			con.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String sql = "Select * from DichVu";
			Statement st = connect().createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int maDV = rs.getInt(1);
				String tenDV = rs.getString(2);
				String loaiDV = rs.getString(3);
				String donViTinh = rs.getString(4);
				int giaNhap = rs.getInt(5);
				int giaBan = rs.getInt(6);
				DichVu dv = new DichVu(maDV, tenDV, loaiDV, donViTinh, giaNhap, giaBan);
				dsdv.add(dv);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dsdv;
	}

	public static boolean addDichVu(DichVu dv) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into DichVu(tenDV, loaiDV, donViTinh, giaNhap, giaBan) values(?,?,?,?,?)");
			stmt.setString(1, dv.getTenDV());
			stmt.setString(2, dv.getLoaiDichVu());
			stmt.setString(3, dv.getDonViTinh());
			stmt.setInt(4, dv.getGiaNhap());
			stmt.setInt(5, dv.getGiaBan());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public static boolean updateDichVu(DichVu dv) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "update DichVu set  tenDV=?,loaiDV=?, donViTinh=?, giaNhap=?, giaBan=? where maDV=?";
			stmt = con.prepareStatement(sql);
			// System.out.println(sql);
			stmt.setString(1, dv.getTenDV());
			stmt.setString(2, dv.getLoaiDichVu());
			stmt.setString(3, dv.getDonViTinh());
			stmt.setInt(4, dv.getGiaNhap());
			stmt.setInt(5, dv.getGiaBan());
			stmt.setInt(6, dv.getMaDV());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public static boolean deleteDichVu(int maDV) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from DichVu where maDV=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, maDV);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public static DichVu timMaDVTheoTenDV(String tenDV) {
        // Thực hiện truy vấn SQL để kiểm tra số điện thoại trong cơ sở dữ liệu
        // Trả về tên khách hàng nếu có, ngược lại trả về null
		DichVu dv = null;
        try (Connection connection = connect()) {
            String query = "SELECT * FROM DichVu WHERE tenDV = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, tenDV);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                	int maDV = resultSet.getInt("maDV");
               
            
                    dv = new DichVu(maDV, null, null, null, 0, 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dv;
    }
}
