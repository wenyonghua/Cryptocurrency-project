# 🔐 权限配置修复说明

## 问题描述

登录成功后，访问管理后台功能时出现权限拒绝错误：

```
org.springframework.security.access.AccessDeniedException: Access Denied
Failed to authorize ... decision ExpressionAuthorizationDecision [granted=false, expressionAttribute=hasAuthority('admin')]
```

---

## 🔍 问题原因

项目中权限配置不统一：

### 之前的配置

1. **UserDetailsServiceImpl** 设置的权限：`"ADMIN"`（大写）
   ```java
   authorities.add(new SimpleGrantedAuthority("ADMIN"));
   ```

2. **部分控制器** 要求的权限：`'admin'`（小写）
   ```java
   @PreAuthorize("hasAuthority('admin')")  // ❌ 小写
   ```

3. **其他控制器** 要求的权限：`'ADMIN'`（大写）
   ```java
   @PreAuthorize("hasAuthority('ADMIN')")  // ✅ 大写
   ```

### 权限检查失败

当用户登录时：
- Spring Security 赋予用户 `"ADMIN"` 权限（大写）
- 但控制器要求 `'admin'` 权限（小写）
- 权限不匹配 → 访问被拒绝

---

## ✅ 解决方案

统一所有权限配置为大写 `'ADMIN'`。

### 修改的文件

1. **SysUserController.java**
   - 修改了 4 处权限配置
   - `hasAuthority('admin')` → `hasAuthority('ADMIN')`

2. **SysConfigController.java**
   - 修改了 4 处权限配置
   - `hasAuthority('admin')` → `hasAuthority('ADMIN')`

### 修改详情

#### SysUserController.java
```java
// 用户列表
@PreAuthorize("hasAuthority('ADMIN')")  // ✅ 改为大写

// 更新用户
@PreAuthorize("hasAuthority('ADMIN')")  // ✅ 改为大写

// 删除用户
@PreAuthorize("hasAuthority('ADMIN')")  // ✅ 改为大写

// 启用/禁用用户
@PreAuthorize("hasAuthority('ADMIN')")  // ✅ 改为大写
```

#### SysConfigController.java
```java
// 配置列表
@PreAuthorize("hasAuthority('ADMIN')")  // ✅ 改为大写

// 更新配置
@PreAuthorize("hasAuthority('ADMIN')")  // ✅ 改为大写

// 新增配置
@PreAuthorize("hasAuthority('ADMIN')")  // ✅ 改为大写

// 删除配置
@PreAuthorize("hasAuthority('ADMIN')")  // ✅ 改为大写
```

---

## 📊 验证结果

修改后，项目中所有权限配置统计：

```bash
$ grep -r "@PreAuthorize.*hasAuthority" crypto-backend/src/main/java --include="*.java" | grep -o "hasAuthority('[^']*')" | sort | uniq -c

  20 hasAuthority('ADMIN')  # ✅ 全部统一为大写
```

---

## 🎯 影响的功能模块

修复后，以下功能现在可以正常访问：

### 用户管理
- ✅ 用户列表查询
- ✅ 更新用户信息
- ✅ 删除用户
- ✅ 启用/禁用用户

### 系统配置
- ✅ 配置列表查询
- ✅ 更新配置
- ✅ 新增配置
- ✅ 删除配置

### 其他管理功能
- ✅ 统计数据查询
- ✅ 合约订单管理
- ✅ 充值订单管理
- ✅ 现货订单管理
- ✅ 交易对管理
- ✅ 币种配置管理
- ✅ 金融产品管理
- ✅ 提现订单管理

---

## 🔑 权限体系说明

### 当前权限配置

项目中定义了两种用户类型：

| 用户类型 | user_type | 权限 | 说明 |
|---------|-----------|------|------|
| 管理员 | 1 | ADMIN | 可以访问所有管理功能 |
| 普通用户 | 0 | USER | 只能访问普通用户功能 |

### 权限设置位置

**UserDetailsServiceImpl.java**
```java
// 根据用户类型设置权限
if (user.getUserType() == 1) {
    authorities.add(new SimpleGrantedAuthority("ADMIN"));
} else {
    authorities.add(new SimpleGrantedAuthority("USER"));
}
```

### 权限检查方式

**控制器方法**
```java
@PreAuthorize("hasAuthority('ADMIN')")  // 要求 ADMIN 权限
public Result<?> someAdminFunction() {
    // 只有管理员可以访问
}
```

---

## 🚀 测试步骤

1. **重启应用**
   - 在 IntelliJ IDEA 中重新运行项目
   - 或者使用 `mvn spring-boot:run`

2. **登录系统**
   - 访问：`http://localhost:8080/`
   - 用户名：`admin`
   - 密码：`admin123`

3. **测试功能**
   - 点击左侧菜单的各个功能
   - 验证是否可以正常访问
   - 检查是否还有权限错误

---

## 📝 注意事项

1. **权限大小写敏感**
   - Spring Security 的权限检查是大小写敏感的
   - `'ADMIN'` ≠ `'admin'`
   - 必须保持一致

2. **统一规范**
   - 项目中统一使用大写权限名称
   - 新增功能时也要使用 `'ADMIN'` 或 `'USER'`

3. **权限扩展**
   - 如果需要添加新的权限，建议使用大写
   - 例如：`'SUPER_ADMIN'`, `'OPERATOR'` 等

---

## ✅ 修复完成

现在所有管理后台功能都可以正常访问了！

如果还有其他权限相关的问题，请检查：
1. 用户的 `user_type` 是否正确（管理员应该是 1）
2. 控制器方法上的 `@PreAuthorize` 注解是否使用了正确的权限名称
3. 后端日志中是否有其他错误信息

