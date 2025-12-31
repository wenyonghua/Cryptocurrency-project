-- 添加平台数据（数据概览）的子菜单
-- 日期: 2025-12-31
-- 说明: 添加楼层信息、每日数据、代理数据、玩家数据

USE crypto_platform;

-- 删除旧的平台数据子菜单（如果存在）
DELETE FROM sys_permission WHERE parent_id = 1 AND id >= 11 AND id <= 14;

-- ========================================
-- 平台数据子菜单
-- ========================================

-- 1.1 楼层信息
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (11, 1, '楼层信息', 'platform-floor', 1, '/platform/floor', 'views/platform/floor', 'OfficeBuilding', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '楼层信息';

-- 1.2 每日数据
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (12, 1, '每日数据', 'platform-daily', 1, '/platform/daily', 'views/platform/daily', 'Calendar', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '每日数据';

-- 1.3 代理数据
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (13, 1, '代理数据', 'platform-agent', 1, '/platform/agent', 'views/platform/agent', 'UserFilled', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '代理数据';

-- 1.4 玩家数据
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (14, 1, '玩家数据', 'platform-player', 1, '/platform/player', 'views/platform/player', 'User', 4, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '玩家数据';

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ 平台数据子菜单添加完成！' AS message;

-- 查看平台数据的所有子菜单
SELECT 
    id AS '菜单ID',
    permission_name AS '菜单名称',
    permission_key AS '权限标识',
    icon AS '图标',
    sort AS '排序'
FROM sys_permission 
WHERE parent_id = 1 AND deleted = 0
ORDER BY sort;

