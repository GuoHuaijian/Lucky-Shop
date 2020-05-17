package com.lucky.shop.auth;

import com.lucky.shop.auth.controller.LoginController;
import com.lucky.shop.common.dto.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class LuckyShopAuthApplicationTests {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private LoginController loginController;

    @Test
    void contextLoads() {

        ResponseResult result = loginController.login("admin", "admin");
//        System.out.println(result.getData());
    }

}
