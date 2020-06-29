package com.lucky.shop.admin.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Admin
 */
@SpringCloudApplication
@EnableFeignClients
@MapperScan(basePackages = "com.lucky.shop.admin.mall.mapper")
@ComponentScan(basePackages = "com.lucky.shop.admin.**.api")
public class LuckyShopAdminMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminMallApplication.class, args);
        System.out.println("       \n" +
                "            ,--.'|_                        ,--.'|_   \n" +
                "            |  | :,'              __  ,-.  |  | :,'  \n" +
                "  .--.--.   :  : ' :            ,' ,'/ /|  :  : ' :  \n" +
                " /  /    '.;__,'  /    ,--.--.  '  | |' |.;__,'  /   \n" +
                "|  :  /`./|  |   |    /       \\ |  |   ,'|  |   |    \n" +
                "|  :  ;_  :__,'| :   .--.  .-. |'  :  /  :__,'| :    \n" +
                " \\  \\    `. '  : |__  \\__\\/: . .|  | '     '  : |__  \n" +
                "  `----.   \\|  | '.'| ,\" .--.; |;  : |     |  | '.'| \n" +
                " /  /`--'  /;  :    ;/  /  ,.  ||  , ;     ;  :    ; \n" +
                "'--'.     / |  ,   /;  :   .'   \\---'      |  ,   /  \n" +
                "  `--'---'   ---`-' |  ,     .-./           ---`-'   \n" +
                "                     `--`---'                        \n" +
                "Mall服务启动成功(♥◠‿◠)");
    }

}
