CREATE DATABASE CTQLSach


USE CTQLSach
use master
drop database CTQLSach

 CREATE TABLE NhanVien(
   maNhanVien  NVARCHAR (50) primary key ,
   tenNhanVien NVARCHAR (80) NOT NULL,  
   ngaySinh DATE not null,
   ngayDauLamViec Date not null,
   sdt varchar(12) ,
   email nvarchar(50),
   gioiTinh BIT not null,
   diaChi NVARCHAR (80) not NULL,
   chucVu NVARCHAR (50) NOT NULL

);
CREATE TABLE TaiKhoan(
   maNhanVien NVARCHAR(50) primary key  foreign key references NhanVien(maNhanVien),
   tenDangNhap NVARCHAR (80) NOT NULL,
   matKhau NVARCHAR (50) NOT NULL,
   
);
 CREATE TABLE KhachHang(
   maKhachHang  NVARCHAR (50) primary key,
   tenKhachHang NVARCHAR (80) NOT NULL,  
   sdt varchar(12) NULL,
   diaChi NVARCHAR (80) NULL

);
CREATE TABLE NhaCungCap(
   maNCC NVARCHAR(50) primary key,
   tenNCC NVARCHAR (80) NOT NULL,
   diaChi NVARCHAR (80)  NULL,
   sdt NVARCHAR (50)  NULL,
   email nvarchar(50) null
);
 CREATE TABLE TheLoai(
   maLoai  NVARCHAR (50) primary key,
   tenTheLoai NVARCHAR (80) NOT NULL
);
CREATE TABLE Sach(
   maSach NVARCHAR(10) primary key,
   tenSach NVARCHAR (80) NOT NULL,
   maLoai NVARCHAR (50) NOT NULL foreign key references TheLoai(maLoai),
   donGia money not null,
   namXuatBan int NULL,
   namSanXuat int null,
   maNCC NVARCHAR(50) foreign key references NhaCungCap(maNCC),
   tenTacGia nvarchar(80) not null,
   soLuong int not null
);
 CREATE TABLE HoaDon(
   maHoaDon  NVARCHAR (50) primary key,
   maNhanVien NVARCHAR (50) NOT NULL foreign key references NhanVien(maNhanVien),  
   ngayLapHD DATE not null,
   maKhachHang NVARCHAR (50) foreign key references KhachHang(maKhachHang),
   tongTien money ,
   ghiChu nvarchar(100)

);
 CREATE TABLE CT_HoaDon(
   maHoaDon  NVARCHAR (50) not null ,
   maSach NVARCHAR(10) not null ,
   soLuong int ,
   donGia money,
   thanhTien money

);

ALTER TABLE CT_HoaDon ADD CONSTRAINT CTHD_PK PRIMARY KEY (maHoaDon,maSach);
Alter Table CT_HoaDon add constraint CTHD_FK foreign key (maHoaDon) references HoaDon (maHoaDon)
Alter Table CT_HoaDon add constraint CTHDS_FK foreign key (maSach) references Sach (maSach)
---------
set dateformat dmy
delete from TaiKhoan
delete from NhanVien
delete from Sach
delete from KhachHang
delete from HoaDon
delete from CT_HoaDon
delete from NhaCungCap
delete from TheLoai

select * from HoaDon


