-- 修复交易所和兑换管理菜单
-- 日期: 2025-12-31
-- 说明: 修复ID冲突，重新分配菜单ID

USE crypto_platform;

-- ========================================
-- 删除旧的错误数据
-- ========================================

DELETE FROM sys_permission WHERE id IN (11, 111, 112);

-- ========================================
-- 重新添加兑换管理（使用新ID）
-- ========================================

-- 15. 兑换管理（一级菜单）
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (15, 0, '兑换管理', 'swap', 1, '/swap', 'Money', 11, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '兑换管理';

-- 15.1 币种管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (151, 15, '币种管理', 'swap-currency', 1, '/swap/currency', 'views/swap/currency', 'Coin', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币种管理';

-- 15.2 兑换订单
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (152, 15, '兑换订单', 'swap-order', 1, '/swap/order', 'views/swap/order', 'Document', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '兑换订单';

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ 菜单修复完成！' AS message;

-- 查看所有一级菜单
SELECT 
    id AS 'ID',
    permission_name AS '一级菜单',
    sort AS '排序'
FROM sys_permission 
WHERE parent_id = 0 AND deleted = 0
ORDER BY sort;

-- 查看交易所菜单
SELECT 
    '交易所' AS '一级菜单',
    permission_name AS '子菜单',
    permission_key AS '权限标识'
FROM sys_permission 
WHERE parent_id = 10 AND deleted = 0
ORDER BY sort;

-- 查看兑换管理菜单
SELECT 
    '兑换管理' AS '一级菜单',
    permission_name AS '子菜单',
    permission_key AS '权限标识'
FROM sys_permission 
WHERE parent_id = 15 AND deleted = 0
ORDER BY sort;

