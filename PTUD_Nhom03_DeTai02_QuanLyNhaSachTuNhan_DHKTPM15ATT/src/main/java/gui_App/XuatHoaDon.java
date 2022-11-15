/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03
 * 
 * mô tả lớp : thực hiện chức năng xuất hóa đơn đã thanh toán bằng PDF 
 */
package gui_App;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class XuatHoaDon {

	private static Date date = new Date();
	private static JTable jTable;
	private static String mahd;
	private static String ngayLap;
	private static String tenKH;
	private static String sdt;
	private static String diachi;
	private static String nhanvien;
	private static String tienKhachDua;
	private static String tienThoi;
	private static String tongTien;
	private static String ghiChu;
	private static String Thue;
	

	public XuatHoaDon() {

	}

	public XuatHoaDon(JTable jTable, String mahd, String ngayLap, String tenKH, String sdt, String diachi,
			String nhanvien, String tienKhachDua, String tienThoi, String tongTien, String ghiChu,String Thue) {
		super();
		this.jTable = jTable;
		this.mahd = mahd;
		this.ngayLap = ngayLap;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.diachi = diachi;
		this.nhanvien = nhanvien;
		this.tienKhachDua = tienKhachDua;
		this.tienThoi = tienThoi;
		this.tongTien = tongTien;
		this.ghiChu = ghiChu;
		this.Thue = Thue;
		
	}

	public static void xuatpdf() {
		Document document = new Document(PageSize.A4, 25, 25, 25, 50);
		String fontAria = "C:\\Users\\hocnv\\OneDrive\\Documents\\Nam_3_HK1\\java_phan_tan\\Bai_tap_lon\\Nhom03_QuanLyNhaSachTuNhan\\lib\\vuArial.ttf";

		String fontBold = "C:\\Users\\hocnv\\OneDrive\\Documents\\Nam_3_HK1\\java_phan_tan\\Bai_tap_lon\\Nhom03_QuanLyNhaSachTuNhan\\lib\\vuArialBold.ttf";

		try {

			PdfWriter.getInstance(document, new FileOutputStream(
					"C:\\Users\\hocnv\\OneDrive\\Documents\\Nam_3_HK1\\java_phan_tan\\Bai_tap_lon\\Nhom03_QuanLyNhaSachTuNhan\\lib\\exportPDF1.pdf"));

			document.open();
			Font fA = new Font(BaseFont.createFont(fontAria, BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
			Font fB = new Font(BaseFont.createFont(fontBold, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 20);
			Font fBx = new Font(BaseFont.createFont(fontBold, BaseFont.IDENTITY_H, BaseFont.EMBEDDED));

			document.add(new Paragraph(
					"Nhà sách AAA\r\n" + "Sđt:0989007777\r\n" + "Địa chỉ: 12 Nguyễn Văn bảo,\r\n" + " P3, Q.Gò vấp",
					fA));
			
			Paragraph title = new Paragraph("Hóa Đơn Bán Hàng", fB);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			Paragraph ngayLapHD = new Paragraph("Ngày Lập:" + ngayLap, fA);
			Paragraph maHD = new Paragraph("Mã Hóa Đơn:" + mahd, fA);
			Paragraph TenKH = new Paragraph("Khách hàng:" + tenKH, fA);
			Paragraph Sdt = new Paragraph("Sđt:" + sdt, fA);
			Paragraph diaChi = new Paragraph("Địa chỉ:" + diachi, fA);
			Paragraph nhanVien = new Paragraph("Nhân viên:" + nhanvien, fA);
			document.add(new Paragraph("ư"));
			document.add(new Paragraph("ư"));
			document.add(new Paragraph("ư"));
			document.add(maHD);
			document.add(ngayLapHD);
			document.add(TenKH);
			document.add(Sdt);
			document.add(diaChi);
			document.add(nhanVien);

			PdfPTable Table = new PdfPTable(5);
			Table.setSpacingBefore(25);
			Table.setSpacingAfter(25);
			Table.setWidthPercentage(100);

			
			PdfPCell cell1, cell2, cell3, cell4, cell5;
			Table.getDefaultCell().setBorder(Rectangle.TOP);
			
			cell1 = new PdfPCell(new Phrase("STT",fBx));
			cell1.setBorder(Rectangle.TOP);
			Table.addCell(cell1);
			cell2 = new PdfPCell(new Phrase("Sản Phẩm", fBx));
			cell2.setBorder(Rectangle.TOP);
			Table.addCell(cell2);
			cell3 = new PdfPCell(new Phrase("SL",fBx));
			cell3.setBorder(Rectangle.TOP);
			Table.addCell(cell3);
			cell4 = new PdfPCell(new Phrase("Đơn Giá", fBx));
			cell4.setBorder(Rectangle.TOP);
			Table.addCell(cell4);
			cell5 = new PdfPCell(new Phrase("Thành Tiền", fBx));
			cell5.setBorder(Rectangle.TOP);
			Table.addCell(cell5);

			int row = jTable.getRowCount();
			int stt = 0;
			for (int i = 0; i < row; i++) {
				stt = i + 1;
				Table.addCell(String.valueOf(stt));

				Table.addCell(new Phrase(jTable.getValueAt(i, 2).toString(), fA));
				Table.addCell(new Phrase(jTable.getValueAt(i, 3).toString(), fA));
				Table.addCell(new Phrase(jTable.getValueAt(i, 4).toString(), fA));
				Table.addCell(new Phrase(jTable.getValueAt(i, 5).toString(), fA));

			}

			document.add(Table);
			double tongT = (Double.parseDouble(tongTien)/100)* Double.parseDouble(Thue);
			double TienCanTra= Double.parseDouble(tongTien)*1.05;

			Paragraph tongtien = new Paragraph("Tổng tiền Sản Phẩm:"+"                                                                                              " + tongTien, fBx);
			Paragraph ThueVAT = new Paragraph("Thuế VAT:5%"+"                                                                                                          " + String.valueOf(tongT), fBx);
			Paragraph TienSauThue = new Paragraph("Tổng tiền cần thanh toán:"+"                                                                                      " + TienCanTra, fBx);
			Paragraph tienkhachDua = new Paragraph("Tiền Khách đưa:" +"                                                                                                     " + tienKhachDua, fBx);
			Paragraph tienThua = new Paragraph("Tiền thừa:"+"                                                                                                                "  + tienThoi, fBx);
			Paragraph ghichu = new Paragraph("Ghi Chú:", fA);
			Paragraph ghichund = new Paragraph(ghiChu, fA);
			document.add(tongtien);
			document.add(ThueVAT);
			document.add(TienSauThue);
			document.add(tienkhachDua);
			document.add(tienThua);
			document.add(ghichu);
			document.add(ghichund);

			document.close();
			Process p;
			try {
				p = Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler C:\\Users\\hocnv\\OneDrive\\Documents\\Nam_3_HK1\\java_phan_tan\\Bai_tap_lon\\Nhom03_QuanLyNhaSachTuNhan\\lib\\exportPDF1.pdf");
				p.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
