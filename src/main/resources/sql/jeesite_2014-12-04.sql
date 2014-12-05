# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.01 (MySQL 5.6.19)
# Database: jeesite
# Generation Time: 2014-12-04 07:25:42 +0000
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
  PRIMARY KEY (`address_id`),
  KEY `st_address_i` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地址信息表';


INSERT INTO `st_address` (`address_id`, `user_id`, `receipt_address`, `receipt_person`, `contact_phone`, `invoice_caput`, `invoice_require`, `other_require`)
VALUES
	(7,'1','11121','2121','21212121','21212121','21212121','21212121'),
	(8,'1','ddsdddd','dsds','dsdsddddd','dsds','dsds','dsds'),
	(9,'76ef9deb273a49d2bb2f116dac45e877','大连市沙河口区软件园路2号','李文','13500899199','大连进出口贸易有限公司','需要将名头与地址对应','无其他要求');



# Dump of table st_audit_his
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_audit_his`;

CREATE TABLE `st_audit_his` (
  `audit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '审批记录ID',
  `register_number` int(11) NOT NULL COMMENT '产品订单编码',
  `audit_person` varchar(64) NOT NULL COMMENT '审批人',
  `audit_date` datetime DEFAULT NULL COMMENT '审批时间',
  `audit_result` varchar(64) NOT NULL COMMENT '审批结果：1.通过 2不通过',
  `turn_down_reason` varchar(64) DEFAULT NULL COMMENT '驳回原因:价格原因；品牌原因；存货原因；其他原因等',
  `turn_down_note` varchar(512) DEFAULT NULL COMMENT '驳回描述',
  `status_cd` varchar(2) NOT NULL COMMENT '状态:1.代表当前最新审批结果。0代表旧版本审批结果',
  PRIMARY KEY (`audit_id`),
  KEY `st_audit_his_i` (`register_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审批结果历史记录表';

INSERT INTO `st_audit_his` (`audit_id`, `register_number`, `audit_person`, `audit_date`, `audit_result`, `turn_down_reason`, `turn_down_note`, `status_cd`)
VALUES
	(1,40,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 13:55:13','3',NULL,NULL,'1'),
	(2,41,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 13:55:13','3',NULL,NULL,'1'),
	(3,40,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:02:47','3',NULL,NULL,'1'),
	(4,41,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:02:47','3',NULL,NULL,'1'),
	(5,40,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09','3',NULL,NULL,'1'),
	(6,41,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09','3',NULL,NULL,'1'),
	(7,35,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09','3',NULL,NULL,'1'),
	(8,36,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09','3',NULL,NULL,'1'),
	(9,37,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09','3',NULL,NULL,'1'),
	(10,38,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09','3',NULL,NULL,'1'),
	(11,39,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09','3',NULL,NULL,'1'),
	(14,40,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:08','3',NULL,NULL,'1'),
	(15,41,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:08','3',NULL,NULL,'1'),
	(16,35,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17','3',NULL,NULL,'1'),
	(17,36,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17','3',NULL,NULL,'1'),
	(18,37,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17','3',NULL,NULL,'1'),
	(19,38,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17','3',NULL,NULL,'1'),
	(20,39,'b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17','3',NULL,NULL,'1');



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

# Dump of table st_ext_supply
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_ext_supply`;

CREATE TABLE `st_ext_supply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品编码',
  `product_name` varchar(128) NOT NULL COMMENT '产品名称',
  `product_num` varchar(128) NOT NULL COMMENT '货号',
  `brand_id` int(11) NOT NULL COMMENT '产品品牌编码',
  `spec_value` varchar(256) NOT NULL COMMENT '规格',
  `unit_value` varchar(256) NOT NULL COMMENT '单位',
  `catalog_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '目录价',
  `accept_person` varchar(64) DEFAULT NULL COMMENT '创建者',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `st_product_i` (`product_name`),
  KEY `st_product_ii` (`product_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品信息表';


INSERT INTO `st_product` (`product_id`, `product_name`, `product_num`, `brand_id`, `spec_value`, `unit_value`, `catalog_fee`, `accept_person`, `accept_date`, `del_flag`)
VALUES
	(1,'β-OXOID 胰蛋白胨/酵母粉','0102314786',1,'1000ml','瓶',120.00,NULL,NULL,'0'),
	(2,'β-PageRuler Prestained Protein Ladder','26617',2,'10*250ul','瓶',3837.00,NULL,NULL,'0'),
	(3,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(4,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(5,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(6,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(7,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(8,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(9,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(10,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(11,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(12,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(13,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(14,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(15,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(16,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(17,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(18,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(19,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(20,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(21,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(22,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(23,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(24,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(25,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(26,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(27,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(28,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(29,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(30,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(31,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(32,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(33,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(34,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(35,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(36,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(37,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(38,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(39,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(40,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(41,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(42,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(43,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(44,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(45,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(46,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(47,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0'),
	(48,'β-Actin (8H10D10) Mouse mAb','KT201-02',3,'100ml','支',100.00,NULL,NULL,'0');


# Dump of table st_product_brand
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_product_brand`;

CREATE TABLE `st_product_brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品品牌编码',
  `brand_name` varchar(256) NOT NULL COMMENT '产品品牌名称',
  `accept_person` varchar(64) DEFAULT NULL COMMENT '创建者',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品品牌表';

INSERT INTO `st_product_brand` (`brand_id`, `brand_name`, `accept_person`, `accept_date`)
VALUES
	(1,'Adas',NULL,NULL),
	(2,'Nexus',NULL,NULL),
	(3,'CST',NULL,NULL);

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
  `if_valid` varchar(2) NOT NULL COMMENT '是否有效 1是有效，0无效',
  PRIMARY KEY (`register_number`),
  KEY `st_product_order_i` (`user_order_id`),
  KEY `st_product_order_ii` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品订单信息表';

INSERT INTO `st_product_order` (`register_number`, `user_order_id`, `area_id`, `user_id`, `product_id`, `order_fee`, `order_num`, `audit_cd`, `status_cd`, `invoice_status`, `accept_date`, `change_date`, `if_valid`)
VALUES
	(15,11,'3','76ef9deb273a49d2bb2f116dac45e877',1,99.99,1,'0','0','0','2014-12-03 13:57:39','2014-12-03 13:57:39','1'),
	(16,12,'3','76ef9deb273a49d2bb2f116dac45e877',1,99.99,1,'0','0','0','2014-12-03 14:31:51','2014-12-03 14:31:51','1'),
	(17,13,'3','76ef9deb273a49d2bb2f116dac45e877',2,99.99,1,'0','0','0','2014-12-03 14:31:56','2014-12-03 14:31:56','1'),
	(18,14,'3','76ef9deb273a49d2bb2f116dac45e877',7,99.99,1,'0','0','0','2014-12-03 14:32:04','2014-12-03 14:32:04','1'),
	(19,15,'3','76ef9deb273a49d2bb2f116dac45e877',12,99.99,1,'0','0','0','2014-12-03 14:32:12','2014-12-03 14:32:12','1'),
	(20,16,'3','76ef9deb273a49d2bb2f116dac45e877',8,99.99,1,'0','0','0','2014-12-03 14:32:21','2014-12-03 14:32:21','1'),
	(21,16,'3','76ef9deb273a49d2bb2f116dac45e877',9,99.99,1,'0','0','0','2014-12-03 14:32:21','2014-12-03 14:32:21','1'),
	(22,16,'3','76ef9deb273a49d2bb2f116dac45e877',11,99.99,1,'0','0','0','2014-12-03 14:32:21','2014-12-03 14:32:21','1'),
	(23,16,'3','76ef9deb273a49d2bb2f116dac45e877',12,99.99,1,'0','0','0','2014-12-03 14:32:21','2014-12-03 14:32:21','1'),
	(24,17,'3','76ef9deb273a49d2bb2f116dac45e877',13,99.99,1,'1','0','0','2014-12-03 14:32:31','2014-12-04 10:39:21','1'),
	(25,17,'3','76ef9deb273a49d2bb2f116dac45e877',14,99.99,1,'1','0','0','2014-12-03 14:32:31','2014-12-04 10:39:21','1'),
	(26,17,'3','76ef9deb273a49d2bb2f116dac45e877',15,99.99,1,'1','0','0','2014-12-03 14:32:31','2014-12-04 10:39:21','1'),
	(27,17,'3','76ef9deb273a49d2bb2f116dac45e877',17,99.99,1,'1','0','0','2014-12-03 14:32:31','2014-12-04 10:39:21','1'),
	(28,17,'3','76ef9deb273a49d2bb2f116dac45e877',18,99.99,1,'1','0','0','2014-12-03 14:32:31','2014-12-04 10:39:21','1'),
	(29,18,'3','76ef9deb273a49d2bb2f116dac45e877',29,99.99,1,'1','0','0','2014-12-03 14:33:38','2014-12-04 10:39:21','1'),
	(30,18,'3','76ef9deb273a49d2bb2f116dac45e877',30,99.99,1,'1','0','0','2014-12-03 14:33:38','2014-12-04 10:39:21','1'),
	(31,18,'3','76ef9deb273a49d2bb2f116dac45e877',31,99.99,1,'1','0','0','2014-12-03 14:33:38','2014-12-04 10:39:21','1'),
	(32,19,'3','76ef9deb273a49d2bb2f116dac45e877',19,99.99,1,'1','0','0','2014-12-03 14:33:44','2014-12-04 10:39:15','1'),
	(33,19,'3','76ef9deb273a49d2bb2f116dac45e877',20,99.99,1,'1','0','0','2014-12-03 14:33:44','2014-12-04 10:39:15','1'),
	(34,19,'3','76ef9deb273a49d2bb2f116dac45e877',21,99.99,1,'1','0','0','2014-12-03 14:33:44','2014-12-04 10:39:15','1'),
	(35,20,'3','76ef9deb273a49d2bb2f116dac45e877',2,99.99,1,'3','0','0','2014-12-03 14:33:52','2014-12-04 14:31:17','1'),
	(36,20,'3','76ef9deb273a49d2bb2f116dac45e877',3,99.99,1,'3','0','0','2014-12-03 14:33:52','2014-12-04 14:31:17','1'),
	(37,20,'3','76ef9deb273a49d2bb2f116dac45e877',4,99.99,1,'3','0','0','2014-12-03 14:33:52','2014-12-04 14:31:17','1'),
	(38,20,'3','76ef9deb273a49d2bb2f116dac45e877',5,99.99,1,'3','0','0','2014-12-03 14:33:52','2014-12-04 14:31:17','1'),
	(39,20,'3','76ef9deb273a49d2bb2f116dac45e877',6,99.99,1,'3','0','0','2014-12-03 14:33:52','2014-12-04 14:31:17','1'),
	(40,21,'3','76ef9deb273a49d2bb2f116dac45e877',1,100.00,1,'3','0','0','2014-12-03 14:33:58','2014-12-04 14:31:08','1'),
	(41,21,'3','76ef9deb273a49d2bb2f116dac45e877',2,100.00,1,'3','0','0','2014-12-03 14:33:58','2014-12-04 14:31:08','1');

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

INSERT INTO `st_product_order_his` (`id`, `register_number`, `order_result`, `accept_person`, `accept_date`)
VALUES
	(1,40,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:12:27'),
	(2,40,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:12:33'),
	(3,40,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:08'),
	(4,41,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:08'),
	(5,40,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:15'),
	(6,41,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:16'),
	(7,40,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:17'),
	(8,41,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:18'),
	(9,40,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:18'),
	(10,41,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:19'),
	(11,40,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:19'),
	(12,40,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:38'),
	(13,41,'修改订购价格：修改前：99.99 修改后：99.99','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:14:38'),
	(14,40,'修改订购价格：修改前：99.99 修改后：100.0','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:18:09'),
	(15,41,'修改订购价格：修改前：99.99 修改后：100.0','570d243ae0454d5ea47348fb75c97573','2014-12-04 09:18:09'),
	(16,40,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:29:26'),
	(17,41,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:29:26'),
	(18,40,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:30:56'),
	(19,41,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:30:56'),
	(20,35,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:15'),
	(21,36,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:15'),
	(22,37,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:15'),
	(23,38,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:15'),
	(24,39,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:15'),
	(25,32,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:15'),
	(26,33,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:15'),
	(27,34,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:15'),
	(28,29,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:21'),
	(29,30,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:21'),
	(30,31,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:21'),
	(31,24,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:21'),
	(32,25,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:21'),
	(33,26,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:21'),
	(34,27,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:21'),
	(35,28,'产品订单状态更改为待审批','76ef9deb273a49d2bb2f116dac45e877','2014-12-04 10:39:21'),
	(36,40,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 13:55:13'),
	(37,41,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 13:55:13'),
	(38,40,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:02:47'),
	(39,41,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:02:47'),
	(40,40,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09'),
	(41,41,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09'),
	(42,35,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09'),
	(43,36,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09'),
	(44,37,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09'),
	(45,38,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09'),
	(46,39,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:06:09'),
	(49,40,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:08'),
	(50,41,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:08'),
	(51,35,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17'),
	(52,36,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17'),
	(53,37,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17'),
	(54,38,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17'),
	(55,39,'审批结果为:3','b6ed4c7544bb40169829c2d573bdee1f','2014-12-04 14:31:17');



# Dump of table st_supplier
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_supplier`;

CREATE TABLE `st_supplier` (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `supplier_name` varchar(100) NOT NULL COMMENT '供应商名称',
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商表';



# Dump of table st_user_beans
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_user_beans`;

CREATE TABLE `st_user_beans` (
  `user_id` varchar(64) NOT NULL COMMENT '客户编号',
  `beans_num` int(11) NOT NULL COMMENT '豆豆数量',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户豆豆信息表';



# Dump of table st_user_group_point_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_user_group_point_account`;

CREATE TABLE `st_user_group_point_account` (
  `user_group_id` varchar(64) NOT NULL COMMENT '客户组编号',
  `point_balance` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `account_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户组积分账户信息表';



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
  PRIMARY KEY (`user_order_id`),
  KEY `st_user_order_i` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户订单信息表';

INSERT INTO `st_user_order` (`user_order_id`, `user_id`, `area_id`, `group_id`, `address_id`, `accept_date`, `status_cd`)
VALUES
	(11,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',9,'2014-12-03 13:57:39','1'),
	(12,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:31:51','1'),
	(13,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:31:56','1'),
	(14,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:32:04','1'),
	(15,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:32:12','1'),
	(16,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:32:21','1'),
	(17,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:32:31','2'),
	(18,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:33:38','2'),
	(19,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:33:44','2'),
	(20,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:33:52','4'),
	(21,'76ef9deb273a49d2bb2f116dac45e877','3','b04cbda7517d4eaf8c9d34a50a9d63a5',0,'2014-12-03 14:33:58','4');


# Dump of table st_user_peas_his
# ------------------------------------------------------------

DROP TABLE IF EXISTS `st_user_peas_his`;

CREATE TABLE `st_user_peas_his` (
  `peas_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_id` varchar(64) NOT NULL COMMENT '客户编码',
  `peas_type` varchar(2) NOT NULL COMMENT '操作类型:1.累积积分2.使用积分',
  `peas_balance` int(11) NOT NULL DEFAULT '0' COMMENT '本次使用或累计豆豆数',
  `register_number` int(11) NOT NULL COMMENT '累积豆豆时是客户订购产品的订单编号；使用豆豆时是客户积分兑换时使用的订单编号',
  `accept_person` varchar(64) DEFAULT NULL COMMENT '创建者',
  `accept_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`peas_id`),
  KEY `st_user_peas_his_i` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户豆豆历史表';

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


INSERT INTO `sys_area` (`id`, `parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','0','0,','100000','中国','1','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('2','1','0,1,','410000','辽宁省','2','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('20','2','0,1,2,','410411','大连市','3','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('3','1','0,1,','320000','吉林省','2','1','2014-11-05 22:18:39','1','2014-11-05 22:18:39','','0'),
	('30','3','0,1,3,','320001','长春市','3','1','2014-11-05 22:19:00','1','2014-11-05 22:19:00','','0'),
	('4','1','0,1,','380000','黑龙江省','2','1','2014-11-05 22:17:56','1','2014-11-05 22:17:56','','0'),
	('40','4','0,1,4,','380001','哈尔滨市','3','1','2014-11-05 22:18:23','1','2014-11-05 22:18:23','','0');


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

INSERT INTO `sys_dict` (`id`, `label`, `value`, `type`, `description`, `sort`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','正常','0','del_flag','删除标记',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('100','内部组','1','sys_group_catagory','组类别',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('101','外部组','2','sys_group_catagory','组类别',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('12','默认主题','default','theme','主题方案',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('17','国家','1','sys_area_type','区域类型',10,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('18','省份、直辖市','2','sys_area_type','区域类型',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('19','地市','3','sys_area_type','区域类型',30,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('2','删除','1','del_flag','删除标记',20,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('20','区县','4','sys_area_type','区域类型',40,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('21','公司','1','sys_office_type','机构类型',60,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('22','部门','2','sys_office_type','机构类型',70,'1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
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

INSERT INTO `sys_menu` (`id`, `parent_id`, `parent_ids`, `module`, `name`, `href`, `target`, `icon`, `sort`, `is_show`, `type`, `permission`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','0','0,','1','顶级菜单','','','',0,'1',NULL,'','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('10','3','0,1,2,3,','1','字典管理','/sys/dict/','','th-list',60,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('11','10','0,1,2,3,10,','1','查看','','','',30,'0',NULL,'sys:dict:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('111','1','0,1,','2','我的订单','','','thumbs-up',0,'1',1,'perms[order:orderlist:all],authc','1','2014-11-13 20:28:00','1','2014-12-03 11:20:08',NULL,'0'),
	('1111','111','0,1,111,','2','所有订单','/order/list/all_order/**','','',100,'1',1,'perms[order:list:all]','1','2014-11-13 20:30:08','1','2014-12-03 11:20:08',NULL,'0'),
	('1112','111','0,1,111,','2','待审批订单','/order/list/customer/approve_view/**','','',1,'1',1,'perms[order:list:approve]','1','2014-11-13 20:29:09','1','2014-12-03 11:20:08',NULL,'0'),
	('1113','111','0,1,111,','2','内勤修改','/order/list/inside/bargain_view/**','','',2,'1',1,'perms[order:list:bargain]','1','2014-11-13 20:30:08','1','2014-12-03 11:20:08',NULL,'0'),
	('1114','111','0,1,111,','2','确认订单','/order/list/customer/confirm_view/**',NULL,NULL,3,'1',1,'perms[order:list:confirm]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('1116','111','0,1,111,','2','已驳回订单','/order/list/customer/reject_view/**',NULL,NULL,5,'1',1,'perms[order:list:reject]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('1117','111','0,1,111,','2','客户下单','/order/list/customer/booking_view/**',NULL,NULL,6,'1',1,'perms[order:list:book]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('1118','111','0,1,111,','2','内勤接单','/order/list/inside/take_view/**','','',7,'1',1,'perms[order:list:take]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('1119','111','0,1,111,','2','内勤出单','/order/list/inside/load_view/**','','',8,'1',1,'perms[order:list:load]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('1120','111','0,1,111,','2','客户收货','/order/list/inside/received_view/**','','',9,'1',1,'perms[order:list:received]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('1121','111','0,1,111,','2','开具发票','/order/list/finance/invoiced_view/**','','',10,'1',1,'perms[order:list:invoiced]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('1122','111','0,1,111,','2','送发票待结款','/order/list/finance/invoiced_nopay_view/**','','',11,'1',1,'perms[order:list:invoiced_nopay]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('1123','111','0,1,111,','2','结款','/order/list/finance/pay_view/**','','',12,'1',1,'perms[order:list:pay]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('12','10','0,1,2,3,10,','1','修改','','','',30,'0',NULL,'sys:dict:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('120','1','0,1,','2','个人信息','','','',100,'1',1,'perms[address:list]','1','2014-11-10 09:10:25','1','2014-12-03 11:20:08',NULL,'0'),
	('1201','120','0,1,120,','2','地址添加','/order/address/add/**','','',110,'1',2,'perms[order:address:add]','1','2014-11-10 09:10:51','1','2014-12-03 11:20:08',NULL,'0'),
	('1202','120','0,1,120,','2','地址管理','/order/address/list/**',NULL,NULL,120,'1',1,'perms[order:address:list]','1',NULL,'1','2014-12-03 11:20:08',NULL,'0'),
	('13','2','0,1,2,','1','机构用户','','','',970,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('130','1','0,1,','2','我的资产','','','',3,'1',1,'perms[order:mine:all]','1','2014-11-13 20:28:00','1','2014-12-03 11:20:08',NULL,'0'),
	('1301','130','0,1,130,','2','我的豆豆','/order/mine/bean/**','','',2,'1',1,'perms[order:mine:bean]','1','2014-11-13 20:29:09','1','2014-12-03 11:20:08',NULL,'0'),
	('1302','130','0,1,130,','2','我的积分','/order/mine/credits/**','','',2,'1',1,'perms[order:mine: credits]','1','2014-11-13 20:29:09','1','2014-12-03 11:20:08',NULL,'0'),
	('14','13','0,1,2,13,','1','区域管理','/sys/area/','','th',50,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('140','1','0,1,','2','我的服务','','',NULL,1,'1',1,'perms[order:service:all]','1','2014-11-13 20:28:00','1','2014-12-03 11:20:08',NULL,'0'),
	('1401','140','0,1,140,','2','我的投诉','/order/service/complain/**','','',1,'1',1,'perms[order:service:complain]','1','2014-11-13 20:29:09','1','2014-12-03 11:20:08',NULL,'0'),
	('15','14','0,1,2,13,14,','1','查看','','','',30,'0',NULL,'sys:area:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('150','1','0,1,','2','我的建议','',NULL,NULL,2,'1',1,'perms[order:service:complain]','1','2014-11-13 20:29:09','1','2014-12-03 11:20:08',NULL,'0'),
	('16','14','0,1,2,13,14,','1','修改','','','',30,'0',NULL,'sys:area:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('17','13','0,1,2,13,','1','机构管理','/sys/office/','','th-large',40,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('18','17','0,1,2,13,17,','1','查看','','','',30,'0',NULL,'sys:office:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('19','17','0,1,2,13,17,','1','修改','','','',30,'0',NULL,'sys:office:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('2','1','0,1,','1','系统设置','','','',900,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('20','13','0,1,2,13,','1','用户管理','/sys/user/','','user',30,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('21','20','0,1,2,13,20,','1','查看','','','',30,'0',NULL,'sys:user:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('22','20','0,1,2,13,20,','1','修改','','','',30,'0',NULL,'sys:user:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('27','1','0,1,','1','我的面板','','','',500,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('28','27','0,1,27,','1','个人信息','','','',990,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('29','28','0,1,27,28,','1','个人信息','/sys/user/info','','user',30,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('3','2','0,1,2,','1','系统设置','','','',980,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('30','28','0,1,27,28,','1','修改密码','/sys/user/modifyPwd','','lock',40,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('4','3','0,1,2,3,','1','菜单管理','/sys/menu/','','list-alt',30,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('5','4','0,1,2,3,4,','1','查看','','','',30,'0',NULL,'sys:menu:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('6','4','0,1,2,3,4,','1','修改','','','',30,'0',NULL,'sys:menu:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('67','2','0,1,2,','1','日志查询','','','',985,'1',NULL,'','1','2013-06-03 00:00:00','1','2014-12-03 11:20:08','','0'),
	('68','67','0,1,2,67,','1','日志查询','/sys/log','','pencil',30,'1',NULL,'sys:log:view','1','2013-06-03 00:00:00','1','2014-12-03 11:20:08','','0'),
	('7','3','0,1,2,3,','1','角色管理','/sys/role/','','lock',50,'1',NULL,'','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('8','7','0,1,2,3,7,','1','查看','','','',30,'0',NULL,'sys:role:view','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0'),
	('9','7','0,1,2,3,7,','1','修改','','','',30,'0',NULL,'sys:role:edit','1','2013-05-27 00:00:00','1','2014-12-03 11:20:08','','0');

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

INSERT INTO `sys_office` (`id`, `parent_id`, `parent_ids`, `area_id`, `code`, `name`, `type`, `grade`, `catagory`, `address`, `zip_code`, `master`, `phone`, `fax`, `email`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','0','0,','1','100000','顶级机构组织','1','1','1','','','','','','','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','','0'),
	('1b7d1aeea9cd4711851ec65199be2aa2','1','0,1,','1','320000001','塞托长春分公司','1','1','1','','','','','','','1','2014-11-05 22:26:48','1','2014-11-05 22:26:48','','0'),
	('38405be8fea04945987bd14647f99da5','1','0,1,','1','38000001','塞托黑龙江分公司','1','1','1','','','','','','','1','2014-11-05 22:25:37','1','2014-11-05 22:25:37','','0'),
	('4a8ec405cb8e4e5f81571b46cb7f534f','db8bf7e94ab1413899a7430f4c9fc859','0,1,db8bf7e94ab1413899a7430f4c9fc859,','3','410000001','内勤部','2','2','1','','','','','','','1','2014-11-05 22:24:18','1','2014-12-03 09:56:22','','0'),
	('58f08124fc4f4fb0a0506d1ea9575f33','1','0,1,','3','11602405','大连理工大学物理实验室','1','1','2','大连市大连理工大学','116024','高老师','','','','1','2014-12-02 11:32:39','1','2014-12-02 11:40:13','','0'),
	('76292c5fb7674d1597d3665d46105e43','38405be8fea04945987bd14647f99da5','0,1,38405be8fea04945987bd14647f99da5,','1','380000002','客服部','2','2','1','','','','','','','1','2014-11-05 22:26:25','1','2014-11-05 22:28:03','','0'),
	('96ad01f9140b47f6b5c5d638a3f4128b','1b7d1aeea9cd4711851ec65199be2aa2','0,1,1b7d1aeea9cd4711851ec65199be2aa2,','4d22ee5105e746d7869ba798a052c97c','3200000001','采购部','2','2','1','','','','','','','1','2014-11-05 22:27:18','1','2014-11-14 20:48:33','','0'),
	('b04cbda7517d4eaf8c9d34a50a9d63a5','58f08124fc4f4fb0a0506d1ea9575f33','0,1,58f08124fc4f4fb0a0506d1ea9575f33,','3','11678009','高老师自有公司','2','1','2','1167829','11728821','高老大','','','','1','2014-12-02 11:35:06','1','2014-12-02 11:40:20','','0'),
	('d307c6d30e1a466ea75dc3f66160936e','db8bf7e94ab1413899a7430f4c9fc859','0,1,db8bf7e94ab1413899a7430f4c9fc859,','3','410000002','财务部','2','2','1','','','','','','','1','2014-11-05 22:24:41','1','2014-12-03 09:56:07','','0'),
	('d45717683fc7409ba28dcd6985746b87','38405be8fea04945987bd14647f99da5','0,1,38405be8fea04945987bd14647f99da5,','1','380000001','采购部','2','2','1','','','','','','','1','2014-11-05 22:25:57','1','2014-11-05 22:27:58','','0'),
	('db8bf7e94ab1413899a7430f4c9fc859','1','0,1,','3','41000001','塞托大连分公司','1','1','1','','','','','','','1','2014-11-05 22:23:16','1','2014-11-05 22:23:16','','0'),
	('de36a169a84742248d8f884d33d9baad','1','0,1,','4d22ee5105e746d7869ba798a052c97c','dsds','dsds','2','1','2','121','2121','121','1','','','1','2014-11-06 15:39:47','1','2014-11-06 15:39:47','','0'),
	('fba75326c9074b2aabe019104277d2bc','1','0,1,','3','','下的人实验室','1','1','2','','','下的人','182829192191','','','1','2014-12-02 20:27:02','1','2014-12-02 20:27:02','','0');

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

INSERT INTO `sys_role` (`id`, `office_id`, `name`, `role_sign`, `data_scope`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`)
VALUES
	('1','1','系统管理员','1','1','1','2013-05-27 00:00:00','1','2014-11-17 23:48:51','','0'),
	('2350bd2b8318459db0f82a308b12b0d8','db8bf7e94ab1413899a7430f4c9fc859','大连内勤','9','5','1','2014-12-02 11:47:53','1','2014-12-03 10:09:33',NULL,'0'),
	('6','1','大连学生','1','8','1','2013-05-27 00:00:00','1','2014-12-03 10:34:17','','0'),
	('dabcc958a63d4bf99dbcf64635d0760e','1','大连财务','6','5','1','2014-11-14 20:39:04','1','2014-12-03 10:10:19','','0'),
	('dc4d368d9ebc4c10825beb9af2405e90','b04cbda7517d4eaf8c9d34a50a9d63a5','大连导师','2','5','1','2014-12-02 11:45:06','1','2014-12-03 11:13:12',NULL,'0');


# Dump of table sys_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

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
	('1','1122'),
	('1','113'),
	('1','1131'),
	('1','1132'),
	('1','114'),
	('1','1141'),
	('1','115'),
	('1','1151'),
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
	('2350bd2b8318459db0f82a308b12b0d8','1'),
	('2350bd2b8318459db0f82a308b12b0d8','111'),
	('2350bd2b8318459db0f82a308b12b0d8','1111'),
	('2350bd2b8318459db0f82a308b12b0d8','1113'),
	('2350bd2b8318459db0f82a308b12b0d8','1118'),
	('2350bd2b8318459db0f82a308b12b0d8','1119'),
	('2350bd2b8318459db0f82a308b12b0d8','1120'),
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
	('6','111'),
	('6','1111'),
	('6','1114'),
	('6','1115'),
	('6','1116'),
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
	('dabcc958a63d4bf99dbcf64635d0760e','111'),
	('dabcc958a63d4bf99dbcf64635d0760e','1111'),
	('dabcc958a63d4bf99dbcf64635d0760e','1121'),
	('dabcc958a63d4bf99dbcf64635d0760e','1122'),
	('dabcc958a63d4bf99dbcf64635d0760e','1123'),
	('dc4d368d9ebc4c10825beb9af2405e90','1'),
	('dc4d368d9ebc4c10825beb9af2405e90','111'),
	('dc4d368d9ebc4c10825beb9af2405e90','1111'),
	('dc4d368d9ebc4c10825beb9af2405e90','1112'),
	('dc4d368d9ebc4c10825beb9af2405e90','1117'),
	('dc4d368d9ebc4c10825beb9af2405e90','120'),
	('dc4d368d9ebc4c10825beb9af2405e90','1201'),
	('dc4d368d9ebc4c10825beb9af2405e90','1202');


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

INSERT INTO `sys_user` (`id`, `company_id`, `office_id`, `login_name`, `password`, `no`, `name`, `email`, `phone`, `mobile`, `qq`, `user_type`, `user_catagory`, `login_ip`, `login_date`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `forward`)
VALUES
	('1','1','1','admin','ad2eda2921f8861636c729ac4b3b49b0979c85c338b90767c9f14281','00001','管理员','yangwn1116@gmail.com','8675','8675',NULL,'','0','0:0:0:0:0:0:0:1','2014-12-03 11:20:08','1','2013-05-27 00:00:00','1','2013-05-27 00:00:00','管理员','0',NULL),
	('570d243ae0454d5ea47348fb75c97573','db8bf7e94ab1413899a7430f4c9fc859','4a8ec405cb8e4e5f81571b46cb7f534f','dl_nq','badbdcfdfbd27744210c1a144292be0b910f97e467b46653619e353a','116','大连内勤-王万里','116@173.com','1829291201021','','','2','2',NULL,NULL,'1','2014-12-02 11:49:40','1','2014-12-03 10:36:02','','0',''),
	('76ef9deb273a49d2bb2f116dac45e877','58f08124fc4f4fb0a0506d1ea9575f33','b04cbda7517d4eaf8c9d34a50a9d63a5','dl_st','d209beffe1ebe404fdb2c0f22946d33ebd24c119f0473f54183b97c7','100','高老师-王学生','123@163.com','12821829121','2121','2121','3','1',NULL,NULL,'1','2014-12-03 10:42:55','1','2014-12-03 10:42:55','11','0','课题方向'),
	('b6ed4c7544bb40169829c2d573bdee1f','58f08124fc4f4fb0a0506d1ea9575f33','b04cbda7517d4eaf8c9d34a50a9d63a5','dl_gao_admin','08d8ef17aa87acd6253630eb6c8c435a732ea4ddbb8ecf233edfb717','1','高老师','gao@163.com','139999999999','','','2','1',NULL,NULL,'1','2014-12-02 11:42:03','1','2014-12-02 11:45:44','','0',''),
	('e9965c7982714e09a5febfd37ad0ad99','db8bf7e94ab1413899a7430f4c9fc859','d307c6d30e1a466ea75dc3f66160936e','dl_cw','4cd76ec18dd3cbc73631f9594fbd0595b14d8e5afece1612bde98552','000003','大连财务-李','1@163.com','139999999999','139999999999','1918101','2','2',NULL,NULL,'1','2014-11-05 23:54:27','1','2014-12-03 10:36:40','','0','课题方向');


# Dump of table sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

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
	('570d243ae0454d5ea47348fb75c97573','2350bd2b8318459db0f82a308b12b0d8'),
	('6','5'),
	('7','2'),
	('7','7'),
	('76ef9deb273a49d2bb2f116dac45e877','6'),
	('8','2'),
	('9','1'),
	('b69c638f106b45bb99b7b16ee93a4795','2'),
	('b6ed4c7544bb40169829c2d573bdee1f','5'),
	('b6ed4c7544bb40169829c2d573bdee1f','dc4d368d9ebc4c10825beb9af2405e90'),
	('bcc2a09a4ac943d48ce61d525109878d','3'),
	('c5fbcace6007470e8f07551995aae248','6'),
	('c6a29d4b98544b2194e0dfdb1a44db06','2'),
	('c6a29d4b98544b2194e0dfdb1a44db06','4'),
	('cdcf84f29e8f493aab3ecc94d34790bb','2'),
	('e72a1d8858674cfea13a97a41ff2afd1','2'),
	('e9965c7982714e09a5febfd37ad0ad99','dabcc958a63d4bf99dbcf64635d0760e'),
	('f760b33f877941fc8eeb9d6c5685d817','6');
