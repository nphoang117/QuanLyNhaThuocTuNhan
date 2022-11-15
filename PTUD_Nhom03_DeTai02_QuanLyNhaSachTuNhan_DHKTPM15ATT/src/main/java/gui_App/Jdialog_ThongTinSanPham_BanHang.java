/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03 
 * 
 * Mô tả lớp : cửa sổ nhập số lượng sản phẩm để thêm vào bảng chi tiết hóa đơn bên giao diện bán hàng
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
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.border.TitledBorder;

import entity.Sach;

import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Jdialog_ThongTinSanPham_BanHang extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSoLuong;
	private JButton btnThemSanPham;
	 private static final long serialVersionUID = 1L;
	 
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			Jdialog_ThongTinSanPham_BanHang dialog = new Jdialog_ThongTinSanPham_BanHang();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	 
	 
	public Jdialog_ThongTinSanPham_BanHang() {
		super((java.awt.Frame)null,true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				POPJPanel.huy = true;
				txtSoLuong.setText( POPJPanel.soLuongMua);
				Jdialog_ThongTinSanPham_BanHang.this.getRootPane().setDefaultButton(btnThemSanPham);
			}
			@Override
			public void windowClosed(WindowEvent e) {
				POPJPanel.huy = true;
			}
		});
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		
		setBounds(100, 100, 450, 214);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng Tin S\u1ED1 L\u01B0\u1EE3ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 416, 155);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Số Lượng:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 46, 98, 31);
		panel.add(lblNewLabel);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(118, 46, 274, 34);
		panel.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		 btnThemSanPham = new JButton("Đồng ý");
		btnThemSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				POPJPanel.soLuongMua = txtSoLuong.getText();
				POPJPanel.huy= false;
		
//				System.out.println("sl:"+ POPJPanel.soLuongMua);
				dispose();
				
//				POPJPanel.soLuongMua = txtSoLuong.getText();
				
			}
		});
		btnThemSanPham.setToolTipText("Thêm Sản Phẩm vào danh sách mua hàng");
		btnThemSanPham.setBackground(Color.ORANGE);
		btnThemSanPham.setForeground(Color.BLACK);
		btnThemSanPham.setBounds(292, 106, 100, 38);
		panel.add(btnThemSanPham);
		
		JButton btnHuySanPham = new JButton("Hủy");
		btnHuySanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				POPJPanel.huy= true;
				dispose();
			}
		});
		btnHuySanPham.setToolTipText("Hủy");
		btnHuySanPham.setBackground(Color.ORANGE);
		btnHuySanPham.setBounds(182, 106, 100, 38);
		panel.add(btnHuySanPham);
	}
	




}
