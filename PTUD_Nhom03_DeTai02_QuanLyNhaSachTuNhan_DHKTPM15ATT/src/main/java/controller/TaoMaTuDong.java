/**
 * Nguyễn Viết Học - 19533591 - Nhóm 03 
 * 
 * Mô tả lớp : Tạo các mã số tự động tăng thông qua đếm số lượng các đối tượng đã có trong dữ liệu
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.HoaDonDAO;
import dao.KhachHangDao;
import dao.NhaCungCapDao;
import dao.NhanVienDao;
import dao.SachDao;
import dao.TheLoaiDao;

public class TaoMaTuDong {

	private HoaDonDAO hoaDonDAO = new HoaDonDAO();
	private KhachHangDao khachHangDao = new KhachHangDao();
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private NhaCungCapDao NCCDao = new NhaCungCapDao();
	private SachDao sachDao = new SachDao();
	private TheLoaiDao theLoaiDao = new TheLoaiDao();

	public TaoMaTuDong() {

	}

	// tạo mã hóa đơn
	public String taoSoHD() {

		String maHoaDon = "";

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		maHoaDon = "HD" + dateFormat.format(date);
//			System.out.println("soHd" + maHoaDon);
		int rowCount = hoaDonDAO.countSoHoaDon(maHoaDon);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maHoaDon = maHoaDon + (rowCount + 1);
			} else if (rowCount > 8) {
				maHoaDon = maHoaDon + "0" + (rowCount + 1);
			} else {
				maHoaDon = maHoaDon + "00" + (rowCount + 1);
			}

		} while (dup);

		return maHoaDon;

	}

//tạo mã cho dl KH
	public String taoMaKhachHang() {

		String maKhachHang = "KH";
		int rowCount = khachHangDao.countSoKhachHang(maKhachHang);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maKhachHang = maKhachHang + (rowCount + 1);
			} else if (rowCount > 8) {
				maKhachHang = maKhachHang + "0" + (rowCount + 1);
			} else {
				maKhachHang = maKhachHang + "00" + (rowCount + 1);
			}

		} while (dup);

		return maKhachHang;

	}

	// tạo ma the loai
	public String taoMaTheLoai() {

		String maTheLoai = "TL";
		int rowCount = theLoaiDao.countTheLoai(maTheLoai);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maTheLoai = maTheLoai + (rowCount + 1);
			} else if (rowCount > 8) {
				maTheLoai = maTheLoai + "0" + (rowCount + 1);
			} else {
				maTheLoai = maTheLoai + "00" + (rowCount + 1);
			}

		} while (dup);

		return maTheLoai;

	}

	// tạo mã cho tbl KH
	public String taoMaKhachHang1() {

		String maKhachHang = "KH";
		int rowCount = khachHangDao.countSoKhachHang(maKhachHang);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maKhachHang = maKhachHang + rowCount;
			} else if (rowCount > 8) {
				maKhachHang = maKhachHang + "0" + rowCount;
			} else {
				maKhachHang = maKhachHang + "00" + rowCount;
			}

		} while (dup);

		return maKhachHang;

	}

//tao ma cho dl nhan vien
	public String taoMaNhanVien() {
		String maNV = "NV";

		int rowCount = nhanVienDao.countSoNhanVien(maNV);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maNV = maNV + (rowCount + 1);
			} else if (rowCount > 8) {
				maNV = maNV + "0" + (rowCount + 1);
			} else {
				maNV = maNV + "00" + (rowCount + 1);
			}

		} while (dup);

		return maNV;

	}

	// tạo mã cho tbl nhân viên
	public String taoMaNhanVien1() {
		String maNV = "NV";

		int rowCount = nhanVienDao.countSoNhanVien(maNV);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maNV = maNV + rowCount;
			} else if (rowCount > 8) {
				maNV = maNV + "0" + rowCount;
			} else {
				maNV = maNV + "00" + rowCount;
			}

		} while (dup);

		return maNV;

	}

//Tao ma cho dl NCC
	public String taoMaNhaCungCap() {
		String maNCC = "NCC";

		int rowCount = NCCDao.countSoNhaCungCap(maNCC);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maNCC = maNCC + (rowCount + 1);
			} else if (rowCount > 8) {
				maNCC = maNCC + "0" + (rowCount + 1);
			} else {
				maNCC = maNCC + "00" + (rowCount + 1);
			}

		} while (dup);

		return maNCC;

	}

	// Tao ma cho tbl NCC
	public String taoMaNhaCungCap1() {
		String maNCC = "NCC";

		int rowCount = NCCDao.countSoNhaCungCap(maNCC);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maNCC = maNCC + rowCount;
			} else if (rowCount > 8) {
				maNCC = maNCC + "0" + rowCount;
			} else {
				maNCC = maNCC + "00" + rowCount;
			}

		} while (dup);

		return maNCC;

	}

	// tạo mã cho dl sach
	public String taoMaSach() {
		String maSach = "SA";

		int rowCount = sachDao.countSach(maSach);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maSach = maSach + (rowCount + 1);
			} else if (rowCount > 8) {
				maSach = maSach + "0" + (rowCount + 1);
			} else {
				maSach = maSach + "00" + (rowCount + 1);
			}

		} while (dup);

		return maSach;
	}

	// tạo mã cho tbl sach
	public String taoMaSach1() {
		String maSach = "SA";

		int rowCount = sachDao.countSach(maSach);

		boolean dup = false;
		do {
			if (rowCount > 98) {
				maSach = maSach + rowCount;
			} else if (rowCount > 8) {
				maSach = maSach + "0" + rowCount;
			} else {
				maSach = maSach + "00" + rowCount;
			}

		} while (dup);

		return maSach;
	}

}
