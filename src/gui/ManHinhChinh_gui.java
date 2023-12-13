package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.TaiKhoanDao;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ManHinhChinh_gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbNguoiDung;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhChinh_gui frame = new ManHinhChinh_gui();
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
	public ManHinhChinh_gui() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManHinhChinh_gui.class.getResource("/res/music-2-24.png")));
		setTitle("Màn hình chính");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setBounds(-10, 0, 1920, 1080);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mn_NV = new JMenu("Nhân viên");
		mn_NV.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		menuBar.add(mn_NV);

		JMenuItem mntm_QuanLyNV = new JMenuItem("Quản lý nhân viên");
		mntm_QuanLyNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn chuyển trang ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				try {
					new gui.PnlQuanLyNhanVien().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				}
			}
		});
		mntm_QuanLyNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_NV.add(mntm_QuanLyNV);

		JMenuItem mntm_TaiKhoan = new JMenuItem("Tài khoản");
		mntm_TaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn chuyển trang ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				try {
					new gui.PnlQuanLyTaiKhoan().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				}
			}
		});
		mntm_TaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_NV.add(mntm_TaiKhoan);

		JMenu mn_KH = new JMenu("Khách hàng");
		mn_KH.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		menuBar.add(mn_KH);

		JMenuItem mntm_QuanLyKH = new JMenuItem("Quản lý khách hàng");
		mntm_QuanLyKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn chuyển trang ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				try {
					new gui.PnlQuanLyKhachHang().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				}
			}
		});
		mntm_QuanLyKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_KH.add(mntm_QuanLyKH);

		JMenu mn_DichVu = new JMenu("Dịch vụ");
		mn_DichVu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		menuBar.add(mn_DichVu);

		JMenuItem mntm_QuanLyDV = new JMenuItem("Quản lý dịch vụ");
		mntm_QuanLyDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn chuyển trang ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				try {
					new gui.PnlQuanLyDichVu().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				}
			}
		});
		mntm_QuanLyDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_DichVu.add(mntm_QuanLyDV);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Đặt dịch vụ");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn chuyển trang ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
					try {
						new PnlDatDichVu().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setVisible(false);
				}
			}
		});
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_DichVu.add(mntmNewMenuItem);

		JMenu mn_Phong = new JMenu("Phòng");
		mn_Phong.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		menuBar.add(mn_Phong);

		JMenuItem mntm_QuanLyP = new JMenuItem("Quản lý phòng");
		mntm_QuanLyP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn chuyển trang ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				try {
					new gui.PnlQuanLyPhong().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				}
			}
		});
		mntm_QuanLyP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_Phong.add(mntm_QuanLyP);

		JMenuItem mntm_DatPhong = new JMenuItem("Đặt phòng");
		mntm_DatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn chuyển trang ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				try {
					new gui.PnlDatPhong().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				}
			}
		});
		mntm_DatPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_Phong.add(mntm_DatPhong);

		JMenu mn_HoaDon = new JMenu("Hóa đơn");
		mn_HoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		menuBar.add(mn_HoaDon);

		JMenuItem mntm_LapHD = new JMenuItem("Lập hóa đơn");
		mntm_LapHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PnlLapHoaDon().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}setVisible(false);
			}
		});
		mntm_LapHD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_HoaDon.add(mntm_LapHD);

		JMenuItem mntm_ThanhToan = new JMenuItem("Thanh Toán");
		mntm_ThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn chuyển trang ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
					try {
						new PnlTinhTien().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setVisible(false);
				}
			}
		});
		mntm_ThanhToan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_HoaDon.add(mntm_ThanhToan);

		JMenu mn_ThongKe = new JMenu("Thống kê");
		mn_ThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		menuBar.add(mn_ThongKe);
		
		JMenuItem mntm_LapHD_1 = new JMenuItem("Thống kê");
		mntm_LapHD_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn chuyển trang ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				try {
					new gui.PnlThongKe().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				}
			}
		});
		mntm_LapHD_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_ThongKe.add(mntm_LapHD_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(-8, 0, 1920, 179);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Karaoke = new JLabel("Karaoke Nice");
		lbl_Karaoke.setBackground(new Color(32, 178, 1));
		lbl_Karaoke.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lbl_Karaoke.setBounds(712, 33, 484, 112);
		panel.add(lbl_Karaoke);

		JButton btn_DangXuat = new JButton("Đăng xuất");
		btn_DangXuat.setIcon(new ImageIcon(ManHinhChinh_gui.class.getResource("/res/account-logout-16.png")));
		btn_DangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn đăng xuất ?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				try {
					new PnlDangNhap().setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				}
			}
		});
		btn_DangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_DangXuat.setBounds(1539, 133, 123, 35);
		panel.add(btn_DangXuat);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_1.setIcon(new ImageIcon(ManHinhChinh_gui.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel_1.setBounds(1567, 6, 75, 73);
		panel.add(lblNewLabel_1);
		
		lbNguoiDung = new JLabel("Người dùng:");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lbNguoiDung.setText(lbNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));
		lbNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbNguoiDung.setBounds(1465, 92, 350, 30);
		panel.add(lbNguoiDung);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ManHinhChinh_gui.class.getResource("/res/kara.png")));
		lblNewLabel_2.setBounds(10, 0, 252, 179);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(159, 244, 1637, 663);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1637, 663);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(ManHinhChinh_gui.class.getResource("/res/background.jpg")));

	}
}