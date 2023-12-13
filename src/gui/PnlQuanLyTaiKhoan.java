package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.NhanVien_dao;
import dao.TaiKhoanDao;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class PnlQuanLyTaiKhoan extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenDangNhap;
	private JTable tbTaiKhoan;
	private JTextField txtMatKhau;
	private JButton btn_DangXuat;
	private JButton btn_Them;
	private JButton btn_Sua;
	private JButton btn_XoaTrang;
	private JButton btn_TimKiem;
	private DefaultTableModel model;
	ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
	private JRadioButton rdKichHoat;
	private int edit;
	private JTextField txtTimKiem;
	private JComboBox cbTenNV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlQuanLyTaiKhoan frame = new PnlQuanLyTaiKhoan();
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
	public PnlQuanLyTaiKhoan() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlQuanLyTaiKhoan.class.getResource("/res/account.png")));
		setTitle("Quản lý tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(-190, 0, 2538, 194);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Karaoke = new JLabel("Karaoke Nice");
		lbl_Karaoke.setBackground(new Color(32, 178, 1));
		lbl_Karaoke.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lbl_Karaoke.setBounds(776, 54, 468, 111);
		panel.add(lbl_Karaoke);

		btn_DangXuat = new JButton("Thoát");
		btn_DangXuat.addActionListener(new ActionListener() {
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
		btn_DangXuat.setIcon(new ImageIcon(PnlQuanLyTaiKhoan.class.getResource("/res/account-logout-16.png")));
		btn_DangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_DangXuat.setBounds(1526, 148, 120, 35);
		panel.add(btn_DangXuat);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlQuanLyTaiKhoan.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel.setBounds(1541, 28, 84, 68);
		panel.add(lblNewLabel);

		JLabel lblNguoiDung = new JLabel("Người dùng: ");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));		
		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiDung.setBounds(1469, 107, 344, 41);
		panel.add(lblNguoiDung);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PnlQuanLyTaiKhoan.class.getResource("/res/kara.png")));
		lblNewLabel_1.setBounds(194, 0, 254, 194);
		panel.add(lblNewLabel_1);

		JLabel lbl_ThongTinTK = new JLabel("Thông tin tài khoản");
		lbl_ThongTinTK.setBounds(24, 200, 274, 47);
		lbl_ThongTinTK.setFont(new Font("Times New Roman", Font.BOLD, 25));
		contentPane.add(lbl_ThongTinTK);

		JLabel lbl_MaTK = new JLabel("Mã nhân viên:");
		lbl_MaTK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_MaTK.setBounds(259, 258, 140, 30);
		contentPane.add(lbl_MaTK);

		JLabel lbl_TenDangNhap = new JLabel("Tên đăng nhập:");
		lbl_TenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_TenDangNhap.setBounds(259, 313, 140, 30);
		contentPane.add(lbl_TenDangNhap);

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenDangNhap.setColumns(10);
		txtTenDangNhap.setBounds(409, 313, 200, 30);
		contentPane.add(txtTenDangNhap);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 428, 1603, 2);
		contentPane.add(separator);

		btn_Them = new JButton("Thêm");
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themTaiKhoan();
			}
		});
		btn_Them.setIcon(new ImageIcon(PnlQuanLyTaiKhoan.class.getResource("/res/icons8-add-20.png")));
		btn_Them.setBackground(new Color(240, 230, 140));
		btn_Them.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Them.setBounds(200, 438, 120, 40);
		contentPane.add(btn_Them);

		btn_Sua = new JButton("Sửa");
		btn_Sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					capNhapTaiKhoan();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Sua.setIcon(new ImageIcon(PnlQuanLyTaiKhoan.class.getResource("/res/icons8-pencil-16.png")));
		btn_Sua.setBackground(new Color(240, 230, 140));
		btn_Sua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Sua.setBounds(450, 438, 120, 40);
		contentPane.add(btn_Sua);

		btn_XoaTrang = new JButton("Xóa trắng");
		btn_XoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		btn_XoaTrang.setIcon(new ImageIcon(PnlQuanLyTaiKhoan.class.getResource("/res/x-mark-16.png")));
		btn_XoaTrang.setBackground(new Color(240, 230, 140));
		btn_XoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_XoaTrang.setBounds(709, 438, 133, 40);
		contentPane.add(btn_XoaTrang);

		btn_TimKiem = new JButton("Làm mới");
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiBang();
			}
		});
		btn_TimKiem.setIcon(new ImageIcon(PnlQuanLyTaiKhoan.class.getResource("/res/icons8-refresh-16.png")));
		btn_TimKiem.setBackground(new Color(240, 230, 140));
		btn_TimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_TimKiem.setBounds(975, 438, 133, 40);
		contentPane.add(btn_TimKiem);

		JLabel lbl_DSTK = new JLabel("Danh sách tài khoản");
		lbl_DSTK.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_DSTK.setBounds(24, 501, 291, 47);
		contentPane.add(lbl_DSTK);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 559, 1540, 314);
		contentPane.add(scrollPane);

		tbTaiKhoan = new JTable();
		tbTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HienThiForm();
			}
		});
		tbTaiKhoan.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 nh\u00E2n vi\u00EAn",
				"T\u00EAn \u0111\u0103ng nh\u1EADp", "M\u1EADt kh\u1EA9u", "K\u00EDch ho\u1EA1t" }));
		tbTaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		list = new TaiKhoanDao().doctuBang();
		model = (DefaultTableModel) tbTaiKhoan.getModel();
		updateTableData();
		scrollPane.setViewportView(tbTaiKhoan);

		JLabel lbl_MatKhau = new JLabel("Mật khẩu:");
		lbl_MatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_MatKhau.setBounds(259, 368, 140, 30);
		contentPane.add(lbl_MatKhau);

		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(409, 368, 200, 30);
		contentPane.add(txtMatKhau);

		JLabel lbl_MaTK_1 = new JLabel("Kích hoạt:");
		lbl_MaTK_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_MaTK_1.setBounds(702, 260, 103, 30);
		contentPane.add(lbl_MaTK_1);

		rdKichHoat = new JRadioButton("");
		rdKichHoat.setSelected(true);
		rdKichHoat.setBounds(811, 265, 34, 23);
		contentPane.add(rdKichHoat);

		JButton btn_TimKiem_1 = new JButton("Tìm kiếm");
		btn_TimKiem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = txtTimKiem.getText();
				searchAndDisplay(tbTaiKhoan, searchText);
			}
		});
		btn_TimKiem_1.setIcon(new ImageIcon(PnlQuanLyTaiKhoan.class.getResource("/res/icons8-search-16.png")));
		btn_TimKiem_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_TimKiem_1.setBackground(new Color(240, 230, 140));
		btn_TimKiem_1.setBounds(1233, 438, 133, 40);
		contentPane.add(btn_TimKiem_1);

		JLabel lbl_MatKhau_1 = new JLabel("Tìm kiếm:");
		lbl_MatKhau_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_MatKhau_1.setBounds(700, 367, 140, 30);
		contentPane.add(lbl_MatKhau_1);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(865, 368, 200, 30);
		contentPane.add(txtTimKiem);

		cbTenNV = new JComboBox();
		timMaNV();
		cbTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbTenNV.setBounds(409, 260, 200, 30);
		contentPane.add(cbTenNV);

	}

	public void resetForm() {
		txtTenDangNhap.setText("");
		txtMatKhau.setText("");
		txtTenDangNhap.requestFocus();
		txtTimKiem.setText("");
	}

	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		updateTableData();
	}

	private void updateTableData() {
		TaiKhoanDao dstk = new TaiKhoanDao();
		List<TaiKhoan> list = dstk.doctuBang();
		for (TaiKhoan tk : list) {
			Object[] rowData = { tk.getMaNV(), tk.getTenDangNhap(), tk.getMatKhau(), tk.isKichHoat() };
			model.addRow(rowData);
		}
		tbTaiKhoan.setModel(model);
	}

	protected void themTaiKhoan() {
		// TODO Auto-generated method stub
		String maNVstr = cbTenNV.getSelectedItem().toString();
		String tenDangNhap = txtTenDangNhap.getText();
		String matKhau = txtMatKhau.getText();
		boolean kichHoat = false; // Mặc định là ko
		if (rdKichHoat.isSelected()) {
			kichHoat = true;
		}

		int maNV = Integer.parseInt(maNVstr);
		if (validData()) {
			TaiKhoan tk = new TaiKhoan(maNV, tenDangNhap, matKhau, kichHoat);
			TaiKhoanDao.addTaiKhoan(tk);
			lamMoiBang();
			JOptionPane.showMessageDialog(this, "Tạo mới thành công");
//			resetForm();
			lamMoiBang();
			Object[] rowData = new Object[] { maNV, tenDangNhap, matKhau, kichHoat };

			((DefaultTableModel) tbTaiKhoan.getModel()).addRow(rowData);

		}
	}


	private boolean validData() {
		String tenDangNhap = txtTenDangNhap.getText();
		String matKhau = txtMatKhau.getText();
		boolean kichHoat = false; // Mặc định là 0
		if (rdKichHoat.isSelected()) {
			kichHoat = true;
		}
		if ((tenDangNhap.length() <= 0)) {
			JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống");
			if (timTenDangNhap(tenDangNhap))
				return false;
		}
		if (!(matKhau.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
			return false;
		}

		return true;
	}

	public void HienThiForm() {
		// Lấy dữ liệu từ JTable
		int row = tbTaiKhoan.getSelectedRow();

		String maNV = tbTaiKhoan.getValueAt(row, 0).toString();
		String tenDangNhap = tbTaiKhoan.getValueAt(row, 1).toString();
		String matKhau = tbTaiKhoan.getValueAt(row, 2).toString();
		// Hiển thị dữ liệu
		cbTenNV.removeAllItems();
		cbTenNV.addItem(maNV);
		txtTenDangNhap.setText(tenDangNhap);
		txtMatKhau.setText(matKhau);
		boolean gioiTinh;
		if (gioiTinh = true) {
			rdKichHoat.isSelected();
		}
	}

	private boolean timTenDangNhap(String tenDangNhap) {
		// TODO Auto-generated method stub
		tenDangNhap = tenDangNhap.toUpperCase();
		TaiKhoanDao ds = new TaiKhoanDao();
		List<TaiKhoan> list = ds.doctuBang();
		for (TaiKhoan tk : list) {
			if (tk.getTenDangNhap().contains(tenDangNhap))
				return false;
		}
		return true;
	}

	private void timMaNV() {
		List<String> services = TaiKhoanDao.ComboBoxDAO.getServices();

		// Xóa dữ liệu cũ trong JComboBox và thêm dữ liệu mới
		cbTenNV.removeAllItems();
		for (String service : services) {
			cbTenNV.addItem(service);
		}
	}

	protected void capNhapTaiKhoan() throws SQLException {
		int row = tbTaiKhoan.getSelectedRow();
		if (validData()) {
			if (row != -1) {
				String maNVString = cbTenNV.getSelectedItem().toString();
				int maNV = Integer.parseInt(maNVString);
				String tenDangNhap = txtTenDangNhap.getText();
				String matKhau = txtMatKhau.getText();
				boolean kichHoat = false; // Mặc định 0
				if (rdKichHoat.isSelected()) {
					kichHoat = true;
				}
				TaiKhoan tk = new TaiKhoan(maNV, tenDangNhap, matKhau, kichHoat);

				if (TaiKhoanDao.updateTaiKhoan(tk))
					;
				model.setValueAt(maNV, row, 0);
				model.setValueAt(tenDangNhap, row, 1);
				model.setValueAt(matKhau, row, 2);
				model.setValueAt(kichHoat, row, 3);
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				resetForm();

			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
			}

		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng trong bảng để cập nhật thông tin nhân viên.");
		}
	}

	private static void searchAndDisplay(JTable table, String searchText) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		List<RowFilter<Object, Object>> filters = new ArrayList<>();
		for (int i = 0; i < model.getColumnCount(); i++) {
			filters.add(RowFilter.regexFilter("(?i)" + searchText, i));
		}

		sorter.setRowFilter(RowFilter.orFilter(filters));

	}
}