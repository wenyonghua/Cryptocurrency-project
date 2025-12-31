-- 更新秒合约管理菜单
-- 日期: 2025-12-31
-- 说明: 添加币种周期菜单

USE crypto_platform;

-- ========================================
-- 秒合约管理子菜单
-- ========================================

-- 9.3 币种周期
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (93, 9, '币种周期', 'seconds-contract-period', 1, '/seconds-contract/period', 'views/seconds-contract/period', 'Timer', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币种周期';

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ 秒合约管理菜单更新完成！' AS message;

-- 查看秒合约管理的所有子菜单
SELECT 
    id AS '菜单ID',
    permission_name AS '菜单名称',
    permission_key AS '权限标识',
    icon AS '图标',
    sort AS '排序'
FROM sys_permission 
WHERE parent_id = 9 AND deleted = 0
ORDER BY sort;

