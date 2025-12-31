# 🔧 IntelliJ IDEA Lombok 配置指南

## 问题描述

编译错误：
```
java: 找不到符号
  符号:   类 JwtAuthenticationFilter
  位置: 程序包 com.crypto.platform.security
```

**原因**：Lombok 注解处理器未启用，导致 `@RequiredArgsConstructor`、`@Data` 等注解无法生成代码。

---

## ✅ 解决步骤

### 步骤 1：安装 Lombok 插件

1. 打开 IntelliJ IDEA
2. 点击菜单：**File** → **Settings** (Windows/Linux) 或 **IntelliJ IDEA** → **Preferences** (macOS)
3. 在左侧菜单中选择：**Plugins**
4. 在搜索框中输入：`Lombok`
5. 找到 **Lombok** 插件（作者：Michail Plushnikov）
6. 点击 **Install** 按钮
7. 安装完成后，点击 **Restart IDE** 重启 IDEA

### 步骤 2：启用注解处理

1. 打开 IntelliJ IDEA
2. 点击菜单：**File** → **Settings** (Windows/Linux) 或 **IntelliJ IDEA** → **Preferences** (macOS)
3. 在左侧菜单中导航到：
   ```
   Build, Execution, Deployment
     → Compiler
       → Annotation Processors
   ```
4. 勾选以下选项：
   - ✅ **Enable annotation processing**
   - ✅ **Obtain processors from project classpath**
5. 点击 **Apply** 和 **OK**

### 步骤 3：重新导入 Maven 项目

1. 在 IDEA 中找到 `crypto-backend/pom.xml` 文件
2. 右键点击 `pom.xml`
3. 选择：**Maven** → **Reload Project**
4. 等待 Maven 下载依赖（可能需要几分钟）

### 步骤 4：清理并重新构建项目

1. 点击菜单：**Build** → **Rebuild Project**
2. 等待构建完成
3. 检查 **Build** 窗口是否有错误

### 步骤 5：验证 Lombok 是否工作

1. 打开任意一个使用了 `@Data` 或 `@RequiredArgsConstructor` 的类
2. 例如：`crypto-backend/src/main/java/com/crypto/platform/admin/entity/SysUser.java`
3. 在类中输入 `user.get` 并按 `Ctrl + Space`（代码补全）
4. 如果能看到 `getId()`, `getUsername()` 等方法，说明 Lombok 已正常工作

---

## 🚀 运行项目

配置完成后，运行项目：

1. 找到 `crypto-backend/src/main/java/com/crypto/platform/CryptoPlatformApplication.java`
2. 右键点击文件
3. 选择：**Run 'CryptoPlatformApplication'**
4. 等待项目启动
5. 访问：`http://localhost:8080/`

---

## 🐛 常见问题

### 问题 1：Lombok 插件已安装，但仍然报错

**解决方案**：
1. 确认注解处理已启用（步骤 2）
2. 重启 IntelliJ IDEA
3. 清理项目：**Build** → **Clean Project**
4. 重新构建：**Build** → **Rebuild Project**

### 问题 2：Maven 依赖下载失败

**解决方案**：
1. 检查网络连接
2. 配置 Maven 镜像（阿里云镜像）
3. 删除 `~/.m2/repository` 中的损坏依赖
4. 重新导入项目

### 问题 3：编译时出现 "java.lang.ExceptionInInitializerError"

**解决方案**：
1. 这是 Lombok 与 Java 版本兼容性问题
2. 确保使用 Java 17
3. 确保 Lombok 版本是 1.18.34（已在 pom.xml 中配置）
4. 使用 IDE 运行，不要使用 `mvn compile`

---

## 📝 验证清单

完成以下检查：

- [ ] Lombok 插件已安装
- [ ] 注解处理已启用
- [ ] Maven 项目已重新加载
- [ ] 项目已重新构建
- [ ] 没有编译错误
- [ ] 可以看到 Lombok 生成的方法（代码补全）
- [ ] 项目可以成功运行

---

## 🎯 下一步

配置完成后：

1. ✅ 运行项目
2. ✅ 访问 `http://localhost:8080/`
3. ✅ 使用 `admin` / `admin123` 登录
4. ✅ 测试管理后台功能

---

## 💡 提示

- **不要使用 Maven 命令行编译**（`mvn compile`），因为可能会遇到 Lombok 配置问题
- **始终使用 IntelliJ IDEA 运行项目**
- 如果修改了 `pom.xml`，记得重新加载 Maven 项目

---

## 📞 需要帮助？

如果按照以上步骤操作后仍然有问题，请提供：
1. IntelliJ IDEA 版本
2. Java 版本（`java -version`）
3. 完整的错误信息
4. Build 窗口的输出

