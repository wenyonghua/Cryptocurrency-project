package com.crypto.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 虚拟货币交易平台启动类
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@SpringBootApplication
public class CryptoPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoPlatformApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("  Crypto Platform 启动成功！");
        System.out.println("  API 文档地址: http://localhost:8080/api/doc.html");
        System.out.println("  Druid 监控: http://localhost:8080/api/druid");
        System.out.println("========================================\n");
    }
}

