/*1. chay doan nay*/
use master

go
drop database techway /*chay dong nay neu trong sqlserver da co databse techway*/
go
create database techway
go 
use techway

/*	
2.1. open file application.properties, chinh none thanh update:
	spring.jpa.hibernate.ddl-auto=none 
	--> spring.jpa.hibernate.ddl-auto=update
2.2. run ung dung spring boot
*/

/*
go
/*3. doi kieu du lieu varchar(255)mac dinh trong entity(String) thanh nvarchar*/
ALTER TABLE products ALTER COLUMN name nvarchar(255)
go
ALTER TABLE categories ALTER COLUMN category_name nvarchar(255)
go 
ALTER TABLE manufacturer ALTER COLUMN manufacturer_name nvarchar(255)
go
ALTER TABLE colors ALTER COLUMN color nvarchar(50)
go
ALTER TABLE camera_features ALTER COLUMN name nvarchar(255)
go
ALTER TABLE screen_techs ALTER COLUMN name nvarchar(255)
go
ALTER TABLE special_features ALTER COLUMN name nvarchar(255)
go
ALTER TABLE advanced_securities ALTER COLUMN name nvarchar(255)
go 
ALTER TABLE camera_features ALTER COLUMN name nvarchar(255)
go
ALTER TABLE orders ALTER COLUMN address nvarchar(255)
go
ALTER TABLE comments ALTER COLUMN content nvarchar(max)
go
ALTER TABLE products ALTER COLUMN image nvarchar(max)

DELETE FROM ROLES
select * from roles
go

select * from categories
*/

go
insert into [dbo].[roles] values ('ROLE_CUST'), ('ROLE_DIRE'), ('ROLE_STAFF')
go

insert into categories(category_no, category_name) values ('phone', N'Điện thoại'), 
	('laptop', N'Laptop'), ('tablet', N'Tablet')
go

insert into manufacturer values (N'iphone'), (N'Samsung'), (N'Oppo'), 
	(N'Xiaomi'), (N'Vivo'), (N'realme'), (N'Nokia'), (N'TCL'),
	(N'mobell'), (N'itel'), (N'Masstel')
go 
select * from colors
insert into colors values (N'Vàng đồng'), (N'Bạc'), (N'Trắng'), (N'Đỏ'), (N'Xanh dương'), (N'Xanh lá'), (N'Xanh rêu'), (N'Cam'), (N'Xanh ngọc'), (N'Tím'), (N'Tím nhạt'), (N'Đen'), (N'Kem'), (N'Xanh')
go
insert into camera_features values (N'Quay video hiển thị kép'), 
	(N'HDR'),
	(N'Chuyên nghiệp (Pro)'),
	(N'Toàn cảnh (Panorama)'),
	(N'Nhãn dán (AR Stickers)'),
	(N'Ban đêm (Night Mode)'),
	(N'Làm đẹp'),
	(N'Siêu độ phân giải'),
	(N'AI Camera'),
	(N'Google Lens'),
	(N'Bộ lọc màu'),
	(N'Quay chậm (Slow Motion)'),
	(N'Xóa phông'),
	(N'Zoom kỹ thuật số'),
	(N'Trôi nhanh thời gian (Time Lapse)'),
	(N'Quay video Full HD')
go
insert into advanced_securities values (N'Mở khoá vân tay dưới màn hình'),
	 (N'Mở khoá khuôn mặt'),
	 (N'Mở khoá khuôn mặt Face ID')
go
insert into special_features values (N'Phát hiện va chạm (Crash Detection)'),
	(N'Loa kép'),
	(N'Màn hình luôn hiển thị AOD'),
	(N'Chạm 2 lần sáng màn hình'),
	(N'Apple Pay'),
	(N'Chế độ đơn giản (Giao diện đơn giản)'),
	(N'Cử chỉ thông minh'),
	(N'Ứng dụng kép (Nhân bản ứng dụng)'),
	(N'Chế độ trẻ em (Không gian trẻ em)'),
	(N'Đa cửa sổ (chia đôi màn hình)'),
	(N'Cử chỉ màn hình tắt'),
	(N'Mở rộng bộ nhớ RAM'),
	(N'Smart Switch (ứng dụng chuyển đổi dữ liệu)'),
	(N'Âm thanh Dolby Atmos'),
	(N'Đa cửa sổ (chia đôi màn hình)'),
	(N'Samsung DeX (Kết nối màn hình sử dụng giao diện tương tự PC)'),
	(N'Tối ưu game (Game Booster)'),
	(N'Trợ lý ảo Samsung Bixby'),
	(N'Âm thanh AKG'),
	(N'Màn hình luôn hiển thị AOD'),
	(N'Không gian thứ hai (Thư mục bảo mật)'),
	(N'Chặn cuộc gọi'),
	(N'Chặn tin nhắn')
	
go
insert into screen_techs values (N'Dynamic AMOLED 2X'),
	(N'Super Retina XDR'),
	(N'IPS LCD'),
	(N'Super AMOLED')
	
go
use techway
select * from products
select * from colors
select * from camera_features
select * from categories
select * from manufacturer
insert into products values()

select * from roles

delete from user_roles where user_id = 2

delete from users

select * FROM users
select * from user_roles

select * from comments
select * from products
insert into comments (content, product_id, created_by) values(N'dfhweioafhewi', 1, 1)
insert into products(product_no, name, [images], price, available, category_id, manufacturer_id, color_id) values('asdfads', 'ss s20', 'photos', 99, 1, 1, 1, 1)