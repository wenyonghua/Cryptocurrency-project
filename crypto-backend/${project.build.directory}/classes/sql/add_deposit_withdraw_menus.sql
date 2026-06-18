-- 添加充提管理的完整菜单项
-- 日期: 2025-12-31
-- 说明: 添加充值列表、提现列表、充值通道配置、提现通道配置

USE crypto_platform;

-- 删除旧的充提管理子菜单（如果存在）
DELETE FROM sys_permission WHERE parent_id = 3 AND id >= 31 AND id <= 34;

-- ========================================
-- 充提管理子菜单（完整版）
-- ========================================

-- 3.1 充值列表
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (31, 3, '充值列表', 'deposit-list', 1, '/deposit/list', 'views/deposit/list', 'Download', 1, 1);

-- 3.2 提现列表
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (32, 3, '提现列表', 'withdraw-list', 1, '/withdraw/list', 'views/withdraw/list', 'Upload', 2, 1);

-- 3.3 充值通道配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (33, 3, '充值通道配置', 'deposit-channel', 1, '/deposit/channel', 'views/deposit/channel', 'Setting', 3, 1);

-- 3.4 提现通道配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (34, 3, '提现通道配置', 'withdraw-channel', 1, '/withdraw/channel', 'views/withdraw/channel', 'Tools', 4, 1);

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ 充提管理菜单添加完成！' AS message;

-- 查看充提管理的所有子菜单
SELECT 
    id AS '菜单ID',
    permission_name AS '菜单名称',
    permission_key AS '权限标识',
    icon AS '图标',
    sort AS '排序'
FROM sys_permission 
WHERE parent_id = 3 AND deleted = 0
ORDER BY sort;

