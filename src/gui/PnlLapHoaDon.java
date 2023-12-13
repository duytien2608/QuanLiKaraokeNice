package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.print.DocFlavor.STRING;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Panel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.xml.sax.HandlerBase;

import dao.NhanVien_dao;
import dao.TaiKhoanDao;
import dao.LapHoaDon_dao;
import entity.ChiTietPhieuDatPhongDichVu;
import entity.HoaDon;
import entity.NhanVien;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class PnlLapHoaDon extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable tbdv;
	private JTextField txtNhanVien;
	private JTextField txtThoiGianKetThuc;
	List<Object[]> dataList;
	private DefaultTableModel model;
	private DefaultTableModel model1;
	private JTextField txtTienDichVu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlLapHoaDon frame = new PnlLapHoaDon();
					frame.setTitle("Phần mềm quản lý karaoke Nice");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public PnlLapHoaDon() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlLapHoaDon.class.getResource("/res/icons8-pencil-16.png")));
		setTitle("Lập hóa đơn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
//		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_ThanhToan = new JLabel("LẬP HÓA ĐƠN:");
		lblNewLabel_ThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_ThanhToan.setBounds(34, 244, 182, 38);
		contentPane.add(lblNewLabel_ThanhToan);

		JLabel lblNewLabel_DanhSachPhongDangSuDung = new JLabel("DANH SÁCH PHÒNG ĐANG SỬ DỤNG:");
		lblNewLabel_DanhSachPhongDangSuDung.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_DanhSachPhongDangSuDung.setBounds(34, 310, 465, 38);
		contentPane.add(lblNewLabel_DanhSachPhongDangSuDung);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(115, 359, 1311, 200);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 phi\u1EBFu \u0111\u1EB7t ph\u00F2ng", "M\u00E3 ph\u00F2ng",
						"T\u00EAn ph\u00F2ng", "T\u00EAn kh\u00E1ch h\u00E0ng",
						"S\u1ED1 l\u01B0\u1EE3ng ng\u01B0\u1EDDi", "Gi\u1EDD b\u1EAFt \u0111\u1EA7u" }));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				int maPDP = (int) table.getValueAt(selectedRow, 0);
				try {
					tinhTongTienDichVu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setRowCount(0);
					getdichvuDangSuDung(maPDP);
					getDuLieu();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		getDuLieu();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(115, 641, 657, 261);
		contentPane.add(scrollPane_1);

		tbdv = new JTable();
		model1 = (DefaultTableModel) tbdv.getModel();
		tbdv.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "T\u00EAn d\u1ECBch v\u1EE5",
				"\u0110\u01A1n v\u1ECB t\u00EDnh", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1" }));
		scrollPane_1.setViewportView(tbdv);

		JLabel lblNewLabel_TinhTien = new JLabel("LẬP HÓA ĐƠN");
		lblNewLabel_TinhTien.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_TinhTien.setBounds(1102, 592, 257, 38);
		contentPane.add(lblNewLabel_TinhTien);

		JLabel lblNewLabel_NhanVienLapHoaDon = new JLabel("Nhân viên lập hóa đơn:");
		lblNewLabel_NhanVienLapHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_NhanVienLapHoaDon.setBounds(942, 651, 217, 24);
		contentPane.add(lblNewLabel_NhanVienLapHoaDon);

		JLabel lblNewLabel_TimKiemTheoTenPhong_2 = new JLabel("Giờ kết thúc:");
		lblNewLabel_TimKiemTheoTenPhong_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_TimKiemTheoTenPhong_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_TimKiemTheoTenPhong_2.setBounds(942, 708, 217, 24);
		contentPane.add(lblNewLabel_TimKiemTheoTenPhong_2);

		JButton btnNewButton_ThanhToan_1 = new JButton("Lập hóa đơn");
		btnNewButton_ThanhToan_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					ThemHoaDon();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_ThanhToan_1.setBackground(new Color(32, 178, 170));
		btnNewButton_ThanhToan_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_ThanhToan_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_ThanhToan_1.setBounds(1155, 857, 142, 45);
		contentPane.add(btnNewButton_ThanhToan_1);

		txtNhanVien = new JTextField();
		txtNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		String tenNV = TaiKhoanDao.getTenNV(tenDangNhap);
		int maNV = NhanVien_dao.timMaNVTheoTen(tenNV);
		String maNVStr = String.valueOf(maNV);
		txtNhanVien.setText(maNVStr);
		txtNhanVien.setEditable(false);
		txtNhanVien.setColumns(10);
		txtNhanVien.setBounds(1169, 648, 257, 29);
		contentPane.add(txtNhanVien);

		txtThoiGianKetThuc = new JTextField();
		txtThoiGianKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		updateTime();
		txtThoiGianKetThuc.setColumns(10);
		txtThoiGianKetThuc.setBounds(1169, 705, 257, 29);
		contentPane.add(txtThoiGianKetThuc);

		JLabel lblNewLabel_DanhSachPhongDangSuDung_1 = new JLabel("DANH SÁCH DỊCH VỤ ĐANG SỬ DỤNG:");
		lblNewLabel_DanhSachPhongDangSuDung_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_DanhSachPhongDangSuDung_1.setBounds(202, 813, 480, 38);
		contentPane.add(lblNewLabel_DanhSachPhongDangSuDung_1);

		JLabel lblNewLabel_TimKiemTheoTenPhong_2_1 = new JLabel("Tiền dịch vụ:");
		lblNewLabel_TimKiemTheoTenPhong_2_1.setForeground(Color.BLACK);
		lblNewLabel_TimKiemTheoTenPhong_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_TimKiemTheoTenPhong_2_1.setBounds(942, 771, 217, 24);
		contentPane.add(lblNewLabel_TimKiemTheoTenPhong_2_1);

		txtTienDichVu = new JTextField();
		txtTienDichVu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTienDichVu.setEditable(false);
		txtTienDichVu.setColumns(10);
		txtTienDichVu.setBounds(1169, 768, 257, 29);
		contentPane.add(txtTienDichVu);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 2012, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlLapHoaDon.class.getResource("/res/kara.png")));
		lblNewLabel.setBounds(0, 0, 257, 200);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Karaoke Nice");
		lblNewLabel_1.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lblNewLabel_1.setBounds(599, 33, 433, 156);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PnlLapHoaDon.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel_2.setBounds(1372, 23, 72, 73);
		panel.add(lblNewLabel_2);
		
		JLabel lblNguoiDung = new JLabel("Người dùng: ");
		String tenDN = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDN));
		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiDung.setBounds(1267, 99, 385, 32);
		panel.add(lblNguoiDung);
		
		JButton btnNewButton = new JButton("Thoát");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,
						"Bạn có muốn trở về màn hình chính ?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
					if (opt == JOptionPane.YES_OPTION) {
						try {
							String tenDangNhap = PnlDangNhap.tenDangNhap;
							String tenNV = TaiKhoanDao.getTenNV(tenDangNhap);
							
							if(TaiKhoanDao.KiemTraNhanVien(tenNV).equals("Nhân viên")) {
							new gui.ManHinhNhanVien_gui().setVisible(true);
							}else if(TaiKhoanDao.KiemTraNhanVien(tenNV).equals("Quản lý")) {
								new gui.ManHinhChinh_gui().setVisible(true);							
							}else if(TaiKhoanDao.KiemTraNhanVien(tenNV).equals("Kế toán")) {
								new gui.ManHinhKeToan_gui().setVisible(true);							
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setVisible(false);
					}
			}
		});
		btnNewButton.setIcon(new ImageIcon(PnlLapHoaDon.class.getResource("/res/account-logout-16.png")));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(1345, 142, 109, 32);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_DanhSachPhongDangSuDung_2 = new JLabel("DANH SÁCH DỊCH VỤ ĐANG SỬ DỤNG:");
		lblNewLabel_DanhSachPhongDangSuDung_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_DanhSachPhongDangSuDung_2.setBounds(34, 592, 501, 38);
		contentPane.add(lblNewLabel_DanhSachPhongDangSuDung_2);
	}

	public void getDuLieu() throws SQLException {
		model = (DefaultTableModel) table.getModel();

		List<Object[]> dataList = LapHoaDon_dao.getDataFromDatabase();
		for (Object[] rowData : dataList) {
			model.addRow(rowData);
		}
	}

	public void getdichvuDangSuDung(int maPDP) throws SQLException {
		model1 = (DefaultTableModel) tbdv.getModel();
		model1.setRowCount(0);
		model = (DefaultTableModel) tbdv.getModel();
		List<Object[]> dataList = LapHoaDon_dao.getDichVuDangSuDung(maPDP);
		for (Object[] rowData : dataList) {
			model.addRow(rowData);
		}

	}

	public void getMaNV(String TenNV) {
		TenNV = txtNhanVien.getText();
		NhanVien_dao.timMaNVTheoTen(TenNV);
	}

	private String updateTime() {
		// Lấy thời gian hiện tại
		Date currentTimeDate = new Date();

		// Định dạng thời gian
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedTime = dateFormat.format(currentTimeDate);

		// Hiển thị thời gian trên JTextField
		txtThoiGianKetThuc.setText(formattedTime);
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				autoInputData();
			}
		});
		timer.start();

		return formattedTime;
	}

	private void autoInputData() {
		txtThoiGianKetThuc.setText(updateTime());
	}

	public void tinhTongTienDichVu() throws SQLException {
		int selectedRow = table.getSelectedRow();
		int maPDP = (int) table.getValueAt(selectedRow, 0);
		double TongTien = LapHoaDon_dao.TongTienDichVuTheoMa(maPDP);
		txtTienDichVu.setText(String.valueOf(TongTien));
	}

	public void ThemHoaDon() throws SQLException {

		int selectedRow = table.getSelectedRow();
		int maPDP = (int) table.getValueAt(selectedRow, 0);
		double TongTien = LapHoaDon_dao.TongTienDichVuTheoMa(maPDP);
		txtTienDichVu.setText(String.valueOf(TongTien));
		String maNVStr = txtNhanVien.getText();
		int maNV = Integer.parseInt(maNVStr);
		String thoiGianKetThuc = txtThoiGianKetThuc.getText();
		HoaDon hd = new HoaDon(0, maPDP, thoiGianKetThuc, TongTien, false, maNV);
		LapHoaDon_dao.addHoaDon(hd);
		LapHoaDon_dao.updateTrangThaiPDP(maPDP);
		LapHoaDon_dao.updateTrangThaiPhong(maPDP);
		JOptionPane.showMessageDialog(this, "Tạo mới thành công");
		int opt = JOptionPane.showConfirmDialog(rootPane,
				"Bạn có muốn thanh toán ?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
			if (opt == JOptionPane.YES_OPTION) {
				new PnlTinhTien().setVisible(true);
				setVisible(false);
			}
		resetForm();

//		}
	}
	public void resetForm() {
		txtThoiGianKetThuc.setText("");
		txtTienDichVu.setText("");
	}
}
