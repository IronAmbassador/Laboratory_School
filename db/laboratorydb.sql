/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : laboratorydb

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 17/06/2025 12:00:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lab_booking
-- ----------------------------
DROP TABLE IF EXISTS `lab_booking`;
CREATE TABLE `lab_booking`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '预约ID，主键，自增',
  `lab_id` int NOT NULL COMMENT '预约实验室ID',
  `user_id` int NOT NULL COMMENT '预约用户ID',
  `start_time` datetime NOT NULL COMMENT '预约开始时间',
  `end_time` datetime NOT NULL COMMENT '预约结束时间',
  `booking_status` int NOT NULL COMMENT '预约状态：1-待审核，2-已通过，3-已拒绝，4-已取消',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预约备注',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '删除标志：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室预约表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_booking
-- ----------------------------
INSERT INTO `lab_booking` VALUES (1, 1, 4, '2023-05-01 09:00:00', '2023-05-01 12:00:00', 2, '进行图像识别实验', 'student1', '2023-04-25 10:30:00', 'teacher', '2025-06-11 12:56:03', 0);
INSERT INTO `lab_booking` VALUES (2, 2, 5, '2023-05-02 13:00:00', '2023-05-02 16:00:00', 2, '材料力学实验', 'student2', '2023-04-26 09:45:00', 'student2', '2023-04-26 09:45:00', 0);
INSERT INTO `lab_booking` VALUES (3, 1, 2, '2023-05-05 08:30:00', '2023-05-05 11:30:00', 2, '课程实验指导', 'teacher1', '2023-04-27 14:20:00', 'mushuo', '2025-06-11 12:31:02', 0);

-- ----------------------------
-- Table structure for lab_course
-- ----------------------------
DROP TABLE IF EXISTS `lab_course`;
CREATE TABLE `lab_course`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名称',
  `teacher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任课教师',
  `up_time` datetime NOT NULL COMMENT '上课时间',
  `down_time` datetime NOT NULL COMMENT '下课时间',
  `lab_id` int NOT NULL COMMENT '实验室ID',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_course
-- ----------------------------
INSERT INTO `lab_course` VALUES (1, '机器学习', '杨洋', '2025-06-02 00:00:00', '2025-06-04 00:00:00', 1, 'admin', '2025-06-11 12:43:04', 'admin', '2025-06-11 12:43:04', 0);

-- ----------------------------
-- Table structure for lab_device
-- ----------------------------
DROP TABLE IF EXISTS `lab_device`;
CREATE TABLE `lab_device`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `device_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备名称',
  `device_date` datetime NOT NULL COMMENT '购买时间',
  `device_price` int NOT NULL COMMENT '设备价格',
  `guarantee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '保修时间',
  `device_factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '生产厂商',
  `device_status` int NOT NULL COMMENT '设备状态',
  `lab_id` int NOT NULL COMMENT '实验室ID',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_device
-- ----------------------------
INSERT INTO `lab_device` VALUES (1, 'Linux系统', '2025-06-03 00:00:00', 20000, '二年', '重庆赛里斯', 0, 1, 'admin', '2025-06-11 12:45:52', 'admin', '2025-06-11 12:45:52', 0);
INSERT INTO `lab_device` VALUES (2, '昇腾', '2025-06-04 00:00:00', 500000, '三年', '北京京东方', 0, 1, 'admin', '2025-06-11 12:47:57', 'admin', '2025-06-11 12:47:57', 0);

-- ----------------------------
-- Table structure for lab_device_repair
-- ----------------------------
DROP TABLE IF EXISTS `lab_device_repair`;
CREATE TABLE `lab_device_repair`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `device_id` int NOT NULL COMMENT '设备ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '故障描述',
  `repair_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报修时间',
  `technician_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '维修技术员',
  `status` int NOT NULL COMMENT '维修状态',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备报修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_device_repair
