drop database if exists auction;

create database auction;

use auction;

#用户表
create table auction_user(
  user_id int(11) auto_increment,
  username varchar(50) not null,
  userpass varchar(50) not null,
  email varchar(100) not null,
  primary key(user_id),
  unique(username)
);

INSERT INTO auction_user (username,userpass,email) VALUES ('tomcat','tomcat','zpkx.wang@gmail.com');
INSERT INTO auction_user (username,userpass,email) VALUES ('mysql','mysql','spring_test@163.com');

#物品种类表
create table kind(
  kind_id int(11) auto_increment,
  kind_name varchar(50) not null, 
  kind_desc varchar(255) not null,
  primary key(kind_id)
);

INSERT INTO kind (kind_name,kind_desc) VALUES ('Painting','great masterpieces of famous artists');
INSERT INTO kind (kind_name,kind_desc) VALUES ('Car','fantastic cars');

#物品状态表
create table state(
  state_id int(11) auto_increment,
  state_name varchar(10),
  primary key(state_id)
);

INSERT INTO state (state_name) VALUES ('In progress');
INSERT INTO state (state_name) VALUES ('Succeed');
INSERT INTO state (state_name) VALUES ('Fail');

#物品表
create table item(
  item_id int(11) auto_increment,
  item_name varchar(255) not null,
  item_remark varchar(255) not null,
  item_desc varchar(255),
  kind_id int(11) not null, 
  addtime date not null,
  endtime date not null,
  init_price double not null, 
  max_price double not null, 
  owner_id int(11) not null, 
  winer_id int(11), 
  state_id int(11) not null, 
  primary key(item_id),
  FOREIGN KEY(kind_id) REFERENCES kind(kind_id), 
  FOREIGN KEY(owner_id) REFERENCES auction_user(user_id),
  FOREIGN KEY(winer_id) REFERENCES auction_user(user_id),
  FOREIGN KEY(state_id) REFERENCES state(state_id)
); 

INSERT INTO item ( item_name , item_remark , item_desc, kind_id, addtime , endtime, init_price,  max_price,  owner_id,  winer_id,  state_id)
	VALUES ( 'aaa', 'abc', 'abcd', 1, '2009-02-06', CURDATE()+92, 230, 250, 1,  null,  1);

INSERT INTO item ( item_name , item_remark , item_desc, kind_id, addtime , endtime, init_price,  max_price,  owner_id,  winer_id,  state_id)
	VALUES ( 'bbb', 'bcd', 'good bcd', 1, '2009-10-10', CURDATE()-2, 210, 210, 2,  null,  3);

INSERT INTO item ( item_name , item_remark , item_desc, kind_id, addtime , endtime, init_price,  max_price,  owner_id,  winer_id,  state_id)
	VALUES ( 'ccc', 'bad ccc', 'old ccc', 2, '2009-12-12', CURDATE()-5, 21000, 25000, 2,  1,  2);

#竞标历史表
create table bid(
  bid_id int(11) auto_increment,
  user_id int(11) not null,
  item_id int(11) not null,
  bid_price double not null,
  bid_date date not null, 
  primary key(bid_id),
  unique(item_id , bid_price),
  FOREIGN KEY(user_id) REFERENCES auction_user(user_id), 
  FOREIGN KEY(item_id) REFERENCES item(item_id)   
);

INSERT INTO bid ( user_id , item_id , bid_price, bid_date)
	VALUES ( 2, 1, 250, CURDATE()-2);
INSERT INTO bid ( user_id , item_id , bid_price, bid_date)
	VALUES ( 1, 3, 25000, CURDATE()-6);

