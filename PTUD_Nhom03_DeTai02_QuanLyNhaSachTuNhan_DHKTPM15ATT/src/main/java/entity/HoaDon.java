/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03 
 */
package entity;

import java.sql.Date;

public class HoaDon {

	private String maHoaDon;
	private NhanVien maNhanVien;
	private Date ngayLapHD;
	private KhachHang maKhachHang;
	private double tongTien;
	private String ghiChu;

	public HoaDon(String maHoaDon, NhanVien maNhanVien, Date ngayLapHD, KhachHang maKhachHang, double tongTien,
			String ghiChu) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.ngayLapHD = ngayLapHD;
		this.maKhachHang = maKhachHang;
		this.tongTien = tongTien;
		this.ghiChu = ghiChu;
	}

	public HoaDon() {
		super();
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public Date getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(Date ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public KhachHang getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(KhachHang maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public double getTongTien() {
		
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", ngayLapHD=" + ngayLapHD
				+ ", maKhachHang=" + maKhachHang + ", tongTien=" + tongTien + ", ghiChu=" + ghiChu + "]";
	}

}
