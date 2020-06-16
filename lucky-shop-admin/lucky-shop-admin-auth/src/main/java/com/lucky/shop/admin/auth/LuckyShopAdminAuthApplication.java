package com.lucky.shop.admin.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Admin
 */
@SpringCloudApplication
@MapperScan(basePackages = "com.lucky.shop.admin.auth.mapper")
@ComponentScan(basePackages = "com.lucky.shop.common.redis.service")
public class LuckyShopAdminAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminAuthApplication.class, args);
    }

}
