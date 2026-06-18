#!/bin/bash

echo "========================================="
echo "🔍 Crypto Platform 启动检查脚本"
echo "========================================="
echo ""

# 检查 Java 版本
echo "1️⃣ 检查 Java 版本..."
java -version
echo ""

# 检查 MySQL 服务
echo "2️⃣ 检查 MySQL 服务..."
if command -v mysql &> /dev/null; then
    echo "✅ MySQL 已安装"
    mysql -u root -p19880806 -e "SELECT VERSION();" 2>/dev/null
    if [ $? -eq 0 ]; then
        echo "✅ MySQL 连接成功"
        echo ""
        echo "📊 检查数据库..."
        mysql -u root -p19880806 -e "SHOW DATABASES LIKE 'crypto_platform';" 2>/dev/null
        echo ""
        echo "📊 检查用户表..."
        mysql -u root -p19880806 crypto_platform -e "SELECT username, user_type, status FROM sys_user WHERE username='admin';" 2>/dev/null
    else
        echo "❌ MySQL 连接失败！请检查密码是否正确"
    fi
else
    echo "❌ MySQL 未安装"
fi
echo ""

# 检查 8080 端口
echo "3️⃣ 检查 8080 端口..."
if lsof -i :8080 > /dev/null 2>&1; then
    echo "✅ 8080 端口正在使用"
    lsof -i :8080
else
    echo "❌ 8080 端口未被使用 - 后端服务未启动"
fi
echo ""

# 检查项目文件
echo "4️⃣ 检查项目文件..."
if [ -f "crypto-backend/src/main/java/com/crypto/platform/CryptoPlatformApplication.java" ]; then
    echo "✅ 主类文件存在"
else
    echo "❌ 主类文件不存在"
fi

if [ -f "crypto-backend/src/main/resources/static/admin/login.html" ]; then
    echo "✅ 登录页面存在"
else
    echo "❌ 登录页面不存在"
fi
echo ""

# 检查编译结果
echo "5️⃣ 检查编译结果..."
if [ -d "crypto-backend/target/classes" ]; then
    echo "✅ target/classes 目录存在"
    if [ -f "crypto-backend/target/classes/com/crypto/platform/CryptoPlatformApplication.class" ]; then
        echo "✅ 主类已编译"
    else
        echo "❌ 主类未编译 - 需要在 IDE 中重新编译"
    fi
else
    echo "❌ target/classes 目录不存在 - 项目未编译"
fi
echo ""

# 测试后端连接
echo "6️⃣ 测试后端服务..."
if curl -s http://localhost:8080/admin/login.html > /dev/null 2>&1; then
    echo "✅ 后端服务正常"
else
    echo "❌ 后端服务无法访问"
fi
echo ""

echo "========================================="
echo "💡 诊断建议"
echo "========================================="
echo ""

if ! lsof -i :8080 > /dev/null 2>&1; then
    echo "⚠️  后端服务未启动，请："
    echo ""
    echo "   1. 在 IntelliJ IDEA 中打开项目"
    echo "   2. 确保安装了 Lombok 插件"
    echo "   3. 启用注解处理（Settings → Compiler → Annotation Processors）"
    echo "   4. 在 Maven 窗口中执行："
    echo "      - 双击 clean"
    echo "      - 双击 compile"
    echo "   5. 右键运行 CryptoPlatformApplication.java"
    echo ""
fi

if ! [ -f "crypto-backend/target/classes/com/crypto/platform/CryptoPlatformApplication.class" ]; then
    echo "⚠️  项目未编译成功，请："
    echo ""
    echo "   1. 检查是否有编译错误（查看 IDE 的 Build 窗口）"
    echo "   2. 确保 Lombok 插件已正确配置"
    echo "   3. 尝试 Build → Rebuild Project"
    echo ""
fi

echo "========================================="
echo "✅ 检查完成"
echo "========================================="

