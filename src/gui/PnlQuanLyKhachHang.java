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

import dao.KhachHang_dao;
import dao.NhanVien_dao;
import dao.TaiKhoanDao;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class PnlQuanLyKhachHang extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTable tbKhachHang;
	private JTextField txtTimKiem;
	ArrayList<KhachHang> list = new ArrayList<KhachHang>();
	private KhachHang_dao dskh;
	private JButton btn_DangXuat;
	private JButton btn_Them;
	private JButton btn_Sua;
	private AbstractButton btn_XoaTrang;
	private JButton btn_TimKiem;
	private JTextField txtDiaChi;
	private int khachHangCounter = 0;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlQuanLyKhachHang frame = new PnlQuanLyKhachHang();
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
	public PnlQuanLyKhachHang() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlQuanLyKhachHang.class.getResource("/res/customer.png")));
		setTitle("Quản lý khách hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(-190, 0, 2369, 182);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Karaoke = new JLabel("Karaoke Nice");
		lbl_Karaoke.setBackground(new Color(32, 178, 1));
		lbl_Karaoke.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lbl_Karaoke.setBounds(811, 45, 407, 115);
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
		btn_DangXuat.setIcon(new ImageIcon(PnlQuanLyKhachHang.class.getResource("/res/account-logout-16.png")));
		btn_DangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_DangXuat.setBounds(1545, 125, 109, 35);
		panel.add(btn_DangXuat);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlQuanLyKhachHang.class.getResource("/res/kara.png")));
		lblNewLabel.setBounds(194, 0, 267, 182);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PnlQuanLyKhachHang.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel_1.setBounds(1565, 11, 85, 67);
		panel.add(lblNewLabel_1);

		JLabel lblNguoiDung = new JLabel("Người dùng: ");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));		
		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiDung.setBounds(1491, 90, 367, 35);
		panel.add(lblNguoiDung);

		JLabel lbl_ThongTinKH = new JLabel("Thông tin khách hàng");
		lbl_ThongTinKH.setBounds(20, 188, 310, 30);
		lbl_ThongTinKH.setFont(new Font("Times New Roman", Font.BOLD, 25));
		contentPane.add(lbl_ThongTinKH);

		JLabel lbl_MaKH = new JLabel("Mã Khách hàng:");
		lbl_MaKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_MaKH.setBounds(270, 229, 156, 30);
		contentPane.add(lbl_MaKH);

		JLabel lbl_TenKH = new JLabel("Tên Khách hàng:");
		lbl_TenKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_TenKH.setBounds(270, 284, 166, 30);
		contentPane.add(lbl_TenKH);

		JLabel lbl_DiaChi = new JLabel("Địa chỉ:");
		lbl_DiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_DiaChi.setBounds(270, 336, 115, 30);
		contentPane.add(lbl_DiaChi);

		JLabel lbl_SĐT = new JLabel("Số điện thoại:");
		lbl_SĐT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_SĐT.setBounds(865, 229, 115, 30);
		contentPane.add(lbl_SĐT);

		JLabel lbl_SoCMND = new JLabel("Email:");
		lbl_SoCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_SoCMND.setBounds(865, 284, 115, 30);
		contentPane.add(lbl_SoCMND);

		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaKH.setBounds(450, 229, 200, 30);
		contentPane.add(txtMaKH);
		txtMaKH.setColumns(10);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(450, 284, 200, 30);
		contentPane.add(txtTenKH);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSDT.setColumns(10);
		txtSDT.setBounds(1020, 229, 200, 30);
		contentPane.add(txtSDT);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(1020, 284, 200, 30);
		contentPane.add(txtEmail);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 391, 1540, 2);
		contentPane.add(separator);

		btn_Them = new JButton("Thêm");
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themKhachHang();
			}
		});
		btn_Them.setIcon(new ImageIcon(PnlQuanLyKhachHang.class.getResource("/res/icons8-add-20.png")));
		btn_Them.setBackground(new Color(240, 230, 140));
		btn_Them.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Them.setBounds(385, 401, 120, 41);
		contentPane.add(btn_Them);

		btn_Sua = new JButton("Sửa");
		btn_Sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					capNhapKhachHang();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Sua.setIcon(new ImageIcon(PnlQuanLyKhachHang.class.getResource("/res/icons8-pencil-16.png")));
		btn_Sua.setBackground(new Color(240, 230, 140));
		btn_Sua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Sua.setBounds(597, 401, 120, 41);
		contentPane.add(btn_Sua);

		btn_XoaTrang = new JButton("Xóa trắng");
		btn_XoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		btn_XoaTrang.setIcon(new ImageIcon(PnlQuanLyKhachHang.class.getResource("/res/x-mark-16.png")));
		btn_XoaTrang.setBackground(new Color(240, 230, 140));
		btn_XoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_XoaTrang.setBounds(802, 401, 132, 41);
		contentPane.add(btn_XoaTrang);

		btn_TimKiem = new JButton("Tìm kiếm");
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = txtTimKiem.getText();
				searchAndDisplay(tbKhachHang, searchText);
			}
		});
		btn_TimKiem.setIcon(new ImageIcon(PnlQuanLyKhachHang.class.getResource("/res/icons8-search-16.png")));
		btn_TimKiem.setBackground(new Color(240, 230, 140));
		btn_TimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_TimKiem.setBounds(1017, 401, 132, 41);
		contentPane.add(btn_TimKiem);

		JLabel lbl_DSNV = new JLabel("Danh sách khách hàng:");
		lbl_DSNV.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_DSNV.setBounds(20, 460, 333, 25);
		contentPane.add(lbl_DSNV);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 495, 1540, 314);
		contentPane.add(scrollPane);

		tbKhachHang = new JTable();
		tbKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HienThiForm();
			}
		});

		tbKhachHang.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 KH", "T\u00EAn KH",
				"S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Email", "\u0110\u1ECBa ch\u1EC9" }));
		model = (DefaultTableModel) tbKhachHang.getModel();
		updateTableData();
		tbKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setViewportView(tbKhachHang);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(450, 336, 340, 30);
		contentPane.add(txtDiaChi);

		JLabel lbl_SoCMND_1 = new JLabel("Tìm kiếm:");
		lbl_SoCMND_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_SoCMND_1.setBounds(865, 336, 115, 30);
		contentPane.add(lbl_SoCMND_1);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(1020, 336, 200, 30);
		contentPane.add(txtTimKiem);

	}

	protected void themKhachHang() {
		// TODO Auto-generated method stub
		String maKH = txtMaKH.getText();
		String tenKH = txtTenKH.getText();
		String SDT = txtSDT.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
		if (validData()) {
			KhachHang kh = new KhachHang(0, tenKH, SDT, email, diaChi);
			JOptionPane.showMessageDialog(this, "Tạo mới thành công");
			KhachHang_dao.addKhachHang(kh);
			lamMoiBang();
			resetForm();
			lamMoiBang();
			int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn đặt phòng hát không ?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (opt == JOptionPane.YES_OPTION) {
				try {
					new PnlDatPhong().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
			}
			Object[] rowData = new Object[] { 0, tenKH, SDT, email, diaChi };
			((DefaultTableModel) tbKhachHang.getModel()).addRow(rowData);

		}
	}

	private boolean validData() {
		String tenKH = txtTenKH.getText();
		String SDT = txtSDT.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
//		Họ tên không được để trống, có thể gồm nhiều từ ngăn cách bởi khoảng 
//		trắng. Không chứa ký số hoặc các ký tự đặc biệt khác, ngoại trừ ký tự 
		if (!(tenKH.length() > 0 && tenKH.matches("[\\p{L}' ]+"))) {
			JOptionPane.showMessageDialog(this, "Họ tên không được chứa số và kí tự đặc biệt");
			return false;
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

	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		updateTableData();
	}

	private void updateTableData() {
		KhachHang_dao dskh = new KhachHang_dao();
		List<KhachHang> list = dskh.doctuBang();
		for (KhachHang kh : list) {
			Object[] rowData = { kh.getMaKH(), kh.getTenKH(), kh.getSdt(), kh.getEmail(), kh.getDiaChi() };
			model.addRow(rowData);
		}
		tbKhachHang.setModel(model);
	}

	private void resetForm() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
		txtDiaChi.setText("");
		txtTimKiem.setText("");
	}

	public void HienThiForm() {
		// Lấy dữ liệu từ JTable
		int row = tbKhachHang.getSelectedRow();

		String maKH = tbKhachHang.getValueAt(row, 0).toString();
		String tenKH = tbKhachHang.getValueAt(row, 1).toString();
		String SDT = tbKhachHang.getValueAt(row, 2).toString();
		String email = tbKhachHang.getValueAt(row, 3).toString();
		String diaChi = tbKhachHang.getValueAt(row, 4).toString();

		// Hiển thị dữ liệu
		txtMaKH.setText(maKH);
		txtTenKH.setText(tenKH);
		txtSDT.setText(SDT);
		txtEmail.setText(email);
		txtDiaChi.setText(diaChi);
	}

	protected void capNhapKhachHang() throws SQLException {
		int row = tbKhachHang.getSelectedRow();
		if (validData()) {
			if (row != -1) {
				String maKHStr = txtMaKH.getText();
				String tenKH = txtTenKH.getText();
				String SDT = txtSDT.getText();
				String email = txtEmail.getText();
				String diaChi = txtDiaChi.getText();
				int maKH = Integer.parseInt(maKHStr);
				KhachHang kh = new KhachHang(maKH, tenKH, SDT, email, diaChi);

				if (KhachHang_dao.updateKhachHang(kh))
					;
				model.setValueAt(maKH, row, 0);
				model.setValueAt(tenKH, row, 1);
				model.setValueAt(SDT, row, 2);
				model.setValueAt(email, row, 3);
				model.setValueAt(diaChi, row, 4);

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