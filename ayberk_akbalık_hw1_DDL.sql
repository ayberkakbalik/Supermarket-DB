drop table if exists customer;
drop table if exists branch;
drop table if exists product;
drop table if exists ordering;
drop table if exists storing;

create table customer 
(
c_id           int            not null auto_increment,
name         varchar(30)      not null,
surname      varchar(30)      not null,
address      varchar(30)      not null,
phone_number varchar(11)      not null,
primary key (c_id)
);

create table product
(
p_id         int              not null auto_increment,
name         varchar(30)      not null,
description  varchar(250)     not null,
price        numeric(4,2)     not null,
primary key (p_id)
);

create table branch
(
b_id           int              not null auto_increment,
name         varchar(30)      not null,
address       varchar(30)      not null,
primary key (b_id)
);

create table ordering
(
c_id int not null,
p_id int not null,
b_id int not null,
order_date   date  not null,
quantity int not null,
primary key (c_id, p_id, b_id),
foreign key (c_id) references customer (c_id),
foreign key (p_id) references product (p_id),
foreign key (b_id) references branch (b_id)
);

create table storing
(
p_id int not null,
b_id int not null,
stock_quantity int,
primary key (p_id, b_id),
foreign key (p_id) references product (p_id),
foreign key (b_id) references branch (b_id)
);
