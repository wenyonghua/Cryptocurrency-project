<template>
  <div class="admin-dashboard">
    <!-- 顶部统计卡片 -->
    <div class="stats-grid">
      <div class="stat-card">
        <div class="stat-icon revenue">
          <i class="fas fa-dollar-sign"></i>
        </div>
        <div class="stat-content">
          <div class="stat-label">平台总收入</div>
          <div class="stat-value">{{ formatCurrency(statistics.totalRevenue) }}</div>
          <div class="stat-trend positive">
            <i class="fas fa-arrow-up"></i> +12.5%
          </div>
        </div>
      </div>

      <div class="stat-card">
        <div class="stat-icon users">
          <i class="fas fa-users"></i>
        </div>
        <div class="stat-content">
          <div class="stat-label">玩家数量</div>
          <div class="stat-value">{{ statistics.playerCount?.toLocaleString() }}</div>
          <div class="stat-trend positive">
            <i class="fas fa-arrow-up"></i> +8.3%
          </div>
        </div>
      </div>

      <div class="stat-card">
        <div class="stat-icon deposit">
          <i class="fas fa-arrow-down"></i>
        </div>
        <div class="stat-content">
          <div class="stat-label">总充值金额</div>
          <div class="stat-value">{{ formatCurrency(statistics.totalDeposit) }}</div>
          <div class="stat-trend positive">
            <i class="fas fa-arrow-up"></i> +15.2%
          </div>
        </div>
      </div>

      <div class="stat-card">
        <div class="stat-icon withdraw">
          <i class="fas fa-arrow-up"></i>
        </div>
        <div class="stat-content">
          <div class="stat-label">总提现金额</div>
          <div class="stat-value">{{ formatCurrency(statistics.totalWithdraw) }}</div>
          <div class="stat-trend negative">
            <i class="fas fa-arrow-down"></i> -3.1%
          </div>
        </div>
      </div>
    </div>

    <!-- 趋势图表 -->
    <div class="charts-grid">
      <div class="chart-card">
        <div class="chart-header">
          <h3>充值趋势（最近7天）</h3>
        </div>
        <div class="chart-content">
          <canvas ref="depositChart"></canvas>
        </div>
      </div>

      <div class="chart-card">
        <div class="chart-header">
          <h3>提现趋势（最近7天）</h3>
        </div>
        <div class="chart-content">
          <canvas ref="withdrawChart"></canvas>
        </div>
      </div>
    </div>

    <!-- 快速操作 -->
    <div class="quick-actions">
      <h3>快速操作</h3>
      <div class="actions-grid">
        <router-link to="/admin/platform" class="action-btn">
          <i class="fas fa-chart-line"></i>
          <span>平台数据</span>
        </router-link>
        <router-link to="/admin/players" class="action-btn">
          <i class="fas fa-users"></i>
          <span>玩家管理</span>
        </router-link>
        <router-link to="/admin/misc" class="action-btn">
          <i class="fas fa-cog"></i>
          <span>杂项管理</span>
        </router-link>
        <router-link to="/admin/settings" class="action-btn">
          <i class="fas fa-sliders-h"></i>
          <span>系统设置</span>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { Chart, registerables } from 'chart.js'

Chart.register(...registerables)

interface Statistics {
  totalRevenue: number
  playerCount: number
  totalDeposit: number
  totalWithdraw: number
  depositTrend: Array<{ date: string; amount: number }>
  withdrawTrend: Array<{ date: string; amount: number }>
}

const statistics = ref<Statistics>({
  totalRevenue: 0,
  playerCount: 0,
  totalDeposit: 0,
  totalWithdraw: 0,
  depositTrend: [],
  withdrawTrend: []
})

const depositChart = ref<HTMLCanvasElement>()
const withdrawChart = ref<HTMLCanvasElement>()

const formatCurrency = (value: number) => {
  return new Intl.NumberFormat('zh-CN', {
    style: 'currency',
    currency: 'CNY'
  }).format(value)
}

const fetchStatistics = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/admin/dashboard/statistics')
    const result = await response.json()
    if (result.code === 200) {
      statistics.value = result.data
      renderCharts()
    }
  } catch (error) {
    console.error('获取统计数据失败:', error)
  }
}

const renderCharts = () => {
  // 渲染充值趋势图
  if (depositChart.value) {
    new Chart(depositChart.value, {
      type: 'line',
      data: {
        labels: statistics.value.depositTrend.map(item => item.date),
        datasets: [{
          label: '充值金额',
          data: statistics.value.depositTrend.map(item => item.amount),
          borderColor: '#10b981',
          backgroundColor: 'rgba(16, 185, 129, 0.1)',
          tension: 0.4
        }]
      }
    })
  }

  // 渲染提现趋势图
  if (withdrawChart.value) {
    new Chart(withdrawChart.value, {
      type: 'line',
      data: {
        labels: statistics.value.withdrawTrend.map(item => item.date),
        datasets: [{
          label: '提现金额',
          data: statistics.value.withdrawTrend.map(item => item.amount),
          borderColor: '#ef4444',
          backgroundColor: 'rgba(239, 68, 68, 0.1)',
          tension: 0.4
        }]
      }
    })
  }
}

onMounted(() => {
  fetchStatistics()
})
</script>

<style scoped>
.admin-dashboard {
  padding: 24px;
  background: #f5f5f5;
  min-height: 100vh;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-bottom: 24px;
}

.stat-card {
  background: white;
  border-radius: 12px;
  padding: 20px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.stat-icon {
  width: 60px;
  height: 60px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  color: white;
}

.stat-icon.revenue { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }
.stat-icon.users { background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%); }
.stat-icon.deposit { background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%); }
.stat-icon.withdraw { background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%); }

.stat-content {
  flex: 1;
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
  margin-bottom: 4px;
}

.stat-trend {
  font-size: 12px;
  display: flex;
  align-items: center;
  gap: 4px;
}

.stat-trend.positive { color: #10b981; }
.stat-trend.negative { color: #ef4444; }

.charts-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
  gap: 20px;
  margin-bottom: 24px;
}

.chart-card {
  background: white;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.chart-header h3 {
  font-size: 18px;
  font-weight: 600;
  color: #333;
  margin-bottom: 16px;
}

.chart-content {
  height: 300px;
}

.quick-actions {
  background: white;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.quick-actions h3 {
  font-size: 18px;
  font-weight: 600;
  color: #333;
  margin-bottom: 16px;
}

.actions-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 16px;
}

.action-btn {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
  padding: 24px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border-radius: 12px;
  text-decoration: none;
  transition: transform 0.2s;
}

.action-btn:hover {
  transform: translateY(-4px);
}

.action-btn i {
  font-size: 32px;
}

.action-btn span {
  font-size: 14px;
  font-weight: 500;
}
</style>

