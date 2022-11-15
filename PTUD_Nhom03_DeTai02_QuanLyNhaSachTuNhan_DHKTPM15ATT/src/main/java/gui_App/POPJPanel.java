/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03
 * 
 * mô tả lớp : thực hiện các chức năng tìm kiếm Sản phẩm , sau đó thêm vào bảng chi tiết hóa đơn
 * - cập nhật số lượng và xóa đi sản phẩm trong bảng  chi tiết hóa đơn,
 * - tìm kiếm khách hàng và Thêm Khách hàng mới , 
 * - Thanh toán hóa đơn.
 */
package gui_App;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.awt.event.ActionEvent;

import com.itextpdf.text.xml.simpleparser.NewLineHandler;
import com.toedter.calendar.JYearChooser;

import connectDB.ConnectDB;
import controller.MyCombobox;

import controller.TaoMaTuDong;
import dao.ChiTietHoaDonDao;
import dao.HoaDonDAO;
import dao.KhachHangDao;
import dao.SachDao;
import dao.TheLoaiDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;
import entity.TheLoai;

import service.Sach_service;

import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.EtchedBorder;

public class POPJPanel extends JPanel {
	private JTextField txtTimSach;
	private static JTable table_sanPham;
	private JTextField txtTimKhachHang;
	public static JTable table_CTHD;
	private JTextField txt_TienKhachTra;
	private JComboBox cmbTheLoai;
	private JLabel lbl_TienDuaLaiKhach;

	private DefaultTableModel modelSach;
	private DefaultTableModel model_CTHD;
	private TableRowSorter<TableModel> rowSorter = null;
	private SachDao sachDao = new SachDao();
	private String[] columnNames = { "Mã Sách", "Tên Sách", "Số lượng", "Đơn Giá" };
	private KhachHangDao khdao = new KhachHangDao();
	private TheLoaiDao theLoaidao = new TheLoaiDao();
	private HoaDonDAO hoaDonDAO = new HoaDonDAO();
	private ChiTietHoaDonDao cTHD_Dao = new ChiTietHoaDonDao();

	private DefaultComboBoxModel cmbModel_KH;
	private DefaultComboBoxModel cmbModel_TL;
	private MyCombobox myccb_kh;
	private JTextField txtTimMaSach;
	private JLabel lbl_TongThanhTien;
	private JLabel lblTenNhanVien;

	public static String soLuongMua = "1";
	public static boolean huy = false;
	public static String tenKH = "";
	public static String sdt = "";
	public static String diaChi = "";

	private static final long serialVersionUID = 1L;
	private JTextField txtGhiChu;
	private JLabel lblSdtKhachHang;
	private JLabel lblDiaChiKhachHang;

	private TaoMaTuDong taoMaTuDong = new TaoMaTuDong();
	private JLabel lblmaHoaDon;
	private JLabel lblTenKh;
	private JLabel lblNgayLapHD;

	/**
	 * Create the panel.
	 */
	public POPJPanel() {

		setLayout(null);
		setBounds(0, 0, 1502, 985);
		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1502, 1010);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(0, 0, 1502, 89);
		panel.add(panel_2);
		panel_2.setLayout(null);

