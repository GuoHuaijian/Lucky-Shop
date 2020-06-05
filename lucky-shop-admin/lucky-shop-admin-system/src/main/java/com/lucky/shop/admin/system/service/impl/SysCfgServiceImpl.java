package com.lucky.shop.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.shop.admin.system.domain.SysCfg;
import com.lucky.shop.admin.system.mapper.SysCfgMapper;
import com.lucky.shop.admin.system.service.SysCfgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统参数
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 16:49
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysCfgServiceImpl extends ServiceImpl<SysCfgMapper, SysCfg> implements SysCfgService {

    /**
     * 新增或修改
     *
     * @param cfg
     * @return
     */
    @Override
    public SysCfg saveOrUpdateCfg(SysCfg cfg) {
        if (cfg.getId() == null) {
            this.save(cfg);
        } else {
            this.updateById(cfg);
        }
        return cfg;
    }

    /**
     * 删除系统参数
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        this.deleteById(id);
    }

    /**
     * 根据name获取系统参数
     *
     * @param cfgName
     * @return
     */
    @Override
    public SysCfg getByCfgName(String cfgName) {
        QueryWrapper<SysCfg> wrapper = new QueryWrapper<>();
        wrapper.eq(SysCfg.COL_CFG_NAME, cfgName);
        return this.getOne(wrapper);
    }

    /**
     * 根据参数名获取参数值
     * 系统获取参数值统一使用该方法
     * 如果参数无法做到后台管理系统和用户端系统同步，这里建议直接从数据库获取
     * todo 建议生产中使用redis来统一管理该参数，这里从redis缓存中获取
     *
     * @param cfgName
     * @return
     */
    @Override
    public String getCfgValue(String cfgName) {
        return getByCfgName(cfgName).getCfgValue();
    }

    /**
     * 更新系统参数
     *
     * @param cfgName
     * @param cfgValue
     */
    @Override
    public void update(String cfgName, String cfgValue) {
        SysCfg cfg = getByCfgName(cfgName);
        cfg.setCfgValue(cfgValue);
        updateById(cfg);
    }
}
