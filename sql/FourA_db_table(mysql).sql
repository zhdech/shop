DROP TABLE IF EXISTS `shop`.`sequence`;
CREATE TABLE  `shop`.`sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` decimal(20,0) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `shop`.`t_goods`;
CREATE TABLE  `shop`.`t_goods` (
  `goodsid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL COMMENT '品牌',
  `model` varchar(45) NOT NULL COMMENT '型号',
  `color` varchar(45) NOT NULL COMMENT '颜色',
  `pirce` int(10) unsigned NOT NULL COMMENT '价格',
  `stock` varchar(45) NOT NULL COMMENT '库存，如果为0，则表示缺货',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`goodsid`)
) ENGINE=InnoDB AUTO_INCREMENT=379 DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `shop`.`t_orderinfo`;
CREATE TABLE  `shop`.`t_orderinfo` (
  `orderid` int(10) unsigned NOT NULL COMMENT '订单ID',
  `goodsid` int(10) unsigned NOT NULL COMMENT '商品ID',
  `amount` int(10) unsigned NOT NULL COMMENT '金额(元)',
  `number` int(10) unsigned NOT NULL COMMENT '数量',
  `loginname` varchar(45) NOT NULL COMMENT '登录人',
  `id` int(10) unsigned zerofill NOT NULL COMMENT '主键',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `status` varchar(45) NOT NULL DEFAULT '0' COMMENT '状态0未下单，1已下单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `shop`.`t_orderlist`;
CREATE TABLE  `shop`.`t_orderlist` (
  `orderid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ordername` varchar(45) NOT NULL,
  `createname` varchar(45) NOT NULL,
  `createmobile` varchar(45) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `orderstatus` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '0未发货，1已发货，9订单无效',
  `totalnum` int(10) unsigned NOT NULL COMMENT '总数量',
  `totalamount` int(10) unsigned NOT NULL COMMENT '总金额',
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `shop`.`t_user`;
CREATE TABLE  `shop`.`t_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `status` int(10) unsigned NOT NULL DEFAULT '0',
  `manager` varchar(45) NOT NULL DEFAULT '0' COMMENT '是否为管理员',
  `address` varchar(3000) NOT NULL COMMENT '发货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `shop`.`t_user_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE  `shop`.`t_user_log` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `address` varchar(2000) NOT NULL,
  `logintime` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='帐号登录日志';
/*!40101 SET character_set_client = @saved_cs_client */;

DELIMITER $$

DROP FUNCTION IF EXISTS `shop`.`currval`$$
CREATE DEFINER=`root`@`localhost` FUNCTION  `shop`.`currval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
	DECLARE value INTEGER;
	SET value = 0;
	SELECT current_value INTO value
		FROM sequence
		WHERE name = seq_name;
	RETURN value;
END;

 $$

DELIMITER ;

DELIMITER $$

DROP FUNCTION IF EXISTS `shop`.`nextval`$$
CREATE DEFINER=`root`@`localhost` FUNCTION  `shop`.`nextval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
	UPDATE sequence
		SET current_value = current_value + increment
		WHERE name = seq_name;
	RETURN currval(seq_name);
END;

 $$

DELIMITER ;


DELIMITER $$

DROP FUNCTION IF EXISTS `shop`.`setval`$$
CREATE DEFINER=`root`@`localhost` FUNCTION  `shop`.`setval`(seq_name VARCHAR(50), value INTEGER) RETURNS int(11)
    DETERMINISTIC
BEGIN
	UPDATE sequence
		SET current_value = value
		WHERE name = seq_name;
	RETURN currval(seq_name);
END;

 $$

DELIMITER ;