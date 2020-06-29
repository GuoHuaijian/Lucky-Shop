package com.lucky.shop.mobile.ucenter;

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
@MapperScan(basePackages = "com.lucky.shop.mobile.ucenter.mapper")
@ComponentScan(basePackages = "com.lucky.shop.**.**.api")
public class LuckyShopMobileUcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopMobileUcenterApplication.class, args);

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
                "用户中心服务启动成功(♥◠‿◠)");
    }

}
