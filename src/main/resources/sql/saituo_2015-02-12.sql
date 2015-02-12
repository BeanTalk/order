# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.19)
# Database: jeesite
# Generation Time: 2015-02-12 14:38:02 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table st_address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_address`;

CREATE TABLE `st_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址编码',
  `user_id` varchar(64) NOT NULL DEFAULT '' COMMENT '客户编码',
  `receipt_address` varchar(512) NOT NULL COMMENT '收货地址',
  `receipt_person` varchar(128) NOT NULL COMMENT '收货人',
  `contact_phone` varchar(128) NOT NULL COMMENT '联系电话',
  `invoice_caput` varchar(512) NOT NULL COMMENT '发票抬头',
  `invoice_require` varchar(512) NOT NULL COMMENT '发票要求',
  `other_require` varchar(512) NOT NULL COMMENT '其他要求',
  `del_flag` char(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`address_id`),
  KEY `st_address_i` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地址信息表';

LOCK TABLES `st_address` WRITE;
/*!40000 ALTER TABLE `st_address` DISABLE KEYS */;

INSERT INTO `st_address` (`address_id`, `user_id`, `receipt_address`, `receipt_person`, `contact_phone`, `invoice_caput`, `invoice_require`, `other_require`, `del_flag`)
VALUES
	(1,'6','大连市高新园区软件路2号','是呵护','13898381919','发票抬头','发票要求','其他要求','0');

/*!40000 ALTER TABLE `st_address` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_again_allot
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_again_allot`;

