-- 插入菜单数据
-- 日期: 2025-12-31
-- 说明: 添加左侧菜单栏的完整菜单项

USE crypto_platform;

-- 清空现有菜单数据（可选，如果需要重新初始化）
-- TRUNCATE TABLE sys_permission;

-- ========================================
-- 一级菜单
-- ========================================

-- 1. 数据概览
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`)
VALUES (1, 0, '数据概览', 'dashboard', 1, '/dashboard', 'DataAnalysis', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '数据概览';

-- 2. 玩家管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`)
VALUES (2, 0, '玩家管理', 'user', 1, '/user', 'User', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '玩家管理';

-- 3. 充提管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`)
VALUES (3, 0, '充提管理', 'deposit', 1, '/deposit', 'Wallet', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '充提管理';

-- 4. 交易管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`)
VALUES (4, 0, '交易管理', 'trade', 1, '/trade', 'TrendCharts', 4, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '交易管理';

-- 5. 金融产品
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`)
VALUES (5, 0, '金融产品', 'finance', 1, '/finance', 'Money', 5, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '金融产品';

-- 6. 系统管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`)
VALUES (6, 0, '系统管理', 'system', 1, '/system', 'Setting', 6, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '系统管理';

-- ========================================
-- 二级菜单 - 玩家管理
-- ========================================

-- 2.1 玩家用户
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (21, 2, '玩家用户', 'user-list', 1, '/user/list', 'views/user/list', 'User', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '玩家用户';

-- 2.2 玩家资产
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (22, 2, '玩家资产', 'user-asset', 1, '/user/asset', 'views/user/asset', 'Wallet', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '玩家资产';

-- 2.3 实名认证
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (23, 2, '实名认证', 'user-kyc', 1, '/user/kyc', 'views/user/kyc', 'Checked', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '实名认证';

-- ========================================
-- 二级菜单 - 充提管理
-- ========================================

-- 3.1 充值记录
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (31, 3, '充值记录', 'deposit-list', 1, '/deposit/list', 'views/deposit/list', 'Download', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '充值记录';

-- 3.2 提现记录
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (32, 3, '提现记录', 'withdraw-list', 1, '/withdraw/list', 'views/withdraw/list', 'Upload', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '提现记录';

-- ========================================
-- 二级菜单 - 交易管理
-- ========================================

-- 4.1 现货交易
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (41, 4, '现货交易', 'spot-order', 1, '/trade/spot', 'views/trade/spot', 'TrendCharts', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '现货交易';

-- 4.2 秒合约
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (42, 4, '秒合约', 'seconds-order', 1, '/trade/seconds', 'views/trade/seconds', 'Timer', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '秒合约';

-- 4.3 周期合约
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (43, 4, '周期合约', 'cycle-order', 1, '/trade/cycle', 'views/trade/cycle', 'Calendar', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '周期合约';

-- ========================================
-- 二级菜单 - 金融产品
-- ========================================

-- 5.1 理财产品
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (51, 5, '理财产品', 'finance-product', 1, '/finance/product', 'views/finance/product', 'Money', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '理财产品';

-- 5.2 挖矿配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (52, 5, '挖矿配置', 'mining-config', 1, '/finance/mining', 'views/finance/mining', 'Cpu', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '挖矿配置';

-- 5.3 借贷配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (53, 5, '借贷配置', 'loan-config', 1, '/finance/loan', 'views/finance/loan', 'CreditCard', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '借贷配置';

-- 5.4 IEO项目
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (54, 5, 'IEO项目', 'ieo-project', 1, '/finance/ieo', 'views/finance/ieo', 'Opportunity', 4, 1)
ON DUPLICATE KEY UPDATE `permission_name` = 'IEO项目';

-- ========================================
-- 二级菜单 - 系统管理
-- ========================================

-- 6.1 币种配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (61, 6, '币种配置', 'currency-config', 1, '/system/currency', 'views/system/currency', 'Coin', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币种配置';

-- 6.2 交易对配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (62, 6, '交易对配置', 'trading-pair', 1, '/system/trading-pair', 'views/system/trading-pair', 'Connection', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '交易对配置';

-- 6.3 系统配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (63, 6, '系统配置', 'system-config', 1, '/system/config', 'views/system/config', 'Tools', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '系统配置';

-- ========================================
-- 按钮权限 - 玩家用户
-- ========================================

-- 玩家用户 - 详情
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`) 
VALUES (211, 21, '详情', 'player:user:detail', 2, 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '详情';

-- 玩家用户 - 编辑
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`) 
VALUES (212, 21, '编辑', 'player:user:edit', 2, 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '编辑';

-- 玩家用户 - 修改
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`) 
VALUES (213, 21, '修改', 'player:user:update', 2, 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '修改';

-- 玩家用户 - 彩金
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`) 
VALUES (214, 21, '彩金', 'player:user:bonus', 2, 4, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '彩金';

-- 玩家用户 - 人工上下分
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`) 
VALUES (215, 21, '人工上下分', 'player:user:adjust', 2, 5, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '人工上下分';

-- 玩家用户 - 完善地址配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`) 
VALUES (216, 21, '完善地址配置', 'player:user:address', 2, 6, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '完善地址配置';

-- 玩家用户 - 借贷上级代理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`) 
VALUES (217, 21, '借贷上级代理', 'player:user:agent', 2, 7, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '借贷上级代理';

-- 玩家用户 - 删除
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`) 
VALUES (218, 21, '删除', 'player:user:delete', 2, 8, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '删除';

-- ========================================
-- 查询结果
-- ========================================

SELECT '菜单数据插入完成！' AS message;

-- 查看插入的菜单
SELECT 
    p1.id AS '菜单ID',
    p1.permission_name AS '菜单名称',
    p1.permission_key AS '权限标识',
    CASE p1.permission_type 
        WHEN 1 THEN '菜单' 
        WHEN 2 THEN '按钮' 
        ELSE '未知' 
    END AS '类型',
    p1.path AS '路径',
    p1.sort AS '排序',
    p2.permission_name AS '父菜单'
FROM sys_permission p1
LEFT JOIN sys_permission p2 ON p1.parent_id = p2.id
WHERE p1.deleted = 0
ORDER BY p1.parent_id, p1.sort;

