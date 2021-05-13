/*
Navicat MySQL Data Transfer

Source Server         : 192.168.56.222
Source Server Version : 50724
Source Host           : 192.168.56.222:3306
Source Database       : sharding-db

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2021-05-11 14:33:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jing_user`
-- ----------------------------
DROP TABLE IF EXISTS `jing_user`;
CREATE TABLE `jing_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `birthday` varchar(20) DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jing_user
-- ----------------------------
