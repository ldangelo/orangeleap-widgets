CREATE TABLE JAVASCRIPT (
  JAVASCRIPT_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  JAVASCRIPT LONGTEXT NOT NULL,
  JAVASCRIPT_NAME VARCHAR(128) NOT NULL,
  INACTIVE CHAR(1) DEFAULT '0',
  DELETED CHAR(1) DEFAULT '0',
  USER_NAME VARCHAR(128) NOT NULL,
  SITE_NAME VARCHAR(255),
  PRIMARY KEY (JAVASCRIPT_ID),
  KEY IDX_USER_NAME (USER_NAME),
  KEY IDX_SITE_NAME (SITE_NAME)
) ENGINE = INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=LATIN1;