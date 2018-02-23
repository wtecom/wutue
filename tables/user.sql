
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Account` varchar(255) NOT NULL DEFAULT ' ',
  `Password` varchar(255) NOT NULL DEFAULT ' ',
  `Name` varchar(255) NOT NULL DEFAULT ' ',
  `Sex` int(10) NOT NULL,
  `Status` int(10) NOT NULL,
  `Type` int(10) NOT NULL,
  `BizCode` varchar(255) NOT NULL DEFAULT ' ',
  `CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CreateId` int(10) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_USER` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--