# shark
主要是自己创建的一个平台
/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : shark

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-06-06 13:42:03
*/
<br>
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_shark_user
-- ----------------------------
DROP TABLE IF EXISTS `t_shark_user`;
CREATE TABLE `t_shark_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `score` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=559 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shark_user
-- ----------------------------
INSERT INTO `t_shark_user` VALUES ('1', '1111', '31');
INSERT INTO `t_shark_user` VALUES ('2', '张书', '2');
INSERT INTO `t_shark_user` VALUES ('3', 'aa', null);
INSERT INTO `t_shark_user` VALUES ('4', 'vv', null);
INSERT INTO `t_shark_user` VALUES ('5', 'dd', null);
INSERT INTO `t_shark_user` VALUES ('6', 'cc', null);
INSERT INTO `t_shark_user` VALUES ('7', 'ee', null);
INSERT INTO `t_shark_user` VALUES ('8', 'gg', null);
INSERT INTO `t_shark_user` VALUES ('9', 'ee', null);
INSERT INTO `t_shark_user` VALUES ('11', 'hh', null);
INSERT INTO `t_shark_user` VALUES ('12', 'jj', null);
INSERT INTO `t_shark_user` VALUES ('23', '123123', null);
INSERT INTO `t_shark_user` VALUES ('88', 'ad', null);
INSERT INTO `t_shark_user` VALUES ('100', 'sda', null);
INSERT INTO `t_shark_user` VALUES ('123', 'sfasdf', null);
INSERT INTO `t_shark_user` VALUES ('213', 'fasdf', null);
INSERT INTO `t_shark_user` VALUES ('222', 'bb', null);
INSERT INTO `t_shark_user` VALUES ('555', '123', null);
INSERT INTO `t_shark_user` VALUES ('556', '张书21', 'eMB8nd4olkQkOr8Hm7t+nw==');
INSERT INTO `t_shark_user` VALUES ('557', '张书22', 'eMB8nd4olkQkOr8Hm7t+nw==');
INSERT INTO `t_shark_user` VALUES ('558', '张书2', 'eMB8nd4olkQkOr8Hm7t+nw==');
