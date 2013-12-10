

create table WEBSITES (USERID integer not null unique, WEBSITEID
bigint not null , DOMAINNAME varchar(255) not null unique,
DESCRIPTION varchar(255), PPVIEW double, PPCLICK double, PPWEEK
double, totalClick bigint, totalView bigint, active smallint, primary
key (WEBSITEID));

drop table WEBSITES;




--used for derby
drop table T_USERINFO;
	create table T_USERINFO
	(
	  ID           VARCHAR(100) primary key,
	  USERCODE     VARCHAR(100),
	  PASSWORD     VARCHAR(100),
	  USERNAME     VARCHAR(100),
	  TYPE         VARCHAR(100),
	  STATE        VARCHAR(10),
	  BUSINESSTYPE VARCHAR(100),
	  ADDRESS      VARCHAR(255),
	  EMAIL        VARCHAR(255),
	  IDNO         VARCHAR(255),
	  IDTYPE       VARCHAR(255),
	  PASSANSWER   VARCHAR(255),
	  PASSQUESTION VARCHAR(255),
	  PHONENO      VARCHAR(255)
	);
	
	--used for oracle
	--create table T_USERINFO
	--(
	--  ID           VARCHAR(100) not null,
	--  USERCODE     VARCHAR(100),
	--  PASSWORD     VARCHAR(100),
	--  USERNAME     VARCHAR(100),
	--  TYPE         VARCHAR(100),
	--  STATE        VARCHAR(10),
	--  BUSINESSTYPE VARCHAR(100),
	--  ADDRESS      VARCHAR(255),
	--  EMAIL        VARCHAR(255),
	--  IDNO         VARCHAR(255),
	--  IDTYPE       VARCHAR(255),
	--  PASSANSWER   VARCHAR(255),
	--  PASSQUESTION VARCHAR(255),
	--  PHONENO      VARCHAR(255)
	--)
	--alter table T_USERINFO
  --add constraint PK_T_USERINFO primary key (ID);
  alter table T_USERINFO drop constraint PK_T_USERINFO;
  
  insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('201207041303182470000', 'rockyzhu2003', 'rockyshi781207', '������', '3', '1', '', '', '', '620202197412190631', '0', '', '', '');

insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('201207041303246890008', '', '', '', '3', '1', '', '', '', '', '0', '', '', '');

insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('201207041307129020016', '', '', '', '', '1', '', '', '', '', '', '', '', '');

insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('201207051406197960000', 'kkk', 'kkk', 'kkk', '2', '2', '', '', '', 'kkk', '0', '', '', '');

insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('201207051801007490024', '', '', '', '', '1', '', '', '', '', '', '', '', '');

insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('201207061526044010000', 'ttt', '1', '1', '2', '2', '', '', '', '1', '0', '', '', '');

insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('201207061527353410009', 'zzz', '1', '������ţ', '2', '2', '1', '', '', '1', '0', '', '', '');

insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('20120707125445930032', 'government1', '123456', '', '2', '1', '', '', '', '', '0', '', '', '');

insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('201207071254516910040', 'government2', '123456', '', '3', '1', '', '', '', '', '0', '', '', '');

insert into t_userinfo (ID, USERCODE, PASSWORD, USERNAME, TYPE, STATE, BUSINESSTYPE, ADDRESS, EMAIL, IDNO, IDTYPE, PASSANSWER, PASSQUESTION, PHONENO)
values ('201207091509023720048', 'government3', '123456', '', '2', '1', '', '', '', '', '0', '', '', '');

  

drop view v_userinfo;
create  view v_userinfo as
	select ID,PASSWORD,USERNAME,TYPE,STATE,ADDRESS from T_USERINFO;
  
	
	
	
	-- Create table  客户基本信息
