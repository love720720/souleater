/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50133
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50133
File Encoding         : 65001

Date: 2017-06-16 14:15:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for T_SER_WEB_USER
-- ----------------------------
DROP TABLE IF EXISTS `T_SER_WEB_USER`;
CREATE TABLE `T_SER_WEB_USER` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(10) NOT NULL,
  `USER_NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `LAST_MODIFY_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`ID`) USING BTREE,
  KEY `USER_USER_NAME_PASSWORD` (`USER_NAME`,`PASSWORD`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_SER_WEB_USER
-- ----------------------------
INSERT INTO `T_SER_WEB_USER` VALUES ('1', '高巍', 'love720720', '$2a$10$Ft2DwElZ/61ah4FZ0Lau0.pQs3bibo04ryNMSF7cX0.eidne3AuIW', '2017-05-26 11:08:22', '2017-05-31 17:16:30');
