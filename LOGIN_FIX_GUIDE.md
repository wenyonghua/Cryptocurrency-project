# 🔐 登录问题修复指南

## 📋 问题总结

登录时出现 "Bad credentials" 错误，经过排查发现以下问题：

### ✅ 已修复的问题

1. **权限配置大小写不一致**
   - 问题：`UserDetailsServiceImpl` 设置的权限是小写 `"admin"`，但控制器使用的是大写 `"ADMIN"`
   - 修复：统一使用大写 `"ADMIN"` 和 `"USER"`
   - 文件：`crypto-backend/src/main/java/com/crypto/platform/security/UserDetailsServiceImpl.java`

2. **数据库表结构缺失字段**
   - 问题：`sys_user` 表缺少 `real_name` 和 `id_card` 字段
   - 问题：字段名不一致 (`last_login_time` vs `login_time`, `last_login_ip` vs `login_ip`)
   - 修复：执行了以下 SQL：
     ```sql
     ALTER TABLE sys_user ADD COLUMN real_name VARCHAR(50) COMMENT '真实姓名' AFTER user_type;
     ALTER TABLE sys_user ADD COLUMN id_card VARCHAR(20) COMMENT '身份证号' AFTER real_name;
     ALTER TABLE sys_user CHANGE COLUMN last_login_time login_time DATETIME COMMENT '最后登录时间';
     ALTER TABLE sys_user CHANGE COLUMN last_login_ip login_ip VARCHAR(50) COMMENT '最后登录IP';
     ```

3. **密码哈希更新**
   - 更新了 admin 用户的密码
   - 新密码：`admin123`
   - 密码哈希：`$2a$10$0Q6CHBB/YR9fRUY81u7Sf.Wnzd3FlwchMAp5bRRjaEF/6fYdtPzOm`

---

## 🚀 如何运行项目

### 方法 1：使用 IntelliJ IDEA（推荐）

#### 1. 安装 Lombok 插件
- 打开 IntelliJ IDEA
- File → Settings → Plugins
- 搜索 "Lombok"
- 点击 Install 并重启 IDE

#### 2. 启用注解处理
- File → Settings
- Build, Execution, Deployment → Compiler → Annotation Processors
- 勾选 "Enable annotation processing"
- 点击 Apply 和 OK

#### 3. 重新导入项目
- 右键点击 `crypto-backend/pom.xml`
- Maven → Reload Project
- 等待依赖下载完成

#### 4. 运行项目
- 找到 `crypto-backend/src/main/java/com/crypto/platform/CryptoPlatformApplication.java`
- 右键点击文件
- 选择 "Run 'CryptoPlatformApplication'"

#### 5. 访问登录页面
- 打开浏览器访问：`http://localhost:8080/`
- 自动跳转到：`http://localhost:8080/admin/login.html`

---

### 方法 2：使用 Maven 命令行

**注意**：由于 Lombok 配置问题，Maven 命令行编译可能会失败。如果失败，请使用方法 1（IDE 运行）。

```bash
cd crypto-backend
mvn clean spring-boot:run
```

---

## 🔑 登录凭证

| 字段 | 值 |
|------|-----|
| **用户名** | `admin` |
| **密码** | `admin123` |
| **用户类型** | 管理员 (user_type = 1) |
| **状态** | 正常 (status = 1) |

---

## 📊 数据库配置

确保 MySQL 数据库正在运行，并且配置正确：

```yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/crypto_platform
    username: root
    password: 19880806
```

---

## 🐛 常见问题

### 1. 编译错误：找不到符号 getXxx() 或 setXxx()

**原因**：Lombok 注解处理器未正确配置

**解决方案**：
- 使用 IntelliJ IDEA 运行（推荐）
- 确保已安装 Lombok 插件
- 确保已启用注解处理

### 2. 登录失败：Bad credentials

**原因**：密码不正确或数据库配置错误

**解决方案**：
- 确认使用正确的密码：`admin123`
- 检查数据库中的密码哈希是否正确
- 执行以下 SQL 验证：
  ```sql
  SELECT username, password, user_type, status 
  FROM sys_user 
  WHERE username = 'admin';
  ```

### 3. 数据库连接失败

**原因**：MySQL 未启动或配置错误

**解决方案**：
- 启动 MySQL 服务
- 检查 `application.yml` 中的数据库配置
- 确认数据库 `crypto_platform` 已创建

---

## 📝 相关文件

- **权限配置**：`crypto-backend/src/main/java/com/crypto/platform/security/UserDetailsServiceImpl.java`
- **登录控制器**：`crypto-backend/src/main/java/com/crypto/platform/api/controller/AuthController.java`
- **数据库更新脚本**：`crypto-backend/src/main/resources/sql/update_sys_user_table.sql`
- **密码生成工具**：`crypto-backend/src/test/java/com/crypto/platform/PasswordGenerator.java`

---

## ✅ 验证步骤

1. ✅ 数据库表结构已更新
2. ✅ 权限配置已修复
3. ✅ 密码已更新
4. ⏳ 使用 IDE 运行项目
5. ⏳ 访问登录页面并测试登录

---

## 🎯 下一步

1. 使用 IntelliJ IDEA 运行项目
2. 访问 `http://localhost:8080/`
3. 使用 `admin` / `admin123` 登录
4. 如果登录成功，你应该能看到管理后台首页

如果还有问题，请检查后端控制台的错误日志！

