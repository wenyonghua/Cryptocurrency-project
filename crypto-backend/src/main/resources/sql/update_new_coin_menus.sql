-- 更新新币发行菜单
-- 日期: 2025-12-31
-- 说明: 添加完整的新币发行子菜单和按钮权限

USE crypto_platform;

-- 删除旧的按钮权限
DELETE FROM sys_permission WHERE parent_id IN (71, 72, 73) AND permission_type = 2;

-- ========================================
-- 新币发行子菜单（已存在）
-- ========================================
-- 7.1 申购订单 (ID: 71)
-- 7.2 订阅审核 (ID: 72)
-- 7.3 币种列表 (ID: 73)

-- ========================================
-- 按钮权限 - 申购订单 (71)
-- ========================================

-- 申购订单 - 查询
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (711, 71, '申购订单查询', 'new-coin:order:query', 2, 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '申购订单查询';

-- 申购订单 - 新增
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (712, 71, '申购订单新增', 'new-coin:order:add', 2, 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '申购订单新增';

-- 申购订单 - 修改
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (713, 71, '申购订单修改', 'new-coin:order:edit', 2, 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '申购订单修改';

-- 申购订单 - 删除
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (714, 71, '申购订单删除', 'new-coin:order:remove', 2, 4, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '申购订单删除';

-- 申购订单 - 导出
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (715, 71, '申购订单导出', 'new-coin:order:export', 2, 5, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '申购订单导出';

-- ========================================
-- 按钮权限 - 币种列表 (73)
-- ========================================

-- 币种列表 - 查询
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (731, 73, '发币查询', 'new-coin:list:query', 2, 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '发币查询';

-- 币种列表 - 新增
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (732, 73, '发币新增', 'new-coin:list:add', 2, 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '发币新增';

-- 币种列表 - 修改
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (733, 73, '发币修改', 'new-coin:list:edit', 2, 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '发币修改';

-- 币种列表 - 删除
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (734, 73, '发币删除', 'new-coin:list:remove', 2, 4, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '发币删除';

-- 币种列表 - 导出
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `sort`, `status`)
VALUES (735, 73, '发币导出', 'new-coin:list:export', 2, 5, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '发币导出';

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ 新币发行菜单和按钮权限添加完成！' AS message;

-- 查看新币发行的所有子菜单
SELECT
    p1.permission_name AS '菜单',
    p2.permission_name AS '按钮权限',
    p2.permission_key AS '权限标识'
FROM sys_permission p1
LEFT JOIN sys_permission p2 ON p1.id = p2.parent_id AND p2.permission_type = 2 AND p2.deleted = 0
WHERE p1.parent_id = 7 AND p1.deleted = 0
ORDER BY p1.sort, p2.sort;

