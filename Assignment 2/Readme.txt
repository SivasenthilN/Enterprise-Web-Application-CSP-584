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

Once sql is up and running start the Mongo DB
Create the table SmartPortables which contain the collection userReviews

Once the Mongo DB is started, run the environmental batch file to make sure all the paths are set

Run the tomcat server which containes the folder "SmartPortables2"- My assignment #2

Once the tomcat is up and running, In Command promt navigate through the folders where i have my class files in SmartPortables2 and compile the java files using the 
command javac *.java

once the compilation is done type the following in the browser
http://localhost/SmartPortables2/home

you are now in the application where you can do the regular activities mentioned in assignment 1 as well as assignment 2.

Note: The login based Data Analytics couldnt be done due to time constraint but most part of the bonus assignment in terms of manupulating the Mongo DB data
is carried out. The Graph is not executed in the assignment which was mentioned in the bonus part.

Recorded 2 videos- one for regular requirements and one for bonus requirments in assignment#2

