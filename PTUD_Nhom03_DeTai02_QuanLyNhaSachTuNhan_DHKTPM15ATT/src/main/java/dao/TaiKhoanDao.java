/**
 * Đoàn Kiều Mỹ Ngọc -19446111 - nhóm 03
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import connectDB.ConnectDB;
import entity.TaiKhoan;

public class TaiKhoanDao {

	/**
	 * cập nhật lại mật khẩu khi biết mã nhân viên và tên đăng nhập
	 * 
	 * @param manv
	 * @param tenDangnhap
	 * @return
	 */
	public boolean capLaiMatKhau(String manv, String tenDangnhap) {

		int rSet = 0;
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "update TaiKhoan set matKhau='111111' where maNhanVien = ?  and tenDangNhap = ? ";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, manv);
			statement.setString(2, tenDangnhap);
			rSet = statement.executeUpdate();

			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rSet > 0;

	}

	/**
	 * cập nhật mật khẩu khi biết mã nhân viên và mật khẩu cũ
	 * 
	 * @param maNV
	 * @param matKhaucu
	 * @param mkMoi
	 * @return
	 */

	public boolean thayDoiMatKhau(String maNV, String matKhaucu, String mkMoi) {
		PreparedStatement stmt;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement("update TaiKhoan set matKhau= ? where maNhanVien = ? and matKhau = ? ");

			stmt.setString(1, mkMoi);
			stmt.setString(2, maNV);
			stmt.setString(3, matKhaucu);
			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n > 0;

	}

	/**
	 * thêm mới một tài khoản
	 * 
	 * @param tk
	 * @return
	 */
	public boolean taoTaiKhoan(TaiKhoan tk) {
		PreparedStatement stmt;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement("insert into TaiKhoan values (?,?,?)");

			stmt.setString(1, tk.getMaNhanVien().getMaNhanVien());
			stmt.setString(2, tk.getTenDangNhap());
			stmt.setString(3, tk.getMatKhau());
			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n > 0;

	}

	/**
	 * xóa một tài khoản khi biết mã nhân viên
	 * 
	 * @param ma
	 * @return
	 */
	public boolean delete(String ma) {

		int n = 0;

		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement("delete from TaiKhoan where maNhanVien = ? ");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("lỗi xóa Tai khoan trong sql");
		}

		return n > 0;
	}

}
