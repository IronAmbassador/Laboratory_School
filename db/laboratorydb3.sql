/*
 Navicat Premium Dump SQL

 Source Server         : 4
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : localhost:3306
 Source Schema         : laboratorydb

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 14/03/2026 21:54:57
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
  `booking_status` int NOT NULL DEFAULT 0 COMMENT '预约状态：1-待审核，2-已通过，3-已拒绝，4-已取消',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预约备注',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '删除标志：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室预约表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_booking
-- ----------------------------
INSERT INTO `lab_booking` VALUES (1, 1, 4, '2023-05-01 09:00:00', '2023-05-01 12:00:00', 2, '进行图像识别实验', 'student1', '2023-04-25 10:30:00', 'teacher', '2025-06-11 12:56:03', 0);
INSERT INTO `lab_booking` VALUES (2, 2, 5, '2023-05-02 13:00:00', '2023-05-02 16:00:00', 2, '材料力学实验', 'student2', '2023-04-26 09:45:00', 'admin', '2026-03-14 17:11:48', 1);
INSERT INTO `lab_booking` VALUES (3, 1, 2, '2023-05-05 08:30:00', '2023-05-05 11:30:00', 2, '课程实验指导', 'teacher1', '2023-04-27 14:20:00', 'mushuo', '2025-06-11 12:31:02', 0);
INSERT INTO `lab_booking` VALUES (4, 9, 7, '2026-03-12 14:39:21', '2026-03-13 14:39:23', 1, '教师预约实验室功能测试', 'teacher', '2026-03-12 14:39:29', 'admin', '2026-03-14 17:11:48', 1);
INSERT INTO `lab_booking` VALUES (5, 8, 6, '2026-03-14 16:36:06', '2026-03-15 16:36:14', 1, '预约功能测试', 'student', '2026-03-14 16:36:32', 'student', '2026-03-14 16:36:32', 0);
INSERT INTO `lab_booking` VALUES (6, 9, 7, '2026-03-14 16:41:34', '2026-03-14 16:41:41', 1, '教师预约实验室功能测试', 'teacher', '2026-03-14 16:42:03', 'teacher', '2026-03-14 16:42:03', 0);
INSERT INTO `lab_booking` VALUES (7, 9, 6, '2026-03-14 17:34:47', '2026-03-14 17:34:50', 1, '学生预约实验室功能测试', 'student', '2026-03-14 17:35:06', 'student', '2026-03-14 17:35:06', 0);
INSERT INTO `lab_booking` VALUES (8, 7, 7, '2026-03-14 20:21:27', '2026-03-14 20:21:30', 2, '学生预约实验室功能测试2', 'teacher', '2026-03-14 20:21:44', 'student', '2026-03-14 20:29:51', 0);
INSERT INTO `lab_booking` VALUES (9, 9, 7, '2026-03-14 20:31:23', '2026-03-14 20:31:25', 1, '教师预约实验室功能测试3', 'teacher', '2026-03-14 20:31:49', 'admin', '2026-03-14 20:32:55', 0);

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
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室课程表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_course
-- ----------------------------
INSERT INTO `lab_course` VALUES (1, '机器学习', '杨洋', '2025-06-02 00:00:00', '2025-06-04 00:00:00', 1, 'admin', '2025-06-11 12:43:04', 'admin', '2025-06-11 12:43:04', 0);
INSERT INTO `lab_course` VALUES (2, '深度学习', '杨洋', '2025-06-09 00:00:00', '2025-06-19 00:00:00', 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `lab_course` VALUES (3, '前端框架学习', '邓老师', '2026-03-14 21:26:50', '2026-03-15 21:26:56', 1, 'teacher', '2026-03-14 21:27:04', 'teacher', '2026-03-14 21:27:04', 0);

-- ----------------------------
-- Table structure for lab_device
-- ----------------------------
DROP TABLE IF EXISTS `lab_device`;
CREATE TABLE `lab_device`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `device_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备名称',
  `device_date` datetime NULL DEFAULT NULL COMMENT '购买时间',
  `device_price` int NULL DEFAULT NULL COMMENT '设备价格',
  `guarantee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '保修时间',
  `device_factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生产厂商',
  `device_status` int NULL DEFAULT NULL COMMENT '设备状态',
  `lab_id` int NULL DEFAULT NULL COMMENT '实验室ID',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室设备表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_device
-- ----------------------------
INSERT INTO `lab_device` VALUES (1, '门窗检查', '2024-12-01 09:00:00', NULL, '2024-2025第一学期', '张老师', 0, 1, 'admin', '2024-12-01 09:00:00', 'admin', '2024-12-01 09:00:00', 0);
INSERT INTO `lab_device` VALUES (2, '电器检查', '2024-12-01 10:00:00', NULL, '2024-2025第一学期', '李老师', 1, 1, 'admin', '2024-12-01 10:00:00', 'admin', '2024-12-01 10:00:00', 0);
INSERT INTO `lab_device` VALUES (3, '门窗检查', '2025-06-03 00:00:00', NULL, '2025-2026第一学期', '杨洋', 0, 6, 'admin', '2025-06-19 10:55:56', 'admin', '2025-06-19 10:55:56', 0);
INSERT INTO `lab_device` VALUES (4, '线路检查', '2024-12-02 09:00:00', NULL, '2024-2025第一学期', '王老师', 0, 2, 'admin', '2024-12-02 09:00:00', 'admin', '2024-12-02 09:00:00', 0);
INSERT INTO `lab_device` VALUES (5, '消防检查', '2024-12-02 14:00:00', NULL, '2024-2025第一学期', '赵老师', 1, 2, 'admin', '2024-12-02 14:00:00', 'admin', '2024-12-02 14:00:00', 0);
INSERT INTO `lab_device` VALUES (6, '设备检查', '2024-12-03 08:30:00', NULL, '2024-2025第一学期', '陈老师', 0, 3, 'admin', '2024-12-03 08:30:00', 'admin', '2024-12-03 08:30:00', 0);
INSERT INTO `lab_device` VALUES (7, '卫生检查', '2024-12-03 15:00:00', NULL, '2024-2025第一学期', '刘老师', 0, 3, 'admin', '2024-12-03 15:00:00', 'admin', '2024-12-03 15:00:00', 0);
INSERT INTO `lab_device` VALUES (8, '门窗检查', '2024-12-04 09:00:00', NULL, '2024-2025第一学期', '杨老师', 1, 4, 'admin', '2024-12-04 09:00:00', 'admin', '2024-12-04 09:00:00', 0);
INSERT INTO `lab_device` VALUES (9, '电器检查', '2024-12-04 11:00:00', NULL, '2024-2025第一学期', '周老师', 0, 4, 'admin', '2024-12-04 11:00:00', 'admin', '2024-12-04 11:00:00', 0);
INSERT INTO `lab_device` VALUES (10, '线路检查', '2024-12-05 10:00:00', NULL, '2024-2025第一学期', '吴老师', 2, 5, 'admin', '2024-12-05 10:00:00', 'admin', '2024-12-05 10:00:00', 0);

-- ----------------------------
-- Table structure for lab_device_repair
-- ----------------------------
DROP TABLE IF EXISTS `lab_device_repair`;
CREATE TABLE `lab_device_repair`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `device_id` int NOT NULL COMMENT '设备ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '故障描述',
  `repair_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报修时间',
  `technician_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '维修技术员',
  `status` int NULL DEFAULT NULL COMMENT '维修状态',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备报修表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_device_repair
-- ----------------------------
INSERT INTO `lab_device_repair` VALUES (1, 1, '内存占满', '2025-06-02', '杨洋', 2, 'admin', '2025-06-11 12:55:02', 'admin', '2025-06-11 12:55:02', 0);
INSERT INTO `lab_device_repair` VALUES (2, 3, '门窗坏了', '2025-06-09', '', 2, 'admin', '2025-06-19 10:56:22', 'admin', '2025-06-19 10:56:22', 0);
INSERT INTO `lab_device_repair` VALUES (3, 2, '实验室投影仪无法正常开机，电源指示灯不亮', '2024-12-01', '维修师傅A', 1, 'admin', '2024-12-01 11:00:00', 'teacher', '2026-03-14 20:36:25', 1);
INSERT INTO `lab_device_repair` VALUES (4, 5, '机房空调制冷效果差，温度过高影响设备运行', '2024-12-02', '维修师傅B', 2, 'admin', '2024-12-02 15:00:00', 'admin', '2024-12-02 16:30:00', 0);
INSERT INTO `lab_device_repair` VALUES (5, 8, '实验室门锁损坏，无法正常锁闭', '2024-12-04', '维修师傅C', 1, 'admin', '2024-12-04 10:00:00', 'admin', '2024-12-04 10:00:00', 0);
INSERT INTO `lab_device_repair` VALUES (6, 10, '安全检查测试', '2026-03-14', '邓师傅', 3, 'student', '2026-03-14 16:37:23', 'teacher', '2026-03-14 16:40:31', 0);

-- ----------------------------
-- Table structure for lab_info
-- ----------------------------
DROP TABLE IF EXISTS `lab_info`;
CREATE TABLE `lab_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '实验室ID，主键，自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '实验室名称',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '删除标志：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室基本信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_info
-- ----------------------------
INSERT INTO `lab_info` VALUES (1, '计算机科学实验室', 'admin', '2023-01-15 09:00:00', 'admin', '2023-01-15 09:00:00', 0);
INSERT INTO `lab_info` VALUES (2, '软件工程实验室', 'admin', '2024-09-01 09:00:00', 'admin', '2024-09-01 09:00:00', 0);
INSERT INTO `lab_info` VALUES (3, '人工智能实验室', 'admin', '2024-09-01 10:00:00', 'admin', '2024-09-01 10:00:00', 0);
INSERT INTO `lab_info` VALUES (4, '博能实验室', NULL, NULL, NULL, NULL, 0);
INSERT INTO `lab_info` VALUES (5, '数据库实验室', 'admin', '2024-09-01 11:00:00', 'admin', '2024-09-01 11:00:00', 0);
INSERT INTO `lab_info` VALUES (6, '网络安全实验室', 'admin', '2024-09-01 12:00:00', 'admin', '2024-09-01 12:00:00', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '登录日志表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `lab_login_log` VALUES (12, 'admin', '2025-06-17 17:46:08', '106.80.9.180', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (13, 'admin', '2025-06-17 21:10:51', '106.80.9.180', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (14, 'admin', '2025-06-18 17:41:09', '183.69.141.159', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (15, 'admin', '2025-06-19 08:33:01', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (16, 'mushuo', '2025-06-19 08:40:05', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 0, '密码错误');
INSERT INTO `lab_login_log` VALUES (17, 'mushuo', '2025-06-19 08:40:10', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (18, 'admin', '2025-06-19 08:41:53', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (19, 'mushuo', '2025-06-19 08:42:14', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (20, 'mushuo', '2025-06-19 08:43:46', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (21, 'mushuo', '2025-06-19 08:46:01', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (22, 'mushuo', '2025-06-19 08:46:19', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (23, 'admin', '2025-06-19 08:47:16', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (24, 'admin', '2025-06-19 08:55:48', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (25, 'admin', '2025-06-19 09:39:41', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (26, 'mushuo', '2025-06-19 11:04:04', '183.69.141.156', '中国|重庆|重庆市|电信', 'MSEdge 137.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (27, 'admin', '2026-03-12 13:42:08', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (28, 'mushuo', '2026-03-12 13:52:21', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (29, 'admin', '2026-03-12 13:53:22', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '密码错误');
INSERT INTO `lab_login_log` VALUES (30, 'teacher', '2026-03-12 13:53:33', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '用户名不存在');
INSERT INTO `lab_login_log` VALUES (31, 'teacher', '2026-03-12 13:53:53', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '用户名不存在');
INSERT INTO `lab_login_log` VALUES (32, 'admin', '2026-03-12 13:55:06', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (33, 'student', '2026-03-12 13:58:14', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '用户名不存在');
INSERT INTO `lab_login_log` VALUES (34, 'studen', '2026-03-12 13:58:23', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (35, 'student', '2026-03-12 14:00:04', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '用户名不存在');
INSERT INTO `lab_login_log` VALUES (36, 'student', '2026-03-12 14:00:11', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '用户名不存在');
INSERT INTO `lab_login_log` VALUES (37, 'studen', '2026-03-12 14:00:16', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (38, 'admin', '2026-03-12 14:00:49', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (39, 'moshuo', '2026-03-12 14:03:46', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '用户名不存在');
INSERT INTO `lab_login_log` VALUES (40, 'mushuo', '2026-03-12 14:03:53', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (41, 'student', '2026-03-12 14:04:48', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (42, 'admin', '2026-03-12 14:08:16', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (43, 'teacher', '2026-03-12 14:35:47', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '用户名不存在');
INSERT INTO `lab_login_log` VALUES (44, 'teacher', '2026-03-12 14:36:05', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '用户名不存在');
INSERT INTO `lab_login_log` VALUES (45, 'teacher', '2026-03-12 14:37:10', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 0, '账号已被禁用');
INSERT INTO `lab_login_log` VALUES (46, 'admin', '2026-03-12 14:37:18', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (47, 'teacher', '2026-03-12 14:38:09', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (48, 'admin', '2026-03-12 14:39:53', '223.104.75.224', '中国|广东省|东莞市|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (49, 'admin', '2026-03-14 15:22:01', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (50, 'student', '2026-03-14 16:33:37', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (51, 'teacher', '2026-03-14 16:38:16', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (52, 'admin', '2026-03-14 16:48:05', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (53, 'admin', '2026-03-14 17:06:10', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (54, 'teacher', '2026-03-14 17:14:33', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (55, 'student', '2026-03-14 17:15:41', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (56, 'admin', '2026-03-14 17:29:38', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (57, 'student', '2026-03-14 17:34:21', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (58, 'admin', '2026-03-14 17:47:51', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (59, 'teacher', '2026-03-14 17:48:51', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (60, 'admin', '2026-03-14 20:23:04', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (61, 'student', '2026-03-14 20:24:51', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (62, 'teacher', '2026-03-14 20:30:57', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (63, 'admin', '2026-03-14 20:32:38', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (64, 'teacher', '2026-03-14 20:33:24', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (65, 'admin', '2026-03-14 20:38:40', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (66, 'teacher', '2026-03-14 20:56:44', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (67, 'student', '2026-03-14 21:29:01', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');
INSERT INTO `lab_login_log` VALUES (68, 'admin', '2026-03-14 21:41:25', '223.104.86.194', '中国|移动', 'Chrome 120.0.0.0', 'Windows 11', 1, '登录成功');

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
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室开放信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_open
-- ----------------------------
INSERT INTO `lab_open` VALUES (1, '杨洋', '男', '18723843671', '2025-05-26 00:00:00', '2025-06-02 00:00:00', 1, 'admin', '2025-06-11 12:42:40', 'admin', '2025-06-11 12:42:40', 0);
INSERT INTO `lab_open` VALUES (2, '邓老师', '女', '18511111111', '2026-03-14 17:12:20', '2026-04-01 00:00:00', 1, 'admin', '2026-03-14 17:12:31', 'admin', '2026-03-14 17:12:31', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `lab_operation_log` VALUES (31, 'admin', '导出', '实验室课程管理', '2025-06-18 12:00:31', '183.69.141.159', '中国|重庆|重庆市|电信', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (32, 'admin', '导出', '实验室课程管理', '2025-06-18 12:00:37', '183.69.141.159', '中国|重庆|重庆市|电信', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (33, 'admin', '导出', '实验室设备管理', '2025-06-18 12:00:45', '183.69.141.159', '中国|重庆|重庆市|电信', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (34, 'admin', '导出', '实验室分室管理', '2025-06-19 10:17:36', '183.69.141.156', '中国|重庆|重庆市|电信', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (35, 'admin', '导出', '实验室分室管理', '2025-06-19 10:17:38', '183.69.141.156', '中国|重庆|重庆市|电信', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (36, 'admin', '导出', '实验室分室管理', '2025-06-19 10:18:37', '183.69.141.156', '中国|重庆|重庆市|电信', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (37, 'admin', '添加', '实验室分室管理', '2025-06-19 10:31:54', '183.69.141.156', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (38, 'admin', '添加', '实验室分室管理', '2025-06-19 10:41:01', '183.69.141.156', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (39, 'admin', '添加', '实验室分室管理', '2025-06-19 10:46:36', '183.69.141.156', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (40, 'admin', '添加', '实验室设备管理', '2025-06-19 10:55:56', '183.69.141.156', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (41, 'admin', '添加', '设备报修管理', '2025-06-19 10:56:22', '183.69.141.156', '中国|重庆|重庆市|电信', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (42, 'admin', '导出', '实验室分室管理', '2025-06-19 11:03:30', '183.69.141.156', '中国|重庆|重庆市|电信', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (43, 'admin', '添加', '用户管理', '2026-03-12 13:57:08', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (44, 'admin', '修改', '用户管理', '2026-03-12 13:57:25', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (45, 'admin', '修改', '用户管理', '2026-03-12 13:57:38', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (46, 'studen', '修改', '用户管理', '2026-03-12 13:59:02', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (47, 'studen', '修改', '用户管理', '2026-03-12 13:59:21', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (48, 'admin', '修改', '用户管理', '2026-03-12 14:01:08', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (49, 'admin', '修改', '用户管理', '2026-03-12 14:01:26', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (50, 'admin', '修改', '用户管理', '2026-03-12 14:01:41', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (51, 'admin', '修改', '用户管理', '2026-03-12 14:01:55', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (52, 'student', '添加', '实验室预约管理', '2026-03-12 14:25:56', '223.104.75.224', '中国|广东省|东莞市|移动', 0, ' \r\n### Error updating database.  Cause: java.sql.SQLException: Field \'booking_status\' doesn\'t have a default value\r\n### The error may exist in com/laboratory/mapper/BookingMapper.java (best guess)\r\n### The error may involve com.laboratory.mapper.BookingMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO lab_booking  ( lab_id, user_id, start_time, end_time,  remark, create_at, create_time, update_at, update_time )  VALUES  ( ?, ?, ?, ?,  ?, ?, ?, ?, ? )\r\n### Cause: java.sql.SQLException: Field \'booking_status\' doesn\'t have a default value\n; Field \'booking_status\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'booking_status\' doesn\'t have a default value');
INSERT INTO `lab_operation_log` VALUES (53, 'system', '添加', '用户管理', '2026-03-12 14:36:39', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (54, 'admin', '修改', '用户管理', '2026-03-12 14:37:49', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (55, 'teacher', '添加', '实验室预约管理', '2026-03-12 14:39:29', '223.104.75.224', '中国|广东省|东莞市|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (56, 'student', '添加', '实验室预约管理', '2026-03-14 16:36:31', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (57, 'student', '添加', '设备报修管理', '2026-03-14 16:37:23', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (58, 'teacher', '修改', '设备报修管理', '2026-03-14 16:40:30', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (59, 'teacher', '添加', '实验室预约管理', '2026-03-14 16:42:02', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (60, 'teacher', '修改', '实验室预约管理', '2026-03-14 16:42:43', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (61, 'admin', '修改', '实验室分室管理', '2026-03-14 16:49:11', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (62, 'admin', '修改', '实验室分室管理', '2026-03-14 16:49:27', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (63, 'admin', '修改', '实验室分室管理', '2026-03-14 16:49:31', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (64, 'admin', '修改', '实验室分室管理', '2026-03-14 16:49:38', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (65, 'admin', '修改', '实验室分室管理', '2026-03-14 16:49:44', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (66, 'admin', '修改', '实验室分室管理', '2026-03-14 16:49:45', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (67, 'admin', '修改', '实验室分室管理', '2026-03-14 16:49:51', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (68, 'admin', '修改', '实验室分室管理', '2026-03-14 16:50:03', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (69, 'admin', '修改', '实验室分室管理', '2026-03-14 16:50:00', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (70, 'admin', '导出', '实验室预约管理', '2026-03-14 17:09:51', '223.104.86.194', '中国|移动', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (71, 'admin', '导出', '实验室预约管理', '2026-03-14 17:10:48', '223.104.86.194', '中国|移动', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (72, 'admin', '批量删除', '实验室预约管理', '2026-03-14 17:11:47', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (73, 'admin', '添加', '实验室开放管理', '2026-03-14 17:12:29', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (74, 'student', '添加', '实验室预约管理', '2026-03-14 17:35:06', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (75, 'teacher', '添加', '实验室预约管理', '2026-03-14 20:21:44', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (76, 'student', '删除', '用户管理', '2026-03-14 20:26:23', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (77, 'student', '修改', '实验室预约管理', '2026-03-14 20:29:51', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (78, 'teacher', '添加', '实验室预约管理', '2026-03-14 20:31:48', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (79, 'admin', '修改', '实验室预约管理', '2026-03-14 20:32:54', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (80, 'teacher', '导出', '实验室分室管理', '2026-03-14 20:33:59', '223.104.86.194', '中国|移动', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (81, 'teacher', '导出', '实验室分室管理', '2026-03-14 20:34:13', '223.104.86.194', '中国|移动', 0, 'LabException{code=500, message=导出数据失败}');
INSERT INTO `lab_operation_log` VALUES (82, 'teacher', '删除', '设备报修管理', '2026-03-14 20:36:21', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (83, 'admin', '修改', '用户管理', '2026-03-14 20:56:07', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (84, 'teacher', '添加', '实验室人员管理', '2026-03-14 21:08:17', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (85, 'teacher', '删除', '实验室人员管理', '2026-03-14 21:08:25', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (86, 'teacher', '修改', '课程项目管理', '2026-03-14 21:25:30', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (87, 'teacher', '添加', '实验室课程管理', '2026-03-14 21:27:02', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (88, 'teacher', '添加', '课程项目管理', '2026-03-14 21:27:44', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (89, 'teacher', '删除', '课程项目管理', '2026-03-14 21:28:03', '223.104.86.194', '中国|移动', 1, 'Result(code=200, message=操作成功, data=null)');
INSERT INTO `lab_operation_log` VALUES (90, 'teacher', '删除', '课程项目管理', '2026-03-14 21:27:54', '223.104.86.194', '中国|移动', 1, 'Result(code=201, message=操作失败, data=null)');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室人员信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_person
-- ----------------------------
INSERT INTO `lab_person` VALUES (1, '杨洋', '男', 16, '巴南', 1, 'admin', '2025-06-11 12:42:21', 'teacher', '2026-03-14 21:08:27', 1);
INSERT INTO `lab_person` VALUES (2, '测试姓名1', '男', 16, '广东潮州', 1, 'teacher', '2026-03-14 21:08:18', 'teacher', '2026-03-14 21:08:18', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室课程项目表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_project
-- ----------------------------
INSERT INTO `lab_project` VALUES (1, 1, '模型构建流程', '2026-03-14 21:25:18', '2026-03-15 21:25:25', '构建机器学习模型详细架构', '2', 'admin', '2025-06-11 12:44:39', 'teacher', '2026-03-14 21:28:05', 1);
INSERT INTO `lab_project` VALUES (2, 3, 'Vue2', '2026-03-14 21:27:19', '2026-03-15 21:27:22', '项目测试1', '2', 'teacher', '2026-03-14 21:27:45', 'teacher', '2026-03-14 21:27:45', 0);

-- ----------------------------
-- Table structure for lab_sub
-- ----------------------------
DROP TABLE IF EXISTS `lab_sub`;
CREATE TABLE `lab_sub`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分室ID，主键，自增',
  `lab_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分室名称',
  `lab_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分室类型：教学型/研究型等',
  `lab_create_year` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分室创建年份',
  `lab_area` int NULL DEFAULT NULL COMMENT '分室面积(平方米)',
  `lab_status` int NULL DEFAULT NULL COMMENT '分室状态：1-正常，2-维护中，3-关闭',
  `lab_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分室地址',
  `lab_seat` int NULL DEFAULT NULL COMMENT '分室座位数',
  `lab_major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属专业',
  `lab_id` int NOT NULL COMMENT '关联的主实验室ID',
  `create_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int NULL DEFAULT 0 COMMENT '删除标志：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实验室分室信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_sub
-- ----------------------------
INSERT INTO `lab_sub` VALUES (1, 'Web前端开发室', '计算机学院', '2024', 80, 0, '教学楼A401', 40, '软件工程,计算机科学与技术', 2, 'admin', '2024-09-01 14:00:00', 'admin', '2024-09-01 14:00:00', 0);
INSERT INTO `lab_sub` VALUES (2, 'Java后端开发室', '计算机学院', '2024', 85, 0, '教学楼A402', 45, '软件工程', 2, 'admin', '2024-09-01 14:30:00', 'admin', '2024-09-01 14:30:00', 0);
INSERT INTO `lab_sub` VALUES (3, '移动应用开发室', '计算机学院', '2024', 75, 0, '教学楼A403', 35, '软件工程,物联网工程', 2, 'admin', '2024-09-01 15:00:00', 'admin', '2024-09-01 15:00:00', 0);
INSERT INTO `lab_sub` VALUES (4, '机器学习实训室', '计算机学院', '2024', 90, 0, '教学楼B301', 50, '软件工程,数据科学与大数据技术', 3, 'admin', '2024-09-01 15:30:00', 'admin', '2024-09-01 15:30:00', 0);
INSERT INTO `lab_sub` VALUES (5, '深度学习研究室', '计算机学院', '2024', 70, 0, '教学楼B302', 30, '软件工程,计算机科学与技术', 3, 'admin', '2024-09-01 16:00:00', 'admin', '2024-09-01 16:00:00', 0);
INSERT INTO `lab_sub` VALUES (6, 'java后端开发', '计算机学院', '2020', 1, 1, '4411', 0, '计算机科学与技术,软件工程', 2, 'admin', '2025-06-19 10:31:55', 'admin', '2025-06-19 10:31:55', 0);
INSERT INTO `lab_sub` VALUES (7, '数据库设计室', '计算机学院', '2024', 65, 0, '教学楼C201', 32, '软件工程,计算机科学与技术', 5, 'admin', '2024-09-01 16:30:00', 'admin', '2024-09-01 16:30:00', 0);
INSERT INTO `lab_sub` VALUES (8, '前端开发', '计算机学院', '2020', 0, 0, '4412', 0, '计算机科学与技术,软件工程', 1, 'admin', '2025-06-19 10:46:36', 'admin', '2025-06-19 10:46:36', 0);
INSERT INTO `lab_sub` VALUES (9, '网络攻防实验室', '计算机学院', '2024', 60, 0, '教学楼D101', 28, '网络空间安全,计算机科学与技术,软件工程,电子信息工程,物联网工程', 6, 'admin', '2024-09-01 17:00:00', 'admin', '2026-03-14 16:50:03', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lab_user
-- ----------------------------
INSERT INTO `lab_user` VALUES (3, 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '男', 'img/avater.png', '2351321183@qq.com', 2, 1, '好人', NULL, '2025-06-11 12:38:55', 'admin', '2026-03-14 20:56:11', 0);
INSERT INTO `lab_user` VALUES (6, 'student', 'student', '4297f44b13955235245b2497399d7a93', '女', NULL, 'abcd@qq.com', 0, 3, '测试学生账号', 'admin', '2026-03-12 13:57:08', 'admin', '2026-03-12 14:01:26', 0);
INSERT INTO `lab_user` VALUES (7, 'teacher', 'teacher', 'e10adc3949ba59abbe56e057f20f883e', '未知', NULL, 'abd@qq.com', 0, 2, NULL, NULL, '2026-03-12 14:36:39', 'admin', '2026-03-12 14:37:49', 0);

SET FOREIGN_KEY_CHECKS = 1;
