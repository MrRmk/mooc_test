/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-02-21 16:49:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `yx_system_store`
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_store`;
CREATE TABLE `yx_system_store` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '门店名称',
  `introduction` varchar(1000) NOT NULL DEFAULT '' COMMENT '简介',
  `phone` char(25) NOT NULL DEFAULT '' COMMENT '手机号码',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '省市区',
  `detailed_address` varchar(255) NOT NULL DEFAULT '' COMMENT '详细地址',
  `image` varchar(255) NOT NULL DEFAULT '' COMMENT '门店logo',
  `latitude` char(25) DEFAULT '' COMMENT '纬度',
  `longitude` char(25) DEFAULT '' COMMENT '经度',
  `valid_time` varchar(100) DEFAULT '' COMMENT '核销有效日期',
  `day_time` varchar(100) NOT NULL DEFAULT '' COMMENT '每日营业开关时间',
  `add_time` int(11) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `is_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否显示',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `status` tinyint(10) NOT NULL DEFAULT '0' COMMENT '店铺状态：默认为0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `phone` (`phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='门店自提';

-- ----------------------------
-- Records of yx_system_store
-- ----------------------------
