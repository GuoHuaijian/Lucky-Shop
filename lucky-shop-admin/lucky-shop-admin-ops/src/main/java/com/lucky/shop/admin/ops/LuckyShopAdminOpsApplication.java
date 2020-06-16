package com.lucky.shop.admin.ops;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Admin
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.lucky.shop.admin.**.api")
@MapperScan(basePackages = "com.lucky.shop.admin.ops.mapper")
public class LuckyShopAdminOpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminOpsApplication.class, args);
    }

}
