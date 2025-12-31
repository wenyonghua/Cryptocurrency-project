<template>
  <div class="player-detail">
    <div class="header">
      <button @click="$router.back()" class="btn-back">
        <i class="fas fa-arrow-left"></i> 返回
      </button>
      <h1>玩家详情</h1>
    </div>

    <!-- 基本信息 -->
    <div class="info-card">
      <h2>基本信息</h2>
      <div class="info-grid">
        <div class="info-item">
          <span class="label">用户ID:</span>
          <span class="value">{{ player.userId }}</span>
        </div>
        <div class="info-item">
          <span class="label">用户名:</span>
          <span class="value">{{ player.username }}</span>
        </div>
        <div class="info-item">
          <span class="label">手机号:</span>
          <span class="value">{{ player.phone }}</span>
        </div>
        <div class="info-item">
          <span class="label">邮箱:</span>
          <span class="value">{{ player.email }}</span>
        </div>
        <div class="info-item">
          <span class="label">VIP等级:</span>
          <span class="value">
            <span class="vip-badge">VIP{{ player.vipLevel }}</span>
          </span>
        </div>
        <div class="info-item">
          <span class="label">状态:</span>
          <span class="value">
            <span :class="['status-badge', player.status === 1 ? 'active' : 'inactive']">
              {{ player.status === 1 ? '正常' : '禁用' }}
            </span>
          </span>
        </div>
        <div class="info-item">
          <span class="label">注册时间:</span>
          <span class="value">{{ player.createTime }}</span>
        </div>
        <div class="info-item">
          <span class="label">最后登录:</span>
          <span class="value">{{ player.lastLoginTime }}</span>
        </div>
      </div>

      <div class="actions">
        <button @click="toggleStatus" class="btn-action">
          {{ player.status === 1 ? '禁用账号' : '启用账号' }}
        </button>
        <button @click="showVipDialog = true" class="btn-action">设置VIP等级</button>
      </div>
    </div>

    <!-- 资产信息 -->
    <div class="info-card">
      <h2>资产信息</h2>
      <table class="asset-table">
        <thead>
          <tr>
            <th>币种</th>
            <th>可用余额</th>
            <th>冻结余额</th>
            <th>总余额</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="asset in player.assets" :key="asset.coinId">
            <td>{{ asset.coinName }}</td>
            <td>{{ asset.availableBalance }}</td>
            <td>{{ asset.frozenBalance }}</td>
            <td>{{ asset.totalBalance }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 统计数据 -->
    <div class="stats-grid">
      <div class="stat-card">
        <div class="stat-label">总充值</div>
        <div class="stat-value">{{ formatCurrency(player.totalDeposit) }}</div>
      </div>
      <div class="stat-card">
        <div class="stat-label">总提现</div>
        <div class="stat-value">{{ formatCurrency(player.totalWithdraw) }}</div>
      </div>
      <div class="stat-card">
        <div class="stat-label">交易次数</div>
        <div class="stat-value">{{ player.tradeCount }}</div>
      </div>
      <div class="stat-card">
        <div class="stat-label">交易量</div>
        <div class="stat-value">{{ formatCurrency(player.tradeVolume) }}</div>
      </div>
    </div>

    <!-- VIP设置对话框 -->
    <div v-if="showVipDialog" class="dialog-overlay" @click="showVipDialog = false">
      <div class="dialog" @click.stop>
        <h3>设置VIP等级</h3>
        <select v-model="newVipLevel" class="vip-select">
          <option value="0">普通用户</option>
          <option value="1">VIP1</option>
          <option value="2">VIP2</option>
          <option value="3">VIP3</option>
          <option value="4">VIP4</option>
          <option value="5">VIP5</option>
        </select>
        <div class="dialog-actions">
          <button @click="showVipDialog = false" class="btn-cancel">取消</button>
          <button @click="updateVipLevel" class="btn-confirm">确定</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const player = ref<any>({})
const showVipDialog = ref(false)
const newVipLevel = ref(0)

const formatCurrency = (value: number) => {
  return new Intl.NumberFormat('zh-CN', {
    style: 'currency',
    currency: 'CNY'
  }).format(value || 0)
}

const fetchPlayerDetail = async () => {
  try {
    const userId = route.params.id
    const response = await fetch(`http://localhost:8080/api/admin/player/${userId}`)
    const result = await response.json()
    if (result.code === 200) {
      player.value = result.data
      newVipLevel.value = result.data.vipLevel
    }
  } catch (error) {
    console.error('获取玩家详情失败:', error)
  }
}

const toggleStatus = async () => {
  try {
    const newStatus = player.value.status === 1 ? 0 : 1
    const response = await fetch(
      `http://localhost:8080/api/admin/player/${player.value.userId}/status?status=${newStatus}`,
      { method: 'PUT' }
    )
    const result = await response.json()
    if (result.code === 200) {
      player.value.status = newStatus
      alert('状态更新成功')
    }
  } catch (error) {
    console.error('更新状态失败:', error)
  }
}

const updateVipLevel = async () => {
  try {
    const response = await fetch(
      `http://localhost:8080/api/admin/player/${player.value.userId}/vip-level?vipLevel=${newVipLevel.value}`,
      { method: 'PUT' }
    )
    const result = await response.json()
    if (result.code === 200) {
      player.value.vipLevel = newVipLevel.value
      showVipDialog.value = false
      alert('VIP等级更新成功')
    }
  } catch (error) {
    console.error('更新VIP等级失败:', error)
  }
}

onMounted(() => {
  fetchPlayerDetail()
})
</script>

<style scoped>
.player-detail {
  padding: 24px;
  background: #f5f5f5;
  min-height: 100vh;
}

.header {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 24px;
}

.btn-back {
  padding: 10px 20px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 6px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
}

.header h1 {
  font-size: 28px;
  font-weight: bold;
  color: #333;
}

.info-card {
  background: white;
  border-radius: 12px;
  padding: 24px;
  margin-bottom: 24px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.info-card h2 {
  font-size: 20px;
  font-weight: 600;
  color: #333;
  margin-bottom: 20px;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 20px;
}

.info-item {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.info-item .label {
  font-size: 14px;
  color: #666;
}

.info-item .value {
  font-size: 16px;
  font-weight: 500;
  color: #333;
}

.vip-badge {
  padding: 4px 12px;
  background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
  color: white;
  border-radius: 12px;
  font-size: 12px;
  font-weight: 600;
}

.status-badge {
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 12px;
  font-weight: 600;
}

.status-badge.active {
  background: #d1fae5;
  color: #065f46;
}

.status-badge.inactive {
  background: #fee2e2;
  color: #991b1b;
}

.actions {
  display: flex;
  gap: 12px;
}

.btn-action {
  padding: 10px 24px;
  background: #667eea;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
}

.asset-table {
  width: 100%;
  border-collapse: collapse;
}

.asset-table th,
.asset-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #eee;
}

.asset-table th {
  background: #f8f9fa;
  font-weight: 600;
  color: #333;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
}

.stat-card {
  background: white;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.stat-label {
  font-size: 14px;
  color: #666;
  margin-bottom: 8px;
}

.stat-value {
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

.dialog-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.dialog {
  background: white;
  border-radius: 12px;
  padding: 24px;
  min-width: 400px;
}

.dialog h3 {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 20px;
}

.vip-select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 6px;
  margin-bottom: 20px;
}

.dialog-actions {
  display: flex;
  gap: 12px;
  justify-content: flex-end;
}

.btn-cancel,
.btn-confirm {
  padding: 10px 24px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
}

.btn-cancel {
  background: #e5e7eb;
  color: #333;
}

.btn-confirm {
  background: #667eea;
  color: white;
}
</style>

