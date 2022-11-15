/**
 * Nguyễn Viết Học _ 19533591 _ Nhóm 03 
 * 
 * Mô tả lớp : thực hiện chức năng đăng nhập và phân quyền cho nhân viên , gồm quản lý và nv bán hàng
 */
package gui_App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ColorUIResource;

import org.w3c.dom.events.MouseEvent;

import dao.NhanVienDao;
import entity.NhanVien;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.Key;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;

public class DangNhapJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenDN;
	private JPasswordField txtMatKhau;
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private JButton btnDangNhap;
	private JLabel lblQuenMatKhau;
	public static NhanVien nv = new NhanVien();
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhapJFrame frame = new DangNhapJFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhapJFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				DangNhapJFrame.this.getRootPane().setDefaultButton(btnDangNhap);
			}
		});
		setTitle("Đăng Nhập");
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 957, 553);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 455, 494);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(DangNhapJFrame.class.getResource("/img/DangNhap.png")));
		lblNewLabel_2.setBounds(10, 48, 435, 384);
		panel.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_1.setBounds(475, 11, 458, 494);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTenDN = new JLabel("Tên Đăng Nhập");
		lblTenDN.setForeground(Color.ORANGE);
		lblTenDN.setFont(new Font("Arial", Font.BOLD, 18));
		lblTenDN.setBounds(10, 155, 174, 36);
		panel_1.add(lblTenDN);

		txtTenDN = new JTextField();
		txtTenDN.setBounds(10, 202, 438, 36);
		panel_1.add(txtTenDN);
		txtTenDN.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 249, 174, 36);
		panel_1.add(lblNewLabel_1);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(10, 296, 438, 36);
		panel_1.add(txtMatKhau);

		txtTenDN.setText("nvhoc");
		txtMatKhau.setText("123456");

		btnDangNhap = new JButton("Đăng Nhập");
		/**
		 * Sự kiện cho nút đăng nhập
		 */
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String tenDN = txtTenDN.getText();
				String matKhau = String.valueOf(txtMatKhau.getPassword());
				if (tenDN.equals("") || matKhau.equals("")) {

					JOptionPane.showMessageDialog(new JFrame(), "Bạn chưa nhập đủ thông tin");
				} else {
					nv = nhanVienDao.dangNhap(tenDN, matKhau);

					if (nv.getChucVu().equals("quản lý")) {

						Display_QuanLy mainJFrame = new Display_QuanLy();
						mainJFrame.setVisible(true);
						mainJFrame.setLocationRelativeTo(null);
						DangNhapJFrame.this.setVisible(false);

					} else if (nv.getChucVu().equals("bán hàng")) {

						Display_NhanVien mainNV = new Display_NhanVien();
						mainNV.setVisible(true);
						mainNV.setLocationRelativeTo(null);
						DangNhapJFrame.this.setVisible(false);

					} else {
						JOptionPane.showMessageDialog(new JFrame(), "đăng nhập thất bại");
					}

				}

			}
		});

		btnDangNhap.setBackground(Color.ORANGE);
		btnDangNhap.setBounds(229, 402, 190, 52);

		panel_1.add(btnDangNhap);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(0, 0, 458, 143);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(0, 0, 193, 144);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.ORANGE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_3.setIcon(new ImageIcon(DangNhapJFrame.class.getResource("/img/logo.png")));

		JLabel lblNewLabel_4 = new JLabel("Chương Trình Quản Lý");
		lblNewLabel_4.setBounds(202, 11, 235, 70);
		panel_2.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));

		JLabel lblNewLabel_5 = new JLabel("Nhà Sách AAA");
		lblNewLabel_5.setBounds(203, 58, 236, 60);
		panel_2.add(lblNewLabel_5);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));

		JButton btnThoat = new JButton("Thoát");
		btnThoat.setForeground(Color.GRAY);
		/**
		 * Sự kiện cho nút Hủy phiên đăng nhập
		 */
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(DangNhapJFrame.this,
						"Bạn có thật sự muốn thoát chương trình", "Thông báo", JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.NO_OPTION) {
					remove(btnThoat);
				} else if (answer == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnThoat.setBounds(45, 402, 174, 52);
		btnThoat.setBackground(Color.WHITE);
		panel_1.add(btnThoat);

		lblQuenMatKhau = new JLabel("Quên mật khẩu ?");
		/**
		 * sự kiện cho chức năng Quên mật khẩu
		 */
		lblQuenMatKhau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblQuenMatKhau.setForeground(new Color(255, 51, 0));
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				Jdialog_QuenMatKhau jdl = new Jdialog_QuenMatKhau();

				jdl.setLocationRelativeTo(null);
				jdl.setVisible(true);
			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				lblQuenMatKhau.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				lblQuenMatKhau.setForeground(Color.BLUE);
			}
		});
		lblQuenMatKhau.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblQuenMatKhau.setForeground(Color.BLUE);
		lblQuenMatKhau.setBounds(10, 343, 107, 21);
		panel_1.add(lblQuenMatKhau);

	}
}
