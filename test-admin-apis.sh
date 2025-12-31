#!/bin/bash

# 后台管理系统 API 测试脚本
# 使用方法: bash test-admin-apis.sh

BASE_URL="http://localhost:8080/api"

echo "========================================="
echo "后台管理系统 API 测试"
echo "========================================="
echo ""

# 颜色定义
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 测试函数
test_api() {
    local name=$1
    local url=$2
    local method=${3:-GET}
    
    echo -e "${YELLOW}测试: ${name}${NC}"
    echo "URL: ${method} ${url}"
    
    if [ "$method" = "GET" ]; then
        response=$(curl -s -w "\n%{http_code}" "${url}")
    else
        response=$(curl -s -w "\n%{http_code}" -X "${method}" "${url}")
    fi
    
    http_code=$(echo "$response" | tail -n1)
    body=$(echo "$response" | sed '$d')
    
    if [ "$http_code" = "200" ]; then
        echo -e "${GREEN}✓ 成功 (HTTP $http_code)${NC}"
        echo "响应: $(echo $body | jq -c '.' 2>/dev/null || echo $body | head -c 100)"
    else
        echo -e "${RED}✗ 失败 (HTTP $http_code)${NC}"
        echo "响应: $body"
    fi
    echo ""
}

echo "========================================="
echo "1. 首页数据统计模块"
echo "========================================="
test_api "获取首页统计数据" "${BASE_URL}/admin/dashboard/statistics"

echo "========================================="
echo "2. 平台数据模块"
echo "========================================="
test_api "获取平台概要信息" "${BASE_URL}/admin/platform/overview"
test_api "获取每日数据列表" "${BASE_URL}/admin/platform/daily-data?current=1&size=5"
test_api "获取玩家数据列表" "${BASE_URL}/admin/platform/player-data?current=1&size=5"

echo "========================================="
echo "3. 玩家管理模块"
echo "========================================="
test_api "获取玩家详情 (ID=1)" "${BASE_URL}/admin/player/1"

echo "========================================="
echo "4. 杂项管理模块 - 秒合约"
echo "========================================="
test_api "获取秒合约订单列表" "${BASE_URL}/admin/misc/seconds-contract/orders?current=1&size=5"

echo "========================================="
echo "5. 杂项管理模块 - 周期合约"
echo "========================================="
test_api "获取周期合约订单列表" "${BASE_URL}/admin/misc/cycle-contract/orders?current=1&size=5"

echo "========================================="
echo "6. 杂项管理模块 - IEO项目"
echo "========================================="
test_api "获取IEO项目列表" "${BASE_URL}/admin/misc/ieo/projects?current=1&size=5"

echo "========================================="
echo "7. 杂项管理模块 - 现货交易"
echo "========================================="
test_api "获取现货交易订单列表" "${BASE_URL}/admin/misc/spot/orders?current=1&size=5"

echo "========================================="
echo "测试完成"
echo "========================================="
echo ""
echo "提示："
echo "1. 如果所有测试都返回 200，说明 API 正常工作"
echo "2. 访问 Swagger 文档查看详细 API 说明: ${BASE_URL}/doc.html"
echo "3. 访问 Druid 监控查看数据库连接: ${BASE_URL}/druid/"

