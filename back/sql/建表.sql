-- mydb.sys_user definition

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '中文用户名',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `created_time` datetime DEFAULT NULL,
  `created_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- mydb.wms_container definition

CREATE TABLE `wms_container` (
  `id` varchar(100) NOT NULL,
  `item_no` varchar(100) NOT NULL,
  `supplier` varchar(100) NOT NULL,
  `container_code` varchar(100) DEFAULT NULL,
  `container` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- mydb.wms_inbound definition

CREATE TABLE `wms_inbound` (
  `id` varchar(100) NOT NULL,
  `inbound_no` varchar(100) NOT NULL,
  `inbound_status` int(11) NOT NULL,
  `inbound_time` varchar(100) DEFAULT NULL,
  `supplier` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- mydb.wms_inbound_detail definition

CREATE TABLE `wms_inbound_detail` (
  `id` varchar(100) NOT NULL,
  `inbound_id` varchar(100) NOT NULL,
  `inbound_no` varchar(100) NOT NULL,
  `item_no` varchar(100) NOT NULL,
  `container` int(11) NOT NULL,
  `plan_quantity` int(11) NOT NULL,
  `real_quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- mydb.wms_inventory definition

CREATE TABLE `wms_inventory` (
  `id` varchar(100) NOT NULL,
  `item_no` varchar(100) NOT NULL,
  `inventory` int(11) NOT NULL,
  `low` int(11) DEFAULT NULL,
  `high` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- mydb.wms_item definition

CREATE TABLE `wms_item` (
  `id` varchar(100) NOT NULL,
  `item_no` varchar(100) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `supplier` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- mydb.wms_supplier definition

CREATE TABLE `wms_supplier` (
  `id` varchar(100) NOT NULL,
  `supplierCode` varchar(100) NOT NULL,
  `supplierName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;