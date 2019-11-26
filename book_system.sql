/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : book_system

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 25/04/2019 22:58:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `BookID` int(11) NOT NULL AUTO_INCREMENT COMMENT '书籍id',
  `BookName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书籍名称',
  `State` char(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书籍状态',
  `BorrowedCount` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '书籍被借次数',
  `BorrowedDate` char(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书籍借出日期',
  PRIMARY KEY (`BookID`,`BookName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;