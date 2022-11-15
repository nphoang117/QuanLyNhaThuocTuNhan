/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03 
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import javax.swing.JTextField;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;

public class HoaDonDAO {

	private static final long serialVersionUID = 1L;

	/**
	 * tính tổng tiền các hóa đơn trong một ngày nào đó
	 * 
	 * @param date
	 * @param maNV
	 * @return
	 */
	public double getDoanhThuNgay(Date date, String maNV) {

		double doanhThu = 0;
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select sum(tongTien) as doanhThu from HoaDon where ngayLapHD = '" + date
					+ "' and maNhanVien='" + maNV + "'";

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				doanhThu = rs.getDouble("doanhThu");
				return doanhThu;
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return doanhThu;

	}

	/**
	 * lấy tất cả hóa đơn bán trong ngày theo mã nhân viên bán hàng
	 * 
	 * @param date
	 * @param maNV
	 * @return
	 */
	public List<HoaDon> getHoaDonBaoCao_NVBH(Date date, String maNV) {

		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT * FROM   HoaDon where ngayLapHD = '" + date + "' and  maNhanVien = '" + maNV + "'";

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				hd.setNgayLapHD(rs.getDate("ngayLapHD"));

				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));

				hd.setMaKhachHang(kh);
				hd.setMaNhanVien(nv);
				hd.setTongTien(rs.getDouble("tongTien"));
				hd.setGhiChu(rs.getString("ghiChu"));

				list.add(hd);
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
	 * tìm kiếm hóa đơn theo ngày cho nv bán hàng
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public List<HoaDon> timHDTheoNgay_NVBH(Date from, Date to, String maNV) {

		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT * FROM   HoaDon where ngayLapHD between '" + from + "' and '" + to
					+ "' and  maNhanVien = '" + maNV + "'";

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				hd.setNgayLapHD(rs.getDate("ngayLapHD"));

				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));

				hd.setMaKhachHang(kh);
				hd.setMaNhanVien(nv);
				hd.setTongTien(rs.getDouble("tongTien"));
				hd.setGhiChu(rs.getString("ghiChu"));

				list.add(hd);
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
	 * tìm kiếm hóa đơn theo ngày cho quản lý
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public List<HoaDon> timHDTheoNgay(Date from, Date to) {

		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT * FROM   HoaDon where ngayLapHD between '" + from + "' and '" + to + "'";

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				hd.setNgayLapHD(rs.getDate("ngayLapHD"));

				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));

				hd.setMaKhachHang(kh);
				hd.setMaNhanVien(nv);
				hd.setTongTien(rs.getDouble("tongTien"));
				hd.setGhiChu(rs.getString("ghiChu"));

				list.add(hd);
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
	 * tìm tất cả hóa đơn theo sđt khách hàng cho quản lý
	 * 
	 * @param txtTimKiem
	 * @return
	 */
	public List<HoaDon> timHDTheoSdtKhachHang(JTextField txtTimKiem) {

		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT HoaDon.* FROM   HoaDon INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang where KhachHang.sdt = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, txtTimKiem.getText());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				hd.setNgayLapHD(rs.getDate("ngayLapHD"));

				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));

				hd.setMaKhachHang(kh);
				hd.setMaNhanVien(nv);
				hd.setTongTien(rs.getDouble("tongTien"));
				hd.setGhiChu(rs.getString("ghiChu"));

				list.add(hd);
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
	 * tìm tất cả hóa đơn theo sđt khách hàng cho nv bán hàng
	 * 
	 * @param txtTimKiem
	 * @return
	 */
	public List<HoaDon> timHDTheoSdtKhachHang_NVBH(JTextField txtTimKiem, String maNV) {

		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT HoaDon.* FROM   HoaDon INNER JOIN KhachHang ON"
					+ " HoaDon.maKhachHang = KhachHang.maKhachHang where KhachHang.sdt = '" + txtTimKiem.getText()
					+ "' and  HoaDon.maNhanVien ='" + maNV + "'";

			PreparedStatement statement = con.prepareStatement(sql);
//			statement.setString(1, txtTimKiem.getText());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				hd.setNgayLapHD(rs.getDate("ngayLapHD"));

				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));

				hd.setMaKhachHang(kh);
				hd.setMaNhanVien(nv);
				hd.setTongTien(rs.getDouble("tongTien"));
				hd.setGhiChu(rs.getString("ghiChu"));

				list.add(hd);
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
	 * Thống kê doanh thu theo ngày
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public List<HoaDon> getThongKeDoanhThuTheoNgay(Date from, Date to) {
		List<HoaDon> list = new ArrayList<HoaDon>();

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select  [ngayLapHD] , sum([tongTien]) as doanhThu from HoaDon  where ngayLapHD between '"
					+ from + "' and '" + to + "' group by [ngayLapHD] ";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setNgayLapHD(rs.getDate("ngayLapHD"));
				hd.setTongTien(rs.getDouble("doanhThu"));

				list.add(hd);

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
	 * mô tả : thống kê doanh thu bán được trong hóa đơn theo ngày
	 * 
	 * @return
	 */
	public List<HoaDon> getThongKeDoanhThu() {
		List<HoaDon> list = new ArrayList<HoaDon>();

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select [ngayLapHD] , sum([tongTien]) as doanhThu from HoaDon group by [ngayLapHD] ";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setNgayLapHD(rs.getDate("ngayLapHD"));
				hd.setTongTien(rs.getDouble("doanhThu"));

				list.add(hd);

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
	 * mô tả : cập nhật ghi chú của hóa đơn khi biết mã hóa đơn
	 * 
	 * @param mahd
	 * @param ghiChu
	 * @return
	 */
	public boolean updateGhiChu(String mahd, String ghiChu) {

		int rSet = 0;
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "update HoaDon set ghiChu = ? where maHoaDon = ? ";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, ghiChu);
			statement.setString(2, mahd);

			rSet = statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rSet > 0;
	}

	/**
	 * mô tả : tìm kiếm các hóa đơn theo mã hóa đơn cho nv bán hàng
	 * 
	 * @param txtTimKiem
	 * @return List<HoaDon>
	 */

	public List<HoaDon> timHDTheoMa_NVBH(JTextField txtTimKiem, String maNV) {

		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from HoaDon where maHoaDon like ? and maNhanVien = '" + maNV + "'";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, "%" + txtTimKiem.getText() + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				hd.setNgayLapHD(rs.getDate("ngayLapHD"));

				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));

				hd.setMaKhachHang(kh);
				hd.setMaNhanVien(nv);
				hd.setTongTien(rs.getDouble("tongTien"));
				hd.setGhiChu(rs.getString("ghiChu"));

				list.add(hd);
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
	 * mô tả : tìm kiếm các hóa đơn theo mã hóa đơn cho quản lý
	 * 
	 * @param txtTimKiem
	 * @return List<HoaDon>
	 */

	public List<HoaDon> timHDTheoMa(JTextField txtTimKiem) {

		List<HoaDon> list = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from HoaDon where maHoaDon like ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, "%" + txtTimKiem.getText() + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				hd.setNgayLapHD(rs.getDate("ngayLapHD"));

				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));

				hd.setMaKhachHang(kh);
				hd.setMaNhanVien(nv);
				hd.setTongTien(rs.getDouble("tongTien"));
				hd.setGhiChu(rs.getString("ghiChu"));

				list.add(hd);
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
	 * mô tả : lấy tất cả các hóa đơn của 1 nhân viên khi biết mã nhân viên đó
	 * 
	 * @param maNV
	 * @return List<HoaDon>
	 */

	public List<HoaDon> getListByMaNV(String maNV) {
		List<HoaDon> list = new ArrayList<HoaDon>();

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from HoaDon where maNhanVien = '" + maNV + "' ";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				Date date = rs.getDate(3);

				hd.setNgayLapHD(date);

				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));

				hd.setMaKhachHang(kh);
				hd.setMaNhanVien(nv);
				hd.setTongTien(rs.getDouble("tongTien"));
				hd.setGhiChu(rs.getString("ghiChu"));
				list.add(hd);

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
	 * mô tả : lấy danh sách tất cả các hóa đơn
	 * 
	 * @return List<HoaDon>
	 */
	public List<HoaDon> getList() {
		List<HoaDon> list = new ArrayList<HoaDon>();

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from HoaDon";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(rs.getString("maHoaDon"));
				Date date = rs.getDate(3);

				hd.setNgayLapHD(date);

				NhanVien nv = new NhanVien(rs.getString("maNhanVien"));
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"));

				hd.setMaKhachHang(kh);
				hd.setMaNhanVien(nv);
				hd.setTongTien(rs.getDouble("tongTien"));
				hd.setGhiChu(rs.getString("ghiChu"));
				list.add(hd);

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
	 * mô tả : đếm số hóa đơn
	 * 
	 * @param maHoaDon
	 * @return int
	 */
	public int countSoHoaDon(String maHoaDon) {

		int rowCount = 0;
//		ResultSet rs = null;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "SELECT count(*) from HoaDon where maHoaDon like ?";

			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + maHoaDon + "%");
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
	 * mô tả : lấy ra một hóa đơn khi biết mã hóa đơn
	 * 
	 * @param maHD
	 * @return
	 */

	public ResultSet getByMaHD(String maHD) {

//		ResultSet rSet = null;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "select * from HoaDon where maHoaDon = ?";

			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + maHD + "%");
			ResultSet rSet = statement.executeQuery();
			return rSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

//	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
//		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
//		return sDate;
//	}

	/**
	 * mô tả : thêm một hóa đơn
	 * 
	 * @param hd
	 * @return
	 */
	public boolean themHD(HoaDon hd) {

		int rSet = 0;
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "set dateformat dmy INSERT INTO [dbo].[HoaDon]"
					+ "([maHoaDon],[maNhanVien],[ngayLapHD],[maKhachHang]," + "[tongTien],[ghiChu])"
					+ "VALUES(?,?,?,?,?,?)";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, hd.getMaHoaDon());
			statement.setString(2, hd.getMaNhanVien().getMaNhanVien());

			statement.setDate(3, hd.getNgayLapHD());

			statement.setString(4, hd.getMaKhachHang().getMaKhachHang());
			statement.setDouble(5, hd.getTongTien());
			statement.setString(6, hd.getGhiChu());

			rSet = statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rSet > 0;
	}

	/**
	 * mô tả : xóa một hóa đơn
	 * 
	 * @param mahd
	 * @return
	 */

	public boolean delete(String mahd) {

		int n = 0;

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement("delete from HoaDon where maHoaDon = ?");
			stmt.setString(1, mahd);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("loi xoa hoa don");
		}

		return n > 0;
	}

}
