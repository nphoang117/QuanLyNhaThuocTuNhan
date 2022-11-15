/**
 * Nguyễn Phạm Hoàng Long - 19443481 - nhóm 03
 * 
 * mô tả lớp : thực hiện các chức năng cơ bản Thêm, Xóa, Sửa ,Tìm kiếm các khách hàng
 */
package gui_App;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


import controller.TaoMaTuDong;
import dao.KhachHangDao;
import dao.Regexdao;
import entity.KhachHang;
import entity.Sach;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

public class KhachHangJPanel extends JPanel implements ActionListener, MouseListener {

	private JTextField txtSearch_KhachHang;
	private JTextField textFieldMaKhachHang;
	private JTextField textFieldDienThoai;
	private JTextField textFieldDiaChi;
	private JTextField textFieldHoTen;
	private JTable table_KH;
	private TaoMaTuDong taoma = new TaoMaTuDong();
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private KhachHangDao listKH = new KhachHangDao();

	private String[] listColumn = { "STT", "Mã Khách Hàng", "Tên Khách Hàng", "Số điện thoại", "Địa Chỉ" };
	private DefaultTableModel model_KH;
	/**
	 * dao
	 */
	private KhachHangDao khachHangDao = new KhachHangDao();
	private Regexdao regex = new Regexdao();

	/**
	 * Create the panel.
	 */
	public KhachHangJPanel() {
		setLayout(null);
		setBounds(0, 0, 1502, 985);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1502, 1123);
		panel.setBackground(new Color(240, 240, 240));
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "T\u00ECm Ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(0, 0, 1502, 89);
		panel.add(panel_1);

		txtSearch_KhachHang = new JTextField();

