Download all the needed Jar Files and Set up all the environmental variables 
First run the MySQL Server
Run the following queries to create database and tables
provide the Mysql username and password in my MySQLDatastoreUtilities file

CREATE DATABASE `smartportables` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `users` (
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `userId` varchar(20) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `repassword` varchar(45) DEFAULT NULL,
  `usertype` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`firstname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `O_Id` varchar(25) NOT NULL,
  `u_Id` varchar(45) DEFAULT NULL,
  `O_Ddate` varchar(45) DEFAULT NULL,
  `O_Status` varchar(45) DEFAULT NULL,
  `item` varchar(45) DEFAULT NULL,
  `itemCount` int(11) DEFAULT NULL,
  `f_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `card_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`O_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `inventory` (
  `item_name` varchar(45) NOT NULL,
  `stock_qty` int(10) DEFAULT NULL,
  `item_unitprice` int(10) DEFAULT NULL,
  `item_category` varchar(45) DEFAULT NULL,
  `allow_sale` bit(1) NOT NULL ,
  `allow_rebate` bit(1) NOT NULL 
  PRIMARY KEY (`item_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inventory_update`()
BEGIN
create temporary table if not exists temp_table (INDEX(item)) as(select item from orders order by O_Ddate desc LIMIT 1);
update inventory set stock_qty= stock_qty-1 where item_name in (select item from temp_table);
drop temporary table temp_table;
END$$
DELIMITER ;


Run the tomcat server which containes the folder "SmartPortables2"- My assignment #3

Once the tomcat is up and running, In Command promt navigate through the folders where i have my class files in SmartPortables2 and compile the java files using the 
command javac *.java

once the compilation is done type the following in the browser
http://localhost/SmartPortables2/home

you are now in the application where you can do the regular activities mentioned in assignment 1 as well as assignment 2.

Note: The login based reports are done for the store manager.
 user name= admin
password= admin 
for the store manager to view the reports.
