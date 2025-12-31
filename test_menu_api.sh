#!/bin/bash

echo "========================================="
echo "测试菜单 API"
echo "========================================="
echo ""

# 测试菜单 API
echo "正在测试 /system/menu/tree API..."
echo ""

curl -s http://localhost:8080/system/menu/tree | python3 -c "
import sys, json
try:
    data = json.load(sys.stdin)
    if data.get('code') == 200:
        print('✅ API 调用成功！')
        print('')
        menus = data.get('data', [])
        print(f'📊 菜单统计：')
        print(f'   一级菜单数量: {len(menus)}')
        print('')
        print('📋 菜单列表：')
        for menu in menus:
            children_count = len(menu.get('children', []))
            print(f'   {menu[\"name\"]} ({children_count}个子菜单)')
            for child in menu.get('children', []):
                print(f'      ├─ {child[\"name\"]}')
    else:
        print('❌ API 返回错误：', data.get('message'))
except Exception as e:
    print('❌ API 调用失败：', str(e))
    print('请确保后端服务已启动')
" 2>/dev/null

if [ $? -ne 0 ]; then
    echo "❌ 无法连接到后端服务"
    echo "请确保 Spring Boot 应用正在运行"
    echo ""
    echo "检查端口 8080 是否被占用："
    lsof -i :8080 2>/dev/null || echo "端口 8080 未被占用"
fi

echo ""
echo "========================================="

