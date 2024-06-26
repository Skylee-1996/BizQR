------------2024-02-02-------------

create user 'bizqrUser'@'localhost' identified by 'mysql';

grant all privileges on bizqrdb.* to 'bizqrUser'@'localhost' with grant option;
flush privileges;

create database bizqrdb;

------2024--02-07------
-- user 테이블 생성
CREATE TABLE IF NOT EXISTS `user` (
    `email` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255),
    `pwd` VARCHAR(255) NOT NULL,
    `nick_name` VARCHAR(255),
    `phone_num` VARCHAR(255),
    `reg_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_login` DATETIME,
    `is_social` TINYINT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (`email`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- board 테이블 생성
-- nickName 조건 수정 (24.02.22)
CREATE TABLE IF NOT EXISTS `board` (
    `bno` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `nick_name` VARCHAR(255),
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT,
    `main_image` VARCHAR(255),
    `reg_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `mod_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `cmtQty` INT DEFAULT 0,
    `read_count` BIGINT DEFAULT 0,
    PRIMARY KEY (`bno`),
    FOREIGN KEY (`email`) REFERENCES `user`(`email`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- store 테이블 생성
    CREATE TABLE IF NOT EXISTS `store` (
    `store_id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `register_num` BIGINT NOT NULL,
    `store_name` VARCHAR(255) NOT NULL,
    `store_address` VARCHAR(255) NOT NULL,
    `store_number` VARCHAR(255) NOT NULL,
    `store_type` VARCHAR(255) NOT NULL,
    `store_hours` VARCHAR(255),
    `company` VARCHAR(255),
    `logo_Image` VARCHAR(255),
    `reg_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`store_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- menu items 테이블 생성
CREATE TABLE IF NOT EXISTS `menu_item` (
    `menu_id` bigint NOT NULL auto_increment,
    `store_id` BIGINT,
    `tab_name` VARCHAR(255),
    `menu_name` VARCHAR(255),
    `menu_price` BIGINT,
    PRIMARY KEY (`menu_id`),
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- file 테이블 생성
CREATE TABLE IF NOT EXISTS `file` (
    `uuid` VARCHAR(255) NOT NULL,
    `bno` BIGINT,
    `store_id` BIGINT,
    `menu_id` BIGINT,
    `save_dir` VARCHAR(255),
    `file_name` VARCHAR(255) NOT NULL,
    `file_type` TINYINT,
    `file_size` BIGINT,
    `reg_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`uuid`),
    FOREIGN KEY (`bno`) REFERENCES `board`(`bno`),
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`),
    FOREIGN KEY (`menu_id`) REFERENCES `menu_item`(`menu_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- social_user 테이블 생성
CREATE TABLE IF NOT EXISTS `social_user` (
     `email` VARCHAR(255) NOT NULL,
    `provider` VARCHAR(255) NOT NULL,
    `nick_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`email`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- auth_user 테이블 생성
CREATE TABLE IF NOT EXISTS `auth_user` (
    `email` VARCHAR(255) NOT NULL,
    `auth` VARCHAR(255) NOT NULL,
    FOREIGN KEY (`email`) REFERENCES `user`(`email`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- register 테이블 생성
CREATE TABLE IF NOT EXISTS `register` (
    `register_num` bigint auto_increment,
    `email`	 VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `company` VARCHAR(255) NOT NULL,
    `store_name` VARCHAR(255) NOT NULL,
    `store_address` VARCHAR(255) NOT NULL,
    `store_type` VARCHAR(255) NOT NULL,
    `subscribe` VARCHAR(255) NOT NULL,
    `owner_num` VARCHAR(255),
    `store_num` VARCHAR(255),
    `paid_time` VARCHAR(255) NOT NULL,
    `merchant_uid` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`register_num`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 컬럼추가 2024.02.13 --
ALTER TABLE `bizqrdb`.`register`
    ADD COLUMN `isRegistered` TINYINT NULL DEFAULT 0 AFTER `store_num`;

-- visit(통계)테이블 생성
CREATE TABLE `bizqrdb`.`visit` (
    `index` BIGINT NOT NULL DEFAULT 0,
    `visit` DATETIME NULL DEFAULT NOW(),
    `number` BIGINT NOT NULL DEFAULT 0);



-- table 테이블 생성
CREATE TABLE IF NOT EXISTS `tables` (
    `table_id` VARCHAR(255) NOT NULL,
    `store_id` BIGINT,
    `table_qr` VARCHAR(255),
    `total_price` BIGINT,
    `is_using` INT(1) DEFAULT 0,
    PRIMARY KEY (`table_id`),
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- order_items 테이블 생성
CREATE TABLE IF NOT EXISTS `order_items` (
    `menu_id` bigint NOT NULL,
    `store_id` BIGINT NOT NULL,
    `table_id` VARCHAR(255) NOT NULL,
    `menu_name` VARCHAR(255) NOT NULL,
    `menu_price` BIGINT,
    `menu_amount` BIGINT,
    `reg_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`menu_id`) REFERENCES `menu_item`(`menu_id`),
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`),
    FOREIGN KEY (`table_id`) REFERENCES `tables`(`table_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- order 테이블 생성
CREATE TABLE IF NOT EXISTS `order` (
    `order_id` VARCHAR(255) NOT NULL,
    `table_id` VARCHAR(255) NOT NULL,
    `store_id` BIGINT NOT NULL,
    `order_status` tinyint default 0,
    `total_price` BIGINT,
    `user_request` VARCHAR(255),
    `reg_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`table_id`) REFERENCES `tables`(`table_id`),
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- payment 테이블 생성
CREATE TABLE IF NOT EXISTS `payment` (
    `payment_id` VARCHAR(255) NOT NULL,
    `order_id` VARCHAR(255) NOT NULL,
    `payment_method` VARCHAR(255),
    `payment_status` tinyint default 0,
    `total_amount` bigint default 0,
    PRIMARY KEY (`payment_id`),
    FOREIGN KEY (`order_id`) REFERENCES `order`(`order_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- order_history 테이블 생성
CREATE TABLE IF NOT EXISTS `order_history` (
    `order_history_id` BIGINT NOT NULL AUTO_INCREMENT,
    `order_id` VARCHAR(255) NOT NULL,
    `table_id` VARCHAR(255) NOT NULL,
    `store_id` BIGINT NOT NULL,
    `menu_id` BIGINT NOT NULL,
    `menu_name` VARCHAR(255) NOT NULL,
    `menu_price` BIGINT,
    `menu_amount` BIGINT,
    `total_price` BIGINT,
    `order_status` TINYINT default 0,
    `user_request` VARCHAR(255),
    `order_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`order_history_id`),
    FOREIGN KEY (`order_id`) REFERENCES `order`(`order_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- Inventory 테이블 생성
CREATE TABLE IF NOT EXISTS inventory (
    `menu_id` bigint NOT NULL,
    `current_stock` INT(20) NOT NULL,
    `supplier_id` VARCHAR(255),
    `last_restock_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (menu_id),
    FOREIGN KEY (menu_id) REFERENCES `menu_item`(menu_id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- comment 테이블 생성
-- nickName 삭제 후 email로 변경 (24.02.22)
CREATE TABLE IF NOT EXISTS `comment` (
    `cno` BIGINT NOT NULL AUTO_INCREMENT,
    `bno` BIGINT NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    `reg_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `mod_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`cno`),
    FOREIGN KEY (`bno`) REFERENCES `board`(`bno`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- transaction 테이블 생성
CREATE TABLE IF NOT EXISTS `transaction` (
    `transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
    `store_id` BIGINT NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `type` VARCHAR(50) NOT NULL,
    `amount` DECIMAL(10, 2) NOT NULL,
    `reg_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`transaction_id`),
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`),
    FOREIGN KEY (`email`) REFERENCES `user`(`email`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

----------- 02/08 -----------
/* 관리자계정 추가 */
/* 관리자 계정 회원가입에서 admin@admin.com 으로 가입후 권한 추가 하기*/

INSERT INTO auth_user (email, auth)
VALUES ('admin@admin.com', 'ROLE_ADMIN');

-----임시 점포---
INSERT INTO `store` (`store_id`, `email`, `register_num`, `store_name`, `store_address`, `store_number`, `store_hours`, `company`, `reg_at`) VALUES
    ('123', 'test@example.com', 1234567890, 'Test Store', '123 Test Address, Test City', '123-456-7890', '09:00-18:00', 'Test Company', CURRENT_TIMESTAMP);

-- 2024-02-26 --
-- store_payment
create table store_payment(
    imp_uid varchar(255) not null,
    merchant_uid varchar(255) not null,
    buyer_email varchar(255) not null,
    buyer_name varchar(255) not null,
    buyer_company varchar(255) not null,
    buyer_address varchar(255) not null,
    buyer_ownerTelNum varchar(255) not null,
    buyer_storeTelNum varchar(255) not null,
    item_name varchar(255) not null,
    item_amount int not null,
    paid_time varchar(255) not null,
    primary key(imp_uid)
    );

create table table_pay_history(
    imp_uid varchar(255) not null,
    merchant_uid varchar(255),
    store_id bigint,
    table_id varchar(255),
    total_price bigint,
    paid_time varchar(255),
    primary key(imp_uid)
    );

