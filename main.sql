create table main (
	main_id nvarchar(20),
	main_name varchar(20), 
	main_price int,
	extra_main_price int
)

insert into main values
('雞', 'chicken',120, 70),
('豬', 'pork',130, 80),
('牛','beef', 150, 90),
('魚', 'fish',180, 110 ),
('羊', 'lamb', 160, 100);


select*from main;

TRUNCATE TABLE main;

drop table main