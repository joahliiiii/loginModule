create table User(
  username VARCHAR(20) not null,
  password varchar(25) not null,
  sex varchar(8),
  email varchar(30),
  website varchar(50),
  profile varchar(500),
  primary key (username)
);
insert into User(username, password, sex, email, website, profile) VALUES('joah','summering','male','joah@joah.com','132',NULL);
INSERT INTO User(username,password) VALUES('joah','jjjjj');

# 删除所有表单数据
delete from User;
# 清空全部数据，不写日志，不可恢复，速度极快
truncate table User;

# 删除一张表
drop table User;

# 查看表结构
desc user;
# 修改表的名字
alter table user rename User ;