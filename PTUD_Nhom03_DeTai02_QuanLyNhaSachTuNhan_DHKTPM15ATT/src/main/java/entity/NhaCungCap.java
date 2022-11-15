/**
 * Nguyễn Phi Hoàng - 19443211 - Nhóm 03
 * 
 */
package entity;

public class NhaCungCap {

	private String maNCC;
	private String tenNCC;
	private String diaChi;
	private String sdt;
	private String email;

	public NhaCungCap(String maNCC, String tenNCC, String diaChi, String sdt, String email) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.email = email;
	}

	public NhaCungCap(String maNCC) {
		super();
		this.maNCC = maNCC;
	}

	public NhaCungCap() {
		super();
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", sdt=" + sdt + ", email="
				+ email + "]";
	}

}
