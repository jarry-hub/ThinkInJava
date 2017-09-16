
CREATE TABLE `mysql_test_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `content` longtext COMMENT '内容',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  `split_table_flag` bigint(20) NOT NULL COMMENT '拆分表的标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mysql_test_1 LIKE mysql_test_0;