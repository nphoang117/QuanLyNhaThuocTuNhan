/**
 * Đoàn Kiều Mỹ Ngọc -19446111 - nhóm 03
 * 
 *  mô tả lớp : cửa sổ thêm thể loại bên giao diện hàng hóa
 */
package gui_App;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.MyCombobox;
import controller.TaoMaTuDong;
import dao.TheLoaiDao;
import entity.KhachHang;
import entity.TheLoai;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Jdialog_ThemTheLoai_HangHoa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTenTL;
	private TaoMaTuDong taoMaTuDong = new TaoMaTuDong();
	private TheLoaiDao theLoaiDao = new TheLoaiDao();
	private JButton btnOK;

	public static MyCombobox myComboboxTL ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Jdialog_ThemTheLoai_HangHoa dialog = new Jdialog_ThemTheLoai_HangHoa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Jdialog_ThemTheLoai_HangHoa() {
		
		super((java.awt.Frame)null,true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Jdialog_ThemTheLoai_HangHoa.this.getRootPane().setDefaultButton(btnOK);
			}
		});
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		
		setTitle("Thêm Thể Loại ");
		setBounds(100, 100, 450, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin th\u1EC3 lo\u1EA1i", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 416, 180);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên thể loại:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 40, 96, 30);
		panel.add(lblNewLabel);
		
		txtTenTL = new JTextField();
		txtTenTL.setBounds(118, 42, 288, 30);
		panel.add(txtTenTL);
		txtTenTL.setColumns(10);
		
		 btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				String maTL= taoMaTuDong.taoMaTheLoai();
				String tenTL = txtTenTL.getText();
				myComboboxTL = new MyCombobox(maTL, tenTL);
				TheLoai theLoai = new TheLoai();
				theLoai.setMaLoai(maTL);
				theLoai.setTenTheLoai(tenTL);
				Boolean resul = theLoaiDao.create(theLoai);
				if(resul) {
					JOptionPane.showMessageDialog(Jdialog_ThemTheLoai_HangHoa.this, "Thêm Thể loại thành công");
				}else {
					JOptionPane.showMessageDialog(Jdialog_ThemTheLoai_HangHoa.this, "Thêm Thể loại thất bại");
				}
				dispose();
				
				
				
			}
		});
		btnOK.setBackground(Color.ORANGE);
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOK.setBounds(270, 109, 136, 48);
		panel.add(btnOK);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHuy.setForeground(Color.LIGHT_GRAY);
		btnHuy.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnHuy.setBackground(Color.WHITE);
		btnHuy.setBounds(124, 111, 136, 48);
		panel.add(btnHuy);
	}
}
