/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : performance

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-09-29 14:45:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `code`
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `codeId` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  `sequence` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`codeId`)
) ENGINE=InnoDB AUTO_INCREMENT=12977 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('1', 'team', '1', 'accounts', '1');
INSERT INTO `code` VALUES ('2', 'team', '2', 'activity', '2');
INSERT INTO `code` VALUES ('3', 'team', '3', 'api', '3');
INSERT INTO `code` VALUES ('4', 'team', '4', 'integration', '4');
INSERT INTO `code` VALUES ('5', 'team', '5', 'portfolio', '5');
INSERT INTO `code` VALUES ('6', 'team', '6', 'react', '6');
INSERT INTO `code` VALUES ('7', 'team', '7', 'cogency', '7');
INSERT INTO `code` VALUES ('8', 'level', '2', '成员', '1');
INSERT INTO `code` VALUES ('9', 'level', '1', '组长', '2');
INSERT INTO `code` VALUES ('10', 'level', '0', '部门负责人', '3');
INSERT INTO `code` VALUES ('11', 'examStatus', '0', '开放', '0');
INSERT INTO `code` VALUES ('12', 'examStatus', '1', '关闭', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `team` varchar(50) DEFAULT NULL,
  `level` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `firstLog` tinyint(4) DEFAULT NULL,
  `createdTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modifiedTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('18', 'hisky', '123456', '7', '0', '0', '0', '2018-09-21 14:20:10', '2018-09-21 14:20:29');
INSERT INTO `user` VALUES ('19', 'abby', '123456', '7', '1', '1', '0', '2018-09-21 13:58:20', '2018-09-21 11:35:49');
INSERT INTO `user` VALUES ('32', 'ben', '123456', '7', '2', '1', '0', '2018-09-26 17:34:13', '2018-09-26 17:34:25');
