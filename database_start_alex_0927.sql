create database FinalProject;

create table member(
id int primary key identity(1,1),
member_id varchar(30) not null,
member_password varchar(30) not null,
member_name nvarchar(20) not null,
email varchar(50) not null,
gender int,
member_address nvarchar(50),
discount_points_left int,
sign_up_date date,
final_log_in_date date,
access_level varchar(20),
coupon_quantity int,
)

INSERT INTO member (member_id, member_password, member_name, email, gender, member_address, discount_points_left, sign_up_date, final_log_in_date, access_level, coupon_quantity)
VALUES ('0988111111', 'pwd1', 'Jack', 'jack@gmail.com', 0, N'台北市', 100,  GETDATE(),  GETDATE(), 'admin', 0),
('0988222222', 'pwd2', 'Ted', 'ted@gmail.com', 1, N'台中市', 100,  GETDATE(),  GETDATE(), 'manager', 0),
('0988333333', 'pwd3', 'Mick', 'mick@gmail.com', 2, N'台南市', 100,  GETDATE(),  GETDATE(), 'worker', 0),
('0988444444', 'pwd4', 'Rick', 'rick@gmail.com', 3, N'高雄市', 200,  GETDATE(),  GETDATE(), 'customer', 3),
('0988555555', 'pwd5', 'Jay', 'jay@gmail.com', 1, N'新竹市', 300,  GETDATE(),  GETDATE(), 'customer', 4),
('0988666666', 'pwd6', 'Taylor Swift', 'ts@gmail.com', 2, N'台北市', 300,  GETDATE(),  GETDATE(), 'customer', 2),
('0988777777', 'pwd7', 'Rock', 'rock@gmail.com', 3, N'台北市', 300,  GETDATE(),  GETDATE(), 'customer', 0);

SELECT * FROM member;

TRUNCATE TABLE member;

DROP TABLE member;


------留言板
create table billboard(
id int primary key identity(1,1),
alias nvarchar(30),
content nvarchar(500),
message_time datetime DEFAULT GETDATE(),
push_number int,
you_suck_number int,
view_count int,
)

INSERT INTO billboard(alias, content, message_time, push_number, you_suck_number, view_count)
VALUES(N'阿明', N'留言測試 1', GETDATE(), 10, 20, 200),
(N'海鮮', N'留言測試 2', GETDATE(), 10, 20, 300),
(N'老闆', N'留言測試 3', GETDATE(), 5, 20, 200),
(N'阿明', N'留言測試 4', GETDATE(), 5, 20, 200),
(N'熱狗', N'留言測試 5', GETDATE(), 50, 20, 400),
(N'大大', N'留言測試 6', GETDATE(), 77, 10, 300);

SELECT * FROM billboard;

TRUNCATE TABLE billboard;

DROP TABLE billboard;
