<template>
  <div class="misc-management">
    <h1 class="page-title">杂项管理</h1>

    <!-- 标签页 -->
    <div class="tabs">
      <button 
        :class="['tab', { active: activeTab === 'seconds' }]"
        @click="activeTab = 'seconds'"
      >
        秒合约
      </button>
      <button 
        :class="['tab', { active: activeTab === 'cycle' }]"
        @click="activeTab = 'cycle'"
      >
        周期合约
      </button>
      <button 
        :class="['tab', { active: activeTab === 'ieo' }]"
        @click="activeTab = 'ieo'"
      >
        IEO项目
      </button>
      <button 
        :class="['tab', { active: activeTab === 'spot' }]"
        @click="activeTab = 'spot'"
      >
        现货交易
      </button>
    </div>

    <!-- 秒合约订单 -->
    <div v-if="activeTab === 'seconds'" class="data-section">
      <h2>秒合约订单</h2>
      <div class="filters">
        <input v-model="secondsFilters.userId" type="text" placeholder="用户ID">
        <select v-model="secondsFilters.status">
          <option value="">全部状态</option>
          <option value="0">进行中</option>
          <option value="1">已结算</option>
          <option value="2">已取消</option>
        </select>
        <button @click="fetchSecondsOrders" class="btn-primary">查询</button>
      </div>

      <table class="data-table">
        <thead>
          <tr>
            <th>订单ID</th>
            <th>用户ID</th>
            <th>交易对</th>
            <th>方向</th>
            <th>投注金额</th>
            <th>开仓价格</th>
            <th>平仓价格</th>
            <th>盈亏</th>
            <th>状态</th>
            <th>创建时间</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in secondsOrders" :key="order.id">
            <td>{{ order.id }}</td>
            <td>{{ order.userId }}</td>
            <td>{{ order.symbol }}</td>
            <td>
              <span :class="['direction-badge', order.direction === 1 ? 'buy' : 'sell']">
                {{ order.direction === 1 ? '买涨' : '买跌' }}
              </span>
            </td>
            <td>{{ formatCurrency(order.betAmount) }}</td>
            <td>{{ order.openPrice }}</td>
            <td>{{ order.closePrice || '-' }}</td>
            <td :class="{ profit: order.profitLoss > 0, loss: order.profitLoss < 0 }">
              {{ order.profitLoss ? formatCurrency(order.profitLoss) : '-' }}
            </td>
            <td>
              <span :class="['status-badge', getStatusClass(order.status)]">
                {{ getStatusText(order.status) }}
              </span>
            </td>
            <td>{{ order.createTime }}</td>
          </tr>
        </tbody>
      </table>

      <div class="pagination">
        <button @click="secondsPage--" :disabled="secondsPage === 1">上一页</button>
        <span>第 {{ secondsPage }} 页</span>
        <button @click="secondsPage++">下一页</button>
      </div>
    </div>

    <!-- 周期合约订单 -->
    <div v-if="activeTab === 'cycle'" class="data-section">
      <h2>周期合约订单</h2>
      <div class="filters">
        <input v-model="cycleFilters.userId" type="text" placeholder="用户ID">
        <select v-model="cycleFilters.status">
          <option value="">全部状态</option>
          <option value="0">进行中</option>
          <option value="1">已结算</option>
          <option value="2">已取消</option>
        </select>
        <button @click="fetchCycleOrders" class="btn-primary">查询</button>
      </div>

      <table class="data-table">
        <thead>
          <tr>
            <th>订单ID</th>
            <th>用户ID</th>
            <th>交易对</th>
            <th>方向</th>
            <th>投注金额</th>
            <th>周期</th>
            <th>开仓价格</th>
            <th>平仓价格</th>
            <th>盈亏</th>
            <th>状态</th>
            <th>创建时间</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in cycleOrders" :key="order.id">
            <td>{{ order.id }}</td>
            <td>{{ order.userId }}</td>
            <td>{{ order.symbol }}</td>
            <td>
              <span :class="['direction-badge', order.direction === 1 ? 'buy' : 'sell']">
                {{ order.direction === 1 ? '买涨' : '买跌' }}
              </span>
            </td>
            <td>{{ formatCurrency(order.betAmount) }}</td>
            <td>{{ order.cyclePeriod }}秒</td>
            <td>{{ order.openPrice }}</td>
            <td>{{ order.closePrice || '-' }}</td>
            <td :class="{ profit: order.profitLoss > 0, loss: order.profitLoss < 0 }">
              {{ order.profitLoss ? formatCurrency(order.profitLoss) : '-' }}
            </td>
            <td>
              <span :class="['status-badge', getStatusClass(order.status)]">
                {{ getStatusText(order.status) }}
              </span>
            </td>
            <td>{{ order.createTime }}</td>
          </tr>
        </tbody>
      </table>

      <div class="pagination">
        <button @click="cyclePage--" :disabled="cyclePage === 1">上一页</button>
        <span>第 {{ cyclePage }} 页</span>
        <button @click="cyclePage++">下一页</button>
      </div>
    </div>

    <!-- IEO项目 -->
    <div v-if="activeTab === 'ieo'" class="data-section">
      <div class="section-header">
        <h2>IEO项目</h2>
        <button @click="showCreateDialog = true" class="btn-primary">创建项目</button>
      </div>

      <table class="data-table">
        <thead>
          <tr>
            <th>项目ID</th>
            <th>项目名称</th>
            <th>代币符号</th>
            <th>发行价格</th>
            <th>发行总量</th>
            <th>已售数量</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in ieoProjects" :key="project.id">
            <td>{{ project.id }}</td>
            <td>{{ project.projectName }}</td>
            <td>{{ project.tokenSymbol }}</td>
            <td>{{ formatCurrency(project.issuePrice) }}</td>
            <td>{{ project.totalSupply }}</td>
            <td>{{ project.soldAmount }}</td>
            <td>{{ project.startTime }}</td>
            <td>{{ project.endTime }}</td>
            <td>
              <span :class="['status-badge', getIeoStatusClass(project.status)]">
                {{ getIeoStatusText(project.status) }}
              </span>
            </td>
            <td>
              <button @click="editProject(project)" class="btn-link">编辑</button>
              <button @click="deleteProject(project.id)" class="btn-link danger">删除</button>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="pagination">
        <button @click="ieoPage--" :disabled="ieoPage === 1">上一页</button>
        <span>第 {{ ieoPage }} 页</span>
        <button @click="ieoPage++">下一页</button>
      </div>
    </div>

    <!-- 现货交易订单 -->
    <div v-if="activeTab === 'spot'" class="data-section">
      <h2>现货交易订单</h2>
      <div class="filters">
        <input v-model="spotFilters.userId" type="text" placeholder="用户ID">
        <select v-model="spotFilters.type">
          <option value="">全部类型</option>
          <option value="1">买入</option>
          <option value="2">卖出</option>
        </select>
        <select v-model="spotFilters.status">
          <option value="">全部状态</option>
          <option value="0">待成交</option>
          <option value="1">部分成交</option>
          <option value="2">完全成交</option>
          <option value="3">已取消</option>
        </select>
        <button @click="fetchSpotOrders" class="btn-primary">查询</button>
      </div>

      <table class="data-table">
        <thead>
          <tr>
            <th>订单ID</th>
            <th>用户ID</th>
            <th>交易对</th>
            <th>类型</th>
            <th>价格</th>
            <th>数量</th>
            <th>已成交</th>
            <th>总金额</th>
            <th>状态</th>
            <th>创建时间</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in spotOrders" :key="order.id">
            <td>{{ order.id }}</td>
            <td>{{ order.userId }}</td>
            <td>{{ order.symbol }}</td>
            <td>
              <span :class="['direction-badge', order.type === 1 ? 'buy' : 'sell']">
                {{ order.type === 1 ? '买入' : '卖出' }}
              </span>
            </td>
            <td>{{ order.price }}</td>
            <td>{{ order.amount }}</td>
            <td>{{ order.filledAmount }}</td>
            <td>{{ formatCurrency(order.totalAmount) }}</td>
            <td>
              <span :class="['status-badge', getSpotStatusClass(order.status)]">
                {{ getSpotStatusText(order.status) }}
              </span>
            </td>
            <td>{{ order.createTime }}</td>
          </tr>
        </tbody>
      </table>

      <div class="pagination">
        <button @click="spotPage--" :disabled="spotPage === 1">上一页</button>
        <span>第 {{ spotPage }} 页</span>
        <button @click="spotPage++">下一页</button>
      </div>
    </div>

    <!-- 创建/编辑IEO项目对话框 -->
    <div v-if="showCreateDialog" class="dialog-overlay" @click="showCreateDialog = false">
      <div class="dialog" @click.stop>
        <h3>{{ editingProject ? '编辑项目' : '创建项目' }}</h3>
        <div class="form-group">
          <label>项目名称</label>
          <input v-model="projectForm.projectName" type="text" placeholder="请输入项目名称">
        </div>
        <div class="form-group">
          <label>代币符号</label>
          <input v-model="projectForm.tokenSymbol" type="text" placeholder="请输入代币符号">
        </div>
        <div class="form-group">
          <label>发行价格</label>
          <input v-model="projectForm.issuePrice" type="number" placeholder="请输入发行价格">
        </div>
        <div class="form-group">
          <label>发行总量</label>
          <input v-model="projectForm.totalSupply" type="number" placeholder="请输入发行总量">
        </div>
        <div class="dialog-actions">
          <button @click="showCreateDialog = false" class="btn-cancel">取消</button>
          <button @click="saveProject" class="btn-confirm">保存</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'

