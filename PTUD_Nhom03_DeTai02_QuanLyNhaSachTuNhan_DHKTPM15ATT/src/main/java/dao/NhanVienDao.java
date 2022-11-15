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

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;

public class NhanVienDao {

	private static final long serialVersionUID = 1L;

	/**
	 * Nguyễn Viết Học - 19533591 - nhóm 03 hàm kiểm tra đăng nhập vào hệ thống
	 * 
	 * @param tenDN
	 * @param matkhau
	 * @return
	 */
	public NhanVien dangNhap(String tenDN, String matkhau) {

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();

			String sql = "select * from NhanVien nv join TaiKhoan tk on nv.maNhanVien = tk.maNhanVien where tenDangNhap = ?  ";

			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, tenDN);
//			statement.setString(2,matKhau);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {

				if (rs.getString("matKhau").equals(matkhau)) {

					JOptionPane.showMessageDialog(new JFrame(), "Đăng nhập thành công");
					String chucvu = rs.getString("chucVu");

					return getNhanVienTheoMa(rs.getString("maNhanVien"));
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Mật Khẩu Không chính xác");
					return null;
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Tên tài khoản không chính xác");
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "Đăng nhập thất bại");
		}

		return null;

	}

	/**
	 * tìm một nhân viên khi biết mã nhân viên
	 * 
	 * @param maNV
	 * @return
	 */
	public NhanVien getNhanVienTheoMa(String maNV) {

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from NhanVien where maNhanVien = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maNV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNhanVien(rs.getString("maNhanVien"));
				nv.setTenNhanVien(rs.getString("tenNhanVien"));
				nv.setNgaySinh(rs.getDate("ngaySinh"));
				nv.setNgayDauLamViec(rs.getDate("ngayDauLamViec"));
				nv.setSdt(rs.getString("sdt"));
				nv.setEmail(rs.getString("email"));
				nv.setGioiTinh(rs.getBoolean("gioiTinh"));
				nv.setDiaChi(rs.getString("diaChi"));
				nv.setChucVu(rs.getString("chucVu"));

				return nv;
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	/**
	 * lấy danh sách toàn bộ nhân viên
	 * 
	 * @return
	 */
	public List<NhanVien> getList() {

		List<NhanVien> listNV = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select * from NhanVien";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNhanVien(rs.getString("maNhanVien"));
				nv.setTenNhanVien(rs.getString("tenNhanVien"));
				nv.setNgaySinh(rs.getDate("ngaySinh"));
				nv.setNgayDauLamViec(rs.getDate("ngayDauLamViec"));
				nv.setSdt(rs.getString("sdt"));
				nv.setEmail(rs.getString("email"));
				nv.setGioiTinh(rs.getBoolean("gioiTinh"));
				nv.setDiaChi(rs.getString("diaChi"));
				nv.setChucVu(rs.getString("chucVu"));

				listNV.add(nv);
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listNV;

	}

	/**
	 * thêm một nhân viên mới
	 * 
	 * @param nv
	 * @return
	 */
	public boolean create(NhanVien nv) {
		PreparedStatement stmt;
		int n = 0;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement("insert into NhanVien values (?,?,?,?,?,?,?,?,?)");

			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getTenNhanVien());
			stmt.setDate(3, nv.getNgaySinh());
			stmt.setDate(4, nv.getNgayDauLamViec());
			stmt.setString(5, nv.getSdt());
			stmt.setString(6, nv.getEmail());
			stmt.setBoolean(7, nv.isGioiTinh());
			stmt.setString(8, nv.getDiaChi());
			stmt.setString(9, nv.getChucVu());

			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	/**
	 * xóa một nhân viên khi biết mã nhân viên
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
			stmt = con.prepareStatement("delete from NhanVien where maNhanVien = ? ");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("lỗi xóa nhan vien trong sql");
		}

		return n > 0;
	}

	/**
	 * cập nhật thông tin nhân viên khi biết mã nhân viên
	 * @param nv
	 * @return
	 */
	public boolean update(NhanVien nv) {

		int n = 0;
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt;
			stmt = con.prepareStatement("UPDATE NhanVien set tenNhanVien = ?,ngaySinh = ? ,ngayDauLamViec = ?,sdt = ?,"
					+ "email = ?,gioiTinh = ?,diaChi = ?,chucVu = ? where maNhanVien = ?");
			stmt.setString(1, nv.getTenNhanVien());
			stmt.setDate(2, nv.getNgaySinh());
			stmt.setDate(3, nv.getNgayDauLamViec());
			stmt.setString(4, nv.getSdt());
			stmt.setString(5, nv.getEmail());
			stmt.setBoolean(6, nv.isGioiTinh());
			stmt.setString(7, nv.getDiaChi());
			stmt.setString(8, nv.getChucVu());
			stmt.setString(9, nv.getMaNhanVien());

			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	/**
	 * tìm kiếm nhân viên khi biết tên nhân viên
	 * @param txtTimNhanVien
	 * @return
	 */
	public List<NhanVien> timKiemTheoTen(JTextField txtTimNhanVien) {
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "select * from NhanVien where tenNhanVien like ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, "%" + txtTimNhanVien.getText() + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNhanVien(rs.getString("maNhanVien"));
				nv.setTenNhanVien(rs.getString("tenNhanVien"));
				nv.setNgaySinh(rs.getDate("ngaySinh"));
				nv.setNgayDauLamViec(rs.getDate("ngayDauLamViec"));
				nv.setSdt(rs.getString("sdt"));
				nv.setEmail(rs.getString("email"));
				nv.setGioiTinh(rs.getBoolean("gioiTinh"));
				nv.setDiaChi(rs.getString("diaChi"));
				nv.setChucVu(rs.getString("chucVu"));

				listNV.add(nv);
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(new JFrame(), "Lỗi tìm kiếm nhân viên");
		}

		return listNV;
	}

	/**
	 * đếm số lượng nhân viên
	 * @param maNV
	 * @return
	 */
	public int countSoNhanVien(String maNV) {
		int rowCount = 0;

		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "SELECT count(*) from NhanVien where maNhanVien like ?";

			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + maNV + "%");
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
