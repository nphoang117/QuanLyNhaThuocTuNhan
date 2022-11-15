/**
 * Đoàn Kiều Mỹ Ngọc -19446111 - nhóm 03
 * 
 *  mô tả lớp : để tạo đối tượng Combobox có 2 giá trị là value(mã) và text( tên) 
 */
package controller;

public class MyCombobox {
	private Object value;
	private Object text;
	public MyCombobox(Object value, Object text) {
		super();
		this.value = value;
		this.text = text;
	}
	@Override
	public String toString() {
		return text.toString();
	}
	
	public String maString() {
		return value.toString();
	}
	
	
	
	

}