-- ----------------------------
INSERT INTO `lab_device_repair` VALUES (1, 1, '内存占满', '2025-06-02', '杨洋', 2, 'admin', '2025-06-11 12:55:02', 'admin', '2025-06-11 12:55:02', 0);

-- ----------------------------
-- Table structure for lab_info
-- ----------------------------
DROP TABLE IF EXISTS `lab_info`;
CREATE TABLE `lab_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '实验室ID，主键，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '实验室名称',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '删除标志：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_info
-- ----------------------------
INSERT INTO `lab_info` VALUES (1, '计算机科学实验室', 'admin', '2023-01-15 09:00:00', 'admin', '2023-01-15 09:00:00', 0);
INSERT INTO `lab_info` VALUES (2, '物理实验室', 'admin', '2023-01-15 09:10:00', 'admin', '2023-01-15 09:10:00', 0);
INSERT INTO `lab_info` VALUES (3, '化学实验室', 'admin', '2023-01-15 09:20:00', 'admin', '2023-01-15 09:20:00', 0);

-- ----------------------------
-- Table structure for lab_login_log
-- ----------------------------
DROP TABLE IF EXISTS `lab_login_log`;
CREATE TABLE `lab_login_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录用户',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录IP',
  `login_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录地址',
  `driver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '驱动名称',
  `os_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作系统',
  `login_state` int NOT NULL COMMENT '登录状态',
  `login_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '登录结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_login_log
