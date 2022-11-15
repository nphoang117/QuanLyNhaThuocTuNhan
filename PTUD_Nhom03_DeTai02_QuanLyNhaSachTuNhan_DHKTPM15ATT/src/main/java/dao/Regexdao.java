/**
 * Nguyễn Phạm Hoàng Long - 19443481 - nhóm 03
 * 
 * mô tả lớp : các hàm kiểm tra điều kiện chính quy khi nhập liệu 
 */
package dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Regexdao {
	/**
	 * kiểm tra chính quy cho địa chỉ
	 * 
	 * @param txtDiaChi
	 * @return
	 */
	public boolean regexDiaChi(JTextField txtDiaChi) {
		String input = txtDiaChi.getText();
		String input1 = input.trim();
		String regex = "^([ A-Za-z0-9,.a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (/* !matcher.find()|| */!(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ!");
			txtDiaChi.requestFocus();
			txtDiaChi.selectAll();
			return false;
		} else
			return true;
	}

	/**
	 * kiểm tra chính quy cho tên
	 * 
	 * @param txtDiaChi
	 * @return
	 */
	public boolean regexTenNCC(JTextField txtDiaChi) {
		String input = txtDiaChi.getText();
		String input1 = input.trim();
		String regex = "^([ A-Za-z0-9,.a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find() || !(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Tên Nhà Cung Cấp không hợp lệ!");
			txtDiaChi.requestFocus();
			txtDiaChi.selectAll();
			return false;
		} else
			return true;
	}

	/**
	 * kiểm tra chính quy cho tên sách
	 * 
	 * @param txtSach
	 * @return
	 */
	public boolean regexTenSach(JTextField txtSach) {
		String input = txtSach.getText();
		String input1 = input.trim();
		String regex = "^([ A-Za-z0-9,.a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find() || !(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Tên Sách Không Hợp Lệ");
			txtSach.requestFocus();
			txtSach.selectAll();
			return false;
		} else
			return true;
	}

	/**
	 * kiểm tra chính quy cho tên
	 * 
	 * @param txtTen
	 * @return
	 */
	public boolean regexTen(JTextField txtTen) {
		String input = txtTen.getText();
		String input1 = input.trim();
		String regex = "^([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find() || !(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!");
			txtTen.requestFocus();
			txtTen.selectAll();
			return false;
		} else
			return true;
	}

	/**
	 * kiểm tra chính quy cho tên tác giả
	 * 
	 * @param txtTen
	 * @return
	 */
	public boolean regexTenTacGia(JTextField txtTen) {
		String input = txtTen.getText();
		String input1 = input.trim();
		String regex = "^([ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find() || !(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Tên Tác Giả không hợp lệ!");
			txtTen.requestFocus();
			txtTen.selectAll();
			return false;
		} else
			return true;
	}

	/**
	 * kiểm tra chính quy cho số điện thoại
	 * 
	 * @param txtSdt
	 * @return
	 */
	public boolean regexSDT(JTextField txtSdt) {
		String input = txtSdt.getText();
		String input1 = input.trim();
		String regex = "^(0[0-9]{9}$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find() || !(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null, "SĐT không hợp lệ!\nSĐT gồm 10 chữ số và bắt đầu bằng số 0");
			txtSdt.requestFocus();
			txtSdt.selectAll();
			return false;
		} else
			return true;
	}

	/**
	 * kiểm tra chính quy cho sđt
	 * 
	 * @param txtSdt
	 * @return
	 */
	public boolean regexSDTNCC(JTextField txtSdt) {
		String input = txtSdt.getText();
		String input1 = input.trim();
		String regex = "^([0-9]{10}$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find() || !(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null, "SĐT Nhà Cung Cấp không hợp lệ!");
			txtSdt.requestFocus();
			txtSdt.selectAll();
			return false;
		} else
			return true;
	}

	public boolean regexSoLuong(JTextField txtSoluong) {
		String input = txtSoluong.getText();
		String input1 = input.trim();
		String regex = "^[1-9]+[0-9]*$";
		if (!input.matches(regex) || !(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null,
					"Số lượng không được để trống, không được nhập chữ và phải lớn hơn 0\nVí dụ: 10");
			txtSoluong.requestFocus();
			txtSoluong.selectAll();
			return false;
		}
		return true;
	}

	public boolean regexDonGia(JTextField textFieldDonGia) {
		String input = textFieldDonGia.getText();
		String input1 = input.trim();
		String regex = "^[1-9]+[0-9]*$";
		if (!input.matches(regex) || !(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Đơn giá không được để trống\nVí dụ: 10");
			textFieldDonGia.requestFocus();
			textFieldDonGia.selectAll();
			return false;
		}
		return true;
	}

	public boolean regexEmail(JTextField txtEmail) {
		String input = txtEmail.getText();
		String input1 = input.trim();
		String regex = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$";
		if (!input.matches(regex) || !(input1.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Email không hợp lệ , Vui lòng nhập lại Email");
			txtEmail.requestFocus();
			txtEmail.selectAll();
			return false;
		}
		return true;
	}

}
