# 菜单显示问题修复指南

## 问题描述
左侧菜单显示的是旧的静态菜单（数据概览、用户管理、资产管理、系统配置），而不是从后端动态加载的新菜单。

## 原因分析
1. 登录后跳转到 `index.html`（使用静态菜单）
2. 应该跳转到 `dashboard.html`（使用动态菜单）
3. 数据库中可能缺少菜单数据

## 解决方案

### 步骤 1: 初始化菜单数据

在数据库中执行菜单初始化脚本：

```bash
# 方式1: 使用 MySQL 命令行
mysql -u root -p crypto_platform < crypto-backend/src/main/resources/sql/init_complete_menu.sql

# 方式2: 在 MySQL Workbench 或其他工具中执行
# 打开文件: crypto-backend/src/main/resources/sql/init_complete_menu.sql
# 执行整个脚本
```

### 步骤 2: 访问正确的页面

有两种方式：

#### 方式 1: 重新登录（推荐）
1. 退出当前登录
2. 重新登录
3. 系统会自动跳转到 `dashboard.html`

#### 方式 2: 直接访问
直接在浏览器中访问：
```
http://localhost:8080/admin/dashboard.html
```

### 步骤 3: 验证菜单

登录后，左侧菜单应该显示：

```
📊 数据概览

👥 玩家管理
  ├─ 玩家用户
  ├─ 玩家资产
  └─ 实名认证

💰 充提管理
  ├─ 充值记录
  └─ 提现记录

📈 交易管理
  ├─ 现货交易
  ├─ 秒合约
  └─ 周期合约

💵 金融产品
  ├─ 理财产品
  ├─ 挖矿配置
  ├─ 借贷配置
  └─ IEO项目

⚙️ 系统管理
  ├─ 币种配置
  ├─ 交易对配置
  └─ 系统配置
```

## 检查清单

### ✅ 数据库检查

```sql
-- 检查菜单数据是否存在
USE crypto_platform;

SELECT 
    p1.id AS '菜单ID',
    p1.permission_name AS '菜单名称',
    p1.permission_key AS '权限标识',
    p1.icon AS '图标',
    p2.permission_name AS '父菜单'
FROM sys_permission p1
LEFT JOIN sys_permission p2 ON p1.parent_id = p2.id
WHERE p1.deleted = 0
ORDER BY p1.parent_id, p1.sort;
```

应该返回 21 条记录（6个一级菜单 + 15个二级菜单）

### ✅ 后端检查

1. 确认后端应用已启动
2. 访问 Swagger UI: `http://localhost:8080/doc.html`
3. 找到 "菜单管理" 接口
4. 测试 `GET /system/menu/tree` 接口
5. 应该返回菜单树结构

### ✅ 前端检查

1. 打开浏览器开发者工具（F12）
2. 切换到 Console 标签
3. 应该看到日志：`菜单加载成功: [...]`
4. 切换到 Network 标签
5. 应该看到请求：`/system/menu/tree` 状态码 200

## 常见问题

### Q1: 菜单还是不显示
**A:** 清除浏览器缓存
- Chrome: `Ctrl+Shift+Delete`
- Firefox: `Ctrl+Shift+Delete`
- Safari: `Cmd+Option+E`

### Q2: API 请求失败
**A:** 检查后端是否启动
```bash
# 检查端口是否被占用
lsof -i :8080

# 或者
netstat -an | grep 8080
```

### Q3: 数据库连接失败
**A:** 检查数据库配置
```yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/crypto_platform
    username: root
    password: your_password
```

## 文件清单

### 已修改的文件
- `crypto-backend/src/main/resources/static/admin/login.html` - 修改登录跳转
- `crypto-backend/src/main/resources/static/admin/dashboard.html` - 动态菜单页面

### 新增的文件
- `crypto-backend/src/main/resources/sql/init_complete_menu.sql` - 完整菜单初始化脚本
- `MENU_FIX_GUIDE.md` - 本文档

## 技术说明

### 菜单加载流程

1. 用户登录成功
2. 跳转到 `dashboard.html`
3. 页面加载时调用 `loadMenu()` 函数
4. 发送请求到 `/system/menu/tree`
5. 后端从数据库查询菜单数据
6. 返回菜单树结构
7. 前端动态渲染菜单

### 菜单数据结构

```json
[
  {
    "id": 1,
    "name": "数据概览",
    "key": "dashboard",
    "icon": "DataAnalysis",
    "path": "/dashboard",
    "children": []
  },
  {
    "id": 2,
    "name": "玩家管理",
    "key": "user",
    "icon": "User",
    "path": "/user",
    "children": [
      {
        "id": 21,
        "name": "玩家用户",
        "key": "user-list",
        "icon": "User",
        "path": "/user/list"
      }
    ]
  }
]
```

## 下一步

如果按照以上步骤操作后菜单仍然不显示，请：

1. 截图浏览器控制台的错误信息
2. 截图 Network 标签的请求详情
3. 提供后端日志
4. 联系开发人员进行排查

