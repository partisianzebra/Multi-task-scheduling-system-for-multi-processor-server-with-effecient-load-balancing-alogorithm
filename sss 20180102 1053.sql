-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema multicore
--

CREATE DATABASE IF NOT EXISTS multicore;
USE multicore;

--
-- Definition of table `priwaiting`
--

DROP TABLE IF EXISTS `priwaiting`;
CREATE TABLE `priwaiting` (
  `name` text NOT NULL,
  `user` text NOT NULL,
  `memory` decimal(10,0) NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `processer` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `priwaiting`
--

/*!40000 ALTER TABLE `priwaiting` DISABLE KEYS */;
/*!40000 ALTER TABLE `priwaiting` ENABLE KEYS */;


--
-- Definition of table `process`
--

DROP TABLE IF EXISTS `process`;
CREATE TABLE `process` (
  `name` text NOT NULL,
  `user` text NOT NULL,
  `memory` decimal(10,0) NOT NULL,
  `id` int(11) NOT NULL,
  `processer` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `process`
--

/*!40000 ALTER TABLE `process` DISABLE KEYS */;
INSERT INTO `process` (`name`,`user`,`memory`,`id`,`processer`) VALUES 
 ('video.exe','C1','98',1,'p1'),
 ('calc.exe','C2','45',1,'p2');
/*!40000 ALTER TABLE `process` ENABLE KEYS */;


--
-- Definition of table `usagememory`
--

DROP TABLE IF EXISTS `usagememory`;
CREATE TABLE `usagememory` (
  `pname` text NOT NULL,
  `totalspace` decimal(10,0) NOT NULL,
  `freespace` decimal(10,0) NOT NULL,
  `occupyspace` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usagememory`
--

/*!40000 ALTER TABLE `usagememory` DISABLE KEYS */;
INSERT INTO `usagememory` (`pname`,`totalspace`,`freespace`,`occupyspace`) VALUES 
 ('core1','105','7','98'),
 ('core2','170','125','45'),
 ('core3','130','130','0');
/*!40000 ALTER TABLE `usagememory` ENABLE KEYS */;


--
-- Definition of table `waiting`
--

DROP TABLE IF EXISTS `waiting`;
CREATE TABLE `waiting` (
  `process` text NOT NULL,
  `prtid` text NOT NULL,
  `status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `waiting`
--

/*!40000 ALTER TABLE `waiting` DISABLE KEYS */;
/*!40000 ALTER TABLE `waiting` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
