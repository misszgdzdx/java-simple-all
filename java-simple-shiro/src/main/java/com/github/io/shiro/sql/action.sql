CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色id，1超管',
  `hash_sign` varchar(255) NOT NULL DEFAULT '' COMMENT '防篡改hash',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

INSERT INTO `my_test`.`user` (`id`, `account`, `password`, `role_id`, `hash_sign`, `remark`, `ctime`, `mtime`) VALUES ('1', '10000', '123456', '1', '', '超管', '2021-01-15 05:06:40', '2021-01-15 05:06:40');
INSERT INTO `my_test`.`user` (`id`, `account`, `password`, `role_id`, `hash_sign`, `remark`, `ctime`, `mtime`) VALUES ('2', '20000', '123456', '2', '', '普管', '2021-01-15 05:06:40', '2021-01-15 05:06:40');

CREATE TABLE `user_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(1) NOT NULL DEFAULT '0' COMMENT '角色id',
  `action` varchar(255) NOT NULL DEFAULT '' COMMENT '权限',
  `hash_sign` varchar(255) NOT NULL DEFAULT '' COMMENT '防篡改hash',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限';

INSERT INTO `my_test`.`user_action` (`id`, `role_id`, `action`, `hash_sign`, `remark`, `ctime`, `mtime`) VALUES ('1', '1', 'index', '', '首页', '2021-01-15 05:08:58', '2021-01-15 05:09:09');
INSERT INTO `my_test`.`user_action` (`id`, `role_id`, `action`, `hash_sign`, `remark`, `ctime`, `mtime`) VALUES ('2', '1', 'add.user', '', '添加用户', '2021-01-15 05:09:28', '2021-01-15 05:09:28');
INSERT INTO `my_test`.`user_action` (`id`, `role_id`, `action`, `hash_sign`, `remark`, `ctime`, `mtime`) VALUES ('3', '2', 'index', '', '首页', '2021-01-15 05:09:33', '2021-01-15 05:09:39');



