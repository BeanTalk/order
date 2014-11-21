# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.01 (MySQL 5.6.19)
# Database: jeesite
# Generation Time: 2014-11-20 14:54:25 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table st_cust_beans
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_cust_beans`;

CREATE TABLE `st_cust_beans` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cust_id` varchar(64) NOT NULL COMMENT '客户编号',
  `beans_num` int(11) NOT NULL COMMENT '豆豆数量',
  `flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='客户豆豆信息表';

LOCK TABLES `st_cust_beans` WRITE;
/*!40000 ALTER TABLE `st_cust_beans` DISABLE KEYS */;

INSERT INTO `st_cust_beans` (`id`, `cust_id`, `beans_num`, `flag`)
VALUES
	(3,'mine',200,NULL),
	(4,'mine',200,NULL),
	(5,'mine',200,NULL),
	(6,'mine',200,NULL),
	(7,'mine',200,NULL),
	(8,'mine',200,NULL),
	(9,'mine',200,NULL),
	(10,'mine',200,NULL),
	(11,'mine',200,NULL),
	(12,'mine',200,NULL),
	(13,'mine',200,NULL),
	(15,'mine',200,NULL),
	(16,'mine',200,NULL),
	(17,'mine',200,NULL);

/*!40000 ALTER TABLE `st_cust_beans` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_ext_supply
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_ext_supply`;

CREATE TABLE `st_ext_supply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sys_area
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_area`;

CREATE TABLE `sys_area` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '区域名称',
  `type` char(1) DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域表';

LOCK TABLES `sys_area` WRITE;
/*!40000 ALTER TABLE `sys_area` DISABLE KEYS */;

