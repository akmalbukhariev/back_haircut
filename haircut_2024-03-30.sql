# ************************************************************
# Sequel Ace SQL dump
# Version 20059
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 176.221.28.239 (MySQL 11.4.0-MariaDB)
# Database: haircut
# Generation Time: 2024-03-30 04:08:50 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table comments
# ------------------------------------------------------------

CREATE TABLE `comments` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hairdresser_no` int(11) NOT NULL,
  `name` longtext DEFAULT NULL,
  `date` longtext DEFAULT NULL,
  `likes` int(4) NOT NULL,
  `seen` int(4) NOT NULL,
  `comments` longtext DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



# Dump of table favorite_hairdresser
# ------------------------------------------------------------

CREATE TABLE `favorite_hairdresser` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `client_no` int(11) NOT NULL,
  `hairdresser_no` int(11) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `favorite_hairdresser` WRITE;
/*!40000 ALTER TABLE `favorite_hairdresser` DISABLE KEYS */;

INSERT INTO `favorite_hairdresser` (`no`, `client_no`, `hairdresser_no`)
VALUES
	(22,1,17),
	(23,1,18),
	(24,2,17);

/*!40000 ALTER TABLE `favorite_hairdresser` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hairdresser_booked_client_himself
# ------------------------------------------------------------

CREATE TABLE `hairdresser_booked_client_himself` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hairdresser_no` int(11) NOT NULL,
  `client_phone` varchar(15) NOT NULL,
  `client_name` varchar(30) DEFAULT NULL,
  `service` longtext DEFAULT NULL,
  `date` longtext DEFAULT NULL,
  `note` longtext NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

LOCK TABLES `hairdresser_booked_client_himself` WRITE;
/*!40000 ALTER TABLE `hairdresser_booked_client_himself` DISABLE KEYS */;

INSERT INTO `hairdresser_booked_client_himself` (`no`, `hairdresser_no`, `client_phone`, `client_name`, `service`, `date`, `note`)
VALUES
	(3,18,'998978876543','Shokir','3,5','1.3.2024/21:40 - 1.3.2024/21:50',''),
	(4,18,'998975554433','Zokir','4,','18.3.2024/13:00 - 18.3.2024/13:40',''),
	(5,18,'998987771123','Samandar','4,5,','17.3.2024/18:5 - 17.3.2024/18:15','Thus is very important appointment.'),
	(6,18,'998976663399','Ali','4,','21.3.2024/22:54 - 21.3.2024/23:55','Esda tut');

/*!40000 ALTER TABLE `hairdresser_booked_client_himself` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hairdresser_booked_clients
# ------------------------------------------------------------

CREATE TABLE `hairdresser_booked_clients` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hairdresser_no` int(11) NOT NULL,
  `client_no` int(11) NOT NULL,
  `service` longtext DEFAULT NULL,
  `date` longtext DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `hairdresser_booked_clients` WRITE;
/*!40000 ALTER TABLE `hairdresser_booked_clients` DISABLE KEYS */;

INSERT INTO `hairdresser_booked_clients` (`no`, `hairdresser_no`, `client_no`, `service`, `date`)
VALUES
	(15,17,1,'1,2','26.2.2024/12:20-26.2.2024/20:30'),
	(16,17,1,'4','26.2.2024/21:30 - 26.2.2024/21:50'),
	(17,18,2,'3,4','18.3.2024/1:58 - 18.3.2024/2:57'),
	(18,18,2,'5','18.3.2024/1:1 - 18.3.2024/2:52'),
	(19,19,1,'6','1.3.2024/19:25 - 1.3.2024/20:31'),
	(20,18,1,'3,4,5','1.3.2024/08:20 - 1.3.2024/17:40'),
	(21,18,1,'3,4,5','1.3.2024/22:47 - 1.3.2024/23:43');

