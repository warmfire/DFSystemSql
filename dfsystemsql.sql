--指向当前要使用的数据库
use master
go
--判断当前数据库是否存在
if exists (select * from sysdatabases where name='DFSystem')
drop database DFSystem--删除数据库
go
--创建数据库
create database DFSystem
on primary
(
	--数据库文件的逻辑名
    name='DFSystem_data',
    --数据库物理文件名（绝对路径）
    filename='D:\DFSystem_data.mdf',
    --数据库文件初始大小
    size=10MB,
    --数据文件增长量
    filegrowth=1MB
)
--创建日志文件
log on
(
    name='DFSystem_log',
    filename='D:\DFSystem_log.ldf',
    size=2MB,
    filegrowth=1MB
)
go
--创建学员信息数据表
use DFSystem
go
if exists (select * from sysobjects where name='Students')
drop table Students
go
create table Students
(
	sId char(11) primary key,
    id int identity(1,1),
    sUserName varchar(20) not null,
    sPassword varchar(20) not null,
    sName varchar(20) not null,--外键 
    className  varchar(20) not null,
    isHeadman int default'-1',
    groupId int default'0',--外键
    isLogin int default'-1',
)
go
--创建教师表
if exists(select * from sysobjects where name='Teachers')
drop table Teachers
go
create table Teachers
(
	tId int identity(1,1) primary key,
    tName varchar(20) not null,
    tPassword varchar(20) not null,
)
go
--创建组内表
if exists(select * from sysobjects where name='InGrades')
drop table InGrades
go
create table InGrades
(
    Id int identity(1,1) primary key,
    sName varchar(20) not null,--外键
    sId char(11) not null,
    groupId int not null,--外键
    part varchar(20),
    workloads int not null CHECK (workloads between 0 and 30) default'0',
    offer int not null CHECK (offer between 0 and 20) default'0',
    attitude int not null CHECK (attitude between 0 and 20) default'0',
    cooperate int not null CHECK (cooperate between 0 and 20) default'0',
    progress int not null CHECK (progress between 0 and 10) default'0',
    selfgrades int not null CHECK (selfgrades between 0 and 100) default'0',
    othergrades int not null CHECK (othergrades between 0 and 100) default'0',
    Grader varchar(20),
)
go
--创建组间表
if exists(select * from sysobjects where name='OutGrades')
drop table OutGrades
create table OutGrades
(
	Id int identity(1,1) primary key,
	sName varchar(20) not null,--外键
	sId char(11) not null,
	groupId int not null,--外键
	workloads int not null CHECK (workloads between 0 and 25) default'0',
    original int not null CHECK (original between 0 and 20) default'0',
    technology int not null CHECK (technology between 0 and 20) default'0',
    beauty int not null CHECK (beauty between 0 and 20 ) default'0',
    express int not null CHECK (express between 0 and 15) default'0',
    allGrades int not null CHECK(allGrades between 0 and 100) default'0',
    Grader varchar(20),
)
go
--创建老师表
if exists(select * from sysobjects where name='TGrades')
drop table TGrades
create table TGrades
(
	Id int identity(1,1) primary key,
	sName varchar(20) not null,--外键
	sId char(11) not null,
	groupId int not null,--外键
    allGrades int not null CHECK(allGrades between 0 and 100) default'0',
    Grader varchar(20),
)
go


----------------------------------约束----------------------------------------


--创建数据表的各种约束
use DFSystem
go

--创建唯一约束unique
if exists(select * from sysobjects where name='uq_sName')
alter table Students drop constraint uq_sName
alter table Students
add constraint uq_sName unique (sName)

--创建外键
if exists(select * from sysobjects where name='uq_sIdi')
alter table InGrades drop constraint uq_sIdi
alter table InGrades
add constraint uq_sIdi foreign key (sId) references Students(sId)

if exists(select * from sysobjects where name='uq_sIdo')
alter table OutGrades drop constraint uq_sIdo
alter table OutGrades
add constraint uq_sIdo foreign key (sId) references Students(sId)

if exists(select * from sysobjects where name='uq_sIdt')
alter table TGrades drop constraint uq_sIdt
alter table TGrades
add constraint uq_sIdt foreign key (sId) references Students(sId)

--创建存储过程
--if exists(select * from sysobjects where name='ist_gdname')
--drop procedure ist_gdname
--go
--create procedure ist_gdname
--(
--   @sun varchar(20)
--)
--as
--	declare  @sid char(11),@name varchar(20),@gid varchar(20)
--    select @name=sName,@gid=groupId,@sid=sId from Students where sUserName=@sun
--	insert into InGrades(sName,sId,groupId) values(@name,@sid,@gid)
--	insert into OutGrades(sName,sId,groupId) values(@name,@sid,@gid)
--	insert into TGrades(sName,sId,groupId) values(@name,@sid,@gid)
--go

-------------------------------------------插入数据--------------------------------------
use DFSystem
go

--插入Teachers
insert into Teachers(tName,tPassword) values('xyg','fzw')

--插入TGrades
--insert into TGrades(sName,sId,groupId, allGrades,grader) values('fzw','13303013107',1,100,'xyg')

--测试存储过程
--exec ist_gdname 'zsj'

--插入Students
--insert into Students(sUserName,sPassword,sName,sId,className,isHeadman,groupId,isLogin) 
--values('zsj','123','周盛军','13303013149','计算机科学与技术',-1,2,-1)
--insert into Students(sUserName,sPassword,sName,sId,className,isHeadman,groupId,isLogin) 
--values('fzw','123','范正威','13303013107','计算机科学与技术',1,2,-1)
--insert into Students(sUserName,sPassword,sName,sId,className,isHeadman,groupId,isLogin) 
--values('cyj','123','陈昱杰','13303013106','计算机科学与技术',-1,1,-1)
