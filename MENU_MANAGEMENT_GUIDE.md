# 📋 菜单管理功能使用指南

## 🎯 功能概述

菜单管理功能提供了完整的左侧菜单栏数据管理，包括菜单的增删改查和树形结构展示。

---

## 📊 数据库表结构

### sys_permission 表

| 字段名 | 类型 | 说明 | 示例 |
|--------|------|------|------|
| id | BIGINT | 权限ID | 1 |
| parent_id | BIGINT | 父权限ID | 0（一级菜单）或 2（二级菜单） |
| permission_name | VARCHAR(50) | 权限名称 | "玩家管理" |
| permission_key | VARCHAR(100) | 权限标识 | "player:manage" |
| permission_type | TINYINT | 权限类型 | 1-菜单，2-按钮 |
| path | VARCHAR(200) | 路由地址 | "/player" |
| component | VARCHAR(200) | 组件路径 | "views/player/index" |
| icon | VARCHAR(100) | 图标 | "User" |
| sort | INT | 排序 | 1, 2, 3... |
| status | TINYINT | 状态 | 0-禁用，1-正常 |
| deleted | TINYINT | 逻辑删除 | 0-未删除，1-已删除 |
| create_time | DATETIME | 创建时间 | 自动填充 |
| update_time | DATETIME | 更新时间 | 自动填充 |

---

## 📋 已创建的菜单结构

### 一级菜单

1. **平台数据** (ID: 1)
   - 权限标识: `platform:data`
   - 路径: `/platform`
   - 图标: `DataAnalysis`

2. **玩家管理** (ID: 2)
   - 权限标识: `player:manage`
   - 路径: `/player`
   - 图标: `User`

### 二级菜单（玩家管理下）

| ID | 菜单名称 | 权限标识 | 路径 | 图标 | 排序 |
|----|---------|---------|------|------|------|
| 21 | 玩家用户 | player:user:list | /player/user | User | 1 |
| 22 | 归属订单 | player:order:list | /player/order | List | 2 |
| 23 | 授权管理 | player:auth:manage | /player/auth | Key | 3 |
| 24 | 玩家资产 | player:asset:list | /player/asset | Wallet | 4 |
| 25 | 银行卡管理 | player:bank:manage | /player/bank | CreditCard | 5 |
| 26 | 实名认证 | player:kyc:manage | /player/kyc | Checked | 6 |
| 27 | 登录日志 | player:login:log | /player/login-log | Document | 7 |

### 按钮权限（玩家用户页面）

| ID | 按钮名称 | 权限标识 | 排序 |
|----|---------|---------|------|
| 211 | 详情 | player:user:detail | 1 |
| 212 | 编辑 | player:user:edit | 2 |
| 213 | 修改 | player:user:update | 3 |
| 214 | 彩金 | player:user:bonus | 4 |
| 215 | 人工上下分 | player:user:adjust | 5 |
| 216 | 完善地址配置 | player:user:address | 6 |
| 217 | 借贷上级代理 | player:user:agent | 7 |
| 218 | 删除 | player:user:delete | 8 |

---

## 🔌 API 接口

### 1. 获取菜单树

**接口地址**: `GET /system/menu/tree`

**请求参数**: 无

**响应示例**:
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "parentId": 0,
      "name": "平台数据",
      "key": "platform:data",
      "type": 1,
      "path": "/platform",
      "component": null,
      "icon": "DataAnalysis",
      "sort": 1,
      "status": 1,
      "children": []
    },
    {
      "id": 2,
      "parentId": 0,
      "name": "玩家管理",
      "key": "player:manage",
      "type": 1,
      "path": "/player",
      "component": null,
      "icon": "User",
      "sort": 2,
      "status": 1,
      "children": [
        {
          "id": 21,
          "parentId": 2,
          "name": "玩家用户",
          "key": "player:user:list",
          "type": 1,
          "path": "/player/user",
          "component": "views/player/user",
          "icon": "User",
          "sort": 1,
          "status": 1,
          "children": []
        }
      ]
    }
  ]
}
```

### 2. 获取当前用户菜单树

**接口地址**: `GET /system/menu/user-tree`

**请求参数**: 无（从 Token 中获取用户信息）

**响应示例**: 同上（目前返回所有菜单，后续可根据用户权限过滤）

---

## 💻 前端调用示例

### JavaScript/Axios

```javascript
// 获取菜单树
async function loadMenuTree() {
  try {
    const response = await axios.get('/system/menu/tree');
    const menuData = response.data.data;
    
    // 渲染菜单
    renderMenu(menuData);
  } catch (error) {
    console.error('加载菜单失败:', error);
  }
}

