package com.lucky.shop.admin.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Admin
 */
@SpringCloudApplication
@MapperScan(basePackages = "com.lucky.shop.admin.system.mapper")
@ComponentScan(basePackages = "com.lucky.shop.common.redis.service")
public class LuckyShopAdminSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminSystemApplication.class, args);
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
                "System服务启动成功(♥◠‿◠)");
    }

}
