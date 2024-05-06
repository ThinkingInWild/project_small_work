create database FinalProject;


create table member(
id int primary key identity(1,1) not null,
member_id varchar(30) not null,
member_password varchar(30) not null,
member_name nvarchar(20) not null,
email varchar(50) not null,
gender int,
member_address nvarchar(50),
discount_points_left int,
sign_up_date date,
final_log_in_date date,
access_level varchar(30),
coupon_quantity int
)

select * from member;

drop table member;

insert into member values
('0911000100', 'abcde','kevin', 'abcde@123', 1, 'abcdefg', 100, '2023-01-01', '2023-01-01',
'admin', 100);


