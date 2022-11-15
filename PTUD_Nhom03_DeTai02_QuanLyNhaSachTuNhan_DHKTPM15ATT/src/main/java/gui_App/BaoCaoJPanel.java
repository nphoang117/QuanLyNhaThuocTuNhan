/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03 
 * 
 * Mô tả lớp :  thống kê doanh thu bán được theo ngày bằng biểu đồ, thống kê số lượng sản phẩm bán được bằng biểu đồ
 */
package gui_App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.ChiTietHoaDonDao;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

import javax.swing.JLabel;
import java.awt.SystemColor;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class BaoCaoJPanel extends JPanel {
	private JTextField textField;
	private HoaDonDAO hoaDonDAO = new HoaDonDAO();
	private ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDao();

	

	/**
	 * Create the panel.
	 */
	public BaoCaoJPanel() {
		setLayout(null);
		setBounds(0,0,1502, 984);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1502, 985);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		JPanel pnl_doanhThu = new JPanel();
		pnl_doanhThu.setBackground(Color.WHITE);
		pnl_doanhThu.setBounds(10, 100, 1482, 418);
		panel.add(pnl_doanhThu);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "T\u00E1c V\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(0, 0, 1502, 89);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JDateChooser dateChooser_from = new JDateChooser();
		dateChooser_from.setDateFormatString("dd/MM/yyyy");
		dateChooser_from.setBounds(103, 30, 253, 30);
		panel_1.add(dateChooser_from);
		
		JLabel lblNewLabel = new JLabel("Từ Ngày:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 30, 83, 30);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Đến Ngày:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(382, 30, 98, 30);
		panel_1.add(lblNewLabel_1);
		
		JDateChooser dateChooser_To = new JDateChooser();
		dateChooser_To.setDateFormatString("dd/MM/yyyy");
		dateChooser_To.setBounds(490, 30, 253, 30);
		panel_1.add(dateChooser_To);
		
		JButton btn_thongKe = new JButton("Thống kê");
		btn_thongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				System.out.println(dateFormat.format(dateChooser_from.getDate()));
				System.out.println(dateFormat.format(dateChooser_To.getDate()));
				setDateToChartDoanhThuTheoNgay(pnl_doanhThu, convertUtilToSql(dateChooser_from.getDate()), convertUtilToSql(dateChooser_To.getDate()));
				
			}
		});
		btn_thongKe.setBounds(753, 30, 98, 30);
		panel_1.add(btn_thongKe);
		
		JPanel pnl_SanPham = new JPanel();
		pnl_SanPham.setBounds(10, 529, 1482, 445);
		panel.add(pnl_SanPham);
	
		
		
		
		
		setDateToChart1(pnl_doanhThu);
		setDateToChartSanPham(pnl_SanPham);
	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	
	private void setDateToChart1(JPanel panelItem) {
		List<HoaDon> listHoaDon = hoaDonDAO.getThongKeDoanhThu();
		if (listHoaDon != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for (HoaDon hd : listHoaDon) {
				dataset.addValue(hd.getTongTien(), "Doanh thu", hd.getNgayLapHD());
			}

			JFreeChart charts = ChartFactory.createBarChart("THỐNG KÊ DOANH THU THEO NGÀY".toUpperCase(), "Thời gian", "Doanh thu",
					dataset , PlotOrientation.VERTICAL, true, true, true);
			ChartPanel chartPanel = new ChartPanel(charts);
			chartPanel.setPreferredSize(new Dimension(panelItem.getWidth(), 350));

			panelItem.removeAll();
			panelItem.setLayout(new CardLayout());
			panelItem.add(chartPanel);
			panelItem.validate();
			panelItem.repaint();
		}
	}
	
	private void setDateToChartDoanhThuTheoNgay(JPanel panelItem, java.sql.Date from, java.sql.Date to) {
		List<HoaDon> listHoaDon = hoaDonDAO.getThongKeDoanhThuTheoNgay(from, to);
		if (listHoaDon != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (HoaDon hd : listHoaDon) {
				dataset.addValue(hd.getTongTien(), "Doanh thu", hd.getNgayLapHD());
			}
			JFreeChart charts = ChartFactory.createBarChart("THỐNG KÊ DOANH THU THEO NGÀY đã chọn".toUpperCase(), "Thời gian", "Doanh thu",
					dataset , PlotOrientation.VERTICAL, true, true, true);
			ChartPanel chartPanel = new ChartPanel(charts);
			chartPanel.setPreferredSize(new Dimension(panelItem.getWidth(), 350));

			panelItem.removeAll();
			panelItem.setLayout(new CardLayout());
			panelItem.add(chartPanel);
			panelItem.validate();
			panelItem.repaint();
		}
	}
	
	private void setDateToChartSanPham(JPanel panelItem) {
		List<ChiTietHoaDon> listCTHD = chiTietHoaDonDao.getThongKeSanPham();
		if (listCTHD != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (ChiTietHoaDon cthd : listCTHD) {
				dataset.addValue(cthd.getSoLuong(), "Số lượng", cthd.getMaSach().getTenSach());
			}

			JFreeChart charts = ChartFactory.createBarChart("THỐNG KÊ SẢN PHẨM đã bán".toUpperCase(), "Tên sản phẩm", "Số Lượng",
					dataset , PlotOrientation.VERTICAL, true, true, true);
			ChartPanel chartPanel = new ChartPanel(charts);
			chartPanel.setPreferredSize(new Dimension(panelItem.getWidth(), 350));

			panelItem.removeAll();
			panelItem.setLayout(new CardLayout());
			panelItem.add(chartPanel);
			panelItem.validate();
			panelItem.repaint();
		}
	}
	
	
	
}
