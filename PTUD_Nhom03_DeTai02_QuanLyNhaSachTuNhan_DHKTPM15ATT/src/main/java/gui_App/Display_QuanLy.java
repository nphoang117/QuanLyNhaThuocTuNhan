/**
 * Nguyễn Viết Học _ 19533591 _ Nhóm 03 
 * 
 * Mô tả lớp : khung menu phân quyền cho nhân viên quản lý
 */
package gui_App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ChuyenManHinhController;
import manHinh.DanhMucManHinh;
import java.awt.event.MouseAdapter;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;

public class Display_QuanLy extends JFrame implements ActionListener {

	private JPanel contentPane;

	private JPopupMenu popupMenu = new JPopupMenu();
	private JMenuItem itemThongTin = new JMenuItem("Thông tin tài khoản");
	private JMenuItem itemDoiMatKhau = new JMenuItem("Thay đỗi mật khẩu");
	private JMenuItem itemDangXuat = new JMenuItem("Đăng xuất");

	private JPanel pnlHoTro;

	private JLabel lblHoTro;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Display_QuanLy() {
		setTitle("Chương trình quản lý nhà sách AAA");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1045);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(Color.DARK_GRAY);
		pnlMenu.setBounds(0, 0, 384, 998);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		JPanel pnlTrangChu = new JPanel();
		pnlTrangChu.setBackground(new Color(255, 204, 0));
		pnlTrangChu.setBounds(0, 0, 384, 89);
		pnlMenu.add(pnlTrangChu);
		pnlTrangChu.setLayout(null);

		JLabel lblTrangChu = new JLabel("Nha Sach AAA");
		lblTrangChu.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/logo.png")));
		lblTrangChu.setFont(new Font("Arial", Font.BOLD, 18));
		lblTrangChu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrangChu.setBounds(10, 11, 364, 67);
		pnlTrangChu.add(lblTrangChu);

		JPanel pnlPOP = new JPanel();
		pnlPOP.setBackground(new Color(255, 204, 0));
		pnlPOP.setBounds(10, 100, 364, 84);
		pnlMenu.add(pnlPOP);
		pnlPOP.setLayout(null);

		JLabel lblPOP = new JLabel("\r\nPOP");
		lblPOP.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/icons8_monitor_32px_1.png")));
		lblPOP.setFont(new Font("Arial", Font.BOLD, 18));
		lblPOP.setHorizontalAlignment(SwingConstants.CENTER);
		lblPOP.setBounds(10, 11, 344, 62);
		pnlPOP.add(lblPOP);

		JPanel pnlHangHoa = new JPanel();
		pnlHangHoa.setBackground(new Color(255, 204, 0));
		pnlHangHoa.setBounds(10, 195, 364, 84);
		pnlMenu.add(pnlHangHoa);
		pnlHangHoa.setLayout(null);

		JLabel lbl_HangHoa = new JLabel("H\u00E0ng H\u00F3a");
		lbl_HangHoa.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/icons8_book_26px.png")));
		lbl_HangHoa.setFont(new Font("Arial", Font.BOLD, 18));
		lbl_HangHoa.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_HangHoa.setBounds(10, 11, 344, 62);
		pnlHangHoa.add(lbl_HangHoa);

		JPanel pnlBaoCao = new JPanel();
		pnlBaoCao.setBackground(new Color(255, 204, 0));
		pnlBaoCao.setBounds(10, 670, 364, 84);
		pnlMenu.add(pnlBaoCao);
		pnlBaoCao.setLayout(null);

		JLabel lblBaoCao = new JLabel("B\u00E1o C\u00E1o");
		lblBaoCao.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/icons8_increase_26px.png")));
		lblBaoCao.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaoCao.setFont(new Font("Arial", Font.BOLD, 18));
		lblBaoCao.setBounds(10, 11, 344, 62);
		pnlBaoCao.add(lblBaoCao);

		JPanel pnlTaiKhoan = new JPanel();
		pnlTaiKhoan.setBackground(new Color(255, 204, 0));
		pnlTaiKhoan.setBounds(10, 901, 364, 84);
		pnlMenu.add(pnlTaiKhoan);
		pnlTaiKhoan.setLayout(null);

		popupMenu.setPopupSize(300, 150);
		popupMenu.add(itemThongTin);
		popupMenu.add(itemDoiMatKhau);
		popupMenu.add(itemDangXuat);

