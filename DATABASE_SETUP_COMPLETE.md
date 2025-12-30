# 🎉 数据库初始化完成报告

## 📊 数据库信息

**数据库名称**: `crypto_platform`  
**字符集**: `utf8mb4`  
**排序规则**: `utf8mb4_unicode_ci`  
**MySQL版本**: 5.7.24  
**初始化时间**: 2025-12-30

---

## 📁 数据库表结构（23张表）

### 1. 用户相关表（2张）
- ✅ `sys_user` - 用户表（3条记录）
- ✅ `user_kyc` - KYC认证表

### 2. 资产相关表（2张）
- ✅ `user_asset` - 用户资产表（6条记录）
- ✅ `asset_log` - 资产变动记录表

### 3. 交易相关表（3张）
- ✅ `spot_order` - 现货交易订单表
- ✅ `seconds_contract_order` - 秒合约订单表
- ✅ `cycle_contract_order` - 周期合约订单表

### 4. 充值提现表（2张）
- ✅ `deposit_order` - 充值订单表
- ✅ `withdraw_order` - 提现订单表

### 5. 金融产品表（8张）
- ✅ `finance_product` - 理财产品表（5条记录）
- ✅ `finance_order` - 理财订单表
- ✅ `mining_config` - 挖矿配置表（3条记录）
- ✅ `mining_order` - 挖矿订单表
- ✅ `loan_config` - 借贷配置表（3条记录）
- ✅ `loan_order` - 借贷订单表
- ✅ `ieo_project` - IEO项目表（2条记录）
- ✅ `ieo_order` - IEO订单表

### 6. 系统配置表（3张）
- ✅ `currency_config` - 币种配置表（9条记录）
- ✅ `trading_pair` - 交易对配置表（9条记录）
- ✅ `sys_config` - 系统配置表

### 7. 推广相关表（2张）
- ✅ `promotion_config` - 推广配置表
- ✅ `promotion_record` - 推广记录表

### 8. 统计表（1张）
- ✅ `platform_statistics` - 平台统计表

---

## 🔑 初始化数据

### 1. 用户数据（3个用户）

| ID | 用户名 | 昵称 | 类型 | 状态 |
|----|--------|------|------|------|
| 1 | admin | 系统管理员 | 管理员 | 正常 |
| 2 | user001 | 测试用户1 | 普通用户 | 正常 |
| 3 | user002 | 测试用户2 | 普通用户 | 正常 |

**默认密码**: `admin123`（所有用户）

### 2. 币种配置（9个币种）

| 币种 | 名称 | 状态 |
|------|------|------|
| USDT | Tether | 启用 |
| BTC | Bitcoin | 启用 |
| ETH | Ethereum | 启用 |
| TRX | TRON | 启用 |
| BNB | Binance Coin | 启用 |
| XRP | Ripple | 启用 |
| ADA | Cardano | 启用 |
| DOGE | Dogecoin | 启用 |
| SOL | Solana | 启用 |

### 3. 交易对配置（9个交易对）

| 交易对 | 基础币种 | 计价币种 | 状态 |
|--------|----------|----------|------|
| BTC/USDT | BTC | USDT | 启用 |
| ETH/USDT | ETH | USDT | 启用 |
| TRX/USDT | TRX | USDT | 启用 |
| BNB/USDT | BNB | USDT | 启用 |
| XRP/USDT | XRP | USDT | 启用 |
| ADA/USDT | ADA | USDT | 启用 |
| DOGE/USDT | DOGE | USDT | 启用 |
| SOL/USDT | SOL | USDT | 启用 |
| ETH/BTC | ETH | BTC | 启用 |

### 4. 理财产品（5个产品）

| ID | 产品名称 | 币种 | 年化收益率 | 期限（天） |
|----|----------|------|------------|------------|
| 1 | BTC活期理财 | BTC | 5.00% | 0（活期） |
| 2 | ETH定期30天 | ETH | 8.00% | 30 |
| 3 | USDT定期60天 | USDT | 10.00% | 60 |
| 4 | USDT定期90天 | USDT | 12.00% | 90 |
| 5 | BTC定期180天 | BTC | 15.00% | 180 |

### 5. 挖矿配置（3个配置）

| ID | 挖矿名称 | 币种 | 日收益率 | 期限（天） |
|----|----------|------|----------|------------|
| 1 | BTC云算力挖矿 | BTC | 0.0548% | 365 |
| 2 | ETH云算力挖矿 | ETH | 0.0493% | 365 |
| 3 | USDT流动性挖矿 | USDT | 0.0411% | 365 |

### 6. 借贷配置（3个配置）

| ID | 借贷名称 | 币种 | 日利率 | 最长期限（天） |
|----|----------|------|--------|----------------|
| 1 | USDT质押借贷 | USDT | 0.05% | 30 |
| 2 | BTC质押借贷 | BTC | 0.03% | 30 |
| 3 | ETH质押借贷 | ETH | 0.04% | 30 |

### 7. IEO项目（2个项目）

| ID | 项目名称 | 代币符号 | 价格 | 状态 |
|----|----------|----------|------|------|
| 1 | 新币IEO项目A | TOKENA | 0.1 USDT | 进行中 |
| 2 | 新币IEO项目B | TOKENB | 0.5 USDT | 进行中 |

### 8. 用户资产（6条记录）

**用户 user001 (ID: 2)**
- USDT: 10,000.00
- BTC: 1.00
- ETH: 10.00

**用户 user002 (ID: 3)**
- USDT: 50,000.00
- BTC: 0.50
- ETH: 5.00

---

## 🔧 数据库配置

### application.yml 配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/crypto_platform?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true
    username: root
    password: ${DB_PASSWORD:19880806}
```

### 环境变量（可选）

如果不想在配置文件中暴露密码，可以设置环境变量：

```bash
export DB_PASSWORD=19880806
```

---

## 🚀 启动后端服务

### 1. 确保 MySQL 服务运行

```bash
# macOS
mysql.server status

# 如果未运行，启动 MySQL
mysql.server start
```

### 2. 启动 Spring Boot 应用

```bash
cd crypto-backend
mvn spring-boot:run
```

或者在 IDE 中运行 `CryptoPlatformApplication.java`

### 3. 访问 API 文档

启动成功后，访问：
- **Swagger UI**: http://localhost:8080/api/doc.html
- **Druid 监控**: http://localhost:8080/api/druid/
  - 用户名: admin
  - 密码: admin

---

## 🔐 登录测试

### 管理员登录

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

### 普通用户登录

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "user001",
    "password": "admin123"
  }'
```

---

## ✅ 验证清单

- [x] 数据库创建成功
- [x] 23张表全部创建
- [x] 用户数据初始化（3个用户）
- [x] 币种配置初始化（9个币种）
- [x] 交易对配置初始化（9个交易对）
- [x] 理财产品初始化（5个产品）
- [x] 挖矿配置初始化（3个配置）
- [x] 借贷配置初始化（3个配置）
- [x] IEO项目初始化（2个项目）
- [x] 用户资产初始化（6条记录）
- [x] 数据库密码配置更新

---

## 📝 注意事项

1. **密码安全**: 生产环境请修改默认密码
2. **数据库备份**: 定期备份数据库
3. **索引优化**: 根据实际查询需求优化索引
4. **监控告警**: 配置数据库监控和告警

---

## 🎉 完成

数据库已成功初始化，所有表结构和初始数据已就绪！

可以开始启动后端服务并进行测试了。