// 渲染菜单（递归）
function renderMenu(menuList, parentElement) {
  menuList.forEach(menu => {
    // 创建菜单项
    const menuItem = document.createElement('div');
    menuItem.className = 'menu-item';
    menuItem.innerHTML = `
      <i class="icon-${menu.icon}"></i>
      <span>${menu.name}</span>
    `;
    
    // 添加点击事件
    menuItem.onclick = () => {
      window.location.href = menu.path;
    };
    
    parentElement.appendChild(menuItem);
    
    // 递归渲染子菜单
    if (menu.children && menu.children.length > 0) {
      const subMenu = document.createElement('div');
      subMenu.className = 'sub-menu';
      parentElement.appendChild(subMenu);
      renderMenu(menu.children, subMenu);
    }
  });
}
```

### Vue 3 示例

```vue
<template>
  <el-menu :default-active="activeMenu" @select="handleMenuSelect">
    <template v-for="menu in menuList" :key="menu.id">
      <!-- 一级菜单 -->
      <el-sub-menu v-if="menu.children && menu.children.length > 0" :index="menu.key">
        <template #title>
          <el-icon><component :is="menu.icon" /></el-icon>
          <span>{{ menu.name }}</span>
        </template>
        
        <!-- 二级菜单 -->
        <el-menu-item 
          v-for="child in menu.children" 
          :key="child.id" 
          :index="child.key">
          {{ child.name }}
        </el-menu-item>
      </el-sub-menu>
      
      <!-- 无子菜单的一级菜单 -->
      <el-menu-item v-else :index="menu.key">
        <el-icon><component :is="menu.icon" /></el-icon>
        <span>{{ menu.name }}</span>
      </el-menu-item>
    </template>
  </el-menu>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const menuList = ref([]);
const activeMenu = ref('');

// 加载菜单
const loadMenu = async () => {
  try {
    const response = await axios.get('/system/menu/tree');
    menuList.value = response.data.data;
  } catch (error) {
    console.error('加载菜单失败:', error);
  }
};

// 菜单选择
const handleMenuSelect = (index) => {
  activeMenu.value = index;
  // 根据菜单key跳转页面
};

onMounted(() => {
  loadMenu();
});
</script>
```

---

## 🛠️ 数据库操作

### 初始化菜单数据

```bash
# 执行 SQL 脚本
mysql -u root -p19880806 < crypto-backend/src/main/resources/sql/insert_menu_data.sql
```

### 查询菜单数据

```sql
-- 查看所有菜单
SELECT * FROM sys_permission WHERE deleted = 0 ORDER BY parent_id, sort;

-- 查看一级菜单
SELECT * FROM sys_permission WHERE parent_id = 0 AND deleted = 0;

-- 查看某个菜单的子菜单
SELECT * FROM sys_permission WHERE parent_id = 2 AND deleted = 0;
```

### 添加新菜单

```sql
-- 添加一级菜单
INSERT INTO sys_permission (parent_id, permission_name, permission_key, permission_type, path, icon, sort, status)
VALUES (0, '新菜单', 'new:menu', 1, '/new', 'Setting', 3, 1);

-- 添加二级菜单
INSERT INTO sys_permission (parent_id, permission_name, permission_key, permission_type, path, component, icon, sort, status)
VALUES (2, '新子菜单', 'new:submenu', 1, '/player/new', 'views/player/new', 'Document', 8, 1);
```

---

## 📚 相关文档

- **PLAYER_MANAGEMENT_API.md** - 玩家管理 API 文档
- **PLAYER_SEARCH_GUIDE.md** - 玩家搜索功能指南
- **insert_menu_data.sql** - 菜单数据初始化脚本

---

## ✅ 已完成功能

- ✅ 创建 sys_permission 表
- ✅ 插入初始菜单数据
- ✅ 实现菜单树形结构 API
- ✅ 支持多级菜单展示
- ✅ 按钮权限管理

## 🚧 待实现功能

- ⏳ 根据用户权限过滤菜单
- ⏳ 菜单的增删改查管理接口
- ⏳ 角色权限关联
- ⏳ 动态路由生成

---

## 🎯 使用建议

1. **重启应用** - 让新功能生效
2. **测试接口** - 使用 Swagger UI 测试 `/system/menu/tree`
3. **前端对接** - 根据返回的菜单数据渲染左侧菜单栏
4. **权限控制** - 后续可根据用户角色过滤菜单

