drop table if exists `dynamic_job`;
CREATE TABLE `dynamic_job`
(
    `job_id`          INT (11) NOT NULL AUTO_INCREMENT COMMENT '任务id',
    `business_id`     INT (11) NOT NULL COMMENT '业务id',
    `bean_name`       varchar(50) NOT NULL default "" COMMENT 'Bean名称',
    `method_name`     varchar(50) NOT NULL default "" COMMENT '方法名称',
    `method_params`   varchar(200) NULL default "" COMMENT '方法参数',
    `cron_expression` varchar(50) NULL default "" COMMENT 'cron表达式',
    `remark`          varchar(100) NULL default "" COMMENT '备注',
    `job_status`      TINYINT (3) NOT NULL default 0 COMMENT '状态（0：正常，1：暂停）',
    `create_user`     VARCHAR(50) NULL default "" COMMENT '创建人',
    `create_time`     TIMESTAMP NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     TIMESTAMP NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT = '动态定时任务'