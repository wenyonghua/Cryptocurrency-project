-- 添加DEFI挖矿菜单
-- 日期: 2025-12-31
-- 说明: 添加DEFI挖矿一级菜单及其子菜单

USE crypto_platform;

-- ========================================
-- 一级菜单
-- ========================================

-- 12. DEFI挖矿
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (12, 0, 'DEFI挖矿', 'defi-mining', 1, '/defi-mining', 'Pointer', 12, 1)
ON DUPLICATE KEY UPDATE `permission_name` = 'DEFI挖矿';

-- ========================================
-- DEFI挖矿子菜单
-- ========================================

-- 12.1 DEFI订单
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (121, 12, 'DEFI订单', 'defi-order', 1, '/defi-mining/order', 'views/defi-mining/order', 'Document', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = 'DEFI订单';

-- 12.2 空投活动
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (122, 12, '空投活动', 'defi-airdrop', 1, '/defi-mining/airdrop', 'views/defi-mining/airdrop', 'Present', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '空投活动';

-- 12.3 挖矿利率配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (123, 12, '挖矿利率配置', 'defi-rate-config', 1, '/defi-mining/rate-config', 'views/defi-mining/rate-config', 'Setting', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '挖矿利率配置';

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ DEFI挖矿菜单添加完成！' AS message;

-- 查看DEFI挖矿菜单
SELECT 
    'DEFI挖矿' AS '一级菜单',
    permission_name AS '子菜单',
    permission_key AS '权限标识',
    icon AS '图标',
    sort AS '排序'
FROM sys_permission 
WHERE parent_id = 12 AND deleted = 0
ORDER BY sort;

