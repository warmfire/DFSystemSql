--ָ��ǰҪʹ�õ����ݿ�
use master
go
--�жϵ�ǰ���ݿ��Ƿ����
if exists (select * from sysdatabases where name='DFSystem')
drop database DFSystem--ɾ�����ݿ�
go
--�������ݿ�
create database DFSystem
on primary
(
	--���ݿ��ļ����߼���
    name='DFSystem_data',
    --���ݿ������ļ���������·����
    filename='D:\DFSystem_data.mdf',
    --���ݿ��ļ���ʼ��С
    size=10MB,
    --�����ļ�������
    filegrowth=1MB
)
--������־�ļ�
log on
(
    name='DFSystem_log',
    filename='D:\DFSystem_log.ldf',
    size=2MB,
    filegrowth=1MB
)
go
--����ѧԱ��Ϣ���ݱ�
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
    sName varchar(20) not null,--��� 
    className  varchar(20) not null,
    isHeadman int default'-1',
    groupId int default'0',--���
    isLogin int default'-1',
)
go
--������ʦ��
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
--�������ڱ�
if exists(select * from sysobjects where name='InGrades')
drop table InGrades
go
create table InGrades
(
    Id int identity(1,1) primary key,
    sName varchar(20) not null,--���
    sId char(11) not null,
    groupId int not null,--���
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
--��������
if exists(select * from sysobjects where name='OutGrades')
drop table OutGrades
create table OutGrades
(
	Id int identity(1,1) primary key,
	sName varchar(20) not null,--���
	sId char(11) not null,
	groupId int not null,--���
	workloads int not null CHECK (workloads between 0 and 25) default'0',
    original int not null CHECK (original between 0 and 20) default'0',
    technology int not null CHECK (technology between 0 and 20) default'0',
    beauty int not null CHECK (beauty between 0 and 20 ) default'0',
    express int not null CHECK (express between 0 and 15) default'0',
    allGrades int not null CHECK(allGrades between 0 and 100) default'0',
    Grader varchar(20),
)
go
--������ʦ��
if exists(select * from sysobjects where name='TGrades')
drop table TGrades
create table TGrades
(
	Id int identity(1,1) primary key,
	sName varchar(20) not null,--���
	sId char(11) not null,
	groupId int not null,--���
    allGrades int not null CHECK(allGrades between 0 and 100) default'0',
    Grader varchar(20),
)
go


----------------------------------Լ��----------------------------------------


--�������ݱ�ĸ���Լ��
use DFSystem
go

--����ΨһԼ��unique
if exists(select * from sysobjects where name='uq_sName')
alter table Students drop constraint uq_sName
alter table Students
add constraint uq_sName unique (sName)

--�������
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

--�����洢����
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

-------------------------------------------��������--------------------------------------
use DFSystem
go

--����Teachers
insert into Teachers(tName,tPassword) values('xyg','fzw')

--����TGrades
--insert into TGrades(sName,sId,groupId, allGrades,grader) values('fzw','13303013107',1,100,'xyg')

--���Դ洢����
--exec ist_gdname 'zsj'

--����Students
--insert into Students(sUserName,sPassword,sName,sId,className,isHeadman,groupId,isLogin) 
--values('zsj','123','��ʢ��','13303013149','�������ѧ�뼼��',-1,2,-1)
--insert into Students(sUserName,sPassword,sName,sId,className,isHeadman,groupId,isLogin) 
--values('fzw','123','������','13303013107','�������ѧ�뼼��',1,2,-1)
--insert into Students(sUserName,sPassword,sName,sId,className,isHeadman,groupId,isLogin) 
--values('cyj','123','���Ž�','13303013106','�������ѧ�뼼��',-1,1,-1)
