package com.lucky.shop.admin.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Admin
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lucky.shop.admin.mall.mapper")
public class LuckyShopAdminMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminMallApplication.class, args);
    }

}