		txtTimSach = new JTextField();
		txtTimSach.setText("Nhập tên sách....");
		txtTimSach.setForeground(Color.GRAY);
		txtTimSach.setFont(new Font("Tahoma", Font.ITALIC, 14));
		txtTimSach.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTimSach.setText("");
				txtTimSach.setForeground(Color.BLACK);
				txtTimSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTimSach.setForeground(Color.GRAY);
				txtTimSach.setFont(new Font("Tahoma", Font.ITALIC, 14));
				txtTimSach.setText("Nhập tên sách ....");
			}
		});

		/**
		 * event tim kiem
		 */
		txtTimSach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				timKiemSachTheoTen();
			}

			@Override
			public void keyReleased(KeyEvent e) {

				timKiemSachTheoTen();

			}
		});

		txtTimSach.setToolTipText("Nhập Từ Khóa Sản phẩm");
		txtTimSach.setBounds(10, 26, 378, 36);
		panel_2.add(txtTimSach);
		txtTimSach.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				"D:\\Hoc\\HK5\\PTUD_1\\Nhom\\Quan_Ly_Sach\\Quan_Ly_Sach\\image\\icons8_search_24px_2.png"));
		lblNewLabel_1.setBounds(12, 25, 39, 29);
		panel_2.add(lblNewLabel_1);

		txtTimMaSach = new JTextField();
		txtTimMaSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemSachTheoMa();
			}
		});
		txtTimMaSach.setForeground(Color.GRAY);
		txtTimMaSach.setFont(new Font("Tahoma", Font.ITALIC, 14));
		txtTimMaSach.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTimMaSach.setText("");
				txtTimMaSach.setForeground(Color.BLACK);
				txtTimMaSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTimMaSach.setForeground(Color.GRAY);
				txtTimMaSach.setFont(new Font("Tahoma", Font.ITALIC, 14));
				txtTimMaSach.setText("Nhập mã Sách....");
			}
		});
		txtTimMaSach.setText("Nhập mã Sách ....");
		txtTimMaSach.setToolTipText("Nhập Từ Khóa Sản phẩm");
		txtTimMaSach.setColumns(10);
		txtTimMaSach.setBounds(398, 26, 378, 36);
		panel_2.add(txtTimMaSach);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Th\u00F4ng tin s\u1EA3n ph\u1EA9m ", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 93, 508, 886);
		panel.add(panel_3);
		panel_3.setLayout(null);

		cmbTheLoai = new JComboBox();

		cmbTheLoai.setBounds(10, 61, 367, 39);
		panel_3.add(cmbTheLoai);

		JLabel lblNewLabel = new JLabel("Chọn Loại Sản Phẩm");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 217, 39);
		panel_3.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 188, 488, 689);
		panel_3.add(scrollPane);

		modelSach = new DefaultTableModel(columnNames, 0);

		table_sanPham = new JTable(modelSach) {

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		;
		/**
		 * Sự kiện thêm Sản phầm từ bảng sản phẩm qua bảng chi tiết hóa đơn
		 */
		table_sanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() >= 2) {

					Jdialog_ThongTinSanPham_BanHang jdl = new Jdialog_ThongTinSanPham_BanHang();
					jdl.setTitle("Nhập Số lượng cần bán");
					jdl.setLocationRelativeTo(null);

					jdl.setVisible(true);
					if (huy) {
						return;
					}

					System.out.println("sl:" + soLuongMua);
					String idSanPham = modelSach.getValueAt(table_sanPham.getSelectedRow(), 0).toString();
					System.out.println("id:" + idSanPham);
					Sach sach = sachDao.getOne(idSanPham);
					System.out.println("sach:" + sach);
					loadDataChiTietHoaDon(sach, soLuongMua);
					lbl_TongThanhTien.setText(String.valueOf(Double.parseDouble(tinhTongTien()) * 1.05));
					soLuongMua = "1";

				}
			}
		});

		scrollPane.setViewportView(table_sanPham);

		table_sanPham.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table_sanPham.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table_sanPham.setRowHeight(50);
		table_sanPham.validate();
		table_sanPham.repaint();

		//

		JLabel lblSchTmc = new JLabel("SÁCH TÌM ĐƯỢC");
		lblSchTmc.setForeground(Color.ORANGE);
		lblSchTmc.setBounds(10, 138, 214, 39);
		panel_3.add(lblSchTmc);
		lblSchTmc.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton btnTim_TL = new JButton("Tìm");
		btnTim_TL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemSachTheoTheLoai();
			}
		});
		btnTim_TL.setIcon(new ImageIcon(POPJPanel.class.getResource("/img/icons8_search_26px.png")));
		btnTim_TL.setBackground(Color.ORANGE);
		btnTim_TL.setForeground(Color.BLACK);
		btnTim_TL.setFont(new Font("Arial", Font.BOLD, 16));
		btnTim_TL.setBounds(387, 59, 95, 39);
		panel_3.add(btnTim_TL);
		
		JButton btnNewButton = new JButton("làm mới");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDataToTableSach_banhang();
			}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setIcon(new ImageIcon(POPJPanel.class.getResource("/img/icons8_available_updates_26px_1.png")));
		btnNewButton.setBounds(372, 140, 126, 43);
		panel_3.add(btnNewButton);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(null, "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_4.setBounds(518, 93, 974, 283);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Nhân Viên:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(27, 138, 82, 37);
		panel_4.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Tìm Khách Hàng:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(413, 23, 122, 37);
		panel_4.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Tên Khách Hàng:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(413, 71, 132, 37);
		panel_4.add(lblNewLabel_6);

		JButton btn_themKH = new JButton("");
		btn_themKH.setIcon(new ImageIcon(POPJPanel.class.getResource("/img/icons8_plus_math_26px.png")));
		/**
		 * sự kiện thêm khách hàng
		 */
		btn_themKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Jdialog_khachHang_pop jdl_kh = new Jdialog_khachHang_pop();
				jdl_kh.setLocationRelativeTo(null);
				jdl_kh.setVisible(true);

				lblTenKh.setText(tenKH);
				lblSdtKhachHang.setText(sdt);
				lblDiaChiKhachHang.setText(diaChi);

//				setDataComboboxKhachHang();

			}
		});
		btn_themKH.setBackground(Color.ORANGE);
		btn_themKH.setForeground(Color.BLACK);
		btn_themKH.setBounds(894, 27, 65, 30);
		panel_4.add(btn_themKH);

		JLabel lblNewLabel_7 = new JLabel("SĐT:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(413, 142, 52, 29);
		panel_4.add(lblNewLabel_7);

		lblSdtKhachHang = new JLabel("...");
		lblSdtKhachHang.setForeground(Color.BLUE);
		lblSdtKhachHang.setBackground(Color.BLUE);
		lblSdtKhachHang.setFont(new Font("Arial", Font.BOLD, 14));
		lblSdtKhachHang.setBounds(555, 141, 246, 29);
		panel_4.add(lblSdtKhachHang);

		lblDiaChiKhachHang = new JLabel("...");
		lblDiaChiKhachHang.setForeground(Color.BLUE);
		lblDiaChiKhachHang.setBackground(Color.BLUE);
		lblDiaChiKhachHang.setFont(new Font("Arial", Font.BOLD, 14));
		lblDiaChiKhachHang.setBounds(552, 194, 397, 29);
		panel_4.add(lblDiaChiKhachHang);

		txtTimKhachHang = new JTextField();
		txtTimKhachHang.setText("0988776612");
		txtTimKhachHang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTimKhachHang.setText("0294724683");
				txtTimKhachHang.setForeground(Color.BLACK);
				txtTimKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTimKhachHang.setForeground(Color.GRAY);
				txtTimKhachHang.setFont(new Font("Tahoma", Font.ITALIC, 14));
				txtTimKhachHang.setText("Nhập SĐT khách hàng để tìm kiếm");
			}
		});
		txtTimKhachHang.setForeground(Color.GRAY);
		txtTimKhachHang.setFont(new Font("Tahoma", Font.ITALIC, 14));
		txtTimKhachHang.setText("Nhập SĐT khách hàng để tìm kiếm");
		/**
		 * sự kiện Tim kiem khach hang
		 */
		txtTimKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemKhachHang();
				txtTimKhachHang.selectAll();
				txtTimKhachHang.requestFocus();
			}
		});

		txtTimKhachHang.setBounds(555, 27, 329, 29);
		panel_4.add(txtTimKhachHang);
		txtTimKhachHang.setColumns(10);

		String[] columnName_cthd = { "STT", "Mã Sách", "Tên Sách", "Số lượng", "Giá Bán", "Thành Tiền" };

		model_CTHD = new DefaultTableModel(columnName_cthd, 0);

		//

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(520, 838, 974, 142);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("Tổng Tiền:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_11.setBounds(204, 30, 84, 26);
		panel_1.add(lblNewLabel_11);

		lbl_TongThanhTien = new JLabel("0");
		lbl_TongThanhTien.setForeground(Color.RED);
		lbl_TongThanhTien.setBounds(298, 32, 117, 26);
		panel_1.add(lbl_TongThanhTien);

		JLabel lblNewLabel_13 = new JLabel("Tiền Khách Trả:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_13.setBounds(471, 28, 117, 30);
		panel_1.add(lblNewLabel_13);

		txt_TienKhachTra = new JTextField();
		/**
		 * sự kiện tính tiền thối cho khách
		 */
		txt_TienKhachTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double tienkhach = Double.parseDouble(txt_TienKhachTra.getText());
					double tongtien = Double.parseDouble(lbl_TongThanhTien.getText());
					if (tienkhach > tongtien) {
						lbl_TienDuaLaiKhach.setText(String.valueOf(tienkhach - tongtien));
					} else {
						JOptionPane.showMessageDialog(new JFrame(),
								"vui long nhập số tiền khách đưa lớn hơn tổng tiền");
						txt_TienKhachTra.requestFocus();
						txt_TienKhachTra.selectAll();
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(new JFrame(), "vui long nhập số tiền khách đã đưa");
					lbl_TienDuaLaiKhach.setText("0");
				}

			}
		});

		txt_TienKhachTra.setBounds(598, 30, 130, 30);
		panel_1.add(txt_TienKhachTra);
		txt_TienKhachTra.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Tiền Thừa:");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_14.setBounds(738, 30, 84, 26);
		panel_1.add(lblNewLabel_14);

		lbl_TienDuaLaiKhach = new JLabel("0");
		lbl_TienDuaLaiKhach.setForeground(Color.BLUE);
		lbl_TienDuaLaiKhach.setBounds(832, 29, 117, 29);
		panel_1.add(lbl_TienDuaLaiKhach);

		JButton btn_ThanhToan = new JButton("Thanh Toán");
		/**
		 * Sự kiện thanh toán Hóa Đơn
		 */
		btn_ThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (model_CTHD.getRowCount() <= 0) {
					JOptionPane.showMessageDialog(new JFrame(), "vui lòng Chọn mặt hàng cần thanh toán");
					return;
				} else if (lblTenKh.getText().trim().equals("...")) {
					JOptionPane.showMessageDialog(new JFrame(), "vui lòng Chọn Khách hàng trước khi thanh toán");
				} if (txt_TienKhachTra.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "vui lòng nhập tiền khách trả trước khi thanh toán");
				} else {

					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss a");
					String dateLaphd = dateFormat.format(new Date());
					HoaDon hd = new HoaDon();
					String mahdString = taoMaTuDong.taoSoHD();
					hd.setMaHoaDon(mahdString);
//			

					hd.setNgayLapHD(convertUtilToSql(new Date()));
					hd.setGhiChu(txtGhiChu.getText());


					KhachHang kh = khdao.getTheoTen(lblTenKh.getText());

					hd.setMaKhachHang(kh);
					NhanVien nv = new NhanVien();
					nv.setMaNhanVien(DangNhapJFrame.nv.getMaNhanVien());
					hd.setMaNhanVien(nv);
					hd.setTongTien(Double.parseDouble(lbl_TongThanhTien.getText()));

					boolean kq = hoaDonDAO.themHD(hd);
					themCTHD(table_CTHD, mahdString);

					if (kq) {
						JOptionPane.showMessageDialog(new JFrame(), "thanh toan thanh Cong");
						XuatHoaDon xuatHoaDon = new XuatHoaDon(table_CTHD, mahdString, dateLaphd, lblTenKh.getText(),
								lblSdtKhachHang.getText(), lblDiaChiKhachHang.getText(), lblTenNhanVien.getText(),
								txt_TienKhachTra.getText(), lbl_TienDuaLaiKhach.getText(), tinhTongTien(),
								txtGhiChu.getText(), "5");
						xuatHoaDon.xuatpdf();

					}
					model_CTHD.setNumRows(0);
					lblmaHoaDon.setText(taoMaTuDong.taoSoHD());
					lblTenKh.setText("...");
					lblSdtKhachHang.setText("...");
					lblDiaChiKhachHang.setText("...");
					txtGhiChu.setText("");
					lblNgayLapHD.setText(getNgayLapHD());
					txt_TienKhachTra.setText("");
					lbl_TongThanhTien.setText("0");
					lbl_TienDuaLaiKhach.setText("0");

				}
			}

		});
	
		btn_ThanhToan.setBackground(Color.ORANGE);
		btn_ThanhToan.setForeground(Color.BLACK);
		btn_ThanhToan.setFont(new Font("Arial", Font.BOLD, 16));
		btn_ThanhToan.setBounds(472, 82, 477, 47);
		panel_1.add(btn_ThanhToan);

		JButton btnHuy = new JButton("Hủy");
		/**
		 * Sự kiện Hủy Hóa đơn
		 */
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(new JFrame(), "bạn có thật sự muốn hủy hóa đơn này",
						"Swing Tester", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					model_CTHD.setRowCount(0);
					txtGhiChu.setText("");
					lblTenKh.setText("...");
					lblSdtKhachHang.setText("...");
					lblDiaChiKhachHang.setText("...");
				} else if (result == JOptionPane.NO_OPTION) {

				}

