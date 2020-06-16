package com.lucky.shop.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author Admin
 */
@SpringCloudApplication
@MapperScan(basePackages = "com.lucky.shop.auth.mapper")
public class LuckyShopAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAuthApplication.class, args);
    }

}
