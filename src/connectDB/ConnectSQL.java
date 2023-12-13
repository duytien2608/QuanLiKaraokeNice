package connectDB;

import connectDB.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
	public static Connection con;
	private static ConnectSQL instance = new ConnectSQL();

	public static ConnectSQL getInstance() {
		return instance;
	}
	public static Connection connect()  throws SQLException{
		String url = "jdbc:sqlserver://localhost:1433;databasename=QLKaraokeNice;encrypt=false";
		String user = "sa";
		String password = "Sa@12345";
		return con = DriverManager.getConnection(url,user,password);
	}
	public void disconnect() {
		if(con!=null) 
			try {
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	public static Connection getConnection(){
		return con;
	}
//        public static void main(String[] args) {
//        try {
//			ConnectSQL.getInstance().connect();
//                        System.out.println("Kết nối với SQL sever thành công");
//
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//    }
}
