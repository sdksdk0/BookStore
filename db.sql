
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
	phone varchar(20) not null unique,
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


--权限控制
create table users(
	id varchar(100) primary key,
	username varchar(100) not null unique,
	password varchar(100) not null
);

create table roles(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	des varchar(255)	
);

create table functions(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	uri varchar(255)
)

CREATE TABLE role_function(
	r_id VARCHAR(100),
	f_id VARCHAR(100),
	PRIMARY KEY(r_id,f_id),
	CONSTRAINT role_id_fk1 FOREIGN KEY (r_id) REFERENCES roles(id),
	CONSTRAINT function_id_fk FOREIGN KEY (f_id) REFERENCES functions(id)
);
CREATE TABLE user_role(
	u_id VARCHAR(100),
	r_id VARCHAR(100),
	PRIMARY KEY(u_id,r_id),
	CONSTRAINT user_id_fk FOREIGN KEY (u_id) REFERENCES users(id),
	CONSTRAINT role_id_fk2 FOREIGN KEY (r_id) REFERENCES roles(id)
);

insert into `functions` (`id`, `name`, `uri`) values('1','主页','/BookStore/manage/index.jsp');
insert into `functions` (`id`, `name`, `uri`) values('2','消息','/BookStore/manage/message.jsp');
insert into `functions` (`id`, `name`, `uri`) values('3','添加分类','/BookStore/manage/addCategory.jsp');
insert into `functions` (`id`, `name`, `uri`) values('4','查询分类','/BookStore/servlet/ManageServlet?op=listCategories');
insert into `functions` (`id`, `name`, `uri`) values('5','添加书籍','/BookStore/servlet/ManageServlet?op=addBookUI');
insert into `functions` (`id`, `name`, `uri`) values('6','查询书籍','/BookStore/servlet/ManageServlet?op=listBooks');


insert into `role_function` (`r_id`, `f_id`) values('1','1');
insert into `role_function` (`r_id`, `f_id`) values('2','1');
insert into `role_function` (`r_id`, `f_id`) values('1','2');
insert into `role_function` (`r_id`, `f_id`) values('2','2');
insert into `role_function` (`r_id`, `f_id`) values('1','3');
insert into `role_function` (`r_id`, `f_id`) values('1','4');
insert into `role_function` (`r_id`, `f_id`) values('1','5');
insert into `role_function` (`r_id`, `f_id`) values('2','5');
insert into `role_function` (`r_id`, `f_id`) values('1','6');
insert into `role_function` (`r_id`, `f_id`) values('2','6');


insert into `roles` (`id`, `name`, `des`) values('1','超级管理员','可以访问任何页面');
insert into `roles` (`id`, `name`, `des`) values('2','普通管理员','书籍部分');


insert into `users` (`id`, `username`, `password`) values('1','admin','123');
insert into `users` (`id`, `username`, `password`) values('2','aa','123');


insert into `user_role` (`u_id`, `r_id`) values('1','1');
insert into `user_role` (`u_id`, `r_id`) values('2','2');

