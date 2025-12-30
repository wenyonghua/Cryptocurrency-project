# 🚀 IntelliJ IDEA 运行指南

**项目**: 虚拟货币交易平台  
**更新时间**: 2025-12-31 01:42  

---

## 📋 前置条件

确保以下软件已安装：

- ✅ **IntelliJ IDEA** (推荐 2023.x 或更高版本)
- ✅ **JDK 17** 或更高版本
- ✅ **MySQL 8.0** 或更高版本
- ✅ **Maven 3.6+** (IDEA 自带)

---

## 🔧 步骤 1: 打开项目

1. **启动 IntelliJ IDEA**

2. **打开项目**
   - 点击 `File` → `Open`
   - 导航到项目路径：
     ```
     /Users/yonghuawen/IdeaProjects/虚拟币项目
     ```
   - 选择 `crypto-backend` 文件夹
   - 点击 `Open`

3. **等待 Maven 导入**
   - IDEA 会自动检测 `pom.xml`
   - 等待右下角的进度条完成（可能需要几分钟）

---

## 🔌 步骤 2: 安装 Lombok 插件

### 方法 A: 通过设置安装

1. **打开插件设置**
   - Mac: `IntelliJ IDEA` → `Preferences` → `Plugins`
   - Windows/Linux: `File` → `Settings` → `Plugins`

2. **搜索并安装 Lombok**
   - 点击 `Marketplace` 标签
   - 在搜索框输入 "Lombok"
   - 找到 "Lombok" 插件（作者：Michail Plushnikov）
   - 点击 `Install` 按钮

3. **重启 IDEA**
   - 安装完成后，点击 `Restart IDE`

### 方法 B: 快速安装（如果已提示）

如果 IDEA 检测到 Lombok 注解，会在右上角显示提示：
- 点击 "Install Lombok plugin"
- 等待安装完成
- 重启 IDEA

---

## ⚙️ 步骤 3: 启用注解处理

1. **打开编译器设置**
   - Mac: `IntelliJ IDEA` → `Preferences` → `Build, Execution, Deployment` → `Compiler` → `Annotation Processors`
   - Windows/Linux: `File` → `Settings` → `Build, Execution, Deployment` → `Compiler` → `Annotation Processors`

2. **启用注解处理**
   - ✅ 勾选 `Enable annotation processing`
   - ✅ 选择 `Obtain processors from project classpath`

3. **应用设置**
   - 点击 `Apply`
   - 点击 `OK`

---

## 🗄️ 步骤 4: 配置数据库

### 确保 MySQL 服务运行

```bash
# Mac (使用 Homebrew)
brew services start mysql

# 或者手动启动
mysql.server start
```

### 验证数据库

```bash
mysql -u root -p
# 输入密码: 19880806

# 在 MySQL 中执行
USE crypto_platform;
SHOW TABLES;
# 应该看到 23 张表
```

---

## 🏗️ 步骤 5: 重新构建项目

1. **清理项目**
   - 点击 `Build` → `Clean Project`

2. **重新构建**
   - 点击 `Build` → `Rebuild Project`
   - 等待构建完成（右下角进度条）

3. **检查错误**
   - 如果没有错误，说明 Lombok 已正确工作！
   - 如果仍有错误，请检查 Lombok 插件是否已安装并重启 IDEA

---

## ▶️ 步骤 6: 运行应用

### 方法 A: 通过主类运行

1. **找到主类**
   - 在项目结构中导航到：
     ```
     crypto-backend/src/main/java/com/crypto/platform/CryptoPlatformApplication.java
     ```

2. **运行应用**
   - 右键点击 `CryptoPlatformApplication.java`
   - 选择 `Run 'CryptoPlatformApplication'`

3. **等待启动**
   - 查看控制台输出
   - 看到以下信息表示启动成功：
     ```
     Started CryptoPlatformApplication in X.XXX seconds
     ```

### 方法 B: 通过 Maven 运行

1. **打开 Maven 工具窗口**
   - 点击右侧的 `Maven` 标签

2. **运行 Spring Boot**
   - 展开 `crypto-platform` → `Plugins` → `spring-boot`
   - 双击 `spring-boot:run`

---

## ✅ 步骤 7: 验证应用

### 访问 Swagger 文档

打开浏览器，访问：
```
http://localhost:8080/api/doc.html
```

应该看到 Knife4j 的 API 文档界面。

### 访问 Druid 监控

```
http://localhost:8080/api/druid/
```

### 测试登录接口

使用 Postman 或 curl 测试：

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

---

## 🐛 常见问题

### 问题 1: Lombok 注解不生效

**症状**: 代码中 `@Data` 注解的类报错，找不到 getter/setter

**解决方案**:
1. 确认 Lombok 插件已安装
2. 确认注解处理已启用
3. 重启 IDEA
4. 重新构建项目

### 问题 2: 端口 8080 已被占用

**症状**: 启动时报错 `Port 8080 is already in use`

**解决方案**:
```bash
# 查找占用端口的进程
lsof -i :8080

# 杀死进程
kill -9 <PID>
```

或者修改 `application.yml` 中的端口：
```yaml
server:
  port: 8081
```

### 问题 3: 数据库连接失败

**症状**: 启动时报错 `Communications link failure`

**解决方案**:
1. 确认 MySQL 服务已启动
2. 检查 `application.yml` 中的数据库配置
3. 验证用户名和密码是否正确

---

## 📊 成功标志

应用成功启动后，你应该看到：

```
  ____                  _        
 / ___|_ __ _   _ _ __ | |_ ___  
| |   | '__| | | | '_ \| __/ _ \ 
| |___| |  | |_| | |_) | || (_) |
 \____|_|   \__, | .__/ \__\___/ 
            |___/|_|              

Started CryptoPlatformApplication in 5.234 seconds (JVM running for 6.123)
```

---

## 🎯 下一步

1. ✅ 测试所有 API 接口
2. ✅ 打开前端管理页面
3. ✅ 进行功能测试
4. ✅ 修复发现的问题

---

## 📞 需要帮助？

如果遇到问题，请检查：
- `CURRENT_STATUS.md` - 当前状态报告
- `DATABASE_SETUP_COMPLETE.md` - 数据库配置
- `BACKEND_API_COMPLETE.md` - API 文档

祝你好运！🚀

