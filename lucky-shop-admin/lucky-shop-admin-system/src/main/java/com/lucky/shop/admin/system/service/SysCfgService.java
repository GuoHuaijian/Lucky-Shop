package com.lucky.shop.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.system.domain.SysCfg;

/**
 * 系统参数
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 16:49
 */
public interface SysCfgService extends IService<SysCfg> {

    /**
     * 新增或修改
     *
     * @param cfg
     * @return
     */
    SysCfg saveOrUpdateCfg(SysCfg cfg);

    /**
     * 删除系统参数
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据name获取系统参数
     *
     * @param cfgName
     * @return
     */
    SysCfg getByCfgName(String cfgName);

    /**
     * 根据参数名获取参数值
     * 系统获取参数值统一使用该方法
     * 如果参数无法做到后台管理系统和用户端系统同步，这里建议直接从数据库获取
     * todo 建议生产中使用redis来统一管理该参数，这里从redis缓存中获取
     *
     * @param cfgName
     * @return
     */
    String getCfgValue(String cfgName);

    /**
     * 更新系统参数
     *
     * @param cfgName
     * @param cfgValue
     */
    void update(String cfgName, String cfgValue);


}
