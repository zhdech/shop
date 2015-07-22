-- MySQL dump 10.13  Distrib 5.1.54, for Win32 (ia32)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	5.1.54-community

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
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` decimal(20,0) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('Q_BASE','245',1);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_goods`
--

DROP TABLE IF EXISTS `t_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goods` (
  `goodsid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL COMMENT '品牌',
  `model` varchar(45) NOT NULL COMMENT '型号',
  `color` varchar(45) NOT NULL COMMENT '颜色',
  `pirce` int(10) unsigned NOT NULL COMMENT '价格',
  `stock` varchar(45) NOT NULL COMMENT '库存，如果为0，则表示缺货',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`goodsid`)
) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods`
--

LOCK TABLES `t_goods` WRITE;
/*!40000 ALTER TABLE `t_goods` DISABLE KEYS */;
INSERT INTO `t_goods` VALUES (1,'小米','红米','红色',1000,'0',''),(3,'小米','小米3','灰色',1000,'100',''),(4,'小米','红米1S','灰色',1000,'100',''),(5,'华为','畅玩','灰色',1000,'100',''),(7,'联想','11','33',2000,'10','联想手机'),(8,'联想','11','33',1500,'23',''),(9,'联想','11','33',1212,'2323','这是中国，我是中国人，这里如何。我就要看看信息是否对。正常    苦'),(10,'联想','11','33',1212,'2323','1212'),(11,'联想','11','33',1212,'2323','1212'),(14,'联想','11','33',500,'12','甘苦村'),(15,'联想','11','33',1212,'2323','1212'),(16,'联想','11','33',1212,'2323','1212'),(17,'联想','11','33',1212,'2323','1212'),(18,'联想','11','33',1212,'2323','1212'),(19,'联想','11','33',1212,'2323','1212'),(20,'联想','11','33',1212,'2323','1212'),(21,'联想','11','33',1212,'2323','1212'),(22,'联想','11','33',1212,'2323','1212'),(23,'联想','11','33',1212,'2323','1212'),(24,'联想','11','33',1212,'2323','1212'),(25,'联想','11','33',1212,'2323','1212'),(26,'联想','11','33',1212,'2323','1212'),(27,'联想','11','33',1212,'2323','1212'),(28,'联想','11','33',1212,'2323','1212'),(29,'联想','11','33',1212,'2323','1212'),(30,'联想','11','33',1212,'2323','1212'),(31,'联想','11','33',1212,'2323','1212'),(32,'联想','11','33',1212,'2323','1212'),(33,'联想','11','33',1212,'2323','1212'),(34,'联想','11','33',1212,'2323','1212'),(35,'联想','11','33',1212,'2323','1212'),(36,'联想','11','33',1212,'2323','1212'),(37,'联想','11','33',1212,'2323','1212'),(38,'联想','11','33',1212,'2323','1212'),(39,'联想','11','33',1212,'2323','1212'),(40,'联想','11','33',1212,'2323','1212'),(41,'联想','11','33',1212,'2323','1212'),(42,'联想','11','33',1212,'2323','1212'),(43,'联想','11','33',1212,'2323','1212'),(44,'联想','11','33',1212,'2323','1212'),(45,'联想','11','33',1212,'2323','1212'),(46,'联想','11','33',1212,'2323','1212'),(47,'联想','11','33',1212,'2323','1212'),(48,'联想','11','33',1212,'2323','1212'),(49,'联想','11','33',1212,'2323','1212'),(50,'联想','11','33',1212,'2323','1212'),(51,'联想','11','33',1212,'2323','1212'),(52,'联想','11','33',1212,'2323','1212'),(53,'联想','11','33',1212,'2323','1212'),(54,'联想','11','33',1212,'2323','1212'),(55,'联想','11','33',1212,'2323','1212'),(56,'联想','11','33',1212,'2323','1212'),(57,'联想','11','33',1212,'2323','1212'),(58,'联想','11','33',1212,'2323','1212'),(59,'联想','11','33',1212,'2323','1212'),(60,'联想','11','33',1212,'2323','1212'),(61,'联想','11','33',1212,'2323','1212'),(62,'联想','11','33',1212,'2323','1212'),(63,'联想','11','33',1212,'2323','1212'),(64,'联想','11','33',1212,'2323','1212'),(65,'联想','11','33',1212,'2323','1212'),(66,'联想','11','33',1212,'2323','1212'),(67,'联想','11','33',1212,'2323','1212'),(68,'联想','11','33',1212,'2323','1212'),(69,'联想','11','33',1212,'2323','1212'),(70,'联想','11','33',1212,'2323','1212'),(71,'联想','11','33',1212,'2323','1212'),(72,'联想','11','33',1212,'2323','1212'),(73,'联想','11','33',1212,'2323','1212'),(74,'联想','11','33',1212,'2323','1212'),(75,'联想','11','33',1212,'2323','1212'),(76,'联想','11','33',1212,'2323','1212'),(77,'联想','11','33',1212,'2323','1212'),(78,'联想','11','33',1212,'2323','1212'),(79,'联想','11','33',1212,'2323','1212'),(80,'联想','11','33',1212,'2323','1212'),(81,'联想','11','33',1212,'2323','1212'),(82,'联想','11','33',1212,'2323','1212'),(83,'联想','11','33',1212,'2323','1212'),(84,'联想','11','33',1212,'2323','1212'),(85,'联想','11','33',1212,'2323','1212'),(86,'联想','11','33',1212,'2323','1212'),(87,'联想','11','33',1212,'2323','1212'),(88,'联想','11','33',1212,'2323','1212'),(89,'联想','11','33',1212,'2323','1212'),(90,'联想','11','33',1212,'2323','1212'),(91,'联想','11','33',1212,'2323','1212'),(92,'联想','11','33',1212,'2323','1212'),(93,'联想','11','33',1212,'2323','1212'),(94,'联想','11','33',1212,'2323','1212'),(95,'联想','11','33',1212,'2323','1212'),(96,'联想','11','33',1212,'2323','1212'),(97,'联想','11','33',1212,'2323','1212'),(98,'联想','11','33',1212,'2323','1212'),(99,'联想','11','33',1212,'2323','1212'),(100,'联想','11','33',1212,'2323','1212'),(101,'联想','11','33',1212,'2323','1212'),(102,'联想','11','33',1212,'2323','1212'),(103,'联想','11','33',1212,'2323','1212'),(104,'联想','11','33',1212,'2323','1212'),(105,'联想','11','33',1212,'2323','1212'),(106,'联想','11','33',1212,'2323','1212'),(107,'联想','11','33',1212,'2323','1212'),(108,'联想','11','33',1212,'2323','1212'),(109,'联想','11','33',1212,'2323','1212'),(110,'联想','11','33',1212,'2323','1212'),(111,'联想','11','33',1212,'2323','1212'),(112,'联想','11','33',1212,'2323','1212'),(113,'联想','11','33',1212,'2323','1212'),(114,'联想','11','33',1212,'2323','1212'),(115,'联想','11','33',1212,'2323','1212'),(116,'联想','11','33',1212,'2323','1212'),(117,'联想','11','33',1212,'2323','1212'),(118,'联想','11','33',1212,'2323','1212'),(119,'联想','11','33',1212,'2323','1212'),(120,'联想','11','33',1212,'2323','1212'),(121,'联想','11','33',1212,'2323','1212'),(122,'联想','11','33',1212,'2323','1212'),(123,'联想','11','33',1212,'2323','1212'),(124,'联想','11','33',1212,'2323','1212'),(125,'联想','11','33',1212,'2323','1212'),(126,'联想','11','33',1212,'2323','1212'),(127,'联想','11','33',1212,'2323','1212'),(128,'联想','11','33',1212,'2323','1212'),(129,'联想','11','33',1212,'2323','1212'),(130,'联想','11','33',1212,'2323','1212'),(131,'联想','11','33',1212,'2323','1212'),(132,'联想','11','33',1212,'2323','1212'),(133,'联想','11','33',1212,'2323','1212'),(134,'联想','11','33',1212,'2323','1212'),(135,'联想','11','33',1212,'2323','1212'),(136,'联想','11','33',1212,'2323','1212'),(137,'联想','11','33',1212,'2323','1212'),(138,'联想','11','33',1212,'2323','1212'),(139,'联想','11','33',1212,'2323','1212'),(140,'联想','11','33',1212,'2323','1212'),(141,'联想','11','33',1212,'2323','1212'),(142,'联想','11','33',1212,'2323','1212'),(143,'联想','11','33',1212,'2323','1212'),(144,'联想','11','33',1212,'2323','1212'),(145,'联想','11','33',1212,'2323','1212'),(146,'联想','11','33',1212,'2323','1212'),(147,'联想','11','33',1212,'2323','1212'),(148,'联想','11','33',1212,'2323','1212'),(149,'联想','11','33',1212,'2323','1212'),(150,'联想','11','33',1212,'2323','1212'),(151,'联想','11','33',1212,'2323','1212'),(152,'联想','11','33',1212,'2323','1212'),(153,'联想','11','33',1212,'2323','1212'),(154,'联想','11','33',1212,'2323','1212'),(155,'联想','11','33',1212,'2323','1212'),(156,'联想','11','33',1212,'2323','1212'),(157,'联想','11','33',1212,'2323','1212'),(158,'联想','11','33',1212,'2323','1212'),(159,'联想','11','33',1212,'2323','1212'),(160,'联想','11','33',1212,'2323','1212'),(161,'联想','11','33',1212,'2323','1212'),(162,'联想','11','33',1212,'2323','1212'),(163,'联想','11','33',1212,'2323','1212'),(164,'联想','11','33',1212,'2323','1212'),(165,'联想','11','33',1212,'2323','1212'),(166,'联想','11','33',1212,'2323','1212'),(167,'联想','11','33',1212,'2323','1212'),(168,'联想','11','33',1212,'2323','1212'),(169,'联想','11','33',1212,'2323','1212'),(170,'联想','11','33',1212,'2323','1212'),(171,'联想','11','33',1212,'2323','1212'),(172,'联想','11','33',1212,'2323','1212'),(173,'联想','11','33',1212,'2323','1212'),(174,'联想','11','33',1212,'2323','1212'),(175,'联想','11','33',1212,'2323','1212'),(176,'联想','11','33',1212,'2323','1212'),(177,'联想','11','33',1212,'2323','1212'),(178,'联想','11','33',1212,'2323','1212'),(179,'联想','11','33',1212,'2323','1212'),(180,'联想','11','33',1212,'2323','1212'),(181,'联想','11','33',1212,'2323','1212'),(182,'联想','11','33',1212,'2323','1212'),(183,'联想','11','33',1212,'2323','1212'),(184,'联想','11','33',1212,'2323','1212'),(185,'联想','11','33',1212,'2323','1212'),(186,'联想','11','33',1212,'2323','1212'),(187,'联想','11','33',1212,'2323','1212'),(188,'联想','11','33',1212,'2323','1212'),(189,'联想','11','33',1212,'2323','1212'),(190,'联想','11','33',1212,'2323','1212'),(191,'联想','11','33',1212,'2323','1212'),(192,'联想','11','33',1212,'2323','1212'),(193,'联想','11','33',1212,'2323','1212'),(194,'联想','11','33',1212,'2323','1212'),(195,'联想','11','33',1212,'2323','1212'),(196,'联想','11','33',1212,'2323','1212'),(197,'联想','11','33',1212,'2323','1212'),(198,'联想','11','33',1212,'2323','1212'),(199,'联想','11','33',1212,'2323','1212'),(200,'联想','11','33',1212,'2323','1212'),(201,'联想','11','33',1212,'2323','1212'),(202,'联想','11','33',1212,'2323','1212'),(203,'联想','11','33',1212,'2323','1212'),(204,'联想','11','33',1212,'2323','1212'),(205,'联想','11','33',1212,'2323','1212'),(206,'联想','11','33',1212,'2323','1212'),(207,'联想','11','33',1212,'2323','1212'),(208,'联想','11','33',1212,'2323','1212'),(209,'联想','11','33',1212,'2323','1212'),(210,'联想','11','33',1212,'2323','1212'),(211,'联想','11','33',1212,'2323','1212'),(212,'联想','11','33',1212,'2323','1212'),(213,'联想','11','33',1212,'2323','1212'),(214,'联想','11','33',1212,'2323','1212'),(215,'联想','11','33',1212,'2323','1212'),(216,'联想','11','33',1212,'2323','1212'),(217,'联想','11','33',1212,'2323','1212'),(218,'联想','11','33',1212,'2323','1212'),(219,'联想','11','33',1212,'2323','1212'),(220,'联想','11','33',1212,'2323','1212'),(221,'联想','11','33',1212,'2323','1212'),(222,'联想','11','33',1212,'2323','1212'),(223,'联想','11','33',1212,'2323','1212'),(224,'联想','11','33',1212,'2323','1212'),(225,'联想','11','33',1212,'2323','1212'),(226,'联想','11','33',1212,'2323','1212'),(227,'联想','11','33',1212,'2323','1212'),(228,'联想','11','33',1212,'2323','1212'),(229,'联想','11','33',1212,'2323','1212'),(230,'联想','11','33',1212,'2323','1212'),(231,'联想','11','33',1212,'2323','1212'),(232,'联想','11','33',1212,'2323','1212'),(233,'联想','11','33',1212,'2323','1212'),(234,'联想','11','33',1212,'2323','1212'),(235,'联想','11','33',1212,'2323','1212'),(236,'联想','11','33',1212,'2323','1212'),(237,'联想','11','33',1212,'2323','1212'),(238,'联想','11','33',1212,'2323','1212'),(239,'联想','11','33',1212,'2323','1212'),(240,'联想','11','33',1212,'2323','1212'),(241,'联想','11','33',1212,'2323','1212'),(242,'联想','11','33',1212,'2323','1212'),(243,'联想','11','33',1212,'2323','1212'),(244,'联想','11','33',1212,'2323','1212'),(245,'联想','11','33',1212,'2323','1212'),(246,'联想','11','33',1212,'2323','1212'),(247,'联想','11','33',1212,'2323','1212'),(248,'联想','11','33',1212,'2323','1212'),(249,'联想','11','33',1212,'2323','1212'),(250,'联想','11','33',1212,'2323','1212'),(251,'联想','11','33',1212,'2323','1212'),(252,'联想','11','33',1212,'2323','1212'),(253,'联想','11','33',1212,'2323','1212'),(254,'联想','11','33',1212,'2323','1212'),(255,'联想','11','33',1212,'2323','1212'),(256,'联想','11','33',1212,'2323','1212'),(257,'联想','11','33',1212,'2323','1212'),(258,'联想','11','33',1212,'2323','1212'),(259,'联想','11','33',1212,'2323','1212'),(260,'联想','11','33',1212,'2323','1212'),(261,'联想','11','33',1212,'2323','1212'),(262,'联想','11','33',1212,'2323','1212'),(263,'联想','11','33',1212,'2323','1212'),(264,'联想','11','33',1212,'2323','1212'),(265,'联想','11','33',1212,'2323','1212'),(266,'联想','11','33',1212,'2323','1212'),(267,'联想','11','33',1212,'2323','1212'),(268,'联想','11','33',1212,'2323','1212'),(269,'联想','11','33',1212,'2323','1212'),(270,'联想','11','33',1212,'2323','1212'),(271,'联想','11','33',1212,'2323','1212'),(272,'联想','11','33',1212,'2323','1212'),(273,'联想','11','33',1212,'2323','1212'),(274,'联想','11','33',1212,'2323','1212'),(275,'联想','11','33',1212,'2323','1212'),(276,'联想','11','33',1212,'2323','1212'),(277,'联想','11','33',1212,'2323','1212'),(278,'联想','11','33',1212,'2323','1212'),(279,'联想','11','33',1212,'2323','1212'),(280,'联想','11','33',1212,'2323','1212'),(281,'联想','11','33',1212,'2323','1212'),(282,'联想','11','33',1212,'2323','1212'),(283,'联想','11','33',1212,'2323','1212'),(284,'联想','11','33',1212,'2323','1212'),(285,'联想','11','33',1212,'2323','1212'),(286,'联想','11','33',1212,'2323','1212'),(287,'联想','11','33',1212,'2323','1212'),(288,'联想','11','33',1212,'2323','1212'),(289,'联想','11','33',1212,'2323','1212'),(290,'联想','11','33',1212,'2323','1212'),(291,'联想','11','33',1212,'2323','1212'),(292,'联想','11','33',1212,'2323','1212'),(293,'联想','11','33',1212,'2323','1212'),(294,'联想','11','33',1212,'2323','1212'),(295,'联想','11','33',1212,'2323','1212'),(296,'联想','11','33',1212,'2323','1212'),(297,'联想','11','33',1212,'2323','1212'),(298,'联想','11','33',1212,'2323','1212'),(299,'联想','11','33',1212,'2323','1212'),(300,'联想','11','33',1212,'2323','1212'),(301,'联想','11','33',1212,'2323','1212'),(302,'联想','11','33',1212,'2323','1212'),(303,'联想','11','33',1212,'2323','1212'),(304,'联想','11','33',1212,'2323','1212'),(305,'联想','11','33',1212,'2323','1212'),(306,'联想','11','33',1212,'2323','1212'),(307,'联想','11','33',1212,'2323','1212'),(308,'联想','11','33',1212,'2323','1212'),(309,'联想','11','33',1212,'2323','1212'),(310,'联想','11','33',1212,'2323','1212'),(311,'联想','11','33',1212,'2323','1212'),(312,'联想','11','33',1212,'2323','1212'),(313,'联想','11','33',1212,'2323','1212'),(314,'联想','11','33',1212,'2323','1212'),(315,'联想','11','33',1212,'2323','1212'),(316,'联想','11','33',1212,'2323','1212'),(317,'联想','11','33',1212,'2323','1212'),(318,'联想','11','33',1212,'2323','1212'),(319,'联想','11','33',1212,'2323','1212'),(320,'联想','11','33',1212,'2323','1212'),(321,'联想','11','33',1212,'2323','1212'),(322,'联想','11','33',1212,'2323','1212'),(323,'联想','11','33',1212,'2323','1212'),(324,'联想','11','33',1212,'2323','1212'),(325,'联想','11','33',1212,'2323','1212'),(326,'联想','11','33',1212,'2323','1212'),(327,'联想','11','33',1212,'2323','1212'),(328,'联想','11','33',1212,'2323','1212'),(329,'联想','11','33',1212,'2323','1212'),(330,'联想','11','33',1212,'2323','1212'),(331,'联想','11','33',1212,'2323','1212'),(332,'联想','11','33',1212,'2323','1212'),(333,'联想','11','33',1212,'2323','1212'),(334,'联想','11','33',1212,'2323','1212'),(335,'联想','11','33',1212,'2323','1212'),(336,'联想','11','33',1212,'2323','1212'),(337,'联想','11','33',1212,'2323','1212'),(338,'联想','11','33',1212,'2323','1212'),(339,'联想','11','33',1212,'2323','1212'),(340,'联想','11','33',1212,'2323','1212'),(341,'联想','11','33',1212,'2323','1212'),(342,'联想','11','33',1212,'2323','1212'),(343,'联想','11','33',1212,'2323','1212'),(344,'联想','11','33',1212,'2323','1212'),(345,'联想','11','33',1212,'2323','1212'),(346,'联想','11','33',1212,'2323','1212'),(347,'联想','11','33',1212,'2323','1212'),(348,'联想','11','33',1212,'2323','1212'),(349,'联想','11','33',1212,'2323','1212'),(350,'联想','11','33',1212,'2323','1212'),(351,'联想','11','33',1212,'2323','1212'),(352,'联想','11','33',1212,'2323','1212'),(353,'联想','11','33',1212,'2323','1212'),(354,'联想','11','33',1212,'2323','1212'),(355,'联想','11','33',1212,'2323','1212'),(356,'联想','11','33',1212,'2323','1212'),(357,'联想','11','33',1212,'2323','1212'),(358,'联想','11','33',1212,'2323','1212'),(359,'联想','11','33',1212,'2323','1212'),(360,'联想','11','33',1212,'2323','1212'),(361,'联想','11','33',1212,'2323','1212'),(362,'联想','11','33',1212,'2323','1212'),(363,'联想','11','33',1212,'2323','1212'),(364,'联想','11','33',1212,'2323','1212'),(365,'联想','11','33',1212,'2323','1212'),(366,'联想','11','33',1212,'2323','1212'),(367,'联想','11','33',1212,'2323','1212'),(368,'联想','11','33',1212,'2323','1212'),(369,'联想','11','33',1212,'2323','1212'),(370,'联想','11','33',1212,'2323','1212'),(371,'联想','11','33',1212,'2323','1212'),(372,'联想','11','33',1212,'2323','1212'),(373,'联想','11','33',1212,'2323','1212'),(374,'联想','11','33',1212,'2323','1212'),(375,'联想','11','33',1212,'2323','1212'),(376,'联想','11','33',1212,'2323','1212'),(377,'华为','畅玩版','白色',1000,'10','劳而无功d');
/*!40000 ALTER TABLE `t_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_orderinfo`
--

DROP TABLE IF EXISTS `t_orderinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_orderinfo` (
  `orderid` int(10) unsigned NOT NULL COMMENT '订单ID',
  `goodsid` int(10) unsigned NOT NULL COMMENT '商品ID',
  `amount` int(10) unsigned NOT NULL COMMENT '金额(元)',
  `number` int(10) unsigned NOT NULL COMMENT '数量',
  `loginname` varchar(45) NOT NULL COMMENT '登录人',
  `id` int(10) unsigned zerofill NOT NULL COMMENT '主键',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `status` varchar(45) NOT NULL DEFAULT '0' COMMENT '状态0未下单，1已下单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_orderinfo`
--

LOCK TABLES `t_orderinfo` WRITE;
/*!40000 ALTER TABLE `t_orderinfo` DISABLE KEYS */;
INSERT INTO `t_orderinfo` VALUES (224,3,1000,4,'admin',0000000225,'2014-09-07 06:44:11','1'),(224,5,1000,1,'admin',0000000226,'2014-09-07 06:44:11','1'),(224,4,1000,1,'admin',0000000228,'2014-09-07 06:44:11','1'),(229,3,1000,1,'admin',0000000230,'2014-09-07 07:49:54','1'),(229,4,1000,2,'admin',0000000231,'2014-09-07 07:49:54','1'),(229,5,1000,3,'admin',0000000232,'2014-09-07 07:49:54','1'),(229,7,2000,4,'admin',0000000233,'2014-09-07 07:49:54','1'),(234,3,1000,2,'dechong',0000000235,'2014-09-07 07:59:15','1'),(234,4,1000,2,'dechong',0000000236,'2014-09-07 07:59:15','1'),(234,5,1000,2,'dechong',0000000237,'2014-09-07 07:59:15','1'),(238,3,1000,1,'dechong',0000000239,'2014-09-07 08:07:21','1'),(238,5,1000,1,'dechong',0000000240,'2014-09-07 08:07:21','1'),(243,3,1000,1,'dechong',0000000244,'2014-09-07 12:28:02','1'),(243,4,1000,1,'dechong',0000000245,'2014-09-07 12:28:02','1');
/*!40000 ALTER TABLE `t_orderinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_orderlist`
--

DROP TABLE IF EXISTS `t_orderlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_orderlist` (
  `orderid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ordername` varchar(45) NOT NULL,
  `createname` varchar(45) NOT NULL,
  `createmobile` varchar(45) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `orderstatus` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '0未发货，1已发货，9订单无效',
  `totalnum` int(10) unsigned NOT NULL COMMENT '总数量',
  `totalamount` int(10) unsigned NOT NULL COMMENT '总金额',
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_orderlist`
--

LOCK TABLES `t_orderlist` WRITE;
/*!40000 ALTER TABLE `t_orderlist` DISABLE KEYS */;
INSERT INTO `t_orderlist` VALUES (224,'admin20140907','admin','13833392156','2014-09-07 06:44:11',0,6,6000),(229,'admin20140907','admin','13833392156','2014-09-07 07:49:53',1,10,14000),(234,'dechong20140907','dechong','13833392156','2014-09-07 07:59:14',1,6,6000),(238,'dechong20140907','dechong','13833392156','2014-09-07 08:07:21',1,2,2000),(243,'dechong20140907','dechong','13833392156','2014-09-07 12:28:02',0,2,2000);
/*!40000 ALTER TABLE `t_orderlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `status` int(10) unsigned NOT NULL DEFAULT '0',
  `manager` varchar(45) NOT NULL DEFAULT '0' COMMENT '是否为管理员',
  `address` varchar(3000) NOT NULL COMMENT '发货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','4fecb0862a17eeb7e40b370980e1028d','13833392156',' 张德崇','276979375',0,'1','河北石家庄市33号'),(2,'dechong','143f08b08d1e63e5684cb98a3a109f56','13833392156',' 张德崇','276979375',0,'0','石家庄市裕华区300号'),(3,'pss','pss','13833392155','pss',NULL,9,'0','sadfasdfadfadfasdf'),(4,'pss','pss','15081810253','彭莎莎',NULL,0,'0','河北');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-07 20:30:59