-- ----------------------------
INSERT INTO `lab_login_log` VALUES (1, '12345', '2025-06-11 12:25:12', '106.80.9.180', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 0, '账号已被禁用');
INSERT INTO `lab_login_log` VALUES (2, '12345', '2025-06-11 12:25:14', '106.80.9.180', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 0, '账号已被禁用');
INSERT INTO `lab_login_log` VALUES (3, '12345', '2025-06-11 12:27:04', '106.80.9.180', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (4, 'admin', '2025-06-11 12:39:04', '106.80.9.180', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 0, '账号已被禁用');
INSERT INTO `lab_login_log` VALUES (5, 'admin', '2025-06-11 12:39:44', '106.80.9.180', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (6, 'teacher', '2025-06-11 12:55:30', '106.80.9.180', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (7, 'admin', '2025-06-12 10:34:41', '106.80.9.180', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (8, 'admin', '2025-06-16 09:40:22', '183.69.141.159', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (9, 'admin', '2025-06-16 10:10:37', '183.69.141.159', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (10, 'admin', '2025-06-17 09:33:31', '183.69.141.159', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (11, 'admin', '2025-06-17 11:10:00', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');

-- ----------------------------
-- Table structure for lab_open
-- ----------------------------
DROP TABLE IF EXISTS `lab_open`;
CREATE TABLE `lab_open`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '负责人',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `start_time` datetime NOT NULL COMMENT '开放时间',
  `stop_time` datetime NOT NULL COMMENT '关闭时间',
  `lab_id` int NOT NULL COMMENT '实验室ID',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室开放信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_open
-- ----------------------------
INSERT INTO `lab_open` VALUES (1, '杨洋', '男', '18723843671', '2025-05-26 00:00:00', '2025-06-02 00:00:00', 1, 'admin', '2025-06-11 12:42:40', 'admin', '2025-06-11 12:42:40', 0);

-- ----------------------------
-- Table structure for lab_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `lab_operation_log`;
CREATE TABLE `lab_operation_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'system',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作类型',
  `operation_module` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作模块',
  `operation_time` datetime NOT NULL COMMENT '操作时间',
  `operation_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作IP',
  `operation_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作地址',
  `operation_state` int NOT NULL COMMENT '操作状态',
  `operation_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '操作结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_operation_log
-- ----------------------------
INSERT INTO `lab_operation_log` VALUES (1, 'system', '添加', '用户管理', '2025-06-11 12:11:33', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_user\' doesn\'t exist\r\n### The error may exist in com/laboratory/mapper/UserMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_user  ( username, nickname, password, user_sex,  user_email,   role_id, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?,   ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_user\' doesn\'t exist\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_user\' doesn\'t exist');
INSERT INTO `lab_operation_log` VALUES (2, 'system', '添加', '用户管理', '2025-06-11 12:11:35', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_user\' doesn\'t exist\r\n### The error may exist in com/laboratory/mapper/UserMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_user  ( username, nickname, password, user_sex,  user_email,   role_id, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?,   ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_user\' doesn\'t exist\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_user\' doesn\'t exist');
INSERT INTO `lab_operation_log` VALUES (3, 'system', '添加', '用户管理', '2025-06-11 12:11:44', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_user\' doesn\'t exist\r\n### The error may exist in com/laboratory/mapper/UserMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_user  ( username, nickname, password, user_sex,  user_email,   role_id, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?,   ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_user\' doesn\'t exist\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_user\' doesn\'t exist');
INSERT INTO `lab_operation_log` VALUES (4, 'system', '添加', '用户管理', '2025-06-11 12:12:49', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'user_email\' in \'field list\'\r\n### The error may exist in com/laboratory/mapper/UserMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_user  ( username, nickname, password, user_sex,  user_email,   role_id, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?,   ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'user_email\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'user_email\' in \'field list\'');
INSERT INTO `lab_operation_log` VALUES (5, 'system', '添加', '用户管理', '2025-06-11 12:14:00', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Column \'create_at\' cannot be null\r\n### The error may exist in com/laboratory/mapper/UserMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_user  ( username, nickname, password, user_sex,  user_email,   role_id, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?,   ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Column \'create_at\' cannot be null\n; Column \'create_at\' cannot be null; nested exception is java.sql.SQLIntegrityConstraintViolationException: Column \'create_at\' cannot be null');
INSERT INTO `lab_operation_log` VALUES (6, 'system', '添加', '用户管理', '2025-06-11 12:14:21', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Column \'create_at\' cannot be null\r\n### The error may exist in com/laboratory/mapper/UserMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_user  ( username, nickname, password, user_sex,  user_email,   role_id, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?,   ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Column \'create_at\' cannot be null\n; Column \'create_at\' cannot be null; nested exception is java.sql.SQLIntegrityConstraintViolationException: Column \'create_at\' cannot be null');
INSERT INTO `lab_operation_log` VALUES (7, 'system', '添加', '用户管理', '2025-06-11 12:17:37', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Column \'create_at\' cannot be null\r\n### The error may exist in com/laboratory/mapper/UserMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_user  ( username, nickname, password, user_sex,  user_email,   role_id, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?,   ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Column \'create_at\' cannot be null\n; Column \'create_at\' cannot be null; nested exception is java.sql.SQLIntegrityConstraintViolationException: Column \'create_at\' cannot be null');
INSERT INTO `lab_operation_log` VALUES (8, 'system', '添加', '用户管理', '2025-06-11 12:20:34', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'user_email\' in \'field list\'\r\n### The error may exist in com/laboratory/mapper/UserMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_user  ( username, nickname, password, user_sex,  user_email,   role_id, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?,   ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'user_email\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'user_email\' in \'field list\'');
INSERT INTO `lab_operation_log` VALUES (9, 'system', '添加', '用户管理', '2025-06-11 12:21:24', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLException: Field \'status\' doesn\'t have a default value\r\n### The error may exist in com/laboratory/mapper/UserMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.UserMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_user  ( username, nickname, password, user_sex,  user_email,   role_id, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?,   ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLException: Field \'status\' doesn\'t have a default value\n; Field \'status\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'status\' doesn\'t have a default value');
INSERT INTO `lab_operation_log` VALUES (10, 'system', '添加', '用户管理', '2025-06-11 12:23:18', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (11, 'system', '添加', '用户管理', '2025-06-11 12:23:34', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (12, 'system', '添加', '用户管理', '2025-06-11 12:09:09', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_login_log\' doesn\'t exist\r\n### The error may exist in com/laboratory/mapper/LoginLogMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.LoginLogMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_login_log  ( username, login_time, login_ip, login_addr, driver_name, os_name, login_state, login_result )  VALUES  ( ?, ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_login_log\' doesn\'t exist\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Table \'laboratorydb.lab_login_log\' doesn\'t exist');
INSERT INTO `lab_operation_log` VALUES (13, 'mushuo', '修改', '用户管理', '2025-06-11 12:30:09', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (14, 'mushuo', '修改', '用户管理', '2025-06-11 12:30:22', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (15, 'mushuo', '修改', '实验室预约管理', '2025-06-11 12:31:01', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (16, 'mushuo', '导出', '实验室课程管理', '2025-06-11 12:35:15', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (17, 'system', '添加', '用户管理', '2025-06-11 12:38:55', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (18, 'system', '添加', '用户管理', '2025-06-11 12:39:19', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (19, 'admin', '添加', '用户管理', '2025-06-11 12:40:28', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (20, 'admin', '添加', '实验室人员管理', '2025-06-11 12:42:21', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (21, 'admin', '添加', '实验室开放管理', '2025-06-11 12:42:40', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (22, 'admin', '添加', '实验室课程管理', '2025-06-11 12:43:04', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (23, 'admin', '添加', '课程项目管理', '2025-06-11 12:44:39', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (24, 'admin', '添加', '实验室设备管理', '2025-06-11 12:45:52', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (25, 'admin', '添加', '实验室设备管理', '2025-06-11 12:47:57', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (26, 'admin', '添加', '设备报修管理', '2025-06-11 12:48:19', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'description\' in \'field list\'\r\n### The error may exist in com/laboratory/mapper/RepairMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.RepairMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_device_repair  ( device_id, description, repair_date, technician_name, status, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'description\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'description\' in \'field list\'');
INSERT INTO `lab_operation_log` VALUES (27, 'admin', '添加', '设备报修管理', '2025-06-11 12:49:32', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'description\' in \'field list\'\r\n### The error may exist in com/laboratory/mapper/RepairMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.RepairMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_device_repair  ( device_id, description, repair_date, technician_name, status, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'description\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'description\' in \'field list\'');
INSERT INTO `lab_operation_log` VALUES (28, 'admin', '添加', '设备报修管理', '2025-06-11 12:51:44', '106.80.9.180', '中国|重庆|重庆市|电信', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'repair_date\' in \'field list\'\r\n### The error may exist in com/laboratory/mapper/RepairMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.RepairMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_device_repair  ( device_id, description, repair_date, technician_name, status, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'repair_date\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'repair_date\' in \'field list\'');
INSERT INTO `lab_operation_log` VALUES (29, 'admin', '添加', '设备报修管理', '2025-06-11 12:55:02', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (30, 'teacher', '修改', '实验室预约管理', '2025-06-11 12:56:02', '106.80.9.180', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');

-- ----------------------------
-- Table structure for lab_person
-- ----------------------------
DROP TABLE IF EXISTS `lab_person`;
CREATE TABLE `lab_person`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `lab_id` int NOT NULL COMMENT '实验室ID',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室人员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_person
-- ----------------------------
INSERT INTO `lab_person` VALUES (1, '杨洋', '男', 16, '巴南', 1, 'admin', '2025-06-11 12:42:21', 'admin', '2025-06-11 12:42:21', 0);

-- ----------------------------
-- Table structure for lab_project
-- ----------------------------
DROP TABLE IF EXISTS `lab_project`;
CREATE TABLE `lab_project`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `course_id` int NOT NULL COMMENT '课程ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目名称',
  `start_time` datetime NOT NULL COMMENT '项目启动时间',
  `stop_time` datetime NOT NULL COMMENT '项目结束时间',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '项目介绍',
  `person_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目成员ID列表',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室课程项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_project
-- ----------------------------
INSERT INTO `lab_project` VALUES (1, 1, '模型构建流程', '2025-06-02 00:00:00', '2025-05-26 00:01:00', '构建机器学习模型详细架构', '1', 'admin', '2025-06-11 12:44:39', 'admin', '2025-06-11 12:44:39', 0);

-- ----------------------------
-- Table structure for lab_sub
-- ----------------------------
DROP TABLE IF EXISTS `lab_sub`;
CREATE TABLE `lab_sub`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分室ID，主键，自增',
  `lab_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分室名称',
  `lab_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分室类型：教学型/研究型等',
  `lab_create_year` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分室创建年份',
  `lab_area` int NULL DEFAULT NULL COMMENT '分室面积(平方米)',
  `lab_status` int NOT NULL COMMENT '分室状态：1-正常，2-维护中，3-关闭',
  `lab_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分室地址',
  `lab_seat` int NULL DEFAULT NULL COMMENT '分室座位数',
  `lab_major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属专业',
  `lab_id` int NOT NULL COMMENT '关联的主实验室ID',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '删除标志：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室分室信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_sub
-- ----------------------------
INSERT INTO `lab_sub` VALUES (1, '人工智能实验室', '研究型', '2020', 120, 1, '主楼B301', 50, '计算机科学', 1, 'admin', '2023-01-16 10:00:00', 'admin', '2023-01-16 10:00:00', 0);
INSERT INTO `lab_sub` VALUES (2, '数据处理实验室', '教学型', '2018', 100, 1, '主楼B302', 45, '计算机科学', 1, 'admin', '2023-01-16 10:10:00', 'admin', '2023-01-16 10:10:00', 0);
INSERT INTO `lab_sub` VALUES (3, '力学实验室', '教学型', '2015', 80, 1, '科学楼A203', 30, '物理学', 2, 'admin', '2023-01-16 10:20:00', 'admin', '2023-01-16 10:20:00', 0);
INSERT INTO `lab_sub` VALUES (4, '有机化学实验室', '研究型', '2019', 90, 1, '化学楼C105', 25, '化学', 3, 'admin', '2023-01-16 10:30:00', 'admin', '2023-01-16 10:30:00', 0);

-- ----------------------------
-- Table structure for lab_user
-- ----------------------------
DROP TABLE IF EXISTS `lab_user`;
CREATE TABLE `lab_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `user_sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `user_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `user_Email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` int NULL DEFAULT 1 COMMENT '状态：1-正常，2-禁用',
  `role_id` int NOT NULL COMMENT '角色ID',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab_user
-- ----------------------------
INSERT INTO `lab_user` VALUES (1, '234123124441', '123', 'e10adc3949ba59abbe56e057f20f883e', '未知', NULL, '2351321183@qq.com', 2, 3, NULL, NULL, '2025-06-11 12:23:19', NULL, '2025-06-11 12:23:19', 0);
INSERT INTO `lab_user` VALUES (2, '12345', 'mushuo', 'e10adc3949ba59abbe56e057f20f883e', '男', NULL, '2351321183@qq.com', 2, 3, '我爱学习，学习使我快乐。。', NULL, '2025-06-11 12:23:34', 'mushuo', '2025-06-11 12:30:22', 0);
INSERT INTO `lab_user` VALUES (3, 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '未知', NULL, '2351321183@qq.com', 2, 1, NULL, NULL, '2025-06-11 12:38:55', NULL, '2025-06-11 12:38:55', 0);
INSERT INTO `lab_user` VALUES (4, 'teacher', 'teacher', 'e10adc3949ba59abbe56e057f20f883e', '未知', NULL, '1134974937@qq.com', 2, 2, NULL, NULL, '2025-06-11 12:39:19', NULL, '2025-06-11 12:39:19', 0);
INSERT INTO `lab_user` VALUES (5, 'mushuo', 'mushuo', 'e10adc3949ba59abbe56e057f20f883e', '未知', NULL, '2351321183@qq.com', 0, 1, '', 'admin', '2025-06-11 12:40:28', 'admin', '2025-06-11 12:40:28', 0);

SET FOREIGN_KEY_CHECKS = 1;
