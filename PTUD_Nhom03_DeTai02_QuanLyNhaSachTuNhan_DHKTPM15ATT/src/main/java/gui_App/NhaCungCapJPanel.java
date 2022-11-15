/**
 * Nguyễn Phi Hoàng - 19443211 - Nhóm 03
 * 
 * mô tả lớp : Thực hiện các chức năng cơ bản Thêm xóa sữa tìm kiếm cho nhà cung cấp
 */
package gui_App;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.TaoMaTuDong;
import dao.NhaCungCapDao;
import dao.Regexdao;
import entity.NhaCungCap;
import entity.Sach;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class NhaCungCapJPanel extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch_NhaCungCap;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtSdt;
	private JTable table_NCC;
	private DefaultTableModel modelNhaCC;
	private String[] listColumn = { "STT", "Mã NCC", "Tên NCC", "Địa Chỉ", "Số điện thoại", "Email" };

	/**
	 * khai bao DAO
	 */
	private NhaCungCapDao listNCC = new NhaCungCapDao();
	private TaoMaTuDong taoma = new TaoMaTuDong();
	private NhaCungCapDao nhaCungCapDao = new NhaCungCapDao();
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaRong;
	private Regexdao regex = new Regexdao();

	/**
	 * Create the panel.
	 */
	public NhaCungCapJPanel() {
		setLayout(null);
		setBounds(0, 0, 1502, 985);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1502, 1123);
		panel.setBackground(new Color(240, 240, 240));
		add(panel);
		panel.setLayout(null);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(
				new TitledBorder(null, "T\u00ECm Ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBackground(Color.ORANGE);
		pnlTimKiem.setBounds(0, 0, 1502, 89);
		panel.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		txtSearch_NhaCungCap = new JTextField();

		/**
		 * Chức năng tìm kiếm theo tên nhà cung cấp
		 */
		txtSearch_NhaCungCap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timkiem();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				timkiem();
			}
		});
		txtSearch_NhaCungCap.setBounds(10, 26, 330, 36);
		pnlTimKiem.add(txtSearch_NhaCungCap);
		txtSearch_NhaCungCap.setColumns(10);

		JPanel pnlThemNhaCC = new JPanel();
		pnlThemNhaCC.setBorder(new TitledBorder(null, "Th\u00F4ng tin nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlThemNhaCC.setBackground(Color.WHITE);
		pnlThemNhaCC.setBounds(0, 89, 1502, 316);
		panel.add(pnlThemNhaCC);
		pnlThemNhaCC.setLayout(null);

		JLabel lblMaNhaCC = new JLabel("Mã NCC :");
		lblMaNhaCC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaNhaCC.setBounds(37, 42, 108, 33);
		pnlThemNhaCC.add(lblMaNhaCC);

		JLabel lblTenNCC = new JLabel("Tên NCC :");
		lblTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenNCC.setBounds(37, 97, 108, 33);
		pnlThemNhaCC.add(lblTenNCC);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(37, 154, 94, 33);
		pnlThemNhaCC.add(lblEmail);

		JLabel lblDiaChi = new JLabel("Địa Chỉ :");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiaChi.setBounds(37, 198, 94, 33);
		pnlThemNhaCC.add(lblDiaChi);

		JLabel lblSdt = new JLabel("Số Điện Thoại :");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSdt.setBounds(37, 242, 113, 33);
		pnlThemNhaCC.add(lblSdt);

		txtMaNCC = new JTextField();
		txtMaNCC.setEditable(false);
		txtMaNCC.setBounds(172, 43, 973, 33);
		pnlThemNhaCC.add(txtMaNCC);
		txtMaNCC.setColumns(10);

		txtTenNCC = new JTextField();
		txtTenNCC.setBounds(172, 97, 973, 33);
		pnlThemNhaCC.add(txtTenNCC);
		txtTenNCC.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(172, 154, 973, 33);
		pnlThemNhaCC.add(txtEmail);
		txtEmail.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(172, 198, 973, 33);
		pnlThemNhaCC.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		txtSdt = new JTextField();
		txtSdt.setBounds(172, 242, 973, 33);
		pnlThemNhaCC.add(txtSdt);
		txtSdt.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(1229, 11, 251, 294);
		pnlThemNhaCC.add(panel_2);
		panel_2.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(57, 29, 143, 50);
		panel_2.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(57, 95, 143, 50);
		panel_2.add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(57, 156, 143, 50);
		panel_2.add(btnSua);

		btnXoaRong = new JButton("Xóa Rỗng");

		btnXoaRong.setBounds(59, 217, 141, 50);
		panel_2.add(btnXoaRong);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 406, 1502, 572);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel pnlView_NhaCungCap = new JPanel();
		pnlView_NhaCungCap.setBorder(new TitledBorder(null, "Danh s\u00E1ch nh\u00E0 cung c\u1EA5p",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlView_NhaCungCap.setBackground(Color.WHITE);
		pnlView_NhaCungCap.setBounds(0, 0, 1492, 559);
		panel_1.add(pnlView_NhaCungCap);
		pnlView_NhaCungCap.setLayout(null);
		/**
		 * table
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 1472, 522);
		pnlView_NhaCungCap.add(scrollPane);

		modelNhaCC = new DefaultTableModel(listColumn, 0);
		table_NCC = new JTable(modelNhaCC) {

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		;
		scrollPane.setViewportView(table_NCC);

		table_NCC.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table_NCC.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table_NCC.setRowHeight(50);
		table_NCC.validate();
		table_NCC.repaint();

		table_NCC.addMouseListener(this);
		setDataTableNhaCC();
		// set Event
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);

		btnThem.addActionListener(this);
		btnXoaRong.addActionListener(this);
	}

	/**
	 * lấy dữ liệu lên bảng nhà cung câos
	 */
	public void setDataTableNhaCC() {
		List<NhaCungCap> listItem = nhaCungCapDao.getList();
		int columns = listColumn.length;
		Object[] obj;
		int rows = listItem.size();

		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				NhaCungCap ncc = listItem.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = ncc.getMaNCC();
				obj[2] = ncc.getTenNCC();
				obj[3] = ncc.getDiaChi();
				obj[4] = ncc.getSdt();
				obj[5] = ncc.getEmail();

				modelNhaCC.addRow(obj);
			}
		}
	}

	/**
	 * sự kiện các nút tác vụ thêm xóa sữa
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (kiemTra()) {
				NhaCungCap s = reverSpFromTextFileNCC();
				Object[] obj = null;
				if (listNCC.create(s)) {
					int maxrow = table_NCC.getRowCount();

					int columns = listColumn.length;
					obj = new Object[columns];
					obj[0] = (maxrow + 1);
					obj[1] = taoma.taoMaNhaCungCap1();
					obj[2] = txtTenNCC.getText();
					obj[3] = txtDiaChi.getText();
					obj[4] = txtSdt.getText();
					obj[5] = txtEmail.getText();

					modelNhaCC.addRow(obj);
					xoaRongTextfieldKhachHang();
				} else {
					JOptionPane.showMessageDialog(this, "MÃ TRÙNG");
				}
			}

		} else if (o.equals(btnXoa)) {
			int row = table_NCC.getSelectedRow();
			if (row >= 0) {
				String maNCC = (String) table_NCC.getValueAt(row, 1);
				if (listNCC.delete(maNCC)) {
					modelNhaCC.removeRow(row);
				}
			}
		} else if (o.equals(btnSua)) {
			if (kiemTra()) {
				SuaNCC();
			}

		} else if (o.equals(btnXoaRong)) {
			xoaRongTextfieldKhachHang();
		}

	}

	/**
	 * kiểm tra biểu thức chính quy
	 * @return
	 */
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

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table_NCC)) {
			int row = table_NCC.getSelectedRow();
			txtMaNCC.setText(table_NCC.getValueAt(row, 1).toString());
			txtDiaChi.setText(table_NCC.getValueAt(row, 3).toString());
			txtSdt.setText(table_NCC.getValueAt(row, 4).toString());
			txtTenNCC.setText(table_NCC.getValueAt(row, 2).toString());
			txtEmail.setText(table_NCC.getValueAt(row, 5).toString());

		}

	}

	/**
	 * lấy thông tin trên form nhập liệu 
	 * @return
	 */
	private NhaCungCap reverSpFromTextFileNCC() {
		String maNCC = taoma.taoMaNhaCungCap();
		String diaChi = txtDiaChi.getText();
		String dienThoai = txtSdt.getText();
		String hoTen = txtTenNCC.getText();
		String email = txtEmail.getText();
		NhaCungCap ncc = new NhaCungCap(maNCC, hoTen, diaChi, dienThoai, email);

		return ncc;
	}

	/**
	 * lấy thông tin trên form nhập liệu 
	 * @return
	 */
	private void xoaRongTextfieldKhachHang() {
		txtMaNCC.setText("");
		txtDiaChi.setText("");
		txtSdt.setText("");
		txtTenNCC.setText("");
		txtEmail.setText("");

		txtMaNCC.requestFocus();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * tìm kiếm nhà cung cấp theo tên
	 */
	private void timkiem() {
		modelNhaCC.setRowCount(0);
		List<NhaCungCap> list = nhaCungCapDao.getListNcc(txtSearch_NhaCungCap);
		int columns = listColumn.length;
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				NhaCungCap ncc = list.get(i);
				obj = new Object[columns];
				obj[0] = i + 1;
				obj[1] = ncc.getMaNCC();
				obj[2] = ncc.getTenNCC();
				obj[3] = ncc.getDiaChi();
				obj[4] = ncc.getSdt();
				obj[5] = ncc.getEmail();
				modelNhaCC.addRow(obj);
			}
		}

	}

	public void SuaNCC() {
		int row = table_NCC.getSelectedRow();
		if (row > 0) {
			String maNCC = txtMaNCC.getText();
			String tenNCC = txtTenNCC.getText();
			String diaChi = txtDiaChi.getText();
			String sdt = txtSdt.getText();
			String email = txtEmail.getText();
			NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, sdt, email);
			if (nhaCungCapDao.suaNCC(ncc)) {
				JOptionPane.showMessageDialog(this, "Sua thanh cong");

				modelNhaCC.setValueAt(txtMaNCC.getText(), row, 1);
				modelNhaCC.setValueAt(txtSdt.getText(), row, 4);
				modelNhaCC.setValueAt(txtDiaChi.getText(), row, 3);
				modelNhaCC.setValueAt(txtTenNCC.getText(), row, 2);
				modelNhaCC.setValueAt(txtEmail.getText(), row, 5);
			}
		} else
			JOptionPane.showMessageDialog(this, "Chọn hàng cần sửa");
	}
}
