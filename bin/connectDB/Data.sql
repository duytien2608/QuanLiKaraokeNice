use QLKaraokeNice
Create table ChiTietPhieuDatPhongDichVu (
	maPhieuDatPhongDichVu int identity(1,1) primary key,
	maPDP int,
	maDV int,
	soLuong int,
	tinhTien bit not null default 0
)
alter table ChiTietPhieuDatPhongDichVu
add CONSTRAINT fk_ChiTietPhieuDatPhongDichVu_maPDP
Foreign key (maPDP) references PhieuDatPhong(maPDP)
alter table ChiTietPhieuDatPhongDichVu
add CONSTRAINT fk_ChiTietPhieuDatPhongDichVu_maDV
Foreign key (maDV) references DichVu(maDV)

Create table DichVu (
	maDV int identity(1,1) primary key,
	tenDV nvarchar(50),
	loaiDV nvarchar(50),
	donViTinh nvarchar(50),
	giaNhap int,
	giaBan int,
)
Create table PhieuDatPhong (
	maPDP int identity(1,1) primary key,
	maPhong int,
	maKH int,
	tenKH  nvarchar(50),
	sdt nvarchar(20),
	soLuongNguoi int,
	thoiGianDat DATETIME,
	tenNhanVien nvarchar(50)
)
alter table PhieuDatPhong
add CONSTRAINT fk_PhieuDatPhong_maKH
Foreign key (maKH) references KhachHang(maKH)
ALTER TABLE dbo.PhieuDatPhong ADD FOREIGN KEY (maPhong) REFERENCES dbo.Phong(maPhong)
ALTER TABLE dbo.PhieuDatPhong ADD FOREIGN KEY (maKH) REFERENCES dbo.KhachHang(maKH)
Create table KhachHang(
	maKH int identity(1,1) primary key,
	tenKH nvarchar(50),
	sdt nvarchar(50),
	email nvarchar(50),
	diaChi nvarchar(50)
)
Create table NhanVien(
	maNV int identity (1,1) primary key,
	tenNV nvarchar(50),
	gioiTinh nvarchar(20),
	ngaySinh nvarchar(20),
	sdt nvarchar(20),
	email nvarchar(50),
	cccd nvarchar(20),
	chucvu nvarchar(50)
)
select *from nhanVien
drop table nhanvien
Create Table Phong (
	maPhong int identity(1,1) Primary Key,
	tenPhong nvarchar(50),
	giaPhong int,
	loaiPhong nvarchar(50),
	tinhTrang nvarchar(50)
)
Create table HoaDon (
	MaHD int identity(1,1) primary key,
	maPhieuDatPhongDichVu int,
	thoiGianKetThuc datetime,
	tongTien float,
	maNV int
)

alter table HoaDon
add CONSTRAINT fk_ChiTietHoaDon_maNV
Foreign key (maNV) references NhanVien(manv)

create table TaiKhoan(
	maNV int,
	tenDangNhap nvarchar(50) not null primary key,
	matKhau nvarchar(50) not null,
	kichHoat bit
)

alter table taikhoan
add CONSTRAINT fk_TaiKhoan_maNV
Foreign key (maNV) references NhanVien(manv)