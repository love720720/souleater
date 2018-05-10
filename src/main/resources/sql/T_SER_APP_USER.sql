/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50133
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50133
File Encoding         : 65001

Date: 2017-06-16 14:15:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for T_SER_APP_USER
-- ----------------------------
DROP TABLE IF EXISTS `T_SER_APP_USER`;
CREATE TABLE `T_SER_APP_USER` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `OPEN_ID` varchar(32) NOT NULL,
  `STATUS` int(1) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `LAST_MODIFY_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_SER_APP_USER
-- ----------------------------
INSERT INTO `T_SER_APP_USER` VALUES ('1', 'o-sQ5wgX51_fbwNv6s16Z45i3S04', '0', '2017-06-15 11:10:32', '2017-06-15 14:20:06');
INSERT INTO `T_SER_APP_USER` VALUES ('2', 'o-sQ5wpxvmuav9E9-asMPIUboHgo', '1', '2017-06-15 14:19:46', '2017-06-15 14:21:00');
