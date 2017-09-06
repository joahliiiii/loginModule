create table user(
  username VARCHAR(20) not null,
  password varchar(25) not null,
  sex varchar(8),
  email varchar(30),
  website varchar(50),
  profile varchar(500)
);
insert into user(username, password, sex, email, website, profile) VALUES('joah','summering','male','joah@joah.com','132',NULL);
INSERT INTO user(username,password) VALUES('joah','jjjjj');