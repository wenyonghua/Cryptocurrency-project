-- =============================================
-- Crypto Platform - 完整数据库表结构
-- 基于原系统功能和 crypto-replica 前端需求
-- =============================================

-- 1. 用户表 (扩展)
CREATE TABLE IF NOT EXISTS `sys_user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '密码',
    `nickname` VARCHAR(50) COMMENT '昵称',
    `email` VARCHAR(100) COMMENT '邮箱',
    `phone` VARCHAR(20) COMMENT '手机号',
    `avatar` VARCHAR(255) COMMENT '头像',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-正常',
    `user_type` TINYINT DEFAULT 0 COMMENT '用户类型：0-普通用户，1-管理员',
    `kyc_level` TINYINT DEFAULT 0 COMMENT 'KYC等级：0-未认证，1-初级，2-高级',
    `vip_level` TINYINT DEFAULT 0 COMMENT 'VIP等级',
    `credit_score` INT DEFAULT 0 COMMENT '信用分',
    `uid` VARCHAR(20) UNIQUE COMMENT '用户UID',
    `invite_code` VARCHAR(20) UNIQUE COMMENT '邀请码',
    `inviter_id` BIGINT COMMENT '邀请人ID',
    `login_password_set` TINYINT DEFAULT 1 COMMENT '是否设置登录密码',
    `fund_password` VARCHAR(255) COMMENT '资金密码',
    `fund_password_set` TINYINT DEFAULT 0 COMMENT '是否设置资金密码',
    `email_verified` TINYINT DEFAULT 0 COMMENT '邮箱是否验证',
    `phone_verified` TINYINT DEFAULT 0 COMMENT '手机是否验证',
    `google_auth_enabled` TINYINT DEFAULT 0 COMMENT '是否启用谷歌验证',
    `google_auth_secret` VARCHAR(100) COMMENT '谷歌验证密钥',
    `wallet_address` VARCHAR(255) COMMENT '钱包地址',
    `last_login_time` DATETIME COMMENT '最后登录时间',
    `last_login_ip` VARCHAR(50) COMMENT '最后登录IP',
    `register_domain` VARCHAR(100) COMMENT '注册域名',
    `agent_id` BIGINT COMMENT '代理ID',
    `tags` VARCHAR(255) COMMENT '用户标签',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-否，1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    KEY `idx_email` (`email`),
    KEY `idx_phone` (`phone`),
    KEY `idx_uid` (`uid`),
    KEY `idx_inviter_id` (`inviter_id`),
    KEY `idx_agent_id` (`agent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 2. KYC认证表
CREATE TABLE IF NOT EXISTS `user_kyc` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `kyc_level` TINYINT NOT NULL COMMENT 'KYC等级：1-初级，2-高级',
    `real_name` VARCHAR(100) COMMENT '真实姓名',
    `id_card_type` VARCHAR(20) COMMENT '证件类型',
    `id_card_number` VARCHAR(50) COMMENT '证件号码',
    `id_card_front` VARCHAR(255) COMMENT '证件正面照',
    `id_card_back` VARCHAR(255) COMMENT '证件背面照',
    `id_card_hand` VARCHAR(255) COMMENT '手持证件照',
    `country` VARCHAR(50) COMMENT '国家',
    `province` VARCHAR(50) COMMENT '省份',
    `city` VARCHAR(50) COMMENT '城市',
    `address` VARCHAR(255) COMMENT '详细地址',
    `status` TINYINT DEFAULT 0 COMMENT '审核状态：0-待审核，1-通过，2-拒绝',
    `reject_reason` VARCHAR(500) COMMENT '拒绝原因',
    `audit_time` DATETIME COMMENT '审核时间',
    `auditor_id` BIGINT COMMENT '审核人ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='KYC认证表';

