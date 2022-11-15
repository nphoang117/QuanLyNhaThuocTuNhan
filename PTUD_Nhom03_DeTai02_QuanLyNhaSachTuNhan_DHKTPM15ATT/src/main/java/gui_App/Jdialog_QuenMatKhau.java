/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03 
 * 
 *  mô tả lớp : cửa sổ cấp lại mật khẩu bên giao diện đăng nhập
 */
package gui_App;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import dao.TaiKhoanDao;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jdialog_QuenMatKhau extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMaNV;
	private JTextField txtTenTaiKhoan;
	private TaiKhoanDao taiKhoanDao= new TaiKhoanDao();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			Jdialog_QuenMatKhau dialog = new Jdialog_QuenMatKhau();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public Jdialog_QuenMatKhau() {
		
		super((java.awt.Frame)null,true);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setTitle("Cấp lại mật khẩu");
		setBounds(100, 100, 524, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin c\u1EA5p l\u1EA1i m\u1EADt kh\u1EA9u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 490, 241);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Nhân Viên:");
		lblNewLabel.setBounds(10, 39, 85, 29);
		panel.add(lblNewLabel);
		
		txtMaNV = new JTextField();
		txtMaNV.setBounds(105, 39, 375, 29);
		panel.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản:");
		lblNewLabel_1.setBounds(10, 92, 85, 29);
		panel.add(lblNewLabel_1);
		
		txtTenTaiKhoan = new JTextField();
		txtTenTaiKhoan.setBounds(105, 92, 375, 29);
		panel.add(txtTenTaiKhoan);
		txtTenTaiKhoan.setColumns(10);
		
		JButton btnCapLaiMK = new JButton("Cấp lại mật khẩu");
		btnCapLaiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String manv = txtMaNV.getText();
				String tendn= txtTenTaiKhoan.getText();
				boolean resul= taiKhoanDao.capLaiMatKhau(manv, tendn);
				if(manv.equals("")||tendn.equals("")) {
					JOptionPane.showMessageDialog(Jdialog_QuenMatKhau.this, "Bạn chưa nhập đủ thông tin");
				}else {
					if(resul) {
						JOptionPane.showMessageDialog(new JFrame(),"Mật khẩu của bạn đã được cấp lại thành công!\n"
								+ "Mật khẩu hiện tại của bạn là : 111111\n "
								+ "Sau khi đăng nhập vui lòng đỗi lại mật khẩu mới để đảm bảo an toàn ");
						dispose();
					}else {
						JOptionPane.showMessageDialog(new JFrame(), "Mã nhân viên hoặc tên tài khoản không chính xác");
					}
				}
				
				
				
			}
		});
		btnCapLaiMK.setBackground(Color.ORANGE);
		btnCapLaiMK.setForeground(Color.BLACK);
		btnCapLaiMK.setBounds(301, 171, 157, 43);
		panel.add(btnCapLaiMK);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.setForeground(Color.GRAY);
		btnHuy.setBackground(Color.WHITE);
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHuy.setBounds(134, 171, 157, 43);
		
		panel.add(btnHuy);
	}
}
