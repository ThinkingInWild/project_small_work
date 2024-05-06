create table inventory(
id int primary key identity(1,1),
inventory_date date not null,
pork int,
beef int,
lamb int,
fish int,
chicken int
);

insert into inventory values('2023-10-26', 0, 0, 0, 0, 0);
 insert into inventory values('2023-10-27', 0, 0, 0, 0, 0);
 insert into inventory values('2023-10-28', 0, 0, 0, 0, 0);
 insert into inventory values('2023-10-29', 0, 0, 0, 0, 0);
 insert into inventory values('2023-10-30', 1, 6, 7, 4, 3);
 insert into inventory values('2023-10-31', 5, 7, 9, 11, 2);
 insert into inventory values('2023-11-01', 1, 5, 5, 5, 5);
 insert into inventory values('2023-11-02', 5, 10, 10, 10, 10);
 insert into inventory values('2023-11-03', 5, 7, 9, 11, 13);
 insert into inventory values('2023-11-04', 5,10, 5, 10, 5);

 truncate table inventory;
 drop table inventory;

select * from inventory;