/**
 * Đoàn Kiều Mỹ Ngọc -19446111 - nhóm 03
 * 
 *  mô tả lớp :viết các hàm truy vấn  dữ liệu từ database Sách
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
import javax.swing.JTable;
import javax.swing.JTextField;

import entity.NhaCungCap;
import entity.Sach;
import entity.TheLoai;

import connectDB.ConnectDB;

public class SachDao {
	private static final long serialVersionUID = 1L;
	TheLoaiDao TLdao = new TheLoaiDao();
	NhaCungCapDao nCCdaoCapDao = new NhaCungCapDao();

	/**
	 * lấy danh sách tất cả sản phẩm
	 * 
	 * @param getList
	 * @return Danh sách sản phẩm sách
	 */
	public List<Sach> getList() {

		List<Sach> list = new ArrayList<Sach>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql =

					"SELECT Sach.maSach, Sach.tenSach, TheLoai.maLoai, TheLoai.tenTheLoai, Sach.donGia, Sach.namXuatBan, \r\n"
							+ "					Sach.namSanXuat, NhaCungCap.maNCC, Sach.tenTacGia, Sach.soLuong\r\n"
							+ "					FROM   Sach JOIN TheLoai ON Sach.maLoai = TheLoai.maLoai join NhaCungCap on NhaCungCap.maNCC=Sach.maNCC";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Sach sach = new Sach();
				sach.setMaSach(rs.getString("maSach"));
				sach.setTenSach(rs.getString("tenSach"));
				TheLoai maLoai = new TheLoai(rs.getString("maloai"));
				// TheLoai tenLoai = new TheLoai(rs.getString("tenTheLoai"));

				sach.setMaLoai(maLoai);
				sach.setDonGia(rs.getDouble("donGia"));
				sach.setNamXB(rs.getInt("namXuatBan"));
				sach.setNamSX(rs.getInt("namSanXuat"));

				NhaCungCap maNhaCungCap = new NhaCungCap(rs.getString("maNCC"));

				sach.setnCC(maNhaCungCap);
				sach.setTenTacGia(rs.getString("tenTacGia"));
				sach.setSoLuong(rs.getInt("soLuong"));

				list.add(sach);
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
	 * tìm kiếm sách khi biết tên gần đúng
	 * 
	 * @param timSachTheoTen
	 * @return danh sách sản phẩm tìm kiếm được theo tên Sách
	 */

	public List<Sach> timSachTheoTen(JTextField txtTimKiem) {

		List<Sach> list = new ArrayList<Sach>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from Sach where tenSach like ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, "%" + txtTimKiem.getText() + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Sach sach = new Sach();
				sach.setMaSach(rs.getString("maSach"));
				sach.setTenSach(rs.getString("tenSach"));
				sach.setDonGia(rs.getDouble("donGia"));
				sach.setSoLuong(rs.getInt("soLuong"));

				list.add(sach);
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
	 * tìm kiếm sách khi biết mã sách
	 * 
	 * @param timSachTheoMa
	 * @return Danh sách sản phẩm sach trong giao diện bán hàng
	 */
	public List<Sach> timSachTheoMa(JTextField txtTimKiem) {

		List<Sach> list = new ArrayList<Sach>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from Sach where maSach = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, txtTimKiem.getText());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Sach sach = new Sach();
				sach.setMaSach(rs.getString("maSach"));
				sach.setTenSach(rs.getString("tenSach"));
				sach.setDonGia(rs.getDouble("donGia"));
				sach.setSoLuong(rs.getInt("soLuong"));

				list.add(sach);
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
	 * tìm kiếm sách khi biết tên gần đúng
	 * 
	 * @param timSachTheoTen2
	 * @return danh sách sản phẩm tìm kiếm được theo tên Sách
	 */
	public List<Sach> timSachTheoTen2(JTextField txtTimKiem) {

		List<Sach> list = new ArrayList<Sach>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from Sach where tenSach like ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, "%" + txtTimKiem.getText() + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Sach sach = new Sach();
				sach.setMaSach(rs.getString("maSach"));
				sach.setTenSach(rs.getString("tenSach"));
				TheLoai maLoai = new TheLoai(rs.getString("maloai"));
				// TheLoai tenLoai = new TheLoai(rs.getString("tenTheLoai"));

				sach.setMaLoai(maLoai);
				sach.setDonGia(rs.getDouble("donGia"));
				sach.setNamXB(rs.getInt("namXuatBan"));
				sach.setNamSX(rs.getInt("namSanXuat"));

				NhaCungCap maNhaCungCap = new NhaCungCap(rs.getString("maNCC"));

				sach.setnCC(maNhaCungCap);
				sach.setTenTacGia(rs.getString("tenTacGia"));
				sach.setSoLuong(rs.getInt("soLuong"));

				list.add(sach);
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
	 * lấy danh sách các sản phẩm sách
	 * 
	 * @param getList_Sach_BanHang
	 * @return danh sách Sản phẩm sách trong giao diện bán hàng
	 */
	public List<Sach> getList_Sach_BanHang() {

		List<Sach> list = new ArrayList<Sach>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT maSach, tenSach, soLuong, donGia" + "FROM   Sach";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Sach sach = new Sach();
				sach.setMaSach(rs.getString("maSach"));
				sach.setTenSach(rs.getString("tenSach"));
				sach.setSoLuong(rs.getInt("soLuong"));
				sach.setDonGia(rs.getDouble("donGia"));

				list.add(sach);
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
	 * tìm kiếm sách theo thể loại
	 * 
	 * @param getSachTheoTheLoai
	 * @return danh sách sản phẩm theo mã thể loại sách
	 */
	public List<Sach> getSachTheoTheLoai(String maTheloai) {

		List<Sach> list = new ArrayList<Sach>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from Sach where maLoai = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maTheloai);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Sach sach = new Sach();
				sach.setMaSach(rs.getString("maSach"));
				sach.setTenSach(rs.getString("tenSach"));
				sach.setDonGia(rs.getDouble("donGia"));
				sach.setSoLuong(rs.getInt("soLuong"));

				list.add(sach);
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(new JFrame(), "looi tim kiem theo the loai");
		}

		return list;

	}

	/**
	 * tìm kiếm sách theo mã sách
	 * 
	 * @param getOne
	 * @return lấy một sản phẩm khi biết mã sách
	 */
	public Sach getOne(String idSach) {
		Sach sach = new Sach();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT * from Sach where maSach = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, idSach);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
//				Sach sach = new Sach();
				sach.setMaSach(rs.getString("maSach"));
				sach.setTenSach(rs.getString("tenSach"));
				TheLoai maLoai = new TheLoai(rs.getString("maloai"));

				sach.setMaLoai(maLoai);
				sach.setDonGia(rs.getDouble("donGia"));
				sach.setNamXB(rs.getInt("namXuatBan"));
				sach.setNamSX(rs.getInt("namSanXuat"));
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				sach.setnCC(ncc);
				sach.setTenTacGia(rs.getString("tenTacGia"));
				sach.setSoLuong(rs.getInt("soLuong"));

//				return sach;

			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sach;

	}

	/**
	 * cập nhập thông tin sách
	 * 
	 * @param update
	 * @return cập nhật lại một sản phẩm sách
	 */
	public boolean update(Sach s) {

		PreparedStatement stmt = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement(
					"update Sach set tenSach=?, maLoai=?,  donGia=?, namXuatBan=?, namSanXuat=?, maNCC=?, tenTacGia=?, soLuong=? where maSach=?");
			stmt.setString(1, s.getTenSach());

			stmt.setString(2, s.getMaLoai().getMaLoai());
			stmt.setDouble(3, s.getDonGia());
			stmt.setInt(4, s.getNamXB());
			stmt.setInt(5, s.getNamSX());

			stmt.setString(6, s.getnCC().getMaNCC());

			stmt.setString(7, s.getTenSach());
			stmt.setInt(8, s.getSoLuong());
			stmt.setString(9, s.getMaSach());

			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return n > 0;

	}

	/**
	 * xóa một sách khi biết mã sách
	 * 
	 * @param delete
	 * @return xóa một sản phẩm sách
	 */
	public boolean delete(String maSach) {

		PreparedStatement stmt = null;
		int n = 0;

		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement("delete from Sach where maSach=?");
			stmt.setString(1, maSach);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
		}
		return n > 0;
	}

	/**
	 * thêm mới một sách
	 * 
	 * @param create
	 * @return tạo mới một sản phẩm sách
	 */
	public boolean create(Sach s) {

		PreparedStatement stmt = null;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement("insert into Sach values (?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, s.getMaSach());
			stmt.setString(2, s.getTenSach());
			stmt.setString(3, s.getMaLoai().getMaLoai());
			stmt.setDouble(4, s.getDonGia());
			stmt.setInt(5, s.getNamXB());
			stmt.setInt(6, s.getNamSX());
			stmt.setString(7, s.getnCC().getMaNCC());
			stmt.setString(8, s.getTenTacGia());
			stmt.setInt(9, s.getSoLuong());
			n = stmt.executeUpdate();

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * 
	 * đếm số lượng sách
	 * 
	 * @param countSach
	 * @return số lượng sách
	 */
	public int countSach(String maSach) {
		int rowCount = 0;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "SELECT count(*) from Sach where maSach like ?";

			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + maSach + "%");
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				rowCount = rs.getInt(1);
				return rowCount;
			}
			statement.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

}