const activeTab = ref('seconds')
const showCreateDialog = ref(false)
const editingProject = ref<any>(null)

// 秒合约
const secondsOrders = ref<any[]>([])
const secondsPage = ref(1)
const secondsFilters = ref({ userId: '', status: '' })

// 周期合约
const cycleOrders = ref<any[]>([])
const cyclePage = ref(1)
const cycleFilters = ref({ userId: '', status: '' })

// IEO项目
const ieoProjects = ref<any[]>([])
const ieoPage = ref(1)
const projectForm = ref({
  projectName: '',
  tokenSymbol: '',
  issuePrice: 0,
  totalSupply: 0
})

// 现货交易
const spotOrders = ref<any[]>([])
const spotPage = ref(1)
const spotFilters = ref({ userId: '', type: '', status: '' })

const formatCurrency = (value: number) => {
  return new Intl.NumberFormat('zh-CN', {
    style: 'currency',
    currency: 'CNY'
  }).format(value || 0)
}

const getStatusClass = (status: number) => {
  const map: any = { 0: 'pending', 1: 'success', 2: 'cancelled' }
  return map[status] || ''
}

const getStatusText = (status: number) => {
  const map: any = { 0: '进行中', 1: '已结算', 2: '已取消' }
  return map[status] || ''
}