-------------
INSERT [dbo].[NhanVien] ([maNhanVien], [chucVu], [diaChi], [email], [gioiTinh], [ngayDauLamViec], [ngaySinh], [sdt], [tenNhanVien]) VALUES (N'NV001', N'bán hàng', N'88 Hùng Vương', N'nphoang@gmail.com', 1, CAST(N'2017-06-03' AS Date), CAST(N'1999-11-05' AS Date), N'088888868', N'Nguyễn Phi Hoàng')
INSERT [dbo].[NhanVien] ([maNhanVien], [chucVu], [diaChi], [email], [gioiTinh], [ngayDauLamViec], [ngaySinh], [sdt], [tenNhanVien]) VALUES (N'NV002', N'quản lý', N'93 Hoàng Văn Thụ', N'nvhoc@gmail.com', 1, CAST(N'2018-05-13' AS Date), CAST(N'2001-01-13' AS Date), N'0123456789', N'Nguyễn Viết Học')
INSERT [dbo].[NhanVien] ([maNhanVien], [chucVu], [diaChi], [email], [gioiTinh], [ngayDauLamViec], [ngaySinh], [sdt], [tenNhanVien]) VALUES (N'NV003', N'bán hàng', N'61 Đoàn Thị Điểm', N'dkmngoc@gmail.com', 0, CAST(N'2018-10-26' AS Date), CAST(N'1998-08-25' AS Date), N'0868686868', N'Đoàn Kiều Mỹ Ngọc')
INSERT [dbo].[NhanVien] ([maNhanVien], [chucVu], [diaChi], [email], [gioiTinh], [ngayDauLamViec], [ngaySinh], [sdt], [tenNhanVien]) VALUES (N'NV004', N'bán hàng', N'34 Hồ Tùng Mậu HN', N'nphlong@gmail.com', 1, CAST(N'2019-09-30' AS Date), CAST(N'1995-07-17' AS Date), N'0682328626', N'Nguyễn Phạm Hoàng Long')
INSERT [dbo].[NhanVien] ([maNhanVien], [chucVu], [diaChi], [email], [gioiTinh], [ngayDauLamViec], [ngaySinh], [sdt], [tenNhanVien]) VALUES (N'NV005', N'bán hàng', N'13 Nguyễn Ái Quốc - Biên Hòa- Đồng Nai', N'thanhnhan@gmail.com', 1, CAST(N'2021-11-15' AS Date), CAST(N'2000-04-11' AS Date), N'0678654320', N'Đoàn Ngọc Thành Nhân')
INSERT [dbo].[NhanVien] ([maNhanVien], [chucVu], [diaChi], [email], [gioiTinh], [ngayDauLamViec], [ngaySinh], [sdt], [tenNhanVien]) VALUES (N'NV006', N'bán hàng', N'45 Nguyễn Văn Nghj - TP HCM', N'anhquoc22@gmail.com', 1, CAST(N'2021-11-01' AS Date), CAST(N'2001-08-14' AS Date), N'0876578432', N'Hoàng Anh Quốc')
GO

INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau], [tenDangNhap]) VALUES (N'NV001', N'123456', N'nphoang')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau], [tenDangNhap]) VALUES (N'NV002', N'123456', N'nvhoc')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau], [tenDangNhap]) VALUES (N'NV003', N'123456', N'dkmngoc')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau], [tenDangNhap]) VALUES (N'NV004', N'123456', N'nphlong')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau], [tenDangNhap]) VALUES (N'NV005', N'123456', N'dntnhan')
INSERT [dbo].[TaiKhoan] ([maNhanVien], [matKhau], [tenDangNhap]) VALUES (N'NV006', N'123456', N'haquoc')

INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH001', N'56 CMT8', N'0294724683', N'Hoàng Thùy Linh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH002', N'74 Bùi Đạt', N'0327487012', N'Trịnh Thu Thảo')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH003', N'127 Phan Đình Phùng', N'0242401792', N'Nguyễn Văn A')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH004', N'272 Lương Khánh Thiện', N'0974824992', N'Trần Ngọc')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH005', N'01 Hồ Thị Kỷ 1', N'029447289284', N'Thái Thị Liễu')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH006', N'Bình Dương', N'0975883344', N'Đoàn Ngọc Thành Nhân')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH007', N'Bình Phước', N'0864735647', N'Vy Đan')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH008', N'Thanh Hóa', N'0846372854', N'Hoàng Vy')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH009', N'12 Nguyễn Công Trí', N'0674837264', N'Minh Thanh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH010', N'33 Phan Đình Phùng Hầ Nội', N'0787432467', N'Văn Hóa An')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH011', N'14 Nguyễn Văn bảo Gò vấp', N'0876578969', N'Thanh Hưng ')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH012', N'40 Phan Bội Châu', N'0745879475', N'Phạm Ngà')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH013', N'12 Phạm Ngũ Lão HCM', N'0986325472', N'Đan Ny')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH014', N'67 Dương Quản hàm', N'0867458657', N'Văn Hùng')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH015', N'14 Nguyễn Oanh HCM', N'07854637485', N'Kiều Anh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH016', N'44 Nguyễn văn Nghi', N'0477453456', N'Anh Thy')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH017', N'98 Hồ Thị Kỷ', N'08796786567', N'Thanh Vy')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH018', N'45 Nguyễn Thái Sơn', N'0758495745', N'Na Na')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH019', N'Bình Chánh HCM', N'0785647384', N'Hồ Ngọc hà')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH020', N'Tân Bình HCM', N'078678566', N'Quế Lương')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH021', N'Bình Dương', N'0946536173', N'Thanh Vũ')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH022', N'Quảng Trị', N'0876785656', N'Ngọc Ánh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH023', N'Hà Nội', N'0876783342', N'Thúy Vân')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH024', N'Bắc Tấn Uyên -Bình Dương', N'0777453310', N'Nguyễn Thị Sang Sang')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [sdt], [tenKhachHang]) VALUES (N'KH025', N'Quảng Trị', N'0987674563', N'Anh Tiến')

INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC001', N' Số 55 Quang Trung, Nguyễn Du, Hai Bà Trưng, Hà Nội', N'cskh_online@nxbkimdong.com.vn', N'1900571595', N'Nhà xuất bản Kim Đồng')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC002', N'Quận 3  TP Hồ Chí Minh', N'hopthubandoc@nxbtre.com.vn', N'1938437450', N'Nhà Xuất Bản Trẻ')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC003', N'Nhà NV22  Khu 12  Ngõ 13 Lĩnh Nam  P. Mai Động  Q. Hoàng Mai  TP. Hà Nội', N'contacts@dinhtibooks.vn', N'2473093388', N'Công ty TNHH Thương mại và Dịch vụ Văn hoá Đinh Tị')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC004', N'Số 43 Đường D4 Khu Him Lam Kênh Tẻ Phường Tân Hưng Quận 7 Hồ Chí Minh ', N'hanhthuan@gmail.com', N'0310576040', N'CÔNG TY TNHHMTV TM HẠNH THUẬN ')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC005', N'59 Đỗ Quang, phường Trung Hoà, quận Cầu Giấy, Hà Nội', N'nhanam@gmail.com', N'9101603420', N'Công Ty Cổ Phần Văn Hoá và Truyền Thông Nhã Nam')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC006', N'449 Bạch Mai  Hai Bà Trưng  Hà Nội', N'tanviet@gmail.com', N'0812757799', N'Nhà sách Tân Việt')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC007', N'Tầng 3  Dream Home Center  11a ngõ 282 Nguyễn Huy Tưởng Thanh Xuân  Hà Nội', N'info@alphabooks.vn', N'0932329959', N'Công ty cổ phần Sách Alpha')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC008', N'Số 19  21 Lô B Đường Trường Sơn Phường 15 Quận 10 Hồ Chí Minh', N'thienlong@gmail.com', N'0287543728', N'Công ty CP Tập đoàn Thiên Long Hoàn Cầu ')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC009', N' 808 Đường Láng Phường Láng Thượng Quận Đống Đa Hà Nội', N'contact@nhasachminhthang.vn', N'0101883129', N'Nhà sách Minh thắng')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC010', N'224 đường 28 Phường Bình Trị Đông B Quận Bình Tân Thành phố Hồ Chí Minh Việt Nam', N'toanphuc@gmail.com', N'0312945825', N'Dn Tư Nhân Thương Mại Toàn Phúc')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC011', N'110D Ngọc Hà  Phường Đội Cấn  Quận Ba Đình  Hà Nội', N'huyhoang@gmail.com', N'2839913636', N'CÔNG TY CỔ PHẦN VĂN HÓA HUY HOÀNG')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC012', N'Phố Sách Hà Nội  Phố 19 tháng 12  Hoàn Kiếm  Hà Nội', N'cskh@minhlongbook.vn', N'0989849396', N'Minh Long Book')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC013', N'Số 50  Đường 5  Khu tập thể F361  phố An Dương  P. Yên Phụ  Q.Tây Hồ  HN', N'tqtoanpc1988@gmail.com', N'0964484633', N'Công ty TNHH văn hóa và truyền thông AZ Việt Nam')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC014', N'tổ dân phố số 1 phường Phúc Diễn  quận Bắc Từ Liêm  Hà Nội', N'Sachthaiha@thaihabooks.com', N'2822532641', N'Thái Hà Books')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC015', N'113 Đông Các  P Ô Chợ Dừa  Q Đống Đa Hà Nội', N'dongahn@sachdonga.vn', N'2438569367', N'ĐÔNG A PUBLISHING')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC016', N'58 79 Nguyễn Minh Hoàng  Phường 12  Q Tân Bình', N'info@1980books.vn', N'0106610291', N'CÔNG TY VĂN HÓA VÀ TRUYỀN THÔNG 1980 BOOKS')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC017', N'720 16  18 Âu Cơ phường 14 quận Tân Bình Thành phố Hồ Chí Minh', N'toppoint.sales@gmail.com', N'0304948484', N'CÔNG TY TNHH MTV TM DV ĐỈNH ĐIỂM')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC018', N'Số nhà 9 ngõ 34 Xã Tả Thanh Oai Huyện Thanh Trì Thành phố Hà Nội,', N'minhphucbooks@gmail.com', N'0678657895', N'Minh Phúc Books')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC019', N'Số 19 phố Lê Hoàn Khu biệt thự Trường An P Thanh Bình  TP Hải Dương  T Hải Dương', N'gdductri@gmail.com', N'0203833688', N'TRUNG TÂM KH GIÁO DỤC VÀ ĐÀO TẠO ĐỨC TRÍ')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [email], [sdt], [tenNCC]) VALUES (N'NCC020', N'62 Nguyễn Thị Minh Khai  P Đa Kao Q1 Tp Hồ Chí Minh', N'tonghop@nxbhcm.com.vn', N'0938225340', N'NXB Tổng Hợp HCM')

INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL001', N'Thiếu Nhi')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL002', N'Giáo Khoa-Tham Khảo')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL003', N'Văn Học')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL004', N'Tâm Lý-Kỹ Năng Sống')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL005', N'Manga-Comic')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL006', N'Sách Học Ngoai Ngữ')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL007', N'Kinh Tế')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL008', N'Khoa Học Kĩ Thuật')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL009', N'Lịch Sử-Địa Lý-Tôn Giáo')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL010', N'Nuôi Dạy Con')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL011', N'Chính Trị-Pháp Lý-Triết Học')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL012', N'Nữ Công Gia Chánh')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL013', N'Tiểu Sử Hồi Ký')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL014', N'Phong Thủy-Kinh Dịch')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL015', N'Văn Hóa-Nghệ Thuật-Du Lịch')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL016', N'Từ Điển')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL017', N'Âm Nhạc-Mỹ Thuật-Thời Trang')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL018', N'Thể Dục Thể Thao-Giải Trí')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL019', N'Báo-Tạp Chí')
INSERT [dbo].[TheLoai] ([maLoai], [tenTheLoai]) VALUES (N'TL020', N'Giáo Trình')



INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat],[namXuatBan] , [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA001', 40000, 2020, 2018, 20, N'Lớp Học Mật Ngữ  Tập 19', N'B R O group', N'TL001', N'NCC002')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA002', 63750, 2020, 2019, 120, N'Hoàng Tử Bé', N'Antoine De Saint Exupery', N'TL001', N'NCC003')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA003', 4875, 2019, 2017, 200, N'Tập Tô Màu Nhà Trẻ  Chủ Đề Động Vật Dưới Nước', N'Trung Kiên', N'TL001', N'NCC003')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA004', 164000, 2019, 2021, 198, N'Sách Giáo Khoa Bộ Lớp 10', N'Bộ Giáo Dục Và Đào Tạo', N'TL001', N'NCC006')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA005', 29450, 2021, 2021, 200, N'Atlat Địa Lí Việt Nam', N'Bộ Giáo Dục Và Đào Tạo', N'TL001', N'NCC019')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA006', 180000, 2021, 2021, 89, N'Sách Giáo Khoa Bộ Lớp 12', N'Bộ Giáo Dục Và Đào Tạo', N'TL002', N'NCC015')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA007', 67150, 2021, 2021, 60, N'Nhà Giả Kim ', N'Paulo Coelho', N'TL003', N'NCC005')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA008', 147000, 2021, 2021, 55, N'Hai Số Phận', N'Jeffrey Archer', N'TL003', N'NCC006')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA009', 59250, 2021, 2021, 78, N'Your Name', N'Shinkai Makoto', N'TL003', N'NCC006')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA010', 148800, 2021, 2017, 88, N'Thay Đổi Cuộc Sống Với Nhân Số Học', N'Lê Đỗ Quỳnh Hương', N'TL004', N'NCC006')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA011', 53040, 2021, 2017, 53, N'Đi Tìm Lẽ Sống', N'Viktor E Frankl', N'TL004', N'NCC016')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA012', 70400, 2021, 2020, 34, N'Tôi Quyết Định Sống Cho Chính Tôi', N'Kim Suhyun', N'TL004', N'NCC016')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA013', 25000, 2021, 2020, 84, N'Nhà Có 5 Nàng Dâu Tập 2', N'Negi Haruba', N'TL005', N'NCC018')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA014', 19500, 2021, 2020, 255, N'One Piece Tập 96', N'Eiichiro Oda', N'TL005', N'NCC020')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA015', 30000, 2021, 2020, 67, N'Mob Psycho 100  Tập 6', N'ONE', N'TL005', N'NCC002')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA016', 82320, 2021, 2020, 66, N'Cẩm Nang Cấu Trúc Tiếng Anh', N'Trang Anh', N'TL006', N'NCC005')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA017', 28000, 2021, 2020, 90, N'600 Động Từ Bất Quy Tắc Và Cách Dùng Các Thì Trong Tiếng Anh', N'Trang Anh', N'TL006', N'NCC005')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA018', 40300, 2021, 2020, 90, N'Little Stories  To Help You Relax', N'Stacey Riches', N'TL006', N'NCC007')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA019', 55250, 2021, 2018, 33, N'Dạy Con Làm Giàu  Tập 1', N'Robert T Kiyosaki', N'TL007', N'NCC004')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA020', 71200, 2021, 2018, 66, N'Nhà Lãnh Đạo Không Chức Danh', N'Robin Sharma', N'TL007', N'NCC017')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA021', 100640, 2021, 2018, 86, N'Người Bán Hàng Vĩ Đại Nhất Thế Giới', N'Og Mandino', N'TL007', N'NCC014')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA022', 116000, 2021, 2018, 69, N'Giáo Trình C  Và Lập Trình Hướng Đối Tượng', N'Phạm Văn Ất   Lê Trường Thông', N'TL008', N'NCC014')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA023', 321000, 2021, 2018, 223, N'Luật Tâm Thức  Giải Mã Ma Trận Vũ Trụ', N'Ngô Sa Thạch', N'TL008', N'NCC016')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA024', 71200, 2021, 2020, 854, N'Thông Điệp Của Nước', N'Masaru Emoto', N'TL008', N'NCC006')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA025', 78000, 2021, 2020, 467, N'Các Triều Đại Việt Nam', N'Quỳnh Cư', N'TL009', N'NCC003')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA026', 87003, 2021, 2021, 965, N'Ngọc Sáng Trong Hoa Sen', N'John Blofeld', N'TL009', N'NCC003')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA027', 55440, 2021, 2021, 53, N'Nam Phương   Hoàng Hậu Cuối Cùng', N'Nam Phương   Hoàng Hậu Cuối Cùng', N'TL009', N'NCC016')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA028', 175055, 2021, 2018, 47, N'Ăn Dặm Không Phải Là Cuộc Chiến', N'Hachun Lyonnet', N'TL010', N'NCC019')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA029', 66099, 2021, 2018, 568, N'Bí Ẩn Của Não Phải ', N'GS Makoto Shichida', N'TL010', N'NCC019')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA030', 60000, 2021, 2018, 87, N'Nuôi Dạy Bé Gái Từ 0 Đến 6 Tuổi', N'Erika Takeuchi', N'TL010', N'NCC019')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA031', 48000, 2021, 2018, 67, N'Luật Đất Đai', N'Quốc Hội', N'TL011', N'NCC013')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA032', 88750, 2021, 2019, 76, N'Bộ Luật Tố Tụng Dân Sự', N'Quốc Hội', N'TL011', N'NCC013')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA033', 26000, 2021, 2019, 75, N'Luật Đầu Tư', N'Quốc Hội', N'TL011', N'NCC013')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA034', 86000, 2021, 2018, 66, N'Nhật Kí Học Làm bánh', N'Linh Trang', N'TL012', N'NCC013')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA035', 119000, 2021, 2018, 89, N'Skincare Chuyên Nghiệp', N'Clemence Von Mueffling', N'TL012', N'NCC015')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA036', 53444, 2021, 2018, 76, N'Tinh Tế Ẩm Thực Nhật Bản', N'Bìa Mềm', N'TL012', N'NCC015')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA037', 130000, 2021, 2018, 4643, N'Sống Mạo Hiểm Một Cách Cẩn Thận', N'Maye Musk', N'TL013', N'NCC016')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA038', 58000, 2021, 2018, 75, N'Trump  Đừng Bao Giờ Bỏ Cuộc', N'Donald JTrump', N'TL013', N'NCC016')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA039', 34, 2021, 2018, 76, N'Châu Nhuận Phát  Đại Hiệp Hồng Kông', N'Lin Feng', N'TL013', N'NCC014')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA040', 63009, 2021, 2018, 55, N'Cuộc Đời Kỳ Lạ Của Nikola Tesla', N'Nikola Tesla', N'TL013', N'NCC018')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA041', 300000, 2021, 2018, 30, N'Dịch Tượng Luận  Tử Vi Bí Kiếp ', N'Thu Giang Nguyễn Duy Cần', N'TL014', N'NCC018')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA042', 84000, 2021, 2018, 635, N'Xem Tướng Biết Người', N'Thiệu Vĩ Hoa', N'TL014', N'NCC017')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA043', 413000, 2021, 2018, 47, N'Câu Chuyện Thực Phẩm', N'DK', N'TL015', N'NCC017')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA044', 156000, 2021, 2018, 463, N'Câu Chuyện Nghệ Thuật', N'Susie Hodge', N'TL015', N'NCC017')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA045', 143000, 2021, 2018, 635, N'Đối Thoại Với Nền Văn Minh Cổ Champa', N'Lê Đình Phụng', N'TL015', N'NCC017')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA046', 62000, 2021, 2018, 543, N'Từ Điển Tiếng Việt', N'Nguyễn Tôn Nhan', N'TL016', N'NCC018')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA047', 89000, 2021, 2019, 534, N'Năm Thứ 1 Với Đàn Piano ', N'Ernest Van De Velde', N'TL017', N'NCC018')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA048', 76000, 2021, 2019, 534, N'Bước Đầu Hướng Dẫn Luyện Khí Công', N'Đỗ Đức Ngọc', N'TL018', N'NCC019')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA049', 20000, 2021, 2019, 34, N'Hoa Học Trò Số 1369 ', N'Nhiều Tác Giả', N'TL019', N'NCC019')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA050', 126000, 2021, 2019, 66, N'Pháp Luật Đại Cương', N'TS Lê Minh Toàn', N'TL020', N'NCC015')
INSERT [dbo].[Sach] ([maSach], [donGia], [namSanXuat], [namXuatBan], [soLuong], [tenSach], [tenTacGia], [maLoai], [maNCC]) VALUES (N'SA051', 20000, 2021, 2021, 50, N'Thiên Thần Nhỏ - Số 412 + 413', N'Nhiều Tác Giả', N'TL019', N'NCC017')
GO



INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211015001', N'', CAST(N'2021-10-15' AS Date), 219009, N'KH001', N'NV001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211015002', N'', CAST(N'2021-10-15' AS Date), 156000, N'KH002', N'NV001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211018001', N'', CAST(N'2021-10-18' AS Date), 607840, N'KH003', N'NV001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211018002', N'', CAST(N'2021-10-18' AS Date), 471625, N'KH004', N'NV002')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211020001', N'', CAST(N'2021-10-20' AS Date), 596855, N'KH005', N'NV001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211020002', N'', CAST(N'2021-10-20' AS Date), 335850, N'KH006', N'NV004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211104001', N'', CAST(N'2021-11-04' AS Date), 272625, N'Kh007', N'NV003')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211104002', N'', CAST(N'2021-11-04' AS Date), 411150, N'KH008', N'NV002')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211117001', N'', CAST(N'2021-11-17' AS Date), 290400, N'KH010', N'NV003')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211117002', N'', CAST(N'2021-11-17' AS Date), 510748, N'KH011', N'NV003')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211117003', N'', CAST(N'2021-11-17' AS Date), 1066000, N'KH012', N'NV003')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211121001', N'', CAST(N'2021-11-21' AS Date), 284478, N'KH013', N'NV003')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211121002', N'', CAST(N'2021-11-21' AS Date), 543100, N'KH014', N'NV004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211205001', N'', CAST(N'2021-12-05' AS Date), 567009, N'KH015', N'NV004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211205002', N'', CAST(N'2021-12-05' AS Date), 521200, N'KH016', N'NV004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211208001', N'', CAST(N'2021-12-08' AS Date), 2479000, N'KH017', N'NV004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211210001', N'', CAST(N'2021-12-10' AS Date), 484540, N'KH001', N'NV001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211217001', N'', CAST(N'2021-12-17' AS Date), 741009, N'KH019', N'NV003')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211217002', N'', CAST(N'2021-12-17' AS Date), 1717200, N'KH020', N'NV002')
INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHD], [tongTien], [maKhachHang], [maNhanVien]) VALUES (N'HD20211220001', N'', CAST(N'2021-12-20' AS Date), 813875, N'KH021', N'NV003')

INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211015001', N'SA040', 63009, 1, 63009)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211015001', N'SA044', 156000, 1, 156000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211015002', N'SA048', 76000, 1, 76000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211015002', N'SA049', 20000, 4, 80000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211018001', N'SA010', 148800, 1, 148800)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211018001', N'SA011', 53040, 6, 318240)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211018001', N'SA012', 70400, 2, 140800)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211018002', N'SA002', 63750, 1, 63750)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211018002', N'SA003', 4875, 1, 4875)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211018002', N'SA023', 321000, 1, 321000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211018002', N'SA046', 62000, 1, 62000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211018002', N'SA049', 20000, 1, 20000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211020001', N'SA008', 147000, 1, 147000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211020001', N'SA010', 148800, 1, 148800)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211020001', N'SA028', 175055, 1, 175055)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211020001', N'SA050', 126000, 1, 126000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211020002', N'SA005', 29450, 1, 29450)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211020002', N'SA006', 180000, 1, 180000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211020002', N'SA007', 67150, 1, 67150)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211020002', N'SA009', 59250, 1, 59250)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211104001', N'SA001', 40000, 1, 40000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211104001', N'SA002', 63750, 1, 63750)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211104001', N'SA003', 4875, 1, 4875)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211104001', N'SA004', 164000, 1, 164000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211104002', N'SA004', 164000, 1, 164000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211104002', N'SA006', 180000, 1, 180000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211104002', N'SA007', 67150, 1, 67150)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117001', N'SA004', 164000, 1, 164000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117001', N'SA007', 67150, 1, 67150)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117001', N'SA009', 59250, 1, 59250)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117002', N'SA007', 67150, 1, 67150)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117002', N'SA008', 147000, 1, 147000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117002', N'SA027', 55444, 1, 55444)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117002', N'SA028', 175055, 1, 175055)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117002', N'SA029', 66099, 1, 66099)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117003', N'SA006', 180000, 1, 180000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117003', N'SA041', 300000, 1, 300000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117003', N'SA042', 84000, 1, 84000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117003', N'SA043', 413000, 1, 413000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211117003', N'SA047', 89000, 1, 89000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121001', N'SA033', 26000, 1, 26000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121001', N'SA034', 86000, 1, 86000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121001', N'SA035', 119000, 1, 119000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121001', N'SA036', 53444, 1, 53444)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121001', N'SA039', 34, 1, 34)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121002', N'SA001', 40000, 1, 40000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121002', N'SA004', 164000, 1, 164000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121002', N'SA005', 29450, 1, 29450)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121002', N'SA006', 180000, 1, 180000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121002', N'SA009', 59250, 1, 59250)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211121002', N'SA012', 70400, 1, 70400)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205001', N'SA040', 63009, 1, 63009)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205001', N'SA042', 84000, 1, 84000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205001', N'SA045', 143000, 1, 143000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205001', N'SA046', 62000, 1, 62000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205001', N'SA047', 89000, 1, 89000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205001', N'SA050', 126000, 1, 126000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205002', N'SA004', 164000, 1, 164000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205002', N'SA007', 67150, 1, 67150)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205002', N'SA008', 147000, 1, 147000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205002', N'SA014', 19500, 1, 19500)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205002', N'SA017', 28000, 1, 28000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205002', N'SA018', 40300, 1, 40300)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211205002', N'SA019', 55250, 1, 55250)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211208001', N'SA041', 300000, 1, 300000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211208001', N'SA044', 156000, 13, 2028000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211208001', N'SA046', 62000, 1, 62000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211208001', N'SA047', 89000, 1, 89000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211210001', N'SA008', 147000, 1, 147000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211210001', N'SA010', 148800, 1, 148800)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211210001', N'SA011', 53040, 1, 53040)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211210001', N'SA012', 70400, 1, 70400)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211210001', N'SA013', 25000, 1, 25000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211210001', N'SA018', 40300, 1, 40300)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217001', N'SA040', 63009, 1, 63009)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217001', N'SA041', 300000, 1, 300000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217001', N'SA042', 84000, 1, 84000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217001', N'SA045', 143000, 1, 143000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217001', N'SA046', 62000, 1, 62000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217001', N'SA047', 89000, 1, 89000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA023', 321000, 1, 321000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA024', 71200, 1, 71200)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA025', 78000, 1, 78000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA041', 300000, 1, 300000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA042', 84000, 1, 84000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA043', 413000, 1, 413000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA044', 156000, 1, 156000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA045', 143000, 1, 143000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA046', 62000, 1, 62000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211217002', N'SA047', 89000, 1, 89000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211220001', N'SA003', 4875, 1, 4875)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211220001', N'SA004', 164000, 1, 164000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211220001', N'SA043', 413000, 1, 413000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211220001', N'SA045', 143000, 1, 143000)
INSERT [dbo].[CT_HoaDon] ([maHoaDon], [maSach], [donGia], [soLuong], [thanhTien]) VALUES (N'HD20211220001', N'SA047', 89000, 1, 89000)






