package com.lucky.shop.admin.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Admin
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.lucky.shop.admin.mall.api")
public class LuckyShopAdminDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyShopAdminDashboardApplication.class, args);
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
                "Dashboard服务启动成功(♥◠‿◠)");
    }

}
