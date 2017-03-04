drop table IF EXISTS rv.customer;
create table rv.customer (
    id int PRIMARY KEY auto_increment not null,
    name varchar(100) not null,
	email varchar(100) not null,
	phone varchar(100) not null,
	address varchar(100) not null
);
