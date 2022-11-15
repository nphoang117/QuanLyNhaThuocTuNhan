/**
 * Đoàn Kiều Mỹ Ngọc - 19446111 - Nhóm 03
 * 
 * Mô tả lớp :  báo cáo tất cả các hóa đơn bán được trong ngày 
 */
package gui_App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.HoaDonDAO;
import dao.KhachHangDao;
import dao.NhanVienDao;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class BaoCaoJPanel_NVBH extends JPanel {
	
	private DefaultTableModel model;
	private static JTable tbl_hd;
	private String[] columnNames = { "STT", "Mã Hóa Đơn", "Nhân Viên", "Ngày Lập", "Khách Hàng", "Tổng tiền",
			"Ghi chú" };
	private HoaDonDAO listHoaDon = new HoaDonDAO();
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private KhachHangDao khdao = new KhachHangDao();

	/**
	 * Create the panel.
	 */
	public BaoCaoJPanel_NVBH() {

		setLayout(null);
		setBounds(0, 0, 1502, 984);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1502, 985);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);

		JPanel pnl_view = new JPanel();
		pnl_view.setBorder(new TitledBorder(null, "H\u00F3a \u0111\u01A1n \u0111\u00E3 b\u00E1n trong ng\u00E0y ",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_view.setBackground(Color.WHITE);
		pnl_view.setBounds(10, 392, 1482, 582);
		panel.add(pnl_view);
		pnl_view.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 1462, 545);
		pnl_view.add(scrollPane);

		model = new DefaultTableModel(columnNames, 0);

		tbl_hd = new JTable(model) {

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		;

		scrollPane.setViewportView(tbl_hd);
		tbl_hd.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tbl_hd.getTableHeader().setPreferredSize(new Dimension(100, 50));
		tbl_hd.setRowHeight(50);
		tbl_hd.validate();
		tbl_hd.repaint();

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(0, 0, 1502, 89);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(30, 100, 448, 280);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BaoCaoJPanel_NVBH.class.getResource("/img/outline_account_circle_white_48dp.png")));
		lblNewLabel.setBounds(32, 85, 131, 140);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Nhân Viên");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(183, 196, 194, 62);
		panel_2.add(lblNewLabel_2);
		
		JLabel lbl_tenNV = new JLabel("Nguyễn Phạm HOàng Long");
		lbl_tenNV.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tenNV.setForeground(Color.WHITE);
		lbl_tenNV.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl_tenNV.setBounds(106, 24, 332, 62);
		panel_2.add(lbl_tenNV);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(Color.ORANGE);
		panel_2_1.setBounds(520, 100, 448, 280);
		panel.add(panel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(BaoCaoJPanel_NVBH.class.getResource("/img/icons8_euro_money_104px.png")));
		lblNewLabel_3.setBounds(45, 85, 131, 140);
		panel_2_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1 = new JLabel(" Doanh Thu Ngày");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2_1.setBounds(174, 196, 264, 62);
		panel_2_1.add(lblNewLabel_2_1);
		
		JLabel lblDoanhThu = new JLabel("10000");
		lblDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoanhThu.setForeground(Color.WHITE);
		lblDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDoanhThu.setBounds(154, 24, 284, 62);
		panel_2_1.add(lblDoanhThu);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBackground(Color.ORANGE);
		panel_2_2.setBounds(1023, 100, 448, 280);
		panel.add(panel_2_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(BaoCaoJPanel_NVBH.class.getResource("/img/icons8_purchase_order_104px.png")));
		lblNewLabel_4.setBounds(54, 86, 131, 140);
		panel_2_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2_2 = new JLabel("Số hóa đơn");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2_2.setBounds(206, 196, 194, 62);
		panel_2_2.add(lblNewLabel_2_2);
		
		JLabel lblSoHoaDon = new JLabel("12");
		lblSoHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoHoaDon.setForeground(Color.WHITE);
		lblSoHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSoHoaDon.setBounds(190, 24, 248, 62);
		panel_2_2.add(lblSoHoaDon);
		
		
		/**
		 * set data table
		 */
		setDataToTableHD();
		model = (DefaultTableModel) tbl_hd.getModel();
		lbl_tenNV.setText(DangNhapJFrame.nv.getTenNhanVien());
		lblDoanhThu.setText(String.valueOf(listHoaDon.getDoanhThuNgay(convertUtilToSql(new Date()),DangNhapJFrame.nv.getMaNhanVien() )));
		lblSoHoaDon.setText(String.valueOf(model.getRowCount()));
		
		System.out.println(listHoaDon.getDoanhThuNgay(convertUtilToSql(new Date()),DangNhapJFrame.nv.getMaNhanVien() ));
		System.out.println(model.getRowCount());

	}

	/**
	 * chuyển ngày bên java.util sang ngày của java.sql
	 * @param uDate
	 * @return
	 */
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	
	

	// lay data len table hoa don cho nhan vien
	private void setDataToTableHD() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDon> listHD = listHoaDon.getHoaDonBaoCao_NVBH( convertUtilToSql(new Date()),DangNhapJFrame.nv.getMaNhanVien());
		model.setRowCount(0);
		int columns = columnNames.length;
		Object[] obj;
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		int rows = listHD.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				HoaDon hd = listHD.get(i);
				obj = new Object[columns];
				obj[0] = (i + 1);
				obj[1] = hd.getMaHoaDon();
				nv = nhanVienDao.getNhanVienTheoMa(hd.getMaNhanVien().getMaNhanVien());
				obj[2] = nv.getTenNhanVien();
				obj[3] = dateFormat.format(hd.getNgayLapHD());
				kh = khdao.getTheoMa(hd.getMaKhachHang().getMaKhachHang());
				obj[4] = kh.getTenKhachHang();
				obj[5] = hd.getTongTien();
				obj[6] = hd.getGhiChu();
				model.addRow(obj);
			}
		}

	}
}
