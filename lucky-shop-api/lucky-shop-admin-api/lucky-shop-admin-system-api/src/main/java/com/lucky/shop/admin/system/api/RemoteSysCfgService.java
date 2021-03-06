package com.lucky.shop.admin.system.api;

import com.lucky.shop.admin.system.api.domain.SysCfg;
import com.lucky.shop.admin.system.api.factory.SysCfgServiceFactory;
import com.lucky.shop.common.core.constant.ServiceNameConstants;
import com.lucky.shop.config.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 系统参数接口
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 19:40
 */
@FeignClient(value = ServiceNameConstants.LUCKY_SHOP_ADMIN_SYSTEM, path = "system/cfg", configuration =
        FeignRequestConfiguration.class, fallbackFactory = SysCfgServiceFactory.class)
public interface RemoteSysCfgService {

    /**
     * 根据参数名获取参数值
     * 系统获取参数值统一使用该方法
     * 如果参数无法做到后台管理系统和用户端系统同步，这里建议直接从数据库获取
     * todo 建议生产中使用redis来统一管理该参数，这里从redis缓存中获取
     *
     * @param cfgName
     * @return
     */
    @GetMapping("getCfgValue/{cfgName}")
    String getCfgValue(@PathVariable String cfgName);

    /**
     * 根据name获取系统参数
     *
     * @param cfgName
     * @return
     */
    @GetMapping("getByCfgName/{cfgName}")
    SysCfg getByCfgName(@PathVariable String cfgName);

    /**
     * 编辑参数
     *
     * @param cfg
     */
    @PostMapping("saveOrUpdate")
    void saveOrUpdate(@RequestBody SysCfg cfg);

}
