package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import connectDB.ConnectSQL;
import dao.NhanVien_dao;
import dao.TaiKhoanDao;
import entity.NhanVien;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlQuanLyNhanVien extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaNV;
	private JTextField txtNgaySinh;
	private JTextField txtTenNV;
	private JTextField txtCCCD;
	private JTextField txtSDT;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbChucVu;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private AbstractButton btn_Them;
	private JButton btn_Sua;
	private JButton btn_XoaTrang;
	private JButton btn_TimKiem;
	private JTable tbNhanVien;
	ArrayList<NhanVien> list = new ArrayList<NhanVien>();
	private JTextField txtEmail;
	private static int nhanVienCounter = 1;
	private DefaultTableModel model;
	private NhanVien_dao dsnv;
	private JTextField txtTimKiem;
	private JComboBox comboBoxGioiTinh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlQuanLyNhanVien frame = new PnlQuanLyNhanVien();
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
	public PnlQuanLyNhanVien() throws SQLException {
		NhanVien_dao nv = new NhanVien_dao();
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlQuanLyNhanVien.class.getResource("/res/employees.png")));
		setTitle("Quản lý nhân viên");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(-190, 0, 2366, 182);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Karaoke = new JLabel("Karaoke Nice");
		lbl_Karaoke.setBackground(new Color(32, 178, 1));
		lbl_Karaoke.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lbl_Karaoke.setBounds(834, 61, 446, 87);
		panel.add(lbl_Karaoke);

		JButton btn_DangXuat = new JButton("Thoát");
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
		btn_DangXuat.setIcon(new ImageIcon(PnlQuanLyNhanVien.class.getResource("/res/account-logout-16.png")));
		btn_DangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_DangXuat.setBounds(1554, 126, 121, 35);
		panel.add(btn_DangXuat);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlQuanLyNhanVien.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel.setBounds(1579, 0, 71, 72);
		panel.add(lblNewLabel);

		JLabel lblNguoiDung = new JLabel("Người dùng: ");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiDung.setEnabled(true);
		lblNguoiDung.setBounds(1510, 77, 372, 38);
		panel.add(lblNguoiDung);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PnlQuanLyNhanVien.class.getResource("/res/kara.png")));
		lblNewLabel_1.setBounds(194, 0, 250, 182);
		panel.add(lblNewLabel_1);

		JLabel lbl_ThongTinNV = new JLabel("Thông tin nhân viên");
		lbl_ThongTinNV.setBounds(20, 188, 260, 41);
		lbl_ThongTinNV.setFont(new Font("Times New Roman", Font.BOLD, 25));
		contentPane.add(lbl_ThongTinNV);

		JLabel lbl_MaNV = new JLabel("Mã NV:");
		lbl_MaNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_MaNV.setBounds(98, 240, 115, 30);
		contentPane.add(lbl_MaNV);

		JLabel lbl_NgaySinh = new JLabel("Ngày sinh:");
		lbl_NgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_NgaySinh.setBounds(98, 295, 115, 30);
		contentPane.add(lbl_NgaySinh);

		JLabel lbl_ChucVu = new JLabel("Chức vụ:");
		lbl_ChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_ChucVu.setBounds(98, 350, 115, 30);
		contentPane.add(lbl_ChucVu);

		JLabel lbl_TenNV = new JLabel("Tên NV:");
		lbl_TenNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_TenNV.setBounds(612, 240, 115, 30);
		contentPane.add(lbl_TenNV);

		JLabel lbl_SoCMND = new JLabel("Số CCCD:");
		lbl_SoCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_SoCMND.setBounds(612, 295, 115, 30);
		contentPane.add(lbl_SoCMND);

		JLabel lbl_GioiTinh = new JLabel("Giới tính:");
		lbl_GioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_GioiTinh.setBounds(1082, 240, 115, 30);
		contentPane.add(lbl_GioiTinh);

		JLabel lbl_SĐT = new JLabel("Số điện thoại:");
		lbl_SĐT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_SĐT.setBounds(1082, 295, 115, 30);
		contentPane.add(lbl_SĐT);

		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaNV.setBounds(217, 240, 200, 30);
		contentPane.add(txtMaNV);
		txtMaNV.setColumns(10);

		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(217, 295, 200, 30);
		contentPane.add(txtNgaySinh);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(732, 240, 200, 30);
		contentPane.add(txtTenNV);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(732, 295, 200, 30);
		contentPane.add(txtCCCD);

		cbChucVu = new JComboBox();
		cbChucVu.setModel(new DefaultComboBoxModel(new String[] { "Quản lý", "Kế toán", "Nhân viên" }));
		cbChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbChucVu.setBounds(217, 350, 200, 30);
		contentPane.add(cbChucVu);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSDT.setColumns(10);
		txtSDT.setBounds(1222, 295, 200, 30);
		contentPane.add(txtSDT);

		rdNam = new JRadioButton("Nam");
		buttonGroup.add(rdNam);
		rdNam.setSelected(true);
		rdNam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdNam.setBounds(1215, 240, 100, 30);
		contentPane.add(rdNam);

		rdNu = new JRadioButton("Nữ");
		buttonGroup.add(rdNu);
		rdNu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdNu.setBounds(1358, 240, 100, 30);
		contentPane.add(rdNu);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 399, 1540, 2);
		contentPane.add(separator);

		btn_Them = new JButton("Thêm");
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thêm nhân viên", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
					themNhanVien();
				}
			}
		});
		btn_Them.setIcon(new ImageIcon(PnlQuanLyNhanVien.class.getResource("/res/icons8-add-20.png")));
		btn_Them.setBackground(new Color(240, 230, 140));
		btn_Them.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Them.setBounds(200, 409, 120, 41);
		contentPane.add(btn_Them);

		btn_Sua = new JButton("Sửa");
		btn_Sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn cập nhập ?", "Xác nhận",
							JOptionPane.YES_NO_OPTION);
					if (opt == JOptionPane.YES_OPTION) {
						capNhapNhanVien();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Sua.setIcon(new ImageIcon(PnlQuanLyNhanVien.class.getResource("/res/icons8-pencil-16.png")));
		btn_Sua.setBackground(new Color(240, 230, 140));
		btn_Sua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Sua.setBounds(450, 409, 120, 41);
		contentPane.add(btn_Sua);

		btn_XoaTrang = new JButton("Xóa trắng");
		btn_XoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		btn_XoaTrang.setIcon(new ImageIcon(PnlQuanLyNhanVien.class.getResource("/res/x-mark-16.png")));
		btn_XoaTrang.setBackground(new Color(240, 230, 140));
		btn_XoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_XoaTrang.setBounds(711, 409, 144, 41);
		contentPane.add(btn_XoaTrang);

		btn_TimKiem = new JButton("Tìm kiếm");
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = txtTimKiem.getText().toLowerCase();
				searchAndDisplay(tbNhanVien, searchText);
			}
		});
		btn_TimKiem.setIcon(new ImageIcon(PnlQuanLyNhanVien.class.getResource("/res/icons8-search-16.png")));
		btn_TimKiem.setBackground(new Color(240, 230, 140));
		btn_TimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_TimKiem.setBounds(1220, 409, 137, 41);
		contentPane.add(btn_TimKiem);

		JLabel lbl_DSNV = new JLabel("Danh sách nhân viên:");
		lbl_DSNV.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_DSNV.setBounds(20, 478, 250, 30);
		contentPane.add(lbl_DSNV);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 526, 1540, 314);
		contentPane.add(scrollPane);

		tbNhanVien = new JTable();
		tbNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HienThiForm();
			}
		});
		tbNhanVien.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 NV", "T\u00EAn NV", "Gi\u1EDBi t\u00EDnh", "Ng\u00E0y sinh", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Email", "CCCD", "Ch\u1EE9c v\u1EE5"
			}
		));
		tbNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		list = new NhanVien_dao().doctuBang();
		model = (DefaultTableModel) tbNhanVien.getModel();
		updateTableData();
		scrollPane.setViewportView(tbNhanVien);

		JLabel lbl_SoCMND_1 = new JLabel("Email:");
		lbl_SoCMND_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_SoCMND_1.setBounds(612, 350, 115, 30);
		contentPane.add(lbl_SoCMND_1);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(732, 350, 200, 30);
		contentPane.add(txtEmail);

		JLabel lbl_SĐT_1 = new JLabel("Tìm kiếm:");
		lbl_SĐT_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_SĐT_1.setBounds(1082, 350, 115, 30);
		contentPane.add(lbl_SĐT_1);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(1222, 350, 200, 30);
		contentPane.add(txtTimKiem);

		JButton btn_LamMoi = new JButton("Làm mới");
		btn_LamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiBang();
			}
		});
		btn_LamMoi.setIcon(new ImageIcon(PnlQuanLyNhanVien.class.getResource("/res/icons8-refresh-16.png")));
		btn_LamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_LamMoi.setBackground(new Color(240, 230, 140));
		btn_LamMoi.setBounds(974, 409, 144, 41);
		contentPane.add(btn_LamMoi);

	}

	public void resetForm() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtNgaySinh.setText("");
		txtSDT.setText("");
		txtCCCD.setText("");
		txtEmail.setText("");
		txtMaNV.requestFocus();
	}

	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		updateTableData();
	}

	private void updateTableData() {
		NhanVien_dao dsnv = new NhanVien_dao();
		List<NhanVien> list = dsnv.doctuBang();
		for (NhanVien nv : list) {
			Object[] rowData = { nv.getMaNV(), nv.getTenNV(), nv.isGioiTinh(), nv.getNgaySinh(), nv.getSDT(),
					nv.getEmail(), nv.getCCCD(), nv.getChucVu() };
			model.addRow(rowData);
		}
		tbNhanVien.setModel(model);
	}

	private boolean validData() {
		String maNV = txtMaNV.getText();
		String tenNV = txtTenNV.getText();
		boolean gioiTinh = false; // Mặc định là nữ
		if (rdNam.isSelected()) {
			gioiTinh = true;
		}
		String ngaySinh = txtNgaySinh.getText();
		String SDT = txtSDT.getText();
		String email = txtEmail.getText();
		String CCCD = txtCCCD.getText();
		String chucVu = cbChucVu.getSelectedItem().toString();
//		Họ tên không được để trống, có thể gồm nhiều từ ngăn cách bởi khoảng 
//		trắng. Không chứa ký số hoặc các ký tự đặc biệt khác, ngoại trừ ký tự 
		if (!(tenNV.length() > 0 && tenNV.matches("[\\p{L}' ]+"))) {
			JOptionPane.showMessageDialog(this, "Họ tên không được chứa số và kí tự đặc biệt");
			return false;
		}
		if (ngaySinh.length() > 0) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate birthDate = LocalDate.parse(ngaySinh, formatter);
				LocalDate now = LocalDate.now();

				// Ensure the person is at least 15 years old (considering 14 years and 364 days
				// as the minimum age)
				if (birthDate.plusYears(18).isAfter(now)) {
					JOptionPane.showMessageDialog(this, "Ngày sinh phải là người trên 18 tuổi");
					return false;
				}
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(this,
						"Định dạng ngày sinh không đúng. Vui lòng sử dụng định dạng yyyy-MM-dd");
				return false;
			}
		}
		if (!(SDT.length() >= 10 && SDT.length() <= 11 && SDT.matches("\\d+"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10-11 chữ số và không chứa ký tự đặc biệt");
			return false;
		}
		if (!(email.matches(".+@gmail\\.com"))) {
			JOptionPane.showMessageDialog(this, "Email phải có ít nhất một ký tự trước @ và kết thúc bằng @gmail.com");
			return false;
		}
		return true;
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



	protected void themNhanVien() {
		// TODO Auto-generated method stub
		String maNV = txtMaNV.getText();
		String tenNV = txtTenNV.getText();
		boolean gioiTinh = false; // Mặc định là nữ
		if (rdNam.isSelected()) {
			gioiTinh = true;
		}
		String ngaySinh = txtNgaySinh.getText();
		String SDT = txtSDT.getText();
		String email = txtEmail.getText();
		String CCCD = txtCCCD.getText();
		String chucVu = cbChucVu.getSelectedItem().toString();
		if (validData()) {
			NhanVien nv = new NhanVien(0, tenNV, gioiTinh, ngaySinh, SDT, email, CCCD, chucVu);
			NhanVien_dao.addNhanVien(nv);
			lamMoiBang();
			JOptionPane.showMessageDialog(this, "Tạo mới thành công");
			resetForm();
			lamMoiBang();
			Object[] rowData = new Object[] { 0, tenNV, gioiTinh, ngaySinh, SDT, email, CCCD, chucVu };

			((DefaultTableModel) tbNhanVien.getModel()).addRow(rowData);

		}

	}

	public void HienThiForm() {
		// Lấy dữ liệu từ JTable
		int row = tbNhanVien.getSelectedRow();

		String maNV = tbNhanVien.getValueAt(row, 0).toString();
		String tenNV = tbNhanVien.getValueAt(row, 1).toString();
		boolean gioiTinh = tbNhanVien.getValueAt(row, 2).toString() != null;
		String ngaySinh = tbNhanVien.getValueAt(row, 3).toString();
		String SDT = tbNhanVien.getValueAt(row, 4).toString();
		String email = tbNhanVien.getValueAt(row, 5).toString();
		String CCCD = tbNhanVien.getValueAt(row, 6).toString();
		String chucVu = tbNhanVien.getValueAt(row, 7).toString();

		// Hiển thị dữ liệu
		txtMaNV.setText(maNV);
		txtTenNV.setText(tenNV);
		boolean gt = false;
		if (rdNam.isSelected()) {
			gt = true;
		}
		txtNgaySinh.setText(ngaySinh);
		txtSDT.setText(SDT);
		txtEmail.setText(email);
		txtCCCD.setText(CCCD);
		cbChucVu.setSelectedItem(chucVu);
	}

	protected void capNhapNhanVien() throws SQLException {
		int row = tbNhanVien.getSelectedRow();
		if (validData()) {
			if (row != -1) {
				String maNV = txtMaNV.getText();
				String tenNV = txtTenNV.getText();
				boolean gioiTinh = false;
				if (rdNam.isSelected()) {
					gioiTinh = true;

					String ngaySinh = txtNgaySinh.getText();
					String SDT = txtSDT.getText();
					String email = txtEmail.getText();
					String CCCD = txtCCCD.getText();
					String chucVu = cbChucVu.getSelectedItem().toString();

					NhanVien nv = new NhanVien(0, tenNV, gioiTinh, ngaySinh, SDT, email, CCCD, chucVu);

					if (NhanVien_dao.updateNhanVien(nv))
						;
					model.setValueAt(maNV, row, 0);
					model.setValueAt(tenNV, row, 1);
					model.setValueAt(gioiTinh, row, 2);
					model.setValueAt(ngaySinh, row, 3);
					model.setValueAt(SDT, row, 4);
					model.setValueAt(email, row, 5);
					model.setValueAt(CCCD, row, 6);
					model.setValueAt(chucVu, row, 7);
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
					resetForm();

				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
				}

			} else {
				JOptionPane.showMessageDialog(this,
						"Vui lòng chọn một hàng trong bảng để cập nhật thông tin nhân viên.");
			}
		}
	}
}