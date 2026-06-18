-- 补齐 user_asset 表字段（与实体 UserAsset 对齐）
-- 在 MySQL 客户端执行一次即可；若列已存在会报错，可忽略

USE crypto_platform;

ALTER TABLE `user_asset`
    ADD COLUMN `asset_type` TINYINT DEFAULT 1 COMMENT '资产类型：1-平台资产，2-理财资产，3-合约资产' AFTER `currency`;

-- 可选：若希望启用逻辑删除，再执行下面语句并在实体中恢复 @TableLogic
-- ALTER TABLE `user_asset` ADD COLUMN `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除' AFTER `frozen`;
