-- 添加玩家管理的额外菜单项
-- 日期: 2025-12-31
-- 说明: 添加归集订单、授权管理、银行卡管理、登录日志

USE crypto_platform;

-- 删除旧的玩家管理子菜单（如果存在）
DELETE FROM sys_permission WHERE parent_id = 2 AND id >= 21 AND id <= 27;

-- ========================================
-- 玩家管理子菜单（完整版）
-- ========================================

-- 2.1 玩家用户
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (21, 2, '玩家用户', 'user-list', 1, '/user/list', 'views/user/list', 'User', 1, 1);

-- 2.2 归集订单
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (22, 2, '归集订单', 'user-order', 1, '/user/order', 'views/user/order', 'List', 2, 1);

-- 2.3 授权管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (23, 2, '授权管理', 'user-auth', 1, '/user/auth', 'views/user/auth', 'Key', 3, 1);

-- 2.4 玩家资产
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (24, 2, '玩家资产', 'user-asset', 1, '/user/asset', 'views/user/asset', 'Wallet', 4, 1);

-- 2.5 银行卡管理
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (25, 2, '银行卡管理', 'user-bank', 1, '/user/bank', 'views/user/bank', 'CreditCard', 5, 1);

-- 2.6 实名认证
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (26, 2, '实名认证', 'user-kyc', 1, '/user/kyc', 'views/user/kyc', 'Checked', 6, 1);

-- 2.7 登录日志
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_name`, `permission_key`, `permission_type`, `path`, `component`, `icon`, `sort`, `status`) 
VALUES (27, 2, '登录日志', 'user-login-log', 1, '/user/login-log', 'views/user/login-log', 'Document', 7, 1);

-- ========================================
-- 查询结果
-- ========================================

SELECT '✅ 玩家管理菜单添加完成！' AS message;

-- 查看玩家管理的所有子菜单
SELECT 
    id AS '菜单ID',
    permission_name AS '菜单名称',
    permission_key AS '权限标识',
    icon AS '图标',
    sort AS '排序'
FROM sys_permission 
WHERE parent_id = 2 AND deleted = 0
ORDER BY sort;