//				cmbKhachHang.setSelectedIndex(0);
			}
		});
		btnHuy.setBackground(new Color(242, 128, 128));
		btnHuy.setFont(new Font("Arial", Font.BOLD, 16));
		btnHuy.setBounds(24, 82, 425, 47);
		panel_1.add(btnHuy);

		JLabel lblNewLabel_9 = new JLabel("Thuế VAT : 5%");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(24, 30, 130, 26);
		panel_1.add(lblNewLabel_9);

		/**
		 * popupmenu cho bảng chi tiết hóa đơn
		 * - chức năng xóa đi sản phẩm có trong bảng chi tiết hóa đơn
		 */

		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem("Delete");
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model_CTHD = (DefaultTableModel) table_CTHD.getModel();
				int row = table_CTHD.getSelectedRow();
				if (row >= 0) {
					model_CTHD.removeRow(row);
					for (int i = 0; i < table_CTHD.getRowCount(); i++) {
						table_CTHD.setValueAt(i + 1, i, 0);

					}
				}
				lbl_TongThanhTien.setText(tinhTongTien());
			}
		});
		/**
		 * - chức năng chỉnh sửa lại số lượng sản phẩm có trong bảng chi tiết hóa đơn
		 */
		JMenuItem UpdateItem = new JMenuItem("update");
		UpdateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table_CTHD.getSelectedRow();
				soLuongMua = table_CTHD.getValueAt(row, 3).toString();

				Jdialog_ThongTinSanPham_BanHang idl = new Jdialog_ThongTinSanPham_BanHang();
				idl.setLocationRelativeTo(null);
				idl.setVisible(true);
				if (huy) {
					return;
				}
				table_CTHD.setValueAt(soLuongMua, row, 3);
				double thanhtien = Double.parseDouble(soLuongMua)
						* Double.parseDouble(table_CTHD.getValueAt(row, 4).toString());
				table_CTHD.setValueAt(thanhtien, row, 5);
				lbl_TongThanhTien.setText(tinhTongTien());
			}
		});

		popupMenu.add(deleteItem);
		popupMenu.add(UpdateItem);
		lblTenNhanVien = new JLabel("...");
		lblTenNhanVien.setForeground(Color.BLUE);
		lblTenNhanVien.setBackground(Color.BLUE);
		lblTenNhanVien.setFont(new Font("Arial", Font.BOLD, 14));
		lblTenNhanVien.setBounds(158, 144, 153, 22);
		panel_4.add(lblTenNhanVien);

		// set tên nhân viên từ giao diện đăng nhập
		lblTenNhanVien.setText(DangNhapJFrame.nv.getTenNhanVien());

		JLabel lblGhiChu = new JLabel("Ghi Chú:");
		lblGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGhiChu.setBounds(27, 234, 65, 29);
		panel_4.add(lblGhiChu);

		txtGhiChu = new JTextField();
		txtGhiChu.setFont(new Font("Arial", Font.PLAIN, 14));
		txtGhiChu.setBounds(102, 233, 862, 29);
		panel_4.add(txtGhiChu);
		txtGhiChu.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Địa chỉ:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(413, 193, 82, 30);
		panel_4.add(lblNewLabel_8);

		JLabel lblNewLabel_2 = new JLabel("Mã Hóa Đơn:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(27, 23, 82, 37);
		panel_4.add(lblNewLabel_2);

		lblmaHoaDon = new JLabel("");
		lblmaHoaDon.setBounds(158, 25, 184, 30);
		panel_4.add(lblmaHoaDon);
		lblmaHoaDon.setForeground(Color.RED);
		lblmaHoaDon.setFont(new Font("Arial", Font.BOLD, 14));
		lblmaHoaDon.setText(taoMaTuDong.taoSoHD());

		JLabel lblNewLabel_3 = new JLabel("Ngày Lập:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(27, 78, 65, 37);
		panel_4.add(lblNewLabel_3);
		lblNgayLapHD = new JLabel("");
		lblNgayLapHD.setBounds(158, 84, 184, 22);
		panel_4.add(lblNgayLapHD);
		lblNgayLapHD.setForeground(Color.BLUE);
		lblNgayLapHD.setBackground(Color.BLUE);
		lblNgayLapHD.setFont(new Font("Arial", Font.BOLD, 14));

		lblNgayLapHD.setText(getNgayLapHD());

		lblTenKh = new JLabel("...");
		lblTenKh.setFont(new Font("Arial", Font.BOLD, 14));
		lblTenKh.setForeground(Color.BLUE);
		lblTenKh.setBounds(555, 71, 329, 35);
		panel_4.add(lblTenKh);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA7m c\u1EA7n thanh to\u00E1n",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(518, 387, 974, 440);
		panel.add(panel_5);
		panel_5.setLayout(null);
		//

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 954, 407);
		panel_5.add(scrollPane_1);

		table_CTHD = new JTable(model_CTHD);
		scrollPane_1.setViewportView(table_CTHD);

		table_CTHD.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table_CTHD.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table_CTHD.setRowHeight(50);
		table_CTHD.validate();
		table_CTHD.repaint();
		table_CTHD.setComponentPopupMenu(popupMenu);

		setDataToTableSach_banhang();
//		setDataComboboxKhachHang();
		setDataComboboxTheLoai();
	}

	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	/**
	 * Lấy Ngày lập hóa đơn hiện tại
	 * @return
	 */
	public String getNgayLapHD() {
		String ngayLapHD = "";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss a");
		Date dateLapHD = new Date();
		ngayLapHD = dateFormat.format(dateLapHD);
		return ngayLapHD;
	}

