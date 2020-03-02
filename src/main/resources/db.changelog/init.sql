-- create database---
CREATE DATABASE sn;

-- use database---
USE sn;

-- create user---
CREATE USER 'sn_user'@'%' IDENTIFIED BY 'sn_user';

-- grant privileges---
GRANT ALL PRIVILEGES ON * . * TO 'sn_user'@'%';
GRANT ALL PRIVILEGES ON sn.* TO 'sn_user'@'%';

-- flush privileges---
FLUSH PRIVILEGES;

-- check if everything ok---
SHOW GRANTS FOR sn_user;
