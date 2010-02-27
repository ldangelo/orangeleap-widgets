--
-- Create schema donatenow
--

CREATE DATABASE IF NOT EXISTS DONATENOW;
USE DONATENOW;

DROP TABLE IF EXISTS PLACEMENTS;
CREATE TABLE  `DONATENOW`.`PLACEMENTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `widgetid` bigint(20) NOT NULL,
  `placementURL` varchar(2048) NOT NULL,
  `errorCount` bigint(20) NOT NULL,
  `giftCount` bigint(20) NOT NULL,
  `viewCount` bigint(20) NOT NULL DEFAULT '0',
  `site` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS WIDGETS;
CREATE TABLE  `DONATENOW`.`WIDGETS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `guid` varchar(128) NOT NULL,
  `userName` varchar(64) NOT NULL,
  `passWord` varchar(64) NOT NULL,
  `projectCode` varchar(64) NOT NULL,
  `motivationCode` varchar(64) NOT NULL,
  `errorCount` bigint(20) NOT NULL,
  `giftCount` bigint(20) NOT NULL,
  `createDate` datetime NOT NULL,
  `widgetHTML` varchar(4096) NOT NULL,
  `viewCount` bigint(20) NOT NULL DEFAULT '0',
  `site` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
