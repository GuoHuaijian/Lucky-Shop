package com.lucky.shop.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.shop.admin.system.domain.SysNotice;

import java.util.List;

/**
 * 通知
 *
 * @Author Guo Huaijian
 * @Date 2020/6/14 21:58
 */
public interface SysNoticeService extends IService<SysNotice> {

    /**
     * 通知列表
     *
     * @param condition
     * @return
     */
    List<SysNotice> list(String condition);

}
