<template>
  <div class="platform-data">
    <h1 class="page-title">平台数据</h1>

    <!-- 平台概要 -->
    <div class="overview-section">
      <h2>平台概要</h2>
      <div class="overview-grid">
        <div class="overview-item">
          <div class="label">总用户数</div>
          <div class="value">{{ overview.totalUsers?.toLocaleString() }}</div>
        </div>
        <div class="overview-item">
          <div class="label">今日新增用户</div>
          <div class="value highlight">{{ overview.todayNewUsers }}</div>
        </div>
        <div class="overview-item">
          <div class="label">今日活跃用户</div>
          <div class="value">{{ overview.todayActiveUsers }}</div>
        </div>
        <div class="overview-item">
          <div class="label">VIP用户数</div>
          <div class="value">{{ overview.vipUserCount }}</div>
        </div>
        <div class="overview-item">
          <div class="label">总充值</div>
          <div class="value">{{ formatCurrency(overview.totalDeposit) }}</div>
        </div>
        <div class="overview-item">
          <div class="label">今日充值</div>
          <div class="value highlight">{{ formatCurrency(overview.todayDeposit) }}</div>
        </div>
        <div class="overview-item">
          <div class="label">总提现</div>
          <div class="value">{{ formatCurrency(overview.totalWithdraw) }}</div>
        </div>
        <div class="overview-item">
          <div class="label">今日提现</div>
          <div class="value highlight">{{ formatCurrency(overview.todayWithdraw) }}</div>
        </div>
      </div>
    </div>

    <!-- 标签页 -->
    <div class="tabs">
      <button 
        :class="['tab', { active: activeTab === 'daily' }]"
        @click="activeTab = 'daily'"
      >
        每日数据
      </button>
      <button 
        :class="['tab', { active: activeTab === 'players' }]"
        @click="activeTab = 'players'"
      >
        玩家数据
      </button>
    </div>

    <!-- 每日数据 -->
    <div v-if="activeTab === 'daily'" class="data-section">
      <div class="filters">
        <input v-model="dailyFilters.startDate" type="date" placeholder="开始日期">
        <input v-model="dailyFilters.endDate" type="date" placeholder="结束日期">
        <button @click="fetchDailyData" class="btn-primary">查询</button>
      </div>

      <table class="data-table">
        <thead>
          <tr>
            <th>日期</th>
            <th>新增用户</th>
            <th>活跃用户</th>
            <th>充值金额</th>
            <th>提现金额</th>
            <th>交易量</th>
            <th>利润</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in dailyData" :key="item.date">
            <td>{{ item.date }}</td>
            <td>{{ item.newUsers }}</td>
            <td>{{ item.activeUsers }}</td>
            <td>{{ formatCurrency(item.depositAmount) }}</td>
            <td>{{ formatCurrency(item.withdrawAmount) }}</td>
            <td>{{ formatCurrency(item.tradeVolume) }}</td>
            <td :class="{ profit: item.profit > 0 }">{{ formatCurrency(item.profit) }}</td>
          </tr>
        </tbody>
      </table>

      <div class="pagination">
        <button @click="dailyPage--" :disabled="dailyPage === 1">上一页</button>
        <span>第 {{ dailyPage }} 页</span>
        <button @click="dailyPage++">下一页</button>
      </div>
    </div>

    <!-- 玩家数据 -->
    <div v-if="activeTab === 'players'" class="data-section">
      <div class="filters">
        <input v-model="playerFilters.keyword" type="text" placeholder="搜索用户名/手机号">
        <select v-model="playerFilters.vipLevel">
          <option value="">全部VIP等级</option>
          <option value="0">普通用户</option>
          <option value="1">VIP1</option>
          <option value="2">VIP2</option>
          <option value="3">VIP3</option>
        </select>
        <select v-model="playerFilters.status">
          <option value="">全部状态</option>
          <option value="1">正常</option>
          <option value="0">禁用</option>
        </select>
        <button @click="fetchPlayerData" class="btn-primary">查询</button>
      </div>

      <table class="data-table">
        <thead>
          <tr>
            <th>用户ID</th>
            <th>用户名</th>
            <th>手机号</th>
            <th>VIP等级</th>
            <th>总资产</th>
            <th>充值总额</th>
            <th>提现总额</th>
            <th>状态</th>
            <th>注册时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="player in playerData" :key="player.userId">
            <td>{{ player.userId }}</td>
            <td>{{ player.username }}</td>
            <td>{{ player.phone }}</td>
            <td><span class="vip-badge">VIP{{ player.vipLevel }}</span></td>
            <td>{{ formatCurrency(player.totalAsset) }}</td>
            <td>{{ formatCurrency(player.totalDeposit) }}</td>
            <td>{{ formatCurrency(player.totalWithdraw) }}</td>
            <td>
              <span :class="['status-badge', player.status === 1 ? 'active' : 'inactive']">
                {{ player.status === 1 ? '正常' : '禁用' }}
              </span>
            </td>
            <td>{{ player.createTime }}</td>
            <td>
              <router-link :to="`/admin/player/${player.userId}`" class="btn-link">详情</router-link>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="pagination">
        <button @click="playerPage--" :disabled="playerPage === 1">上一页</button>
        <span>第 {{ playerPage }} 页</span>
        <button @click="playerPage++">下一页</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'

