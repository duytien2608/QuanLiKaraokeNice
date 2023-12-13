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

public class ManHinhKeToan_gui extends JFrame {

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
					ManHinhKeToan_gui frame = new ManHinhKeToan_gui();
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
	public ManHinhKeToan_gui() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManHinhKeToan_gui.class.getResource("/res/music-2-24.png")));
		setTitle("Màn hình chính");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setBounds(-10, 0, 1920, 1080);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mn_ThongKe = new JMenu("Thống kê");
		mn_ThongKe.addActionListener(new ActionListener() {
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
		mn_ThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		menuBar.add(mn_ThongKe);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Thống kê");
		mntmNewMenuItem.addActionListener(new ActionListener() {
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
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mn_ThongKe.add(mntmNewMenuItem);
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
		btn_DangXuat.setIcon(new ImageIcon(ManHinhKeToan_gui.class.getResource("/res/account-logout-16.png")));
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
		lblNewLabel_1.setIcon(new ImageIcon(ManHinhKeToan_gui.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel_1.setBounds(1567, 6, 75, 73);
		panel.add(lblNewLabel_1);
		
		lbNguoiDung = new JLabel("Người dùng:");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lbNguoiDung.setText(lbNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));
		lbNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbNguoiDung.setBounds(1465, 92, 350, 30);
		panel.add(lbNguoiDung);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ManHinhKeToan_gui.class.getResource("/res/kara.png")));
		lblNewLabel_2.setBounds(10, 0, 252, 179);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(159, 244, 1637, 663);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1637, 663);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(ManHinhKeToan_gui.class.getResource("/res/background.jpg")));

	}
}