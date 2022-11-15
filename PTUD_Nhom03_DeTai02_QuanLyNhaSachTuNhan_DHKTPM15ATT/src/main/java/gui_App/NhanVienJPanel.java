/**
 * Đoàn Kiều Mỹ Ngọc - 19446111 - Nhóm 03
 * 
 * Mô tả lớp : thực hiện các chức năng cơ bản Thêm, Xóa, Sửa ,Tìm kiếm các Nhân Viên ,
 * -  khi thêm và xóa một nhân viên thì cũng đồng thời thêm và xóa một tài khoản của nhân viên đó
 */
package gui_App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.TaoMaTuDong;
import dao.NhanVienDao;
import dao.Regexdao;
import dao.TaiKhoanDao;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVienJPanel extends JPanel implements MouseInputListener, ActionListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JTextField txtSearch_NhanVien;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtSdt;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTable table_NV;
	private JDateChooser dateSinh = new JDateChooser();
	private JDateChooser dateLamViec = new JDateChooser();
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JComboBox cmbChucVu = new JComboBox();
	private JRadioButton rdbNu;
	private JRadioButton rdbNam;
	private ButtonGroup rdbgr = new ButtonGroup();
	private TaoMaTuDong taoma = new TaoMaTuDong();
	private TaiKhoanDao taiKhoanDao = new TaiKhoanDao();

	private String[] listColumn = { "STT", "Mã Nhân viên", "Tên Nhân viên", "ngày Sinh", "Ngày đầu đi làm",
			"Số điện thoại", "Email", "Giới Tính", "địa Chỉ", "Chức Vụ" };

	private DefaultTableModel model_NV;
	// dao
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private Date dateS;
	private Date dateL;
	private Regexdao regex = new Regexdao();

	/**
	 * Create the panel.
	 */
	public NhanVienJPanel() {
		setLayout(null);
		setBounds(0, 0, 1502, 985);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1502, 1112);
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

		txtSearch_NhanVien = new JTextField();

		txtSearch_NhanVien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timKiemNhanVien();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				timKiemNhanVien();
			}
		});
		txtSearch_NhanVien.setBounds(10, 26, 330, 36);
		pnlTimKiem.add(txtSearch_NhanVien);
		txtSearch_NhanVien.setColumns(10);

		JPanel pnlThemNhanVien = new JPanel();
		pnlThemNhanVien.setBorder(new TitledBorder(null, "Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlThemNhanVien.setBackground(Color.WHITE);
		pnlThemNhanVien.setBounds(0, 89, 1502, 316);
		panel.add(pnlThemNhanVien);
		pnlThemNhanVien.setLayout(null);

		JLabel lblMaNV = new JLabel("Mã Nhân Viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaNV.setBounds(32, 37, 108, 33);
		pnlThemNhanVien.add(lblMaNV);

		JLabel lblTenNV = new JLabel("Tên Nhân Viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenNV.setBounds(32, 88, 142, 33);
		pnlThemNhanVien.add(lblTenNV);

		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSdt.setBounds(794, 182, 108, 33);
		pnlThemNhanVien.add(lblSdt);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(32, 227, 108, 33);
		pnlThemNhanVien.add(lblEmail);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiaChi.setBounds(32, 268, 108, 33);
		pnlThemNhanVien.add(lblDiaChi);

		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setBounds(201, 36, 977, 34);
		pnlThemNhanVien.add(txtMaNV);
		txtMaNV.setColumns(10);

		txtTenNV = new JTextField();
		txtTenNV.setBounds(201, 83, 576, 33);
		pnlThemNhanVien.add(txtTenNV);
		txtTenNV.setColumns(10);

		txtSdt = new JTextField();
		txtSdt.setBounds(923, 184, 255, 33);
		pnlThemNhanVien.add(txtSdt);
		txtSdt.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(201, 227, 977, 33);
		pnlThemNhanVien.add(txtEmail);
		txtEmail.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(201, 270, 977, 33);
		pnlThemNhanVien.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		JLabel lblNewLabel = new JLabel("Ngày Sinh:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(32, 134, 95, 37);
		pnlThemNhanVien.add(lblNewLabel);

		// dateSinh = new JDateChooser();
		dateSinh.setDateFormatString("dd/MM/yyyy");
		dateSinh.setBounds(199, 137, 578, 33);
		pnlThemNhanVien.add(dateSinh);

		JLabel lblNewLabel_2 = new JLabel("Giới Tính:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(794, 92, 76, 24);
		pnlThemNhanVien.add(lblNewLabel_2);

		rdbNu = new JRadioButton("Nữ");
		rdbNu.setBounds(923, 94, 69, 25);
		pnlThemNhanVien.add(rdbNu);

		rdbNam = new JRadioButton("Nam");
		rdbNam.setBounds(1009, 94, 69, 25);
		pnlThemNhanVien.add(rdbNam);

		rdbgr.add(rdbNam);
		rdbgr.add(rdbNu);

		JLabel lblChcV = new JLabel("Chức Vụ:");
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChcV.setBounds(794, 145, 95, 26);
		pnlThemNhanVien.add(lblChcV);

		cmbChucVu.setModel(new DefaultComboBoxModel(new String[] { "quản lý", "bán hàng" }));
		cmbChucVu.setBounds(923, 138, 255, 33);
		pnlThemNhanVien.add(cmbChucVu);

		JLabel lblNewLabel_1 = new JLabel("Ngày Bắt Đầu Làm :");
		lblNewLabel_1.setBounds(32, 185, 162, 26);
		pnlThemNhanVien.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		dateLamViec.setBounds(199, 182, 578, 33);
		pnlThemNhanVien.add(dateLamViec);

		// dateLamViec = new JDateChooser();
		dateLamViec.setDateFormatString("dd/MM/yyyy");

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(1221, 24, 255, 277);
		pnlThemNhanVien.add(panel_1);
		panel_1.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(55, 27, 141, 41);
		panel_1.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(55, 95, 141, 41);
		panel_1.add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(55, 161, 141, 41);
		panel_1.add(btnSua);

		JButton btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setBounds(55, 213, 141, 41);
		panel_1.add(btnXoaRong);
		btnXoaRong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				xoaRongTextfieldSach();
			}
		});

		JPanel pnlDanhSach = new JPanel();
		pnlDanhSach.setBackground(Color.WHITE);
		pnlDanhSach.setBorder(null);
		pnlDanhSach.setBounds(0, 406, 1502, 591);
		panel.add(pnlDanhSach);
		pnlDanhSach.setLayout(null);

		JPanel pnlView_NhanVien = new JPanel();
		pnlView_NhanVien.setBorder(new TitledBorder(null, "Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlView_NhanVien.setBackground(Color.WHITE);
		pnlView_NhanVien.setBounds(0, 0, 1502, 578);
		pnlDanhSach.add(pnlView_NhanVien);

		pnlView_NhanVien.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 1482, 542);
		pnlView_NhanVien.add(scrollPane);

		model_NV = new DefaultTableModel(listColumn, 0);

		table_NV = new JTable(model_NV) {

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		;

		scrollPane.setViewportView(table_NV);

		table_NV.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table_NV.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table_NV.setRowHeight(50);
		table_NV.validate();
		table_NV.repaint();

		// event
		setDataToTableNhanVien();
		table_NV.addMouseListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThem.addActionListener(this);

	}

	/**
	 * lấy dữ liệu lên bảng nhân viên
	 */
	public void setDataToTableNhanVien() {
		List<NhanVien> listIten = nhanVienDao.getList();
		int columns = listColumn.length;

		Object[] obj;
		int rows = listIten.size();

		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				NhanVien nv = listIten.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = nv.getMaNhanVien();
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(nv.getNgaySinh());
				obj[4] = dateFormat.format(nv.getNgayDauLamViec());
				obj[5] = nv.getSdt();
				obj[6] = nv.getEmail();
				obj[7] = nv.isGioiTinh() == true ? "Nam" : "Nữ";
				obj[8] = nv.getDiaChi();
				obj[9] = nv.getChucVu();

				model_NV.addRow(obj);
			}
		}
	}

	/**
	 * sự kiện click chuột vào bảng để hiện thông tin lên form
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table_NV)) {
			int row = table_NV.getSelectedRow();
			txtMaNV.setText(table_NV.getValueAt(row, 1).toString());
			txtTenNV.setText(table_NV.getValueAt(row, 2).toString());

			try {
				dateS = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(table_NV.getValueAt(row, 3).toString());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			dateSinh.setDate(dateS);

			try {
				dateL = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(table_NV.getValueAt(row, 4).toString());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			dateLamViec.setDate(dateL);

			txtSdt.setText(table_NV.getValueAt(row, 5).toString());
			txtEmail.setText(table_NV.getValueAt(row, 6).toString());
			if (table_NV.getValueAt(row, 7).toString() == "Nữ") {
				rdbNu.setSelected(true);
			} else
				rdbNam.setSelected(true);
			;

			txtDiaChi.setText(table_NV.getValueAt(row, 8).toString());
			cmbChucVu.setSelectedItem(table_NV.getValueAt(row, 9).toString());

		}

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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * sự kiện cho các nút thêm , xóa , sữa , xóa rỗng
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (kiemTra()) {
				NhanVien nv = reverSpFromTextFileNhanVien();
				Object[] obj = null;
				if (nhanVienDao.create(nv)) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					int maxrow = table_NV.getRowCount();
					int col = listColumn.length;
					obj = new Object[col];
					obj[0] = (maxrow + 1);
					obj[1] = taoma.taoMaNhanVien1();
					obj[2] = txtTenNV.getText();

					obj[3] = dateFormat.format(dateSinh.getDate());
					obj[4] = dateFormat.format(dateLamViec.getDate());

					obj[5] = txtSdt.getText();
					obj[6] = txtEmail.getText();

					String gt;
					if (rdbNu.isSelected()) {
						gt = "Nữ";
					} else
						gt = "Nam";

					obj[7] = gt;
					obj[8] = txtDiaChi.getText();
					obj[9] = cmbChucVu.getSelectedItem();

					model_NV.addRow(obj);
					xoaRongTextfieldSach();
					themTK(nv);

				} else {
					JOptionPane.showMessageDialog(this, "MÃ TRÙNG");
				}
			}

		} else if (o.equals(btnXoa)) {

			int row = table_NV.getSelectedRow();

			if (row >= 0) {
				String manv = (String) table_NV.getValueAt(row, 1);
				System.out.println(manv);
				int answer = JOptionPane.showConfirmDialog(NhanVienJPanel.this, "Bạn có thật sự muốn xóa nhân viên này",
						"Thông báo", JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.NO_OPTION) {
					remove(btnXoa);
				} else if (answer == JOptionPane.YES_OPTION) {
					if (taiKhoanDao.delete(manv) && nhanVienDao.delete(manv)) {
						model_NV.removeRow(row);
					}
				}

			} else {
				JOptionPane.showMessageDialog(this, "Chọn hàng cần xóa");
			}
		} else if (o.equals(btnSua)) {
			if (kiemTra()) {
				int row = table_NV.getSelectedRow();
				if (row > 0) {
					NhanVien nv = reverSpFromTextFileNhanVien1();
					if (nhanVienDao.update(nv)) {
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

						model_NV.setValueAt(txtMaNV.getText(), row, 1);
						model_NV.setValueAt(txtTenNV.getText(), row, 2);
						model_NV.setValueAt(dateFormat.format(dateSinh.getDate()), row, 3);
						model_NV.setValueAt(dateFormat.format(dateLamViec.getDate()), row, 4);
						model_NV.setValueAt(txtSdt.getText(), row, 5);
						model_NV.setValueAt(txtEmail.getText(), row, 6);

						String gt;
						if (rdbNu.isSelected() == true) {
							gt = "Nữ";
						} else
							gt = "Nam";

						model_NV.setValueAt(gt, row, 7);

						model_NV.setValueAt(txtDiaChi.getText(), row, 8);
						model_NV.setValueAt(cmbChucVu.getSelectedItem().toString(), row, 9);
					}

				} else {
					JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
				}
			}
		}

	}

	/**
	 * kiểm tra biểu thức chính quy
	 * 
	 * @return
	 */
	private boolean kiemTra() {
		if (!regex.regexTen(txtTenNV))
			return false;
		if (!regex.regexDiaChi(txtDiaChi))
			return false;
		if (!regex.regexSDT(txtSdt))
			return false;
		if (!regex.regexEmail(txtEmail))
			return false;
		return true;
	}

	/**
	 * thêm một tài khoản
	 * 
	 * @param nv
	 */
	public void themTK(NhanVien nv) {

		TaiKhoan tk = new TaiKhoan();
		tk.setMaNhanVien(nv);
		String tendn = name(nv.getTenNhanVien().toString());

		tk.setTenDangNhap(tendn.toString());
		tk.setMatKhau("123456");

		if (taiKhoanDao.taoTaiKhoan(tk)) {
			JOptionPane.showMessageDialog(this, "bạn đã tạo thêm 1 tài khoản");
		} else
			JOptionPane.showMessageDialog(this, "Không thể tạo tài khoản này");

	}

	/**
	 * xóa rỗng các ô nhập liệu
	 */
	private void xoaRongTextfieldSach() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtSdt.setText("");

		txtSearch_NhanVien.setText("");
		dateSinh.setDate(new Date());

		dateLamViec.setDate(new Date());

	}

	/**
	 * lấy thông tin trên form nhập liệu
	 * 
	 * @return
	 */
	public NhanVien reverSpFromTextFileNhanVien() {

		NhanVien nv = new NhanVien();
		nv.setMaNhanVien(taoma.taoMaNhanVien());
		nv.setTenNhanVien(txtTenNV.getText().toString());

		nv.setNgaySinh(convertUtilToSql(dateSinh.getDate()));

		nv.setNgayDauLamViec(convertUtilToSql(dateLamViec.getDate()));

		nv.setSdt(txtSdt.getText().toString());
		nv.setEmail(txtEmail.getText().toString());

		boolean gioiTinh;
		if (rdbNu.isSelected()) {
			gioiTinh = false;
		} else
			gioiTinh = true;

		nv.setGioiTinh(gioiTinh);

		nv.setDiaChi(txtDiaChi.getText().toString());
		nv.setChucVu(cmbChucVu.getSelectedItem().toString());

		return nv;
	}

	/**
	 * lấy thông tin trên form nhập liệu
	 * 
	 * @return
	 */
	public NhanVien reverSpFromTextFileNhanVien1() {

		NhanVien nv = new NhanVien();
		nv.setMaNhanVien(txtMaNV.getText().toString());
		nv.setTenNhanVien(txtTenNV.getText().toString());

		nv.setNgaySinh(convertUtilToSql(dateSinh.getDate()));

		nv.setNgayDauLamViec(convertUtilToSql(dateLamViec.getDate()));

		nv.setSdt(txtSdt.getText().toString());
		nv.setEmail(txtEmail.getText().toString());

		boolean gioiTinh;
		if (rdbNu.isSelected()) {
			gioiTinh = false;
		} else
			gioiTinh = true;

		nv.setGioiTinh(gioiTinh);

		nv.setDiaChi(txtDiaChi.getText().toString());
		nv.setChucVu(cmbChucVu.getSelectedItem().toString());

		return nv;
	}

	/**
	 * chuyển đỗi date bên java.util sang date java.sql
	 * 
	 * @param uDate
	 * @return
	 */
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	/**
	 * tìm kiếm nhân viên theo tên
	 */
	public void timKiemNhanVien() {
		model_NV.setRowCount(0);
		List<NhanVien> list = nhanVienDao.timKiemTheoTen(txtSearch_NhanVien);
		int columns = listColumn.length;
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				NhanVien nv = list.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = nv.getMaNhanVien();
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(nv.getNgaySinh());
				obj[4] = dateFormat.format(nv.getNgayDauLamViec());
				obj[5] = nv.getSdt();
				obj[6] = nv.getEmail();
				obj[7] = nv.isGioiTinh() == true ? "Nam" : "Nữ";
				obj[8] = nv.getDiaChi();
				obj[9] = nv.getChucVu();

				model_NV.addRow(obj);
			}
		}

	}

	public static String name(String ten) {
		String tenDN = taotendn(covertToString(ten).toLowerCase());
		return tenDN;

	}

	/**
	 * tạo tên đăng nhập trong tài khoản
	 * 
	 * @param ten
	 * @return
	 */
	public static String taotendn(String ten) {
		String tenDN = "";
		String[] parts = ten.split(" ");
		for (int i = 0; i < parts.length; i++) {
			if (i == (parts.length - 1)) {
				tenDN = tenDN + parts[i];
				return tenDN;
			} else if (i < parts.length) {
				tenDN = tenDN + parts[i].charAt(0);
			}
		}
		return tenDN;

	}

	/**
	 * chuyển chữ có dấu thành không dấu và bỏ khoảng trắng
	 * 
	 * @param value
	 * @return
	 */
	public static String covertToString(String value) {
		try {
			String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

			return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