-- 3. 用户资产表
CREATE TABLE IF NOT EXISTS `user_asset` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种',
    `asset_type` TINYINT DEFAULT 1 COMMENT '资产类型：1-平台资产，2-理财资产，3-合约资产',
    `available` DECIMAL(32,8) DEFAULT 0 COMMENT '可用余额',
    `frozen` DECIMAL(32,8) DEFAULT 0 COMMENT '冻结余额',
    `total` DECIMAL(32,8) GENERATED ALWAYS AS (`available` + `frozen`) STORED COMMENT '总余额',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_currency_type` (`user_id`, `currency`, `asset_type`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_currency` (`currency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户资产表';

-- 4. 资产变动记录表
CREATE TABLE IF NOT EXISTS `asset_log` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种',
    `asset_type` TINYINT DEFAULT 1 COMMENT '资产类型',
    `change_type` TINYINT NOT NULL COMMENT '变动类型：1-充值，2-提现，3-交易买入，4-交易卖出，5-转账，6-理财收益，7-推广返佣，8-系统调整，9-人工上分，10-人工下分',
    `amount` DECIMAL(32,8) NOT NULL COMMENT '变动金额',
    `before_balance` DECIMAL(32,8) COMMENT '变动前余额',
    `after_balance` DECIMAL(32,8) COMMENT '变动后余额',
    `order_no` VARCHAR(50) COMMENT '关联订单号',
    `remark` VARCHAR(500) COMMENT '备注',
    `operator_id` BIGINT COMMENT '操作人ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_currency` (`currency`),
    KEY `idx_order_no` (`order_no`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资产变动记录表';

-- 5. 币种配置表
CREATE TABLE IF NOT EXISTS `currency_config` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种代码',
    `name` VARCHAR(50) NOT NULL COMMENT '币种名称',
    `icon` VARCHAR(255) COMMENT '图标URL',
    `decimals` TINYINT DEFAULT 8 COMMENT '小数位数',
    `min_deposit` DECIMAL(32,8) DEFAULT 0 COMMENT '最小充值金额',
    `min_withdraw` DECIMAL(32,8) DEFAULT 0 COMMENT '最小提现金额',
    `withdraw_fee` DECIMAL(32,8) DEFAULT 0 COMMENT '提现手续费',
    `withdraw_fee_type` TINYINT DEFAULT 1 COMMENT '手续费类型：1-固定，2-百分比',
    `daily_withdraw_limit` DECIMAL(32,8) COMMENT '每日提现限额',
    `deposit_enabled` TINYINT DEFAULT 1 COMMENT '是否允许充值',
    `withdraw_enabled` TINYINT DEFAULT 1 COMMENT '是否允许提现',
    `trade_enabled` TINYINT DEFAULT 1 COMMENT '是否允许交易',
    `sort_order` INT DEFAULT 0 COMMENT '排序',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_currency` (`currency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='币种配置表';

-- 6. 交易对配置表
CREATE TABLE IF NOT EXISTS `trading_pair` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `symbol` VARCHAR(20) NOT NULL COMMENT '交易对符号，如BTC/USDT',
    `base_currency` VARCHAR(20) NOT NULL COMMENT '基础币种',
    `quote_currency` VARCHAR(20) NOT NULL COMMENT '计价币种',
    `price_precision` TINYINT DEFAULT 2 COMMENT '价格精度',
    `amount_precision` TINYINT DEFAULT 8 COMMENT '数量精度',
    `min_amount` DECIMAL(32,8) DEFAULT 0 COMMENT '最小交易数量',
    `min_total` DECIMAL(32,8) DEFAULT 0 COMMENT '最小交易额',
    `maker_fee` DECIMAL(10,4) DEFAULT 0 COMMENT 'Maker手续费率',
    `taker_fee` DECIMAL(10,4) DEFAULT 0 COMMENT 'Taker手续费率',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `sort_order` INT DEFAULT 0 COMMENT '排序',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_symbol` (`symbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交易对配置表';

-- 7. 充值订单表
CREATE TABLE IF NOT EXISTS `deposit_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种',
    `amount` DECIMAL(32,8) NOT NULL COMMENT '充值金额',
    `from_address` VARCHAR(255) COMMENT '来源地址',
    `to_address` VARCHAR(255) COMMENT '充值地址',
    `tx_hash` VARCHAR(255) COMMENT '交易哈希',
    `confirmations` INT DEFAULT 0 COMMENT '确认数',
    `required_confirmations` INT DEFAULT 6 COMMENT '需要确认数',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-待确认，1-成功，2-失败',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充值订单表';

-- 8. 提现订单表
CREATE TABLE IF NOT EXISTS `withdraw_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种',
    `amount` DECIMAL(32,8) NOT NULL COMMENT '提现金额',
    `fee` DECIMAL(32,8) DEFAULT 0 COMMENT '手续费',
    `actual_amount` DECIMAL(32,8) NOT NULL COMMENT '实际到账金额',
    `to_address` VARCHAR(255) NOT NULL COMMENT '提现地址',
    `tx_hash` VARCHAR(255) COMMENT '交易哈希',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-待审核，1-审核通过，2-审核拒绝，3-处理中，4-成功，5-失败',
    `audit_time` DATETIME COMMENT '审核时间',
    `auditor_id` BIGINT COMMENT '审核人ID',
    `reject_reason` VARCHAR(500) COMMENT '拒绝原因',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提现订单表';

-- 9. 现货交易订单表
CREATE TABLE IF NOT EXISTS `spot_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `symbol` VARCHAR(20) NOT NULL COMMENT '交易对',
    `side` TINYINT NOT NULL COMMENT '方向：1-买入，2-卖出',
    `order_type` TINYINT NOT NULL COMMENT '订单类型：1-限价，2-市价',
    `price` DECIMAL(32,8) COMMENT '委托价格',
    `amount` DECIMAL(32,8) NOT NULL COMMENT '委托数量',
    `total` DECIMAL(32,8) COMMENT '委托总额',
    `filled_amount` DECIMAL(32,8) DEFAULT 0 COMMENT '成交数量',
    `filled_total` DECIMAL(32,8) DEFAULT 0 COMMENT '成交总额',
    `avg_price` DECIMAL(32,8) COMMENT '成交均价',
    `fee` DECIMAL(32,8) DEFAULT 0 COMMENT '手续费',
    `fee_currency` VARCHAR(20) COMMENT '手续费币种',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-待成交，1-部分成交，2-完全成交，3-已撤销，4-已过期',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_symbol` (`symbol`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='现货交易订单表';

-- 10. 秒合约订单表
CREATE TABLE IF NOT EXISTS `seconds_contract_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `symbol` VARCHAR(20) NOT NULL COMMENT '交易对',
    `direction` TINYINT NOT NULL COMMENT '方向：1-看涨，2-看跌',
    `amount` DECIMAL(32,8) NOT NULL COMMENT '投注金额',
    `duration` INT NOT NULL COMMENT '时长（秒）',
    `open_price` DECIMAL(32,8) NOT NULL COMMENT '开仓价格',
    `close_price` DECIMAL(32,8) COMMENT '平仓价格',
    `profit_rate` DECIMAL(10,4) DEFAULT 0 COMMENT '收益率',
    `profit` DECIMAL(32,8) DEFAULT 0 COMMENT '盈亏金额',
    `fee` DECIMAL(32,8) DEFAULT 0 COMMENT '手续费',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-进行中，1-盈利，2-亏损，3-平局',
    `open_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '开仓时间',
    `close_time` DATETIME COMMENT '平仓时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_symbol` (`symbol`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒合约订单表';

-- 11. 周期合约订单表
CREATE TABLE IF NOT EXISTS `cycle_contract_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `symbol` VARCHAR(20) NOT NULL COMMENT '交易对',
    `direction` TINYINT NOT NULL COMMENT '方向：1-看涨，2-看跌',
    `amount` DECIMAL(32,8) NOT NULL COMMENT '投注金额',
    `cycle_type` TINYINT NOT NULL COMMENT '周期类型：1-1分钟，2-5分钟，3-15分钟，4-30分钟，5-1小时，6-4小时，7-1天',
    `open_price` DECIMAL(32,8) NOT NULL COMMENT '开仓价格',
    `close_price` DECIMAL(32,8) COMMENT '平仓价格',
    `profit_rate` DECIMAL(10,4) DEFAULT 0 COMMENT '收益率',
    `profit` DECIMAL(32,8) DEFAULT 0 COMMENT '盈亏金额',
    `fee` DECIMAL(32,8) DEFAULT 0 COMMENT '手续费',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-进行中，1-盈利，2-亏损，3-平局',
    `open_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '开仓时间',
    `close_time` DATETIME COMMENT '平仓时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_symbol` (`symbol`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='周期合约订单表';

-- 12. 理财产品表
CREATE TABLE IF NOT EXISTS `finance_product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_name` VARCHAR(100) NOT NULL COMMENT '产品名称',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种',
    `min_amount` DECIMAL(32,8) DEFAULT 0 COMMENT '最小投资金额',
    `max_amount` DECIMAL(32,8) COMMENT '最大投资金额',
    `annual_rate` DECIMAL(10,4) NOT NULL COMMENT '年化收益率',
    `duration_days` INT NOT NULL COMMENT '期限（天）',
    `total_quota` DECIMAL(32,8) COMMENT '总额度',
    `remaining_quota` DECIMAL(32,8) COMMENT '剩余额度',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-下架，1-上架',
    `description` TEXT COMMENT '产品描述',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='理财产品表';

-- 13. 理财订单表
CREATE TABLE IF NOT EXISTS `finance_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `product_id` BIGINT NOT NULL COMMENT '产品ID',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种',
    `amount` DECIMAL(32,8) NOT NULL COMMENT '投资金额',
    `annual_rate` DECIMAL(10,4) NOT NULL COMMENT '年化收益率',
    `duration_days` INT NOT NULL COMMENT '期限（天）',
    `expected_profit` DECIMAL(32,8) COMMENT '预期收益',
    `actual_profit` DECIMAL(32,8) DEFAULT 0 COMMENT '实际收益',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-进行中，1-已完成，2-已赎回',
    `start_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    `end_time` DATETIME COMMENT '结束时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_product_id` (`product_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='理财订单表';

-- 14. 挖矿配置表
CREATE TABLE IF NOT EXISTS `mining_config` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `mining_name` VARCHAR(100) NOT NULL COMMENT '挖矿名称',
    `currency` VARCHAR(20) NOT NULL COMMENT '挖矿币种',
    `min_amount` DECIMAL(32,8) DEFAULT 0 COMMENT '最小投入',
    `daily_rate` DECIMAL(10,4) NOT NULL COMMENT '日收益率',
    `duration_days` INT NOT NULL COMMENT '周期（天）',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `description` TEXT COMMENT '描述',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='挖矿配置表';

-- 15. 挖矿订单表
CREATE TABLE IF NOT EXISTS `mining_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `config_id` BIGINT NOT NULL COMMENT '配置ID',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种',
    `amount` DECIMAL(32,8) NOT NULL COMMENT '投入金额',
    `daily_rate` DECIMAL(10,4) NOT NULL COMMENT '日收益率',
    `duration_days` INT NOT NULL COMMENT '周期（天）',
    `total_profit` DECIMAL(32,8) DEFAULT 0 COMMENT '累计收益',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-进行中，1-已完成',
    `start_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    `end_time` DATETIME COMMENT '结束时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_config_id` (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='挖矿订单表';

-- 16. 借贷配置表
CREATE TABLE IF NOT EXISTS `loan_config` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `loan_name` VARCHAR(100) NOT NULL COMMENT '借贷名称',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种',
    `min_amount` DECIMAL(32,8) DEFAULT 0 COMMENT '最小借贷金额',
    `max_amount` DECIMAL(32,8) COMMENT '最大借贷金额',
    `daily_interest_rate` DECIMAL(10,4) NOT NULL COMMENT '日利率',
    `max_duration_days` INT NOT NULL COMMENT '最大期限（天）',
    `collateral_rate` DECIMAL(10,4) NOT NULL COMMENT '抵押率',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='借贷配置表';

-- 17. 借贷订单表
CREATE TABLE IF NOT EXISTS `loan_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `config_id` BIGINT NOT NULL COMMENT '配置ID',
    `loan_currency` VARCHAR(20) NOT NULL COMMENT '借贷币种',
    `loan_amount` DECIMAL(32,8) NOT NULL COMMENT '借贷金额',
    `collateral_currency` VARCHAR(20) NOT NULL COMMENT '抵押币种',
    `collateral_amount` DECIMAL(32,8) NOT NULL COMMENT '抵押金额',
    `daily_interest_rate` DECIMAL(10,4) NOT NULL COMMENT '日利率',
    `duration_days` INT NOT NULL COMMENT '期限（天）',
    `total_interest` DECIMAL(32,8) DEFAULT 0 COMMENT '总利息',
    `repaid_amount` DECIMAL(32,8) DEFAULT 0 COMMENT '已还金额',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-进行中，1-已还清，2-已逾期，3-已清算',
    `start_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    `due_time` DATETIME COMMENT '到期时间',
    `repay_time` DATETIME COMMENT '还款时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='借贷订单表';

-- 18. IEO项目表
CREATE TABLE IF NOT EXISTS `ieo_project` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `project_name` VARCHAR(100) NOT NULL COMMENT '项目名称',
    `token_symbol` VARCHAR(20) NOT NULL COMMENT '代币符号',
    `total_supply` DECIMAL(32,8) NOT NULL COMMENT '总发行量',
    `sale_amount` DECIMAL(32,8) NOT NULL COMMENT '销售数量',
    `price` DECIMAL(32,8) NOT NULL COMMENT '发行价格',
    `min_buy` DECIMAL(32,8) DEFAULT 0 COMMENT '最小购买量',
    `max_buy` DECIMAL(32,8) COMMENT '最大购买量',
    `start_time` DATETIME COMMENT '开始时间',
    `end_time` DATETIME COMMENT '结束时间',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-未开始，1-进行中，2-已结束',
    `description` TEXT COMMENT '项目描述',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IEO项目表';

-- 19. IEO订单表
CREATE TABLE IF NOT EXISTS `ieo_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `project_id` BIGINT NOT NULL COMMENT '项目ID',
    `amount` DECIMAL(32,8) NOT NULL COMMENT '购买数量',
    `price` DECIMAL(32,8) NOT NULL COMMENT '购买价格',
    `total` DECIMAL(32,8) NOT NULL COMMENT '总金额',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-待支付，1-已支付，2-已取消',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IEO订单表';

-- 20. 推广返佣配置表
CREATE TABLE IF NOT EXISTS `promotion_config` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `level` TINYINT NOT NULL COMMENT '层级',
    `commission_rate` DECIMAL(10,4) NOT NULL COMMENT '返佣比例',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推广返佣配置表';

-- 21. 推广返佣记录表
CREATE TABLE IF NOT EXISTS `promotion_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `invitee_id` BIGINT NOT NULL COMMENT '被邀请人ID',
    `level` TINYINT NOT NULL COMMENT '层级',
    `order_no` VARCHAR(50) COMMENT '关联订单号',
    `order_type` TINYINT COMMENT '订单类型：1-交易，2-理财，3-挖矿',
    `commission_amount` DECIMAL(32,8) NOT NULL COMMENT '返佣金额',
    `currency` VARCHAR(20) NOT NULL COMMENT '币种',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-待发放，1-已发放',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_invitee_id` (`invitee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推广返佣记录表';

-- 22. 系统配置表 (已存在)
CREATE TABLE IF NOT EXISTS `sys_config` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `config_key` VARCHAR(100) NOT NULL COMMENT '配置键',
    `config_value` TEXT COMMENT '配置值',
    `config_type` VARCHAR(20) COMMENT '配置类型',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_config_key` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- 23. 平台统计表
CREATE TABLE IF NOT EXISTS `platform_statistics` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `stat_date` DATE NOT NULL COMMENT '统计日期',
    `total_users` INT DEFAULT 0 COMMENT '总用户数',
    `new_users` INT DEFAULT 0 COMMENT '新增用户数',
    `active_users` INT DEFAULT 0 COMMENT '活跃用户数',
    `total_deposit` DECIMAL(32,8) DEFAULT 0 COMMENT '总充值金额',
    `total_withdraw` DECIMAL(32,8) DEFAULT 0 COMMENT '总提现金额',
    `total_trade_volume` DECIMAL(32,8) DEFAULT 0 COMMENT '总交易量',
    `total_profit` DECIMAL(32,8) DEFAULT 0 COMMENT '平台总收益',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_stat_date` (`stat_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='平台统计表';

-- 插入初始数据
-- 币种配置
INSERT INTO `currency_config` (`currency`, `name`, `decimals`, `min_deposit`, `min_withdraw`, `withdraw_fee`, `status`) VALUES
('USDT', 'Tether', 2, 10.00, 10.00, 1.00, 1),
('BTC', 'Bitcoin', 8, 0.0001, 0.001, 0.0005, 1),
('ETH', 'Ethereum', 8, 0.01, 0.01, 0.005, 1),
('TRX', 'TRON', 6, 10.00, 10.00, 1.00, 1),
('BNB', 'Binance Coin', 8, 0.01, 0.01, 0.001, 1);

-- 交易对配置
INSERT INTO `trading_pair` (`symbol`, `base_currency`, `quote_currency`, `price_precision`, `amount_precision`, `min_amount`, `min_total`, `maker_fee`, `taker_fee`, `status`) VALUES
('BTC/USDT', 'BTC', 'USDT', 2, 8, 0.0001, 10.00, 0.001, 0.002, 1),
('ETH/USDT', 'ETH', 'USDT', 2, 8, 0.01, 10.00, 0.001, 0.002, 1),
('TRX/USDT', 'TRX', 'USDT', 4, 2, 10.00, 1.00, 0.001, 0.002, 1),
('BNB/USDT', 'BNB', 'USDT', 2, 8, 0.01, 10.00, 0.001, 0.002, 1);

-- 推广返佣配置
INSERT INTO `promotion_config` (`level`, `commission_rate`, `status`) VALUES
(1, 0.10, 1),
(2, 0.05, 1),
(3, 0.02, 1);
