DROP TABLE IF EXISTS demo_ds_0.sys_merchant;
CREATE TABLE demo_ds_0.sys_merchant (
  `id` bigint(20) NOT NULL ,
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织id(创建商户号)',
  `mid` varchar(64) DEFAULT NULL COMMENT '商户号',
  `keyt` varchar(64) DEFAULT NULL COMMENT '密钥',
  `ip_restricted` bit(1) DEFAULT b'1' COMMENT '是否限制ip',
  `whitelist` varchar(1024) DEFAULT NULL COMMENT 'ip白名单',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `base_url` varchar(256) DEFAULT NULL COMMENT '系统地址',
  `data_exchange_type` tinyint(4) DEFAULT '1' COMMENT '数据交换类型',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统商户配';


DROP TABLE IF EXISTS demo_ds_0.sys_merchant_org;
CREATE TABLE demo_ds_0.sys_merchant_org (
  `id` bigint(20) NOT NULL ,
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级组织id',
  `top_org_id` bigint(20) DEFAULT NULL COMMENT '该条线的顶级组织id',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户号id',
  `enable` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_orgId_topOrgId` (`org_id`,`top_org_id`) USING BTREE,
  KEY `normal_merchantId` (`merchant_id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织商户号关联表';


DROP TABLE IF EXISTS demo_ds_0.sys_merchant_uri;
CREATE TABLE demo_ds_0.sys_merchant_uri (
  `id` bigint(20) NOT NULL ,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户号id',
  `api_type` smallint(4) DEFAULT NULL COMMENT '接口类型',
  `request_uri` varchar(256) DEFAULT NULL COMMENT '请求URI',
  `status` tinyint(4) DEFAULT NULL COMMENT '接口状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `merchant_id` (`merchant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商户接口映射表';


DROP TABLE IF EXISTS demo_ds_0.t_remote_request_record_0;
CREATE TABLE demo_ds_0.t_remote_request_record_0 (
  `id` bigint(20) NOT NULL ,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `mid` varchar(64) DEFAULT NULL COMMENT '商户号',
  `api_type` smallint(4) DEFAULT NULL COMMENT '接口类型',
  `data_exchange_type` tinyint(4) DEFAULT '1' COMMENT '类型',
  `post_time` datetime DEFAULT NULL COMMENT '发送时间',
  `url` varchar(256) DEFAULT NULL COMMENT '地址',
  `param_type` tinyint(4) DEFAULT NULL COMMENT '参数类型:1-form;2-json',
  `param` text COMMENT '参数',
  `method` tinyint(4) DEFAULT '1' COMMENT '请求参数：1-post;2-get;3-put;4-delete',
  `response_time` datetime DEFAULT NULL COMMENT '响应时间',
  `response_body` text COMMENT '响应体',
  `exception_stack` text COMMENT '异常堆栈信息',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='发送数据到第三方记录';

DROP TABLE IF EXISTS demo_ds_0.t_remote_request_record_1;
CREATE TABLE demo_ds_0.t_remote_request_record_1 (
  `id` bigint(20) NOT NULL ,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `mid` varchar(64) DEFAULT NULL COMMENT '商户号',
  `api_type` smallint(4) DEFAULT NULL COMMENT '接口类型',
  `data_exchange_type` tinyint(4) DEFAULT '1' COMMENT '类型',
  `post_time` datetime DEFAULT NULL COMMENT '发送时间',
  `url` varchar(256) DEFAULT NULL COMMENT '地址',
  `param_type` tinyint(4) DEFAULT NULL COMMENT '参数类型:1-form;2-json',
  `param` text COMMENT '参数',
  `method` tinyint(4) DEFAULT '1' COMMENT '请求参数：1-post;2-get;3-put;4-delete',
  `response_time` datetime DEFAULT NULL COMMENT '响应时间',
  `response_body` text COMMENT '响应体',
  `exception_stack` text COMMENT '异常堆栈信息',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='发送数据到第三方记录';


DROP TABLE IF EXISTS demo_ds_1.t_remote_request_record_0;
CREATE TABLE demo_ds_1.t_remote_request_record_0 (
  `id` bigint(20) NOT NULL ,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `mid` varchar(64) DEFAULT NULL COMMENT '商户号',
  `api_type` smallint(4) DEFAULT NULL COMMENT '接口类型',
  `data_exchange_type` tinyint(4) DEFAULT '1' COMMENT '类型',
  `post_time` datetime DEFAULT NULL COMMENT '发送时间',
  `url` varchar(256) DEFAULT NULL COMMENT '地址',
  `param_type` tinyint(4) DEFAULT NULL COMMENT '参数类型:1-form;2-json',
  `param` text COMMENT '参数',
  `method` tinyint(4) DEFAULT '1' COMMENT '请求参数：1-post;2-get;3-put;4-delete',
  `response_time` datetime DEFAULT NULL COMMENT '响应时间',
  `response_body` text COMMENT '响应体',
  `exception_stack` text COMMENT '异常堆栈信息',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='发送数据到第三方记录';

DROP TABLE IF EXISTS demo_ds_1.t_remote_request_record_1;
CREATE TABLE demo_ds_1.t_remote_request_record_1 (
  `id` bigint(20) NOT NULL ,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `mid` varchar(64) DEFAULT NULL COMMENT '商户号',
  `api_type` smallint(4) DEFAULT NULL COMMENT '接口类型',
  `data_exchange_type` tinyint(4) DEFAULT '1' COMMENT '类型',
  `post_time` datetime DEFAULT NULL COMMENT '发送时间',
  `url` varchar(256) DEFAULT NULL COMMENT '地址',
  `param_type` tinyint(4) DEFAULT NULL COMMENT '参数类型:1-form;2-json',
  `param` text COMMENT '参数',
  `method` tinyint(4) DEFAULT '1' COMMENT '请求参数：1-post;2-get;3-put;4-delete',
  `response_time` datetime DEFAULT NULL COMMENT '响应时间',
  `response_body` text COMMENT '响应体',
  `exception_stack` text COMMENT '异常堆栈信息',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='发送数据到第三方记录';



































