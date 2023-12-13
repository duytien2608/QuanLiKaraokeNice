package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectSQL;
import dao.TaiKhoanDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class PnlDangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField textField_NguoiDung;
	private JPasswordField passwordField_MatKhau;
	private Connection connection;
	private String matKhau;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_2_1;
	private JButton btnDangNhap;
	private JButton btnThoat;
	public static String tenDangNhap;
	/**
	 * Launch the application.
	 */
	

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlDangNhap frame = new PnlDangNhap();
//					frame.setExtendedState(MAXIMIZED_BOTH);
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
	public PnlDangNhap() throws ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlDangNhap.class.getResource("/res/music-2-24.png")));
		setTitle("Đăng nhập");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(102, 153, 255));
		panel.setBounds(0, 0, 1898, 215);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Karaoke Nice");
		lblNewLabel_1.setBounds(503, 59, 760, 105);
		lblNewLabel_1.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		panel.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(23, -15, 263, 257);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(PnlDangNhap.class.getResource("/res/kara.png")));
		
		lblNewLabel_2 = new JLabel("Người dùng:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(537, 268, 184, 42);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_2_1 = new JLabel("Mật khẩu:");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_2_1.setBounds(537, 346, 184, 42);
		contentPane.add(lblNewLabel_2_1);
		
		textField_NguoiDung = new JTextField();
		textField_NguoiDung.setBounds(696, 268, 195, 42);
		textField_NguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		contentPane.add(textField_NguoiDung);
		textField_NguoiDung.setColumns(10);
		
		passwordField_MatKhau = new JPasswordField();
		passwordField_MatKhau.setBounds(696, 350, 195, 42);
		passwordField_MatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		contentPane.add(passwordField_MatKhau);
		
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setMnemonic(KeyEvent.VK_ENTER);
		tenDangNhap = textField_NguoiDung.getText();
        matKhau = passwordField_MatKhau.getText();
		btnDangNhap.addActionListener(new ActionListener() {
			


			@Override
			public void actionPerformed(ActionEvent e) {
				
				TaiKhoanDao tk = new TaiKhoanDao();
				
			        // Lấy tên người dùng và mật khẩu từ các hộp văn bản
			        tenDangNhap = textField_NguoiDung.getText();
			        matKhau = passwordField_MatKhau.getText();
			        tk.dangNhap(tenDangNhap, matKhau);
			     
			}
	});
		
		btnDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnDangNhap.setBounds(562, 441, 159, 42);
		contentPane.add(btnDangNhap);
		
		
		btnThoat = new JButton("Thoát");
		btnThoat.setMnemonic(KeyEvent.VK_ESCAPE);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,"Bạn có muốn thoát?","Xác nhận", JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				System.exit(0);
				}
			}
		});
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnThoat.setBounds(777, 441, 159, 42);
		contentPane.add(btnThoat);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(PnlDangNhap.class.getResource("/res/phan-mem-quan-ly-karaoke.png")));
		lblNewLabel_3.setBounds(365, 517, 923, 406);
		contentPane.add(lblNewLabel_3);
	
	    }
	}
