/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03
 * 
 * mô tả lớp : Hiển thị thông tin của Nhân viên đã đăng nhập vào 
 */
package gui_App;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class TaiKhoan_Jdialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtten;
	private JTextField txtsdt;
	private JTextField txtdiachi;
	private JTextField txtemail;
	private JTextField txtGioitinh;
	private JTextField txtChucVu;
	private JTextField txtNgaySInh;

	/**
	 * Create the dialog.
	 */
	public TaiKhoan_Jdialog() {

		super((java.awt.Frame) null, true);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setTitle("Xem thông tin tài khoản");
		setBounds(100, 100, 513, 469);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin t\u00E0i kho\u1EA3n", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 479, 410);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Tên nhân viên:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel.setBounds(10, 39, 124, 30);
				panel.add(lblNewLabel);
			}
			{
				txtten = new JTextField();
				txtten.setEditable(false);
				txtten.setBounds(144, 39, 312, 30);
				panel.add(txtten);
				txtten.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("SĐT:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_1.setBounds(10, 80, 82, 30);
				panel.add(lblNewLabel_1);
			}
			{
				txtsdt = new JTextField();
				txtsdt.setEditable(false);
				txtsdt.setBounds(144, 80, 312, 30);
				panel.add(txtsdt);
				txtsdt.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Địa chỉ:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_2.setBounds(10, 121, 82, 30);
				panel.add(lblNewLabel_2);
			}
			{
				txtdiachi = new JTextField();
				txtdiachi.setEditable(false);
				txtdiachi.setBounds(144, 121, 312, 30);
				panel.add(txtdiachi);
				txtdiachi.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Email:");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_3.setBounds(10, 162, 82, 30);
				panel.add(lblNewLabel_3);
			}
			{
				txtemail = new JTextField();
				txtemail.setEditable(false);
				txtemail.setBounds(144, 162, 312, 30);
				panel.add(txtemail);
				txtemail.setColumns(10);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Giới tính:");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_4.setBounds(10, 203, 82, 30);
				panel.add(lblNewLabel_4);
			}
			{
				txtGioitinh = new JTextField();
				txtGioitinh.setEditable(false);
				txtGioitinh.setBounds(144, 203, 312, 30);
				panel.add(txtGioitinh);
				txtGioitinh.setColumns(10);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Chức Vụ:");
				lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_5.setBounds(10, 244, 82, 30);
				panel.add(lblNewLabel_5);
			}
			{
				txtChucVu = new JTextField();
				txtChucVu.setEditable(false);
				txtChucVu.setBounds(144, 244, 312, 30);
				panel.add(txtChucVu);
				txtChucVu.setColumns(10);
			}
			{
				JButton btnThoat = new JButton("Thoát");
				btnThoat.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnThoat.setBackground(Color.ORANGE);
				btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnThoat.setBounds(307, 354, 148, 45);
				panel.add(btnThoat);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("Ngày sinh:");
				lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_6.setBounds(10, 285, 95, 30);
				panel.add(lblNewLabel_6);
			}
			{
				txtNgaySInh = new JTextField();
				txtNgaySInh.setEditable(false);
				txtNgaySInh.setBounds(144, 285, 312, 30);
				panel.add(txtNgaySInh);
				txtNgaySInh.setColumns(10);
			}
		}
		setdata();
	}

	public void setdata() {
		NhanVien nhanVien = DangNhapJFrame.nv;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		txtten.setText(nhanVien.getTenNhanVien());
		txtChucVu.setText(nhanVien.getChucVu());
		txtdiachi.setText(nhanVien.getDiaChi());
		txtemail.setText(nhanVien.getEmail());
		txtsdt.setText(nhanVien.getSdt());
		txtNgaySInh.setText(dateFormat.format(nhanVien.getNgaySinh()));
		txtGioitinh.setText(nhanVien.isGioiTinh() == true ? "nam" : "Nữ");

	}

}
