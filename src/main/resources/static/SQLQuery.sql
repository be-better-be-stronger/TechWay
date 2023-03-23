/*1. chay doan nay*/
use master
go
drop database techway
go
create database techway
go 
use techway
/*2. run ung dung spring boot*/
insert into [dbo].[roles] values ('CUST', 'Customer'), ('DIRE', 'Director'), ('STAF', 'Staff')
go
/*3. doi kieu du lieu varchar(255)mac dinh trong entity(String) thanh nvarchar*/
ALTER TABLE table_name ALTER COLUMN column_name datatype
go
select * from roles
