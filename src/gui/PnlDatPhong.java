package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

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

import dao.DatPhong_dao;
import dao.KhachHang_dao;
import dao.Phong_dao;
import dao.TaiKhoanDao;
import entity.KhachHang;
import entity.PhieuDatPhong;
import entity.Phong;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JDesktopPane;

public class PnlDatPhong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSoDienThoai;
	private JTextField txtMaKH;
	private JTextField txtSoLuongNguoi;
	private JTextField txtGioBatDau;
	private JTable tbDSPhong;
	private JTextField txtTenNV;
	private JTextField txtTenKH;
	private JComboBox cbTinhTrang;
	private DefaultTableModel model;
	private KhachHang_dao KhachHang_dao;
	private int pdpCounter = 0;

	/**
	 * Launch the application.
	 * 
	 * @return
	 */
	void datPhong() {

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlDatPhong frame = new PnlDatPhong();
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
	public PnlDatPhong() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlDatPhong.class.getResource("/res/order.png")));
		setTitle("Đặt phòng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane =new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(-190, 0, 2584, 178);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_Karaoke = new JLabel("Karaoke Nice");
		lbl_Karaoke.setBackground(new Color(32, 178, 1));
		lbl_Karaoke.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lbl_Karaoke.setBounds(723, 36, 426, 107);
		panel.add(lbl_Karaoke);

		JButton btn_DangXuat = new JButton("Thoát");
		btn_DangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,
						"Bạn có muốn trở về màn hình chính ?", "Xác nhận",
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
		btn_DangXuat.setIcon(new ImageIcon(PnlDatPhong.class.getResource("/res/account-logout-16.png")));
		btn_DangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btn_DangXuat.setBounds(1540, 130, 108, 35);
		panel.add(btn_DangXuat);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlDatPhong.class.getResource("/res/kara.png")));
		lblNewLabel.setBounds(190, 0, 279, 178);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PnlDatPhong.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel_1.setBounds(1563, 11, 86, 79);
		panel.add(lblNewLabel_1);

		JLabel lblNguoiDung = new JLabel("Người dùng:");
		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));		lblNguoiDung.setBounds(1476, 86, 349, 43);
		panel.add(lblNguoiDung);

		JLabel lbl_ThongTinDV = new JLabel("Thông tin đặt phòng:");
		lbl_ThongTinDV.setBounds(10, 184, 250, 25);
		lbl_ThongTinDV.setFont(new Font("Times New Roman", Font.BOLD, 25));
		contentPane.add(lbl_ThongTinDV);

		JLabel lbl_MaDV = new JLabel("Mã khách hàng:");
		lbl_MaDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_MaDV.setBounds(253, 265, 148, 30);
		contentPane.add(lbl_MaDV);

		JLabel lbl_DonViTinh = new JLabel("Tên khách hàng:");
		lbl_DonViTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_DonViTinh.setBounds(253, 319, 131, 30);
		contentPane.add(lbl_DonViTinh);

		JLabel lbl_LoaiDV = new JLabel("Số điện thoại:");
		lbl_LoaiDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_LoaiDV.setBounds(253, 210, 131, 30);
		contentPane.add(lbl_LoaiDV);

		JLabel lbl_TenDV = new JLabel("Số lượng người:");
		lbl_TenDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_TenDV.setBounds(838, 210, 142, 30);
		contentPane.add(lbl_TenDV);

		JLabel lbl_GiaNhap = new JLabel("Giờ bắt đầu:");
		lbl_GiaNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_GiaNhap.setBounds(838, 265, 159, 30);
		contentPane.add(lbl_GiaNhap);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoDienThoai.setBounds(450, 210, 200, 30);
		contentPane.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(450, 265, 200, 30);
		contentPane.add(txtMaKH);

		txtSoLuongNguoi = new JTextField();
		txtSoLuongNguoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSoLuongNguoi.setColumns(10);
		txtSoLuongNguoi.setBounds(1020, 210, 200, 30);
		contentPane.add(txtSoLuongNguoi);

		txtGioBatDau = new JTextField();
		txtGioBatDau.setEditable(false);
		txtGioBatDau.setText("");
