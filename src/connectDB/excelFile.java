package connectDB;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelFile extends ConnectSQL {
	public static void ExportExcel(int maHD) throws SQLException, FileNotFoundException, IOException {
		ConnectSQL con = new ConnectSQL();
		String sql = "select phieudatphong.tenKH, phieudatphong.sdt, tenNV,dichvu.tenDV,soluong,TenPhong,thoigianDat,thoigianketthuc,\r\n"
				+ "	datediff(hour,thoiGianDat,hoadon.thoigianKetthuc) as tongThoiGian\r\n" + "	from HoaDon\r\n"
				+ "	inner join PhieuDatPhong on PhieuDatPhong.maPdp = hoadon.mapdp\r\n"
				+ "	inner join phong on phong.maphong = phieudatphong.maphong\r\n"
				+ "	inner join chitietphieudatphongdichvu on chitietphieudatphongdichvu.mapdp = phieudatphong.mapdp\r\n"
				+ "	inner join dichvu on dichvu.madv = chitietphieudatphongdichvu.madv\r\n"
				+ "	inner join nhanvien on nhanvien.manv = hoadon.manv\r\n" + "	where maHD = ?";
		PreparedStatement ps = con.connect().prepareStatement(sql);
		ps.setInt(1, maHD);
		ResultSet rs = ps.executeQuery();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");
		Row headerRow = sheet.createRow(0);
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			Cell cell = headerRow.createCell(i - 1);
			cell.setCellValue(rs.getMetaData().getColumnName(i));
		}
		int rowNum = 1;
		while (rs.next()) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(rs.getString(i));
			}
		}

		// Lưu workbook vào một file Excel
		try (FileOutputStream outputStream = new FileOutputStream("hoaDon_" + maHD + ".xlsx")) {
			workbook.write(outputStream);
		}

		// Đóng tất cả tài nguyên
		rs.close();
		ps.close();
		connect().close();
		workbook.close();
		System.out.println("Xuất dữ liệu thành công.");

	}
}
