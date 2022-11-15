/**
 * Nguyễn Phi Hoàng - 19443211 - Nhóm 03
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import connectDB.ConnectDB;
import entity.NhaCungCap;

public class NhaCungCapDao {

	private static final long serialVersionUID = 1L;

	/**
	 * lấy danh sách các nhà cung cấp
	 * @return
	 */
	public List<NhaCungCap> getList() {

		List<NhaCungCap> listNCC = new ArrayList<NhaCungCap>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "select * from NhaCungCap";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNCC(rs.getString("maNCC"));
				ncc.setTenNCC(rs.getString("tenNCC"));
				ncc.setDiaChi(rs.getString("diaChi"));
				ncc.setSdt(rs.getString("sdt"));
				ncc.setEmail(rs.getString("email"));

				listNCC.add(ncc);
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listNCC;

	}

	/**
	 * lấy mã và tên của tất cả nhà cung cấp
	 * @return
	 */
	public static ResultSet getList2TP() {

		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			String sql = "select maNCC, tenNCC from NhaCungCap";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			return rs;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	/**
	 * lấy ra tên nhà cung cấp khi biết mã nhà cung cấp
	 * @param ma
	 * @return
	 */
	public String laytentheoma(String ma) {
		// String tenNCC = new String();
		
		PreparedStatement stmt = null;
		String sql = " select tenNCC from NhaCungCap where maNCC=?";
		try {
			ConnectDB.getInstance().connect();;
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("tenNCC");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * xóa một nhà cung cấp
	 * @param maNCC
	 * @return
	 */
	public boolean delete(String maNCC) {
		
		PreparedStatement stmt = null;
		int n = 0;

		try {
			ConnectDB.getInstance().connect();;
			Connection con = ConnectDB.getConnetction();
			stmt = con.prepareStatement("DELETE FROM NhaCungCap where maNCC=?");
			stmt.setString(1, maNCC);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
		}
		return n > 0;
	}

	/**
	 * thêm mới một nhà cung cấp
	 * @param nhaCungCap
	 * @return
	 */
	public boolean create(NhaCungCap nhaCungCap) {
		int n = 0;
		try {
			ConnectDB.getInstance().connect();

			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement("INSERT INTO [dbo].[NhaCungCap] ([maNCC] ,[tenNCC] ,[diaChi],[sdt],[email]) VALUES(?,?,?,?,?)");

			stmt.setString(1, nhaCungCap.getMaNCC());
			stmt.setString(2, nhaCungCap.getTenNCC());
			stmt.setString(3, nhaCungCap.getDiaChi());
			stmt.setString(4, nhaCungCap.getSdt());
			stmt.setString(5, nhaCungCap.getEmail());

			n = stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n > 0;
	}

	/**
	 * lấy ra một nhà cung cấp khi biết mã nhà cung cấp
	 * @param idNCC
	 * @return
	 */
	public NhaCungCap getOne(String idNCC) {
		NhaCungCap nhaCungCap = new NhaCungCap();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT * FROM NhaCungCap WHERE maNCC = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, idNCC);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				nhaCungCap.setMaNCC(rs.getString("maNCC"));
				nhaCungCap.setTenNCC(rs.getString("tenNCC"));
				nhaCungCap.setSdt(rs.getString("sdt"));
				nhaCungCap.setDiaChi(rs.getString("diaChi"));
				nhaCungCap.setEmail(rs.getString("email"));

			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nhaCungCap;

	}

	/**
	 * lấy danh sách tất cả nhà cung cấp
	 * @return
	 */
	public List<NhaCungCap> getList_NCC() {

		List<NhaCungCap> listNCC = new ArrayList<NhaCungCap>();
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			String sql = "SELECT * FROM NhaCungCap";

			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				NhaCungCap nhaCungCap = new NhaCungCap();
				nhaCungCap.setMaNCC(rs.getString("maNCC"));
				nhaCungCap.setTenNCC(rs.getString("tenNCC"));
				nhaCungCap.setSdt(rs.getString("sdt"));
				nhaCungCap.setDiaChi(rs.getString("diaChi"));
				nhaCungCap.setEmail(rs.getString("email"));

				listNCC.add(nhaCungCap);
			}
			statement.close();
			rs.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listNCC;

	}

	/**
	 * lấy danh dách nhà cung cấp khi biết tên 
	 * @param txttim
	 * @return
	 */
	public List<NhaCungCap> getListNcc(JTextField txttim) {
		List<NhaCungCap> list = new ArrayList<NhaCungCap>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			String sql = "select * from NhaCungCap where tenNCC like ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, "%" + txttim.getText() + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNCC(rs.getString("maNCC"));
				ncc.setTenNCC(rs.getString("tenNCC"));
				ncc.setSdt(rs.getString("sdt"));
				ncc.setDiaChi(rs.getString("diaChi"));
				ncc.setEmail(rs.getString("email"));

				list.add(ncc);
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
	 * đếm số lượng nhà cung cấp
	 * @param maNCC
	 * @return
	 */
	public int countSoNhaCungCap(String maNCC) {
		int rowCount = 0;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnetction();
			PreparedStatement stmt = null;

			String sql = "SELECT count(*) from NhaCungCap where maNCC like ?";

			PreparedStatement statement;
			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + maNCC + "%");
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				rowCount=rs.getInt(1);
				return rowCount;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * cập nhật thông tin nhà cung cấp khi biết mã nhà cung cấp
	 * @param ncc
	 * @return
	 */
	public boolean suaNCC(NhaCungCap ncc) {
		
		
		int n=0;
		PreparedStatement stmt = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnetction();
			
			stmt = con.prepareStatement("update NhaCungCap set tenNCC = ?, diaChi = ?, sdt = ? ,email=? where maNCC = ?");
			
			stmt.setString(1, ncc.getTenNCC());
			stmt.setString(2, ncc.getDiaChi());
			stmt.setString(3, ncc.getSdt());
			stmt.setString(4, ncc.getEmail());
			stmt.setString(5, ncc.getMaNCC());
			
			n = stmt.executeUpdate();
			} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		return n>0;
	}

}
