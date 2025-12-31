<template>
  <div class="admin-layout">
    <!-- 侧边栏 -->
    <aside class="sidebar">
      <div class="logo">
        <i class="fas fa-coins"></i>
        <span>后台管理系统</span>
      </div>

      <nav class="nav-menu">
        <router-link to="/admin/dashboard" class="nav-item">
          <i class="fas fa-home"></i>
          <span>首页统计</span>
        </router-link>

        <router-link to="/admin/platform" class="nav-item">
          <i class="fas fa-chart-line"></i>
          <span>平台数据</span>
        </router-link>

        <router-link to="/admin/players" class="nav-item">
          <i class="fas fa-users"></i>
          <span>玩家管理</span>
        </router-link>

        <router-link to="/admin/misc" class="nav-item">
          <i class="fas fa-cog"></i>
          <span>杂项管理</span>
        </router-link>

        <div class="nav-divider"></div>

        <a href="http://localhost:8080/api/doc.html" target="_blank" class="nav-item">
          <i class="fas fa-book"></i>
          <span>API 文档</span>
        </a>

        <a href="http://localhost:8080/api/druid/" target="_blank" class="nav-item">
          <i class="fas fa-database"></i>
          <span>数据库监控</span>
        </a>
      </nav>

      <div class="sidebar-footer">
        <div class="user-info">
          <i class="fas fa-user-circle"></i>
          <span>管理员</span>
        </div>
        <button @click="logout" class="btn-logout">
          <i class="fas fa-sign-out-alt"></i>
          退出登录
        </button>
      </div>
    </aside>

    <!-- 主内容区 -->
    <main class="main-content">
      <!-- 顶部栏 -->
      <header class="top-bar">
        <div class="breadcrumb">
          <i class="fas fa-home"></i>
          <span>{{ currentPageTitle }}</span>
        </div>

        <div class="top-actions">
          <button class="icon-btn" title="刷新">
            <i class="fas fa-sync-alt"></i>
          </button>
          <button class="icon-btn" title="全屏">
            <i class="fas fa-expand"></i>
          </button>
          <button class="icon-btn" title="设置">
            <i class="fas fa-cog"></i>
          </button>
        </div>
      </header>

      <!-- 路由视图 -->
      <div class="content-wrapper">
        <router-view />
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const currentPageTitle = computed(() => {
  const titleMap: Record<string, string> = {
    '/admin/dashboard': '首页统计',
    '/admin/platform': '平台数据',
    '/admin/players': '玩家管理',
    '/admin/misc': '杂项管理'
  }
  return titleMap[route.path] || '后台管理'
})

const logout = () => {
  if (confirm('确定要退出登录吗？')) {
    router.push('/login')
  }
}
</script>

<style scoped>
.admin-layout {
  display: flex;
  height: 100vh;
  background: #f5f5f5;
}

.sidebar {
  width: 260px;
  background: linear-gradient(180deg, #667eea 0%, #764ba2 100%);
  color: white;
  display: flex;
  flex-direction: column;
  box-shadow: 2px 0 8px rgba(0, 0, 0, 0.1);
}

.logo {
  padding: 24px 20px;
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 20px;
  font-weight: bold;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.logo i {
  font-size: 28px;
}

.nav-menu {
  flex: 1;
  padding: 20px 0;
  overflow-y: auto;
}

.nav-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 20px;
  color: rgba(255, 255, 255, 0.8);
  text-decoration: none;
  transition: all 0.3s;
  cursor: pointer;
}

.nav-item:hover {
  background: rgba(255, 255, 255, 0.1);
  color: white;
}

.nav-item.router-link-active {
  background: rgba(255, 255, 255, 0.2);
  color: white;
  border-left: 4px solid white;
}

.nav-item i {
  font-size: 18px;
  width: 24px;
}

.nav-divider {
  height: 1px;
  background: rgba(255, 255, 255, 0.1);
  margin: 12px 20px;
}

.sidebar-footer {
  padding: 20px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 12px;
  padding: 12px;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 8px;
}

.user-info i {
  font-size: 24px;
}

.btn-logout {
  width: 100%;
  padding: 10px;
  background: rgba(255, 255, 255, 0.2);
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  transition: background 0.3s;
}

.btn-logout:hover {
  background: rgba(255, 255, 255, 0.3);
}

.main-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.top-bar {
  height: 64px;
  background: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 16px;
  color: #333;
}

.breadcrumb i {
  color: #667eea;
}

.top-actions {
  display: flex;
  gap: 12px;
}

.icon-btn {
  width: 40px;
  height: 40px;
  border: none;
  background: #f5f5f5;
  border-radius: 8px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s;
}

.icon-btn:hover {
  background: #e5e7eb;
  color: #667eea;
}

.content-wrapper {
  flex: 1;
  overflow-y: auto;
}
</style>

