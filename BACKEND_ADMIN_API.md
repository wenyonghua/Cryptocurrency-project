# 🎯 后台管理系统 API 完整文档

## 📋 目录

1. [首页数据统计](#首页数据统计)
2. [平台数据模块](#平台数据模块)
3. [玩家管理模块](#玩家管理模块)
4. [测试方法](#测试方法)

---

## 📊 首页数据统计

### 1. 获取首页统计数据

**接口**: `GET /api/admin/dashboard/statistics`

**功能**: 获取平台总收入、玩家数量、充值/提现金额及趋势图表数据

**响应数据**:
- `totalRevenue`: 平台总收入
- `playerCount`: 玩家数量
- `totalDeposit`: 总充值金额
- `totalWithdraw`: 提现金额
- `depositTrend`: 充值趋势（最近7天）
- `withdrawTrend`: 提现趋势（最近7天）

---

## 📈 平台数据模块

### 2. 获取平台概要信息

**接口**: `GET /api/admin/platform/overview`

**功能**: 获取平台总体运营数据概览

**响应数据**:
- `totalUsers`: 总用户数
- `todayNewUsers`: 今日新增用户
- `todayActiveUsers`: 今日活跃用户
- `totalDeposit`: 总充值金额
- `todayDeposit`: 今日充值金额
- `totalWithdraw`: 总提现金额
- `todayWithdraw`: 今日提现金额
- `totalTradeVolume`: 总交易量
- `todayTradeVolume`: 今日交易量
- `totalProfit`: 总利润
- `todayProfit`: 今日利润
- `vipUserCount`: VIP用户数
- `verifiedUserCount`: 已认证用户数

### 3. 获取每日数据列表

**接口**: `GET /api/admin/platform/daily-data`

**功能**: 分页查询每日运营数据

**请求参数**:
- `current`: 当前页（默认1）
- `size`: 每页大小（默认10）
- `startDate`: 开始日期（yyyy-MM-dd）
- `endDate`: 结束日期（yyyy-MM-dd）

**响应数据**:
- `statDate`: 统计日期
- `newUsers`: 新增用户数
- `activeUsers`: 活跃用户数
- `depositAmount`: 充值金额
- `withdrawAmount`: 提现金额
- `tradeVolume`: 交易量
- `profit`: 利润

### 4. 获取玩家数据列表

**接口**: `GET /api/admin/platform/player-data`

**功能**: 分页查询玩家数据，支持搜索和筛选

**请求参数**:
- `current`: 当前页（默认1）
- `size`: 每页大小（默认10）
- `keyword`: 搜索关键词（用户名、邮箱、手机号、UID）
- `vipLevel`: VIP等级筛选
- `status`: 状态筛选（0-禁用，1-正常）

**响应数据**:
- `id`: 用户ID
- `username`: 用户名
- `nickname`: 昵称
- `uid`: 用户UID
- `email`: 邮箱
- `phone`: 手机号
- `vipLevel`: VIP等级
- `kycLevel`: KYC等级
- `status`: 状态
- `totalAsset`: 总资产
- `totalDeposit`: 累计充值
- `totalWithdraw`: 累计提现
- `totalTrade`: 累计交易量
- `inviteCount`: 邀请人数
- `lastLoginTime`: 最后登录时间
- `lastLoginIp`: 最后登录IP
- `createTime`: 注册时间

---

## 👥 玩家管理模块

### 5. 获取玩家详情

**接口**: `GET /api/admin/player/{userId}`

**功能**: 根据用户ID获取玩家详细信息，包括资产和统计数据

**路径参数**:
- `userId`: 用户ID

**响应数据**:
- 基本信息：用户名、昵称、邮箱、手机号等
- 资产列表：各币种的可用余额、冻结余额、总余额
- 统计数据：累计充值、累计提现、累计交易量、邀请人数

### 6. 更新玩家状态

**接口**: `PUT /api/admin/player/{userId}/status`

**功能**: 禁用或启用玩家账号

**路径参数**:
- `userId`: 用户ID

**请求参数**:
- `status`: 状态（0-禁用，1-正常）

### 7. 设置玩家VIP等级

**接口**: `PUT /api/admin/player/{userId}/vip-level`

**功能**: 修改玩家的VIP等级

**路径参数**:
- `userId`: 用户ID

**请求参数**:
- `vipLevel`: VIP等级

---

## 🧪 测试方法

### 方法 1: 使用 Swagger UI

1. 启动应用
2. 访问: http://localhost:8080/api/doc.html
3. 找到对应的接口分组
4. 点击接口并测试

### 方法 2: 使用 curl

```bash
# 首页统计
curl http://localhost:8080/api/admin/dashboard/statistics

# 平台概要
curl http://localhost:8080/api/admin/platform/overview

# 每日数据
curl "http://localhost:8080/api/admin/platform/daily-data?current=1&size=10"

# 玩家数据
curl "http://localhost:8080/api/admin/platform/player-data?current=1&size=10"

# 玩家详情
curl http://localhost:8080/api/admin/player/1

# 更新玩家状态
curl -X PUT "http://localhost:8080/api/admin/player/1/status?status=0"

# 设置VIP等级
curl -X PUT "http://localhost:8080/api/admin/player/1/vip-level?vipLevel=2"
```

---

## ✅ 已完成功能

- ✅ 首页数据统计
- ✅ 平台概要信息
- ✅ 每日数据查询
- ✅ 玩家数据查询（分页、搜索、筛选）
- ✅ 玩家详情查看
- ✅ 玩家状态管理
- ✅ 玩家VIP等级设置

---

## 📞 技术支持

- Swagger 文档: http://localhost:8080/api/doc.html
- Druid 监控: http://localhost:8080/api/druid/

