# 🔧 IntelliJ IDEA 编译问题完整解决方案

**问题**: 项目无法编译，Lombok 注解不生效  
**更新时间**: 2025-12-31 01:58

---

## ✅ 完整解决步骤（请按顺序执行）

### 步骤 1: 确认 Lombok 插件已安装

1. **打开插件设置**
   - Mac: `IntelliJ IDEA` → `Preferences` (⌘,)
   - Windows: `File` → `Settings` (Ctrl+Alt+S)

2. **导航到插件**
   ```
   Plugins → Installed
   ```

3. **搜索 Lombok**
   - 在搜索框输入 "Lombok"
   - 确保看到 "Lombok" 插件（作者：Michail Plushnikov）
   - 状态应该是 ✅ **Enabled**

4. **如果没有安装**
   - 切换到 `Marketplace` 标签
   - 搜索 "Lombok"
   - 点击 `Install`
   - 重启 IDEA

---

### 步骤 2: 启用注解处理（关键步骤）

1. **打开设置**
   - Mac: `IntelliJ IDEA` → `Preferences` (⌘,)
   - Windows: `File` → `Settings` (Ctrl+Alt+S)

2. **导航到注解处理器**
   ```
   Build, Execution, Deployment 
     → Compiler 
       → Annotation Processors
   ```

3. **配置注解处理**
   - ✅ 勾选 `Enable annotation processing`
   - ✅ 选择 `Obtain processors from project classpath`
   - `Store generated sources relative to:` 选择 `Module content root`
   - `Production sources directory:` 保持默认 `target/generated-sources/annotations`

4. **点击 Apply → OK**

---

### 步骤 3: 配置 Maven 设置

1. **打开 Maven 设置**
   ```
   Preferences → Build, Execution, Deployment → Build Tools → Maven
   ```

2. **配置 Maven**
   - `Maven home path`: 使用 IDEA 自带的（Bundled Maven 3）
   - ✅ 勾选 `Always update snapshots`
   - ✅ 勾选 `Use plugin registry`

3. **配置 Maven Importing**
   ```
   Preferences → Build, Execution, Deployment → Build Tools → Maven → Importing
   ```
   - ✅ 勾选 `Import Maven projects automatically`
   - ✅ 勾选 `Automatically download: Sources`
   - ✅ 勾选 `Automatically download: Documentation`

4. **点击 Apply → OK**

---

### 步骤 4: 重新加载 Maven 项目

1. **打开 Maven 工具窗口**
   - 点击右侧的 `Maven` 标签
   - 或 `View` → `Tool Windows` → `Maven`

2. **刷新 Maven 项目**
   - 点击工具栏的 🔄 **Reload All Maven Projects** 按钮
   - 或右键 `crypto-platform` → `Maven` → `Reload project`

3. **等待依赖下载完成**
   - 查看右下角进度条
   - 等待显示 "All files are up-to-date"

---

### 步骤 5: 清除缓存并重启（重要）

1. **清除缓存**
   ```
   File → Invalidate Caches...
   ```

2. **在弹出的对话框中**
   - ✅ 勾选 `Clear file system cache and Local History`
   - ✅ 勾选 `Clear downloaded shared indexes`
   - ✅ 勾选 `Clear VCS Log caches and indexes`
   - ✅ 勾选 `Clear workspace model cache`

3. **点击 `Invalidate and Restart`**

4. **等待 IDEA 重启**

---

### 步骤 6: 重新构建项目

**IDEA 重启后：**

1. **等待索引完成**
   - 右下角会显示 "Indexing..."
   - 必须等待索引完成（可能需要几分钟）

2. **清理项目**
   ```
   Build → Clean Project
   ```

3. **重新构建项目**
   ```
   Build → Rebuild Project
   ```

4. **查看构建结果**
   - 底部的 `Build` 窗口应该显示 `BUILD SUCCESSFUL`
   - 如果有错误，查看具体错误信息

---

### 步骤 7: 运行应用

1. **找到主类**
   ```
   crypto-backend/src/main/java/com/crypto/platform/CryptoPlatformApplication.java
   ```

2. **运行应用**
   - 右键点击 `CryptoPlatformApplication.java`
   - 选择 `Run 'CryptoPlatformApplication'`

3. **查看控制台**
   - 应该看到 Spring Boot 启动日志
   - 等待看到 `Started CryptoPlatformApplication in X.XXX seconds`

---

## 🔍 故障排除

### 问题 1: 仍然找不到 getter/setter

**解决方案**:
1. 确认 Lombok 插件已启用
2. 确认注解处理已启用
3. 重新执行"步骤 5: 清除缓存并重启"

### 问题 2: Maven 依赖下载失败

**解决方案**:
```bash
# 在 IDEA Terminal 中运行
cd crypto-backend
rm -rf ~/.m2/repository/com/baomidou
mvn clean install -U -DskipTests
```

然后在 IDEA 中刷新 Maven 项目。

### 问题 3: PaginationInnerInterceptor 找不到

**解决方案**:
1. 确认 MyBatis-Plus 版本是 3.5.9
2. 删除旧版本缓存：
   ```bash
   rm -rf ~/.m2/repository/com/baomidou/mybatis-plus-*/3.5.7
   ```
3. 在 IDEA 中刷新 Maven 项目
4. 清除缓存并重启

### 问题 4: 编译成功但运行失败

**错误**: `Invalid value type for attribute 'factoryBeanObjectType'`

**解决方案**:
- 这是 MyBatis-Plus 版本问题
- 确认 `pom.xml` 中 `mybatis-plus.version` 是 `3.5.9`
- 重新加载 Maven 项目

---

## ✅ 成功标志

### 编译成功

`Build` 窗口显示：
```
BUILD SUCCESSFUL in Xs
```

### 运行成功

控制台显示：
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

### 访问测试

- **API 文档**: http://localhost:8080/api/doc.html
- **Druid 监控**: http://localhost:8080/api/druid/

---

## 📞 仍然有问题？

如果完成所有步骤后仍然无法编译，请提供：

1. **IDEA 版本**
   - `Help` → `About` 查看版本号

2. **Build 窗口的完整错误信息**
   - 截图或复制完整错误

3. **Lombok 插件状态**
   - `Preferences` → `Plugins` → 截图

4. **注解处理配置**
   - `Preferences` → `Annotation Processors` → 截图

我会根据具体情况进一步帮助你！

