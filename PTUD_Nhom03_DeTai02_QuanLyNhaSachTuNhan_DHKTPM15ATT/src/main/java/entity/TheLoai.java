/**
 * Nguyễn Phi Hoàng - 19443211 - Nhóm 03
 * 
 */
package entity;

public class TheLoai {

	private String maLoai;
	private String tenTheLoai;

	public TheLoai(String maLoai, String tenTheLoai) {
		super();
		this.maLoai = maLoai;
		this.tenTheLoai = tenTheLoai;
	}

	public TheLoai() {
		super();
	}

	

	public TheLoai(String maLoai) {
		super();
		this.maLoai = maLoai;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenTheLoai() {
		return tenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}

	@Override
	public String toString() {
		return "TheLoai [maLoai=" + maLoai + ", tenTheLoai=" + tenTheLoai + "]";
	}

}
