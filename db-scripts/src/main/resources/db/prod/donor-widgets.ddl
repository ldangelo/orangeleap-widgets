
DROP TABLE IF EXISTS `PLACEMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PLACEMENTS` (
  `PLACEMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `WIDGET_ID` bigint(20) NOT NULL,
  `PLACEMENT_URL` varchar(2048) NOT NULL,
  `ERROR_COUNT` bigint(20) NOT NULL,
  `VIEW_COUNT` bigint(20) NOT NULL DEFAULT '0',
  `SUBMIT_COUNT` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PLACEMENT_ID`),
  UNIQUE KEY `PLACEMENT_ID_UNIQUE` (`PLACEMENT_ID`),
  KEY `WIDGET_ID_IDX` (`WIDGET_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `STYLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STYLES` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `STYLE` longtext NOT NULL,
  `STYLE_NAME` varchar(128) NOT NULL,
  `USER_NAME` varchar(128) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IDX_USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `WIDGET` (
  `WIDGET_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `WIDGET_GUID` varchar(128) NOT NULL,
  `WIDGET_USERNAME` varchar(64) NOT NULL,
  `WIDGET_PASSWORD` varchar(64) NOT NULL,
  `WIDGET_ERROR_COUNT` bigint(20) NOT NULL DEFAULT '0',
  `WIDGET_CREATE_DATE` datetime NOT NULL,
  `WIDGET_HTML` varchar(4096) NOT NULL DEFAULT '"Dummy"',
  `WIDGET_VIEW_COUNT` bigint(20) NOT NULL DEFAULT '0',
  `WIDGET_TYPE` varchar(32) NOT NULL,
  `WIDGET_DESCRIPTION` varchar(255) DEFAULT NULL,
  `PROJECT_CODE` varchar(128) DEFAULT NULL,
  `MOTIVATION_CODE` varchar(128) DEFAULT NULL,
  `CUSTOM_ENTITY_NAME` varchar(128) DEFAULT NULL,
  `LOGIN_SUCCESS_URL` varchar(128) DEFAULT NULL,
  `LOGIN_FAILURE_URL` varchar(128) DEFAULT NULL,
  `AUTHENTICATION_REQUIRED` tinyint(1) DEFAULT '0',
  `AUTHENTICATION_URL` varchar(128) DEFAULT NULL,
  `SPONSORSHIP_URL` varchar(128) DEFAULT NULL,
  `STYLE_ID` bigint(20) DEFAULT NULL,
  `IFRAME_HTML` varchar(512) DEFAULT NULL,
  `DONATION_URL` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`WIDGET_ID`),
  UNIQUE KEY `WIDGET_GUID_UNIQUE` (`WIDGET_GUID`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


