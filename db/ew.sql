/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50726
Source Host           : 127.0.0.1:3306
Source Database       : ew

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-11-06 10:47:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_action_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_action_log`;
CREATE TABLE `sys_action_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '日志类型',
  `ipaddr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `clazz` varchar(255) DEFAULT NULL COMMENT '产生日志的类',
  `method` varchar(255) DEFAULT NULL COMMENT '产生日志的方法',
  `model` varchar(255) DEFAULT NULL COMMENT '产生日志的表',
  `message` text COMMENT '日志消息',
  `create_date` datetime DEFAULT NULL COMMENT '记录时间',
  `oper_name` varchar(255) DEFAULT NULL COMMENT '产生日志的用户昵称',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统-行为日志';

-- ----------------------------
-- Records of sys_action_log
-- ----------------------------
INSERT INTO `sys_action_log` VALUES ('18', '登入', null, '127.0.0.1', 'com.ew.admin.system.controller.LoginController', 'login', null, '用户登入接口', '2020-11-05 19:03:22', null, null);
INSERT INTO `sys_action_log` VALUES ('19', '登入', null, '127.0.0.1', 'com.ew.admin.system.controller.LoginController', 'login', null, '用户登入接口', '2020-11-05 19:03:22', null, null);
INSERT INTO `sys_action_log` VALUES ('20', '登入', null, '127.0.0.1', 'com.ew.admin.system.controller.LoginController', 'login', null, '用户登入接口', '2020-11-06 10:19:20', null, null);
INSERT INTO `sys_action_log` VALUES ('21', '登入', null, '127.0.0.1', 'com.ew.admin.system.controller.LoginController', 'login', null, '用户登入接口', '2020-11-06 10:21:36', null, null);
INSERT INTO `sys_action_log` VALUES ('22', '登入', null, '127.0.0.1', 'com.ew.admin.system.controller.LoginController', 'login', null, '用户登入接口', '2020-11-06 10:22:49', null, null);
INSERT INTO `sys_action_log` VALUES ('23', '登入', null, '127.0.0.1', 'com.ew.admin.system.controller.LoginController', 'login', null, '用户登入接口', '2020-11-06 10:28:11', null, null);
INSERT INTO `sys_action_log` VALUES ('24', '登入', null, '127.0.0.1', 'com.ew.admin.system.controller.LoginController', 'login', null, '用户登入接口', '2020-11-06 10:31:11', null, null);
INSERT INTO `sys_action_log` VALUES ('25', '登入', null, '127.0.0.1', 'com.ew.admin.system.controller.LoginController', 'login', null, '用户登入接口', '2020-11-06 10:45:05', null, null);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `pids` varchar(255) DEFAULT NULL COMMENT '所有父级编号',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态（1:正常,2:删除）',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统-部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '管理部', null, null, null, null, null, null, null, null, '1');
INSERT INTO `sys_dept` VALUES ('2', '研发部', null, null, null, null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级编号',
  `pids` varchar(255) DEFAULT NULL COMMENT '所有父级编号',
  `url` varchar(255) DEFAULT NULL COMMENT 'URL地址',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型（1:一级菜单,2:子级菜单,3:不是菜单）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态（1:正常,2:删除）',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8 COMMENT='系统-菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('164', '首页', '0', null, null, '#', 'mdi-home', '1', '0', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('165', '用户管理', '167', null, '/system/user.html', 'user', '', '2', '10', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('166', '部门管理', '167', null, '/system/dept.html', '#', '', '2', '11', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('167', '系统', '0', null, null, '#', 'mdi-shield', '2', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('168', '财务', '0', null, null, '#', 'mdi-square-inc-cash', '1', '3', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('169', '会员', '0', null, null, '#', 'mdi-account-multiple', '1', '2', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('170', '查询', '165', null, null, 'system:user:view', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('171', '编辑', '165', null, null, 'system:user:edit', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('172', '删除', '165', null, null, 'system:user:del', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('173', '添加', '165', null, null, 'system:user:add', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('174', '查询', '166', null, null, 'system:dept:view', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('175', '编辑', '166', null, null, 'system:dept:edit', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('176', '删除', '166', null, null, 'system:dept:del', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('177', '添加', '166', null, null, 'system:dept:add', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('178', '菜单管理', '167', null, '/system/menu.html', '#', null, '2', '11', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('179', '查询', '178', null, null, 'system:menu:view', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('180', '编辑', '178', null, null, 'system:menu:edit', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('181', '删除', '178', null, null, 'system:menu:del', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('182', '添加', '178', null, null, 'system:menu:add', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('183', '角色管理', '167', null, '/system/role.html', '#', null, '2', '12', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('184', '查询', '183', null, null, 'system:role:view', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('185', '编辑', '183', null, null, 'system:role:edit', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('186', '删除', '183', null, null, 'system:role:del', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('187', '添加', '183', null, null, 'system:role:add', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('188', '权限管理', '167', null, '/system/roleMenu.html', '#', null, '2', '13', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('189', '查询', '188', null, null, 'system:roleMenu:view', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('190', '编辑', '188', null, null, 'system:roleMenu:edit', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('191', '删除', '188', null, null, 'system:roleMenu:del', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('192', '添加', '188', null, null, 'system:roleMenu:add', null, '3', '1', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('193', '日志管理', '167', null, '/system/log.html', '#', null, '2', '13', null, null, null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('194', '查询', '193', null, null, 'system:log:view', null, '3', '1', null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) DEFAULT NULL COMMENT '角色名称（中文名）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态（1:正常,2:删除）',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='系统-角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'instrt', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('2', 'update', null, null, null, null, null, '2');
INSERT INTO `sys_role` VALUES ('6', 'update', null, null, null, null, null, '2');
INSERT INTO `sys_role` VALUES ('7', 'update', null, '2020-10-31 14:25:00', '2020-10-31 14:25:00', null, null, '2');
INSERT INTO `sys_role` VALUES ('8', 'update', null, '2020-10-31 14:30:49', '2020-10-31 14:30:50', null, null, '2');
INSERT INTO `sys_role` VALUES ('9', 'update', null, '2020-10-31 14:32:12', null, null, null, '2');
INSERT INTO `sys_role` VALUES ('10', '发', null, '2020-11-03 15:15:02', '2020-11-03 15:51:08', '1', '1', '2');
INSERT INTO `sys_role` VALUES ('11', '角色12321', null, '2020-11-03 15:51:17', '2020-11-03 15:51:17', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统-角色菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '165');
INSERT INTO `sys_role_menu` VALUES ('1', '170');
INSERT INTO `sys_role_menu` VALUES ('11', '112');
INSERT INTO `sys_role_menu` VALUES ('11', '123');
INSERT INTO `sys_role_menu` VALUES ('11', '666');
INSERT INTO `sys_role_menu` VALUES ('123', '1054661322597744600');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `password` char(64) DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `picture` varchar(255) DEFAULT NULL COMMENT '头像',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别（1:男,2:女）',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态（1:正常,2:删除）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统-用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'ew', '超级管理员', '123456', '2', '1', '123', '1', '123@163.com', '13544672759', '超级管理员', '2020-10-31 19:14:55', '1', '2020-11-03 16:37:41', '1', '1');
