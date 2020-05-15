create table if not exists user(
	username varchar(64) not null unique,
	password varchar(64) not null,
	email varchar(64) not null unique,
	firstname varchar(64) not null,
	lastname varchar(64) not null,
	mobileno varchar(12) not null,
	verificationstatus boolean,
	verificationtoken varchar(200),
	primary key(username)
);

create table if not exists address(
	id varchar(64) not null unique,
	username varchar(64) not null unique,
	streetname varchar(64) not null,
	city varchar(64) not null ,
	country varchar(64) not null,
	pincode varchar(64) not null,
	type varchar(12) not null,
	primary key(id)
);