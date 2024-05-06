﻿create table detail
(
	id int primary key identity(1,1) not null,
	order_number int NOT NULL,
	main nvarchar(10),
	side1 nvarchar(10),
	side2 nvarchar(10),
	side3 nvarchar(10),
	side4 nvarchar(10),
	extra_main nvarchar(10),
	detail_total int
);

select * from detail;

insert into detail values
(10001, '豬', '高麗菜', '花椰菜', '黃瓜佐甜不辣', '豆乾', '豬', 120 ),
(10002, '豬', '花椰菜', '胡蘿蔔炒蛋', '青江菜', '炒竹筍', '豬', 120),
(10003, '雞', '荷包蛋', '高麗菜', '豆乾', '海帶', '豬', 120),
(10004, '牛', '番茄炒蛋', '黃瓜佐甜不辣', '花椰菜', '豆乾', '魚', 140),
(10005, '羊', '黃瓜佐甜不辣', '海帶', '胡蘿蔔炒蛋', '荷包蛋', '豬', 120),
(10006, '魚', '高麗菜', '炒竹筍', '花椰菜', '豆乾', '羊', 130),
(10007, '羊', '青江菜', '高麗菜', '高麗菜', '番茄炒蛋', '魚', 150),
(10008, '雞', '荷包蛋', '高麗菜', '豆乾', '海帶', '雞', 120),
(10009, '牛', '番茄炒蛋', '黃瓜佐甜不辣', '花椰菜', '豆乾', '豬', 120),
(10010, '羊', '黃瓜佐甜不辣', '海帶', '胡蘿蔔炒蛋', '荷包蛋', '牛', 140),
(10011, '魚', '高麗菜', '炒竹筍', '花椰菜', '豆乾', '羊', 130),
(10012, '羊', '青江菜', '高麗菜', '高麗菜', '番茄炒蛋', '牛', 140);


truncate table detail;

drop table detail;