/*!40000 ALTER TABLE `hairdresser_booked_clients` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hairdresser_info
# ------------------------------------------------------------

CREATE TABLE `hairdresser_info` (
  `no` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `address` longtext DEFAULT NULL,
  `location` varchar(30) DEFAULT '1,1',
  `language` varchar(30) DEFAULT 'Uzbek',
  `notification` varchar(30) DEFAULT 'n_5_minute',
  `workingHour` longtext DEFAULT NULL,
  `uploadImage` longtext DEFAULT NULL,
  `storeImage` longtext DEFAULT NULL,
  `document` longtext DEFAULT NULL,
  `awards` longtext DEFAULT NULL,
  `profession` longtext NOT NULL,
  `deleted` int(4) NOT NULL DEFAULT 0,
  `blocked` int(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `hairdresser_info` WRITE;
/*!40000 ALTER TABLE `hairdresser_info` DISABLE KEYS */;

INSERT INTO `hairdresser_info` (`no`, `name`, `surname`, `phone`, `address`, `location`, `language`, `notification`, `workingHour`, `uploadImage`, `storeImage`, `document`, `awards`, `profession`, `deleted`, `blocked`)
VALUES
	(17,'Jamshid','Halimov','99898765452','Uzbekistan','36.3456,126.3344','Uzbek','n_5_minute','09:10 - 18:10','6.jpg','\\back_haircut\\images\\1708235786401.jpg','yomkm','adsa','Sartarosh',0,0),
	(18,'Sanjar','Jabborov','99897654245','Uzbekistan','36.3156,126.1144','Uzbek','n_5_minute','08:20 - 17:40','1.jpg','\\Dasturlash\\Github\\back_haircut\\images\\1708237426177.jpg','dffg','ddff','Sartarosh',0,0),
	(19,'RUSTAM','string','99890786552','string','string','string','string','string','1.jpg','\\Dasturlash\\Github\\back_haircut\\images\\1708237664994.jpg','dffg','ddff','string',0,0),
	(20,'Nurbek','Qurbonov','99897654341','Uzbekiston','36.878,126.6735','Uzbek','n_5_minute',NULL,NULL,NULL,NULL,NULL,'Sartarosh',0,0),
	(21,'Jaxongir','Jabborov','998973334444','Uzbekiston','1,1','Uzbek','n_5_minute',NULL,NULL,NULL,NULL,NULL,'Sartarosh',0,0);

/*!40000 ALTER TABLE `hairdresser_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table photo_session
# ------------------------------------------------------------

CREATE TABLE `photo_session` (
  `no` int(11) NOT NULL,
  `hairdresser_no` int(11) NOT NULL,
  `storeFile` varchar(50) DEFAULT NULL,
  `uploadFile` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no`,`hairdresser_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;



# Dump of table scores
# ------------------------------------------------------------

CREATE TABLE `scores` (
  `hairdresser_no` int(11) NOT NULL,
  `score1` int(1) NOT NULL DEFAULT 0,
  `score2` int(1) NOT NULL DEFAULT 0,
  `score3` int(1) NOT NULL DEFAULT 0,
  `score4` int(1) NOT NULL DEFAULT 0,
  `score5` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`hairdresser_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

LOCK TABLES `scores` WRITE;
/*!40000 ALTER TABLE `scores` DISABLE KEYS */;

INSERT INTO `scores` (`hairdresser_no`, `score1`, `score2`, `score3`, `score4`, `score5`)
VALUES
	(17,3,5,1,2,4),
	(18,7,3,13,6,8),
	(19,1,3,20,15,30);

