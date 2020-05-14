create table if not exists user(
	id varchar(64) not null unique,
	username varchar(64) not null unique,
	password varchar(64) not null,
	email varchar(64) not null unique,
	firstname varchar(64) not null,
	lastname varchar(64) not null,
	mobileno varchar(12) not null,
	country varchar(20) not null,
	verificationstatus boolean,
	verificationtoken varchar(200),
	primary key(id)
);