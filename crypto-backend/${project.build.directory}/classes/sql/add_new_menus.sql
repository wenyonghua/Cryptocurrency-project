-- 添加新币发行、周期合约交易、秒合约管理菜单
-- 日期: 2025-12-31
-- 说明: 添加新的一级菜单和子菜单

USE crypto_platform;

-- ========================================
-- 一级菜单
-- ========================================

-- 7. 新币发行
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (7, 0, '新币发行', 'new-coin', 1, '/new-coin', 'Coin', 7, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '新币发行';

-- 8. 周期合约交易
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (8, 0, '周期合约交易', 'cycle-contract', 1, '/cycle-contract', 'Grid', 8, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '周期合约交易';

-- 9. 秒合约管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (9, 0, '秒合约管理', 'seconds-contract', 1, '/seconds-contract', 'TrendCharts', 9, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '秒合约管理';

-- ========================================
-- 二级菜单 - 新币发行
-- ========================================

-- 7.1 申购订单
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (71, 7, '申购订单', 'new-coin-order', 1, '/new-coin/order', 'views/new-coin/order', 'Document', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '申购订单';

-- 7.2 订阅审核
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (72, 7, '订阅审核', 'new-coin-audit', 1, '/new-coin/audit', 'views/new-coin/audit', 'Select', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '订阅审核';

-- 7.3 币种列表
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (73, 7, '币种列表', 'new-coin-list', 1, '/new-coin/list', 'views/new-coin/list', 'Memo', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币种列表';

-- ========================================
-- 二级菜单 - 周期合约交易
-- ========================================

-- 8.1 币种配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (81, 8, '币种配置', 'cycle-contract-config', 1, '/cycle-contract/config', 'views/cycle-contract/config', 'Operation', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币种配置';

-- 8.2 委托列表
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (82, 8, '委托列表', 'cycle-contract-order', 1, '/cycle-contract/order', 'views/cycle-contract/order', 'List', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '委托列表';

-- 8.3 持仓列表
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`)
VALUES (83, 8, '持仓列表', 'cycle-contract-position', 1, '/cycle-contract/position', 'views/cycle-contract/position', 'Files', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '持仓列表';

-- ========================================
-- 二级菜单 - 秒合约管理
-- ========================================

-- 9.1 币种配置
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (91, 9, '币种配置', 'seconds-contract-config', 1, '/seconds-contract/config', 'views/seconds-contract/config', 'Operation', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '币种配置';

-- 9.2 秒合约订单
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (92, 9, '秒合约订单', 'seconds-contract-order', 1, '/seconds-contract/order', 'views/seconds-contract/order', 'Document', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '秒合约订单';

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ 新菜单添加完成！' AS message;

-- 查看所有一级菜单
SELECT 
    id AS '菜单ID',
    permission_name AS '菜单名称',
    permission_key AS '权限标识',
    icon AS '图标',
    sort AS '排序'
FROM sys_permission 
WHERE parent_id = 0 AND deleted = 0
ORDER BY sort;

-- 查看新添加的子菜单
SELECT 
    p1.permission_name AS '一级菜单',
    p2.permission_name AS '子菜单',
    p2.permission_key AS '权限标识',
    p2.sort AS '排序'
FROM sys_permission p1
JOIN sys_permission p2 ON p1.id = p2.parent_id
WHERE p1.id IN (7, 8, 9) AND p2.deleted = 0
ORDER BY p1.id, p2.sort;

