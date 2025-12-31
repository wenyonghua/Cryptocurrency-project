-- 添加交易所和兑换管理菜单
-- 日期: 2025-12-31
-- 说明: 添加交易所、兑换管理及其子菜单

USE crypto_platform;

-- ========================================
-- 一级菜单
-- ========================================

-- 10. 交易所
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (10, 0, '交易所', 'exchange', 1, '/exchange', 'Shop', 10, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '交易所';

-- 11. 兑换管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (11, 0, '兑换管理', 'swap', 1, '/swap', 'Money', 11, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '兑换管理';

-- ========================================
-- 交易所子菜单
-- ========================================

-- 10.1 自发币
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (101, 10, '自发币', 'exchange-self-coin', 1, '/exchange/self-coin', 'views/exchange/self-coin', 'Coin', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '自发币';

-- 10.2 币币交易
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (102, 10, '币币交易', 'exchange-trade', 1, '/exchange/trade', 'views/exchange/trade', 'List', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币币交易';

-- 10.3 币种配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (103, 10, '币种配置', 'exchange-currency-config', 1, '/exchange/currency-config', 'views/exchange/currency-config', 'Setting', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币种配置';

-- 10.4 币币交易订单
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (104, 10, '币币交易订单', 'exchange-order', 1, '/exchange/order', 'views/exchange/order', 'Document', 4, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币币交易订单';

-- ========================================
-- 兑换管理子菜单
-- ========================================

-- 11.1 币种管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (111, 11, '币种管理', 'swap-currency', 1, '/swap/currency', 'views/swap/currency', 'Coin', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币种管理';

-- 11.2 兑换订单
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (112, 11, '兑换订单', 'swap-order', 1, '/swap/order', 'views/swap/order', 'Document', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '兑换订单';

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ 交易所和兑换管理菜单添加完成！' AS message;

-- 查看交易所菜单
SELECT 
    '交易所' AS '一级菜单',
    permission_name AS '子菜单',
    permission_key AS '权限标识',
    icon AS '图标'
FROM sys_permission 
WHERE parent_id = 10 AND deleted = 0
ORDER BY sort;

-- 查看兑换管理菜单
SELECT 
    '兑换管理' AS '一级菜单',
    permission_name AS '子菜单',
    permission_key AS '权限标识',
    icon AS '图标'
FROM sys_permission 
WHERE parent_id = 11 AND deleted = 0
ORDER BY sort;

