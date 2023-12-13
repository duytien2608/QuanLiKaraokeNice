package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.TaiKhoanDao;
import dao.ThanhToan_dao;
import dao.ThongKe_dao;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class PnlThongKe extends JFrame {

	private JPanel contentPane;
	private JTextField txtDoanhThuDV;
	private JTextField txtTongDoanhThu;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlThongKe frame = new PnlThongKe();
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
	public PnlThongKe() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlThongKe.class.getResource("/res/thongke.png")));
		setTitle("Thống kê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(1377, 0, -1373, 161);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 178, 170));
		panel_1.setBounds(0, 0, 1518, 203);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlThongKe.class.getResource("/res/kara.png")));
		lblNewLabel.setBounds(0, 0, 235, 203);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Karaoke Nice");
		lblNewLabel_1.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lblNewLabel_1.setBounds(517, 63, 487, 78);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PnlThongKe.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel_2.setBounds(1330, 11, 85, 72);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNguoiDung = new JLabel("Người dùng:");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));
		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiDung.setBounds(1231, 102, 351, 37);
		panel_1.add(lblNguoiDung);
		
		JButton btnNewButton = new JButton("Thoát");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setIcon(new ImageIcon(PnlThongKe.class.getResource("/res/account-logout-16.png")));
		btnNewButton.setBounds(1311, 136, 119, 43);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("THỐNG KÊ");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(10, 240, 318, 67);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Chức năng:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(100, 318, 137, 47);
		contentPane.add(lblNewLabel_4);
		
		JComboBox cbChucNang = new JComboBox();
		cbChucNang.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		cbChucNang.setModel(new DefaultComboBoxModel(new String[] {"Thống kê Doanh thu", "Thống kê Nhân viên", "Thống kê Khách hàng", "Thống kê Phòng hát"}));
		cbChucNang.setBounds(247, 318, 290, 42);
		contentPane.add(cbChucNang);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbChucNang.getSelectedItem().toString().equals("Thống kê Doanh thu")) {
					try {
						ThongKeDoanhThu();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(cbChucNang.getSelectedItem().toString().endsWith("Thống kê Nhân viên")){
					try {
						thongKeNhanVien();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(cbChucNang.getSelectedItem().toString().endsWith("Thống kê Khách hàng")) {
					try {
						thongKeKhachHang();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(cbChucNang.getSelectedItem().toString().equals("Thống kê Phòng hát")) {
					try {
						thongKeTheoPhong();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(PnlThongKe.class.getResource("/res/icons8-search-16.png")));
		btnNewButton_1.setBounds(561, 318, 49, 42);
		contentPane.add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 390, 1518, 14);
		contentPane.add(separator);
		
		JLabel lblNewLabel_4_1 = new JLabel("Doanh thu dịch vụ:");
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_4_1.setBounds(100, 478, 228, 47);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Tổng doanh thu:");
		lblNewLabel_4_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_4_2.setBounds(644, 485, 199, 47);
		contentPane.add(lblNewLabel_4_2);
		
		txtDoanhThuDV = new JTextField();
		txtDoanhThuDV.setEditable(false);
		txtDoanhThuDV.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtDoanhThuDV.setBounds(338, 483, 191, 37);
		contentPane.add(txtDoanhThuDV);
		txtDoanhThuDV.setColumns(10);
		
		txtTongDoanhThu = new JTextField();
		txtTongDoanhThu.setEditable(false);
		txtTongDoanhThu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtTongDoanhThu.setColumns(10);
		txtTongDoanhThu.setBounds(854, 486, 191, 37);
		contentPane.add(txtTongDoanhThu);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(PnlThongKe.class.getResource("/res/phan-mem-quan-ly-karaoke.png")));
		lblNewLabel_5.setBounds(344, 624, 835, 355);
		contentPane.add(lblNewLabel_5);
	}
	public void ThongKeDoanhThu() throws SQLException {
		String[] thongTin = ThongKe_dao.ThongKeDoanhThu();
		txtDoanhThuDV.setText(thongTin[0]);
		txtTongDoanhThu.setText(thongTin[1]);
	}
	public void thongKeNhanVien() throws SQLException {
		String tenNV = JOptionPane.showInputDialog("Nhập tên Nhân viên");
		String[] thongTin = ThongKe_dao.ThongKeTheoNhanVien(tenNV);
		txtDoanhThuDV.setText(thongTin[0]);
		txtTongDoanhThu.setText(thongTin[1]);
	}
	public void thongKeKhachHang() throws SQLException {
		String tenKH = JOptionPane.showInputDialog("Nhập tên Khách hàng");
		int tongTien = ThongKe_dao.ThongKeTheoKH(tenKH);
		String tongTienStr = String.valueOf(tongTien);
		txtTongDoanhThu.setText(tongTienStr);
	}
	public void thongKeTheoPhong() throws SQLException {
		String tenPhong = JOptionPane.showInputDialog("Nhập tên Phòng");
		int tongTien = ThongKe_dao.ThongKeTheoPhong(tenPhong);
		String tongTienStr = String.valueOf(tongTien);
		txtTongDoanhThu.setText(tongTienStr);
	}
}
