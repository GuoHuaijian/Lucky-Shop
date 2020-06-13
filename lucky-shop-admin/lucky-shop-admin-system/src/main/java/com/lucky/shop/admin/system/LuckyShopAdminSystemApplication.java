package com.lucky.shop.admin.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Admin
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lucky.shop.admin.system.mapper")
public class LuckyShopAdminSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminSystemApplication.class, args);
    }

}
