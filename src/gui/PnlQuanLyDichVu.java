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

import dao.DichVu_dao;
import dao.NhanVien_dao;
import dao.TaiKhoanDao;
import entity.DichVu;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
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

public class PnlQuanLyDichVu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaDV;
	private JTextField txtTenDV;
	private JTextField txtGiaNhap;
	private JTable tbDSDichVu;
	private JTextField txtGiaBan;
	private JComboBox cbDVT;
	private JTextField txtTimKiem;
	private JComboBox cbLoaiDV;
	private int dichVuCounter = 0;
	ArrayList<DichVu> list = new ArrayList<DichVu>();
	private DichVu_dao dsdv;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlQuanLyDichVu frame = new PnlQuanLyDichVu();
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
	public PnlQuanLyDichVu() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlQuanLyDichVu.class.getResource("/res/product.png")));
		setTitle("Quản lý dịch vụ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(-180, 0, 1920, 186);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Karaoke = new JLabel("Karaoke Nice");
		lbl_Karaoke.setBackground(new Color(32, 178, 1));
		lbl_Karaoke.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lbl_Karaoke.setBounds(817, 35, 448, 103);
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
		btn_DangXuat.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/account-logout-16.png")));
		btn_DangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_DangXuat.setBounds(1554, 130, 108, 35);
		panel.add(btn_DangXuat);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel.setBounds(1581, 11, 83, 66);
		panel.add(lblNewLabel);
		
		JLabel lblNguoiDung = new JLabel("Người dùng:");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));		
		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiDung.setBounds(1482, 89, 355, 30);
		panel.add(lblNguoiDung);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/kara.png")));
		lblNewLabel_1.setBounds(182, 0, 235, 186);
		panel.add(lblNewLabel_1);

		JLabel lbl_ThongTinDV = new JLabel("Thông tin dịch vụ");
		lbl_ThongTinDV.setBounds(10, 192, 190, 25);
		lbl_ThongTinDV.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(lbl_ThongTinDV);

		JLabel lbl_MaDV = new JLabel("Mã DV:");
		lbl_MaDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_MaDV.setBounds(270, 210, 115, 30);
		contentPane.add(lbl_MaDV);

		JLabel lbl_DonViTinh = new JLabel("Đơn vị tính:");
		lbl_DonViTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_DonViTinh.setBounds(270, 265, 115, 30);
		contentPane.add(lbl_DonViTinh);

		JLabel lbl_LoaiDV = new JLabel("Loại DV:");
		lbl_LoaiDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_LoaiDV.setBounds(270, 320, 115, 30);
		contentPane.add(lbl_LoaiDV);

		JLabel lbl_TenDV = new JLabel("Tên DV:");
		lbl_TenDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_TenDV.setBounds(865, 210, 115, 30);
		contentPane.add(lbl_TenDV);

		JLabel lbl_GiaNhap = new JLabel("Giá nhập:");
		lbl_GiaNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_GiaNhap.setBounds(865, 265, 115, 30);
		contentPane.add(lbl_GiaNhap);

		txtMaDV = new JTextField();
		txtMaDV.setEditable(false);
		txtMaDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaDV.setBounds(450, 210, 200, 30);
		contentPane.add(txtMaDV);
		txtMaDV.setColumns(10);

		txtTenDV = new JTextField();
		txtTenDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenDV.setColumns(10);
		txtTenDV.setBounds(1020, 210, 200, 30);
		contentPane.add(txtTenDV);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(1020, 265, 200, 30);
		contentPane.add(txtGiaNhap);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 361, 1730, 1);
		contentPane.add(separator);

		JButton btn_Them = new JButton("Thêm");
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themDichVu();
			}
		});
		btn_Them.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/icons8-add-20.png")));
		btn_Them.setBackground(new Color(240, 230, 140));
		btn_Them.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Them.setBounds(200, 370, 120, 30);
		contentPane.add(btn_Them);

		JButton btn_Sua = new JButton("Sửa");
		btn_Sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn cập nhập ?", "Xác nhận",
							JOptionPane.YES_NO_OPTION);
					if (opt == JOptionPane.YES_OPTION) {
					capNhapDichVu();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Sua.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/icons8-pencil-16.png")));
		btn_Sua.setBackground(new Color(240, 230, 140));
		btn_Sua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Sua.setBounds(450, 370, 120, 30);
		contentPane.add(btn_Sua);

		JButton btn_Xoa = new JButton("Xóa");
		btn_Xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa ?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				xoaDichVu();
				}
			}
		});
		btn_Xoa.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/icons8-remove-24.png")));
		btn_Xoa.setBackground(new Color(240, 230, 140));
		btn_Xoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Xoa.setBounds(700, 370, 120, 30);
		contentPane.add(btn_Xoa);

		JButton btn_XoaTrang = new JButton("Xóa trắng");
		btn_XoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		btn_XoaTrang.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/x-mark-16.png")));
		btn_XoaTrang.setBackground(new Color(240, 230, 140));
		btn_XoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_XoaTrang.setBounds(950, 370, 132, 30);
		contentPane.add(btn_XoaTrang);

		JButton btn_TimKiem = new JButton("Tìm kiếm");
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = txtTimKiem.getText();
				searchAndDisplay(tbDSDichVu, searchText);
			}
		});
		btn_TimKiem.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/icons8-search-16.png")));
		btn_TimKiem.setBackground(new Color(240, 230, 140));
		btn_TimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_TimKiem.setBounds(1458, 370, 132, 30);
		contentPane.add(btn_TimKiem);

		JLabel lbl_DSDV = new JLabel("Danh sách dịch vụ:");
		lbl_DSDV.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_DSDV.setBounds(10, 410, 200, 25);
		contentPane.add(lbl_DSDV);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 446, 1540, 314);
		contentPane.add(scrollPane);

		tbDSDichVu = new JTable();
		tbDSDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HienThiForm();
			}
		});
		DichVu_dao dv = new DichVu_dao();
		tbDSDichVu.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 d\u1ECBch v\u1EE5", "T\u00EAn d\u1ECBch v\u1EE5", "Lo\u1EA1i d\u1ECBch v\u1EE5",
						"\u0110\u01A1n v\u1ECB t\u00EDnh", "Gi\u00E1 nh\u1EADp", "Gi\u00E1 b\u00E1n" }));
		tbDSDichVu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		list = (ArrayList<DichVu>) dv.docTuBang();
		model = (DefaultTableModel) tbDSDichVu.getModel();
		updateTableData();
		scrollPane.setViewportView(tbDSDichVu);

		cbLoaiDV = new JComboBox();
		cbLoaiDV.setModel(new DefaultComboBoxModel(new String[] { "Đồ uống", "Thức ăn" }));
		cbLoaiDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbLoaiDV.setBounds(450, 320, 200, 30);
		contentPane.add(cbLoaiDV);

		JLabel lbl_GiaDV_1 = new JLabel("Giá bán:");
		lbl_GiaDV_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_GiaDV_1.setBounds(865, 319, 115, 30);
		contentPane.add(lbl_GiaDV_1);

		txtGiaBan = new JTextField();
		txtGiaBan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGiaBan.setColumns(10);
		txtGiaBan.setBounds(1020, 320, 200, 30);
		contentPane.add(txtGiaBan);

		cbDVT = new JComboBox();
		cbDVT.setModel(new DefaultComboBoxModel(new String[] { "CÁI", "LON", "THÙNG", "PHẦN" }));
		cbDVT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbDVT.setBounds(450, 265, 200, 30);
		contentPane.add(cbDVT);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newValue = JOptionPane.showInputDialog("Thêm đơn vị tính", null);
				if (newValue != null && !newValue.isEmpty()) {
					// Thêm giá trị mới vào JComboBox và làm cho nó được chọn
					cbDVT.addItem(newValue);
					cbDVT.setSelectedItem(newValue);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/icons8-add-20.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(667, 264, 33, 31);
		contentPane.add(btnNewButton);

		JLabel lbl_GiaDV_1_1 = new JLabel("Tìm kiếm:");
		lbl_GiaDV_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_GiaDV_1_1.setBounds(1308, 320, 115, 30);
		contentPane.add(lbl_GiaDV_1_1);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(1430, 320, 200, 30);
		contentPane.add(txtTimKiem);
		
		JButton btn_XoaTrang_1 = new JButton("Làm mới");
		btn_XoaTrang_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiBang();
			}
		});
		btn_XoaTrang_1.setIcon(new ImageIcon(PnlQuanLyDichVu.class.getResource("/res/icons8-refresh-16.png")));
		btn_XoaTrang_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_XoaTrang_1.setBackground(new Color(240, 230, 140));
		btn_XoaTrang_1.setBounds(1203, 370, 132, 30);
		contentPane.add(btn_XoaTrang_1);

	}

	protected void themDichVu() {
		// TODO Auto-generated method stub
		String tenDV = txtTenDV.getText();
		String loaiDV = cbLoaiDV.getSelectedItem().toString();
		String donViTinh = cbDVT.getSelectedItem().toString();
		String giaNhapStr = txtGiaNhap.getText();
		String giaBanStr = txtGiaBan.getText();
		int giaBan = Integer.parseInt(giaBanStr);
		int giaNhap = Integer.parseInt(giaNhapStr);

		if (validData()) {
			DichVu dv = new DichVu(0, tenDV, loaiDV, donViTinh, giaNhap, giaBan);
			DichVu_dao.addDichVu(dv);
			lamMoiBang();
			JOptionPane.showMessageDialog(this, "Tạo mới thành công");
			lamMoiBang();
			resetForm();
			Object[] rowData = new Object[] { 0, tenDV, loaiDV, donViTinh, giaNhap, giaBan };

			((DefaultTableModel) tbDSDichVu.getModel()).addRow(rowData);
		}

	}

	private boolean validData() {
		String tenDV = txtTenDV.getText();
		String loaiDV = cbLoaiDV.getSelectedItem().toString();
		String donViTinh = cbDVT.getSelectedItem().toString();
		String giaNhapStr = txtGiaNhap.getText();
		String giaBanStr = txtGiaBan.getText();
		int giaBan = Integer.parseInt(giaBanStr);
		int giaNhap = Integer.parseInt(giaNhapStr);
//		Họ tên không được để trống, có thể gồm nhiều từ ngăn cách bởi khoảng 
//		trắng. Không chứa ký số hoặc các ký tự đặc biệt khác, ngoại trừ ký tự 
		if (!(tenDV.length() > 0 )) {
			JOptionPane.showMessageDialog(this, "Tên dịch vụ không được để trống");
			return false;
		}
		if (!(giaBan > 0)) {
			JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0");
			return false;
		}
		if (!(giaNhap > 0)) {
			JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0");
			return false;
		}
		if (!(giaBan > giaNhap)) {
			JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn giá nhập");
			return false;
		}
		return true;
	}

	public void HienThiForm() {
		// Lấy dữ liệu từ JTable
		int row = tbDSDichVu.getSelectedRow();

		String maDV = tbDSDichVu.getValueAt(row, 0).toString();
		String tenDV = tbDSDichVu.getValueAt(row, 1).toString();
		String loaiDV = tbDSDichVu.getValueAt(row, 2).toString();
		String donViTinh = tbDSDichVu.getValueAt(row, 3).toString();
		Object giaNhap = tbDSDichVu.getValueAt(row, 4).toString();
		Object giaBan = tbDSDichVu.getValueAt(row, 5).toString();
		String giaNhapStr = String.valueOf(giaNhap);
		String giaBanStr = String.valueOf(giaBan);

		// Hiển thị dữ liệu
		txtMaDV.setText(maDV);
		txtTenDV.setText(tenDV);
		cbLoaiDV.setSelectedItem(loaiDV);
		cbDVT.setSelectedItem(donViTinh);
		txtGiaNhap.setText(giaNhapStr);
		txtGiaBan.setText(giaBanStr);
	}

	protected void capNhapDichVu() throws SQLException {
		int row = tbDSDichVu.getSelectedRow();
		if (validData()) {
			if (row != -1) {
				String maDVStr = txtMaDV.getText();
				String tenDV = txtTenDV.getText();
				String loaiDV = cbLoaiDV.getSelectedItem().toString();
				String donViTinh = cbDVT.getSelectedItem().toString();
				String giaNhapStr = txtGiaNhap.getText();
				String giaBanStr = txtGiaBan.getText();
				int giaBan = Integer.parseInt(giaBanStr);
				int giaNhap = Integer.parseInt(giaNhapStr);
				int maDV = Integer.parseInt(maDVStr);
				DichVu dv = new DichVu(maDV, tenDV, loaiDV, donViTinh, giaNhap, giaBan);

				if (DichVu_dao.updateDichVu(dv)) {

					model.setValueAt(maDV, row, 0);
					model.setValueAt(tenDV, row, 1);
					model.setValueAt(loaiDV, row, 2);
					model.setValueAt(donViTinh, row, 3);
					model.setValueAt(giaNhap, row, 4);
					model.setValueAt(giaBan, row, 5);
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

	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		updateTableData();
	}
	private void resetForm() {
		txtMaDV.setText("");
		txtTenDV.setText("");
		txtGiaNhap.setText("");
		txtGiaBan.setText("");
		txtTimKiem.setText("");
	}
	private void updateTableData() {
		DichVu_dao dsdv = new DichVu_dao();
		List<DichVu> list = dsdv.docTuBang();
		for (DichVu dv : list) {
			Object[] rowData = { dv.getMaDV(), dv.getTenDV(), dv.getLoaiDichVu(), dv.getDonViTinh(),dv.getGiaNhap(),dv.getGiaBan() };
			model.addRow(rowData);
		}
		tbDSDichVu.setModel(model);
	}
	private void xoaDichVu() {
		int row = tbDSDichVu.getSelectedRow();
		if(row != -1) {
			String maDVStr = txtMaDV.getText();
			int maDV = Integer.parseInt(maDVStr);
			DichVu_dao.deleteDichVu(maDV);
			lamMoiBang();
			JOptionPane.showMessageDialog(null, "Xóa thành công");
			lamMoiBang();
			resetForm();
			
		}else
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng để xóa");
	}
}