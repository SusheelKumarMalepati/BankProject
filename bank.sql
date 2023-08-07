-- bank database
create database bank;
use bank;
create table customer (
  fname varchar(25),
  lname varchar(25),
  city varchar(25) ,
  branch varchar(25),
  mobile long ,
  accountno int primary key auto_increment
);
ALTER TABLE customer AUTO_INCREMENT=360000;
create table account (
Accountno long,
balance int
);
create table admin (
username varchar(25) primary key,
password varchar(25)
);
insert into admin values('susheel','sus'); 