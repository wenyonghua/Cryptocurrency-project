package com.crypto.platform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j API 文档配置
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("虚拟货币交易平台 API 文档")
                        .version("1.0.0")
                        .description("虚拟货币交易平台后端接口文档")
                        .contact(new Contact()
                                .name("Crypto Platform Team")
                                .email("support@crypto.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}

