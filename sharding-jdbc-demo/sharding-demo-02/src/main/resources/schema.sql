DROP TABLE IF EXISTS demo_ds_0.t_order_0;
CREATE TABLE demo_ds_0.t_order_0 (
  `order_id` bigint(20) NOT NULL ,
  `user_id` int(11) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS demo_ds_0.t_order_1;
CREATE TABLE demo_ds_0.t_order_1 (
  `order_id` bigint(20) NOT NULL ,
  `user_id` int(11) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS demo_ds_1.t_order_0;
CREATE TABLE demo_ds_1.t_order_0 (
  `order_id` bigint(20) NOT NULL ,
  `user_id` int(11) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS demo_ds_1.t_order_1;
CREATE TABLE demo_ds_1.t_order_1 (
  `order_id` bigint(20) NOT NULL ,
  `user_id` int(11) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS demo_ds_0.t_order_item_0;
CREATE TABLE demo_ds_0.t_order_item_0 (
  `order_item_id` bigint(20) NOT NULL ,
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS demo_ds_0.t_order_item_1;
CREATE TABLE demo_ds_0.t_order_item_1 (
  `order_item_id` bigint(20) NOT NULL ,
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS demo_ds_1.t_order_item_0;
CREATE TABLE demo_ds_1.t_order_item_0 (
  `order_item_id` bigint(20) NOT NULL ,
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS demo_ds_1.t_order_item_1;
CREATE TABLE demo_ds_1.t_order_item_1 (
  `order_item_id` bigint(20) NOT NULL ,
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;