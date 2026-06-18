-- 修复DEFI挖矿菜单
-- 日期: 2025-12-31
-- 说明: 修复ID冲突，将DEFI挖矿设为一级菜单

USE crypto_platform;

-- ========================================
-- 删除旧的错误数据
-- ========================================

DELETE FROM sys_permission WHERE id IN (12, 121, 122, 123);

-- ========================================
-- 重新添加DEFI挖矿（使用新ID）
-- ========================================

-- 17. DEFI挖矿（一级菜单）
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (17, 0, 'DEFI挖矿', 'defi-mining', 1, '/defi-mining', 'Pointer', 12, 1)
ON DUPLICATE KEY UPDATE `permission_name` = 'DEFI挖矿';

-- 17.1 DEFI订单
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (171, 17, 'DEFI订单', 'defi-order', 1, '/defi-mining/order', 'views/defi-mining/order', 'Document', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = 'DEFI订单';

-- 17.2 空投活动
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (172, 17, '空投活动', 'defi-airdrop', 1, '/defi-mining/airdrop', 'views/defi-mining/airdrop', 'Present', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '空投活动';

-- 17.3 挖矿利率配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (173, 17, '挖矿利率配置', 'defi-rate-config', 1, '/defi-mining/rate-config', 'views/defi-mining/rate-config', 'Setting', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '挖矿利率配置';

-- ========================================
-- 重新添加每日数据（恢复ID 12）
-- ========================================

INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (12, 1, '每日数据', 'platform-daily', 1, '/platform/daily', 'views/platform/daily', 'Calendar', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '每日数据';

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ DEFI挖矿菜单修复完成！' AS message;

-- 查看所有一级菜单
SELECT 
    id AS 'ID',
    permission_name AS '一级菜单',
    sort AS '排序'
FROM sys_permission 
WHERE parent_id = 0 AND deleted = 0
ORDER BY sort;

-- 查看DEFI挖矿菜单
SELECT 
    'DEFI挖矿' AS '一级菜单',
    permission_name AS '子菜单',
    permission_key AS '权限标识'
FROM sys_permission 
WHERE parent_id = 17 AND deleted = 0
ORDER BY sort;

-- 查看数据概览菜单
SELECT 
    '数据概览' AS '一级菜单',
    permission_name AS '子菜单',
    permission_key AS '权限标识'
FROM sys_permission 
WHERE parent_id = 1 AND deleted = 0
ORDER BY sort;

