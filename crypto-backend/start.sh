#!/bin/bash
# 强制使用 Java 17 启动（Java 25 会导致 Lombok 编译失败、Spring 启动报错）
set -e
cd "$(dirname "$0")"

if /usr/libexec/java_home -v 17 >/dev/null 2>&1; then
  export JAVA_HOME="$(/usr/libexec/java_home -v 17)"
else
  echo "错误: 未找到 Java 17，请安装: brew install openjdk@17"
  exit 1
fi

echo "使用 JAVA_HOME=$JAVA_HOME"
java -version

mvn clean spring-boot:run -Dmaven.test.skip=true "$@"