const getIeoStatusClass = (status: number) => {
  const map: any = { 0: 'pending', 1: 'active', 2: 'ended' }
  return map[status] || ''
}

const getIeoStatusText = (status: number) => {
  const map: any = { 0: '未开始', 1: '进行中', 2: '已结束' }
  return map[status] || ''
}

const getSpotStatusClass = (status: number) => {
  const map: any = { 0: 'pending', 1: 'partial', 2: 'success', 3: 'cancelled' }
  return map[status] || ''
}

const getSpotStatusText = (status: number) => {
  const map: any = { 0: '待成交', 1: '部分成交', 2: '完全成交', 3: '已取消' }
  return map[status] || ''
}

const fetchSecondsOrders = async () => {
  try {
    const params = new URLSearchParams({
      current: secondsPage.value.toString(),
      size: '10',
      ...secondsFilters.value
    })
    const response = await fetch(`http://localhost:8080/api/admin/misc/seconds-contract/orders?${params}`)
    const result = await response.json()
    if (result.code === 200) {
      secondsOrders.value = result.data.records
    }
  } catch (error) {
    console.error('获取秒合约订单失败:', error)
  }
}

const fetchCycleOrders = async () => {
  try {
    const params = new URLSearchParams({
      current: cyclePage.value.toString(),
      size: '10',
      ...cycleFilters.value
    })
    const response = await fetch(`http://localhost:8080/api/admin/misc/cycle-contract/orders?${params}`)
    const result = await response.json()
    if (result.code === 200) {
      cycleOrders.value = result.data.records
    }
  } catch (error) {
    console.error('获取周期合约订单失败:', error)
  }
}

