create schema if not exists test collate utf8mb4_general_ci;

create table if not exists customer
(
	id int auto_increment
		primary key,
	first_name varchar(32) not null,
	last_name varchar(32) not null,
	email varchar(32) not null,
	constraint customer_email_uindex
		unique (email)
);