create table CUSTOMER_INFO
(
  customerid        VARCHAR(40) not null,
  customername      VARCHAR(80),
  customertype      VARCHAR(20),
  certtype          VARCHAR(20),
  certid            VARCHAR(40),
  customerpassword  VARCHAR(20),
  inputorgid        VARCHAR(32),
  inputuserid       VARCHAR(32),
  inputdate         VARCHAR(10),
  remark            VARCHAR(500),
  mfcustomerid      VARCHAR(40),
  status            VARCHAR(20),
  belonggroupid     VARCHAR(40),
  channel           VARCHAR(18),
  loancardno        VARCHAR(32),
  customerscale     VARCHAR(20),
  customerstatus    VARCHAR(10),
  allowstatus       VARCHAR(10),
  initcuststatus    VARCHAR(10),
  creditflag        VARCHAR(10),
  partnerflag       VARCHAR(10),
  creditbelong      VARCHAR(18),
  creditlevel       VARCHAR(80),
  ratingscore       NUMBER(10,6),
  setupdate         VARCHAR(10),
  licenseno         VARCHAR(32),
  licensematurity   VARCHAR(10),
  economytype       VARCHAR(18),
  industrytype      VARCHAR(18),
  rccurrency        VARCHAR(18),
  registercapital   NUMBER(24,6),
  registeradd       VARCHAR(18),
  mostbusiness      VARCHAR(800),
  mainproduction    VARCHAR(200),
  officecountrycode VARCHAR(18),
  officeregioncode  VARCHAR(18),
  paiclupcapital    NUMBER(24,6),
  certcountry       VARCHAR(18),
  afterloanflag     VARCHAR(4)
)
tablespace ALS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 27M
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index CUSTOMER_INFO_IDX_02 on CUSTOMER_INFO (MFCUSTOMERID)
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 4M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index CUSTOMER_INFO_IDX_04 on CUSTOMER_INFO (CUSTOMERTYPE)
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 4M
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table CUSTOMER_INFO
  add constraint PK_CUSTOMER_INFO primary key (CUSTOMERID)
  using index 
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 6M
    next 1M
    minextents 1
    maxextents unlimited
  );




