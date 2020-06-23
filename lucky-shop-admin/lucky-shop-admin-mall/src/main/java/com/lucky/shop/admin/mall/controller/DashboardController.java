package com.lucky.shop.admin.mall.controller;

import com.lucky.shop.admin.mall.service.impl.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 首页
 *
 * @Author Guo Huaijian
 * @Date 2020/6/22 15:03
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    /**
     * 获取首页内容
     *
     * @return
     */
    @GetMapping()
    public Map get() {
        Map data = dashboardService.getDashboardData();
        return data;
    }
}
