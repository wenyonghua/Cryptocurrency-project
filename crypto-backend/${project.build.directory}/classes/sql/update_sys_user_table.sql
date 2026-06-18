-- 更新 sys_user 表结构
-- 日期: 2025-12-31
-- 说明: 添加缺失的字段并修正字段名

USE crypto_platform;

-- 添加 real_name 字段（如果不存在）
ALTER TABLE sys_user ADD COLUMN IF NOT EXISTS real_name VARCHAR(50) COMMENT '真实姓名' AFTER user_type;

-- 添加 id_card 字段（如果不存在）
ALTER TABLE sys_user ADD COLUMN IF NOT EXISTS id_card VARCHAR(20) COMMENT '身份证号' AFTER real_name;

-- 修改字段名：last_login_time -> login_time（如果存在）
ALTER TABLE sys_user CHANGE COLUMN IF EXISTS last_login_time login_time DATETIME COMMENT '最后登录时间';

-- 修改字段名：last_login_ip -> login_ip（如果存在）
ALTER TABLE sys_user CHANGE COLUMN IF EXISTS last_login_ip login_ip VARCHAR(50) COMMENT '最后登录IP';

-- 验证表结构
SELECT 
    COLUMN_NAME, 
    DATA_TYPE, 
    IS_NULLABLE, 
    COLUMN_DEFAULT, 
    COLUMN_COMMENT 
FROM 
    INFORMATION_SCHEMA.COLUMNS 
WHERE 
    TABLE_SCHEMA = 'crypto_platform' 
    AND TABLE_NAME = 'sys_user'
ORDER BY 
    ORDINAL_POSITION;