-- Create table		代码类别表
create table DICT_CATALOG
(
  DICTNO        VARCHAR(32) not null,
  sortno        VARCHAR(32),
  parentDICTNO   VARCHAR(32),
  DICTNAME      VARCHAR(80),
  DICTDESCRIBE  VARCHAR(250),
  DICTATTRIBUTE VARCHAR(250),
  inputuser     VARCHAR(32),
  inputorg      VARCHAR(32),
  inputdate     VARCHAR(20),
  updateuser    VARCHAR(32),
  updatedate    VARCHAR(20),
  remark        VARCHAR(250)
)
tablespace ALS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table DICT_CATALOG
  add constraint PK_DICT_CATALOG primary key (DICTNO)
  using index 
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table		代码库
create table DICT_LIBRARY
(
  DICTNO        VARCHAR(32) not null,
  itemno        VARCHAR(32) not null,
  itemname      VARCHAR(250),
  bankno        VARCHAR(32),
  sortno        VARCHAR(32),
  isinuse       VARCHAR(18),
  itemdescribe  VARCHAR(800),
  itemattribute VARCHAR(800),
  relativecode  VARCHAR(1600),
  attribute1    VARCHAR(800),
  attribute2    VARCHAR(250),
  attribute3    VARCHAR(250),
  attribute4    VARCHAR(250),
  attribute5    VARCHAR(250),
  attribute6    VARCHAR(250),
  attribute7    VARCHAR(250),
  attribute8    VARCHAR(250),
  inputuser     VARCHAR(32),
  inputorg      VARCHAR(32),
  inputdate     VARCHAR(20),
  updateuser    VARCHAR(32),
  updatedate    VARCHAR(20),
  remark        VARCHAR(250),
  helptext      VARCHAR(250)
)
tablespace ALS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 3M
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index IDX1_DICT_LIBRAY on DICT_LIBRARY (DICTNO, SORTNO)
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 704K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IDX_DL_001 on DICT_LIBRARY (DICTNO, ITEMNO, ITEMNAME)
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table DICT_LIBRARY
  add constraint P_PK_DICT_LIBRARY primary key (DICTNO, ITEMNO)
  using index 
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 512K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table		数据对象
create table DATAOBJECT_CATALOG
(
  dono            VARCHAR(32) not null,
  doname          VARCHAR(80),
  dodescribe      VARCHAR(250),
  dotype          VARCHAR(18),
  doclass         VARCHAR(18),
  isinuse         VARCHAR(18),
  colcount        CHAR(1),
  modeid          VARCHAR(32),
  jboclass        VARCHAR(200),
  jbofrom         VARCHAR(1000),
  jbowhere        VARCHAR(1000),
  jbogroup        VARCHAR(500),
  jboorder        VARCHAR(500),
  businessprocess VARCHAR(200),
  exportflag      CHAR(1),
  inputuser       VARCHAR(32),
  inputdate       VARCHAR(20),
  updateuser      VARCHAR(32),
  updatedate      VARCHAR(20),
  remark          VARCHAR(250),
  isvalidate      CHAR(1)
)
tablespace ALS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 832K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table DATAOBJECT_CATALOG
  add constraint SQL110627091240380 primary key (DONO)
  using index 
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table		机构信息
create table ORG_INFO
(
  orgid            VARCHAR(32) not null,
  sortno           VARCHAR(32),
  orgname          VARCHAR(80),
  orglevel         VARCHAR(32),
  orgproperty      VARCHAR(250),
  relativeorgid    VARCHAR(32),
  bankid           VARCHAR(32),
  banklicense      VARCHAR(32),
  businesslicense  VARCHAR(32),
  belongarea       VARCHAR(18),
  orgclass         VARCHAR(18),
  zipcode          VARCHAR(18),
  mainframeorgid   VARCHAR(32),
  mainframeexgid   VARCHAR(32),
  orgcode          VARCHAR(32),
  status           VARCHAR(80),
  orgoldname       VARCHAR(80),
  setupdate        VARCHAR(10),
  orgadd           VARCHAR(80),
  principal        VARCHAR(10),
  orgtel           VARCHAR(80),
  branchnum        INTEGER,
  cmnum            INTEGER,
  businesshours    VARCHAR(80),
  inputorg         VARCHAR(32),
  inputuser        VARCHAR(32),
  inputdate        VARCHAR(20),
  inputdate        VARCHAR(20),
  updateuser       VARCHAR(32),
  updatedate       VARCHAR(20),
  updatedate       VARCHAR(20),
  remark           VARCHAR(250),
  belongorgid      VARCHAR(32),
  hostno           VARCHAR(10),
  vitualserialno   INTEGER,
  vitualid         VARCHAR(32),
  corporgid        VARCHAR(20),
  watermark        VARCHAR(100),
  isapplybill      VARCHAR(2) default '2',
  isapprovebill    VARCHAR(2) default '2',
  singebillsum     NUMBER(24,6) default 0,
  singedatebillsum NUMBER(24,6) default 0,
  billapproveorgid VARCHAR(32),
  pauditlevel      VARCHAR(10),
  belongindcenter  VARCHAR(18),
  rhbigno          VARCHAR(18)
)
tablespace ALS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table ORG_INFO
  add constraint PK_ORG_INFO primary key (ORGID)
  using index 
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table		用户基本信息
create table USER_INFO
(
  userid        VARCHAR(32) not null,
  loginid       VARCHAR(32),
  username      VARCHAR(32),
  password      VARCHAR(32),
  belongorg     VARCHAR(32),
  attribute1    VARCHAR(80),
  attribute2    VARCHAR(80),
  attribute3    VARCHAR(80),
  attribute4    VARCHAR(80),
  attribute5    VARCHAR(80),
  attribute6    VARCHAR(80),
  attribute7    VARCHAR(80),
  attribute8    VARCHAR(80),
  attribute     VARCHAR(80),
  describe1     VARCHAR(250),
  describe2     VARCHAR(250),
  describe3     VARCHAR(250),
  describe4     VARCHAR(250),
  status        VARCHAR(80),
  certtype      VARCHAR(18),
  certid        VARCHAR(32),
  companytel    VARCHAR(32),
  mobiletel     VARCHAR(32),
  email         VARCHAR(80),
  accountid     VARCHAR(32),
  id1           VARCHAR(32),
  id2           VARCHAR(32),
  sum1          NUMBER(24,6),
  sum2          NUMBER(24,6),
  inputorg      VARCHAR(32),
  inputuser     VARCHAR(32),
  inputdate     VARCHAR(20),
  updatedate    VARCHAR(20),
  inputdate     VARCHAR(20),
  updateuser    VARCHAR(32),
  updatedate    VARCHAR(20),
  remark        VARCHAR(250),
  birthday      VARCHAR(10),
  gender        VARCHAR(18),
  familyadd     VARCHAR(250),
  educationalbg VARCHAR(18),
  amlevel       VARCHAR(18),
  title         VARCHAR(18),
  educationexp  VARCHAR(800),
  vocationexp   VARCHAR(800),
  position      VARCHAR(250),
  qualification VARCHAR(250),
  ntid          VARCHAR(32),
  belongteam    VARCHAR(32),
  lob           VARCHAR(32)
)
tablespace ALS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 320K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table USER_INFO
  add constraint P_PK_USER_INFO primary key (USERID)
  using index 
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table		系统菜单表
create table AWE_MENU_INFO
(
  menuid       VARCHAR(32) not null,
  menuname     VARCHAR(250),
  sortno       VARCHAR(32),
  url          VARCHAR(200),
  urlparam     VARCHAR(100),
  isinuse      VARCHAR(1),
  remark       VARCHAR(250),
  inputorgid   VARCHAR(32),
  inputuserid  VARCHAR(32),
  inputdate    VARCHAR(20),
  updateorgid  VARCHAR(32),
  updateuserid VARCHAR(32),
  updatedate   VARCHAR(20),
  icon         VARCHAR(80) default '',
  accesstype   VARCHAR(20),
  displayname  VARCHAR(120)
)
tablespace ALS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table AWE_MENU_INFO
  is '??????????';
