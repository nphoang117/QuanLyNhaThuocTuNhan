/**
 * Nguyễn Phi Hoàng - 19443211 - Nhóm 03
 * 
 */
package entity;

public class Sach {

	private String maSach;
	private String tenSach;
	private TheLoai maLoai;
	private double donGia;
	private int namXB;
	private int namSX;
	private NhaCungCap nCC;
	private String tenTacGia;
	private int soLuong;
	
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public TheLoai getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(TheLoai maLoai) {
		this.maLoai = maLoai;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public int getNamXB() {
		return namXB;
	}
	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}
	public int getNamSX() {
		return namSX;
	}
	public void setNamSX(int namSX) {
		this.namSX = namSX;
	}
	public NhaCungCap getnCC() {
		return nCC;
	}
	public void setnCC(NhaCungCap nCC) {
		this.nCC = nCC;
	}
	public String getTenTacGia() {
		return tenTacGia;
	}
	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public Sach(String maSach, String tenSach, TheLoai maLoai, double donGia, int namXB, int namSX, NhaCungCap nCC,
			String tenTacGia, int soLuong) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.maLoai = maLoai;
		this.donGia = donGia;
		this.namXB = namXB;
		this.namSX = namSX;
		this.nCC = nCC;
		this.tenTacGia = tenTacGia;
		this.soLuong = soLuong;
	}
	public Sach() {
		super();
	}
	public Sach(String maSach) {
		super();
		this.maSach = maSach;
	}
	@Override
	public String toString() {
		return "Sach [maSach=" + maSach + ", tenSach=" + tenSach + ", maLoai=" + maLoai + ", donGia=" + donGia
				+ ", namXB=" + namXB + ", namSX=" + namSX + ", nCC=" + nCC + ", tenTacGia=" + tenTacGia + ", soLuong="
				+ soLuong + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSach == null) ? 0 : maSach.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sach other = (Sach) obj;
		if (maSach == null) {
			if (other.maSach != null)
				return false;
		} else if (!maSach.equals(other.maSach))
			return false;
		return true;
	}
	
	
	
	
	
}
