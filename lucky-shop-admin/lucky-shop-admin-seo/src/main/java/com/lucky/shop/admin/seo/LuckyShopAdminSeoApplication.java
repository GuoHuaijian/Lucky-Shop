package com.lucky.shop.admin.seo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author Admin
 */
@SpringCloudApplication
@MapperScan(basePackages = "com.lucky.shop.admin.seo.mapper")
public class LuckyShopAdminSeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminSeoApplication.class, args);
    }

}
