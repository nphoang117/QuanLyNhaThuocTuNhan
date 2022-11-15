/**
 * Đoàn Kiều Mỹ Ngọc -19446111 - nhóm 03
 * 
 *  mô tả lớp : cửa sổ thay đỗi mật khẩu 
 */
package gui_App;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dao.TaiKhoanDao;
import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ThayDoiMatKhau_jdialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField txtMatKhauCu;
	private JPasswordField txtMkMoi;
	private JPasswordField txtNhapLai;
	private TaiKhoanDao taiKhoanDao = new TaiKhoanDao();
	private JButton btnXacNhan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ThayDoiMatKhau_jdialog dialog = new ThayDoiMatKhau_jdialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ThayDoiMatKhau_jdialog() {
		super((java.awt.Frame) null, true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
			
				ThayDoiMatKhau_jdialog.this.getRootPane().setDefaultButton(btnXacNhan);
			}
		});
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		
		
		
		setTitle("Đỗi mật khẩu");
		setBounds(100, 100, 450, 326);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin \u0111\u1ED7i m\u1EADt kh\u1EA9u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 416, 267);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Mật khẩu cũ:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel.setBounds(10, 39, 132, 30);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Mật khẩu mới:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_1.setBounds(10, 91, 132, 30);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Nhập lại mật khẩu:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_2.setBounds(10, 139, 132, 30);
				panel.add(lblNewLabel_2);
			}
			
			txtMatKhauCu = new JPasswordField();
			txtMatKhauCu.setBounds(161, 39, 227, 30);
			panel.add(txtMatKhauCu);
			
			txtMkMoi = new JPasswordField();
			txtMkMoi.setBounds(162, 91, 227, 30);
			panel.add(txtMkMoi);
			
			txtNhapLai = new JPasswordField();
			txtNhapLai.setBounds(161, 139, 227, 30);
			panel.add(txtNhapLai);
			
			 btnXacNhan = new JButton("Xác nhận");
			btnXacNhan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String mkcu= String.valueOf(txtMatKhauCu.getPassword());
					String mkmoi = String.valueOf(txtMkMoi.getPassword());
					String nhaplai = String.valueOf(txtNhapLai.getPassword());
					if(!mkmoi.equals(nhaplai)) {
						JOptionPane.showMessageDialog(ThayDoiMatKhau_jdialog.this, "Mật khẩu nhập lại không trùng khớp với mật khẩu mới");
					}else {
						NhanVien nv = DangNhapJFrame.nv;
						boolean resul= taiKhoanDao.thayDoiMatKhau(nv.getMaNhanVien(), mkcu, mkmoi);
						if(resul) {
							JOptionPane.showMessageDialog(ThayDoiMatKhau_jdialog.this, "Thay đỗi mật khẩu thành công");
							dispose();
						}else {
							JOptionPane.showMessageDialog(ThayDoiMatKhau_jdialog.this, "Mật khẩu cũ không đúng . Thay đỗi mật khẩu thất bại");
						}
					}
					
					
					
				}
			});
			btnXacNhan.setBackground(Color.ORANGE);
			btnXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnXacNhan.setBounds(245, 203, 139, 38);
			panel.add(btnXacNhan);
			
			JButton btnHuy = new JButton("Hủy");
			btnHuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnHuy.setForeground(Color.GRAY);
			btnHuy.setBackground(Color.WHITE);
			btnHuy.setFont(new Font("Tahoma", Font.ITALIC, 16));
			btnHuy.setBounds(103, 203, 132, 38);
			panel.add(btnHuy);
		}
	}
}