//		txtGioBatDau.setText(updateTime());

		txtGioBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGioBatDau.setColumns(10);
		txtGioBatDau.setBounds(1020, 265, 200, 30);
		contentPane.add(txtGioBatDau);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 360, 1540, 2);
		contentPane.add(separator);

		JButton btn_DatPhong = new JButton("Đặt phòng");
		btn_DatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					datPhongHat();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_DatPhong.setIcon(new ImageIcon(PnlDatPhong.class.getResource("/res/icons8-add-20.png")));
		btn_DatPhong.setBackground(new Color(240, 230, 140));
		btn_DatPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_DatPhong.setBounds(507, 373, 142, 44);
		contentPane.add(btn_DatPhong);

		JButton btn_Huy = new JButton("Hủy");
		btn_Huy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane,
						"Xác nhận Hủy đặt phòng ?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
					if (opt == JOptionPane.YES_OPTION) {
						HuyDatPhong();
						resetForm();
					}
			}
		});
		btn_Huy.setIcon(new ImageIcon(PnlDatPhong.class.getResource("/res/icons8-remove-24.png")));
		btn_Huy.setBackground(new Color(240, 230, 140));
		btn_Huy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_Huy.setBounds(729, 373, 120, 44);
		contentPane.add(btn_Huy);

		JButton btn_TimKiem = new JButton("Tìm kiếm");
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = cbTinhTrang.getSelectedItem().toString();
				searchAndDisplay(tbDSPhong, searchText);
			}
		});
		btn_TimKiem.setIcon(new ImageIcon(PnlDatPhong.class.getResource("/res/icons8-search-16.png")));
		btn_TimKiem.setBackground(new Color(240, 230, 140));
		btn_TimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btn_TimKiem.setBounds(585, 502, 132, 30);
		contentPane.add(btn_TimKiem);

		JLabel lbl_DSDV = new JLabel("Danh sách phòng:");
		lbl_DSDV.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_DSDV.setBounds(10, 464, 200, 25);
		contentPane.add(lbl_DSDV);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 558, 1540, 314);
		contentPane.add(scrollPane);

		tbDSPhong = new JTable();
		tbDSPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layThongTin();
			}
		});
		tbDSPhong.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 ph\u00F2ng",
				"T\u00EAn ph\u00F2ng", "Gi\u00E1 ph\u00F2ng", "Lo\u1EA1i ph\u00F2ng", "T\u00ECnh tr\u1EA1ng" }));
		model = (DefaultTableModel) tbDSPhong.getModel();
		updateTableData();
		tbDSPhong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setViewportView(tbDSPhong);

		JLabel lbl_GiaDV_1 = new JLabel("Nhân viên:");
		lbl_GiaDV_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_GiaDV_1.setBounds(838, 319, 142, 30);
		contentPane.add(lbl_GiaDV_1);

		txtTenNV = new JTextField();
		txtTenNV.setEditable(false);
		txtTenNV.setText(txtTenNV.getText() + " " + PnlDangNhap.tenDangNhap);
		txtTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(1020, 320, 200, 30);
		contentPane.add(txtTenNV);

		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(450, 320, 200, 30);
		contentPane.add(txtTenKH);

		KhachHang_dao = new KhachHang_dao();
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					timKhachHang();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(PnlDatPhong.class.getResource("/res/icons8-search-16.png")));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton.setBounds(660, 210, 34, 30);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(224, 505, 120, 24);
		contentPane.add(lblNewLabel_2);

		cbTinhTrang = new JComboBox();
		cbTinhTrang.setModel(new DefaultComboBoxModel(new String[] {"Phòng Trống", "Phòng đang sử dụng"}));
		cbTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbTinhTrang.setBounds(354, 502, 190, 30);
		contentPane.add(cbTinhTrang);

	}

	private String updateTime() {
		// Lấy thời gian hiện tại
		Date currentTimeDate = new Date();

		// Định dạng thời gian
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedTime = dateFormat.format(currentTimeDate);

		// Hiển thị thời gian trên JTextField
		txtGioBatDau.setText(formattedTime);
		return formattedTime;
	}

	private void timKhachHang() throws SQLException {

		String sdtKH = txtSoDienThoai.getText().trim();
		// Thực hiện tìm kiếm trong cơ sở dữ liệu
		if (!sdtKH.isEmpty()) {
			KhachHang kh = KhachHang_dao.timTenKHTheoSDT(sdtKH);

			if (kh != null) {
				// Hiển thị số điện thoại lên JTextField
				int maKH = kh.getMaKH();
				txtMaKH.setText(String.valueOf(maKH));
				txtTenKH.setText(kh.getTenKH());
			} else {
				// Hiển thị frame khách hàng để thêm Khách hàng vào ds
				int opt = JOptionPane.showConfirmDialog(rootPane,
						"Khách hàng chưa có trong hệ thống, bạn có muốn thêm khách hàng ?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
					if (opt == JOptionPane.YES_OPTION) {
					new PnlQuanLyKhachHang().setVisible(true);
					
						}
				} 
			}else 
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại khách hàng");
	}

	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		updateTableData();
	}

	private void updateTableData() {
		Phong_dao dsp = new Phong_dao();
		List<Phong> list = dsp.docTuBang();
		if (list != null) {
			for (Phong phong : list) {
				Object[] rowData = { phong.getMaPhong(), phong.getTenPhong(), phong.getGiaPhong(), phong.getLoaiPhong(),
						phong.getTinhTrang() };
				model.addRow(rowData);
			}
			tbDSPhong.setModel(model);
		}
	}

	public void resetForm() {
		txtMaKH.setText("");
		txtSoDienThoai.setText("");
		txtSoLuongNguoi.setText("");
		txtTenKH.setText("");
		txtGioBatDau.setText("");
	}
	public void HuyDatPhong() {
		int row = tbDSPhong.getSelectedRow();
		int maPhong = (int) tbDSPhong.getValueAt(row, 0);
		DatPhong_dao.deleteDatPhong(maPhong);
		updateTableData();
		JOptionPane.showMessageDialog(null, "Hủy đặt phòng thành công");
	}
	
	public void layThongTin() {
		//Mã phiếu, mã phòng, mã kh, tên kh, sdt, số lượng ng, thời gian đặt, tên nv
		// Lấy dữ liệu từ JTable
		int row = tbDSPhong.getSelectedRow();

		String maPhong = tbDSPhong.getValueAt(row, 0).toString();
		String tenPhong = tbDSPhong.getValueAt(row, 1).toString();
		String giaPhong = tbDSPhong.getValueAt(row, 2).toString();
		String loaiPhong = tbDSPhong.getValueAt(row, 3).toString();
		String tinhTrang = tbDSPhong.getValueAt(row, 4).toString();
	}
	public void datPhongHat() throws SQLException {
		int row = tbDSPhong.getSelectedRow();

		int maPhong = (int)tbDSPhong.getValueAt(row, 0);
		String tenPhong = tbDSPhong.getValueAt(row, 1).toString();
		String giaPhong = tbDSPhong.getValueAt(row, 2).toString();
		String loaiPhong = tbDSPhong.getValueAt(row, 3).toString();
		String tinhTrang = tbDSPhong.getValueAt(row, 4).toString();
		
		txtGioBatDau.setText(updateTime());
		String maKHStr = txtMaKH.getText();
		String tenKH = txtTenKH.getText();
		String sdt = txtSoDienThoai.getText();
		String soLuongNguoiStr = txtSoLuongNguoi.getText();
		String thoiGianDat = txtGioBatDau.getText();
		String tenNV = txtTenNV.getText();
		int soLuongNguoi = Integer.parseInt(soLuongNguoiStr);
		int maKH = Integer.parseInt(maKHStr);
		if(tinhTrang.equals("Phòng Trống")) {
			PhieuDatPhong pdp = new PhieuDatPhong(0, maPhong, maKH, tenKH, sdt, soLuongNguoi, thoiGianDat,false, tenNV);
//			JOptionPane.showMessageDialog(this, "Tạo mới thành công");
			DatPhong_dao.addDatPhong(pdp);
			DatPhong_dao.updateTrangThaiPhong(maPhong);
			lamMoiBang();
			resetForm();
			int opt = JOptionPane.showConfirmDialog(rootPane,
					"Đặt phòng thành công, bạn có muốn thêm dịch vụ không ?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
				new PnlDatDichVu().setVisible(true);
					setVisible(false);
				}
			}else
				JOptionPane.showMessageDialog(null, "Phòng đang được sử dụng, vui lòng đặt phòng khác");
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