# 🚀 Cryptocurrency Trading Platform

一个功能完整的虚拟货币交易平台，包含后端 API 服务、移动端 H5 应用和管理后台。

## 📁 项目结构

```
Cryptocurrency-project/
├── crypto-backend/          # 后端服务 (Spring Boot)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/crypto/platform/
│   │   │   │       ├── admin/          # 用户管理模块
│   │   │   │       ├── api/            # API 接口
│   │   │   │       ├── asset/          # 资产管理模块
│   │   │   │       ├── common/         # 公共模块
│   │   │   │       ├── config/         # 配置类
│   │   │   │       ├── security/       # 安全模块
│   │   │   │       └── system/         # 系统管理模块
│   │   │   └── resources/
│   │   │       ├── static/admin/       # 管理后台界面
│   │   │       ├── sql/                # 数据库脚本
│   │   │       └── application.yml     # 配置文件
│   │   └── pom.xml
│   └── README.md
│
└── crypto-replica/          # 移动端 H5 应用 (Vue 3 + Vant)
    ├── src/
    │   ├── assets/
    │   ├── components/
    │   ├── i18n/
    │   ├── router/
    │   ├── stores/
    │   └── views/
    ├── package.json
    └── README.md
```

## ✨ 功能特性

### 后端服务 (crypto-backend)

- ✅ **用户认证系统**
  - JWT Token 认证
  - 用户注册/登录
  - 权限管理（普通用户/管理员）

- ✅ **用户管理**
  - 用户列表查询
  - 用户信息管理
  - 用户状态控制
  - KYC 认证等级

- ✅ **资产管理**
  - 多币种资产管理
  - 资产冻结/解冻
  - 资产变动记录
  - 余额查询

- ✅ **系统配置**
  - 系统参数配置
  - 配置项管理

- ✅ **管理后台界面**
  - 数据概览仪表板
  - 用户管理界面
  - 资产管理界面
  - 系统配置界面

### 移动端应用 (crypto-replica)

- ✅ **行情展示**
  - 实时价格展示
  - K线图表
  - 涨跌幅统计

- ✅ **交易功能**
  - 现货交易
  - 订单簿
  - 交易历史

- ✅ **资产管理**
  - 资产总览
  - 充值/提现
  - 资产记录

- ✅ **个人中心**
  - 个人信息
  - 安全设置
  - 多语言支持

## 🛠️ 技术栈

### 后端技术

- **框架**: Spring Boot 3.2.1
- **安全**: Spring Security + JWT
- **数据库**: MySQL 8.0
- **ORM**: MyBatis-Plus 3.5.5
- **连接池**: Druid
- **API 文档**: Knife4j (Swagger)
- **工具类**: Hutool

### 前端技术

- **框架**: Vue 3 + TypeScript
- **UI 组件**: Vant 4
- **状态管理**: Pinia
- **路由**: Vue Router
- **图表**: Lightweight Charts
- **国际化**: Vue I18n
- **构建工具**: Vite

## 🚀 快速开始

### 后端服务

1. **环境要求**
   - JDK 17+
   - Maven 3.6+
   - MySQL 8.0+

2. **数据库配置**
   ```bash
   # 创建数据库
   mysql -u root -p
   CREATE DATABASE crypto_platform CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   
   # 导入数据库脚本
   mysql -u root -p crypto_platform < crypto-backend/src/main/resources/sql/schema.sql
   ```

3. **修改配置**
   编辑 `crypto-backend/src/main/resources/application-dev.yml`
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/crypto_platform
       username: root
       password: your_password
   ```

4. **启动服务**
   ```bash
   cd crypto-backend
   mvn clean install
   mvn spring-boot:run
   ```

5. **访问服务**
   - API 文档: http://localhost:8080/api/doc.html
   - 管理后台: http://localhost:8080/api/admin/login.html
   - Druid 监控: http://localhost:8080/api/druid

### 移动端应用

1. **安装依赖**
   ```bash
   cd crypto-replica
   npm install
   ```

2. **启动开发服务器**
   ```bash
   npm run dev
   ```

3. **访问应用**
   - 开发地址: http://localhost:5173

4. **构建生产版本**
   ```bash
   npm run build
   ```

## 📝 API 接口文档

### 认证接口

- `POST /api/auth/login` - 用户登录
- `POST /api/auth/register` - 用户注册
- `GET /api/auth/userinfo` - 获取当前用户信息

### 用户管理

- `GET /api/admin/user/list` - 用户列表
- `GET /api/admin/user/{id}` - 用户详情
- `PUT /api/admin/user` - 更新用户
- `DELETE /api/admin/user/{id}` - 删除用户
- `PUT /api/admin/user/{id}/status` - 修改用户状态

### 资产管理

- `GET /api/asset/list` - 获取资产列表
- `GET /api/asset/{currency}` - 获取指定币种资产

### 系统配置

- `GET /api/system/config/list` - 配置列表
- `GET /api/system/config/{key}` - 获取配置
- `POST /api/system/config` - 新增配置
- `PUT /api/system/config` - 更新配置
- `DELETE /api/system/config/{id}` - 删除配置

## 🔐 默认账号

管理员账号需要在数据库中手动创建，密码使用 BCrypt 加密。

## 📄 许可证

MIT License

## 👥 贡献者

- [@wenyonghua](https://github.com/wenyonghua)

## 📞 联系方式

如有问题或建议，请提交 Issue 或 Pull Request。

---

⭐ 如果这个项目对你有帮助，请给个 Star！
