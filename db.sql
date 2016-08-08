
create database bookstore;

use bookstore;

--分类表
create table categorys(
	id   VARCHAR(100) PRIMARY KEY,
	name varchar(100)  not null unique,
	des  varchar(255) 
);

--图书表
create table books(
	id varchar(100) primary key,
	name varchar(100),
	author varchar(100),
	price float(8,2),
	path varchar(100),
	filename varchar(100),
	des varchar(255),
	categoryId varchar(100),
	CONSTRAINT category_id_fk FOREIGN KEY (categoryId) REFERENCES categorys(id)  

)





--用户表
create table customers(
	id varchar(100)  primary key,
	username varchar(100) not null unique,
	password varchar(100) not null,
	photo varchar(20) not null unique,
	address varchar(255) not null ,
	email varchar(20) not null unique,
	code varchar(200) unique,
	actived bit(1) 
)

--订单表
create table orders(
	ordernum varchar(100) primary key,
	price float(8,2),
	number int,
	status int,
	customerId VARCHAR(100),
	CONSTRAINT customerId_fk FOREIGN KEY (customerId) REFERENCES customers(id)  
)
)

--订单详情表
create table orderitems(
	id varchar(100) primary key,
	number int,
	price float(8,2),
	ordernum varchar(100),
	bookid varchar(100),
	CONSTRAINT ordernum_fk FOREIGN KEY (ordernum) REFERENCES orders(ordernum),
	CONSTRAINT bookid_fk FOREIGN KEY (bookid) REFERENCES books(id)    
)

--订单编号表
create table ordernum(
	prefix date,
	num int	
)
