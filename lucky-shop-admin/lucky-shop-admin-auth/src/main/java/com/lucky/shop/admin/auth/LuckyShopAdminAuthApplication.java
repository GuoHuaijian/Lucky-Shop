package com.lucky.shop.admin.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Admin
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lucky.shop.auth.mapper")
public class LuckyShopAdminAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminAuthApplication.class, args);
    }

}
