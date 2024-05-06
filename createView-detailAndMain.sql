create view detail_main as
select * from detail
join main on detail.main = main.main_id;

select * from detail_main;

drop view detail_main;