CREATE TABLE `st_again_allot` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `stock_number` int(11) NOT NULL COMMENT '备货产品订单编码',
  `again_allot_reason` varchar(512) DEFAULT NULL COMMENT '重分配原因',
  `accept_person` varchar(64) DEFAULT NULL COMMENT '创建者',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `st_product_order_his_i` (`stock_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='备货产品重分配表';

LOCK TABLES `st_again_allot` WRITE;
/*!40000 ALTER TABLE `st_again_allot` DISABLE KEYS */;

INSERT INTO `st_again_allot` (`id`, `stock_number`, `again_allot_reason`, `accept_person`, `accept_date`)
VALUES
	(1,4,'SB1','10','2015-02-12 21:47:08');

/*!40000 ALTER TABLE `st_again_allot` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_agency
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_agency`;

CREATE TABLE `st_agency` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '经销商编码',
  `agent_name` varchar(200) NOT NULL DEFAULT '' COMMENT '经销商名称',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `st_agency` WRITE;
/*!40000 ALTER TABLE `st_agency` DISABLE KEYS */;

INSERT INTO `st_agency` (`id`, `agent_name`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	(102,'12121','1','2014-12-24 16:37:37','1','2014-12-24 16:37:37',NULL,'0'),
	(103,'434343','1','2014-12-24 16:43:49','1','2014-12-24 16:43:49',NULL,'0');

/*!40000 ALTER TABLE `st_agency` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_audit_his
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_audit_his`;

CREATE TABLE `st_audit_his` (
  `audit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '审批记录ID',
  `register_number` int(11) NOT NULL COMMENT '产品订单编码',
  `audit_person` varchar(64) NOT NULL COMMENT '审批人',
  `audit_date` datetime DEFAULT NULL COMMENT '审批时间',
  `audit_result` varchar(64) NOT NULL DEFAULT '' COMMENT '审批结果：2.已驳回;3.审批通过',
  `turn_down_reason` varchar(64) DEFAULT NULL COMMENT '驳回原因:价格原因；品牌原因；存货原因；其他原因等',
  `turn_down_note` varchar(512) DEFAULT NULL COMMENT '驳回描述',
  `status_cd` varchar(2) NOT NULL COMMENT '状态:1.代表当前最新审批结果。0代表旧版本审批结果',
  PRIMARY KEY (`audit_id`),
  KEY `st_audit_his_i` (`register_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审批结果历史记录表';

LOCK TABLES `st_audit_his` WRITE;
/*!40000 ALTER TABLE `st_audit_his` DISABLE KEYS */;

INSERT INTO `st_audit_his` (`audit_id`, `register_number`, `audit_person`, `audit_date`, `audit_result`, `turn_down_reason`, `turn_down_note`, `status_cd`)
VALUES
	(1,1,'8','2015-02-12 20:37:28','3','','','1'),
	(2,2,'8','2015-02-12 20:37:37','2','1','煞笔','1'),
	(3,3,'8','2015-02-12 20:37:41','3','','','1'),
	(4,4,'8','2015-02-12 20:37:46','2','1','煞笔2','1'),
	(5,5,'8','2015-02-12 20:37:55','3','','','1'),
	(6,6,'8','2015-02-12 20:37:55','3','','','1'),
	(7,2,'8','2015-02-12 20:39:01','2','1','SB1','1'),
	(8,4,'8','2015-02-12 20:39:05','2','1','SB2','1'),
	(9,2,'8','2015-02-12 20:40:05','3','','','1'),
	(10,4,'8','2015-02-12 20:40:07','3','','','1');

/*!40000 ALTER TABLE `st_audit_his` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_brand_buyer_relation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_brand_buyer_relation`;

CREATE TABLE `st_brand_buyer_relation` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品品牌编码',
  `user_id` varchar(64) NOT NULL COMMENT '客户编码',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌与采购人员关系表';

LOCK TABLES `st_brand_buyer_relation` WRITE;
/*!40000 ALTER TABLE `st_brand_buyer_relation` DISABLE KEYS */;

INSERT INTO `st_brand_buyer_relation` (`brand_id`, `user_id`)
VALUES
	(7,'10');

/*!40000 ALTER TABLE `st_brand_buyer_relation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_cust_beans
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_cust_beans`;

CREATE TABLE `st_cust_beans` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cust_id` varchar(64) NOT NULL COMMENT '客户编号',
  `beans_num` int(11) NOT NULL COMMENT '豆豆数量',
  `flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户豆豆信息表';



# Dump of table st_ext_supply
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_ext_supply`;

CREATE TABLE `st_ext_supply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table st_gift
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_gift`;

CREATE TABLE `st_gift` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '礼品ID',
  `gift_name` varchar(64) DEFAULT NULL COMMENT '礼品名称',
  `area_id` varchar(64) DEFAULT NULL COMMENT '所属区域',
  `need_pea` int(8) DEFAULT '0' COMMENT '所需豆豆',
  `need_score` int(8) DEFAULT '0' COMMENT '所需积分',
  `gift_status` int(1) DEFAULT '0' COMMENT '礼品状态: 0,未开始兑换;1,开始兑换;2,兑换结束',
  `gift_num` int(8) DEFAULT '0' COMMENT '礼品可使用数量',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `st_gift` WRITE;
/*!40000 ALTER TABLE `st_gift` DISABLE KEYS */;

INSERT INTO `st_gift` (`id`, `gift_name`, `area_id`, `need_pea`, `need_score`, `gift_status`, `gift_num`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	(1,'gift1','2',100,3333,1,992,NULL,NULL,'6','2015-02-12 21:13:41',NULL,'1'),
	(109,'2121','2',212,2121,1,210,'1','2014-12-16 23:00:36','6','2015-02-12 21:13:33','212121','0'),
	(110,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0'),
	(111,'2121','2',1,2121,0,2121,'1','2015-01-28 21:16:46','1','2015-01-28 21:16:46','2121','0'),
	(112,'1222','2',11,0,0,0,NULL,NULL,NULL,NULL,NULL,'0'),
	(113,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0'),
	(114,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0'),
	(115,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0'),
	(116,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0'),
	(117,'gift1','2',100,3333,1,994,NULL,NULL,'6','2015-02-02 20:26:57',NULL,'1'),
	(118,'2121','2',212,2121,1,215,'1','2014-12-16 23:00:36','6','2015-02-02 20:26:50','212121','0'),
	(119,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0'),
	(120,'2121','2',1,2121,0,2121,'1','2015-01-28 21:16:46','1','2015-01-28 21:16:46','2121','0'),
	(121,'1222','2',11,0,0,0,NULL,NULL,NULL,NULL,NULL,'0'),
	(122,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0'),
	(123,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0'),
	(124,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0'),
	(125,'2121','2',1,2121,0,2121,'1','2014-12-16 23:14:03','1','2014-12-16 23:14:03','212121','0');

/*!40000 ALTER TABLE `st_gift` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_order_complaint
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_order_complaint`;

CREATE TABLE `st_order_complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_order_id` int(11) NOT NULL COMMENT '客户订单编码',
  `register_number` int(11) NOT NULL COMMENT '产品订单编码',
  `area_id` varchar(64) NOT NULL COMMENT '受理地市',
  `user_id` int(11) NOT NULL COMMENT '客户编码',
  `complaint_type` varchar(2) NOT NULL COMMENT '投诉类型：1.产品质量问题、2.产品剂量问题、3.产品包装问题、4.产品保质期问题',
  `complaint_note` varchar(512) DEFAULT NULL COMMENT '投诉内容',
  `status_cd` varchar(2) NOT NULL COMMENT '状态：0.未完成、1.处理中、2.已完成、-1.已取消',
  `complaint_date` datetime DEFAULT NULL COMMENT '投诉时间',
  `handle_person_id` int(11) DEFAULT NULL COMMENT '处理人编码',
  `handle_note` varchar(512) DEFAULT NULL COMMENT '处理意见',
  `handle_result` varchar(2) DEFAULT NULL COMMENT '处理结果：1.协商解决、2.换货、3.退货、4.投诉取消',
  `handle_date` datetime DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`),
  KEY `st_order_complaint_i` (`user_id`),
  KEY `st_order_complaint_ii` (`handle_person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投诉信息表';



# Dump of table st_order_status
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_order_status`;

CREATE TABLE `st_order_status` (
  `status_cd` int(11) NOT NULL COMMENT '序号',
  `status_type` varchar(2) NOT NULL COMMENT '状态类型：1.客户订单 2.产品订单',
  `status_name` varchar(64) NOT NULL COMMENT '状态名称',
  PRIMARY KEY (`status_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单状态表';



# Dump of table st_product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_product`;

CREATE TABLE `st_product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '产品编码',
  `product_name` varchar(200) NOT NULL DEFAULT '' COMMENT '产品名称',
  `product_num` varchar(128) NOT NULL COMMENT '货号',
  `brand_id` int(11) DEFAULT NULL COMMENT '产品品牌编码',
  `spec_value` varchar(256) DEFAULT '' COMMENT '规格',
  `unit_value` varchar(256) DEFAULT '' COMMENT '单位',
  `delivery_fee` double(10,2) DEFAULT NULL COMMENT '备货价',
  `catalog_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '目录价',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `st_product` WRITE;
/*!40000 ALTER TABLE `st_product` DISABLE KEYS */;

INSERT INTO `st_product` (`id`, `product_name`, `product_num`, `brand_id`, `spec_value`, `unit_value`, `delivery_fee`, `catalog_fee`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	(1,'Phospho-Histone H3 (Ser10) Blocking Peptide, 100ug','1000S',3,'1000','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(2,'Phospho-Akt Substrate (RXRXXS*/T*) (23C8D2) Rabbit mAb, 100ul','10001S',3,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(3,'Phospho-Histone H3 (Ser10) Blocking Peptide, 100ug','1000S',3,'10.91','ML',NULL,10.00,NULL,NULL,NULL,NULL,NULL,'0'),
	(4,'β-Catenin Blocking Peptide, 100ug','1002S',1,'10.91','ML',NULL,20.00,NULL,NULL,NULL,NULL,NULL,'0'),
	(5,'HP1α Blocking Peptide, 100ug','1004S',1,'10.91','ML',NULL,30.00,NULL,NULL,NULL,NULL,NULL,'0'),
	(6,'Neurofilament-L Blocking Peptide, 100ug','1005S',1,'10.91','ML',NULL,40.00,NULL,NULL,NULL,NULL,NULL,'0'),
	(7,'TCF1 Blocking Peptide, 100ug','1007S',1,'10.91','ML',NULL,50.00,NULL,NULL,NULL,NULL,NULL,'0'),
	(8,'Acetyl- and Phospho-Histone H3 (Lys9/Ser10) Blocking Peptide, 100ug','1010S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(9,'Cleaved Caspase-8 (Asp391) Blocking Peptide, 100ug','1011S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(10,'Jak1 Blocking Peptide, 100ug','1013S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(11,'EGR1 Blocking Peptide, 100ug','1015S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(12,'LEF1 Blocking Peptide, 100ug','1016S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(13,'Phospho-NF-kappaB p105 (Ser933) Blocking Peptide, 100ug','1021S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(14,'Phospho-IKK-alpha/beta (Ser176/180) Blocking Peptide, 100ug','1023S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(15,'PKA (Thr197) Biotinylated Peptide, 1.25ml','1024S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(16,'β-Actin Blocking Peptide, 100ug','1025S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(17,'Phospho-Bad (Ser112) Blocking Peptide, 100ug','1026S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(18,'Phospho-GSK-3α (Ser21) Blocking Peptide, 100ug','1027S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(19,'β-Tubulin Blocking Peptide, 100ug','1032S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(20,'Cytochrome c Blocking Peptide, 100ug','1033S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(21,'COX IV Blocking Peptide, 100ug','1034S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(22,'Mre11 Blocking Peptide, 100ug','1035S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(23,'PU.1 Blocking Peptide, 100ug','1036S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(24,'Survivin Blocking Peptide, 100ug','1037S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(25,'Phospho-Stat1 (Tyr701) Blocking Peptide, 100ug','1038S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(26,'Jak2 Blocking Peptide, 100ug','1039S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(27,'Cyclin D1 Blocking Peptide, 100ug','1044S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(28,'Phospho-Ezrin (Thr567)/Radixin (Thr564)/ Moesin (Thr558) Blocking Peptide, 100ug','1047S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(29,'NEDD8 Blocking Peptide, 100ug','1048S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(30,'Cleaved Caspase-3 (Asp175) Blocking Peptide, 100ug','1050S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(31,'PP2A B Subunit Blocking Peptide, 100ug','1051S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(32,'Phospho-4E-BP1 (Thr37/46) Blocking Peptide, 100ug','1052S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(33,'4E-BP1 Blocking Peptide, 100ug','1053S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(34,'PP2A A Subunit Blocking Peptide, 100ug','1054S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(35,'p21 Waf1/Cip1 Blocking Peptide, 100ug','1055S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(36,'E-Cadherin Blocking Peptide, 100ug','1056S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(37,'Thioredoxin 1 Blocking Peptide, 100ug','1057S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(38,'HER2/ErbB2 Blocking Peptide, 100ug','1059S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(39,'NF-κB p65 Blocking Peptide, 100ug','1061S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(40,'Acetyl-CoA Carboxylase Blocking Peptide, 100ug','1062S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(41,'Tri-Methyl-Histone H3 (Lys4) Blocking Peptide, 100ug','1064S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(42,'TRAF1 Blocking Peptide, 100ug','1066S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(43,'PP2A C Subunit Blocking Peptide, 100ug','1067S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(44,'mTOR Blocking Peptide, 100ug','1072S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(45,'GSK-3β Blocking Peptide, 100ug','1073S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(46,'AMPKβ1/2 Blocking Peptide, 100ug','1074S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(47,'Stat1 Blocking Peptide (9175 Specific), 100ug','1079S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(48,'DARPP-32 Blocking Peptide, 100ug','1081S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(49,'Acetyl-Histone H3 (Lys9) Blocking Peptide, 100ug','1083S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(50,'BiP Blocking Peptide, 100ug','1084S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(51,'Akt (pan) Blocking Peptide, 100ug','1085S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(52,'Phospho-CREB (Ser133) Blocking Peptide, 100ug','1090S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(53,'Phospho-EGF Receptor (Tyr1068) Blocking Peptide, 100ug','1110S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(54,'IRAK1 (D51G7) XP® Rabbit mAb (Biotinylated), 100ul','11123S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(55,'eNOS (Ser1177) Biotinylated Peptide, 1.25ml','1133S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(56,'PAK1 (Ser144)/ PAK2 (Ser141) Biotinylated Peptide, 1.25ml','1134S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(57,'Phospho-Akt (Ser473) Blocking Peptide, 100ug','1140S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(58,'Rb (Ser780) Biotinylated Peptide, 1.25ml','1142S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(59,'IκBα (Ser32) Biotinylated Peptide, 1.25ml','1146S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(60,'Phospho-p44/42 MAPK (Erk1/2) (Thr202/Tyr204) Blocking Peptide, 100ug','1150S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(61,'S6 Ribosomal Protein Blocking Peptide, 100ug','1155S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(62,'Zap-70 Blocking Peptide, 100ug','1160S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(63,'Phospho-p38 MAPK (Thr180/Tyr182) Blocking Peptide, 100ug','1170S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(64,'Phospho-EGF Receptor (Tyr1173) Blocking Peptide, 100ug','1175S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(65,'Phospho-HER3/ErbB3 (Tyr1289) Blocking Peptide, 100ug','1180S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(66,'ACAT2 Antibody, 100ul,ACAT2 Antibody, 100ul,ACAT2 Antibody, 100ul,ACAT2 Antibody, 100ul','11814S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(67,'ATF-4 (D4B8) Rabbit mAb, 100ul','11815S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(68,'DEPTOR/DEPDC6 (D9F5) Rabbit mAb, 100ul','11816S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(69,'Phospho-GRB10 (Ser476) (D4E6) Rabbit mAb, 100ul','11817S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(70,'Phospho-Acetyl-CoA Carboxylase (Ser79) (D7D11) Rabbit mAb, 100ul','11818S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(71,'SignalSilence® APC3 siRNA I, 300ul','11821S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(72,'SignalSilence® HELLS siRNA I, 300ul','11822S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(73,'SignalSilence® HELLS siRNA II, 300ul','11823S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(74,'TCF12/HEB (D2C10) Rabbit mAb, 100ul','11825S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(75,'FKBP4 Antibody, 100ul','11826S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(76,'Arrestin 1/S-Arrestin Antibody, 100ul','11828S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(77,'VAMP2 Antibody, 100ul','11829S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(78,'SSTR1 Antibody, 100ul','11830S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(79,'WBP2 Antibody, 100ul','11831S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(80,'USP8 (D18F6) Rabbit mAb, 100ul','11832S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(81,'Phospho-Tau (Ser202) Antibody, 100ul','11834S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(82,'FGF Receptor 2 (D4H9) Rabbit mAb, 100ul','11835S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(83,'Phospho-Tau (Ser400/Thr403/Ser404) Antibody, 100ul','11837S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(84,'Cell Fractionation Antibody Sampler Kit, 1Kit','11843S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(85,'LIN28A (D1A1A) XP® Rabbit mAb (Alexa Fluor® 647 Conjugate), 100ul','11845S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(86,'HA-Tag (C29F4) Rabbit mAb (Magnetic Bead Conjugate), 400ul','11846S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(87,'GST (26H1) Mouse mAb (Magnetic Bead Conjugate), 400ul','11847S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(88,'Akt (pan) (C67E7) Rabbit mAb (Magnetic Bead Conjugate), 400ul','11848S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(89,'PAX5 (D19F8) XP® Rabbit mAb (Alexa Fluor® 488 Conjugate), 100ul','11849S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(90,'p21 Waf1/Cip1 (12D1) Rabbit mAb (Alexa Fluor® 594 Conjugate), 100ul','11850S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(91,'PAX5 (D19F8) XP® Rabbit mAb (Alexa Fluor® 647 Conjugate), 100ul','11852S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(92,'PAX5 (D19F8) XP® Rabbit mAb (Alexa Fluor® 555 Conjugate), 100ul','11855S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(93,'Acetyl-Histone H3 (Lys9) (C5B11) Rabbit mAb (Pacific Blue™Conjugate), 100ul','11857S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(94,'PhosphoPlus® EGFR (Tyr1068) Antibody Duet, 1Kit','11862S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(95,'PSMA2 (D3A4) Rabbit mAb, 100ul','11864S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(96,'Phospho-Bad (Ser112) (40A9) Rabbit mAb (PE Conjugate), 100ul','11865S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(97,'SignalSilence® APC2 siRNA I, 300ul','11866S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(98,'NBC1/SLC4A4 Antibody, 100ul','11867S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(99,'Phospho-GIT2 (Tyr392) (D8N9A) Rabbit mAb, 100ul','11873S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(100,'PathScan® Phospho-Bcl-2 (Ser70) Sandwich ELISA Kit, 1Kit','11874S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(101,'Upf2 (D3B10) Rabbit mAb, 100ul','11875S',1,'10.91','ML',NULL,100.11,NULL,NULL,NULL,NULL,NULL,'0'),
	(102,'uLLI','1000S',3,'1000','ML',NULL,100.11,'1','2015-01-28 19:58:28','1','2015-01-28 19:58:28',NULL,'0'),
	(103,'RRR','1000S',3,'1000','ML',NULL,100.11,'1','2015-01-28 20:10:58','1','2015-01-28 20:10:58',NULL,'0'),
	(104,'CCC','1000S',3,'1000','ML',NULL,100.11,'1','2015-01-28 20:27:02','1','2015-01-28 20:27:02',NULL,'0'),
	(105,'BRDU CELL PROFIL ASSAY-200 TESTS','2750',7,'','',NULL,3498.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(106,'BRDU CELL PROFIL ASSAY-1000 TESTS','2752',7,'','',NULL,7593.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(107,'BACTERIAL XPRESS NUCLEIC ACID EXTRACTION','3096',7,'','',NULL,902.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(108,'COX A24 -1ML','3302',7,'','',NULL,1030.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(109,'AQUALITE, LOW EGTA','4100',7,'','',NULL,1950.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(110,'Anti-SHIP, clone P1C1','05-1911',7,'','',NULL,2880.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(111,'Anti-PDK, clone 3H1.2','05-1912',7,'','',NULL,2880.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(112,'RSV, CONTROL SLIDE','5012',7,'','',NULL,78.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(113,'NORMAL MOUSE AB - 10ML','5014',7,'','',NULL,1275.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(114,'MEASLES, CONTROL SLIDE','5031',7,'','',NULL,88.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(115,'PBS PACKET - SEE 5015','5087',7,'','',NULL,129.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(116,'LYSE STOP -60ML','5099',7,'','',NULL,690.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(117,'AQUALITE, STREP ASSAY BUFF, 10X','5103',7,'','',NULL,451.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(118,'AQUALITE WASH BUFFER, 10X-100ML','5108',7,'','',NULL,451.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(119,'WASH SUPPLEMENT-30ML','5117',7,'','',NULL,902.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(120,'SIMULFL HSV/VZV -5ML','5235',7,'','',NULL,2470.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(121,'Anti-RSK-2, clone 4RS-1B11','05-1917',7,'','',NULL,3001.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(122,'Anti-phospho IRS1 (Ser522) clone  17.5.2','05-1921',7,'','',NULL,2880.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(123,'Anti-phospho-Threonine','05-1923',7,'','',NULL,4206.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(124,'125I-Insulin <5 uCi','9011',7,'','',NULL,1381.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(125,'125I-Human Proinsulin <3 uCi','9015',7,'','',NULL,1626.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(126,'125I-LisPro Insulin <3 uCi','9016',7,'','',NULL,2106.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(127,'125I-Human C-Peptide <3 uCi','9020',7,'','',NULL,1381.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(128,'125I-Rat C-Peptide <3 uCi','9022',7,'','',NULL,1381.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(129,'125I-Porcine C-Peptide <3 uCi','9023',7,'','',NULL,2106.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(130,'125I-Glucagon <3 uCi','9030',7,'','',NULL,1451.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(131,'125I-Human Leptin <3 uCi','9081',7,'','',NULL,2106.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(132,'125I-Mouse Leptin <3 uCi','9082',7,'','',NULL,2106.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(133,'125I-Rat Leptin <3 uCi','9083',7,'','',NULL,2106.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(134,'CUSTOM ORDER-125I-Hum Pancreatic Polypep','9091',7,'','',NULL,30490.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(135,'Anti-Lactoylglutathione lyase cloneGlo1a','05-1925',7,'','',NULL,2880.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(136,'FPR CELL LINE','10899',7,'','',NULL,98.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(137,'SSTR2 CELL LINE','10900',7,'','',NULL,98.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(138,'CB2-CHO stable cell line','10901',7,'','',NULL,98.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(139,'Anti-G3BP1, clone 14E5-G9','05-1938',7,'','',NULL,2880.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(140,'KNOB','15236',7,'','',NULL,118.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(141,'Anti-USP7, clone 1G7','05-1946',7,'','',NULL,2880.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(142,'Anti-Ubiquitin carboxyl-terminal hydrola','05-1947',7,'','',NULL,2880.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(143,'Anti-trimethyl Histone H3 (Lys27),','05-1951',7,'','',NULL,4200.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(144,'Anti-SETDB2, clone 5B7.2','05-1952',7,'','',NULL,3966.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(145,'SECONDARY ANTIBODIES-50ML','20775',7,'','',NULL,1509.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(146,'SECONDARY AB, GTX RBT BIOTIN','21537',7,'','',NULL,1392.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(147,'SECONDARY AB, GTX MS, BIOTIN','21538',7,'','',NULL,1392.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(148,'SECONDARY ANTIBODIES - 15ML','21543',7,'','',NULL,1392.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(149,'ANTIBODY DILUENT - 250ML','21544',7,'','',NULL,1158.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(150,'MEASLES NUCLEOPROT BIOTIN, 83KKII','33305',7,'','',NULL,1156.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(151,'Ultrafree-DA Centrifugal Filter 50/pk','42600',7,'','',NULL,1029.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(152,'EHMT1 Antibody (C-Terminus)','07-2045',7,'','',NULL,3498.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(153,'ANTI-EHMT Antibody (Center)','07-2046',7,'','',NULL,4200.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(154,'ANTI-HSPBAP1 Antibody (C-Terminus)','07-2048',7,'','',NULL,3498.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(155,'ANTI-DNMT3A2 Antibody (C-Terminus)','07-2050',7,'','',NULL,3966.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(156,'ANTI-PEMT Antibody (C-Terminus)','07-2051',7,'','',NULL,3966.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(157,'ANTI-MBD1 Antibody (C-Terminus)','07-2054',7,'','',NULL,3498.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(158,'SUBSTRATE,TMB/E-10ML','60096',7,'','',NULL,585.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(159,'SAMPLE/STANDARD DILUENT-60ML','60240',7,'','',NULL,924.00,'1','2015-02-05 20:42:59','1','2015-02-05 20:42:59',NULL,'0'),
	(160,'Anti-PRDM2/RIZ1','07-2068',7,'','',NULL,3381.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(161,'Anti-DMAP1 Antibody (C-Terminus)','07-2072',7,'','',NULL,3498.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(162,'K2M3 TELOMERASE, 5UM','73473',7,'','',NULL,2038.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(163,'Anti-phospho PIX (Ser340)','07-2101',7,'','',NULL,3001.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(164,'MCT8 (MS), RBX - 50ug','74571',7,'','',NULL,2656.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(165,'Anti-FGFR-4','07-2112',7,'','',NULL,0.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(166,'Anti-phospho-Ribosomal Protein S6 (','07-2113',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(167,'Anti-YAP1','07-2114',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(168,'Anti-phospho-RSK4 (Ser372)','07-2115',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(169,'Anti-phospho-RSK3 (Ser360)','07-2116',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(170,'Anti-phospho-RSK1/2 (Ser363)(Turn motif)','07-2117',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(171,'Anti-phospho-RalA (Ser194)','07-2119',7,'','',NULL,2760.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(172,'Anti-phospho-FOXO1 (Thr24)','07-2126',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(173,'Anti-Nucleolar GTP-binding protein 1','07-2128',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(174,'Anti-phospho-Abi-1 (Ser225)','07-2129',7,'','',NULL,3498.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(175,'Anti-Ubiquitin.','07-2130',7,'','',NULL,3724.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(176,'Anti-PIP5K1 gamma (long form)','07-2132',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(177,'Anti-phospho-PLC-1 (Tyr783)','07-2134',7,'','',NULL,3001.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(178,'Anti-TDRD1','07-2138',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(179,'Anti-Cysteine Sulfenic Acid','07-2139',7,'','',NULL,4326.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(180,'Anti-ARHGAP36','07-2142',7,'','',NULL,2760.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(181,'Anti-PLEKHG4B','07-2143',7,'','',NULL,2760.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(182,'Anti-PLEKHG7','07-2144',7,'','',NULL,2913.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(183,'Anti-ARHGAP30','07-2146',7,'','',NULL,2760.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(184,'LUCIFERASE SUBSTRATE -10UG','90064',7,'','',NULL,559.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(185,'LYSIS BUFFER, 5X -5ML','90065',7,'','',NULL,725.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(186,'Anti-Rho GTPase-activating protein 27','07-2148',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(187,'Anti-Rho GTPase-activating protein 23','07-2149',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(188,'Anti-Rac3','07-2151',7,'','',NULL,2760.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(189,'Anti-TRB-3','07-2160',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(190,'Anti-PCNA','07-2162',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(191,'Anti-Frizzled-4','07-2166',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(192,'Anti-SUMO 2/3','07-2167',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(193,'Anti-modified Citrulline','07-2168',7,'','',NULL,0.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(194,'Anti-PI3K-C2ß','07-2170',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(195,'Anti-STAT3','07-2173',7,'','',NULL,3147.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(196,'Sheath Starter Kit','100203',7,'','',NULL,117.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(197,'Coulter Cleanse Starter Kit','100204',7,'','',NULL,117.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(198,'QCH Cells Starter Kit','100205',7,'','',NULL,1205.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(199,'NFkB Cell Starter Kit','100206',7,'','',NULL,1205.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(200,'Anti-Heat Shock Protein 90a','07-2174',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(201,'Anti-phospho-PKD2 (Ser197/Ser200)','07-2177',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(202,'Anti-Abl interactor 1','07-2178',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(203,'Anti-Grb10','07-2182',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(204,'notificationInsertF/shipingKit SLSKS9624','00103276',7,'','',NULL,2.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(205,'Anti-phospho LRP-6 (Thr1572)','07-2187',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(206,'Anti-phospho LRP-6 (Ser1607)','07-2188',7,'','',NULL,0.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(207,'Anti-CAPZIP','07-2189',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(208,'Anti-phospho-CAPZIP (Ser108)','07-2190',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(209,'Anti-phospho-CapZIP (Ser179)','07-2191',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(210,'Anti-phospho-TBK1 (Ser172)','07-2192',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(211,'Anti-IRF-3','07-2193',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(212,'Anti-NDRG1','07-2195',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(213,'Anti-PDE3A','07-2197',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(214,'Anti-phospho-c-Met (Tyr1230/1234/1235)','07-2242',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(215,'Anti-WNK2','07-2261',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(216,'Anti-WNK3','07-2262',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(217,'Anti-WNK4','07-2263',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(218,'Anti-OSR1','07-2264',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(219,'Anti-phospho-TBC1D1 (Ser237)','07-2268',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(220,'Anti-Serine/threonine-protein kinase 39','07-2271',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(221,'Anti-phospho-SPAK (Ser373) / phosph','07-2273',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(222,'Anti-S100-A4','07-2274',7,'','',NULL,3001.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(223,'Anti-phospho GSK3ß (Ser389)','07-2275',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(224,'Anti-mSIN1','07-2276',7,'','',NULL,2880.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(225,'SpeedBead Kit for ImageStreamX (16','400041',7,'','',NULL,9617.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(226,'Calibration Reagent for FlowSight','400300',7,'','',NULL,4809.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(227,'QCH Cells','401000',7,'','',NULL,2399.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(228,'Immunophenotyping Demo   Cells','403000',7,'','',NULL,7207.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(229,'NKkB Demo Cells','404000',7,'','',NULL,4809.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(230,'Custom peptide- ALA1-ANG( 1-7) 1000 mg','833904',7,'','',NULL,0.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(231,'Custom Peptide  Val-His-Leu-Thr-Asn-Ala-Glu VHLTNAE','880194',7,'','',NULL,7020.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(232,'Custom Peptide  glucose-Val-His-Leu-Thr-Asn-Ala-Glu glucose-VHLTNAE','880195',7,'','',NULL,17351.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(233,'PRESSURE RELIEF VALVE','1002101',7,'','',NULL,784.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(234,'Alizarin Red Solution','2003999',7,'','',NULL,368.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(235,'Neurite HCS Immunofluorescence Buffer','2007642',7,'','',NULL,539.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(236,'MEMBRANE HOLDER 8003 MACHINED','2214801',7,'','',NULL,1078.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(237,'LOCK WASHER (SAME AS DC1040809','4001203',7,'','',NULL,39.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(238,'PI, Human MEL-Lines Manual SCC020','4002979',7,'','',NULL,3420.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(239,'96 Well SEQ Plate, 40pk','11134201',7,'','',NULL,33545.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(240,'EGF (MS CULTURE GRADE)','01-101',7,'','',NULL,2644.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(241,'EGF (MS RECEPTOR GRADE)       100UG','01-102',7,'','',NULL,2597.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(242,'FIBROB GTH FAC-2/BASIC FGF  25UG','01-106',7,'','',NULL,4247.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(243,'EGF - 100UG','01-107',7,'','',NULL,2258.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(244,'EGF (hu recombinant)            1mg','01-107BIO',7,'','',NULL,8541.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(245,'FGF-7/KGF, HU RT 10UG','01-118',7,'','',NULL,4294.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(246,'2.5S NERVE GROWTH FACTOR      100UG','01-125',7,'','',NULL,3849.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(247,'2.5S NERVE GROWTH FACTOR  Mg','01-125-K',7,'','',NULL,30022.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(248,'IGF-II,HU (RECOMBINANT)        25UG','01-142',7,'','',NULL,4107.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(249,'IGF-II, HUMAN RECOMBINANT, MG','01-142-K',7,'','',NULL,116988.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(250,'TNF-ALPHA (HU RT)              10UG','01-164',7,'','',NULL,3452.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(251,'7S NGF, MOUSE                 100UG','01-170',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(252,'INTERFERON-GAMMA (IFN-Y)  100 UG','01-172',7,'','',NULL,3803.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(253,'nHU  VEGF(REC) 10UG','01-185',7,'','',NULL,3978.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(254,'IGF-I (E3R)                   25UG','01-189',7,'','',NULL,4446.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(255,'RAT CNTF                       25UG','01-195',7,'','',NULL,4668.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(256,'HEREGULIN  BETA,EGF DOMAIN 100UG','01-201',7,'','',NULL,4376.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(257,'IGF-1 (REC. PRTN EXP E.COLI) 25UG','01-208',7,'','',NULL,4165.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(258,'TGFBETA1  1UG','01-209',7,'','',NULL,0.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(259,'FAS LIGAND, MEMBRANE BOUND 500UL','01-210',7,'','',NULL,3803.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(260,'PDGF  BB(HU RT HOMODIMER)10UG','01-305',7,'','',NULL,4165.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(261,'PDGF  BB(HU RT HOMODIMER) - MG','01-305-K',7,'','',NULL,84111.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(262,'PDGF AA,HUMAN  10UG','01-309',7,'','',NULL,4329.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(263,'PDGF AB (HU REC HETERODIMER)  10UG','01-310',7,'','',NULL,4294.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(264,'EPIDERMAL  GROWTH FACTOR(5X100UG)','01-407',7,'','',NULL,4797.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(265,'ENDOTHELIAL CELL GRTH SUP    50MG','02-101',7,'','',NULL,3276.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(266,'ENDOTHELIAL C.G.S. (ECGS) 10X15MG','02-102',7,'','',NULL,4739.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(267,'BOVINE PITUITARY EXT           50MG','02-103',7,'','',NULL,3276.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(268,'BOVINE PITUITARY EXTR       10X15MG','02-104',7,'','',NULL,4739.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(269,'RIPAb+™ hnRNP M1-M4, clone 1D8','03-100',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(270,'RIPAb+ Anti-PABPC1, clone 10E10','03-101',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(271,'RIPAb+ Anti-HuR, amino acids 3-19','03-102',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(272,'RIPAb+ Anti-SNRNP70','03-103',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(273,'RIPAb+ CUGBP1 Kit, clone 3B1','03-104',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(274,'RIPAb+ Lin28 Kit','03-105',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(275,'RIPAb+ EF1a, clone CBP-KK1','03-107',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(276,'RIPAb+ Fragile X Mental Retardation','03-108',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(277,'RIPAb+ Ago2, clone 9E8.2','03-110',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(278,'RIPAb+ AUF1','03-111',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(279,'RIPAb+ QKI-5','03-112',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(280,'RIPAb+ p54nrb/NonO, clone 78-1-C6','03-113',7,'','',NULL,4434.00,'1','2015-02-05 20:43:00','1','2015-02-05 20:43:00',NULL,'0'),
	(281,'RIPAb+ Musashi 1, clone EP1302','03-114',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(282,'RIPAb+ Musashi 2, clone EP1305Y','03-115',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(283,'RIPAb+™ STAU1 (Staufen 1)','03-116',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(284,'RIPAb+ CUGBP2, clone IH2','03-119',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(285,'RIPAb+™ Aly/REF, clone 11G5','03-120',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(286,'RIPAb+™ FXR1, clone 6BG10','03-176',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(287,'RIPAb+ Hexim 1','03-177',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(288,'RIPAb+ SMN, clone 2B1','03-178',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(289,'RIPAb+ SUZ12, clone 2AO9','03-179',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(290,'RIPAb+™ G3BP1, clone 14E5-G9','03-180',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(291,'RIPAb+™ hnRNPA1 (M9 Region), clone 9H10','03-181',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(292,'HDAC2 (1-488)(His-tag)human recombinant','03-182',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(293,'Furin (Human) Recombinant Protein, 300 Units','03-183',7,'','',NULL,4083.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(294,'RIPAb+™ LSM14A','03-184',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(295,'Histone H2B human recombinant','03-185',7,'','',NULL,2679.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(296,'HDAC8 human recombinant','03-186',7,'','',NULL,4668.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(297,'PCAF human recombinant','03-187',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(298,'p300(catalytic domain)human recombinant','03-188',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(299,'CBP (catalytic domain) humanRecombinant.','03-189',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(300,'HDAC1 (His-tag) human recombinant','03-190',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(301,'RIPAb+ Upf1','03-191',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(302,'RIPAb+ EED, clone AA19','03-196',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(303,'RIPAb+™ IGF2 mRNA-binding protein 3','03-198',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(304,'RIPAb+™ Phospho-eIF4E (Ser209), clo','03-199',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(305,'RIPAb+ SMN, clone 62E7','03-200',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(306,'RIPAb+ Gemin2, clone 2E17','03-202',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(307,'RIPAb+ Gemin6, clone 6H5','03-203',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(308,'RIPAb+™ hnRNPA1, clone 4B10','03-204',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(309,'RIPAb+™ hnRNP C1/C2, clone 4F4','03-205',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(310,'RIPAb+™ hnRNP U, clone 3G6','03-206',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(311,'Chloroethyl Ubiquitin (HA-tag)','03-207',7,'','',NULL,3796.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(312,'SET7/9 human recombinant','03-209',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(313,'HDAC6 (His-tag) human recombinant','03-226',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(314,'HDAC3/NCOR1 complex human recombinant','03-227',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(315,'HDAC2 full-length (His-tag) human r','03-228',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(316,'LSD1 human recombinant','03-229',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(317,'SIRT5 (His-tag) human recombinant','03-230',7,'','',NULL,4668.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(318,'Ubiquitin activating enzyme E1 (His-tag)','03-232',7,'','',NULL,5025.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(319,'HDAC3 (His-tag) human recombinant','03-233',7,'','',NULL,4902.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(320,'Furin (Human) Recombinant Protein, 2 x 300 Units','03-237',7,'','',NULL,5838.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(321,'RIPAb+™ PUM2','03-241',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(322,'RIPAb+™ PUM1','03-242',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(323,'RIPAb+™ Hexim 2','03-245',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(324,'RIPAb+™ FXR2, clone 2C8.2','03-246',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(325,'RIPAb+™ pan Ago, clone 2A8','03-248',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(326,'RIPAb+™ Ago1, clone 4G7-E12','03-249',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(327,'RIPAb+™ Ago3, clone 4B1-F6','03-250',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(328,'RIPAb+™ IGF2BP2, clone 5E10-3E1','03-251',7,'','',NULL,4434.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(329,'Elastase Substrate VIII, Colorimetric','03-32-0009-100MGCN',7,'','',NULL,1509.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(330,'Z-Phe-Arg-7-amido-4-methylcoumarin, HCl','03-32-1501-25MGCN',7,'','',NULL,1158.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(331,'MMP-2/MMP-7 Substrate, Fluorogenic','03-32-5032-1MGCN',7,'','',NULL,1743.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(332,'H-Gly-Pro-Arg-Pro-OH','03-34-0001-25MGCN',7,'','',NULL,1392.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(333,'H-Arg-Gly-Asp-Ser-OH','03-34-0002-25MGCN',7,'','',NULL,1392.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(334,'H-Gly-Arg-Gly-Asp-Ser-OH','03-34-0027-25MGCN',7,'','',NULL,3030.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(335,'H-Gly-Arg-Gly-Asp-Ser-Pro-OH','03-34-0035-25MGCN',7,'','',NULL,2562.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(336,'Calpeptin','03-34-0051-100MGCN',7,'','',NULL,8061.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(337,'H-Gly-Arg-Ala-Asp-Ser-Pro-OH','03-34-0052-25MGCN',7,'','',NULL,2328.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(338,'H-Gly-Arg-Gly-Asp-Thr-Pro-OH','03-34-0055-5MGCN',7,'','',NULL,690.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(339,'ANTI-JAK2','04-001',7,'','',NULL,3001.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(340,'Anti-SOCS1 (mouse)','04-002',7,'','',NULL,2913.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(341,'Anti-SOCS3 (mouse)','04-004',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(342,'anti-Jak3  (or Jak1)','04-011',7,'','',NULL,3001.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(343,'PTEN, MSX 6H2.1-100UG','04-035',7,'','',NULL,3483.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(344,'PTEN, MS x 6H2.1 - MG','04-035-K',7,'','',NULL,25108.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(345,'RalB, Ms x Hu Cln:25 100UG','04-037',7,'','',NULL,3724.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(346,'20S Proteasome Subunit alpha2,MS X HU','04-039',7,'','',NULL,4368.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(347,'20S Proteasome Subunit alpha3, MS X HU','04-040',7,'','',NULL,4612.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(348,'20S Proteasome Subunit alpha4, MS X HU','04-041',7,'','',NULL,4368.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(349,'Suz-12, Ms x 3C1.2-100ug','04-046',7,'','',NULL,4200.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(350,'anti-CuGBP2, clone 1H2','04-047',7,'','',NULL,3604.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(351,'anti-MBNL1, clone 3A4','04-048',7,'','',NULL,3966.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(352,'anti- ATF1','04-068',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(353,'Anti- STAT1','04-069',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(354,'Anti-dimethyl Histone H3 (Arg17)','04-077',7,'','',NULL,4200.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(355,'anti-phospho-Androgen Receptor (Ser81)','04-078',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(356,'anti-trimethyl Histone H4 (lys20)','04-079',7,'','',NULL,4200.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(357,'Anti-BAF250a/ARID1, clone PSG3','04-080',7,'','',NULL,3966.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(358,'BAF155/170, Ms x DXD12, 100mg','04-082',7,'','',NULL,3966.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(359,'Ago 1, Ms x 6D8.2','04-083',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(360,'Ago Family, Ms x 7G9.3-100ug','04-085',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(361,'EAPP (1E4), MSX-100UL','04-086',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(362,'Anti-E2F-6, clone TFE61','04-087',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(363,'ANTI-HNRNPK (F45), MSX-100UL','04-088',7,'','',NULL,3966.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(364,'PARAFIBROMIN (2H1), MSX-100UL','04-089',7,'','',NULL,3732.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(365,'Thyroid hormone receptor Beta 1, Ms','04-096',7,'','',NULL,3498.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(366,'Anti-phospho-NF-kappa-ß p65 (Ser536','04-1000',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(367,'Anti-Beta-Catenin, Clone E247','04-1002',7,'','',NULL,2936.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(368,'Anti-acetyl-Histone H3 (Lys9), clone Y28','04-1003',7,'','',NULL,4200.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(369,'Anti-FKHR, clone EP927Y','04-1005',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(370,'Anti-HIF-1 a, clone EP1215Y','04-1006',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(371,'Anti-FOXO3a, clone EP1949Y','04-1007',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(372,'Anti-NF-kappa-B p65, clone EP2161Y','04-1008',7,'','',NULL,3147.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(373,'Anti-acetyl CoA Carboxylase 1 (ACC1) pho','04-1009',7,'','',NULL,3001.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(374,'Anti-phospho-4E-BP1/2/3 (Thr45), cl','04-1010',7,'','',NULL,2880.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(375,'Anti-Beta-Catenin, Clone EP690Y','04-1011',7,'','',NULL,2880.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(376,'Anti-phospho-4E-BP1 (Thr37) clone','04-1012',7,'','',NULL,3076.00,'1','2015-02-05 20:43:01','1','2015-02-05 20:43:01',NULL,'0'),
	(377,'Anti-Stat3, clone E121-21','04-1014',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(378,'Anti-Stat5 a (C-term), clone E289','04-1016',7,'','',NULL,3030.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(379,'Anti-phospho MEK1/2 (Ser218/Ser222)','04-1017',7,'','',NULL,3182.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(380,'Anti-Progesterone Receptor, clone YR85','04-1018',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(381,'Anti-Synaptophysin (C-term), clone YE269','04-1019',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(382,'Anti-ATF2 (C-term), clone E243','04-1021',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(383,'Anti-DNA-PK, clone Y393','04-1024',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(384,'Anti-SGK, clone Y238','04-1027',7,'','',NULL,3483.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(385,'Anti-Smad2, clone EP567Y','04-1029',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(386,'Anti-GFAP (C-term), clone EP672Y','04-1031',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(387,'Anti-Neurofilament-66, clone EP676Y','04-1032',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(388,'Anti-Smad4 (C-term), clone EP618Y','04-1033',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(389,'Anti-Smad3, clone EP568Y','04-1035',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(390,'Anti-Aurora-B (N-term), clone EP1009Y','04-1036',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(391,'Anti-Aurora-A (C-term), clone EP1008Y','04-1037',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(392,'Anti-Aurora C (N-term), clone EP1011Y','04-1038',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(393,'Anti-Ras, clone EP1125Y','04-1039',7,'','',NULL,3381.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(394,'Anti-Actin, clone EP184E','04-1040',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(395,'Anti-Musashi-1, clone EP1302','04-1041',7,'','',NULL,3732.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(396,'Anti-Cytochrome C, Clone EP1326Y','04-1043',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(397,'Anti-acetyl-Histone H3 (Lys14), clo','04-1044',7,'','',NULL,4200.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(398,'AntiacetylHistoneH3Lys14cl EP964YTrial','04-1044-S',7,'','',NULL,924.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(399,'Anti-Pro Caspase-6, Clone EP1325Y','04-1045',7,'','',NULL,3732.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(400,'Anti-NOTCH1, Clone EP1238Y','04-1046',7,'','',NULL,3001.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(401,'Anti-Ezh 1/2, clone EP1408Y','04-1047',7,'','',NULL,3498.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(402,'Anti-MMP-2, clone EP1183Y','04-1048',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(403,'Anti-ß-Tubulin, class III (C-term),','04-1049',7,'','',NULL,3966.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(404,'Anti-Synuclein, clone EP1539Y','04-1050',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(405,'Anti-Met (N-term), clone EP1454Y','04-1051',7,'','',NULL,3182.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(406,'Anti-Met N-Term Custom Purified PBS only','04-1051-KL',7,'','',NULL,60080.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(407,'Anti-phospho-a-Synuclein (Ser129),','04-1052',7,'','',NULL,0.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(408,'Anti-a-Synuclein, clone EP1646Y','04-1053',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(409,'AntiAlphaSYNUCLEIN,PURIFIED,PBS,100UG/EA','04-1053NA-KL-100',7,'','',NULL,17550.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(410,'Anti-S100-ß (C-term), clone EP1576Y','04-1054',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(411,'Anti-Atg7, Clone EP1759Y','04-1055',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(412,'Anti-IDO, Clone EPR1230Y','04-1056',7,'','',NULL,3001.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(413,'Anti-phospho-eIF4E (Ser209) clone','04-1058',7,'','',NULL,3076.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(414,'Anti-phospho-STAT3 (Tyr705) clone','04-1059',7,'','',NULL,3264.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(415,'Anti-Integrin beta-3A/CD61,clone EP2417Y','04-1060',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(416,'Anti-Integrin beta-3A/CD61, clone E','04-1060NA-K',7,'','',NULL,66093.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(417,'AntiPhosphoNeurofilament M Ser614/Ser619','04-1061',7,'','',NULL,3498.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(418,'Anti-GFAP (N-term), clone EPR1034Y','04-1062',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(419,'Anti-phospho-Met (Tyr1349) clone EP2367Y','04-1063',7,'','',NULL,3182.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(420,'Anti-phospho-NMDAR1Ser889 clone EPR2480Y','04-1064',7,'','',NULL,3732.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(421,'Anti-phospho-PSD95 (Ser295) clone','04-1065',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(422,'Anti-PSD95, clone EP2652Y','04-1066',7,'','',NULL,3966.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(423,'Anti-Beta-Catenin Phospho (pT41/pT4','04-1067',7,'','',NULL,2880.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(424,'Anti-phospho-PSD95 Ser418 clone EPR2618Y','04-1068',7,'','',NULL,3381.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(425,'Anti-Musashi-2, clone EP1305Y','04-1069',7,'','',NULL,3966.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(426,'Anti-Beta-Catenin Phospho (pS33/S37','04-1070',7,'','',NULL,2936.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(427,'Anti-SynGAP, clone EPR2883','04-1071',7,'','',NULL,4668.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(428,'Anti-Transcription Factor PU.1, clo','04-1072',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(429,'Anti-phospho-GluR1 Ser845 clone EPR2148','04-1073',7,'','',NULL,3966.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(430,'Anti-CD31/PECAM-1, clone EP3095','04-1074',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(431,'Anti-phospho-GSK3 ß (Ser9) clone','04-1075',7,'','',NULL,3059.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(432,'Anti-RelB, clone EP613Y','04-1077',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(433,'Anti-Calcium/Calmodulin Kinase II,','04-1079',7,'','',NULL,2936.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(434,'Anti-TCF-4, clone EP2033Y','04-1080',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(435,'Anti-Calcium/Calmodulin Kinase IV,','04-1081',7,'','',NULL,2880.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(436,'Anti-p53 (N-term), clone Y5','04-1083',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(437,'Anti-HIAP-1 / BIR3, Clone E40','04-1087',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(438,'Anti-Caspase-3 (Pro), Clone E83-103','04-1088',7,'','',NULL,3732.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(439,'Anti-14-3-3 ß/a (C-term), clone Y62','04-1089',7,'','',NULL,3381.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(440,'Anti-Caspase-3, Clone E87','04-1090',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(441,'Anti-Sir2/SIRT1, clone E104','04-1091',7,'','',NULL,4200.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(442,'Anti-phospho-Chk2 (Thr68), clone E126','04-1092',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(443,'Anti-phospho-Histone H3 Ser10 clone E173','04-1093',7,'','',NULL,4200.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(444,'Anti-IL-6, Clone E457','04-1095',7,'','',NULL,2880.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(445,'Anti-phospho-Jak2 (Tyr1007/Tyr1008)','04-1098',7,'','',NULL,3462.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(446,'Anti-C/EBP ß (N-term), clone E298','04-1099',7,'','',NULL,3498.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(447,'Anti-Smad1, clone EP565Y','04-1100',7,'','',NULL,3030.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(448,'Anti-Bcl-10 (C-term), Clone EP606Y','04-1101',7,'','',NULL,4083.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(449,'Anti-CD45 (C-term), Clone EP322Y','04-1102',7,'','',NULL,2913.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(450,'Anti-E-Cadherin, clone EP700Y','04-1103',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(451,'Anti-C/EBP a (N-term), clone EP708Y','04-1104',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(452,'Anti-acetyl-Histone H3 (Lys18), clo','04-1107',7,'','',NULL,4200.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(453,'Anti-Vitronectin, clone EP873Y','04-1108',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(454,'Anti-Integrin beta-1/CD29, clone EP1041Y','04-1109',7,'','',NULL,3732.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(455,'Anti-NGF Receptor, clone EP1039Y','04-1111',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(456,'Anti-Neurofilament-L C-term clone EP675Y','04-1112',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(457,'Anti-FADD (N-term), Clone EP887Y','04-1113',7,'','',NULL,3732.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(458,'Anti-TNF alpha, Clone EP1085Y','04-1114',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(459,'Anti-beta-Actin, clone EP1123Y','04-1116',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(460,'Anti-alpha-Tubulin, clone EP1332Y','04-1117',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(461,'Anti-NGF-ß, clone EP1320Y','04-1119',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(462,'Anti-ROCK1, clone EPR638Y','04-1121',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(463,'Anti-MMP-1, clone EP1247Y','04-1122',7,'','',NULL,3732.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(464,'Anti-CD44, clone EPR1013Y','04-1123',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(465,'Anti-SIRT2 (C-term), clone EP1668Y','04-1124',7,'','',NULL,4200.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(466,'Anti-phospho-Rsk3 (Thr353/Thr356),','04-1125',7,'','',NULL,2880.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(467,'Anti-N-cadherin C-terminus cloneEPR1792Y','04-1126',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(468,'Anti-ErbB2/Her2, clone EP1045Y','04-1127',7,'','',NULL,3001.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(469,'Anti-Casein Kinase II (beta subunit)','04-1128',7,'','',NULL,2936.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(470,'Anti-Casein Kinase II (alpha subuni','04-1129',7,'','',NULL,3076.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(471,'Anti-LEF-1/TCF-1 a, clone EP2030Y','04-1130',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(472,'Anti-Integrin alpha4, clone EPR1355Y','04-1131',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(473,'Anti-Rsk 4 (N-term), clone EP1982Y','04-1132',7,'','',NULL,2936.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(474,'Anti-TIMP-1, clone EP1549RY','04-1133',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(475,'Anti-Estrogen Related Receptor a, c','04-1134',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(476,'Anti-Histone H3 Acetyl K56 Clone EPR996Y','04-1135',7,'','',NULL,4200.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(477,'Anti-phospho-PTEN (Ser380) clone EP2138Y','04-1136',7,'','',NULL,3076.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(478,'Anti-acetyl-p53 (Lys373) clone','04-1137',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(479,'Anti-phospho-4E-BP1 (Ser65), clone','04-1138',7,'','',NULL,2880.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(480,'Anti-phospho-4E-BP1 (Thr70), clone','04-1139',7,'','',NULL,3076.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(481,'Anti-PTP1B, clone EP1841Y','04-1140',7,'','',NULL,3076.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(482,'Anti-phospho-Chk2 (Ser33/Ser35), cl','04-1141',7,'','',NULL,3030.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(483,'Anti-pro-NGF, clone EP1318Y','04-1142',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(484,'Anti-FADD Phospho (pS194) Clone EPR1816Y','04-1143',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(485,'Anti-phospho-Her4/ErbB-4 (Tyr1258),clone','04-1144',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(486,'Anti-acetyl-p53(Lys382),clone EPR358(2),','04-1146',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(487,'Anti-MCAM [CD146], clone EPR3208','04-1147',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(488,'Anti-p-NMDAR2B (Ser1303), clone EPR1858Y','04-1148',7,'','',NULL,3849.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(489,'Anti-ILK, clone EPR1592','04-1149',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(490,'Anti-MMP-9, clone EP1254','04-1150',7,'','',NULL,3615.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(491,'Anti-Cyclin D1, clone EPR2241(IHC)-32','04-1151',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(492,'Anti-Cyclin D1, clone EPR2241(IHC)-','04-1151NA-KL',7,'','',NULL,66093.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(493,'Anti-C/EBP ß (C-term), clone E299,','04-1153',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(494,'Anti-phospho-PDGF Receptor ß (Tyr857),','04-1155',7,'','',NULL,3147.00,'1','2015-02-05 20:43:02','1','2015-02-05 20:43:02',NULL,'0'),
	(495,'Anti-phospho-PDGF Receptor ß (Tyr740),','04-1156',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(496,'Anti-phospho-Progesterone Receptor (Ser','04-1157',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(497,'Anti-Calcium/Calmodulin Kinase I, clone','04-1158',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(498,'Anti-LEF-1/TCF-1 a, clone EPR2029Y,','04-1159',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(499,'Anti-KSR1, clone EPR2421Y, Rabbit M','04-1160',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(500,'NOTCH3 (NIZ 8G5), RTX-100UL','04-117',7,'','',NULL,0.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(501,'Anti-Acetyl-Histone H4 (Lys 5)','04-118',7,'','',NULL,4200.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(502,'Anti-acetyl-Histone H4 (Lys12)','04-119',7,'','',NULL,4200.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(503,'Anti-acetylHistoneH4(Lys12), Trial Size','04-119-S',7,'','',NULL,924.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(504,'TAL-1 Ms x BTL73','04-123',7,'','',NULL,3732.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(505,'Brachyury, Ms x 3E4.2-100ug','04-135',7,'','',NULL,3779.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(506,'anti-plakoglobin 15F11','04-139',7,'','',NULL,3498.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(507,'Anti-NOD2, clone 2D9','04-145',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(508,'Anti-NLRX1','04-146',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(509,'Anti-PRMT5, clone 23C7','04-1460',7,'','',NULL,3264.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(510,'Anti-CD80 (B7-1), clone RM80','04-1461',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(511,'Anti-hnRNP F, clone 3H4','04-1462',7,'','',NULL,3030.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(512,'Anti-Y14, clone 1F12','04-1464',7,'','',NULL,3030.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(513,'Anti-Gemin2, clone 2S7','04-1465',7,'','',NULL,3030.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(514,'Anti-SMN, clone 62E7','04-1466',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(515,'Anti-PABP, clone 10E10','04-1467',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(516,'Anti-PD-1 (CD279), clone RMP1-30','04-1468',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(517,'Anti-hnRNP A1, clone 9H10','04-1469',7,'','',NULL,3030.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(518,'Anti-ASC, clone 2EI-7','04-147',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(519,'Anti-OX40L (CD252), clone RM134L','04-1470',7,'','',NULL,2880.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(520,'Anti-phospho-Chk2 (Thr68), clone E12','04-1471',7,'','',NULL,0.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(521,'Anti-Siglec-8, clone 7C9','04-1473',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(522,'Anti-CD27, clone RM27-3E5','04-1475',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(523,'ANTI-CD27, CLONE RM27-3E5, PBS ONLY, -MG','04-1475NA-K',7,'','',NULL,11700.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(524,'Anti-CD275/ICOSL, clone HK5.3','04-1478',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(525,'Anti-methyl-PP2A, C subunit, clone 2A10','04-1479',7,'','',NULL,3121.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(526,'Anti-RPA2 p34, clone RPA20, 1-46','04-1481',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(527,'Anti-CD205 (LY75/DEC-205), clone HD30','04-1485',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(528,'Anti-33D1 (CD8- Mouse DC marker), c','04-1486',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(529,'Anti-HIRA, clone WC119','04-1488',7,'','',NULL,3966.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(530,'Anti-CTCF, clone 2.27.2.5.3.2.5','04-1497',7,'','',NULL,4434.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(531,'Anti-CTCF, clone 2.24.3.10.1.4','04-1498',7,'','',NULL,4434.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(532,'Anti-B7-H3 (CD276), clone MJ8','04-1501',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(533,'Anti-IL-18, clone CPTC-IL18-1','04-1503',7,'','',NULL,2880.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(534,'Anti-CTCF, clone 1.2.1.5.4','04-1506',7,'','',NULL,4434.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(535,'Anti-CD265/RANK, clone R12-31','04-1507',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(536,'Anti-IFNAR-1, clone MARI-5A3','04-151',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(537,'Anti-eIF4G,clone 4G1','04-1516',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(538,'Anti-PD-1, clone RMP1-14','04-1518',7,'','',NULL,2880.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(539,'Anti-RPA1 p70, clone RPA9, 1-30','04-1521',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(540,'Anti-CAF p150, clone SS1, 1-3','04-1522',7,'','',NULL,4200.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(541,'Anti-CAF p60 (clone SS-53, 1-124)','04-1523',7,'','',NULL,4434.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(542,'Anti-Cdt1, clone PKS136, 1-104','04-1524',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(543,'Anti-TAFII250, clone 6B3','04-1525',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(544,'Anti-Vitamin D Receptor, clone 9A7','04-1526',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(545,'Anti-CD86, clone PO.3','04-1527',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(546,'Anti-CD86, clone IT2.2','04-1528',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(547,'Anti-MDM2 (3G9)','04-1530',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(548,'Anti-Plk1-interacting checkpoint helicas','04-1540',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(549,'Anti-ETS1 DBD, clone 1ET 7F2','04-1542',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(550,'Anti-RARa, clone 9a-9A6','04-1545',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(551,'Anti-SAP130, clone 1YO 1B10','04-1549',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(552,'Anti-SC35, clone 1SC-4F11','04-1550',7,'','',NULL,3264.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(553,'Anti-SMRT, clone 1SM-1E7','04-1551',7,'','',NULL,3966.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(554,'Anti-TLS, clone 1FU-1D2','04-1552',7,'','',NULL,3498.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(555,'Anti-Mdmx,clone 8C6','04-1555',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(556,'Anti-MDM4, clone 7A8','04-1556',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(557,'Anti-SIRT1, clone 10E4','04-1557',7,'','',NULL,4551.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(558,'Anti-Estrogen Receptor a, clone F3-A','04-1564',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(559,'Anti-NSD1, clone 1NW-1A10','04-1565',7,'','',NULL,3498.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(560,'Anti-RNA polymerase II subunit B1,','04-1569',7,'','',NULL,3264.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(561,'Anti-RNA polymerase II subunit B1 (phosp','04-1570',7,'','',NULL,3604.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(562,'x-RNA plymrs II sbnt B1 clone 4E12','04-1570-I',7,'','',NULL,3264.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(563,'Anti-RNA polymerase II CTD phospho','04-1571',7,'','',NULL,3721.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(564,'X-RNA plmrs II sbnt B1 clone 3E1','04-1571-I',7,'','',NULL,3264.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(565,'X-RNA plymrs II sbnt B1 clone 3E8','04-1572-I',7,'','',NULL,3264.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(566,'Anti-Ataxin-7, clone 3SCA-1C1','04-1573',7,'','',NULL,3381.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(567,'Anti-Brutons tyrosine kinase (Btk),','04-1578',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(568,'Anti-Interleukin 13 (IL-13), clone 4B3','04-1579',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(569,'Anti-CD137L/4-1BBL, clone TKS-1','04-158',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(570,'Anti-IL-6R-alpha, clone 1C2','04-1581',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(571,'Anti-Interleukin 23 (IL-23) p40 clon','04-1582',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(572,'Anti-Interleukin 21 (IL-21), clone 1G8','04-1583',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(573,'Anti-Interleukin 2 (IL-2), clone 3F9','04-1584',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(574,'Anti-IL-18, clone 12E7.1','04-1585',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(575,'Anti-CD153/CD30L, clone RM153','04-159',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(576,'Anti-CCR9, clone 9B1','04-1590',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(577,'Anti-Activation-induced cytidine deamina','04-1593',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(578,'Anti-NLRP9 (NALP9), clone 19G3.1','04-1595',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(579,'Anti-Interleukin 15 (IL-15), clone 1H3','04-1599',7,'','',NULL,2913.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(580,'Anti-Centrin, clone 20H5','04-1624',7,'','',NULL,3264.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(581,'Anti-phospho-ATF2 (Thr71)','04-199',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(582,'Anti-ATM','04-200',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(583,'Anti-phospho-Cdc25c (Ser216)','04-204',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(584,'Anti-CENP A, CT','04-205',7,'','',NULL,3966.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(585,'Anti-Chk1, NT','04-207',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(586,'Anti-c-Jun, CT','04-210',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(587,'Anti-c-Jun, NT','04-211',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(588,'Anti-phospho-c-Jun (Ser63)','04-212',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(589,'Anti-phospho-c-Jun (Ser73)','04-213',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(590,'Anti-c-Kit','04-214',7,'','',NULL,2908.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(591,'Anti-CKRB/CK11','04-215',7,'','',NULL,3030.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(592,'Anti-c-Myc, NT','04-216',7,'','',NULL,0.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(593,'Anti-phospho-c-Myc (Thr58/Ser62)','04-217',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(594,'Anti-CREB','04-218',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(595,'Anti-Cyclin B1, CT','04-220',7,'','',NULL,3147.00,'1','2015-02-05 20:43:03','1','2015-02-05 20:43:03',NULL,'0'),
	(596,'Anti-Cyclin D1','04-221',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(597,'Anti-Cyclin E1','04-222',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(598,'Anti-Cyclin E2','04-223',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(599,'Anti-eEF2, CT','04-225',7,'','',NULL,2908.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(600,'Anti-Elk1','04-226',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(601,'Anti-ER alpha','04-227',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(602,'Anti-HDAC2, CT','04-229',7,'','',NULL,3498.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(603,'Anti-HDAC3, CT','04-230',7,'','',NULL,3498.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(604,'Anti-HDAC4, CT','04-232',7,'','',NULL,3498.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(605,'Anti-NFk-B p105/p50','04-234',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(606,'Anti-NF-kB p65 subunit','04-235',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(607,'Anti-Nibrin','04-236',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(608,'Anti-Catenin p120','04-237',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(609,'Anti-p16/INK4a','04-239',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(610,'Anti-Kip1 (p27), CT','04-240',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(611,'Anti-p53','04-241',7,'','',NULL,3966.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(612,'Anti-p53, CT','04-242',7,'','',NULL,3966.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(613,'Anti-phospho-p53 (Ser392), clone EP155y','04-244',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(614,'20S Proteasome Subunit alpha7, MS X HU','04-246',7,'','',NULL,4368.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(615,'20S Proteasome Subunit Beta3, MS X HU','04-250',7,'','',NULL,4368.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(616,'20S Proteasome Subunit Beta5i, MS X HU','04-251',7,'','',NULL,4326.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(617,'19S Regulator ATPaseSubunit S6a/Rpt5,mAb','04-253',7,'','',NULL,4368.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(618,'19S Regulator ATPaseSubunit S6b/Rpt3,mAb','04-254',7,'','',NULL,4368.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(619,'19S Regulator ATPase Subunit S7/Rpt1,mAb','04-255',7,'','',NULL,4368.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(620,'19S Reg. ATPase Subunit S10b/Rpt4,mAb','04-257',7,'','',NULL,4326.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(621,'19S Reg non-ATPase Subunit S5a/Rpn10,mAb','04-259',7,'','',NULL,4125.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(622,'19S Reg non-ATPase Subunit S14/Rpn12,mAb','04-260',7,'','',NULL,4368.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(623,'Ubiquitinylated proteins,mAb clone FK1','04-262',7,'','',NULL,4490.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(624,'Ubiquitinylated proteins,mAb clone FK2','04-263',7,'','',NULL,4490.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(625,'Lyn, Ms x 10A6.2-200uL','04-268',7,'','',NULL,2913.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(626,'Heptp Ms x PTPN7','04-278',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(627,'anti-phospho-EGFR (Thr669), clone 5F10','04-281',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(628,'Anti-phospho-EGFR (Thr669), clone 5','04-281-KL',7,'','',NULL,26430.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(629,'anti-phospho-EGFR (Thr654), clone 3F2','04-282',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(630,'anti-phospho-EGFR (Tyr845), clone 12A3','04-283',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(631,'anti-phospho-EGFR (Tyr1045), clone 11C2','04-284',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(632,'anti-phospho-EGFR (Tyr1086), clone 8B8','04-286',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(633,'anti-EGFR, C-term, clone 13GB','04-290',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(634,'anti-erbB2 (cyto domain), clone 24B5','04-291',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(635,'anti-phospho-erbB2 (Ser1113), clone 9E10','04-292',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(636,'anti-phospho-erbB2 (Thr686), clone 7F8','04-293',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(637,'anti-phospho-erbB2 (Tyr1112), clone 19G5','04-294',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(638,'anti-IGF1R (C-term), clone 7G11','04-298',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(639,'anti-phospho-IR(Ty1150/1151), clone 10C3','04-299',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(640,'anti-phospho-IR(Tyr1322), clone 21G12','04-300',7,'','',NULL,3517.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(641,'anti-4E-BP1, rabbit mono','04-321',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(642,'anti-Acetyl CoA Carboxylase 1','04-322',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(643,'anti-AMPK alpha-1','04-323',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(644,'anti-AMPK beta-1, rb mono','04-324',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(645,'anti-B-Raf, rb mono.','04-328',7,'','',NULL,3395.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(646,'anti-phospho-Cbl (Tyr774), Rb mono','04-334',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(647,'anti-EGF Receptor, rb mono','04-337',7,'','',NULL,2936.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(648,'anti-EGFR (C-term), Rb mono','04-338',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(649,'anti-phospho-EGF Receptor (Tyr1068)','04-339',7,'','',NULL,2880.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(650,'anti-phospho-EGF Receptor (Tyr1086)','04-340',7,'','',NULL,2880.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(651,'anti-phospho-EGFR (Tyr1173), rb mono','04-341',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(652,'anti-phospho-eIF-2a (Ser51), rb mono','04-342',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(653,'anti-eIF-5A, rabbit monoclonal','04-343',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(654,'anti-eIF-4E (C-term), Rb mono','04-347',7,'','',NULL,3273.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(655,'anti-ErbB4/HER4 (C-term)','04-348',7,'','',NULL,2880.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(656,'anti-Erk2 p42 MAPK (C-term)','04-349',7,'','',NULL,2880.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(657,'anti-Glycogen Synthase (C-term), Rb mono','04-357',7,'','',NULL,3517.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(658,'anti-GSK3 alpha','04-360',7,'','',NULL,2880.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(659,'anti-GSK3 beta (C-term)','04-361',7,'','',NULL,2880.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(660,'anti-IKK alpha (N-term)','04-365',7,'','',NULL,2880.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(661,'anti-IKK beta','04-366',7,'','',NULL,2880.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(662,'anti-IRAK-3, rb mono','04-367',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(663,'anti-IRAK-4 (N-term)','04-368',7,'','',NULL,2913.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(664,'anti-IRR alpha subunit, Rb mono','04-369',7,'','',NULL,3273.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(665,'anti-Lck/p56','04-372',7,'','',NULL,2913.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(666,'anti-phospho-Lyn  (Tyr507), Rb mono','04-375',7,'','',NULL,3517.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(667,'anti-MEK1 (C-term)','04-376',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(668,'anti-MEK2 (N-term), rb mono','04-377',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(669,'anti-MEKK2, rb mono','04-378',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(670,'anti-MKK3 (N-term)','04-381',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(671,'anti-MKK4 (C-term)','04-382',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(672,'anti-MKK6 (N-term)','04-383',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(673,'anti-phospho-MSK1 (Ser376), rb mono','04-384',7,'','',NULL,3517.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(674,'anti-mTOR, rb mono','04-385',7,'','',NULL,3517.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(675,'anti-MYPT1/MYPT2 (C-term)','04-386',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(676,'anti-NAK/TBK1 (N-term)','04-387',7,'','',NULL,2913.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(677,'anti-p70 S6 Kinase (N-term), rb mono','04-391',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(678,'anti-phospho-p70 S6K (Thr421/Ser424)','04-393',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(679,'anti-PAK3, Rb mono','04-395',7,'','',NULL,3264.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(680,'anti-PDGFRb, rb mono','04-397',7,'','',NULL,2880.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(681,'anti-PI3 Kinase, p110alpha, rb mono','04-399',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(682,'anti-PI3 Kinase, p110beta, rb mono','04-400',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(683,'Anti-P13 Kinase,p110beta, rb mono','04-400-K',7,'','',NULL,52451.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(684,'anti-PI3 Kinase, p110delta, rb mono','04-401',7,'','',NULL,3517.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(685,'anti-PI3 Kinase, p110gamma, rb mono','04-402',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(686,'anti-PI3 Kinase, p85alpha, rb mono','04-403',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(687,'anti-phospho-PKA, RII(Ser96)','04-404',7,'','',NULL,0.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(688,'anti-PKC beta II','04-406',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(689,'anti-PTEN (C-term), rb mono','04-409',7,'','',NULL,3517.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(690,'anti-Raf1, Rb mono','04-412',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(691,'anti-Rap1GAP, rb mono','04-413',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(692,'anti-RSK1, rb mono','04-417',7,'','',NULL,2908.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(693,'anti-phospho-RSK1 (Ser380), Rb mono','04-418',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(694,'anti-phospho-Rsk1(Thr359/Ser363),Rb mono','04-419',7,'','',NULL,3760.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(695,'Anti-phospho-Rsk1(Thr359/Ser363) -ML','04-419-K',7,'','',NULL,31122.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(696,'anti-TSC1, Rb mono','04-426',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(697,'anti-TSC2, Rb mono','04-427',7,'','',NULL,3152.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(698,'anti-AIF rabbit monoclonal','04-430',7,'','',NULL,3849.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(699,'anti-BAD (N-term) rabbit monoclonal','04-432',7,'','',NULL,3849.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(700,'anti-Bak rabbit monoclonal','04-433',7,'','',NULL,3966.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(701,'anti-Bax rabbit monoclonal','04-434',7,'','',NULL,3849.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(702,'anti-Bcl-10 rabbit monoclonal','04-435',7,'','',NULL,3849.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(703,'anti-Bcl-2 rabbit monoclonal','04-436',7,'','',NULL,3849.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(704,'anti-Bcl-6 (C-term) rabbit monoclonal','04-437',7,'','',NULL,3849.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(705,'anti-Bid rabbit monoclonal','04-438',7,'','',NULL,3849.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(706,'anti-Caspase-3 (active) rb mono','04-439',7,'','',NULL,3732.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(707,'anti-Caspase-3 (Pro) rb monoclonal','04-440',7,'','',NULL,3966.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(708,'anti-Caspase-7 rabbit monoclonal','04-441',7,'','',NULL,3849.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(709,'anti-Caspase-9 (Pro) rb monoclonal','04-443',7,'','',NULL,4083.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(710,'anti-Daxx rabbit monoclonal','04-445',7,'','',NULL,3849.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(711,'anti-phospho-hsp27 (Ser78), rb mono','04-447',7,'','',NULL,4490.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(712,'Anti-phospho-hsp27(Ser78), rb mono- ML','04-447-K',7,'','',NULL,40739.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(713,'anti-phospho-HSP27 (Ser82) rb mono','04-448',7,'','',NULL,4368.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(714,'anti-MIP-1 beta/CCL4 (C-term) rb mono','04-449',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(715,'anti-TRAF6 rabbit monoclonal','04-451',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(716,'anti-TRAF6 (C-term) rabbit monoclonal','04-452',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(717,'anti-SUMO-1 rabbit monoclonal','04-453',7,'','',NULL,4368.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(718,'anti-Ubiquitin (C-term) rb monoclonal','04-454',7,'','',NULL,4368.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(719,'anti- CD20 (C-term) rb monoclonal','04-455',7,'','',NULL,3273.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(720,'anti-CD22 rabbit monoclonal','04-456',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(721,'anti-phospho CD22 (Tyr807) rb mono','04-457',7,'','',NULL,3273.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(722,'anti-phospho CD22 (Tyr822) rb mono','04-458',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(723,'anti-phospho CD22 (Tyr842) rb mono','04-459',7,'','',NULL,3517.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(724,'anti-CD3 rabbit monoclonal','04-460',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(725,'anti-C-reactive protein(C-term) rb mono','04-461',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(726,'anti-IL-1 beta rabbit monoclonal','04-462',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(727,'anti-IL-15 rabbit monoclonal','04-463',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(728,'anti-IL-17 rabbit monoclonal','04-464',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(729,'anti-ILR1 rabbit monoclonal','04-465',7,'','',NULL,3030.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(730,'anti-LAT rabbit monoclonal','04-466',7,'','',NULL,3273.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(731,'anti-phospho-LAT(Tyr191) rb mono','04-467',7,'','',NULL,3638.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(732,'Sterol Regulatory Protein,CRL2121-100ug','04-469',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(733,'Farnesyl Transferase,Ms x CRL2418-100ug','04-470',7,'','',NULL,3121.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(734,'anti-phospho-STAT1 (Ser727), clone 12C5','04-478',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(735,'SREBP-2, Ms x CRL 2545-200uL','04-479',7,'','',NULL,3147.00,'1','2015-02-05 20:43:04','1','2015-02-05 20:43:04',NULL,'0'),
	(736,'RhoG, Ms X 1F3B3E5, 100 ug','04-486',7,'','',NULL,3121.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(737,'Ms X, MAPKAP K2, clone 7H4.2 - 200ug','04-539',7,'','',NULL,3121.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(738,'Anti-phospho-p53 (Ser6)','04-540',7,'','',NULL,3966.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(739,'Acetyl-HistoneH4(K5-12),Rbx147-51-5','04-557',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(740,'Caspase-2, Rbt x, 100ul','04-571',7,'','',NULL,3849.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(741,'Caspase-8, Rbt x, 100ul','04-573',7,'','',NULL,3732.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(742,'Caspase-8 (N Term), Rbt x, 100ul','04-574',7,'','',NULL,3615.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(743,'PARP-1 (P116), Rbt x, 100ul','04-575',7,'','',NULL,4083.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(744,'PARP-1 (Cleaved p25), Rbt x, 100ul','04-576',7,'','',NULL,3732.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(745,'RasGap, Rbt x, 100ul','04-577',7,'','',NULL,3030.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(746,'Smac/Diablo, Rbt x, 100ul','04-578',7,'','',NULL,3615.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(747,'Apaf-1, Rbt x, 100ul','04-579',7,'','',NULL,4083.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(748,'MALT/MLT1 (N Term), Rbt x, 100ul','04-580',7,'','',NULL,3760.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(749,'Paxillin (N Term), Rbt x, 100ul','04-581',7,'','',NULL,3966.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(750,'PAK1 (N Term), Rbt x, 100ul','04-582',7,'','',NULL,4083.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(751,'PAK4 (N Term), Rbt x, 100ul','04-583',7,'','',NULL,4083.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(752,'PAK1, Rbt x, 100ul','04-584',7,'','',NULL,3966.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(753,'Desmin, Rbt x, 100ul','04-585',7,'','',NULL,3966.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(754,'Cytokeratin 18, Rbt x, 100ul','04-586',7,'','',NULL,3615.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(755,'Cytokeratin 5, Rbt x, 100ul','04-587',7,'','',NULL,3732.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(756,'Cytokeratin 8, Rbt x, 100ul','04-588',7,'','',NULL,3732.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(757,'Calponin-1 (C Term), Rbt x, 100ul','04-589',7,'','',NULL,3264.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(758,'Caldesmon, Rbt x, 100ul','04-590',7,'','',NULL,4083.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(759,'FAK protein, Rbt x, 100ul','04-591',7,'','',NULL,4083.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(760,'Flotillin-1, Rbt x, 100ul','04-592',7,'','',NULL,4125.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(761,'JAM1 (N Term), Rbt x, 100ul','04-593',7,'','',NULL,3615.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(762,'CBX-8, Ms x 1H10.2-100ug','04-632',7,'','',NULL,3147.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(763,'Anti-TRF1','04-638',7,'','',NULL,3966.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(764,'PP2A B56 delta, Ms X H5D12, 100 uL','04-639',7,'','',NULL,3724.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(765,'Anti-LAP2alpha-100ug','04-640',7,'','',NULL,3147.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(766,'anti-Ago2, clone 9E8.2','04-642',7,'','',NULL,3966.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(767,'DICER, Ms x 5D12.2-100ug','04-721',7,'','',NULL,3147.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(768,'A-MONOMETH-HISH4(K20),NL314 100ul','04-735',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(769,'A-PHOS-AKT1 (SER473),CL SK703  100','04-736',7,'','',NULL,3483.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(770,'ANTI-RAF-1, PC 12-305  100ul','04-739',7,'','',NULL,3483.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(771,'ANTI-SHP-1/2,  PC 12-301','04-742',7,'','',NULL,3264.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(772,'A-TRIMETH-HISH3  (K4),CL MC315 100','04-745',7,'','',NULL,4785.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(773,'A-PH-HIS  H3 (THR3), CL JY325  100','04-746',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(774,'A-PH-MEK1/2  (218/222)/(222/226)  100ul','04-747',7,'','',NULL,3001.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(775,'A-CREB,  CL NL904 PC 12-309','04-767',7,'','',NULL,3147.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(776,'A-DIMETHYL-HIS  H3 (K9)   100UL','04-768',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(777,'A-AKT2/PKBB,   CL AW114  100UL','04-771',7,'','',NULL,3121.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(778,'ANTI-SRC,   CT, PC 12-301','04-772',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(779,'Anti-phospho-MYPT1 (Thr850), clone','04-773',7,'','',NULL,3121.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(780,'ANTI-HA-RAS, PC 12-301  100ul','04-775',7,'','',NULL,3001.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(781,'A-IRS1,  CL AW58 PC 12-305  100ul','04-784',7,'','',NULL,3483.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(782,'A-PH-PKD  (SER916),CL MC29 100UL','04-787',7,'','',NULL,3001.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(783,'Anti-phospho-Histone H3 Thr11 clone MC83','04-789',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(784,'A-DIMETHYL-HIS  H3 (LYS4) 100UL','04-790',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(785,'A-DIMETHYL-HIS H3(LYS4), -1MG','04-790P-KL',7,'','',NULL,42003.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(786,'A-  MONO/DI/TRIMTHYL-HISH3(K4)  100ul','04-791',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(787,'A-PH-CENP-A (S7),  CL NL41 100UL','04-792',7,'','',NULL,4434.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(788,'A-PHOS-RIBOSOMAL   PROTS6(S235)  100ul','04-795',7,'','',NULL,3001.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(789,'A-AKT1/PKBA,   CL AW24   100UL','04-796',7,'','',NULL,3001.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(790,'A-P-MAPK/ERK1/2 (THR185/TYR187)100','04-797',7,'','',NULL,3121.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(791,'a-trimethyl-His H3 (Lys36)    100ul','04-801',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(792,'A-PH-AKT1/PKBA   (THR308)  100UL','04-802',7,'','',NULL,3121.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(793,'ANTI-HMGN2 monoclonal antibody','04-803',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(794,'NFAT1, mouse monoclonal antibody','04-804',7,'','',NULL,3147.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(795,'Anti-SET7/Set9, clone 5F2.3','04-805',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(796,'A-DIMETHYL-HISTONE H3 (ARG2)  200UL','04-808',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(797,'Anti-trimethyl (Lys9)-phospho (Ser1','04-809',7,'','',NULL,4200.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(798,'A-PH-ENOS/NOS III (THR495)','04-811',7,'','',NULL,4434.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(799,'A-PH-PPARγ (S82), CL AW504  100UL','04-816',7,'','',NULL,3147.00,'1','2015-02-05 20:43:05','1','2015-02-05 20:43:05',NULL,'0'),
	(800,'A-PH-HIS  H3 (S10), CL MC463 100UL','04-817',7,'','',NULL,4551.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(801,'A-ESTROGEN RECEPTOR A,CL.60C  100ul','04-820',7,'','',NULL,3147.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(802,'a-dimethyl-His H3(K27),cl614M 100ul','04-821',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(803,'ANTI-RHO  (-A,-B,-C),CL 3L74','04-822',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(804,'ANTI-PHOSPHO-GLUR1  (SER831) N453','04-823',7,'','',NULL,4785.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(805,'A-ESTROGEN  REC B, CL 68-4  100UL','04-824',7,'','',NULL,3147.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(806,'A-PDGF RB , CLONE 4A56        100UL','04-825',7,'','',NULL,3483.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(807,'A-DIMETHYL-HIS   H3 (LYS79)  100ul','04-835',7,'','',NULL,4551.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(808,'A-ROKA/ROCK-II,  CLONE A9W4  200ul','04-841',7,'','',NULL,3498.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(809,'ANTI-GLUR1, CLONE C3T - 100UL','04-855',7,'','',NULL,4785.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(810,'ANTI-TBK1, CLONE AOW9  100ul','04-856',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(811,'ANTI-TBK1, CLONE AOW9 -ML','04-856-K',7,'','',NULL,29917.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(812,'Anti-phospho-Src family (Tyr416), c','04-857',7,'','',NULL,3615.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(813,'Anti-Histone H4, pan          100ul','04-858',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(814,'a-ph-STAT5 (Y694/699),cl A11W 100ul','04-886',7,'','',NULL,3147.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(815,'Anti-Src, clone N6L           100ul','04-889',7,'','',NULL,3615.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(816,'a-JNK3/SAPK1b, clone C05T     100ul','04-893',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(817,'a-p-KDR/Flk-1/VEGFR2(Y1054)   100ul','04-894',7,'','',NULL,3483.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(818,'a-p-KDR/Flk-1/VEGFR2(Y1054) - ML','04-894-K',7,'','',NULL,37253.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(819,'a-p-MET (Y1234/1235),cl NL8   100ul','04-900',7,'','',NULL,3001.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(820,'Anti-NR2A, clone A12W 100UL','04-901',7,'','',NULL,4668.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(821,'a-HA Tag, clone DW2           100ul','04-902',7,'','',NULL,4317.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(822,'Anti-GSK3a/b  100ul','04-903',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(823,'Anti-Smad2/3, clone C4T  100ul','04-914',7,'','',NULL,3147.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(824,'Anti-GluR6/7, clone NL9       100ul','04-921',7,'','',NULL,4434.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(825,'Anti-Hist H3, CT, pan, cl A3S   100ul','04-928',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(826,'Anti-Calmodulin Binding Protein Epi','04-932',7,'','',NULL,4317.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(827,'Anti-Phos SMAD2 (S465/467) cl A5S  100ul','04-953',7,'','',NULL,3147.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(828,'Anti-Beta Catenin, clone 7F7.2','04-958',7,'','',NULL,3001.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(829,'Anti-Retinoid-Related Orphan Recept','04-959',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(830,'Anti-FOXP3, clone 3G3','04-960',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(831,'Anti-Interleukin 12 (IL-12), clone 8.6','04-961',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(832,'Anti-Interleukin 12 (IL-12), clone 17.8','04-962',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(833,'Anti-CTLA4, clone 9H10','04-963',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(834,'Anti-Interleukin 4 (IL-4), clone 11.B.11','04-964',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(835,'Anti-Cby1, clone 8-2','04-966',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(836,'Anti-Desert hedgehog protein (DHH),','04-967',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(837,'Anti-Folr4 (Folate receptor 4), clo','04-968',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(838,'Anti-Osteopontin','04-970',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(839,'Anti-Sonic Hedgehog,C-terminus, clone EP','04-971',7,'','',NULL,2936.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(840,'Anti-CD39, clone 5F2','04-973',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(841,'Anti-phospho-FAK (Tyr397), clone EP2160Y','04-974',7,'','',NULL,3615.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(842,'Anti-HDAC1, clone 2E10  (mouse monoclona','05-100',7,'','',NULL,4551.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(843,'anti-phosphoserine, clone 4A4, 100ug','05-1000',7,'','',NULL,4447.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(844,'anti-phosphoserine, mouse monoclona','05-1000MG',7,'','',NULL,36258.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(845,'Anti-Phosphoserine, clone 4A4, 50ug','05-1000X',7,'','',NULL,2760.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(846,'Anti-phospho-Akt (Ser473)','05-1003',7,'','',NULL,3483.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(847,'Anti-phospho-EGFR (Tyr1173), mono','05-1004',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(848,'ANTI EGF REC. NEUT PC 12-301','05-101',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(849,'ANTI-EGFR NEUT, -MG','05-101-KL',7,'','',NULL,12121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(850,'phospho MAPKAP K2/3, Ms x, 9F10.2 100mg','05-1028',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(851,'ANTI-EGF  RECEPTR PC 12-301','05-104',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(852,'ANTI-SIN1,CLONE 1C7.2','05-1044',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(853,'anti-EGFR, cytoplasmic domain','05-1047',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(854,'anti-Met extracellular domain mouse mono','05-1049',7,'','',NULL,3001.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(855,'4G10 Platinum, 100 uL (+PC 12-302)','05-1050',7,'','',NULL,4326.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(856,'4G10 Platinum, BULK','05-1050-K',7,'','',NULL,35650.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(857,'4G10 Platinum, 30uL','05-1050X',7,'','',NULL,2760.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(858,'anti-ILK, CLONE 6G6.3 mouse mono-100UG','05-1051',7,'','',NULL,3615.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(859,'Anti-phospho-PTEN (Ser380)','05-1056',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(860,'ANTI-Phospho CRKL (pY207)','05-1058',7,'','',NULL,3615.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(861,'Anti-phospho-p38a(Thr180/Tyr182)','05-1059',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(862,'ANTI-phospho STAT1 (Y701)','05-1064',7,'','',NULL,3147.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(863,'Anti-Insulin','05-1066',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(864,'anti-phospho-PRAS40 (Thr246)','05-1069',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(865,'Anti-PRAS40, clone 9D10.2','05-1070',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(866,'anti-Ras (H-,N-), clone 1A6.2','05-1071',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(867,'anti-Ras (K-,H-,N-), clone 9A11.2','05-1072',7,'','',NULL,4326.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(868,'anti-Ras (K-,H-,N-), clone 9A11.2 - 30ug','05-1072SP',7,'','',NULL,952.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(869,'Anti-FOXO1, clone 2H8.2','05-1075',7,'','',NULL,3205.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(870,'Anti-c-Jun, clone 6E4.4','05-1076',7,'','',NULL,3147.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(871,'Anti-STAT3, clone 18F7.1','05-1078',7,'','',NULL,3147.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(872,'Anti-IRS1','05-1085',7,'','',NULL,3724.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(873,'Anti- IRS Custom (Purified, PBS only)','05-1085NA-K',7,'','',NULL,33521.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(874,'Anti-phospho-IRS1 (Ser302)','05-1086',7,'','',NULL,3965.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(875,'Anti-phospho-IRS1 (Ser307)','05-1087',7,'','',NULL,3965.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(876,'Anti-B-Raf, clone 4B3.2','05-1094',7,'','',NULL,3844.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(877,'Anti-acetyl CoA Carboxylase, clone 7D2.2','05-1098',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(878,'Anti-Sulfotyrosine Monoclonal','05-1100',7,'','',NULL,4326.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(879,'Anti-Sulfotyrosine, Clone Sulfo-1C-A2','05-1100-K',7,'','',NULL,40739.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(880,'Anti-Insulin Receptor( ß-Subunit), clone','05-1104',7,'','',NULL,3333.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(881,'Anti-IGF-1R, ß-subunit, clone 1-2 (','05-1106',7,'','',NULL,3205.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(882,'Anti-Insulin, clone CE9H9 (mouse mo','05-1107',7,'','',NULL,2913.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(883,'Anti-Insulin clone MAB1 mouse monoclonal','05-1108',7,'','',NULL,3147.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(884,'Anti-Pro-Insulin C-Peptide, clone C-P-01','05-1109',7,'','',NULL,3462.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(885,'Anti-Harmartin (TSC1) (mouse monoclonal)','05-1113',7,'','',NULL,3030.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(886,'Anti-SG2NA (mouse monoclonal)','05-1115',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(887,'Anti-VEGF clone CH-10 (mouse monoclonal)','05-1116',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(888,'Anti-VEGF, clone VG1 (mouse monoclonal)','05-1117',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(889,'Anti-erbB-2,Cytoplasmic Domain, clone N3','05-1130',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(890,'Anti-erbB-3/HER-3, clone RTJ1/2E11','05-1131',7,'','',NULL,3001.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(891,'Anti-erbB-4/HER-4, clone HFR1/2G4 (','05-1133',7,'','',NULL,3121.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(892,'Anti-Fibroblast Growth Factor Receptor 1','05-1134',7,'','',NULL,3483.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(893,'Anti-PDGFR-ß clone CH-3 mouse monoclonal','05-1135',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(894,'Anti-Focal Adhesion Kinase, cloneBLAb2H7','05-1139',7,'','',NULL,3381.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(895,'Anti-phospho-Focal Adhesion Kinase (Tyr3','05-1140',7,'','',NULL,3381.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(896,'Anti-phospho-Paxillin (Tyr31), cloneM102','05-1143',7,'','',NULL,3381.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(897,'Anti-Talin, C-terminus,  clone TD77','05-1144',7,'','',NULL,3264.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(898,'Anti-ADP-Ribosylation Factor 6, clone 6A','05-1149',7,'','',NULL,3719.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(899,'Anti-Erk1/2 (p44/p42), clone MK12 (ms)','05-1152',7,'','',NULL,3483.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(900,'Anti-JAK1, clone 73 (mouse monoclonal)','05-1154',7,'','',NULL,2880.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(901,'FGF-2/Basic FGF, MSX','05-117',7,'','',NULL,3517.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(902,'Anti-FGF-2/basic FGF (neutralizing)','05-117-K',7,'','',NULL,6224.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(903,'ANTI  FGF2 BFGF, CL BFM2  500UG','05-118',7,'','',NULL,3517.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(904,'ANTI  FGF2 BFGF, CL BFM2         MG','05-118-K',7,'','',NULL,6763.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(905,'Anti-FXR2, clone 2C8.2','05-1214',7,'','',NULL,3966.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(906,'Anti-Progerin (Lamin A/C), clone 13A4','05-1231',7,'','',NULL,3264.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(907,'Acetyl Histone H4 (Lys16), Ms x 4E1','05-1232',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(908,'Anti-FMR1, clone 7H3.2','05-1235',7,'','',NULL,3030.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(909,'Anti-PARN, clone 7A7.1','05-1238',7,'','',NULL,3030.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(910,'Anti-trimethyl-Hist H3 (Lys9), 6F12-H4','05-1242',7,'','',NULL,4551.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(911,'Anti-trimethylHistoneH3(Lys9),TrialSize','05-1242-S',7,'','',NULL,924.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(912,'Anti-Sirt1, clone 3H10.2-Mouse 100ug','05-1243',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(913,'Anti-O-GlcNAc, clone 18B10.C7(3)','05-1244',7,'','',NULL,4085.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(914,'Anti-O-GlcNAc, clone 9D1.E4(10)','05-1245',7,'','',NULL,4085.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(915,'Anti-O-GlcNAc, clone 1F5.D6(14)','05-1246',7,'','',NULL,4085.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(916,'Anti-monomethyl Histone H3 (Lys9), clone','05-1248',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(917,'Anti-dimethyl Histone H3(Lys9), Clo','05-1249',7,'','',NULL,4551.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(918,'Anti-dimethyl Histone H3 (Lys9). Cl','05-1249-K',7,'','',NULL,41933.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(919,'Anti-trimethyl Hist H3 (Lys9), CMA308','05-1250',7,'','',NULL,4551.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(920,'Anti-ubiquitin,  Lys48 specific','05-1307',7,'','',NULL,4688.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(921,'Anti-Ubiquitin, Lys48 Custom PBS only','05-1307P-K',7,'','',NULL,79069.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(922,'Anti-ubiquitin,  Lys63 specific','05-1308',7,'','',NULL,4688.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(923,'ANTI-UBIQUITIN, LYS63 SPECIFIC, BULK','05-1308-K',7,'','',NULL,35474.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(924,'Anti-Histone H2B Ubiqutin Monoclonal','05-1312',7,'','',NULL,4551.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(925,'ANTI-Ubiquitin (Lys 63), clone HWA4C4','05-1313',7,'','',NULL,4085.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(926,'Anti-phospho(Ser10)acetyl (Lys14)Histone','05-1315',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(927,'Anti-SUZ12, clone 2AO9','05-1317',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(928,'Anti-EZH2, clone BD43','05-1319',7,'','',NULL,4434.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(929,'Anti-EED, clone AA19','05-1320',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(930,'Anti-BMI1, clone AF27','05-1321',7,'','',NULL,4668.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(931,'Anti-BMI1, clone DC9','05-1322',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(932,'Anti-phospho-Histone H1, clone 12D11','05-1324',7,'','',NULL,4200.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(933,'Anti-PHD2, clone 76a','05-1327',7,'','',NULL,3604.00,'1','2015-02-05 20:43:06','1','2015-02-05 20:43:06',NULL,'0'),
	(934,'Anti-PHD3, clone 188e','05-1328',7,'','',NULL,3604.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(935,'Anti acetyl Histone H3Lys27 clone CMA309','05-1334',7,'','',NULL,4200.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(936,'Anti-acetyl_Histone H3 (Lys27), clo','05-1334-K',7,'','',NULL,43138.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(937,'Anti-phospho Histone H3(Ser10), clo','05-1336',7,'','',NULL,4200.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(938,'Anti-phospho-H3(Ser10),cl CMA31,TSize','05-1336-S',7,'','',NULL,924.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(939,'Anti dimethyl Histone H3(Lys4), clo','05-1338',7,'','',NULL,4551.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(940,'Anti trimethyl Hist H3(Lys4), CMA304','05-1339',7,'','',NULL,4551.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(941,'AntitrimethylHistoneH3(Lys4)cl CMA304TS','05-1339-S',7,'','',NULL,924.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(942,'Anti Histone H3 monoclonal, clone CMA301','05-1341',7,'','',NULL,4785.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(943,'Anit-Histone H3, clone CMA301','05-1341-K',7,'','',NULL,41933.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(944,'AntiHistoneH3(UnmodLys4)clCMA301Trial','05-1341-S',7,'','',NULL,924.30,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(945,'Anti-Histone H2B, clone 5HH2-2A8','05-1352',7,'','',NULL,4200.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(946,'Anti-Dimethyl (Lys9)-Phospho (Ser10) His','05-1354',7,'','',NULL,4200.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(947,'Anti-acetyl Histone H4 (Lys5/8/12/1','05-1355',7,'','',NULL,4200.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(948,'Anti-Retinoid X Receptor a, clone 4E9','05-1359',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(949,'Anti-RBMS1,  clone 4D11','05-1360',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(950,'Anti-PURA,  clone 2B7','05-1361',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(951,'Anti-RING1, clone 4D6','05-1362',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(952,'Anti-RXRG, clone 1E3','05-1363',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(953,'Anti-Gia2, clone L5 (mouse monoclonal)','05-1403',7,'','',NULL,2880.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(954,'Anti-PLA2, clone CH-7 (mouse monoclonal)','05-1406',7,'','',NULL,3001.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(955,'Anti-CD45, clone F10-89-4 (mouse mo','05-1410',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(956,'Anti-CD45RA, Clone MEM 56 (mouse mo','05-1413',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(957,'Anti-CD45 clone IBL-5/25 (rat monocl','05-1416',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(958,'Anti-PTP-PEST, clone AG25 (mouse mo','05-1417',7,'','',NULL,2880.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(959,'Anti-Grb14, clone 24 (mouse monoclonal)','05-1420',7,'','',NULL,3030.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(960,'Anti-Transforming Growth Factor-ß, clone','05-1423',7,'','',NULL,2913.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(961,'Anti-Endoglin, clone P3D1 (mouse mo','05-1424',7,'','',NULL,3483.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(962,'Anti-14-3-3, 33 kDa isoforms, clone 8C3','05-1425',7,'','',NULL,2913.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(963,'Anti-SLP 76 clone AS55(mouse monoclonal)','05-1426',7,'','',NULL,2880.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(964,'Anti-ARF1, clone 3F1, C-terminus (m','05-1427',7,'','',NULL,3001.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(965,'A HU  SECRETORY PLA2','05-143',7,'','',NULL,2880.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(966,'Anti-Neurofibromin, clone NFn27b (m','05-1430',7,'','',NULL,2913.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(967,'Anti-Casein Kinase 2a, clone 1AD9 (','05-1431',7,'','',NULL,3001.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(968,'Anti -SMAD1','05-1459',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(969,'ANTI-WTAPE (Src family)','05-1461',7,'','',NULL,3264.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(970,'Anti-FOXA1, clone 2F83','05-1466',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(971,'Anti-NF Kappa B p65, clone 1G10.2','05-1469',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(972,'Anti-Raptor, clone 1H6.2','05-1470',7,'','',NULL,3001.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(973,'Anti-Rictor, clone 9F1.2','05-1471',7,'','',NULL,2880.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(974,'ANTI-REST, CLONE 7D1.3,  100UG','05-1477',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(975,'Anti-RUNX2, clone AS110','05-1478',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(976,'anti-GbL/mLST8,clone 3E1.2','05-1482',7,'','',NULL,2880.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(977,'Anti-Zizimin1','05-1483',7,'','',NULL,2880.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(978,'A HU FGF RECEPTOR,MS','05-149',7,'','',NULL,3724.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(979,'Anti-Mad1, clone 9B10','05-1500',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(980,'Anti-MAD2A, clone 17D10','05-1501',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(981,'Anti-CENP-E, clone 177','05-1502',7,'','',NULL,3498.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(982,'Anti-Aly/REF, clone 11G5','05-1510',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(983,'Anti-Y14, clone 4C4','05-1511',7,'','',NULL,3030.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(984,'Anti-FMR1, clone 6A15','05-1512',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(985,'Anti-FXR2, clone A42','05-1513',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(986,'Anti-Transportin 1, clone D45','05-1515',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(987,'Anti-hnRNP U, clone 3G6','05-1516',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(988,'Anti-hnRNP Q, clone 18E4','05-1517',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(989,'Anti-hnRNP L, clone 4D11','05-1518',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(990,'Anti-hnRNP K/J, clone 3C2','05-1519',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(991,'Anti-HhRNP C1/C2,clone 4F4','05-1520',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(992,'Anti-hnRNP A1, clone 4B10','05-1521',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(993,'anti-HnRNP A2/B1','05-1522',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(994,'Anti-Nuclear RNA export factor 1, c','05-1524',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(995,'Anti-Exportin T, clone LOS1','05-1525',7,'','',NULL,3030.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(996,'Anti-Importin subunit alpha-1, clone 8G5','05-1526',7,'','',NULL,3030.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(997,'Anti-eIF4A3, clone 3F1','05-1527',7,'','',NULL,2880.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(998,'Anti-FXR1, clone 6BG10','05-1529',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(999,'Anti-Importin beta-1, clone 3H14','05-1530',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0'),
	(1000,'Anti-TATA-box-binding protein, clon','05-1531',7,'','',NULL,3147.00,'1','2015-02-05 20:43:07','1','2015-02-05 20:43:07',NULL,'0');

/*!40000 ALTER TABLE `st_product` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_product_brand
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_product_brand`;

CREATE TABLE `st_product_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品品牌编码',
  `brand_name` varchar(256) NOT NULL COMMENT '产品品牌名称',
  `brand_name_unique` varchar(256) DEFAULT NULL COMMENT '产品品牌名称唯一标识',
  `buy_discount` decimal(10,2) DEFAULT NULL COMMENT '采购折扣',
  `weight_discount` decimal(10,2) DEFAULT NULL COMMENT '加权折扣',
  `limit_discount` decimal(10,2) DEFAULT NULL COMMENT '最高限价折扣',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品品牌表';

LOCK TABLES `st_product_brand` WRITE;
/*!40000 ALTER TABLE `st_product_brand` DISABLE KEYS */;

INSERT INTO `st_product_brand` (`id`, `brand_name`, `brand_name_unique`, `buy_discount`, `weight_discount`, `limit_discount`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	(1,'苹果','苹果1',0.90,0.50,0.75,NULL,NULL,'1','2014-12-13 22:00:06',NULL,'0'),
	(2,'立法','苹果2',0.50,2121.00,2121.00,NULL,NULL,'1','2014-12-13 21:59:58',NULL,'0'),
	(3,'TEEE','苹果3',0.90,0.50,0.75,NULL,NULL,NULL,NULL,NULL,'0'),
	(5,'2121','苹果5',0.80,2121.00,2121.00,'1','2014-12-13 22:34:37','1','2014-12-13 22:34:37',NULL,'0'),
	(7,'发哥','苹果6',0.90,121.00,2121.00,'1','2015-01-28 20:22:58','1','2015-01-28 20:22:58',NULL,'0');

/*!40000 ALTER TABLE `st_product_brand` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_product_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_product_order`;

CREATE TABLE `st_product_order` (
  `register_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品订单编码',
  `user_order_id` int(11) NOT NULL COMMENT '客户订单编码',
  `area_id` varchar(64) NOT NULL COMMENT '受理地市',
  `user_id` varchar(64) NOT NULL DEFAULT '' COMMENT '客户编码',
  `product_id` int(11) NOT NULL COMMENT '产品编码',
  `order_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '目录价',
  `order_num` int(11) NOT NULL DEFAULT '0' COMMENT '订购数量',
  `audit_cd` varchar(2) NOT NULL COMMENT '审批状态:0未处理 1.待审批;2.已驳回;3.审批通过;',
  `status_cd` varchar(2) NOT NULL COMMENT '状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消',
  `invoice_status` varchar(2) NOT NULL COMMENT '状态:0.初始 1.未开具发票 2.已开具发票3.已送发票',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  `change_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `delivery_date` datetime DEFAULT NULL,
  `point_balance_fee` int(11) DEFAULT NULL,
  `price_paid_fee` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`register_number`),
  KEY `st_product_order_i` (`user_order_id`),
  KEY `st_product_order_ii` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品订单信息表';

LOCK TABLES `st_product_order` WRITE;
/*!40000 ALTER TABLE `st_product_order` DISABLE KEYS */;

INSERT INTO `st_product_order` (`register_number`, `user_order_id`, `area_id`, `user_id`, `product_id`, `order_fee`, `order_num`, `audit_cd`, `status_cd`, `invoice_status`, `accept_date`, `change_date`, `delivery_date`, `point_balance_fee`, `price_paid_fee`)
VALUES
	(1,1,'2','6',1,40.00,2,'3','3','3','2015-02-12 20:18:40','2015-02-12 21:10:04','2015-02-12 21:03:52',190,'78.1'),
	(2,1,'2','6',2,50.00,4,'3','3','3','2015-02-12 20:18:40','2015-02-12 21:12:45','2015-02-12 21:12:24',190,'198.1'),
	(3,1,'2','6',3,5.00,2,'3','3','3','2015-02-12 20:18:40','2015-02-12 21:10:35','2015-02-12 21:03:52',500,'5.0'),
	(4,1,'2','6',102,35.50,6,'3','3','3','2015-02-12 20:18:40','2015-02-12 21:12:45','2015-02-12 21:12:24',0,'213.0'),
	(5,1,'2','6',103,45.65,2,'3','3','3','2015-02-12 20:18:40','2015-02-12 21:12:45','2015-02-12 21:12:24',0,'91.3'),
	(6,1,'2','6',104,50.06,2,'3','3','3','2015-02-12 20:18:40','2015-02-12 21:12:45','2015-02-12 21:12:24',0,'100.12');

/*!40000 ALTER TABLE `st_product_order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_product_order_his
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_product_order_his`;

CREATE TABLE `st_product_order_his` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `register_number` int(11) NOT NULL COMMENT '产品订单编码',
  `order_result` varchar(1024) NOT NULL DEFAULT '' COMMENT '类型结果：1.审批状态 2产品订单项状态 3发票状态',
  `accept_person` varchar(64) DEFAULT NULL COMMENT '创建者',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `st_product_order_his_i` (`register_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品订单操作历史表';

LOCK TABLES `st_product_order_his` WRITE;
/*!40000 ALTER TABLE `st_product_order_his` DISABLE KEYS */;

INSERT INTO `st_product_order_his` (`id`, `register_number`, `order_result`, `accept_person`, `accept_date`)
VALUES
	(1,1,'修改订购价格：修改前：50.06 修改后：40.0','4','2015-02-12 20:19:25'),
	(2,2,'修改订购价格：修改前：50.06 修改后：50.0','4','2015-02-12 20:19:25'),
	(3,3,'修改订购价格：修改前：5.0 修改后：5.0','4','2015-02-12 20:19:25'),
	(4,4,'修改订购价格：修改前：50.06 修改后：35.5','4','2015-02-12 20:19:25'),
	(5,5,'修改订购价格：修改前：50.06 修改后：45.65','4','2015-02-12 20:19:25'),
	(6,6,'修改订购价格：修改前：50.06 修改后：50.06','4','2015-02-12 20:19:25'),
	(7,1,'产品订单状态更改为待审批','6','2015-02-12 20:28:06'),
	(8,2,'产品订单状态更改为待审批','6','2015-02-12 20:28:06'),
	(9,3,'产品订单状态更改为待审批','6','2015-02-12 20:28:06'),
	(10,4,'产品订单状态更改为待审批','6','2015-02-12 20:28:06'),
	(11,5,'产品订单状态更改为待审批','6','2015-02-12 20:28:06'),
	(12,6,'产品订单状态更改为待审批','6','2015-02-12 20:28:06'),
	(13,1,'审批结果为:3','8','2015-02-12 20:37:28'),
	(14,2,'审批结果为:2','8','2015-02-12 20:37:37'),
	(15,3,'审批结果为:3','8','2015-02-12 20:37:41'),
	(16,4,'审批结果为:2','8','2015-02-12 20:37:46'),
	(17,5,'审批结果为:3','8','2015-02-12 20:37:55'),
	(18,6,'审批结果为:3','8','2015-02-12 20:37:55'),
	(19,4,'修改产品订单项，将数量更改为：4','6','2015-02-12 20:38:33'),
	(20,2,'修改产品订单项，将数量更改为：2','6','2015-02-12 20:38:35'),
	(21,2,'审批结果为:2','8','2015-02-12 20:39:01'),
	(22,4,'审批结果为:2','8','2015-02-12 20:39:05'),
	(23,2,'修改产品订单项，将数量更改为：4','6','2015-02-12 20:39:31'),
	(24,4,'修改产品订单项，将数量更改为：6','6','2015-02-12 20:39:50'),
	(25,2,'审批结果为:3','8','2015-02-12 20:40:05'),
	(26,4,'审批结果为:3','8','2015-02-12 20:40:07'),
	(27,1,'导师下单，实收价：78.1 使用积分：190','8','2015-02-12 20:51:40'),
	(28,2,'导师下单，实收价：198.1 使用积分：190','8','2015-02-12 20:51:40'),
	(29,3,'导师下单，实收价：5 使用积分：500','8','2015-02-12 20:51:40'),
	(30,4,'导师下单，实收价：213 使用积分：0','8','2015-02-12 20:51:40'),
	(31,5,'导师下单，实收价：91.3 使用积分：0','8','2015-02-12 20:51:40'),
	(32,6,'导师下单，实收价：100.12 使用积分：0','8','2015-02-12 20:51:40'),
	(33,0,'产品订单状态更改为已出单!','4','2015-02-12 21:02:20'),
	(34,0,'销售人员送货','5','2015-02-12 21:02:20'),
	(35,0,'产品订单状态更改为已出单!','4','2015-02-12 21:02:25'),
	(36,0,'销售人员送货','5','2015-02-12 21:02:25'),
	(37,1,'产品订单状态更改为已收货!','4','2015-02-12 21:03:52'),
	(38,3,'产品订单状态更改为已收货!','4','2015-02-12 21:03:52'),
	(39,1,'产品订单发票状态更改为已开具发票!','9','2015-02-12 21:04:50'),
	(40,1,'销售人员送发票','5','2015-02-12 21:04:50'),
	(41,2,'产品订单发票状态更改为已开具发票!','9','2015-02-12 21:04:50'),
	(42,2,'销售人员送发票','5','2015-02-12 21:04:50'),
	(43,3,'产品订单发票状态更改为已开具发票!','9','2015-02-12 21:04:50'),
	(44,3,'销售人员送发票','5','2015-02-12 21:04:50'),
	(45,4,'产品订单发票状态更改为已开具发票!','9','2015-02-12 21:04:50'),
	(46,4,'销售人员送发票','5','2015-02-12 21:04:50'),
	(47,5,'产品订单发票状态更改为已开具发票!','9','2015-02-12 21:04:50'),
	(48,5,'销售人员送发票','5','2015-02-12 21:04:50'),
	(49,6,'产品订单发票状态更改为已开具发票!','9','2015-02-12 21:04:50'),
	(50,6,'销售人员送发票','5','2015-02-12 21:04:50'),
	(51,1,'产品订单发票状态更改为已送发票!','9','2015-02-12 21:05:22'),
	(52,2,'产品订单发票状态更改为已送发票!','9','2015-02-12 21:05:22'),
	(53,3,'产品订单发票状态更改为已送发票!','9','2015-02-12 21:05:22'),
	(54,4,'产品订单发票状态更改为已送发票!','9','2015-02-12 21:05:22'),
	(55,5,'产品订单发票状态更改为已送发票!','9','2015-02-12 21:05:22'),
	(56,6,'产品订单发票状态更改为已送发票!','9','2015-02-12 21:05:22'),
	(57,1,'产品订单状态更改为已结款','9','2015-02-12 21:10:04'),
	(58,3,'产品订单状态更改为已结款','9','2015-02-12 21:10:35'),
	(59,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(60,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(61,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(62,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(63,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(64,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(65,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(66,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(67,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(68,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(69,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(70,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(71,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(72,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(73,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(74,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(75,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(76,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(77,0,'产品订单状态更改为已出单!','4','2015-02-12 21:12:14'),
	(78,0,'销售人员送货','5','2015-02-12 21:12:14'),
	(79,2,'产品订单状态更改为已收货!','4','2015-02-12 21:12:24'),
	(80,4,'产品订单状态更改为已收货!','4','2015-02-12 21:12:24'),
	(81,5,'产品订单状态更改为已收货!','4','2015-02-12 21:12:24'),
	(82,6,'产品订单状态更改为已收货!','4','2015-02-12 21:12:24'),
	(83,2,'产品订单状态更改为已结款','9','2015-02-12 21:12:45'),
	(84,4,'产品订单状态更改为已结款','9','2015-02-12 21:12:45'),
	(85,5,'产品订单状态更改为已结款','9','2015-02-12 21:12:45'),
	(86,6,'产品订单状态更改为已结款','9','2015-02-12 21:12:45');

/*!40000 ALTER TABLE `st_product_order_his` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_product_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_product_record`;

CREATE TABLE `st_product_record` (
  `record_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品订单编码',
  `user_record_id` int(11) NOT NULL COMMENT '客户记录编码',
  `area_id` varchar(64) NOT NULL COMMENT '受理地市',
  `user_id` int(11) NOT NULL COMMENT '客户编码',
  `product_id` int(11) NOT NULL COMMENT '产品编码',
  `order_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订购价',
  `order_num` int(11) NOT NULL DEFAULT '0' COMMENT '订购数量',
  `supplier_id` int(11) NOT NULL COMMENT '供应商ID',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`record_number`),
  KEY `st_product_record_i` (`user_record_id`),
  KEY `st_product_record_ii` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录产品订单信息表';

LOCK TABLES `st_product_record` WRITE;
/*!40000 ALTER TABLE `st_product_record` DISABLE KEYS */;

INSERT INTO `st_product_record` (`record_number`, `user_record_id`, `area_id`, `user_id`, `product_id`, `order_fee`, `order_num`, `supplier_id`, `accept_date`)
VALUES
	(1,1,'2',6,1,100.00,2,102,'2015-02-12 21:14:21'),
	(2,1,'2',6,2,50.00,4,103,'2015-02-12 21:14:21');

/*!40000 ALTER TABLE `st_product_record` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_stock_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_stock_order`;

CREATE TABLE `st_stock_order` (
  `stock_order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '备货订单编码',
  `user_id` varchar(64) NOT NULL COMMENT '客户编码',
  `area_id` varchar(64) NOT NULL COMMENT '受理地市',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status_cd` varchar(2) NOT NULL COMMENT '状态:1.处理中；2.已完成；-1取消',
  PRIMARY KEY (`stock_order_id`),
  KEY `st_stock_order_i` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='备货订单信息表';

LOCK TABLES `st_stock_order` WRITE;
/*!40000 ALTER TABLE `st_stock_order` DISABLE KEYS */;

INSERT INTO `st_stock_order` (`stock_order_id`, `user_id`, `area_id`, `accept_date`, `status_cd`)
VALUES
	(1,'10','1','2015-02-12 21:46:56','1');

/*!40000 ALTER TABLE `st_stock_order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_stock_product_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_stock_product_order`;

CREATE TABLE `st_stock_product_order` (
  `stock_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '备货产品订单编码',
  `stock_order_id` int(11) NOT NULL COMMENT '备货订单编码',
  `area_id` varchar(64) NOT NULL COMMENT '受理地市',
  `user_id` varchar(64) NOT NULL COMMENT '备货人编码',
  `product_id` int(11) NOT NULL COMMENT '产品编码',
  `brand_id` int(11) NOT NULL COMMENT '产品品牌编码',
  `stock_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '备货价',
  `supplier_id` int(11) NOT NULL COMMENT '供应商编码',
  `if_user_oder_transition` varchar(2) NOT NULL COMMENT '是否客户订单转备货单：0.否 1.是',
  `register_number` int(11) DEFAULT NULL COMMENT '产品订单编码',
  `user_order_id` int(11) DEFAULT NULL COMMENT '客户订单编码',
  `order_num` int(11) NOT NULL DEFAULT '0' COMMENT '备货数量',
  `status_cd` varchar(2) NOT NULL COMMENT '状态:1.待采购 2.已分配 3.已采购 4.已入库',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  `change_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `if_auto_allot` varchar(2) NOT NULL COMMENT '是否自动分配:0.否 1.是',
  `allot_user_id` varchar(64) DEFAULT NULL COMMENT '分配人',
  `buyer_user_id` varchar(64) DEFAULT NULL COMMENT '采购人',
  PRIMARY KEY (`stock_number`),
  KEY `st_stock_product_order_ii` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='备货产品订单信息表';

LOCK TABLES `st_stock_product_order` WRITE;
/*!40000 ALTER TABLE `st_stock_product_order` DISABLE KEYS */;

INSERT INTO `st_stock_product_order` (`stock_number`, `stock_order_id`, `area_id`, `user_id`, `product_id`, `brand_id`, `stock_fee`, `supplier_id`, `if_user_oder_transition`, `register_number`, `user_order_id`, `order_num`, `status_cd`, `accept_date`, `change_date`, `if_auto_allot`, `allot_user_id`, `buyer_user_id`)
VALUES
	(1,1,'1','10',105,7,3148.20,1,'0',NULL,NULL,1,'4','2015-02-12 21:46:56','2015-02-12 21:53:42','1',NULL,'10'),
	(2,1,'1','10',106,7,6833.70,1,'0',NULL,NULL,1,'4','2015-02-12 21:46:56','2015-02-12 21:53:42','1',NULL,'10'),
	(3,1,'1','10',107,7,811.80,1,'0',NULL,NULL,1,'4','2015-02-12 21:46:56','2015-02-12 21:53:42','1',NULL,'10'),
	(4,1,'1','10',108,7,927.00,1,'0',NULL,NULL,1,'3','2015-02-12 21:46:56','2015-02-12 21:48:15','0','12','12');

/*!40000 ALTER TABLE `st_stock_product_order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_supplier
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_supplier`;

CREATE TABLE `st_supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `supplier_name` varchar(100) NOT NULL COMMENT '供应商名称',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商表';

LOCK TABLES `st_supplier` WRITE;
/*!40000 ALTER TABLE `st_supplier` DISABLE KEYS */;

INSERT INTO `st_supplier` (`id`, `supplier_name`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	(1,'TEST1',NULL,NULL,NULL,NULL,NULL,'0'),
	(2,'TEST2',NULL,NULL,NULL,NULL,NULL,'0');

/*!40000 ALTER TABLE `st_supplier` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_user_beans
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_user_beans`;

CREATE TABLE `st_user_beans` (
  `user_id` varchar(64) NOT NULL COMMENT '客户编号',
  `beans_num` int(11) NOT NULL COMMENT '豆豆数量',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户豆豆信息表';

LOCK TABLES `st_user_beans` WRITE;
/*!40000 ALTER TABLE `st_user_beans` DISABLE KEYS */;

INSERT INTO `st_user_beans` (`user_id`, `beans_num`)
VALUES
	('6',373);

/*!40000 ALTER TABLE `st_user_beans` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_user_group_point_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_user_group_point_account`;

CREATE TABLE `st_user_group_point_account` (
  `user_group_id` varchar(64) NOT NULL COMMENT '客户组编号',
  `point_balance` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `account_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户组积分账户信息表';

LOCK TABLES `st_user_group_point_account` WRITE;
/*!40000 ALTER TABLE `st_user_group_point_account` DISABLE KEYS */;

INSERT INTO `st_user_group_point_account` (`user_group_id`, `point_balance`, `account_fee`)
VALUES
	('6',101175,0.00);

/*!40000 ALTER TABLE `st_user_group_point_account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_user_group_point_his
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_user_group_point_his`;

CREATE TABLE `st_user_group_point_his` (
  `point_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `group_id` varchar(64) NOT NULL COMMENT '客户组别编码',
  `point_type` varchar(2) NOT NULL COMMENT '操作类型:1.使用积分 2.累积积分',
  `point_balance` int(11) NOT NULL DEFAULT '0' COMMENT '本次使用或累计积分数',
  `register_number` int(11) NOT NULL COMMENT '累积积分时是客户订购产品的订单编号；使用积分时是客户积分兑换时使用的订单编号',
  `accept_person` varchar(64) DEFAULT NULL COMMENT '创建者',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`point_id`),
  KEY `st_user_group_point_his_i` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户组积分历史表';

LOCK TABLES `st_user_group_point_his` WRITE;
/*!40000 ALTER TABLE `st_user_group_point_his` DISABLE KEYS */;

INSERT INTO `st_user_group_point_his` (`point_id`, `group_id`, `point_type`, `point_balance`, `register_number`, `accept_person`, `accept_date`)
VALUES
	(1,'6','1',190,1,'8','2015-02-12 20:51:40'),
	(2,'6','1',190,2,'8','2015-02-12 20:51:40'),
	(3,'6','1',500,3,'8','2015-02-12 20:51:40'),
	(4,'6','2',234,1,'9','2015-02-12 21:10:04'),
	(5,'6','2',15,3,'9','2015-02-12 21:10:35'),
	(6,'6','2',594,2,'9','2015-02-12 21:12:45'),
	(7,'6','2',639,4,'9','2015-02-12 21:12:45'),
	(8,'6','2',273,5,'9','2015-02-12 21:12:45'),
	(9,'6','2',300,6,'9','2015-02-12 21:12:45');

/*!40000 ALTER TABLE `st_user_group_point_his` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_user_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_user_order`;

CREATE TABLE `st_user_order` (
  `user_order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户订单编码',
  `user_id` varchar(64) NOT NULL COMMENT '客户编码',
  `area_id` varchar(64) NOT NULL COMMENT '受理地市',
  `group_id` varchar(64) NOT NULL COMMENT '客户组别编码',
  `address_id` int(11) NOT NULL COMMENT '地址编码',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status_cd` varchar(2) NOT NULL COMMENT '状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1 已取消',
  `teacher_order_time` datetime DEFAULT NULL COMMENT '导师下单时间',
  PRIMARY KEY (`user_order_id`),
  KEY `st_user_order_i` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户订单信息表';

LOCK TABLES `st_user_order` WRITE;
/*!40000 ALTER TABLE `st_user_order` DISABLE KEYS */;

INSERT INTO `st_user_order` (`user_order_id`, `user_id`, `area_id`, `group_id`, `address_id`, `accept_date`, `status_cd`, `teacher_order_time`)
VALUES
	(1,'6','2','6',1,'2015-02-12 20:18:40','7','2015-02-12 20:51:40');

/*!40000 ALTER TABLE `st_user_order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_user_peas_his
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_user_peas_his`;

CREATE TABLE `st_user_peas_his` (
  `peas_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_id` varchar(64) NOT NULL COMMENT '客户编码',
  `peas_type` varchar(2) NOT NULL COMMENT '操作类型:1.累积积分2.使用积分',
  `peas_balance` int(11) NOT NULL DEFAULT '0' COMMENT '本次使用或累计豆豆数',
  `register_number` int(11) DEFAULT NULL COMMENT '累积豆豆时是客户订购产品的订单编号；使用豆豆时是客户积分兑换时使用的订单编号',
  `gift_id` int(11) DEFAULT NULL COMMENT '兑换礼品ID',
  `accept_person` varchar(64) DEFAULT NULL COMMENT '创建者',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`peas_id`),
  KEY `st_user_peas_his_i` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户豆豆历史表';

LOCK TABLES `st_user_peas_his` WRITE;
/*!40000 ALTER TABLE `st_user_peas_his` DISABLE KEYS */;

INSERT INTO `st_user_peas_his` (`peas_id`, `user_id`, `peas_type`, `peas_balance`, `register_number`, `gift_id`, `accept_person`, `accept_date`)
VALUES
	(1,'6','1',78,1,NULL,'9','2015-02-12 21:10:04'),
	(2,'6','1',5,3,NULL,'9','2015-02-12 21:10:35'),
	(3,'6','1',198,2,NULL,'9','2015-02-12 21:12:45'),
	(4,'6','1',213,4,NULL,'9','2015-02-12 21:12:45'),
	(5,'6','1',91,5,NULL,'9','2015-02-12 21:12:45'),
	(6,'6','1',100,6,NULL,'9','2015-02-12 21:12:45'),
	(7,'6','2',212,NULL,109,'6','2015-02-12 21:13:33'),
	(8,'6','2',100,NULL,1,'6','2015-02-12 21:13:41');

/*!40000 ALTER TABLE `st_user_peas_his` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table st_user_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_user_record`;

CREATE TABLE `st_user_record` (
  `user_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户记录编码',
  `user_id` varchar(64) NOT NULL COMMENT '客户编码',
  `area_id` varchar(64) NOT NULL COMMENT '受理地市',
  `group_id` varchar(64) NOT NULL COMMENT '客户组别编码',
  `address_id` int(11) NOT NULL COMMENT '地址编码',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户记录信息表';

LOCK TABLES `st_user_record` WRITE;
/*!40000 ALTER TABLE `st_user_record` DISABLE KEYS */;

INSERT INTO `st_user_record` (`user_record_id`, `user_id`, `area_id`, `group_id`, `address_id`, `accept_date`)
VALUES
	(1,'6','2','6',1,'2015-02-12 21:14:21');

/*!40000 ALTER TABLE `st_user_record` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_area
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_area`;

CREATE TABLE `sys_area` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '编号',
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
	(1,'0','0,','100000','中国','1','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(2,'1','0,1,','410000','辽宁省','2','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(3,'1','0,1,','320000','吉林省','2','1','2014-11-05 22:18:39','1','2014-11-05 22:18:39','','0'),
	(4,'1','0,1,','380000','黑龙江省','2','1','2014-11-05 22:17:56','1','2014-11-05 22:17:56','','0'),
	(20,'2','0,1,2,','410411','大连市','3','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(30,'3','0,1,3,','320001','长春市','3','1','2014-11-05 22:19:00','1','2014-11-05 22:19:00','','0'),
	(40,'4','0,1,4,','380001','哈尔滨市','3','1','2014-11-05 22:18:23','1','2014-11-05 22:18:23','','0');

/*!40000 ALTER TABLE `sys_area` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_dict
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '编号',
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
	(1,'正常','0','del_flag','删除标记',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(2,'删除','1','del_flag','删除标记',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(3,'显示','1','show_hide','显示/隐藏',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(4,'隐藏','0','show_hide','显示/隐藏',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(5,'是','1','yes_no','是/否',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(6,'否','0','yes_no','是/否',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(12,'默认主题','default','theme','主题方案',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(17,'国家','1','sys_area_type','区域类型',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(18,'省份、直辖市','2','sys_area_type','区域类型',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(19,'地市','3','sys_area_type','区域类型',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(20,'区县','4','sys_area_type','区域类型',40,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(21,' 组','1','sys_office_type','机构类型',60,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(25,'一级','1','sys_office_grade','机构等级',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(26,'二级','2','sys_office_grade','机构等级',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(32,'仅本人数据','1','sys_data_scope','数据范围',90,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(34,'系统管理','1','sys_user_type','用户类型',15,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(35,'部门经理','2','sys_user_type','用户类型',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(36,'普通用户','3','sys_user_type','用户类型',5,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(37,'外部用户','1','sys_user_catagory','用户类别',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(38,'内部用户','2','sys_user_catagory','用户类别',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(45,'权限模块','1','module','模块类型',20,'1','2013-06-03 00:00:00','1','2013-05-27 00:00:00','','0'),
	(46,'订单模块','2','module','模块类型',10,'1','2013-06-03 00:00:00','1','2013-05-27 00:00:00','','0'),
	(53,'咨询','1','cms_guestbook','留言板分类',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(54,'建议','2','cms_guestbook','留言板分类',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(55,'投诉','3','cms_guestbook','留言板分类',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(56,'其它','4','cms_guestbook','留言板分类',40,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(62,'接入日志','1','sys_log_type','日志类型',30,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0'),
	(63,'异常日志','2','sys_log_type','日志类型',40,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0'),
	(64,'菜单属性','1','sys_menu_type','菜单属性',10,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0'),
	(65,'安全属性','2','sys_menu_type','安全属性',20,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','','0'),
	(67,'学生','1','sys_role_sign','特定角色属性',10,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','student','0'),
	(68,'导师','2','sys_role_sign','特定角色属性',20,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','professor','0'),
	(69,'PI','3','sys_role_sign','特定角色属性',30,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','pi','0'),
	(70,'采购','4','sys_role_sign','特定角色属性',40,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','caigou','0'),
	(71,'销售','5','sys_role_sign','特定角色属性',50,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','sale','0'),
	(72,'财务','6','sys_role_sign','特定角色属性',60,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','caiwu','0'),
	(73,'公司级管理员','7','sys_role_sign','特定角色属性',70,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','admin','0'),
	(74,'系统管理员','8','sys_role_sign','特定角色属性',80,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','sysadmin','0'),
	(75,'内勤','9','sys_role_sign','特定角色属性',90,'1','2013-06-03 00:00:00','1','2013-06-03 00:00:00','neiqin','0'),
	(76,'技术支持','10','sys_role_sign','特定角色属性',100,'1','2013-06-03 00:00:00','1',NULL,'support','0'),
	(100,'内部组','1','sys_group_catagory','组类别',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(101,'外部组','2','sys_group_catagory','组类别',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(102,'未开始兑换','0','sys_gift_status','礼品状态',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(103,'开始兑换','1','sys_gift_status','礼品状态',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(104,'兑换结束','2','sys_gift_status','礼品状态',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0');

/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '编号',
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



# Dump of table sys_mdict
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_mdict`;

CREATE TABLE `sys_mdict` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '编号',
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
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '编号',
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
	(1,'0','0,','1','顶级菜单','','','',0,'1',NULL,'','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	(2,'1','0,1,','1','系统设置','','','',900,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(3,'2','0,1,2,','1','系统设置','','','',980,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(4,'3','0,1,2,3,','1','菜单管理','/sys/menu/','','list-alt',30,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(5,'4','0,1,2,3,4,','1','查看','','','',30,'0',NULL,'sys:menu:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(6,'4','0,1,2,3,4,','1','修改','','','',30,'0',NULL,'sys:menu:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(7,'3','0,1,2,3,','1','角色管理','/sys/role/','','lock',50,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(8,'7','0,1,2,3,7,','1','查看','','','',30,'0',NULL,'sys:role:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(9,'7','0,1,2,3,7,','1','修改','','','',30,'0',NULL,'sys:role:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(10,'3','0,1,2,3,','1','字典管理','/sys/dict/','','th-list',60,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(11,'10','0,1,2,3,10,','1','查看','','','',30,'0',NULL,'sys:dict:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(12,'10','0,1,2,3,10,','1','修改','','','',30,'0',NULL,'sys:dict:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(13,'2','0,1,2,','1','机构用户','','','',970,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(14,'13','0,1,2,13,','1','区域管理','/sys/area/','','th',50,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(15,'14','0,1,2,13,14,','1','查看','','','',30,'0',NULL,'sys:area:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(16,'14','0,1,2,13,14,','1','修改','','','',30,'0',NULL,'sys:area:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(17,'13','0,1,2,13,','1','机构管理','/sys/office/','','th-large',40,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(18,'17','0,1,2,13,17,','1','查看','','','',30,'0',NULL,'sys:office:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(19,'17','0,1,2,13,17,','1','修改','','','',30,'0',NULL,'sys:office:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(20,'13','0,1,2,13,','1','用户管理','/sys/user/','','user',30,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(21,'20','0,1,2,13,20,','1','查看','','','',30,'0',NULL,'sys:user:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(22,'20','0,1,2,13,20,','1','修改','','','',30,'0',NULL,'sys:user:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(27,'1','0,1,','1','我的面板','','','',500,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(28,'27','0,1,27,','1','个人信息','','','',990,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(29,'28','0,1,27,28,','1','个人信息','/sys/user/info','','user',30,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(30,'28','0,1,27,28,','1','修改密码','/sys/user/modifyPwd','','lock',40,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	(39,'1','0,1,','1','订单管理','',NULL,NULL,1,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),
	(40,'39','0,1,39,','1','数据管理',NULL,NULL,NULL,10,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),
	(41,'40','0,1,39,40,','1','产品管理','/order/product/',NULL,NULL,10,'1',NULL,'',NULL,NULL,NULL,NULL,NULL,'0'),
	(42,'41','0,1,39,40,41,','1','查看','',NULL,NULL,20,'1',NULL,'order:product:view',NULL,NULL,NULL,NULL,NULL,'0'),
	(43,'41','0,1,39,40,41,','1','修改','',NULL,NULL,30,'1',NULL,'order:product:edit',NULL,NULL,NULL,NULL,NULL,'0'),
	(45,'40','0,1,39,40,','1','品牌管理','/order/brand/',NULL,NULL,10,'1',NULL,'',NULL,NULL,NULL,NULL,NULL,'0'),
	(46,'45','0,1,39,40,45,','1','查看','',NULL,NULL,20,'1',NULL,'order:brand:view',NULL,NULL,NULL,NULL,NULL,'0'),
	(47,'45','0,1,39,40,45,','1','修改','',NULL,NULL,30,'1',NULL,'order:brand:edit',NULL,NULL,NULL,NULL,NULL,'0'),
	(48,'40','0,1,39,40,','1','礼品管理','/order/gift/',NULL,NULL,10,'1',NULL,'',NULL,NULL,NULL,NULL,NULL,'0'),
	(49,'48','0,1,39,40,48,','1','查看','',NULL,NULL,20,'1',NULL,'order:gift:view',NULL,NULL,NULL,NULL,NULL,'0'),
	(50,'48','0,1,39,40,48,','1','修改','',NULL,NULL,30,'1',NULL,'order:gift:edit',NULL,NULL,NULL,NULL,NULL,'0'),
	(51,'40','0,1,39,40,','1','经销商管理','/order/agent/',NULL,NULL,10,'1',NULL,'',NULL,NULL,NULL,NULL,NULL,'0'),
	(52,'48','0,1,39,40,51,','1','查看','',NULL,NULL,20,'1',NULL,'order:agent:view',NULL,NULL,NULL,NULL,NULL,'0'),
	(53,'48','0,1,39,40,51,','1','修改','',NULL,NULL,30,'1',NULL,'order:agent:edit',NULL,NULL,NULL,NULL,NULL,'0'),
	(67,'2','0,1,2,','1','日志查询','','','',985,'1',NULL,'','1','2013-06-03 00:00:00','1','2014-12-03 11:20:08','','0'),
	(68,'67','0,1,2,67,','1','日志查询','/sys/log','','pencil',30,'1',NULL,'sys:log:view','1','2013-06-03 00:00:00','1','2014-12-03 11:20:08','','0'),
	(111,'1','0,1,','2','我的操作','','','thumbs-up',0,'1',1,'perms[order:orderlist:all],authc','1','2014-11-13 20:28:00','1','2014-12-03 11:20:08',NULL,'0'),
	(120,'1','0,1,','2','个人信息','','','',100,'1',1,'perms[address:list]','1','2014-11-10 09:10:25','1','2014-12-03 11:20:08',NULL,'0'),
	(130,'1','0,1,','2','我的资产','','','',3,'1',1,'perms[order:mine:all]','1','2014-11-13 20:28:00','1','2014-12-03 11:20:08',NULL,'0'),
	(140,'1','0,1,','2','我的投诉','','','',3,'1',1,'','1','2014-11-13 20:28:00','1','2014-12-03 11:20:08',NULL,'0'),
	(1111,'111','0,1,111,','2','所有订单','/order/list/all_order/**','','',100,'1',1,'perms[order:list:all]','1','2014-11-13 20:30:08','1','2014-12-03 11:20:08',NULL,'0'),
	(1112,'111','0,1,111,','2','待审批订单','/order/list/customer/approve_view/**','','',1,'1',1,'perms[order:list:approve]','1','2014-11-13 20:29:09','1','2014-12-03 11:20:08',NULL,'0'),
	(1113,'111','0,1,111,','2','内勤议价','/order/list/inside/bargain_view/**','','',2,'1',1,'perms[order:list:bargain]','1','2014-11-13 20:30:08','1','2014-12-03 11:20:08',NULL,'0'),
	(1114,'111','0,1,111,','2','提审订单','/order/list/customer/confirm_view/**',NULL,NULL,3,'1',1,'perms[order:list:confirm]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1115,'111','0,1,111,','2','修改订单','/order/list/customer/upgrade_view/**',NULL,NULL,4,'1',1,'perms[order:list:upgrade]','1',NULL,NULL,NULL,NULL,'0'),
	(1116,'111','0,1,111,','2','取消订单','/order/list/customer/userordercancel_view/**',NULL,NULL,5,'1',1,'perms[order:list:userordercancel]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1117,'111','0,1,111,','2','提交订单','/order/list/customer/book_view/**',NULL,NULL,6,'1',1,'perms[order:list:book]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1118,'111','0,1,111,','2','内勤接单','/order/list/inside/take_view/**','','',7,'1',1,'perms[order:list:take]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1119,'111','0,1,111,','2','内勤出单','/order/list/inside/load_view/**','','',8,'1',1,'perms[order:list:load]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1120,'111','0,1,111,','2','确认收货','/order/list/inside/received_view/**','','',9,'1',1,'perms[order:list:received]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1121,'111','0,1,111,','2','开具发票','/order/list/finance/invoiced_view/**','','',10,'1',1,'perms[order:list:invoiced]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1122,'111','0,1,111,','2','送发票待结款','/order/list/finance/invoiced_nopay_view/**','','',11,'1',1,'perms[order:list:invoiced_nopay]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1123,'111','0,1,111,','2','结款','/order/list/finance/pay_view/**','','',12,'1',1,'perms[order:list:pay]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1124,'111','0,1,111,','2','采购主管分配','/order/list/buy/distribute_view/**',NULL,NULL,13,'1',1,'perms[buy:list:distribute]','1','2014-11-10 09:10:51','1','2014-12-03 11:20:08',NULL,'0'),
	(1125,'111','0,1,111,','2','已分配待采购','/order/list/buy/list/**',NULL,NULL,14,'1',1,'perms[buy:list:wait]','1','2014-11-10 09:10:51','1','2014-12-03 11:20:08',NULL,'0'),
	(1126,'111','0,1,111,','2','待入库','/order/list/buy/storage_view/**',NULL,NULL,15,'1',1,'perms[buy:list:storage]','1',NULL,NULL,NULL,NULL,'0'),
	(1127,'111','0,1,111,','2','采购查询','/order/list/buy/query_view/**',NULL,NULL,16,'1',1,'perms[buy:list:view]','1',NULL,NULL,NULL,NULL,'0'),
	(1128,'111','0,1,111,','2','采购主管查询','/order/list/buy/managed_view/**',NULL,NULL,17,'1',1,'perms[buy:list:managedview]','1',NULL,NULL,NULL,NULL,'0'),
	(1129,'111','0,1,111,','2','处理投诉','/order/list/support/complain_view/**',NULL,NULL,1,'1',1,'perms[complain:support:view]','1',NULL,NULL,NULL,NULL,'0'),
	(1130,'111','0,1,111,','2','记录查询','/order/list/record/**',NULL,NULL,18,'1',1,'perms[record:list:all]',NULL,NULL,NULL,NULL,NULL,'0'),
	(1201,'120','0,1,120,','2','地址添加','/order/address/add/**','','',110,'1',2,'perms[order:address:add]','1','2014-11-10 09:10:51','1','2014-12-03 11:20:08',NULL,'0'),
	(1202,'120','0,1,120,','2','地址管理','/order/address/list/**',NULL,NULL,120,'1',1,'perms[order:address:list]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	(1301,'130','0,1,130,','2','我的豆豆','/order/mine/beanlist/**','','',2,'1',1,'perms[order:mine:bean]','1','2014-11-13 20:29:09','1','2014-12-03 11:20:08',NULL,'0'),
	(1302,'130','0,1,130,','2','我的积分','/order/mine/pointlist/**','','',2,'1',1,'perms[order:mine:point]','1','2014-11-13 20:29:09','1','2014-12-03 11:20:08',NULL,'0'),
	(1401,'140','0,1,140,','2','投诉查询','/order/list/customer/complain_view/**',NULL,NULL,1,'1',1,'perms[order:view:complain]','1','2014-11-13 20:29:09','1','2014-12-03 11:20:08',NULL,'0'),
	(1402,'140','0,1,140,','2','进行投诉','/order/list/all_order/**',NULL,NULL,0,'1',1,'perms[order:list:all]',NULL,NULL,NULL,NULL,NULL,'0');

/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_office
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_office`;

CREATE TABLE `sys_office` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL DEFAULT '' COMMENT '所有父级编号',
  `area_id` varchar(64) NOT NULL COMMENT '归属区域',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `type` char(1) NOT NULL COMMENT '机构类型',
  `if_show` char(1) DEFAULT NULL COMMENT '是否显示下拉框',
  `catagory` char(1) DEFAULT NULL COMMENT '机构类别',
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

INSERT INTO `sys_office` (`id`, `parent_id`, `parent_ids`, `area_id`, `code`, `name`, `type`, `if_show`, `catagory`, `address`, `zip_code`, `master`, `phone`, `fax`, `email`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	(1,'0','0,','1','100000','顶级机构组织','1','1','1','','','','','','','1','2012-05-27 00:00:00','1','2012-05-27 00:00:00','','0'),
	(2,'1','0,1,','1','220000001','塞托长春分公司','1','1','1','','','','','','','1','2014-11-05 22:26:48','1','2014-11-05 22:26:48','','0'),
	(3,'1','0,1,','1','28000001','塞托黑龙江分公司','1','0','1','','','','','','','1','2014-11-05 22:25:27','1','2014-11-05 22:25:27','','0'),
	(4,'10','0,1,10,','2','410000001','内勤部','2','0','1','','','','','','','1','2014-11-05 22:24:18','1','2014-12-02 09:56:22','','0'),
	(5,'1','0,1,','2','11602405','大连理工大学物理实验室','1','1','2','大连市大连理工大学','116024','高老师','','','','1','2014-12-02 11:22:29','1','2015-02-11 11:13:48','','0'),
	(6,'5','0,1,5,','2','11678009','高老师自有公司','2','1','2','1167829','11728821','高老大','','','','1','2014-12-02 11:25:06','1','2015-02-11 11:13:48','','0'),
	(7,'1','0,1,','1',NULL,'212121','1','0',NULL,'212121','212121','212121','212121','212121','212121','1','2014-12-05 22:57:42','1','2014-12-05 22:57:42','212121','1'),
	(8,'10','0,1,10,','2','410000002','财务部','2','0','1','','','','','','','1','2014-11-05 22:24:41','1','2014-12-02 09:56:07','','0'),
	(9,'2','0,1,2,','1','280000001','采购部','2','0','1','','','','','','','1','2014-11-05 22:25:57','1','2014-11-05 22:27:58','','0'),
	(10,'1','0,1,','2','41000001','塞托大连分公司','1','0','1','','','','','','','1','2014-11-05 22:22:16','1','2014-11-05 22:22:16','','0'),
	(11,'1','0,1,','2','','下的人实验室','1','0','2','','','下的人','182829192191','','','1','2014-12-02 20:27:02','1','2014-12-02 20:27:02','','1');

/*!40000 ALTER TABLE `sys_office` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
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
	(1,'1','系统管理员','1','1','1','2012-05-27 00:00:00','1','2014-12-13 15:20:29','','0'),
	(2,'10','大连内勤','9','1','1','2014-12-02 11:47:52','1','2015-02-11 23:38:01',NULL,'0'),
	(3,'1','大连财务','6','1','1','2014-11-14 20:29:04','1','2014-12-13 15:20:21','','0'),
	(4,'6','大连导师','2','1','1','2014-12-02 11:45:06','1','2015-02-11 23:37:53',NULL,'0'),
	(5,'1','大连销售','5','5','1','2014-12-04 20:24:54','1','2014-12-04 20:24:54',NULL,'0'),
	(6,'1','大连学生','1','1','1','2012-05-27 00:00:00','1','2015-02-11 23:37:42','','0'),
	(7,'10','大连采购','4','1','1','2015-01-11 13:58:51','1','2015-01-25 14:16:41',NULL,'0'),
	(8,'10','大连采购主管','4','1','1','2015-01-12 10:04:00','1','2015-01-25 14:16:27',NULL,'0'),
	(9,'10','大连技术支持','10','1','1','2015-01-26 23:35:46','1','2015-01-27 22:42:08',NULL,'0');

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;

INSERT INTO `sys_role_menu` (`role_id`, `menu_id`)
VALUES
	(1,1),
	(1,2),
	(1,3),
	(1,4),
	(1,5),
	(1,6),
	(1,7),
	(1,8),
	(1,9),
	(1,10),
	(1,11),
	(1,12),
	(1,13),
	(1,14),
	(1,15),
	(1,16),
	(1,17),
	(1,18),
	(1,19),
	(1,20),
	(1,21),
	(1,22),
	(1,27),
	(1,28),
	(1,29),
	(1,30),
	(1,39),
	(1,40),
	(1,41),
	(1,42),
	(1,43),
	(1,45),
	(1,46),
	(1,47),
	(1,67),
	(1,68),
	(1,111),
	(1,120),
	(1,130),
	(1,140),
	(1,150),
	(1,1111),
	(1,1112),
	(1,1113),
	(1,1114),
	(1,1115),
	(1,1116),
	(1,1117),
	(1,1118),
	(1,1119),
	(1,1120),
	(1,1121),
	(1,1122),
	(1,1123),
	(1,1201),
	(1,1202),
	(1,1301),
	(1,1302),
	(1,1401),
	(2,1),
	(2,111),
	(2,1111),
	(2,1113),
	(2,1118),
	(2,1119),
	(2,1120),
	(2,1130),
	(3,1),
	(3,111),
	(3,1111),
	(3,1121),
	(3,1122),
	(3,1123),
	(4,1),
	(4,2),
	(4,13),
	(4,17),
	(4,18),
	(4,19),
	(4,111),
	(4,120),
	(4,130),
	(4,1111),
	(4,1112),
	(4,1117),
	(4,1130),
	(4,1201),
	(4,1202),
	(4,1301),
	(4,1302),
	(6,1),
	(6,111),
	(6,120),
	(6,130),
	(6,140),
	(6,1111),
	(6,1114),
	(6,1115),
	(6,1116),
	(6,1130),
	(6,1201),
	(6,1202),
	(6,1301),
	(6,1302),
	(6,1401),
	(6,1402),
	(7,1),
	(7,111),
	(7,1125),
	(7,1126),
	(7,1127),
	(8,1),
	(8,111),
	(8,1124),
	(8,1128),
	(9,1),
	(9,111),
	(9,1129);

/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_office
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_office`;

CREATE TABLE `sys_role_office` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `office_id` int(11) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`role_id`,`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-机构';



# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `company_id` varchar(64) DEFAULT NULL COMMENT '归属公司',
  `office_id` varchar(64) NOT NULL COMMENT '归属部门',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
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

INSERT INTO `sys_user` (`id`, `company_id`, `office_id`, `login_name`, `password`, `name`, `email`, `phone`, `mobile`, `qq`, `user_type`, `user_catagory`, `login_ip`, `login_date`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `forward`)
VALUES
	(1,'1','1','admin','e00cf25ad42683b3df678c61f42c6bda','管理员','dsd@163.com','8675','8675','','','1','0:0:0:0:0:0:0:1','2015-02-12 21:39:34','1','2012-05-27 00:00:00','1','2015-02-10 20:33:42','管理员','0',''),
	(2,NULL,'6','dl_st2','e00cf25ad42683b3df678c61f42c6bda','test','ddd@262.com','21212121','21212121','1992121',NULL,'1',NULL,NULL,'1','2014-12-10 21:57:16','1','2014-12-10 21:58:08','2121','1','12121'),
	(3,NULL,'1','dsds','e00cf25ad42683b3df678c61f42c6bda','211','211@162.com','121','2121','2121',NULL,'2',NULL,NULL,'1','2014-12-05 22:28:02','1','2014-12-05 22:28:02','2121','1','2121'),
	(4,'10','4','dl_nq','e00cf25ad42683b3df678c61f42c6bda','大连内勤-王万里','116@172.com','1829291201021','','','2','2',NULL,NULL,'1','2014-12-02 11:49:40','1','2014-12-13 15:22:54','','0',''),
	(5,'10','10','dl_xs','e00cf25ad42683b3df678c61f42c6bda','大连销售-无','22121@162.com','1782','100101','2121','2','2',NULL,NULL,'1','2014-12-04 20:27:28','1','2014-12-13 15:23:02','','0',''),
	(6,'5','6','dl_st','e00cf25ad42683b3df678c61f42c6bda','高老师-王学生','122@162.com','12821829121','2121','2121','2','1',NULL,NULL,'1','2014-12-02 10:42:55','1','2014-12-13 15:22:01','11','0','课题方向'),
	(7,NULL,'6','dl_st2','e00cf25ad42683b3df678c61f42c6bda','学生02','002@162.com','121219921912','2219921021021','2121210021',NULL,'1',NULL,NULL,'1','2014-12-10 22:06:54','1','2014-12-13 15:22:35','2121','0',''),
	(8,'5','6','dl_gao_admin','e00cf25ad42683b3df678c61f42c6bda','高老师','gao@162.com','129999999999','','','2','1','0:0:0:0:0:0:0:1','2015-02-04 23:23:21','1','2014-12-02 11:42:02','1','2014-12-13 15:22:19','','0',''),
	(9,'10','10','dl_cw','e00cf25ad42683b3df678c61f42c6bda','大连财务-李','1@162.com','129999999999','129999999999','1918101','2','2',NULL,NULL,'1','2014-11-05 22:54:27','1','2014-12-13 15:23:07','','0','课题方向'),
	(10,NULL,'1','dl_cg','e00cf25ad42683b3df678c61f42c6bda','大连采购－张小','22@163.com','212121','18292011212','12292993112',NULL,'2',NULL,NULL,'1','2015-01-11 14:00:05','1','2015-01-12 10:04:30','','0',''),
	(11,NULL,'10','dl_cgzg','e00cf25ad42683b3df678c61f42c6bda','大连采购主管','18829@163.com','1828920020','1881922121','819100000',NULL,'2',NULL,NULL,'1','2015-01-12 10:05:20','1','2015-01-12 10:05:20','','0',''),
	(12,NULL,'10','dl_cg_admin','e00cf25ad42683b3df678c61f42c6bda','大连采购主管-张晓曦','zhangxx@163.com','17289020021','3288392932','1889921',NULL,'2',NULL,NULL,'1','2015-01-22 15:20:49','1','2015-01-22 15:20:49','','0',''),
	(13,NULL,'10','dl_sp','e00cf25ad42683b3df678c61f42c6bda','大连-技术支持','yu@163.com','1829921','2121212121','212121',NULL,'2',NULL,NULL,'1','2015-01-26 23:42:59','1','2015-01-26 23:43:31','2121','0','2121'),
	(14,NULL,'1','221','e10adc3949ba59abbe56e057f20f883e','admin','123456@11.com','2121','2121','2121',NULL,'1',NULL,NULL,'1','2015-02-10 21:02:09','1','2015-02-10 21:02:09','2112','0','2121');

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;

INSERT INTO `sys_user_role` (`user_id`, `role_id`)
VALUES
	(1,1),
	(4,2),
	(5,5),
	(6,6),
	(7,6),
	(8,4),
	(9,3),
	(10,7),
	(11,8),
	(12,8),
	(13,9),
	(14,2);

/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
