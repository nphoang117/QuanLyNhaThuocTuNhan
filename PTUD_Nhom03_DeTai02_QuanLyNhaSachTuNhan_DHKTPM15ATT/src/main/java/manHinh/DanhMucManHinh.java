/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03 
 * 
 * Mô tả lớp : tạo danh mục các màn hình tương ứng với menu để thiết lập hiệu ứng thay đỗi màu khi di chuyển chuột vào menu
 */
package manHinh;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMucManHinh {

		private String kind;
	    private JPanel jpn;
	    private JLabel jlb;

	    public DanhMucManHinh() {
	    }

	    public DanhMucManHinh(String kind, JPanel jpn, JLabel jlb) {
	        this.kind = kind;
	        this.jpn = jpn;
	        this.jlb = jlb;
	    }

	    public String getKind() {
	        return kind;
	    }

	    public void setKind(String kind) {
	        this.kind = kind;
	    }

	    public JPanel getJpn() {
	        return jpn;
	    }

	    public void setJpn(JPanel jpn) {
	        this.jpn = jpn;
	    }

	    public JLabel getJlb() {
	        return jlb;
	    }

	    public void setJlb(JLabel jlb) {
	        this.jlb = jlb;
	    }
	
	
	
}
