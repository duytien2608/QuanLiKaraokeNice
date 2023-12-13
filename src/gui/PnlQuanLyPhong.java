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
import dao.KhachHang_dao;
import dao.Phong_dao;
import dao.TaiKhoanDao;
import entity.KhachHang;
import entity.Phong;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlQuanLyPhong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaPhong;
	private JTextField txtTenPhong;
	private JTable tbDSPhong;
	private JTextField txtGiaPhong;
	private JTextField txtTimKiem;
	private JComboBox cbLoaiPhong;
	private int phongCounter = 5 ;
	ArrayList<Phong> list = new ArrayList<Phong>();
	private Phong_dao dsp;
	private DefaultTableModel model;
	private JButton btnNewButton;
	private JComboBox cbTinhTrang;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlQuanLyPhong frame = new PnlQuanLyPhong();
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
	public PnlQuanLyPhong() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlQuanLyPhong.class.getResource("/res/door.png")));
		setTitle("Quản lý phòng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(-181, 0, 2352, 191);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Karaoke = new JLabel("Karaoke Nice");
		lbl_Karaoke.setBackground(new Color(32, 178, 1));
		lbl_Karaoke.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lbl_Karaoke.setBounds(758, 56, 462, 103);
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
		btn_DangXuat.setIcon(new ImageIcon(PnlQuanLyPhong.class.getResource("/res/account-logout-16.png")));
		btn_DangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_DangXuat.setBounds(1550, 145, 120, 35);
		panel.add(btn_DangXuat);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlQuanLyPhong.class.getResource("/res/kara.png")));
		lblNewLabel.setBounds(183, 0, 301, 191);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PnlQuanLyPhong.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel_1.setBounds(1566, 30, 74, 69);
		panel.add(lblNewLabel_1);
		
		JLabel lblNguoiDung = new JLabel("Người dùng:");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiDung.setBounds(1480, 108, 279, 35);
		panel.add(lblNguoiDung);

		JLabel lbl_ThongTinP = new JLabel("Thông tin phòng");
		lbl_ThongTinP.setBounds(10, 197, 250, 36);
		lbl_ThongTinP.setFont(new Font("Times New Roman", Font.BOLD, 25));
		contentPane.add(lbl_ThongTinP);
		
		JLabel lbl_MaP = new JLabel("Mã phòng:");
		lbl_MaP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_MaP.setBounds(281, 233, 115, 30);
		contentPane.add(lbl_MaP);
		
		JLabel lbl_TenP = new JLabel("Tên phòng:");
		lbl_TenP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_TenP.setBounds(281, 288, 115, 30);
		contentPane.add(lbl_TenP);
		
		JLabel lbl_GiaP = new JLabel("Giá phòng:");
		lbl_GiaP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_GiaP.setBounds(281, 343, 115, 30);
		contentPane.add(lbl_GiaP);
		
		JLabel lbl_LoaiP = new JLabel("Loại phòng:");
		lbl_LoaiP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_LoaiP.setBounds(854, 233, 115, 30);
		contentPane.add(lbl_LoaiP);
		
		JLabel lbl_TinhTrang = new JLabel("Tình trạng:");
		lbl_TinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_TinhTrang.setBounds(854, 288, 115, 30);
		contentPane.add(lbl_TinhTrang);
		
		txtMaPhong = new JTextField();
		txtMaPhong.setEditable(false);
		txtMaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaPhong.setBounds(450, 233, 200, 30);
		contentPane.add(txtMaPhong);
		txtMaPhong.setColumns(10);
		
		txtTenPhong = new JTextField();
		txtTenPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(450, 288, 200, 30);
		contentPane.add(txtTenPhong);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 384, 1540, 2);
		contentPane.add(separator);
		
		JButton btn_Them = new JButton("Thêm");
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themPhongHat();
			}
		});
		btn_Them.setIcon(new ImageIcon(PnlQuanLyPhong.class.getResource("/res/icons8-add-20.png")));
		btn_Them.setBackground(new Color(240, 230, 140));
		btn_Them.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Them.setBounds(200, 394, 120, 42);
		contentPane.add(btn_Them);
		
		JButton btn_Sua = new JButton("Sửa");
		btn_Sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn cập nhập ?", "Xác nhận",
							JOptionPane.YES_NO_OPTION);
					if (opt == JOptionPane.YES_OPTION) {
					capNhapPhongHat();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Sua.setIcon(new ImageIcon(PnlQuanLyPhong.class.getResource("/res/icons8-pencil-16.png")));
		btn_Sua.setBackground(new Color(240, 230, 140));
		btn_Sua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Sua.setBounds(450, 394, 120, 42);
		contentPane.add(btn_Sua);
		
		JButton btn_Xoa = new JButton("Xóa");
		btn_Xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa ?", "Xác nhận",
							JOptionPane.YES_NO_OPTION);
					if (opt == JOptionPane.YES_OPTION) {
					xoaPhongHat();
					}
			}
		});
		btn_Xoa.setIcon(new ImageIcon(PnlQuanLyPhong.class.getResource("/res/icons8-remove-24.png")));
		btn_Xoa.setBackground(new Color(240, 230, 140));
		btn_Xoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Xoa.setBounds(700, 394, 120, 42);
		contentPane.add(btn_Xoa);
		
		JButton btn_XoaTrang = new JButton("Xóa trắng");
		btn_XoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		btn_XoaTrang.setIcon(new ImageIcon(PnlQuanLyPhong.class.getResource("/res/x-mark-16.png")));
		btn_XoaTrang.setBackground(new Color(240, 230, 140));
		btn_XoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_XoaTrang.setBounds(963, 394, 133, 42);
		contentPane.add(btn_XoaTrang);
		
		JButton btn_TimKiem = new JButton("Tìm kiếm");
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = txtTimKiem.getText();
				searchAndDisplay(tbDSPhong, searchText);
			}
		});
		btn_TimKiem.setIcon(new ImageIcon(PnlQuanLyPhong.class.getResource("/res/icons8-search-16.png")));
		btn_TimKiem.setBackground(new Color(240, 230, 140));
		btn_TimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_TimKiem.setBounds(1200, 394, 133, 42);
		contentPane.add(btn_TimKiem);
		
		JLabel lbl_DSP = new JLabel("Danh sách phòng");
		lbl_DSP.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_DSP.setBounds(10, 459, 200, 25);
		contentPane.add(lbl_DSP);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 507, 1540, 314);
		contentPane.add(scrollPane);
		
		tbDSPhong = new JTable();
		tbDSPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HienThiForm();
			}
		});
		tbDSPhong.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 ph\u00F2ng", "T\u00EAn ph\u00F2ng", "Gi\u00E1 ph\u00F2ng", "Lo\u1EA1i ph\u00F2ng", "T\u00ECnh tr\u1EA1ng"
			}
		));
		model = (DefaultTableModel) tbDSPhong.getModel();
		updateTableData();
		tbDSPhong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setViewportView(tbDSPhong);
		
		txtGiaPhong = new JTextField();
		txtGiaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGiaPhong.setColumns(10);
		txtGiaPhong.setBounds(450, 343, 200, 30);
		contentPane.add(txtGiaPhong);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newValue = JOptionPane.showInputDialog("Thêm đơn loại phòng", null);
				if (newValue != null && !newValue.isEmpty()) {
					// Thêm giá trị mới vào JComboBox và làm cho nó được chọn
					cbLoaiPhong.addItem(newValue);
					cbLoaiPhong.setSelectedItem(newValue);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(PnlQuanLyPhong.class.getResource("/res/icons8-add-20.png")));
		btnNewButton.setBounds(1232, 233, 34, 30);
		contentPane.add(btnNewButton);
		
		JLabel lbl_TinhTrang_1 = new JLabel("Tìm kiếm:");
		lbl_TinhTrang_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_TinhTrang_1.setBounds(854, 343, 115, 30);
		contentPane.add(lbl_TinhTrang_1);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(1019, 343, 200, 30);
		contentPane.add(txtTimKiem);
		
		cbLoaiPhong = new JComboBox();
		cbLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbLoaiPhong.setModel(new DefaultComboBoxModel(new String[] {"Phòng thường", "Phòng VIP"}));
		cbLoaiPhong.setBounds(1019, 234, 201, 28);
		contentPane.add(cbLoaiPhong);
		
		cbTinhTrang = new JComboBox();
		cbTinhTrang.setModel(new DefaultComboBoxModel(new String[] {"Phòng trống", "Phòng đang sử dụng"}));
		cbTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbTinhTrang.setBounds(1019, 289, 201, 28);
		contentPane.add(cbTinhTrang);

	}
	protected void themPhongHat() {
		// TODO Auto-generated method stub
		String tenPhong = txtTenPhong.getText();
		String giaPhongStr = txtGiaPhong.getText();
		String loaiPhong = cbLoaiPhong.getSelectedItem().toString();
		String tinhTrang = cbTinhTrang.getSelectedItem().toString();
		int giaPhong = Integer.parseInt(giaPhongStr);
		if (validData()) {
			
			Phong phong = new Phong(0, tenPhong, giaPhong, loaiPhong, tinhTrang);
			JOptionPane.showMessageDialog(this, "Tạo mới thành công");
			Phong_dao.addPhong(phong);
			lamMoiBang();
			resetForm();
			lamMoiBang();
			Object[] rowData = new Object[] { 0, tenPhong, giaPhong, loaiPhong, tinhTrang };

			((DefaultTableModel) tbDSPhong.getModel()).addRow(rowData);

		}
	}

	private boolean validData() {
		String tenPhong = txtTenPhong.getText();
		String giaPhongStr = txtGiaPhong.getText();
		String loaiPhong = cbLoaiPhong.getSelectedItem().toString();
		String tinhTrang = cbTinhTrang.getSelectedItem().toString();
		int giaPhong = Integer.parseInt(giaPhongStr);
//		Họ tên không được để trống, có thể gồm nhiều từ ngăn cách bởi khoảng 
//		trắng. Không chứa ký số hoặc các ký tự đặc biệt khác, ngoại trừ ký tự 
		if (!(tenPhong.length() > 0 )) {
			JOptionPane.showMessageDialog(this, "Tên phòng không được để trống");
			return false;
		}

		if (!(giaPhong > 0)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10-11 chữ số và không chứa ký tự đặc biệt");
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
		Phong_dao dsp = new Phong_dao();
		List<Phong> list = dsp.docTuBang();
		if(list != null) {
		for (Phong phong : list) {
			Object[] rowData = { phong.getMaPhong(), phong.getTenPhong(), phong.getGiaPhong(), phong.getLoaiPhong(), phong.getTinhTrang() };
			model.addRow(rowData);
		}
		tbDSPhong.setModel(model);
		}
	}

	private void resetForm() {
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtGiaPhong.setText("");
		txtTimKiem.setText("");
	}

	public void HienThiForm() {
		// Lấy dữ liệu từ JTable
		int row = tbDSPhong.getSelectedRow();

		String maPhong = tbDSPhong.getValueAt(row, 0).toString();
		String tenPhong = tbDSPhong.getValueAt(row, 1).toString();
		String giaPhong = tbDSPhong.getValueAt(row, 2).toString();
		String loaiPhong = tbDSPhong.getValueAt(row, 3).toString();
		String tinhTrang = tbDSPhong.getValueAt(row, 4).toString();

		// Hiển thị dữ liệu
		txtMaPhong.setText(maPhong);
		txtTenPhong.setText(tenPhong);
		txtGiaPhong.setText(giaPhong);
		cbLoaiPhong.setSelectedItem(loaiPhong);
		cbTinhTrang.setSelectedItem(tinhTrang);
	}

	protected void capNhapPhongHat() throws SQLException {
		int row = tbDSPhong.getSelectedRow();
		if (validData()) {
			if (row != -1) {
				String maPhongStr = txtMaPhong.getText();
				String tenPhong = txtTenPhong.getText();
				String giaPhongStr = txtGiaPhong.getText();
				String loaiPhong = cbLoaiPhong.getSelectedItem().toString();
				String tinhTrang = cbTinhTrang.getSelectedItem().toString();
				int giaPhong = Integer.parseInt(giaPhongStr);
				int maPhong = Integer.parseInt(maPhongStr);
				Phong phong = new Phong(maPhong, tenPhong, giaPhong, loaiPhong, tinhTrang);

				if (Phong_dao.updatePhong(phong));					;
				model.setValueAt(maPhong, row, 0);
				model.setValueAt(tenPhong, row, 1);
				model.setValueAt(giaPhong, row, 2);
				model.setValueAt(loaiPhong, row, 3);
				model.setValueAt(tinhTrang, row, 4);
				lamMoiBang();
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				lamMoiBang();
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
	private void xoaPhongHat() {
		int row = tbDSPhong.getSelectedRow();
		if(row != -1) {
			String maPhong = txtMaPhong.getText();
			Phong_dao.deletePhong(maPhong);
			lamMoiBang();
			JOptionPane.showMessageDialog(null, "Xóa thành công");
			lamMoiBang();
			resetForm();
			
		}else
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng để xóa");
	}
}