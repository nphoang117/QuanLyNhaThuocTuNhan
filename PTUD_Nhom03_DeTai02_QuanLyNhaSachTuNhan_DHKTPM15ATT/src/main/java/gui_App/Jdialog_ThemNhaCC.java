/**
 * Đoàn Kiều Mỹ Ngọc -19446111 - nhóm 03
 * 
 *  mô tả lớp :cửa sổ thêm nhà cung cấp bên giao diện hàng hóa
 */
package gui_App;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.MyCombobox;
import controller.TaoMaTuDong;
import dao.NhaCungCapDao;
import dao.Regexdao;
import entity.NhaCungCap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Jdialog_ThemNhaCC extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTenNCC;
	private JTextField txtDiaChi;
	private JTextField txtSdt;
	private JTextField txtEmail;
	private TaoMaTuDong taoMaTuDong = new TaoMaTuDong();
	private NhaCungCapDao nhaCungCapDao = new NhaCungCapDao();
	private JButton btnOK;
	private Regexdao regex = new Regexdao();
	public static MyCombobox myComboboxNCC;

	/**
	 * Create the dialog.
	 */
	public Jdialog_ThemNhaCC() {

		super((java.awt.Frame) null, true);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				Jdialog_ThemNhaCC.this.getRootPane().setDefaultButton(btnOK);

			}
		});

		setTitle("Thêm Nhà cung cấp");
		setBounds(100, 100, 528, 333);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 494, 274);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblTenncc = new JLabel("Tên NCC:");
				lblTenncc.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTenncc.setBounds(10, 27, 88, 30);
				panel.add(lblTenncc);
			}
			{
				txtTenNCC = new JTextField();
				txtTenNCC.setBounds(108, 29, 354, 30);
				panel.add(txtTenNCC);
				txtTenNCC.setColumns(10);
			}
			{
				JLabel lblDiaChi = new JLabel("Địa chỉ:");
				lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDiaChi.setBounds(10, 68, 88, 30);
				panel.add(lblDiaChi);
			}
			{
				txtDiaChi = new JTextField();
				txtDiaChi.setBounds(108, 68, 354, 30);
				panel.add(txtDiaChi);
				txtDiaChi.setColumns(10);
			}
			{
				JLabel lblSdt = new JLabel("SĐT:");
				lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblSdt.setBounds(10, 109, 82, 30);
				panel.add(lblSdt);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblEmail.setBounds(10, 150, 82, 30);
				panel.add(lblEmail);
			}
			{
				txtSdt = new JTextField();
				txtSdt.setBounds(108, 109, 354, 30);
				panel.add(txtSdt);
				txtSdt.setColumns(10);
			}
			{
				txtEmail = new JTextField();
				txtEmail.setBounds(108, 150, 354, 30);
				panel.add(txtEmail);
				txtEmail.setColumns(10);
			}
			{
				btnOK = new JButton("OK");
				/**
				 * them ncc
				 */
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (kiemTra()) {
							String mancc = taoMaTuDong.taoMaNhaCungCap();
							String tenncc = txtTenNCC.getText();
							String diachi = txtDiaChi.getText();
							String sdt = txtSdt.getText();
							String email = txtEmail.getText();
							NhaCungCap ncc = new NhaCungCap(mancc, tenncc, diachi, sdt, email);
							
							myComboboxNCC = new MyCombobox(mancc, tenncc);

							Boolean resul = nhaCungCapDao.create(ncc);
							if (resul) {
								JOptionPane.showMessageDialog(Jdialog_ThemNhaCC.this, "Thêm nhà cung cấp  thành công");
							} else {
								JOptionPane.showMessageDialog(Jdialog_ThemNhaCC.this, "Thêm nhà cung cấp thất bại");
							}
							dispose();
						}

					}
				});
				btnOK.setBackground(Color.ORANGE);
				btnOK.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnOK.setBounds(324, 208, 138, 43);
				panel.add(btnOK);
			}
			{
				JButton btnHuy = new JButton("Hủy");
				btnHuy.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnHuy.setForeground(Color.LIGHT_GRAY);
				btnHuy.setBackground(Color.WHITE);
				btnHuy.setFont(new Font("Tahoma", Font.ITALIC, 16));
				btnHuy.setBounds(176, 210, 138, 43);
				panel.add(btnHuy);
			}
		}
	}

	private boolean kiemTra() {
		if (!regex.regexTenNCC(txtTenNCC))
			return false;
		if (!regex.regexDiaChi(txtDiaChi))
			return false;
		if (!regex.regexSDTNCC(txtSdt))
			return false;
		if (!regex.regexEmail(txtEmail))
			return false;
		return true;
	}

}
