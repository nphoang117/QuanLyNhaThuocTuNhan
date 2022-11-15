/**
 * Nguyễn Viết Học _ 19533591_ Nhóm 03
 * 
 * mô tả lớp : Thực hiện chức năng thêm mới một khách hàng trong giao diện Bán Hàng 
 */
package gui_App;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuKeyEvent;

import controller.TaoMaTuDong;
import dao.KhachHangDao;
import dao.Regexdao;
import entity.KhachHang;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jdialog_khachHang_pop extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTenKH;
	private JTextField txtSdt;
	private JTextField txtDiaChi;

	private TaoMaTuDong taoMaTuDong = new TaoMaTuDong();
	private KhachHangDao khachHangDao = new KhachHangDao();
	private Regexdao regex = new Regexdao();

	/**
	 * Create the dialog.
	 */
	public Jdialog_khachHang_pop() {

		super((java.awt.Frame) null, true);
		setTitle("Thêm Khách hàng");
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

		setBounds(100, 100, 623, 330);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 589, 270);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Họ và tên :");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 38, 79, 29);
		panel.add(lblNewLabel);

		txtTenKH = new JTextField();
		txtTenKH.setBounds(119, 38, 445, 29);
		panel.add(txtTenKH);
		txtTenKH.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Số điện thoại:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 94, 94, 30);
		panel.add(lblNewLabel_1);

		txtSdt = new JTextField();
		txtSdt.setBounds(119, 95, 445, 29);
		panel.add(txtSdt);
		txtSdt.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Địa chỉ:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 145, 79, 29);
		panel.add(lblNewLabel_2);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(119, 145, 445, 29);
		panel.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (kiemTra()) {
					String makh = taoMaTuDong.taoMaKhachHang();
					String tenkh = txtTenKH.getText();
					String sdt = txtSdt.getText();
					String diaChi = txtDiaChi.getText();
					KhachHang kh = new KhachHang();
					kh.setMaKhachHang(makh);
					kh.setTenKhachHang(tenkh);
					kh.setSdt(sdt);
					kh.setDiaChi(diaChi);
					Boolean resul = khachHangDao.themKhachHang(kh);
					if (resul) {
						JOptionPane.showMessageDialog(Jdialog_khachHang_pop.this, "Thêm Khách Hàng thành công");
						POPJPanel.tenKH = tenkh;
						POPJPanel.sdt = sdt;
						POPJPanel.diaChi = diaChi;
					} else {
						JOptionPane.showMessageDialog(Jdialog_khachHang_pop.this, "Thêm Khách Hàng thất bại");
					}
					dispose();
				}
			}
		});
		btnThem.setFont(new Font("Arial", Font.PLAIN, 14));
		btnThem.setBackground(Color.ORANGE);
		btnThem.setForeground(Color.BLACK);
		btnThem.setBounds(448, 204, 116, 38);
		panel.add(btnThem);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHuy.setBackground(Color.ORANGE);
		btnHuy.setFont(new Font("Arial", Font.PLAIN, 14));
		btnHuy.setBounds(336, 204, 102, 38);
		panel.add(btnHuy);
	}

	/**
	 * Kiểm tra điều kiện nhập
	 * 
	 * @return
	 */
	private boolean kiemTra() {
		if (!regex.regexTen(txtTenKH))
			return false;
		if (!regex.regexSDT(txtSdt))
			return false;
		if (!regex.regexDiaChi(txtDiaChi))
			return false;
		return true;
	}

}