// them  Chi tiết Hóa đơn
	public void themCTHD(JTable tbl, String maHD) {

		for (int i = 0; i < tbl.getRowCount(); i++) {
			ChiTietHoaDon cthh = new ChiTietHoaDon();
			Sach sach = new Sach();
			sach.setMaSach(tbl.getValueAt(i, 1).toString());
			HoaDon hd = new HoaDon();
			hd.setMaHoaDon(maHD);
			cthh.setMaHoaDon(hd);
			cthh.setMaSach(sach);
			cthh.setDonGia(Double.parseDouble(tbl.getValueAt(i, 4).toString()));
			cthh.setSoLuong(Integer.parseInt(tbl.getValueAt(i, 3).toString()));
			System.out.println(cthh.getThanhTien());
//			cthh.setThanhTien(Double.parseDouble(tbl.getValueAt(i, 5).toString()));
			cTHD_Dao.them(cthh);
		}

	}

	/**
	 * thêm sản phẩm từ bảng Sản phẩm qua bảng chi tiết hóa đơn
	 * @param sach
	 * @param soLuong
	 */
	public void loadDataChiTietHoaDon(Sach sach, String soLuong) {
		DecimalFormat df = new DecimalFormat("###,###,###");
		model_CTHD = (DefaultTableModel) table_CTHD.getModel();
		Object obj[] = new Object[6];

		obj[0] = model_CTHD.getRowCount() + 1;
		obj[1] = sach.getMaSach();
		obj[2] = sach.getTenSach();
		obj[3] = soLuong;
		obj[4] = String.valueOf(sach.getDonGia());
		obj[5] = String.valueOf(Double.parseDouble(soLuong) * sach.getDonGia());

		if (table_CTHD.getRowCount() == 0) {
			model_CTHD.addRow(obj);
		} else if (table_CTHD.getRowCount() > 0) {

			for (int i = 0; i < table_CTHD.getRowCount(); i++) {
				String maSach = table_CTHD.getValueAt(i, 1).toString();
				String sl = table_CTHD.getValueAt(i, 3).toString();
				String tt = table_CTHD.getValueAt(i, 5).toString();
				System.out.println(maSach);
				if (sach.getMaSach().equals(maSach)) {
					int soLuongmoi = Integer.parseInt(sl) + Integer.parseInt(soLuong);
					tt = String.valueOf(soLuongmoi * sach.getDonGia());
					table_CTHD.setValueAt(String.valueOf(soLuongmoi), i, 3);
					table_CTHD.setValueAt(tt, i, 5);
					return;
				}
			}
			model_CTHD.addRow(obj);
		}

	}

