/**
 * Đoàn Kiều Mỹ Ngọc -19446111 - nhóm 03
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Sach;
import entity.TheLoai;

public class TheLoaiDao {

	private static final long serialVersionUID = 1L;

	/**
	 * thêm mới một thể loại
	 * 
	 * @param theLoai
	 * @return
	 */
	public boolean create(TheLoai theLoai) {

		PreparedStatement stmt = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement("insert into TheLoai values (?,?)");
			stmt.setString(1, theLoai.getMaLoai());
			stmt.setString(2, theLoai.getTenTheLoai());

			n = stmt.executeUpdate();

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * đếm số lượng thể loại
	 * 
	 * @param maTheloai
	 * @return
	 */
	public int countTheLoai(String maTheloai) {

		int rowCount = 0;
//		ResultSet rs = null;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "SELECT count(*) from TheLoai where maLoai like ?";

			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + maTheloai + "%");
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				rowCount = rs.getInt(1);
				return rowCount;
			}
			statement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * lấy danh sách tất cả thể loại
	 * 
	 * @return
	 */
	public static ResultSet getList() {

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT * from TheLoai";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			return rs;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	/**
	 * lấy tên thể loại khi biết mã
	 * 
	 * @param ma
	 * @return
	 */
	public String laytentheoma(String ma) {
		// String tenNCC = new String();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnetction();
		PreparedStatement stmt = null;
		String sql = " select tenTheLoai from TheLoai where maLoai=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("tenTheLoai");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * lấy mã thể loại khi biết tên
	 * 
	 * @param ten
	 * @return
	 */
	public String laymatheoten(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnetction();
		PreparedStatement stmt = null;
		String sql = " select maLoai from TheLoai where tenTheLoai=?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("maLoai");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