const activeTab = ref('daily')
const overview = ref<any>({})
const dailyData = ref<any[]>([])
const playerData = ref<any[]>([])
const dailyPage = ref(1)
const playerPage = ref(1)

const dailyFilters = ref({
  startDate: '',
  endDate: ''
})

const playerFilters = ref({
  keyword: '',
  vipLevel: '',
  status: ''
})

const formatCurrency = (value: number) => {
  return new Intl.NumberFormat('zh-CN', {
    style: 'currency',
    currency: 'CNY'
  }).format(value || 0)
}

const fetchOverview = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/admin/platform/overview')
    const result = await response.json()
    if (result.code === 200) {
      overview.value = result.data
    }
  } catch (error) {
    console.error('获取平台概要失败:', error)
  }
}

const fetchDailyData = async () => {
  try {
    const params = new URLSearchParams({
      current: dailyPage.value.toString(),
      size: '10',
      ...dailyFilters.value
    })
    const response = await fetch(`http://localhost:8080/api/admin/platform/daily-data?${params}`)
    const result = await response.json()
    if (result.code === 200) {
      dailyData.value = result.data.records
    }
  } catch (error) {
    console.error('获取每日数据失败:', error)
  }
}

const fetchPlayerData = async () => {
  try {
    const params = new URLSearchParams({
      current: playerPage.value.toString(),
      size: '10',
      ...playerFilters.value
    })
    const response = await fetch(`http://localhost:8080/api/admin/platform/player-data?${params}`)
    const result = await response.json()
    if (result.code === 200) {
      playerData.value = result.data.records
    }
  } catch (error) {
    console.error('获取玩家数据失败:', error)
  }
}

watch(activeTab, (newTab) => {
  if (newTab === 'daily') {
    fetchDailyData()
  } else {
    fetchPlayerData()
  }
})

watch(dailyPage, () => fetchDailyData())
watch(playerPage, () => fetchPlayerData())

onMounted(() => {
  fetchOverview()
  fetchDailyData()
})
</script>

<style scoped>
.platform-data {
  padding: 24px;
  background: #f5f5f5;
  min-height: 100vh;
}

.page-title {
  font-size: 28px;
  font-weight: bold;
  color: #333;
  margin-bottom: 24px;
}

.overview-section {
  background: white;
  border-radius: 12px;
  padding: 24px;
  margin-bottom: 24px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.overview-section h2 {
  font-size: 20px;
  font-weight: 600;
  color: #333;
  margin-bottom: 20px;
}

.overview-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
}

.overview-item {
  padding: 16px;
  background: #f8f9fa;
  border-radius: 8px;
}

.overview-item .label {
  font-size: 14px;
  color: #666;
  margin-bottom: 8px;
}

.overview-item .value {
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

.overview-item .value.highlight {
  color: #667eea;
}

.tabs {
  display: flex;
  gap: 8px;
  margin-bottom: 24px;
}

.tab {
  padding: 12px 24px;
  background: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.3s;
}

.tab.active {
  background: #667eea;
  color: white;
}

.data-section {
  background: white;
  border-radius: 12px;
  padding: 24px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.filters {
  display: flex;
  gap: 12px;
  margin-bottom: 20px;
  flex-wrap: wrap;
}

.filters input,
.filters select {
  padding: 10px 16px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 14px;
}

.btn-primary {
  padding: 10px 24px;
  background: #667eea;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
  transition: background 0.3s;
}

.btn-primary:hover {
  background: #5568d3;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.data-table th,
.data-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #eee;
}

.data-table th {
  background: #f8f9fa;
  font-weight: 600;
  color: #333;
}

.data-table td.profit {
  color: #10b981;
  font-weight: 600;
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

.btn-link {
  color: #667eea;
  text-decoration: none;
  font-weight: 500;
}

.btn-link:hover {
  text-decoration: underline;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 16px;
}

.pagination button {
  padding: 8px 16px;
  background: #667eea;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

.pagination button:disabled {
  background: #ccc;
  cursor: not-allowed;
}
</style>

