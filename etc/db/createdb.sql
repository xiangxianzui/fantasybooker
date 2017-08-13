use `fantasybooker`;

set names utf8;

CREATE TABLE `user_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '收货电话',
  `address` varchar(50) NOT NULL DEFAULT '' COMMENT '收货地址',
  `user_code` varchar(50) NOT NULL DEFAULT '' COMMENT '用户唯一标识码',
  `register_time` datetime NOT NULL DEFAULT '2017-01-01' COMMENT '注册时间',
  `activated` tinyint(1) DEFAULT '0' COMMENT '0:未激活；1:激活',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uniq_nickname` (`nickname`),
  UNIQUE INDEX `uniq_email` (`email`),
  UNIQUE INDEX `uniq_user_code` (`user_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';


CREATE TABLE `book_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bookname` varchar(50) NOT NULL default '' COMMENT '书名',
  `author` varchar(50) NOT NULL default '' COMMENT '作者',
  `description` varchar(100) NOT NULL default '' COMMENT '描述',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `amount` int(10) NOT NULL DEFAULT 0 COMMENT '剩余货量',
  `discount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣',
  PRIMARY KEY (`id`),
  INDEX `idx_bookname_author` (`bookname`, `author`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='图书信息表';