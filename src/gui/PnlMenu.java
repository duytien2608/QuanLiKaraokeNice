package gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JSeparator;

public class PnlMenu extends JFrame {

	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlMenu frame = new PnlMenu();
					frame.setExtendedState(MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PnlMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlMenu.class.getResource("/res/music-2-24.png")));
		setTitle("Phần mềm quản lí Karaoke Nice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(0, 0, 1952, 1136);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PHẦN MỀM QUẢN LÝ KARAOKE NICE");
		lblNewLabel.setBounds(553, 67, 672, 66);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JButton btnNhanVien = new JButton("Nhân viên ");
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNhanVien.setForeground(new Color(0, 0, 0));
		btnNhanVien.setBackground(new Color(32, 178, 170));
		btnNhanVien.setBounds(206, 284, 215, 124);
		panel.add(btnNhanVien);
		btnNhanVien.setIcon(new ImageIcon(PnlMenu.class.getResource("/res/employees.png")));
		btnNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnKhachHang = new JButton("Khách hàng");
		btnKhachHang.setBackground(new Color(32, 178, 170));
		btnKhachHang.setForeground(new Color(0, 0, 0));
		btnKhachHang.setIcon(new ImageIcon(PnlMenu.class.getResource("/res/customer.png")));
		btnKhachHang.setBounds(505, 284, 215, 124);
		panel.add(btnKhachHang);
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnDichVu = new JButton("Dịch vụ");
		btnDichVu.setForeground(new Color(0, 0, 0));
		btnDichVu.setBackground(new Color(32, 178, 170));
		btnDichVu.setIcon(new ImageIcon(PnlMenu.class.getResource("/res/order.png")));
		btnDichVu.setBounds(1095, 284, 215, 124);
		panel.add(btnDichVu);
		btnDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDichVu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnThongKe = new JButton("Thống kê");
		btnThongKe.setBackground(new Color(32, 178, 170));
		btnThongKe.setForeground(new Color(0, 0, 0));
		btnThongKe.setIcon(new ImageIcon(PnlMenu.class.getResource("/res/thongke.png")));
		btnThongKe.setBounds(1385, 284, 215, 124);
		panel.add(btnThongKe);
		btnThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnPhongHat = new JButton("Phòng hát");
		btnPhongHat.setForeground(new Color(0, 0, 0));
		btnPhongHat.setBackground(new Color(32, 178, 170));
		btnPhongHat.setIcon(new ImageIcon(PnlMenu.class.getResource("/res/door.png")));
		btnPhongHat.setBounds(803, 284, 215, 124);
		panel.add(btnPhongHat);
		btnPhongHat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(542, 117, 595, 16);
		panel.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PnlMenu.class.getResource("/res/background.jpg")));
		lblNewLabel_1.setBounds(112, 519, 1617, 522);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PnlMenu.class.getResource("/res/kara.png")));
		lblNewLabel_2.setBounds(257, 51, 248, 197);
		panel.add(lblNewLabel_2);
		
		JLabel lblTaiKhoan = new JLabel("Tài khoản đăng nhập:");
		lblTaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTaiKhoan.setBounds(166, 14, 172, 45);
		panel.add(lblTaiKhoan);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setMnemonic('X');
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn đăng xuất?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if(opt == JOptionPane.YES_OPTION) {
					try {
						new gui.PnlDangNhap().setVisible(true);
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
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThoat.setIcon(new ImageIcon(PnlMenu.class.getResource("/res/account-logout-16.png")));
		btnThoat.setBounds(10, 11, 146, 51);
		panel.add(btnThoat);
	}
}
