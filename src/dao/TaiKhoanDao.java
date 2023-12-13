package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import org.apache.commons.collections4.SetUtils.SetView;

import connectDB.ConnectSQL;
import entity.NhanVien;
import entity.TaiKhoan;
import gui.PnlDangNhap;

public class TaiKhoanDao extends ConnectSQL {
	ArrayList<TaiKhoan> dstk;

	public TaiKhoanDao() {
		dstk = new ArrayList<TaiKhoan>();
	}

	public TaiKhoan dangNhap(String tenDangNhap, String matKhau) {
		TaiKhoan tk = null;
		try {
			String sql = "select * from TaiKhoan where tenDangNhap =? and matKhau =? and kichHoat = 1";
			PreparedStatement ps = connect().prepareStatement(sql);
			ps.setString(1, tenDangNhap);
			ps.setString(2, matKhau);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tk = new TaiKhoan();
				tk.setMaNV(rs.getInt(1));
				tk.setTenDangNhap(rs.getString(2));
				tk.setMatKhau(rs.getString(3));
				if(KiemTraTK(tenDangNhap).equals("Quản lý")) {
				new gui.ManHinhChinh_gui().setVisible(true);
				}else if(KiemTraTK(tenDangNhap).equals("Nhân viên")) {
					new gui.ManHinhNhanVien_gui().setVisible(true);
				}else if(KiemTraTK(tenDangNhap).equals("Kế toán")) {
					new gui.ManHinhChinh_gui().setVisible(true);
				}
			} else
				JOptionPane.showMessageDialog(null, "Tài khoản chưa được kích hoạt");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tk;
	}
	public String KiemTraTK(String tenDangNhap) throws SQLException {
		String chucVu = null;
		ConnectSQL con = new ConnectSQL();
		String sql = "select chucVu \r\n"
				+ "	from Nhanvien\r\n"
				+ "	inner join TaiKhoan on nhanvien.maNV = taikhoan.manv\r\n"
				+ "	where taikhoan.tenDangnhap= ?";
		PreparedStatement ps = connect().prepareStatement(sql);
		ps.setString(1, tenDangNhap);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			chucVu = rs.getString("chucVu");
		}
		return chucVu;
	}
	public static String KiemTraNhanVien(String tenNV) throws SQLException {
		String chucVu = null;
		ConnectSQL con = new ConnectSQL();
		String sql = "\r\n"
				+ "	select chucVu \r\n"
				+ "	from Nhanvien\r\n"
				+ "	inner join TaiKhoan on nhanvien.maNV = taikhoan.manv\r\n"
				+ "	where nhanVien.tenNV= ?";
		PreparedStatement ps = connect().prepareStatement(sql);
		ps.setString(1, tenNV);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			chucVu = rs.getString("chucVu");
		}
		return chucVu;
	}
	public ArrayList<TaiKhoan> doctuBang() {
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
			String sql = "Select * from TaiKhoan";
			Statement st = con.connect().createStatement();
			// Thực thi câu lệnh sql
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int maNV = rs.getInt(1);
				String tenDangNhap = rs.getString(2);
				String matKhau = rs.getString(3);
				boolean kichHoat = rs.getBoolean(4);

				TaiKhoan tk = new TaiKhoan(maNV, tenDangNhap, matKhau, kichHoat);
				dstk.add(tk);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dstk;
	}

	public static boolean addTaiKhoan(TaiKhoan tk) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into TaiKhoan(maNV,tenDangNhap,matKhau,kichHoat) values(?,?,?,?)");
			stmt.setInt(1, tk.getMaNV());
			stmt.setString(2, tk.getTenDangNhap());
			stmt.setString(3, tk.getMatKhau());
			stmt.setBoolean(4, tk.isKichHoat());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public static boolean updateTaiKhoan(TaiKhoan tk) throws SQLException {
		ConnectSQL con = new ConnectSQL();
		int n = 0;
		PreparedStatement stmt = null;
		try {
			String sql = "update TaiKhoan set maNV=?, matKhau =?, kichHoat =? where tenDangNhap =?";
			stmt = con.connect().prepareStatement(sql);
			stmt.setInt(1, tk.getMaNV());
			stmt.setString(2, tk.getMatKhau());
			stmt.setBoolean(3, tk.isKichHoat());
			stmt.setString(4, tk.getTenDangNhap());
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}
	public static boolean checkAccount(int maNV) throws SQLException {
		TaiKhoan tk = null;
		ConnectSQL con = new ConnectSQL();
		PreparedStatement stmt = null;
		String sql = "select * from TaiKhoan where maNV = ?";
		stmt.setInt(1, tk.getMaNV());
		// Thực thi câu lệnh sql
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			return false;
		}
		
		return true;
	}
	public class ComboBoxDAO {
		public static List<String> getServices() {
			List<String> maNV = new ArrayList<>();
			ConnectSQL con = new ConnectSQL();
			String sql = "SELECT maNV FROM NhanVien"; // Thay đổi thành tên bảng và trường thực tế của bạn

			try (PreparedStatement preparedStatement = con.connect().prepareStatement(sql);
					ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					String serviceName = resultSet.getString("maNV");
					maNV.add(serviceName);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return maNV;
		}
	}
	public static String getTenNV(String tenDangNhap) throws SQLException{
		String tenNV = null;
		ConnectSQL con = new ConnectSQL();
		String sql = "select TenNV\r\n"
				+ "from NhanVien \r\n"
				+ "inner join TaiKhoan on NhanVien.maNV = TaiKhoan.maNV where TaiKhoan.tenDangNhap = ?";
		PreparedStatement ps = con.connect().prepareStatement(sql);
		ps.setString(1,tenDangNhap);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			tenNV = rs.getString("tenNV");
		}
		return tenNV;
	}
	
}