const fetchIeoProjects = async () => {
  try {
    const params = new URLSearchParams({
      current: ieoPage.value.toString(),
      size: '10'
    })
    const response = await fetch(`http://localhost:8080/api/admin/misc/ieo/projects?${params}`)
    const result = await response.json()
    if (result.code === 200) {
      ieoProjects.value = result.data.records
    }
  } catch (error) {
    console.error('获取IEO项目失败:', error)
  }
}

const fetchSpotOrders = async () => {
  try {
    const params = new URLSearchParams({
      current: spotPage.value.toString(),
      size: '10',
      ...spotFilters.value
    })
    const response = await fetch(`http://localhost:8080/api/admin/misc/spot/orders?${params}`)
    const result = await response.json()
    if (result.code === 200) {
      spotOrders.value = result.data.records
    }
  } catch (error) {
    console.error('获取现货订单失败:', error)
  }
}

const editProject = (project: any) => {
  editingProject.value = project
  projectForm.value = { ...project }
  showCreateDialog.value = true
}

const deleteProject = async (id: number) => {
  if (!confirm('确定要删除这个项目吗？')) return

  try {
    const response = await fetch(`http://localhost:8080/api/admin/misc/ieo/projects/${id}`, {
      method: 'DELETE'
    })
    const result = await response.json()
    if (result.code === 200) {
      alert('删除成功')
      fetchIeoProjects()
    }
  } catch (error) {
    console.error('删除项目失败:', error)
  }
}

const saveProject = async () => {
  try {
    const url = editingProject.value
      ? `http://localhost:8080/api/admin/misc/ieo/projects/${editingProject.value.id}`
      : 'http://localhost:8080/api/admin/misc/ieo/projects'

    const response = await fetch(url, {
      method: editingProject.value ? 'PUT' : 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(projectForm.value)
    })

    const result = await response.json()
    if (result.code === 200) {
      alert('保存成功')
      showCreateDialog.value = false
      editingProject.value = null
      fetchIeoProjects()
    }
  } catch (error) {
    console.error('保存项目失败:', error)
  }
}

watch(activeTab, (newTab) => {
  if (newTab === 'seconds') fetchSecondsOrders()
  else if (newTab === 'cycle') fetchCycleOrders()
  else if (newTab === 'ieo') fetchIeoProjects()
  else if (newTab === 'spot') fetchSpotOrders()
})

watch(secondsPage, () => fetchSecondsOrders())
watch(cyclePage, () => fetchCycleOrders())
watch(ieoPage, () => fetchIeoProjects())
watch(spotPage, () => fetchSpotOrders())

onMounted(() => {
  fetchSecondsOrders()
})
</script>

<style scoped>
.misc-management {
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

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.data-section h2 {
  font-size: 20px;
  font-weight: 600;
  color: #333;
  margin-bottom: 20px;
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
  font-size: 14px;
}

.data-table th {
  background: #f8f9fa;
  font-weight: 600;
  color: #333;
}

.direction-badge {
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 12px;
  font-weight: 600;
}

.direction-badge.buy {
  background: #d1fae5;
  color: #065f46;
}

.direction-badge.sell {
  background: #fee2e2;
  color: #991b1b;
}

.status-badge {
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 12px;
  font-weight: 600;
}

.status-badge.pending {
  background: #fef3c7;
  color: #92400e;
}

.status-badge.active,
.status-badge.success {
  background: #d1fae5;
  color: #065f46;
}

.status-badge.partial {
  background: #dbeafe;
  color: #1e40af;
}

.status-badge.cancelled,
.status-badge.ended {
  background: #e5e7eb;
  color: #374151;
}

.profit {
  color: #10b981;
  font-weight: 600;
}

.loss {
  color: #ef4444;
  font-weight: 600;
}

.btn-link {
  background: none;
  border: none;
  color: #667eea;
  cursor: pointer;
  font-size: 14px;
  margin-right: 12px;
}

.btn-link.danger {
  color: #ef4444;
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
  min-width: 500px;
}

.dialog h3 {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 16px;
}

.form-group label {
  display: block;
  font-size: 14px;
  font-weight: 500;
  color: #333;
  margin-bottom: 8px;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 14px;
}

.dialog-actions {
  display: flex;
  gap: 12px;
  justify-content: flex-end;
  margin-top: 24px;
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

