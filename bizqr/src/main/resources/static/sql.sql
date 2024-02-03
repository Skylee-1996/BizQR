------------2024-02-02-------------

create database bizqrdb;

create user 'bizqrUser'@'localhost' identified by 'mysql';

grant all privileges on bizqrdb.* to 'bizqrUser'@'localhost' with grant option;
flush privileges;

-------------------------------------------
