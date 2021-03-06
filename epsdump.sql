-- MySQL dump 10.13  Distrib 5.1.72, for Win64 (unknown)
--
-- Host: localhost    Database: eps
-- ------------------------------------------------------
-- Server version	5.1.72-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appraisals`
--

DROP TABLE IF EXISTS `appraisals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appraisals` (
  `empid` varchar(20) DEFAULT NULL,
  `d` date DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  KEY `empid` (`empid`),
  CONSTRAINT `appraisals_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `emp_personal` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appraisals`
--

LOCK TABLES `appraisals` WRITE;
/*!40000 ALTER TABLE `appraisals` DISABLE KEYS */;
INSERT INTO `appraisals` VALUES ('emp1','2013-01-01',5000);
/*!40000 ALTER TABLE `appraisals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_personal`
--

DROP TABLE IF EXISTS `emp_personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp_personal` (
  `emp_name` varchar(20) DEFAULT NULL,
  `empid` varchar(10) NOT NULL DEFAULT '',
  `address` varchar(20) DEFAULT NULL,
  `marital_status` varchar(10) DEFAULT NULL,
  `bloodGroup` varchar(10) DEFAULT NULL,
  `phone` decimal(10,0) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_personal`
--

LOCK TABLES `emp_personal` WRITE;
/*!40000 ALTER TABLE `emp_personal` DISABLE KEYS */;
INSERT INTO `emp_personal` VALUES ('divya','EMP1','lbnagar','no','o+ve','783839839','1991-01-22');
/*!40000 ALTER TABLE `emp_personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_professional`
--

DROP TABLE IF EXISTS `emp_professional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp_professional` (
  `empid` varchar(10) DEFAULT NULL,
  `emp_name` varchar(20) DEFAULT NULL,
  `designation` varchar(20) DEFAULT NULL,
  `b_salary` decimal(6,0) DEFAULT NULL,
  `qualification` varchar(25) DEFAULT NULL,
  `department` varchar(10) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `expenses` decimal(5,0) DEFAULT NULL,
  KEY `empid` (`empid`),
  CONSTRAINT `emp_professional_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `emp_personal` (`empid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_professional`
--

LOCK TABLES `emp_professional` WRITE;
/*!40000 ALTER TABLE `emp_professional` DISABLE KEYS */;
INSERT INTO `emp_professional` VALUES ('EMP1','divya','mgr','50000','MS','Developing','Active','2000');
/*!40000 ALTER TABLE `emp_professional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_details` (
  `user_type` varchar(20) DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  UNIQUE KEY `password` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_details`
--

LOCK TABLES `login_details` WRITE;
/*!40000 ALTER TABLE `login_details` DISABLE KEYS */;
INSERT INTO `login_details` VALUES ('HR','HR01','HR01'),('PR','PR01','PR01'),('EMP','EMP1','c32518');
/*!40000 ALTER TABLE `login_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_slip`
--

DROP TABLE IF EXISTS `pay_slip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay_slip` (
  `empid` varchar(10) DEFAULT NULL,
  `emp_name` varchar(20) DEFAULT NULL,
  `designation` varchar(10) DEFAULT NULL,
  `b_salary` decimal(6,0) DEFAULT NULL,
  `total_salary` decimal(6,0) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `bonus` decimal(6,0) DEFAULT NULL,
  KEY `empid` (`empid`),
  CONSTRAINT `pay_slip_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `emp_personal` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_slip`
--

LOCK TABLES `pay_slip` WRITE;
/*!40000 ALTER TABLE `pay_slip` DISABLE KEYS */;
INSERT INTO `pay_slip` VALUES ('emp1','divya','mgr','50000','54500','2014-01-01','5000'),('EMP1','divya','mgr','50000','54500','2013-01-12','5000');
/*!40000 ALTER TABLE `pay_slip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary_details`
--

DROP TABLE IF EXISTS `salary_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary_details` (
  `empid` varchar(10) DEFAULT NULL,
  `b_salary` decimal(6,0) DEFAULT NULL,
  `TA` decimal(6,0) DEFAULT NULL,
  `DA` decimal(6,0) DEFAULT NULL,
  `HRA` decimal(6,0) DEFAULT NULL,
  `health_ins` decimal(6,0) DEFAULT NULL,
  `education_allowance` decimal(6,0) DEFAULT NULL,
  `bonus` decimal(6,0) DEFAULT NULL,
  `d` date DEFAULT NULL,
  KEY `empid` (`empid`),
  CONSTRAINT `salary_details_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `emp_personal` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary_details`
--

LOCK TABLES `salary_details` WRITE;
/*!40000 ALTER TABLE `salary_details` DISABLE KEYS */;
INSERT INTO `salary_details` VALUES ('emp1','50000','1000','1000','1000','2000','3000','5000','2013-01-01'),('EMP1','50000','1000','1000','200','1000','1000','0','2012-01-12'),('EMP1','50000','100','1000','1000','2000','1000','0','2012-01-12');
/*!40000 ALTER TABLE `salary_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-10 14:33:38
