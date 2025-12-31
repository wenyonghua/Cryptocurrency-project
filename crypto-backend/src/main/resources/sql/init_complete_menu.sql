-- 完整菜单数据初始化脚本
-- 日期: 2025-12-31
-- 说明: 初始化所有菜单项，包括一级菜单和二级菜单

USE crypto_platform;

-- 清空现有菜单数据
DELETE FROM sys_permission WHERE id > 0;

-- ========================================
-- 一级菜单
-- ========================================

-- 1. 数据概览
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (1, 0, '数据概览', 'dashboard', 1, '/dashboard', 'DataAnalysis', 1, 1);

-- 2. 玩家管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (2, 0, '玩家管理', 'user', 1, '/user', 'User', 2, 1);

-- 3. 充提管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (3, 0, '充提管理', 'deposit', 1, '/deposit', 'Wallet', 3, 1);

-- 4. 交易管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (4, 0, '交易管理', 'trade', 1, '/trade', 'TrendCharts', 4, 1);

-- 5. 金融产品
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (5, 0, '金融产品', 'finance', 1, '/finance', 'Money', 5, 1);

-- 6. 系统管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (6, 0, '系统管理', 'system', 1, '/system', 'Setting', 6, 1);

-- ========================================
-- 二级菜单 - 玩家管理
-- ========================================

-- 2.1 玩家用户
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (21, 2, '玩家用户', 'user-list', 1, '/user/list', 'views/user/list', 'User', 1, 1);

-- 2.2 玩家资产
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (22, 2, '玩家资产', 'user-asset', 1, '/user/asset', 'views/user/asset', 'Wallet', 2, 1);

-- 2.3 实名认证
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (23, 2, '实名认证', 'user-kyc', 1, '/user/kyc', 'views/user/kyc', 'Checked', 3, 1);

-- ========================================
-- 二级菜单 - 充提管理
-- ========================================

-- 3.1 充值记录
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (31, 3, '充值记录', 'deposit-list', 1, '/deposit/list', 'views/deposit/list', 'Download', 1, 1);

-- 3.2 提现记录
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (32, 3, '提现记录', 'withdraw-list', 1, '/withdraw/list', 'views/withdraw/list', 'Upload', 2, 1);

-- ========================================
-- 二级菜单 - 交易管理
-- ========================================

-- 4.1 现货交易
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (41, 4, '现货交易', 'spot-order', 1, '/trade/spot', 'views/trade/spot', 'TrendCharts', 1, 1);

-- 4.2 秒合约
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (42, 4, '秒合约', 'seconds-order', 1, '/trade/seconds', 'views/trade/seconds', 'Timer', 2, 1);

-- 4.3 周期合约
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (43, 4, '周期合约', 'cycle-order', 1, '/trade/cycle', 'views/trade/cycle', 'Calendar', 3, 1);

-- ========================================
-- 二级菜单 - 金融产品
-- ========================================

-- 5.1 理财产品
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (51, 5, '理财产品', 'finance-product', 1, '/finance/product', 'views/finance/product', 'Money', 1, 1);

-- 5.2 挖矿配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (52, 5, '挖矿配置', 'mining-config', 1, '/finance/mining', 'views/finance/mining', 'Cpu', 2, 1);

-- 5.3 借贷配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (53, 5, '借贷配置', 'loan-config', 1, '/finance/loan', 'views/finance/loan', 'CreditCard', 3, 1);

-- 5.4 IEO项目
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (54, 5, 'IEO项目', 'ieo-project', 1, '/finance/ieo', 'views/finance/ieo', 'Opportunity', 4, 1);

-- ========================================
-- 二级菜单 - 系统管理
-- ========================================

-- 6.1 币种配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (61, 6, '币种配置', 'currency-config', 1, '/system/currency', 'views/system/currency', 'Coin', 1, 1);

-- 6.2 交易对配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (62, 6, '交易对配置', 'trading-pair', 1, '/system/trading-pair', 'views/system/trading-pair', 'Connection', 2, 1);

-- 6.3 系统配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (63, 6, '系统配置', 'system-config', 1, '/system/config', 'views/system/config', 'Tools', 3, 1);

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ 菜单数据初始化完成！' AS message;
SELECT COUNT(*) AS '菜单总数' FROM sys_permission WHERE deleted = 0;

