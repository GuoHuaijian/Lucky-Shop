package com.lucky.shop.admin.dashboard.controller;

import com.lucky.shop.admin.mall.api.RemoteDashboardService;
import com.lucky.shop.common.core.dto.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/22 15:17
 */
@RequestMapping("/dashboard")
@RestController
public class DashboardController {

    @Resource
    private RemoteDashboardService dashboardService;

    /**
     * 获取首页数据
     *
     * @return
     */
    @GetMapping()
    public ResponseResult get() {
        Map data = dashboardService.get();
        return ResponseResult.success(data);
    }
}
