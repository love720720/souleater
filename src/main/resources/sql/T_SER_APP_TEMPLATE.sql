/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50133
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50133
File Encoding         : 65001

Date: 2017-06-16 14:15:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for T_SER_APP_TEMPLATE
-- ----------------------------
DROP TABLE IF EXISTS `T_SER_APP_TEMPLATE`;
CREATE TABLE `T_SER_APP_TEMPLATE` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `TEMPLATE_KEY` varchar(40) NOT NULL,
  `TEMPLATE_NAME` varchar(40) NOT NULL,
  `TEMPLATE_ID` varchar(60) DEFAULT NULL,
  `TITLE` varchar(60) DEFAULT NULL,
  `DESCRIPTION` varchar(60) DEFAULT NULL,
  `PIC_URL` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `LAST_MODIF_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_SER_APP_TEMPLATE
-- ----------------------------
INSERT INTO `T_SER_APP_TEMPLATE` VALUES ('1', 'APP_TEMPLATE_ATTENTION', '微信关注消息', '', '关注消息', '感谢您对本公众号的关注!', 'http://gaowei.nat100.top/souleater/images/app/share.jpg', 'http://gaowei.nat100.top/souleater/app/index', '2017-06-16 10:00:13', '2017-06-16 10:00:13');
INSERT INTO `T_SER_APP_TEMPLATE` VALUES ('2', 'APP_TEMPLATE_START', '微信模板消息', 'CGtgJsY2e8-JEKvP3kV9uYuDQkRyPdSyVOiH_AyPsNg', null, null, null, 'http://gaowei.nat100.top/souleater/app/index', '2017-06-16 10:25:12', '2017-06-16 10:25:12');
