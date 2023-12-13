package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.DatDichVu_dao;
import dao.DatPhong_dao;
import dao.DichVu_dao;
import dao.KhachHang_dao;
import dao.NhanVien_dao;
import dao.Phong_dao;
import dao.TaiKhoanDao;
import entity.ChiTietPhieuDatPhongDichVu;
import entity.DichVu;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlDatDichVu extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaDV;
	private JTextField txtSoLuong;
	private JTable tbDSDV;
	private JComboBox cbTenDV;
	private JComboBox cbTenPhong;
	public int pddvCounter = 2;
	private DefaultTableModel model;
	private JTextField txtMaPDP;
//	private PhieuDatDichVu phieu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlDatDichVu frame = new PnlDatDichVu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public PnlDatDichVu() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PnlDatDichVu.class.getResource("/res/product.png")));
		setTitle("Đặt dịch vụ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 1473, 198);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlDatDichVu.class.getResource("/res/kara.png")));
		lblNewLabel.setBounds(0, 0, 250, 212);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Karaoke Nice");
		lblNewLabel_1.setFont(new Font("Vladimir Script", Font.BOLD, 80));
		lblNewLabel_1.setBounds(517, 63, 482, 97);
		panel.add(lblNewLabel_1);

		JButton btnDangXuat = new JButton("Thoát");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn trở về màn hình chính ?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
					try {
						String tenDangNhap = PnlDangNhap.tenDangNhap;
						String tenNV = TaiKhoanDao.getTenNV(tenDangNhap);

						if (TaiKhoanDao.KiemTraNhanVien(tenNV).equals("Nhân viên")) {
							new gui.ManHinhNhanVien_gui().setVisible(true);
						} else if (TaiKhoanDao.KiemTraNhanVien(tenNV).equals("Quản lý")) {
							new gui.ManHinhChinh_gui().setVisible(true);
						} else if (TaiKhoanDao.KiemTraNhanVien(tenNV).equals("Kế toán")) {
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
		btnDangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnDangXuat.setIcon(new ImageIcon(PnlDatDichVu.class.getResource("/res/account-logout-16.png")));
		btnDangXuat.setBounds(1252, 132, 112, 40);
		panel.add(btnDangXuat);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PnlDatDichVu.class.getResource("/res/tenDangNhap.png")));
		lblNewLabel_2.setBounds(1273, 11, 75, 64);
		panel.add(lblNewLabel_2);

		JLabel lblNguoiDung = new JLabel("Người dùng:");
		String tenDangNhap = PnlDangNhap.tenDangNhap;
		lblNguoiDung.setText(lblNguoiDung.getText() + " " + TaiKhoanDao.getTenNV(tenDangNhap));
		lblNguoiDung.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNguoiDung.setBounds(1179, 86, 350, 41);
		panel.add(lblNguoiDung);

		JLabel lblNewLabel_3 = new JLabel("Thông tin đặt dịch vụ");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(10, 226, 240, 37);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Tên phòng:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(130, 310, 105, 37);
		getContentPane().add(lblNewLabel_4);

		cbTenPhong = new JComboBox();
		LayTenPhong();
		cbTenPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbTenPhong.setBounds(266, 310, 189, 32);
		getContentPane().add(cbTenPhong);

		JLabel lblNewLabel_4_1 = new JLabel("Mã dịch vụ:");
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(604, 378, 105, 37);
		getContentPane().add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel("Tên dịch vụ:");
		lblNewLabel_4_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_2.setBounds(604, 310, 105, 37);
		getContentPane().add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_4 = new JLabel("Số lượng:");
		lblNewLabel_4_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_4.setBounds(1022, 310, 105, 37);
		getContentPane().add(lblNewLabel_4_4);

		cbTenDV = new JComboBox();
		LayTenDichVu();
		cbTenDV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbTenDV.setBounds(719, 310, 189, 32);
		getContentPane().add(cbTenDV);

		txtMaDV = new JTextField();
		txtMaDV.setEditable(false);
		txtMaDV.setBounds(719, 383, 189, 30);
		getContentPane().add(txtMaDV);
		txtMaDV.setColumns(10);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(1137, 315, 92, 30);
		getContentPane().add(txtSoLuong);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 261, 1396, 15);
		getContentPane().add(separator);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					datDichVu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.setBackground(new Color(240, 230, 140));
		btnThem.setIcon(new ImageIcon(PnlDatDichVu.class.getResource("/res/icons8-add-20.png")));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBounds(266, 460, 113, 37);
		getContentPane().add(btnThem);

		JButton btnSua = new JButton("Sửa ");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					suaDichVu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSua.setBackground(new Color(240, 230, 140));
		btnSua.setIcon(new ImageIcon(PnlDatDichVu.class.getResource("/res/icons8-pencil-16.png")));
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSua.setBounds(476, 460, 113, 37);
		getContentPane().add(btnSua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaDichVu();
				JOptionPane.showMessageDialog(null, "Xóa thành công");

			}
		});
		btnXoa.setBackground(new Color(240, 230, 140));
		btnXoa.setIcon(new ImageIcon(PnlDatDichVu.class.getResource("/res/icons8-remove-24.png")));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoa.setBounds(681, 460, 113, 37);
		getContentPane().add(btnXoa);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(new Color(240, 230, 140));
		btnLamMoi.setIcon(new ImageIcon(PnlDatDichVu.class.getResource("/res/icons8-refresh-16.png")));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLamMoi.setBounds(891, 460, 128, 37);
		getContentPane().add(btnLamMoi);

		JButton btnTim = new JButton("");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timMaDV();
			}
		});
		btnTim.setIcon(new ImageIcon(PnlDatDichVu.class.getResource("/res/icons8-search-16.png")));
		btnTim.setBounds(928, 310, 39, 32);
		getContentPane().add(btnTim);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 531, 1396, 15);
		getContentPane().add(separator_1);

		JLabel lblNewLabel_5 = new JLabel("Danh sách dịch vụ đã đặt");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_5.setBounds(10, 543, 269, 30);
		getContentPane().add(lblNewLabel_5);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 595, 1252, 279);
		getContentPane().add(scrollPane);

		tbDSDV = new JTable();
		tbDSDV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hienThiForm();
			}
		});
		model = (DefaultTableModel) tbDSDV.getModel();
