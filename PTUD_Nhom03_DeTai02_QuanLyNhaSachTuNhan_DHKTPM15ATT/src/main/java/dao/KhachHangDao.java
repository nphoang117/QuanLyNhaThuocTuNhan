/**
 * Nguyễn Phạm Hoàng Long - 19443481 - nhóm 03
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhaCungCap;

public class KhachHangDao {

	private static final long serialVersionUID = 1L;

	/**
	 * thêm mới một khách hàng
	 * @param khachHang
	 * @return
	 */
	public boolean themKhachHang(KhachHang khachHang) {

		int n = 0;
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement("insert into KhachHang values (?,?,?,?)");

			stmt.setString(1, khachHang.getMaKhachHang());
			stmt.setString(2, khachHang.getTenKhachHang());
			stmt.setString(3, khachHang.getSdt());
			stmt.setString(4, khachHang.getDiaChi());

			n = stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n > 0;
	}

	/**
	 * đếm số lượng khách hàng
	 * @param makh
	 * @return
	 */
	public int countSoKhachHang(String makh) {

		int rowCount = 0;
//		ResultSet rs = null;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "SELECT count(*) from KhachHang where makhachHang like ?";

			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + makh + "%");
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
	 * 
	 * tìm một khách hàng khi biết mã khách hàng
	 * @param makh
	 * @return
	 */
	public KhachHang getTheoMa(String makh){
		KhachHang kh = new KhachHang();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "select * from KhachHang where maKhachHang = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, makh );
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
				kh.setMaKhachHang(rs.getString("maKhachHang"));
				kh.setTenKhachHang(rs.getString("tenKhachHang"));
				kh.setSdt(rs.getString("sdt"));
				kh.setDiaChi(rs.getString("diaChi"));
				
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(new JFrame(), "loi tim kiem khach hang");
		}

		return kh;
	}
	
	/**
	 * tìm kiếm khách hàng khi biết tên gần giống
	 * @param txtTimKhachHang
	 * @return
	 */
	
	public List<KhachHang> timKiemTheoTen(JTextField txtTimKhachHang){
		List<KhachHang> listKH = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "select * from KhachHang where tenKhachHang like ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, "%" + txtTimKhachHang.getText() + "%" );
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setMaKhachHang(rs.getString("maKhachHang"));
				kh.setTenKhachHang(rs.getString("tenKhachHang"));
				kh.setSdt(rs.getString("sdt"));
				kh.setDiaChi(rs.getString("diaChi"));
				listKH.add(kh);
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(new JFrame(), "loi tim kiem khach hang");
		}

		return listKH;
	}
	/**
	 * tìm kiếm khách hàng khi biết số điện thoại
	 * @param txtTimKhachHang
	 * @return
	 */

	public KhachHang timKiemTheoSdt(JTextField txtTimKhachHang) {
		 KhachHang kh = new KhachHang();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "select * from KhachHang where sdt = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1,  txtTimKhachHang.getText());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
				kh.setMaKhachHang(rs.getString("maKhachHang"));
				kh.setTenKhachHang(rs.getString("tenKhachHang"));
				kh.setSdt(rs.getString("sdt"));
				kh.setDiaChi(rs.getString("diaChi"));
				return kh;
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(new JFrame(), "loi tim kiem khach hang");
		}

		return null;
	}

	/**
	 * tìm kiếm khách hàng khi biết tên 
	 * @param tenkh
	 * @return
	 */
	public KhachHang getTheoTen(String tenkh) {
		KhachHang kh = new KhachHang();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "select * from KhachHang where tenKhachHang = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tenkh);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				kh.setMaKhachHang(rs.getString("maKhachHang"));
				kh.setTenKhachHang(rs.getString("tenKhachHang"));
				kh.setSdt(rs.getString("sdt"));
				kh.setDiaChi(rs.getString("diaChi"));

			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(new JFrame(), "loi tim kiem khach hang");
		}

		return kh;
	}
	/**
	 * lấy danh sách tất cả khách hàng
	 * @return
	 */
	public List<KhachHang> getList() {

		List<KhachHang> listKH = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "select * from KhachHang";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setMaKhachHang(rs.getString("maKhachHang"));
				kh.setTenKhachHang(rs.getString("tenKhachHang"));
				kh.setSdt(rs.getString("sdt"));
				kh.setDiaChi(rs.getString("diaChi"));

				listKH.add(kh);
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listKH;

	}

	/**
	 * lấy danh sách khách hàng
	 * @return
	 */
	public ResultSet getComBoboxKhachHang() {

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT * from KhachHang";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			return rs;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}
	/**
	 * lấy ra một khách hàng khi biết mã
	 * @param idKhachHang
	 * @return
	 */
	public KhachHang getOne(String idKhachHang) {
		KhachHang khachHang = new KhachHang();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT * from KhachHang where maKhachHang = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, idKhachHang);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
//				KhachHang khachHang = new KhachHang();
				khachHang.setMaKhachHang(rs.getString("maKhachHang"));
				khachHang.setTenKhachHang(rs.getString("tenKhachHang"));
				khachHang.setSdt(rs.getString("sdt"));
				khachHang.setDiaChi(rs.getString("diaChi"));
//				return khachHang;

			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return khachHang;

	}

	/**
	 * cập nhật thông tin khách hàng khi biết mã 
	 * @param s
	 * @return
	 */
	public boolean update(KhachHang s) {

		int n = 0;
		PreparedStatement stmt = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();

			stmt = con.prepareStatement(
					"update KhachHang set tenKhachHang=?,sdt=?, diaChi=? where maKhachHang=?");
			stmt.setString(1, s.getTenKhachHang());
			stmt.setString(2, s.getSdt());
			stmt.setString(3, s.getDiaChi());
			stmt.setString(4, s.getMaKhachHang());

			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return n > 0;

	}

	/**
	 * xóa một khách hàng khi biết mã 
	 * @param maKhachHang
	 * @return
	 */
	public boolean delete(String maKhachHang) {
		
		int n = 0;

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement("delete FROM KhachHang where maKhachHang = ?");
			stmt.setString(1, maKhachHang);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
		}
		return n > 0;
	}

	/**
	 * thêm một khách hàng mới
	 * @param s
	 * @return
	 */
	public boolean create(KhachHang s) {

		PreparedStatement stmt = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement("INSERT INTO KhachHang VALUES (?,?,?,?)");
			stmt.setString(1, s.getMaKhachHang());
			stmt.setString(2, s.getTenKhachHang());
			stmt.setString(3, s.getSdt());
			stmt.setString(4, s.getDiaChi());
			n = stmt.executeUpdate();

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n > 0;
	}

}
