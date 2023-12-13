package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.excelFile;
import dao.NhanVien_dao;
import dao.TaiKhoanDao;
import dao.ThanhToan_dao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnlTinhTien extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<HoaDon> list = new ArrayList<HoaDon>();
	private JTextField txtTenKH;
	private JTextField txtSoGioHat;
	private JTextField txtSDT;
	private JTextField txtTongTienHat;
	private JTextField txtTongCong;
	private JTextField txtTienKhachDua;
	private JTextField txtTienTraLai;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlTinhTien frame = new PnlTinhTien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public PnlTinhTien() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlTinhTien.class.getResource("/res/pay.png")));
		setTitle("Thanh Toán");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 2077, 182);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlTinhTien.class.getResource("/res/kara.png")));
		lblNewLabel.setBounds(0, 0, 243, 182);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_1.setIcon(new ImageIcon(PnlTinhTien.class.getResource("/res/account-logout-16.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn trở về màn hình chính ?", "Xác nhận",
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
		btnNewButton_1.setBounds(1145, 126, 114, 33);
		panel.add(btnNewButton_1);
		
		JLabel lblNguoiDung = new JLabel("Người dùng:");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));
		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiDung.setBounds(1084, 85, 270, 30);
		panel.add(lblNguoiDung);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(PnlTinhTien.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel_5.setBounds(1163, 11, 78, 63);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Karaoke Nice");
		lblNewLabel_5_1.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lblNewLabel_5_1.setBounds(495, 52, 558, 107);
		panel.add(lblNewLabel_5_1);

		JLabel lblNewLabel_1 = new JLabel("THANH TOÁN");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(28, 193, 281, 64);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 311, 1077, 156);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					hienThiThongTin();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		;
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 phi\u1EBFu \u0111\u1EB7t ph\u00F2ng",
						"Th\u1EDDi gian k\u1EBFt th\u00FAc", "T\u1ED5ng ti\u1EC1n d\u1ECBch v\u1EE5",
						"Tr\u1EA1ng th\u00E1i", "M\u00E3 nh\u00E2n vi\u00EAn" }));
		list = new ThanhToan_dao().doctuBang();
		model = (DefaultTableModel) table.getModel();
		updateTableData();
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 476, 1368, 16);
		contentPane.add(separator);

		JLabel lblNewLabel_2 = new JLabel("THÔNG TIN THANH TOÁN");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setBounds(40, 516, 372, 51);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("DANH SÁCH HÓA ĐƠN");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_3.setBounds(38, 272, 281, 28);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Tên Khách Hàng:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(38, 578, 175, 37);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Số giờ hát:");
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(38, 660, 160, 37);
		contentPane.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel("Tổng tiền hát:");
		lblNewLabel_4_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_2.setBounds(482, 660, 175, 37);
		contentPane.add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_3 = new JLabel("Số điện thoại:");
		lblNewLabel_4_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_3.setBounds(482, 578, 175, 37);
		contentPane.add(lblNewLabel_4_3);

		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenKH.setBounds(205, 578, 229, 30);
		contentPane.add(txtTenKH);
		txtTenKH.setColumns(10);

		txtSoGioHat = new JTextField();
		txtSoGioHat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoGioHat.setEditable(false);
		txtSoGioHat.setColumns(10);
		txtSoGioHat.setBounds(205, 660, 229, 30);
		contentPane.add(txtSoGioHat);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSDT.setEditable(false);
		txtSDT.setColumns(10);
		txtSDT.setBounds(637, 578, 229, 30);
		contentPane.add(txtSDT);

		txtTongTienHat = new JTextField();
		txtTongTienHat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTongTienHat.setEditable(false);
		
		txtTongTienHat.setColumns(10);
		txtTongTienHat.setBounds(637, 660, 229, 30);
		contentPane.add(txtTongTienHat);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBounds(922, 503, 353, 355);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txtTongCong = new JTextField();
		txtTongCong.setBounds(164, 34, 146, 30);
		txtTongCong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTongCong.setEditable(false);
		txtTongCong.setColumns(10);
		panel_1.add(txtTongCong);

		JLabel lblNewLabel_4_2_1 = new JLabel("Tổng cộng:");
		lblNewLabel_4_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_2_1.setBounds(10, 31, 127, 37);
		panel_1.add(lblNewLabel_4_2_1);

		JLabel lblNewLabel_4_2_2 = new JLabel("Tiền khách đưa:");
		lblNewLabel_4_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_2_2.setBounds(10, 106, 127, 34);
		panel_1.add(lblNewLabel_4_2_2);

		JLabel lblNewLabel_4_2_3 = new JLabel("Trả lại:");
		lblNewLabel_4_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_2_3.setBounds(10, 185, 127, 37);
		panel_1.add(lblNewLabel_4_2_3);

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int TienKhach = Integer.parseInt(txtTienKhachDua.getText());
				int tongTien = Integer.parseInt(txtTongCong.getText());
				int tienTraLai = TienKhach - tongTien;
				if (TienKhach < tongTien) {

					JOptionPane.showMessageDialog(null, "Tiền khách đưa ít hơn tổng tiền hóa đơn");
				} else if (TienKhach > tongTien) {
					String tienThua = String.valueOf(tienTraLai);
					txtTienTraLai.setText(tienThua);
				}
			}
		});
		txtTienKhachDua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTienKhachDua.setColumns(10);
		txtTienKhachDua.setBounds(164, 108, 146, 30);
		panel_1.add(txtTienKhachDua);

		txtTienTraLai = new JTextField();
		txtTienTraLai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTienTraLai.setEditable(false);
		txtTienTraLai.setColumns(10);
		txtTienTraLai.setBounds(164, 188, 146, 30);
		panel_1.add(txtTienTraLai);

		JButton btnNewButton = new JButton("Thanh toán");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						ThanhToan();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(105, 272, 146, 49);
		panel_1.add(btnNewButton);
	}

	private void updateTableData() {
		ThanhToan_dao dshd = new ThanhToan_dao();
		List<HoaDon> list = dshd.doctuBang();
		for (HoaDon hd : list) {
			Object[] rowData = { hd.getMaHD(), hd.getMaPDP(), hd.getThoiGianKetThuc(), hd.getTongTien(),
					hd.isTrangThai(), hd.getMaNV() };
			model.addRow(rowData);
		}
		table.setModel(model);
	}

	public void hienThiThongTin() throws SQLException {
		int row = table.getSelectedRow();
		int maHD = (int) table.getValueAt(row, 0);
		String[] thongTin = ThanhToan_dao.getDataFromTable(maHD);
		txtTenKH.setText(thongTin[0]);
		txtSDT.setText(thongTin[1]);
		int soPhut = Integer.parseInt(thongTin[4]);
		int hours = soPhut / 60;
		int minutes = soPhut % 60;
		txtSoGioHat.setText(hours + " giờ " + minutes + " phút");
		int tienDV = Integer.parseInt(thongTin[3]);
		int tienPhong = Integer.parseInt(thongTin[2]);
		int tienHat = (tienPhong / 60) * soPhut;
		String tienHatStr = String.valueOf(tienHat);
		txtTongTienHat.setText(tienHatStr);
		int tongCong = tienHat + tienDV;
		String tongCongStr = String.valueOf(tongCong);
		txtTongCong.setText(tongCongStr);
	}

	public void ThanhToan() throws SQLException, IOException {
		int row = table.getSelectedRow();
		int maHD = (int) table.getValueAt(row, 0);
		String[] thongTin = ThanhToan_dao.getDataFromTable(maHD);
		int soPhut = Integer.parseInt(thongTin[4]);
		int hours = soPhut / 60;
		String tongTienHatStr = txtTongTienHat.getText();
		int tongTienHat = Integer.parseInt(tongTienHatStr);
		String tongCongStr = txtTongCong.getText();
		int tongCong = Integer.parseInt(tongCongStr);
		ChiTietHoaDon cthd = new ChiTietHoaDon(0, maHD, hours, tongTienHat, tongCong);
		ThanhToan_dao.addChiTietHoaDon(cthd);
		ThanhToan_dao.updateTrangThaiHoaDon(maHD);
		JOptionPane.showMessageDialog(this, "Thanh toán thành công");
		int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xuất hóa đơn ?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
		if (opt == JOptionPane.YES_OPTION) {
			excelFile.ExportExcel(maHD);
		}
		resetForm();
		lamMoiBang();
	}
	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		updateTableData();
	}
	public void resetForm() {
		txtSDT.setText("");
		txtSoGioHat.setText("");
		txtTenKH.setText("");
		txtTienKhachDua.setText("");
		txtTienTraLai.setText("");
		txtTongCong.setText("");
		txtTongTienHat.setText("");
		
	}
}
