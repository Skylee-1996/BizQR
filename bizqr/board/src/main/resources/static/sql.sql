------------2024-02-02-------------

create database bizqrdb;

create user 'bizqrUser'@'localhost' identified by 'mysql';

grant all privileges on bizqrdb.* to 'bizqrUser'@'localhost' with grant option;
flush privileges;

-------------------------------------------

create table board(
                      bno bigint auto_increment,
                      email varchar(255) not null,
                      title varchar(255) not null,
                      user_name varchar(100),
                      content text,
                      reg_date datetime default now(),
                      mod_date datetime default now(),
                      read_count bigint,
                      cmt_qty INT DEFAULT 0,
                      has_file INT DEFAULT 0;

primary key(bno));

create table comment(
                        cno bigint auto_increment,
                        bno bigint not null,
                        writer varchar(200) not null,
                        content text not null,
                        reg_at datetime default now(),
                        mod_at datetime default now(),
                        primary key(cno));

create table file(
                     uuid varchar(256) not null,
                     save_dir varchar(256) not null,
                     file_name varchar(256) not null,
                     file_type tinyint(1) default 0,
                     bno bigint,
                     file_size bigint,
                     reg_at datetime default now(),
                     primary key(uuid));
