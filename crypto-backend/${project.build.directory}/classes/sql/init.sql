-- 创建数据库
CREATE DATABASE IF NOT EXISTS crypto_platform DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE crypto_platform;

-- 系统用户表（已存在，这里只是参考）
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码（BCrypt加密）',
  `nickname` VARCHAR(50) COMMENT '昵称',
  `email` VARCHAR(100) COMMENT '邮箱',
  `phone` VARCHAR(20) COMMENT '手机号',
  `avatar` VARCHAR(255) COMMENT '头像URL',
  `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-正常',
  `user_type` TINYINT DEFAULT 0 COMMENT '用户类型：0-普通用户，1-管理员',
  `real_name` VARCHAR(50) COMMENT '真实姓名',
  `id_card` VARCHAR(20) COMMENT '身份证号',
  `kyc_level` TINYINT DEFAULT 0 COMMENT 'KYC等级：0-未认证，1-初级，2-高级',
  `vip_level` TINYINT DEFAULT 0 COMMENT 'VIP等级',
  `uid` VARCHAR(32) UNIQUE COMMENT '用户UID',
  `invite_code` VARCHAR(20) UNIQUE COMMENT '邀请码',
  `inviter_id` BIGINT COMMENT '邀请人ID',
  `login_ip` VARCHAR(50) COMMENT '最后登录IP',
  `login_time` DATETIME COMMENT '最后登录时间',
  `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_email` (`email`),
  KEY `idx_phone` (`phone`),
  KEY `idx_invite_code` (`invite_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 平台统计数据表
CREATE TABLE IF NOT EXISTS `platform_statistics` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `stat_date` DATE NOT NULL COMMENT '统计日期',
  `total_revenue` DECIMAL(20,2) DEFAULT 0 COMMENT '平台总收入',
  `total_users` INT DEFAULT 0 COMMENT '总用户数',
  `new_users` INT DEFAULT 0 COMMENT '新增用户数',
  `active_users` INT DEFAULT 0 COMMENT '活跃用户数',
  `total_deposit` DECIMAL(20,2) DEFAULT 0 COMMENT '总充值金额',
  `total_withdraw` DECIMAL(20,2) DEFAULT 0 COMMENT '总提现金额',
  `deposit_count` INT DEFAULT 0 COMMENT '充值笔数',
  `withdraw_count` INT DEFAULT 0 COMMENT '提现笔数',
  `total_trade_volume` DECIMAL(20,2) DEFAULT 0 COMMENT '总交易量',
  `total_trade_count` INT DEFAULT 0 COMMENT '总交易笔数',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_stat_date` (`stat_date`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='平台统计数据表';

-- 用户资产表（已存在，这里只是参考）
CREATE TABLE IF NOT EXISTS `user_asset` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '资产ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `currency` VARCHAR(20) NOT NULL COMMENT '币种',
  `available` DECIMAL(20,8) DEFAULT 0 COMMENT '可用余额',
  `frozen` DECIMAL(20,8) DEFAULT 0 COMMENT '冻结余额',
  `total` DECIMAL(20,8) DEFAULT 0 COMMENT '总余额',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_currency` (`user_id`, `currency`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户资产表';

-- 充值订单表（已存在，这里只是参考）
CREATE TABLE IF NOT EXISTS `deposit_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` VARCHAR(64) NOT NULL COMMENT '订单号',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `currency` VARCHAR(20) NOT NULL COMMENT '币种',
  `amount` DECIMAL(20,8) NOT NULL COMMENT '充值金额',
  `address` VARCHAR(255) COMMENT '充值地址',
  `tx_hash` VARCHAR(255) COMMENT '交易哈希',
  `status` TINYINT DEFAULT 0 COMMENT '状态：0-待确认，1-成功，2-失败',
  `confirmations` INT DEFAULT 0 COMMENT '确认数',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充值订单表';

-- 提现订单表（已存在，这里只是参考）
CREATE TABLE IF NOT EXISTS `withdraw_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` VARCHAR(64) NOT NULL COMMENT '订单号',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `currency` VARCHAR(20) NOT NULL COMMENT '币种',
  `amount` DECIMAL(20,8) NOT NULL COMMENT '提现金额',
  `fee` DECIMAL(20,8) DEFAULT 0 COMMENT '手续费',
  `actual_amount` DECIMAL(20,8) NOT NULL COMMENT '实际到账金额',
  `address` VARCHAR(255) NOT NULL COMMENT '提现地址',
  `tx_hash` VARCHAR(255) COMMENT '交易哈希',
  `status` TINYINT DEFAULT 0 COMMENT '状态：0-待审核，1-审核通过，2-审核拒绝，3-处理中，4-成功，5-失败',
  `audit_user_id` BIGINT COMMENT '审核人ID',
  `audit_time` DATETIME COMMENT '审核时间',
  `audit_remark` VARCHAR(500) COMMENT '审核备注',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提现订单表';

-- 插入初始统计数据（示例数据）
INSERT INTO `platform_statistics` (`stat_date`, `total_revenue`, `total_users`, `new_users`, `active_users`, `total_deposit`, `total_withdraw`, `deposit_count`, `withdraw_count`)
VALUES 
  (CURDATE(), 1018904.00, 20, 5, 15, 1020000.00, 1096.00, 50, 10),
  (DATE_SUB(CURDATE(), INTERVAL 1 DAY), 950000.00, 15, 3, 12, 980000.00, 800.00, 45, 8),
  (DATE_SUB(CURDATE(), INTERVAL 2 DAY), 880000.00, 12, 2, 10, 900000.00, 600.00, 40, 6);

-- 插入测试管理员账号（密码：admin123，BCrypt加密）
INSERT INTO `sys_user` (`username`, `password`, `nickname`, `email`, `status`, `user_type`, `uid`, `invite_code`)
VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '管理员', 'admin@crypto.com', 1, 1, 'ADMIN001', 'ADMIN001')
ON DUPLICATE KEY UPDATE `username` = `username`;

