/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50133
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50133
File Encoding         : 65001

Date: 2017-06-16 15:06:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for T_SER_OPERATE_LOG
-- ----------------------------
DROP TABLE IF EXISTS `T_SER_OPERATE_LOG`;
CREATE TABLE `T_SER_OPERATE_LOG` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `UESR_ID` int(11) DEFAULT NULL,
  `OPEN_ID` varchar(32) DEFAULT NULL,
  `DESCRIPTION` varchar(60) NOT NULL,
  `IP` varchar(15) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `LAST_MODIFY_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_SER_OPERATE_LOG
-- ----------------------------
