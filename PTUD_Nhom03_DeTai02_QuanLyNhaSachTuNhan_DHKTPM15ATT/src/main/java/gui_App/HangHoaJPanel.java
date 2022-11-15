/**
 * Đoàn Kiều Mỹ Ngọc - 19446111 - Nhóm 03
 * 
 * Mô tả lớp : thực hiện các chức năng cơ bản Thêm Xóa Sửa Tìm kiếm các sản phẩm hàng hóa 
 */
package gui_App;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.MyCombobox;

import controller.TaoMaTuDong;
//import dao.KhachHangDao;
import dao.NhaCungCapDao;
import dao.Regexdao;
import dao.SachDao;
import dao.TheLoaiDao;
import entity.KhachHang;
import entity.NhaCungCap;
//import dao.TheLoaiDao;
import entity.Sach;
import entity.TheLoai;
import service.Sach_service;

import java.awt.SystemColor;
import java.awt.FlowLayout;
import com.toedter.calendar.JYearChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class HangHoaJPanel extends JPanel implements ActionListener, MouseListener {
	private JTextField textFieldMaSach;
	private JTextField textFieldTenSach;
	private JTextField textFieldSoLuong;
	private JTextField textFieldDonGia;
	private JTextField txtSearch_Sach;
	private DefaultComboBoxModel<Object> cmbModel = new DefaultComboBoxModel<>();

	private DefaultComboBoxModel<Object> cmbModelTL = new DefaultComboBoxModel<>();
	private TaoMaTuDong taoMa = new TaoMaTuDong();
	private JTable tbl_Sach;
	private JComboBox cbmTheLoai;
	private JComboBox cmbNCC;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private SachDao listSach = new SachDao();
	private JYearChooser yearSX;
	private JYearChooser yearXB;
	private NhaCungCapDao NCCDao = new NhaCungCapDao();
	private TheLoaiDao TLDao = new TheLoaiDao();
	private DefaultTableModel modelSach;
	private String[] columnNames = { "STT", "Mã Sách", "Tên Sách", "Thể Loại", "Đơn Giá", "Năm Xuất bản",
			"Năm sản xuất", "Nhà cung cấp", "Tác giả", "số Lượng" };
	private TableRowSorter<TableModel> rowSorter = null;
	private Sach_service sach_service = new Sach_service();
	private JTextField textFieldTacGia;
	private Regexdao regex = new Regexdao();

	/**
	 * Create the panel.
	 */
	public HangHoaJPanel() {

		setLayout(null);
		setBounds(0, 0, 1502, 985);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1502, 1123);
		panel.setBackground(new Color(240, 240, 240));
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(0, 0, 1502, 89);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtSearch_Sach = new JTextField();
		txtSearch_Sach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timKiemSach();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				timKiemSach();
			}
		});
		txtSearch_Sach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearch_Sach.setBounds(10, 26, 330, 36);
		panel_1.add(txtSearch_Sach);
		txtSearch_Sach.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 89, 1502, 332);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblMaSach = new JLabel("Mã Sách");
		lblMaSach.setBounds(51, 28, 108, 33);
		panel_2.add(lblMaSach);
		lblMaSach.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textFieldMaSach = new JTextField();

		textFieldMaSach.setEditable(false);// ko thể sửa lại mã

		textFieldMaSach.setBounds(186, 28, 970, 33);
		panel_2.add(textFieldMaSach);
		textFieldMaSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldMaSach.setColumns(10);

		JLabel lblTenSach = new JLabel("Tên Sách");
		lblTenSach.setBounds(51, 84, 108, 33);
		panel_2.add(lblTenSach);
		lblTenSach.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textFieldTenSach = new JTextField();
		textFieldTenSach.setBounds(186, 84, 385, 34);
		panel_2.add(textFieldTenSach);
		textFieldTenSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldTenSach.setColumns(10);

		JLabel lblSoLuong = new JLabel("Số Lượng");
		lblSoLuong.setBounds(51, 143, 108, 33);
		panel_2.add(lblSoLuong);
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblDonGia = new JLabel("Đơn Gía");
		lblDonGia.setBounds(51, 200, 59, 33);
		panel_2.add(lblDonGia);
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblTheLoai = new JLabel("Thể Loại");
		lblTheLoai.setBounds(51, 257, 74, 33);
		panel_2.add(lblTheLoai);
		lblTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textFieldSoLuong = new JTextField();
		textFieldSoLuong.setBounds(186, 143, 385, 33);
		panel_2.add(textFieldSoLuong);
		textFieldSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldSoLuong.setColumns(10);

		textFieldDonGia = new JTextField();
		textFieldDonGia.setBounds(186, 200, 385, 33);
		panel_2.add(textFieldDonGia);
		textFieldDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDonGia.setColumns(10);

		JLabel lblNmXutBn = new JLabel("Năm Xuất Bản");
		lblNmXutBn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNmXutBn.setBounds(617, 84, 108, 33);
		panel_2.add(lblNmXutBn);

		yearXB = new JYearChooser();
		yearXB.getSpinner().setBounds(0, 0, 386, 34);
		yearXB.setBounds(770, 83, 386, 34);
		panel_2.add(yearXB);
		yearXB.setLayout(null);

		yearSX = new JYearChooser();
		yearSX.getSpinner().setBounds(0, 0, 386, 34);
		yearSX.setBounds(770, 143, 386, 33);
		panel_2.add(yearSX);
		yearSX.setLayout(null);

		JLabel lblNmSnXut = new JLabel("Năm Sản Xuất ");
		lblNmSnXut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNmSnXut.setBounds(617, 143, 108, 33);
		panel_2.add(lblNmSnXut);

		JLabel lblNhCungCp = new JLabel("Nhà Cung Cấp");
		lblNhCungCp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhCungCp.setBounds(617, 257, 108, 33);
		panel_2.add(lblNhCungCp);

		cbmTheLoai = new JComboBox();
		cbmTheLoai.setBounds(186, 257, 328, 33);
		panel_2.add(cbmTheLoai);

		cmbNCC = new JComboBox();
		cmbNCC.setBounds(770, 257, 329, 33);
		panel_2.add(cmbNCC);

		JLabel lblTcGi = new JLabel("Tác Giả");
		lblTcGi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTcGi.setBounds(617, 200, 74, 33);
		panel_2.add(lblTcGi);

		textFieldTacGia = new JTextField();
		textFieldTacGia.setBounds(769, 200, 387, 33);
		panel_2.add(textFieldTacGia);
		textFieldTacGia.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(
				new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(1236, 11, 241, 301);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(38, 30, 163, 52);
		panel_4.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(38, 93, 163, 52);
		panel_4.add(btnXoa);
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnSua = new JButton("Sửa");
		btnSua.setBounds(38, 156, 163, 52);
		panel_4.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JButton btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRongTextfieldSach();
			}
		});
		btnXoaRong.setBounds(38, 219, 163, 52);
		panel_4.add(btnXoaRong);

		JButton btnThemTheLoai = new JButton("");
		btnThemTheLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Jdialog_ThemTheLoai_HangHoa jdl_tl = new Jdialog_ThemTheLoai_HangHoa();
				jdl_tl.setLocationRelativeTo(null);
				jdl_tl.setVisible(true);

				setDataComboboxTheLoai();
				cmbModelTL.setSelectedItem(Jdialog_ThemTheLoai_HangHoa.myComboboxTL);

			}
		});
		btnThemTheLoai.setBackground(Color.ORANGE);
		btnThemTheLoai.setIcon(new ImageIcon(HangHoaJPanel.class.getResource("/img/icons8_plus_math_26px.png")));
		btnThemTheLoai.setBounds(524, 257, 47, 33);
		panel_2.add(btnThemTheLoai);

		JButton btnThemNhaCC = new JButton("");

		btnThemNhaCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Jdialog_ThemNhaCC jdl_ncc = new Jdialog_ThemNhaCC();
				jdl_ncc.setLocationRelativeTo(null);
				jdl_ncc.setVisible(true);

				setDataComboboxNCC();
				cmbModel.setSelectedItem(Jdialog_ThemNhaCC.myComboboxNCC);

			}
		});
		btnThemNhaCC.setBackground(Color.ORANGE);
		btnThemNhaCC.setIcon(new ImageIcon(HangHoaJPanel.class.getResource("/img/icons8_plus_math_26px.png")));
		btnThemNhaCC.setBounds(1109, 257, 47, 33);
		panel_2.add(btnThemNhaCC);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 421, 1502, 578);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JPanel pnlView_Sach = new JPanel();
		pnlView_Sach.setBackground(Color.WHITE);
		pnlView_Sach.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlView_Sach.setBounds(0, 11, 1502, 554);
		panel_3.add(pnlView_Sach);

		/// table

		pnlView_Sach.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 1482, 509);
		pnlView_Sach.add(scrollPane);

		modelSach = new DefaultTableModel(columnNames, 0);

		tbl_Sach = new JTable(modelSach) {

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		;

		scrollPane.setViewportView(tbl_Sach);

		

		tbl_Sach.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tbl_Sach.getTableHeader().setPreferredSize(new Dimension(100, 50));
		tbl_Sach.setRowHeight(50);
		tbl_Sach.validate();
		tbl_Sach.repaint();

		tbl_Sach.addMouseListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);

		btnXoa.addActionListener(this);

		setDataToTableSach();
		setDataComboboxNCC();
		setDataComboboxTheLoai();

	}

	public void setDataComboboxTheLoai() {
		cmbModelTL = (DefaultComboBoxModel) cbmTheLoai.getModel();
		cmbModelTL.removeAllElements();
		ResultSet rSet = TheLoaiDao.getList();

		try {
			while (rSet.next()) {
				String maLoai = rSet.getString("maLoai");
				String tenloai = rSet.getString("tenTheLoai");
				MyCombobox myccb = new MyCombobox(maLoai, tenloai);
				cmbModelTL.addElement(myccb);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Lỗi lấy loại sản phẩm");
		}
	}

	public void setDataComboboxNCC() {

		cmbModel = (DefaultComboBoxModel) cmbNCC.getModel();
		cmbModel.removeAllElements();
		ResultSet rSet = NhaCungCapDao.getList2TP();

		try {

			while (rSet.next()) {
				String maNCC = rSet.getString("maNCC");
				String tenNCC = rSet.getString("tenNCC");
				MyCombobox myccb = new MyCombobox(maNCC, tenNCC);
				cmbModel.addElement(myccb);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Lỗi lấy mã nhà cung cấp");
		}

	}

	public void setDataToTableSach() {
		List<Sach> listIten = sach_service.getList();
		int columns = columnNames.length;
		Object[] obj;
		int rows = listIten.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				Sach sach = listIten.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = sach.getMaSach();
				obj[2] = sach.getTenSach();
				obj[3] = sach.getMaLoai().getMaLoai();
				obj[4] = sach.getDonGia();
				obj[5] = sach.getNamXB();
				obj[6] = sach.getNamSX();
				obj[7] = sach.getnCC().getMaNCC();
				obj[8] = sach.getTenTacGia();
				obj[9] = sach.getSoLuong();
				modelSach.addRow(obj);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (kiemTra()) {

				Sach s = reverSpFromTextFileSach();
				Object[] obj = null;
				if (listSach.create(s)) {
					int maxrow = tbl_Sach.getRowCount();

					int columns = columnNames.length;
					obj = new Object[columns];
					obj[0] = (maxrow + 1);
					String ma = taoMa.taoMaSach1();
					obj[1] = ma;
					obj[2] = textFieldTenSach.getText();

					String maloai;
					MyCombobox tenTheLoai = (MyCombobox) cbmTheLoai.getSelectedItem();
					maloai = tenTheLoai.maString();
					obj[3] = maloai;

					obj[4] = textFieldDonGia.getText();
					obj[5] = yearXB.getValue();
					obj[6] = yearSX.getValue();

					String maNCC;
					MyCombobox tenNCC = (MyCombobox) cmbNCC.getSelectedItem();
					maNCC = tenNCC.maString();
					obj[7] = maNCC;

					obj[8] = textFieldTacGia.getText();
					obj[9] = textFieldSoLuong.getText();
					modelSach.addRow(obj);
					xoaRongTextfieldSach();
				} else {
					JOptionPane.showMessageDialog(this, "MÃ TRÙNG");
				}
				;
			}

		} else if (o.equals(btnXoa)) {
			int row = tbl_Sach.getSelectedRow();
			System.out.println(row);
			if (row >= 0) {
				String maSach = (String) tbl_Sach.getValueAt(row, 1);

				int answer = JOptionPane.showConfirmDialog(HangHoaJPanel.this, "Bạn có thật sự muốn xóa sản phẩm này",
						"Thông báo", JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.NO_OPTION) {
					remove(btnXoa);
				} else if (answer == JOptionPane.YES_OPTION) {

					if (listSach.delete(maSach)) {
						JOptionPane.showMessageDialog(new JFrame(), "Xóa Thành công");
						modelSach.removeRow(row);
						return;
					}
				}

			} else {
				JOptionPane.showMessageDialog(this, "Chọn hàng cần xóa");
			}
		}

		else if (o.equals(btnSua)) {
			if (kiemTra()) {
				if (tbl_Sach.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(this, "Phải chọn dòng để sửa");
				else {
					int row = tbl_Sach.getSelectedRow();

					Sach s = new Sach();
					s.setMaSach(textFieldMaSach.getText().toString());
					s.setTenSach(textFieldTenSach.getText().toString());
					s.setDonGia(Double.parseDouble(textFieldDonGia.getText().toString()));
					s.setNamXB(yearXB.getValue());
					s.setNamSX(yearSX.getValue());

					MyCombobox tenTheLoai = (MyCombobox) cbmTheLoai.getSelectedItem();
					String maTL = tenTheLoai.maString();
					TheLoai TL = new TheLoai(maTL);
					s.setMaLoai(TL);

					MyCombobox tenNCC = (MyCombobox) cmbNCC.getSelectedItem();
					String maNCC = tenNCC.maString();
					NhaCungCap NCC = new NhaCungCap(maNCC);
					s.setnCC(NCC);

					s.setTenTacGia(textFieldTacGia.getText().toString());
					s.setSoLuong(Integer.parseInt(textFieldSoLuong.getText().toString()));

					if (listSach.update(s)) {
						modelSach.setValueAt(textFieldMaSach.getText(), row, 1);
						modelSach.setValueAt(textFieldTenSach.getText(), row, 2);

						String maloai;
						MyCombobox tenTheLoai2 = (MyCombobox) cbmTheLoai.getSelectedItem();
						maloai = tenTheLoai2.maString();
						modelSach.setValueAt(maloai, row, 3);

						modelSach.setValueAt(textFieldDonGia.getText(), row, 4);
						modelSach.setValueAt(yearXB.getValue(), row, 5);
						modelSach.setValueAt(yearSX.getValue(), row, 6);

						String maNCC2;
						MyCombobox tenNCC2 = (MyCombobox) cmbNCC.getSelectedItem();
						maNCC2 = tenNCC2.maString();
						modelSach.setValueAt(maNCC2, row, 7);

						modelSach.setValueAt(textFieldTacGia.getText(), row, 8);
						modelSach.setValueAt(textFieldSoLuong.getText(), row, 9);
					}
				}

			}

		}

	}

	public void MyCombobox() {

		// return null;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tbl_Sach)) {
			int row = tbl_Sach.getSelectedRow();
			textFieldMaSach.setText(tbl_Sach.getValueAt(row, 1).toString());
			textFieldTenSach.setText(tbl_Sach.getValueAt(row, 2).toString());

			MyCombobox TL = chonDoiTuongTrongCmb(tbl_Sach.getValueAt(row, 3).toString(),
					TLDao.laytentheoma(tbl_Sach.getValueAt(row, 3).toString()));

			cmbModelTL.setSelectedItem(TL);

			MyCombobox NCC = chonDoiTuongTrongCmb(tbl_Sach.getValueAt(row, 7).toString(),
					NCCDao.laytentheoma(tbl_Sach.getValueAt(row, 7).toString()));

			cmbModel.setSelectedItem(NCC);

			textFieldDonGia.setText(tbl_Sach.getValueAt(row, 4).toString());
			textFieldSoLuong.setText(tbl_Sach.getValueAt(row, 9).toString());

			int xb = Integer.parseInt(tbl_Sach.getValueAt(row, 5).toString());
			yearXB.setValue(xb);
			int sx = Integer.parseInt(tbl_Sach.getValueAt(row, 6).toString());
			yearSX.setValue(sx);
			textFieldTacGia.setText(tbl_Sach.getValueAt(row, 8).toString());

		}

	}

	public MyCombobox chonDoiTuongTrongCmb(Object ma, Object ten) {
		MyCombobox cb;
		cb = new MyCombobox(ma, ten);
		return cb;
	}

	private void xoaRongTextfieldSach() {
		textFieldMaSach.setText("");
		textFieldTenSach.setText("");
		textFieldDonGia.setText("");
		textFieldSoLuong.setText("");
		textFieldTacGia.setText("");

		textFieldMaSach.requestFocus();
	}

	private boolean kiemTra() {
		if (!regex.regexTenSach(textFieldTenSach))
			return false;
		if (!regex.regexSoLuong(textFieldSoLuong))
			return false;
		if (!regex.regexDonGia(textFieldDonGia))
			return false;
		if (!regex.regexTenTacGia(textFieldTacGia))
			return false;
		return true;

	}

	private Sach reverSpFromTextFileSach() {

		String maSach = taoMa.taoMaSach();
		String tenSach = textFieldTenSach.getText().toString();
		double donGia = Double.parseDouble(textFieldDonGia.getText().toString());
		int namXB = yearXB.getValue();
		int namSX = yearSX.getValue();

		MyCombobox tenTheLoai = (MyCombobox) cbmTheLoai.getSelectedItem();
		String maTL = tenTheLoai.maString();

		String maLoai = maTL;

		MyCombobox tenNCC = (MyCombobox) cmbNCC.getSelectedItem();
		String maNCC = tenNCC.maString();

		String nCC = maNCC;

		String tenTacGia = textFieldTacGia.getText().toString();
		int soLuong = Integer.parseInt(textFieldSoLuong.getText().toString());

		return new Sach(maSach, tenSach, new TheLoai(maLoai), donGia, namXB, namSX, new NhaCungCap(nCC), tenTacGia,
				soLuong);

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

	public void timKiemSach() {
		modelSach.setRowCount(0);
		List<Sach> list = listSach.timSachTheoTen2(txtSearch_Sach);
		int columns = columnNames.length;
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				Sach sach = list.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = sach.getMaSach();
				obj[2] = sach.getTenSach();
				obj[3] = sach.getMaLoai().getMaLoai();
				obj[4] = sach.getDonGia();
				obj[5] = sach.getNamXB();
				obj[6] = sach.getNamSX();
				obj[7] = sach.getnCC().getMaNCC();
				obj[8] = sach.getTenTacGia();
				obj[9] = sach.getSoLuong();
				modelSach.addRow(obj);
			}
		}

	}
}