-- Add comments to the columns 
comment on column AWE_MENU_INFO.accesstype
  is '????';
-- Create/Recreate indexes 
create index IDX1_MENU_INFO on AWE_MENU_INFO (SORTNO)
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table AWE_MENU_INFO
  add constraint P_PK_MENU_INFO primary key (MENUID)
  using index 
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table		角色表
create table AWE_ROLE_INFO
(
  roleid        VARCHAR(32) not null,
  sortno        VARCHAR(32),
  rolename      VARCHAR(80),
  roleattribute VARCHAR(80),
  roledescribe  VARCHAR(4000),
  rolestatus    VARCHAR(80),
  inputuser     VARCHAR(32),
  inputorg      VARCHAR(32),
  inputdate     VARCHAR(20),
  updateuser    VARCHAR(32),
  updatedate    VARCHAR(20),
  remark        VARCHAR(250)
)
tablespace ALS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index IDX1_ROLE_INFO on AWE_ROLE_INFO (SORTNO)
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table AWE_ROLE_INFO
  add constraint P_PK_ROLE_INFO primary key (ROLEID)
  using index 
  tablespace ALS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


  select * from DICT_CATALOG;
  
  insert into "NEWRAY"."DICT_CATALOG" ("DICTNO", "SORTNO", "PARENTDICTNO", "DICTNAME", "DICTDESCRIBE", "DICTATTRIBUTE", "INPUTUSER", "INPUTORG", "INPUTDATE", "UPDATEUSER", "UPDATEDATE", "REMARK") values('YesNo', '0010000100', null, '是否', '描述是或者否', '', 'justin', '99bill', '2013/04/13', '', '', '');


insert into "NEWRAY"."DICT_LIBRARY" ("DICTNO", "ITEMNO", "ITEMNAME", "BANKNO", "SORTNO", "ISINUSE", "ITEMDESCRIBE", "ITEMATTRIBUTE", "RELATIVECODE", "ATTRIBUTE1", "ATTRIBUTE2", "ATTRIBUTE3", "ATTRIBUTE4", "ATTRIBUTE5", "ATTRIBUTE6", "ATTRIBUTE7", "ATTRIBUTE8", "INPUTUSER", "INPUTORG", "INPUTDATE", "UPDATEUSER", "UPDATEDATE", "REMARK", "HELPTEXT") values('YesNo', '1', '是', '', '0010', '1', '表示是', '', '', '', '', '', '', '', '', '', '', 'justin', '', '', '', null, null, null);
insert into "NEWRAY"."DICT_LIBRARY" ("DICTNO", "ITEMNO", "ITEMNAME", "BANKNO", "SORTNO", "ISINUSE", "ITEMDESCRIBE", "ITEMATTRIBUTE", "RELATIVECODE", "ATTRIBUTE1", "ATTRIBUTE2", "ATTRIBUTE3", "ATTRIBUTE4", "ATTRIBUTE5", "ATTRIBUTE6", "ATTRIBUTE7", "ATTRIBUTE8", "INPUTUSER", "INPUTORG", "INPUTDATE", "UPDATEUSER", "UPDATEDATE", "REMARK", "HELPTEXT") values('YesNo', '2', '否', '', '0020', '1', '表示否', '', '', '', '', '', '', '', '', '', '', 'justin', '', '', null, null, null, null);

