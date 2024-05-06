create database FinalProject;


create table reward(
id int identity(1,1) primary key not null,
member_id varchar(10) not null,
order_number int not null,
received_date datetime,
received_points int,
used_date datetime,
used_points int,
current_points int,
)


insert into reward
values
('0988111111',10001,'2023-9-9',100,'2023-10-10',50,50),
('0988222222',10002,'2023-9-9',150,'2023-10-11',50,50),
('0988333333',10003,'2023-9-9',50,'2023-10-13',50,50),
('0988444444',10004,'2023-9-9',100,'2023-10-15',50,50),
('0988555555',10005,'2023-9-9',100,'2023-10-17',50,50),
('0988666666',10006,'2023-9-25',200,'2023-10-13',0,45),
('0988777777',10007,'2023-9-27',100,'2023-10-15',0,50),
('0988111111',10008,'2023-9-30',50,'2023-10-16',0,55),
('0988222222',10009,'2023-9-30',350,'2023-10-17',10,50),
('0988333333',10010,'2023-9-30',50,'2023-10-20',10,50);


select * from reward;

drop table reward;


truncate table reward;