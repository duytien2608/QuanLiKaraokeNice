package connectDB;


import dao.ThanhToan_dao;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author myPC
 */
public class excelFile {
    
    public static void exportToExcel(int maHD,String filePath) throws IOException, SQLException {
		ConnectSQL con = new ConnectSQL();
		String sql = "select phieudatphong.tenKH, phieudatphong.sdt, tenNV,dichvu.tenDV,soluong,TenPhong,thoigianDat,thoigianketthuc,\r\n"
				+ "	datediff(hour,thoiGianDat,thoigianKetthuc) as tongThoiGian\r\n"
				+ "	from HoaDon\r\n"
				+ "	inner join PhieuDatPhong on PhieuDatPhong.maPdp = hoadon.mapdp\r\n"
				+ "	inner join phong on phong.maphong = phieudatphong.maphong\r\n"
				+ "	inner join chitietphieudatphongdichvu on chitietphieudatphongdichvu.mapdp = phieudatphong.mapdp\r\n"
				+ "	inner join dichvu on dichvu.madv = chitietphieudatphongdichvu.madv\r\n"
				+ "	inner join nhanvien on nhanvien.manv = hoadon.manv\r\n"
				+ "	where maHD = ?";
		PreparedStatement preparedStatement = con.connect().prepareStatement(sql);
				preparedStatement.setInt(1, maHD);
				ResultSet resultSet = preparedStatement.executeQuery();

			// Tạo Workbook và Sheet mới
			try (Workbook wb = new  HSSFWorkbook()){
				org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet("Thông tin thanh toán");

				// Tạo header
				Row headerRow = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(0);
				headerRow.createCell(0).setCellValue("TenKH");
				headerRow.createCell(1).setCellValue("sdt");
				headerRow.createCell(2).setCellValue("tenNV");
				headerRow.createCell(3).setCellValue("tenDV");
				headerRow.createCell(4).setCellValue("soLuong");
				headerRow.createCell(5).setCellValue("tenPhong");
				headerRow.createCell(6).setCellValue("thoiGianDat");
				headerRow.createCell(7).setCellValue("thoiGianKetThuc");
				headerRow.createCell(8).setCellValue("tongThoiGian");
				// Thêm dữ liệu
				int rowNum = 1;
				while (resultSet.next()) {
					Row dataRow = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(rowNum++);
					dataRow.createCell(0).setCellValue(resultSet.getInt("TenKH"));
					dataRow.createCell(1).setCellValue(resultSet.getDouble("sdt"));
					dataRow.createCell(2).setCellValue(resultSet.getString("tenNV"));
					dataRow.createCell(3).setCellValue(resultSet.getInt("tenDV"));
					dataRow.createCell(4).setCellValue(resultSet.getDouble("soLuong"));
					dataRow.createCell(5).setCellValue(resultSet.getString("tenPhong"));
					dataRow.createCell(6).setCellValue(resultSet.getInt("thoiGianDat"));
					dataRow.createCell(7).setCellValue(resultSet.getDouble("thoiGianKetThuc"));
					dataRow.createCell(8).setCellValue(resultSet.getString("tongThoiGian"));
				}

				// Ghi vào tệp Excel
				try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
					wb.write(outputStream);
				}

				System.out.println("Excel file created successfully.");
			}
		}



    
    public excelFile() {
        
    }
}
