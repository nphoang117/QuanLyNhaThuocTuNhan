/**
 * Nguyễn Phi Hoàng - 19443211 - Nhóm 03
 * 
 */
package entity;

public class TaiKhoan {

	private NhanVien maNhanVien;
	private String tenDangNhap;
	private String matKhau;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(NhanVien maNhanVien, String tenDangNhap, String matKhau) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}

	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maNhanVien=" + maNhanVien + ", tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + "]";
	}

}