INSERT INTO `sys_area` (`id`, `parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','0','0,','100000','中国','1','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('2','1','0,1,','410000','辽宁省','2','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('3','2','0,1,2,','410411','大连市','3','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('4d22ee5105e746d7869ba798a052c97c','5991b7a880d9490ba4cdb4432479ea93','0,1,5991b7a880d9490ba4cdb4432479ea93,','320001','长春市','3','1','2014-11-05 22:19:00','1','2014-11-05 22:19:00','','0'),
	('5991b7a880d9490ba4cdb4432479ea93','1','0,1,','320000','吉林省','2','1','2014-11-05 22:18:39','1','2014-11-05 22:18:39','','0'),
	('6c1756ed74d94a00b0e814989eef1fd2','1','0,1,','380000','黑龙江省','2','1','2014-11-05 22:17:56','1','2014-11-05 22:17:56','','0'),
	('6f37ede0892947888378378427eb688a','6c1756ed74d94a00b0e814989eef1fd2','0,1,6c1756ed74d94a00b0e814989eef1fd2,','380001','哈尔滨市','3','1','2014-11-05 22:18:23','1','2014-11-05 22:18:23','','0');

/*!40000 ALTER TABLE `sys_area` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_dict
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;

INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `description`, `sort`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','正常','0','del_flag','删除标记',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('12','默认主题','default','theme','主题方案',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('17','国家','1','sys_area_type','区域类型',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('18','省份、直辖市','2','sys_area_type','区域类型',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('19','地市','3','sys_area_type','区域类型',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('2','删除','1','del_flag','删除标记',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('20','区县','4','sys_area_type','区域类型',40,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('21','公司','1','sys_office_type','机构类型',60,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('22','部门','2','sys_office_type','机构类型',70,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('23','高校','3','sys_office_type','机构类型',80,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('24','高校实验室','4','sys_office_type','机构类型',90,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('25','一级','1','sys_office_grade','机构等级',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('26','二级','2','sys_office_grade','机构等级',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('27','所有数据','1','sys_data_scope','数据范围',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('28','所在公司及以下数据','2','sys_data_scope','数据范围',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('29','所在公司数据','3','sys_data_scope','数据范围',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('3','显示','1','show_hide','显示/隐藏',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('30','所在部门及以下数据','4','sys_data_scope','数据范围',40,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('31','所在部门数据','5','sys_data_scope','数据范围',50,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('32','仅本人数据','8','sys_data_scope','数据范围',90,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('33','按明细设置','9','sys_data_scope','数据范围',100,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('34','系统管理','1','sys_user_type','用户类型',15,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('35','部门经理','2','sys_user_type','用户类型',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('36','普通用户','3','sys_user_type','用户类型',5,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('37','外部用户','1','sys_user_catagory','用户类别',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('38','内部用户','2','sys_user_catagory','用户类别',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('4','隐藏','0','show_hide','显示/隐藏',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('45','权限模块','1','module','模块类型',20,'1','2013-06-03 00:00:00','1','2013-05-27 00:00:00','','0'),
	('46','订单模块','2','module','模块类型',10,'1','2013-06-03 00:00:00','1','2013-05-27 00:00:00','','0'),
	('5','是','1','yes_no','是/否',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('53','咨询','1','cms_guestbook','留言板分类',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('54','建议','2','cms_guestbook','留言板分类',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('55','投诉','3','cms_guestbook','留言板分类',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('56','其它','4','cms_guestbook','留言板分类',40,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('6','否','0','yes_no','是/否',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('62','接入日志','1','sys_log_type','日志类型',30,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0'),
	('63','异常日志','2','sys_log_type','日志类型',40,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0'),
	('64','菜单属性','1','sys_menu_type','菜单属性',10,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0'),
	('65','安全属性','2','sys_menu_type','安全属性',20,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0'),
	('67','学生','1','sys_role_sign','特定角色属性',10,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','student','0'),
	('68','导师','2','sys_role_sign','特定角色属性',20,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','professor','0'),
	('69','PI','3','sys_role_sign','特定角色属性',30,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','pi','0'),
	('70','采购','4','sys_role_sign','特定角色属性',40,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','caigou','0'),
	('71','销售','5','sys_role_sign','特定角色属性',50,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','sale','0'),
	('72','财务','6','sys_role_sign','特定角色属性',60,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','caiwu','0'),
	('73','公司级管理员','7','sys_role_sign','特定角色属性',70,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','admin','0'),
	('74','系统管理员','8','sys_role_sign','特定角色属性',80,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','sysadmin','0'),
	('75','内勤','9','sys_role_sign','特定角色属性',90,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','neiqin','0');

/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_dict_bak
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dict_bak`;

CREATE TABLE `sys_dict_bak` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_dict_bak` WRITE;
/*!40000 ALTER TABLE `sys_dict_bak` DISABLE KEYS */;

INSERT INTO `sys_dict_bak` (`id`, `label`, `value`, `type`, `description`, `sort`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','正常','0','del_flag','删除标记',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('12','默认主题','default','theme','主题方案',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('17','国家','1','sys_area_type','区域类型',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('18','省份、直辖市','2','sys_area_type','区域类型',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('19','地市','3','sys_area_type','区域类型',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('2','删除','1','del_flag','删除标记',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('20','区县','4','sys_area_type','区域类型',40,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('21','公司','1','sys_office_type','机构类型',60,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('22','部门','2','sys_office_type','机构类型',70,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('23','一级','1','sys_office_grade','机构等级',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('24','二级','2','sys_office_grade','机构等级',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('27','所有数据','1','sys_data_scope','数据范围',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('28','所在公司及以下数据','2','sys_data_scope','数据范围',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('29','所在公司数据','3','sys_data_scope','数据范围',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('3','显示','1','show_hide','显示/隐藏',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('30','所在部门及以下数据','4','sys_data_scope','数据范围',40,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('31','所在部门数据','5','sys_data_scope','数据范围',50,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('32','仅本人数据','8','sys_data_scope','数据范围',90,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('33','按明细设置','9','sys_data_scope','数据范围',100,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('34','系统管理','1','sys_user_type','用户类型',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('35','部门经理','2','sys_user_type','用户类型',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('36','普通用户','3','sys_user_type','用户类型',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('4','隐藏','0','show_hide','显示/隐藏',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('48','发布','0','cms_del_flag','内容状态',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','1'),
	('49','删除','1','cms_del_flag','内容状态',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','1'),
	('5','是','1','yes_no','是/否',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('50','审核','2','cms_del_flag','内容状态',15,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','1'),
	('53','咨询','1','cms_guestbook','留言板分类',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('54','建议','2','cms_guestbook','留言板分类',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('55','投诉','3','cms_guestbook','留言板分类',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('56','其它','4','cms_guestbook','留言板分类',40,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('6','否','0','yes_no','是/否',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('62','接入日志','1','sys_log_type','日志类型',30,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0'),
	('63','异常日志','2','sys_log_type','日志类型',40,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0');

/*!40000 ALTER TABLE `sys_dict_bak` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;

INSERT INTO `sys_log` (`id`, `type`, `create_by`, `create_date`, `remote_addr`, `user_agent`, `request_uri`, `method`, `params`, `exception`)
VALUES
	('02a1e0303280498693a82db3c62026f5','1','1','2014-11-05 22:28:07','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=320000001&type=2&master=&id=96ad01f9140b47f6b5c5d638a3f4128b&area.name=中国&address=&email=&parent.id=1b7d1aeea9cd4711851ec65199be2aa2&name=采购部&zipCode=&grade=2&parent.name=塞托长春分公司',''),
	('05bcec0a9f9144c1bc7bfa9c34223304','1','1','2014-11-10 19:25:02','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/talk/sys/user/save','POST','phone=212&oldEmail=&oldLoginName=&no=2222222&newPassword=&roleIdList=6&remarks=2121&userCatagory=1&office.id=1&company.name=塞托中国联盟总公司&userType=3&id=&confirmNewPassword=&_roleIdList=on&email=122222@13.com&office.name=塞托中国联盟总公司&company.id=1&name=2222222&loginName=2222222&mobile=21',''),
	('092d6297677843ee98d894ff2421444c','1','1','2014-11-05 22:28:21','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=3200000001&type=2&master=&id=96ad01f9140b47f6b5c5d638a3f4128b&area.name=中国&address=&email=&parent.id=1b7d1aeea9cd4711851ec65199be2aa2&name=采购部&zipCode=&grade=2&parent.name=塞托长春分公司',''),
	('13032e7d862340ce8df22438521c38c2','1','1','2014-11-13 20:21:59','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/save','POST','icon=&module=2&sort=10&permission=perms[address:list]&type=1&id=e374c25d0d704b49b62c0189e81083a0&parent.id=86cb3c878ba0429ab562e0caaafda85e&name=地址管理&target=&href=/order/address/manage/**&parent.name=订单系统&isShow=1',''),
	('142641f0bea34e9f95db389c781916de','1','1','2014-11-14 13:03:20','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/role/save','POST','id=1&menuIds=1,86cb3c878ba0429ab562e0caaafda85e,e374c25d0d704b49b62c0189e81083a0,7f856aa488324ed9a7a146a6b10d8...&dataScope=1&office.name=塞托中国联盟总公司&name=系统管理员&officeIds=&office.id=1&oldName=系统管理员',''),
	('14dc03e85277469394875eefac2928eb','1','1','2014-11-10 19:24:29','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/talk/sys/user/save','POST','phone=ds&oldEmail=&newPassword=&no=111&oldLoginName=&roleIdList=3&remarks=dsds&userCatagory=1&office.id=1&company.name=塞托中国联盟总公司&userType=2&confirmNewPassword=&id=&_roleIdList=on&email=121@163.om&office.name=塞托中国联盟总公司&name=2222&company.id=1&loginName=21222&mobile=dsds',''),
	('151b485544694d7ba3662a76dda1c2f7','1','1','2014-11-05 22:26:48','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=320000001&type=1&master=&id=&area.name=中国&address=&email=&parent.id=1&name=塞托长春分公司&zipCode=&grade=1&parent.name=塞托中国联盟总公司',''),
	('1c61ec88b3504068a0bef4ce2fa99aec','1','1','2014-11-06 00:58:57','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/dict/delete','GET','id=50',''),
	('1c9d339cb3314f9bb16d5097c9e98c7d','1','1','2014-11-05 22:24:41','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=3&remarks=&code=410000002&type=2&master=&id=&area.name=大连市&address=&email=&parent.id=db8bf7e94ab1413899a7430f4c9fc859&name=销售部&zipCode=&grade=1&parent.name=塞托大连分公司',''),
	('1d69e0013ce848cbbda32bd2be93e018','1','1','2014-11-13 20:30:08','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/save','POST','icon=&module=2&sort=30&permission=perms[order:list:wait]&type=1&id=&parent.id=597a2d1b78fb45179cc79304db65db8b&name=待处理订单&target=&href=/order/list/wait/*&parent.name=我的订单&isShow=1',''),
	('1eedcc37162145dfb1f7e5b452ed3397','1','1','2014-11-05 22:24:18','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=3&remarks=&code=410000001&type=2&master=&id=&area.name=大连市&address=&email=&parent.id=db8bf7e94ab1413899a7430f4c9fc859&name=采购部&zipCode=&grade=2&parent.name=塞托大连分公司 ',''),
	('1f5b92f0b506445db24de1b482e07cdd','1','1','2014-11-13 20:21:41','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/save','POST','icon=&module=2&sort=1&permission=&type=2&id=86cb3c878ba0429ab562e0caaafda85e&parent.id=1&name=订单系统&target=&href=/order/**&parent.name=顶级菜单&isShow=1',''),
	('23990ded87f9408c90779ea4d9134757','1','1','2014-11-06 00:58:59','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/dict/delete','GET','id=49',''),
	('240a5dd5f4314a42808b4e5a67892cfb','2','1','2014-11-06 00:18:11','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/jeesite/a','GET','','org.apache.jasper.JasperException: /WEB-INF/views/modules/sys/sysIndex.jsp(46,67) PWC6297: The function getSite cannot be located with the specified prefix'),
	('265bb49fba1a4eaeaba636d4197fac5c','1','1','2014-11-05 22:19:00','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/save','POST','id=&parent.id=5991b7a880d9490ba4cdb4432479ea93&name=长春市&remarks=&code=320001&type=3&parent.name=吉林省',''),
	('29d35743047f42709193840d62a8661a','1','1','2014-11-05 22:27:51','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=3800000001&type=2&master=&id=d45717683fc7409ba28dcd6985746b87&area.name=中国&address=&email=&parent.id=38405be8fea04945987bd14647f99da5&name=采购部&zipCode=&grade=2&parent.name=塞托黑龙江分公司',''),
	('2fc71d7080fb46d7b3fe840fe85190c8','1','1','2014-11-07 09:57:03','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/talk/sys/user/save','POST','phone=&newPassword=&no=dsds&oldLoginName=&roleIdList=2&remarks=&office.id=1&company.name=塞托中国联盟总公司&userType=2&confirmNewPassword=&id=&_roleIdList=on&email=&office.name=塞托中国联盟总公司&name=ds&company.id=1&loginName=dsds&mobile=',''),
	('303919ea027a46fe90489330966c7093','2','2e4f10ad67dd41ee9a507667cd789285','2014-11-05 23:55:28','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/form','GET','id=5991b7a880d9490ba4cdb4432479ea93','org.springframework.web.util.NestedServletException: Handler processing failed; nested exception is java.lang.OutOfMemoryError: PermGen space'),
	('41a20d987f9347a9b87122d5b539b735','1','1','2014-11-05 23:52:26','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/user/save','POST','phone=&oldLoginName=cc&no=000002&newPassword=&roleIdList=2&remarks=&office.id=96ad01f9140b47f6b5c5d638a3f4128b&company.name=塞托长春分公司&userType=2&id=c6a29d4b98544b2194e0dfdb1a44db06&confirmNewPassword=&_roleIdList=on&email=&office.name=采购部&company.id=1b7d1aeea9cd4711851ec65199be2aa2&name=长春塞托管理人&loginName=cc_admin&mobile=',''),
	('4ac858e9d5ff47eda71196ce3f22a61a','1','1','2014-11-15 23:58:54','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/user/save','POST','phone=1212121&oldEmail=&newPassword=&no=212&oldLoginName=&roleIdList=2&remarks=2121&userCatagory=2&office.id=1&company.name=塞托中国联盟总公司&userType=3&confirmNewPassword=&id=&_roleIdList=on&email=11333@163323.com&office.name=塞托中国联盟总公司&name=121&company.id=1&qq=18101691&loginName=11&mobile=2121',''),
	('4d763cb345344e8fba300523f78bc7f1','1','1','2014-11-06 00:58:55','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/dict/delete','GET','id=48',''),
	('54963c099e234d7f995cd1ac1377b5ce','1','1','2014-11-05 22:18:23','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/save','POST','id=&parent.id=6c1756ed74d94a00b0e814989eef1fd2&name=哈尔滨市&remarks=&code=380001&type=3&parent.name=黑龙江省',''),
	('5502e608e0c64ee08ae1de912e389a6f','1','1','2014-11-06 01:55:58','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/a/sys/menu/updateSort','POST','sorts=100&ids=27',''),
	('573c698313df42ecb2708100fa351b50','1','1','2014-11-13 20:31:21','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/save','POST','icon=thumbs-up&module=2&sort=30&permission=perms[order:orderlist:all]&type=1&id=597a2d1b78fb45179cc79304db65db8b&parent.id=86cb3c878ba0429ab562e0caaafda85e&name=我的订单&target=&href=&parent.name=订单系统&isShow=1',''),
	('592b99e43311476ba69c6c55391af2cc','1','1','2014-11-05 22:27:32','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=320000001&type=2&master=&id=96ad01f9140b47f6b5c5d638a3f4128b&area.name=中国&address=&email=&parent.id=1b7d1aeea9cd4711851ec65199be2aa2&name=采购部&zipCode=&grade=2&parent.name=塞托长春分公司',''),
	('5d42ce723c454dc2965a14034621c321','1','1','2014-11-10 23:08:20','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/talk/sys/user/save','POST','phone=1121&oldEmail=&newPassword=&no=1999&oldLoginName=&roleIdList=2&remarks=2121&userCatagory=1&office.id=1&company.name=塞托中国联盟总公司&userType=3&confirmNewPassword=&id=&_roleIdList=on&email=11@163.com&office.name=塞托中国联盟总公司&name=1999&company.id=1&loginName=1999&mobile=2121',''),
	('5f2392db41194018a7443a6ee822fcf3','1','1','2014-11-05 23:51:26','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/user/save','POST','phone=&newPassword=&no=000002&oldLoginName=&roleIdList=3&remarks=&office.id=db8bf7e94ab1413899a7430f4c9fc859&company.name=塞托大连分公司 &userType=2&confirmNewPassword=&id=&_roleIdList=on&email=&office.name=塞托大连分公司 &name=大连塞托管理人&company.id=db8bf7e94ab1413899a7430f4c9fc859&loginName=dl_admin&mobile=',''),
	('5f2d6e4dbfbe4cf2a16ed41d9e77c8de','2','2e4f10ad67dd41ee9a507667cd789285','2014-11-05 23:55:42','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/form','GET','id=2','org.springframework.web.util.NestedServletException: Handler processing failed; nested exception is java.lang.OutOfMemoryError: PermGen space'),
	('681d6eea5cc34c3da04efae96adb1633','1','1','2014-11-05 22:25:37','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=38000001&type=1&master=&id=&area.name=中国&address=&email=&parent.id=1&name=塞托黑龙江分公司&zipCode=&grade=1&parent.name=塞托中国联盟总公司',''),
	('6baff1c4885d4e43bb7f749105765e0a','2','2e4f10ad67dd41ee9a507667cd789285','2014-11-05 23:55:31','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/form','GET','parent.id=5991b7a880d9490ba4cdb4432479ea93','org.springframework.web.util.NestedServletException: Handler processing failed; nested exception is java.lang.OutOfMemoryError: PermGen space'),
	('6d938a75ed4e44eb8f0c20750ef0f4d6','2','2e4f10ad67dd41ee9a507667cd789285','2014-11-05 23:56:55','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/form','GET','','org.springframework.web.util.NestedServletException: Handler processing failed; nested exception is java.lang.OutOfMemoryError: PermGen space'),
	('6f48571df88f4937bd644d2924189b0f','2','1','2014-11-05 22:22:07','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/cms/category/form','GET','','java.lang.NullPointerException'),
	('7fafe4fd04d9425d911c61f12b30fbc0','1','1','2014-11-05 22:28:03','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=380000002&type=2&master=&id=76292c5fb7674d1597d3665d46105e43&area.name=中国&address=&email=&parent.id=38405be8fea04945987bd14647f99da5&name=客服部&zipCode=&grade=2&parent.name=塞托黑龙江分公司',''),
	('87b710119f794f19a433b02d280b3093','1','1','2014-11-05 22:27:58','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=380000001&type=2&master=&id=d45717683fc7409ba28dcd6985746b87&area.name=中国&address=&email=&parent.id=38405be8fea04945987bd14647f99da5&name=采购部&zipCode=&grade=2&parent.name=塞托黑龙江分公司',''),
	('8ba8be66557b412b9e369af5b21ae630','1','1','2014-11-10 08:59:30','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/talk/sys/menu/save','POST','id=&icon=&sort=30&parent.id=32&name=添加地址&target=&permission=perms[address:add]&href=/order/address/add&parent.name=地址管理&isShow=1',''),
	('8e3f0279effa4f3f896d80c98e6dd847','1','1','2014-11-05 22:17:56','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/save','POST','id=&parent.id=1&name=黑龙江省&remarks=&code=380000&type=2&parent.name=中国',''),
	('930a97a27ecd4cd9b1a8f8e5e5275781','1','1','2014-11-13 20:20:55','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/updateSort','POST','sorts=1&ids=86cb3c878ba0429ab562e0caaafda85e',''),
	('97f8fba0dccc4b51bdce3dbbd7a9c9a6','1','1','2014-11-17 23:48:52','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/role/save','POST','id=1&menuIds=,1,111,1112,1111,1113,114,1141,27,28,29,30,112,1121,113,1131,1132,2,13,20,21,22,17,18,19,14,15,16...&dataScope=1&roleSign=1&office.name=塞托中国联盟总公司&name=系统管理员&officeIds=&office.id=1&oldName=系统管理员',''),
	('98d3c61b321b42f5b0b92188c65cc523','2','2e4f10ad67dd41ee9a507667cd789285','2014-11-05 23:56:54','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/form','GET','','org.springframework.web.util.NestedServletException: Handler processing failed; nested exception is java.lang.OutOfMemoryError: PermGen space'),
	('99f6c772e25245bbbff5b61f5a4d7487','2','2e4f10ad67dd41ee9a507667cd789285','2014-11-05 23:55:32','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/form','GET','parent.id=2','org.springframework.web.util.NestedServletException: Handler processing failed; nested exception is java.lang.OutOfMemoryError: PermGen space'),
	('9aa50399379a4482beecd6a14a7cb1d1','1','1','2014-11-05 22:23:16','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=3&remarks=&code=41000001&type=1&master=&id=&area.name=大连市 &address=&email=&zipCode=&name=塞托大连分公司&parent.id=1&grade=1&parent.name=塞托中国联盟总公司 ',''),
	('9d24255b12c64d7fb3824abb53fb8c1d','1','1','2014-11-14 20:48:33','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/office/save','POST','phone=&fax=&area.id=4d22ee5105e746d7869ba798a052c97c&remarks=&code=3200000001&type=2&master=&id=96ad01f9140b47f6b5c5d638a3f4128b&area.name=长春市 &address=&email=&zipCode=&name=采购部&parent.id=1b7d1aeea9cd4711851ec65199be2aa2&grade=2&parent.name=塞托长春分公司',''),
	('9fa6ad3861974b008ec96bda12db65a2','1','1','2014-11-13 20:30:29','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/updateSort','POST','sorts=1&ids=86cb3c878ba0429ab562e0caaafda85e',''),
	('9fccea6118994720b1608f3b1fcb8d65','1','1','2014-11-13 20:28:00','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/save','POST','icon=thumbs-up&module=2&sort=30&permission=perms[order:list:all]&type=1&id=&parent.id=86cb3c878ba0429ab562e0caaafda85e&name=我的订单&target=&href=/order/list/all/*&parent.name=订单系统&isShow=1',''),
	('a0f8279a666e427486576297d68e18d1','1','1','2014-11-06 15:39:47','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/saituo/a/sys/office/save','POST','phone=1&fax=&area.id=4d22ee5105e746d7869ba798a052c97c&remarks=&code=dsds&type=2&master=121&id=&area.name=长春市 &address=121&email=&zipCode=2121&name=dsds&parent.id=1&grade=1&parent.name=塞托中国联盟总公司',''),
	('a1174fd814ab4472a9551fa2e0aa6a16','1','1','2014-11-13 20:30:41','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/updateSort','POST','sorts=1&ids=86cb3c878ba0429ab562e0caaafda85e',''),
	('a38defe2c9244b9b9c54074cd2db6be1','2','2e4f10ad67dd41ee9a507667cd789285','2014-11-05 23:55:33','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/form','GET','parent.id=3','org.apache.jasper.JasperException: PWC6345: There is an error in invoking javac.  A full JDK (not just JRE) is required'),
	('a84863c066af4cd3a4fdfd9c164008cb','1','1','2014-11-10 15:37:34','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/talk/sys/user/save','POST','phone=32&oldEmail=&newPassword=&no=3232&oldLoginName=&roleIdList=2&remarks=&userCatagory=1&office.id=96ad01f9140b47f6b5c5d638a3f4128b&company.name=塞托中国联盟总公司&userType=3&confirmNewPassword=&id=&_roleIdList=on&email=32121@163.com&office.name=采购部 &name=23&company.id=1&loginName=232&mobile=32',''),
	('a9b19d66f6424ef99502e67008ac758f','1','1','2014-11-10 09:10:51','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/talk/sys/menu/save','POST','id=&icon=&module=2&sort=30&parent.id=e374c25d0d704b49b62c0189e81083a0&name=地址添加&target=&permission=perms[address:add]&href=&parent.name=地址管理&isShow=1',''),
	('b15354e861aa4b2d898b535c6effe177','1','1','2014-11-05 22:18:39','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/save','POST','id=&parent.id=1&name=吉林省&remarks=&code=320000&type=2&parent.name=中国',''),
	('b6891305d6a248d9bbce73de2d52a3f4','1','1','2014-11-05 22:26:25','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=3800000002&type=2&master=&id=&area.name=中国&address=&email=&parent.id=38405be8fea04945987bd14647f99da5&name=客服部&zipCode=&grade=2&parent.name=塞托黑龙江分公司',''),
	('b8ce03117d2a400bb47464205effb4c2','1','1','2014-11-13 20:29:09','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/save','POST','icon=&module=2&sort=30&permission=perms[order:list:all]&type=1&id=&parent.id=597a2d1b78fb45179cc79304db65db8b&name=所有订单&target=&href=/order/list/all/*&parent.name=我的订单&isShow=1',''),
	('c1076ba353c14acdb0a3f8f46400e530','1','1','2014-11-14 20:39:04','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/role/save','POST','id=&menuIds=1,2,13,20,21,22,17,18,19,14,15,16&dataScope=5&office.name=塞托中国联盟总公司&name=大连采购&officeIds=&office.id=1&oldName=',''),
	('c15bdf49ae484365ad2f740bdde89521','1','1','2014-11-05 23:52:13','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/user/save','POST','phone=&oldLoginName=&no=000002&newPassword=&roleIdList=2&remarks=&office.id=96ad01f9140b47f6b5c5d638a3f4128b&company.name=塞托长春分公司 &userType=2&id=&confirmNewPassword=&_roleIdList=on&email=&office.name=采购部 &company.id=1b7d1aeea9cd4711851ec65199be2aa2&name=长春塞托管理人&loginName=cc&mobile=',''),
	('c568706a93b647949bc11cc973345327','1','1','2014-11-10 09:10:11','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/talk/sys/menu/save','POST','id=&icon=&module=2&sort=30&parent.id=1&name=订单系统&target=&permission=&href=/order/address&parent.name=顶级菜单&isShow=1',''),
	('c6f07003e740497a810b90af86bdf4f3','2','2e4f10ad67dd41ee9a507667cd789285','2014-11-05 23:55:52','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/form','GET','id=6f37ede0892947888378378427eb688a','org.springframework.web.util.NestedServletException: Handler processing failed; nested exception is java.lang.OutOfMemoryError: PermGen space'),
	('d2063861b40f478fb806a5e654d89c17','1','1','2014-11-13 20:22:18','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/menu/save','POST','icon=&module=2&sort=110&permission=perms[order:address:add],authc&type=2&id=7f856aa488324ed9a7a146a6b10d8847&parent.id=e374c25d0d704b49b62c0189e81083a0&name=地址添加&target=&href=/order/address/add/**&parent.name=地址管理&isShow=1',''),
	('d847c81f6f4d4c1fbc56c5bfcc159800','2','1','2014-11-06 00:14:50','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/jeesite/a','GET','','org.apache.jasper.JasperException: PWC6299: The class com.thinkgem.jeesite.modules.cms.utils.CmsUtils specified in TLD for the function fnc:getSite cannot be found: com.thinkgem.jeesite.modules.cms.utils.CmsUtils'),
	('db92e94825c04c77b5915043395a3dec','1','1','2014-11-10 09:10:25','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/saituo/talk/sys/menu/save','POST','id=&icon=&module=2&sort=30&parent.id=86cb3c878ba0429ab562e0caaafda85e&name=地址管理&target=&permission=perms[address:list]&href=/order/address&parent.name=订单系统&isShow=1',''),
	('e3889941c3534a39bed8a12924cda118','1','1','2014-11-05 22:25:57','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=380000001&type=2&master=&id=&area.name=中国&address=&email=&parent.id=38405be8fea04945987bd14647f99da5&name=采购部&zipCode=&grade=2&parent.name=塞托黑龙江分公司',''),
	('e4859d52cbcc48d6838683cc25ff3afa','2','1','2014-11-16 00:00:54','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/user/form','GET','','org.apache.jasper.JasperException: org.springframework.beans.NotReadablePropertyException: Invalid property \'forward\' of bean class [com.saituo.talk.modules.sys.entity.User]: Bean property \'forward\' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?'),
	('e72f9c599f574c2d9269c949bcec36d4','1','1','2014-11-05 22:24:50','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=3&remarks=&code=410000002&type=2&master=&id=d307c6d30e1a466ea75dc3f66160936e&area.name=大连市&address=&email=&parent.id=db8bf7e94ab1413899a7430f4c9fc859&name=销售部&zipCode=&grade=2&parent.name=塞托大连分公司',''),
	('ea9eff0dcd73446a84ebd92e4c211f4d','1','1','2014-11-05 23:54:27','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/user/save','POST','phone=&oldLoginName=&no=000003&newPassword=&roleIdList=3&remarks=&office.id=4a8ec405cb8e4e5f81571b46cb7f534f&company.name=塞托大连分公司 &userType=2&id=&confirmNewPassword=&_roleIdList=on&email=&office.name=采购部 &company.id=db8bf7e94ab1413899a7430f4c9fc859&name=大连采购组&loginName=dl_cg&mobile=',''),
	('ed5e24a491fe4795ace64683c659e9b1','1','1','2014-11-05 22:27:38','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=3200000001&type=2&master=&id=96ad01f9140b47f6b5c5d638a3f4128b&area.name=中国&address=&email=&parent.id=1b7d1aeea9cd4711851ec65199be2aa2&name=采购部&zipCode=&grade=2&parent.name=塞托长春分公司',''),
	('ee5ffde9770f4487bd28075189d52713','2','2e4f10ad67dd41ee9a507667cd789285','2014-11-05 23:55:29','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/area/form','GET','id=5991b7a880d9490ba4cdb4432479ea93','org.springframework.web.util.NestedServletException: Handler processing failed; nested exception is java.lang.OutOfMemoryError: PermGen space'),
	('eff992351e9847d4a795f17e8607c9a9','2','1','2014-11-05 22:22:16','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/cms/template/tree','GET','','java.lang.NullPointerException'),
	('f18049dd2532408f91a99443eac2e759','1','1','2014-11-05 22:27:18','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10','/jeesite/a/sys/office/save','POST','phone=&fax=&area.id=1&remarks=&code=&type=2&master=&id=&area.name=中国&address=&email=&parent.id=1b7d1aeea9cd4711851ec65199be2aa2&name=采购部&zipCode=&grade=2&parent.name=塞托长春分公司 ',''),
	('fa8873e72e46400587224d2f076c277e','1','1','2014-11-16 00:01:55','0:0:0:0:0:0:0:1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:33.0) Gecko/20100101 Firefox/33.0','/core/talk/sys/user/save','POST','phone=1&oldEmail=&newPassword=&no=23232&roleIdList=6&remarks=323&userCatagory=1&userType=3&confirmNewPassword=&id=&forward=2121&name=223&company.id=1&qq=2121&loginName=3232&oldLoginName=&office.id=1&company.name=塞托中国联盟总公司&_roleIdList=on&email=12232@173.com&office.name=塞托中国联盟总公司&mobile=2121','');

/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_mdict
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_mdict`;

CREATE TABLE `sys_mdict` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `sort` int(11) DEFAULT NULL COMMENT '排序（升序）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_mdict_parent_id` (`parent_id`),
  KEY `sys_mdict_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多级字典表';



# Dump of table sys_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `module` varchar(64) DEFAULT NULL COMMENT '所属模块',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `href` varchar(255) DEFAULT NULL COMMENT '链接',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `is_show` char(1) NOT NULL COMMENT '是否在菜单中显示',
  `type` int(11) DEFAULT NULL COMMENT '菜单类型:1菜单;2安全',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`),
  KEY `sys_menu_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;

INSERT INTO `sys_menu` (`id`, `parent_id`, `parent_ids`, `module`, `name`, `href`, `target`, `icon`, `sort`, `is_show`, `type`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('','','',NULL,'',NULL,NULL,NULL,0,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),
	('1','0','0,','1','顶级菜单','','','',0,'1',NULL,'','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('10','3','0,1,2,3,','1','字典管理','/sys/dict/','','th-list',60,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('11','10','0,1,2,3,10,','1','查看','','','',30,'0',NULL,'sys:dict:view','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('111','1','0,1,','2','我的订单','','','thumbs-up',0,'1',1,'perms[order:orderlist:all]','1','2014-11-13 20:28:00','1','2014-11-13 20:31:21',NULL,'0'),
	('1111','111','0,1,111,','2','待处理订单','/order/list/waitprocess_order/**','','',3,'1',1,'perms[order:list:wait]','1','2014-11-13 20:30:08','1','2014-11-13 20:31:21',NULL,'0'),
	('1112','111','0,1,111,','2','所有订单','/order/list/all_order/**','','',1,'1',1,'perms[order:list:all]','1','2014-11-13 20:29:09','1','2014-11-13 20:31:21',NULL,'0'),
	('1113','111','0,1,111,','2','待审批订单','/ordr/list/waitapprove_order/**','','',30,'1',1,'perms[order:list:approve]','1','2014-11-13 20:30:08','1','2014-11-13 20:31:21',NULL,'0'),
	('112','1','0,1,','2','地址管理','','','',111,'1',1,'perms[address:list]','1','2014-11-10 09:10:25','1','2014-11-13 20:30:41',NULL,'0'),
	('1121','112','0,1,112,','2','地址添加','/order/address/add/**','','',110,'1',2,'perms[order:address:add],authc','1','2014-11-10 09:10:51','1','2014-11-13 20:30:41',NULL,'0'),
	('113','1','0,1,','2','我的资产','','','',1,'1',1,'perms[order:mine:all]','1','2014-11-13 20:28:00','1','2014-11-13 20:31:21',NULL,'0'),
	('1131','113','0,1,113,','2','我的豆豆','/order/mine/bean/**','','',2,'1',1,'perms[order:mine:bean]','1','2014-11-13 20:29:09','1','2014-11-13 20:29:09',NULL,'0'),
	('1132','113','0,1,113,','2','我的积分','/order/mine/credits/**','','',2,'1',1,'perms[order:mine: credits]','1','2014-11-13 20:29:09','1','2014-11-13 20:29:09',NULL,'0'),
	('114','1','0,1,','2','我的服务','','',NULL,0,'1',1,'perms[order:service:all]','1','2014-11-13 20:28:00','1','2014-11-13 20:31:21',NULL,'0'),
	('1141','114','0,1,114,','2','我的投诉','/order/service/complain/**','','',1,'1',1,'perms[order:service:complain]','1','2014-11-13 20:29:09','1','2014-11-13 20:31:21',NULL,'0'),
	('12','10','0,1,2,3,10,','1','修改','','','',30,'0',NULL,'sys:dict:edit','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('13','2','0,1,2,','1','机构用户','','','',970,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('14','13','0,1,2,13,','1','区域管理','/sys/area/','','th',50,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('15','14','0,1,2,13,14,','1','查看','','','',30,'0',NULL,'sys:area:view','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('16','14','0,1,2,13,14,','1','修改','','','',30,'0',NULL,'sys:area:edit','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('17','13','0,1,2,13,','1','机构管理','/sys/office/','','th-large',40,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('18','17','0,1,2,13,17,','1','查看','','','',30,'0',NULL,'sys:office:view','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('19','17','0,1,2,13,17,','1','修改','','','',30,'0',NULL,'sys:office:edit','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('2','1','0,1,','1','系统设置','','','',900,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('20','13','0,1,2,13,','1','用户管理','/sys/user/','','user',30,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('21','20','0,1,2,13,20,','1','查看','','','',30,'0',NULL,'sys:user:view','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('22','20','0,1,2,13,20,','1','修改','','','',30,'0',NULL,'sys:user:edit','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('27','1','0,1,','1','我的面板','','','',100,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('28','27','0,1,27,','1','个人信息','','','',990,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('29','28','0,1,27,28,','1','个人信息','/sys/user/info','','user',30,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('3','2','0,1,2,','1','系统设置','','','',980,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('30','28','0,1,27,28,','1','修改密码','/sys/user/modifyPwd','','lock',40,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('4','3','0,1,2,3,','1','菜单管理','/sys/menu/','','list-alt',30,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('5','4','0,1,2,3,4,','1','查看','','','',30,'0',NULL,'sys:menu:view','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('6','4','0,1,2,3,4,','1','修改','','','',30,'0',NULL,'sys:menu:edit','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('67','2','0,1,2,','1','日志查询','','','',985,'1',NULL,'','1','2013-06-03 00:00:00','1','2014-11-13 20:30:41','','0'),
	('68','67','0,1,2,67,','1','日志查询','/sys/log','','pencil',30,'1',NULL,'sys:log:view','1','2013-06-03 00:00:00','1','2014-11-13 20:30:41','','0'),
	('7','3','0,1,2,3,','1','角色管理','/sys/role/','','lock',50,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('8','7','0,1,2,3,7,','1','查看','','','',30,'0',NULL,'sys:role:view','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0'),
	('9','7','0,1,2,3,7,','1','修改','','','',30,'0',NULL,'sys:role:edit','1','2013-05-27 00:00:00','1','2014-11-13 20:30:41','','0');

/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_office
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_office`;