// lay data vao bang san pham	
	public void setDataToTableSach_banhang() {
		DecimalFormat df = new DecimalFormat("###,###,###");
		List<Sach> listIten = sachDao.getList();
		modelSach.setRowCount(0);
		int columns = columnNames.length;
		Object[] obj;
		int rows = listIten.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				Sach sach = listIten.get(i);
				obj = new Object[columns];
				obj[0] = sach.getMaSach();
				obj[1] = sach.getTenSach();
				obj[2] = sach.getSoLuong();
				obj[3] = sach.getDonGia();
				modelSach.addRow(obj);
			}
		}
	}



// tim kiem theo combobox
	public void timKiemSachTheoTheLoai() {

		modelSach.setRowCount(0);
		MyCombobox cbm_TK = (MyCombobox) cmbModel_TL.getSelectedItem();

		List<Sach> list = sachDao.getSachTheoTheLoai(cbm_TK.maString());
		int columns = columnNames.length;
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				Sach sach = list.get(i);
				obj = new Object[columns];
				obj[0] = sach.getMaSach();
				obj[1] = sach.getTenSach();
				obj[2] = sach.getSoLuong();
				obj[3] = sach.getDonGia();
				modelSach.addRow(obj);
			}
		}
	}

// tim kiem bang text
	public void timKiemSachTheoTen() {
		modelSach.setRowCount(0);
		List<Sach> list = sachDao.timSachTheoTen(txtTimSach);
		int columns = columnNames.length;
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				Sach sach = list.get(i);
				obj = new Object[columns];
				obj[0] = sach.getMaSach();
				obj[1] = sach.getTenSach();
				obj[2] = sach.getSoLuong();
				obj[3] = sach.getDonGia();
				modelSach.addRow(obj);
			}
		}
	}

	/**
	 * tìm kiếm sách theo mã sách
	 */
	public void timKiemSachTheoMa() {
		modelSach.setRowCount(0);
		List<Sach> list = sachDao.timSachTheoMa(txtTimMaSach);
		int columns = columnNames.length;
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				Sach sach = list.get(i);
				obj = new Object[columns];
				obj[0] = sach.getMaSach();
				obj[1] = sach.getTenSach();
				obj[2] = sach.getSoLuong();
				obj[3] = sach.getDonGia();
				modelSach.addRow(obj);
			}
		}
	}


	// tim kiem kh
	public void timKiemKhachHang() {
		KhachHang kh = khdao.timKiemTheoSdt(txtTimKhachHang);
		lblDiaChiKhachHang.setText(kh.getDiaChi());
		lblSdtKhachHang.setText(kh.getSdt());
		lblTenKh.setText(kh.getTenKhachHang());

	}



// lay data vao combobox the loai sach
	public void setDataComboboxTheLoai() {
		cmbModel_TL = (DefaultComboBoxModel) cmbTheLoai.getModel();
		cmbModel_TL.removeAllElements();
		ResultSet rSet = theLoaidao.getList();

		try {
			while (rSet.next()) {
				String maLoai = rSet.getString("maLoai");
				String tenloai = rSet.getString("tenTheLoai");
				MyCombobox myccb = new MyCombobox(maLoai, tenloai);
				cmbModel_TL.addElement(myccb);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "loi lay loai san pham");
		}
	}

	public static double withTwoDecimalPlaces(double value) {
		DecimalFormat df = new DecimalFormat("#.00");
		return new Double(df.format(value));
	}

// tinh tong tien hoa don
	public String tinhTongTien() {
		double tongTien = 0;

		for (int i = 0; i < table_CTHD.getRowCount(); i++) {

			tongTien += Double.parseDouble(table_CTHD.getValueAt(i, 5).toString());
//			System.out.println("tien:" + String.valueOf(tongTien));
		}
		return String.valueOf(tongTien);

	}

	/**
	 * thêm Poppup vào giao diện
	 * @param component
	 * @param popup
	 */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
