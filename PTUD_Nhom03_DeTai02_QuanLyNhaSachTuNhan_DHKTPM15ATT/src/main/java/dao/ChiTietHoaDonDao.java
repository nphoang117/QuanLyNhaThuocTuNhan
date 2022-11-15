/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03 
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;

public class ChiTietHoaDonDao {

	
	
	
	
	
	/**
	 * thống kê tất cả sản phẩm đã bán 
	 * @return
	 */
	public List<ChiTietHoaDon> getThongKeSanPham() {
		List<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT Sach.tenSach, Count( CT_HoaDon.soLuong) as soLuong FROM   CT_HoaDon"
					+ " INNER JOIN Sach ON CT_HoaDon.maSach = Sach.maSach group by Sach.tenSach";

			PreparedStatement statement = con.prepareStatement(sql);
		
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				Sach sach = new Sach();
				sach.setTenSach(rs.getString("tenSach"));
				cthd.setMaSach(sach);
				cthd.setSoLuong(rs.getInt("soLuong"));
				list.add(cthd);

			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	
	/**
	 * mô tả : lấy lên danh sách các chi tiết hóa đơn của một hóa đơn
	 * @param maHD
	 * @return
	 */
	public List<ChiTietHoaDon> getList(String maHD) {
		List<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from CT_HoaDon where maHoaDon = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				Sach sach = new Sach();
				sach.setMaSach(rs.getString("maSach"));
				cthd.setMaSach(sach);
				cthd.setDonGia(rs.getDouble("donGia"));
				cthd.setSoLuong(rs.getInt("soLuong"));
//				cthd.setThanhTien(rs.getDouble("thanhTien"));
				list.add(cthd);

			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}
/**
 * mô tả: xóa một chi tiết hóa đơn 
 * @param mahd
 * @return
 */
	public boolean delete(String mahd) {

		
		int n = 0;

		try {
			ConnectDB.getInstance().connect();;
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement("delete from CT_HoaDon where maHoaDon = ?");
			stmt.setString(1, mahd);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("loi xoa chi tiet hoa don");
		}

		return n > 0;
	}

	/**
	 * mô tả : thêm một chi tiết hóa đơn
	 * @param cthh
	 */
	public void them(ChiTietHoaDon cthh) {

		int rSet = 0;
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "insert into CT_HoaDon values (?,?,?,?,?)";

			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, cthh.getMaHoaDon().getMaHoaDon());
			statement.setString(2, cthh.getMaSach().getMaSach());
			statement.setInt(3, cthh.getSoLuong());
			statement.setDouble(4, cthh.getDonGia());
			statement.setDouble(5, cthh.getThanhTien());

			rSet = statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return rSet >0;

	}

}