//		updateTableData();
		tbDSDV.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 phi\u1EBFu \u0111\u1EB7t d\u1ECBch v\u1EE5",
						"M\u00E3 phi\u1EBFu \u0111\u1EB7t ph\u00F2ng", "M\u00E3 d\u1ECBch v\u1EE5",
						"S\u1ED1 l\u01B0\u1EE3ng" }));
		scrollPane.setViewportView(tbDSDV);

		JButton btnTimDVTheoMa = new JButton("");
		btnTimDVTheoMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenPhong = cbTenPhong.getSelectedItem().toString();
				timMaPDP();
				try {
					DichVuDaDat();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTimDVTheoMa.setIcon(new ImageIcon(PnlDatDichVu.class.getResource("/res/icons8-search-16.png")));
		btnTimDVTheoMa.setBounds(465, 310, 39, 32);
		getContentPane().add(btnTimDVTheoMa);

		JLabel lblNewLabel_4_1_1 = new JLabel("Mã Phiếu đặt phòng:");
		lblNewLabel_4_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4_1_1.setBounds(61, 378, 174, 37);
		getContentPane().add(lblNewLabel_4_1_1);

		txtMaPDP = new JTextField();
		txtMaPDP.setEditable(false);
		txtMaPDP.setColumns(10);
		txtMaPDP.setBounds(266, 383, 189, 30);
		getContentPane().add(txtMaPDP);

	}

	public void LayTenDichVu() {
		List<String> dichVu = DatDichVu_dao.getTenDichVu();
		// Xóa dữ liệu cũ trong JComboBox và thêm dữ liệu mới
		cbTenDV.removeAllItems();
		for (String service : dichVu) {
			cbTenDV.addItem(service);
		}
	}

	public void LayTenPhong() {
		List<String> phong = DatDichVu_dao.getTenPhong();
		cbTenPhong.removeAllItems();
		for (String phongs : phong) {
			cbTenPhong.addItem(phongs);
		}
	}

	public void timMaDV() {
		String tenDV = cbTenDV.getSelectedItem().toString();
		String[] parts = tenDV.split("-");

		if (parts.length > 0) {
			String tenDichVuTruocKiTu = parts[0].trim();
			DichVu dv = DichVu_dao.timMaDVTheoTenDV(tenDichVuTruocKiTu);
			int maDV = dv.getMaDV();
			txtMaDV.setText(String.valueOf(maDV));

		}

	}

	public void timMaPDP() {

		String tenPhong = cbTenPhong.getSelectedItem().toString();

		PhieuDatPhong phieu = DatPhong_dao.getMaPDP(tenPhong);
		int maPDP = phieu.getMaPDP();
		txtMaPDP.setText(String.valueOf(maPDP));
	}

	public void datDichVu() throws SQLException {

		String maPDPStr = txtMaPDP.getText();
		String maDVStr = txtMaDV.getText();
		String tenDVStr = cbTenDV.getSelectedItem().toString();
		String soLuongStr = txtSoLuong.getText();
		int soLuong = Integer.parseInt(soLuongStr);
		int maPDP = Integer.parseInt(maPDPStr);
		int maDV = Integer.parseInt(maDVStr);

		ChiTietPhieuDatPhongDichVu pddv = new ChiTietPhieuDatPhongDichVu(0, maPDP, maDV, soLuong, false);
		DatDichVu_dao.addDatDichVu(pddv);
		DichVuDaDat();
		resetForm();
		Object[] rowData = new Object[] { 0, maPDP, maDV, soLuong };

		((DefaultTableModel) tbDSDV.getModel()).addRow(rowData);
		JOptionPane.showMessageDialog(null, "Đặt dịch vụ thành công");
	}

	public void DichVuDaDat() throws SQLException {
		String maPDPStr = txtMaPDP.getText();
		int maPDP = Integer.parseInt(maPDPStr);
		DatDichVu_dao ddv = new DatDichVu_dao();
		List<ChiTietPhieuDatPhongDichVu> list = ddv.getChiTietByMaPhieuDatPhong(maPDP);
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Mã phiếu đặt dịch vụ");
		tableModel.addColumn("Mã phiếu đặt phòng");
		tableModel.addColumn("Mã dịch vụ");
		tableModel.addColumn("Số lượng");
		for (ChiTietPhieuDatPhongDichVu ct : list) {
			Object[] rowData = { ct.getMaPhieu(), ct.getMaPDP(), ct.getMaDV(), ct.getSoLuong() };
			tableModel.addRow(rowData);
		}
		tbDSDV.setModel(tableModel);
	}

	private void resetForm() {
		txtMaDV.setText("");
		txtSoLuong.setText("");
	}

	protected void suaDichVu() throws SQLException {
		int row = tbDSDV.getSelectedRow();
		if (row != -1) {

			int maPhieu = (int) tbDSDV.getValueAt(row, 0);
			int maPDP = (int) tbDSDV.getValueAt(row, 1);
			int maDV = (int) tbDSDV.getValueAt(row, 2);
			int soLuong = (int) tbDSDV.getValueAt(row, 3);

			String maPhieuStr = String.valueOf(maPhieu);
			String maPDPStr = String.valueOf(maPDP);
			String maDVStr = String.valueOf(maDV);
			String soLuongStr = String.valueOf(soLuong);

			txtMaDV.setText(maDVStr);
			txtMaPDP.setText(maPDPStr);
			txtSoLuong.setText(soLuongStr);

			ChiTietPhieuDatPhongDichVu dv = new ChiTietPhieuDatPhongDichVu(maPhieu, maPDP, maDV, soLuong, false);

			if (DatDichVu_dao.updateDichVu(dv))
				;
			model.setValueAt(maPhieu, row, 0);
			model.setValueAt(maPDP, row, 1);
			model.setValueAt(maDV, row, 2);
			model.setValueAt(soLuong, row, 3);
			JOptionPane.showMessageDialog(this, "Cập nhật thành công");
			resetForm();

		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng trong bảng để cập nhật thông tin nhân viên.");

		}
	}

	public void xoaDichVu() {
		int row = tbDSDV.getSelectedRow();
		int maPhieu = (int) tbDSDV.getValueAt(row, 0);
		int maPDP = (int) tbDSDV.getValueAt(row, 1);
		int maDV = (int) tbDSDV.getValueAt(row, 2);
		int soLuong = (int) tbDSDV.getValueAt(row, 3);
		DatDichVu_dao.deleteDichVu(maPhieu);

	}

	public void hienThiForm() {
		int row = tbDSDV.getSelectedRow();
		int maPhieu = (int) tbDSDV.getValueAt(row, 0);
		int maPDP = (int) tbDSDV.getValueAt(row, 1);
		int maDV = (int) tbDSDV.getValueAt(row, 2);
		int soLuong = (int) tbDSDV.getValueAt(row, 3);

		String maPhieuStr = String.valueOf(maPhieu);
		String maPDPStr = String.valueOf(maPDP);
		String maDVStr = String.valueOf(maDV);
		String soLuongStr = String.valueOf(soLuong);

		txtMaDV.setText(maDVStr);
		txtMaPDP.setText(maPDPStr);
		txtSoLuong.setText(soLuongStr);

	}
}
