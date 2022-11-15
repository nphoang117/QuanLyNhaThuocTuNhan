/**
 * Nguyễn Phi Hoàng - 19443211 - Nhóm 03
 * 
 */
package entity;

import java.sql.Date;

public class NhanVien {

	private String maNhanVien;
	private String tenNhanVien;
	private Date ngaySinh;
	private Date ngayDauLamViec;
	private String sdt;
	private String email;
	private boolean gioiTinh;
	private String diaChi;
	private String chucVu;

	public NhanVien() {
		super();
	}
	

	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}


	public NhanVien(String maNhanVien, String tenNhanVien, Date ngaySinh, Date ngayDauLamViec, String sdt, String email,
			boolean gioiTinh, String diaChi, String chucVu) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.ngaySinh = ngaySinh;
		this.ngayDauLamViec = ngayDauLamViec;
		this.sdt = sdt;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Date getNgayDauLamViec() {
		return ngayDauLamViec;
	}

	public void setNgayDauLamViec(Date ngayDauLamViec) {
		this.ngayDauLamViec = ngayDauLamViec;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", ngaySinh=" + ngaySinh
				+ ", ngayDauLamViec=" + ngayDauLamViec + ", sdt=" + sdt + ", email=" + email + ", gioiTinh=" + gioiTinh
				+ ", diaChi=" + diaChi + ", chucVu=" + chucVu + "]";
	}

}
