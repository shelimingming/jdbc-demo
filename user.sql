/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-01-30 23:06:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'aaa', 'a', 'a');
INSERT INTO `user` VALUES ('2', 'bbb', 'b', 'b');
INSERT INTO `user` VALUES ('3', 'ccc', 'c', 'c');
