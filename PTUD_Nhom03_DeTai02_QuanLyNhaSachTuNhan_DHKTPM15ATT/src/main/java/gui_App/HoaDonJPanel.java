/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03 
 * 
 * Mô tả lớp : Thực hiện các chức năng xem thông tin , cập nhật ghi chú ,tìm kiếm , xóa hóa đơn
 */
package gui_App;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.MyCombobox;
import dao.ChiTietHoaDonDao;
import dao.HoaDonDAO;
import dao.KhachHangDao;
import dao.NhanVienDao;
import dao.SachDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import com.toedter.calendar.JDateChooser;

public class HoaDonJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtxTimKiemTheoMa;
	private JTable table_HoaDon;

	private DefaultTableModel modelHD;
	private String[] columnNames_HD = { "STT", "Mã Hóa Đơn", "Nhân Viên", "Ngày Lập", "Khách Hàng", "Tổng tiền",
			"Ghi chú" };
	private String[] columnNames_CTHD = { "STT", "Mã Sách", "Tên Sách", "Số lượng", "Đơn Giá", "Thành tiền" };
	private HoaDonDAO listHoaDon = new HoaDonDAO();
	private JTextField txtSdtKH;
	private JTextField txtDiaChiKH;
	private JTextField txtMaHoaDon;
	private JTextField txtNgayLapHD;
	private JTextField txtNhanVien;
	private JTable table_CTHD;
	private DefaultTableModel model_CTHD;
	private JTextField txtTongTien;
	private JTextField txtGhiChu;
	private DefaultComboBoxModel cmbModel_KH;

	private KhachHangDao khdao = new KhachHangDao();
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private MyCombobox myccb_kh;
	private ChiTietHoaDonDao cthhdao = new ChiTietHoaDonDao();
	private SachDao sachdao = new SachDao();
	private HoaDonDAO hoaDonDAO = new HoaDonDAO();
	private JComboBox cmbKhachHang;
	private JTextField txtTimKiemTheoSDT_KH;
	private JDateChooser dateChooser_From;
	private JDateChooser dateChooser_To;

	/**
	 * Create the panel.
	 */
	public HoaDonJPanel() {
		setLayout(null);
		setBounds(0, 0, 1501, 985);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1501, 1123);
		panel.setBackground(new Color(240, 240, 240));
		add(panel);
		panel.setLayout(null);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(
				new TitledBorder(null, "T\u00ECm Ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBackground(Color.ORANGE);
		pnlTimKiem.setBounds(0, 0, 1501, 89);
		panel.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		txtxTimKiemTheoMa = new JTextField();
		txtxTimKiemTheoMa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtxTimKiemTheoMa.setText("");
				txtxTimKiemTheoMa.setForeground(Color.black);
				txtxTimKiemTheoMa.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				txtxTimKiemTheoMa.setText("Nhập mã hóa đơn ... +Phím ENTER");
				txtxTimKiemTheoMa.setForeground(Color.LIGHT_GRAY);
				txtxTimKiemTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 14));
				
			}
		});
		txtxTimKiemTheoMa.setText("Nhập mã hóa đơn ... +Phím ENTER");
		txtxTimKiemTheoMa.setForeground(Color.LIGHT_GRAY);
		txtxTimKiemTheoMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DangNhapJFrame.nv.getChucVu().equals("quản lý")) {
					timKiemtheoMaHD_QL();
				}else {
					timKiemtheoMaHD_NVBH();
				}
			}
		});
		txtxTimKiemTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 14));
		txtxTimKiemTheoMa.setBounds(10, 26, 330, 36);
		pnlTimKiem.add(txtxTimKiemTheoMa);
		txtxTimKiemTheoMa.setColumns(10);
		
		txtTimKiemTheoSDT_KH = new JTextField();
		/**
		 * Sự kiện tìm kiếm hóa đơn theo sđt khách hàng
		 */
		txtTimKiemTheoSDT_KH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DangNhapJFrame.nv.getChucVu().equals("quản lý")) {
					timKiemtheoSdtKhachHang_QL();
				}else {
					timKiemtheoSdtKhachHang_NVBH();
				}
			}
		});
		txtTimKiemTheoSDT_KH.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTimKiemTheoSDT_KH.setForeground(Color.black);
				txtTimKiemTheoSDT_KH.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtTimKiemTheoSDT_KH.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				txtTimKiemTheoSDT_KH.setForeground(Color.LIGHT_GRAY);
				txtTimKiemTheoSDT_KH.setFont(new Font("Tahoma", Font.ITALIC, 14));
				txtTimKiemTheoSDT_KH.setText("Nhập Sđt khách hàng ... + Phím ENTER");
			}
		});
		txtTimKiemTheoSDT_KH.setForeground(Color.LIGHT_GRAY);
		txtTimKiemTheoSDT_KH.setFont(new Font("Tahoma", Font.ITALIC, 14));
		txtTimKiemTheoSDT_KH.setText("Nhập Sđt khách hàng ... + Phím ENTER");
		txtTimKiemTheoSDT_KH.setBounds(350, 27, 330, 36);
		pnlTimKiem.add(txtTimKiemTheoSDT_KH);
		txtTimKiemTheoSDT_KH.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("|");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewLabel_8.setBounds(690, 24, 31, 36);
		pnlTimKiem.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Từ Ngày:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_9.setBounds(749, 26, 76, 36);
		pnlTimKiem.add(lblNewLabel_9);
		
		 dateChooser_From = new JDateChooser();
		dateChooser_From.setDateFormatString("dd/MM/yyyy");
		dateChooser_From.setBounds(835, 26, 224, 36);
		pnlTimKiem.add(dateChooser_From);
		
		JLabel lblNewLabel_10 = new JLabel("Đến :");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_10.setBounds(1079, 25, 54, 36);
		pnlTimKiem.add(lblNewLabel_10);
		
		 dateChooser_To = new JDateChooser();
		dateChooser_To.setDateFormatString("dd/MM/yyyy");
		dateChooser_To.setBounds(1143, 26, 224, 36);
		pnlTimKiem.add(dateChooser_To);
		
		JButton btn_TimTheoNgay = new JButton("Tìm");
		/**
		 * sự kiện tìm hóa đơn theo ngày lập hóa đơn
		 */
		btn_TimTheoNgay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DangNhapJFrame.nv.getChucVu().equals("quản lý")) {
					timKiemtheoNgay_QL();
				}else {
					timKiemtheoNgay_NVBH();
				}
				
			}
		});
		btn_TimTheoNgay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_TimTheoNgay.setBounds(1385, 27, 94, 36);
		pnlTimKiem.add(btn_TimTheoNgay);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 93, 1501, 414);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(
				new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(562, 299, 929, 98);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ghichu = txtGhiChu.getText();
				String mahd = txtMaHoaDon.getText();
				boolean rs = hoaDonDAO.updateGhiChu(mahd, ghichu);
				if (ghichu.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "vui lòng nhập ghi chú trước khi cập nhật");
				} else if (rs) {
					JOptionPane.showMessageDialog(new JFrame(), "Cập nhật thành công");
					
					setDataToTableHD_QL();
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "lỗi cập nhật ghi chú hóa đơn");
				}

			}
		});
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCapNhat.setBounds(21, 29, 178, 47);
		panel_3.add(btnCapNhat);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		/**
		 * event button xoa hoa don
		 */
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table_HoaDon.getSelectedRow();
				String mahd = (String) table_HoaDon.getValueAt(row, 1);

				if (row > 0) {
					int answer = JOptionPane.showConfirmDialog(HoaDonJPanel.this, "Bạn có thật sự muốn xóa hóa đơn này",
							"Thông báo", JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.NO_OPTION) {
						remove(btnXoa);
					} else if (answer == JOptionPane.YES_OPTION) {

						boolean cthd = cthhdao.delete(mahd);
						boolean hd = hoaDonDAO.delete(mahd);
						if (cthd && hd) {
							modelHD.removeRow(row);
							xoaRong();
							for (int i = 0; i < table_HoaDon.getRowCount(); i++) {
								table_HoaDon.setValueAt(i + 1, i, 0);

							}
						} else {
							System.out.println("xoa hoa don that bai");
						}
					}

				} else {
					JOptionPane.showMessageDialog(HoaDonJPanel.this, "vui lòng chọn hóa đơn cần xóa");
				}

			}
		});
		btnXoa.setBounds(240, 29, 178, 47);
		panel_3.add(btnXoa);

		JButton btnXoaRong = new JButton("xóa rỗng");
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRong();
			}
		});
		btnXoaRong.setBounds(465, 29, 178, 47);
		panel_3.add(btnXoaRong);
		
		JButton btn_LamMoi = new JButton("Làm Mới");
		btn_LamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(DangNhapJFrame.nv.getChucVu().equals("quản lý")) {
					setDataToTableHD_QL();
				}else {
					setDataToTableHD_NV();
				}
			}
		});
		btn_LamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_LamMoi.setBounds(741, 29, 178, 47);
		panel_3.add(btn_LamMoi);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"S\u1EA3n ph\u1EA9m \u0111\u00E3 mua", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(562, 22, 929, 266);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 23, 909, 232);
		panel_4.add(scrollPane_1);

		table_CTHD = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "STT", "M\u00E3 s\u00E1ch",
				"T\u00EAn s\u00E1ch", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u00E1 b\u00E1n", "Th\u00E0nh ti\u1EC1n" }));
		scrollPane_1.setViewportView(table_CTHD);

		table_CTHD.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table_CTHD.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table_CTHD.setRowHeight(50);
		table_CTHD.validate();
		table_CTHD.repaint();

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(10, 22, 526, 375);
		panel_1.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Mã hóa đơn:");
		lblNewLabel_3.setBounds(20, 11, 74, 29);
		panel_6.add(lblNewLabel_3);

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setBounds(104, 11, 412, 29);
		panel_6.add(txtMaHoaDon);
		txtMaHoaDon.setEditable(false);
		txtMaHoaDon.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Ngày lập:");
		lblNewLabel_4.setBounds(20, 51, 61, 29);
		panel_6.add(lblNewLabel_4);

		txtNgayLapHD = new JTextField();
		txtNgayLapHD.setBounds(104, 51, 412, 29);
		panel_6.add(txtNgayLapHD);
		txtNgayLapHD.setEditable(false);
		txtNgayLapHD.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Nhân viên:");
		lblNewLabel_5.setBounds(20, 91, 74, 29);
		panel_6.add(lblNewLabel_5);

		txtNhanVien = new JTextField();
		txtNhanVien.setBounds(104, 91, 412, 29);
		panel_6.add(txtNhanVien);
		txtNhanVien.setEditable(false);
		txtNhanVien.setColumns(10);
		// table_CTHD.setComponentPopupMenu(popupMenu);

		JLabel lblNewLabel_6 = new JLabel("Tổng tiền:");
		lblNewLabel_6.setBounds(20, 295, 74, 29);
		panel_6.add(lblNewLabel_6);

		txtTongTien = new JTextField();
		txtTongTien.setBounds(107, 295, 409, 29);
		panel_6.add(txtTongTien);
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Ghi chú:");
		lblNewLabel_7.setBounds(25, 335, 56, 29);
		panel_6.add(lblNewLabel_7);

		txtGhiChu = new JTextField();
		txtGhiChu.setBounds(107, 335, 409, 29);
		panel_6.add(txtGhiChu);
		txtGhiChu.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 131, 506, 149);
		panel_6.add(panel_5);
		panel_5.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_5.setBackground(Color.WHITE);
		panel_5.setLayout(null);

		txtDiaChiKH = new JTextField();
		txtDiaChiKH.setBounds(97, 109, 384, 29);
		panel_5.add(txtDiaChiKH);
		txtDiaChiKH.setEditable(false);
		txtDiaChiKH.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Địa chỉ:");
		lblNewLabel_2.setBounds(10, 109, 49, 29);
		panel_5.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("SĐT:");
		lblNewLabel_1.setBounds(10, 69, 49, 29);
		panel_5.add(lblNewLabel_1);

		txtSdtKH = new JTextField();
		txtSdtKH.setBounds(97, 69, 384, 29);
		panel_5.add(txtSdtKH);
		txtSdtKH.setEditable(false);
		txtSdtKH.setColumns(10);

		JLabel lblNewLabel = new JLabel("Khách hàng:");
		lblNewLabel.setBounds(10, 29, 77, 29);
		panel_5.add(lblNewLabel);

		cmbKhachHang = new JComboBox();
		/**
		 * event combobox khach hang
		 */
		cmbKhachHang.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					KhachHang kh = khdao.getTheoTen(cmbModel_KH.getSelectedItem().toString());
					txtSdtKH.setText(kh.getSdt());
					txtDiaChiKH.setText(kh.getDiaChi());

				}
			}
		});
		cmbKhachHang.setBounds(97, 29, 384, 29);
		panel_5.add(cmbKhachHang);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "Danh s\u00E1ch h\u00F3a \u0111\u01A1n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_2.setBounds(0, 512, 1501, 465);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 1481, 430);
		panel_2.add(scrollPane);

		modelHD = new DefaultTableModel(columnNames_HD, 0);

		table_HoaDon = new JTable(modelHD) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		/**
		 * event table hoa don
		 */
		table_HoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = table_HoaDon.getSelectedRow();
				txtMaHoaDon.setText(table_HoaDon.getValueAt(row, 1).toString());

				txtNgayLapHD.setText(table_HoaDon.getValueAt(row, 3).toString());

				txtNhanVien.setText(table_HoaDon.getValueAt(row, 2).toString());
				txtTongTien.setText(table_HoaDon.getValueAt(row, 5).toString());

				txtGhiChu.setText(table_HoaDon.getValueAt(row, 6).toString());
				setDataComboboxKhachHang();
				cmbModel_KH.setSelectedItem(table_HoaDon.getValueAt(row, 4).toString());

				setDataToTable_CTDH(table_HoaDon.getValueAt(row, 1).toString());

			}
		});

		scrollPane.setViewportView(table_HoaDon);


		table_HoaDon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table_HoaDon.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table_HoaDon.setRowHeight(50);
		table_HoaDon.validate();
		table_HoaDon.repaint();
		
		if(DangNhapJFrame.nv.getChucVu().equals("quản lý")) {
			setDataToTableHD_QL();
		}else {
			setDataToTableHD_NV();
		}
		

	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	/**
	 * tìm kiếm theo ngày lập hóa đơn cho nv bán hàng
	 * 
	 */
	public void timKiemtheoNgay_NVBH() {
		modelHD.setRowCount(0);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDon> list = hoaDonDAO.timHDTheoNgay_NVBH(convertUtilToSql(dateChooser_From.getDate()),
				convertUtilToSql(dateChooser_To.getDate()),DangNhapJFrame.nv.getMaNhanVien() );
		int columns = columnNames_HD.length;
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				HoaDon hd = list.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = hd.getMaHoaDon();
				nv = nhanVienDao.getNhanVienTheoMa(hd.getMaNhanVien().getMaNhanVien());
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(hd.getNgayLapHD());
				kh = khdao.getTheoMa(hd.getMaKhachHang().getMaKhachHang());
				obj[4] = kh.getTenKhachHang();
				obj[5] = hd.getTongTien();
				obj[6] = hd.getGhiChu();
				modelHD.addRow(obj);
			}
		}
	}
	/**
	 * tìm kiếm theo ngày lập hóa đơn cho quản lý 
	 * 
	 */
	public void timKiemtheoNgay_QL() {
		modelHD.setRowCount(0);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDon> list = hoaDonDAO.timHDTheoNgay(convertUtilToSql(dateChooser_From.getDate()),convertUtilToSql(dateChooser_To.getDate()));
		int columns = columnNames_HD.length;
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				HoaDon hd = list.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = hd.getMaHoaDon();
				nv = nhanVienDao.getNhanVienTheoMa(hd.getMaNhanVien().getMaNhanVien());
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(hd.getNgayLapHD());
				kh = khdao.getTheoMa(hd.getMaKhachHang().getMaKhachHang());
				obj[4] = kh.getTenKhachHang();
				obj[5] = hd.getTongTien();
				obj[6] = hd.getGhiChu();
				modelHD.addRow(obj);
			}
		}
	}
	/**
	 * tìm kiếm theo số điện thoại khách hàng cho nv bán hàng
	 */
	public void timKiemtheoSdtKhachHang_NVBH() {
		modelHD.setRowCount(0);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDon> list = hoaDonDAO.timHDTheoSdtKhachHang_NVBH(txtTimKiemTheoSDT_KH,DangNhapJFrame.nv.getMaNhanVien());
		int columns = columnNames_HD.length;
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				HoaDon hd = list.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = hd.getMaHoaDon();
				nv = nhanVienDao.getNhanVienTheoMa(hd.getMaNhanVien().getMaNhanVien());
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(hd.getNgayLapHD());
				kh = khdao.getTheoMa(hd.getMaKhachHang().getMaKhachHang());
				obj[4] = kh.getTenKhachHang();
				obj[5] = hd.getTongTien();
				obj[6] = hd.getGhiChu();
				modelHD.addRow(obj);
			}
		}
	}
	
	/**
	 * tìm kiếm theo số điện thoại khách hàng cho quản lý
	 */
	public void timKiemtheoSdtKhachHang_QL() {
		modelHD.setRowCount(0);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDon> list = hoaDonDAO.timHDTheoSdtKhachHang(txtTimKiemTheoSDT_KH);
		int columns = columnNames_HD.length;
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				HoaDon hd = list.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = hd.getMaHoaDon();
				nv = nhanVienDao.getNhanVienTheoMa(hd.getMaNhanVien().getMaNhanVien());
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(hd.getNgayLapHD());
				kh = khdao.getTheoMa(hd.getMaKhachHang().getMaKhachHang());
				obj[4] = kh.getTenKhachHang();
				obj[5] = hd.getTongTien();
				obj[6] = hd.getGhiChu();
				modelHD.addRow(obj);
			}
		}
	}
	/**
	 * tìm kiếm theo mã hóa đơn cho nv bán hàng
	 */
	public void timKiemtheoMaHD_NVBH() {
		modelHD.setRowCount(0);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDon> list = hoaDonDAO.timHDTheoMa_NVBH(txtxTimKiemTheoMa,DangNhapJFrame.nv.getMaNhanVien());
		int columns = columnNames_HD.length;
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				HoaDon hd = list.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = hd.getMaHoaDon();
				nv = nhanVienDao.getNhanVienTheoMa(hd.getMaNhanVien().getMaNhanVien());
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(hd.getNgayLapHD());
				kh = khdao.getTheoMa(hd.getMaKhachHang().getMaKhachHang());
				obj[4] = kh.getTenKhachHang();
				obj[5] = hd.getTongTien();
				obj[6] = hd.getGhiChu();
				modelHD.addRow(obj);
			}
		}
	}
	/**
	 * tìm kiếm theo mã hóa đơn cho quản lý 
	 */
	public void timKiemtheoMaHD_QL() {
		modelHD.setRowCount(0);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDon> list = hoaDonDAO.timHDTheoMa(txtxTimKiemTheoMa);
		int columns = columnNames_HD.length;
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				HoaDon hd = list.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = hd.getMaHoaDon();
				nv = nhanVienDao.getNhanVienTheoMa(hd.getMaNhanVien().getMaNhanVien());
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(hd.getNgayLapHD());
				kh = khdao.getTheoMa(hd.getMaKhachHang().getMaKhachHang());
				obj[4] = kh.getTenKhachHang();
				obj[5] = hd.getTongTien();
				obj[6] = hd.getGhiChu();
				modelHD.addRow(obj);
			}
		}
	}
	/**
	 * xóa rổng form hiển thị 
	 */
	private void xoaRong() {
		txtMaHoaDon.setText("");
		txtNgayLapHD.setText("");
		txtNhanVien.setText("");
		cmbKhachHang.setSelectedItem(null);
		txtSdtKH.setText("");
		txtDiaChiKH.setText("");
		txtTongTien.setText("");
		txtGhiChu.setText("");
		model_CTHD.setRowCount(0);
		table_HoaDon.clearSelection();

	}

	// lay data len table hoa don cho nhan vien
	private void setDataToTableHD_NV() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDon> listHD = listHoaDon.getListByMaNV(DangNhapJFrame.nv.getMaNhanVien());
		modelHD.setRowCount(0);
		int columns = columnNames_HD.length;
		Object[] obj;
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		int rows = listHD.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				HoaDon hd = listHD.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = hd.getMaHoaDon();
				nv = nhanVienDao.getNhanVienTheoMa(hd.getMaNhanVien().getMaNhanVien());
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(hd.getNgayLapHD());
				kh = khdao.getTheoMa(hd.getMaKhachHang().getMaKhachHang());
				obj[4] = kh.getTenKhachHang();
				obj[5] = hd.getTongTien();
				obj[6] = hd.getGhiChu();
				modelHD.addRow(obj);
			}
		}

	}
	// lay data len table hoa don cho quản lý
	private void setDataToTableHD_QL() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDon> listHD = listHoaDon.getList();
		modelHD.setRowCount(0);
		int columns = columnNames_HD.length;
		Object[] obj;
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		int rows = listHD.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				HoaDon hd = listHD.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = hd.getMaHoaDon();
				nv = nhanVienDao.getNhanVienTheoMa(hd.getMaNhanVien().getMaNhanVien());
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(hd.getNgayLapHD());
				kh = khdao.getTheoMa(hd.getMaKhachHang().getMaKhachHang());
				obj[4] = kh.getTenKhachHang();
				obj[5] = hd.getTongTien();
				obj[6] = hd.getGhiChu();
				modelHD.addRow(obj);
			}
		}

	}

	// lay data len table chi tiet hoa don
	public void setDataToTable_CTDH(String mahd) {
		DecimalFormat df = new DecimalFormat("###,###,###");
		List<ChiTietHoaDon> listIten = cthhdao.getList(mahd);
		model_CTHD = (DefaultTableModel) table_CTHD.getModel();
		model_CTHD.setRowCount(0);
		int columns = columnNames_CTHD.length;

		Object[] obj;
		int rows = listIten.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				ChiTietHoaDon cthd = listIten.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);

				obj[1] = cthd.getMaSach().getMaSach();
				Sach sachten = sachdao.getOne(cthd.getMaSach().getMaSach());
				obj[2] = sachten.getTenSach();
				obj[3] = cthd.getSoLuong();
				obj[4] = cthd.getDonGia();
				obj[5] = cthd.getThanhTien();
				model_CTHD.addRow(obj);
			}
		}
	}

	// lay data len combobox khach hang
	public void setDataComboboxKhachHang() {
		cmbModel_KH = (DefaultComboBoxModel) cmbKhachHang.getModel();
		cmbModel_KH.removeAllElements();
		ResultSet rSet = khdao.getComBoboxKhachHang();

		try {
			while (rSet.next()) {
				String maKH = rSet.getString("maKhachHang");
				String tenKH = rSet.getString("tenKhachHang");
				myccb_kh = new MyCombobox(maKH, tenKH);
				cmbModel_KH.addElement(myccb_kh);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "loi lay khach hang");

		}
	}
}
