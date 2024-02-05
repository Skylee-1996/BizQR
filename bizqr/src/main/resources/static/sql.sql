------------2024-02-02-------------

create database bizqrdb;

create user 'bizqrUser'@'localhost' identified by 'mysql';

grant all privileges on bizqrdb.* to 'bizqrUser'@'localhost' with grant option;
flush privileges;

-------------------------------------------
------------2024-02-05-------------
create table user(
email varchar(255) not null,
pwd varchar(100) not null,
nick_name varchar(100) not null,
reg_date datetime default now(),
last_login datetime default now(),
primary key(email));

create table auth_user(
email varchar(255) not null,
auth varchar(200) not null,
foreign key(email) references user(email) on delete cascade);