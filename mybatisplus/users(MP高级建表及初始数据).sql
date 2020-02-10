/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2020-02-08 22:57:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `deleted` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1226155184401543169', 'qianlong', '45', 'qianlong@163.com', null, '2020-02-08 22:45:51', '2020-02-08 22:45:51', '0', '0');
INSERT INTO `users` VALUES ('1226155974591565826', '经理', '45', '经理@163.com', '1226155184401543169', '2020-02-08 22:48:59', null, '0', '0');
INSERT INTO `users` VALUES ('1226157008965664769', '小张', '23', '小张@163.com', '1226155974591565826', '2020-02-08 22:53:05', null, '0', '0');
INSERT INTO `users` VALUES ('1226157009909383170', '小王', '28', '小王@163.com', '1226155974591565826', '2020-02-08 22:53:05', null, '0', '0');
INSERT INTO `users` VALUES ('1226157009917771777', '小李', '33', '小李@163.com', '1226155974591565826', '2020-02-08 22:53:05', null, '0', '0');
