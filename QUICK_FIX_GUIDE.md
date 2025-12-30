# 🚀 快速修复指南 - 启动项目

**问题**: `ClassNotFoundException: com.crypto.platform.CryptoPlatformApplication`  
**原因**: 项目未编译成功（Lombok 注解处理问题）

---

## ✅ 解决方案（在 IntelliJ IDEA 中操作）

### 步骤 1: 重新导入 Maven 项目

1. **打开 Maven 工具窗口**
   - 点击右侧的 `Maven` 标签
   - 或者 `View` → `Tool Windows` → `Maven`

2. **刷新 Maven 项目**
   - 点击 Maven 窗口左上角的 🔄 **Reload All Maven Projects** 按钮
   - 等待依赖下载完成（查看右下角进度条）

### 步骤 2: 确认 Lombok 插件已安装

1. **检查插件**
   - Mac: `IntelliJ IDEA` → `Preferences` → `Plugins`
   - Windows: `File` → `Settings` → `Plugins`

2. **搜索 Lombok**
   - 在 `Installed` 标签中搜索 "Lombok"
   - 如果没有，切换到 `Marketplace` 标签安装

3. **重启 IDEA**（如果刚安装）

### 步骤 3: 启用注解处理

1. **打开设置**
   - Mac: `IntelliJ IDEA` → `Preferences`
   - Windows: `File` → `Settings`

2. **导航到注解处理器**
   ```
   Build, Execution, Deployment 
     → Compiler 
       → Annotation Processors
   ```

3. **启用注解处理**
   - ✅ 勾选 `Enable annotation processing`
   - ✅ 选择 `Obtain processors from project classpath`
   - 点击 `Apply` → `OK`

### 步骤 4: 清理并重新构建

1. **清理项目**
   - 点击 `Build` → `Clean Project`
   - 等待完成

2. **重新构建项目**
   - 点击 `Build` → `Rebuild Project`
   - 等待完成（查看右下角进度条）

3. **检查编译结果**
   - 查看底部的 `Build` 窗口
   - 应该显示 `BUILD SUCCESSFUL`
   - 如果有错误，请查看错误信息

### 步骤 5: 运行应用

1. **找到主类**
   - 在项目结构中找到：
     ```
     crypto-backend/src/main/java/com/crypto/platform/CryptoPlatformApplication.java
     ```

2. **运行应用**
   - 右键点击 `CryptoPlatformApplication.java`
   - 选择 `Run 'CryptoPlatformApplication'`

3. **查看控制台**
   - 应该看到 Spring Boot 启动日志
   - 等待看到 `Started CryptoPlatformApplication`

---

## 🔧 如果仍然失败

### 方案 A: 使用 Maven 在 IDEA 中运行

1. **打开 Maven 工具窗口**
2. **展开项目**
   ```
   crypto-platform 
     → Lifecycle
   ```
3. **双击 `clean`**，等待完成
4. **双击 `compile`**，等待完成
5. **展开 `Plugins` → `spring-boot`**
6. **双击 `spring-boot:run`**

### 方案 B: 检查 JDK 配置

1. **打开项目结构**
   - Mac: `File` → `Project Structure` (⌘;)
   - Windows: `File` → `Project Structure` (Ctrl+Alt+Shift+S)

2. **检查 Project SDK**
   - 确保选择了 **JDK 17** 或更高版本
   - 如果没有，点击 `Add SDK` → `Download JDK`

3. **检查 Language Level**
   - 设置为 `17 - Sealed types, always-strict floating-point semantics`

4. **检查 Modules**
   - 选择 `crypto-backend` 模块
   - 确保 `Language level` 也是 `17`

### 方案 C: 删除 IDEA 缓存

1. **关闭项目**
   - `File` → `Close Project`

2. **删除 IDEA 文件**
   ```bash
   cd /Users/yonghuawen/IdeaProjects/虚拟币项目
   rm -rf .idea
   rm -rf crypto-backend/.idea
   rm -rf crypto-backend/target
   ```

3. **重新打开项目**
   - 在 IDEA 中重新打开项目
   - 等待索引完成

---

## ✅ 成功标志

应用成功启动后，控制台应该显示：

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.1)

...
Started CryptoPlatformApplication in 5.234 seconds
```

然后访问：
- **API 文档**: http://localhost:8080/api/doc.html
- **Druid 监控**: http://localhost:8080/api/druid/

---

## 📞 仍然有问题？

如果以上步骤都不行，请提供：
1. IDEA 的 `Build` 窗口的完整错误信息
2. `Run` 窗口的完整错误信息
3. 你的 IDEA 版本号

我会帮你进一步诊断！