		txtSearch_KhachHang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timkiemKH();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				timkiemKH();
			}
		});
		txtSearch_KhachHang.setBounds(10, 26, 330, 36);
		txtSearch_KhachHang.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 89, 1502, 316);
		panel.add(panel_2);

		JLabel lblMaKhachHang = new JLabel("Mã Khách Hàng");
		lblMaKhachHang.setBounds(47, 32, 113, 33);
		lblMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblHoTen = new JLabel("Họ Tên:");
		lblHoTen.setBounds(47, 99, 113, 26);
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblDienThoai = new JLabel("Số Điện Thoại:");
		lblDienThoai.setBounds(47, 160, 113, 26);
		lblDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setBounds(47, 226, 113, 26);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textFieldMaKhachHang = new JTextField();
		textFieldMaKhachHang.setEditable(false);// không sửa mã
		textFieldMaKhachHang.setBounds(178, 33, 922, 33);
		textFieldMaKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 13));
		textFieldMaKhachHang.setColumns(10);

		textFieldHoTen = new JTextField();
		textFieldHoTen.setBounds(178, 98, 922, 33);
		textFieldHoTen.setColumns(10);

		textFieldDienThoai = new JTextField();
		textFieldDienThoai.setBounds(178, 159, 922, 33);
		textFieldDienThoai.setColumns(10);

		textFieldDiaChi = new JTextField();
		textFieldDiaChi.setBounds(178, 225, 922, 33);
		textFieldDiaChi.setColumns(10);
		panel_2.setLayout(null);
		panel_2.add(lblHoTen);
		panel_2.add(textFieldHoTen);
		panel_2.add(lblMaKhachHang);
		panel_2.add(textFieldMaKhachHang);
		panel_2.add(lblDienThoai);
		panel_2.add(textFieldDienThoai);
		panel_2.add(lblDiaChi);
		panel_2.add(textFieldDiaChi);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(
				new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(1174, 11, 302, 277);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(77, 27, 140, 45);
		panel_4.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(77, 83, 140, 45);
		panel_4.add(btnXoa);
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnSua = new JButton("Sửa");
		btnSua.setBounds(77, 139, 140, 45);
		panel_4.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("Xóa Rỗng");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(77, 195, 140, 45);
		panel_4.add(btnNewButton);

		/**
		 * add event
		 */
		panel_1.setLayout(null);
		panel_1.add(txtSearch_KhachHang);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 405, 1502, 575);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JPanel pnlView_KhachHang = new JPanel();
		pnlView_KhachHang.setBorder(new TitledBorder(null, "Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlView_KhachHang.setBackground(Color.WHITE);
		pnlView_KhachHang.setBounds(0, 0, 1498, 562);
		panel_3.add(pnlView_KhachHang);

		//
	
		pnlView_KhachHang.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 1478, 526);
		pnlView_KhachHang.add(scrollPane);

		model_KH = new DefaultTableModel(listColumn, 0);

		table_KH = new JTable(model_KH) {

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		;

		scrollPane.setViewportView(table_KH);

		table_KH.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table_KH.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table_KH.setRowHeight(50);
		table_KH.validate();
		table_KH.repaint();

// event
		setDataToTableKhachHang();
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		table_KH.addMouseListener(this);
	}

	/**
	 * lấy dữ liệu lên bảng khách hàng
	 */
	public void setDataToTableKhachHang() {
		List<KhachHang> listIten = khachHangDao.getList();
		int columns = listColumn.length;
		Object[] obj;
		int rows = listIten.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				KhachHang kh = listIten.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = kh.getMaKhachHang();

				obj[2] = kh.getTenKhachHang();

				obj[3] = kh.getSdt();
				obj[4] = kh.getDiaChi();

				model_KH.addRow(obj);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if(kiemTra()) {
				KhachHang s = reverSpFromTextFileKhachHang();
				Object[] obj = null;
				if (listKH.create(s)) {
					int maxrow = table_KH.getRowCount();

					int columns = listColumn.length;
					obj = new Object[columns];
					obj[0] = (maxrow + 1);
					obj[1] = taoma.taoMaKhachHang1();
					obj[2] = textFieldHoTen.getText();
					obj[3] = textFieldDienThoai.getText();
					obj[4] = textFieldDiaChi.getText();

					model_KH.addRow(obj);
					xoaRongTextfieldKhachHang();
				} else {
					JOptionPane.showMessageDialog(this, "MÃ TRÙNG");
				}
				;
			}
			

		} else if (o.equals(btnXoa)) {
			int row = table_KH.getSelectedRow();
			if (row >= 0) {
				String maKH = table_KH.getValueAt(row, 1).toString();
				System.out.println(maKH);
				int answer = JOptionPane.showConfirmDialog(KhachHangJPanel.this,
						"Bạn có thật sự muốn xóa khách hàng này", "Thông báo", JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.NO_OPTION) {
					remove(btnXoa);
				} else if (answer == JOptionPane.YES_OPTION) {
					if (listKH.delete(maKH)) {
						model_KH.removeRow(row);
					}
					
				}
				
				
			} else {
				JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa");
			}
		} else if (o.equals(btnSua)) {
			if(kiemTra()) {
				suaKH();
			}
			
		}

	}
	/**
	 * kiểm tra biểu thức chính quy nhập liệu
	 * @return
	 */
	private boolean kiemTra() {
		if (!regex.regexTen(textFieldHoTen))
			return false;
		if (!regex.regexSDT(textFieldDienThoai))
			return false;
		if (!regex.regexDiaChi(textFieldDiaChi))
			return false;
		return true;
	}

	/**
	 * sửa thông tin khách hàng
	 */
	private void suaKH() {
		int row = table_KH.getSelectedRow();

		if (row > 0) {
			String maKhachHang = textFieldMaKhachHang.getText().toString();
			String diaChi = textFieldDiaChi.getText().toString();
			String dienThoai = textFieldDienThoai.getText().toString();
			String hoTen = textFieldHoTen.getText().toString();

			KhachHang KH = new KhachHang(maKhachHang, hoTen, dienThoai, diaChi);
			if (khachHangDao.update(KH)) {
				JOptionPane.showMessageDialog(this, "Sửa Thành Công");

				model_KH.setValueAt(textFieldMaKhachHang.getText(), row, 1);
				model_KH.setValueAt(textFieldHoTen.getText(), row, 2);
				model_KH.setValueAt(textFieldDienThoai.getText(), row, 3);
				model_KH.setValueAt(textFieldDiaChi.getText(), row, 4);
			}
		} else
			JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table_KH)) {
			int row = table_KH.getSelectedRow();
			textFieldMaKhachHang.setText(table_KH.getValueAt(row, 1).toString());
			textFieldHoTen.setText(table_KH.getValueAt(row, 2).toString());
			textFieldDiaChi.setText(table_KH.getValueAt(row, 4).toString());
			textFieldDienThoai.setText(table_KH.getValueAt(row, 3).toString());

		}

	}

	/**
	 * lấy thông tin trên form nhập liệu 
	 * @return
	 */
	private KhachHang reverSpFromTextFileKhachHang() {
		String maKhachHang = taoma.taoMaKhachHang();
		String diaChi = textFieldDiaChi.getText().toString();
		String dienThoai = textFieldDienThoai.getText().toString();
		String hoTen = textFieldHoTen.getText().toString();

		return new KhachHang(maKhachHang, hoTen, dienThoai, diaChi);

	}

	/**
	 * lấy thông tin trên form nhập liệu 
	 * @return
	 */
	private KhachHang reverSpFromTextFileKhachHang1() {
		String maKhachHang = textFieldMaKhachHang.getText().toString();
		String diaChi = textFieldDiaChi.getText().toString();
		String dienThoai = textFieldDienThoai.getText().toString();
		String hoTen = textFieldHoTen.getText().toString();

		return new KhachHang(maKhachHang, hoTen, dienThoai, diaChi);

	}

	private void xoaRongTextfieldKhachHang() {
		textFieldMaKhachHang.setText("");
		textFieldDiaChi.setText("");
		textFieldDienThoai.setText("");
		textFieldHoTen.setText("");

		textFieldMaKhachHang.requestFocus();
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
	 * tìm kiếm khách hàng theo tên
	 */
	public void timkiemKH() {
		model_KH.setRowCount(0);
		List<KhachHang> list = khachHangDao.timKiemTheoTen(txtSearch_KhachHang);
		int columns = listColumn.length;
		Object[] obj;
		int rows = list.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				KhachHang kh = list.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = kh.getMaKhachHang();
				obj[2] = kh.getTenKhachHang();
				obj[3] = kh.getSdt();
				obj[4] = kh.getDiaChi();

				model_KH.addRow(obj);
			}
		}
	}

}
