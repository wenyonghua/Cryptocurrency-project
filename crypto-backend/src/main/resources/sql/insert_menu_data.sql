-- 插入菜单数据
-- 日期: 2025-12-31
-- 说明: 添加左侧菜单栏的菜单项

USE crypto_platform;

-- 清空现有菜单数据（可选，如果需要重新初始化）
-- TRUNCATE TABLE sys_permission;

-- ========================================
-- 一级菜单
-- ========================================

-- 1. 平台数据
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (1, 0, '平台数据', 'platform:data', 1, '/platform', 'DataAnalysis', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '平台数据';

-- 2. 玩家管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `icon`, `sort`, `status`) 
VALUES (2, 0, '玩家管理', 'player:manage', 1, '/player', 'User', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '玩家管理';

-- ========================================
-- 二级菜单 - 玩家管理
-- ========================================

-- 2.1 玩家用户
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (21, 2, '玩家用户', 'player:user:list', 1, '/player/user', 'views/player/user', 'User', 1, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '玩家用户';

-- 2.2 归属订单
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (22, 2, '归属订单', 'player:order:list', 1, '/player/order', 'views/player/order', 'List', 2, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '归属订单';

-- 2.3 授权管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (23, 2, '授权管理', 'player:auth:manage', 1, '/player/auth', 'views/player/auth', 'Key', 3, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '授权管理';

-- 2.4 玩家资产
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (24, 2, '玩家资产', 'player:asset:list', 1, '/player/asset', 'views/player/asset', 'Wallet', 4, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '玩家资产';

-- 2.5 银行卡管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (25, 2, '银行卡管理', 'player:bank:manage', 1, '/player/bank', 'views/player/bank', 'CreditCard', 5, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '银行卡管理';

-- 2.6 实名认证
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (26, 2, '实名认证', 'player:kyc:manage', 1, '/player/kyc', 'views/player/kyc', 'Checked', 6, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '实名认证';

-- 2.7 登录日志
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (27, 2, '登录日志', 'player:login:log', 1, '/player/login-log', 'views/player/login-log', 'Document', 7, 1)
ON DUPLICATE KEY UPDATE `permission_name` = '登录日志';

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

