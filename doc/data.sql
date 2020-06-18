
use res;
insert into resadmin(raname,rapwd) values( 'a','0cc175b9c0f1b6a831c399e269772661');
select * from resadmin;
---用户表初始数据
insert into resuser( username, pwd,email) values( 'a', '0cc175b9c0f1b6a831c399e269772661','a@163.com');
insert into resuser( username, pwd,email) values( 'b', '0cc175b9c0f1b6a831c399e269772661','b@163.com');

--插入菜
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('素炒莴笋丝',22.0,20.0,'营养丰富','500008.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('蛋炒饭',22.0,20.0,'营养丰富','500022.jpg');
insert into resfood( fname,normprice,realprice,detail, fphoto)  values('酸辣鱼',42.0,40.0,'营养丰富','500023.jpg');
insert into resfood( fname,normprice,realprice,detail, fphoto)  values('鲁粉',12.0,10.0,'营养丰富','500024.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('西红柿蛋汤',12.0,10.0,'营养丰富','500025.jpg');



insert into resfood(fname,normprice,realprice,detail, fphoto)   values('炖鸡',102.0,100.0,'营养丰富','500026.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('炒鸡',12.0,10.0,'营养丰富','500033.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('炒饭',12.0,10.0,'营养丰富','500034.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('手撕前女友',12.0,10.0,'营养丰富','500035.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('面条',12.0,10.0,'营养丰富','500036.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('端菜',12.0,10.0,'营养丰富','500038.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('酸豆角',12.0,10.0,'营养丰富','500041.jpg');

--不测试:   生成一条订单   a用户订了  1号菜1份,及2号菜2份
insert into resorder(userid,address,tel,ordertime,deliverytime,ps,status)
values( 1,'湖南省衡阳市','13878789999',now(),now(),'送餐上门',0);

insert into resorderitem(roid,fid,dealprice,num)
values( 1,1,20,1);

insert into resorderitem(roid,fid,dealprice,num)
values( 1,2,20,1);
--注意以上的三条语句要求在事务中处理.
commit;

select fid,fname,normprice,realprice,fphoto,detail from resfood where 1=1 order by fid desc  limit 0,10;

--1.先统计出所有菜品的销量
select fid,sum(num)
from resorderitem
group by fid;

--2.菜品表左外联接上上面的结果,加入条件
select resfood.fid,fname,normprice,realprice,fphoto,sales
from resfood
  left join (
              select fid,sum(num) as sales
              from resorderitem
              group by fid
            )a
    on resfood.fid=a.fid
where 1=1
      and fname like '%素炒%' or detail like '%素炒%'
order by sales desc ,fid desc
limit 0,10;
--3.加入子查询的筛选
select resorderitem.fid as fid ,sum(num) as sales
from resorderitem
  left join resfood
    on resfood.fid=resorderitem.fid
where 1=1
      and fname like '%素炒%' or detail like '%素炒%'
group by resorderitem.fid;
--4.加入
select resfood.fid,fname,normprice,realprice,fphoto,sales
from resfood
  left join (
              select resorderitem.fid as fid ,sum(num) as sales
              from resorderitem
                left join resfood
                  on resfood.fid=resorderitem.fid
              where 1=1
                    and fname like '%素炒%' or detail like '%素炒%'
              group by resorderitem.fid
            )a
    on resfood.fid=a.fid
where 1=1
      and fname like '%素炒%' or detail like '%素炒%'
order by sales desc ,fid desc
limit 0,10;


select *from resuser;