CREATE TABLE `sys_office` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `area_id` varchar(64) NOT NULL COMMENT '归属区域',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `type` char(1) NOT NULL COMMENT '机构类型',
  `grade` char(1) NOT NULL COMMENT '机构等级',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

LOCK TABLES `sys_office` WRITE;
/*!40000 ALTER TABLE `sys_office` DISABLE KEYS */;

INSERT INTO `sys_office` (`id`, `parent_id`, `parent_ids`, `area_id`, `code`, `name`, `type`, `grade`, `address`, `zip_code`, `master`, `phone`, `fax`, `email`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','0','0,','1','100000','塞托中国联盟总公司','1','1','','','','','','','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('1b7d1aeea9cd4711851ec65199be2aa2','1','0,1,','1','320000001','塞托长春分公司','1','1','','','','','','','1','2014-11-05 22:26:48','1','2014-11-05 22:26:48','','0'),
	('38405be8fea04945987bd14647f99da5','1','0,1,','1','38000001','塞托黑龙江分公司','1','1','','','','','','','1','2014-11-05 22:25:37','1','2014-11-05 22:25:37','','0'),
	('4a8ec405cb8e4e5f81571b46cb7f534f','db8bf7e94ab1413899a7430f4c9fc859','0,1,db8bf7e94ab1413899a7430f4c9fc859,','3','410000001','采购部','2','2','','','','','','','1','2014-11-05 22:24:18','1','2014-11-05 22:24:18','','0'),
	('76292c5fb7674d1597d3665d46105e43','38405be8fea04945987bd14647f99da5','0,1,38405be8fea04945987bd14647f99da5,','1','380000002','客服部','2','2','','','','','','','1','2014-11-05 22:26:25','1','2014-11-05 22:28:03','','0'),
	('96ad01f9140b47f6b5c5d638a3f4128b','1b7d1aeea9cd4711851ec65199be2aa2','0,1,1b7d1aeea9cd4711851ec65199be2aa2,','4d22ee5105e746d7869ba798a052c97c','3200000001','采购部','2','2','','','','','','','1','2014-11-05 22:27:18','1','2014-11-14 20:48:33','','0'),
	('d307c6d30e1a466ea75dc3f66160936e','db8bf7e94ab1413899a7430f4c9fc859','0,1,db8bf7e94ab1413899a7430f4c9fc859,','3','410000002','销售部','2','2','','','','','','','1','2014-11-05 22:24:41','1','2014-11-05 22:24:50','','0'),
	('d45717683fc7409ba28dcd6985746b87','38405be8fea04945987bd14647f99da5','0,1,38405be8fea04945987bd14647f99da5,','1','380000001','采购部','2','2','','','','','','','1','2014-11-05 22:25:57','1','2014-11-05 22:27:58','','0'),
	('db8bf7e94ab1413899a7430f4c9fc859','1','0,1,','3','41000001','塞托大连分公司','1','1','','','','','','','1','2014-11-05 22:23:16','1','2014-11-05 22:23:16','','0'),
	('de36a169a84742248d8f884d33d9baad','1','0,1,','4d22ee5105e746d7869ba798a052c97c','dsds','dsds','2','1','121','2121','121','1','','','1','2014-11-06 15:39:47','1','2014-11-06 15:39:47','','1');

/*!40000 ALTER TABLE `sys_office` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `office_id` varchar(64) DEFAULT NULL COMMENT '归属机构',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_sign` varchar(32) DEFAULT NULL COMMENT '角色标识,只能用于后端程序使用',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;

INSERT INTO `sys_role` (`id`, `office_id`, `name`, `role_sign`, `data_scope`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','1','系统管理员','1','1','1','2013-05-27 00:00:00','1','2014-11-17 23:48:51','','0'),
	('2','1','公司管理员',NULL,'2','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('3','1','本公司管理员',NULL,'3','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('4','1','部门管理员',NULL,'4','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('5','1','本部门管理员',NULL,'5','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('6','1','普通用户',NULL,'8','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('7','7','山东省管理员',NULL,'9','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('dabcc958a63d4bf99dbcf64635d0760e','1','大连采购',NULL,'5','1','2014-11-14 20:39:04','1','2014-11-14 20:39:04','','0');

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;

INSERT INTO `sys_role_menu` (`role_id`, `menu_id`)
VALUES
	('1','1'),
	('1','10'),
	('1','11'),
	('1','111'),
	('1','1111'),
	('1','1112'),
	('1','1113'),
	('1','112'),
	('1','1121'),
	('1','113'),
	('1','1131'),
	('1','1132'),
	('1','114'),
	('1','1141'),
	('1','12'),
	('1','13'),
	('1','14'),
	('1','15'),
	('1','16'),
	('1','17'),
	('1','18'),
	('1','19'),
	('1','2'),
	('1','20'),
	('1','21'),
	('1','22'),
	('1','27'),
	('1','28'),
	('1','29'),
	('1','3'),
	('1','30'),
	('1','4'),
	('1','5'),
	('1','6'),
	('1','67'),
	('1','68'),
	('1','7'),
	('1','8'),
	('1','9'),
	('2','1'),
	('2','10'),
	('2','11'),
	('2','12'),
	('2','13'),
	('2','14'),
	('2','15'),
	('2','16'),
	('2','17'),
	('2','18'),
	('2','19'),
	('2','2'),
	('2','20'),
	('2','21'),
	('2','22'),
	('2','27'),
	('2','28'),
	('2','29'),
	('2','3'),
	('2','30'),
	('2','4'),
	('2','5'),
	('2','6'),
	('2','67'),
	('2','68'),
	('2','7'),
	('2','8'),
	('2','9'),
	('3','1'),
	('3','10'),
	('3','11'),
	('3','12'),
	('3','13'),
	('3','14'),
	('3','15'),
	('3','16'),
	('3','17'),
	('3','18'),
	('3','19'),
	('3','2'),
	('3','20'),
	('3','21'),
	('3','22'),
	('3','27'),
	('3','28'),
	('3','29'),
	('3','3'),
	('3','30'),
	('3','4'),
	('3','5'),
	('3','6'),
	('3','67'),
	('3','68'),
	('3','7'),
	('3','8'),
	('3','9'),
	('4','1'),
	('4','10'),
	('4','11'),
	('4','12'),
	('4','13'),
	('4','14'),
	('4','15'),
	('4','16'),
	('4','17'),
	('4','18'),
	('4','19'),
	('4','2'),
	('4','20'),
	('4','21'),
	('4','22'),
	('4','27'),
	('4','28'),
	('4','29'),
	('4','3'),
	('4','30'),
	('4','4'),
	('4','5'),
	('4','6'),
	('4','67'),
	('4','68'),
	('4','7'),
	('4','8'),
	('4','9'),
	('5','1'),
	('5','10'),
	('5','11'),
	('5','12'),
	('5','13'),
	('5','14'),
	('5','15'),
	('5','16'),
	('5','17'),
	('5','18'),
	('5','19'),
	('5','2'),
	('5','20'),
	('5','21'),
	('5','22'),
	('5','27'),
	('5','28'),
	('5','29'),
	('5','3'),
	('5','30'),
	('5','4'),
	('5','5'),
	('5','6'),
	('5','67'),
	('5','68'),
	('5','7'),
	('5','8'),
	('5','9'),
	('6','1'),
	('6','10'),
	('6','11'),
	('6','12'),
	('6','13'),
	('6','14'),
	('6','15'),
	('6','16'),
	('6','17'),
	('6','18'),
	('6','19'),
	('6','2'),
	('6','20'),
	('6','21'),
	('6','22'),
	('6','27'),
	('6','28'),
	('6','29'),
	('6','3'),
	('6','30'),
	('6','4'),
	('6','5'),
	('6','6'),
	('6','67'),
	('6','68'),
	('6','7'),
	('6','8'),
	('6','9'),
	('7','1'),
	('7','10'),
	('7','11'),
	('7','12'),
	('7','13'),
	('7','14'),
	('7','15'),
	('7','16'),
	('7','17'),
	('7','18'),
	('7','19'),
	('7','2'),
	('7','20'),
	('7','21'),
	('7','22'),
	('7','27'),
	('7','28'),
	('7','29'),
	('7','3'),
	('7','30'),
	('7','4'),
	('7','5'),
	('7','6'),
	('7','67'),
	('7','68'),
	('7','7'),
	('7','8'),
	('7','9'),
	('dabcc958a63d4bf99dbcf64635d0760e','1'),
	('dabcc958a63d4bf99dbcf64635d0760e','13'),
	('dabcc958a63d4bf99dbcf64635d0760e','14'),
	('dabcc958a63d4bf99dbcf64635d0760e','15'),
	('dabcc958a63d4bf99dbcf64635d0760e','16'),
	('dabcc958a63d4bf99dbcf64635d0760e','17'),
	('dabcc958a63d4bf99dbcf64635d0760e','18'),
	('dabcc958a63d4bf99dbcf64635d0760e','19'),
	('dabcc958a63d4bf99dbcf64635d0760e','2'),
	('dabcc958a63d4bf99dbcf64635d0760e','20'),
	('dabcc958a63d4bf99dbcf64635d0760e','21'),
	('dabcc958a63d4bf99dbcf64635d0760e','22');

/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_office
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_office`;

CREATE TABLE `sys_role_office` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `office_id` varchar(64) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`role_id`,`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-机构';



# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `company_id` varchar(64) NOT NULL COMMENT '归属公司',
  `office_id` varchar(64) NOT NULL COMMENT '归属部门',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `qq` varchar(200) DEFAULT NULL COMMENT 'QQ',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `user_catagory` char(1) DEFAULT NULL COMMENT '用户种类',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `forward` varchar(255) DEFAULT NULL COMMENT '课题方向',
  PRIMARY KEY (`id`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_login_name` (`login_name`),
  KEY `sys_user_company_id` (`company_id`),
  KEY `sys_user_update_date` (`update_date`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;

INSERT INTO `sys_user` (`id`, `company_id`, `office_id`, `login_name`, `password`, `no`, `name`, `email`, `phone`, `mobile`, `qq`, `user_type`, `user_catagory`, `login_ip`, `login_date`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `forward`)
VALUES
	('','1','1','212','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','12121','1212','121@173.com','212','2121',NULL,'3','1',NULL,NULL,NULL,NULL,NULL,NULL,'21','0',NULL),
	('1','1','1','admin','ad2eda2921f8861636c729ac4b3b49b0979c85c338b90767c9f14281','00001','管理员','yangwn1116@gmail.com','8675','8675',NULL,'',NULL,'0:0:0:0:0:0:0:1','2014-11-17 23:48:52','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','管理员','0',NULL),
	('2661a4f98c7e4ef384d3b4bb4e8e339b','1','1','11','620eec9d99122c307ff4ff91a41a12a8498f8f9b21e91d1eea4d672d','212','121','11333@163323.com','1212121','2121','18101691','3','2',NULL,NULL,'1','2014-11-15 23:58:54','1','2014-11-15 23:58:54','2121','0',NULL),
	('2e4f10ad67dd41ee9a507667cd789285','db8bf7e94ab1413899a7430f4c9fc859','db8bf7e94ab1413899a7430f4c9fc859','dl_admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','000002','大连塞托管理人','','','',NULL,'2',NULL,'127.0.0.1','2014-11-09 10:56:42','1','2014-11-05 23:51:26','1','2014-11-05 23:51:26','','0',NULL),
	('5086e23de98941eca7c32c96692b8dd3','1','1','2222222','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','2222222','2222222','122222@13.com','212','21',NULL,'3','1',NULL,NULL,'1','2014-11-10 19:25:02','1','2014-11-10 19:25:02','2121','0',NULL),
	('b69c638f106b45bb99b7b16ee93a4795','1','96ad01f9140b47f6b5c5d638a3f4128b','232','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','3232','23','32121@163.com','32','32',NULL,'3','1',NULL,NULL,'1','2014-11-10 15:37:34','1','2014-11-10 15:37:34','','0',NULL),
	('bcc2a09a4ac943d48ce61d525109878d','1','1','21222','e6229d4886326120c197a36f7caa006913a84225c2084a40f6d3560a','111','2222','121@163.om','ds','dsds',NULL,'2','1',NULL,NULL,'1','2014-11-10 19:24:29','1','2014-11-10 19:24:29','dsds','0',NULL),
	('c6a29d4b98544b2194e0dfdb1a44db06','1b7d1aeea9cd4711851ec65199be2aa2','96ad01f9140b47f6b5c5d638a3f4128b','cc_admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','000002','长春塞托管理人','','','',NULL,'2',NULL,'127.0.0.1','2014-11-05 23:57:33','1','2014-11-05 23:52:13','1','2014-11-10 09:11:30','','0',NULL),
	('cdcf84f29e8f493aab3ecc94d34790bb','1','1','dsds','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','dsds','ds','dsdsds@136.com','','',NULL,'2',NULL,NULL,NULL,'1','2014-11-07 09:57:03','1','2014-11-07 09:57:03','','0',NULL),
	('e72a1d8858674cfea13a97a41ff2afd1','1','1','1999','f0ed1d0f850c3e32de43d914369fea87a1c69ee61f103ecd59d66826','1999','1999','11@163.com','1121','2121',NULL,'3','1','127.0.0.1','2014-11-10 23:08:36','1','2014-11-10 23:08:20','1','2014-11-10 23:08:20','2121','0',NULL),
	('e9965c7982714e09a5febfd37ad0ad99','db8bf7e94ab1413899a7430f4c9fc859','4a8ec405cb8e4e5f81571b46cb7f534f','dl_cg','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','000003','大连采购组','','','',NULL,'2',NULL,NULL,NULL,'1','2014-11-05 23:54:27','1','2014-11-14 20:39:18','','0',NULL),
	('f760b33f877941fc8eeb9d6c5685d817','1','1','3232','f4c7476f9e34213d95165a88131a1fa025e12867b143c81c6e22fa39','23232','223','12232@173.com','1','2121','2121','3','1',NULL,NULL,'1','2014-11-16 00:01:55','1','2014-11-16 00:01:55','323','0','2121');

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;

INSERT INTO `sys_user_role` (`user_id`, `role_id`)
VALUES
	('','6'),
	('1','1'),
	('10','2'),
	('11','3'),
	('12','4'),
	('13','5'),
	('14','6'),
	('2','1'),
	('2661a4f98c7e4ef384d3b4bb4e8e339b','2'),
	('2e4f10ad67dd41ee9a507667cd789285','3'),
	('3','2'),
	('4','3'),
	('5','4'),
	('5086e23de98941eca7c32c96692b8dd3','6'),
	('6','5'),
	('7','2'),
	('7','7'),
	('8','2'),
	('9','1'),
	('b69c638f106b45bb99b7b16ee93a4795','2'),
	('bcc2a09a4ac943d48ce61d525109878d','3'),
	('c5fbcace6007470e8f07551995aae248','6'),
	('c6a29d4b98544b2194e0dfdb1a44db06','2'),
	('c6a29d4b98544b2194e0dfdb1a44db06','4'),
	('cdcf84f29e8f493aab3ecc94d34790bb','2'),
	('e72a1d8858674cfea13a97a41ff2afd1','2'),
	('e9965c7982714e09a5febfd37ad0ad99','3'),
	('e9965c7982714e09a5febfd37ad0ad99','dabcc958a63d4bf99dbcf64635d0760e'),
	('f760b33f877941fc8eeb9d6c5685d817','6');

/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
