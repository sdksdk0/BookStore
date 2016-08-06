
create database bookstore;

use bookstore;

create table categorys(
	id   VARCHAR(100) PRIMARY KEY,
	name varchar(100)  not null unique,
	des  varchar(255) 
);


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





