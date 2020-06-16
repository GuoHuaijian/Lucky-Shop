package com.lucky.shop.admin.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author Admin
 */
@SpringCloudApplication
@MapperScan(basePackages = "com.lucky.shop.admin.message.mapper")
public class LuckyShopAdminMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminMessageApplication.class, args);
    }

}