/*!40000 ALTER TABLE `scores` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table services
# ------------------------------------------------------------

CREATE TABLE `services` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hairdresser_no` int(11) NOT NULL,
  `services` longtext NOT NULL,
  `price` int(10) NOT NULL DEFAULT 1,
  `color` varchar(30) NOT NULL DEFAULT '#ffffff',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;

INSERT INTO `services` (`no`, `hairdresser_no`, `services`, `price`, `color`)
VALUES
	(1,17,'Ukladka',1000,'#673ab7'),
	(2,17,'Tirnoq bo\'yash',23000,'#f44236'),
	(3,18,'Soch olish',19500,'#1f96f3'),
	(4,18,'Soch bo\'yash',14300,'#f294f1'),
	(5,18,'Minikyur',30300,'#a5eeb8'),
	(6,19,'Soqol olish',12000,'#a2efb8');

/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_info
# ------------------------------------------------------------

CREATE TABLE `user_info` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(15) DEFAULT NULL,
  `name` longtext DEFAULT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `password` longtext DEFAULT NULL,
  `location` varchar(30) DEFAULT '1,1',
  `language` varchar(30) DEFAULT 'Uzbek',
  `notification` varchar(30) DEFAULT 'n_5_minute',
  `is_customer` int(2) DEFAULT NULL COMMENT '1=yes,0=no',
  `is_hairdresser` int(2) DEFAULT 0 COMMENT '1=yes,0=no',
  `deleted` int(2) DEFAULT 0 COMMENT '1=yes,0=no',
  `blocked` int(2) DEFAULT 0 COMMENT '1=yes,0=no',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;

INSERT INTO `user_info` (`no`, `phone`, `name`, `surname`, `password`, `location`, `language`, `notification`, `is_customer`, `is_hairdresser`, `deleted`, `blocked`)
VALUES
	(1,'998987776655','Akmal','Buxariyev','{bcrypt}$2a$10$Vt/yTkzz1.EsO6eNViWFXuTBcJb6xdAXlU8d5N.CT7yeWtEuD.2PC','126,36','Uzbek_cyrillic','n_1_30_hour',1,0,0,0),
	(2,'374644401232','Jasur','Akromov','{bcrypt}$2a$10$TFjGTox0P5ag3s1P.WUSH.L6xrU3GcNR7ii.QnxQ/28CUSxLCE/Fu','36.155','English','n_5_minute',1,1,0,0),
	(3,'998975643123','Zokirbek','Jalolov','{bcrypt}$2a$10$0jQQ9qM8vd85TxTcl.8OK.1n7y9CztqTJoqyJyNRq6BFJ2ZjGhyyi','36.1234,126.45762','Uzbek_cyrillic','n_45_minute',1,0,0,0),
	(4,'998977766554','Qodir','Muxiddinov','{bcrypt}$2a$10$38hj4k2T5uFjmydrnXsix.Vn5bLpAZ9SUR.YocWt7xOOBb.8qa4Zy','36.1234,126.45762','Uzbek','n_5_minute',1,0,0,0),
	(5,'998445567336','Zokir','Ikromov','{bcrypt}$2a$10$g4uasWv.Qox4XgUfCy3FlOK9zKFrHjqmk/ZsS/tdDKyVXD.Amaqhi','36.1234,126.45762','Uzbek','n_5_minute',1,0,0,0),
	(6,'998998887766','Ikrom','Iskandarov','{bcrypt}$2a$10$axZWy/Uz/tmT2cF8CdcGp.snSmV8afZPWNPv0mm3/8aVbecfuY6mu','1,1','Uzbek','n_5_minute',0,0,0,0),
	(7,'9989711122331','Farhod','Tojiddinov','{bcrypt}$2a$10$mdF2uLWdkkql/Oi63UK5Du/GfbGFQ0/lg59gGeiUfOHc0OH2PE9/e','1,1','Uzbek','n_5_minute',1,0,0,0),
	(8,'998908840694','Jonibek','Qudratov','','1,1','Uzbek','n_5_minute',1,1,0,0),
	(9,'998901112222','Rustam','Nabiyev','','1,1','Uzbek','n_5_minute',1,0,0,0);

/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_order_history
# ------------------------------------------------------------

CREATE TABLE `user_order_history` (
  `no` int(11) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `hairdresser_no` int(11) DEFAULT NULL,
  `date` varchar(11) DEFAULT NULL,
  `hour` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