insert into "NEWRAY"."DICT_CATALOG" ("DICTNO", "SORTNO", "PARENTDICTNO", "DICTNAME", "DICTDESCRIBE", "DICTATTRIBUTE", "INPUTUSER", "INPUTORG", "INPUTDATE", "UPDATEUSER", "UPDATEDATE", "REMARK") values('Status', '0010000200', null, '状态', '描述状态', null, 'justin', '99bill', '2013/04/13', null, null, null);

insert into "NEWRAY"."DICT_LIBRARY" ("DICTNO", "ITEMNO", "ITEMNAME", "BANKNO", "SORTNO", "ISINUSE", "ITEMDESCRIBE", "ITEMATTRIBUTE", "RELATIVECODE", "ATTRIBUTE1", "ATTRIBUTE2", "ATTRIBUTE3", "ATTRIBUTE4", "ATTRIBUTE5", "ATTRIBUTE6", "ATTRIBUTE7", "ATTRIBUTE8", "INPUTUSER", "INPUTORG", "INPUTDATE", "UPDATEUSER", "UPDATEDATE", "REMARK", "HELPTEXT") values('Status', '1', '正常', null, '0010', '1', '正常', null, null, null, null, null, null, null, null, null, null, 'justin', null, null, null, null, null, null);
insert into "NEWRAY"."DICT_LIBRARY" ("DICTNO", "ITEMNO", "ITEMNAME", "BANKNO", "SORTNO", "ISINUSE", "ITEMDESCRIBE", "ITEMATTRIBUTE", "RELATIVECODE", "ATTRIBUTE1", "ATTRIBUTE2", "ATTRIBUTE3", "ATTRIBUTE4", "ATTRIBUTE5", "ATTRIBUTE6", "ATTRIBUTE7", "ATTRIBUTE8", "INPUTUSER", "INPUTORG", "INPUTDATE", "UPDATEUSER", "UPDATEDATE", "REMARK", "HELPTEXT") values('Status', '2', '注销', null, '0020', '1', '注销', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

--drop table TABLE_PK_GENERATOR;
create table TABLE_PK_GENERATOR
(
  GENERATORid        INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)primary key,
  pk_name        VARCHAR(32),
  pk_value   DECIMAL(16,0),
  pk_DESCRIBE  VARCHAR(250),
  inputuserid     VARCHAR(32),
  inputorgid      VARCHAR(32),
  inputdate     VARCHAR(20),
  updateuserid    VARCHAR(32),
  updateorgid      VARCHAR(32),
  updatedate    VARCHAR(20),
  remark        VARCHAR(250)
);
--需要有个定时任务在日终时候更新PK_VALUE
insert into "NEWRAY"."TABLE_PK_GENERATOR" ("PK_NAME", "PK_VALUE", "PK_DESCRIBE", "INPUTUSERID", "INPUTORGID", "INPUTDATE", "UPDATEUSERID", "UPDATEORGID", "UPDATEDATE", "REMARK") values('TUserinfo.id', 2013071200000001, '用户表主键', null, null, null, null, null, null, null);



CREATE TABLE T_USERINFO1 (
		ID DECIMAL(25,0) NOT NULL primary key,
		USERCODE VARCHAR(100),
		PASSWORD VARCHAR(100),
		USERNAME VARCHAR(100),
		TYPE VARCHAR(100),
		STATE VARCHAR(10),
		BUSINESSTYPE VARCHAR(100),
		ADDRESS VARCHAR(255),
		EMAIL VARCHAR(255),
		IDNO VARCHAR(255),
		IDTYPE VARCHAR(255),
		PASSANSWER VARCHAR(255),
		PASSQUESTION VARCHAR(255),
		PHONENO VARCHAR(255),
		CHECKBOX VARCHAR(255),
		IDTYPESHOW VARCHAR(255),
		STATESHOW VARCHAR(255)
	);
	
	