		JLabel lblTaiKhoan = new JLabel("T\u00E0i Kho\u1EA3n");
		lblTaiKhoan.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				lblTaiKhoan.setBackground((new Color(255, 255, 51)));
				pnlTaiKhoan.setBackground(new Color(255, 255, 51));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblTaiKhoan.setBackground(new Color(255, 204, 0));
				pnlTaiKhoan.setBackground(new Color(255, 204, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				popupMenu.show(lblTaiKhoan, e.getX(), e.getY() - 150);

			}
		});
		lblTaiKhoan.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/icons8_contact_26px.png")));
		lblTaiKhoan.setFont(new Font("Arial", Font.BOLD, 18));
		lblTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaiKhoan.setBounds(10, 11, 344, 62);
		pnlTaiKhoan.add(lblTaiKhoan);

		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.setLayout(null);
		pnlHoaDon.setBackground(new Color(255, 204, 0));
		pnlHoaDon.setBounds(10, 290, 364, 84);
		pnlMenu.add(pnlHoaDon);

		JLabel lblHoaDon = new JLabel("Hóa Đơn");
		lblHoaDon.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/icons8_purchase_order_26px_1.png")));
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDon.setFont(new Font("Arial", Font.BOLD, 18));
		lblHoaDon.setBounds(10, 11, 344, 62);
		pnlHoaDon.add(lblHoaDon);

		JPanel pnlNhaCungCap = new JPanel();
		pnlNhaCungCap.setLayout(null);
		pnlNhaCungCap.setBackground(new Color(255, 204, 0));
		pnlNhaCungCap.setBounds(10, 385, 364, 84);
		pnlMenu.add(pnlNhaCungCap);

		JLabel lblNhaCungCap = new JLabel("Nh\u00E0 Cung C\u1EA5p");
		lblNhaCungCap.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/icons8_handshake_26px.png")));
		lblNhaCungCap.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhaCungCap.setFont(new Font("Arial", Font.BOLD, 18));
		lblNhaCungCap.setBounds(10, 11, 344, 62);
		pnlNhaCungCap.add(lblNhaCungCap);

		JPanel pnlKhachHang = new JPanel();
		pnlKhachHang.setLayout(null);
		pnlKhachHang.setBackground(new Color(255, 204, 0));
		pnlKhachHang.setBounds(10, 480, 364, 84);
		pnlMenu.add(pnlKhachHang);

		JLabel lblKhachHang = new JLabel("Kh\u00E1ch H\u00E0ng");
		lblKhachHang.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/icons8_people_26px.png")));
		lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang.setFont(new Font("Arial", Font.BOLD, 18));
		lblKhachHang.setBounds(10, 11, 344, 62);
		pnlKhachHang.add(lblKhachHang);

		JPanel pnlNhanVien = new JPanel();
		pnlNhanVien.setLayout(null);
		pnlNhanVien.setBackground(new Color(255, 204, 0));
		pnlNhanVien.setBounds(10, 575, 364, 84);
		pnlMenu.add(pnlNhanVien);

		JLabel lblNhanVien = new JLabel("Nh\u00E2n Vi\u00EAn");
		lblNhanVien.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/icons8_businessman_26px.png")));
		lblNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhanVien.setFont(new Font("Arial", Font.BOLD, 18));
		lblNhanVien.setBounds(10, 11, 344, 62);
		pnlNhanVien.add(lblNhanVien);

		JPanel pnlView = new JPanel();
		pnlView.setBounds(394, 0, 1502, 985);
		contentPane.add(pnlView);

		JLabel lblChucVu = new JLabel("");
		lblChucVu.setBounds(126, 48, 166, 25);
		pnlTaiKhoan.add(lblChucVu);

		pnlHoTro = new JPanel();
		pnlHoTro.setLayout(null);
		pnlHoTro.setBackground(new Color(255, 204, 0));
		pnlHoTro.setBounds(10, 765, 364, 84);
		pnlMenu.add(pnlHoTro);

		lblHoTro = new JLabel("Hỗ Trợ");
		lblHoTro.setIcon(new ImageIcon(Display_QuanLy.class.getResource("/img/icons8_help_26px.png")));
		lblHoTro.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTro.setFont(new Font("Arial", Font.BOLD, 18));
		lblHoTro.setBounds(10, 11, 344, 62);
		pnlHoTro.add(lblHoTro);

		lblChucVu.setText("Chức vụ: " + DangNhapJFrame.nv.getChucVu());

		lblTaiKhoan.setText(DangNhapJFrame.nv.getTenNhanVien());

		ChuyenManHinhController controller = new ChuyenManHinhController(pnlView);

		controller.setView(pnlPOP, lblPOP);

		List<DanhMucManHinh> listItem = new ArrayList<>();

		listItem.add(new DanhMucManHinh("POP", pnlPOP, lblPOP));
		listItem.add(new DanhMucManHinh("HangHoa", pnlHangHoa, lbl_HangHoa));
		listItem.add(new DanhMucManHinh("HoaDon", pnlHoaDon, lblHoaDon));
		listItem.add(new DanhMucManHinh("NhaCungCap", pnlNhaCungCap, lblNhaCungCap));
		listItem.add(new DanhMucManHinh("KhachHang", pnlKhachHang, lblKhachHang));
		listItem.add(new DanhMucManHinh("NhanVien", pnlNhanVien, lblNhanVien));
		listItem.add(new DanhMucManHinh("BaoCao", pnlBaoCao, lblBaoCao));
		listItem.add(new DanhMucManHinh("HoTro", pnlHoTro, lblHoTro));

//		listItem.add(new DanhMucManHinh("TaiKhoan", pnlTaiKhoan, lblTaiKhoan));

		controller.setEvent(listItem);

		itemDangXuat.addActionListener(this);
		itemThongTin.addActionListener(this);
		itemDoiMatKhau.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == itemDangXuat) {
			int answer = JOptionPane.showConfirmDialog(Display_QuanLy.this, "Bạn có thật sự muốn đăng xuất",
					"Thông báo", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.NO_OPTION) {
				remove(itemDangXuat);
			} else if (answer == JOptionPane.YES_OPTION) {
				DangNhapJFrame dn = new DangNhapJFrame();
				dn.setLocationRelativeTo(null);
				dn.setVisible(true);
				dispose();

			}
		} else if (e.getSource() == itemDoiMatKhau) {
			ThayDoiMatKhau_jdialog jdl = new ThayDoiMatKhau_jdialog();
			jdl.setLocationRelativeTo(null);
			jdl.setVisible(true);

		} else if (e.getSource() == itemThongTin) {
			TaiKhoan_Jdialog taiKhoan = new TaiKhoan_Jdialog();
			taiKhoan.setLocationRelativeTo(null);
			taiKhoan.setVisible(true);
		}

	}
}
