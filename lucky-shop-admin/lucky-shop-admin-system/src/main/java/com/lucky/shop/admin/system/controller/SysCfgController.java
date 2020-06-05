package com.lucky.shop.admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.shop.admin.system.domain.SysCfg;
import com.lucky.shop.admin.system.domain.SysFileInfo;
import com.lucky.shop.admin.system.service.SysCfgService;
import com.lucky.shop.admin.system.service.impl.FileService;
import com.lucky.shop.common.dto.ResponseResult;
import com.lucky.shop.common.factory.PageFactory;
import com.lucky.shop.common.tool.LogObjectHolder;
import com.lucky.shop.common.tool.Maps;
import com.lucky.shop.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 系统参数
 *
 * @Author Guo Huaijian
 * @Date 2020/6/5 16:51
 */
@RestController
@RequestMapping("/cfg")
@Slf4j
public class SysCfgController {

    @Autowired
    private SysCfgService cfgService;

    @Autowired
    private FileService fileService;

    /**
     * 查询参数列表
     */
    @GetMapping(value = "/list")
    public ResponseResult list(@RequestParam(required = false) String cfgName, @RequestParam(required = false) String cfgValue) {
        Page<SysCfg> page = new PageFactory<SysCfg>().defaultPage();
        QueryWrapper<SysCfg> wrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(cfgName)){
            wrapper.like(SysCfg.COL_CFG_NAME,cfgName);
        }
        if(StringUtil.isNotEmpty(cfgValue)){
            wrapper.like(SysCfg.COL_CFG_VALUE,cfgValue);
        }
        IPage<SysCfg> result = cfgService.page(page, wrapper);
        return ResponseResult.success(result);
    }

    /**
     * 分组查询参数
     * @param cfgGroup
     * @return
     */
    @GetMapping(value = "/getByPrefix")
    public ResponseResult list(@RequestParam(required = false) String cfgGroup) {
        QueryWrapper<SysCfg> wrapper = new QueryWrapper<>();
        wrapper.like(SysCfg.COL_CFG_NAME,cfgGroup);
        List<SysCfg> list = cfgService.list(wrapper);
        Map map = Maps.newHashMap();
        for(SysCfg cfg:list){
            map.put(cfg.getCfgName(),cfg.getCfgValue());
        }
        return ResponseResult.success(Maps.newHashMap("list",list,"map",map));
    }

    /**
     * 导出参数列表
     * @param cfgName
     * @param cfgValue
     * @return
     */
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public ResponseResult export(@RequestParam(required = false) String cfgName, @RequestParam(required = false) String cfgValue) {
        Page<SysCfg> page = new PageFactory<SysCfg>().defaultPage();
        QueryWrapper<SysCfg> wrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(cfgName)){
            wrapper.like(SysCfg.COL_CFG_NAME,cfgName);
        }
        if(StringUtil.isNotEmpty(cfgValue)){
            wrapper.like(SysCfg.COL_CFG_VALUE,cfgValue);
        }
        IPage<SysCfg> result = cfgService.page(page, wrapper);
        SysFileInfo fileInfo = fileService.createExcel("templates/config.xlsx","系统参数.xlsx",Maps.newHashMap("list", result.getRecords()));
        return ResponseResult.success(fileInfo);
    }

    /**
     * 修改或添加参数
     * @param cfg
     * @return
     */
    @PostMapping()
//    @BussinessLog(value = "编辑参数", key = "cfgName")
    public ResponseResult save(@ModelAttribute @Valid SysCfg cfg){
        if(cfg.getId()!=null){
            SysCfg old = cfgService.getById(cfg.getId());
            LogObjectHolder.me().set(old);
            old.setCfgName(cfg.getCfgName());
            old.setCfgValue(cfg.getCfgValue());
            old.setCfgDesc(cfg.getCfgDesc());
            cfgService.saveOrUpdate(old);
        }else {
            cfgService.saveOrUpdate(cfg);
        }
        return ResponseResult.success();
    }

    /**
     * 删除参数
     * @param id
     * @return
     */
    @DeleteMapping()
//    @BussinessLog(value = "删除参数", key = "id")
    public ResponseResult remove(@RequestParam Long id){
        log.info("id:{}",id);
        cfgService.deleteById(id);
        return ResponseResult.success();
    }

    /**
     * 修改参数
     * @param json
     * @return
     */
    @RequestMapping(value="saveGroup",method = RequestMethod.POST)
//    @BussinessLog(value = "编辑参数")
    public ResponseResult saveGroup(String json){

        Map<String,String> map = Json.fromJson(Map.class,json);
        for(Map.Entry<String,String> entry:map.entrySet()){
            cfgService.update(entry.getKey(),entry.getValue());
        }
        return ResponseResult.success();
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
    @GetMapping("getCfgValue/{cfgName}")
    public String getCfgValue(@PathVariable String cfgName){
        String cfgValue = cfgService.getCfgValue(cfgName);
        return cfgValue;
    }
}
